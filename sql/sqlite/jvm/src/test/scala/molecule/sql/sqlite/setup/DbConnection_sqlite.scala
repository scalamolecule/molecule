package molecule.sql.sqlite.setup

import java.nio.file.{Files, Path}
import java.sql.DriverManager
import molecule.base.api.Schema_sqlite
import molecule.core.marshalling.JdbcProxy_sqlite
import molecule.core.spi.Conn
import molecule.coreTests.setup.DbConnection
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.sqlite.facade.{JdbcConnSQlite_JVM, JdbcHandlerSQlite_JVM}
import org.sqlite.SQLiteDataSource
import scala.util.Using.Manager

trait DbConnection_sqlite extends DbConnection {

  override val platform = "jvm"

  def run(test: Conn => Any, schema: Schema_sqlite): Any = {
    // Choose running tests with in-memory or file-based database:
    runMemDb(test, schema)
//        runFileDb(test, schema)
  }

  def runMemDb(test: Conn => Any, schema: Schema_sqlite): Any = {
    Manager { use =>
      val proxy = JdbcProxy_sqlite("jdbc:sqlite::memory:", schema)
      val sqlConn = use(DriverManager.getConnection(proxy.url))
      val conn  = use(JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn))
      test(conn)
    }.get
  }

  def runFileDb(test: Conn => Any, schema: Schema_sqlite): Any = {
    val ds = new SQLiteDataSource()
    ds.setUrl("jdbc:sqlite:" + sqliteDbPath)
    Manager { use =>
      val proxy   = JdbcProxy_sqlite(ds.getUrl, schema)
      val sqlConn = use(ds.getConnection)
      val conn    = use(JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn))
      test(conn)
    }.get
  }

  private def sqliteDbPath: Path = {
    // Create temp db file for each test suite
    Files.createTempFile(null, ".db").toAbsolutePath
  }
}
