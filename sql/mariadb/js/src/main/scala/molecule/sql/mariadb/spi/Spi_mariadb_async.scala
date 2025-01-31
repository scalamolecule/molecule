package molecule.sql.mariadb.spi

import boopickle.Default._
import molecule.sql.core.spi.SpiBase_async
import molecule.sql.mariadb.query.Model2SqlQuery_mariadb
import scala.concurrent.{Future, ExecutionContext => EC}
import molecule.core.ast.DataModel.Element


object Spi_mariadb_async extends Spi_mariadb_async

trait Spi_mariadb_async extends SpiBase_async {

  override protected def printInspectQuery(label: String, elements: List[Element])
                                          (implicit ec: EC): Future[Unit] = Future {
    val query = new Model2SqlQuery_mariadb(elements).getSqlQuery(Nil, None, None, None)
    printRaw(label, Nil, query)
  }
}
