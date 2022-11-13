// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.number

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOneNum_Long_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      Ns.n.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long4),
      )).transact

      Ns.long(sum).query.get ==> List(
        7 // long1 + long2 + long4
      )
      Ns.n.long(sum).query.get ==> List(
        (1, 1),
        (2, 6), // long2 + long4
      )
    }


    "median" - types { implicit futConn =>
      Ns.n.long.insert(List(
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
      Ns.long(median).query.get ==> List(
        2.0
      )
      Ns.n.long(median).query.get ==> List(
        (1, 1.0),
        (2, 3.0),
      )
    }


    "avg" - types { implicit conn =>
      Ns.n.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long4),
      )).transact

      Ns.long(avg).query.get ==> List(
        2.3333333333333333 // (long1 + long2 + long4) / 3.0
      )
      Ns.n.long(avg).query.get ==> List(
        (1, 1.0),
        (2, 3.0), // (long2 + long4) / 2.0
      )
    }


    "variance" - types { implicit conn =>
      Ns.n.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long4),
      )).transact

      Ns.long(variance).query.get ==> List(
        1.5555555555555554
      )
      Ns.n.long(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }


    "stddev" - types { implicit conn =>
      Ns.n.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long4),
      )).transact

      Ns.long(stddev).query.get ==> List(
        1.247219128924647
      )
      Ns.n.long(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }
  }
}