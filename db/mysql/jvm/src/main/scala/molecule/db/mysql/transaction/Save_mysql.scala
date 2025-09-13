package molecule.db.mysql.transaction

import java.sql.PreparedStatement as PS
import java.util.Date
import molecule.db.common.transaction.{ResolveSave, SqlSave}

trait Save_mysql extends SqlSave { self: ResolveSave =>

  override protected def addSet[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    optSet: Option[Set[T]],
    valueSetter: (PS, Int, T) => Unit,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    cast = ""
    addIterable(attr, paramIndex, optSet, value2json)
  }

  override protected def addSeq[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    optSeq: Option[Seq[T]],
    valueSetter: (PS, Int, T) => Unit,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    cast = ""
    addIterable(attr, paramIndex, optSeq, value2json)
  }

  override protected def addMap[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    optMap: Option[Map[String, T]],
    valueSetter: (PS, Int, T) => Unit,
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    cast = ""
    optMap match {
      case Some(map: Map[_, _]) if map.nonEmpty =>
        (ps: PS, _: Product) => ps.setString(paramIndex, map2json(map, value2json))
      case _                                    =>
        (ps: PS, _: Product) => ps.setNull(paramIndex, 0)
    }
  }


  // Helpers -------------------------------------------------------------------

  private def addIterable[T](
    attr: String,
    paramIndex: Int,
    optIterable: Option[Iterable[T]],
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    cast = ""
    if (optIterable.nonEmpty && optIterable.get.nonEmpty) {
      val json = iterable2json(optIterable.get, value2json)
      (ps: PS, _: Product) => ps.setString(paramIndex, json)
    } else {
      (ps: PS, _: Product) => ps.setNull(paramIndex, 0)
    }
  }

  override protected lazy val transformDate =
    (v: Date) => (ps: PS, n: Int) => ps.setLong(n, v.getTime)

  override protected lazy val setterDate  =
    (ps: PS, n: Int, v: Date) => ps.setLong(n, v.getTime)
}