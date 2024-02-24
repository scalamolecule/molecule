package molecule.sql.mysql.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{JdbcProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuite
import molecule.sql.core.facade.JdbcConn_JS
import scala.util.Random


trait TestSuite_mysql extends CoreTestSuite {

  override val platform = "js"
  override val database = "Mysql"

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val n              = Random.nextInt().abs
    val url            = s"jdbc:tc:mysql:8.1://localhost:3306/test$n?allowMultiQueries=true"
    val recreateSchema =
      s"""drop database if exists test$n;
         |create database test$n;
         |use test$n;
         |""".stripMargin

    val proxy = JdbcProxy(
      url,
      recreateSchema + schema.sqlSchema_mysql,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_mysql,
      useTestContainer = true
    )
    test(JdbcConn_JS(proxy, RpcRequest.request))
  }
}
