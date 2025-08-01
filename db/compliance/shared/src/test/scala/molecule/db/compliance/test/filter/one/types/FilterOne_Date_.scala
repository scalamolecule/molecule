// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.one.types

import java.util.Date
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterOne_Date_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Mandatory" - types {
    val a = (1, date1)
    val b = (2, date2)
    val c = (3, date3)
    for {
      _ <- Entity.i.date.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.date.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.date(date0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.date(date1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.date(Seq(date0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.date(Seq(date1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.date(date1, date2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.date(date1, date0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.date(Seq(date1, date2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.date(Seq(date1, date0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.date(Seq.empty[Date]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.date.not(date0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.date.not(date1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.date.not(date2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.date.not(date3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.date.not(Seq(date0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.date.not(Seq(date1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.date.not(Seq(date2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.date.not(Seq(date3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.date.not(date0, date1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.date.not(date1, date2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.date.not(date2, date3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.date.not(Seq(date0, date1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.date.not(Seq(date1, date2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.date.not(Seq(date2, date3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.date.not(Seq.empty[Date]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.date.<(date2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.date.>(date2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.date.<=(date2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.date.>=(date2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types {
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.date_?.insert(List(
        (a, Some(date1)),
        (b, Some(date2)),
        (c, Some(date3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.date_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.date_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.date_(date0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.date_(date1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.date_(Seq(date0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.date_(Seq(date1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.date_(date1, date2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.date_(date1, date0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.date_(Seq(date1, date2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.date_(Seq(date1, date0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.date_(Seq.empty[Date]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.date_.not(date0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.date_.not(date1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.date_.not(date2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.date_.not(date3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.date_.not(Seq(date0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.date_.not(Seq(date1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.date_.not(Seq(date2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.date_.not(Seq(date3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.date_.not(date0, date1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.date_.not(date1, date2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.date_.not(date2, date3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.date_.not(Seq(date0, date1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.date_.not(Seq(date1, date2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.date_.not(Seq(date2, date3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.date_.not(Seq.empty[Date]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.date_.<(date2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.date_.>(date2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.date_.<=(date2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.date_.>=(date2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types {
    val a = (1, Some(date1))
    val b = (2, Some(date2))
    val c = (3, Some(date3))
    val x = (4, Option.empty[Date])
    for {
      _ <- Entity.i.date_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.date_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.date_?(Some(date0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.date_?(Some(date1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.date_?(Option.empty[Date]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.date_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types {
    for {
      _ <- Entity.i.date.insert(
        (1, date1),
        (2, date2),
        (3, date3),
        (4, date4),
        (5, date5),
        (6, date6),
        (7, date7),
        (8, date8),
        (9, date9),
      ).transact

      _ <- Entity.i.a1.date_.>(date2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.date_.>(date2).date_.<=(date8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.date_.>(date2).date_.<=(date8).date_.not(date4, date5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
