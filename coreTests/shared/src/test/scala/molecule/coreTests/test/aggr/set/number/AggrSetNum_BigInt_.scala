// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.set.number

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetNum_BigInt_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        _ <- Ns.bigInts.apply(sum).query.get.map(_ === List(
          Set(bigInt1 + bigInt2 + bigInt3 + bigInt4)
        ))
        _ <- Ns.i.bigInts(sum).query.get.map(_ === List(
          (1, Set(bigInt1 + bigInt2)),
          (2, Set(bigInt2 + bigInt3 + bigInt4)),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        _ <- Ns.bigInts(median).query.get.map(_ === List(
          Set(bigInt2)
        ))
        _ <- Ns.i.bigInts(median).query.get.map(_ === List(
          (1, Set(bigInt1)),
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
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        _ <- Ns.bigInts(avg).query.get.map(_ === List(
          Set(averageOf(bigInt1, bigInt2, bigInt3, bigInt4))
        ))
        _ <- Ns.i.bigInts(avg).query.get.map(_ === List(
          (1, Set(averageOf(bigInt1, bigInt2))),
          (2, Set(averageOf(bigInt2, bigInt3, bigInt4))),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        _ <- Ns.bigInts(variance).query.get.map(_ === List(
          Set(varianceOf(bigInt1, bigInt2, bigInt3, bigInt4))
        ))
        _ <- Ns.i.bigInts(variance).query.get.map(_ === List(
          (1, Set(varianceOf(bigInt1, bigInt2))),
          (2, Set(varianceOf(bigInt2, bigInt3, bigInt4))),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        _ <- Ns.bigInts(stddev).query.get.map(_ === List(
          Set(stdDevOf(bigInt1, bigInt2, bigInt3, bigInt4))
        ))
        _ <- Ns.i.bigInts(stddev).query.get.map(_ === List(
          (1, Set(stdDevOf(bigInt1, bigInt2))),
          (2, Set(stdDevOf(bigInt2, bigInt3, bigInt4))),
        ))
      } yield ()
    }
  }
}