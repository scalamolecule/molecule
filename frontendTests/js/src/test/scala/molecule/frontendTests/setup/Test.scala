package molecule.frontendTests.setup

import molecule.base.api.Schema_h2
import molecule.core.marshalling.{JdbcProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.frontendTests.domains.schema.TypesSchema_h2
import molecule.sql.core.facade.JdbcConn_JS
import munit.FunSuite
import scala.util.Random

trait Test extends FunSuite {

  implicit class TestableString(s: String) {
    def -(x: => Any): Unit = test(s)(x)
  }

  implicit class ArrowAssert(lhs: Any) {
    def ==>[V](rhs: V): Unit = assertEquals(lhs, rhs)
  }

  def types(test: Conn => Any): Any = run(test, TypesSchema_h2)

  def run(test: Conn => Any, schema: Schema_h2): Any = {
    val url   = s"jdbc:h2:mem:test" + Random.nextInt().abs + ";DB_CLOSE_DELAY=-1"
    val proxy = JdbcProxy(url, schema)
    val conn  = JdbcConn_JS(proxy, RpcRequest.request)
    test(conn)
  }
}
