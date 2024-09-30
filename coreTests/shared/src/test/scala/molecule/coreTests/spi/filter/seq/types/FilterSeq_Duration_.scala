// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.time.Duration
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_Duration_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  val a = (1, List(duration1, duration2))
  val b = (2, List(duration2, duration3, duration3))

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i.durationSeq.insert(a, b).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.durationSeq.has(duration0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq.has(duration1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq.has(duration2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.has(duration3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.durationSeq.has(List(duration0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq.has(List(duration1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq.has(List(duration2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.has(List(duration3)).query.get.map(_ ==> List(b))

          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.durationSeq.has(duration0, duration1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq.has(duration1, duration2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.has(duration1, duration3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.has(duration2, duration3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.has(duration1, duration2, duration3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durationSeq.has(List(duration0, duration1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq.has(List(duration1, duration2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.has(List(duration1, duration3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.has(List(duration2, duration3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.has(List(duration1, duration2, duration3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.durationSeq.has(List.empty[Duration]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i.durationSeq.insert(a, b).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.durationSeq.hasNo(duration0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.hasNo(duration1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSeq.hasNo(duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq.hasNo(duration3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq.hasNo(duration3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq.hasNo(duration5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durationSeq.hasNo(List(duration0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.hasNo(List(duration1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSeq.hasNo(List(duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq.hasNo(List(duration3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq.hasNo(List(duration3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq.hasNo(List(duration5)).query.get.map(_ ==> List(a, b))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.durationSeq.hasNo(duration1, duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq.hasNo(duration1, duration3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq.hasNo(duration1, duration3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq.hasNo(duration1, duration5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.durationSeq.hasNo(List(duration1, duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq.hasNo(List(duration1, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq.hasNo(List(duration1, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq.hasNo(List(duration1, duration5)).query.get.map(_ ==> List(b))

          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.durationSeq.hasNo(List.empty[Duration]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i.durationSeq.insert(a, b).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.durationSeq_.has(duration0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_.has(duration1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSeq_.has(duration2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.has(duration3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.durationSeq_.has(List(duration0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_.has(List(duration1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSeq_.has(List(duration2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.has(List(duration3)).query.get.map(_ ==> List(2))

          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.durationSeq_.has(duration0, duration1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSeq_.has(duration1, duration2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.has(duration1, duration3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.has(duration2, duration3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.has(duration3, duration4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.durationSeq_.has(List(duration0, duration1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSeq_.has(List(duration1, duration2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.has(List(duration1, duration3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.has(List(duration2, duration3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.has(List(duration3, duration4)).query.get.map(_ ==> List(2))

          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.durationSeq_.has(List.empty[Duration]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i.durationSeq.insert(a, b).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.durationSeq_.hasNo(duration0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.hasNo(duration1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationSeq_.hasNo(duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_.hasNo(duration3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSeq_.hasNo(duration3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSeq_.hasNo(duration5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.durationSeq_.hasNo(List(duration0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.hasNo(List(duration1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationSeq_.hasNo(List(duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_.hasNo(List(duration3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSeq_.hasNo(List(duration3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSeq_.hasNo(List(duration5)).query.get.map(_ ==> List(1, 2))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.durationSeq_.hasNo(duration1, duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_.hasNo(duration1, duration3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_.hasNo(duration1, duration3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_.hasNo(duration1, duration5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.durationSeq_.hasNo(List(duration1, duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_.hasNo(List(duration1, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_.hasNo(List(duration1, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_.hasNo(List(duration1, duration5)).query.get.map(_ ==> List(2))

          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.durationSeq_.hasNo(List.empty[Duration]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Seq attributes
  }
}