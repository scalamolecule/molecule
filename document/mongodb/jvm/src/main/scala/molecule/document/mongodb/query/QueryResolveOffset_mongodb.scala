package molecule.document.mongodb.query

import molecule.boilerplate.ast.Model._
import molecule.boilerplate.ops.ModelTransformations_
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.util.{FutureUtils, JavaConversions, ModelUtils}
import molecule.document.mongodb.facade.MongoConn_JVM
import molecule.document.mongodb.query.casting._
import molecule.document.mongodb.util.BsonUtils
import scala.collection.mutable.ListBuffer

case class QueryResolveOffset_mongodb[Tpl](
  elements: List[Element],
  optLimit: Option[Int],
  optOffset: Option[Int],
  m2q: Model2MongoQuery[Tpl]
) extends QueryResolve_mongodb[Tpl](elements, m2q)
  with ModelTransformations_
  with CastBsonDoc_
  with FutureUtils
  with ModelUtils
  with BsonUtils
  with JavaConversions
  with MoleculeLogging {

  // Since a MongoDB field can both have the value null and not exist in the
  // document, we need to coalesce the possible two optional None value alternatives
  // by calling distinct on the tuples result.
  def getListFromOffset_sync(implicit conn: MongoConn_JVM)
  : (List[Tpl], Int, Boolean) = {
    val (isPaginated, forward) = paginationCoords(optLimit, optOffset)
    val elements1              = if (isPaginated && !forward) reverseTopLevelSorting(elements) else elements
    val bsonDocs               = getData(conn, elements1, optLimit, optOffset)
    val tuples                 = ListBuffer.empty[Tpl]
    val bson2tpl               = levelCaster(m2q.immutableCastss)

    if (isPaginated) {
      val it = bsonDocs.iterator()
      if (!it.hasNext) {
        (Nil, 0, false)
      } else {
        val facet    = it.next()
        val rows     = facet.get("rows").asArray()
        val metaData = facet.get("metaData").asArray()
        if (rows.isEmpty) {
          val totalCount = if (metaData.isEmpty) 0 else
            metaData.get(0).asDocument().get("totalCount").asInt32().intValue()
          (Nil, totalCount, false)
        } else {
          rows.forEach { bsonDoc =>
            curLevelDocs.clear()
            tuples += bson2tpl(bsonDoc.asDocument()).asInstanceOf[Tpl]
          }
          val tuples1    = tuples.distinct.toList
          val totalCount = metaData.get(0).asDocument().get("totalCount").asInt32().intValue()
          val fromUntil  = getFromUntil(totalCount, optLimit, optOffset)
          val hasMore    = fromUntil.fold(totalCount > 0)(_._3)
          val result     = if (forward) tuples1 else tuples1.reverse
          (result, totalCount, hasMore)
        }
      }
    } else {
      // Not paginated
      bsonDocs.forEach { bsonDoc =>
        curLevelDocs.clear()
        tuples += bson2tpl(bsonDoc).asInstanceOf[Tpl]
      }
      (tuples.distinct.toList, -1, true) // Total count only used when paginating
    }
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
