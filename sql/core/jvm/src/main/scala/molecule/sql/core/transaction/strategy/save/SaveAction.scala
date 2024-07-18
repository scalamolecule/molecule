package molecule.sql.core.transaction.strategy.save

import java.sql.Connection
import molecule.sql.core.transaction.strategy.{SqlAction, SqlOps}
import scala.collection.mutable.ListBuffer


abstract class SaveAction(
  sqlConn: Connection,
  sqlOps: SqlOps,
  table: String
) extends SqlAction(sqlConn, sqlOps, table) {

  // This is the heart of save operations for this node in the
  // graph of relationships. Each ref recursively save its sub-graph.
  def insert: List[Long] = {
    // Execute referenced namespaces first so that we can
    // reference them subsequently from current namespace
    refs.foreach(_.execute)

    // Execute this namespace insert
    val ps = prepStmt(sqlOps.insertStmt(table, cols, placeHolders))

    // Add one row
    rowSetters.foreach { rowSetter =>
      rowSetter.foreach { colSetter =>
//        println("colSetter: " + colSetter)
        colSetter(ps)
      }
      ps.addBatch()
      //      println("++++++++++++")
    }

    // Retrieve generated ids (various db implementations)
    // Closes prepared statement
    val ids = sqlOps.getIds(sqlConn, table, ps)

    // Execute post inserts of refs given new ids of this namespace
    // (many-to-many joins using this id and ref id in pairs)
    refs.foreach(_.getPostSetters.foreach(_(ids)))

    // Execute post inserts of this ns (card-many ref attr ids-to-join tables)
    getPostSetters.foreach(_(ids))
    ids
  }

  def addCardManyRefAttr(
    ns: String, refAttr: String, refNs: String, refIds: Set[Long]
  ): Unit = {
    if (refIds.nonEmpty) {
      addPostSetter(
        (parentIds: List[Long]) => {
          val leftId = parentIds.head
          val ps     = prepStmt(sqlOps.getJoinStmt(ns, refAttr, refNs))
          val it     = refIds.iterator
          while (it.hasNext) {
            ps.setLong(1, leftId)
            ps.setLong(2, it.next())
            ps.addBatch()
          }
          ps.executeBatch()
          ps.close()
        }
      )
    }
  }


  // Change strategy ----------------------------------------

  // Traverse back to initial InsertAction
  def initialAction: SaveAction

  def refOne(ns: String, refAttr: String, refNs: String): SaveAction = {
    val ref = SaveRefOne(this, sqlConn, sqlOps, ns, refAttr, refNs)
    ref.rowSetters += ListBuffer.empty[PS => Unit]
    refs += ref
    ref
  }

  def refMany(ns: String, refAttr: String, refNs: String): SaveAction = {
    val ref = SaveRefMany(this, sqlConn, sqlOps, ns, refAttr, refNs)
    ref.rowSetters += ListBuffer.empty[PS => Unit]
    refs += ref
    ref
  }

  def backRef: SaveAction = ???

  def optRef: SaveAction = ???
  def optRefNest: SaveAction = ???
}