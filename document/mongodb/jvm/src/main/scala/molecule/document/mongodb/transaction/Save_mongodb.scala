package molecule.document.mongodb.transaction

import java.util
import molecule.base.ast.Card
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveSave
import molecule.core.transaction.ops.SaveOps
import org.bson._
import org.bson.types.ObjectId

trait Save_mongodb
  extends Base_JVM_mongodb
    with SaveOps
    with MoleculeLogging { self: ResolveSave =>


  def getData(elements: List[Element]): Data = {
    initialNs = getInitialNs(elements)
    refDocs = refDocs :+ (List(initialNs), List(List(doc)))
    resolve(elements)


    //
    val data = new BsonDocument().append("action", new BsonString("insert"))
    refDocs.foreach { case (nsPath, documents) =>
//      println("---- " + nsPath)
//      println(documents)
      val ns        = nsPath.last
      val rowsArray = new BsonArray()
      // Add array of rows with single row for this save action
      rowsArray.add(documents.head.head)
      data.append(ns, rowsArray)
    }
    data
  }


  override protected def addOne[T](
    ns: String,
    attr: String,
    optBsonValue: Option[T],
    handleValue: T => Any,
    exts: List[String] = Nil
  ): Unit = {
    optBsonValue.fold {
      doc.append(attr, new BsonNull())
    } { bsonValue =>
      doc.append(attr, bsonValue.asInstanceOf[BsonValue])
    }
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    optSet: Option[Set[Any]],
    transformValue: T => Any,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String],
    exts: List[String] = Nil,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    if (refNs.isDefined) {
      throw ModelError("Can't add non-existing ids of embedded documents in MongoDB. " +
        "Please save embedded document together with main document.")
    }
    optSet.fold {
      doc.append(attr, new BsonNull())
    } {
      case set if set.nonEmpty =>
        val array: util.ArrayList[BsonValue] = new util.ArrayList[BsonValue]()
        // Values have already been transformed in ResolveSave
        set.map(bsonValue => array.add(bsonValue.asInstanceOf[BsonValue]))
        doc.append(attr, new BsonArray(array))

      case _ => doc.append(attr, new BsonNull())
    }
  }


  override protected def addRef(
    ns: String,
    refAttr: String,
    refNs: String,
    card: Card,
    owner: Boolean
  ): Unit = {
    if (owner) {
      // embedded
      // Add embedded document
      val embeddedDoc = new BsonDocument()
      doc.append(refAttr, embeddedDoc)
      // Step into embedded document
      docs = docs.init :+ (docs.last :+ embeddedDoc)
      doc = embeddedDoc

    } else {
      // referenced
      // Add id of referenced document
      val refId = new BsonString(new ObjectId().toHexString)
      doc.append(refAttr, refId)
      // Set id in referenced document
      val refDoc = new BsonDocument()
      refDoc.append("_id", refId)
      // Step into referenced document
      docs = docs.init :+ (docs.last :+ refDoc)
      doc = refDoc
      refDocs = refDocs :+ ((refDocs.last._1 ++ List(refAttr, refNs)) -> List(List(doc)))
    }
  }

  override protected def addBackRef(backRefNs: String): Unit = {
//    if (owner) {
//      // embedded
//      } else {
//
//    }
    // Step back to previous namespace
    doc = docs.last.init.last
    docs = docs.init :+ docs.last.init

    // todo: correct?
//    val prevRefPath = refDocs.last._1.drop(2)
//    val prevNs      = refDocs.toMap.apply(prevRefPath)
//    refDocs = refDocs.init :+ (prevRefPath -> prevNs)
  }

  override protected def handleRefNs(refNs: String): Unit = ()

}