package molecule.document.mongodb.transaction

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.action.Query
import molecule.core.transaction.ResolveUpdate
import molecule.core.transaction.ops.UpdateOps
import molecule.document.mongodb.facade.MongoConn_JVM
import molecule.document.mongodb.spi.SpiSync_mongodb
import org.bson.types.ObjectId
import org.bson._
import org.bson.conversions.Bson
import scala.collection.mutable.ListBuffer

trait Update_mongodb
  extends Base_JVM_mongodb
    with UpdateOps
    with MoleculeLogging { self: ResolveUpdate =>

  doPrint = false

  case class RefData(
    var referee: Option[RefData] = None,
    var ns: String = "",
    setDoc: BsonDocument = new BsonDocument(),
    pushDoc: BsonDocument = new BsonDocument(),
    addToSet: BsonDocument = new BsonDocument(),
    pullAll: BsonDocument = new BsonDocument(),
    arrayFilters: BsonArray = new BsonArray(),
    var ids: Seq[String] = Seq.empty[String],
    var filterElements: List[Element] = List.empty[Element],
    uniqueFilterElements: ListBuffer[Element] = ListBuffer.empty[Element],
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
         |  update              : $update
         |  arrayFilters        : $arrayFilters
         |  ids                 : $ids
         |  filterElements      : $filterElements1
         |  uniqueFilterElements: $uniqueFilterElements1
         |)""".stripMargin
    }
  }

  // Initial namespace data container
  var d  = RefData()
  val dd = ListBuffer.empty[RefData].addOne(d)

  var conn: MongoConn_JVM = null

  def getData(elements: List[Element], conn0: MongoConn_JVM): Data = {
    conn = conn0
    d.ns = getInitialNs(elements)
    resolve(elements)
    val updateData = new BsonDocument("_action", new BsonString("update"))
    dd.foreach {
      case x@RefData(_, ns, setDoc, pushDoc, addToSet, pullAll, arrayFilters,
      ids, filterElements, uniqueFilterElements) =>

        println(x)

        val ids1 = if (isUpdate) {
          if (ids.nonEmpty && filterElements.nonEmpty) {
            println("A")
            val filterIds = query(AttrOneManID(ns, "id", Eq, ids) +: filterElements)
            println("A ------ filterIds: " + filterIds)
            val validIds = ids.intersect(filterIds)
            println("A ------ validIds : " + validIds)
            validIds

          } else if (ids.nonEmpty && uniqueFilterElements.nonEmpty) {
            println("B")
            val filterIds = query(AttrOneManID(ns, "id", Eq, ids) +: uniqueFilterElements.toList)
            println("B ------ uniqueFilterIds: " + filterIds)
            val validIds = ids.intersect(filterIds)
            println("B ------ validIds       : " + validIds)
            validIds

          } else if (uniqueFilterElements.nonEmpty) {
            println("C")
            val filterIds = query(AttrOneManID(ns, "id", V) +: uniqueFilterElements.toList)
            println("C ------ filterIds: " + filterIds)
            filterIds

          } else if (filterElements.nonEmpty) {
            println("D")
            val filterIds = query(AttrOneManID(ns, "id", V) +: filterElements.toList)
            println("D ------ filterIds: " + filterIds)
            filterIds
          } else {
            println("E ------ ")
            ids
          }
        } else {
          if (ids.nonEmpty) {
            println("F")
            //          throw ModelError("f")
            ids
          } else if (filterElements.nonEmpty) {
            //          throw ModelError("g")
            println("G")
            val filterIds = query(AttrOneManID(ns, "id", V) +: filterElements.toList)
            println("G ------ filterIds: " + filterIds)
            filterIds
          } else if (uniqueFilterElements.nonEmpty) {
            throw ModelError("h")
            println("H")
            val filterIds = query(AttrOneManID(ns, "id", V) +: uniqueFilterElements.toList)
            println("H ------ uniqueFilterIds: " + filterIds)
            filterIds
          } else {
            throw ModelError("i")
            println("I")
            Nil
          }
        }

        if (ids1.nonEmpty) {
          val idArray = new BsonArray()
          ids1.foreach(id => idArray.add(new BsonObjectId(new ObjectId(id))))
          val filter = new BsonDocument("_id", new BsonDocument("$in", idArray))

          val update = new BsonDocument()
          if (!setDoc.isEmpty)
            update.append("$set", setDoc)

          if (!pushDoc.isEmpty)
            update.append("$push", pushDoc)

          if (!addToSet.isEmpty)
            update.append("$addToSet", addToSet)

          if (!pullAll.isEmpty)
            update.append("$pullAll", pullAll)

          val nsData = new BsonDocument()
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
      d.filterElements = d.filterElements :+ ref
      path = path :+ refAttr

    } else {
      // Referenced document

      if (d.filterElements.nonEmpty && d.filterElements.last.isInstanceOf[BackRef]) {
        // Remove BackRef orphan
        d.filterElements = d.filterElements.init
      }

      // Retrieve reference ids (could be done in one query for all refs...)
      val refIds = query(List(
        AttrOneTacID(ns, "id", Eq, d.ids),
        AttrOneManID(ns, refAttr, V),
      ))
      path = Nil

      // Process referenced documents
      val referee = Some(d)
      d = RefData(referee, refNs, ids = refIds)
      dd += d
    }
  }

  override def updateOne[T](
    ns: String,
    attr: String,
    vs: Seq[T],
    owner: Boolean,
    transformValue: T => Any,
    handleValue: T => Any,
  ): Unit = {
    if (isUpdate) {
      if (owner) {
        throw ModelError("Can't update non-existing ids of embedded documents in MongoDB.")
      }
      d.filterElements = d.filterElements :+ AttrOneTacInt(ns, attr) // dummy filter
    }
    lazy val pathAttr = if (path.isEmpty) attr else path.mkString("", ".", "." + attr)
    vs match {
      case Seq(v) =>
        //        set(pathAttr, transformValue(v).asInstanceOf[BsonValue])
        d.setDoc.append(pathAttr, transformValue(v).asInstanceOf[BsonValue])
      case Nil    =>
        //        set(pathAttr, new BsonNull())
        d.setDoc.append(pathAttr, new BsonNull())
      case vs     =>
        val cleanAttr = attr.replace("_", "")
        throw ExecutionError(
          s"Can only $update one value for attribute `$ns.$cleanAttr`. Found: " + vs.mkString(", ")
        )
    }
  }

  override def updateSetEq[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
    refNs: Option[String],
    owner: Boolean,
    transform: T => Any,
    set2array: Set[Any] => Array[AnyRef],
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    if (isUpdate) {
      if (owner) {
        throw ModelError("Can't update non-existing ids of embedded documents in MongoDB.")
      }
      d.filterElements = d.filterElements :+ AttrOneTacInt(ns, attr) // dummy filter
    }
    lazy val pathAttr = if (path.isEmpty) attr else path.mkString("", ".", "." + attr)
    lazy val array    = new BsonArray()
    sets match {
      case Seq(vs) =>
        vs.map(v => array.add(transform(v).asInstanceOf[BsonValue]))
        d.setDoc.append(pathAttr, array)
      case Nil     =>
        d.setDoc.append(pathAttr, new BsonNull())
      case vs      =>
        val cleanAttr = attr.replace("_", "")
        throw ExecutionError(
          s"Can only $update one Set of values for Set attribute `$ns.$cleanAttr`. Found: " + vs.mkString(", ")
        )
    }
  }

  override def updateSetAdd[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
    refNs: Option[String],
    owner: Boolean,
    transform: T => Any,
    set2array: Set[Any] => Array[AnyRef],
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    lazy val pathAttr = if (path.isEmpty) attr else path.mkString("", ".", "." + attr)
    sets match {
      case Seq(vs) =>
        vs.size match {
          case 0 => ()
          case 1 =>
            d.pushDoc.append(pathAttr, transform(vs.head).asInstanceOf[BsonValue])
          case _ =>
            lazy val array = new BsonArray()
            vs.map(v => array.add(transform(v).asInstanceOf[BsonValue]))
            d.pushDoc.append(pathAttr, new BsonDocument("$each", array))
        }
      case Nil     => ()
      case vs      =>
        val cleanAttr = attr.replace("_", "")
        throw ExecutionError(
          s"Can only $update one Set of values for Set attribute `$ns.$cleanAttr`. Found: " + vs.mkString(", ")
        )
    }
  }

  override def updateSetSwap[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
    refNs: Option[String],
    owner: Boolean,
    transform: T => Any,
    handleValue: T => Any,
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
    one2json: T => String
  ): Unit = {
    val (retracts0, adds0) = sets.splitAt(sets.length / 2)
    val (retracts, adds)   = (retracts0.flatten, adds0.flatten)
    val swaps              = retracts.zip(adds)
    if (retracts.isEmpty) {
      // Do nothing if no swap pairs
      return
    }
    if (retracts.length != retracts.distinct.length) {
      throw ExecutionError(s"Can't swap from duplicate retract values.")
    }
    if (adds.length != adds.distinct.length) {
      throw ExecutionError(s"Can't swap to duplicate replacement values.")
    }
    if (retracts.size != adds.size) {
      throw ExecutionError(
        s"""Can't swap duplicate keys/values:
           |  RETRACTS: $retracts
           |  ADDS    : $adds
           |""".stripMargin
      )
    }

    lazy val pathAttr = if (path.isEmpty) attr else path.mkString("", ".", "." + attr)
    if (isUpdate) {
      swaps.size match {
        case 0 => ()
        case 1 =>
          val (retract, add) = swaps.head
          d.setDoc.append(s"$pathAttr.$$[$pathAttr]", transform(add).asInstanceOf[BsonValue])
          d.arrayFilters.add(
            new BsonDocument(pathAttr, transform(retract).asInstanceOf[BsonValue])
          )
        case _ =>
          swaps.zipWithIndex.foreach { case ((retract, add), i) =>
            val retractField = pathAttr + i
            val addField     = pathAttr + ".$[" + retractField + s"]"
            d.setDoc.append(addField, transform(add).asInstanceOf[BsonValue])
            d.arrayFilters.add(
              new BsonDocument(retractField, transform(retract).asInstanceOf[BsonValue])
            )
          }
      }
    } else {
      swaps.size match {
        case 0 => ()
        case 1 =>
          val (_, add) = swaps.head
          d.addToSet.append(pathAttr, transform(add).asInstanceOf[BsonValue])
        case _ =>
          val adds = new BsonArray()
          swaps.foreach { case (_, add) =>
            adds.add(transform(add).asInstanceOf[BsonValue])
          }
          d.addToSet.append(pathAttr, new BsonDocument("$each", adds))
      }
    }
  }

  override def updateSetRemove[T](
    ns: String,
    attr: String,
    set: Set[T],
    refNs: Option[String],
    owner: Boolean,
    transform: T => Any,
    handleValue: T => Any,
    exts: List[String],
    one2json: T => String
  ): Unit = {
    lazy val pathAttr = if (path.isEmpty) attr else path.mkString("", ".", "." + attr)
    val vs = new BsonArray()
    if (set.nonEmpty) {
      set.map(v => vs.add(transform(v).asInstanceOf[BsonValue]))
    }
    d.pullAll.append(pathAttr, vs)
  }


  override def handleIds(ns: String, ids0: Seq[String]): Unit = {
    if (d.ids.nonEmpty) {
      throw ModelError(s"Can't apply entity ids twice in $update.")
    }
    d.ids = ids0
  }

  override def handleUniqueFilterAttr(uniqueFilterAttr: AttrOneTac): Unit = {
    if (d.uniqueFilterElements.nonEmpty) {
      throw ModelError(
        s"Can only apply one unique attribute value for $update. Found:\n" + uniqueFilterAttr
      )
    }
    d.uniqueFilterElements += uniqueFilterAttr
  }

  override def handleFilterAttr(filterAttr: AttrOneTac): Unit = {
    d.filterElements = d.filterElements :+ filterAttr
  }


  override def handleBackRef(backRef: BackRef): Unit = {
    d.referee.fold[Unit] {
      path = path.init
      d.filterElements = d.filterElements :+ backRef
    } { referee =>
      path = Nil
      d = referee
    }
  }
}