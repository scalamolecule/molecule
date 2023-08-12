// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.one.number

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOneNum_Float_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

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

        // Using tolerant equality so that the test works with decimal number types too
        // Distinct values (Set semantics) used
        _ <- Ns.float(sum).query.get.map(_.head ==~ float1 + float2 + float3 + float4)
        _ <- Ns.i.float(sum).query.get.map(_.map {
          case (1, sum) => sum ==~ float1 + float2
          case (2, sum) => sum ==~ float2 + float3 + float4
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.float.insert(List(
          (1, float1),
          (1, float2),
          (2, float2),
          (2, float3),
          (2, float4),
        )).transact

        _ <- Ns.float.a1.query.get.map(_ ==> List(float1, float2, float3, float4))
        _ <- Ns.float(median).query.get.map(_.head ==~ (float2 + float3).toDouble / 2.0)

        _ <- Ns.i.float(median).query.get.map(_.map {
          case (1, median) => median ==~ (float1 + float2).toDouble / 2.0
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

        _ <- Ns.float.a1.query.get.map(_ ==> List(float1, float2, float3, float4))
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

        _ <- Ns.float.a1.query.get.map(_ ==> List(float1, float2, float3, float4))
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

        _ <- Ns.float.a1.query.get.map(_ ==> List(float1, float2, float3, float4))
        _ <- Ns.float(stddev).query.get.map(_.head ==~ stdDevOf(float1, float2, float3, float4))

        _ <- Ns.i.float(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(float1, float2)
          case (2, stddev) => stddev ==~ stdDevOf(float2, float3, float4)
        })
      } yield ()
    }
  }
}