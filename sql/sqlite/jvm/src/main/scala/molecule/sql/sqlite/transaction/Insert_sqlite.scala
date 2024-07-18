package molecule.sql.sqlite.transaction

import java.sql.{PreparedStatement => PS}
import java.util.Date
import molecule.core.transaction.{InsertResolvers_, ResolveInsert}
import molecule.sql.core.transaction.SqlInsert

trait Insert_sqlite extends SqlInsert with TxBase_sqlite { self: ResolveInsert with InsertResolvers_ =>

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
    val paramIndex   = insert.paramIndex(attr)
    val stableInsert = insert
    (tpl: Product) => {
      tpl.productElement(tplIndex).asInstanceOf[Map[String, _]] match {
        case map if map.nonEmpty =>
          stableInsert.add((ps: PS) =>
            ps.setString(
              paramIndex,
              map2json(map.asInstanceOf[Map[String, T]], value2json)
            ))

        case _ =>
          stableInsert.add((ps: PS) =>
            ps.setNull(paramIndex, java.sql.Types.NULL))
      }
    }
  }

  override protected def addMapOpt[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val paramIndex   = insert.paramIndex(attr)
    val stableInsert = insert
    (tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case Some(map: Map[_, _]) if map.nonEmpty =>
          stableInsert.add((ps: PS) =>
            ps.setString(
              paramIndex,
              map2json(map.asInstanceOf[Map[String, T]], value2json)
            ))

        case _ =>
          stableInsert.add((ps: PS) =>
            ps.setNull(paramIndex, java.sql.Types.NULL))
      }
    }
  }


  // Helpers -------------------------------------------------------------------

  private def addIterable[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val stableInsert = insert
    optRefNs.fold {
      val paramIndex = stableInsert.paramIndex(attr)
      (tpl: Product) => {
        val iterable = tpl.productElement(tplIndex).asInstanceOf[Iterable[T]]
        if (iterable.nonEmpty) {
          val json = iterable2json(iterable, value2json)
          stableInsert.add((ps: PS) =>
            ps.setString(paramIndex, json))
        } else {
          stableInsert.add((ps: PS) =>
            ps.setNull(paramIndex, java.sql.Types.NULL))
        }
      }
    } { refNs =>
      (tpl: Product) => {
        val refIds = tpl.productElement(tplIndex).asInstanceOf[Iterable[Long]]
        stableInsert.addCardManyRefAttr(ns, attr, refNs, refIds.asInstanceOf[Set[Long]])
      }
    }
  }

  private def addOptIterable[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val stableInsert = insert
    optRefNs.fold {
      val paramIndex = stableInsert.paramIndex(attr)
      (tpl: Product) =>
        tpl.productElement(tplIndex) match {
          case Some(iterable: Iterable[_]) =>
            if (iterable.nonEmpty) {
              val json = iterable2json(iterable.asInstanceOf[Iterable[T]], value2json)
              stableInsert.add((ps: PS) =>
                ps.setString(paramIndex, json))
            } else {
              stableInsert.add((ps: PS) =>
                ps.setNull(paramIndex, java.sql.Types.NULL))
            }
          case None                        =>
            stableInsert.add((ps: PS) =>
              ps.setNull(paramIndex, java.sql.Types.NULL))
        }
    } { refNs =>
      (tpl: Product) => {
        tpl.productElement(tplIndex) match {
          case Some(set: Iterable[_]) if set.nonEmpty =>
            stableInsert.addCardManyRefAttr(
              ns, attr, refNs, set.asInstanceOf[Set[Long]]
            )
          case _                                      => ()
        }
      }
    }
  }

  // Save Floats as Doubles (REAL PRECISION) in SQlite
  override protected lazy val transformFloat =
    (v: Float) => (ps: PS, n: Int) => ps.setDouble(n, v.toString.toDouble)

  override protected lazy val transformDate =
    (v: Date) => (ps: PS, n: Int) => ps.setLong(n, v.getTime)
}