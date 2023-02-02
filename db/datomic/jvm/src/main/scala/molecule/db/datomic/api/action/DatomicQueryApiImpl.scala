package molecule.db.datomic.api.action

import molecule.boilerplate.ast.Model._
import molecule.core.api.Connection
import molecule.core.api.action.QueryApi
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.query.DatomicQueryOffset
import scala.concurrent.{ExecutionContext, Future}

case class DatomicQueryApiImpl[Tpl](
  elements: List[Element],
  limit: Option[Int] = None
) extends QueryApi[Tpl] {

  // Universal api

  override def limit(l: Int): DatomicQueryApiImpl[Tpl] = copy(limit = Some(l))
  override def offset(offset: Int): DatomicQueryApiOffset[Tpl] = DatomicQueryApiOffset(elements, limit, offset)
  override def from(cursor: String): DatomicQueryApiCursor[Tpl] = DatomicQueryApiCursor(elements, limit, cursor)

  override def get(implicit conn: Connection, ec: ExecutionContext): Future[List[Tpl]] = {
    DatomicQueryOffset[Tpl](elements, limit, None)
      .getListFromOffset(conn.asInstanceOf[DatomicConn_JVM], ec).map(_._1)
  }


  // Datomic special features

  // Time
  def asOf(n: Int): QueryApi[Tpl] = ???
  def history: QueryApi[Tpl] = ???
}
