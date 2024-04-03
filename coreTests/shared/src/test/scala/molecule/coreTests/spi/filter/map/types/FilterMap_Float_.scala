// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_Float_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> float1, "b" -> float2))
        val b = (2, Map("a" -> float2, "b" -> float3, "c" -> float4))
        for {
          _ <- Ns.i.floatMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.floatMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val float: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> float1, "b" -> float2))
        val b = (2, Map("a" -> float2, "b" -> float3, "c" -> float4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.floatMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.floatMap("_").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.floatMap("a").query.get.map(_ ==> List((1, float1), (2, float2)))
          _ <- Ns.i.a1.floatMap("b").query.get.map(_ ==> List((1, float2), (2, float3)))
          _ <- Ns.i.a1.floatMap("c").query.get.map(_ ==> List((2, float4)))
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> float1, "b" -> float2)))
        val b = (2, Some(Map("a" -> float2, "b" -> float3, "c" -> float4)))
        val c = (3, None)
        for {
          _ <- Ns.i.floatMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.floatMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[Float] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> float1, "b" -> float2)))
        val b = (2, Some(Map("a" -> float2, "b" -> float3, "c" -> float4)))
        val c = (3, None)
        for {
          _ <- Ns.i.floatMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.floatMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.floatMap_?("a").query.get.map(_ ==> List((1, Some(float1)), (2, Some(float2)), (3, None)))
          _ <- Ns.i.a1.floatMap_?("b").query.get.map(_ ==> List((1, Some(float2)), (2, Some(float3)), (3, None)))
          _ <- Ns.i.a1.floatMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(float4)), (3, None)))
        } yield ()
      }
    }


    "Tacit" - {
      // Use tacit map attribute to filter by keys/values of Maps
      // Use mandatory/optional map attributes to retrieve Maps/keys/values
      // Combine multiple map attributes if both retrieval and filtering is required

      // OBS: Note have tacit map attribute methods `apply` and `not` have OR semantics! 
      // (contrary to Set and Seq that have AND semantics)
      // This is to have the same semantics for searching by key (apply/not) and value (has/hasNo)
      // Also to avoid adding further restricted molecule keywords. 

      val (a1, b2) = ("a" -> float1, "b" -> float2)
      val (b3, c4) = ("b" -> float3, "c" -> float4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.floatMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // floatMap not asserted for i = 0
          _ <- Ns.i.a1.floatMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.floatMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.floatMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.floatMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.floatMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.floatMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_(List("a", "_")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.floatMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.floatMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.floatMap.floatMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.floatMap.floatMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.floatMap.floatMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.floatMap.floatMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.floatMap.floatMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.floatMap.floatMap_("a", "_").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.floatMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.floatMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.floatMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.floatMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.floatMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.floatMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.floatMap_.not(List("a", "_")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.floatMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.floatMap.floatMap_.not("_").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.floatMap.floatMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.floatMap.floatMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.floatMap.floatMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.floatMap.floatMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.floatMap.floatMap_.not("a", "_").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.floatMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.floatMap_.has(float0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.floatMap_.has(float1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.has(float2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.has(float3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.has(float4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.has(float0, float1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.has(float1, float2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.has(float2, float3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_.has(float3, float4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.has(float4, float5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.has(float5, float6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.floatMap_.has(List(float0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.floatMap_.has(List(float1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.has(List(float2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.has(List(float3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.has(List(float4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.has(List(float0, float1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.has(List(float1, float2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.has(List(float2, float3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_.has(List(float3, float4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.has(List(float4, float5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.has(List(float5, float6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.floatMap_.has(List.empty[Float]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.floatMap.floatMap_.has(float0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.floatMap.floatMap_.has(float1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.floatMap.floatMap_.has(float2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.floatMap.floatMap_.has(float3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.floatMap.floatMap_.has(float4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.floatMap.floatMap_.has(float0, float1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.floatMap.floatMap_.has(float1, float2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.floatMap.floatMap_.has(float2, float3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.floatMap.floatMap_.has(float3, float4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.floatMap.floatMap_.has(float4, float5).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.floatMap.floatMap_.has(float5, float6).query.get.map(_ ==> List())
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.floatMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.floatMap_.hasNo(float0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_.hasNo(float1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.hasNo(float2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.hasNo(float3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.hasNo(float4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.hasNo(float0, float1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.hasNo(float1, float2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.hasNo(float2, float3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap_.hasNo(float3, float4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.hasNo(float4, float5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.hasNo(float5, float6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.floatMap_.hasNo(List(float0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatMap_.hasNo(List(float1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.hasNo(List(float2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.hasNo(List(float3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.hasNo(List(float4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.hasNo(List(float0, float1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.hasNo(List(float1, float2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatMap_.hasNo(List(float2, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatMap_.hasNo(List(float3, float4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.hasNo(List(float4, float5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatMap_.hasNo(List(float5, float6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.floatMap_.hasNo(List.empty[Float]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.floatMap.floatMap_.hasNo(float0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.floatMap.floatMap_.hasNo(float1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.floatMap.floatMap_.hasNo(float2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.floatMap.floatMap_.hasNo(float3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.floatMap.floatMap_.hasNo(float4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.floatMap.floatMap_.hasNo(float0, float1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.floatMap.floatMap_.hasNo(float1, float2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.floatMap.floatMap_.hasNo(float2, float3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.floatMap.floatMap_.hasNo(float3, float4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.floatMap.floatMap_.hasNo(float4, float5).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.floatMap.floatMap_.hasNo(float5, float6).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
        } yield ()
      }
    }
  }
}