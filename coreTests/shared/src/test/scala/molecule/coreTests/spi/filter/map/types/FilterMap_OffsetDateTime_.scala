// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import java.time.OffsetDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_OffsetDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> offsetDateTime1, "b" -> offsetDateTime2))
        val b = (2, Map("a" -> offsetDateTime2, "b" -> offsetDateTime3, "c" -> offsetDateTime4))
        for {
          _ <- Ns.i.offsetDateTimeMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.offsetDateTimeMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val offsetDateTime: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> offsetDateTime1, "b" -> offsetDateTime2))
        val b = (2, Map("a" -> offsetDateTime2, "b" -> offsetDateTime3, "c" -> offsetDateTime4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.offsetDateTimeMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.offsetDateTimeMap("-").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.offsetDateTimeMap("a").query.get.map(_ ==> List((1, offsetDateTime1), (2, offsetDateTime2)))
          _ <- Ns.i.a1.offsetDateTimeMap("b").query.get.map(_ ==> List((1, offsetDateTime2), (2, offsetDateTime3)))
          _ <- Ns.i.a1.offsetDateTimeMap("c").query.get.map(_ ==> List((2, offsetDateTime4)))
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> offsetDateTime1, "b" -> offsetDateTime2)))
        val b = (2, Some(Map("a" -> offsetDateTime2, "b" -> offsetDateTime3, "c" -> offsetDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetDateTimeMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.offsetDateTimeMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[OffsetDateTime] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> offsetDateTime1, "b" -> offsetDateTime2)))
        val b = (2, Some(Map("a" -> offsetDateTime2, "b" -> offsetDateTime3, "c" -> offsetDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetDateTimeMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.offsetDateTimeMap_?("-").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.offsetDateTimeMap_?("a").query.get.map(_ ==> List((1, Some(offsetDateTime1)), (2, Some(offsetDateTime2)), (3, None)))
          _ <- Ns.i.a1.offsetDateTimeMap_?("b").query.get.map(_ ==> List((1, Some(offsetDateTime2)), (2, Some(offsetDateTime3)), (3, None)))
          _ <- Ns.i.a1.offsetDateTimeMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(offsetDateTime4)), (3, None)))
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

      val (a1, b2) = ("a" -> offsetDateTime1, "b" -> offsetDateTime2)
      val (b3, c4) = ("b" -> offsetDateTime3, "c" -> offsetDateTime4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetDateTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // offsetDateTimeMap not asserted for i = 0
          _ <- Ns.i.a1.offsetDateTimeMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.offsetDateTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.offsetDateTimeMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetDateTimeMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetDateTimeMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeMap_(List("a", "-")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.offsetDateTimeMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.offsetDateTimeMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_("-").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_("a", "-").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.offsetDateTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.offsetDateTimeMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetDateTimeMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetDateTimeMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetDateTimeMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetDateTimeMap_.not(List("a", "-")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.offsetDateTimeMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.not("-").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.not("a", "-").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.offsetDateTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.offsetDateTimeMap_.has(offsetDateTime0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetDateTimeMap_.has(offsetDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeMap_.has(offsetDateTime2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeMap_.has(offsetDateTime3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeMap_.has(offsetDateTime4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeMap_.has(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeMap_.has(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeMap_.has(offsetDateTime3, offsetDateTime4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeMap_.has(List(offsetDateTime0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetDateTimeMap_.has(List(offsetDateTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeMap_.has(List(offsetDateTime2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeMap_.has(List(offsetDateTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeMap_.has(List(offsetDateTime4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeMap_.has(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeMap_.has(List(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeMap_.has(List(offsetDateTime3, offsetDateTime4)).query.get.map(_ ==> List(2))

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.offsetDateTimeMap_.has(List.empty[OffsetDateTime]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.has(offsetDateTime0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.has(offsetDateTime1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.has(offsetDateTime2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.has(offsetDateTime3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.has(offsetDateTime4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.has(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.has(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.has(offsetDateTime3, offsetDateTime4).query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.offsetDateTimeMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.offsetDateTimeMap_.hasNo(offsetDateTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeMap_.hasNo(offsetDateTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeMap_.hasNo(offsetDateTime2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeMap_.hasNo(offsetDateTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeMap_.hasNo(offsetDateTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeMap_.hasNo(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeMap_.hasNo(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeMap_.hasNo(offsetDateTime3, offsetDateTime4).query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeMap_.hasNo(List(offsetDateTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeMap_.hasNo(List(offsetDateTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeMap_.hasNo(List(offsetDateTime2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeMap_.hasNo(List(offsetDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeMap_.hasNo(List(offsetDateTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeMap_.hasNo(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeMap_.hasNo(List(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeMap_.hasNo(List(offsetDateTime3, offsetDateTime4)).query.get.map(_ ==> List(1))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.offsetDateTimeMap_.hasNo(List.empty[OffsetDateTime]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.hasNo(offsetDateTime0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.hasNo(offsetDateTime1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.hasNo(offsetDateTime2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.hasNo(offsetDateTime3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.hasNo(offsetDateTime4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.hasNo(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.hasNo(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.offsetDateTimeMap.offsetDateTimeMap_.hasNo(offsetDateTime3, offsetDateTime4).query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }
    }
  }
}