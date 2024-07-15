package molecule.sql.core.transaction.strategy

import java.sql.{Connection, PreparedStatement}
import molecule.base.util.BaseHelpers
import scala.collection.mutable.ListBuffer


trait SqlAction extends SqlActionBase {
  val sqlConn: Connection
  protected val refs        = ListBuffer.empty[SqlAction]
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

  def backRef: SqlAction = ???
  def refOne(ns: String, refAttr: String, refNs: String): SqlAction = ???
  def refMany(ns: String, refAttr: String, refNs: String): SqlAction = ???
  def optRef: SqlAction = ???
  def optRefNest: SqlAction = ???
  def nest: SqlAction = ???

  def fromTop: SqlAction
  def execute: List[Long]

  def addCardManyRefAttr(
    ns: String, refAttr: String, refNs: String,
    refIds: Set[Long], defaultValues: String
  ):  Unit

  def render(indent: Int): String = ???
}