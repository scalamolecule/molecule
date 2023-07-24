package molecule.coreTests.test.filterAttr.one

import molecule.coreTests.api.ApiAsyncImplicits
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import molecule.core.spi.SpiAsync
import utest._

trait Sorting extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>


  override lazy val tests = Tests {

    "Sorting" - types { implicit conn =>
      for {
        _ <- Ns.i.int.insert(
          (2, 3),
          (1, 4),
          (1, 3),
          (7, 3)
        ).transact

        // Sort by attribute Ns.i (index marker has to be after expression)
        _ <- Ns.i.<(Ns.int).a1.query.get.map(_ ==> List(
          (1, 3),
          (1, 4),
          (2, 3),
        ))

        // Sort by expression attribute Ns.int
        _ <- Ns.i.<(Ns.int.a1).query.get.map(_ ==> List(
          (2, 3),
          (1, 3),
          (1, 4),
        ))

        // Ns.i primary
        _ <- Ns.i.<(Ns.int.a2).a1.query.get.map(_ ==> List(
          (1, 3),
          (1, 4),
          (2, 3),
        ))
        _ <- Ns.i.<(Ns.int.d2).a1.query.get.map(_ ==> List(
          (1, 4),
          (1, 3),
          (2, 3),
        ))
        _ <- Ns.i.<(Ns.int.a2).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 3),
          (1, 4),
        ))
        _ <- Ns.i.<(Ns.int.d2).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 4),
          (1, 3),
        ))

        // Ns.i secondary
        _ <- Ns.i.<(Ns.int.a1).a2.query.get.map(_ ==> List(
          (1, 3),
          (2, 3),
          (1, 4),
        ))
        _ <- Ns.i.<(Ns.int.d1).a2.query.get.map(_ ==> List(
          (1, 4),
          (1, 3),
          (2, 3),
        ))
        _ <- Ns.i.<(Ns.int.a1).d2.query.get.map(_ ==> List(
          (2, 3),
          (1, 3),
          (1, 4),
        ))
        _ <- Ns.i.<(Ns.int.d1).d2.query.get.map(_ ==> List(
          (1, 4),
          (2, 3),
          (1, 3),
        ))
      } yield ()
    }
  }
}