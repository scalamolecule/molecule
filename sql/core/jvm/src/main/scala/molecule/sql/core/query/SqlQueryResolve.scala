package molecule.sql.core.query

import java.sql.ResultSet
import molecule.boilerplate.ast.Model._
import molecule.core.query.Pagination
import molecule.core.util.ModelUtils
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.javaSql.{PrepStmt, PrepStmtImpl, ResultSetImpl}
import scala.collection.mutable.ListBuffer

abstract class SqlQueryResolve[Tpl](
  elements: List[Element],
  m2q: Model2SqlQuery[Tpl] with SqlQueryBase
) extends Pagination[Tpl] with ModelUtils {

  protected def getData(
    conn: JdbcConn_JVM,
    optLimit: Option[Int],
    optOffset: Option[Int],
  ): ResultSet = {
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
  ): ResultSet = {
    //    println("--- 1 ------------------")
    //    elements.foreach(println)
    //    println("---")
    //    println(query)
    val ps = conn.sqlConn.prepareStatement(
      query,
      ResultSet.TYPE_SCROLL_INSENSITIVE,
      ResultSet.CONCUR_READ_ONLY
    )
    // set input values corresponding to '?' in queries
    inputs.foreach(_(new PrepStmtImpl(ps)))
    ps.executeQuery()
  }


  protected def getRawData(
    conn: JdbcConn_JVM,
    altElements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): ResultSet = {
    val query = m2q.getSqlQuery(altElements, optLimit, optOffset, Some(conn.proxy))
    getResultSet(conn, query)
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
    val sortedRows1  = new ResultSetImpl(sortedRows)
    val flatRowCount = m2q.getRowCount(sortedRows1)

    if (flatRowCount == 0) {
      (Nil, "", false)
    } else {
      if (m2q.isNestedMan || m2q.isNestedOpt) {
        val nestedTpls     = if (m2q.isNestedMan) m2q.rows2nested(sortedRows1) else m2q.rows2nestedOpt(sortedRows1)
        val totalCount     = nestedTpls.length
        val count          = getCount(limit, forward, totalCount)
        val nestedTpls1    = if (forward) nestedTpls else nestedTpls.reverse
        val (tuples, more) = paginateTpls(count, nestedTpls1, identifiers, identifyTpl)
        val tuples1        = if (forward) tuples else tuples.reverse
        val cursor         = nextCursor(tuples1, allTokens)
        (tuples1, cursor, more > 0)

      } else {
        val totalCount = flatRowCount
        val count      = getCount(limit, forward, totalCount)
        val allTuples  = ListBuffer.empty[Tpl]
        val row2tpl    = m2q.castRow2AnyTpl(m2q.aritiess.head, m2q.castss.head, 1, None)
        while (sortedRows.next()) {
          allTuples += row2tpl(sortedRows1).asInstanceOf[Tpl]
        }
        val allTuples1     = if (forward) allTuples else allTuples.reverse
        val (tuples, more) = paginateTpls(count, allTuples1.result(), identifiers, identifyTpl)
        val tuples1        = if (forward) tuples else tuples.reverse
        val cursor         = nextCursor(tuples1, allTokens)
        (tuples1, cursor, more > 0)
      }
    }
  }
}
