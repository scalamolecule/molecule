// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.number

import molecule.boilerplate.api.Keywords._
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrNum_Long_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - cardOne { implicit conn =>
      One.n.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long3),
      )).transact

      One.long(sum).query.get.head ==> (long1 + long2 + long3)
      One.n.long(sum).query.get ==> List(
        (1, long1),
        (2, long2 + long3),
      )
    }


    "median" - cardOne { implicit futConn =>
      One.n.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long4),
      )).transact

      // OBS! Datomic rounds down to nearest whole number
      // (when calculating the median for multiple numbers)!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517

      // Card-one
      One.long(median).query.get.head ==> long2
      One.n.long(median).query.get ==> List(
        (1, long1),
        (2, 3.0),
      )
    }

    "avg" - cardOne { implicit conn =>
      One.n.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long4),
      )).transact

      One.long(avg).query.get.head ==> (long1 + long2 + long4) / 3.0
      One.n.long(avg).query.get ==> List(
        (1, long1 / 1.0),
        (2, (long2 + long4) / 2.0),
      )
    }


    "variance" - cardOne { implicit conn =>
      One.n.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long4),
      )).transact

      One.long(variance).query.get.head ==> 1.5555555555555554
      One.n.long(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }


    "stddev" - cardOne { implicit conn =>
      One.n.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long4),
      )).transact

      One.long(stddev).query.get.head ==> 1.247219128924647
      One.n.long(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }
  }
}