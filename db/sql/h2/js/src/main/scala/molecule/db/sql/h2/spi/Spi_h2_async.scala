package molecule.db.sql.h2.spi

import boopickle.Default.*
import molecule.core.ast.DataModel
import molecule.db.sql.core.spi.SpiBaseJS_async
import molecule.db.sql.h2.query.Model2SqlQuery_h2
import scala.concurrent.{Future, ExecutionContext as EC}


object Spi_h2_async extends Spi_h2_async

trait Spi_h2_async extends SpiBaseJS_async {

  override protected def renderInspectQuery(label: String, dataModel: DataModel)
                                           (implicit ec: EC): Future[String] = Future {
    val query = new Model2SqlQuery_h2(dataModel.elements).getSqlQuery(Nil, None, None, None)
    renderInspection(label, dataModel, query)
  }
}
