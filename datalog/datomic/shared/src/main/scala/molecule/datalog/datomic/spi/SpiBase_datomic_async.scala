package molecule.datalog.datomic.spi

import molecule.core.spi.Renderer
import molecule.datalog.core.query.Model2DatomicQuery
import scala.concurrent.{ExecutionContext, Future}
import molecule.core.ast.DataModel.Element

trait SpiBase_datomic_async extends Renderer {

  protected def printInspectQuery(label: String, elements: List[Element])
                                 (implicit ec: ExecutionContext): Future[Unit] = Future {
    val queries = new Model2DatomicQuery(elements).getDatomicQueries(false)._3
    printRaw(label, Nil, queries)
  }
}
