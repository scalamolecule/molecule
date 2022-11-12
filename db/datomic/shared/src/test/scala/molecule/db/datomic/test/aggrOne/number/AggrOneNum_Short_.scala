// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.number

import molecule.coreTests.dataModels.core.types.dsl.TypesOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOneNum_Short_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - typesOne { implicit conn =>
      NsOne.n.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short4),
      )).transact

      NsOne.short(sum).query.get ==> List(
        7 // short1 + short2 + short4
      )
      NsOne.n.short(sum).query.get ==> List(
        (1, 1),
        (2, 6), // short2 + short4
      )
    }


    "median" - typesOne { implicit futConn =>
      NsOne.n.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short4),
      )).transact

      // OBS! Datomic rounds down to nearest whole number
      // when calculating the median for multiple numbers!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517
      NsOne.short(median).query.get ==> List(
        2.0
      )
      NsOne.n.short(median).query.get ==> List(
        (1, 1.0),
        (2, 3.0),
      )
    }


    "avg" - typesOne { implicit conn =>
      NsOne.n.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short4),
      )).transact

      NsOne.short(avg).query.get ==> List(
        2.3333333333333333 // (short1 + short2 + short4) / 3.0
      )
      NsOne.n.short(avg).query.get ==> List(
        (1, 1.0),
        (2, 3.0), // (short2 + short4) / 2.0
      )
    }


    "variance" - typesOne { implicit conn =>
      NsOne.n.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short4),
      )).transact

      NsOne.short(variance).query.get ==> List(
        1.5555555555555554
      )
      NsOne.n.short(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }


    "stddev" - typesOne { implicit conn =>
      NsOne.n.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short4),
      )).transact

      NsOne.short(stddev).query.get ==> List(
        1.247219128924647
      )
      NsOne.n.short(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }
  }
}