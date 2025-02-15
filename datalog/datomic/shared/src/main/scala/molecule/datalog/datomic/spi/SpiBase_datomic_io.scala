package molecule.datalog.datomic.spi

import cats.effect.IO
import molecule.core.spi.Renderer
import molecule.datalog.core.query.Model2DatomicQuery
import molecule.core.ast.DataModel.Element

trait SpiBase_datomic_io extends Renderer {

  protected def printInspectQuery(
    label: String,
    elements: List[Element]
  ): IO[Unit] = IO {
    val queries = new Model2DatomicQuery(elements).getDatomicQueries(true)._3
    printRaw(label, Nil, queries)
  }
}
