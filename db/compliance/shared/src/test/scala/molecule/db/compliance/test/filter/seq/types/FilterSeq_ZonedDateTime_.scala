// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.seq.types

import java.time.ZonedDateTime
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class FilterSeq_ZonedDateTime_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, List(zonedDateTime1, zonedDateTime2))
  val b = (2, List(zonedDateTime2, zonedDateTime3, zonedDateTime3))

  import api.*
  import suite.*


  "Mandatory: has" - types {
    for {
      _ <- Entity.i.zonedDateTimeSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.zonedDateTimeSeq.has(zonedDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeSeq.has(zonedDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTimeSeq.has(zonedDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTimeSeq.has(zonedDateTime3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.zonedDateTimeSeq.has(List(zonedDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeSeq.has(List(zonedDateTime1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTimeSeq.has(List(zonedDateTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTimeSeq.has(List(zonedDateTime3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.zonedDateTimeSeq.has(zonedDateTime0, zonedDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTimeSeq.has(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTimeSeq.has(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTimeSeq.has(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTimeSeq.has(zonedDateTime1, zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.zonedDateTimeSeq.has(List(zonedDateTime0, zonedDateTime1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTimeSeq.has(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTimeSeq.has(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTimeSeq.has(List(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTimeSeq.has(List(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.zonedDateTimeSeq.has(List.empty[ZonedDateTime]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types {
    for {
      _ <- Entity.i.zonedDateTimeSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime1, zonedDateTime5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime1, zonedDateTime5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.zonedDateTimeSeq.hasNo(List.empty[ZonedDateTime]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types {
    for {
      _ <- Entity.i.zonedDateTimeSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.zonedDateTimeSeq_.has(zonedDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeSeq_.has(zonedDateTime1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeSeq_.has(zonedDateTime2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeSeq_.has(zonedDateTime3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.zonedDateTimeSeq_.has(zonedDateTime0, zonedDateTime1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeSeq_.has(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeSeq_.has(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeSeq_.has(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeSeq_.has(zonedDateTime3, zonedDateTime4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime0, zonedDateTime1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime3, zonedDateTime4)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.zonedDateTimeSeq_.has(List.empty[ZonedDateTime]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types {
    for {
      _ <- Entity.i.zonedDateTimeSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime1, zonedDateTime5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime1, zonedDateTime5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.zonedDateTimeSeq_.hasNo(List.empty[ZonedDateTime]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}