package molecule.datomic.query

import datomic.Database
import molecule.base.error.ExecutionError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.util.FutureUtils
import molecule.datomic.facade.DatomicConn_JVM
import molecule.datomic.subscription.TxReportWatcher
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
case class DatomicQueryResolveOffset[Tpl](
  elements: List[Element],
  limit: Option[Int],
  offset: Option[Int]
) extends DatomicQueryResolve[Tpl](elements, limit)
  with FutureUtils
  with MoleculeLogging {

  // Handles both offset- and non-paginated results
  // returns (rows, total count, hasMore)
  def getListFromOffset_async(implicit conn: DatomicConn_JVM, ec: ExecutionContext)
  : Future[(List[Tpl], Int, Boolean)] = future(getListFromOffset_sync(conn))

  // Datomic querying is synchronous
  def getListFromOffset_sync(implicit conn: DatomicConn_JVM): (List[Tpl], Int, Boolean) = {
    getListFromOffset_sync(None)(conn)
  }
  // Optional use of DB_AFTER for subscriptions
  def getListFromOffset_sync(altDb: Option[datomic.Database])(implicit conn: DatomicConn_JVM)
  : (List[Tpl], Int, Boolean) = try {
    if (offset.isDefined && limit.isDefined && limit.get >> 31 != offset.get >> 31) {
      throw ExecutionError("Limit and offset should both be positive or negative.")
    }
    val rows       = getRawData(conn, altDb = altDb)
    val totalCount = rows.size
    val sortedRows = sortRows(rows)
    //    logger.debug(sortedRows.toArray().mkString("\n"))

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
  } catch {
    case t: Throwable => throw ExecutionError(t.toString)
  }


  def subscribe(
    conn: DatomicConn_JVM,
    txReportWatcher: TxReportWatcher,
    callback: List[Tpl] => Unit
  ): Unit = {
    val allAttrIds   = conn.attrIds
    val queryAttrIds = elements.collect { case a: Attr => allAttrIds(a.name) }
    val dbCallBack   = (dbAfter: Database) => {
      val freshResult: List[Tpl] = DatomicQueryResolveOffset[Tpl](elements, limit, None)
        .getListFromOffset_sync(Some(dbAfter))(conn)._1
      callback(freshResult)
    }
    txReportWatcher.addSubscription(queryAttrIds, dbCallBack)
  }
}