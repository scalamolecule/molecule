package molecule.coreTests.spi.filter.map

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future

trait MapSemantics extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Special Map attribute semantics" - types { implicit conn =>
      for {
        // For simple lookup maps, it's easier to use a map attribute:
        _ <- Ns.i.intMap.insert(
          (10, Map("a" -> 1, "b" -> 2)),
          (20, Map("a" -> 3, "b" -> 4, "c" -> 5)),
        ).transact

        // Get entire Map
        _ <- Ns.i.a1.intMap.query.get.map(_ ==> List(
          (10, Map("a" -> 1, "b" -> 2)),
          (20, Map("a" -> 3, "b" -> 4, "c" -> 5)),
        ))

        // Lookup values by key
        _ <- Ns.i.a1.intMap("a").query.get.map(_ ==> List(
          (10, 1),
          (20, 3),
        ))
        _ <- Ns.i.a1.intMap_("a").query.get.map(_ ==> List(
          10,
          20,
        ))

        // Lookup optional value by key
        _ <- Ns.i.a1.intMap_?("c").query.get.map(_ ==> List(
          (10, None),
          (20, Some(5)),
        ))

        // Avoid values by key
        _ <- Ns.i.a1.intMap_.not("c").query.get.map(_ ==> List(10))

        // Match by value (only on tacit map attributes)
        _ <- Ns.i.a1.intMap_.has(4).query.get.map(_ ==> List(20))
        _ <- Ns.i.a1.intMap_.hasNo(4).query.get.map(_ ==> List(10))
      } yield ()
    }

    "Custom alternative to Map attribute" - types { implicit conn =>
      for {
        // one-to-many relationship could be created to save Map data.
        _ <- Ns.i.Refs.*(Ref.s.i).insert(
          (10, List("a" -> 1, "b" -> 2)),
          (20, List("a" -> 3, "b" -> 4)),
        ).transact

        // This is cumbersome but gives power to make complex queries
        _ <- Ns.i.Refs.*(Ref.s.contains("b").i.>(3)).query.get.map(_ ==> List(
          (20, List("b" -> 4)),
        ))
      } yield ()
    }


    "Matching entire map not supported" - types { implicit conn =>
      for {
        _ <- Ns.intMap(Map(pint1)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Matching collections (Ns.intMap) not supported in queries."
          }

        _ <- Ns.i.intMap_?(Some(Map(pint1))).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Matching collections (Ns.intMap) not supported in queries."
          }

        _ <- Future(compileError("Ns.intMap_(Map(pint1)).query.get"))
      } yield ()
    }


    "equal nothing" - types { implicit conn =>
      for {
        _ <- Ns.i.intMap_?.insert(List(
          (0, None),
          (1, Some(Map("a" -> int1))),
        )).transact

        // Match non-asserted attribute (null) with tacit attribute
        _ <- Ns.i.intMap_().query.get.map(_ ==> List(0))

        // Can't query for empty attribute
        _ <- Ns.i.intMap().query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Applying nothing to mandatory attribute (Ns.intMap) is reserved for updates to retract."
          }
      } yield ()
    }
  }
}