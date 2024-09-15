// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import java.time.OffsetTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_OffsetTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> offsetTime1, "b" -> offsetTime2))
        val b = (2, Map("a" -> offsetTime2, "b" -> offsetTime3, "c" -> offsetTime4))
        for {
          _ <- Ns.i.offsetTimeMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.offsetTimeMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val offsetTime: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> offsetTime1, "b" -> offsetTime2))
        val b = (2, Map("a" -> offsetTime2, "b" -> offsetTime3, "c" -> offsetTime4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.offsetTimeMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.offsetTimeMap("_").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.offsetTimeMap("a").query.get.map(_ ==> List((1, offsetTime1), (2, offsetTime2)))
          _ <- Ns.i.a1.offsetTimeMap("b").query.get.map(_ ==> List((1, offsetTime2), (2, offsetTime3)))
          _ <- Ns.i.a1.offsetTimeMap("c").query.get.map(_ ==> List((2, offsetTime4)))
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> offsetTime1, "b" -> offsetTime2)))
        val b = (2, Some(Map("a" -> offsetTime2, "b" -> offsetTime3, "c" -> offsetTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetTimeMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.offsetTimeMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[OffsetTime] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> offsetTime1, "b" -> offsetTime2)))
        val b = (2, Some(Map("a" -> offsetTime2, "b" -> offsetTime3, "c" -> offsetTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetTimeMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.offsetTimeMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.offsetTimeMap_?("a").query.get.map(_ ==> List((1, Some(offsetTime1)), (2, Some(offsetTime2)), (3, None)))
          _ <- Ns.i.a1.offsetTimeMap_?("b").query.get.map(_ ==> List((1, Some(offsetTime2)), (2, Some(offsetTime3)), (3, None)))
          _ <- Ns.i.a1.offsetTimeMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(offsetTime4)), (3, None)))
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

      val (a1, b2) = ("a" -> offsetTime1, "b" -> offsetTime2)
      val (b3, c4) = ("b" -> offsetTime3, "c" -> offsetTime4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // offsetTimeMap not asserted for i = 0
          _ <- Ns.i.a1.offsetTimeMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.offsetTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.offsetTimeMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetTimeMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.offsetTimeMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetTimeMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_(List("a", "_")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.offsetTimeMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.offsetTimeMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_("a", "_").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.offsetTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.offsetTimeMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetTimeMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetTimeMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetTimeMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetTimeMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetTimeMap_.not(List("a", "_")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.offsetTimeMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.not("_").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.not("a", "_").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.offsetTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime0, offsetTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime1, offsetTime2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime2, offsetTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime3, offsetTime4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime4, offsetTime5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime5, offsetTime6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime0, offsetTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime2, offsetTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime3, offsetTime4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime4, offsetTime5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime5, offsetTime6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.offsetTimeMap_.has(List.empty[OffsetTime]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.has(offsetTime0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.has(offsetTime1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.has(offsetTime2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.has(offsetTime3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.has(offsetTime4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.has(offsetTime0, offsetTime1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.has(offsetTime1, offsetTime2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.has(offsetTime2, offsetTime3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.has(offsetTime3, offsetTime4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.has(offsetTime4, offsetTime5).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.has(offsetTime5, offsetTime6).query.get.map(_ ==> List())
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.offsetTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime0, offsetTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime1, offsetTime2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime2, offsetTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime3, offsetTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime4, offsetTime5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime5, offsetTime6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime0, offsetTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime2, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime3, offsetTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime4, offsetTime5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime5, offsetTime6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List.empty[OffsetTime]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.hasNo(offsetTime0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.hasNo(offsetTime1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.hasNo(offsetTime2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.hasNo(offsetTime3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.hasNo(offsetTime4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.hasNo(offsetTime0, offsetTime1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.hasNo(offsetTime1, offsetTime2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.hasNo(offsetTime2, offsetTime3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.hasNo(offsetTime3, offsetTime4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.hasNo(offsetTime4, offsetTime5).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.offsetTimeMap.offsetTimeMap_.hasNo(offsetTime5, offsetTime6).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
        } yield ()
      }
    }
  }
}