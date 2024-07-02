package molecule.sql.mysql.marshalling

import java.sql.DriverManager
import com.dimafeng.testcontainers.MySQLContainer
import molecule.base.api.Schema
import molecule.core.marshalling.JdbcProxy
import molecule.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}


object Connection_mysql {
  private val url = s"jdbc:tc:mysql:8.1://localhost:3306/test?" +
    s"allowMultiQueries=true&autoReconnect=true&user=root&password="

  private val container = MySQLContainer()
  Class.forName(container.driverClassName)
  private val sqlConn = DriverManager.getConnection(url)

  private val resetDb =
    s"""DROP DATABASE IF EXISTS test;
       |CREATE DATABASE test;
       |USE test;
       |""".stripMargin

  def getConnection(schema: Schema): JdbcConn_JVM = {
    val proxy = JdbcProxy(
      url,
      resetDb + schema.sqlSchema_mysql,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_mysql,
      useTestContainer = true
    )
    getConnection(proxy)
  }

  def getConnection(proxy: JdbcProxy): JdbcConn_JVM = {
    JdbcHandler_JVM.recreateDb(
      JdbcConn_JVM(proxy, sqlConn)
    )
  }
}
