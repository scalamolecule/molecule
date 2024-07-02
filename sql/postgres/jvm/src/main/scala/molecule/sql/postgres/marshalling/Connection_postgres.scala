package molecule.sql.postgres.marshalling

import java.sql.DriverManager
import com.dimafeng.testcontainers.PostgreSQLContainer
import molecule.base.api.Schema
import molecule.core.marshalling.JdbcProxy
import molecule.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}


object Connection_postgres {
  // JVM tests run sequentially so we can re-use
  // the same database and reset it before each test
  private val url = "jdbc:tc:postgresql:16://localhost:5432/test" +
    "?preparedStatementCacheQueries=0"

  private val container = new PostgreSQLContainer()
  Class.forName(container.driverClassName)
  private val sqlConn = DriverManager.getConnection(url)

  private val resetDb =
    s"""DROP SCHEMA IF EXISTS public CASCADE;
       |CREATE SCHEMA public;
       |""".stripMargin

  def getConnection(schema: Schema): JdbcConn_JVM = {
    val proxy = JdbcProxy(
      url,
      resetDb + schema.sqlSchema_postgres,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_postgres,
      useTestContainer = true
    )
    getConnection(proxy)
  }

  def getConnection(proxy: JdbcProxy): JdbcConn_JVM = {
    JdbcHandler_JVM.recreateDb(
      JdbcConn_JVM(proxy, sqlConn)
    )
  }
}
