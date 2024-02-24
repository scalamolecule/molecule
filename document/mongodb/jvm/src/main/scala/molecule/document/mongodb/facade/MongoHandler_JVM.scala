package molecule.document.mongodb.facade

import com.mongodb.client.{MongoClient, MongoClients, MongoDatabase}
import molecule.core.marshalling.MongoProxy
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName
import scala.concurrent.blocking


object MongoHandler_JVM {

  def recreateDb(proxy: MongoProxy): MongoConn_JVM = blocking {
    val container: MongoDBContainer = new MongoDBContainer(
      DockerImageName.parse("mongo").withTag("latest")
    )
    container.start()
    val mongoClient: MongoClient   = MongoClients.create(container.getConnectionString)
    val mongoDb    : MongoDatabase = mongoClient.getDatabase(proxy.dbName)
    mongoDb.drop()
    val conn = MongoConn_JVM(proxy, mongoClient, proxy.dbName, mongoDb)
    conn
  }

  def recreateDb(conn: MongoConn_JVM): MongoConn_JVM = blocking {
    // Start with empty database for each test
    conn.mongoDb.drop()
    conn
  }
}