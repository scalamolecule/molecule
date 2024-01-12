package molecule.coreTests.spi.filterAttr.one

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Sorting extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Adjacent" - types { implicit conn =>
      for {
        _ <- Ns.i.int.insert(
          (2, 3),
          (1, 4),
          (1, 3),
          (7, 3)
        ).transact

        // Sort by Ns.i ASC, then Ns.int ASC
        // Sort marker for Ns.i is still primary even though it "comes after"
        // the expression having the secondary sort marker
        //       --------------
        //      |              |
        _ <- Ns.i.<(Ns.int.a2).a1.query.get.map(_ ==> List((1, 3), (1, 4), (2, 3)))
        //              |  |
        //               --
        // Secondary sort marker for Ns.int (even though it "comes before" the primary sort marker)

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


    "CrossNs" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.insert(
          (2, 3),
          (1, 4),
          (1, 3),
          (7, 3)
        ).transact

        // Since sort markers can't attach to tacit filter attributes there's no sorting ambiguity

        _ <- A.i.<(B.i_).a1.B.i.a2.query.get.map(_ ==> List((1, 3), (1, 4), (2, 3)))
        _ <- A.i.<(B.i_).a1.B.i.d2.query.get.map(_ ==> List((1, 4), (1, 3), (2, 3)))
        _ <- A.i.<(B.i_).d1.B.i.a2.query.get.map(_ ==> List((2, 3), (1, 3), (1, 4)))
        _ <- A.i.<(B.i_).d1.B.i.d2.query.get.map(_ ==> List((2, 3), (1, 4), (1, 3)))

        _ <- A.i.<(B.i_).a2.B.i.a1.query.get.map(_ ==> List((1, 3), (2, 3), (1, 4)))
        _ <- A.i.<(B.i_).a2.B.i.d1.query.get.map(_ ==> List((1, 4), (1, 3), (2, 3)))
        _ <- A.i.<(B.i_).d2.B.i.a1.query.get.map(_ ==> List((2, 3), (1, 3), (1, 4)))
        _ <- A.i.<(B.i_).d2.B.i.d1.query.get.map(_ ==> List((1, 4), (2, 3), (1, 3)))

        // Same as

        _ <- A.i.a1.B.i.>(A.i_).a2.query.get.map(_ ==> List((1, 3), (1, 4), (2, 3)))
        _ <- A.i.a1.B.i.>(A.i_).d2.query.get.map(_ ==> List((1, 4), (1, 3), (2, 3)))
        _ <- A.i.d1.B.i.>(A.i_).a2.query.get.map(_ ==> List((2, 3), (1, 3), (1, 4)))
        _ <- A.i.d1.B.i.>(A.i_).d2.query.get.map(_ ==> List((2, 3), (1, 4), (1, 3)))

        _ <- A.i.a2.B.i.>(A.i_).a1.query.get.map(_ ==> List((1, 3), (2, 3), (1, 4)))
        _ <- A.i.a2.B.i.>(A.i_).d1.query.get.map(_ ==> List((1, 4), (1, 3), (2, 3)))
        _ <- A.i.d2.B.i.>(A.i_).a1.query.get.map(_ ==> List((2, 3), (1, 3), (1, 4)))
        _ <- A.i.d2.B.i.>(A.i_).d1.query.get.map(_ ==> List((1, 4), (2, 3), (1, 3)))
      } yield ()
    }


    "CrossNs owned" - refs { implicit conn =>
      for {
        _ <- A.i.OwnB.i.insert(
          (2, 3),
          (1, 4),
          (1, 3),
          (7, 3)
        ).transact

        _ <- A.i.<(B.i_).a1.OwnB.i.a2.query.get.map(_ ==> List((1, 3), (1, 4), (2, 3)))
        _ <- A.i.<(B.i_).a1.OwnB.i.d2.query.get.map(_ ==> List((1, 4), (1, 3), (2, 3)))
        _ <- A.i.<(B.i_).d1.OwnB.i.a2.query.get.map(_ ==> List((2, 3), (1, 3), (1, 4)))
        _ <- A.i.<(B.i_).d1.OwnB.i.d2.query.get.map(_ ==> List((2, 3), (1, 4), (1, 3)))

        _ <- A.i.<(B.i_).a2.OwnB.i.a1.query.get.map(_ ==> List((1, 3), (2, 3), (1, 4)))
        _ <- A.i.<(B.i_).a2.OwnB.i.d1.query.get.map(_ ==> List((1, 4), (1, 3), (2, 3)))
        _ <- A.i.<(B.i_).d2.OwnB.i.a1.query.get.map(_ ==> List((2, 3), (1, 3), (1, 4)))
        _ <- A.i.<(B.i_).d2.OwnB.i.d1.query.get.map(_ ==> List((1, 4), (2, 3), (1, 3)))

        // Same as

        _ <- A.i.a1.OwnB.i.>(A.i_).a2.query.get.map(_ ==> List((1, 3), (1, 4), (2, 3)))
        _ <- A.i.a1.OwnB.i.>(A.i_).d2.query.get.map(_ ==> List((1, 4), (1, 3), (2, 3)))
        _ <- A.i.d1.OwnB.i.>(A.i_).a2.query.get.map(_ ==> List((2, 3), (1, 3), (1, 4)))
        _ <- A.i.d1.OwnB.i.>(A.i_).d2.query.get.map(_ ==> List((2, 3), (1, 4), (1, 3)))

        _ <- A.i.a2.OwnB.i.>(A.i_).a1.query.get.map(_ ==> List((1, 3), (2, 3), (1, 4)))
        _ <- A.i.a2.OwnB.i.>(A.i_).d1.query.get.map(_ ==> List((1, 4), (1, 3), (2, 3)))
        _ <- A.i.d2.OwnB.i.>(A.i_).a1.query.get.map(_ ==> List((2, 3), (1, 3), (1, 4)))
        _ <- A.i.d2.OwnB.i.>(A.i_).d1.query.get.map(_ ==> List((1, 4), (2, 3), (1, 3)))
      } yield ()
    }
  }
}
