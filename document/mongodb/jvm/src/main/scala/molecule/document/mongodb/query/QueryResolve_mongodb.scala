package molecule.document.mongodb.query

import java.sql.ResultSet
import java.util
import java.util.Arrays.asList
import java.util.Map
import com.mongodb.client.AggregateIterable
import com.mongodb.client.model.Aggregates._
import com.mongodb.client.model.{Aggregates, Filters, Projections, Sorts}
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.util.ModelUtils
import molecule.document.mongodb.facade.MongoConn_JVM
import molecule.document.mongodb.util.BsonUtils
import org.bson.{BsonArray, BsonDocument, BsonValue, Document}
import org.bson.conversions.Bson
import org.bson.json.JsonWriterSettings
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer


abstract class QueryResolve_mongodb[Tpl](
  elements: List[Element],
  m2q: Model2MongoQuery[Tpl]
) extends CursorUtils
  with ModelUtils
  with BsonUtils {

  lazy val edgeValuesNotFound = "Couldn't find next page. Edge rows were all deleted/updated."

  protected def getUniqueValues(tpls0: List[Tpl], uniqueIndex: Int, encode: Any => String): List[String] = {
    val tpls   = (if (tpls0.head.isInstanceOf[Product]) tpls0 else tpls0.map(Tuple1(_))).asInstanceOf[List[Product]]
    val first3 = tpls.take(3).map(t => encode(t.productElement(uniqueIndex))).padTo(3, "")
    val last3  = tpls.takeRight(3).map(t => encode(t.productElement(uniqueIndex))).reverse.padTo(3, "").reverse
    first3 ++ last3
  }

  protected def getRowHashes(tpls: List[Tpl]): List[String] = {
    val first3 = tpls.take(3).map(row => row.hashCode().toString).padTo(3, "")
    val last3  = tpls.takeRight(3).map(row => row.hashCode().toString).reverse.padTo(3, "").reverse
    first3 ++ last3
  }

  protected def getUniquePair(tpls: List[Tpl], uniqueIndex: Int, encode: Any => String): List[String] = {
    tpls.head match {
      case tpl: Product => List(
        encode(tpl.productElement(uniqueIndex)),
        encode(tpls.last.asInstanceOf[Product].productElement(uniqueIndex))
      )
      case v            => List(encode(v), encode(tpls.last))
    }
  }

  protected def getData(
    conn: MongoConn_JVM,
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): AggregateIterable[BsonDocument] = {
    val (collectionName, pipeline) = m2q.getBsonQuery(Nil, optLimit, optOffset, Some(conn.proxy))
    val collection                 = conn.mongoDb.getCollection(collectionName, classOf[BsonDocument])

    //    println("QUERY ----------------------------------------------")
    //    elements.foreach(println)
    //    println("-------")
    //    pipeline.forEach(x => println(x.toBsonDocument.toJson(pretty)))

    collection.aggregate(pipeline)
  }

  protected def getTotalCount(conn: MongoConn_JVM): Int = {
    //    val rs = getResultSet(conn, m2q.getTotalCountQuery)
    //    rs.next()
    //    rs.getInt(1)
    ???
  }

  //  private def getResultSet(conn: MongoConn_JVM, query: BsonDocument): FindIterable[BsonDocument] = {
  //    //    println("--- 1 ------------------")
  //    //    elements.foreach(println)
  //    //    println("---")
  //    //    println(query)
  ////    conn.sqlConn.prepareStatement(
  ////      query,
  ////      ResultSet.TYPE_SCROLL_INSENSITIVE,
  ////      ResultSet.CONCUR_READ_ONLY
  ////    ).executeQuery()
  //
  //
  //    val collection = conn.mongoDb.getCollection("Ns", classOf[BsonDocument])
  //
  //    collection.find(query)
  //  }


  protected def getRawData(
    conn: MongoConn_JVM,
    altElements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): ResultSet = {
    //    val query = m2q.getSqlQuery(altElements, optLimit, optOffset, Some(conn.proxy))
    //    getResultSet(conn, query)

    ???
  }

  protected def getFromUntil(
    tc: Int,
    limit: Option[Int],
    offset: Option[Int]
  ): Option[(Int, Int, Boolean)] = {
    (offset, limit) match {
      case (None, None)                => None
      case (None, Some(l)) if l > 0    => Some((0, l.min(tc), l < tc))
      case (None, Some(l))             => Some(((tc + l).max(0), tc, (tc + l) > 0))
      case (Some(o), None) if o > 0    => Some((o.min(tc), tc, o < tc))
      case (Some(o), None)             => Some((0, (tc + o).min(tc), -o < tc))
      case (Some(o), Some(l)) if l > 0 => Some((o.min(tc), (o + l).min(tc), (o + l) < tc))
      case (Some(o), Some(l))          => Some(((tc + o + l).max(0), (tc + o).max(0), (tc + o + l).max(0) > 0))
    }
  }


  def paginateFromIdentifiers(
    conn: MongoConn_JVM,
    limit: Int,
    forward: Boolean,
    allTokens: List[String],
    attrTokens: List[String],
    identifiers: List[Any],
    identifyTpl: Tpl => Any,
    nextCursor: (List[Tpl], List[String]) => String
  ): (List[Tpl], String, Boolean) = {
    // Filter query by primary non-unique sort attribute
    val filterAttr   = {
      val List(_, dir, _, tpe, ns, attr, _, a, b, c, x, y, z) = attrTokens

      // Filter by most inclusive value
      val first   = List(c, b, a).filter(_.nonEmpty).head
      val last    = List(x, y, z).filter(_.nonEmpty).head
      val (fn, v) = (forward, dir) match {
        case (true, "a") => (Ge, last)
        case (true, _)   => (Le, first)
        case (_, "a")    => (Le, first)
        case (_, _)      => (Ge, last)
      }
      getFilterAttr(tpe, ns, attr, fn, v)
    }
    val altElements  = filterAttr +: elements
    //    val sortedRows   = getRawData(conn, altElements, None, None)
    //    val sortedRows1  = new ResultSetImpl(sortedRows)
    //    val flatRowCount = m2q.getRowCount(sortedRows1)
    val flatRowCount = 42

    if (flatRowCount == 0) {
      (Nil, "", false)
    } else {
      if (m2q.isNested || m2q.isNestedOpt) {
        //        val nestedTpls     = if (m2q.isNested) m2q.rows2nested(sortedRows1) else m2q.rows2nestedOpt(sortedRows1)
        //        val totalCount     = nestedTpls.length
        //        val count          = getCount(limit, forward, totalCount)
        //        val nestedTpls1    = if (forward) nestedTpls else nestedTpls.reverse
        //        val (tuples, more) = paginateTpls(count, nestedTpls1, identifiers, identifyTpl)
        //        val tpls           = if (forward) tuples else tuples.reverse
        //        val cursor         = nextCursor(tpls, allTokens)
        //        (tpls, cursor, more > 0)
        ???

      } else {
        //        val totalCount = flatRowCount
        //        val count      = getCount(limit, forward, totalCount)
        //        val allTuples  = ListBuffer.empty[Tpl]
        //        val row2tpl    = m2q.castRow2AnyTpl(m2q.aritiess.head, m2q.castss.head, 1, None)
        //        while (sortedRows.next()) {
        //          allTuples += row2tpl(sortedRows1).asInstanceOf[Tpl]
        //        }
        //        val allTuples1     = if (forward) allTuples else allTuples.reverse
        //        val (tuples, more) = paginateTpls(count, allTuples1.result(), identifiers, identifyTpl)
        //        val tpls           = if (forward) tuples else tuples.reverse
        //        val cursor         = nextCursor(tpls, allTokens)
        //        (tpls, cursor, more > 0)
        ???
      }
    }
  }

  private def getCount(limit: Int, forward: Boolean, totalCount: Int) = {
    if (forward)
      limit.min(totalCount)
    else
      totalCount - (totalCount + limit).max(0)
  }

  private def paginateTpls(
    count: Int,
    tpls: List[Tpl],
    identifiers: List[Any],
    identify: Tpl => Any
  ): (List[Tpl], Int) = {
    val tuples = ListBuffer.empty[Tpl]
    var window = false
    var i      = 0
    var more   = 0
    @tailrec
    def findFrom(identifiers: List[Any]): Unit = {
      identifiers match {
        case identifier :: remainingIdentifiers =>
          tpls.foreach {
            case tpl if window && i != count        => i += 1; tuples += tpl
            case tpl if identify(tpl) == identifier => window = true
            case _                                  => if (window) more += 1
          }
          if (tuples.isEmpty) {
            // Recursively try with next identifier
            findFrom(remainingIdentifiers)
          }

        case Nil => throw ModelError(edgeValuesNotFound)
      }
    }
    findFrom(identifiers)
    (tuples.result(), more)
  }
}
