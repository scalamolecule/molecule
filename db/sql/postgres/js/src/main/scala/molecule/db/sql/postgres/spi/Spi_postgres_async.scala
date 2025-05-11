package molecule.db.sql.postgres.spi

import boopickle.Default.*
import molecule.db.core.ast.Element
import molecule.db.sql.core.spi.SpiBaseJS_async
import molecule.db.sql.postgres.query.Model2SqlQuery_postgres
import scala.concurrent.{Future, ExecutionContext as EC}


object Spi_postgres_async extends Spi_postgres_async

trait Spi_postgres_async extends SpiBaseJS_async {

  override protected def printInspectQuery(label: String, elements: List[Element])
                                          (implicit ec: EC): Future[Unit] = Future {
    val query = new Model2SqlQuery_postgres(elements).getSqlQuery(Nil, None, None, None)
    printRaw(label, Nil, query)
  }
}
