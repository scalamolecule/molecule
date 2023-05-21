//package molecule.sql.jdbc.setup
//
//import molecule.base.api.Schema
//import molecule.core.api.Connection
//import molecule.core.api.gateway.ApiAsyncGateway
//import molecule.core.marshalling.SqlProxy
//import molecule.coreTests.dataModels.core.schema._
//import molecule.sql.jdbc.api.JdbcApiAsync
//import utest.TestSuite
//import scala.util.Random
//import scala.util.control.NonFatal
//
////trait JdbcTestSuite2 extends JdbcApiAsync {
//trait JdbcTestSuite2 extends TestSuite with JdbcApiAsync with ApiAsyncGateway {
//
//  lazy val isJsPlatform = false
////  lazy val protocol     = BuildInfo.datomicProtocol
////  lazy val useFree      = BuildInfo.datomicUseFree
//
//  def inMem[T](test: Connection => T, schemaTx: Schema): T = {
//    //    val proxy = DatomicPeerProxy("mem", "", schemaTx)
//    //    test(JdbcConn_JS(proxy, JdbcRpcRequest.moleculeRpcRequest))
//    ???
//  }
//
//  def types[T](test: Connection => T): T = inMem(test, TypesSchema)
//  def refs[T](test: Connection => T): T = inMem(test, RefsSchema)
//  def unique[T](test: Connection => T): T = inMem(test, UniqueSchema)
//  def validation[T](test: Connection => T): T = inMem(test, ValidationSchema)
//}
