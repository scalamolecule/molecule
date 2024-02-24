package molecule.sql.mariadb.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{JdbcProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestZioSpecBase
import molecule.sql.core.facade.JdbcConn_JS
import zio.ZLayer


trait ZioSpec_mariadb extends CoreTestZioSpecBase {

  override val platform = "js"
  override val database = "MariaDB"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    val url   = "jdbc:tc:mariadb:latest:///test?allowMultiQueries=true&user=root&password=foo"

    val recreateSchema1 =
      s"""drop database if exists test;
         |create database test;
         |use test;
         |""".stripMargin

    val proxy = JdbcProxy(
      url,
      recreateSchema1 + schema.sqlSchema_mariadb,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_mariadb,
      useTestContainer = true
    )
    ZLayer.succeed(JdbcConn_JS(proxy, RpcRequest.request))
  }
}
