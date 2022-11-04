package molecule.db.datomic.test.aggrOne.number

import molecule.boilerplate.api.Keywords._
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOneNum_Int extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - cardOne { implicit conn =>
      One.n.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int3),
      )).transact

      One.int(sum).query.get.head ==> (int1 + int2 + int3)
      One.n.int(sum).query.get ==> List(
        (1, int1),
        (2, int2 + int3),
      )
    }


    "median" - cardOne { implicit futConn =>
      One.n.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int4),
      )).transact

      // OBS! Datomic rounds down to nearest whole number
      // (when calculating the median for multiple numbers)!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517

      // Card-one
      One.int(median).query.get.head ==> int2
      One.n.int(median).query.get ==> List(
        (1, int1),
        (2, 3.0),
      )
    }

    "avg" - cardOne { implicit conn =>
      One.n.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int4),
      )).transact

      One.int(avg).query.get.head ==> (int1 + int2 + int4) / 3.0
      One.n.int(avg).query.get ==> List(
        (1, int1 / 1.0),
        (2, (int2 + int4) / 2.0),
      )
    }


    "variance" - cardOne { implicit conn =>
      One.n.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int4),
      )).transact

      One.int(variance).query.get.head ==> 1.5555555555555554
      One.n.int(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }


    "stddev" - cardOne { implicit conn =>
      One.n.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int4),
      )).transact

      One.int(stddev).query.get.head ==> 1.247219128924647
      One.n.int(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }
  }
}