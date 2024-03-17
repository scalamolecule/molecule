// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetNum_Float_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Using tolerant equality so that the test works with decimal number types too

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantFloatEquality(toleranceFloat)
      for {
        _ <- Ns.i.floatSet.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        // Sum of all values
        _ <- Ns.floatSet(sum).query.get.map(
          _.head.head ==~ (
            float1 + float2 +
              float2 +
              float3 + float4 +
              float3 + float4
            )
        )

        _ <- Ns.i.floatSet(sum).query.get.map(_.map {
          case (1, setWithSum) => setWithSum.head ==~ float1 + float2
          case (2, setWithSum) => setWithSum.head ==~ (
            float2 +
              float3 + float4 +
              float3 + float4
            )
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      // Different databases have different ways of calculating a median
      database match {
        case "Datomic" =>
          for {
            _ <- Ns.i.floatSet.insert(List(
              (1, Set(float1, float2)),
              (2, Set(float2)),
              (2, Set(float5, float9)),
            )).transact

            // Median of all values - middle number used if odd number of values
            // 1  2  2  5  9
            //       ^
            _ <- Ns.floatSet(median).query.get.map(_.head ==~ float2.toString.toDouble) // whole middle number

            _ <- Ns.i.floatSet(median).query.get.map(_.map {
              case (1, median) => median ==~ float1.toDouble.floor // lower whole number
              case (2, median) => median ==~ float5.toString.toDouble // whole middle number
            })
          } yield ()

        case "MongoDB" =>
          for {
            _ <- Ns.i.floatSet.insert(List(
              (1, Set(float1, float2)),
              (2, Set(float2)),
              (2, Set(float5, float9)),
            )).transact

            _ <- Ns.floatSet(median).query.get.map(_.head ==~ float2.toString.toDouble) // whole middle number

            _ <- Ns.i.floatSet(median).query.get.map(_.map {
              case (1, median) => median ==~ float1.toDouble // lower number
              case (2, median) => median ==~ float5.toString.toDouble // whole middle number
            })
          } yield ()

        case _ =>
          for {
            _ <- Ns.i.floatSet.insert(List(
              (1, Set(float1, float2)),
              (2, Set(float2)),
              (2, Set(float5, float9)),
            )).transact

            _ <- Ns.floatSet(median).query.get.map(_.head ==~ float2.toString.toDouble) // middle number

            _ <- Ns.i.floatSet(median).query.get.map(_.map {
              case (1, median) => median ==~ (float1 + float2).toDouble / 2.0 // average of 2 middle numbers
              case (2, median) => median ==~ float5.toString.toDouble // middle number
            })
          } yield ()
      }
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.floatSet.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        // Average of all values
        _ <- Ns.floatSet(avg).query.get.map(_.head ==~ (
          float1 + float2 +
            float2 +
            float3 + float4 +
            float3 + float4
          ).toDouble / 7.0)

        _ <- Ns.i.floatSet(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (float1 + float2).toDouble / 2.0
          case (2, avg) => avg ==~ (
            float2 +
              float3 + float4 +
              float3 + float4
            ).toDouble / 5.0
        })
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.floatSet.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        // Variance of all values
        _ <- Ns.floatSet(variance).query.get.map(_.head ==~ varianceOf(
          float1, float2,
          float2,
          float3, float4,
          float3, float4
        ))

        _ <- Ns.i.floatSet(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(float1, float2)
          case (2, variance) => variance ==~ varianceOf(
            float2,
            float3, float4,
            float3, float4
          )
        })
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.floatSet.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact


        // Standard deviation of all values
        _ <- Ns.floatSet(stddev).query.get.map(_.head ==~ stdDevOf(
          float1, float2,
          float2,
          float3, float4,
          float3, float4
        ))

        _ <- Ns.i.floatSet(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(float1, float2)
          case (2, stddev) => stddev ==~ stdDevOf(
            float2,
            float3, float4,
            float3, float4
          )
        })
      } yield ()
    }
  }
}