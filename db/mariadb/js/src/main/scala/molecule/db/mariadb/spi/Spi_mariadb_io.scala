package molecule.db.mariadb.spi

import boopickle.Default.*
import cats.effect.IO
import molecule.core.dataModel.DataModel
import molecule.db.common.spi.SpiBase_io
import molecule.db.mariadb.query.Model2SqlQuery_mariadb


object Spi_mariadb_io extends Spi_mariadb_io

trait Spi_mariadb_io extends SpiBase_io {

  override protected def renderInspectQuery(label: String, dataModel: DataModel): IO[String] = IO.blocking {
    val query = new Model2SqlQuery_mariadb(dataModel.elements).getSqlQuery(Nil, None, None, None)
    renderInspection(label, dataModel, query)
  }
}
