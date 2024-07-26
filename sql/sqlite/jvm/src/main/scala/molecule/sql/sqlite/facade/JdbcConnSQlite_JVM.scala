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

}