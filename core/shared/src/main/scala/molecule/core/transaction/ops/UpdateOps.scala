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
  ): Unit

  def updateSetEq[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
    refNs: Option[String],
    owner: Boolean,
    transformValue: T => Any,
    set2array: Set[T] => Array[AnyRef],
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit

  def updateSetAdd[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
    refNs: Option[String],
    owner: Boolean,
    transformValue: T => Any,
    set2array: Set[T] => Array[AnyRef],
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit

  def updateSetRemove[T](
    ns: String,
    attr: String,
    set: Set[T],
    refNs: Option[String],
    owner: Boolean,
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String
  ): Unit

  def updateSeqEq[T](
    ns: String,
    attr: String,
    seqs: Seq[Seq[T]],
    refNs: Option[String],
    owner: Boolean,
    transformValue: T => Any,
    seq2array: Seq[T] => Array[AnyRef],
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = ???

  def updateByteArray(
    ns: String,
    attr: String,
    byteArrays: Seq[Array[Byte]],
  ): Unit = ???

  def updateSeqAdd[T](
    ns: String,
    attr: String,
    seqs: Seq[Seq[T]],
    refNs: Option[String],
    owner: Boolean,
    transformValue: T => Any,
    seq2array: Seq[T] => Array[AnyRef],
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = ???

  def updateSeqRemove[T](
    ns: String,
    attr: String,
    seq: Seq[T],
    refNs: Option[String],
    owner: Boolean,
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String
  ): Unit = ???

  def updateMapEq[T](
    ns: String,
    attr: String,
    map: Map[String, T],
    noValue: Boolean,
    refNs: Option[String],
    owner: Boolean,
    transformValue: T => Any,
    //    set2array: Set[Any] => Array[AnyRef],
    //    exts: List[String],
    //    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = ???

  def updateMapAdd[T](
    ns: String,
    attr: String,
    map: Map[String, T],
    refNs: Option[String],
    owner: Boolean,
    transformValue: T => Any,
    //    set2array: Set[Any] => Array[AnyRef],
    //    exts: List[String],
    //    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = ???

  def updateMapRemove[T](
    ns: String,
    attr: String,
    map: Map[String, T],
    refNs: Option[String],
    owner: Boolean,
    transformValue: T => Any,
    //    exts: List[String],
    //    one2json: T => String
  ): Unit = ???

  protected def handleIds(ns: String, ids: Seq[String]): Unit
  protected def handleUniqueFilterAttr(filterAttr: AttrOneTac): Unit
  protected def handleFilterAttr(filterAttr: AttrOneTac): Unit

  protected def handleBackRef(backRef: BackRef): Unit
  protected def handleRefNs(ref: Ref): Unit
}