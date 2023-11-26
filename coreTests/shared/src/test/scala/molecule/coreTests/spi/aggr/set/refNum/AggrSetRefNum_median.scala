package molecule.coreTests.spi.aggr.set.refNum

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetRefNum_median extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Median of unique values (Set semantics)

  override lazy val tests = Tests {

    // Different databases have different ways of calculating a median
    val (median_2_3, median_1_2) = if (database == "MongoDB") {
      (2, 1)
    } else {
      (
        (2 + 3).toDouble / 2.0,
        (1 + 2).toDouble / 2.0
      )
    }

    "ref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.ii.insert(List(
          (1, Set(1, 2)),
          (2, Set(2, 3)),
          (2, Set(3, 4)),
          (2, Set(3, 4)),
        )).transact

        _ <- A.B.ii.query.get.map(_ ==> List(Set(1, 2, 3, 4)))
        _ <- A.B.ii(median).query.get.map(_.head ==~ median_2_3)

        _ <- A.i.a1.B.ii.query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 3, 4)),
        ))
        _ <- A.i.B.ii(median).query.get.map(_.map {
          case (1, median) => median ==~ median_1_2
          case (2, median) => median ==~ 3.0
        })
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i.C.ii.insert(List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2, 3)),
          (2, 2, Set(3, 4)),
          (2, 2, Set(3, 4)),
        )).transact

        _ <- A.B.C.ii(median).query.get.map(_.head ==~ median_2_3)

        _ <- A.i.B.i.C.ii(median).query.get.map(_.map {
          case (1, 1, median) => median ==~ median_1_2
          case (2, 2, median) => median ==~ 3.0
        })
      } yield ()
    }


    "multiple refs" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.ii.C.ii.insert(List(
          (1, Set(1, 2), Set(1, 2)),
          (2, Set(2, 3), Set(2, 3)),
          (2, Set(3, 4), Set(3, 4)),
          (2, Set(3, 4), Set(3, 4)),
        )).transact

        _ <- A.i.B.ii(median).C.ii(median).query.get.map(_.map {
          case (1, medianB, medianC) =>
            medianB ==~ median_1_2
            medianC ==~ median_1_2
          case (2, medianB, medianC) =>
            medianB ==~ 3.0
            medianC ==~ 3.0
        })
      } yield ()
    }


    "backref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.ii._A.C.ii.insert(List(
          (1, Set(1, 2), Set(1, 2)),
          (2, Set(2, 3), Set(2, 3)),
          (2, Set(3, 4), Set(3, 4)),
          (2, Set(3, 4), Set(3, 4)),
        )).transact

        _ <- A.i.B.ii(median)._A.C.ii(median).query.get.map(_.map {
          case (1, medianB, medianC) =>
            medianB ==~ median_1_2
            medianC ==~ median_1_2
          case (2, medianB, medianC) =>
            medianB ==~ 3.0
            medianC ==~ 3.0
        })
      } yield ()
    }
  }
}