// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.util.Date
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterSeq_Date_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, List(date1, date2))
  val b = (2, List(date2, date3, date3))

  import api._
  import suite._


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.dateSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.dateSeq.has(date0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSeq.has(date1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateSeq.has(date2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateSeq.has(date3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.dateSeq.has(List(date0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSeq.has(List(date1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateSeq.has(List(date2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateSeq.has(List(date3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.dateSeq.has(date0, date1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateSeq.has(date1, date2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateSeq.has(date1, date3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateSeq.has(date2, date3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateSeq.has(date1, date2, date3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.dateSeq.has(List(date0, date1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateSeq.has(List(date1, date2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateSeq.has(List(date1, date3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateSeq.has(List(date2, date3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateSeq.has(List(date1, date2, date3)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.dateSeq.has(List.empty[Date]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.dateSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.dateSeq.hasNo(date0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateSeq.hasNo(date1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.dateSeq.hasNo(date2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSeq.hasNo(date3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateSeq.hasNo(date3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateSeq.hasNo(date5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.dateSeq.hasNo(List(date0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateSeq.hasNo(List(date1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.dateSeq.hasNo(List(date2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSeq.hasNo(List(date3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateSeq.hasNo(List(date3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateSeq.hasNo(List(date5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.dateSeq.hasNo(date1, date2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSeq.hasNo(date1, date3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSeq.hasNo(date1, date3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSeq.hasNo(date1, date5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.dateSeq.hasNo(List(date1, date2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSeq.hasNo(List(date1, date3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSeq.hasNo(List(date1, date3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSeq.hasNo(List(date1, date5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.dateSeq.hasNo(List.empty[Date]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.dateSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.dateSeq_.has(date0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSeq_.has(date1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateSeq_.has(date2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateSeq_.has(date3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.dateSeq_.has(List(date0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSeq_.has(List(date1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateSeq_.has(List(date2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateSeq_.has(List(date3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.dateSeq_.has(date0, date1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateSeq_.has(date1, date2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateSeq_.has(date1, date3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateSeq_.has(date2, date3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateSeq_.has(date3, date4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.dateSeq_.has(List(date0, date1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateSeq_.has(List(date1, date2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateSeq_.has(List(date1, date3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateSeq_.has(List(date2, date3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateSeq_.has(List(date3, date4)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.dateSeq_.has(List.empty[Date]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.dateSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.dateSeq_.hasNo(date0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateSeq_.hasNo(date1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.dateSeq_.hasNo(date2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSeq_.hasNo(date3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateSeq_.hasNo(date3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateSeq_.hasNo(date5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.dateSeq_.hasNo(List(date0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateSeq_.hasNo(List(date1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.dateSeq_.hasNo(List(date2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSeq_.hasNo(List(date3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateSeq_.hasNo(List(date3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateSeq_.hasNo(List(date5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.dateSeq_.hasNo(date1, date2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSeq_.hasNo(date1, date3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSeq_.hasNo(date1, date3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSeq_.hasNo(date1, date5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.dateSeq_.hasNo(List(date1, date2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSeq_.hasNo(List(date1, date3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSeq_.hasNo(List(date1, date3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSeq_.hasNo(List(date1, date5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.dateSeq_.hasNo(List.empty[Date]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}