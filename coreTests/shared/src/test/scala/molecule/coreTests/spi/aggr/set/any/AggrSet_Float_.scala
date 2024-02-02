// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_Float_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.floats.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.floats.query.get.map(_ ==> List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3, float4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.floats(distinct).query.get.map(_ ==> List(
          (1, Set(Set(float1, float2))),
          (2, Set(
            Set(float2),
            Set(float3, float4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.floats(distinct).query.get.map(_ ==> List(
          Set(
            Set(float1, float2),
            Set(float2),
            Set(float3, float4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.floats.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        // Matching values coalesced floato one Set

        _ <- Ns.floats(min).query.get.map(_ ==> List(Set(float1)))
        _ <- Ns.floats(min(1)).query.get.map(_ ==> List(Set(float1)))
        _ <- Ns.floats(min(2)).query.get.map(_ ==> List(Set(float1, float2)))
        _ <- Ns.floats(min(3)).query.get.map(_ ==> List(Set(float1, float2, float3)))

        _ <- Ns.i.a1.floats(min).query.get.map(_ ==> List(
          (1, Set(float1)),
          (2, Set(float2)),
        ))
        // Same as
        _ <- Ns.i.a1.floats(min(1)).query.get.map(_ ==> List(
          (1, Set(float1)),
          (2, Set(float2)),
        ))

        _ <- Ns.i.a1.floats(min(2)).query.get.map(_ ==> List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3)),
        ))

        _ <- Ns.i.a1.floats(min(3)).query.get.map(_ ==> List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3, float4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.floats.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        // Matching values coalesced floato one Set

        _ <- Ns.floats(max).query.get.map(_ ==> List(Set(float4)))
        _ <- Ns.floats(max(1)).query.get.map(_ ==> List(Set(float4)))
        _ <- Ns.floats(max(2)).query.get.map(_ ==> List(Set(float3, float4)))
        _ <- Ns.floats(max(3)).query.get.map(_ ==> List(Set(float2, float3, float4)))

        _ <- Ns.i.a1.floats(max).query.get.map(_ ==> List(
          (1, Set(float2)),
          (2, Set(float4)),
        ))
        // Same as
        _ <- Ns.i.a1.floats(max(1)).query.get.map(_ ==> List(
          (1, Set(float2)),
          (2, Set(float4)),
        ))

        _ <- Ns.i.a1.floats(max(2)).query.get.map(_ ==> List(
          (1, Set(float1, float2)),
          (2, Set(float3, float4)),
        ))

        _ <- Ns.i.a1.floats(max(3)).query.get.map(_ ==> List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3, float4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.floats.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact
        all = Set(float1, float2, float3, float4)
        _ <- Ns.floats(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.floats(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.floats(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.floats.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.floats(count).query.get.map(_ ==> List(7))
        _ <- Ns.floats(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.floats(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 5)
        ))
        _ <- Ns.i.a1.floats(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}