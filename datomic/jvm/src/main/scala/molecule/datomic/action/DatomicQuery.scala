package molecule.datomic.action

import java.util.Date
import molecule.boilerplate.ast.Model._
import molecule.core.action.{Action, Query}
import molecule.core.api.TxReport
import molecule.core.marshalling.dbView._

case class DatomicQuery[Tpl](
  private val elements0: List[Element],
  limit: Option[Int] = None,
  dbView: Option[DbView] = None
) extends Query[Tpl](elements0) {

  // Universal api

  override def limit(l: Int): DatomicQuery[Tpl] = copy(limit = Some(l))
  override def offset(offset: Int): DatomicQueryOffset[Tpl] = DatomicQueryOffset(elements, limit, offset)
  override def from(cursor: String): DatomicQueryCursor[Tpl] = DatomicQueryCursor(elements, limit, cursor)

  // Datomic special features

  // Time
  def asOf(d: Date): DatomicQuery[Tpl] = copy(dbView = Some(AsOf(TxDate(d))))
  def asOf(t: Long): DatomicQuery[Tpl] = copy(dbView = Some(AsOf(TxLong(t))))
  def asOf(txReport: TxReport): DatomicQuery[Tpl] = copy(dbView = Some(AsOf(TxLong(txReport.tx))))

  def since(d: Date): DatomicQuery[Tpl] = copy(dbView = Some(Since(TxDate(d))))
  def since(t: Long): DatomicQuery[Tpl] = copy(dbView = Some(Since(TxLong(t))))
  def since(txReport: TxReport): DatomicQuery[Tpl] = copy(dbView = Some(Since(TxLong(txReport.tx))))

  def widh(txData: Action*): DatomicQuery[Tpl] = ??? // copy(dbView = Some(With(txData)))
//  def widh(txData: Seq[Element]*): DatomicQuery[Tpl] = copy(dbView = Some(With(txData)))

  def history: DatomicQuery[Tpl] = copy(dbView = Some(History))
}
