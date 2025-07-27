// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.map.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterMap_Byte_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Map("a" -> byte1, "b" -> byte2))
  val b = (2, Map("a" -> byte2, "b" -> byte3, "c" -> byte4))

  import api.*
  import suite.*


  "Mandatory: Mandatory map (no filter)" - types {
    for {
      _ <- Entity.i.byteMap.insert(a, b).transact
      _ <- Entity.i.a1.byteMap.query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain keys" - types {
    for {
      _ <- Entity.i.byteMap.insert(a, b).transact

      // Get Map value by key

      // Like calling `apply` on a Scala Map.
      _ <- Entity.i.a1.byteMap("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap("a").query.get.map(_ ==> List((1, byte1), (2, byte2)))
      _ <- Entity.i.a1.byteMap("b").query.get.map(_ ==> List((1, byte2), (2, byte3)))
      _ <- Entity.i.a1.byteMap("c").query.get.map(_ ==> List((2, byte4)))
    } yield ()
  }


  "Mandatory: Map without certain keys" - types {
    for {
      _ <- Entity.i.byteMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.byteMap.not("_").query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteMap.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap.not("c").query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byteMap.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap.not("_", "c").query.get.map(_ ==> List(a))
      // Same as
      _ <- Entity.i.a1.byteMap.not(List("_")).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteMap.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap.not(List("c")).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byteMap.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap.not(List("_", "c")).query.get.map(_ ==> List(a))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.byteMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain values" - types {
    for {
      _ <- Entity.i.byteMap.insert(a, b).transact

      // Maps containing value

      _ <- Entity.i.a1.byteMap.has(byte0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap.has(byte1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byteMap.has(byte2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteMap.has(byte3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.byteMap.has(byte4).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.byteMap.has(Seq(byte0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap.has(Seq(byte1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byteMap.has(Seq(byte2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteMap.has(Seq(byte3)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.byteMap.has(Seq(byte4)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that" value
      _ <- Entity.i.a1.byteMap.has(byte0, byte1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byteMap.has(byte1, byte2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteMap.has(byte2, byte3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteMap.has(byte3, byte4).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.byteMap.has(byte4, byte5).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.byteMap.has(byte5, byte6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.byteMap.has(Seq(byte0, byte1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byteMap.has(Seq(byte1, byte2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteMap.has(Seq(byte2, byte3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteMap.has(Seq(byte3, byte4)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.byteMap.has(Seq(byte4, byte5)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.byteMap.has(Seq(byte5, byte6)).query.get.map(_ ==> List())

      // No values match nothing
      _ <- Entity.i.a1.byteMap.has(Seq.empty[Byte]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: Map without certain values" - types {
    for {
      _ <- Entity.i.byteMap.insert(a, b).transact

      // "Doesn't have this"
      _ <- Entity.i.a1.byteMap.hasNo(byte0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteMap.hasNo(byte1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.byteMap.hasNo(byte2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap.hasNo(byte3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byteMap.hasNo(byte3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byteMap.hasNo(byte5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.byteMap.hasNo(List(byte0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteMap.hasNo(List(byte1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.byteMap.hasNo(List(byte2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap.hasNo(List(byte3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byteMap.hasNo(List(byte3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byteMap.hasNo(List(byte5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.byteMap.hasNo(byte1, byte2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap.hasNo(byte1, byte3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap.hasNo(byte1, byte3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap.hasNo(byte1, byte5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.byteMap.hasNo(List(byte1, byte2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap.hasNo(List(byte1, byte3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap.hasNo(List(byte1, byte3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap.hasNo(List(byte1, byte5)).query.get.map(_ ==> List(b))

      // No values match nothing
      _ <- Entity.i.a1.byteMap.hasNo(List.empty[Byte]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: Tacit Map (no filter)" - types {
    for {
      _ <- Entity.i.byteMap.insert(a, b).transact
      _ <- Entity.i.a1.byteMap_.query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain keys" - types {
    for {
      _ <- Entity.i.insert(0).transact // Entity without map attribute
      _ <- Entity.i.byteMap.insert(a, b).transact

      // "Map contains this OR that key"
      _ <- Entity.i.a1.byteMap_("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap_("a").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteMap_("b").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteMap_("c").query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.byteMap_("a", "c").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteMap_("_", "c").query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.byteMap_(List("_")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap_(List("a")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteMap_(List("b")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteMap_(List("c")).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.byteMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteMap_(List("_", "c")).query.get.map(_ ==> List(2))

      // Empty Seq of keys matches nothing
      _ <- Entity.i.a1.byteMap_(List.empty[String]).query.get.map(_ ==> List())

      // Match entities without map attribute
      _ <- Entity.i.a1.byteMap_().query.get.map(_ ==> List(0))
    } yield ()
  }


  "Tacit: Match map without certain keys" - types {
    for {
      _ <- Entity.i.byteMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.byteMap_.not("_").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteMap_.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap_.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap_.not("c").query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteMap_.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap_.not("_", "c").query.get.map(_ ==> List(1))
      // Same as
      _ <- Entity.i.a1.byteMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteMap_.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap_.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap_.not(List("c")).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteMap_.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.byteMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain values" - types {
    for {
      _ <- Entity.i.byteMap.insert(a, b).transact

      // "Map contains this OR that value"
      _ <- Entity.i.a1.byteMap_.has(byte0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap_.has(byte1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteMap_.has(byte2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteMap_.has(byte3).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.byteMap_.has(byte4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.byteMap_.has(byte0, byte1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteMap_.has(byte1, byte2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteMap_.has(byte2, byte3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteMap_.has(byte3, byte4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.byteMap_.has(byte4, byte5).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.byteMap_.has(byte5, byte6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.byteMap_.has(List(byte0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap_.has(List(byte1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteMap_.has(List(byte2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteMap_.has(List(byte3)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.byteMap_.has(List(byte4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.byteMap_.has(List(byte0, byte1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteMap_.has(List(byte1, byte2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteMap_.has(List(byte2, byte3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteMap_.has(List(byte3, byte4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.byteMap_.has(List(byte4, byte5)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.byteMap_.has(List(byte5, byte6)).query.get.map(_ ==> List())

      // Empty Seq of values matches nothing
      _ <- Entity.i.a1.byteMap_.has(List.empty[Byte]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: Match map without certain values" - types {
    for {
      _ <- Entity.i.byteMap.insert(a, b).transact

      // "Map contains neither this OR that value"
      _ <- Entity.i.a1.byteMap_.hasNo(byte0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteMap_.hasNo(byte1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.byteMap_.hasNo(byte2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap_.hasNo(byte3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteMap_.hasNo(byte4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteMap_.hasNo(byte0, byte1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.byteMap_.hasNo(byte1, byte2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap_.hasNo(byte2, byte3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap_.hasNo(byte3, byte4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteMap_.hasNo(byte4, byte5).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteMap_.hasNo(byte5, byte6).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.byteMap_.hasNo(List(byte0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteMap_.hasNo(List(byte1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.byteMap_.hasNo(List(byte2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap_.hasNo(List(byte3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteMap_.hasNo(List(byte4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteMap_.hasNo(List(byte0, byte1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.byteMap_.hasNo(List(byte1, byte2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap_.hasNo(List(byte2, byte3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteMap_.hasNo(List(byte3, byte4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteMap_.hasNo(List(byte4, byte5)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteMap_.hasNo(List(byte5, byte6)).query.get.map(_ ==> List(1, 2))

      // Negating empty Seq of values matches all
      _ <- Entity.i.a1.byteMap_.hasNo(List.empty[Byte]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Optional map (no filter)" - types {
    val a = (1, Some(Map("a" -> byte1, "b" -> byte2)))
    val b = (2, Some(Map("a" -> byte2, "b" -> byte3, "c" -> byte4)))
    val c = (3, None)
    for {
      _ <- Entity.i.byteMap_?.insert(a, b, c).transact
      _ <- Entity.i.a1.byteMap_?.query.get.map(_ ==> List(a, b, c))
    } yield ()
  }


  "Optional map values by key" - types {

    for {
      _ <- Entity.i.byteMap.insert(a, b).transact
      _ <- Entity.i(3).save.transact // entity without byteMap

      // Like calling `get` on a Scala Map.
      _ <- Entity.i.a1.byteMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
      _ <- Entity.i.a1.byteMap_?("a").query.get.map(_ ==> List((1, Some(byte1)), (2, Some(byte2)), (3, None)))
      _ <- Entity.i.a1.byteMap_?("b").query.get.map(_ ==> List((1, Some(byte2)), (2, Some(byte3)), (3, None)))
      _ <- Entity.i.a1.byteMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(byte4)), (3, None)))
    } yield ()
  }
}