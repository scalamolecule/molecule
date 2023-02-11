package molecule.db.datomic.query.cursor

import java.util.Base64
import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.ops.ModelTransformations
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.api.FutureUtils
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.query.DatomicQueryResolve
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
) extends DatomicQueryResolve[Tpl](elements, limit)
  with FutureUtils with CursorUtils with ModelTransformations with MoleculeLogging {

  def getPage(tokens: List[String], limit: Int)
             (implicit conn: DatomicConn_JVM): (List[Tpl], String, Boolean) = try {
    val List(_, _, tpe, ns, attr, _, a, z) = tokens

    val forward     = limit > 0
    val (fn, v)     = if (forward) (Gt, z) else (Lt, a)
    val filterAttr  = getFilterAttr(tpe, ns, attr, fn, v)
    val altElements = filterAttr +: (if (forward) elements else reverseTopLevelSorting(elements))
    val rows        = getRawData(conn, altElements)
    val sortedRows  = sortRows(rows)
    logger.debug(sortedRows.toArray().mkString("\n"))

    if (sortedRows.size() == 0) {
      (Nil, "", false)
    } else {
      if (isNested) {
        val nestedRows    = rows2nested(sortedRows)
        val toplevelCount = nestedRows.length
        val limitAbs      = limit.abs.min(toplevelCount)
        val hasMore       = limitAbs < toplevelCount
        val selectedRows  = nestedRows.take(limitAbs)
        val tpls          = if (forward) selectedRows else selectedRows.reverse
        val cursor        = nextCursorUnique(tpls, tokens)
        (tpls, cursor, hasMore)

      } else {
        val totalCount = rows.size
        val limitAbs   = limit.abs.min(totalCount)
        val hasMore    = limitAbs < totalCount
        val tuples     = ListBuffer.empty[Tpl]

        if (isNestedOpt) {
          postAdjustPullCasts()
          sortedRows.subList(0, limitAbs).forEach(row => tuples += pullRow2tpl(row))
          val tpls   = if (forward) tuples.result() else tuples.result().reverse
          val cursor = nextCursorUnique(tpls, tokens)
          (tpls, cursor, hasMore)

        } else {
          postAdjustAritiess()
          val row2tpl = castRow2AnyTpl(aritiess.head, castss.head, 0, None)
          sortedRows.subList(0, limitAbs).forEach(row => tuples += row2tpl(row).asInstanceOf[Tpl])
          val tpls   = if (forward) tuples.result() else tuples.result().reverse
          val cursor = nextCursorUnique(tpls, tokens)
          (tpls, cursor, hasMore)
        }
      }
    }
  } catch {
    case t: Throwable => throw MoleculeError(t.toString)
  }


  private def nextCursorUnique(tpls: List[Tpl], tokens: List[String]): String = {
    val List(_, _, tpe, _, _, i, _, _) = tokens
    val tokens1                        = tokens.dropRight(2) ++ getUniquePair(tpls, i.toInt, encoder(tpe, ""))
    Base64.getEncoder.encodeToString(tokens1.mkString("\n").getBytes)
  }
}
