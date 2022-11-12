// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import molecule.coreTests.dataModels.core.types.dsl.TypesOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_Float_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - typesOne { implicit conn =>
      NsOne.n.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float2),
        (2, float3),
      )).transact

      NsOne.n.a1.float.query.get.sortBy(_._2) ==> List(
        (1, float1),
        (2, float2), // 2 rows coalesced
        (2, float3),
      )

      // Distinct values are returned in a List
      NsOne.n.a1.float.apply(distinct).query.get ==> List(
        (1, Set(float1)),
        (2, Set(float2, float3)),
      )

      NsOne.float(distinct).query.get.head ==> Set(
        float1, float2, float3
      )
    }


    "min" - typesOne { implicit conn =>
      NsOne.float.insert(List(float1, float2, float3)).transact
      NsOne.float(min).query.get.head ==> float1
      NsOne.float(min(1)).query.get.head ==> Set(float1)
      NsOne.float(min(2)).query.get.head ==> Set(float1, float2)
    }


    "max" - typesOne { implicit futConn =>
      NsOne.float.insert(List(float1, float2, float3)).transact
      NsOne.float(max).query.get.head ==> float3
      NsOne.float(max(1)).query.get.head ==> Set(float3)
      NsOne.float(max(2)).query.get.head ==> Set(float3, float2)
    }


    "rand" - typesOne { implicit conn =>
      NsOne.float.insert(List(float1, float2, float3)).transact
      val all = Set(float1, float2, float3, float4)
      all.contains(NsOne.float.apply(rand).query.get.head) ==> true
      all.intersect(NsOne.float(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.float(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - typesOne { implicit futConn =>
      NsOne.float.insert(List(float1, float2, float3)).transact
      val all = Set(float1, float2, float3, float4)
      all.contains(NsOne.float(sample).query.get.head) ==> true
      all.intersect(NsOne.float(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.float(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - typesOne { implicit conn =>
      NsOne.n.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float2),
        (2, float3),
      )).transact

      NsOne.n(count).query.get ==> List(4)
      NsOne.n(countDistinct).query.get ==> List(2)

      NsOne.float(count).query.get ==> List(4)
      NsOne.float(countDistinct).query.get ==> List(3)

      NsOne.n.a1.float(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      NsOne.n.a1.float(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}