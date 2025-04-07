//package molecule.adhoc.setup
//
//import java.nio.file.{Files, Path}
//import java.sql.DriverManager
//import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
//import molecule.base.api.Schema_h2
//import molecule.core.marshalling.JdbcProxy
//import molecule.core.spi.Conn
//import molecule.adhoc.domains.schema._
//import molecule.sql.core.facade.JdbcHandler_JVM
//import munit.FunSuite
//import org.h2.jdbcx.JdbcDataSource
//import scala.util.Random
//import scala.util.Using.Manager
//
//trait Test_h2 { _: FunSuite =>
//
//  def types(test: Conn => Any): Any = run(test, TypesSchema_h2)
//  def refs(test: Conn => Any): Any = run(test, RefsSchema_h2)
//  def unique(test: Conn => Any): Any = run(test, UniquesSchema_h2)
//  def validation(test: Conn => Any): Any = run(test, ValidationSchema_h2)
//  def segments(test: Conn => Any): Any = run(test, SegmentsSchema_h2)
//
//
//  def run(test: Conn => Any, schema: Schema_h2): Any = {
//    // Choose running tests with in-memory or file-based database:
//    runMemDb(test, schema)
//    //    runFileDb(test, schema)
//    //    runFileDbWithHikariCP(test, schema)
//  }
//
//  def runMemDb(test: Conn => Any, schema: Schema_h2): Any = {
//    val url = "jdbc:h2:mem:test" + Random.nextInt().abs
//    Manager { use =>
//      val proxy   = JdbcProxy(url, schema)
//      val sqlConn = use(DriverManager.getConnection(proxy.url))
//      val conn    = use(JdbcHandler_JVM.recreateDb(proxy, sqlConn))
//      test(conn)
//    }.get
//  }
//
//
//  def runFileDb(test: Conn => Any, schema: Schema_h2): Any = {
//    val ds             = new JdbcDataSource()
//    val tempDbFilePath = tempDbPath
//    ds.setUrl("jdbc:h2:" + tempDbFilePath)
//    Manager { use =>
//      val proxy   = JdbcProxy(ds.getUrl, schema)
//      val sqlConn = use(ds.getConnection)
//      val conn    = use(JdbcHandler_JVM.recreateDb(proxy, sqlConn))
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
//  def runFileDbWithHikariCP(test: Conn => Any, schema: Schema_h2): Any = {
//    val tempDbFilePath = tempDbPath
//    config.setJdbcUrl("jdbc:h2:" + tempDbFilePath)
//    Manager { use =>
//      val ds      = use(new HikariDataSource(config))
//      val proxy   = JdbcProxy(ds.getJdbcUrl, schema)
//      val sqlConn = use(ds.getConnection)
//      val conn    = use(JdbcHandler_JVM.recreateDb(proxy, sqlConn))
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
