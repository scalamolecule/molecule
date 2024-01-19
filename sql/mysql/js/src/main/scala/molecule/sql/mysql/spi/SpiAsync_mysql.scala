package molecule.sql.mysql.spi

import boopickle.Default._
import molecule.boilerplate.ast.Model._
import molecule.sql.core.spi.SpiAsyncBase
import molecule.sql.mysql.query.Model2SqlQuery_mysql
import scala.concurrent.{Future, ExecutionContext => EC}


object SpiAsync_mysql extends SpiAsync_mysql

trait SpiAsync_mysql extends SpiAsyncBase {

  override protected def printInspectQuery(label: String, elements: List[Element])
                                          (implicit ec: EC): Future[Unit] = Future {
    val query = new Model2SqlQuery_mysql(elements).getSqlQuery(Nil, None, None)
    printRaw(label, Nil, query)
  }
}
