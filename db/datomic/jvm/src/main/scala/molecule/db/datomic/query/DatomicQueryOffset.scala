package molecule.db.datomic.query

import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.api.action.ApiUtils
import molecule.db.datomic.facade.DatomicConn_JVM
import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}

/**
 *
 * @param elements Molecule model
 * @param limit    When going forward from start, use a positive number.
 *                 And vice versa from end with a negative number. Can't be zero.
 * @param offset   Positive offset from start when going forwards,
 *                 negative offset from end when going backwards
 * @tparam Tpl Type of each row
 */
case class DatomicQueryOffset[Tpl](
  elements: List[Element],
  limit: Option[Int],
  offset: Option[Int],
) extends DatomicQuery[Tpl](elements, limit)
  with ApiUtils
  with MoleculeLogging {

  // Handles both offset- and non-paginated results
  // returns (rows, total count, hasMore)
  def getListFromOffset(implicit conn: DatomicConn_JVM, ec: ExecutionContext)
  : Future[(List[Tpl], Int, Boolean)] = future {
    limitNotZero()
    if (offset.isDefined && limit.isDefined && limit.get >> 31 != offset.get >> 31) {
      throw MoleculeError("Limit and offset should both be positive or negative.")
    }
    val rows       = getRawData(conn)
    val totalCount = rows.size
    val sortedRows = sortRows(rows)
    logger.debug(sortedRows.toArray().mkString("\n"))

    if (isNested) {
      val nestedRows    = rows2nested(sortedRows)
      val toplevelCount = nestedRows.length
      val fromUntil     = getFromUntil(toplevelCount, limit, offset)
      val hasMore       = fromUntil.fold(totalCount > 0)(_._3)
      (offsetList(nestedRows, fromUntil), toplevelCount, hasMore)

    } else {
      val fromUntil = getFromUntil(totalCount, limit, offset)
      val hasMore   = fromUntil.fold(totalCount > 0)(_._3)
      val tuples    = ListBuffer.empty[Tpl]

      if (isNestedOpt) {
        postAdjustPullCasts()
        offsetRaw(sortedRows, fromUntil).forEach(row => tuples += pullRow2tpl(row))
        (tuples.result(), totalCount, hasMore)

      } else {
        postAdjustAritiess()
        val row2tpl = castRow2AnyTpl(aritiess.head, castss.head, 0, None)
        offsetRaw(sortedRows, fromUntil).forEach(row => tuples += row2tpl(row).asInstanceOf[Tpl])
        (tuples.result(), totalCount, hasMore)
      }
    }
  }
}
