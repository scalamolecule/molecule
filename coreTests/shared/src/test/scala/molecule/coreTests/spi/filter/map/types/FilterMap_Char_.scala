// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_Char_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> char1, "b" -> char2))
        val b = (2, Map("a" -> char2, "b" -> char3, "c" -> char4))
        for {
          _ <- Ns.i.charMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.charMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val char: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> char1, "b" -> char2))
        val b = (2, Map("a" -> char2, "b" -> char3, "c" -> char4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.charMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.charMap("-").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.charMap("a").query.get.map(_ ==> List((1, char1), (2, char2)))
          _ <- Ns.i.a1.charMap("b").query.get.map(_ ==> List((1, char2), (2, char3)))
          _ <- Ns.i.a1.charMap("c").query.get.map(_ ==> List((2, char4)))
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> char1, "b" -> char2)))
        val b = (2, Some(Map("a" -> char2, "b" -> char3, "c" -> char4)))
        val c = (3, None)
        for {
          _ <- Ns.i.charMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.charMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[Char] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> char1, "b" -> char2)))
        val b = (2, Some(Map("a" -> char2, "b" -> char3, "c" -> char4)))
        val c = (3, None)
        for {
          _ <- Ns.i.charMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.charMap_?("-").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.charMap_?("a").query.get.map(_ ==> List((1, Some(char1)), (2, Some(char2)), (3, None)))
          _ <- Ns.i.a1.charMap_?("b").query.get.map(_ ==> List((1, Some(char2)), (2, Some(char3)), (3, None)))
          _ <- Ns.i.a1.charMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(char4)), (3, None)))
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

      val (a1, b2) = ("a" -> char1, "b" -> char2)
      val (b3, c4) = ("b" -> char3, "c" -> char4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.charMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // charMap not asserted for i = 0
          _ <- Ns.i.a1.charMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.charMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.charMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.charMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.charMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.charMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_(List("a", "-")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.charMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.charMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.charMap.charMap_("-").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.charMap.charMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.charMap.charMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.charMap.charMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.charMap.charMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.charMap.charMap_("a", "-").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.charMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.charMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.charMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.charMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.charMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.charMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.charMap_.not(List("a", "-")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.charMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.charMap.charMap_.not("-").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.charMap.charMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.charMap.charMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.charMap.charMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.charMap.charMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.charMap.charMap_.not("a", "-").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.charMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.charMap_.has(char0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.charMap_.has(char1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.has(char2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.has(char3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.has(char4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.has(char1, char2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.has(char2, char3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_.has(char3, char4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.charMap_.has(List(char0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.charMap_.has(List(char1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.has(List(char2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.has(List(char3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.has(List(char4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.has(List(char1, char2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.has(List(char2, char3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_.has(List(char3, char4)).query.get.map(_ ==> List(2))

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.charMap_.has(List.empty[Char]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.charMap.charMap_.has(char0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.charMap.charMap_.has(char1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.charMap.charMap_.has(char2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.charMap.charMap_.has(char3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.charMap.charMap_.has(char4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.charMap.charMap_.has(char1, char2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.charMap.charMap_.has(char2, char3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.charMap.charMap_.has(char3, char4).query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.charMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.charMap_.hasNo(char0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_.hasNo(char1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.hasNo(char2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.hasNo(char3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.hasNo(char4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.hasNo(char1, char2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.hasNo(char2, char3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap_.hasNo(char3, char4).query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.charMap_.hasNo(List(char0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_.hasNo(List(char1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.hasNo(List(char2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.hasNo(List(char3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.hasNo(List(char4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.hasNo(List(char1, char2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.hasNo(List(char2, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap_.hasNo(List(char3, char4)).query.get.map(_ ==> List(1))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.charMap_.hasNo(List.empty[Char]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.charMap.charMap_.hasNo(char0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.charMap.charMap_.hasNo(char1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.charMap.charMap_.hasNo(char2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.charMap.charMap_.hasNo(char3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.charMap.charMap_.hasNo(char4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.charMap.charMap_.hasNo(char1, char2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.charMap.charMap_.hasNo(char2, char3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.charMap.charMap_.hasNo(char3, char4).query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }
    }
  }
}