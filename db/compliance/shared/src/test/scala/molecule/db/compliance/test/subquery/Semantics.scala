package molecule.db.compliance.test.subquery

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class Semantics(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "tacit/mandatory attribute" - types {
    for {
      _ <- Entity.s.i.Ref.i.s.insert(
        ("a", 1, 2, "x"),
        ("b", 4, 3, "y"),
      ).transact

      // Filter attribute (tacit) - points to outer Ref.i
      _ <- Entity.s.i.>(Ref.i_).Ref.i.s.query.get.map(_ ==> List(
        ("b", 4, 3, "y"),
      ))

      // Filter attribute should be tacit
      _ <- Entity.s.i.>(Ref.i).Ref.i.s
        .query.get.map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attribute Ref.i should be tacit."
        }

      // Note that when a compared mandatory attribute (Ref.i) points to no outer attribute,
      // a cross join with a subquery is build instead with no correlation to the outer query.
      _ <- Entity.s.i.>(Ref.i).a1.query.get.map(_ ==> List(
        ("b", 4, 2), // Ref.i == 2
        ("b", 4, 3), // Ref.i == 3
      ))

      // Subquery without correlation is repeated for all outer rows
      _ <- Entity.s.select(Ref.id(count)).query.get.map(_ ==> List(
        ("a", 2), // Ref.id(count) is 2 for all outer rows
        ("b", 2),
      ))
    } yield ()
  }


  "empty sub rows filtered out" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (1, 1),
        (2, 3),
      ).transact

      _ <- Ref.i.insert(7).transact

      _ <- Ref.i.select(Entity.i_?.ref_(Ref.id_)).query.get.map(_ ==> List(
        (1, Some(1)),
        (3, Some(2)),
        (7, None), // correct
      ))
      _ <- Ref.i.select(Entity.i.ref_(Ref.id_)).query.get.map(_ ==> List(
        (1, 1),
        (3, 2),
        //        (7, null), // excluded
      ))
    } yield ()
  }

  "empty sub rows still aggregated" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2)),
        ("b", List(3, 4, 5)),
        ("c", List()),
      ).transact

      _ <- Entity.s.select(Ref.id(count).i(sum).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", (2, 3)),
        ("b", (3, 12)),
        ("c", (0, 0)), // Both subqueries return default value 0 for empty relationship
      ))
    } yield ()
  }
}
