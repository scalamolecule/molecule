// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.one.number

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOneNum_Long_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.long.insert(List(
          (1, long1),
          (2, long2),
          (2, long4),
        )).transact

        // Using === for tolerant precision comparison
        // (only necessary on JS platform with JavaScript imprecision)
        _ <- Ns.long(sum).query.get.map(_ === List(
          long1 + long2 + long4
        ))
        _ <- Ns.i.long(sum).query.get.map(_ === List(
          (1, long1),
          (2, long2 + long4),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.long.insert(List(
          (1, long1),
          (2, long2),
          (2, long4),
        )).transact

        _ <- Ns.long(median).query.get.map(_ === List(
          long2
        ))
        _ <- Ns.i.long(median).query.get.map(_ === List(
          (1, long1),
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
        _ <- Ns.i.long.insert(List(
          (1, long1),
          (2, long2),
          (2, long4),
        )).transact

        _ <- Ns.long(avg).query.get.map(_ === List(
          averageOf(long1, long2, long4)
        ))
        _ <- Ns.i.long(avg).query.get.map(_ === List(
          (1, averageOf(long1)),
          (2, averageOf(long2, long4)),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.long.insert(List(
          (1, long1),
          (2, long2),
          (2, long4),
        )).transact

        _ <- Ns.long(variance).query.get.map(_ === List(
          varianceOf(long1, long2, long4)
        ))
        _ <- Ns.i.long(variance).query.get.map(_ === List(
          (1, varianceOf(long1)),
          (2, varianceOf(long2, long4)),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.long.insert(List(
          (1, long1),
          (2, long2),
          (2, long4),
        )).transact

        _ <- Ns.long(stddev).query.get.map(_ === List(
          stdDevOf(long1, long2, long4)
        ))
        _ <- Ns.i.long(stddev).query.get.map(_ === List(
          (1, stdDevOf(long1)),
          (2, stdDevOf(long2, long4)),
        ))
      } yield ()
    }
  }
}