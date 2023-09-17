// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.set.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetNum_BigDecimal_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantBigDecimalEquality(toleranceBigDecimal)
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        // Using tolerant equality so that the test works with decimal number types too
        _ <- Ns.bigDecimals(sum).query.get.map(_.head.head ==~ bigDecimal1 + bigDecimal2 + bigDecimal3 + bigDecimal4)

        _ <- Ns.i.bigDecimals(sum).query.get.map(_.map {
          case (1, setWithSum) => setWithSum.head ==~ bigDecimal1 + bigDecimal2
          case (2, setWithSum) => setWithSum.head ==~ bigDecimal2 + bigDecimal3 + bigDecimal4
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        _ <- Ns.bigDecimals.query.get.map(_ ==> List(Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)))
        _ <- Ns.bigDecimals(median).query.get.map(_.head ==~ (bigDecimal2 + bigDecimal3).toDouble / 2.0)

        _ <- Ns.i.a1.bigDecimals.query.get.map(_ ==> List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3, bigDecimal4)),
        ))
        _ <- Ns.i.bigDecimals(median).query.get.map(_.map {
          case (1, median) => median ==~ (bigDecimal1 + bigDecimal2).toDouble / 2.0
          case (2, median) => median ==~ bigDecimal3.toString.toDouble
        })
      } yield ()
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        _ <- Ns.bigDecimals.query.get.map(_ ==> List(Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)))
        _ <- Ns.bigDecimals(avg).query.get.map(_.head ==~ (bigDecimal1 + bigDecimal2 + bigDecimal3 + bigDecimal4).toDouble / 4.0)

        _ <- Ns.i.a1.bigDecimals.query.get.map(_ ==> List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3, bigDecimal4)),
        ))
        _ <- Ns.i.bigDecimals(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (bigDecimal1 + bigDecimal2).toDouble / 2.0
          case (2, avg) => avg ==~ (bigDecimal2 + bigDecimal3 + bigDecimal4).toDouble / 3.0
        })
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        _ <- Ns.bigDecimals.query.get.map(_ ==> List(Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)))
        _ <- Ns.bigDecimals(variance).query.get.map(_.head ==~ varianceOf(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4))

        _ <- Ns.i.a1.bigDecimals.query.get.map(_ ==> List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3, bigDecimal4)),
        ))
        _ <- Ns.i.bigDecimals(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(bigDecimal1, bigDecimal2)
          case (2, variance) => variance ==~ varianceOf(bigDecimal2, bigDecimal3, bigDecimal4)
        })
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        _ <- Ns.bigDecimals.query.get.map(_ ==> List(Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)))
        _ <- Ns.bigDecimals(stddev).query.get.map(_.head ==~ stdDevOf(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4))

        _ <- Ns.i.a1.bigDecimals.query.get.map(_ ==> List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3, bigDecimal4)),
        ))
        _ <- Ns.i.bigDecimals(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(bigDecimal1, bigDecimal2)
          case (2, stddev) => stddev ==~ stdDevOf(bigDecimal2, bigDecimal3, bigDecimal4)
        })
      } yield ()
    }
  }
}