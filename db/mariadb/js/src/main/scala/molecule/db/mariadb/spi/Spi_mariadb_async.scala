package molecule.db.mariadb.spi

import boopickle.Default.*
import molecule.core.dataModel.DataModel
import molecule.db.common.spi.SpiBaseJS_async
import molecule.db.mariadb.query.Model2SqlQuery_mariadb
import scala.concurrent.{Future, ExecutionContext as EC}


object Spi_mariadb_async extends Spi_mariadb_async

trait Spi_mariadb_async extends SpiBaseJS_async {

  override protected def renderInspectQuery(label: String, dataModel: DataModel)
                                           (implicit ec: EC): Future[String] = Future {
    val query = new Model2SqlQuery_mariadb(dataModel.elements).getSqlQuery(Nil, None, None, None)
    renderInspection(label, dataModel, query)
  }
}
