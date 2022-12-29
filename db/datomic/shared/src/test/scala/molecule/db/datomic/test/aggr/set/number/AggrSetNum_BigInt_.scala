// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.set.number

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSetNum_BigInt_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        _ <- Ns.bigInts.apply(sum).query.get.map(_ ==> List(
          Set(10) // bigInt1 + bigInt2 + bigInt3 + bigInt4
        ))
        _ <- Ns.i.bigInts(sum).query.get.map(_ ==> List(
          (1, Set(3)), // bigInt1 + bigInt2
          (2, Set(9)), // bigInt2 + bigInt3 + bigInt4
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        // OBS! Datomic rounds down to nearest whole number
        // (when calculating the median for multiple numbers)!
        // This is another semantic than described on wikipedia:
        // https://en.wikipedia.org/wiki/Median
        // See also
        // https://forum.datomic.com/t/unexpected-median-rounding/517
        _ <- Ns.bigInts(median).query.get.map(_ ==> List(
          Set(2.0)
        ))
        _ <- Ns.i.bigInts(median).query.get.map(_ ==> List(
          (1, Set(1.0)),
          (2, Set(3.0)),
        ))
      } yield ()
    }


    "avg" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        _ <- Ns.bigInts(avg).query.get.map(_ ==> List(
          Set(2.5)
        ))
        _ <- Ns.i.bigInts(avg).query.get.map(_ ==> List(
          (1, Set(1.5)), // (bigInt1 + bigInt2) / 2.0
          (2, Set(3.0)), // (bigInt2 + bigInt3 + bigInt4) / 3.0
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        _ <- Ns.bigInts(variance).query.get.map(_ ==> List(
          Set(1.25)
        ))
        _ <- Ns.i.bigInts(variance).query.get.map(_ ==> List(
          (1, Set(0.25)),
          (2, Set(0.6666666666666666)),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        _ <- Ns.bigInts(stddev).query.get.map(_ ==> List(
          Set(1.118033988749895)
        ))
        _ <- Ns.i.bigInts(stddev).query.get.map(_ ==> List(
          (1, Set(0.5)),
          (2, Set(0.816496580927726)),
        ))
      } yield ()
    }
  }
}