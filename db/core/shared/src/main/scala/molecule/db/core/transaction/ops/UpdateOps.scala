package molecule.db.core.transaction.ops

import molecule.db.core.ast._
import molecule.db.core.transaction.Action2Data

trait UpdateOps extends Action2Data with BaseOps {

  protected def updateOne[T](
    ent: String,
    attr: String,
    op: Op,
    vs: Seq[T],
    transformValue: T => Any,
    exts: List[String],
  ): Unit

  protected def handleIds(ent: String, ids: Seq[Long]): Unit
  protected def handleFilterAttr[T <: Attr & Tacit](filterAttr: T): Unit

  protected def updateSetEq[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit

  protected def updateSetAdd[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit

  protected def updateSetRemove[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String,
    set2array: Set[T] => Array[AnyRef]
  ): Unit

  protected def updateSeqEq[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit

  protected def updateSeqAdd[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit

  protected def updateSeqRemove[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String,
    seq2array: Seq[T] => Array[AnyRef]
  ): Unit

  protected def updateByteArray(
    ent: String,
    attr: String,
    byteArray: Array[Byte],
  ): Unit

  protected def updateMapEq[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    noValue: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit

  protected def updateMapAdd[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    map: Map[String, T],
    transformValue: T => Any,
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
  ): Unit

  protected def updateMapRemove(
    ent: String,
    attr: String,
    optRef: Option[String],
    keys: Seq[String],
    exts: List[String],
  ): Unit

  protected def handleBackRef(backRef: BackRef): Unit
  protected def handleRef(ref: Ref): Unit
}