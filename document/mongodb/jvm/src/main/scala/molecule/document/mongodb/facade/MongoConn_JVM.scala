package molecule.document.mongodb.facade

import java.util
import com.mongodb.client.{MongoClient, MongoDatabase, TransactionBody}
import com.mongodb.{ReadConcern, ReadPreference, TransactionOptions, WriteConcern}
import molecule.base.error.ModelError
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.MongoProxy
import molecule.core.spi.{Conn, TxReport}
import molecule.core.util.ModelUtils
import molecule.document.mongodb.transaction.{Base_JVM_mongodb, DataType_JVM_mongodb}
import molecule.document.mongodb.util.BsonUtils
import org.bson.conversions.Bson
import org.bson.{BsonDocument, BsonObjectId, BsonValue}
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
  with MoleculeLogging {


  override def transact_async(data: Data)(implicit ec: ExecutionContext): Future[TxReport] = {
    Future(transact_sync(data))
  }

  override def transact_sync(data: Data): TxReport = {
    println("TRANSACT ----------------------------------------")
    println(data.toJson(pretty))
    println("")
    data.get("action").asString.getValue match {
      case "insert" => data.size match {
        case 2 => insertEmbedded(data)
        case _ => insertReferenced(data)
      }
      case "update" => ???
      case "delete" => ???
      case other    => throw ModelError("Missing or unexpected action: " + other)
    }
  }

  //  def saveData_sync(collections: BsonDocument): TxReport = {
  //    val collectionData = collections.entrySet.iterator().next()
  //
  //    val (collectionName, documents) = (collectionData.getKey, collectionData.getValue.asInstanceOf[BsonArray])
  //    val collection                  = mongoDb.getCollection(collectionName, classOf[BsonDocument])
  //    debugDocs("SAVE", collections)
  //
  //
  //    val insertResult = collection.insertOne(documents.iterator.next.asDocument())
  //    val idHexString  = insertResult.getInsertedId.asInstanceOf[BsonObjectId].getValue.toHexString
  //    TxReport(List(idHexString))
  //  }


  private def insertReferenced(data: Data): TxReport = {
    var first         = true
    var ids           = List.empty[String]
    val clientSession = mongoClient.startSession()
    val txOptions     = TransactionOptions.builder()
      .readPreference(ReadPreference.primary())
      .readConcern(ReadConcern.LOCAL)
      .writeConcern(WriteConcern.MAJORITY)
      .build()

    val txBody = new TransactionBody[TxReport] {
      override def execute: TxReport = {
        val db = mongoClient.getDatabase(dbName)
        data.forEach {
          case ("action", _)             => // do nothing
          case (collectionName, rawRows) =>
            println(".... " + collectionName)
            val documents    = rawRows.asArray.getValues.asInstanceOf[util.List[BsonDocument]]
            val collection   = db.getCollection(collectionName, classOf[BsonDocument])
            val insertResult = collection.insertMany(clientSession, documents)
            if (first) {
              val idsMap = insertResult.getInsertedIds
              val array  = new Array[BsonValue](idsMap.size)
              idsMap.forEach {
                case (k, v) => array(k) = v
              }
              ids = array.toList.map(_.asInstanceOf[BsonObjectId].getValue.toHexString)
              first = false
            }
        }
        TxReport(ids)
      }
    }
    try
      clientSession.withTransaction(txBody, txOptions)
    catch {
      case e: RuntimeException =>
        // some error handling
        throw e
    } finally clientSession.close
  }

  private def insertEmbedded(data: Data): TxReport = {
    var ids = List.empty[String]
    data.forEach {
      case ("action", _)             => // do nothing
      case (collectionName, rawRows) =>
        val documents    = rawRows.asArray.getValues.asInstanceOf[util.List[BsonDocument]]
        val collection   = mongoDb.getCollection(collectionName, classOf[BsonDocument])
        val insertResult = collection.insertMany(documents)
        val idsMap       = insertResult.getInsertedIds
        val array        = new Array[BsonValue](idsMap.size)
        idsMap.forEach {
          case (k, v) => array(k) = v
        }
        ids = array.toList.map(_.asInstanceOf[BsonObjectId].getValue.toHexString)
    }
    TxReport(ids)
  }

  private def deleteData(colName: String, filter: Bson): TxReport = {
    //    val (collectionName, filter) = data
    val collection = mongoDb.getCollection(colName, classOf[BsonDocument])
    debugFilter("INSERT " + colName, filter)
    collection.deleteMany(filter)
    TxReport(Nil)
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

  //  def atomicTransaction(executions: () => Map[List[String], List[Long]]): TxReport = {
  //    try {
  //      // Atomic transaction of all statements
  //      sqlConn.setAutoCommit(false)
  //
  //      // Execute batches
  //      val idsMap = executions()
  //
  //      // transact all
  //      sqlConn.commit()
  //
  //      val entityIdsInvolved = idsMap.values.toList.reverse.flatten
  //
  //      // Tx entity not implemented for sql-jdbc
  //      TxReport(0, entityIdsInvolved)
  //    } catch {
  //      // re-throw errors to keep stacktrace back to original error
  //      case e: SQLException =>
  //        try {
  //          sqlConn.rollback()
  //          logger.warn("Successfully rolled back unsuccessful insertion with error: " + e)
  //          throw e
  //        } catch {
  //          case e: SQLException =>
  //            logger.error("Couldn't roll back unsuccessful insertion: " + e)
  //            throw e
  //        }
  //      case NonFatal(e)     =>
  //        logger.error("Unexpected insertion error: " + e)
  //        throw e
  //    }
  //  }
  //
  //  def populateStmts(data: Data): Map[List[String], List[Long]] = {
  //    ???
  //    //    val tables     = data._1
  //    //    val joinTables = data._2
  //    //    var idsMap     = Map.empty[List[String], List[Long]]
  //    //    var ids        = List.empty[Long]
  //    //
  //    //    debug("########################################################################################## " + tables.size)
  //    //
  //    //    // Insert statements backwards to obtain auto-generated ref ids for prepending inserts
  //    //    tables.reverse.foreach {
  //    //      case Table(refPath, stmt, ps, populatePS) =>
  //    //        debug("D --- table ----------------------------------------------")
  //    //        debug("idsMap 1: " + idsMap)
  //    //        debug(stmt)
  //    //
  //    //        populatePS(ps, idsMap, 0)
  //    //
  //    //        // Populate prepared statement
  //    //        ps.executeBatch()
  //    //        try {
  //    //          val resultSet = ps.getGeneratedKeys
  //    //          ids = List.empty[Long]
  //    //          if (!refPath.last.init.contains("_")) { // no join tables, but still "_"-suffixed tables
  //    //            while (resultSet.next()) {
  //    //              //              val id = resultSet.getLong(1)
  //    //              //              debug("D  ################# " + id)
  //    //              //              ids = ids :+ id
  //    //              ids = ids :+ resultSet.getLong(1)
  //    //            }
  //    //          }
  //    //        } catch {
  //    //          case e: NullPointerException =>
  //    //            // MariaDB can return null instead of empty ResultSet which we simply ignore
  //    //            logger.debug("Resultset was null")
  //    //            ()
  //    //          case NonFatal(e)             =>
  //    //            e.printStackTrace()
  //    //            throw e
  //    //        }
  //    //        ps.close()
  //    //        idsMap = idsMap + (refPath -> ids)
  //    //        debug("idsMap 2: " + idsMap)
  //    //    }
  //    //
  //    //    joinTables.foreach {
  //    //      case JoinTable(stmt, ps, leftPath, rightPath, rightCounts) =>
  //    //        debug("D --- joinTable -------------------------------------------------\n" + stmt)
  //    //        val idsLeft    = idsMap(leftPath)
  //    //        var idLeft     = 0L
  //    //        var leftIndex  = 0
  //    //        val idsRight   = idsMap(rightPath)
  //    //        var rightIndex = 0
  //    //        var i          = 0
  //    //        while (leftIndex != idsLeft.length) {
  //    //          idLeft = idsLeft(leftIndex)
  //    //          i = 0
  //    //          while (i != rightCounts(leftIndex)) {
  //    //            ps.setLong(1, idLeft)
  //    //            ps.setLong(2, idsRight(rightIndex))
  //    //            ps.addBatch()
  //    //            rightIndex += 1
  //    //            i += 1
  //    //          }
  //    //          leftIndex += 1
  //    //        }
  //    //        ps.executeBatch()
  //    //    }
  //    //
  //    //    idsMap
  //  }
}