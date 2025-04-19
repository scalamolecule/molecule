// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.time.LocalDateTime
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Types.*
import molecule.coreTests.setup.*

case class FilterSeq_LocalDateTime_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, List(localDateTime1, localDateTime2))
  val b = (2, List(localDateTime2, localDateTime3, localDateTime3))

  import api.*
  import suite.*


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.localDateTimeSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.localDateTimeSeq.has(localDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSeq.has(localDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTimeSeq.has(localDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTimeSeq.has(localDateTime3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.localDateTimeSeq.has(List(localDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSeq.has(List(localDateTime1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTimeSeq.has(List(localDateTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTimeSeq.has(List(localDateTime3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.localDateTimeSeq.has(localDateTime0, localDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTimeSeq.has(localDateTime1, localDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTimeSeq.has(localDateTime1, localDateTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTimeSeq.has(localDateTime2, localDateTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTimeSeq.has(localDateTime1, localDateTime2, localDateTime3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.localDateTimeSeq.has(List(localDateTime0, localDateTime1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTimeSeq.has(List(localDateTime1, localDateTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTimeSeq.has(List(localDateTime1, localDateTime3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTimeSeq.has(List(localDateTime2, localDateTime3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTimeSeq.has(List(localDateTime1, localDateTime2, localDateTime3)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.localDateTimeSeq.has(List.empty[LocalDateTime]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.localDateTimeSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(localDateTime0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(localDateTime1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(localDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(localDateTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(localDateTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(localDateTime5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(List(localDateTime0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(List(localDateTime1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(List(localDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(List(localDateTime3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(List(localDateTime3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(List(localDateTime5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(localDateTime1, localDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(localDateTime1, localDateTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(localDateTime1, localDateTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(localDateTime1, localDateTime5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(List(localDateTime1, localDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(List(localDateTime1, localDateTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(List(localDateTime1, localDateTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(List(localDateTime1, localDateTime5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.localDateTimeSeq.hasNo(List.empty[LocalDateTime]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.localDateTimeSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.localDateTimeSeq_.has(localDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSeq_.has(localDateTime1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateTimeSeq_.has(localDateTime2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateTimeSeq_.has(localDateTime3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.localDateTimeSeq_.has(List(localDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSeq_.has(List(localDateTime1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateTimeSeq_.has(List(localDateTime2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateTimeSeq_.has(List(localDateTime3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.localDateTimeSeq_.has(localDateTime0, localDateTime1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateTimeSeq_.has(localDateTime1, localDateTime2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateTimeSeq_.has(localDateTime1, localDateTime3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateTimeSeq_.has(localDateTime2, localDateTime3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateTimeSeq_.has(localDateTime3, localDateTime4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.localDateTimeSeq_.has(List(localDateTime0, localDateTime1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateTimeSeq_.has(List(localDateTime1, localDateTime2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateTimeSeq_.has(List(localDateTime1, localDateTime3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateTimeSeq_.has(List(localDateTime2, localDateTime3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateTimeSeq_.has(List(localDateTime3, localDateTime4)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.localDateTimeSeq_.has(List.empty[LocalDateTime]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.localDateTimeSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(localDateTime0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(localDateTime1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(localDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(localDateTime3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(localDateTime3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(localDateTime5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(List(localDateTime0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(List(localDateTime1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(List(localDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(List(localDateTime3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(List(localDateTime3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(List(localDateTime5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(localDateTime1, localDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(localDateTime1, localDateTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(localDateTime1, localDateTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(localDateTime1, localDateTime5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(List(localDateTime1, localDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(List(localDateTime1, localDateTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(List(localDateTime1, localDateTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(List(localDateTime1, localDateTime5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.localDateTimeSeq_.hasNo(List.empty[LocalDateTime]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}