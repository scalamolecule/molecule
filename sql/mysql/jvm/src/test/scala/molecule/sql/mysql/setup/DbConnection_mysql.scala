package molecule.sql.mysql.setup

import com.mysql.cj.jdbc.MysqlDataSource
import molecule.base.api.{Schema, Schema_mysql}
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
import molecule.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import org.testcontainers.containers.MySQLContainer
import zio.{ZIO, ZLayer}

object DbConnection_mysql {

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

  def getConnection(schema:Schema_mysql): JdbcConn_JVM = {
    val initSql = resetDb + schema.schemaData.head
    val proxy   = JdbcProxy(baseUrl, schema, initSql)

    // Not closing the connection since we re-use it
    JdbcHandler_JVM.recreateDb(proxy, reusedSqlConn)
  }

  def run(test: Conn => Any, schema: Schema_mysql): Any = {
    test(getConnection(schema))
  }


  def connZLayer(schema: Schema_mysql): ZLayer[Any, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking {
        getConnection(schema)
      }
    )
  }
}
