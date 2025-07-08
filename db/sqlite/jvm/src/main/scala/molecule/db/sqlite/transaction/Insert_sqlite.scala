package molecule.db.sqlite.transaction

import java.sql.PreparedStatement as PS
import java.util.Date
import molecule.db.common.transaction.strategy.SqlOps
import molecule.db.common.transaction.{InsertResolvers, ResolveInsert, SqlInsert}

trait Insert_sqlite
  extends SqlInsert { self: ResolveInsert & InsertResolvers & SqlOps =>

  override protected def addSet[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addIterable(attr, optRef, tplIndex, value2json)
  }

  override protected def addSetOpt[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addOptIterable(attr, optRef, tplIndex, value2json)
  }

  override protected def addSeq[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addIterable(attr, optRef, tplIndex, value2json)
  }

  override protected def addSeqOpt[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addOptIterable(attr, optRef, tplIndex, value2json)
  }

  override protected def addMap[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val paramIndex   = insertAction.setCol(attr)
    val stableInsert = insertAction
    (tpl: Product) => {
      tpl.productElement(tplIndex).asInstanceOf[Map[String, ?]] match {
        case map if map.nonEmpty =>
          stableInsert.addColSetter((ps: PS) =>
            ps.setString(
              paramIndex,
              map2json(map.asInstanceOf[Map[String, T]], value2json)
            ))

        case _ =>
          stableInsert.addColSetter((ps: PS) =>
            ps.setNull(paramIndex, java.sql.Types.NULL))
      }
    }
  }

  override protected def addMapOpt[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val paramIndex   = insertAction.setCol(attr)
    val stableInsert = insertAction
    (tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case Some(map: Map[_, _]) if map.nonEmpty =>
          stableInsert.addColSetter((ps: PS) =>
            ps.setString(
              paramIndex,
              map2json(map.asInstanceOf[Map[String, T]], value2json)
            ))

        case _ =>
          stableInsert.addColSetter((ps: PS) =>
            ps.setNull(paramIndex, java.sql.Types.NULL))
      }
    }
  }


  // Helpers -------------------------------------------------------------------

  private def addIterable[T, M[_] <: Iterable[?]](
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val stableInsert = insertAction
    optRef.fold {
      val paramIndex = stableInsert.setCol(attr)
      (tpl: Product) => {
        val iterable = tpl.productElement(tplIndex).asInstanceOf[Iterable[T]]
        if (iterable.nonEmpty) {
          val json = iterable2json(iterable, value2json)
          stableInsert.addColSetter((ps: PS) =>
            ps.setString(paramIndex, json))
        } else {
          stableInsert.addColSetter((ps: PS) =>
            ps.setNull(paramIndex, java.sql.Types.NULL))
        }
      }
    } { ref =>
      val insertRefIds = insertAction.refIds(attr, ref)
      (tpl: Product) => {
        val refIds = tpl.productElement(tplIndex).asInstanceOf[Iterable[Long]]
        insertRefIds.addRefIds(refIds)
      }
    }
  }

  private def addOptIterable[T, M[_] <: Iterable[?]](
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val stableInsert = insertAction
    optRef.fold {
      val paramIndex = stableInsert.setCol(attr)
      (tpl: Product) =>
        tpl.productElement(tplIndex) match {
          case Some(iterable: Iterable[_]) =>
            if (iterable.nonEmpty) {
              val json = iterable2json(iterable.asInstanceOf[Iterable[T]], value2json)
              stableInsert.addColSetter((ps: PS) =>
                ps.setString(paramIndex, json))
            } else {
              stableInsert.addColSetter((ps: PS) =>
                ps.setNull(paramIndex, java.sql.Types.NULL))
            }
          case None                        =>
            stableInsert.addColSetter((ps: PS) =>
              ps.setNull(paramIndex, java.sql.Types.NULL))
        }
    } { ref =>
      val insertRefIds = insertAction.refIds(attr, ref)
      (tpl: Product) => {
        tpl.productElement(tplIndex) match {
          case Some(set: Iterable[_]) =>
            insertRefIds.addRefIds(set.asInstanceOf[Iterable[Long]])
          case _                      =>
            insertRefIds.addRefIds(Iterable.empty[Long])
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