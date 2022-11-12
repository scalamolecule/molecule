package molecule.db.datomic.test.aggrSet.number

import molecule.coreTests.dataModels.core.types.dsl.TypesSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSetNum_Int extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - typesSet { implicit conn =>
      NsSet.n.ints.insert(List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3)),
        (2, Set(int3, int4)),
        (2, Set(int3, int4)),
      )).transact

      NsSet.ints.apply(sum).query.get ==> List(
        Set(10) // int1 + int2 + int3 + int4
      )
      NsSet.n.ints(sum).query.get ==> List(
        (1, Set(3)), // int1 + int2
        (2, Set(9)), // int2 + int3 + int4
      )
    }


    "median" - typesSet { implicit futConn =>
      NsSet.n.ints.insert(List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3)),
        (2, Set(int3, int4)),
        (2, Set(int3, int4)),
      )).transact

      // OBS! Datomic rounds down to nearest whole number
      // (when calculating the median for multiple numbers)!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517
      NsSet.ints(median).query.get ==> List(
        Set(2.0)
      )
      NsSet.n.ints(median).query.get ==> List(
        (1, Set(1.0)),
        (2, Set(3.0)),
      )
    }


    "avg" - typesSet { implicit conn =>
      NsSet.n.ints.insert(List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3)),
        (2, Set(int3, int4)),
        (2, Set(int3, int4)),
      )).transact

      NsSet.ints(avg).query.get ==> List(
        Set(2.5)
      )
      NsSet.n.ints(avg).query.get ==> List(
        (1, Set(1.5)), // (int1 + int2) / 2.0
        (2, Set(3.0)), // (int2 + int3 + int4) / 3.0
      )
    }


    "variance" - typesSet { implicit conn =>
      NsSet.n.ints.insert(List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3)),
        (2, Set(int3, int4)),
        (2, Set(int3, int4)),
      )).transact

      NsSet.ints(variance).query.get ==> List(
        Set(1.25)
      )
      NsSet.n.ints(variance).query.get ==> List(
        (1, Set(0.25)),
        (2, Set(0.6666666666666666)),
      )
    }


    "stddev" - typesSet { implicit conn =>
      NsSet.n.ints.insert(List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3)),
        (2, Set(int3, int4)),
        (2, Set(int3, int4)),
      )).transact

      NsSet.ints(stddev).query.get ==> List(
        Set(1.118033988749895)
      )
      NsSet.n.ints(stddev).query.get ==> List(
        (1, Set(0.5)),
        (2, Set(0.816496580927726)),
      )
    }
  }
}