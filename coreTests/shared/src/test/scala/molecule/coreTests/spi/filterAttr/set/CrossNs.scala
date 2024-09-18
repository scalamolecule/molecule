package molecule.coreTests.spi.filterAttr.set

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait CrossNs extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "has" - refs { implicit conn =>
      for {
        _ <- A.i.iSet.B.iSet.i.insert(
          (1, Set(1, 2), Set(1, 2, 3), 3),
          (2, Set(2, 3), Set(2, 3), 3),
          (2, Set(4), Set(4), 4),
        ).transact

        _ <- A.i.iSet_.has(B.i_).B.iSet.i.a1.query.get.map(_ ==> List(
          (2, Set(2, 3), 3),
          (2, Set(4), 4),
        ))
        _ <- A.i.iSet.has(B.i_).B.iSet_.i.a1.query.get.map(_ ==> List(
          (2, Set(2, 3), 3),
          (2, Set(4), 4),
        ))

        _ <- A.i.a1.iSet_.B.iSet.has(A.i_).query.get.map(_ ==> List(
          (1, Set(1, 2, 3)),
          (2, Set(2, 3)),
        ))
        _ <- A.i.a1.iSet.B.iSet_.has(A.i_).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 3)),
        ))
      } yield ()
    }


    "hasNo" - refs { implicit conn =>
      for {
        _ <- A.i.iSet.B.iSet.i.insert(
          (1, Set(1, 2), Set(1, 2, 3), 3),
          (2, Set(2, 3), Set(2, 3), 3),
          (2, Set(4), Set(4), 4),
          (2, Set(4), Set(3), 4),
        ).transact

        _ <- A.i.iSet_.hasNo(B.i_).B.iSet.i.a1.query.get.map(_ ==> List(
          (1, Set(1, 2, 3), 3),
        ))
        _ <- A.i.iSet.hasNo(B.i_).B.iSet_.i.a1.query.get.map(_ ==> List(
          (1, Set(1, 2), 3),
        ))

        _ <- A.i.a1.iSet_.B.iSet.hasNo(A.i_).query.get.map(_ ==> List(
          (2, Set(3, 4)),
        ))
        _ <- A.i.a1.iSet.B.iSet_.hasNo(A.i_).query.get.map(_ ==> List(
          (2, Set(4)),
        ))
      } yield ()
    }
  }
}
