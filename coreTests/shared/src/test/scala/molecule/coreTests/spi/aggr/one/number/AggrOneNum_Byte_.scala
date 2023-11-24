// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.one.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOneNum_Byte_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantByteEquality(toleranceByte)
      for {
        _ <- Ns.i.byte.insert(List(
          (1, byte1),
          (1, byte2),
          (2, byte2),
          (2, byte3),
          (2, byte4),
        )).transact

        // Using tolerant equality so that the test works with decimal number types too
        // Distinct values (Set semantics) used
        _ <- Ns.byte(sum).query.get.map(_.head ==~ byte1 + byte2 + byte3 + byte4)
        _ <- Ns.i.byte(sum).query.get.map(_.map {
          case (1, sum) => sum ==~ byte1 + byte2
          case (2, sum) => sum ==~ byte2 + byte3 + byte4
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      // Different databases have different ways of calculating a median
      val (median_2_3, median_1_2) = if (database == "MongoDB") {
        (byte2, byte1)
      } else {
        (
          (byte2 + byte3).toDouble / 2.0,
          (byte1 + byte2).toDouble / 2.0
        )
      }
      for {
        _ <- Ns.i.byte.insert(List(
          (1, byte1),
          (1, byte2),
          (2, byte2),
          (2, byte3),
          (2, byte4),
        )).transact

        _ <- Ns.byte(median).query.get.map(_.head ==~ median_2_3)

        _ <- Ns.i.byte(median).query.get.map(_.map {
          case (1, median) => median ==~ median_1_2
          case (2, median) => median ==~ byte3.toString.toDouble
        })
      } yield ()
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.byte.insert(List(
          (1, byte1),
          (1, byte2),
          (2, byte2),
          (2, byte3),
          (2, byte4),
        )).transact

        _ <- Ns.byte(avg).query.get.map(_.head ==~ (byte1 + byte2 + byte3 + byte4).toDouble / 4.0)

        _ <- Ns.i.byte(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (byte1 + byte2).toDouble / 2.0
          case (2, avg) => avg ==~ (byte2 + byte3 + byte4).toDouble / 3.0
        })
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.byte.insert(List(
          (1, byte1),
          (1, byte2),
          (2, byte2),
          (2, byte3),
          (2, byte4),
        )).transact

        _ <- Ns.byte(variance).query.get.map(_.head ==~ varianceOf(byte1, byte2, byte3, byte4))

        _ <- Ns.i.byte(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(byte1, byte2)
          case (2, variance) => variance ==~ varianceOf(byte2, byte3, byte4)
        })
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.byte.insert(List(
          (1, byte1),
          (1, byte2),
          (2, byte2),
          (2, byte3),
          (2, byte4),
        )).transact

        _ <- Ns.byte(stddev).query.get.map(_.head ==~ stdDevOf(byte1, byte2, byte3, byte4))

        _ <- Ns.i.byte(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(byte1, byte2)
          case (2, stddev) => stddev ==~ stdDevOf(byte2, byte3, byte4)
        })
      } yield ()
    }
  }
}