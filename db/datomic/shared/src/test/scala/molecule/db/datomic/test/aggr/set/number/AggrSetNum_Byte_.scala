// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.set.number

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
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

        _ <- Ns.bytes.apply(sum).query.get.map(_ ==> List(
          Set(10) // byte1 + byte2 + byte3 + byte4
        ))
        _ <- Ns.i.bytes(sum).query.get.map(_ ==> List(
          (1, Set(3)), // byte1 + byte2
          (2, Set(9)), // byte2 + byte3 + byte4
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

        // OBS! Datomic rounds down to nearest whole number
        // (when calculating the median for multiple numbers)!
        // This is another semantic than described on wikipedia:
        // https://en.wikipedia.org/wiki/Median
        // See also
        // https://forum.datomic.com/t/unexpected-median-rounding/517
        _ <- Ns.bytes(median).query.get.map(_ ==> List(
          Set(2.0)
        ))
        _ <- Ns.i.bytes(median).query.get.map(_ ==> List(
          (1, Set(1.0)),
          (2, Set(3.0)),
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

        _ <- Ns.bytes(avg).query.get.map(_ ==> List(
          Set(2.5)
        ))
        _ <- Ns.i.bytes(avg).query.get.map(_ ==> List(
          (1, Set(1.5)), // (byte1 + byte2) / 2.0
          (2, Set(3.0)), // (byte2 + byte3 + byte4) / 3.0
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

        _ <- Ns.bytes(variance).query.get.map(_ ==> List(
          Set(1.25)
        ))
        _ <- Ns.i.bytes(variance).query.get.map(_ ==> List(
          (1, Set(0.25)),
          (2, Set(0.6666666666666666)),
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

        _ <- Ns.bytes(stddev).query.get.map(_ ==> List(
          Set(1.118033988749895)
        ))
        _ <- Ns.i.bytes(stddev).query.get.map(_ ==> List(
          (1, Set(0.5)),
          (2, Set(0.816496580927726)),
        ))
      } yield ()
    }
  }
}