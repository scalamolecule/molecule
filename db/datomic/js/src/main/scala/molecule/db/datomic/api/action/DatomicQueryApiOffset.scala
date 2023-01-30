package molecule.db.datomic.api.action

import molecule.boilerplate.ast.Model._
import molecule.core.api.Connection
import molecule.core.api.action.QueryApiOffset
import molecule.db.datomic.facade.DatomicConn_JS
import scala.concurrent.{ExecutionContext, Future}

case class DatomicQueryApiOffset[Tpl](
  elements: List[Element],
  private val limit: Int = 0,
  private val offset: Int = 0
) extends QueryApiOffset[Tpl] {

  override def limit(l: Int): DatomicQueryApiOffset[Tpl] = copy(limit = l)

  override def get(implicit conn0: Connection, ec: ExecutionContext): Future[(List[Tpl], Int)] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    conn.rpc.query[Tpl](conn.proxy, elements).future
    ???
  }
}
