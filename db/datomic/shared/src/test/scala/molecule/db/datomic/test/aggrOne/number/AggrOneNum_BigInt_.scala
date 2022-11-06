// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.number


import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOneNum_BigInt_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - cardOne { implicit conn =>
      NsOne.n.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt3),
      )).transact

      NsOne.bigInt(sum).query.get.head ==> (bigInt1 + bigInt2 + bigInt3)
      NsOne.n.bigInt(sum).query.get ==> List(
        (1, bigInt1),
        (2, bigInt2 + bigInt3),
      )
    }


    "median" - cardOne { implicit futConn =>
      NsOne.n.bigInt.insert(List(
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
      NsOne.bigInt(median).query.get.head ==> bigInt2
      NsOne.n.bigInt(median).query.get ==> List(
        (1, bigInt1),
        (2, 3.0),
      )
    }

    "avg" - cardOne { implicit conn =>
      NsOne.n.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt4),
      )).transact

      NsOne.bigInt(avg).query.get.head ==> (BigDecimal(bigInt1 + bigInt2 + bigInt4) / BigDecimal(3)).toDouble
      NsOne.n.bigInt(avg).query.get ==> List(
        (1, bigInt1 / BigInt(1)),
        (2, (bigInt2 + bigInt4) / BigInt(2)),
      )
    }


    "variance" - cardOne { implicit conn =>
      NsOne.n.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt4),
      )).transact

      NsOne.bigInt(variance).query.get.head ==> 1.5555555555555554
      NsOne.n.bigInt(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }


    "stddev" - cardOne { implicit conn =>
      NsOne.n.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt4),
      )).transact

      NsOne.bigInt(stddev).query.get.head ==> 1.247219128924647
      NsOne.n.bigInt(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }
  }
}