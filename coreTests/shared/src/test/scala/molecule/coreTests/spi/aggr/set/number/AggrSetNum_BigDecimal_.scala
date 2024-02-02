// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetNum_BigDecimal_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Using tolerant equality so that the test works with decimal number types too

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantBigDecimalEquality(toleranceBigDecimal)
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).i.transact

        // Sum of all values
        _ <- Ns.bigDecimals(sum).query.get.map(
          _.head.head ==~ (
            bigDecimal1 + bigDecimal2 +
              bigDecimal2 +
              bigDecimal3 + bigDecimal4 +
              bigDecimal3 + bigDecimal4
            )
        )

        _ <- Ns.i.bigDecimals(sum).query.get.map(_.map {
          case (1, setWithSum) => setWithSum.head ==~ bigDecimal1 + bigDecimal2
          case (2, setWithSum) => setWithSum.head ==~ (
            bigDecimal2 +
              bigDecimal3 + bigDecimal4 +
              bigDecimal3 + bigDecimal4
            )
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      // Different databases have different ways of calculating a median
      database match {
        case "Datomic" =>
          for {
            _ <- Ns.i.bigDecimals.insert(List(
              (1, Set(bigDecimal1, bigDecimal2)),
              (2, Set(bigDecimal2)),
              (2, Set(bigDecimal5, bigDecimal9)),
            )).transact

            // Median of all values - middle number used if odd number of values
            // 1  2  2  5  9
            //       ^
            _ <- Ns.bigDecimals(median).query.get.map(_.head ==~ bigDecimal2.toString.toDouble) // whole middle number

            _ <- Ns.i.bigDecimals(median).query.get.map(_.map {
              case (1, median) => median ==~ bigDecimal1.toDouble.floor // lower whole number
              case (2, median) => median ==~ bigDecimal5.toString.toDouble // whole middle number
            })
          } yield ()

        case "MongoDB" =>
          for {
            _ <- Ns.i.bigDecimals.insert(List(
              (1, Set(bigDecimal1, bigDecimal2)),
              (2, Set(bigDecimal2)),
              (2, Set(bigDecimal5, bigDecimal9)),
            )).transact

            _ <- Ns.bigDecimals(median).query.get.map(_.head ==~ bigDecimal2.toString.toDouble) // whole middle number

            _ <- Ns.i.bigDecimals(median).query.get.map(_.map {
              case (1, median) => median ==~ bigDecimal1.toDouble // lower number
              case (2, median) => median ==~ bigDecimal5.toString.toDouble // whole middle number
            })
          } yield ()

        case _ =>
          for {
            _ <- Ns.i.bigDecimals.insert(List(
              (1, Set(bigDecimal1, bigDecimal2)),
              (2, Set(bigDecimal2)),
              (2, Set(bigDecimal5, bigDecimal9)),
            )).transact

            _ <- Ns.bigDecimals(median).query.get.map(_.head ==~ bigDecimal2.toString.toDouble) // middle number

            _ <- Ns.i.bigDecimals(median).query.get.map(_.map {
              case (1, median) => median ==~ (bigDecimal1 + bigDecimal2).toDouble / 2.0 // average of 2 middle numbers
              case (2, median) => median ==~ bigDecimal5.toString.toDouble // middle number
            })
          } yield ()
      }
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        // Average of all values
        _ <- Ns.bigDecimals(avg).query.get.map(_.head ==~ (
          bigDecimal1 + bigDecimal2 +
            bigDecimal2 +
            bigDecimal3 + bigDecimal4 +
            bigDecimal3 + bigDecimal4
          ).toDouble / 7.0)

        _ <- Ns.i.bigDecimals(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (bigDecimal1 + bigDecimal2).toDouble / 2.0
          case (2, avg) => avg ==~ (
            bigDecimal2 +
              bigDecimal3 + bigDecimal4 +
              bigDecimal3 + bigDecimal4
            ).toDouble / 5.0
        })
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        // Variance of all values
        _ <- Ns.bigDecimals(variance).query.get.map(_.head ==~ varianceOf(
          bigDecimal1, bigDecimal2,
          bigDecimal2,
          bigDecimal3, bigDecimal4,
          bigDecimal3, bigDecimal4
        ))

        _ <- Ns.i.bigDecimals(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(bigDecimal1, bigDecimal2)
          case (2, variance) => variance ==~ varianceOf(
            bigDecimal2,
            bigDecimal3, bigDecimal4,
            bigDecimal3, bigDecimal4
          )
        })
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact


        // Standard deviation of all values
        _ <- Ns.bigDecimals(stddev).query.get.map(_.head ==~ stdDevOf(
          bigDecimal1, bigDecimal2,
          bigDecimal2,
          bigDecimal3, bigDecimal4,
          bigDecimal3, bigDecimal4
        ))

        _ <- Ns.i.bigDecimals(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(bigDecimal1, bigDecimal2)
          case (2, stddev) => stddev ==~ stdDevOf(
            bigDecimal2,
            bigDecimal3, bigDecimal4,
            bigDecimal3, bigDecimal4
          )
        })
      } yield ()
    }
  }
}