package molecule.db.sqlite.setup

import java.sql.DriverManager
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import molecule.db.common.api.MetaDb_sqlite
import molecule.db.common.marshalling.JdbcProxy
import molecule.db.common.spi.Conn
import molecule.db.compliance.setup.DbConnection
import molecule.db.sqlite.facade.JdbcHandlerSQlite_JVM
import org.sqlite.SQLiteDataSource
import zio.{ZIO, ZLayer}
import scala.util.Using.Manager
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.metadb.Types_MetaDb_sqlite
import scala.concurrent.ExecutionContextExecutor
//import scala.concurrent.ExecutionContext

trait DbConnection_sqlite extends DbConnection {


  //  def run2(test: Conn ?=> ExecutionContext ?=> Any, metaDb: MetaDb_sqlite): Any = {
  //    val proxy   = JdbcProxy("jdbc:sqlite::memory:", metaDb)
  //    val sqlConn = DriverManager.getConnection(proxy.url)
  //    given Conn = JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn, true)
  //    given ExecutionContext = scala.concurrent.ExecutionContext.global
  //    test
  //  }

  //  def run2(test: (Conn, ExecutionContext) ?=> Any, metaDb: MetaDb_sqlite): Any = {
  //    val proxy   = JdbcProxy("jdbc:sqlite::memory:", metaDb)
  //    val sqlConn = DriverManager.getConnection(proxy.url)
  //    given Conn = JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn, true)
  //    given ExecutionContext = scala.concurrent.ExecutionContext.global
  //    test
  //  }
  //
  //  def types2(test: (Conn, ExecutionContext) ?=> Any): Any = run2(test, Types_MetaDb_sqlite())


  //  def run3[T](test: (Conn, ExecutionContextExecutor) ?=> T, metaDb: MetaDb_sqlite): T = {
  //    val proxy   = JdbcProxy("jdbc:sqlite::memory:", metaDb)
  //    val sqlConn = DriverManager.getConnection(proxy.url)
  //    given Conn = JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn, true)
  //    given ExecutionContextExecutor = global
  //    test
  //  }

  //  def types3[T](test: (Conn, ExecutionContextExecutor) ?=> T): T = run3[T](test, Types_MetaDb_sqlite())

  def run4(test: Conn ?=> Any, metaDb: MetaDb_sqlite): Any = {
    val proxy   = JdbcProxy("jdbc:sqlite::memory:", metaDb)
    val sqlConn = DriverManager.getConnection(proxy.url)
    given Conn = JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn, true)
    test
  }

  //  def types4(test: Conn ?=> Any): Any = run4(test, Types_MetaDb_sqlite())


  def run(test: Conn ?=> Any, metaDb: MetaDb_sqlite): Any = {
    // Choose running tests with in-memory or file-based database:
    runMemDb(test, metaDb)
    //    runFileDb(test, schema)
    //    runFileDbWithHikariCP(test, schema)
  }

  def runMemDb(test: Conn ?=> Any, metaDb: MetaDb_sqlite): Any = {
    //    Manager { use =>
    //      val proxy   = JdbcProxy("jdbc:sqlite::memory:", schema)
    //      val sqlConn = use(DriverManager.getConnection(proxy.url))
    //      val conn    = use(JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn, true))
    //      test(conn)
    //    }.get

    // Not closing the connection between each test to allow stream
    //    val proxy   = JdbcProxy("jdbc:sqlite::memory:", metaDb)
    //    val sqlConn = DriverManager.getConnection(proxy.url)
    //    val conn    = JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn, true)
    //    test(conn)

    val proxy   = JdbcProxy("jdbc:sqlite::memory:", metaDb)
    val sqlConn = DriverManager.getConnection(proxy.url)
    given Conn = JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn, true)
    test
  }


  def runFileDb(test: Conn ?=> Any, metaDb: MetaDb_sqlite): Any = {
    val ds             = new SQLiteDataSource()
    val tempDbFilePath = tempDbPath
    ds.setUrl("jdbc:sqlite:" + tempDbFilePath)
    Manager { use =>
      val proxy   = JdbcProxy(ds.getUrl, metaDb)
      val sqlConn = use(ds.getConnection)
      given Conn = use(JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn))
      test
      tempDbFilePath.toFile.delete()
    }.get
  }


  val config = new HikariConfig()
  config.setMaximumPoolSize(10)
  config.setConnectionTimeout(3000)

  def runFileDbWithHikariCP(test: Conn ?=> Any, metaDb: MetaDb_sqlite): Any = {
    val tempDbFilePath = tempDbPath
    config.setJdbcUrl("jdbc:sqlite:" + tempDbFilePath)
    Manager { use =>
      val ds      = use(new HikariDataSource(config))
      val proxy   = JdbcProxy(ds.getJdbcUrl, metaDb)
      val sqlConn = use(ds.getConnection)
      given Conn = use(JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn))
      test
      tempDbFilePath.toFile.delete()
    }.get
  }


  def connZLayer(metaDb: MetaDb_sqlite): ZLayer[Any, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking {
        val url     = "jdbc:sqlite::memory:"
        val proxy   = JdbcProxy(url, metaDb)
        val sqlConn = DriverManager.getConnection(proxy.url)
        JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn)
      }
    )
  }
}
