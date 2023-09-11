package molecule.datalog.datomic.spi

import molecule.boilerplate.ast.Model._
import molecule.core.spi.PrintInspect
import molecule.datalog.core.query.Model2DatomicQuery
import scala.concurrent.{ExecutionContext, Future}

trait DatomicSpiAsyncBase extends PrintInspect {

  protected def printInspectQuery(label: String, elements: List[Element])
                                 (implicit ec: ExecutionContext): Future[Unit] = Future {
    val queries = new Model2DatomicQuery(elements).getDatomicQueries(false)._3
    printInspect(label, Nil, queries)
  }
}
