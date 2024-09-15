// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import java.net.URI
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_URI_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> uri1, "b" -> uri2))
        val b = (2, Map("a" -> uri2, "b" -> uri3, "c" -> uri4))
        for {
          _ <- Ns.i.uriMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.uriMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val uri: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> uri1, "b" -> uri2))
        val b = (2, Map("a" -> uri2, "b" -> uri3, "c" -> uri4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.uriMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.uriMap("_").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.uriMap("a").query.get.map(_ ==> List((1, uri1), (2, uri2)))
          _ <- Ns.i.a1.uriMap("b").query.get.map(_ ==> List((1, uri2), (2, uri3)))
          _ <- Ns.i.a1.uriMap("c").query.get.map(_ ==> List((2, uri4)))
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> uri1, "b" -> uri2)))
        val b = (2, Some(Map("a" -> uri2, "b" -> uri3, "c" -> uri4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uriMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.uriMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[URI] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> uri1, "b" -> uri2)))
        val b = (2, Some(Map("a" -> uri2, "b" -> uri3, "c" -> uri4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uriMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.uriMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.uriMap_?("a").query.get.map(_ ==> List((1, Some(uri1)), (2, Some(uri2)), (3, None)))
          _ <- Ns.i.a1.uriMap_?("b").query.get.map(_ ==> List((1, Some(uri2)), (2, Some(uri3)), (3, None)))
          _ <- Ns.i.a1.uriMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(uri4)), (3, None)))
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

      val (a1, b2) = ("a" -> uri1, "b" -> uri2)
      val (b3, c4) = ("b" -> uri3, "c" -> uri4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uriMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // uriMap not asserted for i = 0
          _ <- Ns.i.a1.uriMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.uriMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.uriMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uriMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.uriMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uriMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriMap_(List("a", "_")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.uriMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.uriMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.uriMap.uriMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uriMap.uriMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.uriMap.uriMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.uriMap.uriMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.uriMap.uriMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.uriMap.uriMap_("a", "_").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.uriMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.uriMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uriMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uriMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.uriMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uriMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uriMap_.not(List("a", "_")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.uriMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.uriMap.uriMap_.not("_").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.uriMap.uriMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.uriMap.uriMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uriMap.uriMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.uriMap.uriMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uriMap.uriMap_.not("a", "_").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.uriMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.uriMap_.has(uri0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uriMap_.has(uri1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriMap_.has(uri2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriMap_.has(uri3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriMap_.has(uri4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriMap_.has(uri0, uri1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriMap_.has(uri1, uri2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriMap_.has(uri2, uri3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriMap_.has(uri3, uri4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriMap_.has(uri4, uri5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriMap_.has(uri5, uri6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uriMap_.has(List(uri0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uriMap_.has(List(uri1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriMap_.has(List(uri2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriMap_.has(List(uri3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriMap_.has(List(uri4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriMap_.has(List(uri0, uri1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriMap_.has(List(uri1, uri2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriMap_.has(List(uri2, uri3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriMap_.has(List(uri3, uri4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriMap_.has(List(uri4, uri5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriMap_.has(List(uri5, uri6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.uriMap_.has(List.empty[URI]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.uriMap.uriMap_.has(uri0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uriMap.uriMap_.has(uri1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.uriMap.uriMap_.has(uri2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.uriMap.uriMap_.has(uri3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.uriMap.uriMap_.has(uri4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.uriMap.uriMap_.has(uri0, uri1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.uriMap.uriMap_.has(uri1, uri2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.uriMap.uriMap_.has(uri2, uri3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.uriMap.uriMap_.has(uri3, uri4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.uriMap.uriMap_.has(uri4, uri5).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.uriMap.uriMap_.has(uri5, uri6).query.get.map(_ ==> List())
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.uriMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.uriMap_.hasNo(uri0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriMap_.hasNo(uri1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriMap_.hasNo(uri2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriMap_.hasNo(uri3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriMap_.hasNo(uri4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriMap_.hasNo(uri0, uri1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriMap_.hasNo(uri1, uri2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriMap_.hasNo(uri2, uri3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriMap_.hasNo(uri3, uri4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriMap_.hasNo(uri4, uri5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriMap_.hasNo(uri5, uri6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uriMap_.hasNo(List(uri0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriMap_.hasNo(List(uri1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriMap_.hasNo(List(uri2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriMap_.hasNo(List(uri3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriMap_.hasNo(List(uri4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriMap_.hasNo(List(uri0, uri1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriMap_.hasNo(List(uri1, uri2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriMap_.hasNo(List(uri2, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriMap_.hasNo(List(uri3, uri4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriMap_.hasNo(List(uri4, uri5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriMap_.hasNo(List(uri5, uri6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.uriMap_.hasNo(List.empty[URI]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.uriMap.uriMap_.hasNo(uri0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.uriMap.uriMap_.hasNo(uri1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.uriMap.uriMap_.hasNo(uri2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.uriMap.uriMap_.hasNo(uri3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.uriMap.uriMap_.hasNo(uri4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.uriMap.uriMap_.hasNo(uri0, uri1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.uriMap.uriMap_.hasNo(uri1, uri2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.uriMap.uriMap_.hasNo(uri2, uri3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uriMap.uriMap_.hasNo(uri3, uri4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.uriMap.uriMap_.hasNo(uri4, uri5).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.uriMap.uriMap_.hasNo(uri5, uri6).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
        } yield ()
      }
    }
  }
}