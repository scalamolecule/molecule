package molecule.sql.core.query.cursorStrategy

import java.util.Base64
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.ops.ModelTransformations_
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.util.FutureUtils
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.javaSql.ResultSetImpl
import molecule.sql.core.query.{CursorUtils, JdbcQueryResolve, Model2SqlQuery, SqlQueryBase}
import scala.collection.mutable.ListBuffer

/**
 * Molecule has a unique attribute that is sorted first.
 *
 * Then we can easily filter by its previous value in either direction.
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
  m2q: Model2SqlQuery[Tpl] with SqlQueryBase
) extends JdbcQueryResolve[Tpl](elements, m2q)
  with FutureUtils with CursorUtils with ModelTransformations_ with MoleculeLogging {

  def getPage(tokens: List[String], limit: Int)
             (implicit conn: JdbcConn_JVM): (List[Tpl], String, Boolean) = try {
    val List(_, _, tpe, ns, attr, _, a, z) = tokens

    val forward      = limit > 0
    val (fn, v)      = if (forward) (Gt, z) else (Lt, a)
    val filterAttr   = getFilterAttr(tpe, ns, attr, fn, v)
    val altElements  = filterAttr +: (if (forward) elements else reverseTopLevelSorting(elements))
    val sortedRows   = getRawData(conn, altElements, Some(limit.abs), None)
    val sortedRows1  = new ResultSetImpl(sortedRows)
    val flatRowCount = m2q.getRowCount(sortedRows1)

    if (flatRowCount == 0) {
      (Nil, "", false)
    } else {
      if (m2q.isNested || m2q.isNestedOpt) {
        val nestedRows    = if (m2q.isNested) m2q.rows2nested(sortedRows1) else m2q.rows2nestedOpt(sortedRows1)
        val topLevelCount = nestedRows.length
        val limitAbs      = limit.abs.min(topLevelCount)
        val hasMore       = limitAbs < topLevelCount
        val selectedRows  = nestedRows.take(limitAbs)
        val result        = if (forward) selectedRows else selectedRows.reverse
        val cursor        = nextCursorUniques(result, tokens)
        (result, cursor, hasMore)

      } else {
        val totalCount = getTotalCount(conn)
        val limitAbs   = limit.abs.min(totalCount)
        val hasMore    = limitAbs < totalCount
        val tuples     = ListBuffer.empty[Tpl]
        val row2tpl    = m2q.castRow2AnyTpl(m2q.aritiess.head, m2q.castss.head, 1, None)
        while (sortedRows.next()) {
          tuples += row2tpl(sortedRows1).asInstanceOf[Tpl]
        }
        val result = if (forward) tuples.result() else tuples.result().reverse
        val cursor = nextCursorUniques(result, tokens)
        (result, cursor, hasMore)
      }
    }
  } catch {
    case t: Throwable => throw ModelError(t.toString)
  }


  private def nextCursorUniques(tpls: List[Tpl], tokens: List[String]): String = {
    val List(_, _, tpe, _, _, i, _, _) = tokens
    val tokens1                        = tokens.dropRight(2) ++ getUniquePair(tpls, i.toInt, encoder(tpe, ""))
    Base64.getEncoder.encodeToString(tokens1.mkString("\n").getBytes)
  }
}
