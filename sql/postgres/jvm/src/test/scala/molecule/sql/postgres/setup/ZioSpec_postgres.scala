package molecule.sql.postgres.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.setup.CoreTestZioSpec
import molecule.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import molecule.sql.postgres.setup.{Connection_postgres => c}
import zio.{ZIO, ZLayer}


trait ZioSpec_postgres extends CoreTestZioSpec {

  override val platform = "Postgres jvm"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    def zio(conn: JdbcConn_JVM, recreationStmt: String): ZLayer[Any, Throwable, JdbcConn_JVM] = {
      ZLayer.scoped(
        ZIO.attemptBlocking(
          JdbcHandler_JVM.recreateDb(conn, recreationStmt)
        )
      )
    }

    schema match {
      case TypesSchema      => zio(c.conn_Types, c.recreateStmt_Types)
      case RefsSchema       => zio(c.conn_Refs, c.recreateStmt_Refs)
      case UniquesSchema    => zio(c.conn_Uniques, c.recreateStmt_Uniques)
      case ValidationSchema => zio(c.conn_Validation, c.recreateStmt_Validation)
    }
  }
}
