package molecule.coreTests.spi.aggr.set.ref

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetRef_count extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.ii.insert(List(
          (1, Set(1, 2)),
          (2, Set(2, 3)),
          (2, Set(3, 4)),
          (2, Set(3, 4)),
        )).transact

        // Matching values coalesced into one Set

        _ <- A.B.ii(count).query.get.map(_ ==> List(8))
        _ <- A.B.ii(countDistinct).query.get.map(_ ==> List(4))

        _ <- A.i.a1.B.ii(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 6)
        ))
        _ <- A.i.a1.B.ii(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
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
        )).transact

        _ <- A.B.C.ii(count).query.get.map(_ ==> List(8))
        _ <- A.B.C.ii(countDistinct).query.get.map(_ ==> List(4))

        _ <- A.i.a1.B.i.C.ii(count).query.get.map(_ ==> List(
          (1, 1, 2),
          (2, 2, 6)
        ))
        _ <- A.i.a1.B.i.C.ii(countDistinct).query.get.map(_ ==> List(
          (1, 1, 2),
          (2, 2, 3)
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
        )).transact

        _ <- A.i.a1.B.ii(count).C.ii(count).query.get.map(_ ==> List(
          (1, 2, 2),
          (2, 6, 6)
        ))
        _ <- A.i.a1.B.ii(countDistinct).C.ii(countDistinct).query.get.map(_ ==> List(
          (1, 2, 2),
          (2, 3, 3)
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
        )).transact

        _ <- A.i.a1.B.ii(count)._A.C.ii(count).query.get.map(_ ==> List(
          (1, 2, 2),
          (2, 6, 6)
        ))
        _ <- A.i.a1.B.ii(countDistinct)._A.C.ii(countDistinct).query.get.map(_ ==> List(
          (1, 2, 2),
          (2, 3, 3)
        ))
      } yield ()
    }
  }
}