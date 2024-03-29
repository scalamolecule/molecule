// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.time.Instant
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_Instant_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, Set(instant1, instant2))
        val b = (2, Set(instant2, instant3, instant4))
        for {
          _ <- Ns.i.instantSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.instantSet.has(instant0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet.has(instant1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSet.has(instant2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSet.has(instant3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.instantSet.has(Seq(instant0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet.has(Seq(instant1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSet.has(Seq(instant2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSet.has(Seq(instant3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.instantSet.has(instant1, instant2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSet.has(instant1, instant3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSet.has(instant2, instant3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSet.has(instant1, instant2, instant3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.instantSet.has(Seq(instant1, instant2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSet.has(Seq(instant1, instant3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSet.has(Seq(instant2, instant3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSet.has(Seq(instant1, instant2, instant3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.instantSet.has(Seq.empty[Instant]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(instant1, instant2))
        val b = (2, Set(instant2, instant3, instant4))
        for {
          _ <- Ns.i.instantSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.instantSet.hasNo(instant0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSet.hasNo(instant1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instantSet.hasNo(instant2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet.hasNo(instant3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSet.hasNo(instant4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSet.hasNo(instant5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.instantSet.hasNo(Seq(instant0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSet.hasNo(Seq(instant1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instantSet.hasNo(Seq(instant2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet.hasNo(Seq(instant3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSet.hasNo(Seq(instant4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSet.hasNo(Seq(instant5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.instantSet.hasNo(instant1, instant2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet.hasNo(instant1, instant3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet.hasNo(instant1, instant4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet.hasNo(instant1, instant5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.instantSet.hasNo(Seq(instant1, instant2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet.hasNo(Seq(instant1, instant3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet.hasNo(Seq(instant1, instant4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet.hasNo(Seq(instant1, instant5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.instantSet.hasNo(Seq.empty[Instant]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.instantSet.insert(List(
            (1, Set(instant1, instant2)),
            (2, Set(instant2, instant3, instant4))
          )).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.instantSet_.has(instant0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet_.has(instant1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantSet_.has(instant2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSet_.has(instant3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.instantSet_.has(Seq(instant0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet_.has(Seq(instant1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantSet_.has(Seq(instant2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSet_.has(Seq(instant3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.instantSet_.has(instant1, instant2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSet_.has(instant1, instant3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSet_.has(instant2, instant3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSet_.has(instant1, instant2, instant3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.instantSet_.has(Seq(instant1, instant2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSet_.has(Seq(instant1, instant3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSet_.has(Seq(instant2, instant3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSet_.has(Seq(instant1, instant2, instant3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.instantSet_.has(Seq.empty[Instant]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.instantSet.insert(List(
            (1, Set(instant1, instant2)),
            (2, Set(instant2, instant3, instant4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.instantSet_.hasNo(instant0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSet_.hasNo(instant1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantSet_.hasNo(instant2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet_.hasNo(instant3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantSet_.hasNo(instant4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantSet_.hasNo(instant5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.instantSet_.hasNo(Seq(instant0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSet_.hasNo(Seq(instant1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantSet_.hasNo(Seq(instant2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet_.hasNo(Seq(instant3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantSet_.hasNo(Seq(instant4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantSet_.hasNo(Seq(instant5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.instantSet_.hasNo(instant1, instant2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet_.hasNo(instant1, instant3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet_.hasNo(instant1, instant4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet_.hasNo(instant1, instant5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.instantSet_.hasNo(Seq(instant1, instant2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet_.hasNo(Seq(instant1, instant3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet_.hasNo(Seq(instant1, instant4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet_.hasNo(Seq(instant1, instant5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.instantSet_.hasNo(Seq.empty[Instant]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "has" - types { implicit conn =>
        val a = (1, Some(Set(instant1, instant2)))
        val b = (2, Some(Set(instant2, instant3, instant4)))
        val c = (3, None)
        for {
          _ <- Ns.i.instantSet_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.instantSet_?.has(Some(instant0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet_?.has(Some(instant1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSet_?.has(Some(instant2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSet_?.has(Some(instant3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.instantSet_?.has(Some(Seq(instant0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet_?.has(Some(Seq(instant1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSet_?.has(Some(Seq(instant2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSet_?.has(Some(Seq(instant3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.instantSet_?.has(Some(Seq(instant1, instant2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSet_?.has(Some(Seq(instant1, instant3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSet_?.has(Some(Seq(instant2, instant3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSet_?.has(Some(Seq(instant1, instant2, instant3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.instantSet_?.has(Some(Seq.empty[Instant])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.instantSet_?.has(Option.empty[Instant]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.instantSet_?.has(Option.empty[Seq[Instant]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(instant1, instant2)))
        val b = (2, Some(Set(instant2, instant3, instant4)))
        val c = (3, None)
        for {
          _ <- Ns.i.instantSet_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.instantSet_?.hasNo(Some(instant0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSet_?.hasNo(Some(instant1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instantSet_?.hasNo(Some(instant2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet_?.hasNo(Some(instant3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSet_?.hasNo(Some(instant4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSet_?.hasNo(Some(instant5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.instantSet_?.hasNo(Some(Seq(instant0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSet_?.hasNo(Some(Seq(instant1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instantSet_?.hasNo(Some(Seq(instant2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet_?.hasNo(Some(Seq(instant3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSet_?.hasNo(Some(Seq(instant4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSet_?.hasNo(Some(Seq(instant5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.instantSet_?.hasNo(Some(Seq(instant1, instant2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet_?.hasNo(Some(Seq(instant1, instant3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet_?.hasNo(Some(Seq(instant1, instant4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSet_?.hasNo(Some(Seq(instant1, instant5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.instantSet_?.hasNo(Some(Seq.empty[Instant])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.instantSet_?.hasNo(Option.empty[Instant]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSet_?.hasNo(Option.empty[Seq[Instant]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}