package molecule.datalog.datomic.query.cursorStrategy

import java.util.Base64
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
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
  with FutureUtils with Pagination with MoleculeLogging {

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
    val identifyRow = (isNestedOpt: Boolean) => if (isNestedOpt)
      (row: m2q.Row) => m2q.pullRow2tpl(row).hashCode()
    else {
      val row2AnyTpl     = m2q.castRow2AnyTpl(m2q.aritiess.head, m2q.castss.head, 0, None)
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

  private def nextCursorNoUnique(tpls: List[Tpl], tokens: List[String]): String = {
    val attrTokens = tokens.drop(2).dropRight(6).grouped(13).toList.collect {
      case List(kind, dir, pos, tpe, ns, attr, uniqueIndex, _, _, _, _, _, _) =>
        List(kind, dir, pos, tpe, ns, attr, uniqueIndex) ++
          getUniqueValues(tpls, uniqueIndex.toInt, encoder(tpe, kind))
    }.flatten
    val tokens1    = tokens.take(2) ++ attrTokens ++ getRowHashes(tpls)
    Base64.getEncoder.encodeToString(tokens1.mkString("\n").getBytes)
  }
}
