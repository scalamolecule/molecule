package molecule.sql.h2.setup

import molecule.base.api.Schema
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestZioSpec
import molecule.sql.core.facade.JdbcHandler_JVM
import zio.{ZIO, ZLayer}
import scala.util.Random


trait ZioSpec_h2 extends CoreTestZioSpec {

  override val platform = "jvm"
  override val database = "H2"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    val url   = s"jdbc:h2:mem:test_database_" + Random.nextInt()
    val proxy = JdbcProxy(
      url,
      schema.sqlSchema_h2,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_h2
    )
    ZLayer.scoped(
      ZIO.attemptBlocking(
        JdbcHandler_JVM.recreateDb(proxy)
      )
    )
  }
}
