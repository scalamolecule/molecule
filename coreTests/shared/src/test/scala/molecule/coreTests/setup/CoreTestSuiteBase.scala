package molecule.coreTests.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.core.util.JavaConversions
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.util.{AggrUtils, TestData}
import org.scalactic.TripleEquals
import utest.framework.Formatter
import utest.{TestSuite, ufansi}
import scala.concurrent.Future


trait CoreTestSuiteBase
  extends TestSuite
    with TestData
    with JavaConversions
    with TripleEquals
    with AggrUtils {

  val isJsPlatform: Boolean
  val platform    : String

  def inMem[T](test: Conn => T, schemaTx: Schema): T

  def types[T](test: Conn => T): T = inMem(test, TypesSchema)
  def refs[T](test: Conn => T): T = inMem(test, RefsSchema)
  def unique[T](test: Conn => T): T = inMem(test, UniqueSchema)
  def validation[T](test: Conn => T): T = inMem(test, ValidationSchema)

  def delay[T](ms: Int)(body: => T): Future[T]

  override def utestFormatter: Formatter = new Formatter {
    override def formatIcon(success: Boolean): ufansi.Str = {
      formatResultColor(success)(
        (if (success) "+ " else "X ") + platform
      )
    }
  }
}
