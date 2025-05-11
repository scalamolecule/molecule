package molecule.graphql.client.spi

import boopickle.Default._
import molecule.db.core.ast._
import scala.concurrent.{Future, ExecutionContext => EC}


object SpiAsync_graphql extends SpiAsync_graphql

trait SpiAsync_graphql extends SpiAsyncBase {

  override protected def printInspectQuery(label: String, elements: List[Element])
                                          (implicit ec: EC): Future[Unit] = Future {
//    val query = new Model2SqlQuery_postgres(elements).getSqlQuery(Nil, None, None, None)
//    printRaw(label, Nil, query)
    ???
  }
}
