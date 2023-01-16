package molecule.core.transaction

import java.net.URI
import java.util.{Date, UUID}

trait SaveOps extends Action2Data { self: Save =>

  protected def addV(ns: String, attr: String, optValue: Option[Any]): Unit
  protected def addSet[T](ns: String, attr: String, optSet: Option[Set[T]]): Unit
  protected def backRef(backRefNs: String): Unit
  protected def ref(ns: String, refAttr: String): Unit

  protected def handleNs(ns: String): Unit
  protected def handleComposite(isInsertTxMetaData: Boolean): Unit
  protected def handleTxMetaData(): Unit


  protected val valueString    : String => Any
  protected val valueInt       : Int => Any
  protected val valueLong      : Long => Any
  protected val valueFloat     : Float => Any
  protected val valueDouble    : Double => Any
  protected val valueBoolean   : Boolean => Any
  protected val valueBigInt    : BigInt => Any
  protected val valueBigDecimal: BigDecimal => Any
  protected val valueDate      : Date => Any
  protected val valueUUID      : UUID => Any
  protected val valueURI       : URI => Any
  protected val valueByte      : Byte => Any
  protected val valueShort     : Short => Any
  protected val valueChar      : Char => Any
}