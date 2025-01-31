package molecule.sql.h2.spi

import boopickle.Default._
import molecule.core.ast.DataModel.Element
import molecule.sql.core.spi.SpiBase_async
import molecule.sql.h2.query.Model2SqlQuery_h2
import scala.concurrent.{Future, ExecutionContext => EC}


object Spi_h2_async extends Spi_h2_async

trait Spi_h2_async extends SpiBase_async {

  override protected def printInspectQuery(label: String, elements: List[Element])
                                          (implicit ec: EC): Future[Unit] = Future {
    val query = new Model2SqlQuery_h2(elements).getSqlQuery(Nil, None, None, None)
    printRaw(label, Nil, query)
  }
}
