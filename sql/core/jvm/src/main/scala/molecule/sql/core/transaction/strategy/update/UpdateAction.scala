package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.boilerplate.ast.Model.Element
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.strategy.insert.InsertAction
import molecule.sql.core.transaction.strategy.save.SaveRefIds
import molecule.sql.core.transaction.strategy.{SqlAction, SqlOps}
import scala.collection.mutable.ListBuffer

abstract class UpdateAction(
  parent: UpdateAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  m2q: ListBuffer[Element] => Model2SqlQuery,
  ns: String
) extends SqlAction(parent, sqlConn, sqlOps, ns) {

//  private[transaction] var ids     = List.empty[Long]
  private[transaction] val filters = ListBuffer.empty[Element]
  private[transaction] val clauses = ListBuffer.empty[String]

  def update: List[Long] = {
    children.foreach(_.executeRoot)

    if (cols.isEmpty) {
      // No update if no columns are set (empty collections for instance)
      Nil
    } else {
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


      // Execute post inserts of refs given new ids of this namespace
      // (many-to-many joins using this id and ref id in pairs)
      children.foreach(_.getPostSetters.foreach(_(ids1)))

      // Ref attr ids
      getPostSetters.zip(ids1).foreach {
        case (joinSetter, parentId) =>
          // Join parent id with refAttr ids
          joinSetter(List(parentId))
      }

      ids1
    }
  }


  def paramIndex(
    setAttr: String,
    isUpsert: Boolean,
    ns: String,
    attr: String,
  ): Int = {
    cols += setAttr
    if (!isUpsert) {
      clauses += s"$ns.$attr IS NOT NULL"
    }
    cols.length
  }


  // Change strategy ----------------------------------------

  // Traverse back to initial InsertAction
  def rootAction: UpdateAction = ???

//  def refIds(refAttr: String, refNs: String, refIds: Set[Long]): Unit = {
//    addSibling(InsertRefIds(parent, sqlConn, sqlOps, ns, refAttr, refNs, refIds))
//  }

  def refOne(ns: String, refAttr: String, refNs: String): UpdateAction =
    addChild(UpdateRefOne(this, sqlConn, sqlOps, m2q, ns, refAttr, refNs))

  def refMany(ns: String, refAttr: String, refNs: String): UpdateAction =
    addChild(UpdateRefMany(this, sqlConn, sqlOps, m2q, ns, refAttr, refNs))

  def backRef: UpdateAction = ???

  def optRef: UpdateAction = ???
  def optRefNest: UpdateAction = ???


  // Render --------------------------------------

  override def curStmt: String = {
    val filterClauses = if (filters.isEmpty) Nil else m2q(filters).getWhereClauses
    sqlOps.updateStmt(ns, cols, clauses ++ filterClauses)
  }
}
