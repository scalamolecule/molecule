// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import molecule.coreTests.dataModels.core.types.dsl.TypesSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_Float_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - typesSet { implicit conn =>
      NsSet.n.floats.insert(List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3)),
        (2, Set(float3, float4)),
        (2, Set(float3, float4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      NsSet.n.a1.floats.query.get ==> List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3, float4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      NsSet.n.a1.floats(distinct).query.get ==> List(
        (1, Set(Set(float1, float2))),
        (2, Set(
          Set(float2, float3),
          Set(float3, float4) // 2 rows coalesced
        ))
      )

      NsSet.floats(distinct).query.get ==> List(
        Set(
          Set(float1, float2),
          Set(float2, float3),
          Set(float3, float4),
        )
      )
    }


    "min" - typesSet { implicit conn =>
      NsSet.n.floats.insert(List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3)),
        (2, Set(float3, float4)),
        (2, Set(float3, float4)),
      )).transact

      NsSet.floats(min).query.get ==> List(Set(float1))
      NsSet.floats(min(1)).query.get ==> List(Set(float1))
      NsSet.floats(min(2)).query.get ==> List(Set(float1, float2))

      NsSet.n.floats(min).query.get ==> List(
        (1, Set(float1)),
        (2, Set(float2)),
      )
      // Same as
      NsSet.n.floats(min(1)).query.get ==> List(
        (1, Set(float1)),
        (2, Set(float2)),
      )

      NsSet.n.floats(min(2)).query.get ==> List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3)),
      )
    }


    "max" - typesSet { implicit futConn =>
      NsSet.n.floats.insert(List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3)),
        (2, Set(float3, float4)),
        (2, Set(float3, float4)),
      )).transact

      NsSet.floats(max).query.get ==> List(Set(float4))
      NsSet.floats(max(1)).query.get ==> List(Set(float4))
      NsSet.floats(max(2)).query.get ==> List(Set(float3, float4))

      NsSet.n.floats(max).query.get ==> List(
        (1, Set(float2)),
        (2, Set(float4)),
      )
      // Same as
      NsSet.n.floats(max(1)).query.get ==> List(
        (1, Set(float2)),
        (2, Set(float4)),
      )

      NsSet.n.floats(max(2)).query.get ==> List(
        (1, Set(float1, float2)),
        (2, Set(float3, float4)),
      )
    }


    "rand" - typesSet { implicit conn =>
      NsSet.n.floats.insert(List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3)),
        (2, Set(float3, float4)),
        (2, Set(float3, float4)),
      )).transact
      val all = Set(float1, float2, float3, float4)
      all.contains(NsSet.floats(rand).query.get.head.head) ==> true
      all.intersect(NsSet.floats(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsSet.floats(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - typesSet { implicit futConn =>
      NsSet.n.floats.insert(List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3)),
        (2, Set(float3, float4)),
        (2, Set(float3, float4)),
      )).transact
      val all = Set(float1, float2, float3, float4)
      all.contains(NsSet.floats(sample).query.get.head.head) ==> true
      all.intersect(NsSet.floats(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsSet.floats(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - typesSet { implicit conn =>
      NsSet.n.floats.insert(List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3)),
        (2, Set(float3, float4)),
        (2, Set(float3, float4)),
      )).transact

      NsSet.n(count).query.get ==> List(4)
      NsSet.n(countDistinct).query.get ==> List(2)

      NsSet.floats(count).query.get ==> List(8)
      NsSet.floats(countDistinct).query.get ==> List(4)

      NsSet.n.a1.floats(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      NsSet.n.a1.floats(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}