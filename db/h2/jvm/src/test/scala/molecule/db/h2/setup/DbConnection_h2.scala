package molecule.db.h2.setup

import java.sql.DriverManager
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import molecule.db.common.api.MetaDb_h2
import molecule.db.common.facade.JdbcHandler_JVM
import molecule.db.common.marshalling.JdbcProxy
import molecule.db.common.spi.Conn
import molecule.db.compliance.setup.DbConnection
import org.h2.jdbcx.JdbcDataSource
import zio.{ZIO, ZLayer}
import scala.util.Random
import scala.util.Using.Manager


trait DbConnection_h2 extends DbConnection {

  def run(test: Conn ?=> Any, metaDb: MetaDb_h2): Any = {

    // Using unclosed, gc'ed connection to avoid fs2 Stream
    // to materialize in test assertion after db has been closed.
    runMemDbUnclosed(test, metaDb)

    // Choose running tests with one of various setups
    //    runMemDb(test, schema)
    //    runFileDb(test, schema)
    //    runFileDbWithHikariCP(test, schema)
  }

  // Use in-memory H2 (fastest)
  private def runMemDbUnclosed(test: Conn ?=> Any, metaDb: MetaDb_h2): Any = {
    val url   = "jdbc:h2:mem:test" + Random.nextInt().abs
    val proxy = JdbcProxy(url, metaDb)
    Class.forName("org.h2.Driver")
    val sqlConn = DriverManager.getConnection(proxy.url)
    given Conn = JdbcHandler_JVM.recreateDb(proxy, sqlConn)
    test
  }

  private def runMemDb(test: Conn ?=> Any, metaDb: MetaDb_h2): Any = {
    val url = "jdbc:h2:mem:test" + Random.nextInt().abs
    Manager { use =>
      val proxy   = JdbcProxy(url, metaDb)
      val sqlConn = use(DriverManager.getConnection(proxy.url))
      given Conn = use(JdbcHandler_JVM.recreateDb(proxy, sqlConn))
      test
    }.get
  }


  // Use H2 saving to disk (slower but can persist)
  private def runFileDb(test: Conn ?=> Any, metaDb: MetaDb_h2): Any = {
    val ds             = new JdbcDataSource()
    val tempDbFilePath = tempDbPath
    ds.setUrl("jdbc:h2:" + tempDbFilePath)
    Manager { use =>
      val proxy   = JdbcProxy(ds.getUrl, metaDb)
      val sqlConn = use(ds.getConnection)
      given Conn = use(JdbcHandler_JVM.recreateDb(proxy, sqlConn))
      test
      tempDbFilePath.toFile.delete()
    }.get
  }


  val config = new HikariConfig()
  config.setMaximumPoolSize(10)
  config.setConnectionTimeout(3000)

  // Use Hikari connection pool
  private def runFileDbWithHikariCP(test: Conn ?=> Any, metaDb: MetaDb_h2): Any = {
    val tempDbFilePath = tempDbPath
    config.setJdbcUrl("jdbc:h2:" + tempDbFilePath)
    Manager { use =>
      val ds      = use(new HikariDataSource(config))
      val proxy   = JdbcProxy(ds.getJdbcUrl, metaDb)
      val sqlConn = use(ds.getConnection)
      given Conn = use(JdbcHandler_JVM.recreateDb(proxy, sqlConn))
      test
      tempDbFilePath.toFile.delete()
    }.get
  }


  def connZLayer(metaDb: MetaDb_h2): ZLayer[Any, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking {
        val url     = "jdbc:h2:mem:test" + Random.nextInt().abs
        val proxy   = JdbcProxy(url, metaDb)
        val sqlConn = DriverManager.getConnection(proxy.url)
        JdbcHandler_JVM.recreateDb(proxy, sqlConn)
      }
    )
  }
}
