// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_Char_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  val a = (1, Map("a" -> char1, "b" -> char2))
  val b = (2, Map("a" -> char2, "b" -> char3, "c" -> char4))

  override lazy val tests = Tests {

    "Mandatory" - {

      "Mandatory map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.charMap.insert(a, b).transact
          _ <- Ns.i.a1.charMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Map with certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.charMap.insert(a, b).transact

          // Get Map value by key

          // Like calling `apply` on a Scala Map.
          _ <- Ns.i.a1.charMap("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap("a").query.get.map(_ ==> List((1, char1), (2, char2)))
          _ <- Ns.i.a1.charMap("b").query.get.map(_ ==> List((1, char2), (2, char3)))
          _ <- Ns.i.a1.charMap("c").query.get.map(_ ==> List((2, char4)))
        } yield ()
      }


      "Map without certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.charMap.insert(a, b).transact

          // Get Map without certain key(s)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.charMap.not("_").query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charMap.not("a").query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap.not("b").query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap.not("c").query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charMap.not("a", "c").query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap.not("_", "c").query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.charMap.not(List("_")).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charMap.not(List("a")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap.not(List("b")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap.not(List("c")).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charMap.not(List("a", "c")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap.not(List("_", "c")).query.get.map(_ ==> List(a))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.charMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Map with certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.charMap.insert(a, b).transact

          // Maps containing value

          _ <- Ns.i.a1.charMap.has(char0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap.has(char1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charMap.has(char2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charMap.has(char3).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charMap.has(char4).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.charMap.has(Seq(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap.has(Seq(char1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charMap.has(Seq(char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charMap.has(Seq(char3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charMap.has(Seq(char4)).query.get.map(_ ==> List(b))

          // OR semantics when multiple values

          // "Has this OR that" value
          _ <- Ns.i.a1.charMap.has(char0, char1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charMap.has(char1, char2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charMap.has(char2, char3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charMap.has(char3, char4).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charMap.has(char4, char5).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charMap.has(char5, char6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.charMap.has(Seq(char0, char1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charMap.has(Seq(char1, char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charMap.has(Seq(char2, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charMap.has(Seq(char3, char4)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charMap.has(Seq(char4, char5)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charMap.has(Seq(char5, char6)).query.get.map(_ ==> List())

          // No values match nothing
          _ <- Ns.i.a1.charMap.has(Seq.empty[Char]).query.get.map(_ ==> List())
        } yield ()
      }


      "Map without certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.charMap.insert(a, b).transact

          // "Doesn't have this"
          _ <- Ns.i.a1.charMap.hasNo(char0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charMap.hasNo(char1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charMap.hasNo(char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap.hasNo(char3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charMap.hasNo(char3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charMap.hasNo(char5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.charMap.hasNo(List(char0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charMap.hasNo(List(char1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charMap.hasNo(List(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap.hasNo(List(char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charMap.hasNo(List(char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charMap.hasNo(List(char5)).query.get.map(_ ==> List(a, b))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.charMap.hasNo(char1, char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap.hasNo(char1, char3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap.hasNo(char1, char3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap.hasNo(char1, char5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.charMap.hasNo(List(char1, char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap.hasNo(List(char1, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap.hasNo(List(char1, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap.hasNo(List(char1, char5)).query.get.map(_ ==> List(b))

          // No values match nothing
          _ <- Ns.i.a1.charMap.hasNo(List.empty[Char]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "Tacit Map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.charMap.insert(a, b).transact
          _ <- Ns.i.a1.charMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "Match map with certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.charMap.insert(a, b).transact

          // "Map contains this OR that key"
          _ <- Ns.i.a1.charMap_("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap_("a").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_("_", "c").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.charMap_(List("_")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap_(List("a")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_(List("_", "c")).query.get.map(_ ==> List(2))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.charMap_(List.empty[String]).query.get.map(_ ==> List())

          // Match entities without map attribute
          _ <- Ns.i.a1.charMap_().query.get.map(_ ==> List(0))
        } yield ()
      }


      "Match map without certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.charMap.insert(a, b).transact

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.charMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_.not("a").query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap_.not("b").query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.not("a", "c").query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap_.not("_", "c").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.charMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_.not(List("a")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap_.not(List("b")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.not(List("a", "c")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.charMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "Match map with certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.charMap.insert(a, b).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.charMap_.has(char0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap_.has(char1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.has(char2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_.has(char3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.has(char4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.has(char0, char1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.has(char1, char2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_.has(char2, char3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_.has(char3, char4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.has(char4, char5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.has(char5, char6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.charMap_.has(List(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap_.has(List(char1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.has(List(char2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_.has(List(char3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.has(List(char4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.has(List(char0, char1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.has(List(char1, char2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_.has(List(char2, char3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_.has(List(char3, char4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.has(List(char4, char5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.has(List(char5, char6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.charMap_.has(List.empty[Char]).query.get.map(_ ==> List())
        } yield ()
      }


      "Match map without certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.charMap.insert(a, b).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.charMap_.hasNo(char0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_.hasNo(char1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.hasNo(char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap_.hasNo(char3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.hasNo(char4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.hasNo(char0, char1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.hasNo(char1, char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap_.hasNo(char2, char3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap_.hasNo(char3, char4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.hasNo(char4, char5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.hasNo(char5, char6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.charMap_.hasNo(List(char0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charMap_.hasNo(List(char1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.hasNo(List(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap_.hasNo(List(char3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.hasNo(List(char4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.hasNo(List(char0, char1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charMap_.hasNo(List(char1, char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap_.hasNo(List(char2, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charMap_.hasNo(List(char3, char4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.hasNo(List(char4, char5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charMap_.hasNo(List(char5, char6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.charMap_.hasNo(List.empty[Char]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "Optional map (no filter)" - types { implicit conn =>
        val a = (1, Some(Map("a" -> char1, "b" -> char2)))
        val b = (2, Some(Map("a" -> char2, "b" -> char3, "c" -> char4)))
        val c = (3, None)
        for {
          _ <- Ns.i.charMap_?.insert(a, b, c).transact
          _ <- Ns.i.a1.charMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Optional map values by key" - types { implicit conn =>

        for {
          _ <- Ns.i.charMap.insert(a, b).transact
          _ <- Ns.i(3).save.transact // entity without charMap

          // Like calling `get` on a Scala Map.
          _ <- Ns.i.a1.charMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.charMap_?("a").query.get.map(_ ==> List((1, Some(char1)), (2, Some(char2)), (3, None)))
          _ <- Ns.i.a1.charMap_?("b").query.get.map(_ ==> List((1, Some(char2)), (2, Some(char3)), (3, None)))
          _ <- Ns.i.a1.charMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(char4)), (3, None)))
        } yield ()
      }
    }
  }
}