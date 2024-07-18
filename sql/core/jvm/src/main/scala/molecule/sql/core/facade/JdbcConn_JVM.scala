package molecule.sql.core.facade

import java.sql
import java.sql.{Connection, PreparedStatement, ResultSet, SQLException}
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.{Conn, TxReport}
import molecule.core.util.ModelUtils
import molecule.sql.core.javaSql.{ResultSetImpl, ResultSetInterface}
import molecule.sql.core.transaction.strategy.SqlAction
import molecule.sql.core.transaction.{JoinTable, SqlBase_JVM, SqlDataType_JVM, Table}
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}
import scala.util.control.NonFatal

case class JdbcConn_JVM(
  override val proxy: JdbcProxy,
  private val sqlConn0: sql.Connection
) extends Conn(proxy)
  with SqlDataType_JVM
  with SqlBase_JVM
  with ModelUtils
  with MoleculeLogging {

  // debugging
  doPrint = false
  //  doPrint = true


  override lazy val sqlConn: Connection = sqlConn0

  def queryStmt(query: String): PreparedStatement = {
    sqlConn.prepareStatement(
      query,
      ResultSet.TYPE_SCROLL_INSENSITIVE,
      ResultSet.CONCUR_READ_ONLY
    )
  }

  def resultSet(underlying: ResultSet): ResultSetInterface = {
    new ResultSetImpl(underlying)
  }

  override def transact_async(data: (List[Table], List[JoinTable]))
                             (implicit ec: ExecutionContext): Future[TxReport] = {
    Future(transact_sync(data))
  }

  override def transact_sync(data: Data): TxReport = {
    atomicTransaction(() => populateStmts(data))
  }

  def transact_sync(action: SqlAction): TxReport = {
    atomicTransaction(() => action.execute)
  }

  def atomicTransaction(executions: () => List[Long]): TxReport = {
    try {
      // Atomic transaction of all statements
      sqlConn.setAutoCommit(false)

      // Execute batches
      val ids = executions()

      // commit or fail all
      sqlConn.commit()

      TxReport(ids)
    } catch {
      // re-throw errors to keep stacktrace back to original error
      case e: SQLException =>
        try {
          sqlConn.rollback()
          logger.warn("Successfully rolled back unsuccessful insertion with error: " + e)
          throw e
        } catch {
          case e: SQLException =>
            logger.error("Couldn't roll back unsuccessful insertion: " + e)
            throw e
        }
      case NonFatal(e)     =>
        logger.error("Unexpected insertion error: " + e)
        throw e
    }
  }

  def populateStmts(data: Data): List[Long] = {
    val tables     = data._1
    val joinTables = data._2
    val idsMap     = mutable.Map.empty[List[String], List[Long]]
    val idsAcc     = mutable.Map.empty[List[String], List[Long]]
    val ids        = ListBuffer.empty[Long]


    debug("########################################################################################## " + tables.size)

    // Insert statements backwards to obtain auto-generated ref ids for prepending inserts
    tables.reverse.foreach {
      case Table(refPath, stmt, populatePS, accIds, useAccIds, curIds, upsertStmt, updateIdsMap) =>
        debug("D --- table ---------------------------------------------------------------------------------------------")
        debug("refPath     : " + refPath)
        debug("idsMap 1    : " + idsMap)
        debug("accIds      : " + accIds)
        debug("useAccIds   : " + useAccIds)
        debug("updateIdsMap: " + updateIdsMap)
        debug("curIds      : " + curIds)
        debug("idsAcc 1    : " + idsAcc.toMap)

        val stmt1 = if (useAccIds) {
          val ids = if (updateIdsMap)
            curIds ++ idsAcc.getOrElse(refPath, Nil)
          else
            idsMap.getOrElse(refPath, Nil)
          debug("ids         : " + ids)
          upsertStmt.get(ids)
        } else stmt
        debug("stmt1       : " + stmt1)

        val ps = transactionStmt(stmt1)

        // Populate prepared statement
        populatePS(ps, idsMap, 0)

        extractAffectedIds(refPath, ps, ids, idsMap, idsAcc, curIds, updateIdsMap, accIds)
    }

    //    println("a " + idsMap)


    joinTables.foreach {
      case JoinTable(stmt, leftPath, rightPath, rightCounts) =>
        debug("D --- joinTable -------------------------------------------------\n" + stmt)
        debug("leftPath : " + leftPath)
        debug("rightPath: " + rightPath)
        debug("idsMap 1 : " + idsMap)

        val ps         = transactionStmt(stmt)
        val idsLeft    = idsMap(leftPath)
        var idLeft     = 0L
        var leftIndex  = 0
        val idsRight   = idsMap(rightPath)
        var rightIndex = 0
        var i          = 0
        while (leftIndex != idsLeft.length) {
          idLeft = idsLeft(leftIndex)
          i = 0
          while (i != rightCounts(leftIndex)) {
            ps.setLong(1, idLeft)
            ps.setLong(2, idsRight(rightIndex))
            ps.addBatch()
            rightIndex += 1
            i += 1
          }
          leftIndex += 1
        }
        ps.executeBatch()
    }

    // Return ids of first namespace entities
    val x = idsMap.collectFirst {
      case (List(ns), ids)      => ids
      case (List("0", ns), ids) => ids // nested
    }.getOrElse(Nil)

    //    println("x " + idsMap)
    //    println("x " + x)
    x
  }

  def notJoinTable(refPath: List[String]): Boolean = {
    refPath.last match {
      case "deleteJoins" | "addJoins" => false
      case other                      =>
        // No join tables (also without collision prevention "_"-suffix of table names)
        // ns_join_ref             2 underscores
        // part_ns_join_part_ref   4 underscores
        val underscores = refPath.last.init.count(_ == '_')
        underscores != 2 && underscores != 4
    }
  }

  def extractAffectedIds(
    refPath: List[String],
    ps: PreparedStatement,
    ids: ListBuffer[Long],
    idsMap: mutable.Map[List[String], List[Long]],
    idsAcc: mutable.Map[List[String], List[Long]],
    curIds: List[Long],
    updateIdsMap: Boolean,
    accIds: Boolean,
  ): Unit = {
    // Execute batch of prepared statements
    ps.executeBatch()
    val resultSet = ps.getGeneratedKeys
    ids.clear()

    if (notJoinTable(refPath)) {
      var idSet = false
      while (resultSet.next()) {
        val id = resultSet.getLong(1)
        debug("  ################# " + id)
        ids += id
        //              ids = ids :+ resultSet.getLong(1)
        idSet = true
      }
      if (!idSet) {
        // Not getting ids from updates with MariaDB.
        // So we simply pick previous ids and the id supplied to the update
        ids.clear()
        ids ++= (idsMap.getOrElse(refPath, Nil) ++ curIds)
      }
    }
    ps.close()

    debug("idsMap 2    : " + idsMap)
    if (updateIdsMap)
      idsMap(refPath) = ids.toList
    debug("idsMap 2    : " + idsMap)
    if (accIds) {
      if (idsAcc.contains(refPath)) {
        idsAcc(refPath) = idsAcc(refPath) ++ ids.toList
      } else {
        idsAcc += refPath -> ids.toList
      }
      debug("idsAcc 2    : " + idsAcc.toMap)
    }
  }
}