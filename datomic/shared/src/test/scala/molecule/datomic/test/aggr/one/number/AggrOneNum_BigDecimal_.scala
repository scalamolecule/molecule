// GENERATED CODE ********************************
package molecule.datomic.test.aggr.one.number

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import org.scalactic.TripleEquals._
import utest._


object AggrOneNum_BigDecimal_ extends DatomicTestSuite {


  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimal.insert(List(
          (1, bigDecimal1),
          (2, bigDecimal2),
          (2, bigDecimal4),
        )).transact

        // Using === for tolerant precision comparison
        // (only necessary on JS platform with JavaScript imprecision)
        _ <- Ns.bigDecimal(sum).query.get.map(_ === List(
          bigDecimal1 + bigDecimal2 + bigDecimal4
        ))
        _ <- Ns.i.bigDecimal(sum).query.get.map(_ === List(
          (1, bigDecimal1),
          (2, bigDecimal2 + bigDecimal4),
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

        _ <- Ns.bigDecimal(median).query.get.map(_ === List(
          bigDecimal2
        ))
        _ <- Ns.i.bigDecimal(median).query.get.map(_ === List(
          (1, bigDecimal1),
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
        _ <- Ns.i.bigDecimal.insert(List(
          (1, bigDecimal1),
          (2, bigDecimal2),
          (2, bigDecimal4),
        )).transact

        _ <- Ns.bigDecimal(avg).query.get.map(_ === List(
          averageOf(bigDecimal1, bigDecimal2, bigDecimal4)
        ))
        _ <- Ns.i.bigDecimal(avg).query.get.map(_ === List(
          (1, averageOf(bigDecimal1)),
          (2, averageOf(bigDecimal2, bigDecimal4)),
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

        _ <- Ns.bigDecimal(variance).query.get.map(_ === List(
          varianceOf(bigDecimal1, bigDecimal2, bigDecimal4)
        ))
        _ <- Ns.i.bigDecimal(variance).query.get.map(_ === List(
          (1, varianceOf(bigDecimal1)),
          (2, varianceOf(bigDecimal2, bigDecimal4)),
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

        _ <- Ns.bigDecimal(stddev).query.get.map(_ === List(
          stdDevOf(bigDecimal1, bigDecimal2, bigDecimal4)
        ))
        _ <- Ns.i.bigDecimal(stddev).query.get.map(_ === List(
          (1, stdDevOf(bigDecimal1)),
          (2, stdDevOf(bigDecimal2, bigDecimal4)),
        ))
      } yield ()
    }
  }
}