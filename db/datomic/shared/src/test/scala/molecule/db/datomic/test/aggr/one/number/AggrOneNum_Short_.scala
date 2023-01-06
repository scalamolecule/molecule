// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.one.number

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import org.scalactic.TripleEquals._
import utest._


object AggrOneNum_Short_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.short.insert(List(
          (1, short1),
          (2, short2),
          (2, short4),
        )).transact

        // Using === for tolerant precision comparison
        // (only necessary on JS platform with JavaScript imprecision)
        _ <- Ns.short(sum).query.get.map(_ === List(
          short1 + short2 + short4
        ))
        _ <- Ns.i.short(sum).query.get.map(_ === List(
          (1, short1),
          (2, short2 + short4),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.short.insert(List(
          (1, short1),
          (2, short2),
          (2, short4),
        )).transact

        _ <- Ns.short(median).query.get.map(_ === List(
          short2
        ))
        _ <- Ns.i.short(median).query.get.map(_ === List(
          (1, short1),
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
        _ <- Ns.i.short.insert(List(
          (1, short1),
          (2, short2),
          (2, short4),
        )).transact

        _ <- Ns.short(avg).query.get.map(_ === List(
          averageOf(short1, short2, short4)
        ))
        _ <- Ns.i.short(avg).query.get.map(_ === List(
          (1, averageOf(short1)),
          (2, averageOf(short2, short4)),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.short.insert(List(
          (1, short1),
          (2, short2),
          (2, short4),
        )).transact

        _ <- Ns.short(variance).query.get.map(_ === List(
          varianceOf(short1, short2, short4)
        ))
        _ <- Ns.i.short(variance).query.get.map(_ === List(
          (1, varianceOf(short1)),
          (2, varianceOf(short2, short4)),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.short.insert(List(
          (1, short1),
          (2, short2),
          (2, short4),
        )).transact

        _ <- Ns.short(stddev).query.get.map(_ === List(
          stdDevOf(short1, short2, short4)
        ))
        _ <- Ns.i.short(stddev).query.get.map(_ === List(
          (1, stdDevOf(short1)),
          (2, stdDevOf(short2, short4)),
        ))
      } yield ()
    }
  }
}