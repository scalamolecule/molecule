package molecule.sql.jdbc.facade

import java.sql
import java.sql.SQLException
import datomic.{Datom => _}
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.spi.{Conn, TxReport}
import molecule.core.marshalling.JdbcProxy
import molecule.sql.jdbc.transaction.{JdbcDataType_JVM, Resolver}
import scala.concurrent.{ExecutionContext, Future}
import scala.util.control.NonFatal

case class JdbcConn_jvm(
  override val proxy: JdbcProxy,
  sqlConn: sql.Connection
) extends Conn(proxy)
  with JdbcDataType_JVM
  with MoleculeLogging {

  private[molecule] var fresh = true

//  override def transact_async(data: Data)(implicit ec: ExecutionContext): Future[TxReport] = {
//    Future(transact_sync(data))
//  }

  override def transact_sync(resolvers: Data): TxReport = {
    val insertsCount = resolvers.length
    var insertIndex  = insertsCount
    var ids          = Array.empty[Long]

    // Cache ref ids on possible levels (top level + 7 nested levels)
    var insertIds = Map.empty[(Int, List[String], String), Array[Long]]

    try {
      // Atomic transaction of all statements
      sqlConn.setAutoCommit(false)

      // Insert statements backwards to obtain auto-generated ref ids for prepending inserts
      resolvers.reverse.foreach {
        case Resolver(level, refPath, ns, stmt, ps, populatePS, nestedCounts) =>
          insertIndex -= 1

          // Populate prepared statement
          println("--------------------------------------------------------------\n" + stmt)
          populatePS(ps, insertIds, 0)

          ps.executeBatch()
          val resultSet = ps.getGeneratedKeys // is empty if no nested data

          ids = Array.empty[Long]
          while (resultSet.next()) {
            ids = ids :+ resultSet.getLong(1)
          }
          ps.close()

          // Cache ref ids for this insert
          insertIds += (level, refPath, ns) -> ids
      }

      // transact all
      sqlConn.commit()

      // Tx entity not implemented for sql-jdbc
      TxReport(0, ids.toList) // Use only top-level ids (ok?)
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
}