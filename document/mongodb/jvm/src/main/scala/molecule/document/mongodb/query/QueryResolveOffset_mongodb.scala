package molecule.document.mongodb.query

import com.mongodb.client.{AggregateIterable, FindIterable}
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.util.{FutureUtils, ModelUtils}
import molecule.document.mongodb.facade.MongoConn_JVM
import molecule.document.mongodb.query.casting.CastBsonDoc_
import org.bson._
import org.bson.json.JsonWriterSettings
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

    val pretty: JsonWriterSettings = JsonWriterSettings.builder().indent(true).build()

    println("RESULT ---------------------------------------------")
    bsonDocs.forEach(d => println(d.toJson(pretty)))
    println("")

    // Cast Bson document to entity tuple
    val tuples   = ListBuffer.empty[Tpl]
    val bson2tpl = documentCaster(m2q.castss)
    bsonDocs.forEach { bsonDoc =>
      tuples += bson2tpl(bsonDoc).asInstanceOf[Tpl]
    }
    //    println("tuples: ---------------------- ")
    //    tuples.foreach(println)
    (tuples.toList, tuples.length, false)
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
