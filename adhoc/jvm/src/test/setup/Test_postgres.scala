//package molecule.adhoc.setup
//
//import molecule.base.api.Schema_postgres
//import molecule.core.marshalling.JdbcProxy
//import molecule.core.spi.Conn
//import molecule.adhoc.domains.schema._
//import molecule.sql.core.facade.JdbcHandler_JVM
//import munit.FunSuite
//import org.postgresql.ds.PGSimpleDataSource
//import org.testcontainers.containers.PostgreSQLContainer
//import scala.util.Random
//
//trait Test_postgres { _: FunSuite =>
//
//  def types(test: Conn => Any): Any = run(test, TypesSchema_postgres)
//  def refs(test: Conn => Any): Any = run(test, RefsSchema_postgres)
//  def unique(test: Conn => Any): Any = run(test, UniquesSchema_postgres)
//  def validation(test: Conn => Any): Any = run(test, ValidationSchema_postgres)
//  def segments(test: Conn => Any): Any = run(test, SegmentsSchema_postgres)
//
//
//  private val baseUrl = "postgres:17"
//
//  println(s"Starting $baseUrl docker container...")
//  val container = new PostgreSQLContainer(baseUrl)
//  container.start()
//  println("Postgres docker container started")
//
//  private val dataSource = new PGSimpleDataSource()
//  dataSource.setURL(container.getJdbcUrl)
//  dataSource.setDatabaseName(container.getDatabaseName)
//  dataSource.setUser(container.getUsername)
//  dataSource.setPassword(container.getPassword)
//  dataSource.setPreparedStatementCacheQueries(0)
//
//  // Re-use connection for all tests in this test suite
//  private val reusedSqlConn = dataSource.getConnection
//
//  private val resetDb =
//    s"""DROP SCHEMA IF EXISTS public CASCADE;
//       |CREATE SCHEMA public;
//       |""".stripMargin
//
//
//  def run(test: Conn => Any, schema: Schema_postgres): Any = {
//    val initSql = resetDb + schema.schemaData.head
//    val proxy   = JdbcProxy(baseUrl, schema, initSql)
//
//    // Not closing the connection since we re-use it
//    val conn = JdbcHandler_JVM.recreateDb(proxy, reusedSqlConn)
//    test(conn)
//  }
//}
