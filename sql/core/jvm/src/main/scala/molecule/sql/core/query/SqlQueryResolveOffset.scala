package molecule.sql.core.query

import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.util.{FutureUtils, ModelUtils}
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.query.casting._
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


  def getListFromOffset_sync(implicit conn: JdbcConn_JVM)
  : (List[Tpl], Int, Boolean) = {
    offsetLimitCheck(optLimit, optOffset)
    val sortedRows = getData(conn, optLimit, optOffset)
    m2q.casts match {
      case c: CastTuple  => handleTuples(c, sortedRows, conn)
      case c: CastNested => handleNested(c, sortedRows, conn)
      case _             => ???
    }
  }
  /*
       val row2nestedOptions = m2q.row2nestedOptions

      //      val casts   = m2q.castss.head
      //      val row2tpl = new CastRow2Tpl_[List[Tpl]].cast(m2q.aritiess.head, casts, 1, None)
      val tuples = ListBuffer.empty[Tpl]
      while (sortedRows.next()) {

        val tpl = row2nestedOptions(sortedRows)
        println(tpl)
        tuples += tpl.asInstanceOf[Tpl]

        //        tuples += row2tpl(sortedRows).asInstanceOf[Tpl]
      }
   */

  private def handleTuples(
    c: CastTuple, sortedRows: RS, conn: JdbcConn_JVM
  ): (List[Tpl], Int, Boolean) = {
    val row2tpl = c.tupleCaster
    val tuples  = ListBuffer.empty[Tpl]
    while (sortedRows.next()) {
      tuples += row2tpl(sortedRows).asInstanceOf[Tpl]
    }
    val rows       = order(tuples.toList)
    val totalCount = optOffset.fold(m2q.getRowCount(sortedRows))(_ => getTotalCount(conn))
    val fromUntil  = getFromUntil(totalCount, optLimit, optOffset)
    val hasMore    = fromUntil.fold(totalCount > 0)(_._3)
    (rows, totalCount, hasMore)
  }


  private def handleNested(
    c: CastNested, sortedRows: RS, conn: JdbcConn_JVM
  ): (List[Tpl], Int, Boolean) = {
    val (nestedRows, totalCount) = if (m2q.isManNested) {
      // Nested
      val nestedRows = order((new NestTpls).rows2nested(
        sortedRows, c.getCasters
      ).asInstanceOf[List[Tpl]])
      (nestedRows, m2q.getRowCount(sortedRows))

    } else {
      // OptNested
      val nestedRows = order((new NestOptTpls).rows2optNested(
        sortedRows, c.getCasters
      ).asInstanceOf[List[Tpl]])
      (nestedRows, optOffset.fold(m2q.getRowCount(sortedRows))(_ => getTotalCount(conn)))
    }

    val topLevelCount = nestedRows.length
    val fromUntil     = getFromUntil(topLevelCount, optLimit, optOffset)
    val hasMore       = fromUntil.fold(totalCount > 0)(_._3)
    (offsetList(nestedRows, fromUntil), topLevelCount, hasMore)
  }

  private def order(rows: List[Tpl]) = {
    if (optLimit.fold(true)(_ >= 0) && optOffset.fold(true)(_ >= 0))
      rows else rows.reverse
  }


  def subscribe(
    conn: JdbcConn_JVM,
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

  def unsubscribe(conn: JdbcConn_JVM): Unit = {
    conn.removeCallback(elements)
  }
}
