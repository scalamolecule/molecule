// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_Double_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> double1, "b" -> double2))
        val b = (2, Map("a" -> double2, "b" -> double3, "c" -> double4))
        for {
          _ <- Ns.i.doubleMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.doubleMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val double: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> double1, "b" -> double2))
        val b = (2, Map("a" -> double2, "b" -> double3, "c" -> double4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.doubleMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.doubleMap("_").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.doubleMap("a").query.get.map(_ ==> List((1, double1), (2, double2)))
          _ <- Ns.i.a1.doubleMap("b").query.get.map(_ ==> List((1, double2), (2, double3)))
          _ <- Ns.i.a1.doubleMap("c").query.get.map(_ ==> List((2, double4)))
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> double1, "b" -> double2)))
        val b = (2, Some(Map("a" -> double2, "b" -> double3, "c" -> double4)))
        val c = (3, None)
        for {
          _ <- Ns.i.doubleMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.doubleMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[Double] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> double1, "b" -> double2)))
        val b = (2, Some(Map("a" -> double2, "b" -> double3, "c" -> double4)))
        val c = (3, None)
        for {
          _ <- Ns.i.doubleMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.doubleMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.doubleMap_?("a").query.get.map(_ ==> List((1, Some(double1)), (2, Some(double2)), (3, None)))
          _ <- Ns.i.a1.doubleMap_?("b").query.get.map(_ ==> List((1, Some(double2)), (2, Some(double3)), (3, None)))
          _ <- Ns.i.a1.doubleMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(double4)), (3, None)))
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

      val (a1, b2) = ("a" -> double1, "b" -> double2)
      val (b3, c4) = ("b" -> double3, "c" -> double4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.doubleMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // doubleMap not asserted for i = 0
          _ <- Ns.i.a1.doubleMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.doubleMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.doubleMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.doubleMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.doubleMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.doubleMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_(List("a", "_")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.doubleMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.doubleMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.doubleMap.doubleMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.doubleMap.doubleMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.doubleMap.doubleMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.doubleMap.doubleMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.doubleMap.doubleMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.doubleMap.doubleMap_("a", "_").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.doubleMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.doubleMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.doubleMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.doubleMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.doubleMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.doubleMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.doubleMap_.not(List("a", "_")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.doubleMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.doubleMap.doubleMap_.not("_").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.doubleMap.doubleMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.doubleMap.doubleMap_.not("a", "_").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.doubleMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.doubleMap_.has(double0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.doubleMap_.has(double1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.has(double2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.has(double3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.has(double4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.has(double0, double1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.has(double1, double2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.has(double2, double3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_.has(double3, double4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.has(double4, double5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.has(double5, double6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubleMap_.has(List(double0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.doubleMap_.has(List(double1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.has(List(double2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.has(List(double3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.has(List(double4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.has(List(double0, double1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.has(List(double1, double2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.has(List(double2, double3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_.has(List(double3, double4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.has(List(double4, double5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.has(List(double5, double6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.doubleMap_.has(List.empty[Double]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.doubleMap.doubleMap_.has(double0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.doubleMap.doubleMap_.has(double1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.has(double2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.has(double3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.has(double4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.has(double0, double1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.has(double1, double2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.has(double2, double3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.has(double3, double4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.has(double4, double5).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.has(double5, double6).query.get.map(_ ==> List())
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.doubleMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.doubleMap_.hasNo(double0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_.hasNo(double1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.hasNo(double2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.hasNo(double3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.hasNo(double4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.hasNo(double0, double1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.hasNo(double1, double2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.hasNo(double2, double3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap_.hasNo(double3, double4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.hasNo(double4, double5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.hasNo(double5, double6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double0, double1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double1, double2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double2, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double3, double4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double4, double5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double5, double6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.doubleMap_.hasNo(List.empty[Double]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.doubleMap.doubleMap_.hasNo(double0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.hasNo(double1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.hasNo(double2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.hasNo(double3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.hasNo(double4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.hasNo(double0, double1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.hasNo(double1, double2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.hasNo(double2, double3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.doubleMap.doubleMap_.hasNo(double3, double4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.hasNo(double4, double5).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.doubleMap.doubleMap_.hasNo(double5, double6).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
        } yield ()
      }
    }
  }
}