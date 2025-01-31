package molecule.sql.mysql.spi

import boopickle.Default._
import cats.effect.IO
import molecule.sql.core.spi.SpiBase_io
import molecule.sql.mysql.query.Model2SqlQuery_mysql
import molecule.core.ast.DataModel.Element


object Spi_mysql_io extends Spi_mysql_io

trait Spi_mysql_io extends SpiBase_io {

  override protected def printInspectQuery(label: String, elements: List[Element]): IO[Unit] = IO {
    val query = new Model2SqlQuery_mysql(elements).getSqlQuery(Nil, None, None, None)
    printRaw(label, Nil, query)
  }
}
