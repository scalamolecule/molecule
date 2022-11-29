// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_Float_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.i.floats.insert(List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3)),
        (2, Set(float3, float4)),
        (2, Set(float3, float4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      Ns.i.a1.floats.query.get ==> List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3, float4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      Ns.i.a1.floats(distinct).query.get ==> List(
        (1, Set(Set(float1, float2))),
        (2, Set(
          Set(float2, float3),
          Set(float3, float4) // 2 rows coalesced
        ))
      )

      Ns.floats(distinct).query.get ==> List(
        Set(
          Set(float1, float2),
          Set(float2, float3),
          Set(float3, float4),
        )
      )
    }


    "min" - types { implicit conn =>
      Ns.i.floats.insert(List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3)),
        (2, Set(float3, float4)),
        (2, Set(float3, float4)),
      )).transact

      Ns.floats(min).query.get ==> List(Set(float1))
      Ns.floats(min(1)).query.get ==> List(Set(float1))
      Ns.floats(min(2)).query.get ==> List(Set(float1, float2))

      Ns.i.floats(min).query.get ==> List(
        (1, Set(float1)),
        (2, Set(float2)),
      )
      // Same as
      Ns.i.floats(min(1)).query.get ==> List(
        (1, Set(float1)),
        (2, Set(float2)),
      )

      Ns.i.floats(min(2)).query.get ==> List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3)),
      )
    }


    "max" - types { implicit futConn =>
      Ns.i.floats.insert(List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3)),
        (2, Set(float3, float4)),
        (2, Set(float3, float4)),
      )).transact

      Ns.floats(max).query.get ==> List(Set(float4))
      Ns.floats(max(1)).query.get ==> List(Set(float4))
      Ns.floats(max(2)).query.get ==> List(Set(float3, float4))

      Ns.i.floats(max).query.get ==> List(
        (1, Set(float2)),
        (2, Set(float4)),
      )
      // Same as
      Ns.i.floats(max(1)).query.get ==> List(
        (1, Set(float2)),
        (2, Set(float4)),
      )

      Ns.i.floats(max(2)).query.get ==> List(
        (1, Set(float1, float2)),
        (2, Set(float3, float4)),
      )
    }


    "rand" - types { implicit conn =>
      Ns.i.floats.insert(List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3)),
        (2, Set(float3, float4)),
        (2, Set(float3, float4)),
      )).transact
      val all = Set(float1, float2, float3, float4)
      all.contains(Ns.floats(rand).query.get.head.head) ==> true
      all.intersect(Ns.floats(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.floats(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.i.floats.insert(List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3)),
        (2, Set(float3, float4)),
        (2, Set(float3, float4)),
      )).transact
      val all = Set(float1, float2, float3, float4)
      all.contains(Ns.floats(sample).query.get.head.head) ==> true
      all.intersect(Ns.floats(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.floats(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.i.floats.insert(List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3)),
        (2, Set(float3, float4)),
        (2, Set(float3, float4)),
      )).transact

      Ns.i(count).query.get ==> List(4)
      Ns.i(countDistinct).query.get ==> List(2)

      Ns.floats(count).query.get ==> List(8)
      Ns.floats(countDistinct).query.get ==> List(4)

      Ns.i.a1.floats(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      Ns.i.a1.floats(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}