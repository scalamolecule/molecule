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

  protected def addComposite(
    nsMap: Map[String, MetaNs],
    outerTpl: Int,
    tplIndex: Int,
    elements: List[Element]
  ): Product => Unit

  protected def addNested(
    nsMap: Map[String, MetaNs],
    tplIndex: Int,
    ns: String,
    refAttr: String,
    refNs: String,
    elements: List[Element]
  ): Product => Unit

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

  protected lazy val handleString    : String => Any     = ???
  protected lazy val handleInt       : Int => Any        = ???
  protected lazy val handleLong      : Long => Any       = ???
  protected lazy val handleFloat     : Float => Any      = ???
  protected lazy val handleDouble    : Double => Any     = ???
  protected lazy val handleBoolean   : Boolean => Any    = ???
  protected lazy val handleBigInt    : BigInt => Any     = ???
  protected lazy val handleBigDecimal: BigDecimal => Any = ???
  protected lazy val handleDate      : Date => Any       = ???
  protected lazy val handleUUID      : UUID => Any       = ???
  protected lazy val handleURI       : URI => Any        = ???
  protected lazy val handleByte      : Byte => Any       = ???
  protected lazy val handleShort     : Short => Any      = ???
  protected lazy val handleChar      : Char => Any       = ???

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