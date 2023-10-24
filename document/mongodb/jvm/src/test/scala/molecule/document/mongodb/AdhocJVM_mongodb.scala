package molecule.document.mongodb

import com.mongodb.client.result.InsertOneResult
import com.mongodb.client.{FindIterable, MongoClients, MongoCollection}
import molecule.core.util.Executor._
import molecule.document.mongodb.setup.TestSuite_mongodb
import org.bson._
import org.bson.json.JsonWriterSettings
import org.bson.types.ObjectId
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName
import utest._
import scala.concurrent.Future
import scala.language.implicitConversions


object AdhocJVM_mongodb extends TestSuite_mongodb {


  val container: MongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo").withTag("latest"))
  container.start()
  val client     = MongoClients.create(container.getConnectionString)

  val db         = client.getDatabase("test")

//  db.drop()

  val collection: MongoCollection[BsonDocument] = db.getCollection("testCollection", classOf[BsonDocument])

  val arr = new java.util.ArrayList[BsonValue]()
  arr.add(new BsonString("Documentary"))
  arr.add(new BsonString("Comedy"))

  val insertOneResult: InsertOneResult = collection.insertOne(
    new BsonDocument()
//      .append("id", new BsonObjectId())
//      .append("id", new BsonObjectId(new ObjectId(42)))
      .append("id", new BsonObjectId(new ObjectId("42")))
      .append("title", new BsonString("Ski Bloopers"))
      .append("title", new BsonInt32(42))
      .append("genres", new BsonArray(arr))
  )

  //  BsonDocument bsonDocument = bson.toBsonDocument(BsonDocument.
  //  class, MongoClient.getDefaultCodecRegistry()
  //  );
  //  JsonWriterSettings.Builder settingsBuilder = JsonWriterSettings.builder().indent(true);
  //  System.out.println(bsonDocument.toJson(settingsBuilder.build());

  val found: FindIterable[BsonDocument] = collection.find()

  println("############# yeah ##########################################################################")
  println(insertOneResult.getInsertedId)
  val pretty: JsonWriterSettings = JsonWriterSettings.builder().indent(true).build()
  found.forEach(d => println(d.toJson(pretty)))


  /*
              val result: Nothing = collection.insertOne(new Nothing()
              .append("id", new Nothing)
              .append("title", "Ski Bloopers")
              .append("genres", Arrays.asList("Documentary", "Comedy")))

   */

