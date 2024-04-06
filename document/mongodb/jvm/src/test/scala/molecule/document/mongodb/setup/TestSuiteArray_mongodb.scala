package molecule.document.mongodb.setup

import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.spi.Conn
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.setup.CoreTestSuiteBase
import molecule.coreTests.util.Array2List
import molecule.document.mongodb.facade.MongoHandler_JVM
import molecule.document.mongodb.setup.{Connection_mongodb => c}


trait TestSuiteArray_mongodb extends CoreTestSuiteBase with Array2List with BaseHelpers {

  override val platform              = "jvm"
  override val database              = "MongoDB"
  override val isJsPlatform: Boolean = false

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val conn = schema match {
      case TypesSchema      => MongoHandler_JVM.recreateDb(c.conn_Types)
      case RefsSchema       => MongoHandler_JVM.recreateDb(c.conn_Refs)
      case UniquesSchema    => MongoHandler_JVM.recreateDb(c.conn_Uniques)
      case ValidationSchema => MongoHandler_JVM.recreateDb(c.conn_Validation)
      case PartitionsSchema => MongoHandler_JVM.recreateDb(c.conn_Partitions)
    }
    test(conn)
  }
}
