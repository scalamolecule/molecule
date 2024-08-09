package molecule.datalog.datomic.query.cursorStrategy

import java.util.Base64
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.ops.ModelTransformations_
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.dbView.DbView
import molecule.core.query.Pagination
import molecule.core.util.FutureUtils
import molecule.datalog.core.query.{DatomicQueryBase, Model2DatomicQuery}
import molecule.datalog.datomic.facade.DatomicConn_JVM
import molecule.datalog.datomic.query.DatomicQueryResolve
import scala.collection.mutable.ListBuffer

/**
 * Molecule has a unique attribute that is sorted first.
 *
 * Then we can easily filter by its previous value in either direction.
 *
 * @param elements Molecule model
 * @param optLimit    When going forward from start, use a positive number.
 *                 And vice versa from end with a negative number. Can't be zero.
 * @param cursor   Base64 encoded cursor meta information
 * @tparam Tpl Type of each row
 */
case class PrimaryUnique[Tpl](
  elements: List[Element],
  optLimit: Option[Int],
  cursor: String,
  dbView: Option[DbView],
  m2q: Model2DatomicQuery[Tpl] with DatomicQueryBase
) extends DatomicQueryResolve[Tpl](elements, dbView, m2q)
  with FutureUtils with Pagination[Tpl] with ModelTransformations_ with MoleculeLogging {

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
      if (m2q.isNested) {
        val nestedRows    = m2q.rows2nested(sortedRows)
        val toplevelCount = nestedRows.length
        val limitAbs      = limit.abs.min(toplevelCount)
        val hasMore       = limitAbs < toplevelCount
        val selectedRows  = nestedRows.take(limitAbs)
        val tpls          = if (forward) selectedRows else selectedRows.reverse
        val cursor        = nextCursorUniques(tpls, tokens)
        (tpls, cursor, hasMore)

      } else {
        val totalCount = rows.size
        val limitAbs   = limit.abs.min(totalCount)
        val hasMore    = limitAbs < totalCount
        val tuples     = ListBuffer.empty[Tpl]

        if (m2q.isOptNested) {
          postAdjustPullCasts()
          val row2tpl = m2q.pullOptNestedRow2tpl
          sortedRows.subList(0, limitAbs).forEach(row => tuples += row2tpl(row))
          val tpls   = if (forward) tuples.toList else tuples.toList.reverse
          val cursor = nextCursorUniques(tpls, tokens)
          (tpls, cursor, hasMore)

        } else if (m2q.nestedOptRef) {
          postAdjustPullCasts()
          val row2tpl = m2q.pullOptRefRow2tpl
          sortedRows.subList(0, limitAbs).forEach(row => tuples += row2tpl(row))
          val tpls   = if (forward) tuples.toList else tuples.toList.reverse
          val cursor = nextCursorUniques(tpls, tokens)
          (tpls, cursor, hasMore)

        } else {
          val row2tpl = m2q.castRow2AnyTpl(m2q.aritiess.head, m2q.castss.head, 0, None)
          sortedRows.subList(0, limitAbs).forEach(row => tuples += row2tpl(row).asInstanceOf[Tpl])
          val tpls   = if (forward) tuples.toList else tuples.toList.reverse
          val cursor = nextCursorUniques(tpls, tokens)
          (tpls, cursor, hasMore)
        }
      }
    }
  } catch {
    case t: Throwable => throw ModelError(t.toString)
  }
}
