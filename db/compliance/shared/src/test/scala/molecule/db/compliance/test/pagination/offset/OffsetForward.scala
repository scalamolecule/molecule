package molecule.db.compliance.test.pagination.offset

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class OffsetForward(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Flat" - types {
    for {
      // Empty results
      _ <- Entity.int.a1.query.limit(2).get.map(_ ==> Nil)

      _ <- Entity.int.a1.query.offset(2).get.map(_ ==> (Nil, 0, false))
      _ <- Entity.int.a1.query.limit(2).offset(2).get.map(_ ==> (Nil, 0, false))

      // Populated
      _ <- Entity.int.insert(1, 2, 3).transact

      _ <- Entity.int.a1.query.limit(0).get.map(_ ==> Nil)
      _ <- Entity.int.a1.query.limit(1).get.map(_ ==> List(1))
      _ <- Entity.int.a1.query.limit(2).get.map(_ ==> List(1, 2))
      _ <- Entity.int.a1.query.limit(3).get.map(_ ==> List(1, 2, 3))
      // limit beyond total count just returns all
      _ <- Entity.int.a1.query.limit(4).get.map(_ ==> List(1, 2, 3))

      // When only offset is set, there will be no further rows going forward
      _ <- Entity.int.a1.query.offset(0).get.map(_ ==> (List(1, 2, 3), 3, false))
      _ <- Entity.int.a1.query.offset(1).get.map(_ ==> (List(2, 3), 3, false))
      _ <- Entity.int.a1.query.offset(2).get.map(_ ==> (List(3), 3, false))
      _ <- Entity.int.a1.query.offset(3).get.map(_ ==> (Nil, 3, false))

      _ <- Entity.int.a1.query.limit(2).get.map(_ ==> List(1, 2))
      _ <- Entity.int.a1.query.limit(2).offset(0).get.map(_ ==> (List(1, 2), 3, true)) // one page ahead with 3
      _ <- Entity.int.a1.query.limit(2).offset(1).get.map(_ ==> (List(2, 3), 3, false))
      _ <- Entity.int.a1.query.limit(2).offset(2).get.map(_ ==> (List(3), 3, false))
      _ <- Entity.int.a1.query.limit(2).offset(3).get.map(_ ==> (Nil, 3, false))
    } yield ()
  }


  "Nested" - types {
    for {
      // Empty results
      _ <- Entity.int.a1.Refs.*(Ref.i).query.limit(2).get.map(_ ==> Nil)
      _ <- Entity.int.a1.Refs.*(Ref.i).query.limit(2).offset(2).get.map(_ ==> (Nil, 0, false))

      _ <- Entity.int.Refs.*(Ref.i).insert(
        (1, List(11, 12)),
        (2, List(21, 22)),
        (3, List(31, 32)),
      ).transact

      _ <- Entity.int.a1.Refs.*(Ref.i.a1).query.limit(1).get.map(_ ==> List(
        (1, List(11, 12)),
      ))
      _ <- Entity.int.a1.Refs.*(Ref.i.a1).query.limit(2).get.map(_ ==> List(
        (1, List(11, 12)),
        (2, List(21, 22)),
      ))
      _ <- Entity.int.a1.Refs.*(Ref.i.a1).query.limit(3).get.map(_ ==> List(
        (1, List(11, 12)),
        (2, List(21, 22)),
        (3, List(31, 32)),
      ))
      _ <- Entity.int.a1.Refs.*(Ref.i.a1).query.limit(4).get.map(_ ==> List(
        (1, List(11, 12)),
        (2, List(21, 22)),
        (3, List(31, 32)),
      ))


      _ <- Entity.int.a1.Refs.*(Ref.i.a1).query.limit(2).offset(1).get.map(_ ==> (
        List(
          (2, List(21, 22)),
          (3, List(31, 32)),
        ),
        3, false
      ))
      _ <- Entity.int.a1.Refs.*(Ref.i.a1).query.limit(2).offset(2).get.map(_ ==> (
        List(
          (3, List(31, 32)),
        ),
        3, false
      ))
      _ <- Entity.int.a1.Refs.*(Ref.i).query.limit(2).offset(3).get.map(_ ==> (Nil, 3, false))
    } yield ()
  }


  "Optional nested" - types {
    for {
      _ <- Entity.int.Refs.*(Ref.i).insert(
        (1, List(11, 12)),
        (2, List(21, 22)),
        (3, List(31, 32)),
        (4, Nil),
      ).transact

      _ <- Entity.int.a1.Refs.*?(Ref.i.a1).query.limit(1).get.map(_ ==> List(
        (1, List(11, 12)),
      ))
      _ <- Entity.int.a1.Refs.*?(Ref.i.a1).query.limit(2).get.map(_ ==> List(
        (1, List(11, 12)),
        (2, List(21, 22)),
      ))
      _ <- Entity.int.a1.Refs.*?(Ref.i.a1).query.limit(3).get.map(_ ==> List(
        (1, List(11, 12)),
        (2, List(21, 22)),
        (3, List(31, 32)),
      ))
      _ <- Entity.int.a1.Refs.*?(Ref.i.a1).query.limit(4).get.map(_ ==> List(
        (1, List(11, 12)),
        (2, List(21, 22)),
        (3, List(31, 32)),
        (4, Nil),
      ))
      _ <- Entity.int.a1.Refs.*?(Ref.i.a1).query.limit(5).get.map(_ ==> List(
        (1, List(11, 12)),
        (2, List(21, 22)),
        (3, List(31, 32)),
        (4, Nil),
      ))


      _ <- Entity.int.a1.Refs.*?(Ref.i.a1).query.limit(2).offset(1).get.map(_ ==> (
        List(
          (2, List(21, 22)),
          (3, List(31, 32)),
        ),
        4, true
      ))
      _ <- Entity.int.a1.Refs.*?(Ref.i.a1).query.limit(2).offset(2).get.map(_ ==> (
        List(
          (3, List(31, 32)),
          (4, Nil),
        ),
        4, false
      ))
      _ <- Entity.int.a1.Refs.*?(Ref.i.a1).query.limit(2).offset(3).get.map(_ ==> (
        List(
          (4, Nil),
        ),
        4, false
      ))
      _ <- Entity.int.a1.Refs.*?(Ref.i.a1).query.limit(2).offset(4).get.map(_ ==> (Nil, 4, false))
    } yield ()
  }
}
