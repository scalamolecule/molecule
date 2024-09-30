// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_BigInt_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  val a = (1, Map("a" -> bigInt1, "b" -> bigInt2))
  val b = (2, Map("a" -> bigInt2, "b" -> bigInt3, "c" -> bigInt4))

  override lazy val tests = Tests {

    "Mandatory" - {

      "Mandatory Map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.bigIntMap.insert(a, b).transact
          _ <- Ns.i.a1.bigIntMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Value by key" - types { implicit conn =>
        for {
          _ <- Ns.i.bigIntMap.insert(a, b).transact

          // Get Map value by key

          // Like calling `apply` on a Scala Map.
          _ <- Ns.i.a1.bigIntMap("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap("a").query.get.map(_ ==> List((1, bigInt1), (2, bigInt2)))
          _ <- Ns.i.a1.bigIntMap("b").query.get.map(_ ==> List((1, bigInt2), (2, bigInt3)))
          _ <- Ns.i.a1.bigIntMap("c").query.get.map(_ ==> List((2, bigInt4)))
        } yield ()
      }


      "Map having values" - types { implicit conn =>
        for {
          _ <- Ns.i.bigIntMap.insert(a, b).transact

          // Maps containing value

          _ <- Ns.i.a1.bigIntMap.has(bigInt0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap.has(bigInt1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntMap.has(bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntMap.has(bigInt3).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntMap.has(bigInt4).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigIntMap.has(Seq(bigInt0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap.has(Seq(bigInt1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntMap.has(Seq(bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntMap.has(Seq(bigInt3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntMap.has(Seq(bigInt4)).query.get.map(_ ==> List(b))

          // OR semantics when multiple values

          // "Has this OR that" value
          _ <- Ns.i.a1.bigIntMap.has(bigInt0, bigInt1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntMap.has(bigInt1, bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntMap.has(bigInt2, bigInt3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntMap.has(bigInt3, bigInt4).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntMap.has(bigInt4, bigInt5).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntMap.has(bigInt5, bigInt6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigIntMap.has(Seq(bigInt0, bigInt1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntMap.has(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntMap.has(Seq(bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntMap.has(Seq(bigInt3, bigInt4)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntMap.has(Seq(bigInt4, bigInt5)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntMap.has(Seq(bigInt5, bigInt6)).query.get.map(_ ==> List())

          // No values match nothing
          _ <- Ns.i.a1.bigIntMap.has(Seq.empty[BigInt]).query.get.map(_ ==> List())
        } yield ()
      }


      "Map not having values" - types { implicit conn =>
        for {
          _ <- Ns.i.bigIntMap.insert(a, b).transact

          // "Doesn't have this"
          _ <- Ns.i.a1.bigIntMap.hasNo(bigInt0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntMap.hasNo(bigInt1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntMap.hasNo(bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap.hasNo(bigInt3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntMap.hasNo(bigInt3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntMap.hasNo(bigInt5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigIntMap.hasNo(List(bigInt0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntMap.hasNo(List(bigInt1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntMap.hasNo(List(bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap.hasNo(List(bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntMap.hasNo(List(bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntMap.hasNo(List(bigInt5)).query.get.map(_ ==> List(a, b))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.bigIntMap.hasNo(bigInt1, bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap.hasNo(bigInt1, bigInt5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigIntMap.hasNo(List(bigInt1, bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap.hasNo(List(bigInt1, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap.hasNo(List(bigInt1, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap.hasNo(List(bigInt1, bigInt5)).query.get.map(_ ==> List(b))

          // No values match nothing
          _ <- Ns.i.a1.bigIntMap.hasNo(List.empty[BigInt]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "Tacit Map (no filter)" - types { implicit conn =>
        for {
          _ <- Ns.i.bigIntMap.insert(a, b).transact
          _ <- Ns.i.a1.bigIntMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "Map contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.bigIntMap.insert(a, b).transact

          // "Map contains this OR that key"
          _ <- Ns.i.a1.bigIntMap_("_").query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap_("a").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_("_", "c").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.bigIntMap_(List("_")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap_(List("a")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_(List("_", "c")).query.get.map(_ ==> List(2))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.bigIntMap_(List.empty[String]).query.get.map(_ ==> List())

          // Match entities without map attribute
          _ <- Ns.i.a1.bigIntMap_().query.get.map(_ ==> List(0))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.bigIntMap.insert(a, b).transact

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.bigIntMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_.not("a").query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap_.not("b").query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.not("a", "c").query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap_.not("_", "c").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.bigIntMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_.not(List("a")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap_.not(List("b")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.not(List("a", "c")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.bigIntMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.bigIntMap.insert(a, b).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.bigIntMap_.has(bigInt0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap_.has(bigInt1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.has(bigInt2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_.has(bigInt3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.has(bigInt4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.has(bigInt0, bigInt1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.has(bigInt1, bigInt2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_.has(bigInt2, bigInt3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_.has(bigInt3, bigInt4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.has(bigInt4, bigInt5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.has(bigInt5, bigInt6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigIntMap_.has(List(bigInt0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap_.has(List(bigInt1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.has(List(bigInt2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_.has(List(bigInt3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.has(List(bigInt4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.has(List(bigInt0, bigInt1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.has(List(bigInt1, bigInt2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_.has(List(bigInt2, bigInt3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_.has(List(bigInt3, bigInt4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.has(List(bigInt4, bigInt5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.has(List(bigInt5, bigInt6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.bigIntMap_.has(List.empty[BigInt]).query.get.map(_ ==> List())
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.bigIntMap.insert(a, b).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.bigIntMap_.hasNo(bigInt0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_.hasNo(bigInt1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.hasNo(bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap_.hasNo(bigInt3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.hasNo(bigInt4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.hasNo(bigInt0, bigInt1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.hasNo(bigInt1, bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap_.hasNo(bigInt2, bigInt3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap_.hasNo(bigInt3, bigInt4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.hasNo(bigInt4, bigInt5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.hasNo(bigInt5, bigInt6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigIntMap_.hasNo(List(bigInt0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntMap_.hasNo(List(bigInt1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.hasNo(List(bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap_.hasNo(List(bigInt3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.hasNo(List(bigInt4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.hasNo(List(bigInt0, bigInt1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntMap_.hasNo(List(bigInt1, bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap_.hasNo(List(bigInt2, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntMap_.hasNo(List(bigInt3, bigInt4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.hasNo(List(bigInt4, bigInt5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntMap_.hasNo(List(bigInt5, bigInt6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.bigIntMap_.hasNo(List.empty[BigInt]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "Optional map (no filter)" - types { implicit conn =>
        val a = (1, Some(Map("a" -> bigInt1, "b" -> bigInt2)))
        val b = (2, Some(Map("a" -> bigInt2, "b" -> bigInt3, "c" -> bigInt4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigIntMap_?.insert(a, b, c).transact
          _ <- Ns.i.a1.bigIntMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>

        for {
          _ <- Ns.i.bigIntMap.insert(a, b).transact
          _ <- Ns.i(3).save.transact // entity without bigIntMap

          // Like calling `get` on a Scala Map.
          _ <- Ns.i.a1.bigIntMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.bigIntMap_?("a").query.get.map(_ ==> List((1, Some(bigInt1)), (2, Some(bigInt2)), (3, None)))
          _ <- Ns.i.a1.bigIntMap_?("b").query.get.map(_ ==> List((1, Some(bigInt2)), (2, Some(bigInt3)), (3, None)))
          _ <- Ns.i.a1.bigIntMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(bigInt4)), (3, None)))
        } yield ()
      }
    }
  }
}