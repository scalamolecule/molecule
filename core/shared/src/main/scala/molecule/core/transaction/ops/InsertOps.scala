package molecule.core.transaction.ops

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.ast.SchemaAST._
import molecule.boilerplate.ast.Model._

trait InsertOps {

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

  protected def addV[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    scala2dbTpe: T => Any,
  ): Product => Unit


  protected def addOptV[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    scala2dbTpe: T => Any,
  ): Product => Unit

  protected def addSet[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    scala2dbTpe: T => Any,
  ): Product => Unit

  protected def addOptSet[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    scala2dbTpe: T => Any,
  ): Product => Unit

  protected def addRef(
    ns: String,
    refAttr: String,
    refNs: String,
  ): Product => Unit

  protected def addBackRef(
    backRefNs: String
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
}