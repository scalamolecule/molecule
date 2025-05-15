// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.seq.types

import java.time.LocalDate
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterSeq_LocalDate_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, List(localDate1, localDate2))
  val b = (2, List(localDate2, localDate3, localDate3))

  import api.*
  import suite.*


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.localDateSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.localDateSeq.has(localDate0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateSeq.has(localDate1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateSeq.has(localDate2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateSeq.has(localDate3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.localDateSeq.has(List(localDate0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateSeq.has(List(localDate1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateSeq.has(List(localDate2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateSeq.has(List(localDate3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.localDateSeq.has(localDate0, localDate1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateSeq.has(localDate1, localDate2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateSeq.has(localDate1, localDate3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateSeq.has(localDate2, localDate3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateSeq.has(localDate1, localDate2, localDate3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.localDateSeq.has(List(localDate0, localDate1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateSeq.has(List(localDate1, localDate2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateSeq.has(List(localDate1, localDate3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateSeq.has(List(localDate2, localDate3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateSeq.has(List(localDate1, localDate2, localDate3)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.localDateSeq.has(List.empty[LocalDate]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.localDateSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.localDateSeq.hasNo(localDate0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateSeq.hasNo(localDate1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDateSeq.hasNo(localDate2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateSeq.hasNo(localDate3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateSeq.hasNo(localDate3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateSeq.hasNo(localDate5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.localDateSeq.hasNo(List(localDate0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateSeq.hasNo(List(localDate1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDateSeq.hasNo(List(localDate2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateSeq.hasNo(List(localDate3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateSeq.hasNo(List(localDate3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateSeq.hasNo(List(localDate5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.localDateSeq.hasNo(localDate1, localDate2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateSeq.hasNo(localDate1, localDate3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateSeq.hasNo(localDate1, localDate3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateSeq.hasNo(localDate1, localDate5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.localDateSeq.hasNo(List(localDate1, localDate2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateSeq.hasNo(List(localDate1, localDate3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateSeq.hasNo(List(localDate1, localDate3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateSeq.hasNo(List(localDate1, localDate5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.localDateSeq.hasNo(List.empty[LocalDate]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.localDateSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.localDateSeq_.has(localDate0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateSeq_.has(localDate1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateSeq_.has(localDate2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateSeq_.has(localDate3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.localDateSeq_.has(List(localDate0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateSeq_.has(List(localDate1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateSeq_.has(List(localDate2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateSeq_.has(List(localDate3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.localDateSeq_.has(localDate0, localDate1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateSeq_.has(localDate1, localDate2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateSeq_.has(localDate1, localDate3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateSeq_.has(localDate2, localDate3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateSeq_.has(localDate3, localDate4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.localDateSeq_.has(List(localDate0, localDate1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateSeq_.has(List(localDate1, localDate2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateSeq_.has(List(localDate1, localDate3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateSeq_.has(List(localDate2, localDate3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateSeq_.has(List(localDate3, localDate4)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.localDateSeq_.has(List.empty[LocalDate]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.localDateSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.localDateSeq_.hasNo(localDate0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateSeq_.hasNo(localDate1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localDateSeq_.hasNo(localDate2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateSeq_.hasNo(localDate3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateSeq_.hasNo(localDate3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateSeq_.hasNo(localDate5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.localDateSeq_.hasNo(List(localDate0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateSeq_.hasNo(List(localDate1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localDateSeq_.hasNo(List(localDate2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateSeq_.hasNo(List(localDate3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateSeq_.hasNo(List(localDate3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateSeq_.hasNo(List(localDate5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.localDateSeq_.hasNo(localDate1, localDate2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateSeq_.hasNo(localDate1, localDate3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateSeq_.hasNo(localDate1, localDate3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateSeq_.hasNo(localDate1, localDate5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.localDateSeq_.hasNo(List(localDate1, localDate2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateSeq_.hasNo(List(localDate1, localDate3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateSeq_.hasNo(List(localDate1, localDate3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateSeq_.hasNo(List(localDate1, localDate5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.localDateSeq_.hasNo(List.empty[LocalDate]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}