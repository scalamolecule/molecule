// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.seq.types

import java.time.OffsetDateTime
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterSeq_OffsetDateTime_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, List(offsetDateTime1, offsetDateTime2))
  val b = (2, List(offsetDateTime2, offsetDateTime3, offsetDateTime3))

  import api.*
  import suite.*


  "Mandatory: has" - types {
    for {
      _ <- Entity.i.offsetDateTimeSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.offsetDateTimeSeq.has(offsetDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeSeq.has(offsetDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTimeSeq.has(offsetDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTimeSeq.has(offsetDateTime3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.offsetDateTimeSeq.has(List(offsetDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeSeq.has(List(offsetDateTime1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTimeSeq.has(List(offsetDateTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTimeSeq.has(List(offsetDateTime3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.offsetDateTimeSeq.has(offsetDateTime0, offsetDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTimeSeq.has(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTimeSeq.has(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTimeSeq.has(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTimeSeq.has(offsetDateTime1, offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.offsetDateTimeSeq.has(List(offsetDateTime0, offsetDateTime1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTimeSeq.has(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTimeSeq.has(List(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTimeSeq.has(List(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTimeSeq.has(List(offsetDateTime1, offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.offsetDateTimeSeq.has(List.empty[OffsetDateTime]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types {
    for {
      _ <- Entity.i.offsetDateTimeSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime1, offsetDateTime5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime1, offsetDateTime5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.offsetDateTimeSeq.hasNo(List.empty[OffsetDateTime]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types {
    for {
      _ <- Entity.i.offsetDateTimeSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.offsetDateTimeSeq_.has(offsetDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeSeq_.has(offsetDateTime1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeSeq_.has(offsetDateTime2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeSeq_.has(offsetDateTime3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.offsetDateTimeSeq_.has(offsetDateTime0, offsetDateTime1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeSeq_.has(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeSeq_.has(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeSeq_.has(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeSeq_.has(offsetDateTime3, offsetDateTime4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime0, offsetDateTime1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime3, offsetDateTime4)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.offsetDateTimeSeq_.has(List.empty[OffsetDateTime]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types {
    for {
      _ <- Entity.i.offsetDateTimeSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime1, offsetDateTime5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime1, offsetDateTime5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.offsetDateTimeSeq_.hasNo(List.empty[OffsetDateTime]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}