// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.number

import molecule.coreTests.dataModels.core.types.dsl.CardSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSetNum_Long_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - cardSet { implicit conn =>
      NsSet.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact

      NsSet.longs.apply(sum).query.get ==> List(
        Set(10) // long1 + long2 + long3 + long4
      )
      NsSet.n.longs(sum).query.get ==> List(
        (1, Set(3)), // long1 + long2
        (2, Set(9)), // long2 + long3 + long4
      )
    }


    "median" - cardSet { implicit futConn =>
      NsSet.n.longs.insert(List(
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
      NsSet.longs(median).query.get ==> List(
        Set(2.0)
      )
      NsSet.n.longs(median).query.get ==> List(
        (1, Set(1.0)),
        (2, Set(3.0)),
      )
    }


    "avg" - cardSet { implicit conn =>
      NsSet.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact

      NsSet.longs(avg).query.get ==> List(
        Set(2.5)
      )
      NsSet.n.longs(avg).query.get ==> List(
        (1, Set(1.5)), // (long1 + long2) / 2.0
        (2, Set(3.0)), // (long2 + long3 + long4) / 3.0
      )
    }


    "variance" - cardSet { implicit conn =>
      NsSet.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact

      NsSet.longs(variance).query.get ==> List(
        Set(1.25)
      )
      NsSet.n.longs(variance).query.get ==> List(
        (1, Set(0.25)),
        (2, Set(0.6666666666666666)),
      )
    }


    "stddev" - cardSet { implicit conn =>
      NsSet.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact

      NsSet.longs(stddev).query.get ==> List(
        Set(1.118033988749895)
      )
      NsSet.n.longs(stddev).query.get ==> List(
        (1, Set(0.5)),
        (2, Set(0.816496580927726)),
      )
    }
  }
}