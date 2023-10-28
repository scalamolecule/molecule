package molecule.document.mongodb.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{JdbcProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestZioSpecBase
import molecule.document.mongodb.facade.MongoConn_JS
import zio.ZLayer
import scala.util.Random


trait ZioSpec_mongodb extends CoreTestZioSpecBase {

  override val database = "H2"
  override val platform = "js"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    val url   = s"jdbc:h2:mem:test_database_" + Random.nextInt()
//    val proxy = JdbcProxy(
//      url,
//      schema.sqlSchema_mongodb,
//      schema.metaSchema,
//      schema.nsMap,
//      schema.attrMap,
//      schema.uniqueAttrs,
//      reserved = schema.sqlReserved_mongodb
//    )
//    ZLayer.succeed(JdbcConn_JS(proxy, RpcRequest.request))
    ???
  }
}
