// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.number

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSetNum_Short_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      Ns.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact

      Ns.shorts.apply(sum).query.get ==> List(
        Set(10) // short1 + short2 + short3 + short4
      )
      Ns.n.shorts(sum).query.get ==> List(
        (1, Set(3)), // short1 + short2
        (2, Set(9)), // short2 + short3 + short4
      )
    }


    "median" - types { implicit futConn =>
      Ns.n.shorts.insert(List(
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
      Ns.shorts(median).query.get ==> List(
        Set(2.0)
      )
      Ns.n.shorts(median).query.get ==> List(
        (1, Set(1.0)),
        (2, Set(3.0)),
      )
    }


    "avg" - types { implicit conn =>
      Ns.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact

      Ns.shorts(avg).query.get ==> List(
        Set(2.5)
      )
      Ns.n.shorts(avg).query.get ==> List(
        (1, Set(1.5)), // (short1 + short2) / 2.0
        (2, Set(3.0)), // (short2 + short3 + short4) / 3.0
      )
    }


    "variance" - types { implicit conn =>
      Ns.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact

      Ns.shorts(variance).query.get ==> List(
        Set(1.25)
      )
      Ns.n.shorts(variance).query.get ==> List(
        (1, Set(0.25)),
        (2, Set(0.6666666666666666)),
      )
    }


    "stddev" - types { implicit conn =>
      Ns.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact

      Ns.shorts(stddev).query.get ==> List(
        Set(1.118033988749895)
      )
      Ns.n.shorts(stddev).query.get ==> List(
        (1, Set(0.5)),
        (2, Set(0.816496580927726)),
      )
    }
  }
}