package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.boilerplate.ast.Model.Element
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.strategy.{SqlAction, SqlOps}
import scala.collection.mutable.ListBuffer

abstract class UpdateAction(
  sqlConn: Connection,
  sqlOps: SqlOps,
  m2q: ListBuffer[Element] => Model2SqlQuery,
  ns: String
) extends SqlAction(sqlConn, sqlOps, ns) {

  private[transaction] var ids     = List.empty[Long]
  private[transaction] val filters = ListBuffer.empty[Element]
  private[transaction] val clauses = ListBuffer.empty[String]

  def update: List[Long] = {
    children.foreach(_.execute)

    // Execute this namespace update
    val ps = prepare(curStmt)
    rowSetters.head.foreach(_(ps)) // set values in prepared stmt
    ps.addBatch()

    val ids1 = if (ids.nonEmpty) {
      ps.executeBatch()
      ps.close()
      ids
    } else {
      sqlOps.getIds(sqlConn, ns, ps)
    }

    ids1
  }


  def paramIndex(
    ns: String,
    attr: String,
    isUpsert: Boolean,
    typeCast: String = ""
  ): Int = {
    cols += attr + " = ?" + typeCast
    if (!isUpsert) {
      clauses += s"$ns.$attr IS NOT NULL"
    }
    cols.length
  }


  // Change strategy ----------------------------------------

  // Traverse back to initial InsertAction
  def initialAction: UpdateAction

  def refOne(ns: String, refAttr: String, refNs: String): UpdateAction =
    addChild(UpdateRefOne(this, sqlConn, sqlOps, m2q, ns, refAttr, refNs))

  def refMany(ns: String, refAttr: String, refNs: String): UpdateAction =
    addChild(UpdateRefMany(this, sqlConn, sqlOps, m2q, ns, refAttr, refNs))

  def backRef: UpdateAction = ???

  def optRef: UpdateAction = ???
  def optRefNest: UpdateAction = ???


  // Render --------------------------------------

  override def curStmt: String = {
    val clauses1 = if (filters.isEmpty) clauses else {
      m2q(filters).getWhereClauses
    }
    sqlOps.updateStmt(ns, cols, clauses1)
  }
}
