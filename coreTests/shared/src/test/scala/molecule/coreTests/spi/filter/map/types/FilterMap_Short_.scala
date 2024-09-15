// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_Short_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> short1, "b" -> short2))
        val b = (2, Map("a" -> short2, "b" -> short3, "c" -> short4))
        for {
          _ <- Ns.i.shortMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.shortMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val short: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> short1, "b" -> short2))
        val b = (2, Map("a" -> short2, "b" -> short3, "c" -> short4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.shortMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.shortMap("_").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.shortMap("a").query.get.map(_ ==> List((1, short1), (2, short2)))
          _ <- Ns.i.a1.shortMap("b").query.get.map(_ ==> List((1, short2), (2, short3)))
          _ <- Ns.i.a1.shortMap("c").query.get.map(_ ==> List((2, short4)))
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> short1, "b" -> short2)))
        val b = (2, Some(Map("a" -> short2, "b" -> short3, "c" -> short4)))
        val c = (3, None)
        for {
          _ <- Ns.i.shortMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.shortMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[Short] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> short1, "b" -> short2)))
        val b = (2, Some(Map("a" -> short2, "b" -> short3, "c" -> short4)))
        val c = (3, None)
        for {
          _ <- Ns.i.shortMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.shortMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.shortMap_?("a").query.get.map(_ ==> List((1, Some(short1)), (2, Some(short2)), (3, None)))
          _ <- Ns.i.a1.shortMap_?("b").query.get.map(_ ==> List((1, Some(short2)), (2, Some(short3)), (3, None)))
          _ <- Ns.i.a1.shortMap_?.apply("c").query.get.map(_ ==> List((1, None), (2, Some(short4)), (3, None)))
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

      val (a1, b2) = ("a" -> short1, "b" -> short2)
      val (b3, c4) = ("b" -> short3, "c" -> short4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.shortMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // shortMap not asserted for i = 0
          _ <- Ns.i.a1.shortMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.shortMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.shortMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.shortMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.shortMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.shortMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_(List("a", "_")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.shortMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.shortMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.shortMap.shortMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.shortMap.shortMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.shortMap.shortMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.shortMap.shortMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.shortMap.shortMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.shortMap.shortMap_("a", "_").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.shortMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.shortMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.shortMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.shortMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.shortMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.shortMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.shortMap_.not(List("a", "_")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.shortMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.shortMap.shortMap_.not("_").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.shortMap.shortMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.shortMap.shortMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.shortMap.shortMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.shortMap.shortMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.shortMap.shortMap_.not("a", "_").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.shortMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.shortMap_.has(short0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.shortMap_.has(short1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.has(short2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.has(short3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.has(short4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.has(short0, short1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.has(short1, short2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.has(short2, short3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_.has(short3, short4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.has(short4, short5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.has(short5, short6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shortMap_.has(List(short0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.shortMap_.has(List(short1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.has(List(short2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.has(List(short3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.has(List(short4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.has(List(short0, short1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.has(List(short1, short2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.has(List(short2, short3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_.has(List(short3, short4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.has(List(short4, short5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.has(List(short5, short6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.shortMap_.has(List.empty[Short]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.shortMap.shortMap_.has(short0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.shortMap.shortMap_.has(short1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.shortMap.shortMap_.has(short2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.shortMap.shortMap_.has(short3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.shortMap.shortMap_.has(short4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.shortMap.shortMap_.has(short0, short1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.shortMap.shortMap_.has(short1, short2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.shortMap.shortMap_.has(short2, short3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.shortMap.shortMap_.has(short3, short4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.shortMap.shortMap_.has(short4, short5).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.shortMap.shortMap_.has(short5, short6).query.get.map(_ ==> List())
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.shortMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.shortMap_.hasNo(short0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_.hasNo(short1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.hasNo(short2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.hasNo(short3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.hasNo(short4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.hasNo(short0, short1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.hasNo(short1, short2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.hasNo(short2, short3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap_.hasNo(short3, short4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.hasNo(short4, short5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.hasNo(short5, short6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.shortMap_.hasNo(List(short0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortMap_.hasNo(List(short1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.hasNo(List(short2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.hasNo(List(short3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.hasNo(List(short4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.hasNo(List(short0, short1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.hasNo(List(short1, short2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortMap_.hasNo(List(short2, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortMap_.hasNo(List(short3, short4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.hasNo(List(short4, short5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortMap_.hasNo(List(short5, short6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.shortMap_.hasNo(List.empty[Short]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.shortMap.shortMap_.hasNo(short0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.shortMap.shortMap_.hasNo(short1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.shortMap.shortMap_.hasNo(short2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.shortMap.shortMap_.hasNo(short3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.shortMap.shortMap_.hasNo(short4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.shortMap.shortMap_.hasNo(short0, short1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.shortMap.shortMap_.hasNo(short1, short2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.shortMap.shortMap_.hasNo(short2, short3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.shortMap.shortMap_.hasNo(short3, short4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.shortMap.shortMap_.hasNo(short4, short5).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.shortMap.shortMap_.hasNo(short5, short6).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
        } yield ()
      }
    }
  }
}