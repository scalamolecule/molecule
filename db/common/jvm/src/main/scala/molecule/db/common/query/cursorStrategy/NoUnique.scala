package molecule.db.common.query.cursorStrategy

import molecule.base.error.ModelError
import molecule.core.dataModel.DataModel
import molecule.core.util.MoleculeLogging
import molecule.db.common.facade.JdbcConn_JVM
import molecule.db.common.query.{Pagination, SqlQueryResolve}
import molecule.db.common.util.FutureUtils
import molecule.db.common.query.{Model2SqlQuery, SqlQueryBase}


case class NoUnique[Tpl](
  dataModel: DataModel,
  optLimit: Option[Int],
  cursor: String,
  m2q: Model2SqlQuery & SqlQueryBase
) extends SqlQueryResolve[Tpl](dataModel, m2q)
  with FutureUtils with Pagination[Tpl] with MoleculeLogging {


  def getPage(allTokens: List[String], limit: Int)
             (implicit conn: JdbcConn_JVM)
  : (List[Tpl], String, Boolean) = try {
    val forward     = limit > 0
    val attrsTokens = allTokens.drop(2).dropRight(6).grouped(13).toList.sortBy(_(2))

    val rowHashes = {
      val List(a, b, c, x, y, z) = allTokens.takeRight(6)
      (if (forward) List(z, y, x) else List(a, b, c)).filter(_.nonEmpty).map(_.toInt)
    }

    val identifyTpl = (tpl: Tpl) => tpl.hashCode()

    paginateFromIdentifiers(
      conn,
      limit,
      forward,
      allTokens,
      attrsTokens.head,
      rowHashes,
      identifyTpl,
      nextCursorNoUnique
    )
  } catch {
    case t: Throwable => throw ModelError(t.toString)
  }
}
