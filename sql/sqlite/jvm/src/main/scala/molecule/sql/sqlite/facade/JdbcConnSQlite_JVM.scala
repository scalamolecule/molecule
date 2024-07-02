package molecule.sql.sqlite.facade

import java.sql
import java.sql.{PreparedStatement, ResultSet}
import molecule.core.marshalling.JdbcProxy
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.javaSql.ResultSetInterface
import molecule.sql.sqlite.javaSql.ResultSetImpl_sqlite
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class JdbcConnSQlite_JVM(
  override val proxy: JdbcProxy,
  private val sqlConn0: sql.Connection
) extends JdbcConn_JVM(proxy, sqlConn0) {

  override def queryStmt(query: String): PreparedStatement = {
    sqlConn.prepareStatement(
      query,
      ResultSet.TYPE_FORWARD_ONLY,
      ResultSet.CONCUR_READ_ONLY
    )
  }

  override def resultSet(underlying: ResultSet): ResultSetInterface = {
    new ResultSetImpl_sqlite(underlying)
  }

  override def extractAffectedIds(
    refPath: List[String],
    ps: PreparedStatement,
    ids: ListBuffer[Long],
    idsMap: mutable.Map[List[String], List[Long]],
    idsAcc: mutable.Map[List[String], List[Long]],
    curIds: List[Long],
    updateIdsMap: Boolean,
    accIds: Boolean,
  ): Unit = {
    debug("")
    if (notJoinTable(refPath)) {
      if (curIds.nonEmpty) {
        // We have ids already used
        debug("  curIds: " + curIds)
        ids ++= curIds
        // Execute incoming batch of prepared statements
        ps.executeBatch()

      } else {
        // Get previous last id
        val table     = refPath.last
        val getPrevId = sqlConn.prepareStatement(
          s"select max(id) from $table"
        ).executeQuery()
        getPrevId.next()
        val prevId = getPrevId.getLong(1)
        getPrevId.close()
        debug("  prev id: " + prevId)

        // Execute incoming batch of prepared statements
        ps.executeBatch()
        ids.clear()

        // Since SQlite doesn't allow us to get ps.getGeneratedKeys after an
        // executeBatch(), we get the affected ids by brute force with a query instead.
        val getNewIds = sqlConn.prepareStatement(
          s"select id from $table where id > $prevId order by id asc"
        ).executeQuery()
        while (getNewIds.next()) {
          ids += getNewIds.getLong(1)
        }
        getNewIds.close()
        debug("  new ids: " + ids.mkString(", "))
        debug("")
      }

    } else {
      // Execute incoming batch of prepared statements
      // (don't gather ids from join table rows)
      ps.executeBatch()
      ids.clear()
    }

    debug("  +++++++++++++ ids: " + ids)

    // Close incoming batch of prepared statements
    ps.close()

    // Assign ids to ref path for later retrieval
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