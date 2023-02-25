// GENERATED CODE ********************************
package molecule.datomic.test.aggr.set.number

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._

object AggrSetNum_Double_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        _ <- Ns.doubles.apply(sum).query.get.map(_ === List(
          Set(double1 + double2 + double3 + double4)
        ))
        _ <- Ns.i.doubles(sum).query.get.map(_ === List(
          (1, Set(double1 + double2)),
          (2, Set(double2 + double3 + double4)),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        _ <- Ns.doubles(median).query.get.map(_ === List(
          Set(double2)
        ))
        _ <- Ns.i.doubles(median).query.get.map(_ === List(
          (1, Set(double1)),
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
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        _ <- Ns.doubles(avg).query.get.map(_ === List(
          Set(averageOf(double1, double2, double3, double4))
        ))
        _ <- Ns.i.doubles(avg).query.get.map(_ === List(
          (1, Set(averageOf(double1, double2))),
          (2, Set(averageOf(double2, double3, double4))),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        _ <- Ns.doubles(variance).query.get.map(_ === List(
          Set(varianceOf(double1, double2, double3, double4))
        ))
        _ <- Ns.i.doubles(variance).query.get.map(_ === List(
          (1, Set(varianceOf(double1, double2))),
          (2, Set(varianceOf(double2, double3, double4))),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        _ <- Ns.doubles(stddev).query.get.map(_ === List(
          Set(stdDevOf(double1, double2, double3, double4))
        ))
        _ <- Ns.i.doubles(stddev).query.get.map(_ === List(
          (1, Set(stdDevOf(double1, double2))),
          (2, Set(stdDevOf(double2, double3, double4))),
        ))
      } yield ()
    }
  }
}