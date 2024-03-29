// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.time.LocalTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_LocalTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, Set(localTime1, localTime2))
        val b = (2, Set(localTime2, localTime3, localTime4))
        for {
          _ <- Ns.i.localTimeSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.localTimeSet.has(localTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet.has(localTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSet.has(localTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSet.has(localTime3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localTimeSet.has(Seq(localTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet.has(Seq(localTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSet.has(Seq(localTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSet.has(Seq(localTime3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localTimeSet.has(localTime1, localTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSet.has(localTime1, localTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSet.has(localTime2, localTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSet.has(localTime1, localTime2, localTime3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localTimeSet.has(Seq(localTime1, localTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSet.has(Seq(localTime1, localTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSet.has(Seq(localTime2, localTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSet.has(Seq(localTime1, localTime2, localTime3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.localTimeSet.has(Seq.empty[LocalTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(localTime1, localTime2))
        val b = (2, Set(localTime2, localTime3, localTime4))
        for {
          _ <- Ns.i.localTimeSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.localTimeSet.hasNo(localTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSet.hasNo(localTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeSet.hasNo(localTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet.hasNo(localTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSet.hasNo(localTime4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSet.hasNo(localTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localTimeSet.hasNo(Seq(localTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSet.hasNo(Seq(localTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeSet.hasNo(Seq(localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet.hasNo(Seq(localTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSet.hasNo(Seq(localTime4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSet.hasNo(Seq(localTime5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.localTimeSet.hasNo(localTime1, localTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet.hasNo(localTime1, localTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet.hasNo(localTime1, localTime4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet.hasNo(localTime1, localTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localTimeSet.hasNo(Seq(localTime1, localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet.hasNo(Seq(localTime1, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet.hasNo(Seq(localTime1, localTime4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet.hasNo(Seq(localTime1, localTime5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.localTimeSet.hasNo(Seq.empty[LocalTime]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localTimeSet.insert(List(
            (1, Set(localTime1, localTime2)),
            (2, Set(localTime2, localTime3, localTime4))
          )).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.localTimeSet_.has(localTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet_.has(localTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSet_.has(localTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSet_.has(localTime3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localTimeSet_.has(Seq(localTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet_.has(Seq(localTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSet_.has(Seq(localTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSet_.has(Seq(localTime3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localTimeSet_.has(localTime1, localTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSet_.has(localTime1, localTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSet_.has(localTime2, localTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSet_.has(localTime1, localTime2, localTime3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localTimeSet_.has(Seq(localTime1, localTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSet_.has(Seq(localTime1, localTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSet_.has(Seq(localTime2, localTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSet_.has(Seq(localTime1, localTime2, localTime3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.localTimeSet_.has(Seq.empty[LocalTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localTimeSet.insert(List(
            (1, Set(localTime1, localTime2)),
            (2, Set(localTime2, localTime3, localTime4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.localTimeSet_.hasNo(localTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSet_.hasNo(localTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeSet_.hasNo(localTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet_.hasNo(localTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSet_.hasNo(localTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSet_.hasNo(localTime5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localTimeSet_.hasNo(Seq(localTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSet_.hasNo(Seq(localTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeSet_.hasNo(Seq(localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet_.hasNo(Seq(localTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSet_.hasNo(Seq(localTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSet_.hasNo(Seq(localTime5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.localTimeSet_.hasNo(localTime1, localTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet_.hasNo(localTime1, localTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet_.hasNo(localTime1, localTime4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet_.hasNo(localTime1, localTime5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localTimeSet_.hasNo(Seq(localTime1, localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet_.hasNo(Seq(localTime1, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet_.hasNo(Seq(localTime1, localTime4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet_.hasNo(Seq(localTime1, localTime5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.localTimeSet_.hasNo(Seq.empty[LocalTime]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "has" - types { implicit conn =>
        val a = (1, Some(Set(localTime1, localTime2)))
        val b = (2, Some(Set(localTime2, localTime3, localTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localTimeSet_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.localTimeSet_?.has(Some(localTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet_?.has(Some(localTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSet_?.has(Some(localTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSet_?.has(Some(localTime3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localTimeSet_?.has(Some(Seq(localTime0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet_?.has(Some(Seq(localTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSet_?.has(Some(Seq(localTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSet_?.has(Some(Seq(localTime3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localTimeSet_?.has(Some(Seq(localTime1, localTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSet_?.has(Some(Seq(localTime1, localTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSet_?.has(Some(Seq(localTime2, localTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSet_?.has(Some(Seq(localTime1, localTime2, localTime3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.localTimeSet_?.has(Some(Seq.empty[LocalTime])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.localTimeSet_?.has(Option.empty[LocalTime]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.localTimeSet_?.has(Option.empty[Seq[LocalTime]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(localTime1, localTime2)))
        val b = (2, Some(Set(localTime2, localTime3, localTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localTimeSet_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.localTimeSet_?.hasNo(Some(localTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSet_?.hasNo(Some(localTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeSet_?.hasNo(Some(localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet_?.hasNo(Some(localTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSet_?.hasNo(Some(localTime4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSet_?.hasNo(Some(localTime5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localTimeSet_?.hasNo(Some(Seq(localTime0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSet_?.hasNo(Some(Seq(localTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeSet_?.hasNo(Some(Seq(localTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet_?.hasNo(Some(Seq(localTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSet_?.hasNo(Some(Seq(localTime4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSet_?.hasNo(Some(Seq(localTime5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.localTimeSet_?.hasNo(Some(Seq(localTime1, localTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet_?.hasNo(Some(Seq(localTime1, localTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet_?.hasNo(Some(Seq(localTime1, localTime4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSet_?.hasNo(Some(Seq(localTime1, localTime5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.localTimeSet_?.hasNo(Some(Seq.empty[LocalTime])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.localTimeSet_?.hasNo(Option.empty[LocalTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSet_?.hasNo(Option.empty[Seq[LocalTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}