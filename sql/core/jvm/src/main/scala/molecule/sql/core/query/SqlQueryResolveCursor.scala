package molecule.sql.core.query

import java.util.Base64
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.ops.ModelTransformations_
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.query.Pagination
import molecule.core.util.FutureUtils
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.query.casting.strategy._
import molecule.sql.core.query.cursorStrategy.{NoUnique, PrimaryUnique, SubUnique}

case class SqlQueryResolveCursor[Tpl](
  elements: List[Element],
  optLimit: Option[Int],
  cursor: Option[String],
  m2q: Model2SqlQuery with SqlQueryBase
) extends SqlQueryResolve[Tpl](elements, m2q)
  with FutureUtils
  with Pagination[Tpl]
  with ModelTransformations_
  with MoleculeLogging {


  def getListFromCursor_sync(implicit conn: JdbcConn_JVM)
  : (List[Tpl], String, Boolean) = {
    optLimit match {
      case Some(limit) => cursor match {
        case Some("")     => getInitialPage(limit)
        case Some(cursor) =>
          val raw      = new String(Base64.getDecoder.decode(cursor))
          val tokens   = raw.split("\n").toList
          val strategy = tokens.head
          val hash     = tokens(1)
          if ((elements.hashCode() & 0xFFFFF) != hash.toInt) {
            throw ModelError("Can only use cursor for un-modified query.")
          } else {
            strategy match {
              case "1" => PrimaryUnique(elements, optLimit, cursor, m2q).getPage(tokens, limit)
              case "2" => SubUnique(elements, optLimit, cursor, m2q).getPage(tokens, limit)
              case "3" => NoUnique(elements, optLimit, cursor, m2q).getPage(tokens, limit)
            }
          }
        case None         => throw ModelError("Unexpected undefined cursor.")
      }
      case None        => throw ModelError("Please set limit to use cursor pagination.")
    }
  }

  private def getInitialPage(limit: Int)(implicit conn: JdbcConn_JVM)
  : (List[Tpl], String, Boolean) = {
    val forward      = limit > 0
    val altElements  = if (forward) elements else reverseTopLevelSorting(elements)
    val sortedRows   = getRawData(conn, altElements, Some(limit.abs), None)
    val flatRowCount = m2q.getRowCount(sortedRows)

    if (flatRowCount == 0) {
      (Nil, "", false)
    } else {
      m2q.castStrategy match {
        case c: CastTuple   => handleTuples(c, limit, forward, sortedRows, conn)
        case c: CastOptRefs => handleTuples(c, limit, forward, sortedRows, conn)
        case c: CastNested  => handleNested(c, limit, forward, sortedRows, conn)
        case _                   => ???
      }
    }
  }
}
