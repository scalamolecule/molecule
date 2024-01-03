package molecule.document.mongodb.facade

import java.util
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
import org.bson.{BsonDocument, BsonObjectId, BsonString, BsonValue}
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
      case "update" => ???
      case "delete" => data.size match {
        case 3 => delete(data)
        case n => throw ModelError(s"Unexpectedly found $n key-value pairs in delete bson")
      }
      case other    => throw ModelError("Missing or unexpected action: " + other)
    }
  }


  private def delete(data: Data): TxReport = {
    val deletedIds = data.get("_ids").asArray().getValues.asScala.toList
      .map(_.asInstanceOf[BsonObjectId].getValue.toString)
    data.forEach {
      case ("_action" | "_ids", _) => ()
      case (ns, filterDoc)         =>
        if (deletedIds.nonEmpty) {
          val filters    = filterDoc.asInstanceOf[BsonDocument]
          val collection = mongoDb.getCollection(ns, classOf[BsonDocument])
          collection.deleteMany(filters)
        }
    }
    TxReport(deletedIds)
  }

  private def insertReferenced(data: Data): TxReport = {
    val clientSession = mongoClient.startSession()
    try {
      val txBody    = new TransactionBody[TxReport] {
        override def execute: TxReport = {
          val db      = mongoClient.getDatabase(dbName)
          var firstNs = true
          val ids     = ListBuffer.empty[String]
          data.forEach {
            case ("_action", _) => () // do nothing
            case (ns, rawRows)  =>
              //            println(".... " + collectionName)
              val documents    = rawRows.asArray.getValues.asInstanceOf[util.List[BsonDocument]]
              val collection   = db.getCollection(ns, classOf[BsonDocument])
              val insertResult = collection.insertMany(clientSession, documents)
              if (firstNs) {
                val idsMap = insertResult.getInsertedIds
                idsMap.forEach {
                  case (k, v) if v.isString => ids.addOne(v.asString().getValue)
                  case (k, v)               => ids.addOne(v.asObjectId().getValue.toHexString)
                }
                firstNs = false
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
      case e: RuntimeException =>
        // some error handling
        throw e
    } finally clientSession.close
  }

  private def insertEmbedded(data: Data): TxReport = {
    val ids = ListBuffer.empty[String]
    data.forEach {
      case ("_action", _) => () // do nothing
      case (ns, rawRows)  =>
        val documents    = rawRows.asArray.getValues.asInstanceOf[util.List[BsonDocument]]
        val collection   = mongoDb.getCollection(ns, classOf[BsonDocument])
        val insertResult = collection.insertMany(documents)
        val idsMap       = insertResult.getInsertedIds
        idsMap.forEach {
          case (k, v) => ids.addOne(v.asObjectId().getValue.toHexString)
        }
    }
    TxReport(ids.toList)
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