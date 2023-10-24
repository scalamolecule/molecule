package molecule.sql.mariadb.transaction

import java.sql.{Statement, PreparedStatement => PS}
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ResolveUpdate
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.{SqlUpdate, Table}
import molecule.sql.mariadb.query.Model2SqlQuery_mariadb

trait Update_mariadb extends SqlUpdate { self: ResolveUpdate =>

  override def model2SqlQuery(elements: List[Element]): Model2SqlQuery[Any] =
    new Model2SqlQuery_mariadb[Any](elements)


  override def updateSetEq[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
    transform: T => Any,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String],
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    refNs.fold {
      updateCurRefPath(attr)
      placeHolders = placeHolders :+ s"$attr = ?"
      val colSetter = sets match {
        case Seq(set) =>
          if (!isUpsert) {
            addToUpdateCols(ns, attr)
          }
          val json = set2json(set, value2json)
          (ps: PS, _: IdsMap, _: RowIndex) => {
            ps.setString(curParamIndex, json)
            curParamIndex += 1
          }
        case Nil      =>
          (ps: PS, _: IdsMap, _: RowIndex) => {
            ps.setNull(curParamIndex, 0)
            curParamIndex += 1
          }
        case vs       => throw ExecutionError(
          s"Can only $update one Set of values for Set attribute `$ns.$attr`. Found: " + vs.mkString(", ")
        )
      }
      addColSetter(curRefPath, colSetter)

    } { refNs =>
      // Separate update of ref ids in join table -----------------------------
      val refAttr   = attr
      val joinTable = ss(ns, refAttr, refNs)
      val ns_id     = ss(ns, "id")
      val refNs_id  = ss(refNs, "id")
      val id        = getUpdateId
      sets match {
        case Seq(set) =>
          // Tables are reversed in JdbcConn_JVM and we want to delete first
          manualTableDatas = List(
            addJoins(joinTable, ns_id, refNs_id, id, set.map(_.asInstanceOf[String].toLong)),
            deleteJoins(joinTable, ns_id, id)
          )
        case Nil      =>
          // Delete all joins when no ref ids are applied
          manualTableDatas = List(deleteJoins(joinTable, ns_id, id))
        case vs       => throw ExecutionError(
          s"Can only $update one Set of values for Set attribute `$ns.$attr`. Found: " + vs.mkString(", ")
        )
      }
    }
  }

  override def updateSetAdd[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
    transform: T => Any,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String],
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    refNs.fold {
      if (sets.nonEmpty && sets.head.nonEmpty) {
        updateCurRefPath(attr)
        if (!isUpsert) {
          addToUpdateCols(ns, attr)
        }
        placeHolders = placeHolders :+ s"$attr = JSON_MERGE($attr, ?)"
        val json = set2json(sets.head, value2json)
        addColSetter(curRefPath, (ps: PS, _: IdsMap, _: RowIndex) => {
          ps.setString(curParamIndex, json)
          curParamIndex += 1
        })
      }
    } { refNs =>
      // Separate update of ref ids in join table -----------------------------
      val refAttr   = attr
      val joinTable = ss(ns, refAttr, refNs)
      val ns_id     = ss(ns, "id")
      val refNs_id  = ss(refNs, "id")
      sets match {
        case Seq(set) => manualTableDatas = List(
          addJoins(joinTable, ns_id, refNs_id, getUpdateId, set.map(_.asInstanceOf[String].toLong))
        )
        case Nil      => () // Add no ref ids
        case vs       => throw ExecutionError(
          s"Can only $update one Set of values for Set attribute `$ns.$attr`. Found: " + vs.mkString(", ")
        )
      }
    }
  }

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
    refNs.fold {
      updateCurRefPath(attr)
      val valueTable = "table_" + (placeHolders.size + 1)
      val cast       = exts.head
      if (isUpsert) {
        val addValues     = adds.map(one2json).mkString(", ")
        val retractValues = retracts.map(one2json).mkString(", ")
        placeHolders = placeHolders :+
          s"""$attr = (
             |    SELECT JSON_MERGE(JSON_ARRAYAGG($valueTable.v), JSON_ARRAY($addValues))
             |    FROM   JSON_TABLE($ns.$attr, '$$[*]' COLUMNS (v $cast PATH '$$')) $valueTable
             |    WHERE  $valueTable.v NOT IN ($retractValues)
             |  )""".stripMargin
      } else {
        val replacements = retracts.zip(adds).map {
          case (oldV, newV) => s"WHEN $valueTable.v = ${one2json(oldV)} THEN ${one2json(newV)}"
        }.mkString("\n          ")
        placeHolders = placeHolders :+
          s"""$attr = (
             |    SELECT
             |      JSON_ARRAYAGG(
             |        CASE
             |          $replacements
             |          ELSE $valueTable.v
             |        END
             |      )
             |    FROM JSON_TABLE($ns.$attr, '$$[*]' COLUMNS (v $cast PATH '$$')) $valueTable
             |  )""".stripMargin
      }
      addColSetter(curRefPath, (_: PS, _: IdsMap, _: RowIndex) => ())
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
        val valueTable    = "table_" + (placeHolders.size + 1)
        val cast          = exts.head
        val retractValues = set.map(one2json).mkString(", ")
        placeHolders = placeHolders :+
          s"""$attr = (
             |    SELECT JSON_ARRAYAGG($valueTable.v)
             |    FROM   JSON_TABLE($ns.$attr, '$$[*]' COLUMNS (v $cast PATH '$$')) $valueTable
             |    WHERE  $valueTable.v NOT IN ($retractValues)
             |  )""".stripMargin
        addColSetter(curRefPath, (_: PS, _: IdsMap, _: RowIndex) => ())
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

  override protected lazy val extsString         = List("LONGTEXT", "")
  override protected lazy val extsInt            = List("INT", "")
  override protected lazy val extsLong           = List("BIGINT", "")
  override protected lazy val extsFloat          = List("REAL", "")
  override protected lazy val extsDouble         = List("DOUBLE", "")
  override protected lazy val extsBoolean        = List("TINYINT(1)", "")
  override protected lazy val extsBigInt         = List("DECIMAL(65, 0)", "")
  override protected lazy val extsBigDecimal     = List("DECIMAL(65, 30)", "")
  override protected lazy val extsDate           = List("BIGINT", "")
  override protected lazy val extsDuration       = List("TINYTEXT", "")
  override protected lazy val extsInstant        = List("TINYTEXT", "")
  override protected lazy val extsLocalDate      = List("TINYTEXT", "")
  override protected lazy val extsLocalTime      = List("TINYTEXT", "")
  override protected lazy val extsLocalDateTime  = List("TINYTEXT", "")
  override protected lazy val extsOffsetTime     = List("TINYTEXT", "")
  override protected lazy val extsOffsetDateTime = List("TINYTEXT", "")
  override protected lazy val extsZonedDateTime  = List("TINYTEXT", "")
  override protected lazy val extsUUID           = List("TINYTEXT", "")
  override protected lazy val extsURI            = List("TEXT", "")
  override protected lazy val extsByte           = List("TINYINT", "")
  override protected lazy val extsShort          = List("SMALLINT", "")
  override protected lazy val extsChar           = List("CHAR", "")
}