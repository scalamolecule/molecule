package molecule.db.h2.compliance.subquery.sub

import scala.concurrent.Future
import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.spi.Conn
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.h2.async.*
import molecule.db.h2.setup.DbProviders_h2


class Semantics extends MUnit with DbProviders_h2 with TestUtils {


  "Tacit/mandatory attribute" - types {
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
      _ <- Entity.s.i.>(Ref.i).query.get.map(_ ==> List(
        ("b", 4, 2), // Ref.i == 2
        ("b", 4, 3), // Ref.i == 3
      ))

      // Subquery without correlation is repeated for all outer rows
      _ <- Entity.s.sub(Ref.id(count)).query.get.map(_ ==> List(
        ("a", 2), // Ref.id(count) is 2 for all outer rows
        ("b", 2),
      ))
    } yield ()
  }


  "Empty sub rows filtered out" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (1, 1),
        (2, 3),
      ).transact

      _ <- Ref.i.insert(7).transact

      _ <- Ref.i.sub(Entity.i_?.ref_(Ref.id_)).query.i.get.map(_ ==> List(
        (1, Some(1)),
        (3, Some(2)),
        (7, None), // correct
      ))
      _ <- Ref.i.sub(Entity.i.ref_(Ref.id_)).query.i.get.map(_ ==> List(
        (1, 1),
        (3, 2),
        //        (7, null), // excluded
      ))
    } yield ()
  }

  "Empty sub rows still aggregated" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2)),
        ("b", List(3, 4, 5)),
        ("c", List()),
      ).transact

      // Approach 1: Two separate .sub() calls - creates two independent subqueries
      _ <- Entity.s
        .sub(Ref.id(count).entity_(Entity.id_))
        .sub(Ref.i(sum).entity_(Entity.id_))
        .query.get.map(_ ==> List(
          ("a", 2, 3),
          ("b", 3, 12),
          ("c", 0, 0), // Both subqueries return 0 for empty relationship
        ))
    } yield ()
  }
}
