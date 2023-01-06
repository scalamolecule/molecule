// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.set.number

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSetNum_ref_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.refs.insert(List(
          (1, Set(ref1, ref2)),
          (2, Set(ref2, ref3)),
          (2, Set(ref3, ref4)),
          (2, Set(ref3, ref4)),
        )).transact

        _ <- Ns.refs.apply(sum).query.get.map(_ === List(
          Set(ref1 + ref2 + ref3 + ref4)
        ))
        _ <- Ns.i.refs(sum).query.get.map(_ === List(
          (1, Set(ref1 + ref2)),
          (2, Set(ref2 + ref3 + ref4)),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.refs.insert(List(
          (1, Set(ref1, ref2)),
          (2, Set(ref2, ref3)),
          (2, Set(ref3, ref4)),
          (2, Set(ref3, ref4)),
        )).transact

        _ <- Ns.refs(median).query.get.map(_ === List(
          Set(ref2)
        ))
        _ <- Ns.i.refs(median).query.get.map(_ === List(
          (1, Set(ref1)),
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
        _ <- Ns.i.refs.insert(List(
          (1, Set(ref1, ref2)),
          (2, Set(ref2, ref3)),
          (2, Set(ref3, ref4)),
          (2, Set(ref3, ref4)),
        )).transact

        _ <- Ns.refs(avg).query.get.map(_ === List(
          Set(averageOf(ref1, ref2, ref3, ref4))
        ))
        _ <- Ns.i.refs(avg).query.get.map(_ === List(
          (1, Set(averageOf(ref1, ref2))),
          (2, Set(averageOf(ref2, ref3, ref4))),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.refs.insert(List(
          (1, Set(ref1, ref2)),
          (2, Set(ref2, ref3)),
          (2, Set(ref3, ref4)),
          (2, Set(ref3, ref4)),
        )).transact

        _ <- Ns.refs(variance).query.get.map(_ === List(
          Set(varianceOf(ref1, ref2, ref3, ref4))
        ))
        _ <- Ns.i.refs(variance).query.get.map(_ === List(
          (1, Set(varianceOf(ref1, ref2))),
          (2, Set(varianceOf(ref2, ref3, ref4))),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.refs.insert(List(
          (1, Set(ref1, ref2)),
          (2, Set(ref2, ref3)),
          (2, Set(ref3, ref4)),
          (2, Set(ref3, ref4)),
        )).transact

        _ <- Ns.refs(stddev).query.get.map(_ === List(
          Set(stdDevOf(ref1, ref2, ref3, ref4))
        ))
        _ <- Ns.i.refs(stddev).query.get.map(_ === List(
          (1, Set(stdDevOf(ref1, ref2))),
          (2, Set(stdDevOf(ref2, ref3, ref4))),
        ))
      } yield ()
    }
  }
}