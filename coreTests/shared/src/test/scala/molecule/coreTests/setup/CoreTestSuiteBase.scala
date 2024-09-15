package molecule.coreTests.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.core.util.{AggrUtils, JavaConversions}
import molecule.coreTests.dataModels.schema._
import utest._
import utest.framework.Formatter
import scala.concurrent.Future


trait CoreTestSuiteBase
  extends TestSuite
    with CoreTest
    with JavaConversions
    with TolerantEquality
    with AggrUtils {

  def inMem[T](test: Conn => T, schemaTx: Schema): T

  def graphql[T](schema: Schema)(test: Conn => T): T = inMem(test, schema)
  def types[T](test: Conn => T): T = inMem(test, TypesSchema)
  def refs[T](test: Conn => T): T = inMem(test, RefsSchema)
  def unique[T](test: Conn => T): T = inMem(test, UniquesSchema)
  def validation[T](test: Conn => T): T = inMem(test, ValidationSchema)
  def partition[T](test: Conn => T): T = inMem(test, PartitionsSchema)

  def delay[T](ms: Int)(body: => T): Future[T] = ???

  override def utestFormatter: Formatter = new Formatter {
    override def formatIcon(success: Boolean): ufansi.Str = {
      formatResultColor(success)(
        (if (success) "+ " else "X ") + platform + " " + database
      )
    }
  }
}
