// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_BigInt_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> bigInt1, "b" -> bigInt2))
        val b = (2, Map("a" -> bigInt2, "b" -> bigInt3, "c" -> bigInt4))
        for {
          _ <- Ns.i.bigIntMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.bigIntMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val bigInt: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> bigInt1, "b" -> bigInt2))
        val b = (2, Map("a" -> bigInt2, "b" -> bigInt3, "c" -> bigInt4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.bigIntMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.bigIntMap("-").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.bigIntMap("a").query.get.map(_ ==> List((1, bigInt1), (2, bigInt2)))
          _ <- Ns.i.a1.bigIntMap("b").query.get.map(_ ==> List((1, bigInt2), (2, bigInt3)))
          _ <- Ns.i.a1.bigIntMap("c").query.get.map(_ ==> List((2, bigInt4)))

          // Applying nothing to mandatory map attribute matches nothing,
          // not even entities without map attributes
          _ <- Ns.i.a1.bigIntMap().query.get.map(_ ==> Nil)
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> bigInt1, "b" -> bigInt2)))
        val b = (2, Some(Map("a" -> bigInt2, "b" -> bigInt3, "c" -> bigInt4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigIntMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.bigIntMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[BigInt] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> bigInt1, "b" -> bigInt2)))
        val b = (2, Some(Map("a" -> bigInt2, "b" -> bigInt3, "c" -> bigInt4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigIntMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.bigIntMap_?("-").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.bigIntMap_?("a").query.get.map(_ ==> List((1, Some(bigInt1)), (2, Some(bigInt2)), (3, None)))
          _ <- Ns.i.a1.bigIntMap_?("b").query.get.map(_ ==> List((1, Some(bigInt2)), (2, Some(bigInt3)), (3, None)))
          _ <- Ns.i.a1.bigIntMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(bigInt4)), (3, None)))
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

      val (a1, b2) = ("a" -> bigInt1, "b" -> bigInt2)
      val (b3, c4) = ("b" -> bigInt3, "c" -> bigInt4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigIntMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // bigIntMap not asserted for i = 0
          _ <- Ns.i.a1.bigIntMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.bigIntMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.bigIntMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigIntMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.bigIntMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigIntMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_(List("a", "-")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.bigIntMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.bigIntMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.bigIntMap.bigIntMap_("-").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigIntMap.bigIntMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.bigIntMap.bigIntMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.bigIntMap.bigIntMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.bigIntMap.bigIntMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.bigIntMap.bigIntMap_("a", "-").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.bigIntMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.bigIntMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigIntMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigIntMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.bigIntMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigIntMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigIntMap_.not(List("a", "-")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.bigIntMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.not("-").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.not("a", "-").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.bigIntMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.bigIntMap_.has(bigInt0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigIntMap_.has(bigInt1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.has(bigInt2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.has(bigInt3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.has(bigInt4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.has(bigInt1, bigInt2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.has(bigInt2, bigInt3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_.has(bigInt3, bigInt4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.bigIntMap_.has(List(bigInt0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigIntMap_.has(List(bigInt1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.has(List(bigInt2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.has(List(bigInt3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.has(List(bigInt4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.has(List(bigInt1, bigInt2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.has(List(bigInt2, bigInt3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_.has(List(bigInt3, bigInt4)).query.get.map(_ ==> List(2))

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.bigIntMap_.has(List.empty[BigInt]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.has(bigInt0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.has(bigInt1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.has(bigInt2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.has(bigInt3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.has(bigInt4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.has(bigInt1, bigInt2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.has(bigInt2, bigInt3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.has(bigInt3, bigInt4).query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.bigIntMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.bigIntMap_.hasNo(bigInt0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_.hasNo(bigInt1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.hasNo(bigInt2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.hasNo(bigInt3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.hasNo(bigInt4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.hasNo(bigInt1, bigInt2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.hasNo(bigInt2, bigInt3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap_.hasNo(bigInt3, bigInt4).query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.bigIntMap_.hasNo(List(bigInt0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_.hasNo(List(bigInt1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.hasNo(List(bigInt2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.hasNo(List(bigInt3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.hasNo(List(bigInt4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.hasNo(List(bigInt1, bigInt2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.hasNo(List(bigInt2, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap_.hasNo(List(bigInt3, bigInt4)).query.get.map(_ ==> List(1))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.bigIntMap_.hasNo(List.empty[BigInt]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.hasNo(bigInt0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.hasNo(bigInt1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.hasNo(bigInt2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.hasNo(bigInt3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.hasNo(bigInt4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.hasNo(bigInt1, bigInt2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.hasNo(bigInt2, bigInt3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.bigIntMap.bigIntMap_.hasNo(bigInt3, bigInt4).query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }
    }
  }
}