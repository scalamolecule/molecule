// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.set.types

import java.util.Date
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterSet_Date_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Set(date1, date2))
  val b = (2, Set(date2, date3, date4))

  import api.*
  import suite.*


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.dateSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.dateSet.has(date0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSet.has(date1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateSet.has(date2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateSet.has(date3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.dateSet.has(Seq(date0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSet.has(Seq(date1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateSet.has(Seq(date2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateSet.has(Seq(date3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.dateSet.has(date1, date2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateSet.has(date1, date3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateSet.has(date2, date3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateSet.has(date1, date2, date3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.dateSet.has(Seq(date1, date2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateSet.has(Seq(date1, date3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateSet.has(Seq(date2, date3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateSet.has(Seq(date1, date2, date3)).query.get.map(_ ==> List(a, b))


      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.dateSet.has(Seq.empty[Date]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.dateSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.dateSet.hasNo(date0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateSet.hasNo(date1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.dateSet.hasNo(date2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSet.hasNo(date3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateSet.hasNo(date4).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateSet.hasNo(date5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.dateSet.hasNo(Seq(date0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.dateSet.hasNo(Seq(date1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.dateSet.hasNo(Seq(date2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSet.hasNo(Seq(date3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateSet.hasNo(Seq(date4)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.dateSet.hasNo(Seq(date5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.dateSet.hasNo(date1, date2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSet.hasNo(date1, date3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSet.hasNo(date1, date4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSet.hasNo(date1, date5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.dateSet.hasNo(Seq(date1, date2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSet.hasNo(Seq(date1, date3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSet.hasNo(Seq(date1, date4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSet.hasNo(Seq(date1, date5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.dateSet.hasNo(Seq.empty[Date]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.dateSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.dateSet_.has(date0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSet_.has(date1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateSet_.has(date2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateSet_.has(date3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.dateSet_.has(Seq(date0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSet_.has(Seq(date1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateSet_.has(Seq(date2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateSet_.has(Seq(date3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.dateSet_.has(date0, date1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateSet_.has(date1, date2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateSet_.has(date1, date3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateSet_.has(date2, date3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateSet_.has(date3, date4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.dateSet_.has(Seq(date0, date1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateSet_.has(Seq(date1, date2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateSet_.has(Seq(date1, date3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateSet_.has(Seq(date2, date3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateSet_.has(Seq(date3, date4)).query.get.map(_ ==> List(2))

      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.dateSet_.has(Seq.empty[Date]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.dateSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.dateSet_.hasNo(date0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateSet_.hasNo(date1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.dateSet_.hasNo(date2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSet_.hasNo(date3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateSet_.hasNo(date4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateSet_.hasNo(date5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.dateSet_.hasNo(Seq(date0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.dateSet_.hasNo(Seq(date1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.dateSet_.hasNo(Seq(date2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSet_.hasNo(Seq(date3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateSet_.hasNo(Seq(date4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.dateSet_.hasNo(Seq(date5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.dateSet_.hasNo(date1, date2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSet_.hasNo(date1, date3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSet_.hasNo(date1, date4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSet_.hasNo(date1, date5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.dateSet_.hasNo(Seq(date1, date2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSet_.hasNo(Seq(date1, date3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSet_.hasNo(Seq(date1, date4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.dateSet_.hasNo(Seq(date1, date5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.dateSet_.hasNo(Seq.empty[Date]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Set attributes
}