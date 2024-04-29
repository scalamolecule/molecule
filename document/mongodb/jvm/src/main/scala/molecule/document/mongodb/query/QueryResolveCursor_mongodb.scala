package molecule.document.mongodb.query

import java.util.Base64
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.ops.ModelTransformations_
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.query.Pagination
import molecule.core.util.FutureUtils
import molecule.document.mongodb.facade.MongoConn_JVM
import molecule.document.mongodb.query.casting.CastBsonDoc_
import molecule.document.mongodb.query.cursorStrategy.{NoUnique, PrimaryUnique, SubUnique}
import scala.collection.mutable.ListBuffer

case class QueryResolveCursor_mongodb[Tpl](
  elements: List[Element],
  optLimit: Option[Int],
  cursor: Option[String],
  m2q: Model2MongoQuery[Tpl]
) extends QueryResolve_mongodb[Tpl](elements, m2q)
  with Pagination[Tpl]
  with CastBsonDoc_
  with ModelTransformations_
  with FutureUtils
  with MoleculeLogging {

  def getListFromCursor_sync(implicit conn: MongoConn_JVM)
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


  private def getInitialPage(limit: Int)(implicit conn: MongoConn_JVM)
  : (List[Tpl], String, Boolean) = {
    val forward   = limit > 0
    val elements1 = if (forward) elements else reverseTopLevelSorting(elements)
    val bsonDocs  = getData(conn, elements1, Some(limit), None)
    val tuples    = ListBuffer.empty[Tpl]
    val bson2tpl  = levelCaster(m2q.immutableCastss)
    val it        = bsonDocs.iterator()
    if (!it.hasNext) {
      (Nil, "", false)
    } else {
      val facet    = it.next()
      val rows     = facet.getArray("rows")
      val metaData = facet.getArray("metaData")
      if (rows.isEmpty) {
        val cursor = initialCursor(conn, elements, Nil)
        (Nil, cursor, false)
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
        val cursor     = initialCursor(conn, elements, result)
        (result, cursor, hasMore)
      }
    }
  }
}
