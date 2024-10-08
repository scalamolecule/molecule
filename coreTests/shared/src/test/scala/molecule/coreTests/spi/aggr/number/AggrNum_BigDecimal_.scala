// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.number

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrNum_BigDecimal_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  // Using tolerant equality so that the test works with decimal number types too

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantBigDecimalEquality(toleranceBigDecimal)
      for {
        _ <- Ns.i.bigDecimal.insert(List(
          (1, bigDecimal1),
          (1, bigDecimal2),
          (2, bigDecimal2),
          (2, bigDecimal3),
          (2, bigDecimal4),
        )).transact

        // Sum of all values
        _ <- Ns.bigDecimal(sum).query.get.map(
          _.head ==~ bigDecimal1 + bigDecimal2 + bigDecimal2 + bigDecimal3 + bigDecimal4
        )

        _ <- Ns.i.bigDecimal(sum).query.get.map(_.map {
          case (1, sum) => sum ==~ bigDecimal1 + bigDecimal2
          case (2, sum) => sum ==~ bigDecimal2 + bigDecimal3 + bigDecimal4
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      // Different databases have different ways of calculating a median
      database match {
        case "Datomic" =>
          for {
            _ <- Ns.i.bigDecimal.insert(List(
              (1, bigDecimal1),
              (1, bigDecimal2),
              (2, bigDecimal2),
              (2, bigDecimal5),
              (2, bigDecimal9),
            )).transact

            // Median of all values - middle number used if odd number of values
            // 1  2  2  5  9
            //       ^
            _ <- Ns.bigDecimal(median).query.get.map(_.head ==~ bigDecimal2.toString.toDouble) // middle number

            _ <- Ns.i.bigDecimal(median).query.get.map(_.map {
              case (1, median) => median ==~ bigDecimal1.toDouble.floor // lower whole number
              case (2, median) => median ==~ bigDecimal5.toString.toDouble // middle number
            })
          } yield ()

        case _ =>
          for {
            _ <- Ns.i.bigDecimal.insert(List(
              (1, bigDecimal1),
              (1, bigDecimal2),
              (2, bigDecimal2),
              (2, bigDecimal5),
              (2, bigDecimal9),
            )).transact

            _ <- Ns.bigDecimal(median).query.get.map(_.head ==~ bigDecimal2.toString.toDouble) // middle number

            _ <- Ns.i.bigDecimal(median).query.get.map(_.map {
              case (1, median) => median ==~ (bigDecimal1 + bigDecimal2).toDouble / 2.0 // average of 2 middle numbers
              case (2, median) => median ==~ bigDecimal5.toString.toDouble // middle number
            })
          } yield ()
      }
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bigDecimal.insert(List(
          (1, bigDecimal1),
          (1, bigDecimal2),
          (2, bigDecimal2),
          (2, bigDecimal3),
          (2, bigDecimal4),
        )).transact

        // Average of all values
        _ <- Ns.bigDecimal(avg).query.get.map(
          _.head ==~ (bigDecimal1 + bigDecimal2 + bigDecimal2 + bigDecimal3 + bigDecimal4).toDouble / 5.0
        )

        _ <- Ns.i.bigDecimal(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (bigDecimal1 + bigDecimal2).toDouble / 2.0
          case (2, avg) => avg ==~ (bigDecimal2 + bigDecimal3 + bigDecimal4).toDouble / 3.0
        })
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bigDecimal.insert(List(
          (1, bigDecimal1),
          (1, bigDecimal2),
          (2, bigDecimal2),
          (2, bigDecimal3),
          (2, bigDecimal4),
        )).transact

        // Variance of all values
        _ <- Ns.bigDecimal(variance).query.get.map(
          _.head ==~ varianceOf(bigDecimal1, bigDecimal2, bigDecimal2, bigDecimal3, bigDecimal4)
        )

        _ <- Ns.i.bigDecimal(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(bigDecimal1, bigDecimal2)
          case (2, variance) => variance ==~ varianceOf(bigDecimal2, bigDecimal3, bigDecimal4)
        })
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bigDecimal.insert(List(
          (1, bigDecimal1),
          (1, bigDecimal2),
          (2, bigDecimal2),
          (2, bigDecimal3),
          (2, bigDecimal4),
        )).transact

        // Standard deviation of all values
        _ <- Ns.bigDecimal(stddev).query.get.map(
          _.head ==~ stdDevOf(bigDecimal1, bigDecimal2, bigDecimal2, bigDecimal3, bigDecimal4)
        )

        _ <- Ns.i.bigDecimal(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(bigDecimal1, bigDecimal2)
          case (2, stddev) => stddev ==~ stdDevOf(bigDecimal2, bigDecimal3, bigDecimal4)
        })
      } yield ()
    }
  }
}