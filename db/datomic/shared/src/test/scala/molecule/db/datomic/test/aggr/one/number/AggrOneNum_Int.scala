package molecule.db.datomic.test.aggr.one.number

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOneNum_Int extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.int.insert(List(
          (1, int1),
          (2, int2),
          (2, int4),
        )).transact

        _ <- Ns.int(sum).query.get.map(_ ==> List(
          7 // int1 + int2 + int4
        ))
        _ <- Ns.i.int(sum).query.get.map(_ ==> List(
          (1, 1),
          (2, 6), // int2 + int4
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.int.insert(List(
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
        _ <- Ns.int(median).query.get.map(_ ==> List(
          2.0
        ))
        _ <- Ns.i.int(median).query.get.map(_ ==> List(
          (1, 1.0),
          (2, 3.0),
        ))
      } yield ()
    }


    "avg" - types { implicit conn =>
      for {
        _ <- Ns.i.int.insert(List(
          (1, int1),
          (2, int2),
          (2, int4),
        )).transact

        _ <- Ns.int(avg).query.get.map(_ ==> List(
          2.3333333333333333 // (int1 + int2 + int4) / 3.0
        ))
        _ <- Ns.i.int(avg).query.get.map(_ ==> List(
          (1, 1.0),
          (2, 3.0), // (int2 + int4) / 2.0
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.int.insert(List(
          (1, int1),
          (2, int2),
          (2, int4),
        )).transact

        _ <- Ns.int(variance).query.get.map(_ ==> List(
          1.5555555555555554
        ))
        _ <- Ns.i.int(variance).query.get.map(_ ==> List(
          (1, 0.0),
          (2, 1.0),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.int.insert(List(
          (1, int1),
          (2, int2),
          (2, int4),
        )).transact

        _ <- Ns.int(stddev).query.get.map(_ ==> List(
          1.247219128924647
        ))
        _ <- Ns.i.int(stddev).query.get.map(_ ==> List(
          (1, 0.0),
          (2, 1.0),
        ))
      } yield ()
    }
  }
}