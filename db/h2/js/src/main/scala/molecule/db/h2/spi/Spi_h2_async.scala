package molecule.db.h2.spi

import boopickle.Default.*
import molecule.core.dataModel.DataModel
import molecule.db.common.spi.SpiBaseJS_async
import molecule.db.h2.query.Model2SqlQuery_h2
import scala.concurrent.{Future, ExecutionContext as EC}


object Spi_h2_async extends Spi_h2_async

trait Spi_h2_async extends SpiBaseJS_async {

  override protected def renderInspectQuery(label: String, dataModel: DataModel)
                                           (using ec: EC): Future[String] = Future {
    val query = new Model2SqlQuery_h2(dataModel.elements).getSqlQuery(Nil, None, None, None)
    renderInspection(label, dataModel, query)
  }
}
