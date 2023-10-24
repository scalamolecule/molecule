package molecule.document.mongodb.setup

import com.mongodb.client.{MongoClient, MongoClients, MongoDatabase}
import molecule.base.api.Schema
import molecule.core.marshalling.MongoProxy
import molecule.coreTests.dataModels.core.schema._
import molecule.document.mongodb.facade.MongoDBConn_JVM
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
  val container: MongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo").withTag("latest"))
  container.start()
  val mongoClient: MongoClient   = MongoClients.create(container.getConnectionString)
  val mongoDb    : MongoDatabase = mongoClient.getDatabase("test")

  def proxy(schema: Schema) = {
    MongoProxy(
      "mongodb://localhost:27017",
      "test",
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      useTestContainer = true
    )
  }

  // Use same test database for all tests
  val conn_Types      = MongoDBConn_JVM(proxy(TypesSchema), mongoDb)
  val conn_Refs       = MongoDBConn_JVM(proxy(RefsSchema), mongoDb)
  val conn_Uniques    = MongoDBConn_JVM(proxy(UniquesSchema), mongoDb)
  val conn_Validation = MongoDBConn_JVM(proxy(ValidationSchema), mongoDb)
}
