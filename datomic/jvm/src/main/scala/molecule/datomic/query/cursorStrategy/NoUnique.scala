package molecule.datomic.query.cursorStrategy

import java.util.Base64
import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.util.FutureUtils
import molecule.datomic.facade.DatomicConn_JVM
import molecule.datomic.query.DatomicQueryResolve


case class NoUnique[Tpl](
  elements: List[Element],
  limit: Option[Int],
  cursor: String
) extends DatomicQueryResolve[Tpl](elements, limit)
  with FutureUtils with CursorUtils with MoleculeLogging {

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
      (row: Row) => pullRow2tpl(row).hashCode()
    else
      (row: Row) => row2AnyTpl(row).hashCode()

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
    case t: Throwable => throw MoleculeError(t.toString)
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
