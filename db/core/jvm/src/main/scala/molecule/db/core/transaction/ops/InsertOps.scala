package molecule.db.core.transaction.ops

import molecule.db.base.ast.*
import molecule.db.core.ast.{Attr, Element}

trait InsertOps extends BaseOps {

  protected def addOne[T](
    ent: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String]
  ): Product => Unit

  protected def addOneOpt[T](
    ent: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String]
  ): Product => Unit

  protected def addSet[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit

  protected def addSetOpt[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit

  protected def addSeq[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit

  protected def addSeqOpt[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit

  protected def addByteArray(
    ent: String,
    attr: String,
    tplIndex: Int,
  ): Product => Unit

  protected def addMap[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit

  protected def addMapOpt[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit

  protected def addRef(
    ent: String,
    refAttr: String,
    ref: String,
    card: Card
  ): Product => Unit

  protected def addBackRef(
    backRef: String
  ): Product => Unit

  protected def addOptEntity(
    attrs: List[Attr]
  ): Product => Unit

  protected def addOptRef(
    tplIndex: Int,
    ent: String,
    refAttr: String,
    ref: String,
    elements: List[Element]
  ): Product => Unit

  protected def addNested(
    tplIndex: Int,
    ent: String,
    refAttr: String,
    ref: String,
    elements: List[Element]
  ): Product => Unit
}