package molecule.coreTests.spi.aggr.one.ref

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOneRef_min_max_n extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.insert(
          (1, 1),
          (1, 2),
          (1, 3),
          (2, 4),
          (2, 5),
          (2, 6),
        ).i.transact

        _ <- A.B.i(min(2)).query.i.get.map(_ ==> List(Set(1, 2)))
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


    "2nd ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.C.i.insert(
          (1, 1, 1),
          (1, 1, 2),
          (1, 1, 3),
          (2, 2, 4),
          (2, 2, 5),
          (2, 2, 6),
        ).i.transact

        _ <- A.B.C.i(min(2)).query.i.get.map(_ ==> List(Set(1, 2)))
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


    "multiple refs" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.C.i.insert(
          (10, 1, 1),
          (10, 2, 2),
          (10, 3, 3),
          (10, 4, 4),
          (20, 5, 5),
          (20, 6, 6),
          (20, 7, 7),
          (20, 8, 8),
        ).transact

        _ <- A.i.a1.B.i(min(2)).C.i(min(2)).query.get.map(_ ==> List(
          (10, Set(1, 2), Set(1, 2)),
          (20, Set(5, 6), Set(5, 6)),
        ))
        _ <- A.i.a1.B.i(min(2)).C.i(max(2)).query.get.map(_ ==> List(
          (10, Set(1, 2), Set(3, 4)),
          (20, Set(5, 6), Set(7, 8)),
        ))
        _ <- A.i.a1.B.i(max(2)).C.i(min(2)).query.get.map(_ ==> List(
          (10, Set(3, 4), Set(1, 2)),
          (20, Set(7, 8), Set(5, 6)),
        ))
        _ <- A.i.a1.B.i(max(2)).C.i(max(2)).query.get.map(_ ==> List(
          (10, Set(3, 4), Set(3, 4)),
          (20, Set(7, 8), Set(7, 8)),
        ))
      } yield ()
    }


    "backref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i._A.C.i.insert(
          (10, 1, 1),
          (10, 2, 2),
          (10, 3, 3),
          (10, 4, 4),
          (20, 5, 5),
          (20, 6, 6),
          (20, 7, 7),
          (20, 8, 8),
        ).transact

        _ <- A.i.a1.B.i(min(2))._A.C.i(min(2)).query.get.map(_ ==> List(
          (10, Set(1, 2), Set(1, 2)),
          (20, Set(5, 6), Set(5, 6)),
        ))
        _ <- A.i.a1.B.i(min(2))._A.C.i(max(2)).query.get.map(_ ==> List(
          (10, Set(1, 2), Set(3, 4)),
          (20, Set(5, 6), Set(7, 8)),
        ))
        _ <- A.i.a1.B.i(max(2))._A.C.i(min(2)).query.get.map(_ ==> List(
          (10, Set(3, 4), Set(1, 2)),
          (20, Set(7, 8), Set(5, 6)),
        ))
        _ <- A.i.a1.B.i(max(2))._A.C.i(max(2)).query.get.map(_ ==> List(
          (10, Set(3, 4), Set(3, 4)),
          (20, Set(7, 8), Set(7, 8)),
        ))
      } yield ()
    }
  }
}