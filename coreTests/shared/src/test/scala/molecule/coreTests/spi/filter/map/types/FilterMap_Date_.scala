// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import java.util.Date
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_Date_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> date1, "b" -> date2))
        val b = (2, Map("a" -> date2, "b" -> date3, "c" -> date4))
        for {
          _ <- Ns.i.dateMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.dateMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val date: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> date1, "b" -> date2))
        val b = (2, Map("a" -> date2, "b" -> date3, "c" -> date4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.dateMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.dateMap("_").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.dateMap("a").query.get.map(_ ==> List((1, date1), (2, date2)))
          _ <- Ns.i.a1.dateMap("b").query.get.map(_ ==> List((1, date2), (2, date3)))
          _ <- Ns.i.a1.dateMap("c").query.get.map(_ ==> List((2, date4)))
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> date1, "b" -> date2)))
        val b = (2, Some(Map("a" -> date2, "b" -> date3, "c" -> date4)))
        val c = (3, None)
        for {
          _ <- Ns.i.dateMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.dateMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[Date] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> date1, "b" -> date2)))
        val b = (2, Some(Map("a" -> date2, "b" -> date3, "c" -> date4)))
        val c = (3, None)
        for {
          _ <- Ns.i.dateMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.dateMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.dateMap_?("a").query.get.map(_ ==> List((1, Some(date1)), (2, Some(date2)), (3, None)))
          _ <- Ns.i.a1.dateMap_?("b").query.get.map(_ ==> List((1, Some(date2)), (2, Some(date3)), (3, None)))
          _ <- Ns.i.a1.dateMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(date4)), (3, None)))
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

      val (a1, b2) = ("a" -> date1, "b" -> date2)
      val (b3, c4) = ("b" -> date3, "c" -> date4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.dateMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // dateMap not asserted for i = 0
          _ <- Ns.i.a1.dateMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.dateMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.dateMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.dateMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.dateMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.dateMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateMap_(List("a", "_")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.dateMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.dateMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.dateMap.dateMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.dateMap.dateMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.dateMap.dateMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.dateMap.dateMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.dateMap.dateMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.dateMap.dateMap_("a", "_").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.dateMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.dateMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.dateMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.dateMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.dateMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.dateMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.dateMap_.not(List("a", "_")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.dateMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.dateMap.dateMap_.not("_").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.dateMap.dateMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.dateMap.dateMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.dateMap.dateMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.dateMap.dateMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.dateMap.dateMap_.not("a", "_").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.dateMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.dateMap_.has(date0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.dateMap_.has(date1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateMap_.has(date2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateMap_.has(date3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateMap_.has(date4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateMap_.has(date0, date1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateMap_.has(date1, date2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateMap_.has(date2, date3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateMap_.has(date3, date4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateMap_.has(date4, date5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateMap_.has(date5, date6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dateMap_.has(List(date0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.dateMap_.has(List(date1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateMap_.has(List(date2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateMap_.has(List(date3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateMap_.has(List(date4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateMap_.has(List(date0, date1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateMap_.has(List(date1, date2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateMap_.has(List(date2, date3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateMap_.has(List(date3, date4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateMap_.has(List(date4, date5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateMap_.has(List(date5, date6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.dateMap_.has(List.empty[Date]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.dateMap.dateMap_.has(date0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.dateMap.dateMap_.has(date1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.dateMap.dateMap_.has(date2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.dateMap.dateMap_.has(date3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.dateMap.dateMap_.has(date4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.dateMap.dateMap_.has(date0, date1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.dateMap.dateMap_.has(date1, date2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.dateMap.dateMap_.has(date2, date3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.dateMap.dateMap_.has(date3, date4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.dateMap.dateMap_.has(date4, date5).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.dateMap.dateMap_.has(date5, date6).query.get.map(_ ==> List())
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.dateMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.dateMap_.hasNo(date0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateMap_.hasNo(date1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateMap_.hasNo(date2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateMap_.hasNo(date3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateMap_.hasNo(date4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateMap_.hasNo(date0, date1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateMap_.hasNo(date1, date2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateMap_.hasNo(date2, date3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateMap_.hasNo(date3, date4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateMap_.hasNo(date4, date5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateMap_.hasNo(date5, date6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.dateMap_.hasNo(List(date0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateMap_.hasNo(List(date1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateMap_.hasNo(List(date2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateMap_.hasNo(List(date3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateMap_.hasNo(List(date4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateMap_.hasNo(List(date0, date1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateMap_.hasNo(List(date1, date2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateMap_.hasNo(List(date2, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateMap_.hasNo(List(date3, date4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateMap_.hasNo(List(date4, date5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateMap_.hasNo(List(date5, date6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.dateMap_.hasNo(List.empty[Date]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.dateMap.dateMap_.hasNo(date0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.dateMap.dateMap_.hasNo(date1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.dateMap.dateMap_.hasNo(date2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.dateMap.dateMap_.hasNo(date3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.dateMap.dateMap_.hasNo(date4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.dateMap.dateMap_.hasNo(date0, date1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.dateMap.dateMap_.hasNo(date1, date2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.dateMap.dateMap_.hasNo(date2, date3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.dateMap.dateMap_.hasNo(date3, date4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.dateMap.dateMap_.hasNo(date4, date5).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.dateMap.dateMap_.hasNo(date5, date6).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
        } yield ()
      }
    }
  }
}