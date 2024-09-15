// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import java.time.LocalDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_LocalDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> localDateTime1, "b" -> localDateTime2))
        val b = (2, Map("a" -> localDateTime2, "b" -> localDateTime3, "c" -> localDateTime4))
        for {
          _ <- Ns.i.localDateTimeMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.localDateTimeMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val localDateTime: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> localDateTime1, "b" -> localDateTime2))
        val b = (2, Map("a" -> localDateTime2, "b" -> localDateTime3, "c" -> localDateTime4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.localDateTimeMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.localDateTimeMap("_").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.localDateTimeMap("a").query.get.map(_ ==> List((1, localDateTime1), (2, localDateTime2)))
          _ <- Ns.i.a1.localDateTimeMap("b").query.get.map(_ ==> List((1, localDateTime2), (2, localDateTime3)))
          _ <- Ns.i.a1.localDateTimeMap("c").query.get.map(_ ==> List((2, localDateTime4)))
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> localDateTime1, "b" -> localDateTime2)))
        val b = (2, Some(Map("a" -> localDateTime2, "b" -> localDateTime3, "c" -> localDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localDateTimeMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.localDateTimeMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[LocalDateTime] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> localDateTime1, "b" -> localDateTime2)))
        val b = (2, Some(Map("a" -> localDateTime2, "b" -> localDateTime3, "c" -> localDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localDateTimeMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.localDateTimeMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.localDateTimeMap_?("a").query.get.map(_ ==> List((1, Some(localDateTime1)), (2, Some(localDateTime2)), (3, None)))
          _ <- Ns.i.a1.localDateTimeMap_?("b").query.get.map(_ ==> List((1, Some(localDateTime2)), (2, Some(localDateTime3)), (3, None)))
          _ <- Ns.i.a1.localDateTimeMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(localDateTime4)), (3, None)))
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

      val (a1, b2) = ("a" -> localDateTime1, "b" -> localDateTime2)
      val (b3, c4) = ("b" -> localDateTime3, "c" -> localDateTime4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localDateTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // localDateTimeMap not asserted for i = 0
          _ <- Ns.i.a1.localDateTimeMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.localDateTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.localDateTimeMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateTimeMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.localDateTimeMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateTimeMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_(List("a", "_")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.localDateTimeMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.localDateTimeMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_("a", "_").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.localDateTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.localDateTimeMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateTimeMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateTimeMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localDateTimeMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateTimeMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateTimeMap_.not(List("a", "_")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.localDateTimeMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.not("_").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.not("a", "_").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.localDateTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime0, localDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime1, localDateTime2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime2, localDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime3, localDateTime4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime4, localDateTime5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime5, localDateTime6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime0, localDateTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime1, localDateTime2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime2, localDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime3, localDateTime4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime4, localDateTime5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime5, localDateTime6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.localDateTimeMap_.has(List.empty[LocalDateTime]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.has(localDateTime0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.has(localDateTime1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.has(localDateTime2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.has(localDateTime3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.has(localDateTime4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.has(localDateTime0, localDateTime1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.has(localDateTime1, localDateTime2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.has(localDateTime2, localDateTime3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.has(localDateTime3, localDateTime4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.has(localDateTime4, localDateTime5).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.has(localDateTime5, localDateTime6).query.get.map(_ ==> List())
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.localDateTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime0, localDateTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime1, localDateTime2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime2, localDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime3, localDateTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime4, localDateTime5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime5, localDateTime6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime0, localDateTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime1, localDateTime2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime2, localDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime3, localDateTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime4, localDateTime5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime5, localDateTime6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List.empty[LocalDateTime]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.hasNo(localDateTime0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.hasNo(localDateTime1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.hasNo(localDateTime2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.hasNo(localDateTime3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.hasNo(localDateTime4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.hasNo(localDateTime0, localDateTime1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.hasNo(localDateTime1, localDateTime2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.hasNo(localDateTime2, localDateTime3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.hasNo(localDateTime3, localDateTime4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.hasNo(localDateTime4, localDateTime5).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.localDateTimeMap.localDateTimeMap_.hasNo(localDateTime5, localDateTime6).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
        } yield ()
      }
    }
  }
}