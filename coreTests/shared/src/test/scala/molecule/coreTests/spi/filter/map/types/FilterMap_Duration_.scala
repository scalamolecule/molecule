// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import java.time.Duration
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_Duration_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  val a = (1, Map("a" -> duration1, "b" -> duration2))
  val b = (2, Map("a" -> duration2, "b" -> duration3, "c" -> duration4))

  override lazy val tests = Tests {

    "Mandatory" - {

      "Mandatory map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.durationMap.insert(a, b).transact
          _ <- Ns.i.a1.durationMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Map with certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.durationMap.insert(a, b).transact

          // Get Map value by key

          // Like calling `apply` on a Scala Map.
          _ <- Ns.i.a1.durationMap("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap("a").query.get.map(_ ==> List((1, duration1), (2, duration2)))
          _ <- Ns.i.a1.durationMap("b").query.get.map(_ ==> List((1, duration2), (2, duration3)))
          _ <- Ns.i.a1.durationMap("c").query.get.map(_ ==> List((2, duration4)))
        } yield ()
      }


      "Map without certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.durationMap.insert(a, b).transact

          // Get Map without certain key(s)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.durationMap.not("_").query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationMap.not("a").query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap.not("b").query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap.not("c").query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationMap.not("a", "c").query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap.not("_", "c").query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.durationMap.not(List("_")).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationMap.not(List("a")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap.not(List("b")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap.not(List("c")).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationMap.not(List("a", "c")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap.not(List("_", "c")).query.get.map(_ ==> List(a))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.durationMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Map with certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.durationMap.insert(a, b).transact

          // Maps containing value

          _ <- Ns.i.a1.durationMap.has(duration0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap.has(duration1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationMap.has(duration2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationMap.has(duration3).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationMap.has(duration4).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.durationMap.has(Seq(duration0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap.has(Seq(duration1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationMap.has(Seq(duration2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationMap.has(Seq(duration3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationMap.has(Seq(duration4)).query.get.map(_ ==> List(b))

          // OR semantics when multiple values

          // "Has this OR that" value
          _ <- Ns.i.a1.durationMap.has(duration0, duration1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationMap.has(duration1, duration2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationMap.has(duration2, duration3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationMap.has(duration3, duration4).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationMap.has(duration4, duration5).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationMap.has(duration5, duration6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.durationMap.has(Seq(duration0, duration1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationMap.has(Seq(duration1, duration2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationMap.has(Seq(duration2, duration3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationMap.has(Seq(duration3, duration4)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationMap.has(Seq(duration4, duration5)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationMap.has(Seq(duration5, duration6)).query.get.map(_ ==> List())

          // No values match nothing
          _ <- Ns.i.a1.durationMap.has(Seq.empty[Duration]).query.get.map(_ ==> List())
        } yield ()
      }


      "Map without certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.durationMap.insert(a, b).transact

          // "Doesn't have this"
          _ <- Ns.i.a1.durationMap.hasNo(duration0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationMap.hasNo(duration1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationMap.hasNo(duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap.hasNo(duration3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationMap.hasNo(duration3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationMap.hasNo(duration5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durationMap.hasNo(List(duration0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationMap.hasNo(List(duration1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationMap.hasNo(List(duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap.hasNo(List(duration3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationMap.hasNo(List(duration3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationMap.hasNo(List(duration5)).query.get.map(_ ==> List(a, b))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.durationMap.hasNo(duration1, duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap.hasNo(duration1, duration3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap.hasNo(duration1, duration3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap.hasNo(duration1, duration5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.durationMap.hasNo(List(duration1, duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap.hasNo(List(duration1, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap.hasNo(List(duration1, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap.hasNo(List(duration1, duration5)).query.get.map(_ ==> List(b))

          // No values match nothing
          _ <- Ns.i.a1.durationMap.hasNo(List.empty[Duration]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "Tacit Map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.durationMap.insert(a, b).transact
          _ <- Ns.i.a1.durationMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "Match map with certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.durationMap.insert(a, b).transact

          // "Map contains this OR that key"
          _ <- Ns.i.a1.durationMap_("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap_("a").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_("_", "c").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.durationMap_(List("_")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap_(List("a")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_(List("_", "c")).query.get.map(_ ==> List(2))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.durationMap_(List.empty[String]).query.get.map(_ ==> List())

          // Match entities without map attribute
          _ <- Ns.i.a1.durationMap_().query.get.map(_ ==> List(0))
        } yield ()
      }


      "Match map without certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.durationMap.insert(a, b).transact

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.durationMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_.not("a").query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap_.not("b").query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.not("a", "c").query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap_.not("_", "c").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.durationMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_.not(List("a")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap_.not(List("b")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.not(List("a", "c")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.durationMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "Match map with certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.durationMap.insert(a, b).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.durationMap_.has(duration0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap_.has(duration1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.has(duration2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_.has(duration3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.has(duration4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.has(duration0, duration1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.has(duration1, duration2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_.has(duration2, duration3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_.has(duration3, duration4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.has(duration4, duration5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.has(duration5, duration6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.durationMap_.has(List(duration0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap_.has(List(duration1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.has(List(duration2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_.has(List(duration3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.has(List(duration4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.has(List(duration0, duration1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.has(List(duration1, duration2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_.has(List(duration2, duration3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_.has(List(duration3, duration4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.has(List(duration4, duration5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.has(List(duration5, duration6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.durationMap_.has(List.empty[Duration]).query.get.map(_ ==> List())
        } yield ()
      }


      "Match map without certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.durationMap.insert(a, b).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.durationMap_.hasNo(duration0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_.hasNo(duration1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.hasNo(duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap_.hasNo(duration3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.hasNo(duration4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.hasNo(duration0, duration1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.hasNo(duration1, duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap_.hasNo(duration2, duration3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap_.hasNo(duration3, duration4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.hasNo(duration4, duration5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.hasNo(duration5, duration6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.durationMap_.hasNo(List(duration0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationMap_.hasNo(List(duration1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.hasNo(List(duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap_.hasNo(List(duration3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.hasNo(List(duration4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.hasNo(List(duration0, duration1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationMap_.hasNo(List(duration1, duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap_.hasNo(List(duration2, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationMap_.hasNo(List(duration3, duration4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.hasNo(List(duration4, duration5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationMap_.hasNo(List(duration5, duration6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.durationMap_.hasNo(List.empty[Duration]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "Optional map (no filter)" - types { implicit conn =>
        val a = (1, Some(Map("a" -> duration1, "b" -> duration2)))
        val b = (2, Some(Map("a" -> duration2, "b" -> duration3, "c" -> duration4)))
        val c = (3, None)
        for {
          _ <- Ns.i.durationMap_?.insert(a, b, c).transact
          _ <- Ns.i.a1.durationMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Optional map values by key" - types { implicit conn =>

        for {
          _ <- Ns.i.durationMap.insert(a, b).transact
          _ <- Ns.i(3).save.transact // entity without durationMap

          // Like calling `get` on a Scala Map.
          _ <- Ns.i.a1.durationMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.durationMap_?("a").query.get.map(_ ==> List((1, Some(duration1)), (2, Some(duration2)), (3, None)))
          _ <- Ns.i.a1.durationMap_?("b").query.get.map(_ ==> List((1, Some(duration2)), (2, Some(duration3)), (3, None)))
          _ <- Ns.i.a1.durationMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(duration4)), (3, None)))
        } yield ()
      }
    }
  }
}