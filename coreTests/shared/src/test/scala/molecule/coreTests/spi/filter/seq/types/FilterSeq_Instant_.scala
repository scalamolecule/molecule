// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.time.Instant
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterSeq_Instant_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, List(instant1, instant2))
  val b = (2, List(instant2, instant3, instant3))

  import api._
  import suite._


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.instantSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.instantSeq.has(instant0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSeq.has(instant1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantSeq.has(instant2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantSeq.has(instant3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.instantSeq.has(List(instant0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSeq.has(List(instant1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantSeq.has(List(instant2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantSeq.has(List(instant3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.instantSeq.has(instant0, instant1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantSeq.has(instant1, instant2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantSeq.has(instant1, instant3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantSeq.has(instant2, instant3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantSeq.has(instant1, instant2, instant3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.instantSeq.has(List(instant0, instant1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantSeq.has(List(instant1, instant2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantSeq.has(List(instant1, instant3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantSeq.has(List(instant2, instant3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantSeq.has(List(instant1, instant2, instant3)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.instantSeq.has(List.empty[Instant]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.instantSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.instantSeq.hasNo(instant0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantSeq.hasNo(instant1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.instantSeq.hasNo(instant2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSeq.hasNo(instant3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantSeq.hasNo(instant3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantSeq.hasNo(instant5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.instantSeq.hasNo(List(instant0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantSeq.hasNo(List(instant1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.instantSeq.hasNo(List(instant2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSeq.hasNo(List(instant3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantSeq.hasNo(List(instant3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantSeq.hasNo(List(instant5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.instantSeq.hasNo(instant1, instant2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSeq.hasNo(instant1, instant3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSeq.hasNo(instant1, instant3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSeq.hasNo(instant1, instant5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.instantSeq.hasNo(List(instant1, instant2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSeq.hasNo(List(instant1, instant3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSeq.hasNo(List(instant1, instant3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSeq.hasNo(List(instant1, instant5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.instantSeq.hasNo(List.empty[Instant]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.instantSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.instantSeq_.has(instant0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSeq_.has(instant1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantSeq_.has(instant2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantSeq_.has(instant3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.instantSeq_.has(List(instant0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSeq_.has(List(instant1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantSeq_.has(List(instant2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantSeq_.has(List(instant3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.instantSeq_.has(instant0, instant1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantSeq_.has(instant1, instant2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantSeq_.has(instant1, instant3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantSeq_.has(instant2, instant3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantSeq_.has(instant3, instant4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.instantSeq_.has(List(instant0, instant1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantSeq_.has(List(instant1, instant2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantSeq_.has(List(instant1, instant3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantSeq_.has(List(instant2, instant3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantSeq_.has(List(instant3, instant4)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.instantSeq_.has(List.empty[Instant]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.instantSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.instantSeq_.hasNo(instant0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantSeq_.hasNo(instant1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.instantSeq_.hasNo(instant2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSeq_.hasNo(instant3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantSeq_.hasNo(instant3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantSeq_.hasNo(instant5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.instantSeq_.hasNo(List(instant0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantSeq_.hasNo(List(instant1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.instantSeq_.hasNo(List(instant2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSeq_.hasNo(List(instant3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantSeq_.hasNo(List(instant3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantSeq_.hasNo(List(instant5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.instantSeq_.hasNo(instant1, instant2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSeq_.hasNo(instant1, instant3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSeq_.hasNo(instant1, instant3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSeq_.hasNo(instant1, instant5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.instantSeq_.hasNo(List(instant1, instant2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSeq_.hasNo(List(instant1, instant3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSeq_.hasNo(List(instant1, instant3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSeq_.hasNo(List(instant1, instant5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.instantSeq_.hasNo(List.empty[Instant]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}