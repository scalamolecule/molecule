package molecule.coreTests.spi.aggr.seq.ref

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeqRef_distinct extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "1st ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.iSeq.insert(List(
          (1, List(1, 2)),
          (2, List(2)),
          (2, List(3, 4)),
          (2, List(3, 4)),
        )).transact

        // Matching values coalesced into one Seq

        _ <- A.i.a1.B.iSeq.query.get.map(_ ==> List(
          (1, List(1, 2)),
          (2, List(2)),
          (2, List(3, 4)), // 2 rows coalsedced
        ))

        _ <- A.i.a1.B.iSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(1, 2))),
          (2, Set(
            List(2),
            List(3, 4) // 2 rows coalesced
          ))
        ))

        _ <- A.B.iSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(1, 2),
            List(2),
            List(3, 4),
          )
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

        _ <- A.i.a1.B.i.C.iSeq(distinct).query.get.map(_ ==> List(
          (1, 1, Set(List(1, 2))),
          (2, 2, Set(
            List(2),
            List(3, 4) // 2 rows coalesced
          ))
        ))

        _ <- A.B.C.iSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(1, 2),
            List(2),
            List(3, 4),
          )
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

        _ <- A.i.a1.B.i._A.C.iSeq(distinct).query.get.map(_ ==> List(
          (1, 1, Set(List(1, 2))),
          (2, 2, Set(List(2), List(3, 4))),
        ))
      } yield ()
    }
  }
}