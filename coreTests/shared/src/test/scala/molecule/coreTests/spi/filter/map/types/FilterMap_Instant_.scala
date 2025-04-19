// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import java.time.Instant
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Types.*
import molecule.coreTests.setup.*

case class FilterMap_Instant_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Map("a" -> instant1, "b" -> instant2))
  val b = (2, Map("a" -> instant2, "b" -> instant3, "c" -> instant4))

  import api.*
  import suite.*


  "Mandatory: Mandatory map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.instantMap.insert(a, b).transact
      _ <- Entity.i.a1.instantMap.query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.instantMap.insert(a, b).transact

      // Get Map value by key

      // Like calling `apply` on a Scala Map.
      _ <- Entity.i.a1.instantMap("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap("a").query.get.map(_ ==> List((1, instant1), (2, instant2)))
      _ <- Entity.i.a1.instantMap("b").query.get.map(_ ==> List((1, instant2), (2, instant3)))
      _ <- Entity.i.a1.instantMap("c").query.get.map(_ ==> List((2, instant4)))
    } yield ()
  }


  "Mandatory: Map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.instantMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.instantMap.not("_").query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantMap.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap.not("c").query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantMap.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap.not("_", "c").query.get.map(_ ==> List(a))
      // Same as
      _ <- Entity.i.a1.instantMap.not(List("_")).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantMap.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap.not(List("c")).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantMap.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap.not(List("_", "c")).query.get.map(_ ==> List(a))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.instantMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.instantMap.insert(a, b).transact

      // Maps containing value

      _ <- Entity.i.a1.instantMap.has(instant0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap.has(instant1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantMap.has(instant2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantMap.has(instant3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.instantMap.has(instant4).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.instantMap.has(Seq(instant0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap.has(Seq(instant1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantMap.has(Seq(instant2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantMap.has(Seq(instant3)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.instantMap.has(Seq(instant4)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that" value
      _ <- Entity.i.a1.instantMap.has(instant0, instant1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantMap.has(instant1, instant2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantMap.has(instant2, instant3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantMap.has(instant3, instant4).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.instantMap.has(instant4, instant5).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.instantMap.has(instant5, instant6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.instantMap.has(Seq(instant0, instant1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantMap.has(Seq(instant1, instant2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantMap.has(Seq(instant2, instant3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantMap.has(Seq(instant3, instant4)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.instantMap.has(Seq(instant4, instant5)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.instantMap.has(Seq(instant5, instant6)).query.get.map(_ ==> List())

      // No values match nothing
      _ <- Entity.i.a1.instantMap.has(Seq.empty[Instant]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: Map without certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.instantMap.insert(a, b).transact

      // "Doesn't have this"
      _ <- Entity.i.a1.instantMap.hasNo(instant0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantMap.hasNo(instant1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.instantMap.hasNo(instant2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap.hasNo(instant3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantMap.hasNo(instant3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantMap.hasNo(instant5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.instantMap.hasNo(List(instant0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantMap.hasNo(List(instant1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.instantMap.hasNo(List(instant2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap.hasNo(List(instant3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantMap.hasNo(List(instant3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantMap.hasNo(List(instant5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.instantMap.hasNo(instant1, instant2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap.hasNo(instant1, instant3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap.hasNo(instant1, instant3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap.hasNo(instant1, instant5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.instantMap.hasNo(List(instant1, instant2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap.hasNo(List(instant1, instant3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap.hasNo(List(instant1, instant3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap.hasNo(List(instant1, instant5)).query.get.map(_ ==> List(b))

      // No values match nothing
      _ <- Entity.i.a1.instantMap.hasNo(List.empty[Instant]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: Tacit Map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.instantMap.insert(a, b).transact
      _ <- Entity.i.a1.instantMap_.query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.insert(0).transact // Entity without map attribute
      _ <- Entity.i.instantMap.insert(a, b).transact

      // "Map contains this OR that key"
      _ <- Entity.i.a1.instantMap_("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap_("a").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantMap_("b").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantMap_("c").query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.instantMap_("a", "c").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantMap_("_", "c").query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.instantMap_(List("_")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap_(List("a")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantMap_(List("b")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantMap_(List("c")).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.instantMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantMap_(List("_", "c")).query.get.map(_ ==> List(2))

      // Empty Seq of keys matches nothing
      _ <- Entity.i.a1.instantMap_(List.empty[String]).query.get.map(_ ==> List())

      // Match entities without map attribute
      _ <- Entity.i.a1.instantMap_().query.get.map(_ ==> List(0))
    } yield ()
  }


  "Tacit: Match map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.instantMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.instantMap_.not("_").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantMap_.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap_.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap_.not("c").query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantMap_.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap_.not("_", "c").query.get.map(_ ==> List(1))
      // Same as
      _ <- Entity.i.a1.instantMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantMap_.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap_.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap_.not(List("c")).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantMap_.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.instantMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.instantMap.insert(a, b).transact

      // "Map contains this OR that value"
      _ <- Entity.i.a1.instantMap_.has(instant0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap_.has(instant1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantMap_.has(instant2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantMap_.has(instant3).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.instantMap_.has(instant4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.instantMap_.has(instant0, instant1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantMap_.has(instant1, instant2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantMap_.has(instant2, instant3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantMap_.has(instant3, instant4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.instantMap_.has(instant4, instant5).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.instantMap_.has(instant5, instant6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.instantMap_.has(List(instant0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap_.has(List(instant1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantMap_.has(List(instant2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantMap_.has(List(instant3)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.instantMap_.has(List(instant4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.instantMap_.has(List(instant0, instant1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantMap_.has(List(instant1, instant2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantMap_.has(List(instant2, instant3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantMap_.has(List(instant3, instant4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.instantMap_.has(List(instant4, instant5)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.instantMap_.has(List(instant5, instant6)).query.get.map(_ ==> List())

      // Empty Seq of values matches nothing
      _ <- Entity.i.a1.instantMap_.has(List.empty[Instant]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: Match map without certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.instantMap.insert(a, b).transact

      // "Map contains neither this OR that value"
      _ <- Entity.i.a1.instantMap_.hasNo(instant0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantMap_.hasNo(instant1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.instantMap_.hasNo(instant2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap_.hasNo(instant3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantMap_.hasNo(instant4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantMap_.hasNo(instant0, instant1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.instantMap_.hasNo(instant1, instant2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap_.hasNo(instant2, instant3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap_.hasNo(instant3, instant4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantMap_.hasNo(instant4, instant5).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantMap_.hasNo(instant5, instant6).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.instantMap_.hasNo(List(instant0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantMap_.hasNo(List(instant1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.instantMap_.hasNo(List(instant2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap_.hasNo(List(instant3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantMap_.hasNo(List(instant4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantMap_.hasNo(List(instant0, instant1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.instantMap_.hasNo(List(instant1, instant2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap_.hasNo(List(instant2, instant3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantMap_.hasNo(List(instant3, instant4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantMap_.hasNo(List(instant4, instant5)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantMap_.hasNo(List(instant5, instant6)).query.get.map(_ ==> List(1, 2))

      // Negating empty Seq of values matches all
      _ <- Entity.i.a1.instantMap_.hasNo(List.empty[Instant]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Optional map (no filter)" - types { implicit conn =>
    val a = (1, Some(Map("a" -> instant1, "b" -> instant2)))
    val b = (2, Some(Map("a" -> instant2, "b" -> instant3, "c" -> instant4)))
    val c = (3, None)
    for {
      _ <- Entity.i.instantMap_?.insert(a, b, c).transact
      _ <- Entity.i.a1.instantMap_?.query.get.map(_ ==> List(a, b, c))
    } yield ()
  }


  "Optional map values by key" - types { implicit conn =>

    for {
      _ <- Entity.i.instantMap.insert(a, b).transact
      _ <- Entity.i(3).save.transact // entity without instantMap

      // Like calling `get` on a Scala Map.
      _ <- Entity.i.a1.instantMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
      _ <- Entity.i.a1.instantMap_?("a").query.get.map(_ ==> List((1, Some(instant1)), (2, Some(instant2)), (3, None)))
      _ <- Entity.i.a1.instantMap_?("b").query.get.map(_ ==> List((1, Some(instant2)), (2, Some(instant3)), (3, None)))
      _ <- Entity.i.a1.instantMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(instant4)), (3, None)))
    } yield ()
  }
}