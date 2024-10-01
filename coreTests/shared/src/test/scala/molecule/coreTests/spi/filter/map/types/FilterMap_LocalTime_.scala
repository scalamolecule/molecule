// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import java.time.LocalTime
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_LocalTime_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  val a = (1, Map("a" -> localTime1, "b" -> localTime2))
  val b = (2, Map("a" -> localTime2, "b" -> localTime3, "c" -> localTime4))

  override lazy val tests = Tests {

    "Mandatory" - {

      "Mandatory map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.localTimeMap.insert(a, b).transact
          _ <- Ns.i.a1.localTimeMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Map with certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.localTimeMap.insert(a, b).transact

          // Get Map value by key

          // Like calling `apply` on a Scala Map.
          _ <- Ns.i.a1.localTimeMap("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap("a").query.get.map(_ ==> List((1, localTime1), (2, localTime2)))
          _ <- Ns.i.a1.localTimeMap("b").query.get.map(_ ==> List((1, localTime2), (2, localTime3)))
          _ <- Ns.i.a1.localTimeMap("c").query.get.map(_ ==> List((2, localTime4)))
        } yield ()
      }


      "Map without certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.localTimeMap.insert(a, b).transact

          // Get Map without certain key(s)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.localTimeMap.not("_").query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeMap.not("a").query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap.not("b").query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap.not("c").query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeMap.not("a", "c").query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap.not("_", "c").query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.localTimeMap.not(List("_")).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeMap.not(List("a")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap.not(List("b")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap.not(List("c")).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeMap.not(List("a", "c")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap.not(List("_", "c")).query.get.map(_ ==> List(a))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.localTimeMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Map with certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.localTimeMap.insert(a, b).transact

          // Maps containing value

          _ <- Ns.i.a1.localTimeMap.has(localTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap.has(localTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeMap.has(localTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeMap.has(localTime3).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeMap.has(localTime4).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localTimeMap.has(Seq(localTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap.has(Seq(localTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeMap.has(Seq(localTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeMap.has(Seq(localTime3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeMap.has(Seq(localTime4)).query.get.map(_ ==> List(b))

          // OR semantics when multiple values

          // "Has this OR that" value
          _ <- Ns.i.a1.localTimeMap.has(localTime0, localTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeMap.has(localTime1, localTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeMap.has(localTime2, localTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeMap.has(localTime3, localTime4).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeMap.has(localTime4, localTime5).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeMap.has(localTime5, localTime6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localTimeMap.has(Seq(localTime0, localTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeMap.has(Seq(localTime1, localTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeMap.has(Seq(localTime2, localTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeMap.has(Seq(localTime3, localTime4)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeMap.has(Seq(localTime4, localTime5)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeMap.has(Seq(localTime5, localTime6)).query.get.map(_ ==> List())

          // No values match nothing
          _ <- Ns.i.a1.localTimeMap.has(Seq.empty[LocalTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "Map without certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.localTimeMap.insert(a, b).transact

          // "Doesn't have this"
          _ <- Ns.i.a1.localTimeMap.hasNo(localTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeMap.hasNo(localTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeMap.hasNo(localTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap.hasNo(localTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeMap.hasNo(localTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeMap.hasNo(localTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localTimeMap.hasNo(List(localTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeMap.hasNo(List(localTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeMap.hasNo(List(localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap.hasNo(List(localTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeMap.hasNo(List(localTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeMap.hasNo(List(localTime5)).query.get.map(_ ==> List(a, b))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.localTimeMap.hasNo(localTime1, localTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap.hasNo(localTime1, localTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap.hasNo(localTime1, localTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap.hasNo(localTime1, localTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localTimeMap.hasNo(List(localTime1, localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap.hasNo(List(localTime1, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap.hasNo(List(localTime1, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap.hasNo(List(localTime1, localTime5)).query.get.map(_ ==> List(b))

          // No values match nothing
          _ <- Ns.i.a1.localTimeMap.hasNo(List.empty[LocalTime]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "Tacit Map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.localTimeMap.insert(a, b).transact
          _ <- Ns.i.a1.localTimeMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "Match map with certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.localTimeMap.insert(a, b).transact

          // "Map contains this OR that key"
          _ <- Ns.i.a1.localTimeMap_("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap_("a").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_("_", "c").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localTimeMap_(List("_")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap_(List("a")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_(List("_", "c")).query.get.map(_ ==> List(2))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.localTimeMap_(List.empty[String]).query.get.map(_ ==> List())

          // Match entities without map attribute
          _ <- Ns.i.a1.localTimeMap_().query.get.map(_ ==> List(0))
        } yield ()
      }


      "Match map without certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.localTimeMap.insert(a, b).transact

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.localTimeMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_.not("a").query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap_.not("b").query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.not("a", "c").query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap_.not("_", "c").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.localTimeMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_.not(List("a")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap_.not(List("b")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.not(List("a", "c")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.localTimeMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "Match map with certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.localTimeMap.insert(a, b).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.localTimeMap_.has(localTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap_.has(localTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.has(localTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_.has(localTime3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.has(localTime4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.has(localTime0, localTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.has(localTime1, localTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_.has(localTime2, localTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_.has(localTime3, localTime4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.has(localTime4, localTime5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.has(localTime5, localTime6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime0, localTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime1, localTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime2, localTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime3, localTime4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime4, localTime5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.has(List(localTime5, localTime6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.localTimeMap_.has(List.empty[LocalTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "Match map without certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.localTimeMap.insert(a, b).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime0, localTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime1, localTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime2, localTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime3, localTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime4, localTime5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.hasNo(localTime5, localTime6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime0, localTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime1, localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime2, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime3, localTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime4, localTime5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeMap_.hasNo(List(localTime5, localTime6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.localTimeMap_.hasNo(List.empty[LocalTime]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "Optional map (no filter)" - types { implicit conn =>
        val a = (1, Some(Map("a" -> localTime1, "b" -> localTime2)))
        val b = (2, Some(Map("a" -> localTime2, "b" -> localTime3, "c" -> localTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localTimeMap_?.insert(a, b, c).transact
          _ <- Ns.i.a1.localTimeMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Optional map values by key" - types { implicit conn =>

        for {
          _ <- Ns.i.localTimeMap.insert(a, b).transact
          _ <- Ns.i(3).save.transact // entity without localTimeMap

          // Like calling `get` on a Scala Map.
          _ <- Ns.i.a1.localTimeMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.localTimeMap_?("a").query.get.map(_ ==> List((1, Some(localTime1)), (2, Some(localTime2)), (3, None)))
          _ <- Ns.i.a1.localTimeMap_?("b").query.get.map(_ ==> List((1, Some(localTime2)), (2, Some(localTime3)), (3, None)))
          _ <- Ns.i.a1.localTimeMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(localTime4)), (3, None)))
        } yield ()
      }
    }
  }
}