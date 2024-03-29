// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrNum_Long_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Using tolerant equality so that the test works with decimal number types too

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantLongEquality(toleranceLong)
      for {
        _ <- Ns.i.long.insert(List(
          (1, long1),
          (1, long2),
          (2, long2),
          (2, long3),
          (2, long4),
        )).transact

        // Sum of all values
        _ <- Ns.long(sum).query.get.map(
          _.head ==~ long1 + long2 + long2 + long3 + long4
        )

        _ <- Ns.i.long(sum).query.get.map(_.map {
          case (1, sum) => sum ==~ long1 + long2
          case (2, sum) => sum ==~ long2 + long3 + long4
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      // Different databases have different ways of calculating a median
      database match {
        case "Datomic" =>
          for {
            _ <- Ns.i.long.insert(List(
              (1, long1),
              (1, long2),
              (2, long2),
              (2, long5),
              (2, long9),
            )).transact

            // Median of all values - middle number used if odd number of values
            // 1  2  2  5  9
            //       ^
            _ <- Ns.long(median).query.get.map(_.head ==~ long2.toString.toDouble) // middle number

            _ <- Ns.i.long(median).query.get.map(_.map {
              case (1, median) => median ==~ long1.toDouble.floor // lower whole number
              case (2, median) => median ==~ long5.toString.toDouble // middle number
            })
          } yield ()

        case "MongoDB" =>
          for {
            _ <- Ns.i.long.insert(List(
              (1, long1),
              (1, long2),
              (2, long2),
              (2, long5),
              (2, long9),
            )).transact

            _ <- Ns.long(median).query.get.map(_.head ==~ long2.toString.toDouble) // whole middle number

            _ <- Ns.i.long(median).query.get.map(_.map {
              case (1, median) => median ==~ long1.toDouble // lower number
              case (2, median) => median ==~ long5.toString.toDouble // middle number
            })
          } yield ()

        case _ =>
          for {
            _ <- Ns.i.long.insert(List(
              (1, long1),
              (1, long2),
              (2, long2),
              (2, long5),
              (2, long9),
            )).transact

            _ <- Ns.long(median).query.get.map(_.head ==~ long2.toString.toDouble) // middle number

            _ <- Ns.i.long(median).query.get.map(_.map {
              case (1, median) => median ==~ (long1 + long2).toDouble / 2.0 // average of 2 middle numbers
              case (2, median) => median ==~ long5.toString.toDouble // middle number
            })
          } yield ()
      }
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.long.insert(List(
          (1, long1),
          (1, long2),
          (2, long2),
          (2, long3),
          (2, long4),
        )).transact

        // Average of all values
        _ <- Ns.long(avg).query.get.map(
          _.head ==~ (long1 + long2 + long2 + long3 + long4).toDouble / 5.0
        )

        _ <- Ns.i.long(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (long1 + long2).toDouble / 2.0
          case (2, avg) => avg ==~ (long2 + long3 + long4).toDouble / 3.0
        })
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.long.insert(List(
          (1, long1),
          (1, long2),
          (2, long2),
          (2, long3),
          (2, long4),
        )).transact

        // Variance of all values
        _ <- Ns.long(variance).query.get.map(
          _.head ==~ varianceOf(long1, long2, long2, long3, long4)
        )

        _ <- Ns.i.long(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(long1, long2)
          case (2, variance) => variance ==~ varianceOf(long2, long3, long4)
        })
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.long.insert(List(
          (1, long1),
          (1, long2),
          (2, long2),
          (2, long3),
          (2, long4),
        )).transact

        // Standard deviation of all values
        _ <- Ns.long(stddev).query.get.map(
          _.head ==~ stdDevOf(long1, long2, long2, long3, long4)
        )

        _ <- Ns.i.long(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(long1, long2)
          case (2, stddev) => stddev ==~ stdDevOf(long2, long3, long4)
        })
      } yield ()
    }
  }
}