package molecule.db.datomic.test.aggr.set.number

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSetNum_Int extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.ints.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        _ <- Ns.ints.apply(sum).query.get.map(_ ==> List(
          Set(10) // int1 + int2 + int3 + int4
        ))
        _ <- Ns.i.ints(sum).query.get.map(_ ==> List(
          (1, Set(3)), // int1 + int2
          (2, Set(9)), // int2 + int3 + int4
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.ints.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        // OBS! Datomic rounds down to nearest whole number
        // (when calculating the median for multiple numbers)!
        // This is another semantic than described on wikipedia:
        // https://en.wikipedia.org/wiki/Median
        // See also
        // https://forum.datomic.com/t/unexpected-median-rounding/517
        _ <- Ns.ints(median).query.get.map(_ ==> List(
          Set(2.0)
        ))
        _ <- Ns.i.ints(median).query.get.map(_ ==> List(
          (1, Set(1.0)),
          (2, Set(3.0)),
        ))
      } yield ()
    }


    "avg" - types { implicit conn =>
      for {
        _ <- Ns.i.ints.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        _ <- Ns.ints(avg).query.get.map(_ ==> List(
          Set(2.5)
        ))
        _ <- Ns.i.ints(avg).query.get.map(_ ==> List(
          (1, Set(1.5)), // (int1 + int2) / 2.0
          (2, Set(3.0)), // (int2 + int3 + int4) / 3.0
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.ints.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        _ <- Ns.ints(variance).query.get.map(_ ==> List(
          Set(1.25)
        ))
        _ <- Ns.i.ints(variance).query.get.map(_ ==> List(
          (1, Set(0.25)),
          (2, Set(0.6666666666666666)),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.ints.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        _ <- Ns.ints(stddev).query.get.map(_ ==> List(
          Set(1.118033988749895)
        ))
        _ <- Ns.i.ints(stddev).query.get.map(_ ==> List(
          (1, Set(0.5)),
          (2, Set(0.816496580927726)),
        ))
      } yield ()
    }
  }
}