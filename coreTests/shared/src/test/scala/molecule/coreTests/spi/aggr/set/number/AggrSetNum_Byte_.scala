// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetNum_Byte_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Using tolerant equality so that the test works with decimal number types too

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantByteEquality(toleranceByte)
      for {
        _ <- Ns.i.bytes.insert(List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2)),
          (2, Set(byte3, byte4)),
          (2, Set(byte3, byte4)),
        )).transact

        // Sum of all values
        _ <- Ns.bytes(sum).query.get.map(
          _.head.head ==~ (
            byte1 + byte2 +
              byte2 +
              byte3 + byte4 +
              byte3 + byte4
            )
        )

        _ <- Ns.i.bytes(sum).query.get.map(_.map {
          case (1, setWithSum) => setWithSum.head ==~ byte1 + byte2
          case (2, setWithSum) => setWithSum.head ==~ (
            byte2 +
              byte3 + byte4 +
              byte3 + byte4
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
            _ <- Ns.i.bytes.insert(List(
              (1, Set(byte1, byte2)),
              (2, Set(byte2)),
              (2, Set(byte5, byte9)),
            )).transact

            // Median of all values - middle number used if odd number of values
            // 1  2  2  5  9
            //       ^
            _ <- Ns.bytes(median).query.get.map(_.head ==~ byte2.toString.toDouble) // whole middle number

            _ <- Ns.i.bytes(median).query.get.map(_.map {
              case (1, median) => median ==~ byte1.toDouble.floor // lower whole number
              case (2, median) => median ==~ byte5.toString.toDouble // whole middle number
            })
          } yield ()

        case "MongoDB" =>
          for {
            _ <- Ns.i.bytes.insert(List(
              (1, Set(byte1, byte2)),
              (2, Set(byte2)),
              (2, Set(byte5, byte9)),
            )).transact

            _ <- Ns.bytes(median).query.get.map(_.head ==~ byte2.toString.toDouble) // whole middle number

            _ <- Ns.i.bytes(median).query.get.map(_.map {
              case (1, median) => median ==~ byte1.toDouble // lower number
              case (2, median) => median ==~ byte5.toString.toDouble // whole middle number
            })
          } yield ()

        case _ =>
          for {
            _ <- Ns.i.bytes.insert(List(
              (1, Set(byte1, byte2)),
              (2, Set(byte2)),
              (2, Set(byte5, byte9)),
            )).transact

            _ <- Ns.bytes(median).query.get.map(_.head ==~ byte2.toString.toDouble) // middle number

            _ <- Ns.i.bytes(median).query.get.map(_.map {
              case (1, median) => median ==~ (byte1 + byte2).toDouble / 2.0 // average of 2 middle numbers
              case (2, median) => median ==~ byte5.toString.toDouble // middle number
            })
          } yield ()
      }
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bytes.insert(List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2)),
          (2, Set(byte3, byte4)),
          (2, Set(byte3, byte4)),
        )).transact

        // Average of all values
        _ <- Ns.bytes(avg).query.get.map(_.head ==~ (
          byte1 + byte2 +
            byte2 +
            byte3 + byte4 +
            byte3 + byte4
          ).toDouble / 7.0)

        _ <- Ns.i.bytes(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (byte1 + byte2).toDouble / 2.0
          case (2, avg) => avg ==~ (
            byte2 +
              byte3 + byte4 +
              byte3 + byte4
            ).toDouble / 5.0
        })
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bytes.insert(List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2)),
          (2, Set(byte3, byte4)),
          (2, Set(byte3, byte4)),
        )).transact

        // Variance of all values
        _ <- Ns.bytes(variance).query.get.map(_.head ==~ varianceOf(
          byte1, byte2,
          byte2,
          byte3, byte4,
          byte3, byte4
        ))

        _ <- Ns.i.bytes(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(byte1, byte2)
          case (2, variance) => variance ==~ varianceOf(
            byte2,
            byte3, byte4,
            byte3, byte4
          )
        })
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bytes.insert(List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2)),
          (2, Set(byte3, byte4)),
          (2, Set(byte3, byte4)),
        )).transact


        // Standard deviation of all values
        _ <- Ns.bytes(stddev).query.get.map(_.head ==~ stdDevOf(
          byte1, byte2,
          byte2,
          byte3, byte4,
          byte3, byte4
        ))

        _ <- Ns.i.bytes(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(byte1, byte2)
          case (2, stddev) => stddev ==~ stdDevOf(
            byte2,
            byte3, byte4,
            byte3, byte4
          )
        })
      } yield ()
    }
  }
}