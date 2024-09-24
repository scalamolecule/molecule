// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_Byte_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> byte1, "b" -> byte2))
        val b = (2, Map("a" -> byte2, "b" -> byte3, "c" -> byte4))
        for {
          _ <- Ns.i.byteMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.byteMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val byte: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> byte1, "b" -> byte2))
        val b = (2, Map("a" -> byte2, "b" -> byte3, "c" -> byte4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.byteMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.byteMap("_").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.byteMap("a").query.get.map(_ ==> List((1, byte1), (2, byte2)))
          _ <- Ns.i.a1.byteMap("b").query.get.map(_ ==> List((1, byte2), (2, byte3)))
          _ <- Ns.i.a1.byteMap("c").query.get.map(_ ==> List((2, byte4)))
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> byte1, "b" -> byte2)))
        val b = (2, Some(Map("a" -> byte2, "b" -> byte3, "c" -> byte4)))
        val c = (3, None)
        for {
          _ <- Ns.i.byteMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.byteMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[Byte] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> byte1, "b" -> byte2)))
        val b = (2, Some(Map("a" -> byte2, "b" -> byte3, "c" -> byte4)))
        val c = (3, None)
        for {
          _ <- Ns.i.byteMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.byteMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.byteMap_?("a").query.get.map(_ ==> List((1, Some(byte1)), (2, Some(byte2)), (3, None)))
          _ <- Ns.i.a1.byteMap_?("b").query.get.map(_ ==> List((1, Some(byte2)), (2, Some(byte3)), (3, None)))
          _ <- Ns.i.a1.byteMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(byte4)), (3, None)))
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

      val (a1, b2) = ("a" -> byte1, "b" -> byte2)
      val (b3, c4) = ("b" -> byte3, "c" -> byte4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.byteMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // byteMap not asserted for i = 0
          _ <- Ns.i.a1.byteMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.byteMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.byteMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.byteMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.byteMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.byteMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteMap_(List("a", "_")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.byteMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.byteMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.byteMap.byteMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.byteMap.byteMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.byteMap.byteMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.byteMap.byteMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.byteMap.byteMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.byteMap.byteMap_("a", "_").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.byteMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.byteMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.byteMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.byteMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.byteMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.byteMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.byteMap_.not(List("a", "_")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.byteMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.byteMap.byteMap_.not("_").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.byteMap.byteMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.byteMap.byteMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.byteMap.byteMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.byteMap.byteMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.byteMap.byteMap_.not("a", "_").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.byteMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.byteMap_.has(byte0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.byteMap_.has(byte1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteMap_.has(byte2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteMap_.has(byte3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteMap_.has(byte4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteMap_.has(byte0, byte1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteMap_.has(byte1, byte2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteMap_.has(byte2, byte3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteMap_.has(byte3, byte4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteMap_.has(byte4, byte5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteMap_.has(byte5, byte6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.byteMap_.has(List(byte0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.byteMap_.has(List(byte1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteMap_.has(List(byte2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteMap_.has(List(byte3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteMap_.has(List(byte4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteMap_.has(List(byte0, byte1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteMap_.has(List(byte1, byte2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteMap_.has(List(byte2, byte3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteMap_.has(List(byte3, byte4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteMap_.has(List(byte4, byte5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteMap_.has(List(byte5, byte6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.byteMap_.has(List.empty[Byte]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.byteMap.byteMap_.has(byte0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.byteMap.byteMap_.has(byte1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.byteMap.byteMap_.has(byte2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.byteMap.byteMap_.has(byte3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.byteMap.byteMap_.has(byte4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.byteMap.byteMap_.has(byte0, byte1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.byteMap.byteMap_.has(byte1, byte2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.byteMap.byteMap_.has(byte2, byte3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.byteMap.byteMap_.has(byte3, byte4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.byteMap.byteMap_.has(byte4, byte5).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.byteMap.byteMap_.has(byte5, byte6).query.get.map(_ ==> List())
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.byteMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.byteMap_.hasNo(byte0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteMap_.hasNo(byte1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteMap_.hasNo(byte2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteMap_.hasNo(byte3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteMap_.hasNo(byte4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteMap_.hasNo(byte0, byte1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteMap_.hasNo(byte1, byte2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteMap_.hasNo(byte2, byte3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteMap_.hasNo(byte3, byte4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteMap_.hasNo(byte4, byte5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteMap_.hasNo(byte5, byte6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.byteMap_.hasNo(List(byte0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteMap_.hasNo(List(byte1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteMap_.hasNo(List(byte2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteMap_.hasNo(List(byte3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteMap_.hasNo(List(byte4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteMap_.hasNo(List(byte0, byte1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteMap_.hasNo(List(byte1, byte2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteMap_.hasNo(List(byte2, byte3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteMap_.hasNo(List(byte3, byte4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteMap_.hasNo(List(byte4, byte5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteMap_.hasNo(List(byte5, byte6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.byteMap_.hasNo(List.empty[Byte]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.byteMap.byteMap_.hasNo(byte0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.byteMap.byteMap_.hasNo(byte1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.byteMap.byteMap_.hasNo(byte2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.byteMap.byteMap_.hasNo(byte3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.byteMap.byteMap_.hasNo(byte4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.byteMap.byteMap_.hasNo(byte0, byte1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.byteMap.byteMap_.hasNo(byte1, byte2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.byteMap.byteMap_.hasNo(byte2, byte3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.byteMap.byteMap_.hasNo(byte3, byte4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.byteMap.byteMap_.hasNo(byte4, byte5).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.byteMap.byteMap_.hasNo(byte5, byte6).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
        } yield ()
      }
    }
  }
}