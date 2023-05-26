package molecule.sql.jdbc.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.core.marshalling.{DatomicProxy, JdbcProxy, RpcRequest}
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.setup.CoreTestZioSpecBase
import molecule.coreTests.util.TestData
import molecule.sql.jdbc.facade.JdbcConn_js
import moleculeBuildInfo.BuildInfo
import zio.test.ZIOSpecDefault
import zio.{Task, ZIO, ZLayer}
import scala.concurrent.Promise
import scala.scalajs.js.timers.setTimeout
import scala.util.{Random, Try}


trait JdbcZioSpec extends CoreTestZioSpecBase {

  override val platform = "Jdbc js"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    val url   = s"jdbc:h2:mem:test_database_" + Random.nextInt()
    val proxy = JdbcProxy(
      url,
      schema.sqlSchema("h2"),
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs
    )
    ZLayer.succeed(JdbcConn_js(proxy, RpcRequest.request))
  }
}