  //
  //  //    val f = for {
  //  //      _ <- client.getDatabase("test").createCollection("testCollection").head()
  //  //      //    _ <- collection.insertMany(documents.map(_.bson.asDocument())).head()
  //  //    } yield ()
  //  //
  //  //    Await.result(f, 30.seconds)
  //  //
  //  //    val mongoDBContainer = MongoDBContainer(
  //  //      DockerImageName.parse("mongo")
  //  //        .withTag("latest")
  //  //      //      .withRepository("localhost")
  //  //      //      .withNetwork(Network.newNetwork())
  //  //    )
  //  //
  //  //
  //  //    mongoDBContainer.start()
  //  //    mongoDBContainer.container.waitingFor(Wait.forListeningPort.withStartupTimeout(Duration.ofSeconds(30)))
  //  //
  //  //    assert(mongoDBContainer.replicaSetUrl.nonEmpty)
  //  //
  //  //
  //  //    val mongoClient = MongoClient()
  //  //    //  val mongoClient = MongoClient("mongodb://localhost:27017")
  //  //    //  val mongoClient = MongoClient("mongodb://0.0.0.0:27017")
  //  //
  //  //    //  val db: MongoDatabase = mongoClient.getDatabase("test")
  //  //    //  db.createCollection("hehe")
  //  //    //  db.listCollectionNames().foreach(println)
  //  //
  //  //
  //  //    val db: MongoDatabase = mongoClient.getDatabase("test")
  //  //
  //  //    // get a handle to the "test" collection
  //  //    val collection: MongoCollection[Document] = db.getCollection("col")
  //  //
  //  //    Thread.sleep(10000)
  //  //
  //  //    collection.drop().results()
  //  //
  //  //    // make a document and insert it
  //  //    val doc: Document = Document(
  //  //      "id" -> 0,
  //  //      "name" -> "MongoDB",
  //  //      "type" -> "database",
  //  //      "count" -> 1,
  //  //      "info" -> Document("x" -> 203, "y" -> 102)
  //  //    )
  //  //
  //  //    collection.insertOne(doc).results()
  //  //
  //  //    // get it (since it's the only one in there since we dropped the rest earlier on)
  //  //    collection.find.first().printResults()
  //  //
  //  //    // now, lets add lots of little documents to the collection so we can explore queries and cursors
  //  //    val documents: IndexedSeq[Document] = (1 to 100) map { i: Int =>
  //  //      Document("i" -> i)
  //  //    }
  //  //    val insertObservable                = collection.insertMany(documents)
  //  //
  //  //    val insertAndCount = for {
  //  //      insertResult <- insertObservable
  //  //      countResult <- collection.countDocuments()
  //  //    } yield countResult
  //  //
  //  //    println(s"total # of documents after inserting 100 small ones (should be 101):  ${insertAndCount.headResult()}")
  //  //
  //  //    collection.find().first().printHeadResult()
  //  //
  //  //    // Query Filters
  //  //    // now use a query to get 1 document out
  //  //    collection.find(equal("i", 71)).first().printHeadResult()
  //  //
  //  //    // now use a range query to get a larger subset
  //  //    collection.find(gt("i", 50)).printResults()
  //  //
  //  //    // range query with multiple constraints
  //  //    collection.find(and(gt("i", 50), lte("i", 100))).printResults()
  //  //
  //  //    // Sorting
  //  //    collection.find(exists("i")).sort(descending("i")).first().printHeadResult()
  //  //
  //  //    // Projection
  //  //    collection.find().projection(excludeId()).first().printHeadResult()
  //  //
  //  //    // Aggregation
  //  //    collection
  //  //      .aggregate(
  //  //        Seq(
  //  //          filter(gt("i", 0)),
  //  //          project(Document("""{ITimes10: {$multiply: ["$i", 10]}}"""))
  //  //        )
  //  //      )
  //  //      .printResults()
  //  //
  //  //    // Update One
  //  //    collection.updateOne(equal("i", 10), set("i", 110)).printHeadResult("Update Result: ")
  //  //
  //  //    // Update Many
  //  //    collection.updateMany(lt("i", 100), inc("i", 100)).printHeadResult("Update Result: ")
  //  //
  //  //    // Delete One
  //  //    collection.deleteOne(equal("i", 110)).printHeadResult("Delete Result: ")
  //  //
  //  //    // Delete Many
  //  //    collection.deleteMany(gte("i", 100)).printHeadResult("Delete Result: ")
  //  //
  //  //    // Create Index
  //  //    collection.createIndex(Document("i" -> 1)).printHeadResult("Create Index Result: %s")
  //  //
  //  //    // Clean up
  //  //    collection.drop().results()
  //  //
  //  //    // release resources
  //  //    mongoClient.close()
  //
  //  val doc = BsonDocument(
  //    "id" -> 0,
  //    "name" -> "Bob",
  //    "age" -> BsonNumber(42),
  //    "age" -> new BsonInt32(42),
  //  )
  //
  //  doc.append("hej", new BsonInt64(84L))
  //  val f =

  override lazy val tests = Tests {

    //    "types" - types { implicit conn =>
    "types" - {
      for {
        //        _ <- Ns.int(1).save.i.transact
        //              _ <- Ns.int.insert(1).i.transact
        //        _ <- Ns.int.query.i.get.map(_ ==> List(1))

        _ <- Future(1)

        //        _ <- client.getDatabase("test").createCollection("testCollection").head()
        //            _ <- collection.insertOne(Document("id" -> 0, "name" -> "Bob")) .toFuture()
        //      1 ==> 1
        //        _ <- collection.insertOne(Document("""{"name": "Ben"}"""))
        //        _ <- collection.insertOne(
        //          BsonDocument(
        //            "id" -> 0,
        //            "name" -> "Bob",
        //            "age" -> new BsonInt32(42),
        //          )
        //        ).toFuture()
        //        //
        //        ////        _ = collection.find().first().printHeadResult()
        //        x <- collection.find().first().toFuture()
        //        //
        //        _ = {
        //          println("################################ " + x)
        //          x ==> 7
        //        }
        //        _ <- collection.drop().results()

        //        _ <- rawTransact(
        //          """UPDATE Ns SET
        //            |  ints = (
        //            |    SELECT JSON_MERGE(JSON_ARRAYAGG(t_1.vs), JSON_ARRAY(8))
        //            |    FROM JSON_TABLE(Ns.ints, '$[*]' COLUMNS (vs int PATH '$')) t_1
        //            |    WHERE t_1.vs NOT IN(6)
        //            |  )
        //            |WHERE Ns.id IN(1) AND
        //            |  Ns.ints IS NOT NULL
        //            |""".stripMargin)

        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  SUM(DISTINCT Ns.double)
        //            |FROM Ns
        //            |WHERE
        //            |  Ns.double IS NOT NULL;
        //            |""".stripMargin, true)

      } yield {
        //        mongoClient.close()

        1 ==> 2
      }
    }

    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    //        id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
    //        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))
    //
    //
    //      } yield ()
    //    }
    //
    //
    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //        _ <- Uniques.i(1).save.transact
    //
    //      } yield ()
    //    }


    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //      } yield ()
    //    }
  }
}
