package molecule.document.mongodb.facade

import java.util
import com.mongodb.client.MongoDatabase
import com.mongodb.client.result.InsertManyResult
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.MongoProxy
import molecule.core.spi.{Conn, TxReport}
import molecule.core.util.ModelUtils
import molecule.document.mongodb.transaction.{Base_JVM_mongodb, DataType_JVM_mongodb}
import org.bson.{BsonDocument, BsonObjectId, BsonValue}
import scala.concurrent.{ExecutionContext, Future}

case class MongoConn_JVM(
  override val proxy: MongoProxy,
  mongoDb: MongoDatabase,
  optCollectionName: Option[String] = None
) extends Conn(proxy)
  with DataType_JVM_mongodb
  with Base_JVM_mongodb
  with ModelUtils
  with MoleculeLogging {

//  def use(collectionName: String): Unit = {
//    mongoDb.
//  }

  override def transact_async(data: Data)(implicit ec: ExecutionContext): Future[TxReport] = {
    Future(transact_sync(data))
  }

  override def transact_sync(data: Data): TxReport = {
    val (collectionName, documents) = data
    val collection                  = mongoDb.getCollection(collectionName, classOf[BsonDocument])
    if (documents.size() == 1) {
      val result      = collection.insertOne(documents.get(0))
      val idHexString = result.getInsertedId.asInstanceOf[BsonObjectId].getValue.toHexString
      TxReport(List(idHexString))

    } else {
      val result: InsertManyResult             = collection.insertMany(documents)
      val idsMap: util.Map[Integer, BsonValue] = result.getInsertedIds
      val array                                = new Array[BsonValue](idsMap.size())
      idsMap.forEach { case (k, v) => array(k) = v }
      val ids = array.toList.map(_.asInstanceOf[BsonObjectId].getValue.toHexString)
      TxReport(ids)
    }
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