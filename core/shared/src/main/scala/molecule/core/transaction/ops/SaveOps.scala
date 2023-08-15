package molecule.core.transaction.ops

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.ast.SchemaAST.Card
import molecule.core.transaction.Action2Data

trait SaveOps extends Action2Data {

  protected def addOne[T](
    ns: String,
    attr: String,
    optValue: Option[T],
    handleValue: T => Any
  ): Unit

  protected def addSet[T](
    ns: String,
    attr: String,
    optSet: Option[Set[T]],
    handleValue: T => Any,
    set2array: Set[Any] => Array[AnyRef]
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

  protected def handleNs(ns: String): Unit
  protected def handleComposite(isInsertTxMetaData: Boolean): Unit
  protected def handleTxMetaData(ns: String): Unit

  // Typed input value to expected db type
  protected lazy val transformString    : String => Any     = ???
  protected lazy val transformInt       : Int => Any        = ???
  protected lazy val transformLong      : Long => Any       = ???
  protected lazy val transformFloat     : Float => Any      = ???
  protected lazy val transformDouble    : Double => Any     = ???
  protected lazy val transformBoolean   : Boolean => Any    = ???
  protected lazy val transformBigInt    : BigInt => Any     = ???
  protected lazy val transformBigDecimal: BigDecimal => Any = ???
  protected lazy val transformDate      : Date => Any       = ???
  protected lazy val transformUUID      : UUID => Any       = ???
  protected lazy val transformURI       : URI => Any        = ???
  protected lazy val transformByte      : Byte => Any       = ???
  protected lazy val transformShort     : Short => Any      = ???
  protected lazy val transformChar      : Char => Any       = ???

  protected lazy val handleString    : Any => Any = ???
  protected lazy val handleInt       : Any => Any = ???
  protected lazy val handleLong      : Any => Any = ???
  protected lazy val handleFloat     : Any => Any = ???
  protected lazy val handleDouble    : Any => Any = ???
  protected lazy val handleBoolean   : Any => Any = ???
  protected lazy val handleBigInt    : Any => Any = ???
  protected lazy val handleBigDecimal: Any => Any = ???
  protected lazy val handleDate      : Any => Any = ???
  protected lazy val handleUUID      : Any => Any = ???
  protected lazy val handleURI       : Any => Any = ???
  protected lazy val handleByte      : Any => Any = ???
  protected lazy val handleShort     : Any => Any = ???
  protected lazy val handleChar      : Any => Any = ???

  protected lazy val set2arrayString    : Set[Any] => Array[AnyRef] = ???
  protected lazy val set2arrayInt       : Set[Any] => Array[AnyRef] = ???
  protected lazy val set2arrayLong      : Set[Any] => Array[AnyRef] = ???
  protected lazy val set2arrayFloat     : Set[Any] => Array[AnyRef] = ???
  protected lazy val set2arrayDouble    : Set[Any] => Array[AnyRef] = ???
  protected lazy val set2arrayBoolean   : Set[Any] => Array[AnyRef] = ???
  protected lazy val set2arrayBigInt    : Set[Any] => Array[AnyRef] = ???
  protected lazy val set2arrayBigDecimal: Set[Any] => Array[AnyRef] = ???
  protected lazy val set2arrayDate      : Set[Any] => Array[AnyRef] = ???
  protected lazy val set2arrayUUID      : Set[Any] => Array[AnyRef] = ???
  protected lazy val set2arrayURI       : Set[Any] => Array[AnyRef] = ???
  protected lazy val set2arrayByte      : Set[Any] => Array[AnyRef] = ???
  protected lazy val set2arrayShort     : Set[Any] => Array[AnyRef] = ???
  protected lazy val set2arrayChar      : Set[Any] => Array[AnyRef] = ???
}