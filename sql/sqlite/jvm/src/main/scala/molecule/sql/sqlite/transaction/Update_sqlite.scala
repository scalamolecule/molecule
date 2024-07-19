package molecule.sql.sqlite.transaction

import java.sql.{PreparedStatement => PS}
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ResolveUpdate
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.SqlUpdate
import molecule.sql.sqlite.query.Model2SqlQuery_sqlite

trait Update_sqlite extends SqlUpdate { self: ResolveUpdate =>

  doPrint = false
  //  doPrint = true

  override def model2SqlQuery(elements: List[Element]): Model2SqlQuery =
    new Model2SqlQuery_sqlite(elements)

  protected var curParamIndex = 1


  override def updateSetEq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateIterableEq(ns, attr, optRefNs, set, value2json)
  }

  override def updateSetAdd[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    optRefNs.fold {
      if (set.nonEmpty) {
        cols += attr
        if (!isUpsert) {
          addToUpdateColsNotNull(attr)
        }
        // Using UNION (without ALL) only adds new values to Set
        placeHolders = placeHolders :+
          s"""$attr = (
             |    SELECT JSON_GROUP_ARRAY(VALUE)
             |    FROM (
             |      SELECT _vs.value FROM $ns AS _t, JSON_EACH($attr) AS _vs WHERE _t.id = $ns.id
             |      UNION
             |      SELECT _vs.value FROM JSON_EACH(?) AS _vs
             |    )
             |  )""".stripMargin
        val json = iterable2json(set, value2json)
        addColSetter(curRefPath, (ps: PS, _: IdsMap, _: RowIndex) => {
          ps.setString(curParamIndex, json)
          curParamIndex += 1
        })
      }
    } { refNs =>
      joinAdd(ns, attr, refNs, set)
    }
  }

  override def updateSetRemove[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String,
    set2array: Set[T] => Array[AnyRef]
  ): Unit = {
    optRefNs.fold {
      if (set.nonEmpty) {
        cols += attr
        if (!isUpsert) {
          addToUpdateColsNotNull(attr)
        }
        val retractValues = set.map(one2json).mkString(", ")
        placeHolders = placeHolders :+
          s"""$attr = (
             |    SELECT (
             |      CASE JSON_GROUP_ARRAY(VALUE)
             |        WHEN '[]' THEN NULL
             |        ELSE JSON_GROUP_ARRAY(VALUE)
             |      END
             |    )
             |    FROM (
             |      SELECT _vs.value
             |      FROM $ns AS _t, JSON_EACH($attr) AS _vs
             |      WHERE
             |        _t.id = $ns.id AND
             |        _vs.VALUE NOT IN ($retractValues)
             |    )
             |  )""".stripMargin
        addColSetter(curRefPath, (_: PS, _: IdsMap, _: RowIndex) => ())
      }
    } { refNs =>
      joinRemove(ns, attr, refNs, set)
    }
  }

  override def updateSeqEq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateIterableEq(ns, attr, optRefNs, seq, value2json)
  }

  override def updateSeqAdd[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    optRefNs.fold {
      if (seq.nonEmpty) {
        cols += attr
        if (!isUpsert) {
          addToUpdateColsNotNull(attr)
        }
        // Using UNION ALL to add even duplicates
        placeHolders = placeHolders :+
          s"""$attr = (
             |    SELECT JSON_GROUP_ARRAY(VALUE)
             |    FROM (
             |      SELECT _vs.value FROM $ns as _t, JSON_EACH($attr) AS _vs WHERE _t.id = $ns.id
             |      UNION ALL
             |      SELECT _vs.value FROM JSON_EACH(?) AS _vs
             |    )
             |  )""".stripMargin
        val json = iterable2json(seq, value2json)
        addColSetter(curRefPath, (ps: PS, _: IdsMap, _: RowIndex) => {
          ps.setString(curParamIndex, json)
          curParamIndex += 1
        })
      }
    } { refNs =>
      joinAdd(ns, attr, refNs, seq)
    }
  }

  override def updateSeqRemove[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String,
    seq2array: Seq[T] => Array[AnyRef]
  ): Unit = {
    optRefNs.fold {
      if (seq.nonEmpty) {
        cols += attr
        if (!isUpsert) {
          addToUpdateColsNotNull(attr)
        }
        val retractValues = seq.map(one2json).mkString(", ")
        placeHolders = placeHolders :+
          s"""$attr = (
             |    SELECT (
             |      CASE JSON_GROUP_ARRAY(VALUE)
             |        WHEN '[]' THEN NULL
             |        ELSE JSON_GROUP_ARRAY(VALUE)
             |      END
             |    )
             |    FROM (
             |      SELECT _vs.value
             |      FROM $ns AS _t, JSON_EACH($attr) AS _vs
             |      WHERE
             |        _t.id = $ns.id AND
             |        _vs.VALUE NOT IN ($retractValues)
             |    )
             |  )""".stripMargin
        addColSetter(curRefPath, (_: PS, _: IdsMap, _: RowIndex) => ())
      }
    } { refNs =>
      joinRemove(ns, attr, refNs, seq)
    }
  }

  override protected def updateMapEq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    noValue: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateMapEqJdbc(attr, "", map,
      (ps: PS, paramIndex: Int) => {
        ps.setString(paramIndex, map2json(map, value2json))
      }
    )
  }

  override protected def updateMapAdd[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    map: Map[String, T],
    transformValue: T => Any,
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
  ): Unit = {
    if (map.nonEmpty) {
      cols += attr
      if (!isUpsert) {
        addToUpdateColsNotNull(attr)
      }
      val pairs = map.flatMap {
        case (k, v) => List(
          s"'$$.${validKey(k)}'",
          value2json(new StringBuffer(1), v).toString
        )
      }.mkString(", ")
      placeHolders = placeHolders :+
        s"$attr = JSON_SET(IFNULL($attr, JSON_OBJECT()), $pairs)"
      addColSetter(curRefPath, (_: PS, _: IdsMap, _: RowIndex) => ())
    }
  }

  override protected def updateMapRemove[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    keys: Seq[String],
    exts: List[String],
  ): Unit = {
    if (keys.nonEmpty) {
      cols += attr
      if (!isUpsert) {
        addToUpdateColsNotNull(attr)
      }
      val keys1 = keys.map(k => s"'$$.${validKey(k)}'").mkString(", ")
      placeHolders = placeHolders :+
        s"""$attr = (
           |    CASE JSON_REMOVE($attr, $keys1)
           |    WHEN '{}' THEN NULL
           |    WHEN NULL THEN $attr
           |    ELSE JSON_REMOVE($attr, $keys1)
           |    END
           |  )""".stripMargin
      addColSetter(curRefPath, (_: PS, _: IdsMap, _: RowIndex) => ())
    }
  }


  // Helpers -------------------------------------------------------------------

  private def updateIterableEq[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    refNs: Option[String],
    iterable: M[T],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    refNs.fold {
      cols += attr
      placeHolders = placeHolders :+ s"$attr = ?"
      val colSetter = if (iterable.nonEmpty) {
        if (!isUpsert) {
          addToUpdateColsNotNull(attr)
        }
        (ps: PS, _: IdsMap, _: RowIndex) => {
          val json = iterable2json(iterable.asInstanceOf[Iterable[T]], value2json)
          ps.setString(curParamIndex, json)
          curParamIndex += 1
        }
      } else {
        (ps: PS, _: IdsMap, _: RowIndex) => {
          ps.setNull(curParamIndex, 0)
          curParamIndex += 1
        }
      }
      addColSetter(curRefPath, colSetter)
    } { refNs =>
      joinEq(ns, attr, refNs, iterable)
    }
  }

}