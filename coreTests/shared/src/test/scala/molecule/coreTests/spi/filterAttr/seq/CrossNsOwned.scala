package molecule.coreTests.spi.filterAttr.seq

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait CrossNsOwned extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  val a = (1, List(1, 2), List(1, 2, 3, 3), 3)
  val b = (2, List(2, 3), List(2, 3), 3)
  val c = (2, List(4), List(4), 4)
  val d = (2, List(4), List(3), 4)

  override lazy val tests = Tests {

    "has" - refs { implicit conn =>
      for {
        _ <- A.i.iSeq.OwnB.iSeq.i.insert(
          (1, List(1, 2), List(1, 2, 3, 3), 3),
          (2, List(2, 3), List(2, 3), 3),
          (2, List(4), List(4), 4),
        ).transact

        _ <- A.i.iSeq_.has(B.i_).OwnB.iSeq.i.a1.query.get.map(_.sortBy(_._2.head) ==> List(
          (2, List(2, 3), 3),
          (2, List(4), 4),
        ))
        _ <- A.i.iSeq.has(B.i_).OwnB.iSeq_.i.a1.query.get.map(_.sortBy(_._2.head) ==> List(
          (2, List(2, 3), 3),
          (2, List(4), 4),
        ))

        _ <- A.i.a1.iSeq_.OwnB.iSeq.has(A.i_).query.get.map(_ ==> List(
          (1, List(1, 2, 3, 3)),
          (2, List(2, 3)),
        ))
        _ <- A.i.a1.iSeq.OwnB.iSeq_.has(A.i_).query.get.map(_ ==> List(
          (1, List(1, 2)),
          (2, List(2, 3)),
        ))
      } yield ()
    }


    "hasNo" - refs { implicit conn =>
      for {
        _ <- A.i.iSeq.OwnB.iSeq.i.insert(
          (1, List(1, 2), List(1, 2, 3, 3), 3),
          (2, List(2, 3), List(2, 3), 3),
          (2, List(4), List(4), 4),
          (2, List(4), List(3), 4),
        ).transact

        _ <- A.i.iSeq_.hasNo(B.i_).OwnB.iSeq.i.a1.query.get.map(_ ==> List(
          (1, List(1, 2, 3, 3), 3),
        ))
        _ <- A.i.iSeq.hasNo(B.i_).OwnB.iSeq_.i.a1.query.get.map(_ ==> List(
          (1, List(1, 2), 3),
        ))

        _ <- A.i.iSeq_.OwnB.iSeq.hasNo(A.i_).query.get.map(_.sortBy(_._2.head) ==> List(
          (2, List(3)),
          (2, List(4)),
        ))
        _ <- A.i.iSeq.OwnB.iSeq_.hasNo(A.i_).query.get.map(_ ==> List(
          (2, List(4)),
        ))
      } yield ()
    }
  }
}
