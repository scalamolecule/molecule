package molecule.db.datomic.api.action

import molecule.boilerplate.ast.Model._
import molecule.core.api.Connection
import molecule.core.api.action.{QueryApiCursor, QueryApiOffset}
import molecule.db.datomic.facade.DatomicConn_JS
import scala.concurrent.{ExecutionContext, Future}

case class DatomicQueryApiCursor[Tpl](
  elements: List[Element],
  private val limit: Int = 0,
  private val cursor: String = ""
) extends QueryApiCursor[Tpl] {

  override def limit(l: Int): DatomicQueryApiCursor[Tpl] = copy(limit = l)

  override def get(implicit conn0: Connection, ec: ExecutionContext): Future[(List[Tpl], String)] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    conn.rpc.query[Tpl](conn.proxy, elements).future
    ???
  }
}
