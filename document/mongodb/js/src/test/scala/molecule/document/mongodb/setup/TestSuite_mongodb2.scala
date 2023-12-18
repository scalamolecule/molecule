package molecule.document.mongodb.setup

import molecule.base.api.Schema
import molecule.core.marshalling.MongoProxy
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuite


trait TestSuite_mongodb2 extends CoreTestSuite {

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
