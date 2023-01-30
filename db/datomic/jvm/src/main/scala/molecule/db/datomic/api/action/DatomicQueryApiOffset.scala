package molecule.db.datomic.api.action

import molecule.boilerplate.ast.Model._
import molecule.core.api.Connection
import molecule.core.api.action.QueryApiOffset
import molecule.db.datomic.facade.DatomicConn_JVM
import scala.concurrent.{ExecutionContext, Future}

case class DatomicQueryApiOffset[Tpl](
  elements: List[Element],
  private val limit: Option[Int],
  private val offset: Int
) extends DatomicQuery[Tpl](elements, limit, Some(offset), None) with QueryApiOffset[Tpl] {

  override def limit(l: Int): DatomicQueryApiOffset[Tpl] = copy(limit = Some(l))

  override def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], Int)] = {
    getListOffset(conn.asInstanceOf[DatomicConn_JVM], ec)
  }
}
