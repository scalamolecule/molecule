package molecule.coreTests.spi.aggr.set.ref

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetRef_count extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "1st ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.iSet.insert(List(
          (1, Set(1, 2)),
          (2, Set(2)),
          (2, Set(3, 4)),
          (2, Set(3, 4)),
        )).transact

        // Matching values coalesced into one Set

        _ <- A.B.iSet(count).query.get.map(_ ==> List(7))
        _ <- A.B.iSet(countDistinct).query.get.map(_ ==> List(4))

        _ <- A.i.a1.B.iSet(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 5)
        ))
        _ <- A.i.a1.B.iSet(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.C.iSet.insert(List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2)),
          (2, 2, Set(3, 4)),
          (2, 2, Set(3, 4)),
        )).transact

        _ <- A.B.C.iSet(count).query.get.map(_ ==> List(7))
        _ <- A.B.C.iSet(countDistinct).query.get.map(_ ==> List(4))

        _ <- A.i.a1.B.i.C.iSet(count).query.get.map(_ ==> List(
          (1, 1, 2),
          (2, 2, 5)
        ))
        _ <- A.i.a1.B.i.C.iSet(countDistinct).query.get.map(_ ==> List(
          (1, 1, 2),
          (2, 2, 3)
        ))
      } yield ()
    }


    "backref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i._A.C.iSet.insert(List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2)),
          (2, 2, Set(3, 4)),
          (2, 2, Set(3, 4)),
        )).transact

        _ <- A.i.a1.B.i._A.C.iSet(count).query.get.map(_ ==> List(
          (1, 1, 2),
          (2, 2, 5)
        ))
        _ <- A.i.a1.B.i._A.C.iSet(countDistinct).query.get.map(_ ==> List(
          (1, 1, 2),
          (2, 2, 3)
        ))
      } yield ()
    }
  }
}