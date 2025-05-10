package molecule.graphql.client

import molecule.graphql.client.dsl.Starwars._
import molecule.core.util.Executor._
import molecule.graphql.client.async._
import molecule.graphql.client.schema.StarwarsSchema
import molecule.graphql.client.setup.TestSuite_graphql
import scala.concurrent.Future
import scala.io.Source
//import scala.language.implicitConversions
import caliban.parsing.Parser
import caliban.parsing.adt.Definition.TypeSystemDefinition.TypeDefinition._
import caliban.parsing.adt.Document
import caliban.tools.{ClientWriter, Formatter}

object AdhocJVM_graphql extends FunSuite {


//  "types" - graphql(StarwarsSchema) { implicit conn =>
//    for {
//
//      // Using root query entry point methods to initiate molecules
//
//      _ <- hero("EMPIRE").name.query.get
//
//      _ <- character(1001L).name.query.get
//      _ <- character(1001).name.query.get
//
//      _ <- character(1001).name.Friends.name.query.get
//      //        _ <- character(1001).name.Friends.__typename("Human").name.query.get
//      //        _ <- character(1001).name.Friends.__typename.name.query.get
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
//
//
//  "graphql" - {
//
//    val file   = "molecule/graphql/client/dataModel/Starwars.graphql"
//    val source = Source.fromResource(file).mkString
//
//    //      println("XXX ------------------\n" + source.getLines().mkString("\n"))
//    println("XXX ------------------\n" + source)
//
//
//    //      println("------------------")
//    //      val doc = Parser.parseQueryEither(source).right.get
//
//    //      println("XXX ------------------\n" + doc.map(_.objectTypeDefinitions))
//    //      println("XXX ------------------\n" + doc.objectTypeDefinitions)
//    //      println("XXX ------------------\n" + doc.enumTypeDefinitions.mkString("\n-----\n"))
//    //      for{
//    ////        doc <- Parser.parseQuery(source)
//    //        doc <- Parser.parseQueryEither(source)
//    //        _ <- println(42)
//    //        _ = doc.interfaceTypeDefinitions.foreach(println)
//    //        _ = doc.objectTypeDefinitions.foreach(println)
//    //        _ = println("hej")
//    //
//    //      } yield ()
//
//  }
}