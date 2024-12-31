package molecule.sql.mysql.setup

import com.mysql.cj.jdbc.MysqlDataSource
import molecule.base.api.Schema_mysql
import molecule.core.marshalling.{JdbcProxy, JdbcProxy_mysql}
import molecule.core.spi.Conn
import molecule.coreTests.setup.DbConnection
import molecule.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import org.testcontainers.containers.MySQLContainer
import scala.concurrent.Future

trait DbConnection_mysql extends DbConnection {

  override val platform = "jvm"

  private val baseUrl = "mysql:8.0.33"

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


  def getConnection(schema: Schema_mysql): JdbcConn_JVM = {
    val proxy = JdbcProxy_mysql(
      baseUrl, schema, resetDb + schema.schemaData.head
    )
    getConnection(proxy)
  }

  def getConnection(proxy: JdbcProxy): JdbcConn_JVM = {
    JdbcHandler_JVM.recreateDb(
      JdbcConn_JVM(proxy, reusedSqlConn)
    )
  }


  def run(test: Conn => Any, schema: Schema_mysql): Any = {
    test(getConnection(schema))
  }


  import molecule.core.util.Executor._

  def delay[T](ms: Int)(body: => T): Future[T] = Future {
    Thread.sleep(ms)
    body
  }
}
