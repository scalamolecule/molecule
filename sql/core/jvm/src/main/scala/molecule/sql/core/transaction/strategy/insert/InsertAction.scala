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
    children.foreach(_.execute)

    // Execute this namespace insert
    //    val ps = prepStmt(sqlOps.insertStmt(table, cols, placeHolders))
    val stmt = curStmt
    //    println("\n=============================================" +
    //      "============================================== " + table)
    //    println(stmt)
    val ps   = prepare(stmt)

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
    children.foreach(_.getPostSetters.foreach(_(ids)))

    // Ref attr ids
    getPostSetters.zip(ids).foreach {
      case (joinSetter, parentId) =>
        // Join parent id with refAttr ids
        joinSetter(List(parentId))
    }

    ids
  }


  def addCardManyRefAttr(
    ns: String, refAttr: String, refNs: String, refIds: Set[Long]
  ): Unit = {
    addPostSetter(
      (parentIds: List[Long]) => {
        val leftId = parentIds.head
        val ps     = prepare(sqlOps.getJoinStmt(ns, refAttr, refNs))
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

  // Traverse back and up to initial InsertAction
  def initialAction: InsertAction

  def refOne(ns: String, refAttr: String, refNs: String): InsertAction =
    addChild(InsertRefOne(this, sqlConn, sqlOps, ns, refAttr, refNs))

  def refMany(ns: String, refAttr: String, refNs: String): InsertAction =
    addChild(InsertRefMany(this, sqlConn, sqlOps, ns, refAttr, refNs))

  def backRef: InsertAction = ???

  def nest(ns: String, refAttr: String, refNs: String): InsertAction =
    addChild(InsertNested(this, sqlConn, sqlOps, ns, refAttr, refNs))

  def optRef: InsertAction = ???
  def optRefNest: InsertAction = ???


  // Render --------------------------------------

  override def curStmt: String = sqlOps.insertStmt(ns, cols, placeHolders)
}