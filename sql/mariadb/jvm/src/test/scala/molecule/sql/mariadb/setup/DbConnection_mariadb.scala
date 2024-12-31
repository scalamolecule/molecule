package molecule.sql.mariadb.setup

import java.sql.DriverManager
import com.dimafeng.testcontainers.MariaDBContainer
import molecule.base.api.Schema_mariadb
import molecule.core.marshalling.JdbcProxy_mariadb
import molecule.core.spi.Conn
import molecule.coreTests.setup.DbConnection
import molecule.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}

trait DbConnection_mariadb extends DbConnection {

  override val platform = "jvm"


  private val url = s"jdbc:tc:mariadb:latest:///test" +
    s"?allowMultiQueries=true" +
    s"&autoReconnect=true" +
    s"&user=root" +
    s"&password="

  // Using dimafeng container for MariaDB
  // com.dimafeng.testcontainers.MariaDBContainer

  println(s"Starting $url")
  private val container = MariaDBContainer()
  Class.forName(container.driverClassName)
  private val reusedSqlConn = DriverManager.getConnection(url)
  println("MariaDB started")

  private val resetDb =
    s"""DROP DATABASE IF EXISTS test;
       |CREATE DATABASE test;
       |USE test;
       |""".stripMargin

  def run(test: Conn => Any, schema: Schema_mariadb): Any = {
    val proxy = JdbcProxy_mariadb(url, schema, resetDb + schema.schemaData.head)
    val conn  = JdbcConn_JVM(proxy, reusedSqlConn)
    test(JdbcHandler_JVM.recreateDb(conn))
  }
}
