package molecule.db.compliance.test.filter.map

import scala.concurrent.Future
import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class MapSemantics(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Special Map attribute semantics" - types {
    for {
      // For simple lookup maps, it's easier to use a map attribute:
      _ <- Entity.i.intMap.insert(
        (10, Map("a" -> 1, "b" -> 2)),
        (20, Map("a" -> 3, "b" -> 4, "c" -> 5)),
      ).transact

      // Get entire Map
      _ <- Entity.i.a1.intMap.query.get.map(_ ==> List(
        (10, Map("a" -> 1, "b" -> 2)),
        (20, Map("a" -> 3, "b" -> 4, "c" -> 5)),
      ))

      // Lookup values by key
      _ <- Entity.i.a1.intMap("a").query.get.map(_ ==> List(
        (10, 1),
        (20, 3),
      ))
      _ <- Entity.i.a1.intMap_("a").query.get.map(_ ==> List(
        10,
        20,
      ))

      // Lookup optional value by key
      _ <- Entity.i.a1.intMap_?("c").query.get.map(_ ==> List(
        (10, None),
        (20, Some(5)),
      ))

      // Avoid values by key
      _ <- Entity.i.a1.intMap_.not("c").query.get.map(_ ==> List(10))

      // Match by value (only on tacit map attributes)
      _ <- Entity.i.a1.intMap_.has(4).query.get.map(_ ==> List(20))
      _ <- Entity.i.a1.intMap_.hasNo(4).query.get.map(_ ==> List(10))
    } yield ()
  }

  "Custom alternative to Map attribute" - types {
    for {
      // one-to-many relationship could be created to save Map data.
      _ <- Entity.i.Refs.*(Ref.s.i).insert(
        (10, List("a" -> 1, "b" -> 2)),
        (20, List("a" -> 3, "b" -> 4)),
      ).transact

      // This is cumbersome but gives power to make complex queries
      _ <- Entity.i.Refs.*(Ref.s.contains("b").i.>(3)).query.get.map(_ ==> List(
        (20, List("b" -> 4)),
      ))
    } yield ()
  }


  "Matching entire map not supported" - types {
    for {
      _ <- Entity.intMap(Map(pint1)).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Matching collections (Entity.intMap) not supported in queries."
        }

      _ <- Entity.i.intMap_?(Some(Map(pint1))).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Matching collections (Entity.intMap) not supported in queries."
        }

      _ <- Future(compileErrors("Entity.intMap_(Map(pint1)).query.get"))
    } yield ()
  }


  "equal nothing" - types {
    for {
      _ <- Entity.i.intMap_?.insert(List(
        (0, None),
        (1, Some(Map("a" -> int1))),
      )).transact

      // Match non-asserted attribute (null) with tacit attribute
      _ <- Entity.i.intMap_().query.get.map(_ ==> List(0))

      // Can't query for empty attribute
      _ <- Entity.i.intMap().query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Applying nothing to mandatory attribute (Entity.intMap) is reserved for updates to retract."
        }
    } yield ()
  }
}