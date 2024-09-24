package molecule.coreTests.setup

import cats.effect.IO
import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.dataModels.schema._
import munit.CatsEffectSuite


trait CoreTestSuiteBase_io extends CatsEffectSuite with CoreTest {

  def inMem[T](test: Conn => T, schema: Schema): T

  def graphql[T](schema: Schema)(test: Conn => T): T = inMem(test, schema)
  def types[T](test: Conn => T): T = inMem(test, TypesSchema)
  def refs[T](test: Conn => T): T = inMem(test, RefsSchema)
  def unique[T](test: Conn => T): T = inMem(test, UniquesSchema)
  def validation[T](test: Conn => T): T = inMem(test, ValidationSchema)
  def partition[T](test: Conn => T): T = inMem(test, PartitionsSchema)

  def delay[T](ms: Int)(body: => T): IO[T] = ???
}
