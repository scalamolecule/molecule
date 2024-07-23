package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.boilerplate.ast.Model.Element
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.strategy.{SqlAction, SqlOps}
import scala.collection.mutable.ListBuffer

abstract class UpdateAction(
  parent: UpdateAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  isUpsert: Boolean,
  ns: String
) extends SqlAction(parent, sqlConn, sqlOps, ns) {

    private[transaction] val joins          = ListBuffer.empty[String]
    private[transaction] val filterElements = ListBuffer.empty[Element]



    // Execute update ----------------------------------------

    def update(): Unit = {
      val stmt = curStmt
      if (cols.isEmpty || stmt.isEmpty) {
        // No update if no columns are set or supplied collections are empty
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


  // Change strategy ----------------------------------------

  def deleteRefIds(
    refAttr: String, refNs: String, nsId: Long, refIds: Set[Long] = Set.empty[Long]
  ): Unit = {
    addSibling(UpdateRefIdsDelete(
      parent, sqlConn, sqlOps, isUpsert, ns, refAttr, refNs, nsId, refIds
    ))
  }
  def insertRefIds(
    refAttr: String, refNs: String, refIds: Set[Long]
  ): Unit = {
    addSibling(UpdateRefIdsInsert(
      parent, sqlConn, sqlOps, isUpsert, ns, refAttr, refNs, refIds
    ))
  }

  def refOne(ns: String, refAttr: String, refNs: String): UpdateAction = {
    addChild(UpdateRefOne(this, sqlConn, sqlOps, isUpsert, ns, refAttr, refNs))
  }

  def refMany(ns: String, refAttr: String, refNs: String): UpdateAction = {
    val ref = addChild(UpdateNs(this, sqlConn, sqlOps, isUpsert, refNs, "RefMany"))

    // Make join after current ns is inserted
    addSibling(UpdateRefJoin(this, ref, sqlConn, sqlOps, isUpsert, ns, refAttr, refNs))

    // Continue in ref namespace
    ref
  }

  def backRef: UpdateAction = parent

  def optRef: UpdateAction = ???
  def optRefNest: UpdateAction = ???

  // Traverse back to initial InsertAction
  def rootAction: UpdateAction = ???

  //  def root = rootAction.asInstanceOf[UpdateRoot].idsQuery
}
