package molecule.sql.postgres.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.setup.CoreTestZioSpec
import molecule.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import molecule.sql.postgres.marshalling.{Connection_postgres, Connection_postgres => c}
import zio.{ZIO, ZLayer}


trait ZioSpec_postgres extends CoreTestZioSpec {
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
