// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.one.number

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOneNum_BigDecimal_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      Ns.i.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal4),
      )).transact

      Ns.bigDecimal(sum).query.get ==> List(
        7.7 // bigDecimal1 + bigDecimal2 + bigDecimal4
      )
      Ns.i.bigDecimal(sum).query.get ==> List(
        (1, 1.1),
        (2, 6.6), // bigDecimal2 + bigDecimal4
      )
    }


    "median" - types { implicit futConn =>
      Ns.i.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal4),
      )).transact

      Ns.bigDecimal(median).query.get ==> List(
        2.2
      )
      Ns.i.bigDecimal(median).query.get ==> List(
        (1, 1.1),
        (2, 3.0),
      )
      // OBS! Datomic rounds down to nearest whole number
      // when calculating the median for multiple numbers!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517
    }


    "avg" - types { implicit conn =>
      Ns.i.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal4),
      )).transact

      Ns.bigDecimal(avg).query.get ==> List(
        2.566666666666667 // (bigDecimal1 + bigDecimal2 + bigDecimal4) / 3.0
      )
      Ns.i.bigDecimal(avg).query.get ==> List(
        (1, 1.1),
        (2, 3.3), // (bigDecimal2 + bigDecimal4) / 2.0
      )
    }


    "variance" - types { implicit conn =>
      Ns.i.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal4),
      )).transact

      Ns.bigDecimal(variance).query.get ==> List(
        1.8822222222222225
      )
      Ns.i.bigDecimal(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.2100000000000002),
      )
    }


    "stddev" - types { implicit conn =>
      Ns.i.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal4),
      )).transact

      Ns.bigDecimal(stddev).query.get ==> List(
        1.3719410418171119
      )
      Ns.i.bigDecimal(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.1),
      )
    }
  }
}