// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.number


import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOneNum_Short_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - cardOne { implicit conn =>
      NsOne.n.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short3),
      )).transact

      NsOne.short(sum).query.get.head ==> (short1 + short2 + short3)
      NsOne.n.short(sum).query.get ==> List(
        (1, short1),
        (2, short2 + short3),
      )
    }


    "median" - cardOne { implicit futConn =>
      NsOne.n.short.insert(List(
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
      NsOne.short(median).query.get.head ==> short2
      NsOne.n.short(median).query.get ==> List(
        (1, short1),
        (2, 3.0),
      )
    }

    "avg" - cardOne { implicit conn =>
      NsOne.n.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short4),
      )).transact

      NsOne.short(avg).query.get.head ==> (short1 + short2 + short4) / 3.0
      NsOne.n.short(avg).query.get ==> List(
        (1, short1 / 1.0),
        (2, (short2 + short4) / 2.0),
      )
    }


    "variance" - cardOne { implicit conn =>
      NsOne.n.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short4),
      )).transact

      NsOne.short(variance).query.get.head ==> 1.5555555555555554
      NsOne.n.short(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }


    "stddev" - cardOne { implicit conn =>
      NsOne.n.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short4),
      )).transact

      NsOne.short(stddev).query.get.head ==> 1.247219128924647
      NsOne.n.short(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }
  }
}