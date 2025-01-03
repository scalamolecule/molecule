package molecule.sql.postgres.spi

import boopickle.Default._
import cats.effect.IO
import molecule.boilerplate.ast.DataModel._
import molecule.sql.core.spi.SpiBase_io
import molecule.sql.postgres.query.Model2SqlQuery_postgres


object Spi_postgres_io extends Spi_postgres_io

trait Spi_postgres_io extends SpiBase_io {

  override protected def printInspectQuery(label: String, elements: List[Element]): IO[Unit] = IO {
    val query = new Model2SqlQuery_postgres(elements).getSqlQuery(Nil, None, None, None)
    printRaw(label, Nil, query)
  }
}
