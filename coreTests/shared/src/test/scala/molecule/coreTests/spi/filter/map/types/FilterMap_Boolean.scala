// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_Boolean extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> boolean1, "b" -> boolean2))
        val b = (2, Map("a" -> boolean2, "b" -> boolean3, "c" -> boolean4))
        for {
          _ <- Ns.i.booleanMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.booleanMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val boolean: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> boolean1, "b" -> boolean2))
        val b = (2, Map("a" -> boolean2, "b" -> boolean3, "c" -> boolean4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.booleanMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.booleanMap("-").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.booleanMap("a").query.get.map(_ ==> List((1, boolean1), (2, boolean2)))
          _ <- Ns.i.a1.booleanMap("b").query.get.map(_ ==> List((1, boolean2), (2, boolean3)))
          _ <- Ns.i.a1.booleanMap("c").query.get.map(_ ==> List((2, boolean4)))

          // Applying nothing to mandatory map attribute matches nothing,
          // not even entities without map attributes
          _ <- Ns.i.a1.booleanMap().query.get.map(_ ==> Nil)
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
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[Boolean] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> boolean1, "b" -> boolean2)))
        val b = (2, Some(Map("a" -> boolean2, "b" -> boolean3, "c" -> boolean4)))
        val c = (3, None)
        for {
          _ <- Ns.i.booleanMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.booleanMap_?("-").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.booleanMap_?("a").query.get.map(_ ==> List((1, Some(boolean1)), (2, Some(boolean2)), (3, None)))
          _ <- Ns.i.a1.booleanMap_?("b").query.get.map(_ ==> List((1, Some(boolean2)), (2, Some(boolean3)), (3, None)))
          _ <- Ns.i.a1.booleanMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(boolean4)), (3, None)))
        } yield ()
      }
    }


    "Tacit" - {
      // Use tacit map attribute to filter by keys/values of Maps
      // Use mandatory/optional map attributes to retrieve Maps/keys/values
      // Combine multiple map attributes if both retrieval and filtering is required

      // OBS: Note have tacit map attribute methods `apply` and `not` have OR semantics! 
      // (contrary to Set and Seq that have AND semantics)
      // This is to have the same semantics for searching by key (apply/not) and value (has/hasNo)
      // Also to avoid adding further restricted molecule keywords. 

      val (a1, b2) = ("a" -> boolean1, "b" -> boolean2)
      val (b3, c4) = ("b" -> boolean3, "c" -> boolean4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.booleanMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // booleanMap not asserted for i = 0
          _ <- Ns.i.a1.booleanMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.booleanMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.booleanMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.booleanMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.booleanMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.booleanMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.booleanMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.booleanMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.booleanMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.booleanMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.booleanMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.booleanMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.booleanMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.booleanMap_(List("a", "-")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.booleanMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.booleanMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.booleanMap.booleanMap_("-").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.booleanMap.booleanMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.booleanMap.booleanMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.booleanMap.booleanMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.booleanMap.booleanMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.booleanMap.booleanMap_("a", "-").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.booleanMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.booleanMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.booleanMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.booleanMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.booleanMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.booleanMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.booleanMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.booleanMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.booleanMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.booleanMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.booleanMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.booleanMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.booleanMap_.not(List("a", "-")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.booleanMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.booleanMap.booleanMap_.not("-").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.booleanMap.booleanMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.booleanMap.booleanMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.booleanMap.booleanMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.booleanMap.booleanMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.booleanMap.booleanMap_.not("a", "-").query.get.map(_ ==> List((2, Map(b3, c4))))
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
          _ <- Ns.i.a1.booleanMap_.has(List.empty[Boolean]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.booleanMap.booleanMap_.has(boolean1).query.get.map(_ ==> List((1, Map(aFalse)), (2, Map(bFalse, cTrue))))
          _ <- Ns.i.a1.booleanMap.booleanMap_.has(boolean2).query.get.map(_ ==> List((2, Map(bFalse, cTrue))))
          _ <- Ns.i.a1.booleanMap.booleanMap_.has(boolean1, boolean2).query.get.map(_ ==> List((1, Map(aFalse)), (2, Map(bFalse, cTrue))))
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

          // Combine with retrieval
          _ <- Ns.i.a1.booleanMap.booleanMap_.hasNo(boolean1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanMap.booleanMap_.hasNo(boolean2).query.get.map(_ ==> List((1, Map(aFalse))))
          _ <- Ns.i.a1.booleanMap.booleanMap_.hasNo(boolean1, boolean2).query.get.map(_ ==> List())
        } yield ()
      }
    }
  }
}