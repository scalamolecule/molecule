package molecule.db.datomic.api.action

import molecule.boilerplate.ast.Model._
import molecule.core.api.Connection
import molecule.core.api.action.QueryApiCursor
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.query.DatomicQueryCursor
import scala.concurrent.{ExecutionContext, Future}

case class DatomicQueryApiCursor[Tpl](
  elements: List[Element],
  limit: Option[Int],
  cursor: String
) extends QueryApiCursor[Tpl] {

  override def limit(l: Int): DatomicQueryApiCursor[Tpl] = copy(limit = Some(l))

  override def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], String, Boolean)] = {
    DatomicQueryCursor[Tpl](elements, limit, Some(cursor))
      .getListFromCursor(conn.asInstanceOf[DatomicConn_JVM], ec)
  }
}
