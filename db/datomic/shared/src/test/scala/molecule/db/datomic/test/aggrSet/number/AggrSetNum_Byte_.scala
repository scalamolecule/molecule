// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.number

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSetNum_Byte_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      Ns.n.bytes.insert(List(
        (1, Set(byte1, byte2)),
        (2, Set(byte2, byte3)),
        (2, Set(byte3, byte4)),
        (2, Set(byte3, byte4)),
      )).transact

      Ns.bytes.apply(sum).query.get ==> List(
        Set(10) // byte1 + byte2 + byte3 + byte4
      )
      Ns.n.bytes(sum).query.get ==> List(
        (1, Set(3)), // byte1 + byte2
        (2, Set(9)), // byte2 + byte3 + byte4
      )
    }


    "median" - types { implicit futConn =>
      Ns.n.bytes.insert(List(
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
      Ns.bytes(median).query.get ==> List(
        Set(2.0)
      )
      Ns.n.bytes(median).query.get ==> List(
        (1, Set(1.0)),
        (2, Set(3.0)),
      )
    }


    "avg" - types { implicit conn =>
      Ns.n.bytes.insert(List(
        (1, Set(byte1, byte2)),
        (2, Set(byte2, byte3)),
        (2, Set(byte3, byte4)),
        (2, Set(byte3, byte4)),
      )).transact

      Ns.bytes(avg).query.get ==> List(
        Set(2.5)
      )
      Ns.n.bytes(avg).query.get ==> List(
        (1, Set(1.5)), // (byte1 + byte2) / 2.0
        (2, Set(3.0)), // (byte2 + byte3 + byte4) / 3.0
      )
    }


    "variance" - types { implicit conn =>
      Ns.n.bytes.insert(List(
        (1, Set(byte1, byte2)),
        (2, Set(byte2, byte3)),
        (2, Set(byte3, byte4)),
        (2, Set(byte3, byte4)),
      )).transact

      Ns.bytes(variance).query.get ==> List(
        Set(1.25)
      )
      Ns.n.bytes(variance).query.get ==> List(
        (1, Set(0.25)),
        (2, Set(0.6666666666666666)),
      )
    }


    "stddev" - types { implicit conn =>
      Ns.n.bytes.insert(List(
        (1, Set(byte1, byte2)),
        (2, Set(byte2, byte3)),
        (2, Set(byte3, byte4)),
        (2, Set(byte3, byte4)),
      )).transact

      Ns.bytes(stddev).query.get ==> List(
        Set(1.118033988749895)
      )
      Ns.n.bytes(stddev).query.get ==> List(
        (1, Set(0.5)),
        (2, Set(0.816496580927726)),
      )
    }
  }
}