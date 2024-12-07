package molecule.sql.sqlite.transaction

import java.sql.{PreparedStatement => PS}
import molecule.core.transaction.ResolveUpdate
import molecule.sql.core.transaction.SqlUpdate
import molecule.sql.core.transaction.strategy.SqlOps

trait Update_sqlite
  extends SqlUpdate { self: ResolveUpdate with SqlOps =>

  override def handleReplaceAll[T](attr: String, vs: Seq[T]) = s"REPLACE($attr, ?, '${vs(1)}')"


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
        setAttrPresence(ns, attr)
        val setAttr    =
          s"""$attr = (
             |    SELECT JSON_GROUP_ARRAY(VALUE)
             |    FROM (
             |      SELECT _vs.value FROM $ns AS _t, JSON_EACH($attr) AS _vs WHERE _t.id = $ns.id
             |      UNION
             |      SELECT _vs.value FROM JSON_EACH(?) AS _vs
             |    )
             |  )""".stripMargin
        val json       = iterable2json(set, value2json)
        val paramIndex = updateAction.setCol(setAttr)
        updateAction.addColSetter((ps: PS) => ps.setString(paramIndex, json))
      }
    } { refNs =>
      if (set.nonEmpty) {
        updateAction.insertRefIds(attr, refNs, set.asInstanceOf[Set[Long]])
      }
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
        setAttrPresence(ns, attr)
        val retractValues = set.map(one2json).mkString(", ")
        updateAction.setCol(
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
        )
        updateAction.addColSetter((_: PS) => ())
      }
    } { refNs =>
      if (set.nonEmpty) {
        val refIds = set.asInstanceOf[Set[Long]]
        updateAction.deleteRefIds(attr, refNs, getUpdateId, refIds)
      }
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
        setAttrPresence(ns, attr)
        val setAttr    =
          s"""$attr = (
             |    SELECT JSON_GROUP_ARRAY(VALUE)
             |    FROM (
             |      SELECT _vs.value FROM $ns as _t, JSON_EACH($attr) AS _vs WHERE _t.id = $ns.id
             |      UNION ALL
             |      SELECT _vs.value FROM JSON_EACH(?) AS _vs
             |    )
             |  )""".stripMargin
        val json       = iterable2json(seq, value2json)
        val paramIndex = updateAction.setCol(setAttr)
        updateAction.addColSetter((ps: PS) => ps.setString(paramIndex, json))
      }
    } { refNs =>
      if (seq.nonEmpty) {
        updateAction.insertRefIds(attr, refNs, seq.asInstanceOf[Set[Long]])
      }
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
        setAttrPresence(ns, attr)
        val retractValues = seq.map(one2json).mkString(", ")
        updateAction.setCol(
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
        )
        updateAction.addColSetter((_: PS) => ())
      }
    } { refNs =>
      if (seq.nonEmpty) {
        updateAction.insertRefIds(attr, refNs, seq.asInstanceOf[Set[Long]])
      }
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
    val paramIndex = updateAction.setCol(s"$attr = ?")
    if (map.isEmpty) {
      updateAction.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
    } else {
      setAttrPresence(ns, attr)
      updateAction.addColSetter((ps: PS) =>
        ps.setString(paramIndex, map2json(map, value2json))
      )
    }
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
      setAttrPresence(ns, attr)
      val pairs   = map.flatMap {
        case (k, v) => List(
          s"'$$.${validKey(k)}'",
          value2json(new StringBuffer(1), v).toString
        )
      }.mkString(", ")
      val setAttr =
        s"$attr = JSON_SET(IFNULL($attr, JSON_OBJECT()), $pairs)"
      updateAction.setCol(setAttr)
      updateAction.addColSetter((_: PS) => ())
    }
  }

  override protected def updateMapRemove(
    ns: String,
    attr: String,
    optRefNs: Option[String],
    keys: Seq[String],
    exts: List[String],
  ): Unit = {
    if (keys.nonEmpty) {
      setAttrPresence(ns, attr)
      val keys1   = keys.map(k => s"'$$.${validKey(k)}'").mkString(", ")
      val setAttr =
        s"""$attr = (
           |    CASE JSON_REMOVE($attr, $keys1)
           |    WHEN '{}' THEN NULL
           |    WHEN NULL THEN $attr
           |    ELSE JSON_REMOVE($attr, $keys1)
           |    END
           |  )""".stripMargin
      updateAction.setCol(setAttr)
      updateAction.addColSetter((_: PS) => ())
    }
  }


  // Helpers -------------------------------------------------------------------

  private def updateIterableEq[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    iterable: M[T],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    optRefNs.fold {
      val paramIndex = updateAction.setCol(s"$attr = ?")
      if (iterable.nonEmpty) {
        setAttrPresence(ns, attr)
        updateAction.addColSetter((ps: PS) => {
          val json = iterable2json(iterable.asInstanceOf[Iterable[T]], value2json)
          ps.setString(paramIndex, json)
        })
      } else {
        updateAction.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
      }
    } { refNs =>
      updateAction.deleteRefIds(attr, refNs, getUpdateId)
      val refIds = iterable.asInstanceOf[Set[Long]]
      if (refIds.nonEmpty) {
        updateAction.insertRefIds(attr, refNs, refIds)
      }
    }
  }
}