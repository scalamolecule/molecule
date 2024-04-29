package molecule.document.mongodb.query.cursorStrategy

import molecule.boilerplate.ast.Model._
import molecule.boilerplate.ops.ModelTransformations_
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.query.Pagination
import molecule.core.util.FutureUtils
import molecule.document.mongodb.facade.MongoConn_JVM
import molecule.document.mongodb.query.casting.CastBsonDoc_
import molecule.document.mongodb.query.{Model2MongoQuery, QueryResolve_mongodb}
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
  m2q: Model2MongoQuery[Tpl]
) extends QueryResolve_mongodb[Tpl](elements, m2q)
  with Pagination[Tpl]
  with CastBsonDoc_
  with ModelTransformations_
  with FutureUtils
  with MoleculeLogging {

  def getPage(tokens: List[String], limit: Int)
             (implicit conn: MongoConn_JVM): (List[Tpl], String, Boolean) = {
    val List(_, _, tpe, ns, attr, _, a, z) = tokens

    val forward    = limit > 0
    val (fn, v)    = if (forward) (Gt, z) else (Lt, a)
    val filterAttr = getFilterAttr(tpe, ns, attr, fn, v)
    val elements1  = if (forward) elements else reverseTopLevelSorting(elements)
    val elements2  = addFilterAttr(elements1, filterAttr)
    val bsonDocs   = getData(conn, elements2, Some(limit), None)
    val tuples     = ListBuffer.empty[Tpl]
    val bson2tpl   = levelCaster(m2q.immutableCastss)
    val it         = bsonDocs.iterator()

    if (!it.hasNext) {
      (Nil, "", false)
    } else {
      val facet    = it.next()
      val rows     = facet.getArray("rows")
      val metaData = facet.getArray("metaData")
      if (rows.isEmpty) {
        (Nil, "", false)
      } else {
        rows.forEach { bsonDoc =>
          curLevelDocs.clear()
          tuples += bson2tpl(bsonDoc.asDocument()).asInstanceOf[Tpl]
        }
        val tuples1    = tuples.distinct.toList
        val totalCount = metaData.get(0).asDocument().get("totalCount").asInt32().intValue()
        val fromUntil  = getFromUntil(totalCount, Some(limit), None)
        val hasMore    = fromUntil.fold(totalCount > 0)(_._3)
        val result     = if (forward) tuples1 else tuples1.reverse
        val cursor     = nextCursorUniques(result, tokens)
        (result, cursor, hasMore)
      }
    }
  }
}
