// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.number


import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOneNum_Double_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - cardOne { implicit conn =>
      NsOne.n.double.insert(List(
        (1, double1),
        (2, double2),
        (2, double3),
      )).transact

      NsOne.double(sum).query.get.head ==> 6.6000000000000005
      NsOne.n.double(sum).query.get ==> List(
        (1, double1),
        (2, double2 + double3),
      )
    }


    "median" - cardOne { implicit futConn =>
      NsOne.n.double.insert(List(
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
      NsOne.double(median).query.get.head ==> double2
      NsOne.n.double(median).query.get ==> List(
        (1, double1),
        (2, 3.0),
      )
    }

    "avg" - cardOne { implicit conn =>
      NsOne.n.double.insert(List(
        (1, double1),
        (2, double2),
        (2, double4),
      )).transact

      NsOne.double(avg).query.get.head ==> (double1 + double2 + double4) / 3.0
      NsOne.n.double(avg).query.get ==> List(
        (1, double1 / 1.0),
        (2, (double2 + double4) / 2.0),
      )
    }


    "variance" - cardOne { implicit conn =>
      NsOne.n.double.insert(List(
        (1, double1),
        (2, double2),
        (2, double4),
      )).transact

      NsOne.double(variance).query.get.head ==> 1.8822222222222225
      NsOne.n.double(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.2100000000000002),
      )
    }


    "stddev" - cardOne { implicit conn =>
      NsOne.n.double.insert(List(
        (1, double1),
        (2, double2),
        (2, double4),
      )).transact

      NsOne.double(stddev).query.get.head ==> 1.3719410418171119
      NsOne.n.double(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.1),
      )
    }
  }
}