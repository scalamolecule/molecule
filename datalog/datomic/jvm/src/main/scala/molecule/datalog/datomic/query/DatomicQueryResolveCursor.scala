package molecule.datalog.datomic.query

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
import molecule.datalog.datomic.query.cursorStrategy.{NoUnique, PrimaryUnique, SubUnique}
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

/**
 *
 * @param elements Molecule model
 * @param optLimit When going forward from start, use a positive number.
 *                 And vice versa from end with a negative number. Can't be zero.
 * @param cursor   Base64 encoded cursor meta information
 * @tparam Tpl Type of each row
 */
case class DatomicQueryResolveCursor[Tpl](
  elements: List[Element],
  optLimit: Option[Int],
  cursor: Option[String],
  dbView: Option[DbView],
  m2q: Model2DatomicQuery[Tpl] with DatomicQueryBase
) extends DatomicQueryResolve[Tpl](elements, dbView, m2q)
  with FutureUtils
  with Pagination[Tpl]
  with ModelTransformations_
  with MoleculeLogging {


  def getListFromCursor_sync(implicit conn: DatomicConn_JVM)
  : (List[Tpl], String, Boolean) = {
    optLimit match {
      case Some(limit) => cursor match {
        case Some("")     => getInitialPage(limit)
        case Some(cursor) =>
          val raw      = new String(Base64.getDecoder.decode(cursor))
          val tokens   = raw.split("\n").toList
          val strategy = tokens.head
          val hash     = tokens(1)

          if ((elements.hashCode() & 0xFFFFF) != hash.toInt) {
            throw ModelError("Can only use cursor for un-modified query.")
          } else {
            strategy match {
              case "1" => PrimaryUnique(elements, optLimit, cursor, dbView, m2q).getPage(tokens, limit)
              case "2" => SubUnique(elements, optLimit, cursor, dbView, m2q).getPage(tokens, limit)
              case "3" => NoUnique(elements, optLimit, cursor, dbView, m2q).getPage(tokens, limit)
            }
          }
        case None         => throw ModelError("Unexpected undefined cursor.")
      }
      case None        => throw ModelError("Please set limit to use cursor pagination.")
    }
  }


  private def getInitialPage(limit: Int)(implicit conn: DatomicConn_JVM)
  : (List[Tpl], String, Boolean) = {
    val forward     = limit > 0
    val altElements = if (forward) elements else reverseTopLevelSorting(elements)
    val rows        = getRawData(conn, altElements)
    val sortedRows  = sortRows(rows)
    logger.debug(sortedRows.toArray().mkString("\n"))

    if (m2q.isNested) {
      val nestedRows    = m2q.rows2nested(sortedRows)
      val toplevelCount = nestedRows.length
      val limitAbs      = limit.abs.min(toplevelCount)
      val hasMore       = limitAbs < toplevelCount
      val selectedRows  = nestedRows.take(limitAbs)
      val tpls          = if (forward) selectedRows else selectedRows.reverse
      val cursor        = initialCursor(conn, elements, tpls)
      (tpls, cursor, hasMore)

    } else {
      val totalCount = rows.size
      val limitAbs   = limit.abs.min(totalCount)
      val hasMore    = limitAbs < totalCount
      val tuples     = ListBuffer.empty[Tpl]

      if (m2q.isNestedOpt) {
        postAdjustPullCasts()
        if (totalCount == 0) {
          (Nil, "", false)
        } else {
          val selectedRows = sortedRows.subList(0, limitAbs)
          selectedRows.forEach(row => tuples += m2q.pullRow2tpl(row))
          val tpls   = (if (forward) tuples.toList else tuples.toList.reverse).filterNot(_ == Nil)
          val cursor = initialCursor(conn, elements, tpls)
          (tpls, cursor, hasMore)
        }

      } else {
        if (totalCount == 0) {
          (Nil, "", false)
        } else {
          val row2tpl      = m2q.castRow2AnyTpl(m2q.aritiess.head, m2q.castss.head, 0, None)
          val selectedRows = sortedRows.subList(0, limitAbs)
          selectedRows.forEach(row => tuples += row2tpl(row).asInstanceOf[Tpl])
          val tpls   = if (forward) tuples.toList else tuples.toList.reverse
          val cursor = initialCursor(conn, elements, tpls)
          (tpls, cursor, hasMore)
        }
      }
    }
  }
}
