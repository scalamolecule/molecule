// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.number

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrNum_Byte_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - cardOne { implicit conn =>
      One.n.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte3),
      )).transact

      One.byte(sum).query.get.head ==> (byte1 + byte2 + byte3)
      One.n.byte(sum).query.get ==> List(
        (1, byte1),
        (2, byte2 + byte3),
      )
    }


    "median" - cardOne { implicit futConn =>
      One.n.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte4),
      )).transact

      // OBS! Datomic rounds down to nearest whole number
      // (when calculating the median for multiple numbers)!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517

      // Card-one
      One.byte(median).query.get.head ==> byte2
      One.n.byte(median).query.get ==> List(
        (1, byte1),
        (2, 4), // why is this 3 and not 3???
      )
    }

    "avg" - cardOne { implicit conn =>
      One.n.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte4),
      )).transact

      One.byte(avg).query.get.head ==> (byte1 + byte2 + byte4) / 3.0
      One.n.byte(avg).query.get ==> List(
        (1, byte1 / 1.0),
        (2, (byte2 + byte4) / 2.0),
      )
    }


    "variance" - cardOne { implicit conn =>
      One.n.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte4),
      )).transact

      One.byte(variance).query.get.head ==> 1.5555555555555554
      One.n.byte(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }


    "stddev" - cardOne { implicit conn =>
      One.n.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte4),
      )).transact

      One.byte(stddev).query.get.head ==> 1.247219128924647
      One.n.byte(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }
  }
}