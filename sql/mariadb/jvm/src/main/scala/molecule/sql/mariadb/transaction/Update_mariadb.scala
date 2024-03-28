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
    refNs: Option[String],
    owner: Boolean,
    transformValue: T => Any,
    set2array: Set[T] => Array[AnyRef],
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    refNs.fold {
      updateCurRefPath(attr)
      placeHolders = placeHolders :+ s"$attr = ?"
      val colSetter = sets match {
        case Seq(set) =>
          if (set.nonEmpty) {
            if (!isUpsert) {
              addToUpdateCols(ns, attr)
            }
            val json = set2json(set, value2json)
            (ps: PS, _: IdsMap, _: RowIndex) => {
              ps.setString(curParamIndex, json)
              curParamIndex += 1
            }
          } else {
            (ps: PS, _: IdsMap, _: RowIndex) => {
              ps.setNull(curParamIndex, 0)
              curParamIndex += 1
            }
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
          if (set.nonEmpty) {
            // Tables are reversed in JdbcConn_JVM and we want to delete first
            manualTableDatas = List(
              addJoins(joinTable, ns_id, refNs_id, id, set.map(_.asInstanceOf[String].toLong)),
              deleteJoins(joinTable, ns_id, id)
            )
          } else {
            // Delete all joins when no ref ids are applied
            manualTableDatas = List(deleteJoins(joinTable, ns_id, id))
          }
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
    refNs: Option[String],
    owner: Boolean,
    transformValue: T => Any,
    set2array: Set[T] => Array[AnyRef],
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


  override def updateSetRemove[T](
    ns: String,
    attr: String,
    set: Set[T],
    refNs: Option[String],
    owner: Boolean,
    transformValue: T => Any,
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