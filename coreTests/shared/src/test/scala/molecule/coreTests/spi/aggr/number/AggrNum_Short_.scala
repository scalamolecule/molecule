// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.number

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrNum_Short_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  // Using tolerant equality so that the test works with decimal number types too

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantShortEquality(toleranceShort)
      for {
        _ <- Ns.i.short.insert(List(
          (1, short1),
          (1, short2),
          (2, short2),
          (2, short3),
          (2, short4),
        )).transact

        // Sum of all values
        _ <- Ns.short(sum).query.get.map(
          _.head ==~ short1 + short2 + short2 + short3 + short4
        )

        _ <- Ns.i.short(sum).query.get.map(_.map {
          case (1, sum) => sum ==~ short1 + short2
          case (2, sum) => sum ==~ short2 + short3 + short4
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      // Different databases have different ways of calculating a median
      database match {
        case "Datomic" =>
          for {
            _ <- Ns.i.short.insert(List(
              (1, short1),
              (1, short2),
              (2, short2),
              (2, short5),
              (2, short9),
            )).transact

            // Median of all values - middle number used if odd number of values
            // 1  2  2  5  9
            //       ^
            _ <- Ns.short(median).query.get.map(_.head ==~ short2.toString.toDouble) // middle number

            _ <- Ns.i.short(median).query.get.map(_.map {
              case (1, median) => median ==~ short1.toDouble.floor // lower whole number
              case (2, median) => median ==~ short5.toString.toDouble // middle number
            })
          } yield ()

        case _ =>
          for {
            _ <- Ns.i.short.insert(List(
              (1, short1),
              (1, short2),
              (2, short2),
              (2, short5),
              (2, short9),
            )).transact

            _ <- Ns.short(median).query.get.map(_.head ==~ short2.toString.toDouble) // middle number

            _ <- Ns.i.short(median).query.get.map(_.map {
              case (1, median) => median ==~ (short1 + short2).toDouble / 2.0 // average of 2 middle numbers
              case (2, median) => median ==~ short5.toString.toDouble // middle number
            })
          } yield ()
      }
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.short.insert(List(
          (1, short1),
          (1, short2),
          (2, short2),
          (2, short3),
          (2, short4),
        )).transact

        // Average of all values
        _ <- Ns.short(avg).query.get.map(
          _.head ==~ (short1 + short2 + short2 + short3 + short4).toDouble / 5.0
        )

        _ <- Ns.i.short(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (short1 + short2).toDouble / 2.0
          case (2, avg) => avg ==~ (short2 + short3 + short4).toDouble / 3.0
        })
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.short.insert(List(
          (1, short1),
          (1, short2),
          (2, short2),
          (2, short3),
          (2, short4),
        )).transact

        // Variance of all values
        _ <- Ns.short(variance).query.get.map(
          _.head ==~ varianceOf(short1, short2, short2, short3, short4)
        )

        _ <- Ns.i.short(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(short1, short2)
          case (2, variance) => variance ==~ varianceOf(short2, short3, short4)
        })
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.short.insert(List(
          (1, short1),
          (1, short2),
          (2, short2),
          (2, short3),
          (2, short4),
        )).transact

        // Standard deviation of all values
        _ <- Ns.short(stddev).query.get.map(
          _.head ==~ stdDevOf(short1, short2, short2, short3, short4)
        )

        _ <- Ns.i.short(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(short1, short2)
          case (2, stddev) => stddev ==~ stdDevOf(short2, short3, short4)
        })
      } yield ()
    }
  }
}