package molecule.db.compliance.test.aggregation.ref

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import org.scalactic.Equality

case class AggrRef_min_max_n(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "ref" - refs {
    for {
      _ <- A.i.B.i.insert(
        (1, 1),
        (1, 2),
        (1, 3),
        (2, 4),
        (2, 5),
        (2, 6),
        (2, 6), // (make sure grouped values coalesce)
      ).transact

      _ <- A.B.i(min(2)).query.get.map(_ ==> List(Set(1, 2)))
      _ <- A.B.i(max(2)).query.get.map(_ ==> List(Set(5, 6)))
      _ <- A.B.i(min(2)).i(max(2)).query.get.map(_ ==> List((Set(1, 2), Set(5, 6))))

      _ <- A.i.a1.B.i(min(2)).query.get.map(_ ==> List(
        (1, Set(1, 2)),
        (2, Set(4, 5))
      ))

      _ <- A.i.a1.B.i(max(2)).query.get.map(_ ==> List(
        (1, Set(2, 3)),
        (2, Set(5, 6))
      ))

      _ <- A.i.a1.B.i(min(2)).i(max(2)).query.get.map(_ ==> List(
        (1, Set(1, 2), Set(2, 3)),
        (2, Set(4, 5), Set(5, 6))
      ))
    } yield ()
  }


  "2nd ref" - refs {
    for {
      _ <- A.i.B.i.C.i.insert(
        (1, 1, 1),
        (1, 1, 2),
        (1, 1, 3),
        (2, 2, 4),
        (2, 2, 5),
        (2, 2, 6),
        (2, 2, 6), // (make sure grouped values coalesce)
      ).transact

      _ <- A.B.C.i(min(2)).query.get.map(_ ==> List(Set(1, 2)))
      _ <- A.B.C.i(max(2)).query.get.map(_ ==> List(Set(5, 6)))
      _ <- A.B.C.i(min(2)).i(max(2)).query.get.map(_ ==> List((Set(1, 2), Set(5, 6))))

      _ <- A.i.a1.B.i.C.i(min(2)).query.get.map(_ ==> List(
        (1, 1, Set(1, 2)),
        (2, 2, Set(4, 5))
      ))

      _ <- A.i.a1.B.i.C.i(max(2)).query.get.map(_ ==> List(
        (1, 1, Set(2, 3)),
        (2, 2, Set(5, 6))
      ))

      _ <- A.i.a1.B.i.C.i(min(2)).i(max(2)).query.get.map(_ ==> List(
        (1, 1, Set(1, 2), Set(2, 3)),
        (2, 2, Set(4, 5), Set(5, 6))
      ))
    } yield ()
  }


  "multiple refs" - refs {
    for {
      _ <- A.i.B.i.C.i.insert(
        (1, 1, 1),
        (1, 2, 2),
        (1, 3, 3),
        (1, 4, 4),
        (2, 5, 5),
        (2, 6, 6),
        (2, 7, 7),
        (2, 8, 8),
        (2, 8, 8), // (make sure grouped values coalesce)
      ).transact

      _ <- A.i.a1.B.i(min(2)).C.i(min(2)).query.get.map(_ ==> List(
        (1, Set(1, 2), Set(1, 2)),
        (2, Set(5, 6), Set(5, 6)),
      ))
      _ <- A.i.a1.B.i(min(2)).C.i(max(2)).query.get.map(_ ==> List(
        (1, Set(1, 2), Set(3, 4)),
        (2, Set(5, 6), Set(7, 8)),
      ))
      _ <- A.i.a1.B.i(max(2)).C.i(min(2)).query.get.map(_ ==> List(
        (1, Set(3, 4), Set(1, 2)),
        (2, Set(7, 8), Set(5, 6)),
      ))
      _ <- A.i.a1.B.i(max(2)).C.i(max(2)).query.get.map(_ ==> List(
        (1, Set(3, 4), Set(3, 4)),
        (2, Set(7, 8), Set(7, 8)),
      ))
    } yield ()
  }


  "backref" - refs {
    for {
      _ <- A.i.B.i._A.C.i.insert(
        (1, 1, 1),
        (1, 2, 2),
        (1, 3, 3),
        (1, 4, 4),
        (2, 5, 5),
        (2, 6, 6),
        (2, 7, 7),
        (2, 8, 8),
        (2, 8, 8), // (make sure grouped values coalesce)
      ).transact

      _ <- A.i.a1.B.i(min(2))._A.C.i(min(2)).query.get.map(_ ==> List(
        (1, Set(1, 2), Set(1, 2)),
        (2, Set(5, 6), Set(5, 6)),
      ))
      _ <- A.i.a1.B.i(min(2))._A.C.i(max(2)).query.get.map(_ ==> List(
        (1, Set(1, 2), Set(3, 4)),
        (2, Set(5, 6), Set(7, 8)),
      ))
      _ <- A.i.a1.B.i(max(2))._A.C.i(min(2)).query.get.map(_ ==> List(
        (1, Set(3, 4), Set(1, 2)),
        (2, Set(7, 8), Set(5, 6)),
      ))
      _ <- A.i.a1.B.i(max(2))._A.C.i(max(2)).query.get.map(_ ==> List(
        (1, Set(3, 4), Set(3, 4)),
        (2, Set(7, 8), Set(7, 8)),
      ))
    } yield ()
  }
}