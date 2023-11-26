// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.one.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOneNum_Short_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

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

        // Sum of distinct values (Set semantics)

        _ <- Ns.short(sum).query.get.map(_.head ==~ short1 + short2 + short3 + short4)
        _ <- Ns.i.short(sum).query.get.map(_.map {
          case (1, sum) => sum ==~ short1 + short2
          case (2, sum) => sum ==~ short2 + short3 + short4
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      // Different databases have different ways of calculating a median
      val (median_2_3, median_1_2) = if (database == "MongoDB") {
        (short2, short1)
      } else {
        (
          (short2 + short3).toDouble / 2.0,
          (short1 + short2).toDouble / 2.0
        )
      }
      for {
        _ <- Ns.i.short.insert(List(
          (1, short1),
          (1, short2),
          (2, short2),
          (2, short3),
          (2, short4),
        )).transact

        // Median of unique values (Set semantics)

        _ <- Ns.short(median).query.get.map(_.head ==~ median_2_3)

        _ <- Ns.i.short(median).query.get.map(_.map {
          case (1, median) => median ==~ median_1_2
          case (2, median) => median ==~ short3.toString.toDouble
        })
      } yield ()
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

        // Average of unique values (Set semantics)

        _ <- Ns.short(avg).query.get.map(_.head ==~ (short1 + short2 + short3 + short4).toDouble / 4.0)

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

        // Variance of unique values (Set semantics)

        _ <- Ns.short(variance).query.get.map(_.head ==~ varianceOf(short1, short2, short3, short4))

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

        // Standard deviation of unique values (Set semantics)

        _ <- Ns.short(stddev).query.get.map(_.head ==~ stdDevOf(short1, short2, short3, short4))

        _ <- Ns.i.short(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(short1, short2)
          case (2, stddev) => stddev ==~ stdDevOf(short2, short3, short4)
        })
      } yield ()
    }
  }
}