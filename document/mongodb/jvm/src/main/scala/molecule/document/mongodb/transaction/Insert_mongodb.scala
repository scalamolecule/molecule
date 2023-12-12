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

  doPrint = false

  def debug(obj: Any, s: String = ""): Unit = if (false) {
    val id  = System.identityHashCode(obj)
    val idP = id + padS(10, id.toString)
    val txt = s + padS(12, s)
    println(s"$idP  $txt  $obj")
  }

  def getData(nsMap: Map[String, MetaNs], elements: List[Element], tpls: Seq[Product]): Data = {
    initialNs = getInitialNs(elements)
    var first    = true
    val tpl2bson = getResolver(nsMap, elements)
    val data     = new BsonDocument().append("action", new BsonString("insert"))
    tpls.foreach { tpl =>
      doc = new BsonDocument()
      docs = List(List(doc))
      val rows = new BsonArray()
      rows.add(doc)
      nsDocs.clear()
      nsDocs(initialNs) = rows

      // Convert tpl to bson
      tpl2bson(tpl)

      nsDocs.foreach { case (ns, rows) =>
        println(ns)
        if (first && !data.containsKey(ns)) {
          data.append(ns, new BsonArray())
        }
        println(rows)
        data.get(ns).asArray().addAll(rows)
      }
      first = false
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
      doc.append(attr, tpl.productElement(tplIndex) match {
        case Some(scalaValue) => handleValue(scalaValue.asInstanceOf[T]).asInstanceOf[BsonValue]
        case None             => new BsonNull()
      })
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
    if (refNs.isDefined) {
      throw ModelError("Can't add non-existing ids of embedded documents in MongoDB. " +
        "Please save embedded document together with main document.")
    }
    (tpl: Product) => {
      tpl.productElement(tplIndex).asInstanceOf[Set[T]] match {
        case set if set.nonEmpty =>
          val array: util.ArrayList[BsonValue] = new util.ArrayList[BsonValue]()
          set.map(scalaValue => array.add(transformValue(scalaValue).asInstanceOf[BsonValue]))
          doc.append(attr, new BsonArray(array))

        case _ => doc.append(attr, new BsonNull())
      }
      () // saveDoc mutated
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
    if (refNs.isDefined) {
      throw ModelError("Can't add non-existing ids of embedded documents in MongoDB. " +
        "Please save embedded document together with main document.")
    }
    (tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case Some(set: Set[_]) if set.nonEmpty =>
          val array: util.ArrayList[BsonValue] = new util.ArrayList[BsonValue]()
          set.asInstanceOf[Set[T]].map(scalaValue => array.add(transformValue(scalaValue).asInstanceOf[BsonValue]))
          doc.append(attr, new BsonArray(array))

        case _ => doc.append(attr, new BsonNull())
      }
      ()
    }
  }

  override protected def addRef(
    ns: String,
    refAttr: String,
    refNs: String,
    card: Card,
    owner: Boolean
  ): Product => Unit = {
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
        val refId = new BsonString(new ObjectId().toHexString)
        doc.append(refAttr, refId)
        // Set id in referenced document
        val refDoc = new BsonDocument()
        refDoc.append("_id", refId)
        // Step into referenced document
        docs = docs.init :+ (docs.last :+ refDoc)
        doc = refDoc

        val rows = nsDocs.getOrElse(refNs, new BsonArray())
        rows.add(doc)
        nsDocs(refNs) = rows
      }
    }
  }

  override protected def addBackRef(backRefNs: String): Product => Unit = {
    (_: Product) =>
      // Step back to previous namespace/doc
      docs = docs.init :+ docs.last.init
      doc = docs.last.init.last
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
    // Recursively resolve nested data
    val resolveNested = getResolver(nsMap, nestedElements)

    val tupled = countValueAttrs(nestedElements) match {
      case 1 => (tpl: Product) => tpl.productElement(tplIndex).asInstanceOf[Seq[Any]].map(Tuple1(_))
      case _ => (tpl: Product) => tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
    }

    (tpl: Product) => {
      val nestedTuples = tupled(tpl)
      val nestedArray  = new BsonArray()
      doc.append(refAttr, nestedArray)
      val outerDoc = doc

      // Initiate nested level
      docs = docs :+ Nil

      nestedTuples.foreach { nestedTpl =>
        debug("", "------------------------------------------------")
        // Start from fresh namespace on this level
        doc = new BsonDocument()
        docs = docs.init :+ List(doc)
        resolveNested(nestedTpl)
        nestedArray.add(docs.last.head.clone())
      }
      debug("", "------------------------------------------------")
      debug(outerDoc, "outer")
    }
  }
}