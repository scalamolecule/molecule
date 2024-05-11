package molecule.document.mongodb.facade

import java.util
import com.mongodb.client.model.{InsertManyOptions, UpdateOptions}
import com.mongodb.client.{MongoClient, MongoDatabase, TransactionBody}
import com.mongodb.{ReadConcern, ReadPreference, TransactionOptions, WriteConcern}
import molecule.base.error.ModelError
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.MongoProxy
import molecule.core.spi.{Conn, TxReport}
import molecule.core.util.{JavaConversions, ModelUtils}
import molecule.document.mongodb.transaction.{Base_JVM_mongodb, DataType_JVM_mongodb}
import molecule.document.mongodb.util.BsonUtils
import org.bson.conversions.Bson
import org.bson.{BsonArray, BsonDocument, BsonNull, BsonObjectId, BsonString}
import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}

case class MongoConn_JVM(
  override val proxy: MongoProxy,
  mongoClient: MongoClient,
  dbName: String,
  mongoDb: MongoDatabase,
  optCollectionName: Option[String] = None
) extends Conn(proxy)
  with DataType_JVM_mongodb
  with Base_JVM_mongodb
  with ModelUtils
  with BsonUtils
  with JavaConversions
  with MoleculeLogging {


  override def transact_async(data: Data)(implicit ec: ExecutionContext): Future[TxReport] = {
    Future(transact_sync(data))
  }

  override def transact_sync(data: Data): TxReport = {
    //    println("TRANSACT ----------------------------------------")
    //    println(data.toJson(pretty))
    //    println("")
    data.get("_action").asString.getValue match {
      case "insert" => data.size match {
        case 1 => TxReport(Nil)
        case 2 => insertEmbedded(data)
        case _ => insertReferenced(data)
      }

      case "update" =>
        if (data.size > 1) {
          // Only update if there are any collections/namespaces to update
          update(data)
        } else TxReport(Nil)

      case "delete" => data.size match {
        case 3 => delete(data)
        case 2 =>
          // Add empty dummy _ids (for `rawDelete` not needing to supply ids)
          val dummyIds = new BsonArray()
          dummyIds.add(new BsonObjectId())
          data.append("_ids", dummyIds)
          delete(data)
        case n => throw ModelError(s"Unexpectedly found $n key-value pairs in delete bson:\n" + data)
      }
      case other    => throw ModelError("Missing or unexpected action: " + other)
    }
  }

  private def insertReferenced(data: Data): TxReport = {
    val clientSession = mongoClient.startSession()
    try {
      val txBody    = new TransactionBody[TxReport] {
        override def execute: TxReport = {
          val ids        = ListBuffer.empty[String]
          val selfJoins  = data.get("_selfJoins").asInt32.getValue
          var firstNs    = true
          val firstNsIds = ListBuffer.empty[String]
          data.forEach {
            case ("_action" | "_selfJoins", _) => () // do nothing
            case (ns, nsData)                  =>
              val documents    = nsData.asArray.getValues.asInstanceOf[util.List[BsonDocument]]
              val collection   = mongoDb.getCollection(ns, classOf[BsonDocument])
              val insertResult = collection.insertMany(clientSession, documents)
              if (firstNs) {
                // Add ids of initial namespace
                val idsMap = insertResult.getInsertedIds
                idsMap.forEach {
                  case (k, v) if v.isString => firstNsIds += v.asString.getValue
                  case (k, v)               => firstNsIds += v.asObjectId.getValue.toHexString
                }
                firstNs = false
              }
          }

          if (selfJoins == 0) {
            firstNsIds.foreach { id => ids += id }
          } else {
            var i = selfJoins
            firstNsIds.foreach { id =>
              if (i == selfJoins) {
                ids += id
                i -= 1
              } else {
                // Ignore ids of self-joined entities
                i = selfJoins
              }
            }
          }
          TxReport(ids.toList)
        }
      }
      val txOptions = TransactionOptions.builder()
        .readPreference(ReadPreference.primary())
        .readConcern(ReadConcern.LOCAL)
        .writeConcern(WriteConcern.MAJORITY)
        .build()
      clientSession.withTransaction(txBody, txOptions)
    } catch {
      case e: RuntimeException => throw e // some error handling
    } finally clientSession.close
  }

  private def insertEmbedded(data: Data): TxReport = {
    val insertedIds = ListBuffer.empty[String]
    var first       = true
    data.forEach {
      case ("_action", _) => () // do nothing
      case (ns, rawRows)  =>
        val documents    = rawRows.asArray.getValues.asInstanceOf[util.List[BsonDocument]]
        val collection   = mongoDb.getCollection(ns, classOf[BsonDocument])
        val insertResult = collection.insertMany(documents)
        val idsMap       = insertResult.getInsertedIds
        if (first) {
          idsMap.forEach {
            case (k, v) => insertedIds += v.asObjectId().getValue.toHexString
          }
          first = false
        }
    }
    TxReport(insertedIds.toList)
  }


  private def update(data: Data): TxReport = {
    val clientSession = mongoClient.startSession()
    try {
      val txBody    = new TransactionBody[TxReport] {
        override def execute: TxReport = {
          var updatedIds = List.empty[String]
          var firstNs    = true
          data.forEach {
            case ("_action", _) => ()

            case ("_newRefIds", refData) =>
              //              println("++++ _newRefIds: " + refData)

              // Insert missing refs
              val nss = refData.asDocument()
              nss.forEach { case (refNs, refIds0) =>
                val refIds = refIds0.asArray()
                if (!refIds.isEmpty) {
                  val collection = mongoDb.getCollection(refNs, classOf[BsonDocument])
                  collection.insertMany(clientSession, refIds.asInstanceOf[util.List[BsonDocument]])
                }
              }

            case ("_refAttrs", refData) =>
              //              println("++++ _refAttrs: " + refData)

              // Add ref attributes to referring doc
              val nss = refData.asDocument()
              nss.forEach { case (ns, updateData) =>
                val collection = mongoDb.getCollection(ns, classOf[BsonDocument])
                updateData.asArray().forEach { bson =>
                  val doc    = bson.asDocument()
                  val filter = doc.getDocument("filter")
                  val update = doc.getDocument("update")
                  collection.updateOne(clientSession, filter, update)
                }
              }

            case (ns, nsData0) =>
              val nsData = nsData0.asDocument()
              val filter = nsData.getDocument("filter")
              if (firstNs) {
                updatedIds = filter
                  .getDocument("_id")
                  .getArray("$in").getValues.asScala.toList
                  .map(_.asInstanceOf[BsonObjectId].getValue.toString)
                firstNs = false
              }




              //              val update: Bson = nsData.get("update").asInstanceOf[Bson]

              //              println(s"$ns ######## filter")
              //              println(filter.toJson(pretty))
              //              println("######## update")
              //              println(update.toJson(pretty))


              val collection = mongoDb.getCollection(ns, classOf[BsonDocument])
              //              collection.updateMany(clientSession, filter, update, new UpdateOptions().upsert(true))
//              collection.updateMany(clientSession, filter, update)
              //              collection.updateMany(clientSession, filter, arrayList)

              nsData.get("update") match {
                case doc: BsonDocument => collection.updateMany(clientSession, filter, doc)
                case arr: BsonArray    =>
                  val stages = new util.ArrayList[Bson]()
                  arr.forEach(stage => stages.add(stage.asDocument()))
                  collection.updateMany(clientSession, filter, stages)
              }

          }


          TxReport(updatedIds)
        }
      }
      val txOptions = TransactionOptions.builder()
        .readPreference(ReadPreference.primary())
        .readConcern(ReadConcern.LOCAL)
        .writeConcern(WriteConcern.MAJORITY)
        .build()
      clientSession.withTransaction(txBody, txOptions)
    } catch {
      case e: RuntimeException => throw e // some error handling
    } finally clientSession.close()
  }


  private def delete(data: Data): TxReport = {
    val deletedIds = data.getArray("_ids").getValues.asScala.toList
      .map(_.asInstanceOf[BsonObjectId].getValue.toString)
    if (deletedIds.nonEmpty) {
      data.forEach {
        case ("_action" | "_ids", _) => ()
        case (ns, deletedIdsFilter)  =>
          mongoDb.getCollection(ns, classOf[BsonDocument])
            .deleteMany(deletedIdsFilter.asInstanceOf[BsonDocument])
      }
    }
    TxReport(deletedIds)
  }
}