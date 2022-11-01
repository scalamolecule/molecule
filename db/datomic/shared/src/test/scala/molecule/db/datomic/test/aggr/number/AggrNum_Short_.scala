// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.number

import molecule.boilerplate.api.Keywords._
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrNum_Short_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - cardOne { implicit conn =>
      One.n.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short3),
      )).transact

      One.short(sum).query.get.head ==> (short1 + short2 + short3)
      One.n.short(sum).query.get ==> List(
        (1, short1),
        (2, short2 + short3),
      )
    }


    "median" - cardOne { implicit futConn =>
      One.n.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short4),
      )).transact

      // OBS! Datomic rounds down to nearest whole number
      // (when calculating the median for multiple numbers)!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517

      // Card-one
      One.short(median).query.get.head ==> short2
      One.n.short(median).query.get ==> List(
        (1, short1),
        (2, 4), // why is this 3 and not 3???
      )
    }

    "avg" - cardOne { implicit conn =>
      One.n.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short4),
      )).transact

      One.short(avg).query.get.head ==> (short1 + short2 + short4) / 3.0
      One.n.short(avg).query.get ==> List(
        (1, short1 / 1.0),
        (2, (short2 + short4) / 2.0),
      )
    }


    "variance" - cardOne { implicit conn =>
      One.n.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short4),
      )).transact

      One.short(variance).query.get.head ==> 1.5555555555555554
      One.n.short(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }


    "stddev" - cardOne { implicit conn =>
      One.n.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short4),
      )).transact

      One.short(stddev).query.get.head ==> 1.247219128924647
      One.n.short(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }
  }
}