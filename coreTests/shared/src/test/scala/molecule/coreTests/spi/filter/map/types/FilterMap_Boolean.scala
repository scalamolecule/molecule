package molecule.coreTests.spi.filter.map.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_Boolean extends CoreTestSuite with Api_async { spi: Spi_async =>

  val a = (1, Map("a" -> boolean1, "b" -> boolean2))
  val b = (2, Map("a" -> boolean2, "b" -> boolean3, "c" -> boolean4))

  override lazy val tests = Tests {

    "Mandatory" - {

      "Mandatory Map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.booleanMap.insert(a, b).transact
          _ <- Ns.i.a1.booleanMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Value by key" - types { implicit conn =>
        for {
          _ <- Ns.i.booleanMap.insert(a, b).transact

          // Get Map value by key

          _ <- Ns.i.a1.booleanMap("_").query.get.map(_ ==> List()) // When no map is saved
          _ <- Ns.i.a1.booleanMap("a").query.get.map(_ ==> List((1, boolean1), (2, boolean2)))
          _ <- Ns.i.a1.booleanMap("b").query.get.map(_ ==> List((1, boolean2), (2, boolean3)))
          _ <- Ns.i.a1.booleanMap("c").query.get.map(_ ==> List((2, boolean4)))
        } yield ()
      }


      "Map having values" - types { implicit conn =>
        val a = (1, Map("a" -> boolean1))
        val b = (2, Map("a" -> boolean1, "b" -> boolean2))
        val c = (3, Map("c" -> boolean2))
        for {
          _ <- Ns.i.booleanMap.insert(a, b, c).transact

          // Maps containing value

          _ <- Ns.i.a1.booleanMap.has(boolean1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.booleanMap.has(boolean2).query.get.map(_ ==> List(b, c))
          // Same as
          _ <- Ns.i.a1.booleanMap.has(Seq(boolean1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.booleanMap.has(Seq(boolean2)).query.get.map(_ ==> List(b, c))

          // OR semantics when multiple values

          // "Has this OR that" value
          _ <- Ns.i.a1.booleanMap.has(boolean1, boolean2).query.get.map(_ ==> List(a, b, c))
          // Same as
          _ <- Ns.i.a1.booleanMap.has(Seq(boolean1, boolean2)).query.get.map(_ ==> List(a, b, c))

          // No values match nothing
          _ <- Ns.i.a1.booleanMap.has(Seq.empty[Boolean]).query.get.map(_ ==> List())
        } yield ()
      }


      "Map not having values" - types { implicit conn =>
        val a = (1, Map("a" -> boolean1))
        val b = (2, Map("a" -> boolean1, "b" -> boolean2))
        val c = (3, Map("c" -> boolean2))
        for {
          _ <- Ns.i.booleanMap.insert(a, b, c).transact

          // "Doesn't have this"
          _ <- Ns.i.a1.booleanMap.hasNo(boolean1).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.booleanMap.hasNo(boolean2).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.booleanMap.hasNo(List(boolean1)).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.booleanMap.hasNo(List(boolean2)).query.get.map(_ ==> List(a))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.booleanMap.hasNo(boolean1, boolean2).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.booleanMap.hasNo(List(boolean1, boolean2)).query.get.map(_ ==> List())

          // Negating no values matches all
          _ <- Ns.i.a1.booleanMap.hasNo(List.empty[Boolean]).query.get.map(_ ==> List(a, b, c))
        } yield ()
      }
    }


    "Tacit" - {

      "Tacit Map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.booleanMap.insert(a, b).transact
          _ <- Ns.i.a1.booleanMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "Map contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.booleanMap.insert(a, b).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.booleanMap_("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanMap_("a").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.booleanMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.booleanMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.booleanMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.booleanMap_("_", "c").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.booleanMap_(List("_")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanMap_(List("a")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.booleanMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.booleanMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.booleanMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.booleanMap_(List("_", "c")).query.get.map(_ ==> List(2))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.booleanMap_(List.empty[String]).query.get.map(_ ==> List())

          // Match entities without map attribute
          _ <- Ns.i.a1.booleanMap_().query.get.map(_ ==> List(0))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.booleanMap.insert(a, b).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.booleanMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.booleanMap_.not("a").query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanMap_.not("b").query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.booleanMap_.not("a", "c").query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanMap_.not("_", "c").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.booleanMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.booleanMap_.not(List("a")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanMap_.not(List("b")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.booleanMap_.not(List("a", "c")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.booleanMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        val aFalse = "a" -> boolean1
        val bFalse = "b" -> boolean1
        val cTrue  = "c" -> boolean2
        for {
          _ <- Ns.i.booleanMap.insert(List(
            (1, Map(aFalse)),
            (2, Map(bFalse, cTrue)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.booleanMap_.has(boolean1).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.booleanMap_.has(boolean2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.booleanMap_.has(boolean1, boolean2).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.booleanMap_.has(List(boolean1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.booleanMap_.has(List(boolean2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.booleanMap_.has(List(boolean1, boolean2)).query.get.map(_ ==> List(1, 2))

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.booleanMap_.has(List.empty[Boolean]).query.get.map(_ ==> List())
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        val aFalse = "a" -> boolean1
        val bFalse = "b" -> boolean1
        val cTrue  = "c" -> boolean2
        for {
          _ <- Ns.i.booleanMap.insert(List(
            (1, Map(aFalse)),
            (2, Map(bFalse, cTrue)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.booleanMap_.hasNo(boolean1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanMap_.hasNo(boolean2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.booleanMap_.hasNo(boolean1, boolean2).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.booleanMap_.hasNo(List(boolean1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanMap_.hasNo(List(boolean2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.booleanMap_.hasNo(List(boolean1, boolean2)).query.get.map(_ ==> List())

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.booleanMap_.hasNo(List.empty[Boolean]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> boolean1, "b" -> boolean2)))
        val b = (2, Some(Map("a" -> boolean2, "b" -> boolean3, "c" -> boolean4)))
        val c = (3, None)
        for {
          _ <- Ns.i.booleanMap_?.insert(a, b, c).transact
          _ <- Ns.i.a1.booleanMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        for {
          _ <- Ns.i.booleanMap.insert(a, b).transact
          _ <- Ns.i(3).save.transact // entity without intMap

          // Like calling `get` on a Scala Map.
          _ <- Ns.i.a1.booleanMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.booleanMap_?("a").query.get.map(_ ==> List((1, Some(boolean1)), (2, Some(boolean2)), (3, None)))
          _ <- Ns.i.a1.booleanMap_?("b").query.get.map(_ ==> List((1, Some(boolean2)), (2, Some(boolean3)), (3, None)))
          _ <- Ns.i.a1.booleanMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(boolean4)), (3, None)))
        } yield ()
      }
    }
  }
}