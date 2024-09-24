// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.time.ZonedDateTime
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_ZonedDateTime_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, Set(zonedDateTime1, zonedDateTime2))
        val b = (2, Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))
        for {
          _ <- Ns.i.zonedDateTimeSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.zonedDateTimeSet.has(zonedDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSet.has(zonedDateTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSet.has(zonedDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSet.has(zonedDateTime3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSet.has(Seq(zonedDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSet.has(Seq(zonedDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSet.has(Seq(zonedDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSet.has(Seq(zonedDateTime3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.zonedDateTimeSet.has(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSet.has(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSet.has(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSet.has(zonedDateTime1, zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSet.has(Seq(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSet.has(Seq(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSet.has(Seq(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSet.has(Seq(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.zonedDateTimeSet.has(Seq.empty[ZonedDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(zonedDateTime1, zonedDateTime2))
        val b = (2, Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))
        for {
          _ <- Ns.i.zonedDateTimeSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(zonedDateTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(zonedDateTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(zonedDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(zonedDateTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(zonedDateTime4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(zonedDateTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(Seq(zonedDateTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(Seq(zonedDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(Seq(zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(Seq(zonedDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(Seq(zonedDateTime4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(Seq(zonedDateTime5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(zonedDateTime1, zonedDateTime4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(zonedDateTime1, zonedDateTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(Seq(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(Seq(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(Seq(zonedDateTime1, zonedDateTime4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(Seq(zonedDateTime1, zonedDateTime5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.zonedDateTimeSet.hasNo(Seq.empty[ZonedDateTime]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.zonedDateTimeSet.insert(List(
            (1, Set(zonedDateTime1, zonedDateTime2)),
            (2, Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))
          )).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.zonedDateTimeSet_.has(zonedDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSet_.has(zonedDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSet_.has(zonedDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSet_.has(zonedDateTime3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSet_.has(Seq(zonedDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSet_.has(Seq(zonedDateTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSet_.has(Seq(zonedDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSet_.has(Seq(zonedDateTime3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.zonedDateTimeSet_.has(zonedDateTime0, zonedDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSet_.has(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSet_.has(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSet_.has(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSet_.has(zonedDateTime3, zonedDateTime4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSet_.has(Seq(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSet_.has(Seq(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSet_.has(Seq(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSet_.has(Seq(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.zonedDateTimeSet_.has(Seq.empty[ZonedDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.zonedDateTimeSet.insert(List(
            (1, Set(zonedDateTime1, zonedDateTime2)),
            (2, Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(zonedDateTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(zonedDateTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(zonedDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(zonedDateTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(zonedDateTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(zonedDateTime5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(Seq(zonedDateTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(Seq(zonedDateTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(Seq(zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(Seq(zonedDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(Seq(zonedDateTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(Seq(zonedDateTime5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(zonedDateTime1, zonedDateTime4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(zonedDateTime1, zonedDateTime5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(Seq(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(Seq(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(Seq(zonedDateTime1, zonedDateTime4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(Seq(zonedDateTime1, zonedDateTime5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.zonedDateTimeSet_.hasNo(Seq.empty[ZonedDateTime]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Set attributes
  }
}