package molecule.sql.h2.spi

import boopickle.Default._
import molecule.boilerplate.ast.Model._
import molecule.sql.core.spi.SpiAsyncBase
import molecule.sql.h2.query.Model2SqlQuery_h2
import scala.concurrent.{Future, ExecutionContext => EC}


object SpiAsync_h2 extends SpiAsync_h2

trait SpiAsync_h2 extends SpiAsyncBase {

  override protected def printInspectQuery(label: String, elements: List[Element])
                                          (implicit ec: EC): Future[Unit] = Future {
    val query = new Model2SqlQuery_h2(elements).getSqlQuery(Nil, None, None, None)
    printRaw(label, Nil, query)
  }
}
