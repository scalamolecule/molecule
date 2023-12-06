package molecule.document.mongodb.setup

import com.mongodb.client.{MongoClient, MongoClients, MongoDatabase}
import molecule.base.api.Schema
import molecule.core.marshalling.MongoProxy
import molecule.coreTests.dataModels.core.schema._
import molecule.document.mongodb.facade.MongoConn_JVM
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName

object Connection_mongodb {

  //  // Scala test container setup
  //  val mongoDBContainer = MongoDBContainer(DockerImageName.parse("mongo").withTag("latest"))
  //  mongoDBContainer.start()
  //  // Scala testcontainer shifts port, so we need to get the generated port
  //  val mongoClient = MongoClient(mongoDBContainer.replicaSetUrl)
  //  val collection = mongoClient.getDatabase("test").getCollection[BsonDocument]("testCollection")

  // Java test container setup
  val container: MongoDBContainer = new MongoDBContainer(
    DockerImageName.parse("mongo").withTag("latest")
  )
  container.start()
  val mongoClient: MongoClient   = MongoClients.create(container.getConnectionString)
  val dbName                     = "test"
  val mongoDb    : MongoDatabase = mongoClient.getDatabase(dbName)

  def proxy(schema: Schema) = {
    MongoProxy(
      "mongodb://localhost:27017",
      dbName,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      useTestContainer = true
    )
  }

  // Use same test database for all tests
  val conn_Types      = MongoConn_JVM(proxy(TypesSchema), mongoClient, dbName, mongoDb)
  val conn_Refs       = MongoConn_JVM(proxy(RefsSchema), mongoClient, dbName, mongoDb)
  val conn_Uniques    = MongoConn_JVM(proxy(UniquesSchema), mongoClient, dbName, mongoDb)
  val conn_Validation = MongoConn_JVM(proxy(ValidationSchema), mongoClient, dbName, mongoDb)
}
