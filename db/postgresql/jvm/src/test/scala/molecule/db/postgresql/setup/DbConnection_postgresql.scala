package molecule.db.postgresql.setup

import molecule.db.common.api.MetaDb_postgresql
import molecule.db.common.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import molecule.db.common.marshalling.JdbcProxy
import molecule.db.common.spi.Conn
import molecule.db.compliance.setup.DbConnection
import org.postgresql.ds.PGSimpleDataSource
import org.testcontainers.containers.PostgreSQLContainer
import zio.{ZIO, ZLayer}

object DbConnection_postgresql extends DbConnection {

  private val baseUrl = "postgres:17"

  println(s"Starting $baseUrl docker container...")
  val container = new PostgreSQLContainer(baseUrl)
  container.start()
  println("Postgresql docker container started")

  private val dataSource = new PGSimpleDataSource()
  dataSource.setURL(container.getJdbcUrl)
  dataSource.setDatabaseName(container.getDatabaseName)
  dataSource.setUser(container.getUsername)
  dataSource.setPassword(container.getPassword)
  dataSource.setPreparedStatementCacheQueries(0)

  // Re-use connection for all tests in this test suite
  private val reusedSqlConn = dataSource.getConnection

  private val resetDb =
    s"""DROP SCHEMA IF EXISTS public CASCADE;
       |CREATE SCHEMA public;
       |""".stripMargin


  def getConnection(metaDb: MetaDb_postgresql): JdbcConn_JVM = {
    val proxy = JdbcProxy(baseUrl, metaDb, resetDb)

    // Not closing the connection since we re-use it
    JdbcHandler_JVM.recreateDb(proxy, reusedSqlConn)
  }

  def run(test: Conn ?=> Any, metaDb: MetaDb_postgresql): Any = {
    given Conn = getConnection(metaDb)
    test
  }


  def connZLayer(metaDb: MetaDb_postgresql): ZLayer[Any, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking {
        getConnection(metaDb)
      }
    )
  }
}
