// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.any

import molecule.boilerplate.api.Keywords._
import molecule.boilerplate.api.Keywords._
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Aggr_Float_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardOne { implicit conn =>
      One.n.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float2),
        (2, float3),
      )).transact

      // Distinct values are returned in a List
      One.float.apply(distinct).query.get.head.sorted ==> List(float1, float2, float3)
      One.n.a1.float(distinct).query.get.map(r => (r._1, r._2.sorted)) ==> List(
        (1, List(float1)),
        (2, List(float2, float3)),
      )
    }


    "min" - cardOne { implicit conn =>
      One.float.insert(List(float1, float2, float3)).transact
      One.float.apply(min).query.get.head ==> float1
      One.float(min(1)).query.get.head ==> List(float1)
      One.float(min(2)).query.get.head ==> List(float1, float2)
    }


    "max" - cardOne { implicit futConn =>
      One.float.insert(List(float1, float2, float3)).transact
      One.float(max).query.get.head ==> float3
      One.float(max(1)).query.get.head ==> List(float3)
      One.float(max(2)).query.get.head ==> List(float3, float2)
    }


    "rand" - cardOne { implicit conn =>
      One.float.insert(List(float1, float2, float3)).transact
      val all = Seq(float1, float2, float3, float4)
      all.contains(One.float(rand).query.get.head) ==> true
      all.intersect(One.float(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.float(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardOne { implicit futConn =>
      One.float.insert(List(float1, float2, float3)).transact
      val all = Seq(float1, float2, float3, float4)
      all.contains(One.float(sample).query.get.head) ==> true
      all.intersect(One.float(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.float(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardOne { implicit conn =>
      One.n.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float2),
        (2, float3),
      )).transact

      One.n(count).query.get ==> List(4)
      One.n(countDistinct).query.get ==> List(2)

      One.float(count).query.get ==> List(4)
      One.float(countDistinct).query.get ==> List(3)

      One.n.a1.float(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      One.n.a1.float(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}