package molecule.db.datomic.api.action

import molecule.boilerplate.ast.Model._
import molecule.core.api.Connection
import molecule.core.api.action.QueryApi
import molecule.db.datomic.facade.DatomicConn_JS
import scala.concurrent.{ExecutionContext, Future}

class DatomicQueryApiImpl[Tpl](elements: List[Element])
  extends QueryApi[Tpl] {

  override def take(n: Int): DatomicQueryApiImpl[Tpl] = this
  override def drop(n: Int): DatomicQueryApiImpl[Tpl] = this
  override def from(cursor: String): DatomicQueryApiImpl[Tpl] = this

  override def get(implicit conn0: Connection, ec: ExecutionContext): Future[List[Tpl]] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    conn.rpc.query[Tpl](conn.proxy, elements).future
  }
}
