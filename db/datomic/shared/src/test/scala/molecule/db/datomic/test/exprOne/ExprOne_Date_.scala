// GENERATED CODE ********************************
package molecule.db.datomic.test.exprOne

import java.util.Date
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object ExprOne_Date_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - cardOne { implicit conn =>
      val a = (1, date1)
      val b = (2, date2)
      val c = (3, date3)
      One.n.date.insert(List(a, b, c)).transact

      // Find all attribute values
      One.n.a1.date.query.get ==> List(a, b, c)

      // Find value(s) matching
      One.n.a1.date(date0).query.get ==> List()
      One.n.a1.date(date1).query.get ==> List(a)
      One.n.a1.date(Seq(date0)).query.get ==> List()
      One.n.a1.date(Seq(date1)).query.get ==> List(a)
      // OR semantics for multiple args
      One.n.a1.date(date1, date2).query.get ==> List(a, b)
      One.n.a1.date(date1, date0).query.get ==> List(a)
      One.n.a1.date(Seq(date1, date2)).query.get ==> List(a, b)
      One.n.a1.date(Seq(date1, date0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      One.n.a1.date(Seq.empty[Date]).query.get ==> List()

      // Find values not matching
      One.n.a1.date.not(date0).query.get ==> List(a, b, c)
      One.n.a1.date.not(date1).query.get ==> List(b, c)
      One.n.a1.date.not(date2).query.get ==> List(a, c)
      One.n.a1.date.not(date3).query.get ==> List(a, b)
      One.n.a1.date.not(Seq(date0)).query.get ==> List(a, b, c)
      One.n.a1.date.not(Seq(date1)).query.get ==> List(b, c)
      One.n.a1.date.not(Seq(date2)).query.get ==> List(a, c)
      One.n.a1.date.not(Seq(date3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      One.n.a1.date.not(date0, date1).query.get ==> List(b, c)
      One.n.a1.date.not(date1, date2).query.get ==> List(c)
      One.n.a1.date.not(date2, date3).query.get ==> List(a)
      One.n.a1.date.not(Seq(date0, date1)).query.get ==> List(b, c)
      One.n.a1.date.not(Seq(date1, date2)).query.get ==> List(c)
      One.n.a1.date.not(Seq(date2, date3)).query.get ==> List(a)
      // Empty Seq of negation args matches all values
      One.n.a1.date.not(Seq.empty[Date]).query.get ==> List(a, b, c)

      // Find values in range
      One.n.a1.date.<(date2).query.get ==> List(a)
      One.n.a1.date.>(date2).query.get ==> List(c)
      One.n.a1.date.<=(date2).query.get ==> List(a, b)
      One.n.a1.date.>=(date2).query.get ==> List(b, c)
    }


    "Tacit" - cardOne { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      One.n.date_?.insert(List(
        (a, Some(date1)),
        (b, Some(date2)),
        (c, Some(date3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      One.n.a1.date_.query.get ==> List(a, b, c)

      // Match non-asserted attribute (null)
      One.n.a1.date_().query.get ==> List(x)

      // Match attribute values without returning them
      One.n.a1.date_(date0).query.get ==> List()
      One.n.a1.date_(date1).query.get ==> List(a)
      One.n.a1.date_(Seq(date0)).query.get ==> List()
      One.n.a1.date_(Seq(date1)).query.get ==> List(a)
      // OR semantics for multiple args
      One.n.a1.date_(date1, date2).query.get ==> List(a, b)
      One.n.a1.date_(date1, date0).query.get ==> List(a)
      One.n.a1.date_(Seq(date1, date2)).query.get ==> List(a, b)
      One.n.a1.date_(Seq(date1, date0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      One.n.a1.date_(Seq.empty[Date]).query.get ==> List()

      // Match non-matching values without returning them
      One.n.a1.date_.not(date0).query.get ==> List(a, b, c)
      One.n.a1.date_.not(date1).query.get ==> List(b, c)
      One.n.a1.date_.not(date2).query.get ==> List(a, c)
      One.n.a1.date_.not(date3).query.get ==> List(a, b)
      One.n.a1.date_.not(Seq(date0)).query.get ==> List(a, b, c)
      One.n.a1.date_.not(Seq(date1)).query.get ==> List(b, c)
      One.n.a1.date_.not(Seq(date2)).query.get ==> List(a, c)
      One.n.a1.date_.not(Seq(date3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      One.n.a1.date_.not(date0, date1).query.get ==> List(b, c)
      One.n.a1.date_.not(date1, date2).query.get ==> List(c)
      One.n.a1.date_.not(date2, date3).query.get ==> List(a)
      One.n.a1.date_.not(Seq(date0, date1)).query.get ==> List(b, c)
      One.n.a1.date_.not(Seq(date1, date2)).query.get ==> List(c)
      One.n.a1.date_.not(Seq(date2, date3)).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      One.n.a1.date_.not(Seq.empty[Date]).query.get ==> List(a, b, c)

      // Match value ranges without returning them
      One.n.a1.date_.<(date2).query.get ==> List(a)
      One.n.a1.date_.>(date2).query.get ==> List(c)
      One.n.a1.date_.<=(date2).query.get ==> List(a, b)
      One.n.a1.date_.>=(date2).query.get ==> List(b, c)
    }


    "Optional" - cardOne { implicit conn =>
      val a = (1, Some(date1))
      val b = (2, Some(date2))
      val c = (3, Some(date3))
      val x = (4, Option.empty[Date])
      One.n.date_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      One.n.a1.date_?.query.get ==> List(a, b, c, x)

      // Find optional values matching
      One.n.a1.date_?(Some(date0)).query.get ==> List()
      One.n.a1.date_?(Some(date1)).query.get ==> List(a)
      One.n.a1.date_?(Some(Seq(date0))).query.get ==> List()
      One.n.a1.date_?(Some(Seq(date1))).query.get ==> List(a)
      // OR semantics for Ses of multiple args
      One.n.a1.date_?(Some(Seq(date1, date2))).query.get ==> List(a, b)
      One.n.a1.date_?(Some(Seq(date1, date0))).query.get ==> List(a)
      // Empty Seq of args matches no values
      One.n.a1.date_?(Some(Seq.empty[Date])).query.get ==> List()
      // None matches non-asserted/null values
      One.n.a1.date_?(Option.empty[Date]).query.get ==> List(x)
      One.n.a1.date_?(Option.empty[Seq[Date]]).query.get ==> List(x)

      // Find optional values not matching
      One.n.a1.date_?.not(Some(date0)).query.get ==> List(a, b, c)
      One.n.a1.date_?.not(Some(date1)).query.get ==> List(b, c)
      One.n.a1.date_?.not(Some(date2)).query.get ==> List(a, c)
      One.n.a1.date_?.not(Some(date3)).query.get ==> List(a, b)
      One.n.a1.date_?.not(Some(Seq(date0))).query.get ==> List(a, b, c)
      One.n.a1.date_?.not(Some(Seq(date1))).query.get ==> List(b, c)
      One.n.a1.date_?.not(Some(Seq(date2))).query.get ==> List(a, c)
      One.n.a1.date_?.not(Some(Seq(date3))).query.get ==> List(a, b)
      // OR semantics for multiple negation args
      One.n.a1.date_?.not(Some(Seq(date0, date1))).query.get ==> List(b, c)
      One.n.a1.date_?.not(Some(Seq(date1, date2))).query.get ==> List(c)
      One.n.a1.date_?.not(Some(Seq(date2, date3))).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      One.n.a1.date_?.not(Some(Seq.empty[Date])).query.get ==> List(a, b, c)
      // None matches all asserted values (non-null)
      One.n.a1.date_?.not(Option.empty[Date]).query.get ==> List(a, b, c)
      One.n.a1.date_?.not(Option.empty[Seq[Date]]).query.get ==> List(a, b, c)

      // Find optional values in range
      One.n.a1.date_?.<(Some(date2)).query.get ==> List(a)
      One.n.a1.date_?.>(Some(date2)).query.get ==> List(c)
      One.n.a1.date_?.<=(Some(date2)).query.get ==> List(a, b)
      One.n.a1.date_?.>=(Some(date2)).query.get ==> List(b, c)
      // None can't be compared and returns empty result
      One.n.a1.date_?.<(None).query.get ==> List()
      One.n.a1.date_?.<=(None).query.get ==> List()
      One.n.a1.date_?.>(None).query.get ==> List()
      One.n.a1.date_?.>=(None).query.get ==> List()
    }
  }
}