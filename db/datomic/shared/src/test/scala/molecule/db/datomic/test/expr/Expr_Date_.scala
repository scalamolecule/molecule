// GENERATED CODE ********************************
package molecule.db.datomic.test.expr

import java.util.Date
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Expr_Date_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - cardOne { implicit conn =>
      val a = (1, date1)
      val b = (2, date2)
      val c = (3, date3)
      One.n.date.insert(List(a, b, c)).transact

      // Apply args to find matching values
      One.n.a1.date(date0).query.get ==> List()
      One.n.a1.date(date1).query.get ==> List(a)
      One.n.a1.date(date1, date2).query.get ==> List(a, b)
      One.n.a1.date(date1, date0).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.date(Seq(date0)).query.get ==> List()
      One.n.a1.date(Seq(date1)).query.get ==> List(a)
      One.n.a1.date(Seq(date1, date2)).query.get ==> List(a, b)
      One.n.a1.date(Seq(date1, date0)).query.get ==> List(a)
      One.n.a1.date(Seq.empty[Date]).query.get ==> List()


      One.n.a1.date.not(date0).query.get ==> List(a, b, c)
      One.n.a1.date.not(date1).query.get ==> List(b, c)
      One.n.a1.date.not(date2).query.get ==> List(a, c)
      One.n.a1.date.not(date3).query.get ==> List(a, b)
      One.n.a1.date.not(date0, date1).query.get ==> List(b, c)
      One.n.a1.date.not(date1, date2).query.get ==> List(c)
      One.n.a1.date.not(date2, date3).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.date.not(Seq(date0)).query.get ==> List(a, b, c)
      One.n.a1.date.not(Seq(date1)).query.get ==> List(b, c)
      One.n.a1.date.not(Seq(date2)).query.get ==> List(a, c)
      One.n.a1.date.not(Seq(date3)).query.get ==> List(a, b)
      One.n.a1.date.not(Seq(date0, date1)).query.get ==> List(b, c)
      One.n.a1.date.not(Seq(date1, date2)).query.get ==> List(c)
      One.n.a1.date.not(Seq(date2, date3)).query.get ==> List(a)
      One.n.a1.date.not(Seq.empty[Date]).query.get ==> List(a, b, c)


      One.n.a1.date.<(date2).query.get ==> List(a)
      One.n.a1.date.>(date2).query.get ==> List(c)
      One.n.a1.date.<=(date2).query.get ==> List(a, b)
      One.n.a1.date.>=(date2).query.get ==> List(b, c)
    }


    "Tacit" - cardOne { implicit conn =>
      One.n.date_?.insert(List(
        (1, Some(date1)),
        (2, Some(date2)),
        (3, Some(date3)),
        (4, None)
      )).transact

      val a = 1
      val b = 2
      val c = 3
      val d = 4

      // No value asserted
      One.n.a1.date_.apply().query.get ==> List(d)

      One.n.a1.date_(date0).query.get ==> List()
      One.n.a1.date_.apply(date1).query.get ==> List(a)
      One.n.a1.date_(date1, date2).query.get ==> List(a, b)
      One.n.a1.date_(date1, date0).query.get ==> List(a)
      // Same as
      One.n.a1.date_(Seq(date0)).query.get ==> List()
      One.n.a1.date_(Seq(date1)).query.get ==> List(a)
      One.n.a1.date_(Seq(date1, date2)).query.get ==> List(a, b)
      One.n.a1.date_(Seq(date1, date0)).query.get ==> List(a)
      One.n.a1.date_(Seq.empty[Date]).query.get ==> List()


      One.n.a1.date_.not(date0).query.get ==> List(a, b, c)
      One.n.a1.date_.not(date1).query.get ==> List(b, c)
      One.n.a1.date_.not(date2).query.get ==> List(a, c)
      One.n.a1.date_.not(date3).query.get ==> List(a, b)
      One.n.a1.date_.not(date0, date1).query.get ==> List(b, c)
      One.n.a1.date_.not(date1, date2).query.get ==> List(c)
      One.n.a1.date_.not(date2, date3).query.get ==> List(a)
      // Same as
      One.n.a1.date_.not(Seq(date0)).query.get ==> List(a, b, c)
      One.n.a1.date_.not(Seq(date1)).query.get ==> List(b, c)
      One.n.a1.date_.not(Seq(date2)).query.get ==> List(a, c)
      One.n.a1.date_.not(Seq(date3)).query.get ==> List(a, b)
      One.n.a1.date_.not(Seq(date0, date1)).query.get ==> List(b, c)
      One.n.a1.date_.not(Seq(date1, date2)).query.get ==> List(c)
      One.n.a1.date_.not(Seq(date2, date3)).query.get ==> List(a)
      One.n.a1.date_.not(Seq.empty[Date]).query.get ==> List(a, b, c)


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

      One.n.a1.date_?(Some(date0)).query.get ==> List()
      One.n.a1.date_?.apply(Some(date1)).query.get ==> List(a)

      One.n.a1.date_?(Some(Seq(date0))).query.get ==> List()
      One.n.a1.date_?(Some(Seq(date1))).query.get ==> List(a)
      One.n.a1.date_?(Some(Seq(date1, date2))).query.get ==> List(a, b)
      One.n.a1.date_?(Some(Seq(date1, date0))).query.get ==> List(a)
      One.n.a1.date_?(Some(Seq.empty[Date])).query.get ==> List()

      One.n.a1.date_?(Option.empty[Date]).query.get ==> List(x)
      One.n.a1.date_?(Option.empty[Seq[Date]]).query.get ==> List(x)


      // For non-String cardinality-one attributes, `not` and `!=` have same semantics
      // "Not this value"
      One.n.a1.date_?.not(Some(date0)).query.get ==> List(a, b, c)
      One.n.a1.date_?.not(Some(date1)).query.get ==> List(b, c)
      One.n.a1.date_?.not(Some(date2)).query.get ==> List(a, c)
      One.n.a1.date_?.not(Some(date3)).query.get ==> List(a, b)
      // Same as
      One.n.a1.date_?.not(Some(Seq(date0))).query.get ==> List(a, b, c)
      One.n.a1.date_?.not(Some(Seq(date1))).query.get ==> List(b, c)
      One.n.a1.date_?.not(Some(Seq(date2))).query.get ==> List(a, c)
      One.n.a1.date_?.not(Some(Seq(date3))).query.get ==> List(a, b)

      // "Not this OR that value
      One.n.a1.date_?.not(Some(Seq(date0, date1))).query.get ==> List(b, c)
      One.n.a1.date_?.not(Some(Seq(date1, date2))).query.get ==> List(c)
      One.n.a1.date_?.not(Some(Seq(date2, date3))).query.get ==> List(a)
      One.n.a1.date_?.not(Some(Seq.empty[Date])).query.get ==> List(a, b, c)

      One.n.a1.date_?.not(Option.empty[Date]).query.get ==> List(a, b, c)
      One.n.a1.date_?.not(Option.empty[Seq[Date]]).query.get ==> List(a, b, c)


      One.n.a1.date_?.<(Some(date2)).query.get ==> List(a)
      One.n.a1.date_?.>(Some(date2)).query.get ==> List(c)
      One.n.a1.date_?.<=(Some(date2)).query.get ==> List(a, b)
      One.n.a1.date_?.>=(Some(date2)).query.get ==> List(b, c)

      // None can't be compared
      One.n.a1.date_?.<(None).query.get ==> List()
      One.n.a1.date_?.<=(None).query.get ==> List()
      One.n.a1.date_?.>(None).query.get ==> List()
      One.n.a1.date_?.>=(None).query.get ==> List()
    }
  }
}
