package molecule.db.datalog.datomic.spi

import molecule.base.error.*
import molecule.core.ast.DataModel.Element
import molecule.core.spi.{Conn, Renderer}
import molecule.db.datalog.core.query.Model2DatomicQuery
import zio.ZIO

trait SpiBase_datomic_zio extends Renderer {

  protected def printInspectQuery(
    label: String,
    elements: List[Element]
  ): ZIO[Conn, MoleculeError, Unit] = ZIO.succeed {
    val queries = new Model2DatomicQuery(elements).getDatomicQueries(true)._3
    printRaw(label, Nil, queries)
  }
}
