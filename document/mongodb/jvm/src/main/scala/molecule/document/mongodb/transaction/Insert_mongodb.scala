package molecule.document.mongodb.transaction

import java.util
import molecule.base.ast._
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ops.InsertOps
import molecule.core.transaction.{InsertResolvers_, ResolveInsert}
import molecule.core.util.ModelUtils
import org.bson._
import org.bson.types.ObjectId
import scala.collection.mutable

trait Insert_mongodb
  extends Base_JVM_mongodb
    with InsertOps
    with DataType_JVM_mongodb
    with ModelUtils
    with MoleculeLogging { self: ResolveInsert with InsertResolvers_ =>

  doPrint = true

  def debug(obj: Any, s: String = ""): Unit = if (false) {
    val id  = System.identityHashCode(obj)
    val idP = id + padS(10, id.toString)
    val txt = s + padS(12, s)
    println(s"$idP  $txt  $obj")
  }

  def getData(nsMap: Map[String, MetaNs], elements: List[Element], tpls: Seq[Product]): Data = {
    initialNs = getInitialNs(elements)
    nss += initialNs
    val tpl2bson = getResolver(nsMap, elements)

    // Prepare adding ns Data to namespaces
    val nssDocs = mutable.Map.empty[String, (Int, BsonArray)]
    nss.zipWithIndex.foreach { case (ns, i) => nssDocs(ns) = (i, new BsonArray()) }

    var first  = true
    var nsData = new BsonArray()

    tpls.foreach { tpl =>
      level = 0
      doc = new BsonDocument()
      docs = List(List(doc))
      nsData = new BsonArray()
      nsData.add(doc)
      nsDocs.clear()
      nsIndex = 0
      nsDocs(initialNs) = (nsIndex, nsData)

      // Convert tpl to bson
      tpl2bson(tpl)

      // Add docs in namespaces
      nsDocs.toList.sortBy(_._2._1).foreach { case (ns, (_, nsData)) =>
        nssDocs(ns)._2.addAll(nsData)
      }
      first = false
    }

    val data = new BsonDocument("_action", new BsonString("insert"))

    val nssDocsList = nssDocs.toList.sortBy(_._2._1)
    if (!nssDocsList.head._2._2.isEmpty) {
      data.append("_selfJoins", new BsonInt32(selfJoins))
      // Loop referenced namespaces
      nssDocsList.collect {
        case (ns, (_, nsDocs: BsonArray)) if !nsDocs.isEmpty =>
          data.append(ns, nsDocs)
      }
    }
    data
  }

  override protected def addOne[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil
  ): Product => Unit = {
    (tpl: Product) => {
      doc.append(
        attr,
        transformValue(tpl.productElement(tplIndex).asInstanceOf[T]).asInstanceOf[BsonValue]
      )
      debug(doc, "add")
    }
  }

  override protected def addOneOpt[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil
  ): Product => Unit = {
    (tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case Some(scalaValue) => doc.append(
          attr,
          transformValue(scalaValue.asInstanceOf[T]).asInstanceOf[BsonValue]
        )
        case None             => ()
      }
    }
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addIterable(attr, tplIndex, transformValue)
  }

  override protected def addSetOpt[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addOptIterable(attr, tplIndex, transformValue)
  }


  override protected def addSeq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addIterable(attr, tplIndex, transformValue)
  }

  override protected def addSeqOpt[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addOptIterable(attr, tplIndex, transformValue)
  }

  override protected def addByteArray(
    ns: String,
    attr: String,
    tplIndex: Int,
  ): Product => Unit = {
    (tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case byteArray: Array[_] if byteArray.nonEmpty =>
          // Can't get BsonBinary working - todo
          // doc.append(attr, new BsonBinary(byteArray))

          val array: util.ArrayList[BsonValue] = new util.ArrayList[BsonValue]()
          byteArray.asInstanceOf[Array[Byte]].map(byte => array.add(new BsonInt32(byte)))
          doc.append(attr, new BsonArray(array))

        case Some(byteArray: Array[_]) if !byteArray.isEmpty =>
          val array: util.ArrayList[BsonValue] = new util.ArrayList[BsonValue]()
          byteArray.asInstanceOf[Array[Byte]].map(byte => array.add(new BsonInt32(byte)))
          doc.append(attr, new BsonArray(array))

        case _ => doc.append(attr, new BsonNull())
      }
    }
  }

  override protected def addMap[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
    //    set2array: Set[Any] => Array[AnyRef],
    //    exts: List[String],
  ): Product => Unit = ???

  override protected def addMapOpt[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
    //    set2array: Set[Any] => Array[AnyRef],
    //    exts: List[String],
  ): Product => Unit = ???

  override protected def addRef(
    ns: String,
    refAttr: String,
    refNs: String,
    card: Card,
    owner: Boolean
  ): Product => Unit = {
    nss += refNs
    if (owner) {
      (_: Product) => {
        // Embed document
        val embeddedDoc = new BsonDocument()
        doc.append(refAttr, embeddedDoc)
        // Step into embedded document
        docs = docs.init :+ (docs.last :+ embeddedDoc)
        doc = embeddedDoc
      }
    } else {
      if (level == 0 && initialNs == refNs) {
        // Count top level self joins for correct id insertions in MongoConn_JVM.insertReferenced
        selfJoins += 1
      }
      (_: Product) => {
        // Reference document
        val refId = new BsonObjectId()
        val ref   = card match {
          case CardOne => refId
          case _       =>
            val array = new BsonArray
            array.add(refId)
            array
        }
        doc.append(refAttr, ref)

        // Set id in referenced document
        doc = new BsonDocument()
        doc.append("_id", refId)

        // Step into referenced document
        docs = docs.init :+ (docs.last :+ doc)

        val (i, nsData2) = nsDocs.getOrElse(refNs, {
          nsIndex += 1
          (nsIndex, new BsonArray())
        })
        nsData2.add(doc)
        nsDocs(refNs) = (i, nsData2)
      }
    }
  }

  override protected def addBackRef(backRefNs: String): Product => Unit = {
    (_: Product) =>
      // Step back to previous namespace/doc
      doc = docs.last.init.last
      docs = docs.init :+ docs.last.init
  }

  override protected def addNested(
    nsMap: Map[String, MetaNs],
    tplIndex: Int,
    ns: String,
    refAttr: String,
    refNs: String,
    owner: Boolean,
    nestedElements: List[Element]
  ): Product => Unit = {
    nss += refNs
    // Recursively resolve nested data
    val resolveNested = getResolver(nsMap, nestedElements)

    val tupled = countValueAttrs(nestedElements) match {
      case 1 => (tpl: Product) => tpl.productElement(tplIndex).asInstanceOf[Seq[Any]].map(Tuple1(_))
      case _ => (tpl: Product) => tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
    }
    if (owner) {
      (tpl: Product) => {
        val nestedTuples = tupled(tpl)

        // Initiate nested level
        val nestedArray = new BsonArray()
        doc.append(refAttr, nestedArray)
        docs = docs :+ Nil

        nestedTuples.foreach { nestedTpl =>
          // Add embedded document
          doc = new BsonDocument()
          docs = docs.init :+ List(doc)

          // Save outer doc
          val outerDoc = doc

          // Process nested data
          resolveNested(nestedTpl)

          // Add doc to embedded array
          nestedArray.add(outerDoc)
        }
      }

    } else {

      // Increase level for counting only top level self join
      level += 1

      (tpl: Product) => {
        level += 1
        val nestedTuples = tupled(tpl)

        // Initiate nested level
        val refIds = new BsonArray()
        doc.append(refAttr, refIds)
        docs = docs :+ Nil

        val (i, referencedDocs) = nsDocs.getOrElse(refNs, {
          nsIndex += 1
          (nsIndex, new BsonArray())
        })
        nestedTuples.foreach { nestedTpl =>

          // Referenced document
          doc = new BsonDocument()

          // Step into referenced document
          docs = docs.init :+ List(doc)

          // Save outer doc
          val outerDoc = doc

          // Save immutable clone to see if anything has changed
          val beforeDoc = doc.clone

          // Process nested data
          resolveNested(nestedTpl)

          if (beforeDoc != outerDoc) {
            // Reference document
            val refId = new BsonString(new ObjectId().toHexString)
            refIds.add(refId)

            // Set id in referenced document
            outerDoc.append("_id", refId)

            // Add doc to namespace
            referencedDocs.add(outerDoc)
          } else {
            docs = docs.init
          }
        }
        nsDocs(refNs) = (i, referencedDocs)
      }
    }
  }


  // Helpers -------------------------------------------------------------------

  private def addIterable[T, M[_] <: Iterable[_]](
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
  ): Product => Unit = {
    (tpl: Product) => {
      tpl.productElement(tplIndex).asInstanceOf[M[T]] match {
        case iterable if iterable.nonEmpty =>
          val array: util.ArrayList[BsonValue] = new util.ArrayList[BsonValue](iterable.size)
          iterable.asInstanceOf[Iterable[T]].foreach(scalaValue =>
            array.add(transformValue(scalaValue).asInstanceOf[BsonValue])
          )
          doc.append(attr, new BsonArray(array))

        case _ => doc.append(attr, new BsonNull())
      }
    }
  }

  private def addOptIterable[T, M[_] <: Iterable[_]](
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
  ): Product => Unit = {
    (tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case Some(iterable: Iterable[_]) if iterable.nonEmpty =>
          val array: util.ArrayList[BsonValue] = new util.ArrayList[BsonValue](iterable.size)
          iterable.asInstanceOf[Iterable[T]].map(scalaValue =>
            array.add(transformValue(scalaValue).asInstanceOf[BsonValue])
          )
          doc.append(attr, new BsonArray(array))

        case _ => ()
      }
    }
  }
}