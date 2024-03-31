package molecule.coreTests.spi.filter.seq.ref

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterRefSeq_Card2RefOwned extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "mandatory" - refs { implicit conn =>
      for {
        _ <- A.i.OwnBb.iSeq.insert(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
          (4, List())
        ).transact

        // has

        _ <- A.i.a1.OwnBb.iSeq.has(1).query.get.map(_ ==> List(
          (1, List(1, 2, 2)),
        ))
        _ <- A.i.a1.OwnBb.iSeq.has(2).query.get.map(_ ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
        ))

        _ <- A.i.a1.OwnBb.iSeq.has(2, 1).query.get.map(_ ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
        ))
        _ <- A.i.a1.OwnBb.iSeq.has(2, 7).query.get.map(_ ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
        ))
        _ <- A.i.a1.OwnBb.iSeq.has(2, 3).query.get.map(_ ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (3, List(3)),
        ))


        // hasNo

        _ <- A.i.a1.OwnBb.iSeq.hasNo(1).query.get.map(_ ==> List(
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
        ))
        _ <- A.i.a1.OwnBb.iSeq.hasNo(2).query.get.map(_ ==> List(
          (2, List(7)),
          (3, List(3)),
        ))
        _ <- A.i.a1.OwnBb.iSeq.hasNo(3).query.get.map(_ ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
        ))
      } yield ()
    }


    "tacit" - refs { implicit conn =>
      for {
        _ <- A.i.OwnBb.iSeq.insert(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
          (4, List())
        ).transact

        // has
        _ <- A.i.a1.OwnBb.iSeq_.has(1).query.get.map(_ ==> List(1))
        _ <- A.i.a1.OwnBb.iSeq_.has(2).query.get.map(_ ==> List(1, 2))
        _ <- A.i.a1.OwnBb.iSeq_.has(2, 7).query.get.map(_ ==> List(1, 2, 2))
        _ <- A.i.a1.OwnBb.iSeq_.has(2, 3).query.get.map(_ ==> List(1, 2, 3))

        // hasNo
        _ <- A.i.a1.OwnBb.iSeq_.hasNo(1).query.get.map(_ ==> List(2, 2, 3))
        _ <- A.i.a1.OwnBb.iSeq_.hasNo(2).query.get.map(_ ==> List(2, 3))
        _ <- A.i.a1.OwnBb.iSeq_.hasNo(3).query.get.map(_ ==> List(1, 2, 2))

        // no value - match non-asserted attribute (null)
        // Nothing returned since there's no relationship to B
        _ <- A.i.a1.OwnBb.iSeq_().query.get.map(_ ==> Nil)
      } yield ()
    }

    // No filtering on optional Seq attributes
  }
}
