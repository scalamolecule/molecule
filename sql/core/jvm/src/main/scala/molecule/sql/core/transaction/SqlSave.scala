package molecule.sql.core.transaction

import java.sql.{PreparedStatement => PS}
import boopickle.Default._
import molecule.base.ast._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveSave
import molecule.core.transaction.ops.SaveOps
import molecule.core.util.SerializationUtils
import molecule.sql.core.transaction.strategy.SqlAction
import molecule.sql.core.transaction.strategy.save.SaveNs

trait SqlSave
  extends SqlBase_JVM
    with SaveOps with SqlBaseOps
    with MoleculeLogging
    with SerializationUtils { self: ResolveSave =>


  def getSaveStrategy(elements: List[Element]): SqlAction = {
    action = SaveNs(sqlConn, getInitialNs(elements))
    resolve(elements)
    action.fromTop
  }

  override protected def addOne[T](
    ns: String,
    attr: String,
    optValue: Option[T],
    transformValue: T => Any,
    exts: List[String] = Nil
  ): Unit = {
    val paramIndex = action.paramIndex
    optValue.fold {
      action.add(attr, (ps: PS) => ps.setNull(paramIndex, 0))
    } { value =>
      val setter = transformValue(value).asInstanceOf[(PS, Int) => Unit]
      action.add(
        attr,
        (ps: PS) => setter(ps, paramIndex),
        "?",
        exts(2) // Add type casting for postgres
      )
    }
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    optSet: Option[Set[T]],
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    addIterable(ns, attr, optRefNs, optSet, exts(1), set2array)
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
    addIterable(ns, attr, optRefNs, optSeq, exts(1), seq2array)
  }

  override protected def addByteArray(
    ns: String,
    attr: String,
    optArray: Option[Array[Byte]],
  ): Unit = {
    val paramIndex = action.paramIndex
    if (optArray.nonEmpty && optArray.get.nonEmpty) {
      action.add(attr, (ps: PS) => ps.setBytes(paramIndex, optArray.get))
    } else {
      action.add(attr, (ps: PS) => ps.setNull(paramIndex, 0))
    }
  }

  override protected def addMap[T](
    ns: String,
    attr: String,
    optMap: Option[Map[String, T]],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    val paramIndex = action.paramIndex
    optMap match {
      case Some(map: Map[_, _]) if map.nonEmpty =>
        action.add(attr, (ps: PS) =>
          ps.setBytes(paramIndex, map2jsonByteArray(map, value2json)))
      case _                                    =>
        action.add(attr, (ps: PS) => ps.setNull(paramIndex, 0))
    }
  }

  override protected def addRef(
    ns: String, refAttr: String, refNs: String, card: Card
  ): Unit = {
    action = card match {
      case CardOne => action.refOne(ns, refAttr, refNs)
      case _       => action.refMany(ns, refAttr, refNs)
    }
  }

  override protected def addBackRef(backRefNs: String): Unit = {
    action = action.backRef
  }

  override protected def handleRefNs(refNs: String): Unit = ()


  // Helpers -------------------------------------------------------------------

  private def addIterable[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    optIterable: Option[M[T]],
    sqlTpe: String,
    iterable2array: M[T] => Array[AnyRef],
  ): Unit = {
    optRefNs.fold {
      val paramIndex = action.paramIndex
      if (optIterable.nonEmpty && optIterable.get.nonEmpty) {
        val iterable = optIterable.get
        action.add(attr, (ps: PS) => {
          val conn  = ps.getConnection
          val array = conn.createArrayOf(sqlTpe, iterable2array(iterable))
          ps.setArray(paramIndex, array)
        })
      } else {
        action.add(attr, (ps: PS) => ps.setNull(paramIndex, 0))
      }
    } { refNs =>
      optIterable.foreach(refIds =>
        action.addCardManyRefAttr(
          ns, attr, refNs, refIds.asInstanceOf[Set[Long]], defaultValues
        )
      )
    }
  }
}