package molecule.sql.postgres.spi

import boopickle.Default._
import molecule.boilerplate.ast.Model._
import molecule.sql.core.spi.SpiBase_async
import molecule.sql.postgres.query.Model2SqlQuery_postgres
import scala.concurrent.{Future, ExecutionContext => EC}


object Spi_postgres_async extends Spi_postgres_async

trait Spi_postgres_async extends SpiBase_async {

  override protected def printInspectQuery(label: String, elements: List[Element])
                                          (implicit ec: EC): Future[Unit] = Future {
    val query = new Model2SqlQuery_postgres(elements).getSqlQuery(Nil, None, None, None)
    printRaw(label, Nil, query)
  }
}
