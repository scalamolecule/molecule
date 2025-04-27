package molecule.db.sql.mariadb.spi

import boopickle.Default.*
import molecule.core.ast.DataModel.Element
import molecule.db.sql.core.spi.SpiBaseJS_async
import molecule.db.sql.mariadb.query.Model2SqlQuery_mariadb
import scala.concurrent.{Future, ExecutionContext as EC}


object Spi_mariadb_async extends Spi_mariadb_async

trait Spi_mariadb_async extends SpiBaseJS_async {

  override protected def printInspectQuery(label: String, elements: List[Element])
                                          (implicit ec: EC): Future[Unit] = Future {
    val query = new Model2SqlQuery_mariadb(elements).getSqlQuery(Nil, None, None, None)
    printRaw(label, Nil, query)
  }
}
