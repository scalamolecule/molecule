// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import java.time.Duration
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_Duration_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> duration1, "b" -> duration2))
        val b = (2, Map("a" -> duration2, "b" -> duration3, "c" -> duration4))
        for {
          _ <- Ns.i.durationMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.durationMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val duration: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> duration1, "b" -> duration2))
        val b = (2, Map("a" -> duration2, "b" -> duration3, "c" -> duration4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.durationMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.durationMap("-").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.durationMap("a").query.get.map(_ ==> List((1, duration1), (2, duration2)))
          _ <- Ns.i.a1.durationMap("b").query.get.map(_ ==> List((1, duration2), (2, duration3)))
          _ <- Ns.i.a1.durationMap("c").query.get.map(_ ==> List((2, duration4)))

          // Applying nothing to mandatory map attribute matches nothing,
          // not even entities without map attributes
          _ <- Ns.i.a1.durationMap().query.get.map(_ ==> Nil)
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> duration1, "b" -> duration2)))
        val b = (2, Some(Map("a" -> duration2, "b" -> duration3, "c" -> duration4)))
        val c = (3, None)
        for {
          _ <- Ns.i.durationMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.durationMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[Duration] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> duration1, "b" -> duration2)))
        val b = (2, Some(Map("a" -> duration2, "b" -> duration3, "c" -> duration4)))
        val c = (3, None)
        for {
          _ <- Ns.i.durationMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.durationMap_?("-").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.durationMap_?("a").query.get.map(_ ==> List((1, Some(duration1)), (2, Some(duration2)), (3, None)))
          _ <- Ns.i.a1.durationMap_?("b").query.get.map(_ ==> List((1, Some(duration2)), (2, Some(duration3)), (3, None)))
          _ <- Ns.i.a1.durationMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(duration4)), (3, None)))
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

      val (a1, b2) = ("a" -> duration1, "b" -> duration2)
      val (b3, c4) = ("b" -> duration3, "c" -> duration4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.durationMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // durationMap not asserted for i = 0
          _ <- Ns.i.a1.durationMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.durationMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.durationMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.durationMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.durationMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.durationMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_(List("a", "-")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.durationMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.durationMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.durationMap.durationMap_("-").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.durationMap.durationMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.durationMap.durationMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.durationMap.durationMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.durationMap.durationMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.durationMap.durationMap_("a", "-").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.durationMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.durationMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.durationMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.durationMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.durationMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.durationMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.durationMap_.not(List("a", "-")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.durationMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.durationMap.durationMap_.not("-").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.durationMap.durationMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.durationMap.durationMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.durationMap.durationMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.durationMap.durationMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.durationMap.durationMap_.not("a", "-").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.durationMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.durationMap_.has(duration0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.durationMap_.has(duration1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.has(duration2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.has(duration3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.has(duration4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.has(duration1, duration2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.has(duration2, duration3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_.has(duration3, duration4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.durationMap_.has(List(duration0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.durationMap_.has(List(duration1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.has(List(duration2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.has(List(duration3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.has(List(duration4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.has(List(duration1, duration2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.has(List(duration2, duration3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_.has(List(duration3, duration4)).query.get.map(_ ==> List(2))

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.durationMap_.has(List.empty[Duration]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.durationMap.durationMap_.has(duration0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.durationMap.durationMap_.has(duration1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.durationMap.durationMap_.has(duration2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.durationMap.durationMap_.has(duration3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.durationMap.durationMap_.has(duration4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.durationMap.durationMap_.has(duration1, duration2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.durationMap.durationMap_.has(duration2, duration3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.durationMap.durationMap_.has(duration3, duration4).query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.durationMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.durationMap_.hasNo(duration0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_.hasNo(duration1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.hasNo(duration2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.hasNo(duration3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.hasNo(duration4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.hasNo(duration1, duration2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.hasNo(duration2, duration3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap_.hasNo(duration3, duration4).query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.durationMap_.hasNo(List(duration0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_.hasNo(List(duration1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.hasNo(List(duration2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.hasNo(List(duration3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.hasNo(List(duration4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.hasNo(List(duration1, duration2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.hasNo(List(duration2, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap_.hasNo(List(duration3, duration4)).query.get.map(_ ==> List(1))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.durationMap_.hasNo(List.empty[Duration]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.durationMap.durationMap_.hasNo(duration0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.durationMap.durationMap_.hasNo(duration1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.durationMap.durationMap_.hasNo(duration2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.durationMap.durationMap_.hasNo(duration3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.durationMap.durationMap_.hasNo(duration4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.durationMap.durationMap_.hasNo(duration1, duration2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.durationMap.durationMap_.hasNo(duration2, duration3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.durationMap.durationMap_.hasNo(duration3, duration4).query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }
    }
  }
}