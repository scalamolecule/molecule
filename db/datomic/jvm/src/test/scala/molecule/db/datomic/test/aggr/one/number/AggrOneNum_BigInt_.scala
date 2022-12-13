// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.one.number

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOneNum_BigInt_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      Ns.i.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt4),
      )).transact

      Ns.bigInt(sum).query.get ==> List(
        7 // bigInt1 + bigInt2 + bigInt4
      )
      Ns.i.bigInt(sum).query.get ==> List(
        (1, 1),
        (2, 6), // bigInt2 + bigInt4
      )
    }


    "median" - types { implicit futConn =>
      Ns.i.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt4),
      )).transact

      // OBS! Datomic rounds down to nearest whole number
      // when calculating the median for multiple numbers!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517
      Ns.bigInt(median).query.get ==> List(
        2.0
      )
      Ns.i.bigInt(median).query.get ==> List(
        (1, 1.0),
        (2, 3.0),
      )
    }


    "avg" - types { implicit conn =>
      Ns.i.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt4),
      )).transact

      Ns.bigInt(avg).query.get ==> List(
        2.3333333333333333 // (bigInt1 + bigInt2 + bigInt4) / 3.0
      )
      Ns.i.bigInt(avg).query.get ==> List(
        (1, 1.0),
        (2, 3.0), // (bigInt2 + bigInt4) / 2.0
      )
    }


    "variance" - types { implicit conn =>
      Ns.i.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt4),
      )).transact

      Ns.bigInt(variance).query.get ==> List(
        1.5555555555555554
      )
      Ns.i.bigInt(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }


    "stddev" - types { implicit conn =>
      Ns.i.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt4),
      )).transact

      Ns.bigInt(stddev).query.get ==> List(
        1.247219128924647
      )
      Ns.i.bigInt(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.0),
      )
    }
  }
}