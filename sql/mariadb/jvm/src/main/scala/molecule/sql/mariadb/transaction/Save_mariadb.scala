package molecule.sql.mariadb.transaction

import java.sql.{PreparedStatement => PS}
import java.util.Date
import molecule.base.util.BaseHelpers
import molecule.core.transaction.ResolveSave
import molecule.sql.core.transaction.SqlSave
import molecule.sql.core.transaction.strategy.SqlOps

trait Save_mariadb
  extends SqlSave
    with BaseHelpers { self: ResolveSave & SqlOps =>

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
    val paramIndex = saveAction.setCol(attr)
    optMap match {
      case Some(map: Map[_, _]) if map.nonEmpty =>
        saveAction.addColSetter((ps: PS) =>
          ps.setString(paramIndex, map2json(map, value2json)))
      case _                                    =>
        saveAction.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
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
      val paramIndex = saveAction.setCol(attr)
      if (optIterable.nonEmpty && optIterable.get.nonEmpty) {
        val json = iterable2json(optIterable.get, value2json)
        saveAction.addColSetter((ps: PS) => ps.setString(paramIndex, json))
      } else {
        saveAction.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
      }
    } { ref =>
      optIterable.foreach(refIds =>
        saveAction.refIds(attr, ref, refIds.asInstanceOf[Set[Long]])
      )
    }
  }

  override protected lazy val transformDate =
    (v: Date) => (ps: PS, n: Int) => ps.setLong(n, v.getTime)
}