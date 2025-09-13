package molecule.db.mariadb.transaction

import java.sql.PreparedStatement as PS
import java.util.Date
import molecule.core.util.BaseHelpers
import molecule.db.common.transaction.strategy.SqlOps
import molecule.db.common.transaction.{ResolveSave, SqlSave}

trait Save_mariadb extends SqlSave with BaseHelpers { self: ResolveSave =>

  override protected def addSet[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    optSet: Option[Set[T]],
    valueSetter: (PS, Int, T) => Unit,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
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
    optMap match {
      case Some(map: Map[_, _]) if map.nonEmpty =>
        (ps: PS, _: Product) =>
          ps.setString(paramIndex, map2json(map.asInstanceOf[Map[String, T]], value2json))
      case _ =>
        (ps: PS, _: Product) =>
          ps.setNull(paramIndex, java.sql.Types.NULL)
    }
  }


  // Helpers -------------------------------------------------------------------

  private def addIterable[T](
    attr: String,
    paramIndex: Int,
    optIterable: Option[Iterable[T]],
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    if (optIterable.nonEmpty && optIterable.get.nonEmpty) {
      val json = iterable2json(optIterable.get, value2json)
      (ps: PS, _: Product) => ps.setString(paramIndex, json)
    } else {
      (ps: PS, _: Product) => ps.setNull(paramIndex, java.sql.Types.NULL)
    }
  }

  override protected lazy val transformDate =
    (v: Date) => (ps: PS, n: Int) => ps.setLong(n, v.getTime)
}