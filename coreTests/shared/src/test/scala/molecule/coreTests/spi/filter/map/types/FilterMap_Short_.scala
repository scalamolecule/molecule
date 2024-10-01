// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_Short_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  val a = (1, Map("a" -> short1, "b" -> short2))
  val b = (2, Map("a" -> short2, "b" -> short3, "c" -> short4))

  override lazy val tests = Tests {

    "Mandatory" - {

      "Mandatory map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.shortMap.insert(a, b).transact
          _ <- Ns.i.a1.shortMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Map with certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.shortMap.insert(a, b).transact

          // Get Map value by key

          // Like calling `apply` on a Scala Map.
          _ <- Ns.i.a1.shortMap("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap("a").query.get.map(_ ==> List((1, short1), (2, short2)))
          _ <- Ns.i.a1.shortMap("b").query.get.map(_ ==> List((1, short2), (2, short3)))
          _ <- Ns.i.a1.shortMap("c").query.get.map(_ ==> List((2, short4)))
        } yield ()
      }


      "Map without certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.shortMap.insert(a, b).transact

          // Get Map without certain key(s)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.shortMap.not("_").query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortMap.not("a").query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap.not("b").query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap.not("c").query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortMap.not("a", "c").query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap.not("_", "c").query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.shortMap.not(List("_")).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortMap.not(List("a")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap.not(List("b")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap.not(List("c")).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortMap.not(List("a", "c")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap.not(List("_", "c")).query.get.map(_ ==> List(a))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.shortMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Map with certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.shortMap.insert(a, b).transact

          // Maps containing value

          _ <- Ns.i.a1.shortMap.has(short0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap.has(short1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortMap.has(short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortMap.has(short3).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortMap.has(short4).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shortMap.has(Seq(short0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap.has(Seq(short1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortMap.has(Seq(short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortMap.has(Seq(short3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortMap.has(Seq(short4)).query.get.map(_ ==> List(b))

          // OR semantics when multiple values

          // "Has this OR that" value
          _ <- Ns.i.a1.shortMap.has(short0, short1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortMap.has(short1, short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortMap.has(short2, short3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortMap.has(short3, short4).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortMap.has(short4, short5).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortMap.has(short5, short6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shortMap.has(Seq(short0, short1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortMap.has(Seq(short1, short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortMap.has(Seq(short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortMap.has(Seq(short3, short4)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortMap.has(Seq(short4, short5)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortMap.has(Seq(short5, short6)).query.get.map(_ ==> List())

          // No values match nothing
          _ <- Ns.i.a1.shortMap.has(Seq.empty[Short]).query.get.map(_ ==> List())
        } yield ()
      }


      "Map without certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.shortMap.insert(a, b).transact

          // "Doesn't have this"
          _ <- Ns.i.a1.shortMap.hasNo(short0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortMap.hasNo(short1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortMap.hasNo(short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap.hasNo(short3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortMap.hasNo(short3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortMap.hasNo(short5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shortMap.hasNo(List(short0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortMap.hasNo(List(short1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortMap.hasNo(List(short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap.hasNo(List(short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortMap.hasNo(List(short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortMap.hasNo(List(short5)).query.get.map(_ ==> List(a, b))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.shortMap.hasNo(short1, short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap.hasNo(short1, short3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap.hasNo(short1, short3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap.hasNo(short1, short5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shortMap.hasNo(List(short1, short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap.hasNo(List(short1, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap.hasNo(List(short1, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap.hasNo(List(short1, short5)).query.get.map(_ ==> List(b))

          // No values match nothing
          _ <- Ns.i.a1.shortMap.hasNo(List.empty[Short]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "Tacit Map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.shortMap.insert(a, b).transact
          _ <- Ns.i.a1.shortMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "Match map with certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.shortMap.insert(a, b).transact

          // "Map contains this OR that key"
          _ <- Ns.i.a1.shortMap_("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap_("a").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_("_", "c").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.shortMap_(List("_")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap_(List("a")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_(List("_", "c")).query.get.map(_ ==> List(2))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.shortMap_(List.empty[String]).query.get.map(_ ==> List())

          // Match entities without map attribute
          _ <- Ns.i.a1.shortMap_().query.get.map(_ ==> List(0))
        } yield ()
      }


      "Match map without certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.shortMap.insert(a, b).transact

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.shortMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_.not("a").query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap_.not("b").query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.not("a", "c").query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap_.not("_", "c").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.shortMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_.not(List("a")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap_.not(List("b")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.not(List("a", "c")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.shortMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "Match map with certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.shortMap.insert(a, b).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.shortMap_.has(short0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap_.has(short1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.has(short2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_.has(short3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.has(short4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.has(short0, short1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.has(short1, short2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_.has(short2, short3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_.has(short3, short4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.has(short4, short5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.has(short5, short6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shortMap_.has(List(short0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap_.has(List(short1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.has(List(short2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_.has(List(short3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.has(List(short4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.has(List(short0, short1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.has(List(short1, short2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_.has(List(short2, short3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_.has(List(short3, short4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.has(List(short4, short5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.has(List(short5, short6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.shortMap_.has(List.empty[Short]).query.get.map(_ ==> List())
        } yield ()
      }


      "Match map without certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.shortMap.insert(a, b).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.shortMap_.hasNo(short0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_.hasNo(short1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.hasNo(short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap_.hasNo(short3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.hasNo(short4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.hasNo(short0, short1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.hasNo(short1, short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap_.hasNo(short2, short3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap_.hasNo(short3, short4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.hasNo(short4, short5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.hasNo(short5, short6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.shortMap_.hasNo(List(short0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_.hasNo(List(short1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.hasNo(List(short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap_.hasNo(List(short3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.hasNo(List(short4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.hasNo(List(short0, short1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.hasNo(List(short1, short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap_.hasNo(List(short2, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap_.hasNo(List(short3, short4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.hasNo(List(short4, short5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.hasNo(List(short5, short6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.shortMap_.hasNo(List.empty[Short]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "Optional map (no filter)" - types { implicit conn =>
        val a = (1, Some(Map("a" -> short1, "b" -> short2)))
        val b = (2, Some(Map("a" -> short2, "b" -> short3, "c" -> short4)))
        val c = (3, None)
        for {
          _ <- Ns.i.shortMap_?.insert(a, b, c).transact
          _ <- Ns.i.a1.shortMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Optional map values by key" - types { implicit conn =>

        for {
          _ <- Ns.i.shortMap.insert(a, b).transact
          _ <- Ns.i(3).save.transact // entity without shortMap

          // Like calling `get` on a Scala Map.
          _ <- Ns.i.a1.shortMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.shortMap_?("a").query.get.map(_ ==> List((1, Some(short1)), (2, Some(short2)), (3, None)))
          _ <- Ns.i.a1.shortMap_?("b").query.get.map(_ ==> List((1, Some(short2)), (2, Some(short3)), (3, None)))
          _ <- Ns.i.a1.shortMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(short4)), (3, None)))
        } yield ()
      }
    }
  }
}