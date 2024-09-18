package molecule.coreTests.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.dataModels.schema._
import zio.test.ZIOSpecDefault
import zio.{Task, ZLayer}


trait CoreTestZioSpecBase extends ZIOSpecDefault with CoreTest {

  def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn]

  def types[T]: ZLayer[T, Throwable, Conn] = inMem(TypesSchema)
  def refs[T]: ZLayer[T, Throwable, Conn] = inMem(RefsSchema)
  def unique[T]: ZLayer[T, Throwable, Conn] = inMem(UniquesSchema)
  def validation[T]: ZLayer[T, Throwable, Conn] = inMem(ValidationSchema)

  def delay[T](ms: Int)(body: => T): Task[T]
}
