package molecule.db.h2.facade

import java.sql.Connection
import molecule.db.common.authentication.AuthContext
import molecule.db.common.facade.JdbcConn_JVM
import molecule.db.common.marshalling.JdbcProxy

class JdbcConn_JVM_h2(
  proxy: JdbcProxy,
  sqlConn: Connection,
  authContext: Option[AuthContext] = None
) extends JdbcConn_JVM(proxy, sqlConn, authContext) {

  override def withAuthContext(authCtx: AuthContext): JdbcConn_JVM_h2 = {
    new JdbcConn_JVM_h2(proxy, sqlConn, Some(authCtx))
  }

  override def clearAuth: JdbcConn_JVM_h2 = {
    new JdbcConn_JVM_h2(proxy, sqlConn, None)
  }
}

object JdbcConn_JVM_h2 {
  def apply(proxy: JdbcProxy, sqlConn: Connection, authContext: Option[AuthContext] = None): JdbcConn_JVM_h2 =
    new JdbcConn_JVM_h2(proxy, sqlConn, authContext)
}
