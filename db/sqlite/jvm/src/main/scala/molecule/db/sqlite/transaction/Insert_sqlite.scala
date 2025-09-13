package molecule.db.sqlite.transaction

import java.sql.PreparedStatement as PS
import java.util.Date
import molecule.db.common.transaction.{InsertResolvers, ResolveInsert, SqlInsert}

trait Insert_sqlite extends SqlInsert { self: ResolveInsert =>

  override protected def addSet[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    cast = ""
    addIterable(attr, paramIndex, tplIndex, value2json)
  }

  override protected def addSetOpt[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    cast = ""
    addOptIterable(attr, paramIndex, tplIndex, value2json)
  }

  override protected def addSeq[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    cast = ""
    addIterable(attr, paramIndex, tplIndex, value2json)
  }

  override protected def addSeqOpt[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    cast = ""
    addOptIterable(attr, paramIndex, tplIndex, value2json)
  }

  override protected def addMap[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    cast = ""
    (ps: PS, tpl: Product) => {
      tpl.productElement(tplIndex).asInstanceOf[Map[String, ?]] match {
        case map if map.nonEmpty =>
          ps.setString(
            paramIndex,
            map2json(map.asInstanceOf[Map[String, T]], value2json)
          )

        case _ =>
          ps.setNull(paramIndex, java.sql.Types.NULL)
      }
    }
  }

  override protected def addMapOpt[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    cast = ""
    (ps: PS, tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case Some(map: Map[_, _]) if map.nonEmpty =>
          ps.setString(
            paramIndex,
            map2json(map.asInstanceOf[Map[String, T]], value2json)
          )

        case _ => ps.setNull(paramIndex, java.sql.Types.NULL)
      }
    }
  }


  // Helpers -------------------------------------------------------------------

  private def addIterable[T, M[_] <: Iterable[?]](
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    cast = ""
    (ps: PS, tpl: Product) => {
      val iterable = tpl.productElement(tplIndex).asInstanceOf[Iterable[T]]
      if (iterable.nonEmpty) {
        val json = iterable2json(iterable, value2json)
        ps.setString(paramIndex, json)
      } else {
        ps.setNull(paramIndex, java.sql.Types.NULL)
      }
    }
  }

  private def addOptIterable[T, M[_] <: Iterable[?]](
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    cast = ""
    (ps: PS, tpl: Product) =>
      tpl.productElement(tplIndex) match {
        case Some(iterable: Iterable[_]) =>
          if (iterable.nonEmpty) {
            val json = iterable2json(iterable.asInstanceOf[Iterable[T]], value2json)
            ps.setString(paramIndex, json)
          } else {
            ps.setNull(paramIndex, java.sql.Types.NULL)
          }
        case None                        =>
          ps.setNull(paramIndex, java.sql.Types.NULL)
      }
  }

  // Save Floats as Doubles (REAL PRECISION) in SQlite
  override protected lazy val transformFloat =
    (v: Float) => (ps: PS, n: Int) => ps.setDouble(n, v.toString.toDouble)

  override protected lazy val transformDate =
    (v: Date) => (ps: PS, n: Int) => ps.setLong(n, v.getTime)


  override protected lazy val setterFloat =
    (ps: PS, n: Int, v: Float) => ps.setDouble(n, v.toString.toDouble)

  override protected lazy val setterDate =
    (ps: PS, n: Int, v: Date) => ps.setLong(n, v.getTime)
}