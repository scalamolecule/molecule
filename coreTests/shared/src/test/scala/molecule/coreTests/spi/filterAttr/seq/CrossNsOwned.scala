package molecule.coreTests.spi.filterAttr.seq

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait CrossNsOwned extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  val a = (1, List(1, 2), List(1, 2, 3, 3), 3)
  val b = (2, List(2, 3), List(2, 3), 3)
  val c = (2, List(4), List(4), 4)

  val d = (2, List(4), List(3), 4)

  override lazy val tests = Tests {

    "equal (apply) - Lists that match other Lists" - refs { implicit conn =>
      for {
        _ <- A.i.iSeq.OwnB.iSeq.i.insert(a, b, c).transact

        _ <- A.i.iSeq_(B.iSeq_).OwnB.iSeq.query.get.map(_.sortBy(_._2.head) ==> List(
          (2, List(2, 3)), // (Lists are note coalesced as Sets are)
          (2, List(4)),
        ))
        _ <- A.i.iSeq_.OwnB.iSeq(A.iSeq_).query.get.map(_.sortBy(_._2.head) ==> List(
          (2, List(2, 3)),
          (2, List(4)),
        ))
      } yield ()
    }


    "not equal - Lists that don't match other Lists" - refs { implicit conn =>
      for {
        _ <- A.i.iSeq.OwnB.iSeq.i.insert(a, b, c).transact

        _ <- A.i.iSeq_.not(B.iSeq_).OwnB.iSeq.query.get.map(_ ==> List(
          (1, List(1, 2, 3, 3))
        ))
        _ <- A.i.iSeq.not(B.iSeq_).OwnB.iSeq_.query.get.map(_ ==> List(
          (1, List(1, 2))
        ))

        _ <- A.i.iSeq_.OwnB.iSeq.not(A.iSeq_).query.get.map(_ ==> List(
          (1, List(1, 2, 3, 3))
        ))
        _ <- A.i.iSeq.OwnB.iSeq_.not(A.iSeq_).query.get.map(_ ==> List(
          (1, List(1, 2))
        ))
      } yield ()
    }


    "has - Lists that contain all values of other List" - refs { implicit conn =>
      for {
        _ <- A.i.iSeq.OwnB.iSeq.i.insert(a, b, c).transact

        _ <- A.i.iSeq_.has(B.iSeq_).OwnB.iSeq.query.get.map(_.sortBy(_._2.head) ==> List(
          (2, List(2, 3)), // (Lists are note coalesced as Sets are)
          (2, List(4)),
        ))
        _ <- A.i.iSeq.has(B.iSeq_).OwnB.iSeq_.query.get.map(_.sortBy(_._2.head) ==> List(
          (2, List(2, 3)),
          (2, List(4)),
        ))

        _ <- A.i.a1.iSeq_.OwnB.iSeq.has(A.iSeq_).query.get.map(_.sortBy(_._2.head) ==> List(
          (1, List(1, 2, 3, 3)),
          (2, List(2, 3)),
          (2, List(4)),
        ))
        _ <- A.i.a1.iSeq.OwnB.iSeq_.has(A.iSeq_).query.get.map(_.sortBy(_._2.head) ==> List(
          (1, List(1, 2)),
          (2, List(2, 3)),
          (2, List(4)),
        ))
      } yield ()
    }

    "has - Lists that contain value of other attribute" - refs { implicit conn =>
      for {
        _ <- A.i.iSeq.OwnB.iSeq.i.insert(a, b, c).transact

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


    "hasNo - Lists that don't contain any values of other List" - refs { implicit conn =>
      for {
        _ <- A.i.iSeq.OwnB.iSeq.i.insert(a, b, c, d).transact

        _ <- A.i.iSeq_.hasNo(B.iSeq_).OwnB.iSeq.query.get.map(_ ==> List(
          (2, List(3))
        ))
        _ <- A.i.iSeq.hasNo(B.iSeq_).OwnB.iSeq_.query.get.map(_ ==> List(
          (2, List(4))
        ))

        _ <- A.i.a1.iSeq_.OwnB.iSeq.hasNo(A.iSeq_).query.get.map(_ ==> List(
          (2, List(3)),
        ))
        _ <- A.i.a1.iSeq.OwnB.iSeq_.hasNo(A.iSeq_).query.get.map(_ ==> List(
          (2, List(4))
        ))
      } yield ()
    }

    "hasNo - Lists that don't contain value of other attribute" - refs { implicit conn =>
      for {
        _ <- A.i.iSeq.OwnB.iSeq.i.insert(a, b, c, d).transact

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
