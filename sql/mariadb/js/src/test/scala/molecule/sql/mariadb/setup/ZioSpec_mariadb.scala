package molecule.sql.mariadb.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestZioSpecBase
import zio.ZLayer


trait ZioSpec_mariadb extends CoreTestZioSpecBase with ConnectionJS_mariadb {
  override val platform = "js"
  override val database = "MariaDB"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    ZLayer.succeed(getConnection(schema))
  }
}
