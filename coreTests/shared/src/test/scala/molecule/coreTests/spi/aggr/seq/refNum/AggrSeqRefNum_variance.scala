package molecule.coreTests.spi.aggr.seq.refNum

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeqRefNum_variance extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Variance of unique values (Seq semantics)

  override lazy val tests = Tests {

    "1st ref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.iSeq.insert(List(
          (1, Seq(1, 2)),
          (2, Seq(2)),
          (2, Seq(3, 4)),
          (2, Seq(3, 4)),
        )).transact

        _ <- A.B.iSeq(variance).query.get.map(_.head ==~ varianceOf(1, 2, 2, 3, 4, 3, 4))

        _ <- A.i.B.iSeq(variance).a1.query.get.map(_ ==~ List(
          (1, varianceOf(1, 2)),
          (2, varianceOf(2, 3, 4, 3, 4)),
        ))
        _ <- A.i.B.iSeq(variance).d1.query.get.map(_ ==~ List(
          (2, varianceOf(2, 3, 4, 3, 4)),
          (1, varianceOf(1, 2)),
        ))
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i.C.iSeq.insert(List(
          (1, 1, Seq(1, 2)),
          (2, 2, Seq(2)),
          (2, 2, Seq(3, 4)),
          (2, 2, Seq(3, 4)),
        )).transact

        _ <- A.B.C.iSeq(variance).query.get.map(_.head ==~ varianceOf(1, 2, 2, 3, 4, 3, 4))

        _ <- A.i.B.i.C.iSeq(variance).a1.query.get.map(_ ==~ List(
          (1, 1, varianceOf(1, 2)),
          (2, 2, varianceOf(2, 3, 4, 3, 4)),
        ))
        _ <- A.i.B.i.C.iSeq(variance).d1.query.get.map(_ ==~ List(
          (2, 2, varianceOf(2, 3, 4, 3, 4)),
          (1, 1, varianceOf(1, 2)),
        ))
      } yield ()
    }


    "backref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i._A.C.iSeq.insert(List(
          (1, 1, Seq(1, 2)),
          (2, 2, Seq(2)),
          (2, 2, Seq(3, 4)),
          (2, 2, Seq(3, 4)),
        )).transact

        _ <- A.i.B.i._A.C.iSeq(variance).a1.query.get.map(_ ==~ List(
          (1, 1, varianceOf(1, 2)),
          (2, 2, varianceOf(2, 3, 4, 3, 4)),
        ))
        _ <- A.i.B.i._A.C.iSeq(variance).d1.query.get.map(_ ==~ List(
          (2, 2, varianceOf(2, 3, 4, 3, 4)),
          (1, 1, varianceOf(1, 2)),
        ))
      } yield ()
    }
  }
}