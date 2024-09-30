// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_Float_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  val a = (1, Map("a" -> float1, "b" -> float2))
  val b = (2, Map("a" -> float2, "b" -> float3, "c" -> float4))

  override lazy val tests = Tests {

    "Mandatory" - {

      "Mandatory Map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.floatMap.insert(a, b).transact
          _ <- Ns.i.a1.floatMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Value by key" - types { implicit conn =>
        for {
          _ <- Ns.i.floatMap.insert(a, b).transact

          // Get Map value by key

          // Like calling `apply` on a Scala Map.
          _ <- Ns.i.a1.floatMap("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap("a").query.get.map(_ ==> List((1, float1), (2, float2)))
          _ <- Ns.i.a1.floatMap("b").query.get.map(_ ==> List((1, float2), (2, float3)))
          _ <- Ns.i.a1.floatMap("c").query.get.map(_ ==> List((2, float4)))
        } yield ()
      }


      "Map having values" - types { implicit conn =>
        for {
          _ <- Ns.i.floatMap.insert(a, b).transact

          // Maps containing value

          _ <- Ns.i.a1.floatMap.has(float0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap.has(float1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatMap.has(float2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatMap.has(float3).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatMap.has(float4).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.floatMap.has(Seq(float0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap.has(Seq(float1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatMap.has(Seq(float2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatMap.has(Seq(float3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatMap.has(Seq(float4)).query.get.map(_ ==> List(b))

          // OR semantics when multiple values

          // "Has this OR that" value
          _ <- Ns.i.a1.floatMap.has(float0, float1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatMap.has(float1, float2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatMap.has(float2, float3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatMap.has(float3, float4).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatMap.has(float4, float5).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatMap.has(float5, float6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.floatMap.has(Seq(float0, float1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatMap.has(Seq(float1, float2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatMap.has(Seq(float2, float3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatMap.has(Seq(float3, float4)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatMap.has(Seq(float4, float5)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatMap.has(Seq(float5, float6)).query.get.map(_ ==> List())

          // No values match nothing
          _ <- Ns.i.a1.floatMap.has(Seq.empty[Float]).query.get.map(_ ==> List())
        } yield ()
      }


      "Map not having values" - types { implicit conn =>
        for {
          _ <- Ns.i.floatMap.insert(a, b).transact

          // "Doesn't have this"
          _ <- Ns.i.a1.floatMap.hasNo(float0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatMap.hasNo(float1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatMap.hasNo(float2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap.hasNo(float3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatMap.hasNo(float3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatMap.hasNo(float5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floatMap.hasNo(List(float0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatMap.hasNo(List(float1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatMap.hasNo(List(float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap.hasNo(List(float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatMap.hasNo(List(float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatMap.hasNo(List(float5)).query.get.map(_ ==> List(a, b))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.floatMap.hasNo(float1, float2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap.hasNo(float1, float3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap.hasNo(float1, float3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap.hasNo(float1, float5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.floatMap.hasNo(List(float1, float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap.hasNo(List(float1, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap.hasNo(List(float1, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap.hasNo(List(float1, float5)).query.get.map(_ ==> List(b))

          // No values match nothing
          _ <- Ns.i.a1.floatMap.hasNo(List.empty[Float]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "Tacit Map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.floatMap.insert(a, b).transact
          _ <- Ns.i.a1.floatMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "Map contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.floatMap.insert(a, b).transact

          // "Map contains this OR that key"
          _ <- Ns.i.a1.floatMap_("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap_("a").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_("_", "c").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.floatMap_(List("_")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap_(List("a")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_(List("_", "c")).query.get.map(_ ==> List(2))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.floatMap_(List.empty[String]).query.get.map(_ ==> List())

          // Match entities without map attribute
          _ <- Ns.i.a1.floatMap_().query.get.map(_ ==> List(0))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.floatMap.insert(a, b).transact

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.floatMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_.not("a").query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap_.not("b").query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.not("a", "c").query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap_.not("_", "c").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.floatMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_.not(List("a")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap_.not(List("b")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.not(List("a", "c")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.floatMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.floatMap.insert(a, b).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.floatMap_.has(float0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap_.has(float1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.has(float2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_.has(float3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.has(float4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.has(float0, float1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.has(float1, float2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_.has(float2, float3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_.has(float3, float4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.has(float4, float5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.has(float5, float6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.floatMap_.has(List(float0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap_.has(List(float1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.has(List(float2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_.has(List(float3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.has(List(float4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.has(List(float0, float1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.has(List(float1, float2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_.has(List(float2, float3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_.has(List(float3, float4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.has(List(float4, float5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.has(List(float5, float6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.floatMap_.has(List.empty[Float]).query.get.map(_ ==> List())
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.floatMap.insert(a, b).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.floatMap_.hasNo(float0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_.hasNo(float1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.hasNo(float2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap_.hasNo(float3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.hasNo(float4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.hasNo(float0, float1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.hasNo(float1, float2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap_.hasNo(float2, float3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap_.hasNo(float3, float4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.hasNo(float4, float5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.hasNo(float5, float6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.floatMap_.hasNo(List(float0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_.hasNo(List(float1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.hasNo(List(float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap_.hasNo(List(float3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.hasNo(List(float4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.hasNo(List(float0, float1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.hasNo(List(float1, float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap_.hasNo(List(float2, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap_.hasNo(List(float3, float4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.hasNo(List(float4, float5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.hasNo(List(float5, float6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.floatMap_.hasNo(List.empty[Float]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "Optional map (no filter)" - types { implicit conn =>
        val a = (1, Some(Map("a" -> float1, "b" -> float2)))
        val b = (2, Some(Map("a" -> float2, "b" -> float3, "c" -> float4)))
        val c = (3, None)
        for {
          _ <- Ns.i.floatMap_?.insert(a, b, c).transact
          _ <- Ns.i.a1.floatMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>

        for {
          _ <- Ns.i.floatMap.insert(a, b).transact
          _ <- Ns.i(3).save.transact // entity without floatMap

          // Like calling `get` on a Scala Map.
          _ <- Ns.i.a1.floatMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.floatMap_?("a").query.get.map(_ ==> List((1, Some(float1)), (2, Some(float2)), (3, None)))
          _ <- Ns.i.a1.floatMap_?("b").query.get.map(_ ==> List((1, Some(float2)), (2, Some(float3)), (3, None)))
          _ <- Ns.i.a1.floatMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(float4)), (3, None)))
        } yield ()
      }
    }
  }
}