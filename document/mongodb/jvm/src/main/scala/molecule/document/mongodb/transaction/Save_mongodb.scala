package molecule.document.mongodb.transaction

import java.util
import molecule.base.ast.{Card, CardOne}
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
    val nsData = new BsonArray()
    nsData.add(doc) // 1 row of data to save
    initialNs = getInitialNs(elements)
    nsIndex = 0
    nsDocs(initialNs) = (nsIndex, nsData)

    resolve(elements)

    val data = new BsonDocument("_action", new BsonString("insert"))

    val nssDocsList = nsDocs.toList.sortBy(_._2._1)
    if (!nssDocsList.head._2._2.isEmpty) {
      data.append("_selfJoins", new BsonInt32(selfJoins))
      // Loop referenced namespaces
      nsDocs.foreach { case (ns, (_, nsData)) =>
        data.append(ns, nsData)
      }
    }
    data
  }

  override protected def addOne[T](
    ns: String,
    attr: String,
    optBsonValue: Option[T],
    transformValue: T => Any,
    exts: List[String] = Nil
  ): Unit = {
    noEmbeddedIds(exts)
    optBsonValue.fold {
      doc.append(attr, new BsonNull())
    } { bsonValue =>
      doc.append(attr, transformValue(bsonValue).asInstanceOf[BsonValue])
    }
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    optSet: Option[Set[T]],
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    noEmbeddedIds(exts)
    addIterable(attr, optSet, transformValue)
  }

  override protected def addSeq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    optSeq: Option[Seq[T]],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    addIterable(attr, optSeq, transformValue)
  }

  override protected def addByteArray(
    ns: String,
    attr: String,
    optArray: Option[Array[Byte]],
  ): Unit = {
    optArray.fold {
      doc.append(attr, new BsonNull())
    } {
      case byteArray if byteArray.nonEmpty =>
        // Can't get BsonBinary working - todo
        // doc.append(attr, new BsonBinary(byteArray))

        val array: util.ArrayList[BsonValue] = new util.ArrayList[BsonValue]()
        byteArray.map(byte => array.add(new BsonInt32(byte)))
        doc.append(attr, new BsonArray(array))

      case _ => doc.append(attr, new BsonNull())
    }
  }

  override protected def addMap[T](
    ns: String,
    attr: String,
    optMap: Option[Map[String, T]],
    transformValue: T => Any,
    //    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer
    //    set2map: Set[Any] => Map[String, AnyRef],
    //    optRefNs: Option[String],
  ): Unit = {
    optMap.fold {
      doc.append(attr, new BsonNull())
    } {
      case map if map.nonEmpty =>
        val mapDoc = new BsonDocument()
        map.map { case (k, v) =>
          mapDoc.append(validKey(k), transformValue(v).asInstanceOf[BsonValue])
        }
        doc.append(attr, mapDoc)

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
      }

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
      val (i, nsData) = nsDocs.getOrElse(refNs, {
        nsIndex += 1
        (nsIndex, new BsonArray())
      })
      nsData.add(doc)
      nsDocs(refNs) = (i, nsData)
    }
  }

  override protected def addBackRef(backRefNs: String): Unit = {
    // Step back to previous namespace
    doc = docs.last.init.last
    docs = docs.init :+ docs.last.init
  }

  override protected def handleRefNs(refNs: String): Unit = ()


  // Helpers -------------------------------------------------------------------

  private def addIterable[T, M[_] <: Iterable[_]](
    attr: String,
    optIterable: Option[M[T]],
    transformValue: T => Any,
  ): Unit = {
    optIterable.fold {
      doc.append(attr, new BsonNull())
    } {
      case iterable if iterable.nonEmpty =>
        val array: util.ArrayList[BsonValue] = new util.ArrayList[BsonValue]()
        iterable.asInstanceOf[Iterable[T]].map(bsonValue =>
          array.add(transformValue(bsonValue).asInstanceOf[BsonValue])
        )
        doc.append(attr, new BsonArray(array))

      case _ => doc.append(attr, new BsonNull())
    }
  }
}