package molecule.coreTests.compliance.pagination.offset

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait OffsetBackwards extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Avoid backwards pagination in sql" - types { implicit conn =>
      for {
        _ <- Ns.int.insert(1, 2, 3).transact

        // Backward pagination uses offset = totalCount-limit which on large
        // data sets become inefficient
        _ <- Ns.int.a1.query.limit(-2).get.map(_ ==> List(2, 3))

        // Get the same data by reversing the sorting. This is more efficient
        // since the offset is kept as low as possible.
        _ <- Ns.int.d1.query.limit(2).get.map(_ ==> List(3, 2))

        // Reverse output to appear traversing backwards
        _ <- Ns.int.d1.query.limit(2).get.map(_.reverse ==> List(2, 3))
      } yield ()
    }


    "Flat" - types { implicit conn =>
      for {
        // Empty results
        _ <- Ns.int.a1.query.limit(-2).get.map(_ ==> Nil)
        _ <- Ns.int.a1.query.limit(-2).offset(-2).get.map(_ ==> (Nil, 0, false))

        // Populated
        _ <- Ns.int.insert(1, 2, 3).transact

        _ <- Ns.int.a1.query.limit(-1).get.map(_ ==> List(3))
        _ <- Ns.int.a1.query.limit(-2).get.map(_ ==> List(2, 3))
        _ <- Ns.int.a1.query.limit(-3).get.map(_ ==> List(1, 2, 3))
        // limit below total count just returns all
        _ <- Ns.int.a1.query.limit(-4).get.map(_ ==> List(1, 2, 3))

        _ <- Ns.int.a1.query.offset(0).get.map(_ ==> (List(1, 2, 3), 3, true))
        _ <- Ns.int.a1.query.offset(-1).get.map(_ ==> (List(1, 2), 3, true))
        _ <- Ns.int.a1.query.offset(-2).get.map(_ ==> (List(1), 3, true))
        _ <- Ns.int.a1.query.offset(-3).get.map(_ ==> (Nil, 3, false))

        _ <- Ns.int.a1.query.limit(-2).offset(-1).get.map(_._1 ==> List(1, 2))
        _ <- Ns.int.a1.query.limit(-2).offset(-2).get.map(_._1 ==> List(1))
        _ <- Ns.int.a1.query.limit(-2).offset(-3).get.map(_._1 ==> Nil)
      } yield ()
    }


    "Nested" - types { implicit conn =>
      for {
        _ <- Ns.int.Refs.*(Ref.i).insert(
          (1, List(11, 12)),
          (2, List(21, 22)),
          (3, List(31, 32)),
        ).transact

        // From end .......................

        _ <- Ns.int.a1.Refs.*(Ref.i.a1).query.limit(-1).get.map(_ ==> List(
          (3, List(31, 32)),
        ))
        _ <- Ns.int.a1.Refs.*(Ref.i.a1).query.limit(-2).get.map(_ ==> List(
          (2, List(21, 22)),
          (3, List(31, 32)),
        ))
        _ <- Ns.int.a1.Refs.*(Ref.i.a1).query.limit(-3).get.map(_ ==> List(
          (1, List(11, 12)),
          (2, List(21, 22)),
          (3, List(31, 32)),
        ))
        _ <- Ns.int.a1.Refs.*(Ref.i.a1).query.limit(-4).get.map(_ ==> List(
          (1, List(11, 12)),
          (2, List(21, 22)),
          (3, List(31, 32)),
        ))


        _ <- Ns.int.a1.Refs.*(Ref.i.a1).query.limit(-2).offset(-1).get.map(_ ==> (
          List(
            (1, List(11, 12)),
            (2, List(21, 22)),
          ),
          3, false
        ))
        _ <- Ns.int.a1.Refs.*(Ref.i.a1).query.limit(-2).offset(-2).get.map(_ ==> (
          List(
            (1, List(11, 12)),
          ),
          3, false
        ))
        _ <- Ns.int.a1.Refs.*(Ref.i).query.limit(-2).offset(-3).get.map(_ ==> (Nil, 3, false))
      } yield ()
    }


    "Optional nested" - types { implicit conn =>
      for {
        _ <- Ns.int.Refs.*(Ref.i).insert(
          (1, List(11, 12)),
          (2, List(21, 22)),
          (3, List(31, 32)),
          (4, Nil),
        ).transact

        // From end .......................

        _ <- Ns.int.a1.Refs.*?(Ref.i).query.limit(-1).get.map(_ ==> List(
          (4, Nil),
        ))
        _ <- Ns.int.a1.Refs.*?(Ref.i.a1).query.limit(-2).get.map(_ ==> List(
          (3, List(31, 32)),
          (4, Nil),
        ))
        _ <- Ns.int.a1.Refs.*?(Ref.i.a1).query.limit(-3).get.map(_ ==> List(
          (2, List(21, 22)),
          (3, List(31, 32)),
          (4, Nil),
        ))
        _ <- Ns.int.a1.Refs.*?(Ref.i.a1).query.limit(-4).get.map(_ ==> List(
          (1, List(11, 12)),
          (2, List(21, 22)),
          (3, List(31, 32)),
          (4, Nil),
        ))
        _ <- Ns.int.a1.Refs.*?(Ref.i.a1).query.limit(-5).get.map(_ ==> List(
          (1, List(11, 12)),
          (2, List(21, 22)),
          (3, List(31, 32)),
          (4, Nil),
        ))


        _ <- Ns.int.a1.Refs.*?(Ref.i.a1).query.limit(-2).offset(-1).get.map(_ ==> (
          List(
            (2, List(21, 22)),
            (3, List(31, 32)),
          ),
          4, true
        ))
        _ <- Ns.int.a1.Refs.*?(Ref.i.a1).query.limit(-2).offset(-2).get.map(_ ==> (
          List(
            (1, List(11, 12)),
            (2, List(21, 22)),
          ),
          4, false
        ))
        _ <- Ns.int.a1.Refs.*?(Ref.i.a1).query.limit(-2).offset(-3).get.map(_ ==> (
          List(
            (1, List(11, 12)),
          ),
          4, false
        ))
        _ <- Ns.int.a1.Refs.*?(Ref.i).query.limit(-2).offset(-4).get.map(_ ==> (Nil, 4, false))
      } yield ()
    }
  }
}
