package molecule.db.sql.core.query

import molecule.db.base.error.ModelError
import molecule.db.core.ast.{DataModel, Element}
import molecule.db.core.util.Executor.global
import molecule.db.core.util.{FutureUtils, ModelUtils, MoleculeLogging}
import molecule.db.sql.core.facade.JdbcConn_JVM
import molecule.db.sql.core.query.casting.strategy.*
import molecule.db.sql.core.query.casting.{NestOptTpls, NestTpls}
import scala.concurrent.Future


case class SqlQueryResolveOffset[Tpl](
  dataModel: DataModel,
  optLimit: Option[Int],
  optOffset: Option[Int],
  m2q: Model2SqlQuery & SqlQueryBase
) extends SqlQueryResolve[Tpl](dataModel, m2q)
  with FutureUtils
  with ModelUtils
  with MoleculeLogging {

  lazy val forward = optLimit.fold(true)(_ >= 0) && optOffset.fold(true)(_ >= 0)

  def getListFromOffset_sync(implicit conn: JdbcConn_JVM)
  : (List[Tpl], Int, Boolean) = {
    offsetLimitCheck(optLimit, optOffset)
    val sortedRows = getData(conn, optLimit, optOffset)
    m2q.castStrategy match {
      case c: CastTuple     => handleTuples(c, sortedRows, conn)
      case c: CastOptRefs   => handleTuples(c, sortedRows, conn)
      case c: CastOptEntity => handleTuples(c, sortedRows, conn)
      case c: CastNested    => handleNested(c, sortedRows, conn)
      case other            => throw ModelError(
        "Un-allowed element for offset pagination: " + other
      )
    }
  }


  private def handleTuples(
    c: CastStrategy, sortedRows: RS, conn: JdbcConn_JVM
  ): (List[Tpl], Int, Boolean) = {
    val tpls       = castTuples(c.rs2row, sortedRows, forward)
    val totalCount = optOffset.fold(
      m2q.getRowCount(sortedRows)
    )(_ => getTotalCount(conn))
    val fromUntil  = getFromUntil(totalCount, optLimit, optOffset)
    val hasMore    = fromUntil.fold(totalCount > 0)(_._3)
    (tpls, totalCount, hasMore)
  }


  private def handleNested(
    c: CastNested, sortedRows: RS, conn: JdbcConn_JVM
  ): (List[Tpl], Int, Boolean) = {
    val (nestedRows, totalCount) = if (m2q.isManNested) {
      // Nested
      val nestedRows = order((new NestTpls).rows2nested(
        sortedRows, c.tupleCasters
      ).asInstanceOf[List[Tpl]])
      (nestedRows, m2q.getRowCount(sortedRows))

    } else {
      // OptNested
      val nestedRows = order((new NestOptTpls).rows2optNested(
        sortedRows, c.tupleCasters
      ).asInstanceOf[List[Tpl]])
      (nestedRows, optOffset.fold(m2q.getRowCount(sortedRows))(_ => getTotalCount(conn)))
    }

    val topLevelCount = nestedRows.length
    val fromUntil     = getFromUntil(topLevelCount, optLimit, optOffset)
    val hasMore       = fromUntil.fold(totalCount > 0)(_._3)
    (offsetList(nestedRows, fromUntil), topLevelCount, hasMore)
  }


  private def order(rows: List[Tpl]): List[Tpl] = {
    if (forward) rows else rows.reverse
  }


  def subscribe(
    conn: JdbcConn_JVM,
    callback: List[Tpl] => Unit,
    freshM2q: List[Element] => Model2SqlQuery & SqlQueryBase
  ): Unit = {
    val involvedAttrs        = getAttrNames(dataModel.elements)
    val involvedDeleteEntity = getInitialEntity(dataModel.elements)
    val maybeCallback        = (mutationAttrs: Set[String], isDelete: Boolean) => {

      println("involvedAttrs: " + involvedAttrs)
      println("mutationAttrs: " + mutationAttrs)

      if (
        mutationAttrs.exists(involvedAttrs.contains) ||
          isDelete && mutationAttrs.head.startsWith(involvedDeleteEntity)
      ) {
        println(s"  $mutationAttrs triggering callback")
        Future(
          callback {
            SqlQueryResolveOffset(dataModel, optLimit, None, freshM2q(dataModel.elements))
              .getListFromOffset_sync(conn)._1
          }
        )
      } else Future.unit
    }
    conn.addCallback((dataModel, maybeCallback))
  }
}
