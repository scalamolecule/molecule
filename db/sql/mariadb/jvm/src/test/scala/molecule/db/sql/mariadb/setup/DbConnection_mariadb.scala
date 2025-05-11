package molecule.db.sql.mariadb.setup

import java.sql.DriverManager
import com.dimafeng.testcontainers.MariaDBContainer
import molecule.db.base.api.{Schema, Schema_mariadb}
import molecule.db.compliance.setup.DbConnection
import molecule.db.core.marshalling.JdbcProxy
import molecule.db.core.spi.Conn
import molecule.db.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import molecule.db.sql.mariadb
import zio.{ZIO, ZLayer}

//object DbConnection_mariadb extends DbConnection {
object DbConnection_mariadb  {

  private val url = s"jdbc:tc:mariadb:latest:///test" +
    s"?allowMultiQueries=true" +
    s"&autoReconnect=true" +
    s"&user=root" +
    s"&password="

  // Using dimafeng container for MariaDB to be able to config through url
  println(s"Starting mariadb:latest docker container...")
  private val container = MariaDBContainer()
  Class.forName(container.driverClassName)
  println("MariaDB docker container started")

  // Re-use connection for all tests in this test suite
  private val reusedSqlConn = DriverManager.getConnection(url)

  private val resetDb =
    s"""DROP DATABASE IF EXISTS test;
       |CREATE DATABASE test;
       |USE test;
       |""".stripMargin

  def getConnection(schema:Schema_mariadb): JdbcConn_JVM = {
    val initSql = resetDb + schema.schemaData.head
    val proxy   = JdbcProxy(url, schema, initSql)

    // Not closing the connection since we re-use it
    JdbcHandler_JVM.recreateDb(proxy, reusedSqlConn)
  }

  def run(test: Conn => Any, schema: Schema_mariadb): Any = {
    test(getConnection(schema))
  }


  def connZLayer(schema: Schema_mariadb): ZLayer[Any, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking {
        getConnection(schema)
      }
    )
  }
}
