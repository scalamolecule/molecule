// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_Double_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  val a = (1, Map("a" -> double1, "b" -> double2))
  val b = (2, Map("a" -> double2, "b" -> double3, "c" -> double4))

  override lazy val tests = Tests {

    "Mandatory" - {

      "Mandatory Map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.doubleMap.insert(a, b).transact
          _ <- Ns.i.a1.doubleMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Value by key" - types { implicit conn =>
        for {
          _ <- Ns.i.doubleMap.insert(a, b).transact

          // Get Map value by key

          // Like calling `apply` on a Scala Map.
          _ <- Ns.i.a1.doubleMap("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap("a").query.get.map(_ ==> List((1, double1), (2, double2)))
          _ <- Ns.i.a1.doubleMap("b").query.get.map(_ ==> List((1, double2), (2, double3)))
          _ <- Ns.i.a1.doubleMap("c").query.get.map(_ ==> List((2, double4)))
        } yield ()
      }


      "Map having values" - types { implicit conn =>
        for {
          _ <- Ns.i.doubleMap.insert(a, b).transact

          // Maps containing value

          _ <- Ns.i.a1.doubleMap.has(double0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap.has(double1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleMap.has(double2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleMap.has(double3).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleMap.has(double4).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubleMap.has(Seq(double0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap.has(Seq(double1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleMap.has(Seq(double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleMap.has(Seq(double3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleMap.has(Seq(double4)).query.get.map(_ ==> List(b))

          // OR semantics when multiple values

          // "Has this OR that" value
          _ <- Ns.i.a1.doubleMap.has(double0, double1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleMap.has(double1, double2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleMap.has(double2, double3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleMap.has(double3, double4).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleMap.has(double4, double5).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleMap.has(double5, double6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubleMap.has(Seq(double0, double1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleMap.has(Seq(double1, double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleMap.has(Seq(double2, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleMap.has(Seq(double3, double4)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleMap.has(Seq(double4, double5)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleMap.has(Seq(double5, double6)).query.get.map(_ ==> List())

          // No values match nothing
          _ <- Ns.i.a1.doubleMap.has(Seq.empty[Double]).query.get.map(_ ==> List())
        } yield ()
      }


      "Map not having values" - types { implicit conn =>
        for {
          _ <- Ns.i.doubleMap.insert(a, b).transact

          // "Doesn't have this"
          _ <- Ns.i.a1.doubleMap.hasNo(double0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleMap.hasNo(double1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleMap.hasNo(double2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap.hasNo(double3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleMap.hasNo(double3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleMap.hasNo(double5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubleMap.hasNo(List(double0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleMap.hasNo(List(double1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleMap.hasNo(List(double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap.hasNo(List(double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleMap.hasNo(List(double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleMap.hasNo(List(double5)).query.get.map(_ ==> List(a, b))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.doubleMap.hasNo(double1, double2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap.hasNo(double1, double3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap.hasNo(double1, double3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap.hasNo(double1, double5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubleMap.hasNo(List(double1, double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap.hasNo(List(double1, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap.hasNo(List(double1, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap.hasNo(List(double1, double5)).query.get.map(_ ==> List(b))

          // No values match nothing
          _ <- Ns.i.a1.doubleMap.hasNo(List.empty[Double]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "Tacit Map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.doubleMap.insert(a, b).transact
          _ <- Ns.i.a1.doubleMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "Map contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.doubleMap.insert(a, b).transact

          // "Map contains this OR that key"
          _ <- Ns.i.a1.doubleMap_("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap_("a").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_("_", "c").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.doubleMap_(List("_")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap_(List("a")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_(List("_", "c")).query.get.map(_ ==> List(2))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.doubleMap_(List.empty[String]).query.get.map(_ ==> List())

          // Match entities without map attribute
          _ <- Ns.i.a1.doubleMap_().query.get.map(_ ==> List(0))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.doubleMap.insert(a, b).transact

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.doubleMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_.not("a").query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap_.not("b").query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.not("a", "c").query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap_.not("_", "c").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.doubleMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_.not(List("a")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap_.not(List("b")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.not(List("a", "c")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.doubleMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.doubleMap.insert(a, b).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.doubleMap_.has(double0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap_.has(double1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.has(double2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_.has(double3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.has(double4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.has(double0, double1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.has(double1, double2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_.has(double2, double3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_.has(double3, double4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.has(double4, double5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.has(double5, double6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubleMap_.has(List(double0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap_.has(List(double1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.has(List(double2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_.has(List(double3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.has(List(double4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.has(List(double0, double1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.has(List(double1, double2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_.has(List(double2, double3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_.has(List(double3, double4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.has(List(double4, double5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.has(List(double5, double6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.doubleMap_.has(List.empty[Double]).query.get.map(_ ==> List())
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.doubleMap.insert(a, b).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.doubleMap_.hasNo(double0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_.hasNo(double1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.hasNo(double2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap_.hasNo(double3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.hasNo(double4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.hasNo(double0, double1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.hasNo(double1, double2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap_.hasNo(double2, double3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap_.hasNo(double3, double4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.hasNo(double4, double5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.hasNo(double5, double6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double0, double1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double1, double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double2, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double3, double4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double4, double5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleMap_.hasNo(List(double5, double6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.doubleMap_.hasNo(List.empty[Double]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "Optional map (no filter)" - types { implicit conn =>
        val a = (1, Some(Map("a" -> double1, "b" -> double2)))
        val b = (2, Some(Map("a" -> double2, "b" -> double3, "c" -> double4)))
        val c = (3, None)
        for {
          _ <- Ns.i.doubleMap_?.insert(a, b, c).transact
          _ <- Ns.i.a1.doubleMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>

        for {
          _ <- Ns.i.doubleMap.insert(a, b).transact
          _ <- Ns.i(3).save.transact // entity without doubleMap

          // Like calling `get` on a Scala Map.
          _ <- Ns.i.a1.doubleMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.doubleMap_?("a").query.get.map(_ ==> List((1, Some(double1)), (2, Some(double2)), (3, None)))
          _ <- Ns.i.a1.doubleMap_?("b").query.get.map(_ ==> List((1, Some(double2)), (2, Some(double3)), (3, None)))
          _ <- Ns.i.a1.doubleMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(double4)), (3, None)))
        } yield ()
      }
    }
  }
}