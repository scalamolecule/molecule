package molecule.document.mongodb.transaction

import molecule.base.ast.CardOne
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
import molecule.document.mongodb.util.BsonUtils
import org.bson._
import org.bson.types.ObjectId
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

  private var topLevelIds = List.empty[String]
  private val refIds      = new BsonDocument()
  private val refAttrs    = new BsonDocument()

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

    val (filterElements1, requiredNsPaths1) = getFilters(elements.reverse, differentiateOwned = true)
    requiredNsPaths = requiredNsPaths1

    val filters = AttrOneManID(getInitialNs(elements), "id", V) :: filterElements1

    println("------ filters --------")
    filters.foreach(println)
    //    println("------ requiredNsPaths: " + requiredNsPaths)

    val filters1 = filters.dropRight(2) :+ filters.last

    //    println("------ filters1 --------")
    //    filters1.foreach(println)

    val currentDataRows = new QueryResolveOffset_mongodb[Any](
      filters, None, None, new Model2MongoQuery[Any](filters)
    ).getData(conn)

    var ids = List.empty[String]
    currentDataRows.forEach(row => ids = ids :+ row.getObjectId("_id").getValue.toString)
    nsData.ids = ids

    println("--------- currentDataRows")
    currentDataRows.forEach(row => println(row.toJson(pretty)))
    println(ids)
    println("----------")

    //    println("--------- filter")
    //    val (collectionName, pipeline) = new Model2MongoQuery[Any](filters).getBsonQuery(Nil, None, None)
    //    val filter = pipeline.get(0).toBsonDocument().getDocument("$match")
    //    println(filter.toJson(pretty))
    //    println("----------")


    resolve(elements)
    val updateData = new BsonDocument()
      .append("_action", new BsonString("update"))

    if (!refIds.isEmpty)
      updateData.append("_refIds", refIds)

    if (!refAttrs.isEmpty)
      updateData.append("_refAttrs", refAttrs)

    nssData.foreach {
      case NsData(_, ns, setDoc, unsetDoc, pushDoc, addToSet, pullAll, arrayFilters, ids, filterElements) =>
        val ids1 = if (ids.nonEmpty) {
          println(s"1 --- $ns: " + ids.toList)
          ids
        } else {
          val filterElements1 = AttrOneManID(ns, "id", V) +: filterElements
          println(s"2 --- filter elements: ")
          filterElements1.foreach(println)

          val ids = query(filterElements1)
          println(s"2 --- $ns: " + ids)
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

          lazy val nsData = new BsonDocument()
            .append("filter", filter)
            .append("update", update)

          if (!arrayFilters.isEmpty)
            nsData.append("arrayFilters", arrayFilters)

          // Only add update data for namespace if there is ids and data to update
          if (!update.isEmpty) {
            //            println(nsData.toJson(pretty))
            updateData.append(ns, nsData)
          }
        }
    }
    updateData
  }

  override def handleRefNs(ref: Ref): Unit = {
    val Ref(ns, refAttr, refNs, card, owner, _) = ref
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

      val currentRefIds0 = SpiSync_mongodb.query_get[Option[String]](Query(
        List(
          AttrOneTacID(ns, "id", Eq, nsData.ids),
          if (card.isInstanceOf[CardOne])
            AttrOneOptID(ns, refAttr, V)
          else
            AttrSetOptID(ns, refAttr, V)
        )
      ))(conn)

      println("nsData.filterElements: " + nsData.filterElements)
      println("nsData.ids           : " + nsData.ids)
      println("currentRefIds0       : " + currentRefIds0)

      val curRefIds   = new BsonArray()
      val curRefAttrs = new BsonArray()
      var i           = -1
      val allRefIds   = currentRefIds0.map {
        case Some(id) =>
          println("---------  " + id)

          id
        case None     =>
          i += 1
          val refId = new BsonObjectId()

          println("... " + refId)
          // Add id of referenced document to be created
          curRefIds.add(new BsonDocument("_id", refId))

          // Add reference from current document to reference document
          curRefAttrs.add(new BsonDocument()
            .append("filter", new BsonDocument("_id", new BsonObjectId(new ObjectId(nsData.ids(i)))))
            .append("update", new BsonDocument("$set", new BsonDocument(refAttr, refId)))
          )

          // Add soon to be inserted ref id
          refId.asObjectId.getValue.toString
      }

      if (!curRefIds.isEmpty) {
        // refs
        if (refIds.containsKey(refNs)) {
          val allRefIds = refIds.getArray(refNs)
          allRefIds.addAll(curRefIds)
          refIds.put(refNs, allRefIds)
        } else {
          refIds.append(refNs, curRefIds)
        }

        // ref attributes
        if (refAttrs.containsKey(ns)) {
          val allRefAttrs = refAttrs.getArray(ns)
          allRefAttrs.addAll(curRefAttrs)
          refAttrs.put(ns, allRefAttrs)
        } else {
          refAttrs.append(ns, curRefAttrs)
        }
      }

      // Continue from ref namespace
      val referee = Some(nsData)
      path = Nil
      nsData = NsData(referee, refNs, ids = allRefIds)
      nssData += nsData
    }
  }

  private def query(elements: List[Element]): List[String] = {
    SpiSync_mongodb.query_get[String](Query(elements))(conn)
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
    if (byteArray.isEmpty) {
      () // no update
    } else {
      lazy val array = new BsonArray()
      byteArray.map(v => array.add(new BsonInt32(v)))
      nsData.setDoc.append(pathAttr, array)
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
  ): Unit = {
    // Add dummy filter to ensure presence of attr data
    nsData.filterElements = nsData.filterElements :+ AttrOneTacInt(ns, attr)

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
      val prefix = if (path.isEmpty) "" else path.mkString("", ".", ".")
      map.map { case (k, v) =>
        nsData.setDoc.append(prefix + attr + "." + validKey(k), transformValue(v).asInstanceOf[BsonValue])
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
    val prefix = if (path.isEmpty) "" else path.mkString("", ".", ".")
    map.map { case (k, _) =>
      nsData.unsetDoc.append(prefix + attr + "." + validKey(k), new BsonNull())
      //      nsData.unsetDoc.append(attr + "." + validKey(k), new BsonNull())
    }
  }


  override protected def handleIds(ns: String, ids0: Seq[String]): Unit = {
    //    if (nsData.ids.nonEmpty) {
    //      throw ModelError(s"Can't apply entity ids twice in update.")
    //    }
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

    // Add dummy filter to ensure presence of attr data
    nsData.filterElements = nsData.filterElements :+ AttrOneTacInt(ns, attr)

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