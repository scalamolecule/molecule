package molecule.datalog.datomic.spi

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.spi.{Conn, PrintInspect}
import molecule.datalog.core.query.Model2DatomicQuery
import zio.{Task, ZIO}

trait DatomicSpiZioBase extends PrintInspect {

  protected def printInspectQuery(
    label: String,
    elements: List[Element]
  ): ZIO[Conn, MoleculeError, Unit] = ZIO.succeed {
    val queries = new Model2DatomicQuery(elements).getDatomicQueries(true)._3
    printInspect(label, Nil, queries)
  }
}
