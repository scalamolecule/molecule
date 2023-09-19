package molecule.sql.postgres.transaction

import java.sql.{Statement, PreparedStatement => PS}
import java.util.Date
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveUpdate
import molecule.core.transaction.ops.UpdateOps
import molecule.sql.core.transaction.{JdbcBase_JVM, Table}
import molecule.sql.h2.transaction.Update_h2
import molecule.sql.postgres.query.Model2SqlQuery_postgres
import scala.annotation.tailrec

trait Update_postgres
  extends Update_h2 { self: ResolveUpdate =>

  override def updateSetSwap[T](
    a: AttrSet,
    sets: Seq[Set[T]],
    transform: T => Any,
    handleValue: T => Any,
    refNs: Option[String],
    exts: List[String]
  ): Unit = {
    val (retracts0, adds0) = sets.splitAt(sets.length / 2)
    val (retracts, adds)   = (retracts0.flatten, adds0.flatten)
    val swaps              = retracts.zip(adds)
    if (retracts.isEmpty) {
      // Do nothing if no swap pairs
      return
    }
    val count = retracts.length
    if (count != retracts.distinct.length) {
      throw ExecutionError(s"Can't swap from duplicate retract values.")
    }
    if (adds.length != adds.distinct.length) {
      throw ExecutionError(s"Can't swap to duplicate replacement values.")
    }
    if (retracts.size != adds.size) {
      throw ExecutionError(
        s"""Can't swap duplicate keys/values:
           |  RETRACTS: $retracts
           |  ADDS    : $adds
           |""".stripMargin
      )
    }
    val attr         = a.attr
    val placeholders = adds.map(_ => "?").mkString(", ")
    val dbType       = exts(1)
    refNs.fold {
      updateCurRefPath(attr)
      val colSetter = if (isUpsert) {
        placeHolders = placeHolders :+
          s"""$attr = (
             |    SELECT array(
             |      SELECT unnest($attr|| array[$placeholders]::$dbType[]) EXCEPT
             |      SELECT unnest(array[$placeholders]::$dbType[])
             |    )
             |  )""".stripMargin

        (ps: PS, _: IdsMap, _: RowIndex) => {
          adds.foreach { add =>
            handleValue(add).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
            curParamIndex += 1
          }
          retracts.foreach { retract =>
            handleValue(retract).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
            curParamIndex += 1
          }
        }
      } else {
        val cast = exts.head
        @tailrec
        def replace(swaps: Int, calls: String = "", args: String = ""): String = {
          if (swaps == 0)
            s"$calls$attr$args"
          else
            replace(swaps - 1, s"ARRAY_REPLACE($calls", s", ?$cast, ?$cast)$args")
        }
        placeHolders = placeHolders :+ (s"$attr = " + replace(count))
        (ps: PS, _: IdsMap, _: RowIndex) => {
          swaps.foreach { case (retract, add) =>
            handleValue(retract).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
            handleValue(add).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex + 1)
            curParamIndex += 2
          }
        }
      }
      addColSetter(curRefPath, colSetter)

    } { refNs =>
      // Separate update of ref ids in join table -----------------------------
      val ns        = a.ns
      val refAttr   = a.attr
      val joinTable = s"${ns}_${refAttr}_$refNs"
      val ns_id     = ns + "_id"
      val refNs_id  = refNs + "_id"
      val id        = getUpdateId
      if (isUpsert) {
        val retractIds = retracts.mkString(s" AND $refNs_id IN (", ", ", ")")
        manualTableDatas = List(
          // Add joins regardless if the old ref id was present
          addJoins(joinTable, ns_id, refNs_id, id, adds.asInstanceOf[Seq[Long]]),
          deleteJoins(joinTable, ns_id, id, retractIds)
        )
      } else {
        val matches   = swaps.map {
          case (oldId, newId) => s"WHEN $refNs_id = $oldId THEN $newId"
        }.mkString("\n      ")
        val swapJoins =
          s"""UPDATE $joinTable
             |SET
             |  $refNs_id =
             |    CASE
             |      $matches
             |      ELSE $refNs_id
             |    END
             |WHERE $ns_id = $id""".stripMargin
        val swapPS    = sqlConn.prepareStatement(swapJoins)
        val swap      = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
        val swapPath  = List("swapJoins")

        // Do updates only (no new ref ids inserted if old ref id was not there)
        manualTableDatas = List(Table(swapPath, swapJoins, swapPS, swap))
      }
    }
  }

  override def updateSetRemove[T](
    a: AttrSet,
    set: Set[T],
    transform: T => Any,
    handleValue: T => Any,
    refNs: Option[String],
    exts: List[String]
  ): Unit = {
    val attr = a.attr
    refNs.fold {
      updateCurRefPath(attr)
      val colSetter = if (set.nonEmpty) {
        if (!isUpsert) {
          addToUpdateCols(a)
        }
        val cast = exts.head
        @tailrec
        def remove(swaps: Int, calls: String = "", args: String = ""): String = {
          if (swaps == 0)
            s"$calls$attr$args"
          else
            remove(swaps - 1, s"ARRAY_REMOVE($calls", s", ?$cast)$args")
        }
        placeHolders = placeHolders :+ (s"$attr = " + remove(set.size))
        (ps: PS, _: IdsMap, _: RowIndex) => {
          set.foreach { v =>
            handleValue(v).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
            curParamIndex += 1
          }
        }

      } else {
        // Keep as is
        placeHolders = placeHolders :+ s"$attr = $attr"
        (_: PS, _: IdsMap, _: RowIndex) => ()
      }
      addColSetter(curRefPath, colSetter)

    } { refNs =>
      if (set.nonEmpty) {
        // Separate update of ref ids in join table -----------------------------
        val ns         = a.ns
        val refAttr    = a.attr
        val joinTable  = s"${ns}_${refAttr}_$refNs"
        val ns_id      = ns + "_id"
        val refNs_id   = refNs + "_id"
        val retractIds = set.mkString(s" AND $refNs_id IN (", ", ", ")")
        manualTableDatas = List(
          deleteJoins(joinTable, ns_id, getUpdateId, retractIds)
        )
      }
    }
  }

  override protected lazy val extsString     = List("",       "VARCHAR")
  override protected lazy val extsInt        = List("",       "INTEGER")
  override protected lazy val extsLong       = List("",       "BIGINT")
  override protected lazy val extsFloat      = List("",       "DECIMAL")
  override protected lazy val extsDouble     = List("",       "DECIMAL")
  override protected lazy val extsBoolean    = List("",       "BOOLEAN")
  override protected lazy val extsBigInt     = List("",       "DECIMAL")
  override protected lazy val extsBigDecimal = List("",       "DECIMAL")
  override protected lazy val extsDate       = List("",       "DATE")
  override protected lazy val extsUUID       = List("::uuid", "UUID")
  override protected lazy val extsURI        = List("",       "VARCHAR")
  override protected lazy val extsByte       = List("",       "SMALLINT")
  override protected lazy val extsShort      = List("",       "SMALLINT")
  override protected lazy val extsChar       = List("",       "TEXT")
}