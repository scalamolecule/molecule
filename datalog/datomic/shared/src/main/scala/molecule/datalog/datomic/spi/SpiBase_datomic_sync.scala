package molecule.datalog.datomic.spi

import molecule.boilerplate.ast.DataModel._
import molecule.core.spi.Renderer
import molecule.datalog.core.query.Model2DatomicQuery

trait SpiBase_datomic_sync extends Renderer {

  protected def printInspectQuery(label: String, elements: List[Element]): Unit = {
    val queries = new Model2DatomicQuery(elements).getDatomicQueries(false)._3
    printRaw(label, elements, queries)
  }
}
