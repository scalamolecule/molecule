package molecule.sql.mysql.marshalling

import java.sql.DriverManager
import com.mysql.cj.jdbc.MysqlDataSource
import molecule.base.api.Schema
import molecule.core.marshalling.JdbcProxy
import molecule.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import org.testcontainers.containers.MySQLContainer


object Connection_mysql {

  private val baseUrl = s"mysql:8.0.33"

  private val mysql = {
    println(s"Starting $baseUrl ...")
    val pg = new MySQLContainer(baseUrl)
    pg.start()
    println("Mysql started")
    pg
  }

  private val dataSource = new MysqlDataSource()
  dataSource.setURL(mysql.getJdbcUrl)
  dataSource.setDatabaseName(mysql.getDatabaseName)
  dataSource.setUser(mysql.getUsername)
  dataSource.setPassword(mysql.getPassword)
  dataSource.setAllowMultiQueries(true)
  dataSource.setAutoReconnect(true)

  // JVM tests run sequentially so we can re-use
  // the same database and reset it before each test
  private val reusedSqlConn = dataSource.getConnection

  private val resetDb =
    s"""DROP DATABASE IF EXISTS test;
       |CREATE DATABASE test;
       |USE test;
       |""".stripMargin


  def getConnection(schema: Schema): JdbcConn_JVM = {
    val proxy = JdbcProxy(
      baseUrl,
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
      JdbcConn_JVM(proxy, reusedSqlConn)
    )
  }

  def getNewConnection(proxy: JdbcProxy): JdbcConn_JVM = {
    JdbcHandler_JVM.recreateDb(
      JdbcConn_JVM(proxy, DriverManager.getConnection(proxy.url))
    )
  }
}
