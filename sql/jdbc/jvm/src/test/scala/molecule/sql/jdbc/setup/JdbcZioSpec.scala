package molecule.sql.jdbc.setup

import molecule.base.api.Schema
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.setup.CoreTestZioSpec
import molecule.sql.jdbc.facade.JdbcHandler_jvm
import zio.{ZIO, ZLayer}
import scala.util.Random


trait JdbcZioSpec extends CoreTestZioSpec {

  override val platform = "Jdbc jvm"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    val url   = s"jdbc:h2:mem:test_database_" + Random.nextInt()
    val proxy = JdbcProxy(
      url,
      schema.sqlSchema("h2"),
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs
    )
    ZLayer.scoped(
      ZIO.attemptBlocking(
        JdbcHandler_jvm.recreateDb(proxy, url)
      )
    )
  }
}
