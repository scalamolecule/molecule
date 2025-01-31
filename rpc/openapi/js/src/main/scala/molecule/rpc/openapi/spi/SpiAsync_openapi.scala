package molecule.rpc.openapi.spi

import boopickle.Default._
import molecule.core.ast.DataModel._
import scala.concurrent.{Future, ExecutionContext => EC}


object SpiAsync_openapi extends SpiAsync_openapi

trait SpiAsync_openapi extends SpiAsyncBase {

  override protected def printInspectQuery(label: String, elements: List[Element])
                                          (implicit ec: EC): Future[Unit] = Future {
    //    val query = new Model2SqlQuery_postgres(elements).getSqlQuery(Nil, None, None, None)
    //    printRaw(label, Nil, query)
    ???
  }
}
