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
    handleScalaValue: T => Any,
  ): Product => Unit

  protected def addOneOpt[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    handleScalaValue: T => Any,
  ): Product => Unit

  protected def addSet[T](
    ns: String,
    attr: String,
    set2array: Set[T] => Array[AnyRef],
    refNsOpt: Option[String],
    tplIndex: Int,
    handleScalaValue: T => Any,
  ): Product => Unit

  protected def addSetOpt[T](
    ns: String,
    attr: String,
    set2array: Set[T] => Array[AnyRef],
    refNsOpt: Option[String],
    tplIndex: Int,
    handleScalaValue: T => Any,
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
  protected lazy val valueString    : String => Any     = ???
  protected lazy val valueInt       : Int => Any        = ???
  protected lazy val valueLong      : Long => Any       = ???
  protected lazy val valueFloat     : Float => Any      = ???
  protected lazy val valueDouble    : Double => Any     = ???
  protected lazy val valueBoolean   : Boolean => Any    = ???
  protected lazy val valueBigInt    : BigInt => Any     = ???
  protected lazy val valueBigDecimal: BigDecimal => Any = ???
  protected lazy val valueDate      : Date => Any       = ???
  protected lazy val valueUUID      : UUID => Any       = ???
  protected lazy val valueURI       : URI => Any        = ???
  protected lazy val valueByte      : Byte => Any       = ???
  protected lazy val valueShort     : Short => Any      = ???
  protected lazy val valueChar      : Char => Any       = ???

  protected lazy val set2arrayString    : Set[String] => Array[AnyRef]     = ???
  protected lazy val set2arrayInt       : Set[Int] => Array[AnyRef]        = ???
  protected lazy val set2arrayLong      : Set[Long] => Array[AnyRef]       = ???
  protected lazy val set2arrayFloat     : Set[Float] => Array[AnyRef]      = ???
  protected lazy val set2arrayDouble    : Set[Double] => Array[AnyRef]     = ???
  protected lazy val set2arrayBoolean   : Set[Boolean] => Array[AnyRef]    = ???
  protected lazy val set2arrayBigInt    : Set[BigInt] => Array[AnyRef]     = ???
  protected lazy val set2arrayBigDecimal: Set[BigDecimal] => Array[AnyRef] = ???
  protected lazy val set2arrayDate      : Set[Date] => Array[AnyRef]       = ???
  protected lazy val set2arrayUUID      : Set[UUID] => Array[AnyRef]       = ???
  protected lazy val set2arrayURI       : Set[URI] => Array[AnyRef]        = ???
  protected lazy val set2arrayByte      : Set[Byte] => Array[AnyRef]       = ???
  protected lazy val set2arrayShort     : Set[Short] => Array[AnyRef]      = ???
  protected lazy val set2arrayChar      : Set[Char] => Array[AnyRef]       = ???
}