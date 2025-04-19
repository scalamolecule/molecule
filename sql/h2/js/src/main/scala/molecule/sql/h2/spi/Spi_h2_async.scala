package molecule.sql.h2.spi

import boopickle.Default.*
import molecule.core.ast.DataModel.Element
import molecule.sql.core.spi.SpiBaseJS_async
import molecule.sql.h2.query.Model2SqlQuery_h2
import scala.concurrent.{Future, ExecutionContext as EC}


object Spi_h2_async extends Spi_h2_async

trait Spi_h2_async extends SpiBaseJS_async {

  override protected def printInspectQuery(label: String, elements: List[Element])
                                          (implicit ec: EC): Future[Unit] = Future {
    val query = new Model2SqlQuery_h2(elements).getSqlQuery(Nil, None, None, None)
    printRaw(label, Nil, query)
  }
}
