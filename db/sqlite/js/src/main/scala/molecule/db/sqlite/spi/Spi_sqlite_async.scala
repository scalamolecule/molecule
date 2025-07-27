package molecule.db.sqlite.spi

import boopickle.Default.*
import molecule.core.dataModel.DataModel
import molecule.db.common.spi.SpiBaseJS_async
import molecule.db.sqlite.query.Model2SqlQuery_sqlite
import scala.concurrent.{Future, ExecutionContext as EC}


object Spi_sqlite_async extends Spi_sqlite_async

trait Spi_sqlite_async extends SpiBaseJS_async {

  override protected def renderInspectQuery(label: String, dataModel: DataModel)
                                           (using ec: EC): Future[String] = Future {
    val query = new Model2SqlQuery_sqlite(dataModel.elements).getSqlQuery(Nil, None, None, None)
    renderInspection(label, dataModel, query)
  }
}
