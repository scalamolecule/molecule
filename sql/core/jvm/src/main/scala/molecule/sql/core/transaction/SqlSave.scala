package molecule.sql.core.transaction

import java.sql.{PreparedStatement => PS}
import boopickle.Default._
import molecule.base.ast._
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ResolveSave
import molecule.core.transaction.ops.SaveOps
import molecule.core.util.SerializationUtils
import molecule.sql.core.transaction.strategy.SqlOps
import molecule.sql.core.transaction.strategy.save.{SaveAction, SaveRoot}

trait SqlSave
  extends SaveOps
    with SqlBaseOps
    with SerializationUtils { self: ResolveSave with SqlOps =>

  protected var saveAction: SaveAction = null

  def getSaveAction(elements: List[Element]): SaveAction = {
    saveAction = SaveRoot(sqlOps, getInitialNs(elements)).saveEnt
    resolve(elements)
    saveAction.rootAction
  }

  override protected def addOne[T](
    ent: String,
    attr: String,
    optValue: Option[T],
    transformValue: T => Any,
    exts: List[String] = Nil
  ): Unit = {
    val paramIndex = saveAction.setCol(attr, exts(2))
    optValue.fold {
      saveAction.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
    } { value =>
      val setter = transformValue(value).asInstanceOf[(PS, Int) => Unit]
      saveAction.addColSetter((ps: PS) => setter(ps, paramIndex))
    }
  }

  override protected def addSet[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    optSet: Option[Set[T]],
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    addIterable(attr, optRef, optSet, exts(1), set2array)
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
    addIterable(attr, optRef, optSeq, exts(1), seq2array)
  }

  override protected def addByteArray(
    ent: String,
    attr: String,
    optArray: Option[Array[Byte]],
  ): Unit = {
    val paramIndex = saveAction.setCol(attr)
    if (optArray.nonEmpty && optArray.get.nonEmpty) {
      saveAction.addColSetter((ps: PS) => ps.setBytes(paramIndex, optArray.get))
    } else {
      saveAction.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
    }
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
          ps.setBytes(paramIndex, map2jsonByteArray(map, value2json)))
      case _                                    =>
        saveAction.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
    }
  }

  override protected def addRef(
    ent: String, refAttr: String, ref: String, card: Card
  ): Unit = {
    saveAction = card match {
      case CardOne => saveAction.refOne(ent, refAttr, ref)
      case _       => saveAction.refMany(ent, refAttr, ref)
    }
  }

  override protected def addBackRef(backRef: String): Unit = {
    saveAction = saveAction.backRef
  }

  override protected def handleRef(ref: String): Unit = ()


  // Helpers -------------------------------------------------------------------

  private def addIterable[T, M[_] <: Iterable[_]](
    attr: String,
    optRef: Option[String],
    optIterable: Option[M[T]],
    sqlTpe: String,
    iterable2array: M[T] => Array[AnyRef],
  ): Unit = {
    optRef.fold {
      val paramIndex = saveAction.setCol(attr)
      if (optIterable.nonEmpty && optIterable.get.nonEmpty) {
        val iterable = optIterable.get
        saveAction.addColSetter((ps: PS) => {
          val conn  = ps.getConnection
          val array = conn.createArrayOf(sqlTpe, iterable2array(iterable))
          ps.setArray(paramIndex, array)
        })
      } else {
        saveAction.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
      }
    } { ref =>
      optIterable.foreach(refIds =>
        saveAction.refIds(attr, ref, refIds.asInstanceOf[Set[Long]])
      )
    }
  }
}