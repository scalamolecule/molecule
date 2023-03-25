package molecule.datomic.test.pagination.offset

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._

object OffsetBackwards extends DatomicTestSuite {

  override lazy val tests = Tests {

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

        _ <- Ns.int.a1.Refs.*(Ref.i).query.limit(-1).get.map(_ ==> List(
          (3, List(31, 32)),
        ))
        _ <- Ns.int.a1.Refs.*(Ref.i).query.limit(-2).get.map(_ ==> List(
          (2, List(21, 22)),
          (3, List(31, 32)),
        ))
        _ <- Ns.int.a1.Refs.*(Ref.i).query.limit(-3).get.map(_ ==> List(
          (1, List(11, 12)),
          (2, List(21, 22)),
          (3, List(31, 32)),
        ))
        _ <- Ns.int.a1.Refs.*(Ref.i).query.limit(-4).get.map(_ ==> List(
          (1, List(11, 12)),
          (2, List(21, 22)),
          (3, List(31, 32)),
        ))


        _ <- Ns.int.a1.Refs.*(Ref.i).query.limit(-2).offset(-1).get.map(_ ==> (
          List(
            (1, List(11, 12)),
            (2, List(21, 22)),
          ),
          3, false
        ))
        _ <- Ns.int.a1.Refs.*(Ref.i).query.limit(-2).offset(-2).get.map(_ ==> (
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
        _ <- Ns.int.a1.Refs.*?(Ref.i).query.limit(-2).get.map(_ ==> List(
          (3, List(31, 32)),
          (4, Nil),
        ))
        _ <- Ns.int.a1.Refs.*?(Ref.i).query.limit(-3).get.map(_ ==> List(
          (2, List(21, 22)),
          (3, List(31, 32)),
          (4, Nil),
        ))
        _ <- Ns.int.a1.Refs.*?(Ref.i).query.limit(-4).get.map(_ ==> List(
          (1, List(11, 12)),
          (2, List(21, 22)),
          (3, List(31, 32)),
          (4, Nil),
        ))
        _ <- Ns.int.a1.Refs.*?(Ref.i).query.limit(-5).get.map(_ ==> List(
          (1, List(11, 12)),
          (2, List(21, 22)),
          (3, List(31, 32)),
          (4, Nil),
        ))


        _ <- Ns.int.a1.Refs.*?(Ref.i).query.limit(-2).offset(-1).get.map(_ ==> (
          List(
            (2, List(21, 22)),
            (3, List(31, 32)),
          ),
          4, true
        ))
        _ <- Ns.int.a1.Refs.*?(Ref.i).query.limit(-2).offset(-2).get.map(_ ==> (
          List(
            (1, List(11, 12)),
            (2, List(21, 22)),
          ),
          4, false
        ))
        _ <- Ns.int.a1.Refs.*?(Ref.i).query.limit(-2).offset(-3).get.map(_ ==> (
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
