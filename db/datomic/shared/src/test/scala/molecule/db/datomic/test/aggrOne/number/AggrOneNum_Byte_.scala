// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.number

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOneNum_Byte_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - cardOne { implicit conn =>
      NsOne.n.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte4),
      )).transact

      NsOne.byte(sum).query.get ==> List(
        7 // byte1 + byte2 + byte4
      )
      NsOne.n.byte(sum).query.get ==> List(
        (1, 1),
        (2, 6), // byte2 + byte4
      )
    }


    "median" - cardOne { implicit futConn =>
      NsOne.n.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte4),
      )).transact

      // OBS! Datomic rounds down to nearest whole number
      // when calculating the median for multiple numbers!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517
      NsOne.byte(median).query.get ==> List(
        2.0
      )
      NsOne.n.byte(median).query.get ==> List(
        (1, 1.0),
        (2, 3.0),
      )
    }


    "avg" - cardOne { implicit conn =>
      NsOne.n.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte4),
      )).transact

      NsOne.byte(avg).query.get ==> List(
        2.3333333333333333 // (byte1 + byte2 + byte4) / 3.0
      )
      NsOne.n.byte(avg).query.get ==> List(
        (1, 1.0),
        (2, 3.0), // (byte2 + byte4) / 2.0
      )
    }


    "variance" - cardOne { implicit conn =>
      NsOne.n.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte4),
      )).transact

      NsOne.byte(variance).query.get ==> List(
        1.5555555555555554
      )
      NsOne.n.byte(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }


    "stddev" - cardOne { implicit conn =>
      NsOne.n.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte4),
      )).transact

      NsOne.byte(stddev).query.get ==> List(
        1.247219128924647
      )
      NsOne.n.byte(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }
  }
}