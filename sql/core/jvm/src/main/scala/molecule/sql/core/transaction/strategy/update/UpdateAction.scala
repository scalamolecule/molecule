package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.sql.core.transaction.strategy.save.{SaveAction, SaveRefMany, SaveRefOne}
import molecule.sql.core.transaction.strategy.{SqlAction, SqlOps}
import scala.collection.mutable.ListBuffer

abstract class UpdateAction(
  sqlConn: Connection,
  sqlOps: SqlOps,
  ns: String
) extends SqlAction(sqlConn, sqlOps, ns) {

  // This is the heart of save operations for this node in the
  // graph of relationships. Each ref recursively save its sub-graph.
  def update: List[Long] = {
    // Execute referenced namespaces first so that we can
    // reference them subsequently from current namespace
    refs.foreach(_.execute)

    // Execute this namespace insert
    val ps = prepStmt(sqlOps.insertStmt(ns, cols, placeHolders))

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
    val ids = sqlOps.getIds(sqlConn, ns, ps)

    // Execute post inserts of refs given new ids of this namespace
    // (many-to-many joins using this id and ref id in pairs)
    refs.foreach(_.getPostSetters.foreach(_(ids)))

    // Execute post inserts of this ns (card-many ref attr ids-to-join tables)
    getPostSetters.foreach(_(ids))
    ids
  }




  // Change strategy ----------------------------------------

  // Traverse back to initial InsertAction
  def initialAction: UpdateAction

  def refOne(ns: String, refAttr: String, refNs: String): UpdateAction = {
    val ref = UpdateRefOne(this, sqlConn, sqlOps, ns, refAttr, refNs)
    ref.rowSetters += ListBuffer.empty[PS => Unit]
    refs += ref
    ref
  }

  def refMany(ns: String, refAttr: String, refNs: String): UpdateAction = {
    val ref = UpdateRefMany(this, sqlConn, sqlOps, ns, refAttr, refNs)
    ref.rowSetters += ListBuffer.empty[PS => Unit]
    refs += ref
    ref
  }

  def backRef: UpdateAction = ???

  def optRef: UpdateAction = ???
  def optRefNest: UpdateAction = ???
}
