// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.one.any

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_Float_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.i.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float2),
        (2, float3),
      )).transact

      Ns.i.a1.float.query.get.sortBy(_._2) ==> List(
        (1, float1),
        (2, float2), // 2 rows coalesced
        (2, float3),
      )

      // Distinct values are returned in a List
      Ns.i.a1.float.apply(distinct).query.get ==> List(
        (1, Set(float1)),
        (2, Set(float2, float3)),
      )

      Ns.float(distinct).query.get.head ==> Set(
        float1, float2, float3
      )
    }


    "min" - types { implicit conn =>
      Ns.float.insert(List(float1, float2, float3)).transact
      Ns.float(min).query.get ==> List(float1)
      Ns.float(min(1)).query.get ==> List(Set(float1))
      Ns.float(min(2)).query.get ==> List(Set(float1, float2))
    }


    "max" - types { implicit futConn =>
      Ns.float.insert(List(float1, float2, float3)).transact
      Ns.float(max).query.get ==> List(float3)
      Ns.float(max(1)).query.get ==> List(Set(float3))
      Ns.float(max(2)).query.get ==> List(Set(float3, float2))
    }


    "rand" - types { implicit conn =>
      Ns.float.insert(List(float1, float2, float3)).transact
      val all = Set(float1, float2, float3, float4)
      all.contains(Ns.float.apply(rand).query.get.head) ==> true
      all.intersect(Ns.float(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.float(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.float.insert(List(float1, float2, float3)).transact
      val all = Set(float1, float2, float3, float4)
      all.contains(Ns.float(sample).query.get.head) ==> true
      all.intersect(Ns.float(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.float(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.i.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float2),
        (2, float3),
      )).transact

      Ns.i(count).query.get ==> List(4)
      Ns.i(countDistinct).query.get ==> List(2)

      Ns.float(count).query.get ==> List(4)
      Ns.float(countDistinct).query.get ==> List(3)

      Ns.i.a1.float(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      Ns.i.a1.float(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}