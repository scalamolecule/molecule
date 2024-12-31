package molecule.sql.postgres.setup

import molecule.base.api.Schema_postgres
import molecule.core.marshalling.JdbcProxy_postgres
import molecule.core.spi.Conn
import molecule.coreTests.setup.DbConnection
import molecule.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import org.postgresql.ds.PGSimpleDataSource
import org.testcontainers.containers.PostgreSQLContainer

trait DbConnection_postgres extends DbConnection {

  override val platform = "jvm"

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
  // the same database and reset it before each test.
  // This is much faster than re-creating a new database for each test.
  private val reusedSqlConn = dataSource.getConnection

  private val resetDb =
    s"""DROP SCHEMA IF EXISTS public CASCADE;
       |CREATE SCHEMA public;
       |""".stripMargin


  def run(test: Conn => Any, schema: Schema_postgres): Any = {
    // For speed, re-use the same connection for all tests
    // by dropping the previous database before each test.

    val proxy = JdbcProxy_postgres(baseUrl, schema, resetDb + schema.schemaData.head)
    val conn = JdbcConn_JVM(proxy, reusedSqlConn)
    test(JdbcHandler_JVM.recreateDb(conn))
  }
}
