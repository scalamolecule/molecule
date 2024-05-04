package molecule.document.mongodb.transaction

import molecule.base.ast.{Card, CardOne}
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
import scala.collection.mutable.ListBuffer

trait Update_mongodbOLD3
  extends Base_JVM_mongodb
    with UpdateOps
    with BsonUtils
    with JavaConversions
    with MoleculeLogging { self: ResolveUpdate =>

  doPrint = false

  case class NsData(
    var parent: Option[NsData] = None,
    var path: List[String] = List.empty[String],
    var ns: String = "",
    setDoc: BsonDocument = new BsonDocument(),
    unsetDoc: BsonDocument = new BsonDocument(),
    pushDoc: BsonDocument = new BsonDocument(),
    addToSet: BsonDocument = new BsonDocument(),
    pullAll: BsonDocument = new BsonDocument(),
    refIds: BsonArray = new BsonArray(),
    refAttrs: BsonArray = new BsonArray(),
    filterIds: BsonArray = new BsonArray(),
  ) {
    override def toString: String = {
      val parent1    = if (parent.isEmpty) "None" else s"Some(${parent.get.ns})"
      val refIds1    = if (refIds.isEmpty) "[]" else {
        refIds.iterator().asScala.map(_.asDocument().toJson(pretty)).mkString("[\n", ",\n", "]")
      }
      val refAttrs1  = if (refAttrs.isEmpty) "[]" else {
        refAttrs.iterator().asScala.map(_.asDocument().toJson(pretty)).mkString("[\n", ",\n", "]")
      }
      val filterIds1 = if (filterIds.isEmpty) "[]" else {
        filterIds.iterator().asScala.map(_.asObjectId().getValue.toString).mkString("\n    ", "\n    ", "")
      }
      s"""######################################################################
         |RefData(
         |  parent   : $parent1
         |  path     : $path
         |  ns       : $ns
         |  setDoc   : $setDoc
         |  unsetDoc : $unsetDoc
         |  pushDoc  : $pushDoc
         |  addToSet : $addToSet
         |  pullAll  : $pullAll
         |  refIds   : $refIds1
         |  refAttrs : $refAttrs1
         |  filterIds: $filterIds1
         |)
         |----------------------------------------------------------------------""".stripMargin
    }
  }

  private val rowResolvers    = ListBuffer.empty[BsonDocument => Unit]
  private val topRowResolvers = ListBuffer.empty[(BsonDocument, Int) => Unit]

  private var requiredNsPaths = List(List.empty[String])
  private var currentNsPath   = List.empty[String]

  private var topLevelIds = List.empty[String]
  private val refIds      = new BsonDocument()
  private val refAttrs    = new BsonDocument()

  // Initial namespace data container
  var nsData  = NsData()
  val nssData = ListBuffer.empty[NsData] += nsData
  private var nsPath = List.empty[String]

  var conn: MongoConn_JVM = null

  private var firstRow  = true
  private var ids       = List.empty[BsonObjectId]
  private var idLists   = List.empty[List[BsonObjectId]]
  private var rowSize   = 0
  private var attrIndex = 0

  def getData(elements: List[Element], conn0: MongoConn_JVM): Data = {
    conn = conn0
    val ns = getInitialNs(elements)
    nsData.path = List(ns)
    nsData.ns = ns


    val (filterElements1, requiredNsPaths1) = getFilters(elements.reverse, differentiateOwned = true)
    requiredNsPaths = requiredNsPaths1

    val filters = AttrOneManID(ns, "id", V) :: filterElements1

    println("------ filters --------")
    filters.foreach(println)

    val filterMatchRows = new QueryResolveOffset_mongodb[Any](
      filters, None, None, new Model2MongoQuery[Any](filters)
    ).getData(conn)

    filterMatchRows.forEach { row =>
      nsData.filterIds.add(row.getObjectId("_id"))
    }

    println("--------- currentDataRows")
    filterMatchRows.forEach(row => println(row.toJson(pretty)))
    println("----------")

    resolve(elements)

    // Resolve row updates
    var rowIndex = 0
    filterMatchRows.forEach { topRow =>
      nsData = nssData.head
      nsPath = List(ns)
      topRowResolvers.foreach { resolve =>
        resolve(topRow, rowIndex)
      }
      rowIndex += 1
      firstRow = false
    }

    val refIdNss   = new BsonDocument()
    val refAttrNss = new BsonDocument()
    val updateData = new BsonDocument()
      .append("_action", new BsonString("update"))
      .append("_newRefIds", refIdNss)
      .append("_refAttrs", refAttrNss)

    nssData.foreach {
      case w@NsData(_, path, ns, setDoc, unsetDoc, pushDoc, addToSet, pullAll, refIds, refAttrs, filterIds) =>

        println(w)

        if (!refIds.isEmpty)
          refIdNss.put(ns, refIds)

        if (!refAttrs.isEmpty)
          refAttrNss.put(ns, refAttrs)

        if (!filterIds.isEmpty) {
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

          // Only add update data for namespace if there is ids and data to update
          if (!update.isEmpty) {
            val nsData = new BsonDocument()
              .append("filter", new BsonDocument("_id", new BsonDocument("$in", filterIds)))
              .append("update", update)

            //            println(nsData.toJson(pretty))
            updateData.append(ns, nsData)
          }
        }
    }

    if (refIdNss.isEmpty)
      updateData.remove("_newRefIds")

    if (refAttrNss.isEmpty)
      updateData.remove("_refAttrs")

    updateData
  }


  private def getRefNsData(refNs: String): NsData = {
    if (firstRow) {
      nsPath = nsPath :+ refNs
      val newRefNsData = NsData(Some(nsData), nsPath, refNs)
      nssData += newRefNsData
      newRefNsData
    } else {
      nssData.last
    }
  }

  private def getCurRefIds(ns: String, refAttr: String, card: Card): BsonValue => Option[Set[String]] = {
    if (card.isInstanceOf[CardOne]) {
      // Optional Set with single card-one ref id
      (filterId: BsonValue) =>
        SpiSync_mongodb.query_get[Option[String]](Query(
          List(
            AttrOneTacID(ns, "id", Eq, List(filterId.asObjectId.getValue.toString)),
            AttrOneOptID(ns, refAttr, V)
          )
        ))(conn).headOption.flatMap(_.map(Set(_)))
    } else {
      // Optional Set of card-many ref ids
      (filterId: BsonValue) =>
        SpiSync_mongodb.query_get[Option[Set[String]]](Query(
          List(
            AttrOneTacID(ns, "id", Eq, List(filterId.asObjectId.getValue.toString)),
            AttrSetOptID(ns, refAttr, V)
          )
        ))(conn).headOption.flatten
    }
  }

  override def handleRefNs(ref: Ref): Unit = {
    val Ref(ns, refAttr, refNs, card, owner, _) = ref
    currentNsPath = currentNsPath match {
      case Nil => List(ns, refAttr, refNs)
      case cur => cur ++ List(refAttr, refNs)
    }
    //    println("------------")
    //    println(requiredNsPaths)
    //    println(currentNsPath)

    if (owner) {
      // Embedded document

      topRowResolvers += { (_: BsonDocument, _: Int) =>
        if (firstRow) {
          embeddedPath = embeddedPath :+ refAttr
          //          nsData.ns = refNs
        }
      }

    } else {
      // Referenced document

      val pathRefAttr = if (nsPath.isEmpty) refAttr else nsPath.mkString("", ".", s".$refAttr")
      lazy val curRefIds = getCurRefIds(ns, refAttr, card)

      topRowResolvers += { (topRow: BsonDocument, topRowIndex: Int) =>
        val refNsData = getRefNsData(refNs)

        topRow.get(pathRefAttr) match {
          case refId: BsonObjectId  => refNsData.filterIds.add(refId)
          case refDoc: BsonDocument => refNsData.filterIds.add(refDoc.getObjectId("_id"))
          case null                 =>
            @tailrec
            def getBaseId(nsData: NsData): BsonValue = {
              if (nsData.path.last == ns) {
                nsData.filterIds.get(topRowIndex)
              } else {
                // Backref id
                getBaseId(nsData.parent.get)
              }
            }
            val id = getBaseId(nsData)

            curRefIds(id) match {
              case None =>
                // Add single new ref id (regardless of card-one/many)
                val newRefId = new BsonObjectId()

                println(s"$id  $ns  $refAttr  NEW refs: " + List(newRefId.getValue.toString))

                nsData.refAttrs.add(new BsonDocument()
                  .append("filter", new BsonDocument("_id", topRow.get("_id")))
                  .append("update", new BsonDocument("$set", new BsonDocument(refAttr, newRefId)))
                )
                refNsData.refIds.add(new BsonDocument("_id", newRefId))
                refNsData.filterIds.add(newRefId)

              case Some(refIds) =>
                // Add current ref ids
                println(s"$id  CUR refs: " + refIds)
                refNsData.filterIds.addAll(refIds.map(id => new BsonObjectId(new ObjectId(id))).asJava)
            }
        }

        // Continue from updated ref namespace
        nsData = refNsData
      }
    }
  }

  private def query(elements: List[Element]): List[String] = {
    SpiSync_mongodb.query_get[String](Query(elements))(conn)
  }

  override protected def handleBackRef(backRef: BackRef): Unit = {
    if (firstRow) {
      nsData.parent.fold[Unit] {
        //        embeddedPath = embeddedPath.init
        //      nsData.filterElements = nsData.filterElements :+ backRef
      } { parent =>
        //      embeddedPath = Nil
        nsPath = nsPath.init
        nsData = parent
      }
    }
  }

  override protected def updateOne[T](
    ns: String,
    attr: String,
    owner: Boolean,
    vs: Seq[T],
    transformValue: T => Any,
  ): Unit = {
    topRowResolvers += { (_, _) =>
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
      attrIndex += 1
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
      topRowResolvers += { (_, _) =>
        nsData.setDoc.append(pathAttr, array)
      }
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
    topRowResolvers += { (_, _) =>
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
      topRowResolvers += { (_, _) =>
        map.map { case (k, v) =>
          nsData.setDoc.append(prefix + attr + "." + validKey(k), transformValue(v).asInstanceOf[BsonValue])
        }
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
    topRowResolvers += { (_, _) =>
      map.map { case (k, _) =>
        nsData.unsetDoc.append(prefix + attr + "." + validKey(k), new BsonNull())
      }
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
    if (owner) {
      throw ModelError("Can't update non-existing ids of embedded documents in MongoDB.")
    }
    topRowResolvers += { (_, _) =>
      lazy val pathAttr = if (embeddedPath.isEmpty) attr else embeddedPath.mkString("", ".", "." + attr)
      lazy val array    = new BsonArray()
      if (iterable.nonEmpty) {
        iterable.asInstanceOf[Iterable[T]].map(v => array.add(transformValue(v).asInstanceOf[BsonValue]))
        nsData.setDoc.append(pathAttr, array)
      } else {
        nsData.setDoc.append(pathAttr, new BsonNull())
      }
    }
  }

  private def updateIterableAdd[T, M[_] <: Iterable[_]](
    attr: String,
    iterable0: M[T],
    transformValue: T => Any,
  ): Unit = {
    topRowResolvers += { (_, _) =>
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
  }

  private def updateIterableRemove[T, M[_] <: Iterable[_]](
    attr: String,
    iterable0: M[T],
    transformValue: T => Any,
  ): Unit = {
    topRowResolvers += { (_, _) =>
      lazy val pathAttr = if (embeddedPath.isEmpty) attr else embeddedPath.mkString("", ".", "." + attr)
      val vs       = new BsonArray()
      val iterable = iterable0.asInstanceOf[Iterable[T]]
      if (iterable.nonEmpty) {
        iterable.map(v => vs.add(transformValue(v).asInstanceOf[BsonValue]))
      }
      nsData.pullAll.append(pathAttr, vs)
    }
  }
}