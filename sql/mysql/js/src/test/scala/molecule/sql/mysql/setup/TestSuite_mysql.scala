package molecule.sql.mysql.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{JdbcProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuite
import molecule.sql.core.facade.JdbcConn_JS
import scala.util.Random


trait TestSuite_mysql extends CoreTestSuite {

  override val platform = "Mysql js"

  val recreateSchema =
    s"""DROP SCHEMA IF EXISTS public CASCADE;
       |CREATE SCHEMA public;
       |""".stripMargin

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val n   = Random.nextInt()
    val url = s"jdbc:tc:mysql:8.1://localhost:3306/test$n?allowMultiQueries=true"

    // Using the same db causes contention between tests since tests are run in parallel with rpc
    //    val url = "jdbc:tc:mysql:8.1://localhost:3306/test?allowMultiQueries=true"

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
