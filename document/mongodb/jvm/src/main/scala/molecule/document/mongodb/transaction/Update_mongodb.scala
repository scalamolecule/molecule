package molecule.document.mongodb.transaction

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.action.Query
import molecule.core.transaction.ResolveUpdate
import molecule.core.transaction.ops.UpdateOps
import molecule.core.util.JavaConversions
import molecule.document.mongodb.facade.MongoConn_JVM
import molecule.document.mongodb.query.{Model2MongoQuery, QueryResolveOffset_mongodb}
import molecule.document.mongodb.spi.SpiSync_mongodb
import molecule.document.mongodb.sync.pretty
import molecule.document.mongodb.util.BsonUtils
import org.bson.types.ObjectId
import org.bson._
import org.bson.conversions.Bson
import scala.collection.mutable.ListBuffer

trait Update_mongodb
  extends Base_JVM_mongodb
    with UpdateOps
    with BsonUtils
    with JavaConversions
    with MoleculeLogging { self: ResolveUpdate =>

  doPrint = false

  private var requiredNsPaths = List(List.empty[String])
  private var currentNsPath   = List.empty[String]

  case class NsData(
    var referee: Option[NsData] = None,
    var ns: String = "",
    setDoc: BsonDocument = new BsonDocument(),
    unsetDoc: BsonDocument = new BsonDocument(),
    pushDoc: BsonDocument = new BsonDocument(),
    addToSet: BsonDocument = new BsonDocument(),
    pullAll: BsonDocument = new BsonDocument(),
    arrayFilters: BsonArray = new BsonArray(),
    var ids: Seq[String] = Seq.empty[String],
    var filterElements: List[Element] = List.empty[Element],
    //    uniqueFilterElements: ListBuffer[Element] = ListBuffer.empty[Element],
  ) {
    override def toString: String = {
      val referee1              = if (referee.isEmpty) "None" else s"Some(${referee.get.ns})"
      val filterElements1       = if (filterElements.isEmpty) "Nil" else
        filterElements.mkString("\n    ", ",\n    ", "")
      val uniqueFilterElements1 = if (uniqueFilterElements.isEmpty) "Nil" else
        uniqueFilterElements.mkString("\n    ", ",\n    ", "")
      s"""RefData(
         |  referee             : $referee1
         |  ns                  : $ns
         |  setDoc              : $setDoc
         |  unsetDoc            : $unsetDoc
         |  pushDoc             : $pushDoc
         |  addToSet            : $addToSet
         |  pullAll             : $pullAll
         |  arrayFilters        : $arrayFilters
         |  ids                 : $ids
         |  filterElements      : $filterElements1
         |  uniqueFilterElements: $uniqueFilterElements1
         |)""".stripMargin
    }
  }

  // Initial namespace data container
  var nsData  = NsData()
  val nssData = ListBuffer.empty[NsData] += nsData

  var conn: MongoConn_JVM = null

  def getData(elements: List[Element], conn0: MongoConn_JVM): Data = {
    conn = conn0
    nsData.ns = getInitialNs(elements)

    val (filterElements1, requiredNsPaths1) = getFilters(elements.reverse)
    requiredNsPaths = requiredNsPaths1

    val filters = AttrOneManID(getInitialNs(elements), "id", V) :: filterElements1

    //    println("------ filters --------")
    //    filters.foreach(println)
    //    println("------ requiredNsPaths: " + requiredNsPaths)

    val currentDataRows = new QueryResolveOffset_mongodb[Any](
      filters, None, None, new Model2MongoQuery[Any](filters)
    ).getData(conn)

    val (collectionName, pipeline) = new Model2MongoQuery[Any](filters).getBsonQuery(Nil, None, None)

    val filter = pipeline.get(0).toBsonDocument().get("$match").asDocument()

    println("--------- currentDataRows")
    currentDataRows.forEach(row => println(row.toJson(pretty)))

    println("--------- filter")
    //    filter.asScala.toList.foreach(println)
    //    println(pipeline.get(0).toBsonDocument().get("$match"))
    println(filter.toJson(pretty))
    println("----------")
    //    println(pipeline2json(filter, Some(collectionName)))

    resolve(elements)
    val updateData = new BsonDocument("_action", new BsonString("update"))
    nssData.foreach {
      case NsData(_, ns, setDoc, unsetDoc, pushDoc, addToSet, pullAll, arrayFilters, ids, filterElements) =>
        //        val ids1x = if (isUpdate) {
        //          if (ids.nonEmpty && filterElements.nonEmpty) {
        //            val filterIds = query(AttrOneManID(ns, "id", Eq, ids) +: filterElements)
        //            val validIds  = ids.intersect(filterIds)
        //            validIds
        //
        //          } else if (ids.nonEmpty && uniqueFilterElements.nonEmpty) {
        //            val filterIds = query(AttrOneManID(ns, "id", Eq, ids) +: uniqueFilterElements.toList)
        //            val validIds  = ids.intersect(filterIds)
        //            validIds
        //
        //          } else if (uniqueFilterElements.nonEmpty) {
        //            val filterIds = query(AttrOneManID(ns, "id", V) +: uniqueFilterElements.toList)
        //            filterIds
        //
        //          } else if (filterElements.nonEmpty) {
        //            val filterIds = query(AttrOneManID(ns, "id", V) +: filterElements)
        //            filterIds
        //          } else {
        //            ids
        //          }
        //        } else if (ids.nonEmpty) {
        //          ids
        //        } else {
        //          query(AttrOneManID(ns, "id", V) +: filterElements)
        //        }

        //        val ids1X = {
        //          if (ids.nonEmpty && filterElements.nonEmpty) {
        //            val filterIds = query(AttrOneManID(ns, "id", Eq, ids) +: filterElements)
        //            val validIds  = ids.intersect(filterIds)
        //            validIds
        //
        //          } else if (ids.nonEmpty && uniqueFilterElements.nonEmpty) {
        //            val uniqueIds = query(AttrOneManID(ns, "id", Eq, ids) +: uniqueFilterElements.toList)
        //            val validIds  = ids.intersect(uniqueIds)
        //            validIds
        //
        //          } else if (uniqueFilterElements.nonEmpty) {
        //            val filterIds = query(AttrOneManID(ns, "id", V) +: uniqueFilterElements.toList)
        //            filterIds
        //
        //          } else if (filterElements.nonEmpty) {
        //            val filterIds = query(AttrOneManID(ns, "id", V) +: filterElements)
        //            filterIds
        //          } else {
        //            ids
        //          }
        //        }
        //        val ids1 = {
        //          if (ids.nonEmpty && filterElements.nonEmpty) {
        //            val filterIds = query(AttrOneManID(ns, "id", Eq, ids) +: filterElements)
        //            val validIds  = ids.intersect(filterIds)
        //            validIds
        //
        //          } else if (ids.nonEmpty && uniqueFilterElements.nonEmpty) {
        //            val filterIds = query(AttrOneManID(ns, "id", Eq, ids) +: uniqueFilterElements.toList)
        //            val validIds  = ids.intersect(filterIds)
        //            validIds
        //
        //          } else if (uniqueFilterElements.nonEmpty) {
        ////          } else if (ids.isEmpty && uniqueFilterElements.nonEmpty) {
        //            val filterIds = query(AttrOneManID(ns, "id", V) +: uniqueFilterElements.toList)
        //            filterIds
        //
        //          } else if (filterElements.nonEmpty) {
        ////          } else if (ids.isEmpty && filterElements.nonEmpty) {
        //            val filterIds = query(AttrOneManID(ns, "id", V) +: filterElements)
        //            filterIds
        //          } else if (ids.nonEmpty) {
        ////          } else {
        //            ids
        //          }
        //          else {
        //            println("++++++++ " + filterElements)
        //            query(AttrOneManID(ns, "id", V) +: filterElements)
        //          }
        //        }

        val ids1 = if (ids.nonEmpty) {
          println("--- A: " + ids)
          ids
        } else {
          val ids = query(AttrOneManID(ns, "id", V) +: filterElements)
          println("--- B: ")
          filterElements.foreach(println)
          println("--- B: " + ids)
          ids
        }
        if (ids1.nonEmpty) {
          val idArray = new BsonArray()
          ids1.foreach(id => idArray.add(new BsonObjectId(new ObjectId(id))))
          val filter = new BsonDocument("_id", new BsonDocument("$in", idArray))

          val update = new BsonDocument()
          if (!setDoc.isEmpty)
            update.append("$set", setDoc)

          if (!unsetDoc.isEmpty)
            update.append("$unset", unsetDoc)

          if (!pushDoc.isEmpty)
            update.append("$push", pushDoc)

          if (!addToSet.isEmpty)
            update.append("$addToSet", addToSet)

          if (!pullAll.isEmpty)
            update.append("$pullAll", pullAll)

          val nsData = new BsonDocument()
            //            .append("filter", filter)
            .append("filter", filter)
            .append("update", update)


          if (!arrayFilters.isEmpty)
            nsData.append("arrayFilters", arrayFilters)

          // Only add update data for namespace if there is ids and data to update
          if (!update.isEmpty) {
            updateData.append(ns, nsData)
          }
        }
    }
    updateData
  }

  private def query(elements: List[Element]): List[String] = {
    SpiSync_mongodb.query_get[String](Query(elements))(conn)
  }

  override def handleRefNs(ref: Ref): Unit = {
    val Ref(ns, refAttr, refNs, _, owner, _) = ref
    if (owner) {
      // Embedded document
      nsData.filterElements = nsData.filterElements :+ ref
      path = path :+ refAttr

    } else {
      // Referenced document

      if (nsData.filterElements.nonEmpty && nsData.filterElements.last.isInstanceOf[BackRef]) {
        // Remove BackRef orphan
        nsData.filterElements = nsData.filterElements.init
      }

      // Retrieve reference ids (could be done in one query for all refs...)
      val refIds = query(List(
        AttrOneTacID(ns, "id", Eq, nsData.ids),
        AttrOneManID(ns, refAttr, V),
      ))
      path = Nil

      // Process referenced documents
      val referee = Some(nsData)
      nsData = NsData(referee, refNs, ids = refIds)
      nssData += nsData
    }
  }

  override protected def handleBackRef(backRef: BackRef): Unit = {
    nsData.referee.fold[Unit] {
      path = path.init
      nsData.filterElements = nsData.filterElements :+ backRef
    } { referee =>
      path = Nil
      nsData = referee
    }
  }

  override protected def updateOne[T](
    ns: String,
    attr: String,
    owner: Boolean,
    vs: Seq[T],
    transformValue: T => Any,
  ): Unit = {
    if (owner) {
      throw ModelError("Can't update non-existing ids of embedded documents in MongoDB.")
    }
    nsData.filterElements = nsData.filterElements :+ AttrOneTacInt(ns, attr) // dummy filter

    lazy val pathAttr = if (path.isEmpty) attr else path.mkString("", ".", "." + attr)
    vs match {
      case Seq(v) => nsData.setDoc.append(pathAttr, transformValue(v).asInstanceOf[BsonValue])
      case Nil    => nsData.setDoc.append(pathAttr, new BsonNull())
      case vs     =>
        val cleanAttr = attr.replace("_", "")
        throw ExecutionError(
          s"Can only update one value for attribute `$ns.$cleanAttr`. Found: " + vs.mkString(", ")
        )
    }
  }

  override protected def updateSetEq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateIterableEq(ns, attr, owner, set, transformValue)
  }

  override protected def updateSetAdd[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateIterableAdd(attr, set, transformValue)
  }

  override protected def updateSetRemove[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String
  ): Unit = {
    updateIterableRemove(attr, set, transformValue)
  }

  override protected def updateSeqEq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateIterableEq(ns, attr, owner, seq, transformValue)
  }

  override protected def updateSeqAdd[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateIterableAdd(attr, seq, transformValue)
  }

  override protected def updateSeqRemove[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String
  ): Unit = {
    updateIterableRemove(attr, seq, transformValue)
  }

  override protected def updateByteArray(
    ns: String,
    attr: String,
    byteArray: Array[Byte],
  ): Unit = {
    lazy val pathAttr = if (path.isEmpty) attr else path.mkString("", ".", "." + attr)
    byteArray.length match {
      case 0 => ()
      case 1 =>
        nsData.pushDoc.append(pathAttr, new BsonInt32(byteArray.head))
      case _ =>
        lazy val array = new BsonArray()
        byteArray.map(v => array.add(new BsonInt32(v)))
        nsData.pushDoc.append(pathAttr, new BsonDocument("$each", array))
    }
  }

  override protected def updateMapEq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    noValue: Boolean,
    owner: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
    //    exts: List[String],
    //    set2array: Set[Any] => Array[AnyRef],
  ): Unit = {
    //    if (isUpdate) {
    //      if (owner) {
    //        throw ModelError("Can't update non-existing ids of embedded documents in MongoDB.")
    //      }
    //          d.filterElements = d.filterElements :+ AttrOneTacInt(ns, attr) // dummy filter
    //    }
    lazy val pathAttr = if (path.isEmpty) attr else path.mkString("", ".", "." + attr)
    if (map.nonEmpty) {
      val mapDoc = new BsonDocument()
      map.map { case (k, v) =>
        mapDoc.append(validKey(k), transformValue(v).asInstanceOf[BsonValue])
      }
      nsData.setDoc.append(pathAttr, mapDoc)

    } else {
      nsData.setDoc.append(pathAttr, new BsonNull())
    }
  }

  override protected def updateMapAdd[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
  ): Unit = {
    if (map.nonEmpty) {
      map.map { case (k, v) =>
        nsData.setDoc.append(attr + "." + validKey(k), transformValue(v).asInstanceOf[BsonValue])
      }
    }
  }

  override protected def updateMapRemove[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
  ): Unit = {
    map.map { case (k, _) =>
      nsData.unsetDoc.append(attr + "." + validKey(k), new BsonNull())
    }
  }


  override protected def handleIds(ns: String, ids0: Seq[String]): Unit = {
    if (nsData.ids.nonEmpty) {
      throw ModelError(s"Can't apply entity ids twice in update.")
    }
    nsData.ids = ids0
  }

  override protected def handleUniqueFilterAttr(uniqueFilterAttr: AttrOneTac): Unit = {
    //    if (nsData.uniqueFilterElements.nonEmpty) {
    //      throw ModelError(
    //        s"Can only apply one unique attribute value for update. Found:\n" + uniqueFilterAttr
    //      )
    //    }
    //    nsData.uniqueFilterElements += uniqueFilterAttr
    ()
  }

  override protected def handleFilterAttr[T <: Attr with Tacit](filterAttr: T): Unit = {
    nsData.filterElements = nsData.filterElements :+ filterAttr
  }




  // Helpers -------------------------------------------------------------------

  private def updateIterableEq[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    owner: Boolean,
    iterable: M[T],
    transformValue: T => Any,
  ): Unit = {
    if (owner) {
      throw ModelError("Can't update non-existing ids of embedded documents in MongoDB.")
    }
    //    d.filterElements = d.filterElements :+ AttrOneTacInt(ns, attr) // dummy filter

    lazy val pathAttr = if (path.isEmpty) attr else path.mkString("", ".", "." + attr)
    lazy val array    = new BsonArray()
    if (iterable.nonEmpty) {
      iterable.asInstanceOf[Iterable[T]].map(v => array.add(transformValue(v).asInstanceOf[BsonValue]))
      nsData.setDoc.append(pathAttr, array)
    } else {
      nsData.setDoc.append(pathAttr, new BsonNull())
    }
  }

  private def updateIterableAdd[T, M[_] <: Iterable[_]](
    attr: String,
    iterable0: M[T],
    transformValue: T => Any,
  ): Unit = {
    lazy val pathAttr = if (path.isEmpty) attr else path.mkString("", ".", "." + attr)
    val iterable = iterable0.asInstanceOf[Iterable[T]]
    iterable.size match {
      case 0 => ()
      case 1 =>
        nsData.pushDoc.append(pathAttr, transformValue(iterable.head).asInstanceOf[BsonValue])
      case _ =>
        lazy val array = new BsonArray()
        iterable.map(v => array.add(transformValue(v).asInstanceOf[BsonValue]))
        nsData.pushDoc.append(pathAttr, new BsonDocument("$each", array))
    }
  }

  private def updateIterableRemove[T, M[_] <: Iterable[_]](
    attr: String,
    iterable0: M[T],
    transformValue: T => Any,
  ): Unit = {
    lazy val pathAttr = if (path.isEmpty) attr else path.mkString("", ".", "." + attr)
    val vs       = new BsonArray()
    val iterable = iterable0.asInstanceOf[Iterable[T]]
    if (iterable.nonEmpty) {
      iterable.map(v => vs.add(transformValue(v).asInstanceOf[BsonValue]))
    }
    nsData.pullAll.append(pathAttr, vs)
  }
}