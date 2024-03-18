package molecule.coreTests.spi.aggr.seq.ref

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeqRef_count extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "1st ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.iSeq.insert(List(
          (1, List(1, 2)),
          (2, List(2)),
          (2, List(3, 4)),
          (2, List(3, 4)),
        )).transact

        // Matching values coalesced into one List

        _ <- A.B.iSeq(count).query.get.map(_ ==> List(7))
        _ <- A.B.iSeq(countDistinct).query.get.map(_ ==> List(4))

        _ <- A.i.B.iSeq(count).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 5),
        ))
        _ <- A.i.B.iSeq(count).d1.query.get.map(_ ==> List(
          (2, 5),
          (1, 2),
        ))

        _ <- A.i.B.iSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- A.i.B.iSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.C.iSeq.insert(List(
          (1, 1, List(1, 2)),
          (2, 2, List(2)),
          (2, 2, List(3, 4)),
          (2, 2, List(3, 4)),
        )).transact

        _ <- A.B.C.iSeq(count).query.get.map(_ ==> List(7))
        _ <- A.B.C.iSeq(countDistinct).query.get.map(_ ==> List(4))

        _ <- A.i.B.i.C.iSeq(count).a1.query.get.map(_ ==> List(
          (1, 1, 2),
          (2, 2, 5),
        ))
        _ <- A.i.B.i.C.iSeq(count).d1.query.get.map(_ ==> List(
          (2, 2, 5),
          (1, 1, 2),
        ))

        _ <- A.i.B.i.C.iSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 1, 2),
          (2, 2, 3),
        ))
        _ <- A.i.B.i.C.iSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 2, 3),
          (1, 1, 2),
        ))
      } yield ()
    }


    "backref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i._A.C.iSeq.insert(List(
          (1, 1, List(1, 2)),
          (2, 2, List(2)),
          (2, 2, List(3, 4)),
          (2, 2, List(3, 4)),
        )).transact

        _ <- A.i.B.i._A.C.iSeq(count).a1.query.get.map(_ ==> List(
          (1, 1, 2),
          (2, 2, 5),
        ))
        _ <- A.i.B.i._A.C.iSeq(count).d1.query.get.map(_ ==> List(
          (2, 2, 5),
          (1, 1, 2),
        ))

        _ <- A.i.B.i._A.C.iSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 1, 2),
          (2, 2, 3),
        ))
        _ <- A.i.B.i._A.C.iSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 2, 3),
          (1, 1, 2),
        ))
      } yield ()
    }
  }
}