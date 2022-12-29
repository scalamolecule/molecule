// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.set.number

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSetNum_BigDecimal_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        _ <- Ns.bigDecimals.apply(sum).query.get.map(_ ==> List(
          Set(11) // bigDecimal1 + bigDecimal2 + bigDecimal3 + bigDecimal4
        ))
        _ <- Ns.i.bigDecimals(sum).query.get.map(_ ==> List(
          (1, Set(3.3)), // bigDecimal1 + bigDecimal2
          (2, Set(9.9)), // bigDecimal2 + bigDecimal3 + bigDecimal4
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        _ <- Ns.bigDecimals(median).query.get.map(_ ==> List(
          Set(2.0)
        ))
        _ <- Ns.i.bigDecimals(median).query.get.map(_ ==> List(
          (1, Set(1.0)),
          (2, Set(3.3)),
        ))
        // OBS! Datomic rounds down to nearest whole number
        // (when calculating the median for multiple numbers)!
        // This is another semantic than described on wikipedia:
        // https://en.wikipedia.org/wiki/Median
        // See also
        // https://forum.datomic.com/t/unexpected-median-rounding/517
      } yield ()
    }


    "avg" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        _ <- Ns.bigDecimals(avg).query.get.map(_ ==> List(
          Set(2.75) // (bigDecimal1 + bigDecimal2 + bigDecimal3 + bigDecimal4) / 4.0
        ))
        _ <- Ns.i.bigDecimals(avg).query.get.map(_ ==> List(
          (1, Set(1.65)), // (bigDecimal1 + bigDecimal2) / 2.0
          (2, Set(3.3000000000000003)), // (bigDecimal2 + bigDecimal3 + bigDecimal4) / 3.0
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        _ <- Ns.bigDecimals(variance).query.get.map(_ ==> List(
          Set(1.5125000000000002)
        ))
        _ <- Ns.i.bigDecimals(variance).query.get.map(_ ==> List(
          (1, Set(0.30250000000000005)),
          (2, Set(0.8066666666666668)),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        _ <- Ns.bigDecimals(stddev).query.get.map(_ ==> List(
          Set(1.2298373876248845)
        ))
        _ <- Ns.i.bigDecimals(stddev).query.get.map(_ ==> List(
          (1, Set(0.55)),
          (2, Set(0.8981462390204987)),
        ))
      } yield ()
    }
  }
}