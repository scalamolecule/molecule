// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_Char_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  val a = (1, Set(char1, char2))
  val b = (2, Set(char2, char3, char4))

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i.charSet.insert(a, b).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.charSet.has(char0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet.has(char1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet.has(char2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.has(char3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.charSet.has(Seq(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet.has(Seq(char1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet.has(Seq(char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.has(Seq(char3)).query.get.map(_ ==> List(b))

          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.charSet.has(char1, char2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.has(char1, char3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.has(char2, char3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.has(char1, char2, char3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.charSet.has(Seq(char1, char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.has(Seq(char1, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.has(Seq(char2, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.has(Seq(char1, char2, char3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.charSet.has(Seq.empty[Char]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i.charSet.insert(a, b).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.charSet.hasNo(char0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.hasNo(char1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSet.hasNo(char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet.hasNo(char3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet.hasNo(char4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet.hasNo(char5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.charSet.hasNo(Seq(char0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.hasNo(Seq(char1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSet.hasNo(Seq(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet.hasNo(Seq(char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet.hasNo(Seq(char4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet.hasNo(Seq(char5)).query.get.map(_ ==> List(a, b))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.charSet.hasNo(char1, char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet.hasNo(char1, char3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet.hasNo(char1, char4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet.hasNo(char1, char5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.charSet.hasNo(Seq(char1, char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet.hasNo(Seq(char1, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet.hasNo(Seq(char1, char4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet.hasNo(Seq(char1, char5)).query.get.map(_ ==> List(b))

          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.charSet.hasNo(Seq.empty[Char]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i.charSet.insert(a, b).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.charSet_.has(char0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_.has(char1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSet_.has(char2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(char3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.charSet_.has(Seq(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_.has(Seq(char1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSet_.has(Seq(char2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(Seq(char3)).query.get.map(_ ==> List(2))

          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.charSet_.has(char0, char1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSet_.has(char1, char2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(char1, char3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(char2, char3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(char3, char4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.charSet_.has(Seq(char0, char1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSet_.has(Seq(char1, char2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(Seq(char1, char3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(Seq(char2, char3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(Seq(char3, char4)).query.get.map(_ ==> List(2))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.charSet_.has(Seq.empty[Char]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i.charSet.insert(a, b).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.charSet_.hasNo(char0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.hasNo(char1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charSet_.hasNo(char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_.hasNo(char3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSet_.hasNo(char4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSet_.hasNo(char5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.charSet_.hasNo(Seq(char0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.hasNo(Seq(char1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charSet_.hasNo(Seq(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_.hasNo(Seq(char3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSet_.hasNo(Seq(char4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSet_.hasNo(Seq(char5)).query.get.map(_ ==> List(1, 2))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.charSet_.hasNo(char1, char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_.hasNo(char1, char3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_.hasNo(char1, char4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_.hasNo(char1, char5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.charSet_.hasNo(Seq(char1, char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_.hasNo(Seq(char1, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_.hasNo(Seq(char1, char4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_.hasNo(Seq(char1, char5)).query.get.map(_ ==> List(2))

          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.charSet_.hasNo(Seq.empty[Char]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Set attributes
  }
}