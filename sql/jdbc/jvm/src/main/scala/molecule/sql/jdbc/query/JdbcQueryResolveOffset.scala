package molecule.sql.jdbc.query

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.util.{FutureUtils, ModelUtils}
import molecule.sql.core.javaSql.ResultSetImpl
import molecule.sql.jdbc.facade.JdbcConn_JVM
import scala.collection.mutable.ListBuffer

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
) extends JdbcQueryResolve[Tpl](elements)
  with FutureUtils
  with ModelUtils
  with MoleculeLogging {

  lazy val forward = optLimit.fold(true)(_ >= 0) && optOffset.fold(true)(_ >= 0)

  def getListFromOffset_sync(implicit conn: JdbcConn_JVM)
  : (List[Tpl], Int, Boolean) = {
    lazy val limitSign  = optLimit.get >> 31
    lazy val offsetSign = optOffset.get >> 31
    if (optOffset.isDefined && optLimit.isDefined && limitSign != offsetSign) {
      throw ModelError("Limit and offset should both be positive or negative.")
    }
    val sortedRows = getData(conn, optLimit, optOffset)
    val sortedRows1  = new ResultSetImpl(sortedRows)
    if (isNested || isNestedOpt) {
      val totalCount    = if (isNested) getRowCount(sortedRows1) else
        optOffset.fold(getRowCount(sortedRows1))(_ => getTotalCount(conn))
      val nestedRows0   = if (isNested) rows2nested(sortedRows1) else rows2nestedOpt(sortedRows1)
      val nestedRows    = if (forward) nestedRows0 else nestedRows0.reverse
      val topLevelCount = nestedRows.length
      val fromUntil     = getFromUntil(topLevelCount, optLimit, optOffset)
      val hasMore       = fromUntil.fold(totalCount > 0)(_._3)
      (offsetList(nestedRows, fromUntil), topLevelCount, hasMore)

    } else {
      val totalCount = optOffset.fold(getRowCount(sortedRows1))(_ => getTotalCount(conn))
      val row2tpl    = castRow2AnyTpl(aritiess.head, castss.head, 1, None)
      val tuples     = ListBuffer.empty[Tpl]
      while (sortedRows.next()) {
        tuples += row2tpl(sortedRows1).asInstanceOf[Tpl]
      }
      val fromUntil = getFromUntil(totalCount, optLimit, optOffset)
      val hasMore   = fromUntil.fold(totalCount > 0)(_._3)
      val result    = if (forward) tuples.result() else tuples.result().reverse
      (result, totalCount, hasMore)
    }
  }


  def subscribe(
    conn: JdbcConn_JVM,
    callback: List[Tpl] => Unit
  ): Unit = {
    val involvedAttrs    = getAttrNames(elements)
    val involvedDeleteNs = getInitialNs(elements)
    val maybeCallback    = (mutationAttrs: Set[String], isDelete: Boolean) => {
      if (
        mutationAttrs.exists(involvedAttrs.contains) ||
          isDelete && mutationAttrs.head.startsWith(involvedDeleteNs)
      ) {
        callback(
          JdbcQueryResolveOffset(elements, optLimit, None)
            .getListFromOffset_sync(conn)._1
        )
      }
    }
    conn.addCallback(elements -> maybeCallback)
  }

  def unsubscribe(conn: JdbcConn_JVM): Unit = {
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
