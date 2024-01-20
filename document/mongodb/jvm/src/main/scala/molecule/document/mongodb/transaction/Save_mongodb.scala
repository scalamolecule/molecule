package molecule.document.mongodb.transaction

import java.util
import molecule.base.ast.{Card, CardOne}
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
    val nsData = new BsonArray()
    nsData.add(doc) // 1 row of data to save
    initialNs = getInitialNs(elements)
    nsDocs(initialNs) = nsData

    resolve(elements)

    if (!refIds.isEmpty) {
      refIdss.add(refIds)
    }
    val data = new BsonDocument()
      .append("_action", new BsonString("insert"))
      .append("_selfJoins", new BsonInt32(selfJoins))
      .append("_refIdss", refIdss)

    // Loop referenced namespaces
    nsDocs.foreach { case (ns, nsData) =>
      data.append(ns, nsData)
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
      doc = embeddedDoc
      docs = docs.init :+ (docs.last :+ doc)

    } else {
      // Reference document
      val refId = new BsonObjectId()

      if (initialNs == refNs) {
        // Count top level self joins for correct id insertions in MongoConn_JVM.insertReferenced
        selfJoins += 1
      } else {
        // Add top level ref id if not a self-join
        refIds.add(refId)
      }

      //      refIds.add(refId)
      val ref = card match {
        case CardOne => refId
        case _       =>
          val array = new BsonArray
          array.add(refId)
          array
      }
      doc.append(refAttr, ref)

      // Set id in new referenced document
      doc = new BsonDocument()
      doc.append("_id", refId)

      // Step into referenced document
      docs = docs.init :+ (docs.last :+ doc)

      // Add doc to namespace docs
      val nsData = nsDocs.getOrElse(refNs, new BsonArray())
      nsData.add(doc)
      nsDocs(refNs) = nsData
    }
  }

  override protected def addBackRef(backRefNs: String): Unit = {
    // Step back to previous namespace
    doc = docs.last.init.last
    docs = docs.init :+ docs.last.init
  }

  override protected def handleRefNs(refNs: String): Unit = ()
}