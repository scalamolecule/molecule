package molecule.core.transaction.ops

import molecule.base.ast._
import molecule.core.transaction.Action2Data

trait SaveOps extends Action2Data with BaseOps {

  protected def addOne[T](
    ns: String,
    attr: String,
    optValue: Option[T],
    transformValue: T => Any,
    exts: List[String]
  ): Unit

  protected def addSet[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    optSet: Option[Set[T]],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit

  protected def addSeq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    optSeq: Option[Seq[T]],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit

  protected def addByteArray(
    ns: String,
    attr: String,
    optArray: Option[Array[Byte]],
  ): Unit

  protected def addMap[T](
    ns: String,
    attr: String,
    optMap: Option[Map[String, T]],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit

  protected def addRef(
    ns: String,
    refAttr: String,
    refNs: String,
    card: Card
  ): Unit

  protected def addBackRef(
    backRefNs: String
  ): Unit

  protected def handleRefNs(refNs: String): Unit
}