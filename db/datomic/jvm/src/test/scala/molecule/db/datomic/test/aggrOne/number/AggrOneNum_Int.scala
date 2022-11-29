package molecule.db.datomic.test.aggrOne.number

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOneNum_Int extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      Ns.i.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int4),
      )).transact

      Ns.int(sum).query.get ==> List(
        7 // int1 + int2 + int4
      )
      Ns.i.int(sum).query.get ==> List(
        (1, 1),
        (2, 6), // int2 + int4
      )
    }


    "median" - types { implicit futConn =>
      Ns.i.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int4),
      )).transact

      // OBS! Datomic rounds down to nearest whole number
      // when calculating the median for multiple numbers!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517
      Ns.int(median).query.get ==> List(
        2.0
      )
      Ns.i.int(median).query.get ==> List(
        (1, 1.0),
        (2, 3.0),
      )
    }


    "avg" - types { implicit conn =>
      Ns.i.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int4),
      )).transact

      Ns.int(avg).query.get ==> List(
        2.3333333333333333 // (int1 + int2 + int4) / 3.0
      )
      Ns.i.int(avg).query.get ==> List(
        (1, 1.0),
        (2, 3.0), // (int2 + int4) / 2.0
      )
    }


    "variance" - types { implicit conn =>
      Ns.i.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int4),
      )).transact

      Ns.int(variance).query.get ==> List(
        1.5555555555555554
      )
      Ns.i.int(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }


    "stddev" - types { implicit conn =>
      Ns.i.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int4),
      )).transact

      Ns.int(stddev).query.get ==> List(
        1.247219128924647
      )
      Ns.i.int(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }
  }
}