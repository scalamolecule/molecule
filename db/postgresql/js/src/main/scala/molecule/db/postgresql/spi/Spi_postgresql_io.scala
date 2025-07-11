package molecule.db.postgresql.spi

import cats.effect.IO
import molecule.core.dataModel.DataModel
import molecule.db.common.spi.SpiBase_io
import molecule.db.postgresql.query.Model2SqlQuery_postgresql


object Spi_postgresql_io extends Spi_postgresql_io

trait Spi_postgresql_io extends SpiBase_io {

  override protected def renderInspectQuery(label: String, dataModel: DataModel): IO[String] = IO.blocking {
    val query = new Model2SqlQuery_postgresql(dataModel.elements).getSqlQuery(Nil, None, None, None)
    renderInspection(label, dataModel, query)
  }
}
