package molecule.core.transaction

import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._
import scala.collection.mutable.ListBuffer

trait InsertOps { self: Insert =>

  protected val prevRefs: ListBuffer[AnyRef]

  protected def addComposite(tplIndex: Int, elements: List[Element]): Product => Unit
  protected def addNested(tplIndex: Int, ns: String, refAttr: String, elements: List[Element]): Product => Unit
  protected def addV(ns: String, attr: String, n: Int, value: Any => Any): Product => Unit
  protected def addOptV(ns: String, attr: String, n: Int, value: Any => Any): Product => Unit
  protected def addTxV(ns: String, attr: String, n: Int, value: Any => Any): Product => Unit
  protected def addSet(ns: String, attr: String, n: Int, value: Any => Any): Product => Unit
  protected def addOptSet(ns: String, attr: String, n: Int, value: Any => Any): Product => Unit
  protected def addRef(ns: String, refAttr: String): Product => Unit
  protected def addBackRef(backRefNs: String): Product => Unit

  protected lazy val valueString    : Any => Any = ???
  protected lazy val valueInt       : Any => Any = ???
  protected lazy val valueLong      : Any => Any = ???
  protected lazy val valueFloat     : Any => Any = ???
  protected lazy val valueDouble    : Any => Any = ???
  protected lazy val valueBoolean   : Any => Any = ???
  protected lazy val valueBigInt    : Any => Any = ???
  protected lazy val valueBigDecimal: Any => Any = ???
  protected lazy val valueDate      : Any => Any = ???
  protected lazy val valueUUID      : Any => Any = ???
  protected lazy val valueURI       : Any => Any = ???
  protected lazy val valueByte      : Any => Any = ???
  protected lazy val valueShort     : Any => Any = ???
  protected lazy val valueChar      : Any => Any = ???
}