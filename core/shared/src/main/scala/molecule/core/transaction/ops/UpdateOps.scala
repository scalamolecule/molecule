package molecule.core.transaction.ops

import molecule.boilerplate.ast.Model._
import molecule.core.transaction.Action2Data

trait UpdateOps extends Action2Data with BaseOps {

  def updateOne[T](
    ns: String,
    attr: String,
    vs: Seq[T],
    owner: Boolean,
    transformValue: T => Any,
    handleValue: T => Any
  ): Unit

  def updateSetEq[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
    refNs: Option[String],
    owner: Boolean,
    transform: T => Any,
    set2array: Set[Any] => Array[AnyRef],
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit

  def updateSetAdd[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
    refNs: Option[String],
    owner: Boolean,
    transform: T => Any,
    set2array: Set[Any] => Array[AnyRef],
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit

  def updateSetRemove[T](
    ns: String,
    attr: String,
    set: Set[T],
    refNs: Option[String],
    owner: Boolean,
    transform: T => Any,
    handleValue: T => Any,
    exts: List[String],
    one2json: T => String
  ): Unit

  def updateArrayEq[T](
    ns: String,
    attr: String,
    arrays: Seq[Array[T]],
    refNs: Option[String],
    owner: Boolean,
    transform: T => Any,
    //    set2array: Set[Any] => Array[AnyRef],
    //    exts: List[String],
    //    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = ???

  def updateByteArray(
    ns: String,
    attr: String,
    byteArrays: Seq[Array[Byte]],
  ): Unit = ???

  def updateArrayAdd[T](
    ns: String,
    attr: String,
    arrays: Seq[Array[T]],
    refNs: Option[String],
    owner: Boolean,
    transform: T => Any,
    //    set2array: Set[Any] => Array[AnyRef],
    //    exts: List[String],
    //    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = ???

  def updateArrayRemove[T](
    ns: String,
    attr: String,
    array: Array[T],
    refNs: Option[String],
    owner: Boolean,
    transform: T => Any,
    //    handleValue: T => Any,
    //    exts: List[String],
    //    one2json: T => String
  ): Unit = ???

  protected def handleIds(ns: String, ids: Seq[String]): Unit
  protected def handleUniqueFilterAttr(filterAttr: AttrOneTac): Unit
  protected def handleFilterAttr(filterAttr: AttrOneTac): Unit

  protected def handleBackRef(backRef: BackRef): Unit
  protected def handleRefNs(ref: Ref): Unit
}