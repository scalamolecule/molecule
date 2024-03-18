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

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.floatSet.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.floatSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.floatSet(countDistinct).query.get.map(_ ==> List(4))

        // Sort by counts

        _ <- Ns.i.floatSet(count).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 5),
        ))
        _ <- Ns.i.floatSet(count).d1.query.get.map(_ ==> List(
          (2, 5),
          (1, 2),
        ))

        _ <- Ns.i.floatSet(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.floatSet(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.floatSet.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        // Minimum value

        _ <- Ns.floatSet(min).query.get.map(_ ==> List(float1))

        // Sort by minimum value
        _ <- Ns.i.floatSet(min).a1.query.get.map(_ ==> List(
          (1, float1),
          (2, float2),
        ))
        _ <- Ns.i.floatSet(min).d1.query.get.map(_ ==> List(
          (2, float2),
          (1, float1),
        ))

        // Set of minimum values

        _ <- Ns.floatSet(min(1)).query.get.map(_ ==> List(Set(float1)))
        _ <- Ns.floatSet(min(2)).query.get.map(_ ==> List(Set(float1, float2)))
        _ <- Ns.floatSet(min(3)).query.get.map(_ ==> List(Set(float1, float2, float3)))

        _ <- Ns.i.a1.floatSet(min(1)).query.get.map(_ ==> List(
          (1, Set(float1)),
          (2, Set(float2)),
        ))
        _ <- Ns.i.a1.floatSet(min(2)).query.get.map(_ ==> List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3)),
        ))
        _ <- Ns.i.a1.floatSet(min(3)).query.get.map(_ ==> List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3, float4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.floatSet.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        // Maximum value

        _ <- Ns.floatSet(max).query.get.map(_ ==> List(float4))

        // Sort by maximum value
        _ <- Ns.i.floatSet(max).a1.query.get.map(_ ==> List(
          (1, float2),
          (2, float4),
        ))
        _ <- Ns.i.floatSet(max).d1.query.get.map(_ ==> List(
          (2, float4),
          (1, float2),
        ))

        // Set of maximum values

        _ <- Ns.floatSet(max(1)).query.get.map(_ ==> List(Set(float4)))
        _ <- Ns.floatSet(max(2)).query.get.map(_ ==> List(Set(float3, float4)))
        _ <- Ns.floatSet(max(3)).query.get.map(_ ==> List(Set(float2, float3, float4)))

        _ <- Ns.i.a1.floatSet(max(1)).query.get.map(_ ==> List(
          (1, Set(float2)),
          (2, Set(float4)),
        ))
        _ <- Ns.i.a1.floatSet(max(2)).query.get.map(_ ==> List(
          (1, Set(float1, float2)),
          (2, Set(float3, float4)),
        ))
        _ <- Ns.i.a1.floatSet(max(3)).query.get.map(_ ==> List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3, float4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.floatSet.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        all = Set(float1, float2, float3, float4)

        _ <- Ns.floatSet(sample).query.get.map { rows =>
          val singleSampleValue: Float = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // Sort by sample value (not checked here)
        _ <- Ns.i.floatSet(sample).a1.query.get
        _ <- Ns.i.floatSet(sample).d1.query.get

        // Multiple samples
        _ <- Ns.floatSet(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[Float] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.floatSet(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[Float] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct sets of values" - types { implicit conn =>
      for {
        _ <- Ns.i.floatSet.insert(List(
          (1, Set(float1, float2)),
          (2, Set(float2)),
          (2, Set(float3, float4)),
          (2, Set(float3, float4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.floatSet.query.get.map(_ ==> List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3, float4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.floatSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(float1, float2))),
          (2, Set(
            Set(float2),
            Set(float3, float4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.floatSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(float1, float2),
            Set(float2),
            Set(float3, float4),
          )
        ))
      } yield ()
    }
  }
}