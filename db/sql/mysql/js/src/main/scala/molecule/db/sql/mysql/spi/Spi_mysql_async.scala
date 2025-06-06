package molecule.db.sql.mysql.spi

import boopickle.Default.*
import molecule.db.core.ast.Element
import molecule.db.sql.core.spi.SpiBaseJS_async
import molecule.db.sql.mysql.query.Model2SqlQuery_mysql
import scala.concurrent.{Future, ExecutionContext as EC}


object Spi_mysql_async extends Spi_mysql_async

trait Spi_mysql_async extends SpiBaseJS_async {

  override protected def printInspectQuery(label: String, elements: List[Element])
                                          (implicit ec: EC): Future[Unit] = Future {
    val query = new Model2SqlQuery_mysql(elements).getSqlQuery(Nil, None, None, None)
    printRaw(label, Nil, query)
  }
}
