// GENERATED CODE ********************************
package molecule.datomic.test.aggr.set.number

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._

object AggrSetNum_Float_ extends DatomicTestSuite {


  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.i.floats.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        _ <- Ns.floats.apply(sum).query.get.map(_ === List(
          Set(float1 + float2 + float3 + float4)
        ))
        _ <- Ns.i.floats(sum).query.get.map(_ === List(
          (1, Set(float1 + float2)),
          (2, Set(float2 + float3 + float4)),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      for {
        _ <- Ns.i.floats.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        _ <- Ns.floats(median).query.get.map(_ === List(
          Set(float2)
        ))
        _ <- Ns.i.floats(median).query.get.map(_ === List(
          (1, Set(float1)),
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
        _ <- Ns.i.floats.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        _ <- Ns.floats(avg).query.get.map(_ === List(
          Set(averageOf(float1, float2, float3, float4))
        ))
        _ <- Ns.i.floats(avg).query.get.map(_ === List(
          (1, Set(averageOf(float1, float2))),
          (2, Set(averageOf(float2, float3, float4))),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.i.floats.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        _ <- Ns.floats(variance).query.get.map(_ === List(
          Set(varianceOf(float1, float2, float3, float4))
        ))
        _ <- Ns.i.floats(variance).query.get.map(_ === List(
          (1, Set(varianceOf(float1, float2))),
          (2, Set(varianceOf(float2, float3, float4))),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.i.floats.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        _ <- Ns.floats(stddev).query.get.map(_ === List(
          Set(stdDevOf(float1, float2, float3, float4))
        ))
        _ <- Ns.i.floats(stddev).query.get.map(_ === List(
          (1, Set(stdDevOf(float1, float2))),
          (2, Set(stdDevOf(float2, float3, float4))),
        ))
      } yield ()
    }
  }
}