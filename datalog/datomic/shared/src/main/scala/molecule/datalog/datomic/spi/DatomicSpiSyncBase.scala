package molecule.datalog.datomic.spi

import molecule.boilerplate.ast.Model._
import molecule.core.spi.Renderer
import molecule.datalog.core.query.Model2DatomicQuery

trait DatomicSpiSyncBase extends Renderer {

  protected def printInspectQuery(label: String, elements: List[Element]): Unit = {
    val queries = new Model2DatomicQuery(elements).getDatomicQueries(false)._3
    printRaw(label, elements, queries)
  }
}
