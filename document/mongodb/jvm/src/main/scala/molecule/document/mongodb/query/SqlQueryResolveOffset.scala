package molecule.document.mongodb.query

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.util.{FutureUtils, ModelUtils}
import molecule.document.mongodb.facade.MongoDBConn_JVM
import molecule.document.mongodb.javaSql.ResultSetImpl
import scala.collection.mutable.ListBuffer

case class SqlQueryResolveOffset[Tpl](
  elements: List[Element],
  optLimit: Option[Int],
  optOffset: Option[Int],
  m2q: Model2SqlQuery[Tpl] with SqlQueryBase
) extends SqlQueryResolve[Tpl](elements, m2q)
  with FutureUtils
  with ModelUtils
  with MoleculeLogging {

  lazy val forward = optLimit.fold(true)(_ >= 0) && optOffset.fold(true)(_ >= 0)

  def getListFromOffset_sync(implicit conn: MongoDBConn_JVM)
  : (List[Tpl], Int, Boolean) = {
    lazy val limitSign  = optLimit.get >> 31
    lazy val offsetSign = optOffset.get >> 31
    if (optOffset.isDefined && optLimit.isDefined && limitSign != offsetSign) {
      throw ModelError("Limit and offset should both be positive or negative.")
    }
    val sortedRows  = getData(conn, optLimit, optOffset)
    val sortedRows1 = new ResultSetImpl(sortedRows)
    if (m2q.isNested || m2q.isNestedOpt) {
      val totalCount    = if (m2q.isNested) m2q.getRowCount(sortedRows1) else
        optOffset.fold(m2q.getRowCount(sortedRows1))(_ => getTotalCount(conn))
      val nestedRows0   = if (m2q.isNested)
        m2q.rows2nested(sortedRows1)
      else
        m2q.rows2nestedOpt(sortedRows1)
      val nestedRows    = if (forward) nestedRows0 else nestedRows0.reverse
      val topLevelCount = nestedRows.length
      val fromUntil     = getFromUntil(topLevelCount, optLimit, optOffset)
      val hasMore       = fromUntil.fold(totalCount > 0)(_._3)
      (offsetList(nestedRows, fromUntil), topLevelCount, hasMore)

    } else {
      val totalCount = optOffset.fold(m2q.getRowCount(sortedRows1))(_ => getTotalCount(conn))
      val casts      = m2q.castss.head
      val row2tpl    = m2q.castRow2AnyTpl(m2q.aritiess.head, casts, 1, None)
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
    conn: MongoDBConn_JVM,
    callback: List[Tpl] => Unit,
    freshM2q: List[Element] => Model2SqlQuery[Tpl] with SqlQueryBase
  ): Unit = {
    val involvedAttrs    = getAttrNames(elements)
    val involvedDeleteNs = getInitialNs(elements)
    val maybeCallback    = (mutationAttrs: Set[String], isDelete: Boolean) => {
      if (
        mutationAttrs.exists(involvedAttrs.contains) ||
          isDelete && mutationAttrs.head.startsWith(involvedDeleteNs)
      ) {
        callback(
          SqlQueryResolveOffset(elements, optLimit, None, freshM2q(elements))
            .getListFromOffset_sync(conn)._1
        )
      }
    }
    conn.addCallback(elements -> maybeCallback)
  }

  def unsubscribe(conn: MongoDBConn_JVM): Unit = {
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
