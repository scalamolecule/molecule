package molecule.db.sql.h2.spi

import boopickle.Default.*
import cats.effect.IO
import molecule.db.core.ast.Element
import molecule.db.sql.core.spi.SpiBase_io
import molecule.db.sql.h2.query.Model2SqlQuery_h2


object Spi_h2_io extends Spi_h2_io

trait Spi_h2_io extends SpiBase_io {

  override protected def printInspectQuery(label: String, elements: List[Element]): IO[Unit] = IO.blocking {
    val query = new Model2SqlQuery_h2(elements).getSqlQuery(Nil, None, None, None)
    printRaw(label, Nil, query)
  }
}
