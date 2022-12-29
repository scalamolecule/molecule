// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.one.any

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_Float_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.float.insert(List(
          (1, float1),
          (2, float2),
          (2, float2),
          (2, float3),
        )).transact

        _ <- Ns.i.a1.float.query.get.map(_.sortBy(_._2) ==> List(
          (1, float1),
          (2, float2), // 2 rows coalesced
          (2, float3),
        ))

        // Distinct values are returned in a List
        _ <- Ns.i.a1.float(distinct).query.get.map(_ ==> List(
          (1, Set(float1)),
          (2, Set(float2, float3)),
        ))

        _ <- Ns.float(distinct).query.get.map(_.head ==> Set(
          float1, float2, float3
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.float.insert(List(float1, float2, float3)).transact
        _ <- Ns.float(min).query.get.map(_ ==> List(float1))
        _ <- Ns.float(min(1)).query.get.map(_ ==> List(Set(float1)))
        _ <- Ns.float(min(2)).query.get.map(_ ==> List(Set(float1, float2)))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.float.insert(List(float1, float2, float3)).transact
        _ <- Ns.float(max).query.get.map(_ ==> List(float3))
        _ <- Ns.float(max(1)).query.get.map(_ ==> List(Set(float3)))
        _ <- Ns.float(max(2)).query.get.map(_ ==> List(Set(float3, float2)))
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