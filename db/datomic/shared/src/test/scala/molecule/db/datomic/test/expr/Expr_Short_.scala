// GENERATED CODE ********************************
package molecule.db.datomic.test.expr

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Expr_Short_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - cardOne { implicit conn =>
      val a = (1, short1)
      val b = (2, short2)
      val c = (3, short3)
      One.n.short.insert(List(a, b, c)).transact

      // Apply args to find matching values
      One.n.a1.short(short0).query.get ==> List()
      One.n.a1.short(short1).query.get ==> List(a)
      One.n.a1.short(short1, short2).query.get ==> List(a, b)
      One.n.a1.short(short1, short0).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.short(Seq(short0)).query.get ==> List()
      One.n.a1.short(Seq(short1)).query.get ==> List(a)
      One.n.a1.short(Seq(short1, short2)).query.get ==> List(a, b)
      One.n.a1.short(Seq(short1, short0)).query.get ==> List(a)
      One.n.a1.short(Seq.empty[Short]).query.get ==> List()


      One.n.a1.short.not(short0).query.get ==> List(a, b, c)
      One.n.a1.short.not(short1).query.get ==> List(b, c)
      One.n.a1.short.not(short2).query.get ==> List(a, c)
      One.n.a1.short.not(short3).query.get ==> List(a, b)
      One.n.a1.short.not(short0, short1).query.get ==> List(b, c)
      One.n.a1.short.not(short1, short2).query.get ==> List(c)
      One.n.a1.short.not(short2, short3).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.short.not(Seq(short0)).query.get ==> List(a, b, c)
      One.n.a1.short.not(Seq(short1)).query.get ==> List(b, c)
      One.n.a1.short.not(Seq(short2)).query.get ==> List(a, c)
      One.n.a1.short.not(Seq(short3)).query.get ==> List(a, b)
      One.n.a1.short.not(Seq(short0, short1)).query.get ==> List(b, c)
      One.n.a1.short.not(Seq(short1, short2)).query.get ==> List(c)
      One.n.a1.short.not(Seq(short2, short3)).query.get ==> List(a)
      One.n.a1.short.not(Seq.empty[Short]).query.get ==> List(a, b, c)


      One.n.a1.short.<(short2).query.get ==> List(a)
      One.n.a1.short.>(short2).query.get ==> List(c)
      One.n.a1.short.<=(short2).query.get ==> List(a, b)
      One.n.a1.short.>=(short2).query.get ==> List(b, c)
    }


    "Tacit" - cardOne { implicit conn =>
      One.n.short_?.insert(List(
        (1, Some(short1)),
        (2, Some(short2)),
        (3, Some(short3)),
        (4, None)
      )).transact

      val a = 1
      val b = 2
      val c = 3
      val d = 4

      // No value asserted
      One.n.a1.short_.apply().query.get ==> List(d)

      One.n.a1.short_(short0).query.get ==> List()
      One.n.a1.short_.apply(short1).query.get ==> List(a)
      One.n.a1.short_(short1, short2).query.get ==> List(a, b)
      One.n.a1.short_(short1, short0).query.get ==> List(a)
      // Same as
      One.n.a1.short_(Seq(short0)).query.get ==> List()
      One.n.a1.short_(Seq(short1)).query.get ==> List(a)
      One.n.a1.short_(Seq(short1, short2)).query.get ==> List(a, b)
      One.n.a1.short_(Seq(short1, short0)).query.get ==> List(a)
      One.n.a1.short_(Seq.empty[Short]).query.get ==> List()


      One.n.a1.short_.not(short0).query.get ==> List(a, b, c)
      One.n.a1.short_.not(short1).query.get ==> List(b, c)
      One.n.a1.short_.not(short2).query.get ==> List(a, c)
      One.n.a1.short_.not(short3).query.get ==> List(a, b)
      One.n.a1.short_.not(short0, short1).query.get ==> List(b, c)
      One.n.a1.short_.not(short1, short2).query.get ==> List(c)
      One.n.a1.short_.not(short2, short3).query.get ==> List(a)
      // Same as
      One.n.a1.short_.not(Seq(short0)).query.get ==> List(a, b, c)
      One.n.a1.short_.not(Seq(short1)).query.get ==> List(b, c)
      One.n.a1.short_.not(Seq(short2)).query.get ==> List(a, c)
      One.n.a1.short_.not(Seq(short3)).query.get ==> List(a, b)
      One.n.a1.short_.not(Seq(short0, short1)).query.get ==> List(b, c)
      One.n.a1.short_.not(Seq(short1, short2)).query.get ==> List(c)
      One.n.a1.short_.not(Seq(short2, short3)).query.get ==> List(a)
      One.n.a1.short_.not(Seq.empty[Short]).query.get ==> List(a, b, c)


      One.n.a1.short_.<(short2).query.get ==> List(a)
      One.n.a1.short_.>(short2).query.get ==> List(c)
      One.n.a1.short_.<=(short2).query.get ==> List(a, b)
      One.n.a1.short_.>=(short2).query.get ==> List(b, c)
    }


    "Optional" - cardOne { implicit conn =>
      val a = (1, Some(short1))
      val b = (2, Some(short2))
      val c = (3, Some(short3))
      val x = (4, Option.empty[Short])

      One.n.short_?.insert(List(a, b, c, x)).transact

      One.n.a1.short_?(Some(short0)).query.get ==> List()
      One.n.a1.short_?.apply(Some(short1)).query.get ==> List(a)

      One.n.a1.short_?(Some(Seq(short0))).query.get ==> List()
      One.n.a1.short_?(Some(Seq(short1))).query.get ==> List(a)
      One.n.a1.short_?(Some(Seq(short1, short2))).query.get ==> List(a, b)
      One.n.a1.short_?(Some(Seq(short1, short0))).query.get ==> List(a)
      One.n.a1.short_?(Some(Seq.empty[Short])).query.get ==> List()

      One.n.a1.short_?(Option.empty[Short]).query.get ==> List(x)
      One.n.a1.short_?(Option.empty[Seq[Short]]).query.get ==> List(x)


      // For non-String cardinality-one attributes, `not` and `!=` have same semantics
      // "Not this value"
      One.n.a1.short_?.not(Some(short0)).query.get ==> List(a, b, c)
      One.n.a1.short_?.not(Some(short1)).query.get ==> List(b, c)
      One.n.a1.short_?.not(Some(short2)).query.get ==> List(a, c)
      One.n.a1.short_?.not(Some(short3)).query.get ==> List(a, b)
      // Same as
      One.n.a1.short_?.not(Some(Seq(short0))).query.get ==> List(a, b, c)
      One.n.a1.short_?.not(Some(Seq(short1))).query.get ==> List(b, c)
      One.n.a1.short_?.not(Some(Seq(short2))).query.get ==> List(a, c)
      One.n.a1.short_?.not(Some(Seq(short3))).query.get ==> List(a, b)

      // "Not this OR that value
      One.n.a1.short_?.not(Some(Seq(short0, short1))).query.get ==> List(b, c)
      One.n.a1.short_?.not(Some(Seq(short1, short2))).query.get ==> List(c)
      One.n.a1.short_?.not(Some(Seq(short2, short3))).query.get ==> List(a)
      One.n.a1.short_?.not(Some(Seq.empty[Short])).query.get ==> List(a, b, c)

      One.n.a1.short_?.not(Option.empty[Short]).query.get ==> List(a, b, c)
      One.n.a1.short_?.not(Option.empty[Seq[Short]]).query.get ==> List(a, b, c)


      One.n.a1.short_?.<(Some(short2)).query.get ==> List(a)
      One.n.a1.short_?.>(Some(short2)).query.get ==> List(c)
      One.n.a1.short_?.<=(Some(short2)).query.get ==> List(a, b)
      One.n.a1.short_?.>=(Some(short2)).query.get ==> List(b, c)

      // None can't be compared
      One.n.a1.short_?.<(None).query.get ==> List()
      One.n.a1.short_?.<=(None).query.get ==> List()
      One.n.a1.short_?.>(None).query.get ==> List()
      One.n.a1.short_?.>=(None).query.get ==> List()
    }
  }
}
