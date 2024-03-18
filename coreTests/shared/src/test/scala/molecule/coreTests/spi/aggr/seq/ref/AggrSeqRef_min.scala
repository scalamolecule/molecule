package molecule.coreTests.spi.aggr.seq.ref

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeqRef_min extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "1st ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.iSeq.insert(List(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(3, 4)),
          (2, List(3, 4)),
        )).transact

        _ <- A.B.iSeq(min).query.get.map(_ ==> List(1))

        _ <- A.i.B.iSeq(min).a1.query.get.map(_ ==> List(
          (1, 1),
          (2, 2),
        ))
        _ <- A.i.B.iSeq(min).d1.query.get.map(_ ==> List(
          (2, 2),
          (1, 1),
        ))

        
        _ <- A.B.iSeq(min(1)).query.get.map(_ ==> List(Set(1)))
        _ <- A.B.iSeq(min(2)).query.get.map(_ ==> List(Set(1, 2)))
        _ <- A.B.iSeq(min(3)).query.get.map(_ ==> List(Set(1, 2, 3)))

        _ <- A.i.a1.B.iSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(1)),
          (2, Set(2)),
        ))
        _ <- A.i.a1.B.iSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 3)),
        ))
        _ <- A.i.a1.B.iSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 3, 4)),
        ))
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.C.iSeq.insert(List(
          (1, 1, List(1, 2, 2)),
          (2, 2, List(2)),
          (2, 2, List(3, 4)),
          (2, 2, List(3, 4)),
        )).transact

        _ <- A.B.C.iSeq(min).query.get.map(_ ==> List(1))

        _ <- A.i.B.i.C.iSeq(min).a1.query.get.map(_ ==> List(
          (1, 1, 1),
          (2, 2, 2),
        ))
        _ <- A.i.B.i.C.iSeq(min).d1.query.get.map(_ ==> List(
          (2, 2, 2),
          (1, 1, 1),
        ))
        
        
        _ <- A.B.C.iSeq(min(1)).query.get.map(_ ==> List(Set(1)))
        _ <- A.B.C.iSeq(min(2)).query.get.map(_ ==> List(Set(1, 2)))
        _ <- A.B.C.iSeq(min(3)).query.get.map(_ ==> List(Set(1, 2, 3)))

        _ <- A.i.a1.B.i.C.iSeq(min(1)).query.get.map(_ ==> List(
          (1, 1, Set(1)),
          (2, 2, Set(2)),
        ))
        _ <- A.i.a1.B.i.C.iSeq(min(2)).query.get.map(_ ==> List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2, 3)),
        ))
        _ <- A.i.a1.B.i.C.iSeq(min(3)).query.get.map(_ ==> List(
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

        _ <- A.i.B.i._A.C.iSeq(min).a1.query.get.map(_ ==> List(
          (1, 1, 1),
          (2, 2, 2),
        ))
        _ <- A.i.B.i._A.C.iSeq(min).d1.query.get.map(_ ==> List(
          (2, 2, 2),
          (1, 1, 1),
        ))
        
        
        _ <- A.i.a1.B.i._A.C.iSeq(min(1)).query.get.map(_ ==> List(
          (1, 1, Set(1)),
          (2, 2, Set(2)),
        ))
        _ <- A.i.a1.B.i._A.C.iSeq(min(2)).query.get.map(_ ==> List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2, 3)),
        ))
        _ <- A.i.a1.B.i._A.C.iSeq(min(3)).query.get.map(_ ==> List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2, 3, 4)),
        ))
      } yield ()
    }
  }
}