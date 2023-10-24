package molecule.document.mongodb.setup

import molecule.base.api.Schema
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestZioSpec
import molecule.document.mongodb.facade.MongoHandler_JVM
import zio.{ZIO, ZLayer}
import scala.util.Random


trait ZioSpec_mongodb extends CoreTestZioSpec {

  override val database = "H2"
  override val platform = "jvm"

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
//    ZLayer.scoped(
//      ZIO.attemptBlocking(
//        JdbcHandler_JVM.recreateDb(proxy)
//      )
//    )
    ???
  }
}
