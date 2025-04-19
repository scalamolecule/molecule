// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.time.Duration
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Types.*
import molecule.coreTests.setup.*

case class FilterSeq_Duration_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, List(duration1, duration2))
  val b = (2, List(duration2, duration3, duration3))

  import api.*
  import suite.*


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.durationSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.durationSeq.has(duration0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.durationSeq.has(duration1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.durationSeq.has(duration2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.durationSeq.has(duration3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.durationSeq.has(List(duration0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.durationSeq.has(List(duration1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.durationSeq.has(List(duration2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.durationSeq.has(List(duration3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.durationSeq.has(duration0, duration1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.durationSeq.has(duration1, duration2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.durationSeq.has(duration1, duration3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.durationSeq.has(duration2, duration3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.durationSeq.has(duration1, duration2, duration3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.durationSeq.has(List(duration0, duration1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.durationSeq.has(List(duration1, duration2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.durationSeq.has(List(duration1, duration3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.durationSeq.has(List(duration2, duration3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.durationSeq.has(List(duration1, duration2, duration3)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.durationSeq.has(List.empty[Duration]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.durationSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.durationSeq.hasNo(duration0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.durationSeq.hasNo(duration1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.durationSeq.hasNo(duration2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.durationSeq.hasNo(duration3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.durationSeq.hasNo(duration3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.durationSeq.hasNo(duration5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.durationSeq.hasNo(List(duration0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.durationSeq.hasNo(List(duration1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.durationSeq.hasNo(List(duration2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.durationSeq.hasNo(List(duration3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.durationSeq.hasNo(List(duration3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.durationSeq.hasNo(List(duration5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.durationSeq.hasNo(duration1, duration2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.durationSeq.hasNo(duration1, duration3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.durationSeq.hasNo(duration1, duration3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.durationSeq.hasNo(duration1, duration5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.durationSeq.hasNo(List(duration1, duration2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.durationSeq.hasNo(List(duration1, duration3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.durationSeq.hasNo(List(duration1, duration3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.durationSeq.hasNo(List(duration1, duration5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.durationSeq.hasNo(List.empty[Duration]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.durationSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.durationSeq_.has(duration0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.durationSeq_.has(duration1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.durationSeq_.has(duration2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.durationSeq_.has(duration3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.durationSeq_.has(List(duration0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.durationSeq_.has(List(duration1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.durationSeq_.has(List(duration2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.durationSeq_.has(List(duration3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.durationSeq_.has(duration0, duration1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.durationSeq_.has(duration1, duration2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.durationSeq_.has(duration1, duration3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.durationSeq_.has(duration2, duration3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.durationSeq_.has(duration3, duration4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.durationSeq_.has(List(duration0, duration1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.durationSeq_.has(List(duration1, duration2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.durationSeq_.has(List(duration1, duration3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.durationSeq_.has(List(duration2, duration3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.durationSeq_.has(List(duration3, duration4)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.durationSeq_.has(List.empty[Duration]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.durationSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.durationSeq_.hasNo(duration0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.durationSeq_.hasNo(duration1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.durationSeq_.hasNo(duration2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.durationSeq_.hasNo(duration3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.durationSeq_.hasNo(duration3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.durationSeq_.hasNo(duration5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.durationSeq_.hasNo(List(duration0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.durationSeq_.hasNo(List(duration1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.durationSeq_.hasNo(List(duration2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.durationSeq_.hasNo(List(duration3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.durationSeq_.hasNo(List(duration3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.durationSeq_.hasNo(List(duration5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.durationSeq_.hasNo(duration1, duration2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.durationSeq_.hasNo(duration1, duration3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.durationSeq_.hasNo(duration1, duration3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.durationSeq_.hasNo(duration1, duration5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.durationSeq_.hasNo(List(duration1, duration2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.durationSeq_.hasNo(List(duration1, duration3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.durationSeq_.hasNo(List(duration1, duration3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.durationSeq_.hasNo(List(duration1, duration5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.durationSeq_.hasNo(List.empty[Duration]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}