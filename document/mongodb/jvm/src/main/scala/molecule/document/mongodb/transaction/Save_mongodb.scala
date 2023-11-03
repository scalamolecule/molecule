package molecule.document.mongodb.transaction

import java.util
import molecule.base.ast.Card
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveSave
import molecule.core.transaction.ops.SaveOps
import org.bson._

trait Save_mongodb
  extends Base_JVM_mongodb
    with SaveOps
    with MoleculeLogging { self: ResolveSave =>


  def getData(elements: List[Element]): Data = {
    initialNs = getInitialNs(elements)
    resolve(elements)
    val saveDocs = new util.ArrayList[BsonDocument]()
    saveDocs.add(docs.head.head)
    (initialNs, saveDocs)
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


  override protected def addRef(ns: String, refAttr: String, refNs: String, card: Card): Unit = {
    val refDoc = new BsonDocument()
    // Make relationship
    doc.append(refAttr, refDoc)
    // Step into related namespace
    docs = docs.init :+ (docs.last :+ refDoc)
    // New namespace ready to append field-value pairs
    doc = refDoc
  }

  override protected def addBackRef(backRefNs: String): Unit = {
    // Step back to previous namespace
    doc = docs.last.init.last
    docs = docs.init :+ docs.last.init
  }

  override protected def handleRefNs(refNs: String): Unit = ()

}