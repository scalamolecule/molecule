package molecule.db.compliance.test.relationship

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders


case class ManyToMany(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "M2M property" - joinTable {
    import molecule.db.compliance.domains.dsl.JoinTable.*
    for {
      // Entities to be joined
      List(a1, a2) <- A.i.insert(1, 2).transact.map(_.ids)
      List(b1, b2) <- B.i.insert(3, 4).transact.map(_.ids)

      // Insert joins
      _ <- J.a.i.b.insert(
        (a1, 10, b1),
        (a2, 20, b2),
      ).transact

      // Access join property
      _ <- A.i.Js.i.a1.B.i.query.get.map(_ ==> List(
        (1, 10, 3),
        (2, 20, 4),
      ))

      // Reverse direction
      _ <- B.i.Js.i.a1.A.i.query.get.map(_ ==> List(
        (3, 10, 1),
        (4, 20, 2),
      ))
    } yield ()
  }

  "Bridge to target values" - joinTable {
    import molecule.db.compliance.domains.dsl.JoinTable.*
    for {
      // Entities to be joined
      List(a1, a2) <- A.i.insert(1, 2).transact.map(_.ids)
      List(b1, b2, b3) <- B.i.insert(3, 4, 5).transact.map(_.ids)

      // Insert joins
      _ <- J.a.b.insert(
        (a1, b1),
        (a2, b2),
        (a2, b3)
      ).transact

      // Via join
      _ <- A.i.Js.B.i.a1.query.get.map(_ ==> List(
        (1, 3),
        (2, 4),
        (2, 5)
      ))
      _ <- B.i.a1.Js.A.i.query.get.map(_ ==> List(
        (3, 1),
        (4, 2),
        (5, 2)
      ))

      // Bridging to target (same as above)
      _ <- A.i.Bs.i.a1.query.get.map(_ ==> List(
        (1, 3),
        (2, 4),
        (2, 5)
      ))
      _ <- B.i.a1.As.i.query.get.map(_ ==> List(
        (3, 1),
        (4, 2),
        (5, 2)
      ))
    } yield ()
  }

  "Nested m2m values" - joinTable {
    import molecule.db.compliance.domains.dsl.JoinTable.*
    for {
      // Entities to be joined
      List(a1, a2) <- A.i.insert(1, 2).transact.map(_.ids)
      List(b1, b2, b3) <- B.i.insert(3, 4, 5).transact.map(_.ids)

      // Insert joins
      _ <- J.a.b.insert(
        (a1, b1),
        (a2, b2),
        (a2, b3)
      ).transact

      // Nested via join
      _ <- A.i.a1.Js.*(J.B.i.a1).query.get.map(_ ==> List(
        (1, List(3)),
        (2, List(4, 5))
      ))
      _ <- B.i.a1.Js.*(J.A.i).query.get.map(_ ==> List(
        (3, List(1)),
        (4, List(2)),
        (5, List(2))
      ))

      // Nested joined values
      _ <- A.i.a1.Bs.**(B.i.a1).query.get.map(_ ==> List(
        (1, List(3)),
        (2, List(4, 5))
      ))
      _ <- B.i.a1.As.**(A.i).query.get.map(_ ==> List(
        (3, List(1)),
        (4, List(2)),
        (5, List(2))
      ))
    } yield ()
  }
}
