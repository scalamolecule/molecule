// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.map.types

import java.net.URI
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterMap_URI_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Map("a" -> uri1, "b" -> uri2))
  val b = (2, Map("a" -> uri2, "b" -> uri3, "c" -> uri4))

  import api.*
  import suite.*


  "Mandatory: Mandatory map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.uriMap.insert(a, b).transact
      _ <- Entity.i.a1.uriMap.query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.uriMap.insert(a, b).transact

      // Get Map value by key

      // Like calling `apply` on a Scala Map.
      _ <- Entity.i.a1.uriMap("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap("a").query.get.map(_ ==> List((1, uri1), (2, uri2)))
      _ <- Entity.i.a1.uriMap("b").query.get.map(_ ==> List((1, uri2), (2, uri3)))
      _ <- Entity.i.a1.uriMap("c").query.get.map(_ ==> List((2, uri4)))
    } yield ()
  }


  "Mandatory: Map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.uriMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.uriMap.not("_").query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriMap.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap.not("c").query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriMap.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap.not("_", "c").query.get.map(_ ==> List(a))
      // Same as
      _ <- Entity.i.a1.uriMap.not(List("_")).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriMap.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap.not(List("c")).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriMap.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap.not(List("_", "c")).query.get.map(_ ==> List(a))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.uriMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.uriMap.insert(a, b).transact

      // Maps containing value

      _ <- Entity.i.a1.uriMap.has(uri0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap.has(uri1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriMap.has(uri2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriMap.has(uri3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uriMap.has(uri4).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.uriMap.has(Seq(uri0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap.has(Seq(uri1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriMap.has(Seq(uri2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriMap.has(Seq(uri3)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uriMap.has(Seq(uri4)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that" value
      _ <- Entity.i.a1.uriMap.has(uri0, uri1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriMap.has(uri1, uri2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriMap.has(uri2, uri3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriMap.has(uri3, uri4).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uriMap.has(uri4, uri5).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uriMap.has(uri5, uri6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.uriMap.has(Seq(uri0, uri1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriMap.has(Seq(uri1, uri2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriMap.has(Seq(uri2, uri3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriMap.has(Seq(uri3, uri4)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uriMap.has(Seq(uri4, uri5)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uriMap.has(Seq(uri5, uri6)).query.get.map(_ ==> List())

      // No values match nothing
      _ <- Entity.i.a1.uriMap.has(Seq.empty[URI]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: Map without certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.uriMap.insert(a, b).transact

      // "Doesn't have this"
      _ <- Entity.i.a1.uriMap.hasNo(uri0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriMap.hasNo(uri1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uriMap.hasNo(uri2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap.hasNo(uri3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriMap.hasNo(uri3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriMap.hasNo(uri5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.uriMap.hasNo(List(uri0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriMap.hasNo(List(uri1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uriMap.hasNo(List(uri2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap.hasNo(List(uri3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriMap.hasNo(List(uri3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriMap.hasNo(List(uri5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.uriMap.hasNo(uri1, uri2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap.hasNo(uri1, uri3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap.hasNo(uri1, uri3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap.hasNo(uri1, uri5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.uriMap.hasNo(List(uri1, uri2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap.hasNo(List(uri1, uri3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap.hasNo(List(uri1, uri3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap.hasNo(List(uri1, uri5)).query.get.map(_ ==> List(b))

      // No values match nothing
      _ <- Entity.i.a1.uriMap.hasNo(List.empty[URI]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: Tacit Map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.uriMap.insert(a, b).transact
      _ <- Entity.i.a1.uriMap_.query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.insert(0).transact // Entity without map attribute
      _ <- Entity.i.uriMap.insert(a, b).transact

      // "Map contains this OR that key"
      _ <- Entity.i.a1.uriMap_("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap_("a").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriMap_("b").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriMap_("c").query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uriMap_("a", "c").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriMap_("_", "c").query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.uriMap_(List("_")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap_(List("a")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriMap_(List("b")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriMap_(List("c")).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uriMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriMap_(List("_", "c")).query.get.map(_ ==> List(2))

      // Empty Seq of keys matches nothing
      _ <- Entity.i.a1.uriMap_(List.empty[String]).query.get.map(_ ==> List())

      // Match entities without map attribute
      _ <- Entity.i.a1.uriMap_().query.get.map(_ ==> List(0))
    } yield ()
  }


  "Tacit: Match map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.uriMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.uriMap_.not("_").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriMap_.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap_.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap_.not("c").query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriMap_.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap_.not("_", "c").query.get.map(_ ==> List(1))
      // Same as
      _ <- Entity.i.a1.uriMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriMap_.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap_.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap_.not(List("c")).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriMap_.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.uriMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.uriMap.insert(a, b).transact

      // "Map contains this OR that value"
      _ <- Entity.i.a1.uriMap_.has(uri0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap_.has(uri1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriMap_.has(uri2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriMap_.has(uri3).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uriMap_.has(uri4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uriMap_.has(uri0, uri1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriMap_.has(uri1, uri2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriMap_.has(uri2, uri3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriMap_.has(uri3, uri4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uriMap_.has(uri4, uri5).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uriMap_.has(uri5, uri6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.uriMap_.has(List(uri0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap_.has(List(uri1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriMap_.has(List(uri2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriMap_.has(List(uri3)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uriMap_.has(List(uri4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uriMap_.has(List(uri0, uri1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriMap_.has(List(uri1, uri2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriMap_.has(List(uri2, uri3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriMap_.has(List(uri3, uri4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uriMap_.has(List(uri4, uri5)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uriMap_.has(List(uri5, uri6)).query.get.map(_ ==> List())

      // Empty Seq of values matches nothing
      _ <- Entity.i.a1.uriMap_.has(List.empty[URI]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: Match map without certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.uriMap.insert(a, b).transact

      // "Map contains neither this OR that value"
      _ <- Entity.i.a1.uriMap_.hasNo(uri0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriMap_.hasNo(uri1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uriMap_.hasNo(uri2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap_.hasNo(uri3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriMap_.hasNo(uri4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriMap_.hasNo(uri0, uri1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uriMap_.hasNo(uri1, uri2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap_.hasNo(uri2, uri3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap_.hasNo(uri3, uri4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriMap_.hasNo(uri4, uri5).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriMap_.hasNo(uri5, uri6).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.uriMap_.hasNo(List(uri0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriMap_.hasNo(List(uri1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uriMap_.hasNo(List(uri2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap_.hasNo(List(uri3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriMap_.hasNo(List(uri4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriMap_.hasNo(List(uri0, uri1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uriMap_.hasNo(List(uri1, uri2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap_.hasNo(List(uri2, uri3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriMap_.hasNo(List(uri3, uri4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriMap_.hasNo(List(uri4, uri5)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriMap_.hasNo(List(uri5, uri6)).query.get.map(_ ==> List(1, 2))

      // Negating empty Seq of values matches all
      _ <- Entity.i.a1.uriMap_.hasNo(List.empty[URI]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Optional map (no filter)" - types { implicit conn =>
    val a = (1, Some(Map("a" -> uri1, "b" -> uri2)))
    val b = (2, Some(Map("a" -> uri2, "b" -> uri3, "c" -> uri4)))
    val c = (3, None)
    for {
      _ <- Entity.i.uriMap_?.insert(a, b, c).transact
      _ <- Entity.i.a1.uriMap_?.query.get.map(_ ==> List(a, b, c))
    } yield ()
  }


  "Optional map values by key" - types { implicit conn =>

    for {
      _ <- Entity.i.uriMap.insert(a, b).transact
      _ <- Entity.i(3).save.transact // entity without uriMap

      // Like calling `get` on a Scala Map.
      _ <- Entity.i.a1.uriMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
      _ <- Entity.i.a1.uriMap_?("a").query.get.map(_ ==> List((1, Some(uri1)), (2, Some(uri2)), (3, None)))
      _ <- Entity.i.a1.uriMap_?("b").query.get.map(_ ==> List((1, Some(uri2)), (2, Some(uri3)), (3, None)))
      _ <- Entity.i.a1.uriMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(uri4)), (3, None)))
    } yield ()
  }
}