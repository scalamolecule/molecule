// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.time.Duration
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_Duration_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  val a = (1, Set(duration1, duration2))
  val b = (2, Set(duration2, duration3, duration4))

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i.durationSet.insert(a, b).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.durationSet.has(duration0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet.has(duration1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet.has(duration2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.has(duration3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.durationSet.has(Seq(duration0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet.has(Seq(duration1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet.has(Seq(duration2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.has(Seq(duration3)).query.get.map(_ ==> List(b))

          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.durationSet.has(duration1, duration2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.has(duration1, duration3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.has(duration2, duration3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.has(duration1, duration2, duration3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durationSet.has(Seq(duration1, duration2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.has(Seq(duration1, duration3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.has(Seq(duration2, duration3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.has(Seq(duration1, duration2, duration3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.durationSet.has(Seq.empty[Duration]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i.durationSet.insert(a, b).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.durationSet.hasNo(duration0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.hasNo(duration1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSet.hasNo(duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet.hasNo(duration3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet.hasNo(duration4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet.hasNo(duration5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durationSet.hasNo(Seq(duration0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.hasNo(Seq(duration1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSet.hasNo(Seq(duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet.hasNo(Seq(duration3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet.hasNo(Seq(duration4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet.hasNo(Seq(duration5)).query.get.map(_ ==> List(a, b))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.durationSet.hasNo(duration1, duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet.hasNo(duration1, duration3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet.hasNo(duration1, duration4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet.hasNo(duration1, duration5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.durationSet.hasNo(Seq(duration1, duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet.hasNo(Seq(duration1, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet.hasNo(Seq(duration1, duration4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet.hasNo(Seq(duration1, duration5)).query.get.map(_ ==> List(b))

          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.durationSet.hasNo(Seq.empty[Duration]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i.durationSet.insert(a, b).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.durationSet_.has(duration0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_.has(duration1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSet_.has(duration2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.has(duration3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.durationSet_.has(Seq(duration0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_.has(Seq(duration1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSet_.has(Seq(duration2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.has(Seq(duration3)).query.get.map(_ ==> List(2))

          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.durationSet_.has(duration0, duration1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSet_.has(duration1, duration2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.has(duration1, duration3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.has(duration2, duration3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.has(duration3, duration4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.durationSet_.has(Seq(duration0, duration1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSet_.has(Seq(duration1, duration2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.has(Seq(duration1, duration3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.has(Seq(duration2, duration3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.has(Seq(duration3, duration4)).query.get.map(_ ==> List(2))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.durationSet_.has(Seq.empty[Duration]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i.durationSet.insert(a, b).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.durationSet_.hasNo(duration0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.hasNo(duration1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationSet_.hasNo(duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_.hasNo(duration3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSet_.hasNo(duration4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSet_.hasNo(duration5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.durationSet_.hasNo(Seq(duration0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.hasNo(Seq(duration1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationSet_.hasNo(Seq(duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_.hasNo(Seq(duration3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSet_.hasNo(Seq(duration4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSet_.hasNo(Seq(duration5)).query.get.map(_ ==> List(1, 2))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.durationSet_.hasNo(duration1, duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_.hasNo(duration1, duration3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_.hasNo(duration1, duration4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_.hasNo(duration1, duration5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.durationSet_.hasNo(Seq(duration1, duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_.hasNo(Seq(duration1, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_.hasNo(Seq(duration1, duration4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_.hasNo(Seq(duration1, duration5)).query.get.map(_ ==> List(2))

          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.durationSet_.hasNo(Seq.empty[Duration]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Set attributes
  }
}