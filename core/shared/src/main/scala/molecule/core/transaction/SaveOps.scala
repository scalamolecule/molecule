package molecule.core.transaction

import java.net.URI
import java.util.{Date, UUID}

trait SaveOps extends Action2Data { self: SaveExtraction =>

  protected def addV(ns: String, attr: String, optValue: Option[Any]): Unit
  protected def addSet[T](ns: String, attr: String, optSet: Option[Set[T]]): Unit
  protected def backRef(backRefNs: String): Unit
  protected def ref(ns: String, refAttr: String): Unit

  protected def handleNs(ns: String): Unit
  protected def handleComposite(isInsertTxMetaData: Boolean): Unit
  protected def handleTxMetaData(): Unit


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