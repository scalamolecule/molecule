package molecule.db.datomic.query.cursor

import java.util.Base64
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.api.action.ApiUtils
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.query.DatomicQuery
import scala.concurrent.{ExecutionContext, Future}

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
 * @param limit    When going forward from start, use a positive number.
 *                 And vice versa from end with a negative number. Can't be zero.
 * @param cursor   Base64 encoded cursor meta information, including previous edge values.
 * @tparam Tpl Type of each row
 */
case class SubUnique[Tpl](
  elements: List[Element],
  limit: Option[Int],
  cursor: String
) extends DatomicQuery[Tpl](elements, limit)
  with ApiUtils with CursorUtils with MoleculeLogging {

  def getPage(allTokens: List[String], limit: Int)
             (implicit conn: DatomicConn_JVM, ec: ExecutionContext)
  : Future[(List[Tpl], String, Boolean)] = future {
    val forward     = limit > 0
    val attrsTokens = allTokens.drop(2).grouped(13).toList.sortBy(_(2))

    val (uniqueIndex, uniqueValues) = {
      val List(_, _, _, tpe, _, _, i, a, b, c, x, y, z) = attrsTokens.find(_.head == "UNIQUE").get

      val uniqueValues = (if (forward) List(z, y, x) else List(a, b, c)).filter(_.nonEmpty).map(decoder(tpe))
      (i.toInt, uniqueValues)
    }

    val identifyTpl = (tpl: Tpl) => tpl.asInstanceOf[Product].productElement(uniqueIndex)
    val identifyRow = (_: Boolean) => (row: Row) => row.get(uniqueIndex)

    paginateFromIdentifiers(
      conn,
      limit,
      forward,
      allTokens,
      attrsTokens.head,
      uniqueValues,
      identifyTpl,
      identifyRow,
      nextCursorSubUnique
    )
  }

  private def nextCursorSubUnique(tpls: List[Tpl], tokens: List[String]): String = {
    val attrTokens = tokens.drop(2).grouped(13).toList.collect {
      case List(kind, dir, pos, tpe, ns, attr, uniqueIndex, _, _, _, _, _, _) =>
        List(kind, dir, pos, tpe, ns, attr, uniqueIndex) ++
          getUniqueValues(tpls, uniqueIndex.toInt, encoder(tpe, kind))
    }.flatten
    val tokens1    = tokens.take(2) ++ attrTokens
    Base64.getEncoder.encodeToString(tokens1.mkString("\n").getBytes)
  }
}
