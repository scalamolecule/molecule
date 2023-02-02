package molecule.db.datomic.api.action

import molecule.boilerplate.ast.Model._
import molecule.core.api.Connection
import molecule.core.api.action.QueryApiOffset
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.query.DatomicQueryOffset
import scala.concurrent.{ExecutionContext, Future}

case class DatomicQueryApiOffset[Tpl](
  elements: List[Element],
  limit: Option[Int],
  offset: Int
) extends QueryApiOffset[Tpl] {

  override def limit(l: Int): DatomicQueryApiOffset[Tpl] = copy(limit = Some(l))

  override def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], Int, Boolean)] = {
    DatomicQueryOffset[Tpl](elements, limit, Some(offset))
      .getListFromOffset(conn.asInstanceOf[DatomicConn_JVM], ec)
  }
}
