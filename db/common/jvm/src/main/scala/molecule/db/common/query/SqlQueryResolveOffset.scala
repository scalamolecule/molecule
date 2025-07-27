package molecule.db.common.query

import molecule.base.error.ModelError
import molecule.core.dataModel.DataModel
import molecule.core.util.MoleculeLogging
import molecule.db.common.facade.JdbcConn_JVM
import molecule.db.common.query.casting.strategy.{CastNested, CastOptEntity, CastOptRefs, CastStrategy, CastTuple}
import molecule.db.common.query.casting.{NestOptTpls, NestTpls}
import molecule.db.common.util.{FutureUtils, ModelUtils}


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

  def getListFromOffset_sync(using conn: JdbcConn_JVM)
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
}
