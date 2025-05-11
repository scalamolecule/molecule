// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import molecule.db.compliance.setup.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.domains.dsl.Refs.*

case class Aggr_Float_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types { implicit conn =>
    for {
      _ <- Entity.i.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float2),
        (2, float3),
      )).transact

      _ <- Entity.i.float.a1.query.get.map(_ ==> List(
        (1, float1),
        (2, float2), // 2 rows coalesced
        (2, float3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.float(distinct).query.get.map(_ ==> List(
        (1, Set(float1)),
        (2, Set(float2, float3)),
      ))

      _ <- Entity.float(distinct).query.get.map(_.head ==> Set(
        float1, float2, float3
      ))
    } yield ()
  }


  "min/max" - types { implicit conn =>
    for {
      _ <- Entity.i.float.insert(
        (1, float1),
        (1, float2),
        (1, float3),
        (2, float4),
        (2, float5),
        (2, float6),
      ).transact

      _ <- Entity.float(min).query.get.map(_ ==> List(float1))
      _ <- Entity.float(max).query.get.map(_ ==> List(float6))
      _ <- Entity.float(min).float(max).query.get.map(_ ==> List((float1, float6)))

      _ <- Entity.i.a1.float(min).query.get.map(_ ==> List(
        (1, float1),
        (2, float4)
      ))

      _ <- Entity.i.a1.float(max).query.get.map(_ ==> List(
        (1, float3),
        (2, float6)
      ))

      _ <- Entity.i.a1.float(min).float(max).query.get.map(_ ==> List(
        (1, float1, float3),
        (2, float4, float6)
      ))
    } yield ()
  }

  "min/max n" - types { implicit conn =>
    for {
      _ <- Entity.i.float.insert(
        (1, float1),
        (1, float2),
        (1, float3),
        (2, float4),
        (2, float5),
        (2, float6),
        (2, float6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.float(min(1)).query.get.map(_ ==> List(Set(float1)))
      _ <- Entity.float(min(2)).query.get.map(_ ==> List(Set(float1, float2)))

      _ <- Entity.float(max(1)).query.get.map(_ ==> List(Set(float6)))
      _ <- Entity.float(max(2)).query.get.map(_ ==> List(Set(float5, float6)))

      _ <- Entity.i.a1.float(min(2)).query.get.map(_ ==> List(
        (1, Set(float1, float2)),
        (2, Set(float4, float5))
      ))

      _ <- Entity.i.a1.float(max(2)).query.get.map(_ ==> List(
        (1, Set(float2, float3)),
        (2, Set(float5, float6))
      ))

      _ <- Entity.i.a1.float(min(2)).float(max(2)).query.get.map(_ ==> List(
        (1, Set(float1, float2), Set(float2, float3)),
        (2, Set(float4, float5), Set(float5, float6))
      ))
    } yield ()
  }


  "sample" - types { implicit futConn =>
    val all = Set(float1, float2, float3, float4)
    for {
      _ <- Entity.float.insert(List(float1, float2, float3)).transact
      _ <- Entity.float(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.float(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.float(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types { implicit conn =>
    for {
      _ <- Entity.i.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float2),
        (2, float3),
      )).transact

      _ <- Entity.float(count).query.get.map(_ ==> List(4))
      _ <- Entity.i.a1.float(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- Entity.float(countDistinct).query.get.map(_ ==> List(3))
      _ <- Entity.i.a1.float(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}