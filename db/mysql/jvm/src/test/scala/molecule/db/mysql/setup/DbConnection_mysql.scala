package molecule.db.mysql.setup

import com.mysql.cj.jdbc.MysqlDataSource
import molecule.db.common.api.MetaDb_mysql
import molecule.db.common.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import molecule.db.common.marshalling.JdbcProxy
import molecule.db.common.spi.Conn
import molecule.db.compliance.setup.DbConnection
import org.testcontainers.containers.MySQLContainer
import zio.{ZIO, ZLayer}

object DbConnection_mysql extends DbConnection {

  private val baseUrl = "mysql:9.0.0"

  println(s"Starting $baseUrl docker container...")
  val container = new MySQLContainer(baseUrl)
  container.start()
  println("Mysql docker container started")

  private val dataSource = new MysqlDataSource()
  dataSource.setURL(container.getJdbcUrl)
  dataSource.setDatabaseName(container.getDatabaseName)
  dataSource.setUser(container.getUsername)
  dataSource.setPassword(container.getPassword)
  dataSource.setAllowMultiQueries(true)
  dataSource.setAutoReconnect(true)

  // Re-use connection for all tests in this test suite
  private val reusedSqlConn = dataSource.getConnection

  private val resetDb =
    s"""DROP DATABASE IF EXISTS test;
       |CREATE DATABASE test;
       |USE test;
       |""".stripMargin

  def getConnection(metaDb: MetaDb_mysql): JdbcConn_JVM = {
    val proxy = JdbcProxy(baseUrl, metaDb, resetDb)

    // Not closing the connection since we re-use it
    JdbcHandler_JVM.recreateDb(proxy, reusedSqlConn)
  }

  def run(test: Conn ?=> Any, metaDb: MetaDb_mysql): Any = {
    given Conn = getConnection(metaDb)
    test
  }


  def connZLayer(metaDb: MetaDb_mysql): ZLayer[Any, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking {
        getConnection(metaDb)
      }
    )
  }
}
