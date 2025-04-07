//package molecule.adhoc.setup
//
//import molecule.base.api.Schema_mariadb
//import molecule.core.marshalling.{JdbcProxy, RpcRequest}
//import molecule.core.spi.Conn
//import molecule.adhoc.domains.schema._
//import molecule.sql.core.facade.JdbcConn_JS
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
//  def run(test: Conn => Any, schema: Schema_mariadb): Any = {
//    // Since RPC calls run in parallel we need a new connection for
//    // each test when using Docker containers.
//    // This makes the test suite run slower compared to sequential runs
//    // of jvm tests.
//    val n   = Random.nextInt().abs
//    val url = s"jdbc:tc:mariadb:latest:///test$n" +
//      s"?allowMultiQueries=true" +
//      s"&autoReconnect=true" +
//      s"&user=root" +
//      s"&password="
//
//    val proxy = JdbcProxy(url, schema)
//    val conn  = JdbcConn_JS(proxy, RpcRequest.request)
//    test(conn)
//  }
//}
