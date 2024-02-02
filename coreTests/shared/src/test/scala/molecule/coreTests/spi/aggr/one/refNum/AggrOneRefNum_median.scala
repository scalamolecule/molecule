package molecule.coreTests.spi.aggr.one.refNum

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOneRefNum_median extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Median of unique values (Set semantics)

  override lazy val tests = Tests {

    // Different databases have different ways of calculating a median
    // when the count of values is even and there's no middle value
    val wholeOrAverage = if (List("Datomic", "MongoDB").contains(database)) {
      // lower whole number
      int1
    } else {
      // average
      (int1 + int2).toDouble / 2.0
    }


    "ref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i.insert(List(
          (1, 1),
          (1, 2),
          (2, 2),
          (2, 3),
          (2, 9),
        )).transact

        // Median of all (non-coalesced) values
        _ <- A.B.i(median).query.get.map(_.head ==~ 2)

        _ <- A.i.B.i(median).query.get.map(_.map {
          case (1, median) => median ==~ wholeOrAverage
          case (2, median) => median ==~ 3
        })
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i.C.i.insert(List(
          (1, 1, 1),
          (1, 1, 2),
          (2, 2, 2),
          (2, 2, 3),
          (2, 2, 9),
        )).transact

        _ <- A.B.C.i(median).query.get.map(_.head ==~ 2)

        _ <- A.i.B.i.C.i(median).query.get.map(_.map {
          case (1, 1, median) => median ==~ wholeOrAverage
          case (2, 2, median) => median ==~ 3
        })
      } yield ()
    }


    "multiple refs" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i.C.i.insert(List(
          (1, 1, 1),
          (1, 2, 2),
          (2, 2, 2),
          (2, 3, 3),
          (2, 9, 9),
        )).transact

        _ <- A.i.a1.B.i(median).C.i(median).query.get.map(_.map {
          case (1, median1, median2) =>
            median1 ==~ wholeOrAverage
            median2 ==~ wholeOrAverage
          case (2, median1, median2) =>
            median1 ==~ 3.0
            median2 ==~ 3.0
        })
      } yield ()
    }


    "backref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i._A.C.i.insert(List(
          (1, 1, 1),
          (1, 2, 2),
          (2, 2, 2),
          (2, 3, 3),
          (2, 9, 9),
        )).transact

        _ <- A.i.a1.B.i(median)._A.C.i(median).query.get.map(_.map {
          case (1, median1, median2) =>
            median1 ==~ wholeOrAverage
            median2 ==~ wholeOrAverage
          case (2, median1, median2) =>
            median1 ==~ 3.0
            median2 ==~ 3.0
        })
      } yield ()
    }
  }
}