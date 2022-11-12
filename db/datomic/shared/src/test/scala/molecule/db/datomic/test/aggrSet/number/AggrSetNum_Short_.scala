// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.number

import molecule.coreTests.dataModels.core.types.dsl.TypesSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSetNum_Short_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - typesSet { implicit conn =>
      NsSet.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact

      NsSet.shorts.apply(sum).query.get ==> List(
        Set(10) // short1 + short2 + short3 + short4
      )
      NsSet.n.shorts(sum).query.get ==> List(
        (1, Set(3)), // short1 + short2
        (2, Set(9)), // short2 + short3 + short4
      )
    }


    "median" - typesSet { implicit futConn =>
      NsSet.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact

      // OBS! Datomic rounds down to nearest whole number
      // (when calculating the median for multiple numbers)!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517
      NsSet.shorts(median).query.get ==> List(
        Set(2.0)
      )
      NsSet.n.shorts(median).query.get ==> List(
        (1, Set(1.0)),
        (2, Set(3.0)),
      )
    }


    "avg" - typesSet { implicit conn =>
      NsSet.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact

      NsSet.shorts(avg).query.get ==> List(
        Set(2.5)
      )
      NsSet.n.shorts(avg).query.get ==> List(
        (1, Set(1.5)), // (short1 + short2) / 2.0
        (2, Set(3.0)), // (short2 + short3 + short4) / 3.0
      )
    }


    "variance" - typesSet { implicit conn =>
      NsSet.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact

      NsSet.shorts(variance).query.get ==> List(
        Set(1.25)
      )
      NsSet.n.shorts(variance).query.get ==> List(
        (1, Set(0.25)),
        (2, Set(0.6666666666666666)),
      )
    }


    "stddev" - typesSet { implicit conn =>
      NsSet.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact

      NsSet.shorts(stddev).query.get ==> List(
        Set(1.118033988749895)
      )
      NsSet.n.shorts(stddev).query.get ==> List(
        (1, Set(0.5)),
        (2, Set(0.816496580927726)),
      )
    }
  }
}