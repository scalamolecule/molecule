package molecule.datalog.datomic.query.cursorStrategy

import molecule.base.error.ModelError
import molecule.boilerplate.ast.DataModel._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.dbView.DbView
import molecule.core.query.Pagination
import molecule.core.util.FutureUtils
import molecule.datalog.core.query.{DatomicQueryBase, Model2DatomicQuery}
import molecule.datalog.datomic.facade.DatomicConn_JVM
import molecule.datalog.datomic.query.DatomicQueryResolve


case class NoUnique[Tpl](
  elements: List[Element],
  optLimit: Option[Int],
  cursor: String,
  dbView: Option[DbView],
  m2q: Model2DatomicQuery[Tpl] with DatomicQueryBase
) extends DatomicQueryResolve[Tpl](elements, dbView, m2q)
  with FutureUtils with Pagination[Tpl] with MoleculeLogging {

  def getPage(allTokens: List[String], limit: Int)
             (implicit conn: DatomicConn_JVM)
  : (List[Tpl], String, Boolean) = try {
    val forward     = limit > 0
    val attrsTokens = allTokens.drop(2).dropRight(6).grouped(13).toList.sortBy(_(2))

    val rowHashes = {
      val List(a, b, c, x, y, z) = allTokens.takeRight(6)
      (if (forward) List(z, y, x) else List(a, b, c)).filter(_.nonEmpty).map(_.toInt)
    }

    val identifyTpl = (tpl: Tpl) => tpl.hashCode()
    val identifyRow = (_: Unit) => {
      val row2AnyTpl = m2q.castRow2AnyTpl(m2q.castss.head, 0)
      (row: m2q.Row) => row2AnyTpl(row).hashCode()
    }
    paginateFromIdentifiers(
      conn,
      limit,
      forward,
      allTokens,
      attrsTokens.head,
      rowHashes,
      identifyTpl,
      identifyRow,
      nextCursorNoUnique
    )
  } catch {
    case t: Throwable => throw ModelError(t.toString)
  }
}
