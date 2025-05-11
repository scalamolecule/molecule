package molecule.db.sql.mariadb.spi

import boopickle.Default.*
import cats.effect.IO
import molecule.db.core.ast.Element
import molecule.db.sql.core.spi.SpiBase_io
import molecule.db.sql.mariadb.query.Model2SqlQuery_mariadb


object Spi_mariadb_io extends Spi_mariadb_io

trait Spi_mariadb_io extends SpiBase_io {

  override protected def printInspectQuery(label: String, elements: List[Element]): IO[Unit] = IO.blocking {
    val query = new Model2SqlQuery_mariadb(elements).getSqlQuery(Nil, None, None, None)
    printRaw(label, Nil, query)
  }
}
