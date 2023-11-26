package molecule.coreTests.spi.aggr.set.ref

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetRef_min extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.ii.insert(List(
          (1, Set(1, 2)),
          (2, Set(2, 3)),
          (2, Set(3, 4)),
          (2, Set(3, 4)),
        )).i.transact

        // Matching values coalesced into one Set

        _ <- A.B.ii(min).query.i.get.map(_ ==> List(Set(1)))
        _ <- A.B.ii(min(1)).query.get.map(_ ==> List(Set(1)))
        _ <- A.B.ii(min(2)).query.get.map(_ ==> List(Set(1, 2)))
        _ <- A.B.ii(min(3)).query.get.map(_ ==> List(Set(1, 2, 3)))

        _ <- A.i.a1.B.ii(min).query.get.map(_ ==> List(
          (1, Set(1)),
          (2, Set(2)),
        ))
        // Same as
        _ <- A.i.a1.B.ii(min(1)).query.get.map(_ ==> List(
          (1, Set(1)),
          (2, Set(2)),
        ))

        _ <- A.i.a1.B.ii(min(2)).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 3)),
        ))

        _ <- A.i.a1.B.ii(min(3)).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 3, 4)),
        ))
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.C.ii.insert(List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2, 3)),
          (2, 2, Set(3, 4)),
          (2, 2, Set(3, 4)),
        )).i.transact

        _ <- A.B.C.ii(min).query.i.get.map(_ ==> List(Set(1)))
        _ <- A.B.C.ii(min(1)).query.get.map(_ ==> List(Set(1)))
        _ <- A.B.C.ii(min(2)).query.get.map(_ ==> List(Set(1, 2)))
        _ <- A.B.C.ii(min(3)).query.get.map(_ ==> List(Set(1, 2, 3)))

        _ <- A.i.a1.B.i.C.ii(min).query.get.map(_ ==> List(
          (1, 1, Set(1)),
          (2, 2, Set(2)),
        ))
        // Same as
        _ <- A.i.a1.B.i.C.ii(min(1)).query.get.map(_ ==> List(
          (1, 1, Set(1)),
          (2, 2, Set(2)),
        ))

        _ <- A.i.a1.B.i.C.ii(min(2)).query.get.map(_ ==> List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2, 3)),
        ))

        _ <- A.i.a1.B.i.C.ii(min(3)).query.get.map(_ ==> List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2, 3, 4)),
        ))
      } yield ()
    }


    "multiple refs" - refs { implicit conn =>
      for {
        _ <- A.i.B.ii.C.ii.insert(List(
          (1, Set(1, 2), Set(1, 2)),
          (2, Set(2, 3), Set(2, 3)),
          (2, Set(3, 4), Set(3, 4)),
          (2, Set(3, 4), Set(3, 4)),
        )).i.transact

        _ <- A.i.a1.B.ii(min).C.ii(min).query.get.map(_ ==> List(
          (1, Set(1), Set(1)),
          (2, Set(2), Set(2)),
        ))
        // Same as
        _ <- A.i.a1.B.ii(min(1)).C.ii(min(1)).query.get.map(_ ==> List(
          (1, Set(1), Set(1)),
          (2, Set(2), Set(2)),
        ))

        _ <- A.i.a1.B.ii(min(2)).C.ii(min(2)).query.get.map(_ ==> List(
          (1, Set(1, 2), Set(1, 2)),
          (2, Set(2, 3), Set(2, 3)),
        ))

        _ <- A.i.a1.B.ii(min(3)).C.ii(min(3)).query.get.map(_ ==> List(
          (1, Set(1, 2), Set(1, 2)),
          (2, Set(2, 3, 4), Set(2, 3, 4)),
        ))
      } yield ()
    }


    "backref" - refs { implicit conn =>
      for {
        _ <- A.i.B.ii._A.C.ii.insert(List(
          (1, Set(1, 2), Set(1, 2)),
          (2, Set(2, 3), Set(2, 3)),
          (2, Set(3, 4), Set(3, 4)),
          (2, Set(3, 4), Set(3, 4)),
        )).i.transact

        _ <- A.i.a1.B.ii(min)._A.C.ii(min).query.get.map(_ ==> List(
          (1, Set(1), Set(1)),
          (2, Set(2), Set(2)),
        ))
        // Same as
        _ <- A.i.a1.B.ii(min(1))._A.C.ii(min(1)).query.get.map(_ ==> List(
          (1, Set(1), Set(1)),
          (2, Set(2), Set(2)),
        ))

        _ <- A.i.a1.B.ii(min(2))._A.C.ii(min(2)).query.get.map(_ ==> List(
          (1, Set(1, 2), Set(1, 2)),
          (2, Set(2, 3), Set(2, 3)),
        ))

        _ <- A.i.a1.B.ii(min(3))._A.C.ii(min(3)).query.get.map(_ ==> List(
          (1, Set(1, 2), Set(1, 2)),
          (2, Set(2, 3, 4), Set(2, 3, 4)),
        ))
      } yield ()
    }
  }
}