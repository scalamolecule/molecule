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

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantFloatEquality(toleranceFloat)
      for {
        _ <- Ns.i.floats.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        // Using tolerant equality so that the test works with decimal number types too
        _ <- Ns.floats(sum).query.get.map(_.head.head ==~ float1 + float2 + float3 + float4)

        _ <- Ns.i.floats(sum).query.get.map(_.map {
          case (1, setWithSum) => setWithSum.head ==~ float1 + float2
          case (2, setWithSum) => setWithSum.head ==~ float2 + float3 + float4
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.floats.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        _ <- Ns.floats.query.get.map(_ ==> List(Set(float1, float2, float3, float4)))
        _ <- Ns.floats(median).query.get.map(_.head ==~ (float2 + float3).toDouble / 2.0)

        _ <- Ns.i.a1.floats.query.get.map(_ ==> List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3, float4)),
        ))
        _ <- Ns.i.floats(median).query.get.map(_.map {
          case (1, median) => median ==~ (float1 + float2).toDouble / 2.0
          case (2, median) => median ==~ float3.toString.toDouble
        })
      } yield ()
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.floats.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        _ <- Ns.floats.query.get.map(_ ==> List(Set(float1, float2, float3, float4)))
        _ <- Ns.floats(avg).query.get.map(_.head ==~ (float1 + float2 + float3 + float4).toDouble / 4.0)

        _ <- Ns.i.a1.floats.query.get.map(_ ==> List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3, float4)),
        ))
        _ <- Ns.i.floats(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (float1 + float2).toDouble / 2.0
          case (2, avg) => avg ==~ (float2 + float3 + float4).toDouble / 3.0
        })
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.floats.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        _ <- Ns.floats.query.get.map(_ ==> List(Set(float1, float2, float3, float4)))
        _ <- Ns.floats(variance).query.get.map(_.head ==~ varianceOf(float1, float2, float3, float4))

        _ <- Ns.i.a1.floats.query.get.map(_ ==> List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3, float4)),
        ))
        _ <- Ns.i.floats(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(float1, float2)
          case (2, variance) => variance ==~ varianceOf(float2, float3, float4)
        })
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.floats.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        _ <- Ns.floats.query.get.map(_ ==> List(Set(float1, float2, float3, float4)))
        _ <- Ns.floats(stddev).query.get.map(_.head ==~ stdDevOf(float1, float2, float3, float4))

        _ <- Ns.i.a1.floats.query.get.map(_ ==> List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3, float4)),
        ))
        _ <- Ns.i.floats(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(float1, float2)
          case (2, stddev) => stddev ==~ stdDevOf(float2, float3, float4)
        })
      } yield ()
    }
  }
}