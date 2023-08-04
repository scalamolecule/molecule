package molecule.coreTests.test.aggr.one.number

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOneNum_Int extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.int.insert(List(
          (1, int1),
          (2, int2),
          (2, int4),
        )).transact

        // Using === for tolerant precision comparison
        // (only necessary on JS platform with JavaScript rounding imprecision)
        _ <- Ns.int(sum).query.get.map(_ === List(
          int1 + int2 + int4
        ))
        _ <- Ns.i.int(sum).query.get.map(_ === List(
          (1, int1),
          (2, int2 + int4),
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

        _ <- Ns.int(median).query.get.map(_ === List(
          int2
        ))
        _ <- Ns.i.int(median).query.get.map(_ === List(
          (1, int1),
          (2, 3.0),
          // OBS! Datomic rounds down to nearest whole number
          // when calculating the median for multiple numbers!
          // This is another semantic than described on wikipedia:
          // https://en.wikipedia.org/wiki/Median
          // See also
          // https://forum.datomic.com/t/unexpected-median-rounding/517
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

        _ <- Ns.int(avg).query.get.map(_ === List(
          averageOf(int1, int2, int4)
        ))
        _ <- Ns.i.int(avg).query.get.map(_ === List(
          (1, averageOf(int1)),
          (2, averageOf(int2, int4)),
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

        _ <- Ns.int(variance).query.get.map(_ === List(
          varianceOf(int1, int2, int4)
        ))
        _ <- Ns.i.int(variance).query.get.map(_ === List(
          (1, varianceOf(int1)),
          (2, varianceOf(int2, int4)),
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

        _ <- Ns.int(stddev).query.get.map(_ === List(
          stdDevOf(int1, int2, int4)
        ))
        _ <- Ns.i.int(stddev).query.get.map(_ === List(
          (1, stdDevOf(int1)),
          (2, stdDevOf(int2, int4)),
        ))
      } yield ()
    }
  }
}