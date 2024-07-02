package molecule.sql.mysql.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestZioSpecBase
import zio.ZLayer


trait ZioSpec_mysql extends CoreTestZioSpecBase with ConnectionJS_mysql {
  override val platform = "js"
  override val database = "Mysql"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    ZLayer.succeed(getConnection(schema))
  }
}
