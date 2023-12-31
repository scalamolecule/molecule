package molecule.document.mongodb.setup

import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuite
import molecule.document.mongodb.facade.MongoHandler_JVM
import molecule.document.mongodb.schema.{Refs2Schema, Types2Schema, Uniques2Schema, Validation2Schema}
import molecule.document.mongodb.setup.{Connection_mongodb2 => c}


trait TestSuite_mongodb2 extends CoreTestSuite with BaseHelpers {

  override val database = "MongoDB"
  override val platform = "jvm"


  override def utestAfterAll(): Unit = {
    println("====== closing MongoDB client ======")
    // c.mongoClient.close() // needed for tests?
  }


  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val conn = schema match {
      case Types2Schema      => MongoHandler_JVM.recreateDb(c.conn_Types)
      case Refs2Schema       => MongoHandler_JVM.recreateDb(c.conn_Refs)
      case Uniques2Schema    => MongoHandler_JVM.recreateDb(c.conn_Uniques)
      case Validation2Schema => MongoHandler_JVM.recreateDb(c.conn_Validation)
    }
    test(conn)
  }

  override def types[T](test: Conn => T): T = inMem(test, Types2Schema)
  override def refs[T](test: Conn => T): T = inMem(test, Refs2Schema)
  override def unique[T](test: Conn => T): T = inMem(test, Uniques2Schema)
  override def validation[T](test: Conn => T): T = inMem(test, Validation2Schema)
}
