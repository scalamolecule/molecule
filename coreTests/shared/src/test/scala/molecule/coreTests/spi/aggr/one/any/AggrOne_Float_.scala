// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.one.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOne_Float_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.float.insert(List(
          (1, float1),
          (2, float2),
          (2, float2),
          (2, float3),
        )).transact

        _ <- Ns.i.float.a1.query.get.map(_ ==> List(
          (1, float1),
          (2, float2), // 2 rows coalesced
          (2, float3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.float(distinct).query.get.map(_ ==> List(
          (1, Set(float1)),
          (2, Set(float2, float3)),
        ))

        _ <- Ns.float(distinct).query.get.map(_.head ==> Set(
          float1, float2, float3
        ))
      } yield ()
    }


    "min/max" - types { implicit conn =>
      for {
        _ <- Ns.i.float.insert(
          (1, float1),
          (1, float2),
          (1, float3),
          (2, float4),
          (2, float5),
          (2, float6),
        ).transact

        _ <- Ns.float(min).query.get.map(_ ==> List(float1))
        _ <- Ns.float(min(1)).query.get.map(_ ==> List(Set(float1)))
        _ <- Ns.float(min(2)).query.get.map(_ ==> List(Set(float1, float2)))

        _ <- Ns.float(max).query.get.map(_ ==> List(float6))
        _ <- Ns.float(max(1)).query.get.map(_ ==> List(Set(float6)))
        _ <- Ns.float(max(2)).query.get.map(_ ==> List(Set(float5, float6)))

        _ <- Ns.i.a1.float(min(2)).query.get.map(_ ==> List(
          (1, Set(float1, float2)),
          (2, Set(float4, float5))
        ))

        _ <- Ns.i.a1.float(max(2)).query.get.map(_ ==> List(
          (1, Set(float2, float3)),
          (2, Set(float5, float6))
        ))

        _ <- Ns.i.a1.float(min(2)).float(max(2)).query.get.map(_ ==> List(
          (1, Set(float1, float2), Set(float2, float3)),
          (2, Set(float4, float5), Set(float5, float6))
        ))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.float.insert(List(float1, float2, float3)).transact
        all = Set(float1, float2, float3, float4)
        _ <- Ns.float(rand).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.float(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.float(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.float.insert(List(float1, float2, float3)).transact
        all = Set(float1, float2, float3, float4)
        _ <- Ns.float(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.float(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.float(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count, countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.float.insert(List(
          (1, float1),
          (2, float2),
          (2, float2),
          (2, float3),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.float(count).query.get.map(_ ==> List(4))
        _ <- Ns.float(countDistinct).query.get.map(_ ==> List(3))

        _ <- Ns.i.a1.float(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))
        _ <- Ns.i.a1.float(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}