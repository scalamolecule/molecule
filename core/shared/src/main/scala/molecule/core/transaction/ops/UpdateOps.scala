package molecule.core.transaction.ops

import molecule.boilerplate.ast.Model._
import molecule.core.transaction.Action2Data

trait UpdateOps extends Action2Data with BaseOps {

  def updateOne[T](
    ns: String,
    attr: String,
    owner: Boolean,
    vs: Seq[T],
    transformValue: T => Any,
  ): Unit

  def updateSetEq[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit

  def updateSetEq2[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = ???

  def updateSetAdd[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit

  def updateSetRemove[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String
  ): Unit

  def updateSeqEq[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = ???

  def updateSeqAdd[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = ???

  def updateSeqRemove[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String
  ): Unit = ???

  def updateByteArray(
    ns: String,
    attr: String,
    byteArray: Array[Byte],
  ): Unit = ???

  def updateMapEq[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    noValue: Boolean,
    owner: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
    //    exts: List[String],
    //    set2array: Set[Any] => Array[AnyRef],
  ): Unit = ???

  def updateMapAdd[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
    //    set2array: Set[Any] => Array[AnyRef],
  ): Unit = ???

  def updateMapRemove[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
    //    one2json: T => String
  ): Unit = ???

  protected def handleIds(ns: String, ids: Seq[String]): Unit
  protected def handleUniqueFilterAttr(filterAttr: AttrOneTac): Unit
  protected def handleFilterAttr(filterAttr: AttrOneTac): Unit

  protected def handleBackRef(backRef: BackRef): Unit
  protected def handleRefNs(ref: Ref): Unit
}