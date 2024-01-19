package molecule.sql.mariadb.spi

import boopickle.Default._
import molecule.boilerplate.ast.Model._
import molecule.sql.core.spi.SpiAsyncBase
import molecule.sql.mariadb.query.Model2SqlQuery_mariadb
import scala.concurrent.{Future, ExecutionContext => EC}


object SpiAsync_mariadb extends SpiAsync_mariadb

trait SpiAsync_mariadb extends SpiAsyncBase {

  override protected def printInspectQuery(label: String, elements: List[Element])
                                          (implicit ec: EC): Future[Unit] = Future {
    val query = new Model2SqlQuery_mariadb(elements).getSqlQuery(Nil, None, None)
    printRaw(label, Nil, query)
  }
}
