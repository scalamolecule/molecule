package molecule.db.datalog.datomic.query.cursorStrategy

import molecule.base.error.ModelError
import molecule.core.ast.DataModel
import molecule.core.util.MoleculeLogging
import molecule.db.core.marshalling.dbView.DbView
import molecule.db.core.query.Pagination
import molecule.db.core.util.FutureUtils
import molecule.db.datalog.core.query.{DatomicQueryBase, Model2DatomicQuery}
import molecule.db.datalog.datomic.facade.DatomicConn_JVM
import molecule.db.datalog.datomic.query.DatomicQueryResolve


case class NoUnique[Tpl](
  dataModel: DataModel,
  optLimit: Option[Int],
  cursor: String,
  dbView: Option[DbView],
  m2q: Model2DatomicQuery[Tpl] & DatomicQueryBase
) extends DatomicQueryResolve[Tpl](dataModel, dbView, m2q)
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
