package molecule.sql.jdbc.spi

import molecule.boilerplate.ast.Model._
import molecule.core.spi.PrintInspect
import molecule.sql.core.query.Model2SqlQuery
import scala.concurrent.{ExecutionContext, Future}

trait JdbcSpiAsyncBase extends PrintInspect {

  protected def printInspectQuery(label: String, elements: List[Element])
                                 (implicit ec: ExecutionContext): Future[Unit] = Future {
//    val queries = new DatomicModel2Query(elements).getQueries(true)._3
    val queries = new Model2SqlQuery(elements).getSqlQuery(Nil) //._3
    printInspect(label, Nil, queries)
  }


}
