package molecule.db.postgresql.spi

import molecule.core.dataModel.DataModel
import molecule.db.common.spi.SpiBaseJS_async
import molecule.db.postgresql.query.Model2SqlQuery_postgresql
import scala.concurrent.{Future, ExecutionContext as EC}


object Spi_postgresql_async extends Spi_postgresql_async

trait Spi_postgresql_async extends SpiBaseJS_async {

  override protected def renderInspectQuery(label: String, dataModel: DataModel)
                                           (implicit ec: EC): Future[String] = Future {
    val query = new Model2SqlQuery_postgresql(dataModel.elements).getSqlQuery(Nil, None, None, None)
    renderInspection(label, dataModel, query)
  }
}
