package molecule.sql.jdbc.query.cursorStrategy

import java.util.Base64
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.ops.ModelTransformations_
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.util.FutureUtils
import molecule.sql.core.query.cursor.CursorUtils
import molecule.sql.jdbc.facade.JdbcConn_jvm
import molecule.sql.jdbc.query.JdbcQueryResolve
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
  cursor: String
) extends JdbcQueryResolve[Tpl](elements)
  with FutureUtils with CursorUtils with ModelTransformations_ with MoleculeLogging {

  def getPage(tokens: List[String], limit: Int)
             (implicit conn: JdbcConn_jvm): (List[Tpl], String, Boolean) = try {
    val List(_, _, tpe, ns, attr, _, a, z) = tokens

    val forward      = limit > 0
    val (fn, v)      = if (forward) (Gt, z) else (Lt, a)
    val filterAttr   = getFilterAttr(tpe, ns, attr, fn, v)
    val altElements  = filterAttr +: (if (forward) elements else reverseTopLevelSorting(elements))
    val sortedRows   = getRawData(conn, altElements, Some(limit.abs), None)
    val flatRowCount = getRowCount(sortedRows)

    if (flatRowCount == 0) {
      (Nil, "", false)
    } else {
      if (isNested || isNestedOpt) {
        val nestedRows    = if (isNested) rows2nested(sortedRows) else rows2nestedOpt(sortedRows)
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
        val row2tpl    = castRow2AnyTpl(aritiess.head, castss.head, 1, None)
        while (sortedRows.next()) {
          tuples += row2tpl(sortedRows).asInstanceOf[Tpl]
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
