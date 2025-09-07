package molecule.db.common.transaction

import java.sql.PreparedStatement as PS
import molecule.core.dataModel.*
import molecule.db.common.transaction.strategy.{SqlAction, SqlOps}
import molecule.db.common.transaction.strategy.insert.{InsertAction, InsertRoot}


trait SqlInsert extends ValueTransformers { self: ResolveInsert =>

  protected def addOne[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    valueSetter: (PS, Int, T) => Unit,
    exts: List[String] = Nil
  ): (PS, Product) => Unit = {
    (ps: PS, tpl: Product) =>
      valueSetter(ps, paramIndex, tpl.productElement(tplIndex).asInstanceOf[T])
  }

  protected def addOneOpt[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    valueSetter: (PS, Int, T) => Unit,
    exts: List[String] = Nil
  ): (PS, Product) => Unit = {
    (ps: PS, tpl: Product) =>
      tpl.productElement(tplIndex) match {
        case Some(scalaValue) => valueSetter(ps, paramIndex, scalaValue.asInstanceOf[T])
        case None             => ps.setNull(paramIndex, java.sql.Types.NULL)
      }
  }

  protected def addSet[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    addIterable(attr, exts(1), paramIndex, tplIndex, set2array)
  }

  private def addIterable[T, M[_] <: Iterable[?]](
    attr: String,
    sqlTpe: String,
    paramIndex: Int,
    tplIndex: Int,
    iterable2array: M[T] => Array[AnyRef],
  ): (PS, Product) => Unit = {
    (ps: PS, tpl: Product) => {
      val array = iterable2array(tpl.productElement(tplIndex).asInstanceOf[M[T]])
      if (array.nonEmpty) {
        val conn = ps.getConnection
        val arr  = conn.createArrayOf(sqlTpe, array)
        ps.setArray(paramIndex, arr)
      } else {
        ps.setNull(paramIndex, java.sql.Types.NULL)
      }
    }
  }

  protected def addSetOpt[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    addOptIterable(attr, exts(1), paramIndex, tplIndex, set2array)
  }

  private def addOptIterable[T, M[_] <: Iterable[?]](
    attr: String,
    sqlTpe: String,
    paramIndex: Int,
    tplIndex: Int,
    iterable2array: M[T] => Array[AnyRef],
  ): (PS, Product) => Unit = {
    (ps: PS, tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case Some(iterable: Iterable[_]) =>
          val array = iterable2array(iterable.asInstanceOf[M[T]])
          val conn  = ps.getConnection
          val arr   = conn.createArrayOf(sqlTpe, array)
          ps.setArray(paramIndex, arr)

        case None => ps.setNull(paramIndex, java.sql.Types.NULL)
      }
    }
  }

  protected def addSeq[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    addIterable(attr, exts(1), paramIndex, tplIndex, seq2array)
  }

  protected def addSeqOpt[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    addOptIterable(attr, exts(1), paramIndex, tplIndex, seq2array)
  }

  protected def addByteArray(
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
  ): (PS, Product) => Unit = {
    (ps: PS, tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case byteArray: Array[_] if byteArray.nonEmpty =>
          ps.setBytes(paramIndex, byteArray.asInstanceOf[Array[Byte]])

        case Some(byteArray: Array[_]) if !byteArray.isEmpty =>
          ps.setBytes(paramIndex, byteArray.asInstanceOf[Array[Byte]])

        case _ => ps.setNull(paramIndex, 0)
      }
    }
  }

  protected def addMap[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    (ps: PS, tpl: Product) => {
      tpl.productElement(tplIndex).asInstanceOf[Map[String, ?]] match {
        case map if map.nonEmpty =>
          ps.setBytes(
            paramIndex,
            map2jsonByteArray(map.asInstanceOf[Map[String, T]], value2json)
          )

        case _ => ps.setNull(paramIndex, java.sql.Types.NULL)
      }
    }
  }

  protected def addMapOpt[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    (ps: PS, tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case Some(map: Map[_, _]) if map.nonEmpty =>
          ps.setBytes(
            paramIndex,
            map2jsonByteArray(map.asInstanceOf[Map[String, T]], value2json)
          )

        case _ => ps.setNull(paramIndex, java.sql.Types.NULL)
      }
    }
  }
}