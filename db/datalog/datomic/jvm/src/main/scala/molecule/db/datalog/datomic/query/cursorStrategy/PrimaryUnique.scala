package molecule.db.datalog.datomic.query.cursorStrategy

import molecule.base.error.ModelError
import molecule.core.dataModel.*
import molecule.core.util.MoleculeLogging
import molecule.db.core.marshalling.dbView.DbView
import molecule.db.core.ops.ModelTransformations_
import molecule.db.core.query.Pagination
import molecule.db.core.util.FutureUtils
import molecule.db.datalog.core.query.{DatomicQueryBase, Model2DatomicQuery}
import molecule.db.datalog.datomic.facade.DatomicConn_JVM
import molecule.db.datalog.datomic.query.DatomicQueryResolve
import scala.collection.mutable.ListBuffer

/**
 * Molecule has a unique attribute that is sorted first.
 *
 * Then we can easily filter by its previous value in either direction.
 *
 * @param elements Molecule model
 * @param optLimit When going forward from start, use a positive number.
 *                 And vice versa from end with a negative number. Can't be zero.
 * @param cursor   Base64 encoded cursor meta information
 * @tparam Tpl Type of each row
 */
case class PrimaryUnique[Tpl](
  dataModel: DataModel,
  optLimit: Option[Int],
  cursor: String,
  dbView: Option[DbView],
  m2q: Model2DatomicQuery[Tpl] & DatomicQueryBase
) extends DatomicQueryResolve[Tpl](dataModel, dbView, m2q)
  with FutureUtils with Pagination[Tpl] with ModelTransformations_ with MoleculeLogging {

  def getPage(tokens: List[String], limit: Int)
             (implicit conn: DatomicConn_JVM): (List[Tpl], String, Boolean) = try {
    val List(_, _, tpe, ent, attr, _, a, z) = tokens

    val forward     = limit > 0
    val (fn, v)     = if (forward) (Gt, z) else (Lt, a)
    val filterAttr  = getFilterAttr(tpe, ent, attr, fn, v)
    val altElements = filterAttr +: (if (forward) dataModel.elements else reverseTopLevelSorting(dataModel.elements))
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
          val row2tpl = m2q.castRow2AnyTpl(m2q.castss.head, 0)
          sortedRows.subList(0, limitAbs).forEach(row =>
            tuples += row2tpl(row).asInstanceOf[Tpl]
          )
          val tpls   = if (forward) tuples.toList else tuples.toList.reverse
          val cursor = nextCursorUniques(tpls, tokens)
          (tpls, cursor, hasMore)

        } else if (m2q.nestedOptRef) {
          postAdjustPullCasts()
          val row2tpl = m2q.castRow2AnyTpl(m2q.castss.head, 0)
          sortedRows.subList(0, limitAbs).forEach(row =>
            tuples += row2tpl(row).asInstanceOf[Tpl]
          )
          val tpls   = if (forward) tuples.toList else tuples.toList.reverse
          val cursor = nextCursorUniques(tpls, tokens)
          (tpls, cursor, hasMore)

        } else {
          val row2tpl = m2q.castRow2AnyTpl(m2q.castss.head, 0)
          sortedRows.subList(0, limitAbs).forEach(row =>
            tuples += row2tpl(row).asInstanceOf[Tpl]
          )
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
