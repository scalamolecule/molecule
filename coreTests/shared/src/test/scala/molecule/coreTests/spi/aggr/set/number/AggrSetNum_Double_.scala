// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetNum_Double_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Using tolerant equality so that the test works with decimal number types too

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        // Sum of unique values (Set semantics)

        _ <- Ns.doubles(sum).query.get.map(_.head.head ==~ double1 + double2 + double3 + double4)

        _ <- Ns.i.doubles(sum).query.get.map(_.map {
          case (1, setWithSum) => setWithSum.head ==~ double1 + double2
          case (2, setWithSum) => setWithSum.head ==~ double2 + double3 + double4
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      // Different databases have different ways of calculating a median
      val (median_2_3, median_1_2) = if (database == "MongoDB") {
        (double2, double1)
      } else {
        (
          (double2 + double3).toDouble / 2.0,
          (double1 + double2).toDouble / 2.0
        )
      }
      for {
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        // Median of unique values (Set semantics)

        _ <- Ns.doubles.query.get.map(_ ==> List(Set(double1, double2, double3, double4)))
        _ <- Ns.doubles(median).query.get.map(_.head ==~ median_2_3)

        _ <- Ns.i.a1.doubles.query.get.map(_ ==> List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3, double4)),
        ))
        _ <- Ns.i.doubles(median).query.get.map(_.map {
          case (1, median) => median ==~ median_1_2
          case (2, median) => median ==~ double3.toString.toDouble
        })
      } yield ()
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        // Average of unique values (Set semantics)

        _ <- Ns.doubles.query.get.map(_ ==> List(Set(double1, double2, double3, double4)))
        _ <- Ns.doubles(avg).query.get.map(_.head ==~ (double1 + double2 + double3 + double4).toDouble / 4.0)

        _ <- Ns.i.a1.doubles.query.get.map(_ ==> List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3, double4)),
        ))
        _ <- Ns.i.doubles(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (double1 + double2).toDouble / 2.0
          case (2, avg) => avg ==~ (double2 + double3 + double4).toDouble / 3.0
        })
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        // Variance of unique values (Set semantics)

        _ <- Ns.doubles.query.get.map(_ ==> List(Set(double1, double2, double3, double4)))
        _ <- Ns.doubles(variance).query.get.map(_.head ==~ varianceOf(double1, double2, double3, double4))

        _ <- Ns.i.a1.doubles.query.get.map(_ ==> List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3, double4)),
        ))
        _ <- Ns.i.doubles(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(double1, double2)
          case (2, variance) => variance ==~ varianceOf(double2, double3, double4)
        })
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        // Standard deviation of unique values (Set semantics)

        _ <- Ns.doubles.query.get.map(_ ==> List(Set(double1, double2, double3, double4)))
        _ <- Ns.doubles(stddev).query.get.map(_.head ==~ stdDevOf(double1, double2, double3, double4))

        _ <- Ns.i.a1.doubles.query.get.map(_ ==> List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3, double4)),
        ))
        _ <- Ns.i.doubles(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(double1, double2)
          case (2, stddev) => stddev ==~ stdDevOf(double2, double3, double4)
        })
      } yield ()
    }
  }
}