package molecule.db.common.query

import java.util.Base64
import molecule.core.dataModel.DataModel
import molecule.core.error.ModelError
import molecule.core.util.MoleculeLogging
import molecule.db.common.facade.JdbcConn_JVM
import molecule.db.common.ops.ModelTransformations_
import molecule.db.common.query.Pagination
import molecule.db.common.query.casting.strategy.{CastNested, CastOptRefs, CastTuple}
import molecule.db.common.query.cursorStrategy.{NoUnique, PrimaryUnique, SubUnique}
import molecule.db.common.util.FutureUtils

case class SqlQueryResolveCursor[Tpl](
  dataModel: DataModel,
  optLimit: Option[Int],
  cursor: Option[String],
  m2q: Model2SqlQuery & SqlQueryBase
) extends SqlQueryResolve[Tpl](dataModel, m2q)
  with FutureUtils
  with Pagination[Tpl]
  with ModelTransformations_
  with MoleculeLogging {


  def getListFromCursor_sync(using conn: JdbcConn_JVM)
  : (List[Tpl], String, Boolean) = {
    optLimit match {
      case Some(limit) => cursor match {
        case Some("")     => getInitialPage(limit)
        case Some(cursor) =>
          val raw      = new String(Base64.getDecoder.decode(cursor))
          val tokens   = raw.split("\n").toList
          val strategy = tokens.head
          val hash     = tokens(1)
          if ((dataModel.elements.hashCode() & 0xFFFFF) != hash.toInt) {
            throw ModelError("Can only use cursor for un-modified query.")
          } else {
            strategy match {
              case "1" => PrimaryUnique(dataModel, optLimit, cursor, m2q).getPage(tokens, limit)
              case "2" => SubUnique(dataModel, optLimit, cursor, m2q).getPage(tokens, limit)
              case "3" => NoUnique(dataModel, optLimit, cursor, m2q).getPage(tokens, limit)
            }
          }
        case None         => throw ModelError("Unexpected undefined cursor.")
      }
      case None        => throw ModelError("Please set limit to use cursor pagination.")
    }
  }

  private def getInitialPage(limit: Int)(using conn: JdbcConn_JVM)
  : (List[Tpl], String, Boolean) = {
    val forward      = limit > 0
    val altElements  = if (forward) dataModel.elements else reverseTopLevelSorting(dataModel.elements)
    val sortedRows   = getRawData(conn, altElements, Some(limit.abs), None)
    val flatRowCount = m2q.getRowCount(sortedRows)

    if (flatRowCount == 0) {
      (Nil, "", false)
    } else {
      m2q.castStrategy match {
        case c: CastTuple   => handleTuples(c, limit, forward, sortedRows, conn)
        case c: CastOptRefs => handleTuples(c, limit, forward, sortedRows, conn)
        case c: CastNested  => handleNested(c, limit, forward, sortedRows, conn)
        case other          => throw ModelError(
          "Un-allowed element for cursor pagination: " + other
        )
      }
    }
  }
}
