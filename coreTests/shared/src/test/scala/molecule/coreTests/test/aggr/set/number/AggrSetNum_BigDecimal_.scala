// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.set.number

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetNum_BigDecimal_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        _ <- Ns.bigDecimals.apply(sum).query.get.map(_ === List(
          Set(bigDecimal1 + bigDecimal2 + bigDecimal3 + bigDecimal4)
        ))
        _ <- Ns.i.bigDecimals(sum).query.get.map(_ === List(
          (1, Set(bigDecimal1 + bigDecimal2)),
          (2, Set(bigDecimal2 + bigDecimal3 + bigDecimal4)),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        _ <- Ns.bigDecimals(median).query.get.map(_ === List(
          Set(bigDecimal2)
        ))
        _ <- Ns.i.bigDecimals(median).query.get.map(_ === List(
          (1, Set(bigDecimal1)),
          (2, Set(3.0)),
          // OBS! Datomic rounds down to nearest whole number
          // (when calculating the median for multiple numbers)!
          // This is another semantic than described on wikipedia:
          // https://en.wikipedia.org/wiki/Median
          // See also
          // https://forum.datomic.com/t/unexpected-median-rounding/517
        ))
      } yield ()
    }


    "avg" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        _ <- Ns.bigDecimals(avg).query.get.map(_ === List(
          Set(averageOf(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4))
        ))
        _ <- Ns.i.bigDecimals(avg).query.get.map(_ === List(
          (1, Set(averageOf(bigDecimal1, bigDecimal2))),
          (2, Set(averageOf(bigDecimal2, bigDecimal3, bigDecimal4))),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        _ <- Ns.bigDecimals(variance).query.get.map(_ === List(
          Set(varianceOf(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4))
        ))
        _ <- Ns.i.bigDecimals(variance).query.get.map(_ === List(
          (1, Set(varianceOf(bigDecimal1, bigDecimal2))),
          (2, Set(varianceOf(bigDecimal2, bigDecimal3, bigDecimal4))),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        _ <- Ns.bigDecimals(stddev).query.get.map(_ === List(
          Set(stdDevOf(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4))
        ))
        _ <- Ns.i.bigDecimals(stddev).query.get.map(_ === List(
          (1, Set(stdDevOf(bigDecimal1, bigDecimal2))),
          (2, Set(stdDevOf(bigDecimal2, bigDecimal3, bigDecimal4))),
        ))
      } yield ()
    }
  }
}