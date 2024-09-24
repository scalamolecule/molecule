package molecule.sql.mariadb.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuite_zio
import molecule.sql.mariadb.marshalling.Connection_mariadb
import zio.{ZIO, ZLayer}

trait TestSuite_mariadb_zio extends CoreTestSuite_zio {

  override val platform = "jvm"
  override val database = "MariaDB"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking(
        Connection_mariadb.getConnection(schema)
      )
    )
  }
}
