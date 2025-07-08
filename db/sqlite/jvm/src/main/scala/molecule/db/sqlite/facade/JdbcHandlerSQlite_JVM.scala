package molecule.db.sqlite.facade

import java.sql.Connection
import java.util.regex.Pattern
import molecule.db.common.marshalling.JdbcProxy
import molecule.db.common.util.SchemaLoader
import org.sqlite.Function
import scala.util.Using.Manager

/** SQlite connection factory
 *
 * Can generate custom regex function (for in-mem)
 * */
object JdbcHandlerSQlite_JVM extends SchemaLoader{

  def recreateDb(
    proxy: JdbcProxy,
    sqlConn: Connection,
    addRegexFn: Boolean = false
  ): JdbcConnSQlite_JVM = {
    if (addRegexFn) {
      // Add custom regexp function to sqlite (only necessary for in-mem)
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
      val schema = getSchema(proxy.metaDb.schemaResourcePath)
      stmt.executeUpdate(schema)
      conn
    }.get
  }
}