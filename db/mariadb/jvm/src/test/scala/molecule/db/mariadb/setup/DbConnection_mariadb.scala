package molecule.db.mariadb.setup

import java.sql.DriverManager
import com.dimafeng.testcontainers.MariaDBContainer
import molecule.db.common.api.MetaDb_mariadb
import molecule.db.common.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import molecule.db.common.marshalling.JdbcProxy
import molecule.db.common.spi.Conn
import molecule.db.common.util.SchemaLoader
import molecule.db.compliance.setup.DbConnection
import zio.{ZIO, ZLayer}

object DbConnection_mariadb extends SchemaLoader with DbConnection {
  //object DbConnection_mariadb {

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

  def getConnection(metaDb: MetaDb_mariadb): JdbcConn_JVM = {
    val proxy = JdbcProxy(url, metaDb, resetDb)

    // Not closing the connection since we re-use it
    JdbcHandler_JVM.recreateDb(proxy, reusedSqlConn)
  }

  def run(test: Conn ?=> Any, metaDb: MetaDb_mariadb): Any = {
    given Conn = getConnection(metaDb)
    test
  }


  def connZLayer(metaDb: MetaDb_mariadb): ZLayer[Any, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking {
        getConnection(metaDb)
      }
    )
  }
}
