package molecule.sql.jdbc.setup

import molecule.base.api.Schema
import molecule.core.api.Connection
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.setup.CoreTestSuite
import molecule.sql.jdbc.api.JdbcApiAsync

trait JdbcTestSuite extends CoreTestSuite with JdbcApiAsync {

  override lazy val isJsPlatform = true
//  lazy val protocol     = BuildInfo.datomicProtocol
//  lazy val useFree      = BuildInfo.datomicUseFree

  override def inMem[T](test: Connection => T, schemaTx: Schema): T = {
//    val proxy = DatomicPeerProxy("mem", "", schemaTx)
//    test(JdbcConn_JS(proxy, JdbcRpcRequest.moleculeRpcRequest))
    ???
  }

  override def types[T](test: Connection => T): T = inMem(test, TypesSchema)
  override def refs[T](test: Connection => T): T = inMem(test, RefsSchema)
  override def unique[T](test: Connection => T): T = inMem(test, UniqueSchema)
  override def validation[T](test: Connection => T): T = inMem(test, ValidationSchema)

//  override def delay[T](ms: Int)(body: => T): Future[T] = {
//    val promise = Promise[T]()
//    setTimeout(ms)(
//      promise.complete(Try(body))
//    )
//    promise.future
//  }
}
