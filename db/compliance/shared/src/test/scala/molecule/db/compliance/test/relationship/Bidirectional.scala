package molecule.db.compliance.test.relationship

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders


case class Bidirectional(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Query in both directions, basic" - refs {
    import molecule.db.compliance.domains.dsl.Refs.*
    for {
      // Foreign key in A pointing to B (many-to-one relationship)
      _ <- A.i(1).B.i(2).save.transact

      // Many-to-one
      _ <- A.i.B.i.query.get.map(_ ==> List((1, 2)))

      // One-to-Many, uses generated accessor Aa
      _ <- B.i.Aa.i.query.get.map(_ ==> List((2, 1)))
    } yield ()
  }


  "One-to-many as nested" - refs {
    import molecule.db.compliance.domains.dsl.Refs.*
    for {

      List(b1, b2) <- B.i.insert(1, 2).transact.map(_.ids)

      // Foreign key in A pointing to B (many-to-one relationship)
      _ <- A.i.b.insert(
        (1, b1),
        (2, b1),
        (3, b2)
      ).transact

      // Many-to-one
      _ <- A.i.a1.B.i.query.get.map(_ ==> List(
        (1, 1),
        (2, 1),
        (3, 2)
      ))

      // One-to-Many
      _ <- B.i.Aa.i.a1.query.get.map(_ ==> List(
        (1, 1),
        (1, 2),
        (2, 3)
      ))

      // One-to-many relationship can even be nested
      _ <- B.i.a1.Aa.*(A.i.a1).query.get.map(_ ==> List(
        (1, List(1, 2)),
        (2, List(3))
      ))
    } yield ()
  }
}
