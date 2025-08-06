package molecule.db.mysql.spi

import scala.concurrent.{Future, ExecutionContext as EC}
import boopickle.Default.*
import molecule.core.dataModel.DataModel
import molecule.db.common.spi.SpiBaseJS_async
import molecule.db.mysql.query.Model2SqlQuery_mysql


object Spi_mysql_async extends Spi_mysql_async

trait Spi_mysql_async extends SpiBaseJS_async {

  override protected def renderInspectQuery(label: String, dataModel: DataModel)
                                           (using ec: EC): Future[String] = Future {
    val query = new Model2SqlQuery_mysql(dataModel.elements).getSqlQuery(Nil, None, None, None)
    renderInspection(label, dataModel, query)
  }
}
