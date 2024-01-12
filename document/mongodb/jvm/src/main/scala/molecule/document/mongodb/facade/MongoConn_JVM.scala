package molecule.document.mongodb.facade

import java.util
import com.mongodb.client.model.UpdateOptions
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
import org.bson.{BsonArray, BsonDocument, BsonObjectId, BsonString}
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
        case n => throw ModelError(s"Unexpectedly found $n key-value pairs in delete bson:\n" + data)
      }
      case other    => throw ModelError("Missing or unexpected action: " + other)
    }
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
            case (ns, nsData0)  =>
              val nsData = nsData0.asDocument()
              val filter = nsData.get("filter").asDocument()
              if (firstNs) {
                updatedIds = filter
                  .get("_id").asDocument()
                  .get("$in").asArray().getValues.asScala.toList
                  .map(_.asInstanceOf[BsonObjectId].getValue.toString)
                firstNs = false
              }

              val update = nsData.get("update").asDocument()

              //              println("ns    : " + ns)
              //              println("filter: " + filter)
              //              println("update: " + update)

              val collection = mongoDb.getCollection(ns, classOf[BsonDocument])

              if (nsData.containsKey("arrayFilters")) {
                val arrayFilters = new util.ArrayList[Bson]
                nsData.get("arrayFilters").asArray().forEach(filter =>
                  arrayFilters.add(filter.asDocument())
                )
                val updateOptions = new UpdateOptions().arrayFilters(arrayFilters)
                collection.updateMany(clientSession, filter, update, updateOptions)
              } else {
                collection.updateMany(clientSession, filter, update)
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
    val deletedIds = data.get("_ids").asArray().getValues.asScala.toList
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

  private def insertReferenced(data: Data): TxReport = {
    val clientSession = mongoClient.startSession()
    try {
      val txBody    = new TransactionBody[TxReport] {
        override def execute: TxReport = {
          //          val db          = mongoClient.getDatabase(dbName)
          var firstNs     = true
          val insertedIds = ListBuffer.empty[String]
          data.forEach {
            case ("_action", _) => () // do nothing
            case (ns, nsData)   =>
              //            println(".... " + collectionName)
              val documents    = nsData.asArray.getValues.asInstanceOf[util.List[BsonDocument]]
              val collection   = mongoDb.getCollection(ns, classOf[BsonDocument])
              val insertResult = collection.insertMany(clientSession, documents)
              if (firstNs) {
                val idsMap = insertResult.getInsertedIds
                idsMap.forEach {
                  case (k, v) if v.isString => insertedIds.addOne(v.asString().getValue)
                  case (k, v)               => insertedIds.addOne(v.asObjectId().getValue.toHexString)
                }
                firstNs = false
              }
          }
          TxReport(insertedIds.toList)
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
    data.forEach {
      case ("_action", _) => () // do nothing
      case (ns, rawRows)  =>
        val documents    = rawRows.asArray.getValues.asInstanceOf[util.List[BsonDocument]]
        val collection   = mongoDb.getCollection(ns, classOf[BsonDocument])
        val insertResult = collection.insertMany(documents)
        val idsMap       = insertResult.getInsertedIds
        idsMap.forEach {
          case (k, v) => insertedIds.addOne(v.asObjectId().getValue.toHexString)
        }
    }
    TxReport(insertedIds.toList)
  }


  //  private def debugDocs(action: String, documents: util.List[BsonDocument]): Unit = {
  private def debugDocs(action: String, document: BsonDocument): Unit = {
    //    val pretty: JsonWriterSettings = JsonWriterSettings.builder().indent(true).build()
    //    document.entrySet().forEach(pair => println(d.toBsonDocument.toJson(pretty)))
    //    documents.forEach(d => println(d.toBsonDocument.toJson(pretty)))
    println(action + " ----------------------------------------")
    println(document.toJson(pretty))
    println("")
  }
  private def debugFilter(action: String, filter: Bson): Unit = {
    println(action + " ----------------------------------------")
    //    val pretty: JsonWriterSettings = JsonWriterSettings.builder().indent(true).build()
    println(filter.toBsonDocument.toJson(pretty))
    println("")
  }
}