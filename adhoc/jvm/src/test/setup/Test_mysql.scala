//package molecule.adhoc.setup
//
//import com.mysql.cj.jdbc.MysqlDataSource
//import molecule.base.api.Schema_mysql
//import molecule.core.marshalling.JdbcProxy
//import molecule.core.spi.Conn
//import molecule.adhoc.domains.schema._
//import molecule.sql.core.facade.JdbcHandler_JVM
//import munit.FunSuite
//import org.testcontainers.containers.MySQLContainer
//
//trait Test_mysql { _: FunSuite =>
//
//  def types(test: Conn => Any): Any = run(test, TypesSchema_mysql)
//  def refs(test: Conn => Any): Any = run(test, RefsSchema_mysql)
//  def unique(test: Conn => Any): Any = run(test, UniquesSchema_mysql)
//  def validation(test: Conn => Any): Any = run(test, ValidationSchema_mysql)
//  def segments(test: Conn => Any): Any = run(test, SegmentsSchema_mysql)
//
//
//  private val baseUrl = "mysql:9.0.0"
//
//  println(s"Starting $baseUrl docker container...")
//  val container = new MySQLContainer(baseUrl)
//  container.start()
//  println("Mysql docker container started")
//
//  private val dataSource = new MysqlDataSource()
//  dataSource.setURL(container.getJdbcUrl)
//  dataSource.setDatabaseName(container.getDatabaseName)
//  dataSource.setUser(container.getUsername)
//  dataSource.setPassword(container.getPassword)
//  dataSource.setAllowMultiQueries(true)
//  dataSource.setAutoReconnect(true)
//
//  // Re-use connection for all tests in this test suite
//  private val reusedSqlConn = dataSource.getConnection
//
//  private val resetDb =
//    s"""DROP DATABASE IF EXISTS test;
//       |CREATE DATABASE test;
//       |USE test;
//       |""".stripMargin
//
//
//  def run(test: Conn => Any, schema: Schema_mysql): Any = {
//    val initSql = resetDb + schema.schemaData.head
//    val proxy   = JdbcProxy(baseUrl, schema, initSql)
//
//    // Not closing the connection since we re-use it
//    val conn = JdbcHandler_JVM.recreateDb(proxy, reusedSqlConn)
//    test(conn)
//  }
//}
