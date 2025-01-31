package molecule.sql.sqlite.spi

import boopickle.Default._
import molecule.core.ast.DataModel.Element
import molecule.sql.core.spi.SpiBase_async
import molecule.sql.sqlite.query.Model2SqlQuery_sqlite
import scala.concurrent.{Future, ExecutionContext => EC}


object Spi_sqlite_async extends Spi_sqlite_async

trait Spi_sqlite_async extends SpiBase_async {

  override protected def printInspectQuery(label: String, elements: List[Element])
                                          (implicit ec: EC): Future[Unit] = Future {
    val query = new Model2SqlQuery_sqlite(elements).getSqlQuery(Nil, None, None, None)
    printRaw(label, Nil, query)
  }
}
