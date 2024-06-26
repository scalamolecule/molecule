package molecule.sql.mysql.transaction

import java.sql.{PreparedStatement => PS}
import java.util.Date
import molecule.base.util.BaseHelpers
import molecule.core.transaction.ResolveSave
import molecule.sql.core.transaction.SqlSave

trait Save_mysql extends SqlSave with BaseHelpers { self: ResolveSave =>

  override protected def addSet[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    optSet: Option[Set[T]],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    addIterable(ns, attr, optRefNs, optSet, value2json)
  }

  override protected def addSeq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    optSeq: Option[Seq[T]],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    addIterable(ns, attr, optRefNs, optSeq, value2json)
  }

  override protected def addMap[T](
    ns: String,
    attr: String,
    optMap: Option[Map[String, T]],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    val (curPath, paramIndex) = getParamIndex(attr)
    val colSetter: Setter     = optMap match {
      case Some(map: Map[_, _]) if map.nonEmpty =>
        (ps: PS, _: IdsMap, _: RowIndex) =>
          ps.setString(paramIndex, map2json(map, value2json))
      case _                                    =>
        (ps: PS, _: IdsMap, _: RowIndex) =>
          ps.setNull(paramIndex, 0)
    }
    addColSetter(curPath, colSetter)
  }

  override protected lazy val transformDate =
    (v: Date) => (ps: PS, n: Int) => ps.setLong(n, v.getTime)


  // Helpers -------------------------------------------------------------------

  private def addIterable[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    optIterable: Option[Iterable[T]],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    optRefNs.fold {
      val (curPath, paramIndex) = getParamIndex(attr)
      val colSetter: Setter     = if (optIterable.nonEmpty && optIterable.get.nonEmpty) {
        val json = iterable2json(optIterable.get, value2json)
        (ps: PS, _: IdsMap, _: RowIndex) => ps.setString(paramIndex, json)
      } else {
        (ps: PS, _: IdsMap, _: RowIndex) => ps.setNull(paramIndex, 0)
      }
      addColSetter(curPath, colSetter)
    } { refNs =>
      join(ns, attr, refNs, optIterable)
    }
  }
}