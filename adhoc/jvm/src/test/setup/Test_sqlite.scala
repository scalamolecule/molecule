//package molecule.adhoc.setup
//
//import java.nio.file.{Files, Path}
//import java.sql.DriverManager
//import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
//import molecule.base.api.Schema_sqlite
//import molecule.core.marshalling.JdbcProxy
//import molecule.core.spi.Conn
//import molecule.adhoc.domains.schema._
//import molecule.sql.sqlite.facade.JdbcHandlerSQlite_JVM
//import munit.FunSuite
//import org.sqlite.SQLiteDataSource
//import scala.util.Using.Manager
//
//trait Test_sqlite { _: FunSuite =>
//
//  def types(test: Conn => Any): Any = run(test, TypesSchema_sqlite)
//  def refs(test: Conn => Any): Any = run(test, RefsSchema_sqlite)
//  def unique(test: Conn => Any): Any = run(test, UniquesSchema_sqlite)
//  def validation(test: Conn => Any): Any = run(test, ValidationSchema_sqlite)
//  def segments(test: Conn => Any): Any = run(test, SegmentsSchema_sqlite)
//
//
//  def run(test: Conn => Any, schema: Schema_sqlite): Any = {
//    // Choose running tests with in-memory or file-based database:
//    runMemDb(test, schema)
//    //    runFileDb(test, schema)
//    //    runFileDbWithHikariCP(test, schema)
//  }
//
//  def runMemDb(test: Conn => Any, schema: Schema_sqlite): Any = {
//    Manager { use =>
//      val proxy   = JdbcProxy("jdbc:sqlite::memory:", schema)
//      val sqlConn = use(DriverManager.getConnection(proxy.url))
//      val conn    = use(JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn, true))
//      test(conn)
//    }.get
//  }
//
//
//  def runFileDb(test: Conn => Any, schema: Schema_sqlite): Any = {
//    val ds             = new SQLiteDataSource()
//    val tempDbFilePath = tempDbPath
//    ds.setUrl("jdbc:sqlite:" + tempDbFilePath)
//    Manager { use =>
//      val proxy   = JdbcProxy(ds.getUrl, schema)
//      val sqlConn = use(ds.getConnection)
//      val conn    = use(JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn))
//      test(conn)
//      tempDbFilePath.toFile.delete()
//    }.get
//  }
//
//
//  val config = new HikariConfig()
//  config.setMaximumPoolSize(10)
//  config.setConnectionTimeout(3000)
//
//  def runFileDbWithHikariCP(test: Conn => Any, schema: Schema_sqlite): Any = {
//    val tempDbFilePath = tempDbPath
//    config.setJdbcUrl("jdbc:sqlite:" + tempDbFilePath)
//    Manager { use =>
//      val ds      = use(new HikariDataSource(config))
//      val proxy   = JdbcProxy(ds.getJdbcUrl, schema)
//      val sqlConn = use(ds.getConnection)
//      val conn    = use(JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn))
//      test(conn)
//      tempDbFilePath.toFile.delete()
//    }.get
//  }
//
//  def tempDbPath: Path = {
//    // Create temp db file for each test suite
//    Files.createTempFile(null, ".db").toAbsolutePath
//  }
//}
