// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.number

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrNum_Double_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - cardOne { implicit conn =>
      One.n.double.insert(List(
        (1, double1),
        (2, double2),
        (2, double3),
      )).transact

      One.double(sum).query.get.head ==> 6.6000000000000005
      One.n.double(sum).query.get ==> List(
        (1, double1),
        (2, double2 + double3),
      )
    }


    "median" - cardOne { implicit futConn =>
      One.n.double.insert(List(
        (1, double1),
        (2, double2),
        (2, double4),
      )).transact

      // OBS! Datomic rounds down to nearest whole number
      // (when calculating the median for multiple numbers)!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517

      // Card-one
      One.double(median).query.get.head ==> double2
      One.n.double(median).query.get ==> List(
        (1, double1),
        (2, 3.0),
      )
    }

    "avg" - cardOne { implicit conn =>
      One.n.double.insert(List(
        (1, double1),
        (2, double2),
        (2, double4),
      )).transact

      One.double(avg).query.get.head ==> (double1 + double2 + double4) / 3.0
      One.n.double(avg).query.get ==> List(
        (1, double1 / 1.0),
        (2, (double2 + double4) / 2.0),
      )
    }


    "variance" - cardOne { implicit conn =>
      One.n.double.insert(List(
        (1, double1),
        (2, double2),
        (2, double4),
      )).transact

      One.double(variance).query.get.head ==> 1.8822222222222225
      One.n.double(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.2100000000000002),
      )
    }


    "stddev" - cardOne { implicit conn =>
      One.n.double.insert(List(
        (1, double1),
        (2, double2),
        (2, double4),
      )).transact

      One.double(stddev).query.get.head ==> 1.3719410418171119
      One.n.double(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.1),
      )
    }
  }
}