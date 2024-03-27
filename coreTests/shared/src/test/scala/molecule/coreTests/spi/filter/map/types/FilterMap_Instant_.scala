// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import java.time.Instant
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_Instant_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> instant1, "b" -> instant2))
        val b = (2, Map("a" -> instant2, "b" -> instant3, "c" -> instant4))
        for {
          _ <- Ns.i.instantMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.instantMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val instant: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> instant1, "b" -> instant2))
        val b = (2, Map("a" -> instant2, "b" -> instant3, "c" -> instant4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.instantMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.instantMap("-").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.instantMap("a").query.get.map(_ ==> List((1, instant1), (2, instant2)))
          _ <- Ns.i.a1.instantMap("b").query.get.map(_ ==> List((1, instant2), (2, instant3)))
          _ <- Ns.i.a1.instantMap("c").query.get.map(_ ==> List((2, instant4)))

          // Applying nothing to mandatory map attribute matches nothing,
          // not even entities without map attributes
          _ <- Ns.i.a1.instantMap().query.get.map(_ ==> Nil)
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> instant1, "b" -> instant2)))
        val b = (2, Some(Map("a" -> instant2, "b" -> instant3, "c" -> instant4)))
        val c = (3, None)
        for {
          _ <- Ns.i.instantMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.instantMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[Instant] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> instant1, "b" -> instant2)))
        val b = (2, Some(Map("a" -> instant2, "b" -> instant3, "c" -> instant4)))
        val c = (3, None)
        for {
          _ <- Ns.i.instantMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.instantMap_?("-").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.instantMap_?("a").query.get.map(_ ==> List((1, Some(instant1)), (2, Some(instant2)), (3, None)))
          _ <- Ns.i.a1.instantMap_?("b").query.get.map(_ ==> List((1, Some(instant2)), (2, Some(instant3)), (3, None)))
          _ <- Ns.i.a1.instantMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(instant4)), (3, None)))
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

      val (a1, b2) = ("a" -> instant1, "b" -> instant2)
      val (b3, c4) = ("b" -> instant3, "c" -> instant4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.instantMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // instantMap not asserted for i = 0
          _ <- Ns.i.a1.instantMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.instantMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.instantMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.instantMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.instantMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.instantMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantMap_(List("a", "-")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.instantMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.instantMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.instantMap.instantMap_("-").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.instantMap.instantMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.instantMap.instantMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.instantMap.instantMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.instantMap.instantMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.instantMap.instantMap_("a", "-").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.instantMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.instantMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.instantMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.instantMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.instantMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.instantMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.instantMap_.not(List("a", "-")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.instantMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.instantMap.instantMap_.not("-").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.instantMap.instantMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.instantMap.instantMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.instantMap.instantMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.instantMap.instantMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.instantMap.instantMap_.not("a", "-").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.instantMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.instantMap_.has(instant0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.instantMap_.has(instant1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantMap_.has(instant2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantMap_.has(instant3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantMap_.has(instant4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantMap_.has(instant1, instant2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantMap_.has(instant2, instant3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantMap_.has(instant3, instant4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.instantMap_.has(List(instant0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.instantMap_.has(List(instant1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantMap_.has(List(instant2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantMap_.has(List(instant3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantMap_.has(List(instant4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantMap_.has(List(instant1, instant2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantMap_.has(List(instant2, instant3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantMap_.has(List(instant3, instant4)).query.get.map(_ ==> List(2))

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.instantMap_.has(List.empty[Instant]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.instantMap.instantMap_.has(instant0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.instantMap.instantMap_.has(instant1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.instantMap.instantMap_.has(instant2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.instantMap.instantMap_.has(instant3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.instantMap.instantMap_.has(instant4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.instantMap.instantMap_.has(instant1, instant2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.instantMap.instantMap_.has(instant2, instant3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.instantMap.instantMap_.has(instant3, instant4).query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.instantMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.instantMap_.hasNo(instant0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantMap_.hasNo(instant1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantMap_.hasNo(instant2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantMap_.hasNo(instant3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantMap_.hasNo(instant4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantMap_.hasNo(instant1, instant2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantMap_.hasNo(instant2, instant3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantMap_.hasNo(instant3, instant4).query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.instantMap_.hasNo(List(instant0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantMap_.hasNo(List(instant1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantMap_.hasNo(List(instant2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantMap_.hasNo(List(instant3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantMap_.hasNo(List(instant4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantMap_.hasNo(List(instant1, instant2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantMap_.hasNo(List(instant2, instant3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantMap_.hasNo(List(instant3, instant4)).query.get.map(_ ==> List(1))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.instantMap_.hasNo(List.empty[Instant]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.instantMap.instantMap_.hasNo(instant0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.instantMap.instantMap_.hasNo(instant1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.instantMap.instantMap_.hasNo(instant2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.instantMap.instantMap_.hasNo(instant3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.instantMap.instantMap_.hasNo(instant4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.instantMap.instantMap_.hasNo(instant1, instant2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.instantMap.instantMap_.hasNo(instant2, instant3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.instantMap.instantMap_.hasNo(instant3, instant4).query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }
    }
  }
}