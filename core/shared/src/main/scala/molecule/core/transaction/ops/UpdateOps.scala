package molecule.core.transaction.ops

import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.Action2Data

trait UpdateOps extends Action2Data {

  protected def uniqueIds(
    filterAttr: AttrOneTac,
    ns: String,
    attr: String
  ): Seq[AnyRef]

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