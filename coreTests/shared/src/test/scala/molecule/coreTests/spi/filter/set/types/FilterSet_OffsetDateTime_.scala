// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.time.OffsetDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
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
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime0, offsetDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime3, offsetDateTime4).query.get.map(_ ==> List(2))
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

    // No filtering on optional Set attributes
  }
}