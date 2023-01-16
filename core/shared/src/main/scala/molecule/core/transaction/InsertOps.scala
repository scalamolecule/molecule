package molecule.core.transaction

import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._
import scala.collection.mutable.ListBuffer

trait InsertOps extends Action2Data { self: Insert =>

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

  protected val valueString    : Any => Any
  protected val valueInt       : Any => Any
  protected val valueLong      : Any => Any
  protected val valueFloat     : Any => Any
  protected val valueDouble    : Any => Any
  protected val valueBoolean   : Any => Any
  protected val valueBigInt    : Any => Any
  protected val valueBigDecimal: Any => Any
  protected val valueDate      : Any => Any
  protected val valueUUID      : Any => Any
  protected val valueURI       : Any => Any
  protected val valueByte      : Any => Any
  protected val valueShort     : Any => Any
  protected val valueChar      : Any => Any
}