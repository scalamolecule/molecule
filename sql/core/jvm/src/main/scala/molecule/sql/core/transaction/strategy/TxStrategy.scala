package molecule.sql.core.transaction.strategy

import java.sql.{Connection, PreparedStatement}
import molecule.base.util.BaseHelpers
import scala.collection.mutable.ListBuffer

trait TxBase extends BaseHelpers {
  type PS = PreparedStatement
  type RowIndex = Int
  type ParamIndex = Int
  type Cast = (PS, ParamIndex) => Any
  type Setter = PS => Unit

  def joinIdNames(ns: String, refNs: String): (String, String) = {
    if (ns == refNs)
      (ss(ns, "1_id"), ss(refNs, "2_id"))
    else
      (ss(ns, "id"), ss(refNs, "id"))
  }
}

trait TxStrategy extends TxBase {
  val sqlConn: Connection
  protected val refs        = ListBuffer.empty[TxStrategy]
  private   val postSetters = ListBuffer.empty[List[Long] => Unit]

  // For inspection
  val stmts = ListBuffer.empty[String]

  def paramIndex: Int
  def add(
    attr: String,
    setter: Setter,
    placeHolder: String = "?",
    typeCast: String = ""
  ): Unit

  def addPostSetter(postSetter: List[Long] => Unit): Unit = {
    postSetters += postSetter
  }
  def getPostSetters: ListBuffer[List[Long] => Unit] = postSetters

  def backRef: TxStrategy = ???
  def refOne(ns: String, refAttr: String, refNs: String): TxStrategy = ???
  def refMany(ns: String, refAttr: String, refNs: String): TxStrategy = ???
  def optRef: TxStrategy = ???
  def optRefNest: TxStrategy = ???
  def nest: TxStrategy = ???

  def fromTop: TxStrategy
  def execute: List[Long]

  def addCardManyRefAttr(
    ns: String, refAttr: String, refNs: String,
    refIds: Set[Long], defaultValues: String
  ):  Unit

  def render(indent: Int): String = ???
}