package molecule.db.datalog.datomic.spi

import molecule.db.core.ast.Element
import molecule.db.core.spi.Renderer
import molecule.db.datalog.core.query.Model2DatomicQuery
import scala.concurrent.{ExecutionContext, Future}

trait SpiBase_datomic_async extends Renderer {

  protected def printInspectQuery(label: String, elements: List[Element])
                                 (implicit ec: ExecutionContext): Future[Unit] = Future {
    val queries = new Model2DatomicQuery(elements).getDatomicQueries(false)._3
    printRaw(label, Nil, queries)
  }
}
