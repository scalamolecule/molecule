package molecule.sql.jdbc.setup

import molecule.base.api.Schema
import molecule.core.api.Connection
import molecule.core.marshalling.SqlProxy
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.setup.CoreTestSuite
import molecule.sql.jdbc.api.JdbcApiAsync
import molecule.sql.jdbc.facade.{JdbcConn_JVM, JdbcHandler}
import scala.util.Random
import scala.util.control.NonFatal

trait JdbcTestSuite extends CoreTestSuite with JdbcApiAsync {

  override lazy val isJsPlatform = false
//  lazy val protocol     = BuildInfo.datomicProtocol
//  lazy val useFree      = BuildInfo.datomicUseFree

  override def inMem[T](test: Connection => T, schemaTx: Schema): T = {
    println("##########")

    val url               = s"jdbc:h2:mem:test_database_" + Random.nextInt()
    val proxy             = SqlProxy(url, schemaTx)
    var conn: JdbcConn_JVM = JdbcConn_JVM(proxy, null)
    try {
      Class.forName("org.h2.Driver")
      conn = JdbcHandler.recreateDb(proxy, url)
      test(conn)
    } catch {
      case NonFatal(exc) => throw exc
    } finally {
      conn.sqlConn.close()
    }
  }

  override def types[T](test: Connection => T): T = inMem(test, TypesSchema)
  override def refs[T](test: Connection => T): T = inMem(test, RefsSchema)
  override def unique[T](test: Connection => T): T = inMem(test, UniqueSchema)
  override def validation[T](test: Connection => T): T = inMem(test, ValidationSchema)
}
