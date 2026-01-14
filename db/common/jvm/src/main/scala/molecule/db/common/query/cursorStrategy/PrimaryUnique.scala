package molecule.db.common.query.cursorStrategy

import molecule.core.dataModel.*
import molecule.core.error.ModelError
import molecule.core.util.MoleculeLogging
import molecule.db.common.facade.JdbcConn_JVM
import molecule.db.common.ops.ModelTransformations_
import molecule.db.common.query.casting.strategy.{CastNested, CastOptRefs, CastTuple}
import molecule.db.common.query.{Model2SqlQuery, Pagination, SqlQueryBase, SqlQueryResolve}
import molecule.db.common.util.FutureUtils

/**
 * Molecule has a unique attribute that is sorted first.
 *
 * Then we can easily filter by its previous value in either direction
 * to skip previously shown data.
 *
 * @param elements Molecule model
 * @param optLimit Optional positive row offset number
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
             (using conn: JdbcConn_JVM): (List[Tpl], String, Boolean) = {
    val List(_, _, tpe, ent, attr, _, a, z) = tokens

    val (fn, v)      = (Gt, z)
    val filterAttr   = getFilterAttr(tpe, ent, attr, fn, v)
    val altElements  = filterAttr +: dataModel.elements
    val sortedRows   = getRawData(conn, altElements, Some(limit.abs), None)
    val flatRowCount = m2q.getRowCount(sortedRows)

    if (flatRowCount == 0) {
      (Nil, "", false)
    } else {
      m2q.castStrategy match {
        case c: CastTuple   => handleTuples(c, limit, sortedRows, conn)
        case c: CastOptRefs => handleTuples(c, limit, sortedRows, conn)
        case c: CastNested  => handleNested(c, limit, sortedRows, conn)
        case other          => throw ModelError(
          "Un-allowed element for primary unique cursor pagination: " + other
        )
      }
    }
  }
}
