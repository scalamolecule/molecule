// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import java.util.UUID
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_UUID_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  val a = (1, Map("a" -> uuid1, "b" -> uuid2))
  val b = (2, Map("a" -> uuid2, "b" -> uuid3, "c" -> uuid4))

  override lazy val tests = Tests {

    "Mandatory" - {

      "Mandatory map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.uuidMap.insert(a, b).transact
          _ <- Ns.i.a1.uuidMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Map with certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.uuidMap.insert(a, b).transact

          // Get Map value by key

          // Like calling `apply` on a Scala Map.
          _ <- Ns.i.a1.uuidMap("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap("a").query.get.map(_ ==> List((1, uuid1), (2, uuid2)))
          _ <- Ns.i.a1.uuidMap("b").query.get.map(_ ==> List((1, uuid2), (2, uuid3)))
          _ <- Ns.i.a1.uuidMap("c").query.get.map(_ ==> List((2, uuid4)))
        } yield ()
      }


      "Map without certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.uuidMap.insert(a, b).transact

          // Get Map without certain key(s)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.uuidMap.not("_").query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidMap.not("a").query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap.not("b").query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap.not("c").query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidMap.not("a", "c").query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap.not("_", "c").query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.uuidMap.not(List("_")).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidMap.not(List("a")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap.not(List("b")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap.not(List("c")).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidMap.not(List("a", "c")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap.not(List("_", "c")).query.get.map(_ ==> List(a))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.uuidMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Map with certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.uuidMap.insert(a, b).transact

          // Maps containing value

          _ <- Ns.i.a1.uuidMap.has(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap.has(uuid1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidMap.has(uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidMap.has(uuid3).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidMap.has(uuid4).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuidMap.has(Seq(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap.has(Seq(uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidMap.has(Seq(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidMap.has(Seq(uuid3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidMap.has(Seq(uuid4)).query.get.map(_ ==> List(b))

          // OR semantics when multiple values

          // "Has this OR that" value
          _ <- Ns.i.a1.uuidMap.has(uuid0, uuid1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidMap.has(uuid1, uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidMap.has(uuid2, uuid3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidMap.has(uuid3, uuid4).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidMap.has(uuid4, uuid5).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidMap.has(uuid5, uuid6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuidMap.has(Seq(uuid0, uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidMap.has(Seq(uuid1, uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidMap.has(Seq(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidMap.has(Seq(uuid3, uuid4)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidMap.has(Seq(uuid4, uuid5)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidMap.has(Seq(uuid5, uuid6)).query.get.map(_ ==> List())

          // No values match nothing
          _ <- Ns.i.a1.uuidMap.has(Seq.empty[UUID]).query.get.map(_ ==> List())
        } yield ()
      }


      "Map without certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.uuidMap.insert(a, b).transact

          // "Doesn't have this"
          _ <- Ns.i.a1.uuidMap.hasNo(uuid0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidMap.hasNo(uuid1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidMap.hasNo(uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap.hasNo(uuid3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidMap.hasNo(uuid3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidMap.hasNo(uuid5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuidMap.hasNo(List(uuid0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidMap.hasNo(List(uuid1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidMap.hasNo(List(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap.hasNo(List(uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidMap.hasNo(List(uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidMap.hasNo(List(uuid5)).query.get.map(_ ==> List(a, b))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.uuidMap.hasNo(uuid1, uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap.hasNo(uuid1, uuid5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuidMap.hasNo(List(uuid1, uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap.hasNo(List(uuid1, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap.hasNo(List(uuid1, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap.hasNo(List(uuid1, uuid5)).query.get.map(_ ==> List(b))

          // No values match nothing
          _ <- Ns.i.a1.uuidMap.hasNo(List.empty[UUID]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "Tacit Map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.uuidMap.insert(a, b).transact
          _ <- Ns.i.a1.uuidMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "Match map with certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.uuidMap.insert(a, b).transact

          // "Map contains this OR that key"
          _ <- Ns.i.a1.uuidMap_("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap_("a").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_("_", "c").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.uuidMap_(List("_")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap_(List("a")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_(List("_", "c")).query.get.map(_ ==> List(2))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.uuidMap_(List.empty[String]).query.get.map(_ ==> List())

          // Match entities without map attribute
          _ <- Ns.i.a1.uuidMap_().query.get.map(_ ==> List(0))
        } yield ()
      }


      "Match map without certain keys" - types { implicit conn =>
        for {
          _ <- Ns.i.uuidMap.insert(a, b).transact

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.uuidMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_.not("a").query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap_.not("b").query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.not("a", "c").query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap_.not("_", "c").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.uuidMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_.not(List("a")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap_.not(List("b")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.not(List("a", "c")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.uuidMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "Match map with certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.uuidMap.insert(a, b).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.uuidMap_.has(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap_.has(uuid1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.has(uuid2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_.has(uuid3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.has(uuid4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.has(uuid0, uuid1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.has(uuid1, uuid2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_.has(uuid2, uuid3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_.has(uuid3, uuid4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.has(uuid4, uuid5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.has(uuid5, uuid6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuidMap_.has(List(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap_.has(List(uuid1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.has(List(uuid2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_.has(List(uuid3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.has(List(uuid4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.has(List(uuid0, uuid1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.has(List(uuid1, uuid2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_.has(List(uuid2, uuid3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_.has(List(uuid3, uuid4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.has(List(uuid4, uuid5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.has(List(uuid5, uuid6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.uuidMap_.has(List.empty[UUID]).query.get.map(_ ==> List())
        } yield ()
      }


      "Match map without certain values" - types { implicit conn =>
        for {
          _ <- Ns.i.uuidMap.insert(a, b).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.uuidMap_.hasNo(uuid0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_.hasNo(uuid1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.hasNo(uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap_.hasNo(uuid3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.hasNo(uuid4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.hasNo(uuid0, uuid1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.hasNo(uuid1, uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap_.hasNo(uuid2, uuid3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap_.hasNo(uuid3, uuid4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.hasNo(uuid4, uuid5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.hasNo(uuid5, uuid6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uuidMap_.hasNo(List(uuid0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidMap_.hasNo(List(uuid1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.hasNo(List(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap_.hasNo(List(uuid3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.hasNo(List(uuid4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.hasNo(List(uuid0, uuid1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidMap_.hasNo(List(uuid1, uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap_.hasNo(List(uuid2, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidMap_.hasNo(List(uuid3, uuid4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.hasNo(List(uuid4, uuid5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidMap_.hasNo(List(uuid5, uuid6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.uuidMap_.hasNo(List.empty[UUID]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "Optional map (no filter)" - types { implicit conn =>
        val a = (1, Some(Map("a" -> uuid1, "b" -> uuid2)))
        val b = (2, Some(Map("a" -> uuid2, "b" -> uuid3, "c" -> uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuidMap_?.insert(a, b, c).transact
          _ <- Ns.i.a1.uuidMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Optional map values by key" - types { implicit conn =>

        for {
          _ <- Ns.i.uuidMap.insert(a, b).transact
          _ <- Ns.i(3).save.transact // entity without uuidMap

          // Like calling `get` on a Scala Map.
          _ <- Ns.i.a1.uuidMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.uuidMap_?("a").query.get.map(_ ==> List((1, Some(uuid1)), (2, Some(uuid2)), (3, None)))
          _ <- Ns.i.a1.uuidMap_?("b").query.get.map(_ ==> List((1, Some(uuid2)), (2, Some(uuid3)), (3, None)))
          _ <- Ns.i.a1.uuidMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(uuid4)), (3, None)))
        } yield ()
      }
    }
  }
}