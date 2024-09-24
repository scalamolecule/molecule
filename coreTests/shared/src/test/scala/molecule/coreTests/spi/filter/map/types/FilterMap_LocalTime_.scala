// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import java.time.LocalTime
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_LocalTime_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> localTime1, "b" -> localTime2))
        val b = (2, Map("a" -> localTime2, "b" -> localTime3, "c" -> localTime4))
        for {
          _ <- Ns.i.localTimeMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.localTimeMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val localTime: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> localTime1, "b" -> localTime2))
        val b = (2, Map("a" -> localTime2, "b" -> localTime3, "c" -> localTime4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.localTimeMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.localTimeMap("_").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.localTimeMap("a").query.get.map(_ ==> List((1, localTime1), (2, localTime2)))
          _ <- Ns.i.a1.localTimeMap("b").query.get.map(_ ==> List((1, localTime2), (2, localTime3)))
          _ <- Ns.i.a1.localTimeMap("c").query.get.map(_ ==> List((2, localTime4)))
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> localTime1, "b" -> localTime2)))
        val b = (2, Some(Map("a" -> localTime2, "b" -> localTime3, "c" -> localTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localTimeMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.localTimeMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[LocalTime] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> localTime1, "b" -> localTime2)))
        val b = (2, Some(Map("a" -> localTime2, "b" -> localTime3, "c" -> localTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localTimeMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.localTimeMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.localTimeMap_?("a").query.get.map(_ ==> List((1, Some(localTime1)), (2, Some(localTime2)), (3, None)))
          _ <- Ns.i.a1.localTimeMap_?("b").query.get.map(_ ==> List((1, Some(localTime2)), (2, Some(localTime3)), (3, None)))
          _ <- Ns.i.a1.localTimeMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(localTime4)), (3, None)))
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

      val (a1, b2) = ("a" -> localTime1, "b" -> localTime2)
      val (b3, c4) = ("b" -> localTime3, "c" -> localTime4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // localTimeMap not asserted for i = 0
          _ <- Ns.i.a1.localTimeMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.localTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.localTimeMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localTimeMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.localTimeMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localTimeMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_(List("a", "_")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.localTimeMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.localTimeMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.localTimeMap.localTimeMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localTimeMap.localTimeMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_("a", "_").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.localTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.localTimeMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localTimeMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localTimeMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localTimeMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localTimeMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localTimeMap_.not(List("a", "_")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.localTimeMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.not("_").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.not("a", "_").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.localTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.localTimeMap_.has(localTime0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localTimeMap_.has(localTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.has(localTime2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.has(localTime3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.has(localTime4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.has(localTime0, localTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.has(localTime1, localTime2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.has(localTime2, localTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_.has(localTime3, localTime4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.has(localTime4, localTime5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.has(localTime5, localTime6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime0, localTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime1, localTime2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime2, localTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime3, localTime4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime4, localTime5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime5, localTime6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.localTimeMap_.has(List.empty[LocalTime]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.has(localTime0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.has(localTime1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.has(localTime2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.has(localTime3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.has(localTime4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.has(localTime0, localTime1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.has(localTime1, localTime2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.has(localTime2, localTime3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.has(localTime3, localTime4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.has(localTime4, localTime5).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.has(localTime5, localTime6).query.get.map(_ ==> List())
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.localTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime0, localTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime1, localTime2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime2, localTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime3, localTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime4, localTime5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime5, localTime6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime0, localTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime1, localTime2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime2, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime3, localTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime4, localTime5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime5, localTime6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.localTimeMap_.hasNo(List.empty[LocalTime]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.hasNo(localTime0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.hasNo(localTime1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.hasNo(localTime2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.hasNo(localTime3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.hasNo(localTime4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.hasNo(localTime0, localTime1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.hasNo(localTime1, localTime2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.hasNo(localTime2, localTime3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.hasNo(localTime3, localTime4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.hasNo(localTime4, localTime5).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localTimeMap.localTimeMap_.hasNo(localTime5, localTime6).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
        } yield ()
      }
    }
  }
}