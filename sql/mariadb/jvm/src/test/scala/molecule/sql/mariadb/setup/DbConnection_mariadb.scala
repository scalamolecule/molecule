package molecule.sql.mariadb.setup

import java.sql.DriverManager
import com.dimafeng.testcontainers.MariaDBContainer
import molecule.base.api.Schema_mariadb
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
import molecule.sql.core.facade.JdbcHandler_JVM

object DbConnection_mariadb {

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

  def run(test: Conn => Any, schema: Schema_mariadb): Any = {
    val initSql = resetDb + schema.schemaData.head
    val proxy   = JdbcProxy(url, schema, initSql)

    // Not closing the connection since we re-use it
    val conn = JdbcHandler_JVM.recreateDb(proxy, reusedSqlConn)
    test(conn)
  }
}
