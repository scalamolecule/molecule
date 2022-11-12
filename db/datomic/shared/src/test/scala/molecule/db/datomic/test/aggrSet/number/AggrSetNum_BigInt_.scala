// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.number

import molecule.coreTests.dataModels.core.types.dsl.TypesSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSetNum_BigInt_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - typesSet { implicit conn =>
      NsSet.n.bigInts.insert(List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3)),
        (2, Set(bigInt3, bigInt4)),
        (2, Set(bigInt3, bigInt4)),
      )).transact

      NsSet.bigInts.apply(sum).query.get ==> List(
        Set(10) // bigInt1 + bigInt2 + bigInt3 + bigInt4
      )
      NsSet.n.bigInts(sum).query.get ==> List(
        (1, Set(3)), // bigInt1 + bigInt2
        (2, Set(9)), // bigInt2 + bigInt3 + bigInt4
      )
    }


    "median" - typesSet { implicit futConn =>
      NsSet.n.bigInts.insert(List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3)),
        (2, Set(bigInt3, bigInt4)),
        (2, Set(bigInt3, bigInt4)),
      )).transact

      // OBS! Datomic rounds down to nearest whole number
      // (when calculating the median for multiple numbers)!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517
      NsSet.bigInts(median).query.get ==> List(
        Set(2.0)
      )
      NsSet.n.bigInts(median).query.get ==> List(
        (1, Set(1.0)),
        (2, Set(3.0)),
      )
    }


    "avg" - typesSet { implicit conn =>
      NsSet.n.bigInts.insert(List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3)),
        (2, Set(bigInt3, bigInt4)),
        (2, Set(bigInt3, bigInt4)),
      )).transact

      NsSet.bigInts(avg).query.get ==> List(
        Set(2.5)
      )
      NsSet.n.bigInts(avg).query.get ==> List(
        (1, Set(1.5)), // (bigInt1 + bigInt2) / 2.0
        (2, Set(3.0)), // (bigInt2 + bigInt3 + bigInt4) / 3.0
      )
    }


    "variance" - typesSet { implicit conn =>
      NsSet.n.bigInts.insert(List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3)),
        (2, Set(bigInt3, bigInt4)),
        (2, Set(bigInt3, bigInt4)),
      )).transact

      NsSet.bigInts(variance).query.get ==> List(
        Set(1.25)
      )
      NsSet.n.bigInts(variance).query.get ==> List(
        (1, Set(0.25)),
        (2, Set(0.6666666666666666)),
      )
    }


    "stddev" - typesSet { implicit conn =>
      NsSet.n.bigInts.insert(List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3)),
        (2, Set(bigInt3, bigInt4)),
        (2, Set(bigInt3, bigInt4)),
      )).transact

      NsSet.bigInts(stddev).query.get ==> List(
        Set(1.118033988749895)
      )
      NsSet.n.bigInts(stddev).query.get ==> List(
        (1, Set(0.5)),
        (2, Set(0.816496580927726)),
      )
    }
  }
}