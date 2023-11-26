// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.one.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOneNum_BigInt_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Using tolerant equality so that the test works with decimal number types too

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantBigIntEquality(toleranceBigInt)
      for {
        _ <- Ns.i.bigInt.insert(List(
          (1, bigInt1),
          (1, bigInt2),
          (2, bigInt2),
          (2, bigInt3),
          (2, bigInt4),
        )).transact

        // Sum of distinct values (Set semantics)

        _ <- Ns.bigInt(sum).query.get.map(_.head ==~ bigInt1 + bigInt2 + bigInt3 + bigInt4)
        _ <- Ns.i.bigInt(sum).query.get.map(_.map {
          case (1, sum) => sum ==~ bigInt1 + bigInt2
          case (2, sum) => sum ==~ bigInt2 + bigInt3 + bigInt4
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      // Different databases have different ways of calculating a median
      val (median_2_3, median_1_2) = if (database == "MongoDB") {
        (bigInt2, bigInt1)
      } else {
        (
          (bigInt2 + bigInt3).toDouble / 2.0,
          (bigInt1 + bigInt2).toDouble / 2.0
        )
      }
      for {
        _ <- Ns.i.bigInt.insert(List(
          (1, bigInt1),
          (1, bigInt2),
          (2, bigInt2),
          (2, bigInt3),
          (2, bigInt4),
        )).transact

        // Median of unique values (Set semantics)

        _ <- Ns.bigInt(median).query.get.map(_.head ==~ median_2_3)

        _ <- Ns.i.bigInt(median).query.get.map(_.map {
          case (1, median) => median ==~ median_1_2
          case (2, median) => median ==~ bigInt3.toString.toDouble
        })
      } yield ()
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bigInt.insert(List(
          (1, bigInt1),
          (1, bigInt2),
          (2, bigInt2),
          (2, bigInt3),
          (2, bigInt4),
        )).transact

        // Average of unique values (Set semantics)

        _ <- Ns.bigInt(avg).query.get.map(_.head ==~ (bigInt1 + bigInt2 + bigInt3 + bigInt4).toDouble / 4.0)

        _ <- Ns.i.bigInt(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (bigInt1 + bigInt2).toDouble / 2.0
          case (2, avg) => avg ==~ (bigInt2 + bigInt3 + bigInt4).toDouble / 3.0
        })
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bigInt.insert(List(
          (1, bigInt1),
          (1, bigInt2),
          (2, bigInt2),
          (2, bigInt3),
          (2, bigInt4),
        )).transact

        // Variance of unique values (Set semantics)

        _ <- Ns.bigInt(variance).query.get.map(_.head ==~ varianceOf(bigInt1, bigInt2, bigInt3, bigInt4))

        _ <- Ns.i.bigInt(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(bigInt1, bigInt2)
          case (2, variance) => variance ==~ varianceOf(bigInt2, bigInt3, bigInt4)
        })
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bigInt.insert(List(
          (1, bigInt1),
          (1, bigInt2),
          (2, bigInt2),
          (2, bigInt3),
          (2, bigInt4),
        )).transact

        // Standard deviation of unique values (Set semantics)

        _ <- Ns.bigInt(stddev).query.get.map(_.head ==~ stdDevOf(bigInt1, bigInt2, bigInt3, bigInt4))

        _ <- Ns.i.bigInt(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(bigInt1, bigInt2)
          case (2, stddev) => stddev ==~ stdDevOf(bigInt2, bigInt3, bigInt4)
        })
      } yield ()
    }
  }
}