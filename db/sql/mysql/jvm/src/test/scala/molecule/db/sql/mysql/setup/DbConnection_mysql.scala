package molecule.db.sql.mysql.setup

import com.mysql.cj.jdbc.MysqlDataSource
import molecule.db.compliance.setup.DbConnection
import molecule.db.core.api.MetaDb_mysql
import molecule.db.core.marshalling.JdbcProxy
import molecule.db.core.spi.Conn
import molecule.db.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import org.testcontainers.containers.MySQLContainer
import zio.{ZIO, ZLayer}

object DbConnection_mysql extends DbConnection{

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
//    val initSql = resetDb + metaDb.schemaData.head
    val initSql = resetDb + getFileContent(metaDb.schemaResourcePath)
    val proxy   = JdbcProxy(baseUrl, metaDb, initSql)

    // Not closing the connection since we re-use it
    JdbcHandler_JVM.recreateDb(proxy, reusedSqlConn)
  }

  def run(test: Conn => Any, metaDb: MetaDb_mysql): Any = {
    test(getConnection(metaDb))
  }


  def connZLayer(metaDb: MetaDb_mysql): ZLayer[Any, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking {
        getConnection(metaDb)
      }
    )
  }
}
