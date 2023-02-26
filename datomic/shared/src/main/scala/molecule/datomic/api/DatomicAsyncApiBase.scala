package molecule.datomic.api

import molecule.boilerplate.ast.Model._
import molecule.core.api.PrintInspect
import molecule.datomic.query.DatomicModel2Query
import scala.concurrent.{ExecutionContext, Future}

trait DatomicAsyncApiBase extends PrintInspect {

  protected def printInspectQuery(label: String, elements: List[Element])
                                 (implicit ec: ExecutionContext): Future[Unit] = {
    val d2q = new DatomicModel2Query(elements)
    // Process model to query
    d2q.processQueries(true)
    Future(
      printInspect(label, Nil, d2q.renderQueries(elements)._3)
    )
  }
}
