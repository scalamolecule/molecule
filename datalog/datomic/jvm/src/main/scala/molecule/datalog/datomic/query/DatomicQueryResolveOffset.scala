package molecule.datalog.datomic.query

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.dbView.DbView
import molecule.core.util.FutureUtils
import molecule.datalog.core.query.{DatomicQueryBase, Model2DatomicQuery}
import molecule.datalog.datomic.facade.DatomicConn_JVM
import scala.collection.mutable.ListBuffer

/**
 * @param elements  Molecule model
 * @param optLimit  When going forward from start, use a positive number.
 *                  And vice versa from end with a negative number. Can't be zero.
 * @param optOffset Positive offset from start when going forwards,
 *                  negative offset from end when going backwards
 * @param dbView    Database with a time perspective (Datomic)
 */
case class DatomicQueryResolveOffset[Tpl](
  elements: List[Element],
  optLimit: Option[Int],
  optOffset: Option[Int],
  dbView: Option[DbView],
  m2q: Model2DatomicQuery[Tpl] with DatomicQueryBase
) extends DatomicQueryResolve[Tpl](elements, dbView, m2q)
  with FutureUtils
  with MoleculeLogging {


  // Datomic querying is synchronous
  def getListFromOffset_sync(implicit conn: DatomicConn_JVM): (List[Tpl], Int, Boolean) = {
    getListFromOffset_sync(None)(conn)
  }

  // Optional use of DB_AFTER for subscriptions
  def getListFromOffset_sync(altDb: Option[datomic.Database])(implicit conn: DatomicConn_JVM)
  : (List[Tpl], Int, Boolean) = {
    lazy val limitSign  = optLimit.get >> 31
    lazy val offsetSign = optOffset.get >> 31
    if (optOffset.isDefined && optLimit.isDefined && limitSign != offsetSign) {
      throw ModelError("Limit and offset should both be positive or negative.")
    }
    val rows       = getRawData(conn, altDb = altDb)
    val totalCount = rows.size
    val sortedRows = sortRows(rows)

    if (m2q.isNested) {
      val nestedRows    = m2q.rows2nested(sortedRows)
      val topLevelCount = nestedRows.length
      val fromUntil     = getFromUntil(topLevelCount, optLimit, optOffset)
      val hasMore       = fromUntil.fold(totalCount > 0)(_._3)
      (offsetList(nestedRows, fromUntil), topLevelCount, hasMore)

    } else {
      val fromUntil = getFromUntil(totalCount, optLimit, optOffset)
      val hasMore   = fromUntil.fold(totalCount > 0)(_._3)
      val tuples    = ListBuffer.empty[Tpl]

      if (m2q.isNestedOpt) {
        postAdjustPullCasts()
        offsetRaw(sortedRows, fromUntil).forEach { row =>
          tuples += m2q.pullRow2tpl(row)
        }
        (tuples.result(), totalCount, hasMore)

      } else {
        val row2tpl = m2q.castRow2AnyTpl(m2q.aritiess.head, m2q.castss.head, 0, None)
        offsetRaw(sortedRows, fromUntil).forEach(row => tuples += row2tpl(row).asInstanceOf[Tpl])
        (tuples.result(), totalCount, hasMore)
      }
    }
  }


  def subscribe(
    conn: DatomicConn_JVM,
    callback: List[Tpl] => Unit
  ): Unit = {
    val involvedAttrs    = getAttrNames(elements)
    val involvedDeleteNs = getInitialNs(elements)
    val maybeCallback    = (mutationAttrs: Set[String], isDelete: Boolean) => {
      if (
        mutationAttrs.exists(involvedAttrs.contains) ||
          isDelete && mutationAttrs.head.startsWith(involvedDeleteNs)
      ) {
        val m2q = new Model2DatomicQuery[Tpl](elements)
        callback(
          DatomicQueryResolveOffset(elements, optLimit, None, None, m2q).getListFromOffset_sync(conn)._1
        )
      }
    }
    conn.addCallback(elements -> maybeCallback)
  }

  def unsubscribe(conn: DatomicConn_JVM): Unit = {
    conn.removeCallback(elements)
  }


  private def offsetList(
    sortedRows: List[Tpl],
    fromUntil: Option[(Int, Int, Boolean)]
  ): List[Tpl] = {
    fromUntil.fold(sortedRows) {
      case (from, until, _) => sortedRows.slice(from, until)
    }
  }
}
