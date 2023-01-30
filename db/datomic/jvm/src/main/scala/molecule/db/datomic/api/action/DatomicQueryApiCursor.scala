package molecule.db.datomic.api.action

import molecule.boilerplate.ast.Model._
import molecule.core.api.Connection
import molecule.core.api.action.QueryApiCursor
import molecule.db.datomic.facade.DatomicConn_JVM
import scala.concurrent.{ExecutionContext, Future}

case class DatomicQueryApiCursor[Tpl](
  elements: List[Element],
  private val limit: Option[Int],
  private val cursor: String
) extends DatomicQuery[Tpl](elements, limit, None, Some(cursor)) with QueryApiCursor[Tpl] {

  override def limit(l: Int): DatomicQueryApiCursor[Tpl] = copy(limit = Some(l))

  override def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], String)] = {
    getListCursor(conn.asInstanceOf[DatomicConn_JVM], ec)
  }
}
