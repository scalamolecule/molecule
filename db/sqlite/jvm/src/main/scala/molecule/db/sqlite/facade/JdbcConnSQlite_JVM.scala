package molecule.db.sqlite.facade

import java.sql
import java.sql.{PreparedStatement, ResultSet}
import molecule.db.common.authentication.AuthContext
import molecule.db.common.facade.JdbcConn_JVM
import molecule.db.common.javaSql.ResultSetInterface
import molecule.db.common.marshalling.JdbcProxy
import molecule.db.sqlite.javaSql.ResultSetImpl_sqlite

class JdbcConnSQlite_JVM(
  override val proxy: JdbcProxy,
  private val sqlConn0: sql.Connection,
  override val authContext: Option[AuthContext] = None
) extends JdbcConn_JVM(
  proxy,
  sqlConn0,
  authContext
) {

  // SQLite uses custom ResultSetImpl for performance (caching)
  override def resultSet(underlying: ResultSet): ResultSetInterface = {
    new ResultSetImpl_sqlite(underlying)
  }

  override def queryStmt(query: String): PreparedStatement = {
    sqlConn.prepareStatement(
      query,
      ResultSet.TYPE_FORWARD_ONLY, // only option for sqlite
      ResultSet.CONCUR_READ_ONLY
    )
  }

  override def withAuthContext(authCtx: AuthContext): JdbcConnSQlite_JVM =
    new JdbcConnSQlite_JVM(proxy, sqlConn0, Some(authCtx))

  override def clearAuth: JdbcConnSQlite_JVM =
    new JdbcConnSQlite_JVM(proxy, sqlConn0, None)
}