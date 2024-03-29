package molecule.document.mongodb.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{JdbcProxy, MongoProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestZioSpecBase
import molecule.document.mongodb.facade.MongoConn_JS
import zio.ZLayer
import scala.util.Random


trait ZioSpec_mongodb extends CoreTestZioSpecBase {

  override val platform = "js"
  override val database = "H2"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    val proxy = MongoProxy(
      "mongodb://localhost:27017",
      "test",
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
    )
    ZLayer.succeed(MongoConn_JS(proxy, RpcRequest.request))
  }
}
