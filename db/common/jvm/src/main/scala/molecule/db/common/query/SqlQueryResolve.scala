package molecule.db.common.query

import molecule.base.error.ModelError
import molecule.core.dataModel.*
import molecule.db.common.facade.JdbcConn_JVM
import molecule.db.common.javaSql.{PrepStmt, PrepStmtImpl, ResultSetInterface}
import molecule.db.common.query.Pagination
import molecule.db.common.query.casting.strategy.{CastNested, CastOptRefs, CastStrategy, CastTuple}
import molecule.db.common.query.casting.{NestOptTpls, NestTpls}
import molecule.db.common.util.ModelUtils
import scala.collection.mutable.ListBuffer


abstract class SqlQueryResolve[Tpl](
  dataModel: DataModel,
  m2q: Model2SqlQuery & SqlQueryBase
) extends Pagination[Tpl] with ModelUtils {

  type RS = ResultSetInterface

  private lazy val inputs: List[PrepStmt => Unit] = m2q.binders.toList


  protected def getData(
    conn: JdbcConn_JVM,
    optLimit: Option[Int],
    optOffset: Option[Int],
  ): ResultSetInterface = {
    val query  = m2q.getSqlQuery(Nil, optLimit, optOffset, Some(conn.proxy))
    getResultSet(conn, query)
  }

  protected def getTotalCount(conn: JdbcConn_JVM): Int = {
    val rs = getResultSet(conn, m2q.getTotalCountQuery)
    rs.next()
    rs.getInt(1)
  }

  private def getResultSet(conn: JdbcConn_JVM, query: String): ResultSetInterface = {
    val ps  = conn.queryStmt(query)
    val ps1 = new PrepStmtImpl(ps)
    // set input values corresponding to '?' in queries
    inputs.foreach(_(ps1))
    conn.resultSet(ps.executeQuery())
  }

  protected def getRawData(
    conn: JdbcConn_JVM,
    altElements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): ResultSetInterface = {
    val query = m2q.getSqlQuery(altElements, optLimit, optOffset, Some(conn.proxy))
    getResultSet(conn, query)
  }

  protected def handleTuples(
    c: CastStrategy, limit: Int, forward: Boolean, sortedRows: RS, conn: JdbcConn_JVM
  ): (List[Tpl], String, Boolean) = {
    val totalCount = getTotalCount(conn)
    val limitAbs   = limit.abs.min(totalCount)
    val hasMore    = limitAbs < totalCount
    val tpls       = castTuples(c.rs2row, sortedRows, forward)
    val cursor     = initialCursor(conn, dataModel.elements, tpls)
    (tpls, cursor, hasMore)
  }

  protected def castTuples(rs2row: RS => Any, sortedRows: RS, forward: Boolean): List[Tpl] = {
    val tuples = ListBuffer.empty[Tpl]
    while (sortedRows.next()) {
      tuples += rs2row(sortedRows).asInstanceOf[Tpl]
    }
    if (forward) tuples.toList else tuples.toList.reverse
  }

  protected def handleNested(
    c: CastNested, limit: Int, forward: Boolean, sortedRows: RS, conn: JdbcConn_JVM
  ): (List[Tpl], String, Boolean) = {
    val (nestedTpls, topLevelCount) = getNestedTpls(sortedRows, c)
    val limitAbs                    = limit.abs.min(topLevelCount)
    val hasMore                     = limitAbs < topLevelCount
    val selectedRows                = nestedTpls.take(limitAbs)
    val tpls                        = if (forward) selectedRows else selectedRows.reverse
    val cursor                      = initialCursor(conn, dataModel.elements, tpls)
    (tpls, cursor, hasMore)
  }

  def paginateFromIdentifiers(
    conn: JdbcConn_JVM,
    limit: Int,
    forward: Boolean,
    allTokens: List[String],
    attrTokens: List[String],
    identifiers: List[Any],
    identifyTpl: Tpl => Any,
    nextCursor: (List[Tpl], List[String]) => String
  ): (List[Tpl], String, Boolean) = {
    // Filter query by primary non-unique sort attribute
    val filterAttr   = {
      val List(_, dir, _, tpe, ent, attr, _, a, b, c, x, y, z) = attrTokens

      // Filter by most inclusive value
      val first   = List(c, b, a).filter(_.nonEmpty).head
      val last    = List(x, y, z).filter(_.nonEmpty).head
      val (fn, v) = (forward, dir) match {
        case (true, "a") => (Ge, last)
        case (true, _)   => (Le, first)
        case (_, "a")    => (Le, first)
        case (_, _)      => (Ge, last)
      }
      getFilterAttr(tpe, ent, attr, fn, v)
    }
    val altElements  = filterAttr +: dataModel.elements
    val sortedRows   = getRawData(conn, altElements, None, None)
    val flatRowCount = m2q.getRowCount(sortedRows)

    if (flatRowCount == 0) {
      (Nil, "", false)
    } else {
      m2q.castStrategy match {
        case c: CastTuple   => paginateTuples(c, limit, forward, allTokens, identifiers, identifyTpl, nextCursor, sortedRows, conn)
        case c: CastOptRefs => paginateTuples(c, limit, forward, allTokens, identifiers, identifyTpl, nextCursor, sortedRows, conn)
        case c: CastNested  => paginateNested(c, limit, forward, allTokens, identifiers, identifyTpl, nextCursor, sortedRows)
        case other          => throw ModelError(
          "Un-allowed element for cursor pagination: " + other
        )
      }
    }
  }

  private def paginateTuples(
    c: CastStrategy,
    limit: Int,
    forward: Boolean,
    allTokens: List[String],
    identifiers: List[Any],
    identifyTpl: Tpl => Any,
    nextCursor: (List[Tpl], List[String]) => String,
    sortedRows: RS,
    conn: JdbcConn_JVM
  ): (List[Tpl], String, Boolean) = {
    val totalCount = getTotalCount(conn)
    val nestedTpls = castTuples(c.rs2row, sortedRows, forward)
    paginatedResult(limit, forward, allTokens, identifiers, identifyTpl, nextCursor, nestedTpls, totalCount)

  }

  private def paginateNested(
    c: CastNested,
    limit: Int,
    forward: Boolean,
    allTokens: List[String],
    identifiers: List[Any],
    identifyTpl: Tpl => Any,
    nextCursor: (List[Tpl], List[String]) => String,
    sortedRows: RS,
  ): (List[Tpl], String, Boolean) = {
    val (nestedTpls0, totalCount) = getNestedTpls(sortedRows, c)
    val nestedTpls                = if (forward) nestedTpls0 else nestedTpls0.reverse
    paginatedResult(limit, forward, allTokens, identifiers, identifyTpl, nextCursor, nestedTpls, totalCount)
  }

  private def paginatedResult(
    limit: Int,
    forward: Boolean,
    allTokens: List[String],
    identifiers: List[Any],
    identifyTpl: Tpl => Any,
    nextCursor: (List[Tpl], List[String]) => String,
    nestedTpls1: List[Tpl],
    totalCount: Int
  ): (List[Tpl], String, Boolean) = {
    val count         = getCount(limit, forward, totalCount)
    val (tpls0, more) = paginateTpls(count, nestedTpls1, identifiers, identifyTpl)
    val tpls          = if (forward) tpls0 else tpls0.reverse
    val cursor        = nextCursor(tpls, allTokens)
    (tpls, cursor, more > 0)
  }

  private def getNestedTpls(sortedRows: RS, c: CastNested): (List[Tpl], Int) = {
    val nestedTpls = if (m2q.isManNested) {
      // Nested
      (new NestTpls).rows2nested(
        sortedRows, c.tupleCasters
      ).asInstanceOf[List[Tpl]]
    } else {
      // OptNested
      (new NestOptTpls).rows2optNested(
        sortedRows, c.tupleCasters
      ).asInstanceOf[List[Tpl]]
    }
    (nestedTpls, nestedTpls.length)
  }
}
