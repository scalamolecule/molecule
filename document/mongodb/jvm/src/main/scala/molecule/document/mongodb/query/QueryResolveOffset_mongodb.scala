package molecule.document.mongodb.query

import com.mongodb.client.AggregateIterable
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.util.{FutureUtils, JavaConversions, ModelUtils}
import molecule.document.mongodb.facade.MongoConn_JVM
import molecule.document.mongodb.query.casting._
import molecule.document.mongodb.util.BsonUtils
import org.bson._
import scala.collection.mutable.ListBuffer

case class QueryResolveOffset_mongodb[Tpl](
  elements: List[Element],
  optLimit: Option[Int],
  optOffset: Option[Int],
  m2q: Model2MongoQuery[Tpl]
) extends QueryResolve_mongodb[Tpl](elements, m2q)
  with CastBsonDoc_
  with FutureUtils
  with ModelUtils
  with BsonUtils
  with JavaConversions
  with MoleculeLogging {

  lazy val forward = optLimit.fold(true)(_ >= 0) && optOffset.fold(true)(_ >= 0)

  def getListFromOffset_sync(implicit conn: MongoConn_JVM)
  : (List[Tpl], Int, Boolean) = {
    lazy val limitSign  = optLimit.get >> 31
    lazy val offsetSign = optOffset.get >> 31
    if (optOffset.isDefined && optLimit.isDefined && limitSign != offsetSign) {
      throw ModelError("Limit and offset should both be positive or negative.")
    }
    val bsonDocs: AggregateIterable[BsonDocument] = getData(conn, optLimit, optOffset)

    if (bsonDocs.iterator().hasNext) {
      println("RESULT ---------------------------------------------")
      val array = new BsonArray()
      bsonDocs.forEach(d => array.add(d))
      println(bsonDocs.asScala.map(_.toJson(pretty)).mkString(",\n"))
      println("")
    }

    val tuples = ListBuffer.empty[Tpl]
    //    val casts  = m2q.updatedCasts.casts
    //    casts.foreach(println)


    //    val bson2tpl = documentCaster(m2q.updatedCasts.casts)

    val bson2tpl = documentCaster(m2q.immutableCastss)
    bsonDocs.forEach { bsonDoc =>
//      println("........ " + bsonDoc)
//      println("   ..... " + bson2tpl(bsonDoc).asInstanceOf[Tpl])
      curLevelDocs.clear()


      // Cast Bson document to entity tuple
      tuples += bson2tpl(bsonDoc).asInstanceOf[Tpl]
    }
    // Since a MongoDB field can both have the value null and not exist in the
    // document, we need to coalesce the possible two optional None value alternatives.
    val entities = tuples.distinct.toList

    //    println("tuples: ---------------------- ")
    //    tuples.foreach(println)
    (entities, entities.length, false)
  }


  def subscribe(
    conn: MongoConn_JVM,
    callback: List[Tpl] => Unit,
    freshM2q: List[Element] => Model2MongoQuery[Tpl]
  ): Unit = {
    val involvedAttrs    = getAttrNames(elements)
    val involvedDeleteNs = getInitialNs(elements)
    val maybeCallback    = (mutationAttrs: Set[String], isDelete: Boolean) => {
      if (
        mutationAttrs.exists(involvedAttrs.contains) ||
          isDelete && mutationAttrs.head.startsWith(involvedDeleteNs)
      ) {
        callback(
          QueryResolveOffset_mongodb(elements, optLimit, None, freshM2q(elements))
            .getListFromOffset_sync(conn)._1
        )
      }
    }
    conn.addCallback(elements -> maybeCallback)
  }

  def unsubscribe(conn: MongoConn_JVM): Unit = {
    conn.removeCallback(elements)
  }


  private def offsetList(
    sortedRows: List[Tpl],
    fromUntil: Option[(Int, Int, Boolean)]
  ): List[Tpl] = {
    fromUntil.fold(sortedRows) {
      case (from, until, _) => sortedRows.slice(from, until)
    }
  }
}
