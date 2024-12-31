package molecule.coreTests.spi.pagination.offset

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class OffsetBackwards(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Avoid backwards pagination in sql" - types { implicit conn =>
    for {
      _ <- Entity.int.insert(1, 2, 3).transact

      // Backward pagination uses offset = totalCount-limit which on large
      // data sets become inefficient
      _ <- Entity.int.a1.query.limit(-2).get.map(_ ==> List(2, 3))

      // Get the same data by reversing the sorting. This is more efficient
      // since the offset is kept as low as possible.
      _ <- Entity.int.d1.query.limit(2).get.map(_ ==> List(3, 2))

      // Reverse output to appear traversing backwards
      _ <- Entity.int.d1.query.limit(2).get.map(_.reverse ==> List(2, 3))
    } yield ()
  }


  "Flat" - types { implicit conn =>
    for {
      // Empty results
      _ <- Entity.int.a1.query.limit(-2).get.map(_ ==> Nil)
      _ <- Entity.int.a1.query.limit(-2).offset(-2).get.map(_ ==> (Nil, 0, false))

      // Populated
      _ <- Entity.int.insert(1, 2, 3).transact

      _ <- Entity.int.a1.query.limit(-1).get.map(_ ==> List(3))
      _ <- Entity.int.a1.query.limit(-2).get.map(_ ==> List(2, 3))
      _ <- Entity.int.a1.query.limit(-3).get.map(_ ==> List(1, 2, 3))
      // limit below total count just returns all
      _ <- Entity.int.a1.query.limit(-4).get.map(_ ==> List(1, 2, 3))

      // When only offset is set, there will be no further rows going backwards
      _ <- Entity.int.a1.query.offset(0).get.map(_ ==> (List(1, 2, 3), 3, false))
      _ <- Entity.int.a1.query.offset(-1).get.map(_ ==> (List(1, 2), 3, false))
      _ <- Entity.int.a1.query.offset(-2).get.map(_ ==> (List(1), 3, false))
      _ <- Entity.int.a1.query.offset(-3).get.map(_ ==> (Nil, 3, false))

      _ <- Entity.int.a1.query.limit(-2).offset(0).get.map(_ ==> (List(2, 3), 3, true)) // one page before with 1
      _ <- Entity.int.a1.query.limit(-2).offset(-1).get.map(_ ==> (List(1, 2), 3, false))
      _ <- Entity.int.a1.query.limit(-2).offset(-2).get.map(_ ==> (List(1), 3, false))
      _ <- Entity.int.a1.query.limit(-2).offset(-3).get.map(_ ==> (List(), 3, false))
    } yield ()
  }


  "Nested" - types { implicit conn =>
    for {
      _ <- Entity.int.Refs.*(Ref.i).insert(
        (1, List(11, 12)),
        (2, List(21, 22)),
        (3, List(31, 32)),
      ).transact

      // From end .......................

      _ <- Entity.int.a1.Refs.*(Ref.i.a1).query.limit(-1).get.map(_ ==> List(
        (3, List(31, 32)),
      ))
      _ <- Entity.int.a1.Refs.*(Ref.i.a1).query.limit(-2).get.map(_ ==> List(
        (2, List(21, 22)),
        (3, List(31, 32)),
      ))
      _ <- Entity.int.a1.Refs.*(Ref.i.a1).query.limit(-3).get.map(_ ==> List(
        (1, List(11, 12)),
        (2, List(21, 22)),
        (3, List(31, 32)),
      ))
      _ <- Entity.int.a1.Refs.*(Ref.i.a1).query.limit(-4).get.map(_ ==> List(
        (1, List(11, 12)),
        (2, List(21, 22)),
        (3, List(31, 32)),
      ))


      _ <- Entity.int.a1.Refs.*(Ref.i.a1).query.limit(-2).offset(-1).get.map(_ ==> (
        List(
          (1, List(11, 12)),
          (2, List(21, 22)),
        ),
        3, false
      ))
      _ <- Entity.int.a1.Refs.*(Ref.i.a1).query.limit(-2).offset(-2).get.map(_ ==> (
        List(
          (1, List(11, 12)),
        ),
        3, false
      ))
      _ <- Entity.int.a1.Refs.*(Ref.i).query.limit(-2).offset(-3).get.map(_ ==> (Nil, 3, false))
    } yield ()
  }


  "Optional nested" - types { implicit conn =>
    for {
      _ <- Entity.int.Refs.*(Ref.i).insert(
        (1, List(11, 12)),
        (2, List(21, 22)),
        (3, List(31, 32)),
        (4, Nil),
      ).transact

      // From end .......................

      _ <- Entity.int.a1.Refs.*?(Ref.i).query.limit(-1).get.map(_ ==> List(
        (4, Nil),
      ))
      _ <- Entity.int.a1.Refs.*?(Ref.i.a1).query.limit(-2).get.map(_ ==> List(
        (3, List(31, 32)),
        (4, Nil),
      ))
      _ <- Entity.int.a1.Refs.*?(Ref.i.a1).query.limit(-3).get.map(_ ==> List(
        (2, List(21, 22)),
        (3, List(31, 32)),
        (4, Nil),
      ))
      _ <- Entity.int.a1.Refs.*?(Ref.i.a1).query.limit(-4).get.map(_ ==> List(
        (1, List(11, 12)),
        (2, List(21, 22)),
        (3, List(31, 32)),
        (4, Nil),
      ))
      _ <- Entity.int.a1.Refs.*?(Ref.i.a1).query.limit(-5).get.map(_ ==> List(
        (1, List(11, 12)),
        (2, List(21, 22)),
        (3, List(31, 32)),
        (4, Nil),
      ))


      _ <- Entity.int.a1.Refs.*?(Ref.i.a1).query.limit(-2).offset(-1).get.map(_ ==> (
        List(
          (2, List(21, 22)),
          (3, List(31, 32)),
        ),
        4, true
      ))
      _ <- Entity.int.a1.Refs.*?(Ref.i.a1).query.limit(-2).offset(-2).get.map(_ ==> (
        List(
          (1, List(11, 12)),
          (2, List(21, 22)),
        ),
        4, false
      ))
      _ <- Entity.int.a1.Refs.*?(Ref.i.a1).query.limit(-2).offset(-3).get.map(_ ==> (
        List(
          (1, List(11, 12)),
        ),
        4, false
      ))
      _ <- Entity.int.a1.Refs.*?(Ref.i).query.limit(-2).offset(-4).get.map(_ ==> (Nil, 4, false))
    } yield ()
  }
}
