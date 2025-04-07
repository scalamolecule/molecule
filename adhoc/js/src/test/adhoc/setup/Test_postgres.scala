//package molecule.adhoc.setup
//
//import molecule.base.api.{Schema_postgres, Schema_sqlite}
//import molecule.core.marshalling.{JdbcProxy, RpcRequest}
//import molecule.core.spi.Conn
//import molecule.adhoc.domains.schema._
//import molecule.sql.core.facade.JdbcConn_JS
//import munit.FunSuite
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
//  def getConnection(schema: Schema_postgres): JdbcConn_JS = {
//    // Since RPC calls run in parallel we need a new connection for
//    // each test when using Docker containers.
//    // This makes the test suite run slower compared to sequential runs
//    // of jvm tests.
//    val n   = Random.nextInt().abs
//    val url = s"jdbc:tc:postgresql:16://localhost:5432/test$n" +
//      s"?preparedStatementCacheQueries=0"
//
//    val proxy = JdbcProxy(url, schema)
//    JdbcConn_JS(proxy, RpcRequest.request)
//  }
//
//  def run(test: Conn => Any, schema: Schema_postgres): Any = {
//    test(getConnection(schema))
//  }
//}
