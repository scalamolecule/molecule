package molecule.sql.mariadb.marshalling

import java.sql.DriverManager
import com.dimafeng.testcontainers.MariaDBContainer
import molecule.base.api.Schema
import molecule.core.marshalling.JdbcProxy
import molecule.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}


object Connection_mariadb {

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


  def getConnection(schema: Schema): JdbcConn_JVM = {
    val proxy = JdbcProxy(
      url,
      resetDb + schema.sqlSchema_mariadb,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_mariadb,
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
