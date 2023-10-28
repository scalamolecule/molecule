package molecule.document.mongodb.setup


import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.spi.Conn
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.setup.CoreTestSuite
import molecule.document.mongodb.facade.MongoHandler_JVM
import molecule.document.mongodb.setup.{Connection_mongodb => c}


trait TestSuite_mongodb extends CoreTestSuite with BaseHelpers {

  override val database = "MongoDB"
  override val platform = "jvm"


  override def utestAfterAll(): Unit = {
    println("====== closing MongoDB client ======")
    c.mongoClient.close()
  }


  override def inMem[T](test: Conn => T, schema: Schema): T = {
    //        c.collection.drop()

    val conn = schema match {
      case TypesSchema      => MongoHandler_JVM.recreateDb(c.conn_Types)
      case RefsSchema       => MongoHandler_JVM.recreateDb(c.conn_Refs)
      case UniquesSchema    => MongoHandler_JVM.recreateDb(c.conn_Uniques)
      case ValidationSchema => MongoHandler_JVM.recreateDb(c.conn_Validation)
    }
    test(conn)
  }
}
