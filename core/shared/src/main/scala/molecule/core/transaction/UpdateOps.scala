package molecule.core.transaction

import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._

trait UpdateOps extends Action2Data { self: Update =>

  protected def uniqueEids(filterAttr: AttrOneTac, ns: String, attr: String): Seq[AnyRef]

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