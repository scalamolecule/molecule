package molecule.sql.mariadb.spi

import boopickle.Default._
import cats.effect.IO
import molecule.core.ast.DataModel.Element
import molecule.sql.core.spi.SpiBase_io
import molecule.sql.mariadb.query.Model2SqlQuery_mariadb


object Spi_mariadb_io extends Spi_mariadb_io

trait Spi_mariadb_io extends SpiBase_io {

  override protected def printInspectQuery(label: String, elements: List[Element]): IO[Unit] = IO.blocking {
    val query = new Model2SqlQuery_mariadb(elements).getSqlQuery(Nil, None, None, None)
    printRaw(label, Nil, query)
  }
}
