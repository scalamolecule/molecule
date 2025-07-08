package molecule.db.postgres.spi

import molecule.core.dataModel.DataModel
import molecule.db.common.spi.SpiBaseJS_async
import molecule.db.postgres.query.Model2SqlQuery_postgres
import scala.concurrent.{Future, ExecutionContext as EC}


object Spi_postgres_async extends Spi_postgres_async

trait Spi_postgres_async extends SpiBaseJS_async {

  override protected def renderInspectQuery(label: String, dataModel: DataModel)
                                           (implicit ec: EC): Future[String] = Future {
    val query = new Model2SqlQuery_postgres(dataModel.elements).getSqlQuery(Nil, None, None, None)
    renderInspection(label, dataModel, query)
  }
}
