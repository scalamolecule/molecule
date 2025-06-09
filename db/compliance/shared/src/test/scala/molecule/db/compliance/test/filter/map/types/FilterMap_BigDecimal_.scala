// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.map.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterMap_BigDecimal_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Map("a" -> bigDecimal1, "b" -> bigDecimal2))
  val b = (2, Map("a" -> bigDecimal2, "b" -> bigDecimal3, "c" -> bigDecimal4))

  import api.*
  import suite.*


  "Mandatory: Mandatory map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.bigDecimalMap.insert(a, b).transact
      _ <- Entity.i.a1.bigDecimalMap.query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.bigDecimalMap.insert(a, b).transact

      // Get Map value by key

      // Like calling `apply` on a Scala Map.
      _ <- Entity.i.a1.bigDecimalMap("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap("a").query.get.map(_ ==> List((1, bigDecimal1), (2, bigDecimal2)))
      _ <- Entity.i.a1.bigDecimalMap("b").query.get.map(_ ==> List((1, bigDecimal2), (2, bigDecimal3)))
      _ <- Entity.i.a1.bigDecimalMap("c").query.get.map(_ ==> List((2, bigDecimal4)))
    } yield ()
  }


  "Mandatory: Map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.bigDecimalMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.bigDecimalMap.not("_").query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimalMap.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap.not("c").query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimalMap.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap.not("_", "c").query.get.map(_ ==> List(a))
      // Same as
      _ <- Entity.i.a1.bigDecimalMap.not(List("_")).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimalMap.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap.not(List("c")).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimalMap.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap.not(List("_", "c")).query.get.map(_ ==> List(a))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.bigDecimalMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.bigDecimalMap.insert(a, b).transact

      // Maps containing value

      _ <- Entity.i.a1.bigDecimalMap.has(bigDecimal0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap.has(bigDecimal1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimalMap.has(bigDecimal2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimalMap.has(bigDecimal3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigDecimalMap.has(bigDecimal4).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.bigDecimalMap.has(Seq(bigDecimal0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap.has(Seq(bigDecimal1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimalMap.has(Seq(bigDecimal2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimalMap.has(Seq(bigDecimal3)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigDecimalMap.has(Seq(bigDecimal4)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that" value
      _ <- Entity.i.a1.bigDecimalMap.has(bigDecimal0, bigDecimal1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimalMap.has(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimalMap.has(bigDecimal2, bigDecimal3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimalMap.has(bigDecimal3, bigDecimal4).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigDecimalMap.has(bigDecimal4, bigDecimal5).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigDecimalMap.has(bigDecimal5, bigDecimal6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.bigDecimalMap.has(Seq(bigDecimal0, bigDecimal1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimalMap.has(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimalMap.has(Seq(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimalMap.has(Seq(bigDecimal3, bigDecimal4)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigDecimalMap.has(Seq(bigDecimal4, bigDecimal5)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigDecimalMap.has(Seq(bigDecimal5, bigDecimal6)).query.get.map(_ ==> List())

      // No values match nothing
      _ <- Entity.i.a1.bigDecimalMap.has(Seq.empty[BigDecimal]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: Map without certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.bigDecimalMap.insert(a, b).transact

      // "Doesn't have this"
      _ <- Entity.i.a1.bigDecimalMap.hasNo(bigDecimal0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimalMap.hasNo(bigDecimal1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigDecimalMap.hasNo(bigDecimal2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap.hasNo(bigDecimal3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimalMap.hasNo(bigDecimal3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimalMap.hasNo(bigDecimal5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.bigDecimalMap.hasNo(List(bigDecimal0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimalMap.hasNo(List(bigDecimal1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigDecimalMap.hasNo(List(bigDecimal2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap.hasNo(List(bigDecimal3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimalMap.hasNo(List(bigDecimal3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimalMap.hasNo(List(bigDecimal5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.bigDecimalMap.hasNo(bigDecimal1, bigDecimal2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap.hasNo(bigDecimal1, bigDecimal3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap.hasNo(bigDecimal1, bigDecimal3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap.hasNo(bigDecimal1, bigDecimal5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.bigDecimalMap.hasNo(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap.hasNo(List(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap.hasNo(List(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap.hasNo(List(bigDecimal1, bigDecimal5)).query.get.map(_ ==> List(b))

      // No values match nothing
      _ <- Entity.i.a1.bigDecimalMap.hasNo(List.empty[BigDecimal]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: Tacit Map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.bigDecimalMap.insert(a, b).transact
      _ <- Entity.i.a1.bigDecimalMap_.query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.insert(0).transact // Entity without map attribute
      _ <- Entity.i.bigDecimalMap.insert(a, b).transact

      // "Map contains this OR that key"
      _ <- Entity.i.a1.bigDecimalMap_("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap_("a").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalMap_("b").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalMap_("c").query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigDecimalMap_("a", "c").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalMap_("_", "c").query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.bigDecimalMap_(List("_")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap_(List("a")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalMap_(List("b")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalMap_(List("c")).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigDecimalMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalMap_(List("_", "c")).query.get.map(_ ==> List(2))

      // Empty Seq of keys matches nothing
      _ <- Entity.i.a1.bigDecimalMap_(List.empty[String]).query.get.map(_ ==> List())

      // Match entities without map attribute
      _ <- Entity.i.a1.bigDecimalMap_().query.get.map(_ ==> List(0))
    } yield ()
  }


  "Tacit: Match map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.bigDecimalMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.bigDecimalMap_.not("_").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalMap_.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap_.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap_.not("c").query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalMap_.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap_.not("_", "c").query.get.map(_ ==> List(1))
      // Same as
      _ <- Entity.i.a1.bigDecimalMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalMap_.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap_.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap_.not(List("c")).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalMap_.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.bigDecimalMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.bigDecimalMap.insert(a, b).transact

      // "Map contains this OR that value"
      _ <- Entity.i.a1.bigDecimalMap_.has(bigDecimal0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap_.has(bigDecimal1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalMap_.has(bigDecimal2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalMap_.has(bigDecimal3).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigDecimalMap_.has(bigDecimal4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigDecimalMap_.has(bigDecimal0, bigDecimal1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalMap_.has(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalMap_.has(bigDecimal2, bigDecimal3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalMap_.has(bigDecimal3, bigDecimal4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigDecimalMap_.has(bigDecimal4, bigDecimal5).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigDecimalMap_.has(bigDecimal5, bigDecimal6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.bigDecimalMap_.has(List(bigDecimal0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap_.has(List(bigDecimal1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalMap_.has(List(bigDecimal2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalMap_.has(List(bigDecimal3)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigDecimalMap_.has(List(bigDecimal4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigDecimalMap_.has(List(bigDecimal0, bigDecimal1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalMap_.has(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalMap_.has(List(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalMap_.has(List(bigDecimal3, bigDecimal4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigDecimalMap_.has(List(bigDecimal4, bigDecimal5)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigDecimalMap_.has(List(bigDecimal5, bigDecimal6)).query.get.map(_ ==> List())

      // Empty Seq of values matches nothing
      _ <- Entity.i.a1.bigDecimalMap_.has(List.empty[BigDecimal]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: Match map without certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.bigDecimalMap.insert(a, b).transact

      // "Map contains neither this OR that value"
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(bigDecimal0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(bigDecimal1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(bigDecimal2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(bigDecimal3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(bigDecimal4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(bigDecimal0, bigDecimal1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(bigDecimal1, bigDecimal2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(bigDecimal2, bigDecimal3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(bigDecimal3, bigDecimal4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(bigDecimal4, bigDecimal5).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(bigDecimal5, bigDecimal6).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(List(bigDecimal0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(List(bigDecimal1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(List(bigDecimal2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(List(bigDecimal3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(List(bigDecimal4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(List(bigDecimal0, bigDecimal1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(List(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(List(bigDecimal3, bigDecimal4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(List(bigDecimal4, bigDecimal5)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(List(bigDecimal5, bigDecimal6)).query.get.map(_ ==> List(1, 2))

      // Negating empty Seq of values matches all
      _ <- Entity.i.a1.bigDecimalMap_.hasNo(List.empty[BigDecimal]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Optional map (no filter)" - types { implicit conn =>
    val a = (1, Some(Map("a" -> bigDecimal1, "b" -> bigDecimal2)))
    val b = (2, Some(Map("a" -> bigDecimal2, "b" -> bigDecimal3, "c" -> bigDecimal4)))
    val c = (3, None)
    for {
      _ <- Entity.i.bigDecimalMap_?.insert(a, b, c).transact
      _ <- Entity.i.a1.bigDecimalMap_?.query.get.map(_ ==> List(a, b, c))
    } yield ()
  }


  "Optional map values by key" - types { implicit conn =>

    for {
      _ <- Entity.i.bigDecimalMap.insert(a, b).transact
      _ <- Entity.i(3).save.transact // entity without bigDecimalMap

      // Like calling `get` on a Scala Map.
      _ <- Entity.i.a1.bigDecimalMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
      _ <- Entity.i.a1.bigDecimalMap_?("a").query.get.map(_ ==> List((1, Some(bigDecimal1)), (2, Some(bigDecimal2)), (3, None)))
      _ <- Entity.i.a1.bigDecimalMap_?("b").query.get.map(_ ==> List((1, Some(bigDecimal2)), (2, Some(bigDecimal3)), (3, None)))
      _ <- Entity.i.a1.bigDecimalMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(bigDecimal4)), (3, None)))
    } yield ()
  }
}