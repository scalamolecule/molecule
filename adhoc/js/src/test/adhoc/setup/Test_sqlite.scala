//package molecule.adhoc.setup
//
//import molecule.base.api.Schema_sqlite
//import molecule.core.marshalling.{JdbcProxy, RpcRequest}
//import molecule.core.spi.Conn
//import molecule.adhoc.domains.schema._
//import molecule.sql.core.facade.JdbcConn_JS
//import munit.FunSuite
//
//trait Test_sqlite { _: FunSuite =>
//
//  def types(test: Conn => Any): Any = run(test, TypesSchema_sqlite)
//  def refs(test: Conn => Any): Any = run(test, RefsSchema_sqlite)
//  def unique(test: Conn => Any): Any = run(test, UniquesSchema_sqlite)
//  def validation(test: Conn => Any): Any = run(test, ValidationSchema_sqlite)
//  def segments(test: Conn => Any): Any = run(test, SegmentsSchema_sqlite)
//
//  def run(test: Conn => Any, schema: Schema_sqlite): Any = {
//    val proxy = JdbcProxy("jdbc:sqlite::memory:", schema)
//    test(JdbcConn_JS(proxy, RpcRequest.request))
//  }
//}
