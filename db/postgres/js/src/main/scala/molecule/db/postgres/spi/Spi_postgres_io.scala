package molecule.db.postgres.spi

import cats.effect.IO
import molecule.core.dataModel.DataModel
import molecule.db.common.spi.SpiBase_io
import molecule.db.postgres.query.Model2SqlQuery_postgres


object Spi_postgres_io extends Spi_postgres_io

trait Spi_postgres_io extends SpiBase_io {

  override protected def renderInspectQuery(label: String, dataModel: DataModel): IO[String] = IO.blocking {
    val query = new Model2SqlQuery_postgres(dataModel.elements).getSqlQuery(Nil, None, None, None)
    renderInspection(label, dataModel, query)
  }
}
