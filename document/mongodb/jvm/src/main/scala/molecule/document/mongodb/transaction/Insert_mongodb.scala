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
    val tpl2bson   = getResolver(nsMap, elements)
    val insertDocs = new util.ArrayList[BsonDocument]()
    tpls.foreach { tpl =>
      // Build new Bson doc from each entity tuple
      doc = new BsonDocument()
      docs = List(List(doc))
      tpl2bson(tpl)
      val topDoc = docs.head.head
      debug(topDoc, "result")
      insertDocs.add(topDoc)
    }
    (getInitialNs(elements), insertDocs)
    ???
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
    (_: Product) => {
      val refDoc = new BsonDocument()
      // Make relationship
      doc.append(refAttr, refDoc)
      // Step into related namespace
      docs = docs.init :+ (docs.last :+ refDoc)
      // Work on in new namespace
      debug(doc, "add ref")
      doc = refDoc
      debug(refDoc, "ref")
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
    //    val joinTable  = ss(ns, refAttr, refNs)
    //    val (id1, id2) = if (ns == refNs)
    //      (ss(ns, "1_id"), ss(refNs, "2_id"))
    //    else
    //      (ss(ns, "id"), ss(refNs, "id"))
    //    val nextLevel  = level + 1
    //    val joinPath   = curRefPath :+ joinTable
    //    val leftPath   = curRefPath
    //    val rightPath  = List(s"$nextLevel", refNs)
    //    joins = joins :+ (joinPath, id1, id2, leftPath, rightPath)
    //    rightCountsMap(joinPath) = List.empty[Int]
    //
    //    // Initiate new level
    //    level = nextLevel
    //    curRefPath = List(s"$level", refNs)
    //    colSettersMap += curRefPath -> Nil
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
        //        debug(doc, "tplB")
        nestedArray.add(docs.last.head.clone())
      }
      debug("", "------------------------------------------------")
      debug(outerDoc, "outer")
    }
  }
}