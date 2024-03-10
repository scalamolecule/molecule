package molecule.core.transaction.ops

import molecule.base.ast._
import molecule.core.transaction.Action2Data

trait SaveOps extends Action2Data with BaseOps {

  protected def addOne[T](
    ns: String,
    attr: String,
    optValue: Option[T],
    handleValue: T => Any,
    exts: List[String]
  ): Unit

  protected def addSet[T](
    ns: String,
    attr: String,
    optSet: Option[Set[Any]],
    transformValue: T => Any,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String],
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit

  protected def addArray[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    optArray: Option[Array[Any]],
    transformValue: T => Any,
//    set2array: Array[Any] => Array[AnyRef],
//    exts: List[String],
//    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = ???

  protected def addByteArray(
    ns: String,
    attr: String,
    optArray: Option[Array[Byte]],
  ): Unit = ???

  protected def addMap[T](
    ns: String,
    attr: String,
    optMap: Option[Map[String, Any]],
    transformValue: T => Any,
//    set2map: Set[Any] => Map[String, AnyRef],
//    refNs: Option[String],
//    exts: List[String],
//    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = ???

  protected def addRef(
    ns: String,
    refAttr: String,
    refNs: String,
    card: Card,
    owner: Boolean
  ): Unit

  protected def addBackRef(
    backRefNs: String
  ): Unit

  protected def handleRefNs(refNs: String): Unit
}