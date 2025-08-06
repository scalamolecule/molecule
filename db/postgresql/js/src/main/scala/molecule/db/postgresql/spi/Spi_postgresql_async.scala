package molecule.db.postgresql.spi

import scala.concurrent.{Future, ExecutionContext as EC}
import molecule.core.dataModel.DataModel
import molecule.db.common.spi.SpiBaseJS_async
import molecule.db.postgresql.query.Model2SqlQuery_postgresql


object Spi_postgresql_async extends Spi_postgresql_async

trait Spi_postgresql_async extends SpiBaseJS_async {

  override protected def renderInspectQuery(label: String, dataModel: DataModel)
                                           (using ec: EC): Future[String] = Future {
    val query = new Model2SqlQuery_postgresql(dataModel.elements).getSqlQuery(Nil, None, None, None)
    renderInspection(label, dataModel, query)
  }
}
