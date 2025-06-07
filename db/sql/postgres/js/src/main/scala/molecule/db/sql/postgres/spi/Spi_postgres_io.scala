package molecule.db.sql.postgres.spi

import boopickle.Default.*
import cats.effect.IO
import molecule.db.core.ast.{DataModel, Element}
import molecule.db.sql.core.spi.SpiBase_io
import molecule.db.sql.postgres.query.Model2SqlQuery_postgres


object Spi_postgres_io extends Spi_postgres_io

trait Spi_postgres_io extends SpiBase_io {

  override protected def renderInspectQuery(label: String, dataModel: DataModel): IO[String] = IO.blocking {
    val query = new Model2SqlQuery_postgres(dataModel.elements).getSqlQuery(Nil, None, None, None)
    renderInspection(label, dataModel, query)
  }
}
