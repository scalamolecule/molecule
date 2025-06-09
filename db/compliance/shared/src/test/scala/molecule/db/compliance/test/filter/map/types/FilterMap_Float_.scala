// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.map.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterMap_Float_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Map("a" -> float1, "b" -> float2))
  val b = (2, Map("a" -> float2, "b" -> float3, "c" -> float4))

  import api.*
  import suite.*


  "Mandatory: Mandatory map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.floatMap.insert(a, b).transact
      _ <- Entity.i.a1.floatMap.query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.floatMap.insert(a, b).transact

      // Get Map value by key

      // Like calling `apply` on a Scala Map.
      _ <- Entity.i.a1.floatMap("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap("a").query.get.map(_ ==> List((1, float1), (2, float2)))
      _ <- Entity.i.a1.floatMap("b").query.get.map(_ ==> List((1, float2), (2, float3)))
      _ <- Entity.i.a1.floatMap("c").query.get.map(_ ==> List((2, float4)))
    } yield ()
  }


  "Mandatory: Map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.floatMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.floatMap.not("_").query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatMap.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap.not("c").query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatMap.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap.not("_", "c").query.get.map(_ ==> List(a))
      // Same as
      _ <- Entity.i.a1.floatMap.not(List("_")).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatMap.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap.not(List("c")).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatMap.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap.not(List("_", "c")).query.get.map(_ ==> List(a))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.floatMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.floatMap.insert(a, b).transact

      // Maps containing value

      _ <- Entity.i.a1.floatMap.has(float0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap.has(float1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatMap.has(float2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatMap.has(float3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.floatMap.has(float4).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.floatMap.has(Seq(float0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap.has(Seq(float1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatMap.has(Seq(float2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatMap.has(Seq(float3)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.floatMap.has(Seq(float4)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that" value
      _ <- Entity.i.a1.floatMap.has(float0, float1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatMap.has(float1, float2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatMap.has(float2, float3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatMap.has(float3, float4).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.floatMap.has(float4, float5).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.floatMap.has(float5, float6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.floatMap.has(Seq(float0, float1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatMap.has(Seq(float1, float2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatMap.has(Seq(float2, float3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatMap.has(Seq(float3, float4)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.floatMap.has(Seq(float4, float5)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.floatMap.has(Seq(float5, float6)).query.get.map(_ ==> List())

      // No values match nothing
      _ <- Entity.i.a1.floatMap.has(Seq.empty[Float]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: Map without certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.floatMap.insert(a, b).transact

      // "Doesn't have this"
      _ <- Entity.i.a1.floatMap.hasNo(float0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatMap.hasNo(float1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.floatMap.hasNo(float2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap.hasNo(float3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatMap.hasNo(float3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatMap.hasNo(float5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.floatMap.hasNo(List(float0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatMap.hasNo(List(float1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.floatMap.hasNo(List(float2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap.hasNo(List(float3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatMap.hasNo(List(float3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatMap.hasNo(List(float5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.floatMap.hasNo(float1, float2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap.hasNo(float1, float3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap.hasNo(float1, float3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap.hasNo(float1, float5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.floatMap.hasNo(List(float1, float2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap.hasNo(List(float1, float3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap.hasNo(List(float1, float3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap.hasNo(List(float1, float5)).query.get.map(_ ==> List(b))

      // No values match nothing
      _ <- Entity.i.a1.floatMap.hasNo(List.empty[Float]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: Tacit Map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.floatMap.insert(a, b).transact
      _ <- Entity.i.a1.floatMap_.query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.insert(0).transact // Entity without map attribute
      _ <- Entity.i.floatMap.insert(a, b).transact

      // "Map contains this OR that key"
      _ <- Entity.i.a1.floatMap_("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap_("a").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatMap_("b").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatMap_("c").query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.floatMap_("a", "c").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatMap_("_", "c").query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.floatMap_(List("_")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap_(List("a")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatMap_(List("b")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatMap_(List("c")).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.floatMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatMap_(List("_", "c")).query.get.map(_ ==> List(2))

      // Empty Seq of keys matches nothing
      _ <- Entity.i.a1.floatMap_(List.empty[String]).query.get.map(_ ==> List())

      // Match entities without map attribute
      _ <- Entity.i.a1.floatMap_().query.get.map(_ ==> List(0))
    } yield ()
  }


  "Tacit: Match map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.floatMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.floatMap_.not("_").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatMap_.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap_.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap_.not("c").query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatMap_.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap_.not("_", "c").query.get.map(_ ==> List(1))
      // Same as
      _ <- Entity.i.a1.floatMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatMap_.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap_.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap_.not(List("c")).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatMap_.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.floatMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.floatMap.insert(a, b).transact

      // "Map contains this OR that value"
      _ <- Entity.i.a1.floatMap_.has(float0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap_.has(float1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatMap_.has(float2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatMap_.has(float3).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.floatMap_.has(float4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.floatMap_.has(float0, float1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatMap_.has(float1, float2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatMap_.has(float2, float3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatMap_.has(float3, float4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.floatMap_.has(float4, float5).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.floatMap_.has(float5, float6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.floatMap_.has(List(float0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap_.has(List(float1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatMap_.has(List(float2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatMap_.has(List(float3)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.floatMap_.has(List(float4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.floatMap_.has(List(float0, float1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatMap_.has(List(float1, float2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatMap_.has(List(float2, float3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatMap_.has(List(float3, float4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.floatMap_.has(List(float4, float5)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.floatMap_.has(List(float5, float6)).query.get.map(_ ==> List())

      // Empty Seq of values matches nothing
      _ <- Entity.i.a1.floatMap_.has(List.empty[Float]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: Match map without certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.floatMap.insert(a, b).transact

      // "Map contains neither this OR that value"
      _ <- Entity.i.a1.floatMap_.hasNo(float0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatMap_.hasNo(float1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.floatMap_.hasNo(float2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap_.hasNo(float3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatMap_.hasNo(float4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatMap_.hasNo(float0, float1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.floatMap_.hasNo(float1, float2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap_.hasNo(float2, float3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap_.hasNo(float3, float4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatMap_.hasNo(float4, float5).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatMap_.hasNo(float5, float6).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.floatMap_.hasNo(List(float0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatMap_.hasNo(List(float1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.floatMap_.hasNo(List(float2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap_.hasNo(List(float3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatMap_.hasNo(List(float4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatMap_.hasNo(List(float0, float1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.floatMap_.hasNo(List(float1, float2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap_.hasNo(List(float2, float3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatMap_.hasNo(List(float3, float4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatMap_.hasNo(List(float4, float5)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatMap_.hasNo(List(float5, float6)).query.get.map(_ ==> List(1, 2))

      // Negating empty Seq of values matches all
      _ <- Entity.i.a1.floatMap_.hasNo(List.empty[Float]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Optional map (no filter)" - types { implicit conn =>
    val a = (1, Some(Map("a" -> float1, "b" -> float2)))
    val b = (2, Some(Map("a" -> float2, "b" -> float3, "c" -> float4)))
    val c = (3, None)
    for {
      _ <- Entity.i.floatMap_?.insert(a, b, c).transact
      _ <- Entity.i.a1.floatMap_?.query.get.map(_ ==> List(a, b, c))
    } yield ()
  }


  "Optional map values by key" - types { implicit conn =>

    for {
      _ <- Entity.i.floatMap.insert(a, b).transact
      _ <- Entity.i(3).save.transact // entity without floatMap

      // Like calling `get` on a Scala Map.
      _ <- Entity.i.a1.floatMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
      _ <- Entity.i.a1.floatMap_?("a").query.get.map(_ ==> List((1, Some(float1)), (2, Some(float2)), (3, None)))
      _ <- Entity.i.a1.floatMap_?("b").query.get.map(_ ==> List((1, Some(float2)), (2, Some(float3)), (3, None)))
      _ <- Entity.i.a1.floatMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(float4)), (3, None)))
    } yield ()
  }
}