package molecule.db.h2.spi

import boopickle.Default.*
import cats.effect.IO
import molecule.core.dataModel.DataModel
import molecule.db.common.spi.SpiBase_io
import molecule.db.h2.query.Model2SqlQuery_h2


object Spi_h2_io extends Spi_h2_io

trait Spi_h2_io extends SpiBase_io {

  override protected def renderInspectQuery(label: String, dataModel: DataModel): IO[String] = IO.blocking {
    val query = new Model2SqlQuery_h2(dataModel.elements).getSqlQuery(Nil, None, None, None)
    renderInspection(label, dataModel, query)
  }
}
