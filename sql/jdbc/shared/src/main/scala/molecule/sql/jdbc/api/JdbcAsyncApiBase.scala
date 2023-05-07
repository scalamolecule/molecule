package molecule.sql.jdbc.api

import molecule.boilerplate.ast.Model._
import molecule.core.api.PrintInspect
import molecule.sql.core.query.SqlModel2Query
import scala.concurrent.{ExecutionContext, Future}

trait JdbcAsyncApiBase extends PrintInspect {

  protected def printInspectQuery(label: String, elements: List[Element])
                                 (implicit ec: ExecutionContext): Future[Unit] = Future {
//    val queries = new DatomicModel2Query(elements).getQueries(true)._3
    val queries = new SqlModel2Query(elements).getQueries(false)._3
    printInspect(label, Nil, queries)
  }
}