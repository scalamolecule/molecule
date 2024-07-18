package molecule.sql.core.transaction.strategy.insert

import java.sql.Connection
import molecule.sql.core.transaction.strategy.{SqlAction, SqlOps}


abstract class InsertAction(
  sqlConn: Connection,
  sqlOps: SqlOps,
  ns: String
) extends SqlAction(sqlConn, sqlOps, ns) {

  def insert: List[Long] = {

    // Execute referenced namespaces first so that we can reference them subsequently
    refs.foreach(_.execute)

    // Execute this namespace insert
    //    val ps = prepStmt(sqlOps.insertStmt(table, cols, placeHolders))
    val stmt = sqlOps.insertStmt(ns, cols, placeHolders)
    //    println("\n=============================================" +
    //      "============================================== " + table)
    //    println(stmt)
    val ps   = prepStmt(stmt)

    // Add rows
    rowSetters.foreach { rowSetter =>
      //      println("••••••••••••••••••••••••")
      if (rowSetter.nonEmpty) {
        rowSetter.foreach { colSetter =>
          //          println("colSetter: " + colSetter)
          colSetter(ps)
        }
        ps.addBatch()
        //        println("++++++++++++")
      }
    }

    // Retrieve generated ids (various db implementations)
    // Executes and closes prepared statement
    val ids = sqlOps.getIds(sqlConn, ns, ps)

    // Execute post inserts of refs given new ids of this namespace
    // (many-to-many joins using this id and ref id in pairs)
    refs.foreach(_.getPostSetters.foreach(_(ids)))

    // Ref attr ids
    getPostSetters.zip(ids).foreach {
      case (joinSetter, parentId) =>
        // Join parent id with refAttr ids
        joinSetter(List(parentId))
    }

    ids
  }


  def addCardManyJoins(
    ns: String, refAttr: String, refNs: String, refIds: List[Long]
  ): Unit = {
    addPostSetter(
      (parentIds: List[Long]) => {
        val (l1, l2) = (parentIds.length, refIds.length)
        if (l1 != l2) {
          throw new Exception(
            s"Unexpected different number of left/right ids for " +
              s"joinTable ${ns}_${refAttr}_$refNs: $l1/$l2"
          )
        }
        val ps       = prepStmt(sqlOps.getJoinStmt(ns, refAttr, refNs))
        val leftIds  = parentIds.iterator
        val rightIds = refIds.iterator
        while (rightIds.hasNext) {
          ps.setLong(1, leftIds.next())
          ps.setLong(2, rightIds.next())
          ps.addBatch()
        }
        ps.executeBatch()
        ps.close()
      }
    )
  }

  def addCardManyRefAttr(
    ns: String, refAttr: String, refNs: String, refIds: Set[Long]
  ): Unit = {
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

  // Change strategy ----------------------------------------

  // Traverse back to initial InsertAction
  def initialAction: InsertAction

  def refOne(ns: String, refAttr: String, refNs: String): InsertAction = {
    val ref = InsertRefOne(this, sqlConn, sqlOps, ns, refAttr, refNs)
    refs += ref
    ref
  }
  def refMany(ns: String, refAttr: String, refNs: String): InsertAction = {
    val ref = InsertRefMany(this, sqlConn, sqlOps, ns, refAttr, refNs)
    refs += ref
    ref
  }

  def backRef: InsertAction = ???

  def nest(ns: String, refAttr: String, refNs: String): InsertAction = {
    val ref = InsertNested(this, sqlConn, sqlOps, ns, refAttr, refNs)
    //        ref.rowSetters += ListBuffer.empty[PS => Unit]
    refs += ref
    ref
  }

  def optRef: InsertAction = ???
  def optRefNest: InsertAction = ???
}