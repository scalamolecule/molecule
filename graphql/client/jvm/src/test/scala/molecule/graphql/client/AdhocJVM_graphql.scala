package molecule.graphql.client

import molecule.db.compliance.setup.Test
import scala.io.Source

class AdhocJVM_graphql extends Test {

  import ujson.*

  val endpoint = "https://swapi-graphql.netlify.app/graphql"

  def graphql(query: String): String = {
    val data         = Obj("query" -> Str(query))
    val dataRendered = data.render()
    println(dataRendered)
    val response = requests.post(
      url = endpoint,
      headers = Seq("Content-Type" -> "application/json"),
      data = data.render()
    )
    response.text()
  }

  val types = graphql(
    """{
          __schema {
            types {
              name
            }
          }
       }"""
  )

  val luke = graphql(
    """{
         person(personID: 1) {
           id
           name
         }
       }"""
  )

  println("-----------------")
  println(types)
  println("-----------------")
  println(luke)
  println("-----------------")

  //  enum Episode:
  //    case NEWHOPE, EMPIRE, JEDI

  //  "types" - graphql(StarwarsSchema) { implicit starwars =>
  //  "types" - { //implicit starwars =>
  //    for {
  //
  //      // Using root query entry point methods to initiate molecules
  //
  //      _ <- Starwars.hero("EMPIRE").name.query.get
  //
  //      _ <- character(1001L).name.query.get
  //      _ <- character(1001).name.query.get
  //
  //      _ <- character(1001).name.Friends.name.query.get
  //
  //      _ <- human(1001).name.homePlanet.query.get
  //      _ <- human(1001).name.homePlanet("EARTH").query.get
  //      _ <- human(1001).name.homePlanet.not("EARTH").query.get
  //
  //      _ <- droid(1001).name.primaryFunction.query.get
  //
  //      //        _ <- Human.apply("hej").name.query.get
  //
  //
  //    } yield ()
  //  }
  //  sealed trait Episode






  "read schema file" - {

    //    val file   = "molecule/graphql/client/dataModel/Starwars.graphql"
    //    val file   = "/Users/mg/molecule/molecule/molecule/graphql/client/shared/src/main/resources/Starwars.graphql"
    //    val file   = "graphql/client/shared/src/main/resources/Starwars.graphql"
    //    val file   = "client/shared/src/main/resources/Starwars.graphql"
    //    val file   = "resources/Starwars.graphql"
    //    val file   = "molecule/graphql/client/shared/src/main/scala/molecule/graphql/client/dataModel/Starwars.scala"
    //    val file   = "molecule/graphql/client/dataModel/Starwars.scala"

    val file   = "Starwars.graphql"
    val source = Source.fromResource(file).mkString

    // Public Starwars graphql endpoint!:
    // https://swapi-graphql.netlify.app/graphql

    //      println("XXX ------------------\n" + source.getLines().mkString("\n"))
    //    println(source)


    //      println("------------------")
    //      val doc = Parser.parseQueryEither(source).right.get

    //      println("XXX ------------------\n" + doc.map(_.objectTypeDefinitions))
    //      println("XXX ------------------\n" + doc.objectTypeDefinitions)
    //      println("XXX ------------------\n" + doc.enumTypeDefinitions.mkString("\n-----\n"))
    //      for{
    ////        doc <- Parser.parseQuery(source)
    //        doc <- Parser.parseQueryEither(source)
    //        _ <- println(42)
    //        _ = doc.interfaceTypeDefinitions.foreach(println)
    //        _ = doc.objectTypeDefinitions.foreach(println)
    //        _ = println("hej")
    //
    //      } yield ()

  }
}