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
    val rows = new BsonArray()
    rows.add(doc)
    nsDocs(initialNs) = rows
    resolve(elements)
    val data = new BsonDocument().append("action", new BsonString("insert"))
    nsDocs.foreach { case (ns, rows) =>
      data.append(ns, rows)
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
      // Embed document
      val embeddedDoc = new BsonDocument()
      doc.append(refAttr, embeddedDoc)
      // Step into embedded document
      docs = docs.init :+ (docs.last :+ embeddedDoc)
      doc = embeddedDoc

    } else {
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

  override protected def addBackRef(backRefNs: String): Unit = {
    // Step back to previous namespace
    doc = docs.last.init.last
    docs = docs.init :+ docs.last.init
  }

  override protected def handleRefNs(refNs: String): Unit = ()
}