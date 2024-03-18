package molecule.coreTests.spi.aggr.seq.refNum

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeqRefNum_median extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Median of unique values (Seq semantics)

  override lazy val tests = Tests {
    // Different databases have different ways of calculating a median
    val wholeOrAverage = database match {
      case "Datomic" | "MongoDB" => 1.0 // lower whole number
      case _                     => (1 + 2).toDouble / 2.0 // average of 2 middle numbers
    }

    "1st ref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.iSeq.insert(List(
          (1, Seq(1, 2)),
          (2, Seq(2)),
          (2, Seq(5, 9)),
        )).transact

        _ <- A.B.iSeq(median).query.get.map(_.head ==~ 2)

        _ <- A.i.B.iSeq(median).a1.query.get.map(_ ==~ List(
          (1, wholeOrAverage),
          (2, 5.0),
        ))
        _ <- A.i.B.iSeq(median).d1.query.get.map(_ ==~ List(
          (2, 5.0),
          (1, wholeOrAverage),
        ))
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i.C.iSeq.insert(List(
          (1, 1, Seq(1, 2)),
          (2, 2, Seq(2)),
          (2, 2, Seq(5, 9)),
        )).transact

        _ <- A.B.C.iSeq(median).query.get.map(_.head ==~ 2)

        _ <- A.i.B.i.C.iSeq(median).a1.query.get.map(_ ==~ List(
          (1, 1, wholeOrAverage),
          (2, 2, 5.0),
        ))
        _ <- A.i.B.i.C.iSeq(median).d1.query.get.map(_ ==~ List(
          (2, 2, 5.0),
          (1, 1, wholeOrAverage),
        ))
      } yield ()
    }


    "backref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i._A.C.iSeq.insert(List(
          (1, 1, Seq(1, 2)),
          (2, 2, Seq(2)),
          (2, 2, Seq(5, 9)),
        )).transact

        _ <- A.i.B.i._A.C.iSeq(median).a1.query.get.map(_ ==~ List(
          (1, 1, wholeOrAverage),
          (2, 2, 5.0),
        ))
        _ <- A.i.B.i._A.C.iSeq(median).d1.query.get.map(_ ==~ List(
          (2, 2, 5.0),
          (1, 1, wholeOrAverage),
        ))
      } yield ()
    }
  }
}