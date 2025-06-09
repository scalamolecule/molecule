package molecule.db.datalog.datomic.spi

import molecule.base.error.MoleculeError
import molecule.core.ast.DataModel
import molecule.db.core.spi.{Conn, Renderer}
import molecule.db.datalog.core.query.Model2DatomicQuery
import zio.ZIO

trait SpiBase_datomic_zio extends Renderer {

  protected def renderInspectQuery(
    label: String,
    dataModel: DataModel
  ): ZIO[Conn, MoleculeError, String] = ZIO.succeed {
    val queries = new Model2DatomicQuery(dataModel).getDatomicQueries(true)._3
    renderInspection(label, dataModel, queries)
  }
}
