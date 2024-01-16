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
    val initialNs = getInitialNs(elements)
    nss += initialNs
    val tpl2bson = getResolver(nsMap, elements)

    // Prepare adding ns Data to namespaces
    val nssDocs = mutable.Map.empty[String, BsonArray]
    nss.foreach(ns => nssDocs(ns) = new BsonArray())

    var first = true
    tpls.foreach { tpl =>
      doc = new BsonDocument()
      docs = List(List(doc))
      val nsData = new BsonArray()
      nsData.add(doc)
      nsDocs.clear()
      nsDocs(initialNs) = nsData

      // Convert tpl to bson
      tpl2bson(tpl)

      // Add docs in namespaces
      nsDocs.foreach { case (ns, nsData) =>
        nssDocs(ns).addAll(nsData)
      }
      first = false
    }

    val data = new BsonDocument("_action", new BsonString("insert"))
    nssDocs.collect {
      case (ns, nsDocs: BsonArray) if !nsDocs.isEmpty => data.append(ns, nsDocs)
    }

    data
  }

  override protected def addOne[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    handleValue: T => Any,
    exts: List[String] = Nil
  ): Product => Unit = {
    (tpl: Product) => {
      doc.append(
        attr,
        handleValue(tpl.productElement(tplIndex).asInstanceOf[T]).asInstanceOf[BsonValue]
      )
      debug(doc, "add")
    }
  }

  override protected def addOneOpt[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    handleValue: T => Any,
    exts: List[String] = Nil
  ): Product => Unit = {
    (tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case Some(scalaValue) => doc.append(
          attr,
          handleValue(scalaValue.asInstanceOf[T]).asInstanceOf[BsonValue]
        )
        case None             => ()
      }
    }
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    (tpl: Product) => {
      tpl.productElement(tplIndex).asInstanceOf[Set[T]] match {
        case set if set.nonEmpty =>
          val array: util.ArrayList[BsonValue] = new util.ArrayList[BsonValue](set.size)
          set.map(scalaValue => array.add(transformValue(scalaValue).asInstanceOf[BsonValue]))
          doc.append(attr, new BsonArray(array))

        case _ => doc.append(attr, new BsonNull())
      }
    }
  }

  override protected def addSetOpt[T](
    ns: String,
    attr: String,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    (tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case Some(set: Set[_]) if set.nonEmpty =>
          val array: util.ArrayList[BsonValue] = new util.ArrayList[BsonValue](set.size)
          set.asInstanceOf[Set[T]].map(scalaValue =>
            array.add(transformValue(scalaValue).asInstanceOf[BsonValue])
          )
          doc.append(attr, new BsonArray(array))

        case _ => ()
      }
    }
  }

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
      (_: Product) => {
        // Reference document
        val refId = new BsonObjectId()
        doc.append(refAttr, refId)
        // Set id in referenced document
        val refDoc = new BsonDocument()
        refDoc.append("_id", refId)
        // Step into referenced document
        docs = docs.init :+ (docs.last :+ refDoc)
        doc = refDoc

        val nsData = nsDocs.getOrElse(refNs, new BsonArray())
        nsData.add(doc)
        nsDocs(refNs) = nsData
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
      (tpl: Product) => {
        val nestedTuples = tupled(tpl)

        // Initiate nested level
        val refIds = new BsonArray()
        doc.append(refAttr, refIds)
        docs = docs :+ Nil

        val referencedDocs = nsDocs.getOrElse(refNs, new BsonArray())
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
        nsDocs(refNs) = referencedDocs
      }
    }
  }
}