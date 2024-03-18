package molecule.coreTests.spi.aggr.seq.refNum

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeqRefNum_avg extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Average of unique values (Seq semantics)

  override lazy val tests = Tests {
    val all  = 1 + 2 + 2 + 2 + 3 + 4 + 3 + 4
    val all2 = 2 + 3 + 4 + 3 + 4

    "1st ref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.iSeq.insert(List(
          (1, Seq(1, 2, 2)),
          (2, Seq(2)),
          (2, Seq(3, 4)),
          (2, Seq(3, 4)),
        )).transact

        _ <- A.B.iSeq(avg).query.get.map(_.head ==~ all.toDouble / 8.0)

        _ <- A.i.B.iSeq(avg).a1.query.get.map(_ ==~ List(
          (1, (1 + 2 + 2).toDouble / 3.0),
          (2, all2.toDouble / 5.0),
        ))
        _ <- A.i.B.iSeq(avg).d1.query.get.map(_ ==~ List(
          (2, all2.toDouble / 5.0),
          (1, (1 + 2 + 2).toDouble / 3.0),
        ))
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i.C.iSeq.insert(List(
          (1, 1, Seq(1, 2, 2)),
          (2, 2, Seq(2)),
          (2, 2, Seq(3, 4)),
          (2, 2, Seq(3, 4)),
        )).transact

        _ <- A.B.C.iSeq(avg).query.get.map(_.head ==~ all.toDouble / 8.0)

        _ <- A.i.B.i.C.iSeq(avg).a1.query.get.map(_ ==~ List(
          (1, 1, (1 + 2 + 2).toDouble / 3.0),
          (2, 2, all2.toDouble / 5.0),
        ))
        _ <- A.i.B.i.C.iSeq(avg).d1.query.get.map(_ ==~ List(
          (2, 2, all2.toDouble / 5.0),
          (1, 1, (1 + 2 + 2).toDouble / 3.0),
        ))
      } yield ()
    }


    "backref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i._A.C.iSeq.insert(List(
          (1, 1, Seq(1, 2, 2)),
          (2, 2, Seq(2)),
          (2, 2, Seq(3, 4)),
          (2, 2, Seq(3, 4)),
        )).transact

        _ <- A.i.B.i._A.C.iSeq(avg).a1.query.get.map(_ ==~ List(
          (1, 1, (1 + 2 + 2).toDouble / 3.0),
          (2, 2, all2.toDouble / 5.0),
        ))
        _ <- A.i.B.i._A.C.iSeq(avg).d1.query.get.map(_ ==~ List(
          (2, 2, all2.toDouble / 5.0),
          (1, 1, (1 + 2 + 2).toDouble / 3.0),
        ))
      } yield ()
    }
  }
}