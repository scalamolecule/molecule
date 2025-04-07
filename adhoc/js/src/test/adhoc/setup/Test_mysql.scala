//package molecule.adhoc.setup
//
//import molecule.base.api.Schema_mysql
//import molecule.core.marshalling.{JdbcProxy, RpcRequest}
//import molecule.core.spi.Conn
//import molecule.adhoc.domains.schema._
//import molecule.sql.core.facade.JdbcConn_JS
//import munit.FunSuite
//import scala.util.Random
//
//trait Test_mysql { _: FunSuite =>
//
//  def types(test: Conn => Any): Any = run(test, TypesSchema_mysql)
//  def refs(test: Conn => Any): Any = run(test, RefsSchema_mysql)
//  def unique(test: Conn => Any): Any = run(test, UniquesSchema_mysql)
//  def validation(test: Conn => Any): Any = run(test, ValidationSchema_mysql)
//  def segments(test: Conn => Any): Any = run(test, SegmentsSchema_mysql)
//
//  def getConnection(schema: Schema_mysql): JdbcConn_JS = {
//    // Since RPC calls run in parallel we need a new connection for
//    // each test when using Docker containers.
//    // This makes the test suite run slower compared to sequential runs
//    // of jvm tests.
//    val n   = Random.nextInt().abs
//    val url = s"jdbc:tc:mysql:8.1://localhost:3306/test$n" +
//      s"?allowMultiQueries=true"
//
//    val proxy = JdbcProxy(url, schema)
//    JdbcConn_JS(proxy, RpcRequest.request)
//  }
//
//  def run(test: Conn => Any, schema: Schema_mysql): Any = {
//    test(getConnection(schema))
//  }
//}
