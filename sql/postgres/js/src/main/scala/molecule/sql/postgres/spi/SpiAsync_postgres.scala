package molecule.sql.postgres.spi

import boopickle.Default._
import molecule.boilerplate.ast.Model._
import molecule.sql.core.spi.SpiAsyncBase
import molecule.sql.postgres.query.Model2SqlQuery_postgres
import scala.concurrent.{Future, ExecutionContext => EC}


object SpiAsync_postgres extends SpiAsync_postgres

trait SpiAsync_postgres extends SpiAsyncBase {

  override protected def printInspectQuery(label: String, elements: List[Element])
                                          (implicit ec: EC): Future[Unit] = Future {
    val query = new Model2SqlQuery_postgres(elements).getSqlQuery(Nil, None, None, None)
    printInspect(label, Nil, query)
  }
}
