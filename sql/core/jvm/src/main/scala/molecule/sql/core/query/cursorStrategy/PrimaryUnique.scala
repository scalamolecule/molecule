package molecule.sql.core.query.cursorStrategy

import molecule.base.error.ModelError
import molecule.core.ast.DataModel._
import molecule.core.ops.ModelTransformations_
import molecule.core.query.Pagination
import molecule.core.util.{FutureUtils, MoleculeLogging}
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.query.casting.strategy.{CastNested, CastOptRefs, CastTuple}
import molecule.sql.core.query.{Model2SqlQuery, SqlQueryBase, SqlQueryResolve}

/**
 * Molecule has a unique attribute that is sorted first.
 *
 * Then we can easily filter by its previous value in either direction
 * to skip previously shown data.
 *
 * @param elements Molecule model
 * @param optLimit When going forward from start, use a positive number.
 *                 And vice versa from end with a negative number. Can't be zero.
 * @param cursor   Base64 encoded cursor meta information
 * @tparam Tpl Type of each row
 */
case class PrimaryUnique[Tpl](
  elements: List[Element],
  optLimit: Option[Int],
  cursor: String,
  m2q: Model2SqlQuery with SqlQueryBase
) extends SqlQueryResolve[Tpl](elements, m2q)
  with FutureUtils with Pagination[Tpl] with ModelTransformations_ with MoleculeLogging {


  def getPage(tokens: List[String], limit: Int)
             (implicit conn: JdbcConn_JVM): (List[Tpl], String, Boolean) = {
    val List(_, _, tpe, ent, attr, _, a, z) = tokens

    val forward      = limit > 0
    val (fn, v)      = if (forward) (Gt, z) else (Lt, a)
    val filterAttr   = getFilterAttr(tpe, ent, attr, fn, v)
    val altElements  = filterAttr +: (if (forward) elements else reverseTopLevelSorting(elements))
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
          "Un-allowed element for primary unique cursor pagination: " + other
        )
      }
    }
  }
}
