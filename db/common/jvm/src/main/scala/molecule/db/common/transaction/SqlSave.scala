package molecule.db.common.transaction

import java.sql.PreparedStatement as PS
import boopickle.Default.*
import molecule.core.dataModel.*
import molecule.db.common.transaction.strategy.SqlOps
import molecule.db.common.transaction.strategy.save.{SaveAction, SaveRoot}
import molecule.db.common.util.SerializationUtils


trait SqlSave extends ValueTransformers with SerializationUtils { self: ResolveSave =>

  protected def addOne[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    optValue: Option[T],
    valueSetter: (PS, Int, T) => Unit,
    exts: List[String] = Nil
  ): (PS, Product) => Unit = {
    optValue.fold {
      (ps: PS, _: Product) => ps.setNull(paramIndex, java.sql.Types.NULL)
    } { value =>
      (ps: PS, _: Product) => valueSetter(ps, paramIndex, value)
    }
  }

  protected def addSet[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    optSet: Option[Set[T]],
    valueSetter: (PS, Int, T) => Unit,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    addIterable(attr, paramIndex, optSet, exts(1), set2array)
  }

  protected def addSeq[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    optSeq: Option[Seq[T]],
    valueSetter: (PS, Int, T) => Unit,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    addIterable(attr, paramIndex, optSeq, exts(1), seq2array)
  }

  protected def addByteArray(
    ent: String,
    attr: String,
    paramIndex: Int,
    optArray: Option[Array[Byte]],
  ): (PS, Product) => Unit = {
    if (optArray.nonEmpty && optArray.get.nonEmpty) {
      (ps: PS, _: Product) => ps.setBytes(paramIndex, optArray.get)
    } else {
      (ps: PS, _: Product) => ps.setNull(paramIndex, java.sql.Types.NULL)
    }
  }

  protected def addMap[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    optMap: Option[Map[String, T]],
    valueSetter: (PS, Int, T) => Unit,
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    optMap match {
      case Some(map: Map[_, _]) if map.nonEmpty =>
        (ps: PS, _: Product) => ps.setBytes(paramIndex, map2jsonByteArray(map, value2json))
      case _                                    =>
        (ps: PS, _: Product) => ps.setNull(paramIndex, java.sql.Types.NULL)
    }
  }


  private def addIterable[T, M[_] <: Iterable[?]](
    attr: String,
    paramIndex: Int,
    optIterable: Option[M[T]],
    sqlTpe: String,
    iterable2array: M[T] => Array[AnyRef],
  ): (PS, Product) => Unit = {
    if (optIterable.nonEmpty && optIterable.get.nonEmpty) {
      val iterable = optIterable.get
      (ps: PS, _: Product) =>
        val conn  = ps.getConnection
        val array = conn.createArrayOf(sqlTpe, iterable2array(iterable))
        ps.setArray(paramIndex, array)
    } else {
      (ps: PS, _: Product) => ps.setNull(paramIndex, java.sql.Types.NULL)
    }
  }
}