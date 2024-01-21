package molecule.document.mongodb.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.dataModels.core.schema.{RefsSchema, TypesSchema, UniquesSchema, ValidationSchema}
import molecule.coreTests.setup.CoreTestZioSpec
import molecule.document.mongodb.facade.MongoHandler_JVM
import molecule.document.mongodb.setup.{Connection_mongodb => c}
import zio.{ZIO, ZLayer}


trait ZioSpec_mongodb extends CoreTestZioSpec {

  override val database = "MongoDB"
  override val platform = "jvm"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking(
        schema match {
          case TypesSchema      => MongoHandler_JVM.recreateDb(c.conn_Types)
          case RefsSchema       => MongoHandler_JVM.recreateDb(c.conn_Refs)
          case UniquesSchema    => MongoHandler_JVM.recreateDb(c.conn_Uniques)
          case ValidationSchema => MongoHandler_JVM.recreateDb(c.conn_Validation)
        }
      )
    )
  }
}
