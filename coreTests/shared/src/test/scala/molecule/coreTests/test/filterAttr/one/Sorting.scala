package molecule.coreTests.test.filterAttr.one

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
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


        // Sort by Ns.i ASC, then Ns.int ASC
        // Index marker for Ns.i is after the expression:
        //       --------------
        //      |              |
        _ <- Ns.i.<(Ns.int.a2).a1.query.get.map(_ ==> List((1, 3), (1, 4), (2, 3)))
        //              |  |
        //               --
        // Index marker for Ns.int is just after the attribute


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
