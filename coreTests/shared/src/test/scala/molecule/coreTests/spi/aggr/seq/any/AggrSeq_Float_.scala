// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_Float_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.floatSeq.insert(List(
          (1, List(float1, float2, float2)),
          (2, List(float2)),
          (2, List(float3, float4, float4)),
          (2, List(float3, float4, float4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.floatSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.floatSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.floatSeq(count).a1.query.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.floatSeq(count).d1.query.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.floatSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.floatSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.floatSeq.insert(List(
          (1, List(float1, float2, float2)),
          (2, List(float2)),
          (2, List(float3, float4, float4)),
          (2, List(float3, float4, float4)),
        )).transact

        // Minimum value

        _ <- Ns.floatSeq(min).query.get.map(_ ==> List(float1))

        // We can sort by minimum value
        _ <- Ns.i.floatSeq(min).a1.query.get.map(_ ==> List(
          (1, float1),
          (2, float2),
        ))
        _ <- Ns.i.floatSeq(min).d1.query.get.map(_ ==> List(
          (2, float2),
          (1, float1),
        ))

        // Set of minimum values

        _ <- Ns.floatSeq.apply(min(1)).query.get.map(_ ==> List(Set(float1)))
        _ <- Ns.floatSeq(min(2)).query.get.map(_ ==> List(Set(float1, float2)))
        _ <- Ns.floatSeq(min(3)).query.get.map(_ ==> List(Set(float1, float2, float3)))

        _ <- Ns.i.a1.floatSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(float1)),
          (2, Set(float2)),
        ))
        _ <- Ns.i.a1.floatSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3)),
        ))
        _ <- Ns.i.a1.floatSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(float1, float2)),
          (2, Set(float2, float3, float4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.floatSeq.insert(List(
          (1, List(float1, float2, float2)),
          (2, List(float2)),
          (2, List(float3, float4, float4)),
          (2, List(float3, float4, float4)),
        )).transact

        // Maximum value

        _ <- Ns.floatSeq(max).query.get.map(_ ==> List(float4))

        // We can sort by maximum value
        _ <- Ns.i.floatSeq(max).a1.query.get.map(_ ==> List(
          (1, float2),
          (2, float4),
        ))
        _ <- Ns.i.floatSeq(max).d1.query.get.map(_ ==> List(
          (2, float4),
          (1, float2),
        ))

        // Set of maximum values

        _ <- Ns.floatSeq(max(1)).query.get.map(_ ==> List(Set(float4)))
        _ <- Ns.floatSeq(max(2)).query.get.map(_ ==> List(Set(float4, float3)))
        _ <- Ns.floatSeq(max(3)).query.get.map(_ ==> List(Set(float4, float3, float2)))

        // Same as
        _ <- Ns.i.a1.floatSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(float2)),
          (2, Set(float4)),
        ))
        _ <- Ns.i.a1.floatSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(float2, float1)),
          (2, Set(float4, float3)),
        ))
        _ <- Ns.i.a1.floatSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(float2, float1)),
          (2, Set(float4, float3, float2)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.floatSeq.insert(List(
          (1, List(float1, float2, float2)),
          (2, List(float2)),
          (2, List(float3, float4, float4)),
          (2, List(float3, float4, float4)),
        )).transact

        all = Set(float1, float2, float3, float4)

        _ <- Ns.floatSeq(sample).query.get.map { rows =>
          val singleSampleValue = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // We can sort by sample value (not checked here)
        _ <- Ns.i.floatSeq(sample).a1.query.get
        _ <- Ns.i.floatSeq(sample).d1.query.get

        // Multiple samples
        _ <- Ns.floatSeq(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[Float] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.floatSeq(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[Float] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.floatSeq.insert(List(
          (1, List(float1, float2, float2)),
          (2, List(float2)),
          (2, List(float3, float4, float4)),
          (2, List(float3, float4, float4)),
        )).transact

        // Non-aggregated card-many Seq of attribute values coalesce
        _ <- Ns.i.floatSeq.query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(float1, float2, float2)),
          (2, List(float2)),
          (2, List(float3, float4, float4)), // 2 rows with List(float3, float4) coalesced
        ))

        // Use `distinct` keyword to retrieve unique Seqs of Seqs
        _ <- Ns.i.a1.floatSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(float1, float2, float2))),
          (2, Set(
            List(float2),
            List(float3, float4, float4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.floatSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(float1, float2, float2),
            List(float2),
            List(float3, float4, float4),
          )
        ))
      } yield ()
    }
  }
}