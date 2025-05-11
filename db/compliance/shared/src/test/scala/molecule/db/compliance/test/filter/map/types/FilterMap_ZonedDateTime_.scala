// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.map.types

import java.time.ZonedDateTime
import molecule.db.compliance.setup.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.domains.dsl.Refs.*

case class FilterMap_ZonedDateTime_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Map("a" -> zonedDateTime1, "b" -> zonedDateTime2))
  val b = (2, Map("a" -> zonedDateTime2, "b" -> zonedDateTime3, "c" -> zonedDateTime4))

  import api.*
  import suite.*


  "Mandatory: Mandatory map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.zonedDateTimeMap.insert(a, b).transact
      _ <- Entity.i.a1.zonedDateTimeMap.query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.zonedDateTimeMap.insert(a, b).transact

      // Get Map value by key

      // Like calling `apply` on a Scala Map.
      _ <- Entity.i.a1.zonedDateTimeMap("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap("a").query.get.map(_ ==> List((1, zonedDateTime1), (2, zonedDateTime2)))
      _ <- Entity.i.a1.zonedDateTimeMap("b").query.get.map(_ ==> List((1, zonedDateTime2), (2, zonedDateTime3)))
      _ <- Entity.i.a1.zonedDateTimeMap("c").query.get.map(_ ==> List((2, zonedDateTime4)))
    } yield ()
  }


  "Mandatory: Map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.zonedDateTimeMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.zonedDateTimeMap.not("_").query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTimeMap.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap.not("c").query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTimeMap.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap.not("_", "c").query.get.map(_ ==> List(a))
      // Same as
      _ <- Entity.i.a1.zonedDateTimeMap.not(List("_")).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTimeMap.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap.not(List("c")).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTimeMap.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap.not(List("_", "c")).query.get.map(_ ==> List(a))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.zonedDateTimeMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.zonedDateTimeMap.insert(a, b).transact

      // Maps containing value

      _ <- Entity.i.a1.zonedDateTimeMap.has(zonedDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap.has(zonedDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTimeMap.has(zonedDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTimeMap.has(zonedDateTime3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.zonedDateTimeMap.has(zonedDateTime4).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime3)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime4)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that" value
      _ <- Entity.i.a1.zonedDateTimeMap.has(zonedDateTime0, zonedDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTimeMap.has(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTimeMap.has(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTimeMap.has(zonedDateTime3, zonedDateTime4).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.zonedDateTimeMap.has(zonedDateTime4, zonedDateTime5).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.zonedDateTimeMap.has(zonedDateTime5, zonedDateTime6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime0, zonedDateTime1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime3, zonedDateTime4)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime4, zonedDateTime5)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.zonedDateTimeMap.has(Seq(zonedDateTime5, zonedDateTime6)).query.get.map(_ ==> List())

      // No values match nothing
      _ <- Entity.i.a1.zonedDateTimeMap.has(Seq.empty[ZonedDateTime]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: Map without certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.zonedDateTimeMap.insert(a, b).transact

      // "Doesn't have this"
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(zonedDateTime0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(zonedDateTime1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(zonedDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(zonedDateTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(zonedDateTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(zonedDateTime5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(List(zonedDateTime0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(List(zonedDateTime1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(List(zonedDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(List(zonedDateTime3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(List(zonedDateTime3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(List(zonedDateTime5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(zonedDateTime1, zonedDateTime5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(List(zonedDateTime1, zonedDateTime5)).query.get.map(_ ==> List(b))

      // No values match nothing
      _ <- Entity.i.a1.zonedDateTimeMap.hasNo(List.empty[ZonedDateTime]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: Tacit Map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.zonedDateTimeMap.insert(a, b).transact
      _ <- Entity.i.a1.zonedDateTimeMap_.query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.insert(0).transact // Entity without map attribute
      _ <- Entity.i.zonedDateTimeMap.insert(a, b).transact

      // "Map contains this OR that key"
      _ <- Entity.i.a1.zonedDateTimeMap_("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap_("a").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeMap_("b").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeMap_("c").query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.zonedDateTimeMap_("a", "c").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeMap_("_", "c").query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.zonedDateTimeMap_(List("_")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap_(List("a")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeMap_(List("b")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeMap_(List("c")).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.zonedDateTimeMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeMap_(List("_", "c")).query.get.map(_ ==> List(2))

      // Empty Seq of keys matches nothing
      _ <- Entity.i.a1.zonedDateTimeMap_(List.empty[String]).query.get.map(_ ==> List())

      // Match entities without map attribute
      _ <- Entity.i.a1.zonedDateTimeMap_().query.get.map(_ ==> List(0))
    } yield ()
  }


  "Tacit: Match map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.zonedDateTimeMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.zonedDateTimeMap_.not("_").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeMap_.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap_.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap_.not("c").query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeMap_.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap_.not("_", "c").query.get.map(_ ==> List(1))
      // Same as
      _ <- Entity.i.a1.zonedDateTimeMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeMap_.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap_.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap_.not(List("c")).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeMap_.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.zonedDateTimeMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.zonedDateTimeMap.insert(a, b).transact

      // "Map contains this OR that value"
      _ <- Entity.i.a1.zonedDateTimeMap_.has(zonedDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap_.has(zonedDateTime1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeMap_.has(zonedDateTime2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeMap_.has(zonedDateTime3).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.zonedDateTimeMap_.has(zonedDateTime4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.zonedDateTimeMap_.has(zonedDateTime0, zonedDateTime1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeMap_.has(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeMap_.has(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeMap_.has(zonedDateTime3, zonedDateTime4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.zonedDateTimeMap_.has(zonedDateTime4, zonedDateTime5).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.zonedDateTimeMap_.has(zonedDateTime5, zonedDateTime6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.zonedDateTimeMap_.has(List(zonedDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap_.has(List(zonedDateTime1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeMap_.has(List(zonedDateTime2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeMap_.has(List(zonedDateTime3)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.zonedDateTimeMap_.has(List(zonedDateTime4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.zonedDateTimeMap_.has(List(zonedDateTime0, zonedDateTime1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeMap_.has(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeMap_.has(List(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeMap_.has(List(zonedDateTime3, zonedDateTime4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.zonedDateTimeMap_.has(List(zonedDateTime4, zonedDateTime5)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.zonedDateTimeMap_.has(List(zonedDateTime5, zonedDateTime6)).query.get.map(_ ==> List())

      // Empty Seq of values matches nothing
      _ <- Entity.i.a1.zonedDateTimeMap_.has(List.empty[ZonedDateTime]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: Match map without certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.zonedDateTimeMap.insert(a, b).transact

      // "Map contains neither this OR that value"
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime0, zonedDateTime1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime3, zonedDateTime4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime4, zonedDateTime5).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(zonedDateTime5, zonedDateTime6).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime0, zonedDateTime1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime3, zonedDateTime4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime4, zonedDateTime5)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(List(zonedDateTime5, zonedDateTime6)).query.get.map(_ ==> List(1, 2))

      // Negating empty Seq of values matches all
      _ <- Entity.i.a1.zonedDateTimeMap_.hasNo(List.empty[ZonedDateTime]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Optional map (no filter)" - types { implicit conn =>
    val a = (1, Some(Map("a" -> zonedDateTime1, "b" -> zonedDateTime2)))
    val b = (2, Some(Map("a" -> zonedDateTime2, "b" -> zonedDateTime3, "c" -> zonedDateTime4)))
    val c = (3, None)
    for {
      _ <- Entity.i.zonedDateTimeMap_?.insert(a, b, c).transact
      _ <- Entity.i.a1.zonedDateTimeMap_?.query.get.map(_ ==> List(a, b, c))
    } yield ()
  }


  "Optional map values by key" - types { implicit conn =>

    for {
      _ <- Entity.i.zonedDateTimeMap.insert(a, b).transact
      _ <- Entity.i(3).save.transact // entity without zonedDateTimeMap

      // Like calling `get` on a Scala Map.
      _ <- Entity.i.a1.zonedDateTimeMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
      _ <- Entity.i.a1.zonedDateTimeMap_?("a").query.get.map(_ ==> List((1, Some(zonedDateTime1)), (2, Some(zonedDateTime2)), (3, None)))
      _ <- Entity.i.a1.zonedDateTimeMap_?("b").query.get.map(_ ==> List((1, Some(zonedDateTime2)), (2, Some(zonedDateTime3)), (3, None)))
      _ <- Entity.i.a1.zonedDateTimeMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(zonedDateTime4)), (3, None)))
    } yield ()
  }
}