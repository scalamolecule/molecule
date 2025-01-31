package molecule.rpc.grpc.spi

import boopickle.Default._
import molecule.core.ast.DataModel._
import scala.concurrent.{Future, ExecutionContext => EC}


object SpiAsync_grpc extends SpiAsync_grpc

trait SpiAsync_grpc extends SpiAsyncBase {

  override protected def printInspectQuery(label: String, elements: List[Element])
                                          (implicit ec: EC): Future[Unit] = Future {
    //    val query = new Model2SqlQuery_postgres(elements).getSqlQuery(Nil, None, None, None)
    //    printRaw(label, Nil, query)
    ???
  }
}
