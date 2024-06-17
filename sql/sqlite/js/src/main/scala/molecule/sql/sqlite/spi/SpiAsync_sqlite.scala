package molecule.sql.sqlite.spi

import boopickle.Default._
import molecule.boilerplate.ast.Model._
import molecule.sql.core.spi.SpiAsyncBase
import molecule.sql.sqlite.query.Model2SqlQuery_sqlite
import scala.concurrent.{Future, ExecutionContext => EC}


object SpiAsync_sqlite extends SpiAsync_sqlite

trait SpiAsync_sqlite extends SpiAsyncBase {

  override protected def printInspectQuery(label: String, elements: List[Element])
                                          (implicit ec: EC): Future[Unit] = Future {
    val query = new Model2SqlQuery_sqlite(elements).getSqlQuery(Nil, None, None, None)
    printRaw(label, Nil, query)
  }
}
