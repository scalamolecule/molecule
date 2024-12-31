package molecule.sql.sqlite.facade

import java.sql.{Connection, DriverManager}
import java.util.regex.Pattern
import molecule.core.marshalling.JdbcProxy
import org.sqlite.Function
import scala.util.Using.Manager

/** SQlite connection factory
 *
 * Generates custom regex function as default
 * */
object JdbcHandlerSQlite_JVM {

  def recreateDb(
    proxy: JdbcProxy,
    sqlConn: Connection,
    addRegexFn: Boolean = true
  ): JdbcConnSQlite_JVM = {
    if (addRegexFn) {
      // Add custom regexp function to sqlite
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
    }
    Manager { use =>
      val conn = new JdbcConnSQlite_JVM(proxy, sqlConn)
      val stmt = use(conn.sqlConn.createStatement)
//      val stmt = conn.sqlConn.createStatement
      stmt.executeUpdate(proxy.schema.schemaData.head)
      conn
    }.get
  }
}