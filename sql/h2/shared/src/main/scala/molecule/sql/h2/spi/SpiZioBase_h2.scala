package molecule.sql.h2.spi

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.spi.{Conn, PrintInspect, SpiZio}
import molecule.sql.h2.query.Model2SqlQuery_h2
import zio.{Task, ZIO}

trait SpiZioBase_h2 extends PrintInspect { spi: SpiZio =>

  protected def printInspectQuery(
    label: String,
    elements: List[Element]
  ): ZIO[Conn, MoleculeError, Unit] = ZIO.succeed {
    val query = new Model2SqlQuery_h2(elements).getSqlQuery(Nil, None, None, None)
    printInspect(label, Nil, query)
  }
}
