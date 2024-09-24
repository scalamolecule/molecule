// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import java.time.LocalDate
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_LocalDate_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> localDate1, "b" -> localDate2))
        val b = (2, Map("a" -> localDate2, "b" -> localDate3, "c" -> localDate4))
        for {
          _ <- Ns.i.localDateMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.localDateMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val localDate: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> localDate1, "b" -> localDate2))
        val b = (2, Map("a" -> localDate2, "b" -> localDate3, "c" -> localDate4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.localDateMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.localDateMap("_").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.localDateMap("a").query.get.map(_ ==> List((1, localDate1), (2, localDate2)))
          _ <- Ns.i.a1.localDateMap("b").query.get.map(_ ==> List((1, localDate2), (2, localDate3)))
          _ <- Ns.i.a1.localDateMap("c").query.get.map(_ ==> List((2, localDate4)))
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> localDate1, "b" -> localDate2)))
        val b = (2, Some(Map("a" -> localDate2, "b" -> localDate3, "c" -> localDate4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localDateMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.localDateMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[LocalDate] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> localDate1, "b" -> localDate2)))
        val b = (2, Some(Map("a" -> localDate2, "b" -> localDate3, "c" -> localDate4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localDateMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.localDateMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.localDateMap_?("a").query.get.map(_ ==> List((1, Some(localDate1)), (2, Some(localDate2)), (3, None)))
          _ <- Ns.i.a1.localDateMap_?("b").query.get.map(_ ==> List((1, Some(localDate2)), (2, Some(localDate3)), (3, None)))
          _ <- Ns.i.a1.localDateMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(localDate4)), (3, None)))
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

      val (a1, b2) = ("a" -> localDate1, "b" -> localDate2)
      val (b3, c4) = ("b" -> localDate3, "c" -> localDate4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localDateMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // localDateMap not asserted for i = 0
          _ <- Ns.i.a1.localDateMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.localDateMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.localDateMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.localDateMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateMap_(List("a", "_")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.localDateMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.localDateMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.localDateMap.localDateMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateMap.localDateMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localDateMap.localDateMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateMap.localDateMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateMap.localDateMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateMap.localDateMap_("a", "_").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.localDateMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.localDateMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localDateMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateMap_.not(List("a", "_")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.localDateMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.localDateMap.localDateMap_.not("_").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateMap.localDateMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateMap.localDateMap_.not("a", "_").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.localDateMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.localDateMap_.has(localDate0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateMap_.has(localDate1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateMap_.has(localDate2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateMap_.has(localDate3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateMap_.has(localDate4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateMap_.has(localDate0, localDate1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateMap_.has(localDate1, localDate2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateMap_.has(localDate2, localDate3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateMap_.has(localDate3, localDate4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateMap_.has(localDate4, localDate5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateMap_.has(localDate5, localDate6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localDateMap_.has(List(localDate0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateMap_.has(List(localDate1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateMap_.has(List(localDate2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateMap_.has(List(localDate3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateMap_.has(List(localDate4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateMap_.has(List(localDate0, localDate1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateMap_.has(List(localDate1, localDate2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateMap_.has(List(localDate2, localDate3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateMap_.has(List(localDate3, localDate4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateMap_.has(List(localDate4, localDate5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateMap_.has(List(localDate5, localDate6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.localDateMap_.has(List.empty[LocalDate]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.localDateMap.localDateMap_.has(localDate0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateMap.localDateMap_.has(localDate1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.has(localDate2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.has(localDate3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.has(localDate4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.has(localDate0, localDate1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.has(localDate1, localDate2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.has(localDate2, localDate3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.has(localDate3, localDate4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.has(localDate4, localDate5).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.has(localDate5, localDate6).query.get.map(_ ==> List())
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.localDateMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.localDateMap_.hasNo(localDate0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateMap_.hasNo(localDate1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateMap_.hasNo(localDate2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateMap_.hasNo(localDate3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateMap_.hasNo(localDate4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateMap_.hasNo(localDate0, localDate1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateMap_.hasNo(localDate1, localDate2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateMap_.hasNo(localDate2, localDate3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateMap_.hasNo(localDate3, localDate4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateMap_.hasNo(localDate4, localDate5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateMap_.hasNo(localDate5, localDate6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localDateMap_.hasNo(List(localDate0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateMap_.hasNo(List(localDate1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateMap_.hasNo(List(localDate2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateMap_.hasNo(List(localDate3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateMap_.hasNo(List(localDate4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateMap_.hasNo(List(localDate0, localDate1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateMap_.hasNo(List(localDate1, localDate2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateMap_.hasNo(List(localDate2, localDate3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateMap_.hasNo(List(localDate3, localDate4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateMap_.hasNo(List(localDate4, localDate5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateMap_.hasNo(List(localDate5, localDate6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.localDateMap_.hasNo(List.empty[LocalDate]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.localDateMap.localDateMap_.hasNo(localDate0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.hasNo(localDate1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.hasNo(localDate2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.hasNo(localDate3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.hasNo(localDate4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.hasNo(localDate0, localDate1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.hasNo(localDate1, localDate2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.hasNo(localDate2, localDate3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateMap.localDateMap_.hasNo(localDate3, localDate4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.hasNo(localDate4, localDate5).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localDateMap.localDateMap_.hasNo(localDate5, localDate6).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
        } yield ()
      }
    }
  }
}