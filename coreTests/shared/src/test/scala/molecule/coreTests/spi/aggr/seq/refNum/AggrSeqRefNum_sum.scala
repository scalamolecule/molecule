package molecule.coreTests.spi.aggr.seq.refNum

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeqRefNum_sum extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Sum of unique values (Seq semantics)

  override lazy val tests = Tests {
    val all  = 1 + 2 + 2 + 3 + 4 + 3 + 4
    val all2 = 2 + 3 + 4 + 3 + 4

    "1st ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.iSeq.insert(List(
          (1, Seq(1, 2)),
          (2, Seq(2)),
          (2, Seq(3, 4)),
          (2, Seq(3, 4)),
        )).transact

        _ <- A.B.iSeq(sum).query.get.map(_.head ==~ all)

        _ <- A.i.B.iSeq(sum).a1.query.get.map(_ ==~ List(
          (1, 1 + 2),
          (2, all2),
        ))
        _ <- A.i.B.iSeq(sum).d1.query.get.map(_ ==~ List(
          (2, all2),
          (1, 1 + 2),
        ))
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.C.iSeq.insert(List(
          (1, 1, Seq(1, 2)),
          (2, 2, Seq(2)),
          (2, 2, Seq(3, 4)),
          (2, 2, Seq(3, 4)),
        )).transact

        _ <- A.B.C.iSeq(sum).query.get.map(_.head ==~ all)

        _ <- A.i.B.i.C.iSeq(sum).a1.query.get.map(_ ==~ List(
          (1, 1, 1 + 2),
          (2, 2, all2),
        ))
        _ <- A.i.B.i.C.iSeq(sum).d1.query.get.map(_ ==~ List(
          (2, 2, all2),
          (1, 1, 1 + 2),
        ))
      } yield ()
    }


    "backref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i._A.C.iSeq.insert(List(
          (1, 1, Seq(1, 2)),
          (2, 2, Seq(2)),
          (2, 2, Seq(3, 4)),
          (2, 2, Seq(3, 4)),
        )).transact

        _ <- A.i.B.i._A.C.iSeq(sum).a1.query.get.map(_ ==~ List(
          (1, 1, 1 + 2),
          (2, 2, all2),
        ))
        _ <- A.i.B.i._A.C.iSeq(sum).d1.query.get.map(_ ==~ List(
          (2, 2, all2),
          (1, 1, 1 + 2),
        ))
      } yield ()
    }
  }
}