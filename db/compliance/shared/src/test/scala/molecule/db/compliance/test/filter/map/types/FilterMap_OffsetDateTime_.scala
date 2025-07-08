// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.map.types

import java.time.OffsetDateTime
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterMap_OffsetDateTime_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Map("a" -> offsetDateTime1, "b" -> offsetDateTime2))
  val b = (2, Map("a" -> offsetDateTime2, "b" -> offsetDateTime3, "c" -> offsetDateTime4))

  import api.*
  import suite.*


  "Mandatory: Mandatory map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.offsetDateTimeMap.insert(a, b).transact
      _ <- Entity.i.a1.offsetDateTimeMap.query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.offsetDateTimeMap.insert(a, b).transact

      // Get Map value by key

      // Like calling `apply` on a Scala Map.
      _ <- Entity.i.a1.offsetDateTimeMap("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap("a").query.get.map(_ ==> List((1, offsetDateTime1), (2, offsetDateTime2)))
      _ <- Entity.i.a1.offsetDateTimeMap("b").query.get.map(_ ==> List((1, offsetDateTime2), (2, offsetDateTime3)))
      _ <- Entity.i.a1.offsetDateTimeMap("c").query.get.map(_ ==> List((2, offsetDateTime4)))
    } yield ()
  }


  "Mandatory: Map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.offsetDateTimeMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.offsetDateTimeMap.not("_").query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTimeMap.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap.not("c").query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTimeMap.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap.not("_", "c").query.get.map(_ ==> List(a))
      // Same as
      _ <- Entity.i.a1.offsetDateTimeMap.not(List("_")).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTimeMap.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap.not(List("c")).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTimeMap.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap.not(List("_", "c")).query.get.map(_ ==> List(a))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.offsetDateTimeMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.offsetDateTimeMap.insert(a, b).transact

      // Maps containing value

      _ <- Entity.i.a1.offsetDateTimeMap.has(offsetDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap.has(offsetDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTimeMap.has(offsetDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTimeMap.has(offsetDateTime3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetDateTimeMap.has(offsetDateTime4).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.offsetDateTimeMap.has(Seq(offsetDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap.has(Seq(offsetDateTime1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTimeMap.has(Seq(offsetDateTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTimeMap.has(Seq(offsetDateTime3)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetDateTimeMap.has(Seq(offsetDateTime4)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that" value
      _ <- Entity.i.a1.offsetDateTimeMap.has(offsetDateTime0, offsetDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTimeMap.has(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTimeMap.has(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTimeMap.has(offsetDateTime3, offsetDateTime4).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetDateTimeMap.has(offsetDateTime4, offsetDateTime5).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetDateTimeMap.has(offsetDateTime5, offsetDateTime6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.offsetDateTimeMap.has(Seq(offsetDateTime0, offsetDateTime1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTimeMap.has(Seq(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTimeMap.has(Seq(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTimeMap.has(Seq(offsetDateTime3, offsetDateTime4)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetDateTimeMap.has(Seq(offsetDateTime4, offsetDateTime5)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetDateTimeMap.has(Seq(offsetDateTime5, offsetDateTime6)).query.get.map(_ ==> List())

      // No values match nothing
      _ <- Entity.i.a1.offsetDateTimeMap.has(Seq.empty[OffsetDateTime]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: Map without certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.offsetDateTimeMap.insert(a, b).transact

      // "Doesn't have this"
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(offsetDateTime0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(offsetDateTime1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(offsetDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(offsetDateTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(offsetDateTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(offsetDateTime5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(List(offsetDateTime0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(List(offsetDateTime1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(List(offsetDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(List(offsetDateTime3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(List(offsetDateTime3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(List(offsetDateTime5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(offsetDateTime1, offsetDateTime5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(List(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(List(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(List(offsetDateTime1, offsetDateTime5)).query.get.map(_ ==> List(b))

      // No values match nothing
      _ <- Entity.i.a1.offsetDateTimeMap.hasNo(List.empty[OffsetDateTime]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: Tacit Map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.offsetDateTimeMap.insert(a, b).transact
      _ <- Entity.i.a1.offsetDateTimeMap_.query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.insert(0).transact // Entity without map attribute
      _ <- Entity.i.offsetDateTimeMap.insert(a, b).transact

      // "Map contains this OR that key"
      _ <- Entity.i.a1.offsetDateTimeMap_("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap_("a").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeMap_("b").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeMap_("c").query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.offsetDateTimeMap_("a", "c").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeMap_("_", "c").query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.offsetDateTimeMap_(List("_")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap_(List("a")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeMap_(List("b")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeMap_(List("c")).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.offsetDateTimeMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeMap_(List("_", "c")).query.get.map(_ ==> List(2))

      // Empty Seq of keys matches nothing
      _ <- Entity.i.a1.offsetDateTimeMap_(List.empty[String]).query.get.map(_ ==> List())

      // Match entities without map attribute
      _ <- Entity.i.a1.offsetDateTimeMap_().query.get.map(_ ==> List(0))
    } yield ()
  }


  "Tacit: Match map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.offsetDateTimeMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.offsetDateTimeMap_.not("_").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeMap_.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap_.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap_.not("c").query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeMap_.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap_.not("_", "c").query.get.map(_ ==> List(1))
      // Same as
      _ <- Entity.i.a1.offsetDateTimeMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeMap_.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap_.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap_.not(List("c")).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeMap_.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.offsetDateTimeMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.offsetDateTimeMap.insert(a, b).transact

      // "Map contains this OR that value"
      _ <- Entity.i.a1.offsetDateTimeMap_.has(offsetDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap_.has(offsetDateTime1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeMap_.has(offsetDateTime2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeMap_.has(offsetDateTime3).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.offsetDateTimeMap_.has(offsetDateTime4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.offsetDateTimeMap_.has(offsetDateTime0, offsetDateTime1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeMap_.has(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeMap_.has(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeMap_.has(offsetDateTime3, offsetDateTime4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.offsetDateTimeMap_.has(offsetDateTime4, offsetDateTime5).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.offsetDateTimeMap_.has(offsetDateTime5, offsetDateTime6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.offsetDateTimeMap_.has(List(offsetDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap_.has(List(offsetDateTime1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeMap_.has(List(offsetDateTime2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeMap_.has(List(offsetDateTime3)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.offsetDateTimeMap_.has(List(offsetDateTime4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.offsetDateTimeMap_.has(List(offsetDateTime0, offsetDateTime1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeMap_.has(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeMap_.has(List(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeMap_.has(List(offsetDateTime3, offsetDateTime4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.offsetDateTimeMap_.has(List(offsetDateTime4, offsetDateTime5)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.offsetDateTimeMap_.has(List(offsetDateTime5, offsetDateTime6)).query.get.map(_ ==> List())

      // Empty Seq of values matches nothing
      _ <- Entity.i.a1.offsetDateTimeMap_.has(List.empty[OffsetDateTime]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: Match map without certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.offsetDateTimeMap.insert(a, b).transact

      // "Map contains neither this OR that value"
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(offsetDateTime0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(offsetDateTime1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(offsetDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(offsetDateTime3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(offsetDateTime4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(offsetDateTime0, offsetDateTime1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(offsetDateTime3, offsetDateTime4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(offsetDateTime4, offsetDateTime5).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(offsetDateTime5, offsetDateTime6).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(List(offsetDateTime0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(List(offsetDateTime1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(List(offsetDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(List(offsetDateTime3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(List(offsetDateTime4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(List(offsetDateTime0, offsetDateTime1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(List(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(List(offsetDateTime3, offsetDateTime4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(List(offsetDateTime4, offsetDateTime5)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(List(offsetDateTime5, offsetDateTime6)).query.get.map(_ ==> List(1, 2))

      // Negating empty Seq of values matches all
      _ <- Entity.i.a1.offsetDateTimeMap_.hasNo(List.empty[OffsetDateTime]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Optional map (no filter)" - types { implicit conn =>
    val a = (1, Some(Map("a" -> offsetDateTime1, "b" -> offsetDateTime2)))
    val b = (2, Some(Map("a" -> offsetDateTime2, "b" -> offsetDateTime3, "c" -> offsetDateTime4)))
    val c = (3, None)
    for {
      _ <- Entity.i.offsetDateTimeMap_?.insert(a, b, c).transact
      _ <- Entity.i.a1.offsetDateTimeMap_?.query.get.map(_ ==> List(a, b, c))
    } yield ()
  }


  "Optional map values by key" - types { implicit conn =>

    for {
      _ <- Entity.i.offsetDateTimeMap.insert(a, b).transact
      _ <- Entity.i(3).save.transact // entity without offsetDateTimeMap

      // Like calling `get` on a Scala Map.
      _ <- Entity.i.a1.offsetDateTimeMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
      _ <- Entity.i.a1.offsetDateTimeMap_?("a").query.get.map(_ ==> List((1, Some(offsetDateTime1)), (2, Some(offsetDateTime2)), (3, None)))
      _ <- Entity.i.a1.offsetDateTimeMap_?("b").query.get.map(_ ==> List((1, Some(offsetDateTime2)), (2, Some(offsetDateTime3)), (3, None)))
      _ <- Entity.i.a1.offsetDateTimeMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(offsetDateTime4)), (3, None)))
    } yield ()
  }
}