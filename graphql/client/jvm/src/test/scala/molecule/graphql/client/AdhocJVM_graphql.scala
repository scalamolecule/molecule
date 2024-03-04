package molecule.graphql.client

import molecule.graphql.client.dsl.Starwars._
import molecule.core.util.Executor._
import molecule.graphql.client.async._
import molecule.graphql.client.setup.TestSuite_graphql
import utest._
import scala.language.implicitConversions

object AdhocJVM_graphql extends TestSuite_graphql {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      for {

        // Using root query entry point methods to initiate molecules

        _ <- hero("EMPIRE").name.query.get

        _ <- character("1001").name.query.get
        _ <- character(1001L).name.query.get
        _ <- character(1001).name.query.get

        _ <- character(1001).name.Friends.name.query.get
        //        _ <- character(1001).name.Friends.__typename("Human").name.query.get
        _ <- character(1001).name.Friends.__typename.name.query.get

        _ <- human(1001).name.homePlanet.query.get
        _ <- human(1001).name.homePlanet("EARTH").query.get
        _ <- human(1001).name.homePlanet.not("EARTH").query.get

        _ <- droid(1001).name.primaryFunction.query.get


      } yield ()
    }
  }
}