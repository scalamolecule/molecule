package molecule.db.sql.sqlite.spi

import boopickle.Default.*
import cats.effect.IO
import molecule.core.dataModel.DataModel
import molecule.db.sql.core.spi.SpiBase_io
import molecule.db.sql.sqlite.query.Model2SqlQuery_sqlite


object Spi_sqlite_io extends Spi_sqlite_io

trait Spi_sqlite_io extends SpiBase_io {

  override protected def renderInspectQuery(label: String, dataModel: DataModel): IO[String] = IO.blocking {
    val query = new Model2SqlQuery_sqlite(dataModel.elements).getSqlQuery(Nil, None, None, None)
    renderInspection(label, dataModel, query)
  }
}
