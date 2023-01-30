package molecule.db.datomic.api.action

import molecule.boilerplate.ast.Model._
import molecule.core.api.Connection
import molecule.core.api.action.QueryApi
import molecule.db.datomic.facade.DatomicConn_JS
import scala.concurrent.{ExecutionContext, Future}

case class DatomicQueryApiImpl[Tpl](
  elements: List[Element],
  private val limit: Int = 0
) extends QueryApi[Tpl] {

  override def limit(l: Int): DatomicQueryApiImpl[Tpl] = copy(limit = l)
  override def offset(o: Int): DatomicQueryApiOffset[Tpl] = DatomicQueryApiOffset(elements, limit, o)
  override def from(cursor: String): DatomicQueryApiCursor[Tpl] = DatomicQueryApiCursor(elements, limit, cursor)

  override def get(implicit conn0: Connection, ec: ExecutionContext): Future[List[Tpl]] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    conn.rpc.query[Tpl](conn.proxy, elements).future
  }
}
