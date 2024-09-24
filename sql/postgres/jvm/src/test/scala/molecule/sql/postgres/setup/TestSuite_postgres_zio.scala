package molecule.sql.postgres.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuite_zio
import molecule.sql.postgres.marshalling.Connection_postgres
import zio.{ZIO, ZLayer}


trait TestSuite_postgres_zio extends CoreTestSuite_zio {
  override val platform = "jvm"
  override val database = "Postgres"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking(
        Connection_postgres.getConnection(schema)
      )
    )
  }
}
