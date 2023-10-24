package molecule.document.mongodb.spi

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.spi.{Conn, Renderer, SpiZio}
import molecule.document.mongodb.query.Model2SqlQuery_mongodb
import zio.{Task, ZIO}

trait SpiZioBase_mongodb extends Renderer { spi: SpiZio =>

  protected def printInspectQuery(
    label: String,
    elements: List[Element]
  ): ZIO[Conn, MoleculeError, Unit] = ZIO.succeed {
    val query = new Model2SqlQuery_mongodb(elements).getSqlQuery(Nil, None, None, None)
    printRaw(label, Nil, query)
  }
}
