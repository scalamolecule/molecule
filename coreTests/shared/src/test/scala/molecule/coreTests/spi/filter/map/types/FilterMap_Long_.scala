// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_Long_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> long1, "b" -> long2))
        val b = (2, Map("a" -> long2, "b" -> long3, "c" -> long4))
        for {
          _ <- Ns.i.longMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.longMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val long: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> long1, "b" -> long2))
        val b = (2, Map("a" -> long2, "b" -> long3, "c" -> long4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.longMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.longMap("-").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.longMap("a").query.get.map(_ ==> List((1, long1), (2, long2)))
          _ <- Ns.i.a1.longMap("b").query.get.map(_ ==> List((1, long2), (2, long3)))
          _ <- Ns.i.a1.longMap("c").query.get.map(_ ==> List((2, long4)))

          // Applying nothing to mandatory map attribute matches nothing,
          // not even entities without map attributes
          _ <- Ns.i.a1.longMap().query.get.map(_ ==> Nil)
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> long1, "b" -> long2)))
        val b = (2, Some(Map("a" -> long2, "b" -> long3, "c" -> long4)))
        val c = (3, None)
        for {
          _ <- Ns.i.longMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.longMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[Long] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> long1, "b" -> long2)))
        val b = (2, Some(Map("a" -> long2, "b" -> long3, "c" -> long4)))
        val c = (3, None)
        for {
          _ <- Ns.i.longMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.longMap_?("-").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.longMap_?("a").query.get.map(_ ==> List((1, Some(long1)), (2, Some(long2)), (3, None)))
          _ <- Ns.i.a1.longMap_?("b").query.get.map(_ ==> List((1, Some(long2)), (2, Some(long3)), (3, None)))
          _ <- Ns.i.a1.longMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(long4)), (3, None)))
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

      val (a1, b2) = ("a" -> long1, "b" -> long2)
      val (b3, c4) = ("b" -> long3, "c" -> long4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.longMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // longMap not asserted for i = 0
          _ <- Ns.i.a1.longMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.longMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.longMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.longMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.longMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.longMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longMap_(List("a", "-")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.longMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.longMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.longMap.longMap_("-").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.longMap.longMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.longMap.longMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.longMap.longMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.longMap.longMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.longMap.longMap_("a", "-").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.longMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.longMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.longMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.longMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.longMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.longMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.longMap_.not(List("a", "-")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.longMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.longMap.longMap_.not("-").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.longMap.longMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.longMap.longMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.longMap.longMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.longMap.longMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.longMap.longMap_.not("a", "-").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.longMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.longMap_.has(long0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.longMap_.has(long1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longMap_.has(long2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longMap_.has(long3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longMap_.has(long4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longMap_.has(long1, long2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longMap_.has(long2, long3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longMap_.has(long3, long4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.longMap_.has(List(long0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.longMap_.has(List(long1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longMap_.has(List(long2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longMap_.has(List(long3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longMap_.has(List(long4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longMap_.has(List(long1, long2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longMap_.has(List(long2, long3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longMap_.has(List(long3, long4)).query.get.map(_ ==> List(2))

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.longMap_.has(List.empty[Long]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.longMap.longMap_.has(long0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.longMap.longMap_.has(long1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.longMap.longMap_.has(long2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.longMap.longMap_.has(long3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.longMap.longMap_.has(long4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.longMap.longMap_.has(long1, long2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.longMap.longMap_.has(long2, long3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.longMap.longMap_.has(long3, long4).query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.longMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.longMap_.hasNo(long0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longMap_.hasNo(long1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longMap_.hasNo(long2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longMap_.hasNo(long3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longMap_.hasNo(long4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longMap_.hasNo(long1, long2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longMap_.hasNo(long2, long3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longMap_.hasNo(long3, long4).query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.longMap_.hasNo(List(long0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longMap_.hasNo(List(long1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longMap_.hasNo(List(long2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longMap_.hasNo(List(long3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longMap_.hasNo(List(long4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longMap_.hasNo(List(long1, long2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longMap_.hasNo(List(long2, long3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longMap_.hasNo(List(long3, long4)).query.get.map(_ ==> List(1))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.longMap_.hasNo(List.empty[Long]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.longMap.longMap_.hasNo(long0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.longMap.longMap_.hasNo(long1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.longMap.longMap_.hasNo(long2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.longMap.longMap_.hasNo(long3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.longMap.longMap_.hasNo(long4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.longMap.longMap_.hasNo(long1, long2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.longMap.longMap_.hasNo(long2, long3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.longMap.longMap_.hasNo(long3, long4).query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }
    }
  }
}