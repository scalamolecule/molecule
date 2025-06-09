// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.map.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterMap_String_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Map("a" -> string1, "b" -> string2))
  val b = (2, Map("a" -> string2, "b" -> string3, "c" -> string4))

  import api.*
  import suite.*


  "Mandatory: Mandatory map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.stringMap.insert(a, b).transact
      _ <- Entity.i.a1.stringMap.query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.stringMap.insert(a, b).transact

      // Get Map value by key

      // Like calling `apply` on a Scala Map.
      _ <- Entity.i.a1.stringMap("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap("a").query.get.map(_ ==> List((1, string1), (2, string2)))
      _ <- Entity.i.a1.stringMap("b").query.get.map(_ ==> List((1, string2), (2, string3)))
      _ <- Entity.i.a1.stringMap("c").query.get.map(_ ==> List((2, string4)))
    } yield ()
  }


  "Mandatory: Map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.stringMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.stringMap.not("_").query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringMap.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap.not("c").query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringMap.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap.not("_", "c").query.get.map(_ ==> List(a))
      // Same as
      _ <- Entity.i.a1.stringMap.not(List("_")).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringMap.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap.not(List("c")).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringMap.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap.not(List("_", "c")).query.get.map(_ ==> List(a))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.stringMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.stringMap.insert(a, b).transact

      // Maps containing value

      _ <- Entity.i.a1.stringMap.has(string0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap.has(string1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringMap.has(string2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringMap.has(string3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.stringMap.has(string4).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.stringMap.has(Seq(string0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap.has(Seq(string1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringMap.has(Seq(string2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringMap.has(Seq(string3)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.stringMap.has(Seq(string4)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that" value
      _ <- Entity.i.a1.stringMap.has(string0, string1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringMap.has(string1, string2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringMap.has(string2, string3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringMap.has(string3, string4).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.stringMap.has(string4, string5).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.stringMap.has(string5, string6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.stringMap.has(Seq(string0, string1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringMap.has(Seq(string1, string2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringMap.has(Seq(string2, string3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringMap.has(Seq(string3, string4)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.stringMap.has(Seq(string4, string5)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.stringMap.has(Seq(string5, string6)).query.get.map(_ ==> List())

      // No values match nothing
      _ <- Entity.i.a1.stringMap.has(Seq.empty[String]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: Map without certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.stringMap.insert(a, b).transact

      // "Doesn't have this"
      _ <- Entity.i.a1.stringMap.hasNo(string0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringMap.hasNo(string1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.stringMap.hasNo(string2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap.hasNo(string3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringMap.hasNo(string3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringMap.hasNo(string5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.stringMap.hasNo(List(string0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringMap.hasNo(List(string1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.stringMap.hasNo(List(string2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap.hasNo(List(string3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringMap.hasNo(List(string3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringMap.hasNo(List(string5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.stringMap.hasNo(string1, string2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap.hasNo(string1, string3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap.hasNo(string1, string3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap.hasNo(string1, string5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.stringMap.hasNo(List(string1, string2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap.hasNo(List(string1, string3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap.hasNo(List(string1, string3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap.hasNo(List(string1, string5)).query.get.map(_ ==> List(b))

      // No values match nothing
      _ <- Entity.i.a1.stringMap.hasNo(List.empty[String]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: Tacit Map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.stringMap.insert(a, b).transact
      _ <- Entity.i.a1.stringMap_.query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.insert(0).transact // Entity without map attribute
      _ <- Entity.i.stringMap.insert(a, b).transact

      // "Map contains this OR that key"
      _ <- Entity.i.a1.stringMap_("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap_("a").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringMap_("b").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringMap_("c").query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.stringMap_("a", "c").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringMap_("_", "c").query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.stringMap_(List("_")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap_(List("a")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringMap_(List("b")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringMap_(List("c")).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.stringMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringMap_(List("_", "c")).query.get.map(_ ==> List(2))

      // Empty Seq of keys matches nothing
      _ <- Entity.i.a1.stringMap_(List.empty[String]).query.get.map(_ ==> List())

      // Match entities without map attribute
      _ <- Entity.i.a1.stringMap_().query.get.map(_ ==> List(0))
    } yield ()
  }


  "Tacit: Match map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.stringMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.stringMap_.not("_").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringMap_.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap_.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap_.not("c").query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringMap_.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap_.not("_", "c").query.get.map(_ ==> List(1))
      // Same as
      _ <- Entity.i.a1.stringMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringMap_.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap_.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap_.not(List("c")).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringMap_.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.stringMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.stringMap.insert(a, b).transact

      // "Map contains this OR that value"
      _ <- Entity.i.a1.stringMap_.has(string0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap_.has(string1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringMap_.has(string2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringMap_.has(string3).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.stringMap_.has(string4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.stringMap_.has(string0, string1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringMap_.has(string1, string2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringMap_.has(string2, string3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringMap_.has(string3, string4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.stringMap_.has(string4, string5).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.stringMap_.has(string5, string6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.stringMap_.has(List(string0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap_.has(List(string1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringMap_.has(List(string2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringMap_.has(List(string3)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.stringMap_.has(List(string4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.stringMap_.has(List(string0, string1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringMap_.has(List(string1, string2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringMap_.has(List(string2, string3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringMap_.has(List(string3, string4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.stringMap_.has(List(string4, string5)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.stringMap_.has(List(string5, string6)).query.get.map(_ ==> List())

      // Empty Seq of values matches nothing
      _ <- Entity.i.a1.stringMap_.has(List.empty[String]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: Match map without certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.stringMap.insert(a, b).transact

      // "Map contains neither this OR that value"
      _ <- Entity.i.a1.stringMap_.hasNo(string0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringMap_.hasNo(string1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.stringMap_.hasNo(string2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap_.hasNo(string3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringMap_.hasNo(string4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringMap_.hasNo(string0, string1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.stringMap_.hasNo(string1, string2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap_.hasNo(string2, string3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap_.hasNo(string3, string4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringMap_.hasNo(string4, string5).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringMap_.hasNo(string5, string6).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.stringMap_.hasNo(List(string0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringMap_.hasNo(List(string1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.stringMap_.hasNo(List(string2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap_.hasNo(List(string3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringMap_.hasNo(List(string4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringMap_.hasNo(List(string0, string1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.stringMap_.hasNo(List(string1, string2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap_.hasNo(List(string2, string3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringMap_.hasNo(List(string3, string4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringMap_.hasNo(List(string4, string5)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringMap_.hasNo(List(string5, string6)).query.get.map(_ ==> List(1, 2))

      // Negating empty Seq of values matches all
      _ <- Entity.i.a1.stringMap_.hasNo(List.empty[String]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Optional map (no filter)" - types { implicit conn =>
    val a = (1, Some(Map("a" -> string1, "b" -> string2)))
    val b = (2, Some(Map("a" -> string2, "b" -> string3, "c" -> string4)))
    val c = (3, None)
    for {
      _ <- Entity.i.stringMap_?.insert(a, b, c).transact
      _ <- Entity.i.a1.stringMap_?.query.get.map(_ ==> List(a, b, c))
    } yield ()
  }


  "Optional map values by key" - types { implicit conn =>

    for {
      _ <- Entity.i.stringMap.insert(a, b).transact
      _ <- Entity.i(3).save.transact // entity without stringMap

      // Like calling `get` on a Scala Map.
      _ <- Entity.i.a1.stringMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
      _ <- Entity.i.a1.stringMap_?("a").query.get.map(_ ==> List((1, Some(string1)), (2, Some(string2)), (3, None)))
      _ <- Entity.i.a1.stringMap_?("b").query.get.map(_ ==> List((1, Some(string2)), (2, Some(string3)), (3, None)))
      _ <- Entity.i.a1.stringMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(string4)), (3, None)))
    } yield ()
  }
}