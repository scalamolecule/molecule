package molecule.db.sqlite.transaction

import java.sql.PreparedStatement as PS
import java.util.Date
import molecule.db.common.transaction.{ResolveSave, SqlSave}
import molecule.db.common.transaction.strategy.SqlOps

trait Save_sqlite
  extends SqlSave { self: ResolveSave & SqlOps =>

  override protected def addSet[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    optSet: Option[Set[T]],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    addIterable(attr, optRef, optSet, value2json)
  }

  override protected def addSeq[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    optSeq: Option[Seq[T]],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    addIterable(attr, optRef, optSeq, value2json)
  }

  override protected def addMap[T](
    ent: String,
    attr: String,
    optMap: Option[Map[String, T]],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    val paramIndex1 = saveAction.setCol(attr)
    optMap match {
      case Some(map: Map[_, _]) if map.nonEmpty =>
        saveAction.addColSetter((ps: PS) =>
          ps.setString(paramIndex1, map2json(map, value2json)))
      case _                                    =>
        saveAction.addColSetter((ps: PS) => ps.setNull(paramIndex1, 0))
    }
  }


  // Helpers -------------------------------------------------------------------

  private def addIterable[T](
    attr: String,
    optRef: Option[String],
    optIterable: Option[Iterable[T]],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    optRef.fold {
      val paramIndex1 = saveAction.setCol(attr)
      if (optIterable.nonEmpty && optIterable.get.nonEmpty) {
        val json = iterable2json(optIterable.get, value2json)
        saveAction.addColSetter((ps: PS) => ps.setString(paramIndex1, json))
      } else {
        saveAction.addColSetter((ps: PS) => ps.setNull(paramIndex1, 0))
      }
    } { ref =>
      optIterable.foreach(refIds =>
        saveAction.refIds(attr, ref, refIds.asInstanceOf[Set[Long]])
      )
    }
  }

  // Save Floats as Doubles (REAL PRECISION) in SQlite
  override protected lazy val transformFloat =
    (v: Float) => (ps: PS, n: Int) => ps.setDouble(n, v.toString.toDouble)

  override protected lazy val transformDate =
    (v: Date) => (ps: PS, n: Int) => ps.setLong(n, v.getTime)
}