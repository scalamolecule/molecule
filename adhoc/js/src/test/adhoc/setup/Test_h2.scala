//package molecule.adhoc.setup
//
//import molecule.base.api.Schema_h2
//import molecule.core.marshalling.{JdbcProxy, RpcRequest}
//import molecule.core.spi.Conn
//import molecule.adhoc.domains.schema._
//import molecule.sql.core.facade.JdbcConn_JS
//import munit.FunSuite
//import scala.util.Random
//
//trait Test_h2 { _: FunSuite =>
//
//  def types(test: Conn => Any): Any = run(test, TypesSchema_h2)
//  def refs(test: Conn => Any): Any = run(test, RefsSchema_h2)
//  def unique(test: Conn => Any): Any = run(test, UniquesSchema_h2)
//  def validation(test: Conn => Any): Any = run(test, ValidationSchema_h2)
//  def segments(test: Conn => Any): Any = run(test, SegmentsSchema_h2)
//
//  def run(test: Conn => Any, schema: Schema_h2): Any = {
//    val url   = s"jdbc:h2:mem:test" + Random.nextInt().abs + ";DB_CLOSE_DELAY=-1"
//    val proxy = JdbcProxy(url, schema)
//    val conn  = JdbcConn_JS(proxy, RpcRequest.request)
//    test(conn)
//  }
//}
