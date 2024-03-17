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
    val wholeOrAverage = database match {
      case "Datomic" | "MongoDB" => 1.0 // lower whole number
      case _                     => (1 + 2).toDouble / 2.0 // average of 2 middle numbers
    }

    "1st ref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.iSet.insert(List(
          (1, Set(1, 2)),
          (2, Set(2)),
          (2, Set(5, 9)),
        )).transact

        _ <- A.B.iSet(median).query.get.map(_.head ==~ 2)

        _ <- A.i.B.iSet(median).query.get.map(_.map {
          case (1, median) => median ==~ wholeOrAverage
          case (2, median) => median ==~ 5.0
        })
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i.C.iSet.insert(List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2)),
          (2, 2, Set(5, 9)),
        )).transact

        _ <- A.B.C.iSet(median).query.get.map(_.head ==~ 2)

        _ <- A.i.B.i.C.iSet(median).query.get.map(_.map {
          case (1, 1, median) => median ==~ wholeOrAverage
          case (2, 2, median) => median ==~ 5.0
        })
      } yield ()
    }


    "backref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i._A.C.iSet.insert(List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2)),
          (2, 2, Set(5, 9)),
        )).transact

        _ <- A.i.B.i._A.C.iSet(median).query.get.map(_.map {
          case (1, 1, median) => median ==~ wholeOrAverage
          case (2, 2, median) => median ==~ 5.0
        })
      } yield ()
    }
  }
}