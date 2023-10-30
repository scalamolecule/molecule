package molecule.sql.postgres.transaction

import java.sql.{Statement, PreparedStatement => PS}
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ResolveUpdate
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.{SqlUpdate, Table}
import molecule.sql.postgres.query.Model2SqlQuery_postgres
import scala.annotation.tailrec

trait Update_postgres extends SqlUpdate { self: ResolveUpdate =>

  override def model2SqlQuery(elements: List[Element]): Model2SqlQuery[Any] =
    new Model2SqlQuery_postgres[Any](elements)

  override def updateSetSwap[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
    transform: T => Any,
    handleValue: T => Any,
    refNs: Option[String],
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
    one2json: T => String
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
      val refAttr   = attr
      val joinTable = ss(ns, refAttr, refNs)
      val ns_id     = ss(ns, "id")
      val refNs_id  = ss(refNs, "id")
      val id        = getUpdateId
      if (isUpsert) {
        val retractIds = retracts.mkString(s" AND $refNs_id IN (", ", ", ")")
        manualTableDatas = List(
          // Add joins regardless if the old ref id was present
          addJoins(joinTable, ns_id, refNs_id, id, adds.map(_.asInstanceOf[String].toLong)),
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
        val swapPS    = sqlConn.prepareStatement(swapJoins, Statement.RETURN_GENERATED_KEYS)
        val swap      = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
        val swapPath  = List("swapJoins")

        // Do updates only (no new ref ids inserted if old ref id was not there)
        manualTableDatas = List(Table(swapPath, swapJoins, swapPS, swap))
      }
    }
  }

  override def updateSetRemove[T](
    ns: String,
    attr: String,
    set: Set[T],
    transform: T => Any,
    handleValue: T => Any,
    refNs: Option[String],
    exts: List[String],
    one2json: T => String
  ): Unit = {
    refNs.fold {
      if (set.nonEmpty) {
        updateCurRefPath(attr)
        if (!isUpsert) {
          addToUpdateCols(ns, attr)
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
        addColSetter(curRefPath, (ps: PS, _: IdsMap, _: RowIndex) => {
          set.foreach { v =>
            handleValue(v).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
            curParamIndex += 1
          }
        })
      }
    } { refNs =>
      if (set.nonEmpty) {
        // Separate update of ref ids in join table -----------------------------
        val refAttr    = attr
        val joinTable  = ss(ns, refAttr, refNs)
        val ns_id      = ss(ns, "id")
        val refNs_id   = ss(refNs, "id")
        val retractIds = set.mkString(s" AND $refNs_id IN (", ", ", ")")
        manualTableDatas = List(
          deleteJoins(joinTable, ns_id, getUpdateId, retractIds)
        )
      }
    }
  }

  override protected lazy val extsString         = List("", "VARCHAR")
  override protected lazy val extsInt            = List("", "INTEGER")
  override protected lazy val extsLong           = List("", "BIGINT")
  override protected lazy val extsFloat          = List("", "DECIMAL")
  override protected lazy val extsDouble         = List("", "DECIMAL")
  override protected lazy val extsBoolean        = List("", "BOOLEAN")
  override protected lazy val extsBigInt         = List("", "DECIMAL")
  override protected lazy val extsBigDecimal     = List("", "DECIMAL")
  override protected lazy val extsDate           = List("", "BIGINT")
  override protected lazy val extsDuration       = List("", "VARCHAR")
  override protected lazy val extsInstant        = List("", "VARCHAR")
  override protected lazy val extsLocalDate      = List("", "VARCHAR")
  override protected lazy val extsLocalTime      = List("", "VARCHAR")
  override protected lazy val extsLocalDateTime  = List("", "VARCHAR")
  override protected lazy val extsOffsetTime     = List("", "VARCHAR")
  override protected lazy val extsOffsetDateTime = List("", "VARCHAR")
  override protected lazy val extsZonedDateTime  = List("", "VARCHAR")
  override protected lazy val extsUUID           = List("::uuid", "UUID")
  override protected lazy val extsURI            = List("", "VARCHAR")
  override protected lazy val extsByte           = List("", "SMALLINT")
  override protected lazy val extsShort          = List("", "SMALLINT")
  override protected lazy val extsChar           = List("", "TEXT")
}