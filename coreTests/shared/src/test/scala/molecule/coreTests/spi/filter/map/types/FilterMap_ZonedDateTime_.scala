// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import java.time.ZonedDateTime
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_ZonedDateTime_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> zonedDateTime1, "b" -> zonedDateTime2))
        val b = (2, Map("a" -> zonedDateTime2, "b" -> zonedDateTime3, "c" -> zonedDateTime4))
        for {
          _ <- Ns.i.zonedDateTimeMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.zonedDateTimeMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val zonedDateTime: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> zonedDateTime1, "b" -> zonedDateTime2))
        val b = (2, Map("a" -> zonedDateTime2, "b" -> zonedDateTime3, "c" -> zonedDateTime4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.zonedDateTimeMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.zonedDateTimeMap("_").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.zonedDateTimeMap("a").query.get.map(_ ==> List((1, zonedDateTime1), (2, zonedDateTime2)))
          _ <- Ns.i.a1.zonedDateTimeMap("b").query.get.map(_ ==> List((1, zonedDateTime2), (2, zonedDateTime3)))
          _ <- Ns.i.a1.zonedDateTimeMap("c").query.get.map(_ ==> List((2, zonedDateTime4)))
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> zonedDateTime1, "b" -> zonedDateTime2)))
        val b = (2, Some(Map("a" -> zonedDateTime2, "b" -> zonedDateTime3, "c" -> zonedDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.zonedDateTimeMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.zonedDateTimeMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[ZonedDateTime] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> zonedDateTime1, "b" -> zonedDateTime2)))
        val b = (2, Some(Map("a" -> zonedDateTime2, "b" -> zonedDateTime3, "c" -> zonedDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.zonedDateTimeMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.zonedDateTimeMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.zonedDateTimeMap_?("a").query.get.map(_ ==> List((1, Some(zonedDateTime1)), (2, Some(zonedDateTime2)), (3, None)))
          _ <- Ns.i.a1.zonedDateTimeMap_?("b").query.get.map(_ ==> List((1, Some(zonedDateTime2)), (2, Some(zonedDateTime3)), (3, None)))
          _ <- Ns.i.a1.zonedDateTimeMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(zonedDateTime4)), (3, None)))
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

      val (a1, b2) = ("a" -> zonedDateTime1, "b" -> zonedDateTime2)
      val (b3, c4) = ("b" -> zonedDateTime3, "c" -> zonedDateTime4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.zonedDateTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // zonedDateTimeMap not asserted for i = 0
          _ <- Ns.i.a1.zonedDateTimeMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.zonedDateTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.zonedDateTimeMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.zonedDateTimeMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.zonedDateTimeMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_(List("a", "_")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.zonedDateTimeMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.zonedDateTimeMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_("a", "_").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.zonedDateTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.zonedDateTimeMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.zonedDateTimeMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.zonedDateTimeMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.zonedDateTimeMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.zonedDateTimeMap_.not(List("a", "_")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.zonedDateTimeMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.not("_").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.not("a", "_").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.zonedDateTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime0, zonedDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime3, zonedDateTime4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime4, zonedDateTime5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime5, zonedDateTime6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime0, zonedDateTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime3, zonedDateTime4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime4, zonedDateTime5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime5, zonedDateTime6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List.empty[ZonedDateTime]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.has(zonedDateTime0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.has(zonedDateTime1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.has(zonedDateTime2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.has(zonedDateTime3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.has(zonedDateTime4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.has(zonedDateTime0, zonedDateTime1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.has(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.has(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.has(zonedDateTime3, zonedDateTime4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.has(zonedDateTime4, zonedDateTime5).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.has(zonedDateTime5, zonedDateTime6).query.get.map(_ ==> List())
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.zonedDateTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime0, zonedDateTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime3, zonedDateTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime4, zonedDateTime5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime5, zonedDateTime6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime0, zonedDateTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime3, zonedDateTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime4, zonedDateTime5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime5, zonedDateTime6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List.empty[ZonedDateTime]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.hasNo(zonedDateTime0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.hasNo(zonedDateTime1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.hasNo(zonedDateTime2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.hasNo(zonedDateTime3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.hasNo(zonedDateTime4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.hasNo(zonedDateTime0, zonedDateTime1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.hasNo(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.hasNo(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.hasNo(zonedDateTime3, zonedDateTime4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.hasNo(zonedDateTime4, zonedDateTime5).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.zonedDateTimeMap.zonedDateTimeMap_.hasNo(zonedDateTime5, zonedDateTime6).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
        } yield ()
      }
    }
  }
}