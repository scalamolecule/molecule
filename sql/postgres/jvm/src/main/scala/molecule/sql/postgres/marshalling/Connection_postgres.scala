package molecule.sql.postgres.marshalling

import java.sql.DriverManager
import molecule.base.api.Schema
import molecule.core.marshalling.JdbcProxy
import molecule.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import org.postgresql.ds.PGSimpleDataSource
import org.testcontainers.containers.PostgreSQLContainer


object Connection_postgres {

  private val baseUrl = "postgres:16"

  private val postgres = {
    println(s"Starting $baseUrl")
    val pg = new PostgreSQLContainer(baseUrl)
    pg.start()
    println("Postgres started")
    pg
  }

  private val dataSource = new PGSimpleDataSource()
  dataSource.setURL(postgres.getJdbcUrl)
  dataSource.setDatabaseName(postgres.getDatabaseName)
  dataSource.setUser(postgres.getUsername)
  dataSource.setPassword(postgres.getPassword)
  dataSource.setPreparedStatementCacheQueries(0)

  // JVM tests run sequentially so we can re-use
  // the same database and reset it before each test
  private val reusedSqlConn = dataSource.getConnection

  private val resetDb =
    s"""DROP SCHEMA IF EXISTS public CASCADE;
       |CREATE SCHEMA public;
       |""".stripMargin


  def getConnection(schema: Schema): JdbcConn_JVM = {
    val proxy = JdbcProxy(
      baseUrl,
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
      JdbcConn_JVM(proxy, reusedSqlConn)
    )
  }

  def getNewConnection(proxy: JdbcProxy): JdbcConn_JVM = {
    JdbcHandler_JVM.recreateDb(
      JdbcConn_JVM(proxy, DriverManager.getConnection(proxy.url))
    )
  }
}
