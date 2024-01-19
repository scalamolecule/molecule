package molecule.document.mongodb.query

import java.sql.ResultSet
import com.mongodb.client.AggregateIterable
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.Pagination
import molecule.core.util.ModelUtils
import molecule.document.mongodb.facade.MongoConn_JVM
import molecule.document.mongodb.util.BsonUtils
import org.bson.BsonDocument
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer


abstract class QueryResolve_mongodb[Tpl](
  elements: List[Element],
  m2q: Model2MongoQuery[Tpl]
) extends Pagination[Tpl]
  with ModelUtils
  with BsonUtils {

  protected def getData(
    conn: MongoConn_JVM,
    altElements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): AggregateIterable[BsonDocument] = {
    val (collectionName, pipeline) = m2q.getBsonQuery(altElements, optLimit, optOffset)
    val collection                 = conn.mongoDb.getCollection(collectionName, classOf[BsonDocument])

    println("QUERY ----------------------------------------------")
    //    elements.foreach(println)
    //    println("-------")
    println(pipeline2json(pipeline, Some(collectionName)))


    //    val totalCount = collection.aggregate(pipelineAll).iterator().
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
    //    val query = m2q.getSqlQuery(altElements, optLimit, optOffset)
    //    getResultSet(conn, query)

    ???
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
      if (m2q.isNested) {
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
}
