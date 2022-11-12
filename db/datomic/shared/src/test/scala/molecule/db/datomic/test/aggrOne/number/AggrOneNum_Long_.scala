// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.number

import molecule.coreTests.dataModels.core.types.dsl.TypesOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOneNum_Long_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - typesOne { implicit conn =>
      NsOne.n.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long4),
      )).transact

      NsOne.long(sum).query.get ==> List(
        7 // long1 + long2 + long4
      )
      NsOne.n.long(sum).query.get ==> List(
        (1, 1),
        (2, 6), // long2 + long4
      )
    }


    "median" - typesOne { implicit futConn =>
      NsOne.n.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long4),
      )).transact

      // OBS! Datomic rounds down to nearest whole number
      // when calculating the median for multiple numbers!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517
      NsOne.long(median).query.get ==> List(
        2.0
      )
      NsOne.n.long(median).query.get ==> List(
        (1, 1.0),
        (2, 3.0),
      )
    }


    "avg" - typesOne { implicit conn =>
      NsOne.n.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long4),
      )).transact

      NsOne.long(avg).query.get ==> List(
        2.3333333333333333 // (long1 + long2 + long4) / 3.0
      )
      NsOne.n.long(avg).query.get ==> List(
        (1, 1.0),
        (2, 3.0), // (long2 + long4) / 2.0
      )
    }


    "variance" - typesOne { implicit conn =>
      NsOne.n.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long4),
      )).transact

      NsOne.long(variance).query.get ==> List(
        1.5555555555555554
      )
      NsOne.n.long(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }


    "stddev" - typesOne { implicit conn =>
      NsOne.n.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long4),
      )).transact

      NsOne.long(stddev).query.get ==> List(
        1.247219128924647
      )
      NsOne.n.long(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }
  }
}