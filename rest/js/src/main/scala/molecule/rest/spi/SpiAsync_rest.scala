package molecule.rest.spi

import boopickle.Default._
import molecule.db.core.ast._
import scala.concurrent.{Future, ExecutionContext => EC}


object SpiAsync_rest extends SpiAsync_rest

trait SpiAsync_rest extends SpiAsyncBase {

  override protected def renderInspectQuery(label: String, dataModel: DataModel)
                                           (implicit ec: EC): Future[String] = Future {
//    val query = new Model2SqlQuery_postgres(elements).getSqlQuery(Nil, None, None, None)
//    printRaw(label, Nil, query)
    ???
  }
}
