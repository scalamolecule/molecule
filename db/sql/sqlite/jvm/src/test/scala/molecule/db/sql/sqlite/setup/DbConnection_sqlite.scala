package molecule.db.sql.sqlite.setup

import java.sql.DriverManager
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import molecule.core.dataModel.Schema_sqlite
import molecule.db.compliance.setup.DbConnection
import molecule.db.core.marshalling.JdbcProxy
import molecule.db.core.spi.Conn
import molecule.db.sql.sqlite.facade.JdbcHandlerSQlite_JVM
import org.sqlite.SQLiteDataSource
import zio.{ZIO, ZLayer}
import scala.util.Using.Manager

trait DbConnection_sqlite extends DbConnection {

  def run(test: Conn => Any, schema: Schema_sqlite): Any = {
    // Choose running tests with in-memory or file-based database:
    runMemDb(test, schema)
    //    runFileDb(test, schema)
    //    runFileDbWithHikariCP(test, schema)
  }

  def runMemDb(test: Conn => Any, schema: Schema_sqlite): Any = {
    //    Manager { use =>
    //      val proxy   = JdbcProxy("jdbc:sqlite::memory:", schema)
    //      val sqlConn = use(DriverManager.getConnection(proxy.url))
    //      val conn    = use(JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn, true))
    //      test(conn)
    //    }.get

    // Not closing the connection between each test to allow stream
    val proxy   = JdbcProxy("jdbc:sqlite::memory:", schema)
    val sqlConn = DriverManager.getConnection(proxy.url)
    val conn    = JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn, true)
    test(conn)
  }


  def runFileDb(test: Conn => Any, schema: Schema_sqlite): Any = {
    val ds             = new SQLiteDataSource()
    val tempDbFilePath = tempDbPath
    ds.setUrl("jdbc:sqlite:" + tempDbFilePath)
    Manager { use =>
      val proxy   = JdbcProxy(ds.getUrl, schema)
      val sqlConn = use(ds.getConnection)
      val conn    = use(JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn))
      test(conn)
      tempDbFilePath.toFile.delete()
    }.get
  }


  val config = new HikariConfig()
  config.setMaximumPoolSize(10)
  config.setConnectionTimeout(3000)

  def runFileDbWithHikariCP(test: Conn => Any, schema: Schema_sqlite): Any = {
    val tempDbFilePath = tempDbPath
    config.setJdbcUrl("jdbc:sqlite:" + tempDbFilePath)
    Manager { use =>
      val ds      = use(new HikariDataSource(config))
      val proxy   = JdbcProxy(ds.getJdbcUrl, schema)
      val sqlConn = use(ds.getConnection)
      val conn    = use(JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn))
      test(conn)
      tempDbFilePath.toFile.delete()
    }.get
  }


  def connZLayer(schema: Schema_sqlite): ZLayer[Any, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking {
        val url     = "jdbc:sqlite::memory:"
        val proxy   = JdbcProxy(url, schema)
        val sqlConn = DriverManager.getConnection(proxy.url)
        JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn)
      }
    )
  }
}
