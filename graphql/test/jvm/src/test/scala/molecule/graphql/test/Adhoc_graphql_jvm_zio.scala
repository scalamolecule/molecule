package molecule.graphql.test

import caliban.parsing.adt.Definition.TypeSystemDefinition.TypeDefinition.*
import caliban.tools.SchemaLoader
import ujson.*
import scala.io.Source

//class AdhocJVM_graphql extends Test {
object Adhoc_graphql_jvm_zio extends StarWarsTest_zio {


  val url = "https://swapi-graphql.netlify.app/graphql"

  def graphql(query: String): String = {
    val data         = Obj("query" -> Str(query))
    val dataRendered = data.render()
    println(dataRendered)
    val response = requests.post(
      url = url,
      headers = Seq("Content-Type" -> "application/json"),
      data = data.render()
    )
    response.text()
  }
  //
  //  lazy val hero = graphql(
  //    """query MyQuery {
  //      |  person(personID: 1) {
  //      |    name
  //      |  }
  //      |}""".stripMargin
  //  )
  //
  //  lazy val types = graphql(
  //    """{
  //          __schema {
  //            types {
  //              name
  //            }
  //          }
  //       }"""
  //  )
  //
  //  lazy val luke = graphql(
  //    """{
  //         person(personID: 1) {
  //           id
  //           name
  //         }
  //       }"""
  //  )

  //  println("-----------------")
  //  println(hero)
  //  println("-----------------")
  //  println(types)
  //  println("-----------------")
  //  println(luke)
  //  println("-----------------")

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

  override def spec = {
    suite("Schema introspection")(

      test("read schema file") {

        def load(loader: SchemaLoader) = loader.load.map { doc =>
          doc.typeDefinitions.collect {
            case ObjectTypeDefinition(a, name, b, c, fields)    => println("1 " + name + "\n    " + fields.map(_.ofType).mkString(", "))
            case InputObjectTypeDefinition(_, name, _, fields)  => println("2 " + name + "\n    " + fields.map(_.name).mkString(", "))
            case EnumTypeDefinition(_, name, _, fields)         => println("3 " + name + "\n    " + fields.map(_.enumValue).mkString(", "))
            case UnionTypeDefinition(_, name, _, fields)        => println("4 " + name + "\n    " + fields.mkString(", "))
            case ScalarTypeDefinition(_, name, fields)          => println("5 " + name + "\n    " + fields.map(_.name).mkString(", "))
            case InterfaceTypeDefinition(_, name, _, _, fields) => println("6 " + name + "\n    " + fields.map(_.name).mkString(", "))
          }
          1 ==> 1
        }

        load(SchemaLoader.fromString(Source.fromResource("Starwars.graphql").mkString))
        //        println("################################################################")
        //        load(SchemaLoader.fromString(Source.fromResource("Starwars2.graphql").mkString))
        //        load(SchemaLoader.fromFile("graphql/client/shared/src/main/resources/Starwars.graphql"))

        //        val url = "https://swapi-graphql.netlify.app/graphql"
        //        load(FromIntrospection(url, None, false))
        //        load(fromIntrospectionWith(url, None)(_.supportIsRepeatable(true)))
        //load(fromIntrospection(url, None, IntrospectionClient.Config.default))
//        load(SchemaLoader.fromIntrospection(url, None))
      }
    )
  }
}