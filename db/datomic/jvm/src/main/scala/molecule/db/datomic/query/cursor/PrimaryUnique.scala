package molecule.db.datomic.query.cursor

import java.util.Base64
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.ops.ModelTransformations
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.api.action.ApiUtils
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.query.DatomicQuery
import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}

/**
 * Molecule has a unique attribute that is sorted first.
 *
 * Then we can easily filter by its previous value in either direction.
 *
 * @param elements Molecule model
 * @param limit    When going forward from start, use a positive number.
 *                 And vice versa from end with a negative number. Can't be zero.
 * @param cursor   Base64 encoded cursor meta information
 * @tparam Tpl Type of each row
 */
case class PrimaryUnique[Tpl](
  elements: List[Element],
  limit: Option[Int],
  cursor: String
) extends DatomicQuery[Tpl](elements, limit)
  with ApiUtils
  with CursorUtils
  with ModelTransformations
  with MoleculeLogging {

  def getPage(tokens: List[String], limit: Int)
             (implicit conn: DatomicConn_JVM, ec: ExecutionContext)
  : Future[(List[Tpl], String, Boolean)] = future {
    val List(_, _, tpe, ns, attr, _, a, z) = tokens

    val forward     = limit > 0
    val (fn, v)     = if (forward) (Gt, z) else (Lt, a)
    val filterAttr  = getFilterAttr(tpe, ns, attr, fn, v)
    val altElements = filterAttr +: (if (forward) elements else reverseTopLevelSorting(elements))
    val rows        = getRawData(conn, altElements)
    val totalCount  = rows.size
    val sortedRows  = sortRows(rows)
    val limitAbs    = limit.abs.min(totalCount)
    logger.debug(sortedRows.toArray().mkString("\n"))
    postAdjustAritiess()
    if (totalCount == 0) {
      (Nil, "", false)
    } else {
      val hasMore = limitAbs < totalCount
      val tuples  = ListBuffer.empty[Tpl]
      val row2tpl = castRow2Tpl(aritiess.head, castss.head, 0, None)
      sortedRows.subList(0, limitAbs).forEach(row => tuples += row2tpl(row).asInstanceOf[Tpl])
      val tpls   = if (forward) tuples.result() else tuples.result().reverse
      val cursor = nextCursorUnique(tpls, tokens)
      (tpls, cursor, hasMore)
    }
  }


  private def nextCursorUnique(tpls: List[Tpl], tokens: List[String]): String = {
    val List(_, _, tpe, _, _, i, _, _) = tokens

    val uniqueIndex = i.toInt
    val encode      = encoder(tpe)
    val tokens1     = tokens.dropRight(2) ++ getUniquePair(tpls, uniqueIndex, encode)
    Base64.getEncoder.encodeToString(tokens1.mkString("\n").getBytes)
  }
}
