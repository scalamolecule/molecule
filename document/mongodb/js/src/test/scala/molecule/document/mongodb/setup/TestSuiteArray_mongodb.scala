package molecule.document.mongodb.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{MongoProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.{CoreTestSuite, CoreTestSuiteBase}
import molecule.document.mongodb.facade.MongoConn_JS


trait TestSuiteArray_mongodb extends CoreTestSuiteBase {

  override val platform              = "js"
  override val database              = "MongoDB"
  override val isJsPlatform: Boolean = true

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val proxy = MongoProxy(
      "mongodb://localhost:27017",
      "test",
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      useTestContainer = true
    )
    test(MongoConn_JS(proxy, RpcRequest.request))
  }
}
