package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model.Element
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.strategy.insert.InsertAction
import molecule.sql.core.transaction.strategy.save.{SaveNs, SaveRefIds, SaveRefJoin, SaveRefOne}
import molecule.sql.core.transaction.strategy.{SqlAction, SqlOps}
import scala.collection.mutable.ListBuffer

abstract class UpdateAction(
  parent: UpdateAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  m2q: ListBuffer[Element] => Model2SqlQuery,
  ns: String
) extends SqlAction(parent, sqlConn, sqlOps, ns) {

  private[transaction] val filters = ListBuffer.empty[Element]
  private[transaction] val clauses = ListBuffer.empty[String]

//  def update: List[Long] = {
//    children.foreach(_.executeRoot)
//
//    if (cols.isEmpty) {
//      // No update if no columns are set (empty collections for instance)
//      Nil
//    } else {
//      // Execute this namespace update
//      val ps = prepare(curStmt)
//      rowSetters.head.foreach(_(ps)) // set values in prepared stmt
//      ps.addBatch()
//
//      val ids1 = if (ids.nonEmpty) {
//        ps.executeBatch()
//        ps.close()
//        ids
//      } else {
//        sqlOps.getIds(sqlConn, ns, ps)
//      }
//
//
//      // Execute post inserts of refs given new ids of this namespace
//      // (many-to-many joins using this id and ref id in pairs)
//      children.foreach(_.getPostSetters.foreach(_(ids1)))
//
//      // Ref attr ids
//      getPostSetters.zip(ids1).foreach {
//        case (joinSetter, parentId) =>
//          // Join parent id with refAttr ids
//          joinSetter(List(parentId))
//      }
//
//      ids1
//    }
//  }

  def update(): Unit = {
    if (cols.isEmpty) {
      // No update if no columns are set (only joins for instance)
      ()
    } else {
      // Execute this namespace update
      val ps = prepare(curStmt)
      rowSetters.head.foreach(_(ps)) // set values in prepared update stmt
      ps.addBatch()

      ids = if (ids.nonEmpty) {
        ps.executeBatch()
        ps.close()
        ids
      } else {
        sqlOps.getIds(sqlConn, ns, ps)
      }
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

  def deleteRefIds(
    refAttr: String, refNs: String, nsId: Long, refIds: Set[Long] = Set.empty[Long]
  ): Unit = {
    addSibling(UpdateRefIdsDelete(
      parent, sqlConn, sqlOps, m2q, ns, refAttr, refNs, nsId, refIds
    ))
  }
  def insertRefIds(
    refAttr: String, refNs: String, refIds: Set[Long]
  ): Unit = {
    addSibling(UpdateRefIdsInsert(
      parent, sqlConn, sqlOps, m2q, ns, refAttr, refNs, refIds
    ))
  }

  def refOne(ns: String, refAttr: String, refNs: String): UpdateAction = {
//    addChild(UpdateRefOne(this, sqlConn, sqlOps, m2q, ns, refAttr, refNs))


//    addSibling(UpdateQueryIds(
//      this, sqlConn, sqlOps, m2q, ns, refAttr, refNs, paramIndex(refAttr)
//    ))


//    addChild(UpdateRefOne(
//      this, sqlConn, sqlOps, m2q, ns, refAttr, refNs, paramIndex(refAttr)
//    ))

    addSibling(UpdateNs(this, sqlConn, sqlOps, m2q, refNs, "RefOne"))

  }

  def refMany(ns: String, refAttr: String, refNs: String): UpdateAction = {
//    addChild(UpdateRefMany(this, sqlConn, sqlOps, m2q, ns, refAttr, refNs))
    val ref = addChild(UpdateNs(this, sqlConn, sqlOps, m2q, refNs, "RefMany"))

    // Make join after current ns is inserted
    addSibling(UpdateRefJoin(this, ref, sqlConn, sqlOps, m2q, ns, refAttr, refNs))

    // Continue in ref namespace
    ref
  }

  def backRef: UpdateAction = parent

  def optRef: UpdateAction = ???
  def optRefNest: UpdateAction = ???

  // Traverse back to initial InsertAction
  def rootAction: UpdateAction = ???


//  // Render --------------------------------------
//
//  override def curStmt: String = {
//    val filterClauses = if (filters.isEmpty) Nil else m2q(filters).getWhereClauses
//    sqlOps.updateStmt(ns, cols, clauses ++ filterClauses)
//  }
}
