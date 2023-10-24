package molecule.document.mongodb.spi

import boopickle.Default._
import molecule.boilerplate.ast.Model._
import molecule.document.mongodb.spi.SpiAsyncBase
import molecule.document.mongodb.query.Model2SqlQuery_mongodb
import scala.concurrent.{Future, ExecutionContext => EC}


object SpiAsync_mongodb extends SpiAsync_mongodb

trait SpiAsync_mongodb extends SpiAsyncBase {

  override protected def printInspectQuery(label: String, elements: List[Element])
                                          (implicit ec: EC): Future[Unit] = Future {
    val query = new Model2SqlQuery_mongodb(elements).getSqlQuery(Nil, None, None, None)
    printRaw(label, Nil, query)
  }
}
