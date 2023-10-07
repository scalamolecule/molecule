package molecule.coreTests.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.util.TestData
import zio.test.ZIOSpecDefault
import zio.{Task, ZLayer}


trait CoreTestZioSpecBase extends ZIOSpecDefault with TestData {

  val isJsPlatform: Boolean
  val database    : String
  val platform    : String

  def inMem[T](schemaTx: Schema): ZLayer[T, Throwable, Conn]

  def types[T]: ZLayer[T, Throwable, Conn] = inMem(TypesSchema)
  def refs[T]: ZLayer[T, Throwable, Conn] = inMem(RefsSchema)
  def unique[T]: ZLayer[T, Throwable, Conn] = inMem(UniquesSchema)
  def validation[T]: ZLayer[T, Throwable, Conn] = inMem(ValidationSchema)

  def delay[T](ms: Int)(body: => T): Task[T]
}
