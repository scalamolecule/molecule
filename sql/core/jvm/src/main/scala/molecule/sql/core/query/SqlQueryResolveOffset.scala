package molecule.sql.core.query

import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.util.{FutureUtils, ModelUtils}
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.query.castStrategy._
import molecule.sql.core.query.casting.CastTpl_
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

  def getListFromOffset_sync(implicit conn: JdbcConn_JVM)
  : (List[Tpl], Int, Boolean) = {
    offsetLimitCheck(optLimit, optOffset)
    val sortedRows = getData(conn, optLimit, optOffset)

    val castedRows = m2q.casts match {
      case c: CastTuple =>
        val row2tpl = c.tupleCaster
        val tuples  = ListBuffer.empty[Tpl]
        while (sortedRows.next()) {
          //          val tpl = row2tpl(sortedRows)
          //          println(tpl)
          //          tuples += tpl.asInstanceOf[Tpl]
          tuples += row2tpl(sortedRows).asInstanceOf[Tpl]
        }
        if (forward) tuples.toList else tuples.reverse.toList.reverse

      case c: CastNested => NestTpls2.rows2nested(
        sortedRows,
        c.getCasters
      ).asInstanceOf[List[Tpl]]

      case _ => ???
    }

    println("XXX   " + castedRows)


    if (m2q.isManNested || m2q.isOptNested) {
      val nestedRows0 = if (m2q.isManNested)
        m2q.rows2nested(sortedRows)
      else
        m2q.rows2optNnested(sortedRows)
      val nestedRows  = if (forward) nestedRows0 else nestedRows0.reverse


      val totalCount = if (m2q.isManNested)
        m2q.getRowCount(sortedRows)
      else
        optOffset.fold(m2q.getRowCount(sortedRows))(_ => getTotalCount(conn))

      val topLevelCount = nestedRows.length
      val fromUntil     = getFromUntil(topLevelCount, optLimit, optOffset)
      val hasMore       = fromUntil.fold(totalCount > 0)(_._3)
      (offsetList(nestedRows, fromUntil), topLevelCount, hasMore)

    } else if (m2q.hasOptRef) {

      //      val row2nestedOptions = m2q.row2nestedOptions
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


      val totalCount = optOffset.fold(m2q.getRowCount(sortedRows))(_ => getTotalCount(conn))
      val fromUntil  = getFromUntil(totalCount, optLimit, optOffset)
      val hasMore    = fromUntil.fold(totalCount > 0)(_._3)
      (tuples.toList, totalCount, hasMore)

      //      ???

    } else {
      val casts    = m2q.castss.head
      val row2tpl1 = CastTpl_.castTpl(m2q.aritiess.head, casts, 1)
      //      val row2tpl    = CastTpl2_.tupleCaster(m2q)

      //      val row2tpl    = m2q.casts.asInstanceOf[m2q.CastTuple].row2tpl
      //      val tuples     = ListBuffer.empty[Tpl]
      //      while (sortedRows.next()) {
      //
      //        val tpl = row2tpl(sortedRows)
      //        println(tpl)
      //        tuples += tpl.asInstanceOf[Tpl]
      //        //        tuples += row2tpl(sortedRows).asInstanceOf[Tpl]
      //      }
      //      val result    = if (forward) tuples.toList else tuples.toList.reverse
      val result = castedRows


      val totalCount = optOffset.fold(m2q.getRowCount(sortedRows))(_ => getTotalCount(conn))
      val fromUntil  = getFromUntil(totalCount, optLimit, optOffset)
      val hasMore    = fromUntil.fold(totalCount > 0)(_._3)
      (result, totalCount, hasMore)
    }
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
