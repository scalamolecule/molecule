// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterMap_Long_(
  suite: MUnitSuite,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  val a = (1, Map("a" -> long1, "b" -> long2))
  val b = (2, Map("a" -> long2, "b" -> long3, "c" -> long4))

  import api._
  import suite._


  "Mandatory: Mandatory map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.longMap.insert(a, b).transact
      _ <- Entity.i.a1.longMap.query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.longMap.insert(a, b).transact

      // Get Map value by key

      // Like calling `apply` on a Scala Map.
      _ <- Entity.i.a1.longMap("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap("a").query.get.map(_ ==> List((1, long1), (2, long2)))
      _ <- Entity.i.a1.longMap("b").query.get.map(_ ==> List((1, long2), (2, long3)))
      _ <- Entity.i.a1.longMap("c").query.get.map(_ ==> List((2, long4)))
    } yield ()
  }


  "Mandatory: Map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.longMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.longMap.not("_").query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longMap.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap.not("c").query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longMap.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap.not("_", "c").query.get.map(_ ==> List(a))
      // Same as
      _ <- Entity.i.a1.longMap.not(List("_")).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longMap.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap.not(List("c")).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longMap.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap.not(List("_", "c")).query.get.map(_ ==> List(a))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.longMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.longMap.insert(a, b).transact

      // Maps containing value

      _ <- Entity.i.a1.longMap.has(long0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap.has(long1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longMap.has(long2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longMap.has(long3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.longMap.has(long4).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.longMap.has(Seq(long0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap.has(Seq(long1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longMap.has(Seq(long2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longMap.has(Seq(long3)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.longMap.has(Seq(long4)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that" value
      _ <- Entity.i.a1.longMap.has(long0, long1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longMap.has(long1, long2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longMap.has(long2, long3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longMap.has(long3, long4).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.longMap.has(long4, long5).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.longMap.has(long5, long6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.longMap.has(Seq(long0, long1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longMap.has(Seq(long1, long2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longMap.has(Seq(long2, long3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longMap.has(Seq(long3, long4)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.longMap.has(Seq(long4, long5)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.longMap.has(Seq(long5, long6)).query.get.map(_ ==> List())

      // No values match nothing
      _ <- Entity.i.a1.longMap.has(Seq.empty[Long]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: Map without certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.longMap.insert(a, b).transact

      // "Doesn't have this"
      _ <- Entity.i.a1.longMap.hasNo(long0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longMap.hasNo(long1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.longMap.hasNo(long2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap.hasNo(long3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longMap.hasNo(long3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longMap.hasNo(long5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.longMap.hasNo(List(long0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longMap.hasNo(List(long1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.longMap.hasNo(List(long2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap.hasNo(List(long3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longMap.hasNo(List(long3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longMap.hasNo(List(long5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.longMap.hasNo(long1, long2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap.hasNo(long1, long3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap.hasNo(long1, long3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap.hasNo(long1, long5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.longMap.hasNo(List(long1, long2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap.hasNo(List(long1, long3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap.hasNo(List(long1, long3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap.hasNo(List(long1, long5)).query.get.map(_ ==> List(b))

      // No values match nothing
      _ <- Entity.i.a1.longMap.hasNo(List.empty[Long]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: Tacit Map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.longMap.insert(a, b).transact
      _ <- Entity.i.a1.longMap_.query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.insert(0).transact // Entity without map attribute
      _ <- Entity.i.longMap.insert(a, b).transact

      // "Map contains this OR that key"
      _ <- Entity.i.a1.longMap_("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap_("a").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longMap_("b").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longMap_("c").query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.longMap_("a", "c").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longMap_("_", "c").query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.longMap_(List("_")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap_(List("a")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longMap_(List("b")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longMap_(List("c")).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.longMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longMap_(List("_", "c")).query.get.map(_ ==> List(2))

      // Empty Seq of keys matches nothing
      _ <- Entity.i.a1.longMap_(List.empty[String]).query.get.map(_ ==> List())

      // Match entities without map attribute
      _ <- Entity.i.a1.longMap_().query.get.map(_ ==> List(0))
    } yield ()
  }


  "Tacit: Match map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.longMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.longMap_.not("_").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longMap_.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap_.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap_.not("c").query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longMap_.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap_.not("_", "c").query.get.map(_ ==> List(1))
      // Same as
      _ <- Entity.i.a1.longMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longMap_.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap_.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap_.not(List("c")).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longMap_.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.longMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.longMap.insert(a, b).transact

      // "Map contains this OR that value"
      _ <- Entity.i.a1.longMap_.has(long0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap_.has(long1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longMap_.has(long2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longMap_.has(long3).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.longMap_.has(long4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.longMap_.has(long0, long1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longMap_.has(long1, long2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longMap_.has(long2, long3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longMap_.has(long3, long4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.longMap_.has(long4, long5).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.longMap_.has(long5, long6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.longMap_.has(List(long0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap_.has(List(long1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longMap_.has(List(long2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longMap_.has(List(long3)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.longMap_.has(List(long4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.longMap_.has(List(long0, long1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longMap_.has(List(long1, long2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longMap_.has(List(long2, long3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longMap_.has(List(long3, long4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.longMap_.has(List(long4, long5)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.longMap_.has(List(long5, long6)).query.get.map(_ ==> List())

      // Empty Seq of values matches nothing
      _ <- Entity.i.a1.longMap_.has(List.empty[Long]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: Match map without certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.longMap.insert(a, b).transact

      // "Map contains neither this OR that value"
      _ <- Entity.i.a1.longMap_.hasNo(long0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longMap_.hasNo(long1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.longMap_.hasNo(long2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap_.hasNo(long3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longMap_.hasNo(long4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longMap_.hasNo(long0, long1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.longMap_.hasNo(long1, long2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap_.hasNo(long2, long3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap_.hasNo(long3, long4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longMap_.hasNo(long4, long5).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longMap_.hasNo(long5, long6).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.longMap_.hasNo(List(long0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longMap_.hasNo(List(long1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.longMap_.hasNo(List(long2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap_.hasNo(List(long3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longMap_.hasNo(List(long4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longMap_.hasNo(List(long0, long1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.longMap_.hasNo(List(long1, long2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap_.hasNo(List(long2, long3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longMap_.hasNo(List(long3, long4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longMap_.hasNo(List(long4, long5)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longMap_.hasNo(List(long5, long6)).query.get.map(_ ==> List(1, 2))

      // Negating empty Seq of values matches all
      _ <- Entity.i.a1.longMap_.hasNo(List.empty[Long]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Optional map (no filter)" - types { implicit conn =>
    val a = (1, Some(Map("a" -> long1, "b" -> long2)))
    val b = (2, Some(Map("a" -> long2, "b" -> long3, "c" -> long4)))
    val c = (3, None)
    for {
      _ <- Entity.i.longMap_?.insert(a, b, c).transact
      _ <- Entity.i.a1.longMap_?.query.get.map(_ ==> List(a, b, c))
    } yield ()
  }


  "Optional map values by key" - types { implicit conn =>

    for {
      _ <- Entity.i.longMap.insert(a, b).transact
      _ <- Entity.i(3).save.transact // entity without longMap

      // Like calling `get` on a Scala Map.
      _ <- Entity.i.a1.longMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
      _ <- Entity.i.a1.longMap_?("a").query.get.map(_ ==> List((1, Some(long1)), (2, Some(long2)), (3, None)))
      _ <- Entity.i.a1.longMap_?("b").query.get.map(_ ==> List((1, Some(long2)), (2, Some(long3)), (3, None)))
      _ <- Entity.i.a1.longMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(long4)), (3, None)))
    } yield ()
  }
}