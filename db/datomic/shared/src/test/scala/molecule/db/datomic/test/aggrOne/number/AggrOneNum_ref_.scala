// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.number

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOneNum_ref_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      Ns.n.ref.insert(List(
        (1, ref1),
        (2, ref2),
        (2, ref4),
      )).transact

      Ns.ref(sum).query.get ==> List(
        7 // ref1 + ref2 + ref4
      )
      Ns.n.ref(sum).query.get ==> List(
        (1, 1),
        (2, 6), // ref2 + ref4
      )
    }


    "median" - types { implicit futConn =>
      Ns.n.ref.insert(List(
        (1, ref1),
        (2, ref2),
        (2, ref4),
      )).transact

      // OBS! Datomic rounds down to nearest whole number
      // when calculating the median for multiple numbers!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517
      Ns.ref(median).query.get ==> List(
        2.0
      )
      Ns.n.ref(median).query.get ==> List(
        (1, 1.0),
        (2, 3.0),
      )
    }


    "avg" - types { implicit conn =>
      Ns.n.ref.insert(List(
        (1, ref1),
        (2, ref2),
        (2, ref4),
      )).transact

      Ns.ref(avg).query.get ==> List(
        2.3333333333333333 // (ref1 + ref2 + ref4) / 3.0
      )
      Ns.n.ref(avg).query.get ==> List(
        (1, 1.0),
        (2, 3.0), // (ref2 + ref4) / 2.0
      )
    }


    "variance" - types { implicit conn =>
      Ns.n.ref.insert(List(
        (1, ref1),
        (2, ref2),
        (2, ref4),
      )).transact

      Ns.ref(variance).query.get ==> List(
        1.5555555555555554
      )
      Ns.n.ref(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }


    "stddev" - types { implicit conn =>
      Ns.n.ref.insert(List(
        (1, ref1),
        (2, ref2),
        (2, ref4),
      )).transact

      Ns.ref(stddev).query.get ==> List(
        1.247219128924647
      )
      Ns.n.ref(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }
  }
}