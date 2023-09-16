package molecule.sql.postgres.setup

import molecule.base.api.Schema
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestZioSpec
import molecule.sql.core.facade.JdbcHandler_JVM
import zio.{ZIO, ZLayer}
import scala.util.Random


trait ZioSpec_postgres extends CoreTestZioSpec {

  override val platform = "Postgres jvm"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    val url   = s"jdbc:h2:mem:test_database_" + Random.nextInt()
    val proxy = JdbcProxy(
      url,
      schema.sqlSchema_h2,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs
    )
    ZLayer.scoped(
      ZIO.attemptBlocking(
        JdbcHandler_JVM.recreateDb(proxy)
      )
    )
  }
}
