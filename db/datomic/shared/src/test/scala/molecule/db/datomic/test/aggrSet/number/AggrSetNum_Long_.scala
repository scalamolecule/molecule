// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.number

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSetNum_Long_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      Ns.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact

      Ns.longs.apply(sum).query.get ==> List(
        Set(10) // long1 + long2 + long3 + long4
      )
      Ns.n.longs(sum).query.get ==> List(
        (1, Set(3)), // long1 + long2
        (2, Set(9)), // long2 + long3 + long4
      )
    }


    "median" - types { implicit futConn =>
      Ns.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact

      // OBS! Datomic rounds down to nearest whole number
      // (when calculating the median for multiple numbers)!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517
      Ns.longs(median).query.get ==> List(
        Set(2.0)
      )
      Ns.n.longs(median).query.get ==> List(
        (1, Set(1.0)),
        (2, Set(3.0)),
      )
    }


    "avg" - types { implicit conn =>
      Ns.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact

      Ns.longs(avg).query.get ==> List(
        Set(2.5)
      )
      Ns.n.longs(avg).query.get ==> List(
        (1, Set(1.5)), // (long1 + long2) / 2.0
        (2, Set(3.0)), // (long2 + long3 + long4) / 3.0
      )
    }


    "variance" - types { implicit conn =>
      Ns.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact

      Ns.longs(variance).query.get ==> List(
        Set(1.25)
      )
      Ns.n.longs(variance).query.get ==> List(
        (1, Set(0.25)),
        (2, Set(0.6666666666666666)),
      )
    }


    "stddev" - types { implicit conn =>
      Ns.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact

      Ns.longs(stddev).query.get ==> List(
        Set(1.118033988749895)
      )
      Ns.n.longs(stddev).query.get ==> List(
        (1, Set(0.5)),
        (2, Set(0.816496580927726)),
      )
    }
  }
}