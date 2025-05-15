package molecule.db.datalog.datomic.spi

import molecule.db.core.ast.DataModel
import molecule.db.core.spi.Renderer
import molecule.db.datalog.core.query.Model2DatomicQuery

trait SpiBase_datomic_sync extends Renderer {

  protected def printInspectQuery(label: String, dataModel: DataModel): Unit = {
    val queries = new Model2DatomicQuery(dataModel).getDatomicQueries(false)._3
    printRaw(label, dataModel.elements, queries)
  }
}
