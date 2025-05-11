package molecule.db.datalog.datomic.spi

import cats.effect.IO
import molecule.db.core.ast.Element
import molecule.db.core.spi.Renderer
import molecule.db.core.util.IOUtils
import molecule.db.datalog.core.query.Model2DatomicQuery

trait SpiBase_datomic_io extends IOUtils with Renderer {

  protected def printInspectQuery(
    label: String,
    elements: List[Element]
  ): IO[Unit] = IO.blocking {
    val queries = new Model2DatomicQuery(elements).getDatomicQueries(true)._3
    printRaw(label, Nil, queries)
  }
}
