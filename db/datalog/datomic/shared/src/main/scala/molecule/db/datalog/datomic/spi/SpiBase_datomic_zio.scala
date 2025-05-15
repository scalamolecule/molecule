package molecule.db.datalog.datomic.spi

import molecule.db.base.error.MoleculeError
import molecule.db.core.ast.DataModel
import molecule.db.core.spi.{Conn, Renderer}
import molecule.db.datalog.core.query.Model2DatomicQuery
import zio.ZIO

trait SpiBase_datomic_zio extends Renderer {

  protected def printInspectQuery(
    label: String,
    dataModel: DataModel
  ): ZIO[Conn, MoleculeError, Unit] = ZIO.succeed {
    val queries = new Model2DatomicQuery(dataModel).getDatomicQueries(true)._3
    printRaw(label, Nil, queries)
  }
}
