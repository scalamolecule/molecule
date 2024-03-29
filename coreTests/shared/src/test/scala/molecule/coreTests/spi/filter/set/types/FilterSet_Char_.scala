// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_Char_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        for {
          _ <- Ns.i.charSet.insert(List(a, b)).transact

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
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        for {
          _ <- Ns.i.charSet.insert(List(a, b)).transact

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
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.charSet.insert(List(
            (1, Set(char1, char2)),
            (2, Set(char2, char3, char4))
          )).transact

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
          _ <- Ns.i.a1.charSet_.has(char1, char2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(char1, char3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(char2, char3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(char1, char2, char3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.charSet_.has(Seq(char1, char2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(Seq(char1, char3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(Seq(char2, char3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(Seq(char1, char2, char3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.charSet_.has(Seq.empty[Char]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.charSet.insert(List(
            (1, Set(char1, char2)),
            (2, Set(char2, char3, char4))
          )).transact

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


    "Optional" - {

      "has" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        for {
          _ <- Ns.i.charSet_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.charSet_?.has(Some(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_?.has(Some(char1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet_?.has(Some(char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.has(Some(char3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.charSet_?.has(Some(Seq(char0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_?.has(Some(Seq(char1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet_?.has(Some(Seq(char2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.has(Some(Seq(char3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.charSet_?.has(Some(Seq(char1, char2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.has(Some(Seq(char1, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.has(Some(Seq(char2, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.has(Some(Seq(char1, char2, char3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.charSet_?.has(Some(Seq.empty[Char])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.charSet_?.has(Option.empty[Char]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.charSet_?.has(Option.empty[Seq[Char]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        for {
          _ <- Ns.i.charSet_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.charSet_?.hasNo(Some(char0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.hasNo(Some(char1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSet_?.hasNo(Some(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_?.hasNo(Some(char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet_?.hasNo(Some(char4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet_?.hasNo(Some(char5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq(char0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq(char1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq(char2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq(char3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq(char4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq(char5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq(char1, char2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq(char1, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq(char1, char4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq(char1, char5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq.empty[Char])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.charSet_?.hasNo(Option.empty[Char]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.hasNo(Option.empty[Seq[Char]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}