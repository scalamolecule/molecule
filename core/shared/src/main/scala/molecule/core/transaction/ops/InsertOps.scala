package molecule.core.transaction.ops

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.ast.SchemaAST._
import molecule.boilerplate.ast.Model._

trait InsertOps {

  protected def addOne[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    handleValue: T => Any
  ): Product => Unit

  protected def addOneOpt[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    handleValue: T => Any
  ): Product => Unit

  protected def addSet[T](
    ns: String,
    attr: String,
    set2array: Set[Any] => Array[AnyRef],
    refNsOpt: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
  ): Product => Unit

  protected def addSetOpt[T](
    ns: String,
    attr: String,
    set2array: Set[Any] => Array[AnyRef],
    refNsOpt: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
  ): Product => Unit

  protected def addRef(
    ns: String,
    refAttr: String,
    refNs: String,
    card: Card
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
    elements: List[Element]
  ): Product => Unit

  protected lazy val transformString    : String => Any     = identity
  protected lazy val transformInt       : Int => Any        = identity
  protected lazy val transformLong      : Long => Any       = identity
  protected lazy val transformFloat     : Float => Any      = identity
  protected lazy val transformDouble    : Double => Any     = identity
  protected lazy val transformBoolean   : Boolean => Any    = identity
  protected lazy val transformBigInt    : BigInt => Any     = identity
  protected lazy val transformBigDecimal: BigDecimal => Any = identity
  protected lazy val transformDate      : Date => Any       = identity
  protected lazy val transformUUID      : UUID => Any       = identity
  protected lazy val transformURI       : URI => Any        = identity
  protected lazy val transformByte      : Byte => Any       = identity
  protected lazy val transformShort     : Short => Any      = identity
  protected lazy val transformChar      : Char => Any       = identity

  protected lazy val handleString    : Any => Any = identity
  protected lazy val handleInt       : Any => Any = identity
  protected lazy val handleLong      : Any => Any = identity
  protected lazy val handleFloat     : Any => Any = identity
  protected lazy val handleDouble    : Any => Any = identity
  protected lazy val handleBoolean   : Any => Any = identity
  protected lazy val handleBigInt    : Any => Any = identity
  protected lazy val handleBigDecimal: Any => Any = identity
  protected lazy val handleDate      : Any => Any = identity
  protected lazy val handleUUID      : Any => Any = identity
  protected lazy val handleURI       : Any => Any = identity
  protected lazy val handleByte      : Any => Any = identity
  protected lazy val handleShort     : Any => Any = identity
  protected lazy val handleChar      : Any => Any = identity

  protected lazy val set2arrayString    : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayInt       : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayLong      : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayFloat     : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayDouble    : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayBoolean   : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayBigInt    : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayBigDecimal: Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayDate      : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayUUID      : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayURI       : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayByte      : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayShort     : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayChar      : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
}