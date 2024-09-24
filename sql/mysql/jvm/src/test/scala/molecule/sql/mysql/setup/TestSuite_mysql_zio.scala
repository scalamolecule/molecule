package molecule.sql.mysql.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuite_zio
import molecule.sql.mysql.marshalling.Connection_mysql
import zio.{ZIO, ZLayer}

trait TestSuite_mysql_zio extends CoreTestSuite_zio {

  override val platform = "jvm"
  override val database = "Mysql"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking(
        Connection_mysql.getConnection(schema)
      )
    )
  }
}
