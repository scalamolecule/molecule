package molecule.sql.jdbc.query

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.dbView.DbView
import molecule.core.util.FutureUtils
import molecule.sql.jdbc.facade.JdbcConn_jvm
import molecule.sql.jdbc.subscription.TxReportWatcher
import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}

/**
 * @param elements  Molecule model
 * @param optLimit  When going forward from start, use a positive number.
 *                  And vice versa from end with a negative number. Can't be zero.
 * @param optOffset Positive offset from start when going forwards,
 *                  negative offset from end when going backwards
 * @param dbView    Database with a time perspective (Datomic)
 * @tparam Tpl
 */
case class JdbcQueryResolveOffset[Tpl](
  elements: List[Element],
  optLimit: Option[Int],
  optOffset: Option[Int]
) extends JdbcQueryResolve[Tpl](elements, optLimit, optOffset)
  with FutureUtils
  with MoleculeLogging {

  lazy val isBackwards = optLimit.isDefined && optLimit.get < 0 || optOffset.isDefined && optOffset.get < 0


  def getListFromOffset_sync(implicit conn: JdbcConn_jvm)
  : (List[Tpl], Int, Boolean) = {
    lazy val limitSign  = optLimit.get >> 31
    lazy val offsetSign = optOffset.get >> 31
    if (optOffset.isDefined && optLimit.isDefined && limitSign != offsetSign) {
      throw ModelError("Limit and offset should both be positive or negative.")
    }
    val sortedRows: Row = getData(conn)
    //    println("sortedRows : " + sortedRows)
    //    println("total count: " + totalCount)

    if (isNested) {
      val totalCount    = getRowCount(sortedRows)
      val nestedRows0   = rows2nested(sortedRows)
      val nestedRows    = if (isBackwards) nestedRows0.reverse else nestedRows0
      val toplevelCount = nestedRows.length
      val fromUntil     = getFromUntil(toplevelCount, optLimit, optOffset)
      val hasMore       = fromUntil.fold(totalCount > 0)(_._3)
      (offsetList(nestedRows, fromUntil), toplevelCount, hasMore)

    } else if (isNestedOpt) {
      val totalCount    = optOffset.fold(getRowCount(sortedRows))(_ => getTotalCount(conn))
      val nestedRows0    = rows2nestedOpt(sortedRows)
      val nestedRows    = if (isBackwards) nestedRows0.reverse else nestedRows0
      val toplevelCount = nestedRows.length
      val fromUntil     = getFromUntil(toplevelCount, optLimit, optOffset)
      val hasMore       = fromUntil.fold(totalCount > 0)(_._3)
      (offsetList(nestedRows, fromUntil), toplevelCount, hasMore)

    } else {
      val totalCount = optOffset.fold(getRowCount(sortedRows))(_ => getTotalCount(conn))
      val row2tpl    = castRow2AnyTpl(aritiess.head, castss.head, 1, None)
      val tuples     = ListBuffer.empty[Tpl]
      while (sortedRows.next()) {
        tuples += row2tpl(sortedRows).asInstanceOf[Tpl]
      }
      val fromUntil = getFromUntil(totalCount, optLimit, optOffset)
      val hasMore   = fromUntil.fold(totalCount > 0)(_._3)
      val result    = if (isBackwards) tuples.result().reverse else tuples.result()
      (result, totalCount, hasMore)
    }
  }


  def subscribe(
    conn: JdbcConn_jvm,
    txReportWatcher: TxReportWatcher,
    callback: List[Tpl] => Unit
  ): Unit = {
    //    val allAttrIds   = conn.attrIds
    //    val queryAttrIds = elements.collect { case a: Attr => allAttrIds(a.name) }
    //    val dbCallBack   = (dbAfter: Database) => {
    //      val freshResult: List[Tpl] = SqlQueryResolveOffset[Tpl](elements, limit, None, None)
    //        .getListFromOffset_sync(Some(dbAfter))(conn)._1
    //      callback(freshResult)
    //    }
    //    txReportWatcher.addSubscription(queryAttrIds, dbCallBack)
    ???
  }
}
