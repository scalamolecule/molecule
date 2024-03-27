// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import java.util.UUID
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_UUID_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> uuid1, "b" -> uuid2))
        val b = (2, Map("a" -> uuid2, "b" -> uuid3, "c" -> uuid4))
        for {
          _ <- Ns.i.uuidMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.uuidMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val uuid: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> uuid1, "b" -> uuid2))
        val b = (2, Map("a" -> uuid2, "b" -> uuid3, "c" -> uuid4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.uuidMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.uuidMap("-").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.uuidMap("a").query.get.map(_ ==> List((1, uuid1), (2, uuid2)))
          _ <- Ns.i.a1.uuidMap("b").query.get.map(_ ==> List((1, uuid2), (2, uuid3)))
          _ <- Ns.i.a1.uuidMap("c").query.get.map(_ ==> List((2, uuid4)))

          // Applying nothing to mandatory map attribute matches nothing,
          // not even entities without map attributes
          _ <- Ns.i.a1.uuidMap().query.get.map(_ ==> Nil)
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> uuid1, "b" -> uuid2)))
        val b = (2, Some(Map("a" -> uuid2, "b" -> uuid3, "c" -> uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuidMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.uuidMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[UUID] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> uuid1, "b" -> uuid2)))
        val b = (2, Some(Map("a" -> uuid2, "b" -> uuid3, "c" -> uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuidMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.uuidMap_?("-").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.uuidMap_?("a").query.get.map(_ ==> List((1, Some(uuid1)), (2, Some(uuid2)), (3, None)))
          _ <- Ns.i.a1.uuidMap_?("b").query.get.map(_ ==> List((1, Some(uuid2)), (2, Some(uuid3)), (3, None)))
          _ <- Ns.i.a1.uuidMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(uuid4)), (3, None)))
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

      val (a1, b2) = ("a" -> uuid1, "b" -> uuid2)
      val (b3, c4) = ("b" -> uuid3, "c" -> uuid4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uuidMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // uuidMap not asserted for i = 0
          _ <- Ns.i.a1.uuidMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.uuidMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.uuidMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uuidMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.uuidMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uuidMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_(List("a", "-")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.uuidMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.uuidMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.uuidMap.uuidMap_("-").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uuidMap.uuidMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.uuidMap.uuidMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.uuidMap.uuidMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.uuidMap.uuidMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.uuidMap.uuidMap_("a", "-").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.uuidMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.uuidMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uuidMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uuidMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.uuidMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uuidMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uuidMap_.not(List("a", "-")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.uuidMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.uuidMap.uuidMap_.not("-").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.uuidMap.uuidMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.uuidMap.uuidMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uuidMap.uuidMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.uuidMap.uuidMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uuidMap.uuidMap_.not("a", "-").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.uuidMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.uuidMap_.has(uuid0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uuidMap_.has(uuid1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.has(uuid2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.has(uuid3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.has(uuid4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.has(uuid1, uuid2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.has(uuid2, uuid3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_.has(uuid3, uuid4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.uuidMap_.has(List(uuid0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uuidMap_.has(List(uuid1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.has(List(uuid2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.has(List(uuid3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.has(List(uuid4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.has(List(uuid1, uuid2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.has(List(uuid2, uuid3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_.has(List(uuid3, uuid4)).query.get.map(_ ==> List(2))

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.uuidMap_.has(List.empty[UUID]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.uuidMap.uuidMap_.has(uuid0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uuidMap.uuidMap_.has(uuid1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.uuidMap.uuidMap_.has(uuid2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.uuidMap.uuidMap_.has(uuid3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.uuidMap.uuidMap_.has(uuid4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.uuidMap.uuidMap_.has(uuid1, uuid2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.uuidMap.uuidMap_.has(uuid2, uuid3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.uuidMap.uuidMap_.has(uuid3, uuid4).query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.uuidMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.uuidMap_.hasNo(uuid0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_.hasNo(uuid1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.hasNo(uuid2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.hasNo(uuid3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.hasNo(uuid4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.hasNo(uuid1, uuid2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.hasNo(uuid2, uuid3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap_.hasNo(uuid3, uuid4).query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.uuidMap_.hasNo(List(uuid0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_.hasNo(List(uuid1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.hasNo(List(uuid2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.hasNo(List(uuid3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.hasNo(List(uuid4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.hasNo(List(uuid1, uuid2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.hasNo(List(uuid2, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap_.hasNo(List(uuid3, uuid4)).query.get.map(_ ==> List(1))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.uuidMap_.hasNo(List.empty[UUID]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.uuidMap.uuidMap_.hasNo(uuid0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.uuidMap.uuidMap_.hasNo(uuid1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.uuidMap.uuidMap_.hasNo(uuid2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.uuidMap.uuidMap_.hasNo(uuid3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.uuidMap.uuidMap_.hasNo(uuid4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.uuidMap.uuidMap_.hasNo(uuid1, uuid2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.uuidMap.uuidMap_.hasNo(uuid2, uuid3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.uuidMap.uuidMap_.hasNo(uuid3, uuid4).query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }
    }
  }
}