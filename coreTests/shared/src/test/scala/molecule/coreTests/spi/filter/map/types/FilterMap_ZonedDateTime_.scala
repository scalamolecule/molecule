// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import java.time.ZonedDateTime
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_ZonedDateTime_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  val a = (1, Map("a" -> zonedDateTime1, "b" -> zonedDateTime2))
  val b = (2, Map("a" -> zonedDateTime2, "b" -> zonedDateTime3, "c" -> zonedDateTime4))

  override lazy val tests = Tests {

    "Mandatory" - {

      "Mandatory Map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.zonedDateTimeMap.insert(a, b).transact
          _ <- Ns.i.a1.zonedDateTimeMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Value by key" - types { implicit conn =>
        for {
          _ <- Ns.i.zonedDateTimeMap.insert(a, b).transact

          // Get Map value by key

          // Like calling `apply` on a Scala Map.
          _ <- Ns.i.a1.zonedDateTimeMap("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap("a").query.get.map(_ ==> List((1, zonedDateTime1), (2, zonedDateTime2)))
          _ <- Ns.i.a1.zonedDateTimeMap("b").query.get.map(_ ==> List((1, zonedDateTime2), (2, zonedDateTime3)))
          _ <- Ns.i.a1.zonedDateTimeMap("c").query.get.map(_ ==> List((2, zonedDateTime4)))
        } yield ()
      }


      "Map having values" - types { implicit conn =>
        for {
          _ <- Ns.i.zonedDateTimeMap.insert(a, b).transact

          // Maps containing value

          _ <- Ns.i.a1.zonedDateTimeMap.has(zonedDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap.has(zonedDateTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeMap.has(zonedDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeMap.has(zonedDateTime3).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeMap.has(zonedDateTime4).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime4)).query.get.map(_ ==> List(b))

          // OR semantics when multiple values

          // "Has this OR that" value
          _ <- Ns.i.a1.zonedDateTimeMap.has(zonedDateTime0, zonedDateTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeMap.has(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeMap.has(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeMap.has(zonedDateTime3, zonedDateTime4).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeMap.has(zonedDateTime4, zonedDateTime5).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeMap.has(zonedDateTime5, zonedDateTime6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime0, zonedDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime3, zonedDateTime4)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime4, zonedDateTime5)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime5, zonedDateTime6)).query.get.map(_ ==> List())

          // No values match nothing
          _ <- Ns.i.a1.zonedDateTimeMap.has(Seq.empty[ZonedDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "Map not having values" - types { implicit conn =>
        for {
          _ <- Ns.i.zonedDateTimeMap.insert(a, b).transact

          // "Doesn't have this"
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(zonedDateTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(zonedDateTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(zonedDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(zonedDateTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(zonedDateTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(zonedDateTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(List(zonedDateTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(List(zonedDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(List(zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(List(zonedDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(List(zonedDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(List(zonedDateTime5)).query.get.map(_ ==> List(a, b))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(zonedDateTime1, zonedDateTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(List(zonedDateTime1, zonedDateTime5)).query.get.map(_ ==> List(b))

          // No values match nothing
          _ <- Ns.i.a1.zonedDateTimeMap.hasNo(List.empty[ZonedDateTime]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "Tacit Map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.zonedDateTimeMap.insert(a, b).transact
          _ <- Ns.i.a1.zonedDateTimeMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "Map contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.zonedDateTimeMap.insert(a, b).transact

          // "Map contains this OR that key"
          _ <- Ns.i.a1.zonedDateTimeMap_("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap_("a").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_("_", "c").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeMap_(List("_")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap_(List("a")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_(List("_", "c")).query.get.map(_ ==> List(2))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.zonedDateTimeMap_(List.empty[String]).query.get.map(_ ==> List())

          // Match entities without map attribute
          _ <- Ns.i.a1.zonedDateTimeMap_().query.get.map(_ ==> List(0))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.zonedDateTimeMap.insert(a, b).transact

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.zonedDateTimeMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_.not("a").query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap_.not("b").query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.not("a", "c").query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap_.not("_", "c").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_.not(List("a")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap_.not(List("b")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.not(List("a", "c")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.zonedDateTimeMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.zonedDateTimeMap.insert(a, b).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime0, zonedDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime3, zonedDateTime4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime4, zonedDateTime5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(zonedDateTime5, zonedDateTime6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime0, zonedDateTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime3, zonedDateTime4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime4, zonedDateTime5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List(zonedDateTime5, zonedDateTime6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.zonedDateTimeMap_.has(List.empty[ZonedDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.zonedDateTimeMap.insert(a, b).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime0, zonedDateTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime3, zonedDateTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime4, zonedDateTime5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime5, zonedDateTime6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime0, zonedDateTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime3, zonedDateTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime4, zonedDateTime5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime5, zonedDateTime6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.zonedDateTimeMap_.hasNo(List.empty[ZonedDateTime]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "Optional map (no filter)" - types { implicit conn =>
        val a = (1, Some(Map("a" -> zonedDateTime1, "b" -> zonedDateTime2)))
        val b = (2, Some(Map("a" -> zonedDateTime2, "b" -> zonedDateTime3, "c" -> zonedDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.zonedDateTimeMap_?.insert(a, b, c).transact
          _ <- Ns.i.a1.zonedDateTimeMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>

        for {
          _ <- Ns.i.zonedDateTimeMap.insert(a, b).transact
          _ <- Ns.i(3).save.transact // entity without zonedDateTimeMap

          // Like calling `get` on a Scala Map.
          _ <- Ns.i.a1.zonedDateTimeMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.zonedDateTimeMap_?("a").query.get.map(_ ==> List((1, Some(zonedDateTime1)), (2, Some(zonedDateTime2)), (3, None)))
          _ <- Ns.i.a1.zonedDateTimeMap_?("b").query.get.map(_ ==> List((1, Some(zonedDateTime2)), (2, Some(zonedDateTime3)), (3, None)))
          _ <- Ns.i.a1.zonedDateTimeMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(zonedDateTime4)), (3, None)))
        } yield ()
      }
    }
  }
}