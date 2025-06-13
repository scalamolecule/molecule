package molecule.db.sql.core.query.cursorStrategy

import molecule.base.error.ModelError
import molecule.core.dataModel.DataModel
import molecule.core.util.MoleculeLogging
import molecule.db.core.query.Pagination
import molecule.db.core.util.FutureUtils
import molecule.db.sql.core.facade.JdbcConn_JVM
import molecule.db.sql.core.query.{Model2SqlQuery, SqlQueryBase, SqlQueryResolve}

/**
 * Molecule has a unique attribute that is not sorted first.
 *
 * We filter by the previous value of the primary non-unique attribute and then
 * loop until the previous value of the unique attribute is found and then take
 * the following rows.
 *
 * This is of course not optimal, especially if the primary standard sort
 * attribute contains few values and the data set is big.
 * *
 * Presumes that the row with the previous unique value hasn't been altered.
 *
 * @param elements Molecule model
 * @param optLimit When going forward from start, use a positive number.
 *                 And vice versa from end with a negative number. Can't be zero.
 * @param cursor   Base64 encoded cursor meta information, including previous edge values.
 * @tparam Tpl Type of each row
 */
case class SubUnique[Tpl](
  dataModel: DataModel,
  optLimit: Option[Int],
  cursor: String,
  m2q: Model2SqlQuery & SqlQueryBase
) extends SqlQueryResolve[Tpl](dataModel, m2q)
  with FutureUtils with Pagination[Tpl] with MoleculeLogging {


  def getPage(allTokens: List[String], limit: Int)
             (implicit conn: JdbcConn_JVM)
  : (List[Tpl], String, Boolean) = try {
    val forward     = limit > 0
    val attrsTokens = allTokens.drop(2).grouped(13).toList.sortBy(_(2))

    val (uniqueIndex, uniqueValues) = {
      val List(_, _, _, tpe, _, _, i, a, b, c, x, y, z) =
        attrsTokens.find(_.head == "UNIQUE").get

      val uniqueValues = (if (forward)
        List(z, y, x)
      else
        List(a, b, c)).filter(_.nonEmpty).map(decoder(tpe))
      (i.toInt, uniqueValues)
    }

    val identifyTpl = (tpl: Tpl) =>
      tpl.asInstanceOf[Product].productElement(uniqueIndex)

    paginateFromIdentifiers(
      conn,
      limit,
      forward,
      allTokens,
      attrsTokens.head,
      uniqueValues,
      identifyTpl,
      nextCursorSubUnique
    )
  } catch {
    case t: Throwable => throw ModelError(t.toString)
  }
}
