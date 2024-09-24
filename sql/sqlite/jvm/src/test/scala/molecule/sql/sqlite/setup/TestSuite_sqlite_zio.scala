package molecule.sql.sqlite.setup

import java.sql.DriverManager
import java.util.regex.Pattern
import molecule.base.api.Schema
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuite_zio
import molecule.sql.sqlite.facade.JdbcConnSQlite_JVM
import org.sqlite.Function
import zio.{ZIO, ZLayer}
import scala.concurrent.blocking


trait TestSuite_sqlite_zio extends CoreTestSuite_zio {

  override val platform = "jvm"
  override val database = "SQlite"

  val url = "jdbc:sqlite::memory:"

  def recreateDb(proxy: JdbcProxy): JdbcConnSQlite_JVM = blocking {
    val sqlConn = DriverManager.getConnection(url)
    val conn    = new JdbcConnSQlite_JVM(proxy, sqlConn)

    // Add regexp function to sqlite
    Function.create(conn.sqlConn, "REGEXP", new Function() {
      override def xFunc(): Unit = {
        val expression = value_text(0)
        var value      = value_text(1)
        if (value == null)
          value = ""

        val pattern = Pattern.compile(expression)
        result(if (pattern.matcher(value).find()) 1 else 0)
      }
    })

    val stmt = conn.sqlConn.createStatement
    stmt.executeUpdate(proxy.createSchema)
    stmt.close()
    conn
  }

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    val proxy = JdbcProxy(
      url,
      schema.sqlSchema_sqlite,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_sqlite
    )

    ZLayer.scoped(
      ZIO.attemptBlocking(
        recreateDb(proxy)
      )
    )
  }
}
