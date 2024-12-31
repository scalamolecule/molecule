package molecule.coreTests.spi.filter.map.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterMap_Boolean(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  val a = (1, Map("a" -> boolean1, "b" -> boolean2))
  val b = (2, Map("a" -> boolean2, "b" -> boolean3, "c" -> boolean4))

  import api._
  import suite._


  "Mandatory: Mandatory Map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.booleanMap.insert(a, b).transact
      _ <- Entity.i.a1.booleanMap.query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.booleanMap.insert(a, b).transact

      // Get Map value by key

      _ <- Entity.i.a1.booleanMap("_").query.get.map(_ ==> List()) // When no map is saved
      _ <- Entity.i.a1.booleanMap("a").query.get.map(_ ==> List((1, boolean1), (2, boolean2)))
      _ <- Entity.i.a1.booleanMap("b").query.get.map(_ ==> List((1, boolean2), (2, boolean3)))
      _ <- Entity.i.a1.booleanMap("c").query.get.map(_ ==> List((2, boolean4)))
    } yield ()
  }


  "Mandatory: Match map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.booleanMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.booleanMap.not("_").query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.booleanMap.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.booleanMap.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.booleanMap.not("c").query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.booleanMap.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.booleanMap.not("_", "c").query.get.map(_ ==> List(a))
      // Same as
      _ <- Entity.i.a1.booleanMap.not(List("_")).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.booleanMap.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.booleanMap.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.booleanMap.not(List("c")).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.booleanMap.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.booleanMap.not(List("_", "c")).query.get.map(_ ==> List(a))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.booleanMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain values" - types { implicit conn =>
    val a = (1, Map("a" -> boolean1))
    val b = (2, Map("a" -> boolean1, "b" -> boolean2))
    val c = (3, Map("c" -> boolean2))
    for {
      _ <- Entity.i.booleanMap.insert(a, b, c).transact

      // Maps containing value

      _ <- Entity.i.a1.booleanMap.has(boolean1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.booleanMap.has(boolean2).query.get.map(_ ==> List(b, c))
      // Same as
      _ <- Entity.i.a1.booleanMap.has(Seq(boolean1)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.booleanMap.has(Seq(boolean2)).query.get.map(_ ==> List(b, c))

      // OR semantics when multiple values

      // "Has this OR that" value
      _ <- Entity.i.a1.booleanMap.has(boolean1, boolean2).query.get.map(_ ==> List(a, b, c))
      // Same as
      _ <- Entity.i.a1.booleanMap.has(Seq(boolean1, boolean2)).query.get.map(_ ==> List(a, b, c))

      // No values match nothing
      _ <- Entity.i.a1.booleanMap.has(Seq.empty[Boolean]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: Map without certain values" - types { implicit conn =>
    val a = (1, Map("a" -> boolean1))
    val b = (2, Map("a" -> boolean1, "b" -> boolean2))
    val c = (3, Map("c" -> boolean2))
    for {
      _ <- Entity.i.booleanMap.insert(a, b, c).transact

      // "Doesn't have this"
      _ <- Entity.i.a1.booleanMap.hasNo(boolean1).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.booleanMap.hasNo(boolean2).query.get.map(_ ==> List(a))
      // Same as
      _ <- Entity.i.a1.booleanMap.hasNo(List(boolean1)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.booleanMap.hasNo(List(boolean2)).query.get.map(_ ==> List(a))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.booleanMap.hasNo(boolean1, boolean2).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.booleanMap.hasNo(List(boolean1, boolean2)).query.get.map(_ ==> List())

      // Negating no values matches all
      _ <- Entity.i.a1.booleanMap.hasNo(List.empty[Boolean]).query.get.map(_ ==> List(a, b, c))
    } yield ()
  }


  "Tacit: Tacit Map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.booleanMap.insert(a, b).transact
      _ <- Entity.i.a1.booleanMap_.query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.insert(0).transact // Entity without map attribute
      _ <- Entity.i.booleanMap.insert(a, b).transact

      // "Map contains this OR that key"
      _ <- Entity.i.a1.booleanMap_("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.booleanMap_("a").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.booleanMap_("b").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.booleanMap_("c").query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.booleanMap_("a", "c").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.booleanMap_("_", "c").query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.booleanMap_(List("_")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.booleanMap_(List("a")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.booleanMap_(List("b")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.booleanMap_(List("c")).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.booleanMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.booleanMap_(List("_", "c")).query.get.map(_ ==> List(2))

      // Empty Seq of keys matches nothing
      _ <- Entity.i.a1.booleanMap_(List.empty[String]).query.get.map(_ ==> List())

      // Match entities without map attribute
      _ <- Entity.i.a1.booleanMap_().query.get.map(_ ==> List(0))
    } yield ()
  }


  "Tacit: Match map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.booleanMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.booleanMap_.not("_").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.booleanMap_.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.booleanMap_.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.booleanMap_.not("c").query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.booleanMap_.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.booleanMap_.not("_", "c").query.get.map(_ ==> List(1))
      // Same as
      _ <- Entity.i.a1.booleanMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.booleanMap_.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.booleanMap_.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.booleanMap_.not(List("c")).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.booleanMap_.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.booleanMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.booleanMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: has value(s)" - types { implicit conn =>
    val aFalse = "a" -> boolean1
    val bFalse = "b" -> boolean1
    val cTrue  = "c" -> boolean2
    for {
      _ <- Entity.i.booleanMap.insert(List(
        (1, Map(aFalse)),
        (2, Map(bFalse, cTrue)),
      )).transact

      // "Map contains this OR that value"
      _ <- Entity.i.a1.booleanMap_.has(boolean1).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.booleanMap_.has(boolean2).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.booleanMap_.has(boolean1, boolean2).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.booleanMap_.has(List(boolean1)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.booleanMap_.has(List(boolean2)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.booleanMap_.has(List(boolean1, boolean2)).query.get.map(_ ==> List(1, 2))

      // Empty Seq of values matches nothing
      _ <- Entity.i.a1.booleanMap_.has(List.empty[Boolean]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: Match map with certain values" - types { implicit conn =>
    val aFalse = "a" -> boolean1
    val bFalse = "b" -> boolean1
    val cTrue  = "c" -> boolean2
    for {
      _ <- Entity.i.booleanMap.insert(List(
        (1, Map(aFalse)),
        (2, Map(bFalse, cTrue)),
      )).transact

      // "Map contains neither this OR that value"
      _ <- Entity.i.a1.booleanMap_.hasNo(boolean1).query.get.map(_ ==> List())
      _ <- Entity.i.a1.booleanMap_.hasNo(boolean2).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.booleanMap_.hasNo(boolean1, boolean2).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.booleanMap_.hasNo(List(boolean1)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.booleanMap_.hasNo(List(boolean2)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.booleanMap_.hasNo(List(boolean1, boolean2)).query.get.map(_ ==> List())

      // Negating empty Seq of values matches all
      _ <- Entity.i.a1.booleanMap_.hasNo(List.empty[Boolean]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Optiopnal: Get optional map" - types { implicit conn =>
    val a = (1, Some(Map("a" -> boolean1, "b" -> boolean2)))
    val b = (2, Some(Map("a" -> boolean2, "b" -> boolean3, "c" -> boolean4)))
    val c = (3, None)
    for {
      _ <- Entity.i.booleanMap_?.insert(a, b, c).transact
      _ <- Entity.i.a1.booleanMap_?.query.get.map(_ ==> List(a, b, c))
    } yield ()
  }


  "Optiopnal: Optional map values by key" - types { implicit conn =>
    for {
      _ <- Entity.i.booleanMap.insert(a, b).transact
      _ <- Entity.i(3).save.transact // entity without intMap

      // Like calling `get` on a Scala Map.
      _ <- Entity.i.a1.booleanMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
      _ <- Entity.i.a1.booleanMap_?("a").query.get.map(_ ==> List((1, Some(boolean1)), (2, Some(boolean2)), (3, None)))
      _ <- Entity.i.a1.booleanMap_?("b").query.get.map(_ ==> List((1, Some(boolean2)), (2, Some(boolean3)), (3, None)))
      _ <- Entity.i.a1.booleanMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(boolean4)), (3, None)))
    } yield ()
  }
}