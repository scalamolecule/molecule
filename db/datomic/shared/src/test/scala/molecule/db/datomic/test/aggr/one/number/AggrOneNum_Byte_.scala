// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.one.number

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import org.scalactic.TripleEquals._
import utest._


object AggrOneNum_Byte_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.byte.insert(List(
          (1, byte1),
          (2, byte2),
          (2, byte4),
        )).transact

        // Using === for tolerant precision comparison
        // (only necessary on JS platform with JavaScript imprecision)
        _ <- Ns.byte(sum).query.get.map(_ === List(
          byte1 + byte2 + byte4
        ))
        _ <- Ns.i.byte(sum).query.get.map(_ === List(
          (1, byte1),
          (2, byte2 + byte4),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.byte.insert(List(
          (1, byte1),
          (2, byte2),
          (2, byte4),
        )).transact

        _ <- Ns.byte(median).query.get.map(_ === List(
          byte2
        ))
        _ <- Ns.i.byte(median).query.get.map(_ === List(
          (1, byte1),
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
        _ <- Ns.i.byte.insert(List(
          (1, byte1),
          (2, byte2),
          (2, byte4),
        )).transact

        _ <- Ns.byte(avg).query.get.map(_ === List(
          averageOf(byte1, byte2, byte4)
        ))
        _ <- Ns.i.byte(avg).query.get.map(_ === List(
          (1, averageOf(byte1)),
          (2, averageOf(byte2, byte4)),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.byte.insert(List(
          (1, byte1),
          (2, byte2),
          (2, byte4),
        )).transact

        _ <- Ns.byte(variance).query.get.map(_ === List(
          varianceOf(byte1, byte2, byte4)
        ))
        _ <- Ns.i.byte(variance).query.get.map(_ === List(
          (1, varianceOf(byte1)),
          (2, varianceOf(byte2, byte4)),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.byte.insert(List(
          (1, byte1),
          (2, byte2),
          (2, byte4),
        )).transact

        _ <- Ns.byte(stddev).query.get.map(_ === List(
          stdDevOf(byte1, byte2, byte4)
        ))
        _ <- Ns.i.byte(stddev).query.get.map(_ === List(
          (1, stdDevOf(byte1)),
          (2, stdDevOf(byte2, byte4)),
        ))
      } yield ()
    }
  }
}