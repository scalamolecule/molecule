package molecule.sql.h2.setup

import java.nio.file.Files
import molecule.base.api.Schema_h2
import molecule.core.marshalling.JdbcProxy_h2
import molecule.core.spi.Conn
import molecule.coreTests.setup.DbConnection
import molecule.sql.core.facade.JdbcConn_JVM
import org.h2.jdbcx.JdbcDataSource
import scala.concurrent.Future
import scala.util.Using.Manager


trait DbConnection_h2 extends DbConnection {

  override val platform = "jvm"

  def run(test: Conn => Any, schema: Schema_h2): Any = {
    val ds = new JdbcDataSource()
    ds.setURL("jdbc:h2:" + Files.createTempDirectory(null).toAbsolutePath)
    Manager { use =>
      val sqlConn = use(ds.getConnection)
      val stmt    = use(sqlConn.createStatement)
      stmt.execute(schema.schemaData.head) // transact Person schema

      val proxy = JdbcProxy_h2(ds.getURL, schema)
      val conn  = JdbcConn_JVM(proxy, sqlConn)
      test(conn)
    }.get
  }


  import molecule.core.util.Executor._

  def delay[T](ms: Int)(body: => T): Future[T] = Future {
    Thread.sleep(ms)
    body
  }
}
