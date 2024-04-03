package molecule.core.transaction.ops

import molecule.base.ast._
import molecule.boilerplate.ast.Model._

trait InsertOps extends BaseOps {

  protected def addOne[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String]
  ): Product => Unit

  protected def addOneOpt[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String]
  ): Product => Unit

  protected def addSet[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit

  protected def addSetOpt[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit

  protected def addSeq[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = ???

  protected def addSeqOpt[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = ???

  protected def addByteArray(
    ns: String,
    attr: String,
    tplIndex: Int,
  ): Product => Unit = ???

  protected def addMap[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
    //    set2array: Set[Any] => Array[AnyRef],
    //    exts: List[String],
  ): Product => Unit = ???

  protected def addMapOpt[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
    //    set2array: Set[Any] => Array[AnyRef],
    //    exts: List[String],
  ): Product => Unit = ???

  protected def addRef(
    ns: String,
    refAttr: String,
    refNs: String,
    card: Card,
    owner: Boolean
  ): Product => Unit

  protected def addBackRef(
    backRefNs: String
  ): Product => Unit

  protected def addNested(
    nsMap: Map[String, MetaNs],
    tplIndex: Int,
    ns: String,
    refAttr: String,
    refNs: String,
    owner: Boolean,
    elements: List[Element]
  ): Product => Unit
}