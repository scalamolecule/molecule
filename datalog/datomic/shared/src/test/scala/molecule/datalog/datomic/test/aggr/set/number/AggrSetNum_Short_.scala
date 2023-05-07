// GENERATED CODE ********************************
package molecule.datalog.datomic.test.aggr.set.number

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._

object AggrSetNum_Short_ extends DatomicTestSuite {


  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.shorts.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact

        _ <- Ns.shorts.apply(sum).query.get.map(_ === List(
          Set(short1 + short2 + short3 + short4)
        ))
        _ <- Ns.i.shorts(sum).query.get.map(_ === List(
          (1, Set(short1 + short2)),
          (2, Set(short2 + short3 + short4)),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.shorts.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact

        _ <- Ns.shorts(median).query.get.map(_ === List(
          Set(short2)
        ))
        _ <- Ns.i.shorts(median).query.get.map(_ === List(
          (1, Set(short1)),
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
        _ <- Ns.i.shorts.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact

        _ <- Ns.shorts(avg).query.get.map(_ === List(
          Set(averageOf(short1, short2, short3, short4))
        ))
        _ <- Ns.i.shorts(avg).query.get.map(_ === List(
          (1, Set(averageOf(short1, short2))),
          (2, Set(averageOf(short2, short3, short4))),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.shorts.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact

        _ <- Ns.shorts(variance).query.get.map(_ === List(
          Set(varianceOf(short1, short2, short3, short4))
        ))
        _ <- Ns.i.shorts(variance).query.get.map(_ === List(
          (1, Set(varianceOf(short1, short2))),
          (2, Set(varianceOf(short2, short3, short4))),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.shorts.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact

        _ <- Ns.shorts(stddev).query.get.map(_ === List(
          Set(stdDevOf(short1, short2, short3, short4))
        ))
        _ <- Ns.i.shorts(stddev).query.get.map(_ === List(
          (1, Set(stdDevOf(short1, short2))),
          (2, Set(stdDevOf(short2, short3, short4))),
        ))
      } yield ()
    }
  }
}