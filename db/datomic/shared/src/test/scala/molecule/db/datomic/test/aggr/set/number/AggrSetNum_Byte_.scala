// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.set.number

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic.async._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSetNum_Byte_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.bytes.insert(List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2, byte3)),
          (2, Set(byte3, byte4)),
          (2, Set(byte3, byte4)),
        )).transact

        _ <- Ns.bytes.apply(sum).query.get.map(_ === List(
          Set(byte1 + byte2 + byte3 + byte4)
        ))
        _ <- Ns.i.bytes(sum).query.get.map(_ === List(
          (1, Set(byte1 + byte2)),
          (2, Set(byte2 + byte3 + byte4)),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.bytes.insert(List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2, byte3)),
          (2, Set(byte3, byte4)),
          (2, Set(byte3, byte4)),
        )).transact

        _ <- Ns.bytes(median).query.get.map(_ === List(
          Set(byte2)
        ))
        _ <- Ns.i.bytes(median).query.get.map(_ === List(
          (1, Set(byte1)),
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
        _ <- Ns.i.bytes.insert(List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2, byte3)),
          (2, Set(byte3, byte4)),
          (2, Set(byte3, byte4)),
        )).transact

        _ <- Ns.bytes(avg).query.get.map(_ === List(
          Set(averageOf(byte1, byte2, byte3, byte4))
        ))
        _ <- Ns.i.bytes(avg).query.get.map(_ === List(
          (1, Set(averageOf(byte1, byte2))),
          (2, Set(averageOf(byte2, byte3, byte4))),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.bytes.insert(List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2, byte3)),
          (2, Set(byte3, byte4)),
          (2, Set(byte3, byte4)),
        )).transact

        _ <- Ns.bytes(variance).query.get.map(_ === List(
          Set(varianceOf(byte1, byte2, byte3, byte4))
        ))
        _ <- Ns.i.bytes(variance).query.get.map(_ === List(
          (1, Set(varianceOf(byte1, byte2))),
          (2, Set(varianceOf(byte2, byte3, byte4))),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.bytes.insert(List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2, byte3)),
          (2, Set(byte3, byte4)),
          (2, Set(byte3, byte4)),
        )).transact

        _ <- Ns.bytes(stddev).query.get.map(_ === List(
          Set(stdDevOf(byte1, byte2, byte3, byte4))
        ))
        _ <- Ns.i.bytes(stddev).query.get.map(_ === List(
          (1, Set(stdDevOf(byte1, byte2))),
          (2, Set(stdDevOf(byte2, byte3, byte4))),
        ))
      } yield ()
    }
  }
}