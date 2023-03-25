// GENERATED CODE ********************************
package molecule.datomic.test.aggr.one.number

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import org.scalactic.TripleEquals._
import utest._


object AggrOneNum_Double_ extends DatomicTestSuite {


  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.double.insert(List(
          (1, double1),
          (2, double2),
          (2, double4),
        )).transact

        // Using === for tolerant precision comparison
        // (only necessary on JS platform with JavaScript imprecision)
        _ <- Ns.double(sum).query.get.map(_ === List(
          double1 + double2 + double4
        ))
        _ <- Ns.i.double(sum).query.get.map(_ === List(
          (1, double1),
          (2, double2 + double4),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.double.insert(List(
          (1, double1),
          (2, double2),
          (2, double4),
        )).transact

        _ <- Ns.double(median).query.get.map(_ === List(
          double2
        ))
        _ <- Ns.i.double(median).query.get.map(_ === List(
          (1, double1),
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
        _ <- Ns.i.double.insert(List(
          (1, double1),
          (2, double2),
          (2, double4),
        )).transact

        _ <- Ns.double(avg).query.get.map(_ === List(
          averageOf(double1, double2, double4)
        ))
        _ <- Ns.i.double(avg).query.get.map(_ === List(
          (1, averageOf(double1)),
          (2, averageOf(double2, double4)),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.double.insert(List(
          (1, double1),
          (2, double2),
          (2, double4),
        )).transact

        _ <- Ns.double(variance).query.get.map(_ === List(
          varianceOf(double1, double2, double4)
        ))
        _ <- Ns.i.double(variance).query.get.map(_ === List(
          (1, varianceOf(double1)),
          (2, varianceOf(double2, double4)),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.double.insert(List(
          (1, double1),
          (2, double2),
          (2, double4),
        )).transact

        _ <- Ns.double(stddev).query.get.map(_ === List(
          stdDevOf(double1, double2, double4)
        ))
        _ <- Ns.i.double(stddev).query.get.map(_ === List(
          (1, stdDevOf(double1)),
          (2, stdDevOf(double2, double4)),
        ))
      } yield ()
    }
  }
}