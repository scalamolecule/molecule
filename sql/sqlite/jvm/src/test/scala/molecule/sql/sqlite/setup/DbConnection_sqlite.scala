package molecule.sql.sqlite.setup

import java.nio.file.Files
import molecule.base.api.Schema_sqlite
import molecule.core.marshalling.JdbcProxy_sqlite
import molecule.core.spi.Conn
import molecule.coreTests.domains.schema.TypesSchema_sqlite
import molecule.coreTests.setup.DbConnection
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.sqlite.facade.{JdbcConnSQlite_JVM, JdbcHandlerSQlite_JVM}
import org.sqlite.SQLiteDataSource
import scala.concurrent.Future
import scala.util.Using.Manager

trait DbConnection_sqlite extends DbConnection {

  override val platform = "jvm"

  def run(test: Conn => Any, schema: Schema_sqlite): Any = {
    val proxy                    = JdbcProxy_sqlite("jdbc:sqlite::memory:", schema)
    var conn: JdbcConnSQlite_JVM = null
    try {
      conn = JdbcHandlerSQlite_JVM.recreateDb(proxy)
      test(conn)
    } finally {
      if (conn != null && conn.sqlConn != null) {
        // Closing connection after each test
        conn.sqlConn.close()
      }
    }
  }

  lazy val sqliteDbPath = Files.createTempFile(null, ".db").toAbsolutePath
  def runRealDb(test: Conn => Any): Any = {
    val ds = new SQLiteDataSource()
    ds.setUrl("jdbc:sqlite:" + sqliteDbPath)
    Manager { use =>
      val sqlConn = use(ds.getConnection)
      val stmt    = use(sqlConn.createStatement)
      stmt.execute(TypesSchema_sqlite.schemaData.head) // transact Person schema

      val proxy = JdbcProxy_sqlite(ds.getUrl, TypesSchema_sqlite)
      val conn  = JdbcConn_JVM(proxy, sqlConn)
      test(conn) // run test
    }.get
  }




  import molecule.core.util.Executor._

  def delay[T](ms: Int)(body: => T): Future[T] = Future {
    Thread.sleep(ms)
    body
  }
}
