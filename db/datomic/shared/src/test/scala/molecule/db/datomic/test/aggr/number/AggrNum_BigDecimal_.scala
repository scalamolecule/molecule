// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.number

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrNum_BigDecimal_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - cardOne { implicit conn =>
      One.n.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal3),
      )).transact

      One.bigDecimal(sum).query.get.head ==> (bigDecimal1 + bigDecimal2 + bigDecimal3)
      One.n.bigDecimal(sum).query.get ==> List(
        (1, bigDecimal1),
        (2, bigDecimal2 + bigDecimal3),
      )
    }


    "median" - cardOne { implicit futConn =>
      One.n.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal4),
      )).transact

      // OBS! Datomic rounds down to nearest whole number
      // (when calculating the median for multiple numbers)!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517

      // Card-one
      One.bigDecimal(median).query.get.head ==> bigDecimal2
      One.n.bigDecimal(median).query.get ==> List(
        (1, bigDecimal1),
        (2, 3.0),
      )
    }

    "avg" - cardOne { implicit conn =>
      One.n.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal4),
      )).transact

      One.bigDecimal(avg).query.get.head ==> ((bigDecimal1 + bigDecimal2 + bigDecimal4) / 3.0).toDouble
      One.n.bigDecimal(avg).query.get ==> List(
        (1, bigDecimal1 / 1.0),
        (2, (bigDecimal2 + bigDecimal4) / 2.0),
      )
    }


    "variance" - cardOne { implicit conn =>
      One.n.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal4),
      )).transact

      One.bigDecimal(variance).query.get.head ==> 1.8822222222222225
      One.n.bigDecimal(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.2100000000000002),
      )
    }


    "stddev" - cardOne { implicit conn =>
      One.n.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal4),
      )).transact

      One.bigDecimal(stddev).query.get.head ==> 1.3719410418171119
      One.n.bigDecimal(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.1),
      )
    }
  }
}