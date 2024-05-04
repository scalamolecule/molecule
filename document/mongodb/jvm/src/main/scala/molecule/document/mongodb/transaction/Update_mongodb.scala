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
import scala.annotation.tailrec
import scala.collection.immutable.List
import scala.collection.mutable.ListBuffer

trait Update_mongodb
  extends Base_JVM_mongodb
    with UpdateOps
    with BsonUtils
    with JavaConversions
    with MoleculeLogging { self: ResolveUpdate =>

  doPrint = false

  case class NsData(
    var parent: Option[NsData] = None,
    var ns: String = "",
    var nsPath: List[String] = List.empty[String],
    var refAttrPath: List[String] = List.empty[String],
    setDoc: BsonDocument = new BsonDocument(),
    unsetDoc: BsonDocument = new BsonDocument(),
    pushDoc: BsonDocument = new BsonDocument(),
    addToSet: BsonDocument = new BsonDocument(),
    pullAll: BsonDocument = new BsonDocument(),
    newRefIds: BsonArray = new BsonArray(), // array of BsonObjectId
    refAttrs: BsonArray = new BsonArray(), // array of BsonObjectId
    filterIds: BsonArray = new BsonArray(), // array of BsonObjectId
  ) {
    override def toString: String = {
      val parent1    = if (parent.isEmpty) "None" else s"Some(${parent.get.ns})"
      val newRefIds1 = if (newRefIds.isEmpty) "[]" else {
        newRefIds.iterator().asScala.map(_.asDocument().toJson(pretty)).mkString("[\n", ",\n", "]")
      }
      val refAttrs1  = if (refAttrs.isEmpty) "[]" else {
        refAttrs.iterator().asScala.map(_.asDocument().toJson(pretty)).mkString("[\n", ",\n", "]")
      }
      val filterIds1 = if (filterIds.isEmpty) "[]" else {
        filterIds.iterator().asScala.map(_.asObjectId().getValue.toString).mkString("\n    ", "\n    ", "")
      }
      s"""######################################################################
         |RefData(
         |  parent     : $parent1
         |  ns         : $ns
         |  nsPath     : $nsPath
         |  refAttrPath: $refAttrPath
         |  setDoc     : $setDoc
         |  unsetDoc   : $unsetDoc
         |  pushDoc    : $pushDoc
         |  addToSet   : $addToSet
         |  pullAll    : $pullAll
         |  newRefIds  : $newRefIds1
         |  refAttrs   : $refAttrs1
         |  filterIds  : $filterIds1
         |)
         |----------------------------------------------------------------------""".stripMargin
    }
  }

  private var conn: MongoConn_JVM = null

  private var requiredNsPaths = List(List.empty[String])

  // Initial namespace data container
  private var nsData  = NsData()
  private var nssData = List(nsData)

  private var filterMatchRows = List.empty[BsonDocument]
  private var refsCount       = 0
  private var refIndex        = 0


  protected def noUpdateOwned(owner: Boolean): Unit = if (owner) {
    throw ModelError("Can't update non-existing ids of embedded documents in MongoDB.")
  } else
    ()


  def getData(elements: List[Element], conn0: MongoConn_JVM): Data = {
    conn = conn0
    val ns = getInitialNs(elements)
    nsData.nsPath = List(ns)
    nsData.ns = ns
    nsData.nsPath = List(ns)

    val (filterElements1, requiredNsPaths1) = getFilters(elements.reverse, differentiateOwned = true)
    requiredNsPaths = requiredNsPaths1

    val filters = AttrOneManID(ns, "id", V) :: filterElements1

    println("------ filters --------")
    filters.foreach(println)

    refsCount = filterElements1.count {
      case a: AttrOneOptID => noUpdateOwned(a.owner); true
      case a: AttrSetOptID => noUpdateOwned(a.owner); true
      case _: AttrOneManID => true
      case _: AttrSetManID => true
      case _               => false
    }
    filterMatchRows = new QueryResolveOffset_mongodb[Any](
      filters, None, None, new Model2MongoQuery[Any](filters)
    ).getData(conn).asScala.toList

    // Add top level ids
    filterMatchRows.foreach { row =>
      nsData.filterIds.add(row.getObjectId("_id"))
    }

    println(s"--------- currentDataRows " + filterMatchRows.length)
    filterMatchRows.foreach(row => println(row.toJson(pretty)))

    resolve(elements)

    val newRefIds_ = new BsonDocument()
    val refAttrs_  = new BsonDocument()
    val updateData = new BsonDocument()
      .append("_action", new BsonString("update"))
      .append("_newRefIds", newRefIds_)
      .append("_refAttrs", refAttrs_)

    var adding = false
    nssData.foreach {
      case w@NsData(_, ns, _, _, setDoc, unsetDoc, pushDoc, addToSet, pullAll, newRefIds, refAttrs, filterIds) =>

        println(w)
        //
        //        if (!newRefIds.isEmpty)
        //          newRefIds_.put(ns, newRefIds)
        //
        //        if (!refAttrs.isEmpty)
        //          refAttrs_.put(ns, refAttrs)

        if (!filterIds.isEmpty) {
          val update = new BsonDocument()
          if (!setDoc.isEmpty) {
            update.append("$set", setDoc)
            adding = true
          }

          if (!unsetDoc.isEmpty) {
            update.append("$unset", unsetDoc)
          }

          if (!pushDoc.isEmpty) {
            update.append("$push", pushDoc)
            adding = true
          }

          if (!addToSet.isEmpty) {
            update.append("$addToSet", addToSet)
            adding = true
          }

          if (!pullAll.isEmpty) {
            update.append("$pullAll", pullAll)
          }

          println("%%%%%%%%%  adding: " + adding)


          if (adding && !newRefIds.isEmpty)
            newRefIds_.put(ns, newRefIds)

          if (!refAttrs.isEmpty)
            refAttrs_.put(ns, refAttrs)

          // Only add update data for namespace if there is ids and data to update
          //          if (adding && !update.isEmpty) {
          if (!update.isEmpty) {
            //            println("AAAAA  " + update.toJson(pretty))
            //            println("AAAAA  " + update.keySet())
            //            println("AAAAA  " + (update.keySet().toString == "[$pullAll]"))

            //            if (!refIds.isEmpty)
            //              refIds_.put(ns, refIds)
            //
            //            if (!refAttrs.isEmpty)
            //              refAttrs_.put(ns, refAttrs)

            updateData.append(ns, new BsonDocument()
              .append("filter", new BsonDocument("_id", new BsonDocument("$in", filterIds)))
              .append("update", update)
            )
          }
        }
    }

    //    println("XXXX  " + adding)

    // Cleanup un-used pairs from update data
    //    if (!adding || refIds_.isEmpty) updateData.remove("_newRefIds")
    //    if (!adding || refAttrs_.isEmpty) updateData.remove("_refAttrs")
    if (newRefIds_.isEmpty) updateData.remove("_newRefIds")
    if (refAttrs_.isEmpty) updateData.remove("_refAttrs")


    println("XXXX  " + updateData.toJson(pretty))

    updateData
  }

  @tailrec
  private final def getBaseIds(ns: String, nsData: NsData): List[String] = {
    if (ns == nsData.nsPath.last) {
      nsData.filterIds.iterator().asScala.toList.map(_.asObjectId.getValue.toString)
    } else {
      // Get ids from backref namespace
      getBaseIds(ns, nsData.parent.get)
    }
  }

  override def handleRefNs(ref: Ref): Unit = {
    val Ref(ns, refAttr, refNs, card, owner, _) = ref
    //    nsPath = nsPath ++ List(refAttr, refNs)
    //    refAttrPath = refAttrPath :+ refAttr
    //    println("------------")
    //    println(requiredNsPaths)
    //    println(currentNsPath)

    //    println(s"refIndex $refIndex < knownRefsCount $refsCount")

    if (owner) {
      // Embedded document
      embeddedPath = embeddedPath :+ refAttr
      nsData.nsPath = nsData.nsPath ++ List(refAttr, refNs)
      nsData.refAttrPath = nsData.refAttrPath :+ refAttr

    } else {
      // Referenced document
      val refNsData = NsData(Some(nsData), refNs,
        nsData.nsPath ++ List(refAttr, refNs),
        nsData.refAttrPath :+ refAttr)

      def addNewRefId(id: BsonObjectId): Boolean = {
        // Add single new ref id (regardless of card-one/many)
        val newRefId = new BsonObjectId()
        //        println(s"$id  $ns  $refAttr  NEW ref: " + newRefId.getValue.toString)
        nsData.refAttrs.add(new BsonDocument()
          .append("filter", new BsonDocument("_id", id))
          .append("update", new BsonDocument("$set", new BsonDocument(refAttr, newRefId)))
        )
        refNsData.newRefIds.add(new BsonDocument("_id", newRefId))
        refNsData.filterIds.add(newRefId)
      }


      if (refIndex < refsCount) {
        // Maybe we have a ref

        @tailrec
        def getRef(doc: BsonDocument, embeddedPath: List[String]): BsonValue = {
          embeddedPath match {
            case Nil                 => doc.get(refAttr)
            case prevRefAttr :: tail => getRef(doc.getDocument(prevRefAttr), tail)
          }
        }


        val prefix = if (nsData.refAttrPath.isEmpty) "" else nsData.refAttrPath.mkString("", ".", ".")
        filterMatchRows.foreach { row =>
          val r = getRef(row, embeddedPath)
          //          println("+++++++  " + (prefix + refAttr))
          //          println("+++++++  " + r)
          r match {
            case refId: BsonObjectId  => refNsData.filterIds.add(refId)
            case refDoc: BsonDocument => refNsData.filterIds.add(refDoc.getObjectId("_id"))
            case refIds: BsonArray    =>
              if (refIds.isEmpty)
                addNewRefId(row.getObjectId("_id"))
              else
                refIds.forEach(refId => refNsData.filterIds.add(refId))
            case null                 => addNewRefId(row.getObjectId("_id"))
          }
        }
      } else {
        val ids = getBaseIds(ns, nsData)
        //        println("???????  " + ids)
        val x   = if (card.isInstanceOf[CardOne]) {
          // Optional Set with single card-one ref id
          SpiSync_mongodb.query_get[(String, Option[String])](Query(
            List(
              AttrOneManID(ns, "id", Eq, ids),
              AttrOneOptID(ns, refAttr, V)
            )
          ))(conn).map { case (id, optRefId) => (id, optRefId.map(Set(_))) }
        } else {
          // Optional Set of card-many ref ids
          SpiSync_mongodb.query_get[(String, Option[Set[String]])](Query(
            List(
              AttrOneManID(ns, "id", Eq, ids),
              AttrSetOptID(ns, refAttr, V)
            )
          ))(conn)
        }
        //        println("x  " + x)

        x foreach {
          case (id, None)         => addNewRefId(new BsonObjectId(new ObjectId(id)))
          case (id, Some(refIds)) =>
            // Add current ref ids
            //            println(s"$id  CUR refs: " + refIds)
            refNsData.filterIds.addAll(refIds.map(refId => new BsonObjectId(new ObjectId(refId))).asJava)
        }
      }

      // Continue from updated ref namespace
      nsData = refNsData
      nssData = nssData :+ nsData
      refIndex += 1
      embeddedPath = Nil
    }
  }

  private def query(elements: List[Element]): List[String] = {
    SpiSync_mongodb.query_get[String](Query(elements))(conn)
  }

  override protected def handleBackRef(backRef: BackRef): Unit = {
    nsData.parent.fold[Unit] {
      embeddedPath = embeddedPath.init
    } { parent =>
      embeddedPath = Nil
      nsData = parent
      refIndex -= 1
    }
  }

  override protected def updateOne[T](
    ns: String,
    attr: String,
    owner: Boolean,
    vs: Seq[T],
    transformValue: T => Any,
  ): Unit = {
    //    if (owner) {
    //      throw ModelError("Can't update non-existing ids of embedded documents in MongoDB.")
    //    }
    noUpdateOwned(owner)
    lazy val pathAttr = if (embeddedPath.isEmpty) attr else embeddedPath.mkString("", ".", "." + attr)
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
    updateIterableEq(attr, owner, set, transformValue)
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
    updateIterableEq(attr, owner, seq, transformValue)
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
    lazy val pathAttr = if (embeddedPath.isEmpty) attr else embeddedPath.mkString("", ".", "." + attr)
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
    lazy val pathAttr = if (embeddedPath.isEmpty) attr else embeddedPath.mkString("", ".", "." + attr)
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
      val prefix = if (embeddedPath.isEmpty) "" else embeddedPath.mkString("", ".", ".")
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
    val prefix = if (embeddedPath.isEmpty) "" else embeddedPath.mkString("", ".", ".")
    map.map { case (k, _) =>
      nsData.unsetDoc.append(prefix + attr + "." + validKey(k), new BsonNull())
    }
  }

  override protected def handleIds(ns: String, ids: Seq[String]): Unit = ()
  override protected def handleUniqueFilterAttr(uniqueFilterAttr: AttrOneTac): Unit = ()
  override protected def handleFilterAttr[T <: Attr with Tacit](filterAttr: T): Unit = ()


  // Helpers -------------------------------------------------------------------

  private def updateIterableEq[T, M[_] <: Iterable[_]](
    attr: String,
    owner: Boolean,
    iterable: M[T],
    transformValue: T => Any,
  ): Unit = {
    noUpdateOwned(owner)
    lazy val pathAttr = if (embeddedPath.isEmpty) attr else embeddedPath.mkString("", ".", "." + attr)
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
    lazy val pathAttr = if (embeddedPath.isEmpty) attr else embeddedPath.mkString("", ".", "." + attr)
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
    lazy val pathAttr = if (embeddedPath.isEmpty) attr else embeddedPath.mkString("", ".", "." + attr)
    val vs       = new BsonArray()
    val iterable = iterable0.asInstanceOf[Iterable[T]]
    if (iterable.nonEmpty) {
      iterable.map(v => vs.add(transformValue(v).asInstanceOf[BsonValue]))
    }
    nsData.pullAll.append(pathAttr, vs)
  }
}