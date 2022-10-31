// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.number

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrNum_BigInt_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - cardOne { implicit conn =>
      One.n.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt3),
      )).transact

      One.bigInt(sum).query.get.head ==> (bigInt1 + bigInt2 + bigInt3)
      One.n.bigInt(sum).query.get ==> List(
        (1, bigInt1),
        (2, bigInt2 + bigInt3),
      )
    }


    "median" - cardOne { implicit futConn =>
      One.n.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt4),
      )).transact

      // OBS! Datomic rounds down to nearest whole number
      // (when calculating the median for multiple numbers)!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517

      // Card-one
      One.bigInt(median).query.get.head ==> bigInt2
      One.n.bigInt(median).query.get ==> List(
        (1, bigInt1),
        (2, 3.0),
      )
    }

    "avg" - cardOne { implicit conn =>
      One.n.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt4),
      )).transact

      One.bigInt(avg).query.get.head ==> (BigDecimal(bigInt1 + bigInt2 + bigInt4) / BigDecimal(3)).toDouble
      One.n.bigInt(avg).query.get ==> List(
        (1, bigInt1 / BigInt(1)),
        (2, (bigInt2 + bigInt4) / BigInt(2)),
      )
    }


    "variance" - cardOne { implicit conn =>
      One.n.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt4),
      )).transact

      One.bigInt(variance).query.get.head ==> 1.5555555555555554
      One.n.bigInt(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }


    "stddev" - cardOne { implicit conn =>
      One.n.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt4),
      )).transact

      One.bigInt(stddev).query.get.head ==> 1.247219128924647
      One.n.bigInt(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }
  }
}