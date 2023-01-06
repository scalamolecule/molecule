// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.set.number

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSetNum_Long_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.longs.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact

        _ <- Ns.longs.apply(sum).query.get.map(_ === List(
          Set(long1 + long2 + long3 + long4)
        ))
        _ <- Ns.i.longs(sum).query.get.map(_ === List(
          (1, Set(long1 + long2)),
          (2, Set(long2 + long3 + long4)),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.longs.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact

        _ <- Ns.longs(median).query.get.map(_ === List(
          Set(long2)
        ))
        _ <- Ns.i.longs(median).query.get.map(_ === List(
          (1, Set(long1)),
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
        _ <- Ns.i.longs.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact

        _ <- Ns.longs(avg).query.get.map(_ === List(
          Set(averageOf(long1, long2, long3, long4))
        ))
        _ <- Ns.i.longs(avg).query.get.map(_ === List(
          (1, Set(averageOf(long1, long2))),
          (2, Set(averageOf(long2, long3, long4))),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.longs.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact

        _ <- Ns.longs(variance).query.get.map(_ === List(
          Set(varianceOf(long1, long2, long3, long4))
        ))
        _ <- Ns.i.longs(variance).query.get.map(_ === List(
          (1, Set(varianceOf(long1, long2))),
          (2, Set(varianceOf(long2, long3, long4))),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.longs.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact

        _ <- Ns.longs(stddev).query.get.map(_ === List(
          Set(stdDevOf(long1, long2, long3, long4))
        ))
        _ <- Ns.i.longs(stddev).query.get.map(_ === List(
          (1, Set(stdDevOf(long1, long2))),
          (2, Set(stdDevOf(long2, long3, long4))),
        ))
      } yield ()
    }
  }
}