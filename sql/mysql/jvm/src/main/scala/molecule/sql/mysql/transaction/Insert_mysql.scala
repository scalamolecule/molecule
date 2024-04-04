package molecule.sql.mysql.transaction

import java.sql.{PreparedStatement => PS}
import java.util.Date
import molecule.core.transaction.{InsertResolvers_, ResolveInsert}
import molecule.sql.core.transaction.SqlInsert

trait Insert_mysql extends SqlInsert { self: ResolveInsert with InsertResolvers_ =>

  doPrint = false

  override protected def addSet[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addIterable(ns, attr, optRefNs, tplIndex, value2json)
  }

  override protected def addSetOpt[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addOptIterable(ns, attr, optRefNs, tplIndex, value2json)
  }

  override protected def addSeq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addIterable(ns, attr, optRefNs, tplIndex, value2json)
  }

  override protected def addSeqOpt[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addOptIterable(ns, attr, optRefNs, tplIndex, value2json)
  }

  override protected def addMap[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val (curPath, paramIndex) = getParamIndex(attr)
    (tpl: Product) =>
      val colSetter = tpl.productElement(tplIndex).asInstanceOf[Map[String, _]] match {
        case map if map.nonEmpty =>
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setString(paramIndex, map2json(map.asInstanceOf[Map[String, T]], value2json))

        case _ =>
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setNull(paramIndex, java.sql.Types.NULL)
      }
      addColSetter(curPath, colSetter)
  }

  override protected def addMapOpt[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val (curPath, paramIndex) = getParamIndex(attr)
    (tpl: Product) =>
      val colSetter = tpl.productElement(tplIndex) match {
        case Some(map: Map[_, _]) if map.nonEmpty =>
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setString(paramIndex, map2json(map.asInstanceOf[Map[String, T]], value2json))

        case _ =>
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setNull(paramIndex, java.sql.Types.NULL)
      }
      addColSetter(curPath, colSetter)
  }

  override protected lazy val transformDate =
    (v: Date) => (ps: PS, n: Int) => ps.setLong(n, v.getTime)


  // Helpers -------------------------------------------------------------------

  private def addIterable[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    optRefNs.fold {
      val (curPath, paramIndex) = getParamIndex(attr)
      (tpl: Product) => {
        val iterable  = tpl.productElement(tplIndex).asInstanceOf[Iterable[T]]
        val colSetter = if (iterable.nonEmpty) {
          val json = iterable2json(iterable, value2json)
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setString(paramIndex, json)
        } else {
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setNull(paramIndex, java.sql.Types.NULL)
        }
        addColSetter(curPath, colSetter)
      }
    } { refNs =>
      join(ns, attr, refNs, tplIndex)
    }
  }

  private def addOptIterable[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    optRefNs.fold {
      val (curPath, paramIndex) = getParamIndex(attr)
      (tpl: Product) => {
        val colSetter = tpl.productElement(tplIndex) match {
          case Some(iterable: Iterable[_]) =>
            if (iterable.nonEmpty) {
              val json = iterable2json(iterable.asInstanceOf[Iterable[T]], value2json)
              (ps: PS, _: IdsMap, _: RowIndex) =>
                ps.setString(paramIndex, json)
            } else {
              (ps: PS, _: IdsMap, _: RowIndex) =>
                ps.setNull(paramIndex, java.sql.Types.NULL)
            }
          case None                        =>
            (ps: PS, _: IdsMap, _: RowIndex) =>
              ps.setNull(paramIndex, java.sql.Types.NULL)
        }
        addColSetter(curPath, colSetter)
      }
    } { refNs =>
      optJoin(ns, attr, refNs, tplIndex)
    }
  }
}