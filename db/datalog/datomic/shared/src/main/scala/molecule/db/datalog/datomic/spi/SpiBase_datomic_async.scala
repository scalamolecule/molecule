package molecule.db.datalog.datomic.spi

import molecule.core.ast.DataModel
import molecule.db.core.spi.Renderer
import molecule.db.datalog.core.query.Model2DatomicQuery
import scala.concurrent.{ExecutionContext, Future}

trait SpiBase_datomic_async extends Renderer {

  protected def renderInspectQuery(
    label: String,
    dataModel: DataModel
  )(implicit ec: ExecutionContext): Future[String] = Future {
    val queries = new Model2DatomicQuery(dataModel).getDatomicQueries(false)._3
    renderInspection(label, dataModel, queries)
  }
}
