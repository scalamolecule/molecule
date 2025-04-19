package molecule.sql.mysql.spi

import boopickle.Default.*
import cats.effect.IO
import molecule.core.ast.DataModel.Element
import molecule.sql.core.spi.SpiBase_io
import molecule.sql.mysql.query.Model2SqlQuery_mysql


object Spi_mysql_io extends Spi_mysql_io

trait Spi_mysql_io extends SpiBase_io {

  override protected def printInspectQuery(label: String, elements: List[Element]): IO[Unit] = IO.blocking {
    val query = new Model2SqlQuery_mysql(elements).getSqlQuery(Nil, None, None, None)
    printRaw(label, Nil, query)
  }
}
