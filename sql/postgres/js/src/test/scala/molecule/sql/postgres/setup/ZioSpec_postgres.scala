package molecule.sql.postgres.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestZioSpecBase
import zio.ZLayer


trait ZioSpec_postgres extends CoreTestZioSpecBase with ConnectionJS_postgres {
  override val platform = "js"
  override val database = "Postgres"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    ZLayer.succeed(getConnection(schema))
  }
}
