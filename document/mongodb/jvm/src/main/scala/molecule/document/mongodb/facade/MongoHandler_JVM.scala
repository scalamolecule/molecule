package molecule.document.mongodb.facade

import com.mongodb.client.{MongoClient, MongoClients, MongoDatabase}
import molecule.core.marshalling.MongoProxy
import scala.concurrent.blocking


object MongoHandler_JVM {

  def recreateDb(proxy: MongoProxy): MongoConn_JVM = blocking {
    val mongoClient: MongoClient   = MongoClients.create(proxy.connectionString)
    val mongoDb    : MongoDatabase = mongoClient.getDatabase(proxy.dbName)
    mongoDb.drop()
    val conn = MongoConn_JVM(proxy, mongoDb)
    conn
  }

  // For docker test containers
  def recreateDb(conn: MongoConn_JVM): MongoConn_JVM = blocking {
    // Start with empty database for each test
    conn.mongoDb.drop()
    conn
  }
}