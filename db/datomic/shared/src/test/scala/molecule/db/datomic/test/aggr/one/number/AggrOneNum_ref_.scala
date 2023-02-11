// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.one.number

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic.async._
import molecule.db.datomic.setup.DatomicTestSuite
import org.scalactic.TripleEquals._
import utest._


object AggrOneNum_ref_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.ref.insert(List(
          (1, ref1),
          (2, ref2),
          (2, ref4),
        )).transact

        // Using === for tolerant precision comparison
        // (only necessary on JS platform with JavaScript imprecision)
        _ <- Ns.ref(sum).query.get.map(_ === List(
          ref1 + ref2 + ref4
        ))
        _ <- Ns.i.ref(sum).query.get.map(_ === List(
          (1, ref1),
          (2, ref2 + ref4),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.ref.insert(List(
          (1, ref1),
          (2, ref2),
          (2, ref4),
        )).transact

        _ <- Ns.ref(median).query.get.map(_ === List(
          ref2
        ))
        _ <- Ns.i.ref(median).query.get.map(_ === List(
          (1, ref1),
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
        _ <- Ns.i.ref.insert(List(
          (1, ref1),
          (2, ref2),
          (2, ref4),
        )).transact

        _ <- Ns.ref(avg).query.get.map(_ === List(
          averageOf(ref1, ref2, ref4)
        ))
        _ <- Ns.i.ref(avg).query.get.map(_ === List(
          (1, averageOf(ref1)),
          (2, averageOf(ref2, ref4)),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.ref.insert(List(
          (1, ref1),
          (2, ref2),
          (2, ref4),
        )).transact

        _ <- Ns.ref(variance).query.get.map(_ === List(
          varianceOf(ref1, ref2, ref4)
        ))
        _ <- Ns.i.ref(variance).query.get.map(_ === List(
          (1, varianceOf(ref1)),
          (2, varianceOf(ref2, ref4)),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.ref.insert(List(
          (1, ref1),
          (2, ref2),
          (2, ref4),
        )).transact

        _ <- Ns.ref(stddev).query.get.map(_ === List(
          stdDevOf(ref1, ref2, ref4)
        ))
        _ <- Ns.i.ref(stddev).query.get.map(_ === List(
          (1, stdDevOf(ref1)),
          (2, stdDevOf(ref2, ref4)),
        ))
      } yield ()
    }
  }
}