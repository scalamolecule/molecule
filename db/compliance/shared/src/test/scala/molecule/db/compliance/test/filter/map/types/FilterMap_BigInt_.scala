// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.map.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterMap_BigInt_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Map("a" -> bigInt1, "b" -> bigInt2))
  val b = (2, Map("a" -> bigInt2, "b" -> bigInt3, "c" -> bigInt4))

  import api.*
  import suite.*


  "Mandatory: Mandatory map (no filter)" - types {
    for {
      _ <- Entity.i.bigIntMap.insert(a, b).transact
      _ <- Entity.i.a1.bigIntMap.query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain keys" - types {
    for {
      _ <- Entity.i.bigIntMap.insert(a, b).transact

      // Get Map value by key

      // Like calling `apply` on a Scala Map.
      _ <- Entity.i.a1.bigIntMap("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap("a").query.get.map(_ ==> List((1, bigInt1), (2, bigInt2)))
      _ <- Entity.i.a1.bigIntMap("b").query.get.map(_ ==> List((1, bigInt2), (2, bigInt3)))
      _ <- Entity.i.a1.bigIntMap("c").query.get.map(_ ==> List((2, bigInt4)))
    } yield ()
  }


  "Mandatory: Map without certain keys" - types {
    for {
      _ <- Entity.i.bigIntMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.bigIntMap.not("_").query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntMap.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap.not("c").query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntMap.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap.not("_", "c").query.get.map(_ ==> List(a))
      // Same as
      _ <- Entity.i.a1.bigIntMap.not(List("_")).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntMap.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap.not(List("c")).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntMap.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap.not(List("_", "c")).query.get.map(_ ==> List(a))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.bigIntMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain values" - types {
    for {
      _ <- Entity.i.bigIntMap.insert(a, b).transact

      // Maps containing value

      _ <- Entity.i.a1.bigIntMap.has(bigInt0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap.has(bigInt1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntMap.has(bigInt2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntMap.has(bigInt3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigIntMap.has(bigInt4).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.bigIntMap.has(Seq(bigInt0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap.has(Seq(bigInt1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntMap.has(Seq(bigInt2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntMap.has(Seq(bigInt3)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigIntMap.has(Seq(bigInt4)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that" value
      _ <- Entity.i.a1.bigIntMap.has(bigInt0, bigInt1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntMap.has(bigInt1, bigInt2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntMap.has(bigInt2, bigInt3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntMap.has(bigInt3, bigInt4).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigIntMap.has(bigInt4, bigInt5).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigIntMap.has(bigInt5, bigInt6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.bigIntMap.has(Seq(bigInt0, bigInt1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntMap.has(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntMap.has(Seq(bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntMap.has(Seq(bigInt3, bigInt4)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigIntMap.has(Seq(bigInt4, bigInt5)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigIntMap.has(Seq(bigInt5, bigInt6)).query.get.map(_ ==> List())

      // No values match nothing
      _ <- Entity.i.a1.bigIntMap.has(Seq.empty[BigInt]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: Map without certain values" - types {
    for {
      _ <- Entity.i.bigIntMap.insert(a, b).transact

      // "Doesn't have this"
      _ <- Entity.i.a1.bigIntMap.hasNo(bigInt0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntMap.hasNo(bigInt1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigIntMap.hasNo(bigInt2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap.hasNo(bigInt3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntMap.hasNo(bigInt3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntMap.hasNo(bigInt5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.bigIntMap.hasNo(List(bigInt0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntMap.hasNo(List(bigInt1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigIntMap.hasNo(List(bigInt2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap.hasNo(List(bigInt3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntMap.hasNo(List(bigInt3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntMap.hasNo(List(bigInt5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.bigIntMap.hasNo(bigInt1, bigInt2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap.hasNo(bigInt1, bigInt5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.bigIntMap.hasNo(List(bigInt1, bigInt2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap.hasNo(List(bigInt1, bigInt3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap.hasNo(List(bigInt1, bigInt3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap.hasNo(List(bigInt1, bigInt5)).query.get.map(_ ==> List(b))

      // No values match nothing
      _ <- Entity.i.a1.bigIntMap.hasNo(List.empty[BigInt]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: Tacit Map (no filter)" - types {
    for {
      _ <- Entity.i.bigIntMap.insert(a, b).transact
      _ <- Entity.i.a1.bigIntMap_.query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain keys" - types {
    for {
      _ <- Entity.i.insert(0).transact // Entity without map attribute
      _ <- Entity.i.bigIntMap.insert(a, b).transact

      // "Map contains this OR that key"
      _ <- Entity.i.a1.bigIntMap_("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap_("a").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntMap_("b").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntMap_("c").query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigIntMap_("a", "c").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntMap_("_", "c").query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.bigIntMap_(List("_")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap_(List("a")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntMap_(List("b")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntMap_(List("c")).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigIntMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntMap_(List("_", "c")).query.get.map(_ ==> List(2))

      // Empty Seq of keys matches nothing
      _ <- Entity.i.a1.bigIntMap_(List.empty[String]).query.get.map(_ ==> List())

      // Match entities without map attribute
      _ <- Entity.i.a1.bigIntMap_().query.get.map(_ ==> List(0))
    } yield ()
  }


  "Tacit: Match map without certain keys" - types {
    for {
      _ <- Entity.i.bigIntMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.bigIntMap_.not("_").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntMap_.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap_.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap_.not("c").query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntMap_.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap_.not("_", "c").query.get.map(_ ==> List(1))
      // Same as
      _ <- Entity.i.a1.bigIntMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntMap_.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap_.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap_.not(List("c")).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntMap_.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.bigIntMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain values" - types {
    for {
      _ <- Entity.i.bigIntMap.insert(a, b).transact

      // "Map contains this OR that value"
      _ <- Entity.i.a1.bigIntMap_.has(bigInt0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap_.has(bigInt1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntMap_.has(bigInt2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntMap_.has(bigInt3).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigIntMap_.has(bigInt4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigIntMap_.has(bigInt0, bigInt1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntMap_.has(bigInt1, bigInt2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntMap_.has(bigInt2, bigInt3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntMap_.has(bigInt3, bigInt4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigIntMap_.has(bigInt4, bigInt5).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigIntMap_.has(bigInt5, bigInt6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.bigIntMap_.has(List(bigInt0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap_.has(List(bigInt1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntMap_.has(List(bigInt2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntMap_.has(List(bigInt3)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigIntMap_.has(List(bigInt4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigIntMap_.has(List(bigInt0, bigInt1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntMap_.has(List(bigInt1, bigInt2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntMap_.has(List(bigInt2, bigInt3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntMap_.has(List(bigInt3, bigInt4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigIntMap_.has(List(bigInt4, bigInt5)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigIntMap_.has(List(bigInt5, bigInt6)).query.get.map(_ ==> List())

      // Empty Seq of values matches nothing
      _ <- Entity.i.a1.bigIntMap_.has(List.empty[BigInt]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: Match map without certain values" - types {
    for {
      _ <- Entity.i.bigIntMap.insert(a, b).transact

      // "Map contains neither this OR that value"
      _ <- Entity.i.a1.bigIntMap_.hasNo(bigInt0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntMap_.hasNo(bigInt1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigIntMap_.hasNo(bigInt2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap_.hasNo(bigInt3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntMap_.hasNo(bigInt4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntMap_.hasNo(bigInt0, bigInt1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigIntMap_.hasNo(bigInt1, bigInt2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap_.hasNo(bigInt2, bigInt3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap_.hasNo(bigInt3, bigInt4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntMap_.hasNo(bigInt4, bigInt5).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntMap_.hasNo(bigInt5, bigInt6).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.bigIntMap_.hasNo(List(bigInt0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntMap_.hasNo(List(bigInt1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigIntMap_.hasNo(List(bigInt2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap_.hasNo(List(bigInt3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntMap_.hasNo(List(bigInt4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntMap_.hasNo(List(bigInt0, bigInt1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigIntMap_.hasNo(List(bigInt1, bigInt2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap_.hasNo(List(bigInt2, bigInt3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntMap_.hasNo(List(bigInt3, bigInt4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntMap_.hasNo(List(bigInt4, bigInt5)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntMap_.hasNo(List(bigInt5, bigInt6)).query.get.map(_ ==> List(1, 2))

      // Negating empty Seq of values matches all
      _ <- Entity.i.a1.bigIntMap_.hasNo(List.empty[BigInt]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Optional map (no filter)" - types {
    val a = (1, Some(Map("a" -> bigInt1, "b" -> bigInt2)))
    val b = (2, Some(Map("a" -> bigInt2, "b" -> bigInt3, "c" -> bigInt4)))
    val c = (3, None)
    for {
      _ <- Entity.i.bigIntMap_?.insert(a, b, c).transact
      _ <- Entity.i.a1.bigIntMap_?.query.get.map(_ ==> List(a, b, c))
    } yield ()
  }


  "Optional map values by key" - types {

    for {
      _ <- Entity.i.bigIntMap.insert(a, b).transact
      _ <- Entity.i(3).save.transact // entity without bigIntMap

      // Like calling `get` on a Scala Map.
      _ <- Entity.i.a1.bigIntMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
      _ <- Entity.i.a1.bigIntMap_?("a").query.get.map(_ ==> List((1, Some(bigInt1)), (2, Some(bigInt2)), (3, None)))
      _ <- Entity.i.a1.bigIntMap_?("b").query.get.map(_ ==> List((1, Some(bigInt2)), (2, Some(bigInt3)), (3, None)))
      _ <- Entity.i.a1.bigIntMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(bigInt4)), (3, None)))
    } yield ()
  }
}