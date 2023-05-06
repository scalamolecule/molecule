package molecule.sql.core.setup

import molecule.base.api.SchemaTransaction
import molecule.core.api.Connection
import molecule.core.marshalling.DatomicPeerProxy
import molecule.coreTests.dataModels.core.schema._
import molecule.sql.core.facade.SqlConn_JS
import moleculeBuildInfo.BuildInfo
import scala.concurrent.{Future, Promise}
import scala.scalajs.js.timers.setTimeout
import scala.util.Try

trait SqlTestSuite extends SqlTestSuiteBase {

  lazy val isJsPlatform = true
  lazy val protocol     = BuildInfo.datomicProtocol
  lazy val useFree      = BuildInfo.datomicUseFree

  def inMem[T](test: Connection => T, schemaTx: SchemaTransaction): T = {
    val proxy = DatomicPeerProxy("mem", "", schemaTx)
    test(SqlConn_JS(proxy, SqlRpcRequest.moleculeRpcRequest))
  }

  def types[T](test: Connection => T): T = inMem(test, TypesSchema)
  def refs[T](test: Connection => T): T = inMem(test, RefsSchema)
  def unique[T](test: Connection => T): T = inMem(test, UniqueSchema)
  def validation[T](test: Connection => T): T = inMem(test, ValidationSchema)

  def delay[T](ms: Int)(body: => T): Future[T] = {
    val promise = Promise[T]()
    setTimeout(ms)(
      promise.complete(Try(body))
    )
    promise.future
  }
}
