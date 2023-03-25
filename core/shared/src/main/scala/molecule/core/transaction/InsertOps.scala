package molecule.core.transaction

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.error.InsertError
import molecule.boilerplate.ast.Model._
import scala.collection.mutable.ListBuffer

trait InsertOps { self: InsertExtraction_ =>

  protected val prevRefs: ListBuffer[AnyRef]

  protected def addComposite(
    outerTpl: Int,
    tplIndex: Int,
    elements: List[Element]
  ): Product => Seq[InsertError]

  protected def addNested(
    tplIndex: Int,
    ns: String,
    refAttr: String,
    elements: List[Element]
  ): Product => Seq[InsertError]

  protected def addV[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    value: T => Any,
    validate: T => Seq[String]
  ): Product => Seq[InsertError]

  protected def addOptV[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    value: T => Any,
    validate: T => Seq[String]
  ): Product => Seq[InsertError]

  protected def addSet[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    value: T => Any,
    validate: T => Seq[String]
  ): Product => Seq[InsertError]

  protected def addOptSet[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    value: T => Any,
    validate: T => Seq[String]
  ): Product => Seq[InsertError]

//  protected def addTxV[T](
//    ns: String,
//    attr: String,
//    outerTpl: Int,
//    tplIndex: Int,
//    value: T => Any
//  ): Product => Seq[InsertError]

  protected def addRef(
    ns: String,
    refAttr: String
  ): Product => Seq[InsertError]

  protected def addBackRef(
    backRefNs: String
  ): Product => Seq[InsertError]

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