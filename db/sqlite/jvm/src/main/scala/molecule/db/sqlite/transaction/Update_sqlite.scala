package molecule.db.sqlite.transaction

import java.sql.PreparedStatement as PS
import molecule.db.common.transaction.strategy.SqlOps
import molecule.db.common.transaction.{ResolveUpdate, SqlUpdate}

trait Update_sqlite extends SqlUpdate { self: ResolveUpdate =>

  override def handleReplaceAll[T](attr: String, vs: Seq[T]) = s"REPLACE($attr, ?, '${vs(1)}')"


  override def updateSetEq[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (String, PS => Unit) = {
    updateIterableEq(ent, attr, paramIndex, set, value2json)
  }

  override def updateSetAdd[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (String, PS => Unit) = {
    if (set.isEmpty) {
      (s"$attr = $attr", (ps: PS) => ()) // unchanged value
    } else {
      val colInput =
        s"""$attr = (
           |    SELECT JSON_GROUP_ARRAY(VALUE)
           |    FROM (
           |      SELECT _vs.value FROM $ent AS _t, JSON_EACH($attr) AS _vs WHERE _t.id = $ent.id
           |      UNION
           |      SELECT _vs.value FROM JSON_EACH(?) AS _vs
           |    )
           |  )""".stripMargin
      val json     = iterable2json(set, value2json)
      (colInput, (ps: PS) => ps.setString(paramIndex, json))
    }
  }

  override def updateSetRemove[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String,
    set2array: Set[T] => Array[AnyRef]
  ): (String, PS => Unit) = {
    if (set.isEmpty) {
      (s"$attr = $attr", (ps: PS) => ()) // unchanged value
    } else {
      val retractValues = set.map(one2json).mkString(", ")
      val colInput      =
        s"""$attr = (
           |    SELECT (
           |      CASE JSON_GROUP_ARRAY(VALUE)
           |        WHEN '[]' THEN NULL
           |        ELSE JSON_GROUP_ARRAY(VALUE)
           |      END
           |    )
           |    FROM (
           |      SELECT _vs.value
           |      FROM $ent AS _t, JSON_EACH($attr) AS _vs
           |      WHERE
           |        _t.id = $ent.id AND
           |        _vs.VALUE NOT IN ($retractValues)
           |    )
           |  )""".stripMargin

      (colInput, (_: PS) => ())
    }
  }

  override def updateSeqEq[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (String, PS => Unit) = {
    updateIterableEq(ent, attr, paramIndex, seq, value2json)
  }

  override def updateSeqAdd[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (String, PS => Unit) = {
    if (seq.isEmpty) {
      (s"$attr = $attr", (ps: PS) => ()) // unchanged value
    } else {
      val colInput =
        s"""$attr = (
           |    SELECT JSON_GROUP_ARRAY(VALUE)
           |    FROM (
           |      SELECT _vs.value FROM $ent as _t, JSON_EACH($attr) AS _vs WHERE _t.id = $ent.id
           |      UNION ALL
           |      SELECT _vs.value FROM JSON_EACH(?) AS _vs
           |    )
           |  )""".stripMargin
      val json     = iterable2json(seq, value2json)
      (colInput, (ps: PS) => ps.setString(paramIndex, json))
    }
  }

  override def updateSeqRemove[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String,
    seq2array: Seq[T] => Array[AnyRef]
  ): (String, PS => Unit) = {
    if (seq.isEmpty) {
      (s"$attr = $attr", (ps: PS) => ()) // unchanged value
    } else {
      val retractValues = seq.map(one2json).mkString(", ")
      val colInput      =
        s"""$attr = (
           |    SELECT (
           |      CASE JSON_GROUP_ARRAY(VALUE)
           |        WHEN '[]' THEN NULL
           |        ELSE JSON_GROUP_ARRAY(VALUE)
           |      END
           |    )
           |    FROM (
           |      SELECT _vs.value
           |      FROM $ent AS _t, JSON_EACH($attr) AS _vs
           |      WHERE
           |        _t.id = $ent.id AND
           |        _vs.VALUE NOT IN ($retractValues)
           |    )
           |  )""".stripMargin
      (colInput, (_: PS) => ())
    }
  }

  override protected def updateMapEq[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    noValue: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): (String, PS => Unit) = {
    val colSetter = if (map.isEmpty) {
      (ps: PS) => ps.setNull(paramIndex, 0)
    } else {
      (ps: PS) => ps.setString(paramIndex, map2json(map, value2json))
    }
    (s"$attr = ?", colSetter)
  }

  override protected def updateMapAdd[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    map: Map[String, T],
    transformValue: T => Any,
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
  ): (String, PS => Unit) = {
    if (map.isEmpty) {
      (s"$attr = $attr", (ps: PS) => ()) // unchanged value
    } else {
      val pairs    = map.flatMap {
        case (k, v) => List(
          s"'$$.${validKey(k)}'",
          value2json(new StringBuffer(1), v).toString
        )
      }.mkString(", ")
      val colInput = s"$attr = JSON_SET(IFNULL($attr, JSON_OBJECT()), $pairs)"
      (colInput, (_: PS) => ())
    }
  }

  override protected def updateMapRemove(
    ent: String,
    attr: String,
    paramIndex: Int,
    keys: Seq[String],
    exts: List[String],
  ): (String, PS => Unit) = {
    if (keys.isEmpty) {
      (s"$attr = $attr", (ps: PS) => ()) // unchanged value
    } else {
      val keys1    = keys.map(k => s"'$$.${validKey(k)}'").mkString(", ")
      val colInput =
        s"""$attr = (
           |    CASE JSON_REMOVE($attr, $keys1)
           |    WHEN '{}' THEN NULL
           |    WHEN NULL THEN $attr
           |    ELSE JSON_REMOVE($attr, $keys1)
           |    END
           |  )""".stripMargin
      (colInput, (_: PS) => ())
    }
  }


  // Helpers -------------------------------------------------------------------

  private def updateIterableEq[T, M[_] <: Iterable[?]](
    ent: String,
    attr: String,
    paramIndex: Int,
    iterable: M[T],
    value2json: (StringBuffer, T) => StringBuffer
  ): (String, PS => Unit) = {
    val colSetter = if (iterable.nonEmpty) {
      (ps: PS) => {
        val json = iterable2json(iterable.asInstanceOf[Iterable[T]], value2json)
        ps.setString(paramIndex, json)
      }
    } else {
      (ps: PS) => ps.setNull(paramIndex, 0)
    }
    (s"$attr = ?", colSetter)
  }
}