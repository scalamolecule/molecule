package molecule.db.common.transaction.ops

import molecule.base.metaModel.Cardinality
import molecule.db.common.transaction.Action2Data

trait SaveOps extends Action2Data with BaseOps {

  protected def addOne[T](
    ent: String,
    attr: String,
    optValue: Option[T],
    transformValue: T => Any,
    exts: List[String]
  ): Unit

  protected def addSet[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    optSet: Option[Set[T]],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit

  protected def addSeq[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    optSeq: Option[Seq[T]],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit

  protected def addByteArray(
    ent: String,
    attr: String,
    optArray: Option[Array[Byte]],
  ): Unit

  protected def addMap[T](
    ent: String,
    attr: String,
    optMap: Option[Map[String, T]],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit

  protected def addRef(
    ent: String,
    refAttr: String,
    ref: String,
    card: Cardinality
  ): Unit

  protected def addBackRef(
    backRef: String
  ): Unit

  protected def handleRef(ref: String): Unit
}