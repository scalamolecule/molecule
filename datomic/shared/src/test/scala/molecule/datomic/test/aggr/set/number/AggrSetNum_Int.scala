package molecule.datomic.test.aggr.set.number

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._

object AggrSetNum_Int extends DatomicTestSuite {


  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.ints.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        _ <- Ns.ints.apply(sum).query.get.map(_ === List(
          Set(int1 + int2 + int3 + int4)
        ))
        _ <- Ns.i.ints(sum).query.get.map(_ === List(
          (1, Set(int1 + int2)),
          (2, Set(int2 + int3 + int4)),
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

        _ <- Ns.ints(median).query.get.map(_ === List(
          Set(int2)
        ))
        _ <- Ns.i.ints(median).query.get.map(_ === List(
          (1, Set(int1)),
          (2, Set(3.0)),
          // OBS! Datomic rounds down to nearest whole number
          // (when calculating the median for multiple numbers)!
          // This is another semantic than described on wikipedia:
          // https://en.wikipedia.org/wiki/Median
          // See also
          // https://forum.datomic.com/t/unexpected-median-rounding/517
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

        _ <- Ns.ints(avg).query.get.map(_ === List(
          Set(averageOf(int1, int2, int3, int4))
        ))
        _ <- Ns.i.ints(avg).query.get.map(_ === List(
          (1, Set(averageOf(int1, int2))),
          (2, Set(averageOf(int2, int3, int4))),
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

        _ <- Ns.ints(variance).query.get.map(_ === List(
          Set(varianceOf(int1, int2, int3, int4))
        ))
        _ <- Ns.i.ints(variance).query.get.map(_ === List(
          (1, Set(varianceOf(int1, int2))),
          (2, Set(varianceOf(int2, int3, int4))),
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

        _ <- Ns.ints(stddev).query.get.map(_ === List(
          Set(stdDevOf(int1, int2, int3, int4))
        ))
        _ <- Ns.i.ints(stddev).query.get.map(_ === List(
          (1, Set(stdDevOf(int1, int2))),
          (2, Set(stdDevOf(int2, int3, int4))),
        ))
      } yield ()
    }
  }
}