package molecule.db.compliance.test.filterAttr.one

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Sorting(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Same entity" - types { implicit conn =>
    for {
      _ <- Entity.i.int.insert(
        (2, 3),
        (1, 4),
        (1, 3),
        (7, 3)
      ).transact

      _ <- Entity.i.<(Entity.int_).a1.int.a2.query.get.map(_ ==> List((1, 3), (1, 4), (2, 3)))
      _ <- Entity.i.<(Entity.int_).a1.int.d2.query.get.map(_ ==> List((1, 4), (1, 3), (2, 3)))
      _ <- Entity.i.<(Entity.int_).d1.int.a2.query.get.map(_ ==> List((2, 3), (1, 3), (1, 4)))
      _ <- Entity.i.<(Entity.int_).d1.int.d2.query.get.map(_ ==> List((2, 3), (1, 4), (1, 3)))

      _ <- Entity.i.<(Entity.int_).a2.int.a1.query.get.map(_ ==> List((1, 3), (2, 3), (1, 4)))
      _ <- Entity.i.<(Entity.int_).a2.int.d1.query.get.map(_ ==> List((1, 4), (1, 3), (2, 3)))
      _ <- Entity.i.<(Entity.int_).d2.int.a1.query.get.map(_ ==> List((2, 3), (1, 3), (1, 4)))
      _ <- Entity.i.<(Entity.int_).d2.int.d1.query.get.map(_ ==> List((1, 4), (2, 3), (1, 3)))

      // Same as

      _ <- Entity.i.a1.int.>(Entity.i_).a2.query.get.map(_ ==> List((1, 3), (1, 4), (2, 3)))
      _ <- Entity.i.a1.int.>(Entity.i_).d2.query.get.map(_ ==> List((1, 4), (1, 3), (2, 3)))
      _ <- Entity.i.d1.int.>(Entity.i_).a2.query.get.map(_ ==> List((2, 3), (1, 3), (1, 4)))
      _ <- Entity.i.d1.int.>(Entity.i_).d2.query.get.map(_ ==> List((2, 3), (1, 4), (1, 3)))

      _ <- Entity.i.a2.int.>(Entity.i_).a1.query.get.map(_ ==> List((1, 3), (2, 3), (1, 4)))
      _ <- Entity.i.a2.int.>(Entity.i_).d1.query.get.map(_ ==> List((1, 4), (1, 3), (2, 3)))
      _ <- Entity.i.d2.int.>(Entity.i_).a1.query.get.map(_ ==> List((2, 3), (1, 3), (1, 4)))
      _ <- Entity.i.d2.int.>(Entity.i_).d1.query.get.map(_ ==> List((1, 4), (2, 3), (1, 3)))
    } yield ()
  }


  "CrossEntity" - refs { implicit conn =>
    for {
      _ <- A.i.B.i.insert(
        (2, 3),
        (1, 4),
        (1, 3),
        (7, 3)
      ).transact

      // Since sort markers can't attach to tacit filter attributes there's no sorting ambiguity

      _ <- A.i.<(B.i_).a1.B.i.a2.query.get.map(_ ==> List((1, 3), (1, 4), (2, 3)))
      _ <- A.i.<(B.i_).a1.B.i.d2.query.get.map(_ ==> List((1, 4), (1, 3), (2, 3)))
      _ <- A.i.<(B.i_).d1.B.i.a2.query.get.map(_ ==> List((2, 3), (1, 3), (1, 4)))
      _ <- A.i.<(B.i_).d1.B.i.d2.query.get.map(_ ==> List((2, 3), (1, 4), (1, 3)))

      _ <- A.i.<(B.i_).a2.B.i.a1.query.get.map(_ ==> List((1, 3), (2, 3), (1, 4)))
      _ <- A.i.<(B.i_).a2.B.i.d1.query.get.map(_ ==> List((1, 4), (1, 3), (2, 3)))
      _ <- A.i.<(B.i_).d2.B.i.a1.query.get.map(_ ==> List((2, 3), (1, 3), (1, 4)))
      _ <- A.i.<(B.i_).d2.B.i.d1.query.get.map(_ ==> List((1, 4), (2, 3), (1, 3)))

      // Same as

      _ <- A.i.a1.B.i.>(A.i_).a2.query.get.map(_ ==> List((1, 3), (1, 4), (2, 3)))
      _ <- A.i.a1.B.i.>(A.i_).d2.query.get.map(_ ==> List((1, 4), (1, 3), (2, 3)))
      _ <- A.i.d1.B.i.>(A.i_).a2.query.get.map(_ ==> List((2, 3), (1, 3), (1, 4)))
      _ <- A.i.d1.B.i.>(A.i_).d2.query.get.map(_ ==> List((2, 3), (1, 4), (1, 3)))

      _ <- A.i.a2.B.i.>(A.i_).a1.query.get.map(_ ==> List((1, 3), (2, 3), (1, 4)))
      _ <- A.i.a2.B.i.>(A.i_).d1.query.get.map(_ ==> List((1, 4), (1, 3), (2, 3)))
      _ <- A.i.d2.B.i.>(A.i_).a1.query.get.map(_ ==> List((2, 3), (1, 3), (1, 4)))
      _ <- A.i.d2.B.i.>(A.i_).d1.query.get.map(_ ==> List((1, 4), (2, 3), (1, 3)))
    } yield ()
  }
}
