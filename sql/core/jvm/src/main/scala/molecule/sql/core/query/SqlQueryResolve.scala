package molecule.sql.core.query

import molecule.boilerplate.ast.Model._
import molecule.core.query.Pagination
import molecule.core.util.ModelUtils
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.javaSql.{PrepStmt, PrepStmtImpl, ResultSetInterface}
import molecule.sql.core.query.casting.{CastNested, CastTuple, NestOptTpls, NestTpls}
import scala.collection.mutable.ListBuffer

abstract class SqlQueryResolve[Tpl](
  elements: List[Element],
  m2q: Model2SqlQuery[Tpl] with SqlQueryBase
) extends Pagination[Tpl] with ModelUtils {

  type RS = ResultSetInterface

  protected def getData(
    conn: JdbcConn_JVM,
    optLimit: Option[Int],
    optOffset: Option[Int],
  ): ResultSetInterface = {
    val query  = m2q.getSqlQuery(Nil, optLimit, optOffset, Some(conn.proxy))
    val inputs = m2q.inputs.toList
    getResultSet(conn, query, inputs)
  }

  protected def getTotalCount(conn: JdbcConn_JVM): Int = {
    val rs = getResultSet(conn, m2q.getTotalCountQuery)
    rs.next()
    rs.getInt(1)
  }

  private def getResultSet(
    conn: JdbcConn_JVM,
    query: String,
    inputs: List[PrepStmt => Unit] = Nil
  ): ResultSetInterface = {
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
    c: CastTuple, limit: Int, forward: Boolean, sortedRows: RS, conn: JdbcConn_JVM
  ): (List[Tpl], String, Boolean) = {
    val totalCount = getTotalCount(conn)
    val limitAbs   = limit.abs.min(totalCount)
    val hasMore    = limitAbs < totalCount
    val row2tpl    = c.tupleCaster
    val tuples     = ListBuffer.empty[Tpl]
    while (sortedRows.next()) {
      tuples += row2tpl(sortedRows).asInstanceOf[Tpl]
    }
    val result = if (forward) tuples.toList else tuples.toList.reverse
    val cursor = initialCursor(conn, elements, result)
    (result, cursor, hasMore)
  }


  protected def handleNested(
    c: CastNested, limit: Int, forward: Boolean, sortedRows: RS, conn: JdbcConn_JVM
  ): (List[Tpl], String, Boolean) = {
    val nestedRows    = if (m2q.isManNested) {
      // Nested
      (new NestTpls).rows2nested(
        sortedRows, c.getCasters
      ).asInstanceOf[List[Tpl]]
    } else {
      // OptNested
      (new NestOptTpls).rows2optNested(
        sortedRows, c.getCasters
      ).asInstanceOf[List[Tpl]]
    }
    val topLevelCount = nestedRows.length
    val limitAbs      = limit.abs.min(topLevelCount)
    val hasMore       = limitAbs < topLevelCount
    val selectedRows  = nestedRows.take(limitAbs)
    val tpls          = if (forward) selectedRows else selectedRows.reverse
    val cursor        = initialCursor(conn, elements, tpls)
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
      val List(_, dir, _, tpe, ns, attr, _, a, b, c, x, y, z) = attrTokens

      // Filter by most inclusive value
      val first   = List(c, b, a).filter(_.nonEmpty).head
      val last    = List(x, y, z).filter(_.nonEmpty).head
      val (fn, v) = (forward, dir) match {
        case (true, "a") => (Ge, last)
        case (true, _)   => (Le, first)
        case (_, "a")    => (Le, first)
        case (_, _)      => (Ge, last)
      }
      getFilterAttr(tpe, ns, attr, fn, v)
    }
    val altElements  = filterAttr +: elements
    val sortedRows   = getRawData(conn, altElements, None, None)
    val flatRowCount = m2q.getRowCount(sortedRows)

    if (flatRowCount == 0) {
      (Nil, "", false)
    } else {
      m2q.casts match {
        case c: CastTuple  => paginateTuples(c, limit, forward, allTokens, identifiers, identifyTpl, nextCursor, sortedRows, conn)
        case c: CastNested => paginateNested(c, limit, forward, allTokens, identifiers, identifyTpl, nextCursor, sortedRows)
        case _             => ???
      }
    }
  }

  protected def paginateTuples(
    c: CastTuple,
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
    val count      = getCount(limit, forward, totalCount)
    val row2tpl    = c.tupleCaster
    val allTuples  = ListBuffer.empty[Tpl]
    while (sortedRows.next()) {
      allTuples += row2tpl(sortedRows).asInstanceOf[Tpl]
    }
    val allTuples1     = if (forward) allTuples else allTuples.reverse
    val (tuples, more) = paginateTpls(count, allTuples1.result(), identifiers, identifyTpl)
    val rows           = if (forward) tuples else tuples.reverse
    val cursor         = nextCursor(rows, allTokens)
    (rows, cursor, more > 0)
  }


  protected def paginateNested(
    c: CastNested,
    limit: Int,
    forward: Boolean,
    allTokens: List[String],
    identifiers: List[Any],
    identifyTpl: Tpl => Any,
    nextCursor: (List[Tpl], List[String]) => String,
    sortedRows: RS,
  ): (List[Tpl], String, Boolean) = {
    val nestedTpls = if (m2q.isManNested) {
      // Nested
      (new NestTpls).rows2nested(
        sortedRows, c.getCasters
      ).asInstanceOf[List[Tpl]]
    } else {
      // OptNested
      (new NestOptTpls).rows2optNested(
        sortedRows, c.getCasters
      ).asInstanceOf[List[Tpl]]
    }
    val totalCount     = nestedTpls.length
    val count          = getCount(limit, forward, totalCount)
    val nestedTpls1    = if (forward) nestedTpls else nestedTpls.reverse
    val (tuples, more) = paginateTpls(count, nestedTpls1, identifiers, identifyTpl)
    val rows           = if (forward) tuples else tuples.reverse
    val cursor         = nextCursor(rows, allTokens)
    (rows, cursor, more > 0)
  }
}
