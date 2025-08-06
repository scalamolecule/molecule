// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.map.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class FilterMap_Char_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Map("a" -> char1, "b" -> char2))
  val b = (2, Map("a" -> char2, "b" -> char3, "c" -> char4))

  import api.*
  import suite.*


  "Mandatory: Mandatory map (no filter)" - types {
    for {
      _ <- Entity.i.charMap.insert(a, b).transact
      _ <- Entity.i.a1.charMap.query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain keys" - types {
    for {
      _ <- Entity.i.charMap.insert(a, b).transact

      // Get Map value by key

      // Like calling `apply` on a Scala Map.
      _ <- Entity.i.a1.charMap("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap("a").query.get.map(_ ==> List((1, char1), (2, char2)))
      _ <- Entity.i.a1.charMap("b").query.get.map(_ ==> List((1, char2), (2, char3)))
      _ <- Entity.i.a1.charMap("c").query.get.map(_ ==> List((2, char4)))
    } yield ()
  }


  "Mandatory: Map without certain keys" - types {
    for {
      _ <- Entity.i.charMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.charMap.not("_").query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.charMap.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap.not("c").query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.charMap.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap.not("_", "c").query.get.map(_ ==> List(a))
      // Same as
      _ <- Entity.i.a1.charMap.not(List("_")).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.charMap.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap.not(List("c")).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.charMap.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap.not(List("_", "c")).query.get.map(_ ==> List(a))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.charMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain values" - types {
    for {
      _ <- Entity.i.charMap.insert(a, b).transact

      // Maps containing value

      _ <- Entity.i.a1.charMap.has(char0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap.has(char1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.charMap.has(char2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.charMap.has(char3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.charMap.has(char4).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.charMap.has(Seq(char0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap.has(Seq(char1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.charMap.has(Seq(char2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.charMap.has(Seq(char3)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.charMap.has(Seq(char4)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that" value
      _ <- Entity.i.a1.charMap.has(char0, char1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.charMap.has(char1, char2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.charMap.has(char2, char3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.charMap.has(char3, char4).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.charMap.has(char4, char5).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.charMap.has(char5, char6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.charMap.has(Seq(char0, char1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.charMap.has(Seq(char1, char2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.charMap.has(Seq(char2, char3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.charMap.has(Seq(char3, char4)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.charMap.has(Seq(char4, char5)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.charMap.has(Seq(char5, char6)).query.get.map(_ ==> List())

      // No values match nothing
      _ <- Entity.i.a1.charMap.has(Seq.empty[Char]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: Map without certain values" - types {
    for {
      _ <- Entity.i.charMap.insert(a, b).transact

      // "Doesn't have this"
      _ <- Entity.i.a1.charMap.hasNo(char0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.charMap.hasNo(char1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.charMap.hasNo(char2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap.hasNo(char3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.charMap.hasNo(char3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.charMap.hasNo(char5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.charMap.hasNo(List(char0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.charMap.hasNo(List(char1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.charMap.hasNo(List(char2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap.hasNo(List(char3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.charMap.hasNo(List(char3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.charMap.hasNo(List(char5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.charMap.hasNo(char1, char2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap.hasNo(char1, char3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap.hasNo(char1, char3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap.hasNo(char1, char5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.charMap.hasNo(List(char1, char2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap.hasNo(List(char1, char3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap.hasNo(List(char1, char3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap.hasNo(List(char1, char5)).query.get.map(_ ==> List(b))

      // No values match nothing
      _ <- Entity.i.a1.charMap.hasNo(List.empty[Char]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: Tacit Map (no filter)" - types {
    for {
      _ <- Entity.i.charMap.insert(a, b).transact
      _ <- Entity.i.a1.charMap_.query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain keys" - types {
    for {
      _ <- Entity.i.insert(0).transact // Entity without map attribute
      _ <- Entity.i.charMap.insert(a, b).transact

      // "Map contains this OR that key"
      _ <- Entity.i.a1.charMap_("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap_("a").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charMap_("b").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charMap_("c").query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.charMap_("a", "c").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charMap_("_", "c").query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.charMap_(List("_")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap_(List("a")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charMap_(List("b")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charMap_(List("c")).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.charMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charMap_(List("_", "c")).query.get.map(_ ==> List(2))

      // Empty Seq of keys matches nothing
      _ <- Entity.i.a1.charMap_(List.empty[String]).query.get.map(_ ==> List())

      // Match entities without map attribute
      _ <- Entity.i.a1.charMap_().query.get.map(_ ==> List(0))
    } yield ()
  }


  "Tacit: Match map without certain keys" - types {
    for {
      _ <- Entity.i.charMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.charMap_.not("_").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charMap_.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap_.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap_.not("c").query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charMap_.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap_.not("_", "c").query.get.map(_ ==> List(1))
      // Same as
      _ <- Entity.i.a1.charMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charMap_.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap_.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap_.not(List("c")).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charMap_.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.charMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain values" - types {
    for {
      _ <- Entity.i.charMap.insert(a, b).transact

      // "Map contains this OR that value"
      _ <- Entity.i.a1.charMap_.has(char0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap_.has(char1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charMap_.has(char2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charMap_.has(char3).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.charMap_.has(char4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.charMap_.has(char0, char1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charMap_.has(char1, char2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charMap_.has(char2, char3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charMap_.has(char3, char4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.charMap_.has(char4, char5).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.charMap_.has(char5, char6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.charMap_.has(List(char0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap_.has(List(char1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charMap_.has(List(char2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charMap_.has(List(char3)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.charMap_.has(List(char4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.charMap_.has(List(char0, char1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charMap_.has(List(char1, char2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charMap_.has(List(char2, char3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charMap_.has(List(char3, char4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.charMap_.has(List(char4, char5)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.charMap_.has(List(char5, char6)).query.get.map(_ ==> List())

      // Empty Seq of values matches nothing
      _ <- Entity.i.a1.charMap_.has(List.empty[Char]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: Match map without certain values" - types {
    for {
      _ <- Entity.i.charMap.insert(a, b).transact

      // "Map contains neither this OR that value"
      _ <- Entity.i.a1.charMap_.hasNo(char0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charMap_.hasNo(char1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.charMap_.hasNo(char2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap_.hasNo(char3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charMap_.hasNo(char4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charMap_.hasNo(char0, char1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.charMap_.hasNo(char1, char2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap_.hasNo(char2, char3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap_.hasNo(char3, char4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charMap_.hasNo(char4, char5).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charMap_.hasNo(char5, char6).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.charMap_.hasNo(List(char0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charMap_.hasNo(List(char1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.charMap_.hasNo(List(char2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap_.hasNo(List(char3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charMap_.hasNo(List(char4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charMap_.hasNo(List(char0, char1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.charMap_.hasNo(List(char1, char2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap_.hasNo(List(char2, char3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charMap_.hasNo(List(char3, char4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charMap_.hasNo(List(char4, char5)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charMap_.hasNo(List(char5, char6)).query.get.map(_ ==> List(1, 2))

      // Negating empty Seq of values matches all
      _ <- Entity.i.a1.charMap_.hasNo(List.empty[Char]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Optional map (no filter)" - types {
    val a = (1, Some(Map("a" -> char1, "b" -> char2)))
    val b = (2, Some(Map("a" -> char2, "b" -> char3, "c" -> char4)))
    val c = (3, None)
    for {
      _ <- Entity.i.charMap_?.insert(a, b, c).transact
      _ <- Entity.i.a1.charMap_?.query.get.map(_ ==> List(a, b, c))
    } yield ()
  }


  "Optional map values by key" - types {

    for {
      _ <- Entity.i.charMap.insert(a, b).transact
      _ <- Entity.i(3).save.transact // entity without charMap

      // Like calling `get` on a Scala Map.
      _ <- Entity.i.a1.charMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
      _ <- Entity.i.a1.charMap_?("a").query.get.map(_ ==> List((1, Some(char1)), (2, Some(char2)), (3, None)))
      _ <- Entity.i.a1.charMap_?("b").query.get.map(_ ==> List((1, Some(char2)), (2, Some(char3)), (3, None)))
      _ <- Entity.i.a1.charMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(char4)), (3, None)))
    } yield ()
  }
}