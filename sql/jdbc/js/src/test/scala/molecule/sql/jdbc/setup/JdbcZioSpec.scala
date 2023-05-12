package molecule.sql.jdbc.setup

import molecule.base.api.Schema
import molecule.core.api.Connection
import molecule.core.marshalling.DatomicPeerProxy
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.util.TestData
import molecule.sql.jdbc.facade.JdbcConn_JS
import moleculeBuildInfo.BuildInfo
import zio.test.ZIOSpecDefault
import zio.{Task, ZIO, ZLayer}
import scala.concurrent.Promise
import scala.scalajs.js.timers.setTimeout
import scala.util.Try

trait JdbcZioSpec extends ZIOSpecDefault with TestData {

  lazy val isJsPlatform = false
  lazy val protocol     = BuildInfo.datomicProtocol
  lazy val useFree      = BuildInfo.datomicUseFree

  def inMem(schemaTx: Schema): ZLayer[Any, Throwable, Connection] = {
    val proxy = DatomicPeerProxy("mem", "", schemaTx)
    ZLayer.succeed(JdbcConn_JS(proxy, JdbcRpcRequest.moleculeRpcRequest))
  }

  def types = inMem(TypesSchema)
  def refs = inMem(RefsSchema)
  def unique = inMem(UniqueSchema)
  def validation = inMem(ValidationSchema)


  def delay[T](ms: Int)(body: => T): Task[T] = ZIO.fromFuture { _ =>
    val promise = Promise[T]()
    setTimeout(ms)(
      promise.complete(Try(body))
    )
    promise.future
  }
}
