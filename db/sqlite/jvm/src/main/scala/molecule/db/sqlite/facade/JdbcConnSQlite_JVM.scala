package molecule.db.sqlite.facade

import java.sql
import java.sql.{PreparedStatement, ResultSet}
import molecule.db.common.facade.JdbcConn_JVM
import molecule.db.common.javaSql.ResultSetInterface
import molecule.db.common.marshalling.JdbcProxy
import molecule.db.sqlite.javaSql.ResultSetImpl_sqlite

class JdbcConnSQlite_JVM(
  override val proxy: JdbcProxy,
  private val sqlConn0: sql.Connection
) extends JdbcConn_JVM(proxy, sqlConn0) {

  override def queryStmt(query: String): PreparedStatement = {
    sqlConn.prepareStatement(
      query,
      ResultSet.TYPE_FORWARD_ONLY, // only option for sqlite
      ResultSet.CONCUR_READ_ONLY
    )
  }

  override def resultSet(underlying: ResultSet): ResultSetInterface = {
    new ResultSetImpl_sqlite(underlying)
  }
}