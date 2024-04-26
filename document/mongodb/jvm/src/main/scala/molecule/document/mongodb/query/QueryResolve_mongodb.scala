package molecule.document.mongodb.query

import com.mongodb.client.AggregateIterable
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.Pagination
import molecule.core.util.ModelUtils
import molecule.document.mongodb.facade.MongoConn_JVM
import molecule.document.mongodb.query.casting.CastBsonDoc_
import molecule.document.mongodb.util.BsonUtils
import org.bson.BsonDocument
import scala.collection.mutable.ListBuffer


abstract class QueryResolve_mongodb[Tpl](
  elements: List[Element],
  m2q: Model2MongoQuery[Tpl]
) extends Pagination[Tpl]
  with CastBsonDoc_
  with ModelUtils
  with BsonUtils {

  def getData(
    conn: MongoConn_JVM,
    altElements: List[Element] = Nil,
    optLimit: Option[Int] = None,
    optOffset: Option[Int] = None
  ): AggregateIterable[BsonDocument] = {
    val (collectionName, pipeline) = m2q.getBsonQuery(altElements, optLimit, optOffset)
    val collection                 = conn.mongoDb.getCollection(collectionName, classOf[BsonDocument])

    //    println("QUERY ----------------------------------------------")
    //    elements.foreach(println)
    //    println("-------")
    //    println(pipeline2json(pipeline, Some(collectionName)))

    collection.aggregate(pipeline)
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
    val filterAttr  = {
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
    val altElements = addFilterAttr(elements, filterAttr)
    val bsonDocs    = getData(conn, altElements, Some(Int.MaxValue), None)
    val allTuples   = ListBuffer.empty[Tpl]
    val bson2tpl    = levelCaster(m2q.immutableCastss)
    val it          = bsonDocs.iterator()

    if (!it.hasNext) {
      (Nil, "", false)
    } else {
      val facet    = it.next()
      val rows     = facet.get("rows").asArray()
      val metaData = facet.get("metaData").asArray()
      if (rows.isEmpty) {
        (Nil, "", false)
      } else {
        val totalCount = metaData.get(0).asDocument().get("totalCount").asInt32().intValue()
        val count      = getCount(limit, forward, totalCount)
        rows.forEach { bsonDoc =>
          curLevelDocs.clear()
          allTuples += bson2tpl(bsonDoc.asDocument()).asInstanceOf[Tpl]
        }
        val allTuples0     = allTuples.distinct.toList
        val allTuples1     = if (forward) allTuples0 else allTuples0.reverse
        val (tuples, more) = paginateTpls(count, allTuples1, identifiers, identifyTpl)
        val tuples1        = if (forward) tuples else tuples.reverse
        val cursor         = nextCursor(tuples1, allTokens)
        (tuples1, cursor, more > 0)
      }
    }
  }


  protected def addFilterAttr(elements: List[Element], filterAttr: Attr): List[Element] = {
    var found     = false
    val elements1 = elements.flatMap {
      case a: Attr if a.cleanName == filterAttr.cleanName =>
        found = true
        // Add filter attribute after matching attribute so that the main
        // attribute is not suffixed in expressions, projection etc.
        List(a, filterAttr)
      case other                                          => List(other)
    }
    if (!found) {
      throw ModelError(
        s"""Couldn't match filter attribute:
           |$filterAttr
           |in model:
           |${elements.mkString("\n")}
           |""".stripMargin
      )
    }
    elements1
  }
}
