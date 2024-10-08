// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.number

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrNum_Double_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  // Using tolerant equality so that the test works with decimal number types too

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.double.insert(List(
          (1, double1),
          (1, double2),
          (2, double2),
          (2, double3),
          (2, double4),
        )).transact

        // Sum of all values
        _ <- Ns.double(sum).query.get.map(
          _.head ==~ double1 + double2 + double2 + double3 + double4
        )

        _ <- Ns.i.double(sum).query.get.map(_.map {
          case (1, sum) => sum ==~ double1 + double2
          case (2, sum) => sum ==~ double2 + double3 + double4
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      // Different databases have different ways of calculating a median
      database match {
        case "Datomic" =>
          for {
            _ <- Ns.i.double.insert(List(
              (1, double1),
              (1, double2),
              (2, double2),
              (2, double5),
              (2, double9),
            )).transact

            // Median of all values - middle number used if odd number of values
            // 1  2  2  5  9
            //       ^
            _ <- Ns.double(median).query.get.map(_.head ==~ double2.toString.toDouble) // middle number

            _ <- Ns.i.double(median).query.get.map(_.map {
              case (1, median) => median ==~ double1.toDouble.floor // lower whole number
              case (2, median) => median ==~ double5.toString.toDouble // middle number
            })
          } yield ()

        case _ =>
          for {
            _ <- Ns.i.double.insert(List(
              (1, double1),
              (1, double2),
              (2, double2),
              (2, double5),
              (2, double9),
            )).transact

            _ <- Ns.double(median).query.get.map(_.head ==~ double2.toString.toDouble) // middle number

            _ <- Ns.i.double(median).query.get.map(_.map {
              case (1, median) => median ==~ (double1 + double2).toDouble / 2.0 // average of 2 middle numbers
              case (2, median) => median ==~ double5.toString.toDouble // middle number
            })
          } yield ()
      }
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.double.insert(List(
          (1, double1),
          (1, double2),
          (2, double2),
          (2, double3),
          (2, double4),
        )).transact

        // Average of all values
        _ <- Ns.double(avg).query.get.map(
          _.head ==~ (double1 + double2 + double2 + double3 + double4).toDouble / 5.0
        )

        _ <- Ns.i.double(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (double1 + double2).toDouble / 2.0
          case (2, avg) => avg ==~ (double2 + double3 + double4).toDouble / 3.0
        })
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.double.insert(List(
          (1, double1),
          (1, double2),
          (2, double2),
          (2, double3),
          (2, double4),
        )).transact

        // Variance of all values
        _ <- Ns.double(variance).query.get.map(
          _.head ==~ varianceOf(double1, double2, double2, double3, double4)
        )

        _ <- Ns.i.double(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(double1, double2)
          case (2, variance) => variance ==~ varianceOf(double2, double3, double4)
        })
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.double.insert(List(
          (1, double1),
          (1, double2),
          (2, double2),
          (2, double3),
          (2, double4),
        )).transact

        // Standard deviation of all values
        _ <- Ns.double(stddev).query.get.map(
          _.head ==~ stdDevOf(double1, double2, double2, double3, double4)
        )

        _ <- Ns.i.double(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(double1, double2)
          case (2, stddev) => stddev ==~ stdDevOf(double2, double3, double4)
        })
      } yield ()
    }
  }
}