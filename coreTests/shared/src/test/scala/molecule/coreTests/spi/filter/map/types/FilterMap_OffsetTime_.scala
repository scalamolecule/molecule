// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import java.time.OffsetTime
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_OffsetTime_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  val a = (1, Map("a" -> offsetTime1, "b" -> offsetTime2))
  val b = (2, Map("a" -> offsetTime2, "b" -> offsetTime3, "c" -> offsetTime4))

  override lazy val tests = Tests {

    "Mandatory" - {

      "Mandatory map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.offsetTimeMap.insert(a, b).transact
          _ <- Ns.i.a1.offsetTimeMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Map with certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.offsetTimeMap.insert(a, b).transact

          // Get Map value by key

          // Like calling `apply` on a Scala Map.
          _ <- Ns.i.a1.offsetTimeMap("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap("a").query.get.map(_ ==> List((1, offsetTime1), (2, offsetTime2)))
          _ <- Ns.i.a1.offsetTimeMap("b").query.get.map(_ ==> List((1, offsetTime2), (2, offsetTime3)))
          _ <- Ns.i.a1.offsetTimeMap("c").query.get.map(_ ==> List((2, offsetTime4)))
        } yield ()
      }


      "Map without certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.offsetTimeMap.insert(a, b).transact

          // Get Map without certain key(s)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.offsetTimeMap.not("_").query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeMap.not("a").query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap.not("b").query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap.not("c").query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeMap.not("a", "c").query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap.not("_", "c").query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.offsetTimeMap.not(List("_")).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeMap.not(List("a")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap.not(List("b")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap.not(List("c")).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeMap.not(List("a", "c")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap.not(List("_", "c")).query.get.map(_ ==> List(a))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.offsetTimeMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Map with certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.offsetTimeMap.insert(a, b).transact

          // Maps containing value

          _ <- Ns.i.a1.offsetTimeMap.has(offsetTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap.has(offsetTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeMap.has(offsetTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeMap.has(offsetTime3).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeMap.has(offsetTime4).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetTimeMap.has(Seq(offsetTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap.has(Seq(offsetTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeMap.has(Seq(offsetTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeMap.has(Seq(offsetTime3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeMap.has(Seq(offsetTime4)).query.get.map(_ ==> List(b))

          // OR semantics when multiple values

          // "Has this OR that" value
          _ <- Ns.i.a1.offsetTimeMap.has(offsetTime0, offsetTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeMap.has(offsetTime1, offsetTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeMap.has(offsetTime2, offsetTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeMap.has(offsetTime3, offsetTime4).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeMap.has(offsetTime4, offsetTime5).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeMap.has(offsetTime5, offsetTime6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetTimeMap.has(Seq(offsetTime0, offsetTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeMap.has(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeMap.has(Seq(offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeMap.has(Seq(offsetTime3, offsetTime4)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeMap.has(Seq(offsetTime4, offsetTime5)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeMap.has(Seq(offsetTime5, offsetTime6)).query.get.map(_ ==> List())

          // No values match nothing
          _ <- Ns.i.a1.offsetTimeMap.has(Seq.empty[OffsetTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "Map without certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.offsetTimeMap.insert(a, b).transact

          // "Doesn't have this"
          _ <- Ns.i.a1.offsetTimeMap.hasNo(offsetTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeMap.hasNo(offsetTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeMap.hasNo(offsetTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap.hasNo(offsetTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeMap.hasNo(offsetTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeMap.hasNo(offsetTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimeMap.hasNo(List(offsetTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeMap.hasNo(List(offsetTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeMap.hasNo(List(offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap.hasNo(List(offsetTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeMap.hasNo(List(offsetTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeMap.hasNo(List(offsetTime5)).query.get.map(_ ==> List(a, b))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.offsetTimeMap.hasNo(offsetTime1, offsetTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap.hasNo(offsetTime1, offsetTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap.hasNo(offsetTime1, offsetTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap.hasNo(offsetTime1, offsetTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetTimeMap.hasNo(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap.hasNo(List(offsetTime1, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap.hasNo(List(offsetTime1, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap.hasNo(List(offsetTime1, offsetTime5)).query.get.map(_ ==> List(b))

          // No values match nothing
          _ <- Ns.i.a1.offsetTimeMap.hasNo(List.empty[OffsetTime]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "Tacit Map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.offsetTimeMap.insert(a, b).transact
          _ <- Ns.i.a1.offsetTimeMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "Match map with certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.offsetTimeMap.insert(a, b).transact

          // "Map contains this OR that key"
          _ <- Ns.i.a1.offsetTimeMap_("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap_("a").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_("_", "c").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetTimeMap_(List("_")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap_(List("a")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_(List("_", "c")).query.get.map(_ ==> List(2))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.offsetTimeMap_(List.empty[String]).query.get.map(_ ==> List())

          // Match entities without map attribute
          _ <- Ns.i.a1.offsetTimeMap_().query.get.map(_ ==> List(0))
        } yield ()
      }


      "Match map without certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.offsetTimeMap.insert(a, b).transact

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.offsetTimeMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_.not("a").query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap_.not("b").query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.not("a", "c").query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap_.not("_", "c").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.offsetTimeMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_.not(List("a")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap_.not(List("b")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.not(List("a", "c")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.offsetTimeMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "Match map with certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.offsetTimeMap.insert(a, b).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime0, offsetTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime1, offsetTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime2, offsetTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime3, offsetTime4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime4, offsetTime5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.has(offsetTime5, offsetTime6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime0, offsetTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime2, offsetTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime3, offsetTime4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime4, offsetTime5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.has(List(offsetTime5, offsetTime6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.offsetTimeMap_.has(List.empty[OffsetTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "Match map without certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.offsetTimeMap.insert(a, b).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime0, offsetTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime1, offsetTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime2, offsetTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime3, offsetTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime4, offsetTime5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(offsetTime5, offsetTime6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime0, offsetTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime2, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime3, offsetTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime4, offsetTime5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List(offsetTime5, offsetTime6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.offsetTimeMap_.hasNo(List.empty[OffsetTime]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "Optional map (no filter)" - types { implicit conn =>
        val a = (1, Some(Map("a" -> offsetTime1, "b" -> offsetTime2)))
        val b = (2, Some(Map("a" -> offsetTime2, "b" -> offsetTime3, "c" -> offsetTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetTimeMap_?.insert(a, b, c).transact
          _ <- Ns.i.a1.offsetTimeMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Optional map values by key" - types { implicit conn =>

        for {
          _ <- Ns.i.offsetTimeMap.insert(a, b).transact
          _ <- Ns.i(3).save.transact // entity without offsetTimeMap

          // Like calling `get` on a Scala Map.
          _ <- Ns.i.a1.offsetTimeMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.offsetTimeMap_?("a").query.get.map(_ ==> List((1, Some(offsetTime1)), (2, Some(offsetTime2)), (3, None)))
          _ <- Ns.i.a1.offsetTimeMap_?("b").query.get.map(_ ==> List((1, Some(offsetTime2)), (2, Some(offsetTime3)), (3, None)))
          _ <- Ns.i.a1.offsetTimeMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(offsetTime4)), (3, None)))
        } yield ()
      }
    }
  }
}