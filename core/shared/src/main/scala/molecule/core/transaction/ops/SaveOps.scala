package molecule.core.transaction.ops

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.ast.SchemaAST.Card
import molecule.core.transaction.Action2Data

trait SaveOps extends Action2Data {

  protected def addOne[T](
    ns: String,
    attr: String,
    optValue: Option[T]
  ): Unit

  protected def addSet[T](
    ns: String,
    attr: String,
    optSet: Option[Set[T]],
    set2array: Set[T] => Array[AnyRef]
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