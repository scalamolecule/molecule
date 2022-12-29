package molecule.db.datomic.test.aggr.set.number

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSetNum_Double extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        _ <- Ns.doubles.apply(sum).query.get.map(_ ==> List(
          Set(11) // double1 + double2 + double3 + double4
        ))
        _ <- Ns.i.doubles(sum).query.get.map(_ ==> List(
          (1, Set(3.3000000000000003)), // double1 + double2
          (2, Set(9.9)), // double2 + double3 + double4
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        _ <- Ns.doubles(median).query.get.map(_ ==> List(
          Set(2.0)
        ))
        _ <- Ns.i.doubles(median).query.get.map(_ ==> List(
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
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        _ <- Ns.doubles(avg).query.get.map(_ ==> List(
          Set(2.75) // (double1 + double2 + double3 + double4) / 4.0
        ))
        _ <- Ns.i.doubles(avg).query.get.map(_ ==> List(
          (1, Set(1.6500000000000001)), // (double1 + double2) / 2.0
          (2, Set(3.3000000000000003)), // (double2 + double3 + double4) / 3.0
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        _ <- Ns.doubles(variance).query.get.map(_ ==> List(
          Set(1.5125000000000002)
        ))
        _ <- Ns.i.doubles(variance).query.get.map(_ ==> List(
          (1, Set(0.30250000000000005)),
          (2, Set(0.8066666666666668)),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        _ <- Ns.doubles(stddev).query.get.map(_ ==> List(
          Set(1.2298373876248845)
        ))
        _ <- Ns.i.doubles(stddev).query.get.map(_ ==> List(
          (1, Set(0.55)),
          (2, Set(0.8981462390204987)),
        ))
      } yield ()
    }
  }
}