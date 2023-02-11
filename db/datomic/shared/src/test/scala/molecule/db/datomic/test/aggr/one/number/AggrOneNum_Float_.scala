// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.one.number

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic.async._
import molecule.db.datomic.setup.DatomicTestSuite
import org.scalactic.TripleEquals._
import utest._


object AggrOneNum_Float_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.float.insert(List(
          (1, float1),
          (2, float2),
          (2, float4),
        )).transact

        // Using === for tolerant precision comparison
        // (only necessary on JS platform with JavaScript imprecision)
        _ <- Ns.float(sum).query.get.map(_ === List(
          float1 + float2 + float4
        ))
        _ <- Ns.i.float(sum).query.get.map(_ === List(
          (1, float1),
          (2, float2 + float4),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.float.insert(List(
          (1, float1),
          (2, float2),
          (2, float4),
        )).transact

        _ <- Ns.float(median).query.get.map(_ === List(
          float2
        ))
        _ <- Ns.i.float(median).query.get.map(_ === List(
          (1, float1),
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
        _ <- Ns.i.float.insert(List(
          (1, float1),
          (2, float2),
          (2, float4),
        )).transact

        _ <- Ns.float(avg).query.get.map(_ === List(
          averageOf(float1, float2, float4)
        ))
        _ <- Ns.i.float(avg).query.get.map(_ === List(
          (1, averageOf(float1)),
          (2, averageOf(float2, float4)),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.float.insert(List(
          (1, float1),
          (2, float2),
          (2, float4),
        )).transact

        _ <- Ns.float(variance).query.get.map(_ === List(
          varianceOf(float1, float2, float4)
        ))
        _ <- Ns.i.float(variance).query.get.map(_ === List(
          (1, varianceOf(float1)),
          (2, varianceOf(float2, float4)),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.float.insert(List(
          (1, float1),
          (2, float2),
          (2, float4),
        )).transact

        _ <- Ns.float(stddev).query.get.map(_ === List(
          stdDevOf(float1, float2, float4)
        ))
        _ <- Ns.i.float(stddev).query.get.map(_ === List(
          (1, stdDevOf(float1)),
          (2, stdDevOf(float2, float4)),
        ))
      } yield ()
    }
  }
}