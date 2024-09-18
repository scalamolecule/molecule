// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_BigDecimal_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> bigDecimal1, "b" -> bigDecimal2))
        val b = (2, Map("a" -> bigDecimal2, "b" -> bigDecimal3, "c" -> bigDecimal4))
        for {
          _ <- Ns.i.bigDecimalMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.bigDecimalMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val bigDecimal: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> bigDecimal1, "b" -> bigDecimal2))
        val b = (2, Map("a" -> bigDecimal2, "b" -> bigDecimal3, "c" -> bigDecimal4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.bigDecimalMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.bigDecimalMap("_").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.bigDecimalMap("a").query.get.map(_ ==> List((1, bigDecimal1), (2, bigDecimal2)))
          _ <- Ns.i.a1.bigDecimalMap("b").query.get.map(_ ==> List((1, bigDecimal2), (2, bigDecimal3)))
          _ <- Ns.i.a1.bigDecimalMap("c").query.get.map(_ ==> List((2, bigDecimal4)))
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> bigDecimal1, "b" -> bigDecimal2)))
        val b = (2, Some(Map("a" -> bigDecimal2, "b" -> bigDecimal3, "c" -> bigDecimal4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigDecimalMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.bigDecimalMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[BigDecimal] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> bigDecimal1, "b" -> bigDecimal2)))
        val b = (2, Some(Map("a" -> bigDecimal2, "b" -> bigDecimal3, "c" -> bigDecimal4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigDecimalMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.bigDecimalMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.bigDecimalMap_?("a").query.get.map(_ ==> List((1, Some(bigDecimal1)), (2, Some(bigDecimal2)), (3, None)))
          _ <- Ns.i.a1.bigDecimalMap_?("b").query.get.map(_ ==> List((1, Some(bigDecimal2)), (2, Some(bigDecimal3)), (3, None)))
          _ <- Ns.i.a1.bigDecimalMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(bigDecimal4)), (3, None)))
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

      val (a1, b2) = ("a" -> bigDecimal1, "b" -> bigDecimal2)
      val (b3, c4) = ("b" -> bigDecimal3, "c" -> bigDecimal4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigDecimalMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // bigDecimalMap not asserted for i = 0
          _ <- Ns.i.a1.bigDecimalMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.bigDecimalMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.bigDecimalMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigDecimalMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.bigDecimalMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigDecimalMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalMap_(List("a", "_")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.bigDecimalMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.bigDecimalMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_("a", "_").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.bigDecimalMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.bigDecimalMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigDecimalMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigDecimalMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.bigDecimalMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigDecimalMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigDecimalMap_.not(List("a", "_")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.bigDecimalMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.not("_").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.not("a", "_").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.bigDecimalMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.bigDecimalMap_.has(bigDecimal0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigDecimalMap_.has(bigDecimal1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalMap_.has(bigDecimal2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalMap_.has(bigDecimal3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalMap_.has(bigDecimal4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalMap_.has(bigDecimal0, bigDecimal1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalMap_.has(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalMap_.has(bigDecimal2, bigDecimal3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalMap_.has(bigDecimal3, bigDecimal4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalMap_.has(bigDecimal4, bigDecimal5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalMap_.has(bigDecimal5, bigDecimal6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigDecimalMap_.has(List(bigDecimal0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigDecimalMap_.has(List(bigDecimal1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalMap_.has(List(bigDecimal2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalMap_.has(List(bigDecimal3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalMap_.has(List(bigDecimal4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalMap_.has(List(bigDecimal0, bigDecimal1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalMap_.has(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalMap_.has(List(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalMap_.has(List(bigDecimal3, bigDecimal4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalMap_.has(List(bigDecimal4, bigDecimal5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalMap_.has(List(bigDecimal5, bigDecimal6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.bigDecimalMap_.has(List.empty[BigDecimal]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.has(bigDecimal0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.has(bigDecimal1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.has(bigDecimal2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.has(bigDecimal3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.has(bigDecimal4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.has(bigDecimal0, bigDecimal1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.has(bigDecimal1, bigDecimal2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.has(bigDecimal2, bigDecimal3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.has(bigDecimal3, bigDecimal4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.has(bigDecimal4, bigDecimal5).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.has(bigDecimal5, bigDecimal6).query.get.map(_ ==> List())
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.bigDecimalMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(bigDecimal0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(bigDecimal1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(bigDecimal2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(bigDecimal3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(bigDecimal4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(bigDecimal0, bigDecimal1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(bigDecimal2, bigDecimal3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(bigDecimal3, bigDecimal4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(bigDecimal4, bigDecimal5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(bigDecimal5, bigDecimal6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(List(bigDecimal0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(List(bigDecimal1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(List(bigDecimal2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(List(bigDecimal3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(List(bigDecimal4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(List(bigDecimal0, bigDecimal1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(List(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(List(bigDecimal3, bigDecimal4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(List(bigDecimal4, bigDecimal5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(List(bigDecimal5, bigDecimal6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.bigDecimalMap_.hasNo(List.empty[BigDecimal]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.hasNo(bigDecimal0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.hasNo(bigDecimal1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.hasNo(bigDecimal2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.hasNo(bigDecimal3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.hasNo(bigDecimal4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.hasNo(bigDecimal0, bigDecimal1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.hasNo(bigDecimal1, bigDecimal2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.hasNo(bigDecimal2, bigDecimal3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.hasNo(bigDecimal3, bigDecimal4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.hasNo(bigDecimal4, bigDecimal5).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.bigDecimalMap.bigDecimalMap_.hasNo(bigDecimal5, bigDecimal6).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
        } yield ()
      }
    }
  }
}