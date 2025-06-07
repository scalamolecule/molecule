package molecule.rpc.grpc.spi

import boopickle.Default._
import molecule.db.core.ast._
import scala.concurrent.{Future, ExecutionContext => EC}


object SpiAsync_grpc extends SpiAsync_grpc

trait SpiAsync_grpc extends SpiAsyncBase {

  override protected def renderInspectQuery(label: String, dataModel: DataModel)
                                           (implicit ec: EC): Future[Unit] = Future {
    //    val query = new Model2SqlQuery_postgres(elements).getSqlQuery(Nil, None, None, None)
    //    printRaw(label, Nil, query)
    ???
  }
}
