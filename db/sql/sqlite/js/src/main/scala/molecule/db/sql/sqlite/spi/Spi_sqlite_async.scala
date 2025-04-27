package molecule.db.sql.sqlite.spi

import boopickle.Default.*
import molecule.core.ast.DataModel.Element
import molecule.db.sql.core.spi.SpiBaseJS_async
import molecule.db.sql.sqlite.query.Model2SqlQuery_sqlite
import scala.concurrent.{Future, ExecutionContext as EC}


object Spi_sqlite_async extends Spi_sqlite_async

trait Spi_sqlite_async extends SpiBaseJS_async {

  override protected def printInspectQuery(label: String, elements: List[Element])
                                          (implicit ec: EC): Future[Unit] = Future {
    val query = new Model2SqlQuery_sqlite(elements).getSqlQuery(Nil, None, None, None)
    printRaw(label, Nil, query)
  }
}
