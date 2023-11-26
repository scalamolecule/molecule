// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.one.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOneNum_Float_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Using tolerant equality so that the test works with decimal number types too

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantFloatEquality(toleranceFloat)
      for {
        _ <- Ns.i.float.insert(List(
          (1, float1),
          (1, float2),
          (2, float2),
          (2, float3),
          (2, float4),
        )).transact

        // Sum of distinct values (Set semantics)

        _ <- Ns.float(sum).query.get.map(_.head ==~ float1 + float2 + float3 + float4)
        _ <- Ns.i.float(sum).query.get.map(_.map {
          case (1, sum) => sum ==~ float1 + float2
          case (2, sum) => sum ==~ float2 + float3 + float4
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      // Different databases have different ways of calculating a median
      val (median_2_3, median_1_2) = if (database == "MongoDB") {
        (float2, float1)
      } else {
        (
          (float2 + float3).toDouble / 2.0,
          (float1 + float2).toDouble / 2.0
        )
      }
      for {
        _ <- Ns.i.float.insert(List(
          (1, float1),
          (1, float2),
          (2, float2),
          (2, float3),
          (2, float4),
        )).transact

        // Median of unique values (Set semantics)

        _ <- Ns.float(median).query.get.map(_.head ==~ median_2_3)

        _ <- Ns.i.float(median).query.get.map(_.map {
          case (1, median) => median ==~ median_1_2
          case (2, median) => median ==~ float3.toString.toDouble
        })
      } yield ()
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.float.insert(List(
          (1, float1),
          (1, float2),
          (2, float2),
          (2, float3),
          (2, float4),
        )).transact

        // Average of unique values (Set semantics)

        _ <- Ns.float(avg).query.get.map(_.head ==~ (float1 + float2 + float3 + float4).toDouble / 4.0)

        _ <- Ns.i.float(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (float1 + float2).toDouble / 2.0
          case (2, avg) => avg ==~ (float2 + float3 + float4).toDouble / 3.0
        })
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.float.insert(List(
          (1, float1),
          (1, float2),
          (2, float2),
          (2, float3),
          (2, float4),
        )).transact

        // Variance of unique values (Set semantics)

        _ <- Ns.float(variance).query.get.map(_.head ==~ varianceOf(float1, float2, float3, float4))

        _ <- Ns.i.float(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(float1, float2)
          case (2, variance) => variance ==~ varianceOf(float2, float3, float4)
        })
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.float.insert(List(
          (1, float1),
          (1, float2),
          (2, float2),
          (2, float3),
          (2, float4),
        )).transact

        // Standard deviation of unique values (Set semantics)

        _ <- Ns.float(stddev).query.get.map(_.head ==~ stdDevOf(float1, float2, float3, float4))

        _ <- Ns.i.float(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(float1, float2)
          case (2, stddev) => stddev ==~ stdDevOf(float2, float3, float4)
        })
      } yield ()
    }
  }
}