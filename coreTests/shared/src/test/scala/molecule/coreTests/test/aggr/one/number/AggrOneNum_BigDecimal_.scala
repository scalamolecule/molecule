// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.one.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOneNum_BigDecimal_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

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

        // Using tolerant equality so that the test works with decimal number types too
        // Distinct values (Set semantics) used
        _ <- Ns.bigDecimal(sum).query.get.map(_.head ==~ bigDecimal1 + bigDecimal2 + bigDecimal3 + bigDecimal4)
        _ <- Ns.i.bigDecimal(sum).query.get.map(_.map {
          case (1, sum) => sum ==~ bigDecimal1 + bigDecimal2
          case (2, sum) => sum ==~ bigDecimal2 + bigDecimal3 + bigDecimal4
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bigDecimal.insert(List(
          (1, bigDecimal1),
          (1, bigDecimal2),
          (2, bigDecimal2),
          (2, bigDecimal3),
          (2, bigDecimal4),
        )).transact

        _ <- Ns.bigDecimal.a1.query.get.map(_ ==> List(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4))
        _ <- Ns.bigDecimal(median).query.get.map(_.head ==~ (bigDecimal2 + bigDecimal3).toDouble / 2.0)

        _ <- Ns.i.bigDecimal(median).query.get.map(_.map {
          case (1, median) => median ==~ (bigDecimal1 + bigDecimal2).toDouble / 2.0
          case (2, median) => median ==~ bigDecimal3.toString.toDouble
        })
      } yield ()
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

        _ <- Ns.bigDecimal.a1.query.get.map(_ ==> List(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4))
        _ <- Ns.bigDecimal(avg).query.get.map(_.head ==~ (bigDecimal1 + bigDecimal2 + bigDecimal3 + bigDecimal4).toDouble / 4.0)

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

        _ <- Ns.bigDecimal.a1.query.get.map(_ ==> List(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4))
        _ <- Ns.bigDecimal(variance).query.get.map(_.head ==~ varianceOf(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4))

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

        _ <- Ns.bigDecimal.a1.query.get.map(_ ==> List(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4))
        _ <- Ns.bigDecimal(stddev).query.get.map(_.head ==~ stdDevOf(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4))

        _ <- Ns.i.bigDecimal(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(bigDecimal1, bigDecimal2)
          case (2, stddev) => stddev ==~ stdDevOf(bigDecimal2, bigDecimal3, bigDecimal4)
        })
      } yield ()
    }
  }
}