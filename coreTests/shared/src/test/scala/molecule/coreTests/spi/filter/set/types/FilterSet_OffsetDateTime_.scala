// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.time.OffsetDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_OffsetDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, Set(offsetDateTime1, offsetDateTime2))
        val b = (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4))
        for {
          _ <- Ns.i.offsetDateTimeSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.offsetDateTimeSet.has(offsetDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet.has(offsetDateTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet.has(offsetDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.has(offsetDateTime3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet.has(Seq(offsetDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet.has(Seq(offsetDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet.has(Seq(offsetDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.has(Seq(offsetDateTime3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetDateTimeSet.has(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.has(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.has(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.has(offsetDateTime1, offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet.has(Seq(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.has(Seq(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.has(Seq(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.has(Seq(offsetDateTime1, offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetDateTimeSet.has(Seq.empty[OffsetDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(offsetDateTime1, offsetDateTime2))
        val b = (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4))
        for {
          _ <- Ns.i.offsetDateTimeSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(offsetDateTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(offsetDateTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(offsetDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(offsetDateTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(offsetDateTime4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(offsetDateTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq(offsetDateTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq(offsetDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq(offsetDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq(offsetDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq(offsetDateTime4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq(offsetDateTime5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(offsetDateTime1, offsetDateTime4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(offsetDateTime1, offsetDateTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq(offsetDateTime1, offsetDateTime4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq(offsetDateTime1, offsetDateTime5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq.empty[OffsetDateTime]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetDateTimeSet.insert(List(
            (1, Set(offsetDateTime1, offsetDateTime2)),
            (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4))
          )).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet_.has(Seq(offsetDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_.has(Seq(offsetDateTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(Seq(offsetDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(Seq(offsetDateTime3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime1, offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet_.has(Seq(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(Seq(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(Seq(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(Seq(offsetDateTime1, offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetDateTimeSet_.has(Seq.empty[OffsetDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetDateTimeSet.insert(List(
            (1, Set(offsetDateTime1, offsetDateTime2)),
            (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(offsetDateTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(offsetDateTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(offsetDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(offsetDateTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(offsetDateTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(offsetDateTime5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq(offsetDateTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq(offsetDateTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq(offsetDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq(offsetDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq(offsetDateTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq(offsetDateTime5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(offsetDateTime1, offsetDateTime4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(offsetDateTime1, offsetDateTime5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq(offsetDateTime1, offsetDateTime4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq(offsetDateTime1, offsetDateTime5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq.empty[OffsetDateTime]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "has" - types { implicit conn =>
        val a = (1, Some(Set(offsetDateTime1, offsetDateTime2)))
        val b = (2, Some(Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetDateTimeSet_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(offsetDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(offsetDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(offsetDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(offsetDateTime3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(Seq(offsetDateTime0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(Seq(offsetDateTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(Seq(offsetDateTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(Seq(offsetDateTime3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(Seq(offsetDateTime1, offsetDateTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(Seq(offsetDateTime1, offsetDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(Seq(offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(Seq(offsetDateTime1, offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(Seq.empty[OffsetDateTime])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Option.empty[OffsetDateTime]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Option.empty[Seq[OffsetDateTime]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(offsetDateTime1, offsetDateTime2)))
        val b = (2, Some(Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetDateTimeSet_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(offsetDateTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(offsetDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(offsetDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(offsetDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(offsetDateTime4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(offsetDateTime5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq(offsetDateTime0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq(offsetDateTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq(offsetDateTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq(offsetDateTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq(offsetDateTime4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq(offsetDateTime5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq(offsetDateTime1, offsetDateTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq(offsetDateTime1, offsetDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq(offsetDateTime1, offsetDateTime4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq(offsetDateTime1, offsetDateTime5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq.empty[OffsetDateTime])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Option.empty[OffsetDateTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Option.empty[Seq[OffsetDateTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}