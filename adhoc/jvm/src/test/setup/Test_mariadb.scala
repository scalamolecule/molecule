//package molecule.adhoc.setup
//
//import java.sql.DriverManager
//import com.dimafeng.testcontainers.MariaDBContainer
//import molecule.base.api.Schema_mariadb
//import molecule.core.marshalling.JdbcProxy
//import molecule.core.spi.Conn
//import molecule.adhoc.domains.schema._
//import molecule.sql.core.facade.JdbcHandler_JVM
//import munit.FunSuite
//import scala.util.Random
//
//trait Test_mariadb { _: FunSuite =>
//
//  def types(test: Conn => Any): Any = run(test, TypesSchema_mariadb)
//  def refs(test: Conn => Any): Any = run(test, RefsSchema_mariadb)
//  def unique(test: Conn => Any): Any = run(test, UniquesSchema_mariadb)
//  def validation(test: Conn => Any): Any = run(test, ValidationSchema_mariadb)
//  def segments(test: Conn => Any): Any = run(test, SegmentsSchema_mariadb)
//
//  private val url = s"jdbc:tc:mariadb:latest:///test" +
//    s"?allowMultiQueries=true" +
//    s"&autoReconnect=true" +
//    s"&user=root" +
//    s"&password="
//
//  // Using dimafeng container for MariaDB to be able to config through url
//  println(s"Starting mariadb:latest docker container...")
//  private val container = MariaDBContainer()
//  Class.forName(container.driverClassName)
//  println("MariaDB docker container started")
//
//  // Re-use connection for all tests in this test suite
//  private val reusedSqlConn = DriverManager.getConnection(url)
//
//  private val resetDb =
//    s"""DROP DATABASE IF EXISTS test;
//       |CREATE DATABASE test;
//       |USE test;
//       |""".stripMargin
//
//  def run(test: Conn => Any, schema: Schema_mariadb): Any = {
//    val initSql = resetDb + schema.schemaData.head
//    val proxy   = JdbcProxy(url, schema, initSql)
//
//    // Not closing the connection since we re-use it
//    val conn = JdbcHandler_JVM.recreateDb(proxy, reusedSqlConn)
//    test(conn)
//  }
//}
