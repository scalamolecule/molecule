package molecule.sql.mysql.spi

import boopickle.Default._
import molecule.boilerplate.ast.DataModel._
import molecule.sql.core.spi.SpiBase_async
import molecule.sql.mysql.query.Model2SqlQuery_mysql
import scala.concurrent.{Future, ExecutionContext => EC}


object Spi_mysql_async extends Spi_mysql_async

trait Spi_mysql_async extends SpiBase_async {

  override protected def printInspectQuery(label: String, elements: List[Element])
                                          (implicit ec: EC): Future[Unit] = Future {
    val query = new Model2SqlQuery_mysql(elements).getSqlQuery(Nil, None, None, None)
    printRaw(label, Nil, query)
  }
}
