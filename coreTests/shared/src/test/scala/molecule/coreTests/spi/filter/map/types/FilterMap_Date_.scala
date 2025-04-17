// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import java.util.Date
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterMap_Date_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Map("a" -> date1, "b" -> date2))
  val b = (2, Map("a" -> date2, "b" -> date3, "c" -> date4))

  import api._
  import suite._


  "Mandatory: Mandatory map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.dateMap.insert(a, b).transact
      _ <- Entity.i.a1.dateMap.query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.dateMap.insert(a, b).transact

      // Get Map value by key

      // Like calling `apply` on a Scala Map.
      _ <- Entity.i.a1.dateMap("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap("a").query.get.map(_ ==> List((1, date1), (2, date2)))
      _ <- Entity.i.a1.dateMap("b").query.get.map(_ ==> List((1, date2), (2, date3)))
      _ <- Entity.i.a1.dateMap("c").query.get.map(_ ==> List((2, date4)))
    } yield ()
  }


  "Mandatory: Map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.dateMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.dateMap.not("_").query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateMap.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap.not("c").query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateMap.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap.not("_", "c").query.get.map(_ ==> List(a))
      // Same as
      _ <- Entity.i.a1.dateMap.not(List("_")).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateMap.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap.not(List("c")).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateMap.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap.not(List("_", "c")).query.get.map(_ ==> List(a))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.dateMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.dateMap.insert(a, b).transact

      // Maps containing value

      _ <- Entity.i.a1.dateMap.has(date0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap.has(date1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateMap.has(date2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateMap.has(date3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.dateMap.has(date4).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.dateMap.has(Seq(date0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap.has(Seq(date1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateMap.has(Seq(date2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateMap.has(Seq(date3)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.dateMap.has(Seq(date4)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that" value
      _ <- Entity.i.a1.dateMap.has(date0, date1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateMap.has(date1, date2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateMap.has(date2, date3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateMap.has(date3, date4).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.dateMap.has(date4, date5).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.dateMap.has(date5, date6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.dateMap.has(Seq(date0, date1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateMap.has(Seq(date1, date2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateMap.has(Seq(date2, date3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateMap.has(Seq(date3, date4)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.dateMap.has(Seq(date4, date5)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.dateMap.has(Seq(date5, date6)).query.get.map(_ ==> List())

      // No values match nothing
      _ <- Entity.i.a1.dateMap.has(Seq.empty[Date]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: Map without certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.dateMap.insert(a, b).transact

      // "Doesn't have this"
      _ <- Entity.i.a1.dateMap.hasNo(date0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateMap.hasNo(date1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.dateMap.hasNo(date2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap.hasNo(date3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateMap.hasNo(date3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateMap.hasNo(date5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.dateMap.hasNo(List(date0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateMap.hasNo(List(date1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.dateMap.hasNo(List(date2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap.hasNo(List(date3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateMap.hasNo(List(date3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateMap.hasNo(List(date5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.dateMap.hasNo(date1, date2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap.hasNo(date1, date3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap.hasNo(date1, date3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap.hasNo(date1, date5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.dateMap.hasNo(List(date1, date2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap.hasNo(List(date1, date3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap.hasNo(List(date1, date3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap.hasNo(List(date1, date5)).query.get.map(_ ==> List(b))

      // No values match nothing
      _ <- Entity.i.a1.dateMap.hasNo(List.empty[Date]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: Tacit Map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.dateMap.insert(a, b).transact
      _ <- Entity.i.a1.dateMap_.query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.insert(0).transact // Entity without map attribute
      _ <- Entity.i.dateMap.insert(a, b).transact

      // "Map contains this OR that key"
      _ <- Entity.i.a1.dateMap_("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap_("a").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateMap_("b").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateMap_("c").query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.dateMap_("a", "c").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateMap_("_", "c").query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.dateMap_(List("_")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap_(List("a")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateMap_(List("b")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateMap_(List("c")).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.dateMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateMap_(List("_", "c")).query.get.map(_ ==> List(2))

      // Empty Seq of keys matches nothing
      _ <- Entity.i.a1.dateMap_(List.empty[String]).query.get.map(_ ==> List())

      // Match entities without map attribute
      _ <- Entity.i.a1.dateMap_().query.get.map(_ ==> List(0))
    } yield ()
  }


  "Tacit: Match map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.dateMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.dateMap_.not("_").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateMap_.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap_.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap_.not("c").query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateMap_.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap_.not("_", "c").query.get.map(_ ==> List(1))
      // Same as
      _ <- Entity.i.a1.dateMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateMap_.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap_.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap_.not(List("c")).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateMap_.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.dateMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.dateMap.insert(a, b).transact

      // "Map contains this OR that value"
      _ <- Entity.i.a1.dateMap_.has(date0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap_.has(date1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateMap_.has(date2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateMap_.has(date3).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.dateMap_.has(date4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.dateMap_.has(date0, date1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateMap_.has(date1, date2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateMap_.has(date2, date3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateMap_.has(date3, date4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.dateMap_.has(date4, date5).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.dateMap_.has(date5, date6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.dateMap_.has(List(date0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap_.has(List(date1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateMap_.has(List(date2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateMap_.has(List(date3)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.dateMap_.has(List(date4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.dateMap_.has(List(date0, date1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateMap_.has(List(date1, date2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateMap_.has(List(date2, date3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateMap_.has(List(date3, date4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.dateMap_.has(List(date4, date5)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.dateMap_.has(List(date5, date6)).query.get.map(_ ==> List())

      // Empty Seq of values matches nothing
      _ <- Entity.i.a1.dateMap_.has(List.empty[Date]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: Match map without certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.dateMap.insert(a, b).transact

      // "Map contains neither this OR that value"
      _ <- Entity.i.a1.dateMap_.hasNo(date0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateMap_.hasNo(date1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.dateMap_.hasNo(date2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap_.hasNo(date3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateMap_.hasNo(date4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateMap_.hasNo(date0, date1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.dateMap_.hasNo(date1, date2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap_.hasNo(date2, date3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap_.hasNo(date3, date4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateMap_.hasNo(date4, date5).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateMap_.hasNo(date5, date6).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.dateMap_.hasNo(List(date0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateMap_.hasNo(List(date1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.dateMap_.hasNo(List(date2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap_.hasNo(List(date3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateMap_.hasNo(List(date4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateMap_.hasNo(List(date0, date1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.dateMap_.hasNo(List(date1, date2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap_.hasNo(List(date2, date3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateMap_.hasNo(List(date3, date4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateMap_.hasNo(List(date4, date5)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateMap_.hasNo(List(date5, date6)).query.get.map(_ ==> List(1, 2))

      // Negating empty Seq of values matches all
      _ <- Entity.i.a1.dateMap_.hasNo(List.empty[Date]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Optional map (no filter)" - types { implicit conn =>
    val a = (1, Some(Map("a" -> date1, "b" -> date2)))
    val b = (2, Some(Map("a" -> date2, "b" -> date3, "c" -> date4)))
    val c = (3, None)
    for {
      _ <- Entity.i.dateMap_?.insert(a, b, c).transact
      _ <- Entity.i.a1.dateMap_?.query.get.map(_ ==> List(a, b, c))
    } yield ()
  }


  "Optional map values by key" - types { implicit conn =>

    for {
      _ <- Entity.i.dateMap.insert(a, b).transact
      _ <- Entity.i(3).save.transact // entity without dateMap

      // Like calling `get` on a Scala Map.
      _ <- Entity.i.a1.dateMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
      _ <- Entity.i.a1.dateMap_?("a").query.get.map(_ ==> List((1, Some(date1)), (2, Some(date2)), (3, None)))
      _ <- Entity.i.a1.dateMap_?("b").query.get.map(_ ==> List((1, Some(date2)), (2, Some(date3)), (3, None)))
      _ <- Entity.i.a1.dateMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(date4)), (3, None)))
    } yield ()
  }
}