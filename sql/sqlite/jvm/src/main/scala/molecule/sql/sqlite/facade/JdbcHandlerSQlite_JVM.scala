package molecule.sql.sqlite.facade

import java.sql.DriverManager
import java.util.regex.Pattern
import molecule.core.marshalling.JdbcProxy
import org.sqlite.Function
import scala.concurrent.blocking


object JdbcHandlerSQlite_JVM {

  // For in-memory dbs
  def recreateDb(proxy: JdbcProxy): JdbcConnSQlite_JVM = blocking {
    val sqlConn = DriverManager.getConnection(proxy.url)

    // Add regexp function to sqlite
    Function.create(sqlConn, "REGEXP", new Function() {
      override def xFunc(): Unit = {
        val expression = value_text(0)
        var value      = value_text(1)
        if (value == null)
          value = ""

        val pattern = Pattern.compile(expression)
        result(if (pattern.matcher(value).find()) 1 else 0)
      }
    })

    val conn = new JdbcConnSQlite_JVM(proxy, sqlConn)
    val stmt = conn.sqlConn.createStatement
    stmt.executeUpdate(proxy.createSchema)
    stmt.close()
    conn
  }
}