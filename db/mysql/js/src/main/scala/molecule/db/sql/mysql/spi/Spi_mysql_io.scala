package molecule.db.sql.mysql.spi

import boopickle.Default.*
import cats.effect.IO
import molecule.core.dataModel.DataModel
import molecule.db.common.spi.SpiBase_io
import molecule.db.sql.mysql.query.Model2SqlQuery_mysql


object Spi_mysql_io extends Spi_mysql_io

trait Spi_mysql_io extends SpiBase_io {

  override protected def renderInspectQuery(label: String, dataModel: DataModel): IO[String] = IO.blocking {
    val query = new Model2SqlQuery_mysql(dataModel.elements).getSqlQuery(Nil, None, None, None)
    renderInspection(label, dataModel, query)
  }
}
