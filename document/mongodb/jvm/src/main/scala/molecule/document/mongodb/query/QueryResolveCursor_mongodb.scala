package molecule.document.mongodb.query

import java.util.Base64
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.ops.ModelTransformations_
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.query.Pagination
import molecule.core.spi.Conn
import molecule.core.util.FutureUtils
import molecule.document.mongodb.facade.MongoConn_JVM
import molecule.document.mongodb.query.casting.CastBsonDoc_
import molecule.document.mongodb.query.cursorStrategy.{NoUnique, PrimaryUnique, SubUnique}
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

case class QueryResolveCursor_mongodb[Tpl](
  elements: List[Element],
  optLimit: Option[Int],
  cursor: Option[String],
  m2q: Model2MongoQuery[Tpl]
) extends QueryResolve_mongodb[Tpl](elements, m2q)
  with CastBsonDoc_
  with FutureUtils
  with Pagination[Tpl]
  with ModelTransformations_
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
    //    val forward      = limit > 0
    //    val altElements  = if (forward) elements else reverseTopLevelSorting(elements)
    ////    val sortedRows   = getRawData(conn, altElements, Some(limit.abs), None)
    ////    val sortedRows1  = new ResultSetImpl(sortedRows)
    ////    val flatRowCount = m2q.getRowCount(sortedRows1)
    //    val flatRowCount = 42
    //
    //    if (flatRowCount == 0) {
    //      (Nil, "", false)
    //    } else {
    //      if (m2q.isNested) {
    ////        val nestedRows    = if (m2q.isNested) m2q.rows2nested(sortedRows1) else m2q.rows2nestedOpt(sortedRows1)
    ////        val topLevelCount = nestedRows.length
    ////        val limitAbs      = limit.abs.min(topLevelCount)
    ////        val hasMore       = limitAbs < topLevelCount
    ////        val selectedRows  = nestedRows.take(limitAbs)
    ////        val tpls          = if (forward) selectedRows else selectedRows.reverse
    ////        val cursor        = initialCursor(conn, tpls)
    ////        (tpls, cursor, hasMore)
    //        ???
    //
    //      } else {
    //        val totalCount = getTotalCount(conn)
    //        val limitAbs   = limit.abs.min(totalCount)
    //        val hasMore    = limitAbs < totalCount
    //        val tuples     = ListBuffer.empty[Tpl]
    ////        val row2tpl    = m2q.castRow2AnyTpl(m2q.aritiess.head, m2q.castss.head, 1, None)
    ////        while (sortedRows.next()) {
    ////          tuples += row2tpl(sortedRows1).asInstanceOf[Tpl]
    ////        }
    //        val result = if (forward) tuples.toList else tuples.toList.reverse
    //        val cursor = initialCursor(conn, result)
    //        (result, cursor, hasMore)
    //        ???
    //      }
    //    }


    //    val (isPaginated, forward) = pagination(optLimit, None)
    //    val elements1              = if (isPaginated && !forward) reverseTopLevelSorting(elements) else elements
    //    val bsonDocs               = getData(conn, elements1, optLimit, None)
    //    val tuples                 = ListBuffer.empty[Tpl]
    //    val bson2tpl               = levelCaster(m2q.immutableCastss)
    //
    //    if (isPaginated) {
    //      val it = bsonDocs.iterator()
    //      if (!it.hasNext) {
    //        (Nil, "", false)
    //      } else {
    //        val facet    = it.next()
    //        val rows     = facet.get("rows").asArray()
    //        val metaData = facet.get("metaData").asArray()
    //        if (rows.isEmpty) {
    //          val totalCount = if (metaData.isEmpty) "" else
    //            metaData.get(0).asDocument().get("totalCount").asInt32().intValue()
    //
    //          val nextCursor = initialCursor(conn, Nil)
    //
    //          (Nil, totalCount, false)
    //        } else {
    //          rows.forEach { bsonDoc =>
    //            curLevelDocs.clear()
    //            tuples += bson2tpl(bsonDoc.asDocument()).asInstanceOf[Tpl]
    //          }
    //          val tuples1    = tuples.distinct.toList
    //          val totalCount = metaData.get(0).asDocument().get("totalCount").asInt32().intValue()
    //          val fromUntil  = getFromUntil(totalCount, optLimit, None)
    //          val hasMore    = fromUntil.fold(totalCount > 0)(_._3)
    //          val result     = if (forward) tuples1 else tuples1.reverse
    //          (result, totalCount, hasMore)
    //        }
    //      }
    //    } else {
    //      // Not paginated
    //      bsonDocs.forEach { bsonDoc =>
    //        curLevelDocs.clear()
    //        tuples += bson2tpl(bsonDoc).asInstanceOf[Tpl]
    //      }
    //      (tuples.distinct.toList, -1, true) // Total count only used when paginating
    //    }
    ???
  }
}
