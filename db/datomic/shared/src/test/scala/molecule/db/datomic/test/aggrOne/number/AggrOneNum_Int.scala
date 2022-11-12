package molecule.db.datomic.test.aggrOne.number

import molecule.coreTests.dataModels.core.types.dsl.TypesOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOneNum_Int extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - typesOne { implicit conn =>
      NsOne.n.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int4),
      )).transact

      NsOne.int(sum).query.get ==> List(
        7 // int1 + int2 + int4
      )
      NsOne.n.int(sum).query.get ==> List(
        (1, 1),
        (2, 6), // int2 + int4
      )
    }


    "median" - typesOne { implicit futConn =>
      NsOne.n.int.insert(List(
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
      NsOne.int(median).query.get ==> List(
        2.0
      )
      NsOne.n.int(median).query.get ==> List(
        (1, 1.0),
        (2, 3.0),
      )
    }


    "avg" - typesOne { implicit conn =>
      NsOne.n.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int4),
      )).transact

      NsOne.int(avg).query.get ==> List(
        2.3333333333333333 // (int1 + int2 + int4) / 3.0
      )
      NsOne.n.int(avg).query.get ==> List(
        (1, 1.0),
        (2, 3.0), // (int2 + int4) / 2.0
      )
    }


    "variance" - typesOne { implicit conn =>
      NsOne.n.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int4),
      )).transact

      NsOne.int(variance).query.get ==> List(
        1.5555555555555554
      )
      NsOne.n.int(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }


    "stddev" - typesOne { implicit conn =>
      NsOne.n.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int4),
      )).transact

      NsOne.int(stddev).query.get ==> List(
        1.247219128924647
      )
      NsOne.n.int(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }
  }
}