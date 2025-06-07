package molecule.db.sql.mariadb.spi

import boopickle.Default.*
import cats.effect.IO
import molecule.db.core.ast.{DataModel, Element}
import molecule.db.sql.core.spi.SpiBase_io
import molecule.db.sql.mariadb.query.Model2SqlQuery_mariadb


object Spi_mariadb_io extends Spi_mariadb_io

trait Spi_mariadb_io extends SpiBase_io {

  override protected def renderInspectQuery(label: String, dataModel: DataModel): IO[String] = IO.blocking {
    val query = new Model2SqlQuery_mariadb(dataModel.elements).getSqlQuery(Nil, None, None, None)
    renderInspection(label, dataModel, query)
  }
}
