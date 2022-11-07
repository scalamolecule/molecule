// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.number

import molecule.coreTests.dataModels.core.types.dsl.CardSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import molecule.db.datomic.test.aggrSet.number.AggrSetNum_BigDecimal_.bigDecimal1
import utest._
import scala.collection.immutable.Set

object AggrSetNum_BigDecimal_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - cardSet { implicit conn =>
      NsSet.n.bigDecimals.insert(List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal3, bigDecimal4)),
        (2, Set(bigDecimal3, bigDecimal4)),
      )).transact

      NsSet.bigDecimals.apply(sum).query.get ==> List(
        Set(11) // bigDecimal1 + bigDecimal2 + bigDecimal3 + bigDecimal4
      )
      NsSet.n.bigDecimals(sum).query.get ==> List(
        (1, Set(3.3)), // bigDecimal1 + bigDecimal2
        (2, Set(9.9)), // bigDecimal2 + bigDecimal3 + bigDecimal4
      )
    }


    "median" - cardSet { implicit futConn =>
      NsSet.n.bigDecimals.insert(List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal3, bigDecimal4)),
        (2, Set(bigDecimal3, bigDecimal4)),
      )).transact

      NsSet.bigDecimals(median).query.get ==> List(
        Set(2.0)
      )
      NsSet.n.bigDecimals(median).query.get ==> List(
        (1, Set(1.0)),
        (2, Set(3.3)),
      )
      // OBS! Datomic rounds down to nearest whole number
      // (when calculating the median for multiple numbers)!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517
    }


    "avg" - cardSet { implicit conn =>
      NsSet.n.bigDecimals.insert(List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal3, bigDecimal4)),
        (2, Set(bigDecimal3, bigDecimal4)),
      )).transact

      NsSet.bigDecimals(avg).query.get ==> List(
        Set(2.75) // (bigDecimal1 + bigDecimal2 + bigDecimal3 + bigDecimal4) / 4.0
      )
      NsSet.n.bigDecimals(avg).query.get ==> List(
        (1, Set(1.65)), // (bigDecimal1 + bigDecimal2) / 2.0
        (2, Set(3.3000000000000003)), // (bigDecimal2 + bigDecimal3 + bigDecimal4) / 3.0
      )
    }


    "variance" - cardSet { implicit conn =>
      NsSet.n.bigDecimals.insert(List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal3, bigDecimal4)),
        (2, Set(bigDecimal3, bigDecimal4)),
      )).transact

      NsSet.bigDecimals(variance).query.get ==> List(
        Set(1.5125000000000002)
      )
      NsSet.n.bigDecimals(variance).query.get ==> List(
        (1, Set(0.30250000000000005)),
        (2, Set(0.8066666666666668)),
      )
    }


    "stddev" - cardSet { implicit conn =>
      NsSet.n.bigDecimals.insert(List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal3, bigDecimal4)),
        (2, Set(bigDecimal3, bigDecimal4)),
      )).transact

      NsSet.bigDecimals(stddev).query.get ==> List(
        Set(1.2298373876248845)
      )
      NsSet.n.bigDecimals(stddev).query.get ==> List(
        (1, Set(0.55)),
        (2, Set(0.8981462390204987)),
      )
    }
  }
}