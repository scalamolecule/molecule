package molecule.document.mongodb.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{JdbcProxy, MongoProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuite
import molecule.document.mongodb.facade.MongoConn_JS
import scala.util.Random


trait TestSuite_mongodb extends CoreTestSuite {

  override val database = "MongoDB"
  override val platform = "js"

  override def inMem[T](test: Conn => T, schema: Schema): T = {
//    val url   = s"jdbc:h2:mem:test_database_" + Random.nextInt()
    val proxy = MongoProxy(
      "mongodb://localhost:27017",
      "test",
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
    )
//    test(MongoDBConn_JS(proxy, RpcRequest.request))

    ???
  }

}
