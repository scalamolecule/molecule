// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.one.number

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOneNum_BigDecimal_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimal.insert(List(
          (1, bigDecimal1),
          (2, bigDecimal2),
          (2, bigDecimal4),
        )).transact

        _ <- Ns.bigDecimal(sum).query.get.map(_ ==> List(
          7.7 // bigDecimal1 + bigDecimal2 + bigDecimal4
        ))
        _ <- Ns.i.bigDecimal(sum).query.get.map(_ ==> List(
          (1, 1.1),
          (2, 6.6), // bigDecimal2 + bigDecimal4
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.bigDecimal.insert(List(
          (1, bigDecimal1),
          (2, bigDecimal2),
          (2, bigDecimal4),
        )).transact

        _ <- Ns.bigDecimal(median).query.get.map(_ ==> List(
          2.2
        ))
        _ <- Ns.i.bigDecimal(median).query.get.map(_ ==> List(
          (1, 1.1),
          (2, 3.0),
        ))
        // OBS! Datomic rounds down to nearest whole number
        // when calculating the median for multiple numbers!
        // This is another semantic than described on wikipedia:
        // https://en.wikipedia.org/wiki/Median
        // See also
        // https://forum.datomic.com/t/unexpected-median-rounding/517
      } yield ()
    }


    "avg" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimal.insert(List(
          (1, bigDecimal1),
          (2, bigDecimal2),
          (2, bigDecimal4),
        )).transact

        _ <- Ns.bigDecimal(avg).query.get.map(_ ==> List(
          2.566666666666667 // (bigDecimal1 + bigDecimal2 + bigDecimal4) / 3.0
        ))
        _ <- Ns.i.bigDecimal(avg).query.get.map(_ ==> List(
          (1, 1.1),
          (2, 3.3), // (bigDecimal2 + bigDecimal4) / 2.0
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimal.insert(List(
          (1, bigDecimal1),
          (2, bigDecimal2),
          (2, bigDecimal4),
        )).transact

        _ <- Ns.bigDecimal(variance).query.get.map(_ ==> List(
          1.8822222222222225
        ))
        _ <- Ns.i.bigDecimal(variance).query.get.map(_ ==> List(
          (1, 0.0),
          (2, 1.2100000000000002),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimal.insert(List(
          (1, bigDecimal1),
          (2, bigDecimal2),
          (2, bigDecimal4),
        )).transact

        _ <- Ns.bigDecimal(stddev).query.get.map(_ ==> List(
          1.3719410418171119
        ))
        _ <- Ns.i.bigDecimal(stddev).query.get.map(_ ==> List(
          (1, 0.0),
          (2, 1.1),
        ))
      } yield ()
    }
  }
}