package molecule.coreTests.spi.filter.map.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterMap_Int(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Map("a" -> int1, "b" -> int2))
  val b = (2, Map("a" -> int2, "b" -> int3, "c" -> int4))

  import api._
  import suite._


  "Mandatory: Mandatory map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.intMap.insert(a, b).transact
      _ <- Entity.i.a1.intMap.query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.intMap.insert(a, b).transact

      // Get Map value by key

      // Like calling `apply` on a Scala Map.
      _ <- Entity.i.a1.intMap("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap("a").query.get.map(_ ==> List((1, int1), (2, int2)))
      _ <- Entity.i.a1.intMap("b").query.get.map(_ ==> List((1, int2), (2, int3)))
      _ <- Entity.i.a1.intMap("c").query.get.map(_ ==> List((2, int4)))
    } yield ()
  }


  "Mandatory: Map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.intMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.intMap.not("_").query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intMap.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap.not("c").query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intMap.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap.not("_", "c").query.get.map(_ ==> List(a))
      // Same as
      _ <- Entity.i.a1.intMap.not(List("_")).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intMap.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap.not(List("c")).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intMap.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap.not(List("_", "c")).query.get.map(_ ==> List(a))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.intMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.intMap.insert(a, b).transact

      // Maps containing value

      _ <- Entity.i.a1.intMap.has(int0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap.has(int1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intMap.has(int2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intMap.has(int3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.intMap.has(int4).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.intMap.has(Seq(int0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap.has(Seq(int1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intMap.has(Seq(int2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intMap.has(Seq(int3)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.intMap.has(Seq(int4)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that" value
      _ <- Entity.i.a1.intMap.has(int0, int1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intMap.has(int1, int2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intMap.has(int2, int3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intMap.has(int3, int4).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.intMap.has(int4, int5).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.intMap.has(int5, int6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.intMap.has(Seq(int0, int1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intMap.has(Seq(int1, int2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intMap.has(Seq(int2, int3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intMap.has(Seq(int3, int4)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.intMap.has(Seq(int4, int5)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.intMap.has(Seq(int5, int6)).query.get.map(_ ==> List())

      // No values match nothing
      _ <- Entity.i.a1.intMap.has(Seq.empty[Int]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: Map without certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.intMap.insert(a, b).transact

      // "Doesn't have this"
      _ <- Entity.i.a1.intMap.hasNo(int0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intMap.hasNo(int1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.intMap.hasNo(int2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap.hasNo(int3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intMap.hasNo(int3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intMap.hasNo(int5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.intMap.hasNo(List(int0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intMap.hasNo(List(int1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.intMap.hasNo(List(int2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap.hasNo(List(int3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intMap.hasNo(List(int3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intMap.hasNo(List(int5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.intMap.hasNo(int1, int2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap.hasNo(int1, int3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap.hasNo(int1, int3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap.hasNo(int1, int5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.intMap.hasNo(List(int1, int2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap.hasNo(List(int1, int3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap.hasNo(List(int1, int3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap.hasNo(List(int1, int5)).query.get.map(_ ==> List(b))

      // No values match nothing
      _ <- Entity.i.a1.intMap.hasNo(List.empty[Int]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: Tacit Map (no filter)" - types { implicit conn =>
    for {
      _ <- Entity.i.intMap.insert(a, b).transact
      _ <- Entity.i.a1.intMap_.query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.insert(0).transact // Entity without map attribute
      _ <- Entity.i.intMap.insert(a, b).transact

      // "Map contains this OR that key"
      _ <- Entity.i.a1.intMap_("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap_("a").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intMap_("b").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intMap_("c").query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.intMap_("a", "c").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intMap_("_", "c").query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.intMap_(List("_")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap_(List("a")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intMap_(List("b")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intMap_(List("c")).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.intMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intMap_(List("_", "c")).query.get.map(_ ==> List(2))

      // Empty Seq of keys matches nothing
      _ <- Entity.i.a1.intMap_(List.empty[String]).query.get.map(_ ==> List())

      // Match entities without map attribute
      _ <- Entity.i.a1.intMap_().query.get.map(_ ==> List(0))
    } yield ()
  }


  "Tacit: Match map without certain keys" - types { implicit conn =>
    for {
      _ <- Entity.i.intMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.intMap_.not("_").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intMap_.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap_.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap_.not("c").query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intMap_.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap_.not("_", "c").query.get.map(_ ==> List(1))
      // Same as
      _ <- Entity.i.a1.intMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intMap_.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap_.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap_.not(List("c")).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intMap_.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.intMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.intMap.insert(a, b).transact

      // "Map contains this OR that value"
      _ <- Entity.i.a1.intMap_.has(int0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap_.has(int1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intMap_.has(int2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intMap_.has(int3).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.intMap_.has(int4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.intMap_.has(int0, int1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intMap_.has(int1, int2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intMap_.has(int2, int3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intMap_.has(int3, int4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.intMap_.has(int4, int5).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.intMap_.has(int5, int6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.intMap_.has(List(int0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap_.has(List(int1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intMap_.has(List(int2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intMap_.has(List(int3)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.intMap_.has(List(int4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.intMap_.has(List(int0, int1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intMap_.has(List(int1, int2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intMap_.has(List(int2, int3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intMap_.has(List(int3, int4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.intMap_.has(List(int4, int5)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.intMap_.has(List(int5, int6)).query.get.map(_ ==> List())

      // Empty Seq of values matches nothing
      _ <- Entity.i.a1.intMap_.has(List.empty[Int]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: Match map without certain values" - types { implicit conn =>
    for {
      _ <- Entity.i.intMap.insert(a, b).transact

      // "Map contains neither this OR that value"
      _ <- Entity.i.a1.intMap_.hasNo(int0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intMap_.hasNo(int1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.intMap_.hasNo(int2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap_.hasNo(int3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intMap_.hasNo(int4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intMap_.hasNo(int0, int1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.intMap_.hasNo(int1, int2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap_.hasNo(int2, int3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap_.hasNo(int3, int4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intMap_.hasNo(int4, int5).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intMap_.hasNo(int5, int6).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.intMap_.hasNo(List(int0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intMap_.hasNo(List(int1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.intMap_.hasNo(List(int2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap_.hasNo(List(int3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intMap_.hasNo(List(int4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intMap_.hasNo(List(int0, int1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.intMap_.hasNo(List(int1, int2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap_.hasNo(List(int2, int3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intMap_.hasNo(List(int3, int4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intMap_.hasNo(List(int4, int5)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intMap_.hasNo(List(int5, int6)).query.get.map(_ ==> List(1, 2))

      // Negating empty Seq of values matches all
      _ <- Entity.i.a1.intMap_.hasNo(List.empty[Int]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Optional map (no filter)" - types { implicit conn =>
    val a = (1, Some(Map("a" -> int1, "b" -> int2)))
    val b = (2, Some(Map("a" -> int2, "b" -> int3, "c" -> int4)))
    val c = (3, None)
    for {
      _ <- Entity.i.intMap_?.insert(a, b, c).transact
      _ <- Entity.i.a1.intMap_?.query.get.map(_ ==> List(a, b, c))
    } yield ()
  }


  "Optional map values by key" - types { implicit conn =>

    for {
      _ <- Entity.i.intMap.insert(a, b).transact
      _ <- Entity.i(3).save.transact // entity without intMap

      // Like calling `get` on a Scala Map.
      _ <- Entity.i.a1.intMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
      _ <- Entity.i.a1.intMap_?("a").query.get.map(_ ==> List((1, Some(int1)), (2, Some(int2)), (3, None)))
      _ <- Entity.i.a1.intMap_?("b").query.get.map(_ ==> List((1, Some(int2)), (2, Some(int3)), (3, None)))
      _ <- Entity.i.a1.intMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(int4)), (3, None)))
    } yield ()
  }
}