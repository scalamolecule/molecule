package molecule.sql.postgres.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{JdbcProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuite
import molecule.sql.core.facade.JdbcConn_JS
import scala.util.Random


trait TestSuite_postgres extends CoreTestSuite {

  override val database = "Postgres"
  override val platform = "js"

  val recreateSchema =
    s"""DROP SCHEMA IF EXISTS public CASCADE;
       |CREATE SCHEMA public;
       |""".stripMargin

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val n   = Random.nextInt().abs
    val url = s"jdbc:tc:postgresql:15://localhost:5432/test$n?preparedStatementCacheQueries=0"

    // Using the same db causes contention between tests since tests are run in parallel with rpc
    val url1 = s"jdbc:tc:postgresql:15://localhost:5432/test?preparedStatementCacheQueries=0"

    val proxy = JdbcProxy(
      url,
      recreateSchema + schema.sqlSchema_postgres,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_postgres,
      useTestContainer = true
    )
    test(JdbcConn_JS(proxy, RpcRequest.request))
  }
}
