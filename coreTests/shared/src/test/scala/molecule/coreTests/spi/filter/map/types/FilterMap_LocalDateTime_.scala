// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import java.time.LocalDateTime
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_LocalDateTime_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  val a = (1, Map("a" -> localDateTime1, "b" -> localDateTime2))
  val b = (2, Map("a" -> localDateTime2, "b" -> localDateTime3, "c" -> localDateTime4))

  override lazy val tests = Tests {

    "Mandatory" - {

      "Mandatory Map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.localDateTimeMap.insert(a, b).transact
          _ <- Ns.i.a1.localDateTimeMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Value by key" - types { implicit conn =>
        for {
          _ <- Ns.i.localDateTimeMap.insert(a, b).transact

          // Get Map value by key

          // Like calling `apply` on a Scala Map.
          _ <- Ns.i.a1.localDateTimeMap("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap("a").query.get.map(_ ==> List((1, localDateTime1), (2, localDateTime2)))
          _ <- Ns.i.a1.localDateTimeMap("b").query.get.map(_ ==> List((1, localDateTime2), (2, localDateTime3)))
          _ <- Ns.i.a1.localDateTimeMap("c").query.get.map(_ ==> List((2, localDateTime4)))
        } yield ()
      }


      "Map having values" - types { implicit conn =>
        for {
          _ <- Ns.i.localDateTimeMap.insert(a, b).transact

          // Maps containing value

          _ <- Ns.i.a1.localDateTimeMap.has(localDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap.has(localDateTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeMap.has(localDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeMap.has(localDateTime3).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeMap.has(localDateTime4).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localDateTimeMap.has(Seq(localDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap.has(Seq(localDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeMap.has(Seq(localDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeMap.has(Seq(localDateTime3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeMap.has(Seq(localDateTime4)).query.get.map(_ ==> List(b))

          // OR semantics when multiple values

          // "Has this OR that" value
          _ <- Ns.i.a1.localDateTimeMap.has(localDateTime0, localDateTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeMap.has(localDateTime1, localDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeMap.has(localDateTime2, localDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeMap.has(localDateTime3, localDateTime4).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeMap.has(localDateTime4, localDateTime5).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeMap.has(localDateTime5, localDateTime6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localDateTimeMap.has(Seq(localDateTime0, localDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeMap.has(Seq(localDateTime1, localDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeMap.has(Seq(localDateTime2, localDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeMap.has(Seq(localDateTime3, localDateTime4)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeMap.has(Seq(localDateTime4, localDateTime5)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeMap.has(Seq(localDateTime5, localDateTime6)).query.get.map(_ ==> List())

          // No values match nothing
          _ <- Ns.i.a1.localDateTimeMap.has(Seq.empty[LocalDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "Map not having values" - types { implicit conn =>
        for {
          _ <- Ns.i.localDateTimeMap.insert(a, b).transact

          // "Doesn't have this"
          _ <- Ns.i.a1.localDateTimeMap.hasNo(localDateTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeMap.hasNo(localDateTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeMap.hasNo(localDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap.hasNo(localDateTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeMap.hasNo(localDateTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeMap.hasNo(localDateTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateTimeMap.hasNo(List(localDateTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeMap.hasNo(List(localDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeMap.hasNo(List(localDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap.hasNo(List(localDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeMap.hasNo(List(localDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeMap.hasNo(List(localDateTime5)).query.get.map(_ ==> List(a, b))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.localDateTimeMap.hasNo(localDateTime1, localDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap.hasNo(localDateTime1, localDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap.hasNo(localDateTime1, localDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap.hasNo(localDateTime1, localDateTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localDateTimeMap.hasNo(List(localDateTime1, localDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap.hasNo(List(localDateTime1, localDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap.hasNo(List(localDateTime1, localDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap.hasNo(List(localDateTime1, localDateTime5)).query.get.map(_ ==> List(b))

          // No values match nothing
          _ <- Ns.i.a1.localDateTimeMap.hasNo(List.empty[LocalDateTime]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "Tacit Map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.localDateTimeMap.insert(a, b).transact
          _ <- Ns.i.a1.localDateTimeMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "Map contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.localDateTimeMap.insert(a, b).transact

          // "Map contains this OR that key"
          _ <- Ns.i.a1.localDateTimeMap_("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap_("a").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_("_", "c").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localDateTimeMap_(List("_")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap_(List("a")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_(List("_", "c")).query.get.map(_ ==> List(2))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.localDateTimeMap_(List.empty[String]).query.get.map(_ ==> List())

          // Match entities without map attribute
          _ <- Ns.i.a1.localDateTimeMap_().query.get.map(_ ==> List(0))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.localDateTimeMap.insert(a, b).transact

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.localDateTimeMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_.not("a").query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap_.not("b").query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.not("a", "c").query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap_.not("_", "c").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.localDateTimeMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_.not(List("a")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap_.not(List("b")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.not(List("a", "c")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.localDateTimeMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.localDateTimeMap.insert(a, b).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime0, localDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime1, localDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime2, localDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime3, localDateTime4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime4, localDateTime5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.has(localDateTime5, localDateTime6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime0, localDateTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime1, localDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime2, localDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime3, localDateTime4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime4, localDateTime5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.has(List(localDateTime5, localDateTime6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.localDateTimeMap_.has(List.empty[LocalDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.localDateTimeMap.insert(a, b).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime0, localDateTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime1, localDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime2, localDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime3, localDateTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime4, localDateTime5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(localDateTime5, localDateTime6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime0, localDateTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime1, localDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime2, localDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime3, localDateTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime4, localDateTime5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List(localDateTime5, localDateTime6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.localDateTimeMap_.hasNo(List.empty[LocalDateTime]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "Optional map (no filter)" - types { implicit conn =>
        val a = (1, Some(Map("a" -> localDateTime1, "b" -> localDateTime2)))
        val b = (2, Some(Map("a" -> localDateTime2, "b" -> localDateTime3, "c" -> localDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localDateTimeMap_?.insert(a, b, c).transact
          _ <- Ns.i.a1.localDateTimeMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>

        for {
          _ <- Ns.i.localDateTimeMap.insert(a, b).transact
          _ <- Ns.i(3).save.transact // entity without localDateTimeMap

          // Like calling `get` on a Scala Map.
          _ <- Ns.i.a1.localDateTimeMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.localDateTimeMap_?("a").query.get.map(_ ==> List((1, Some(localDateTime1)), (2, Some(localDateTime2)), (3, None)))
          _ <- Ns.i.a1.localDateTimeMap_?("b").query.get.map(_ ==> List((1, Some(localDateTime2)), (2, Some(localDateTime3)), (3, None)))
          _ <- Ns.i.a1.localDateTimeMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(localDateTime4)), (3, None)))
        } yield ()
      }
    }
  }
}