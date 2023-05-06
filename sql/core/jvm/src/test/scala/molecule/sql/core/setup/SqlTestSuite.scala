package molecule.sql.core.setup

import molecule.base.api.SchemaTransaction
import molecule.core.api.Connection
import molecule.core.marshalling.SqlProxy
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.schema._
import molecule.sql.core.facade.{SqlConn_JVM, SqlHandler}
import moleculeBuildInfo.BuildInfo
import scala.concurrent.Future
import scala.util.Random
import scala.util.control.NonFatal

trait SqlTestSuite extends SqlTestSuiteBase {

  lazy val isJsPlatform = false
  lazy val protocol     = BuildInfo.datomicProtocol
  lazy val useFree      = BuildInfo.datomicUseFree

  def inMem[T](test: Connection => T, schemaTx: SchemaTransaction): T = {
    val url               = s"jdbc:h2:mem:test_database_" + Random.nextInt()
    val proxy             = SqlProxy(url, schemaTx)
    var conn: SqlConn_JVM = SqlConn_JVM(proxy, null)
    try {
      Class.forName("org.h2.Driver")
      conn = SqlHandler.recreateDb(proxy, url)
      test(conn)
    } catch {
      case NonFatal(exc) => throw exc
    } finally {
      conn.sqlConn.close()
    }
  }

  def types[T](test: Connection => T): T = inMem(test, TypesSchema)
  def refs[T](test: Connection => T): T = inMem(test, RefsSchema)
  def unique[T](test: Connection => T): T = inMem(test, UniqueSchema)
  def validation[T](test: Connection => T): T = inMem(test, ValidationSchema)

  def delay[T](ms: Int)(body: => T): Future[T] = Future {
    Thread.sleep(ms)
    body
  }
}
