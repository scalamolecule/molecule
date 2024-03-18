package molecule.coreTests.spi.aggr.seq.ref

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeqRef_max extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "1st ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.iSeq.insert(List(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(3, 4)),
          (2, List(3, 4)),
        )).transact

        _ <- A.B.iSeq(max).query.get.map(_ ==> List(4))

        _ <- A.i.B.iSeq(max).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 4),
        ))
        _ <- A.i.B.iSeq(max).d1.query.get.map(_ ==> List(
          (2, 4),
          (1, 2),
        ))


        _ <- A.B.iSeq(max(1)).query.get.map(_ ==> List(Set(4)))
        _ <- A.B.iSeq(max(2)).query.get.map(_ ==> List(Set(3, 4)))
        _ <- A.B.iSeq(max(3)).query.get.map(_ ==> List(Set(2, 3, 4)))

        _ <- A.i.a1.B.iSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(2)),
          (2, Set(4)),
        ))
        _ <- A.i.a1.B.iSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(3, 4)),
        ))
        _ <- A.i.a1.B.iSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 3, 4)),
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

        _ <- A.B.C.iSeq(max).query.get.map(_ ==> List(4))

        _ <- A.i.B.i.C.iSeq(max).a1.query.get.map(_ ==> List(
          (1, 1, 2),
          (2, 2, 4),
        ))
        _ <- A.i.B.i.C.iSeq(max).d1.query.get.map(_ ==> List(
          (2, 2, 4),
          (1, 1, 2),
        ))


        _ <- A.B.C.iSeq(max(1)).query.get.map(_ ==> List(Set(4)))
        _ <- A.B.C.iSeq(max(2)).query.get.map(_ ==> List(Set(3, 4)))
        _ <- A.B.C.iSeq(max(3)).query.get.map(_ ==> List(Set(2, 3, 4)))

        _ <- A.i.a1.B.i.C.iSeq(max(1)).query.get.map(_ ==> List(
          (1, 1, Set(2)),
          (2, 2, Set(4)),
        ))
        _ <- A.i.a1.B.i.C.iSeq(max(2)).query.get.map(_ ==> List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(3, 4)),
        ))
        _ <- A.i.a1.B.i.C.iSeq(max(3)).query.get.map(_ ==> List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2, 3, 4)),
        ))
      } yield ()
    }


    "backref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i._A.C.iSeq.insert(List(
          (1, 1, List(1, 2, 2)),
          (2, 2, List(2)),
          (2, 2, List(3, 4)),
          (2, 2, List(3, 4)),
        )).transact

        _ <- A.B._A.C.iSeq(max).query.get.map(_ ==> List(4))

        _ <- A.i.B.i._A.C.iSeq(max).a1.query.get.map(_ ==> List(
          (1, 1, 2),
          (2, 2, 4),
        ))
        _ <- A.i.B.i._A.C.iSeq(max).d1.query.get.map(_ ==> List(
          (2, 2, 4),
          (1, 1, 2),
        ))


        _ <- A.B._A.C.iSeq(max(1)).query.get.map(_ ==> List(Set(4)))
        _ <- A.B._A.C.iSeq(max(2)).query.get.map(_ ==> List(Set(3, 4)))
        _ <- A.B._A.C.iSeq(max(3)).query.get.map(_ ==> List(Set(2, 3, 4)))

        _ <- A.i.a1.B.i._A.C.iSeq(max(1)).query.get.map(_ ==> List(
          (1, 1, Set(2)),
          (2, 2, Set(4)),
        ))
        _ <- A.i.a1.B.i._A.C.iSeq(max(2)).query.get.map(_ ==> List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(3, 4)),
        ))
        _ <- A.i.a1.B.i._A.C.iSeq(max(3)).query.get.map(_ ==> List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2, 3, 4)),
        ))
      } yield ()
    }
  }
}