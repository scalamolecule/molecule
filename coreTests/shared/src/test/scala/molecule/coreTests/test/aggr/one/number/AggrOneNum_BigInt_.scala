// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.one.number

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOneNum_BigInt_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInt.insert(List(
          (1, bigInt1),
          (2, bigInt2),
          (2, bigInt4),
        )).transact

        // Using === for tolerant precision comparison
        // (only necessary on JS platform with JavaScript imprecision)
        _ <- Ns.bigInt(sum).query.get.map(_ === List(
          bigInt1 + bigInt2 + bigInt4
        ))
        _ <- Ns.i.bigInt(sum).query.get.map(_ === List(
          (1, bigInt1),
          (2, bigInt2 + bigInt4),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.bigInt.insert(List(
          (1, bigInt1),
          (2, bigInt2),
          (2, bigInt4),
        )).transact

        _ <- Ns.bigInt(median).query.get.map(_ === List(
          bigInt2
        ))
        _ <- Ns.i.bigInt(median).query.get.map(_ === List(
          (1, bigInt1),
          (2, 3.0),
          // OBS! Datomic rounds down to nearest whole number
          // when calculating the median for multiple numbers!
          // This is another semantic than described on wikipedia:
          // https://en.wikipedia.org/wiki/Median
          // See also
          // https://forum.datomic.com/t/unexpected-median-rounding/517
        ))
      } yield ()
    }


    "avg" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInt.insert(List(
          (1, bigInt1),
          (2, bigInt2),
          (2, bigInt4),
        )).transact

        _ <- Ns.bigInt(avg).query.get.map(_ === List(
          averageOf(bigInt1, bigInt2, bigInt4)
        ))
        _ <- Ns.i.bigInt(avg).query.get.map(_ === List(
          (1, averageOf(bigInt1)),
          (2, averageOf(bigInt2, bigInt4)),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInt.insert(List(
          (1, bigInt1),
          (2, bigInt2),
          (2, bigInt4),
        )).transact

        _ <- Ns.bigInt(variance).query.get.map(_ === List(
          varianceOf(bigInt1, bigInt2, bigInt4)
        ))
        _ <- Ns.i.bigInt(variance).query.get.map(_ === List(
          (1, varianceOf(bigInt1)),
          (2, varianceOf(bigInt2, bigInt4)),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInt.insert(List(
          (1, bigInt1),
          (2, bigInt2),
          (2, bigInt4),
        )).transact

        _ <- Ns.bigInt(stddev).query.get.map(_ === List(
          stdDevOf(bigInt1, bigInt2, bigInt4)
        ))
        _ <- Ns.i.bigInt(stddev).query.get.map(_ === List(
          (1, stdDevOf(bigInt1)),
          (2, stdDevOf(bigInt2, bigInt4)),
        ))
      } yield ()
    }
  }
}