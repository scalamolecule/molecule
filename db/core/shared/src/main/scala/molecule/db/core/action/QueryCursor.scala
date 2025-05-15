package molecule.db.core.action

import molecule.db.core.ast.DataModel
import molecule.db.core.marshalling.dbView.DbView

case class QueryCursor[Tpl](
  private[molecule] val dataModel: DataModel,
  private[molecule] val optLimit: Option[Int],
  private[molecule] val cursor: String,
  private[molecule] val dbView: Option[DbView] = None,
  private[molecule] val doInspect: Boolean = false
) extends Action {

  def limit(l: Int): QueryCursor[Tpl] = copy(optLimit = Some(l))

  // Inspect Query
  def i: QueryCursor[Tpl] = copy(doInspect = true)
}
