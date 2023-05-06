package molecule.sql.core.setup

import molecule.base.api.SchemaTransaction
import molecule.core.api.Connection
import molecule.core.marshalling.DatomicPeerProxy
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.util.TestData
import molecule.sql.core.facade.SqlConn_JS
import moleculeBuildInfo.BuildInfo
import zio.test.ZIOSpecDefault
import zio.{Task, ZIO, ZLayer}
import scala.concurrent.Promise
import scala.scalajs.js.timers.setTimeout
import scala.util.Try

trait SqlZioSpec extends ZIOSpecDefault with TestData {

  lazy val isJsPlatform = false
  lazy val protocol     = BuildInfo.datomicProtocol
  lazy val useFree      = BuildInfo.datomicUseFree

  def inMem(schemaTx: SchemaTransaction): ZLayer[Any, Throwable, Connection] = {
    val proxy = DatomicPeerProxy("mem", "", schemaTx)
    ZLayer.succeed(SqlConn_JS(proxy, SqlRpcRequest.moleculeRpcRequest))
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
