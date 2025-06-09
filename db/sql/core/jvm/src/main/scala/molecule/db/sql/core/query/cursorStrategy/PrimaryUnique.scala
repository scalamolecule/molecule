package molecule.db.sql.core.query.cursorStrategy

import molecule.base.error.ModelError
import molecule.core.ast.*
import molecule.core.util.MoleculeLogging
import molecule.db.core.ops.ModelTransformations_
import molecule.db.core.query.Pagination
import molecule.db.core.util.FutureUtils
import molecule.db.sql.core.facade.JdbcConn_JVM
import molecule.db.sql.core.query.casting.strategy.{CastNested, CastOptRefs, CastTuple}
import molecule.db.sql.core.query.{Model2SqlQuery, SqlQueryBase, SqlQueryResolve}

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
  dataModel: DataModel,
  optLimit: Option[Int],
  cursor: String,
  m2q: Model2SqlQuery & SqlQueryBase
) extends SqlQueryResolve[Tpl](dataModel, m2q)
  with FutureUtils with Pagination[Tpl] with ModelTransformations_ with MoleculeLogging {


  def getPage(tokens: List[String], limit: Int)
             (implicit conn: JdbcConn_JVM): (List[Tpl], String, Boolean) = {
    val List(_, _, tpe, ent, attr, _, a, z) = tokens

    val forward      = limit > 0
    val (fn, v)      = if (forward) (Gt, z) else (Lt, a)
    val filterAttr   = getFilterAttr(tpe, ent, attr, fn, v)
    val altElements  = filterAttr +: (if (forward) dataModel.elements else reverseTopLevelSorting(dataModel.elements))
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
