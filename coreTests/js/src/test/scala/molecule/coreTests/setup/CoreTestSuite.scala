package molecule.coreTests.setup

import molecule.base.api.Schema
import molecule.core.api.{ApiAsync2, Connection}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.schema._
import scala.concurrent.Future

trait CoreTestSuite extends CoreTestSuiteBase with ApiAsync2 {

  override lazy val isJsPlatform = true
  //  lazy val protocol     = BuildInfo.datomicProtocol
  //  lazy val useFree      = BuildInfo.datomicUseFree

//  abstract override val tests = ???

  def inMem[T](test: Connection => T, schemaTx: Schema): T = ???

  def types[T](test: Connection => T): T = inMem(test, TypesSchema)
  def refs[T](test: Connection => T): T = inMem(test, RefsSchema)
  def unique[T](test: Connection => T): T = inMem(test, UniqueSchema)
  def validation[T](test: Connection => T): T = inMem(test, ValidationSchema)

  def delay[T](ms: Int)(body: => T): Future[T] = Future {
    Thread.sleep(ms)
    body
  }
}
