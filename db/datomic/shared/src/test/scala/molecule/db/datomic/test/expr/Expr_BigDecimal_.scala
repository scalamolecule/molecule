// GENERATED CODE ********************************
package molecule.db.datomic.test.expr

import molecule.boilerplate.api.Keywords._
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Expr_BigDecimal_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - cardOne { implicit conn =>
      val a = (1, bigDecimal1)
      val b = (2, bigDecimal2)
      val c = (3, bigDecimal3)
      One.n.bigDecimal.insert(List(a, b, c)).transact

      // Apply args to find matching values
      One.n.a1.bigDecimal(bigDecimal0).query.get ==> List()
      One.n.a1.bigDecimal(bigDecimal1).query.get ==> List(a)
      One.n.a1.bigDecimal(bigDecimal1, bigDecimal2).query.get ==> List(a, b)
      One.n.a1.bigDecimal(bigDecimal1, bigDecimal0).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.bigDecimal(Seq(bigDecimal0)).query.get ==> List()
      One.n.a1.bigDecimal(Seq(bigDecimal1)).query.get ==> List(a)
      One.n.a1.bigDecimal(Seq(bigDecimal1, bigDecimal2)).query.get ==> List(a, b)
      One.n.a1.bigDecimal(Seq(bigDecimal1, bigDecimal0)).query.get ==> List(a)
      One.n.a1.bigDecimal(Seq.empty[BigDecimal]).query.get ==> List()


      One.n.a1.bigDecimal.not(bigDecimal0).query.get ==> List(a, b, c)
      One.n.a1.bigDecimal.not(bigDecimal1).query.get ==> List(b, c)
      One.n.a1.bigDecimal.not(bigDecimal2).query.get ==> List(a, c)
      One.n.a1.bigDecimal.not(bigDecimal3).query.get ==> List(a, b)
      One.n.a1.bigDecimal.not(bigDecimal0, bigDecimal1).query.get ==> List(b, c)
      One.n.a1.bigDecimal.not(bigDecimal1, bigDecimal2).query.get ==> List(c)
      One.n.a1.bigDecimal.not(bigDecimal2, bigDecimal3).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.bigDecimal.not(Seq(bigDecimal0)).query.get ==> List(a, b, c)
      One.n.a1.bigDecimal.not(Seq(bigDecimal1)).query.get ==> List(b, c)
      One.n.a1.bigDecimal.not(Seq(bigDecimal2)).query.get ==> List(a, c)
      One.n.a1.bigDecimal.not(Seq(bigDecimal3)).query.get ==> List(a, b)
      One.n.a1.bigDecimal.not(Seq(bigDecimal0, bigDecimal1)).query.get ==> List(b, c)
      One.n.a1.bigDecimal.not(Seq(bigDecimal1, bigDecimal2)).query.get ==> List(c)
      One.n.a1.bigDecimal.not(Seq(bigDecimal2, bigDecimal3)).query.get ==> List(a)
      One.n.a1.bigDecimal.not(Seq.empty[BigDecimal]).query.get ==> List(a, b, c)


      One.n.a1.bigDecimal.<(bigDecimal2).query.get ==> List(a)
      One.n.a1.bigDecimal.>(bigDecimal2).query.get ==> List(c)
      One.n.a1.bigDecimal.<=(bigDecimal2).query.get ==> List(a, b)
      One.n.a1.bigDecimal.>=(bigDecimal2).query.get ==> List(b, c)
    }


    "Tacit" - cardOne { implicit conn =>
      One.n.bigDecimal_?.insert(List(
        (1, Some(bigDecimal1)),
        (2, Some(bigDecimal2)),
        (3, Some(bigDecimal3)),
        (4, None)
      )).transact

      val a = 1
      val b = 2
      val c = 3
      val d = 4

      // No value asserted
      One.n.a1.bigDecimal_.apply().query.get ==> List(d)

      One.n.a1.bigDecimal_(bigDecimal0).query.get ==> List()
      One.n.a1.bigDecimal_.apply(bigDecimal1).query.get ==> List(a)
      One.n.a1.bigDecimal_(bigDecimal1, bigDecimal2).query.get ==> List(a, b)
      One.n.a1.bigDecimal_(bigDecimal1, bigDecimal0).query.get ==> List(a)
      // Same as
      One.n.a1.bigDecimal_(Seq(bigDecimal0)).query.get ==> List()
      One.n.a1.bigDecimal_(Seq(bigDecimal1)).query.get ==> List(a)
      One.n.a1.bigDecimal_(Seq(bigDecimal1, bigDecimal2)).query.get ==> List(a, b)
      One.n.a1.bigDecimal_(Seq(bigDecimal1, bigDecimal0)).query.get ==> List(a)
      One.n.a1.bigDecimal_(Seq.empty[BigDecimal]).query.get ==> List()


      One.n.a1.bigDecimal_.not(bigDecimal0).query.get ==> List(a, b, c)
      One.n.a1.bigDecimal_.not(bigDecimal1).query.get ==> List(b, c)
      One.n.a1.bigDecimal_.not(bigDecimal2).query.get ==> List(a, c)
      One.n.a1.bigDecimal_.not(bigDecimal3).query.get ==> List(a, b)
      One.n.a1.bigDecimal_.not(bigDecimal0, bigDecimal1).query.get ==> List(b, c)
      One.n.a1.bigDecimal_.not(bigDecimal1, bigDecimal2).query.get ==> List(c)
      One.n.a1.bigDecimal_.not(bigDecimal2, bigDecimal3).query.get ==> List(a)
      // Same as
      One.n.a1.bigDecimal_.not(Seq(bigDecimal0)).query.get ==> List(a, b, c)
      One.n.a1.bigDecimal_.not(Seq(bigDecimal1)).query.get ==> List(b, c)
      One.n.a1.bigDecimal_.not(Seq(bigDecimal2)).query.get ==> List(a, c)
      One.n.a1.bigDecimal_.not(Seq(bigDecimal3)).query.get ==> List(a, b)
      One.n.a1.bigDecimal_.not(Seq(bigDecimal0, bigDecimal1)).query.get ==> List(b, c)
      One.n.a1.bigDecimal_.not(Seq(bigDecimal1, bigDecimal2)).query.get ==> List(c)
      One.n.a1.bigDecimal_.not(Seq(bigDecimal2, bigDecimal3)).query.get ==> List(a)
      One.n.a1.bigDecimal_.not(Seq.empty[BigDecimal]).query.get ==> List(a, b, c)


      One.n.a1.bigDecimal_.<(bigDecimal2).query.get ==> List(a)
      One.n.a1.bigDecimal_.>(bigDecimal2).query.get ==> List(c)
      One.n.a1.bigDecimal_.<=(bigDecimal2).query.get ==> List(a, b)
      One.n.a1.bigDecimal_.>=(bigDecimal2).query.get ==> List(b, c)
    }


    "Optional" - cardOne { implicit conn =>
      val a = (1, Some(bigDecimal1))
      val b = (2, Some(bigDecimal2))
      val c = (3, Some(bigDecimal3))
      val x = (4, Option.empty[BigDecimal])

      One.n.bigDecimal_?.insert(List(a, b, c, x)).transact

      One.n.a1.bigDecimal_?(Some(bigDecimal0)).query.get ==> List()
      One.n.a1.bigDecimal_?.apply(Some(bigDecimal1)).query.get ==> List(a)

      One.n.a1.bigDecimal_?(Some(Seq(bigDecimal0))).query.get ==> List()
      One.n.a1.bigDecimal_?(Some(Seq(bigDecimal1))).query.get ==> List(a)
      One.n.a1.bigDecimal_?(Some(Seq(bigDecimal1, bigDecimal2))).query.get ==> List(a, b)
      One.n.a1.bigDecimal_?(Some(Seq(bigDecimal1, bigDecimal0))).query.get ==> List(a)
      One.n.a1.bigDecimal_?(Some(Seq.empty[BigDecimal])).query.get ==> List()

      One.n.a1.bigDecimal_?(Option.empty[BigDecimal]).query.get ==> List(x)
      One.n.a1.bigDecimal_?(Option.empty[Seq[BigDecimal]]).query.get ==> List(x)


      // For non-String cardinality-one attributes, `not` and `!=` have same semantics
      // "Not this value"
      One.n.a1.bigDecimal_?.not(Some(bigDecimal0)).query.get ==> List(a, b, c)
      One.n.a1.bigDecimal_?.not(Some(bigDecimal1)).query.get ==> List(b, c)
      One.n.a1.bigDecimal_?.not(Some(bigDecimal2)).query.get ==> List(a, c)
      One.n.a1.bigDecimal_?.not(Some(bigDecimal3)).query.get ==> List(a, b)
      // Same as
      One.n.a1.bigDecimal_?.not(Some(Seq(bigDecimal0))).query.get ==> List(a, b, c)
      One.n.a1.bigDecimal_?.not(Some(Seq(bigDecimal1))).query.get ==> List(b, c)
      One.n.a1.bigDecimal_?.not(Some(Seq(bigDecimal2))).query.get ==> List(a, c)
      One.n.a1.bigDecimal_?.not(Some(Seq(bigDecimal3))).query.get ==> List(a, b)

      // "Not this OR that value
      One.n.a1.bigDecimal_?.not(Some(Seq(bigDecimal0, bigDecimal1))).query.get ==> List(b, c)
      One.n.a1.bigDecimal_?.not(Some(Seq(bigDecimal1, bigDecimal2))).query.get ==> List(c)
      One.n.a1.bigDecimal_?.not(Some(Seq(bigDecimal2, bigDecimal3))).query.get ==> List(a)
      One.n.a1.bigDecimal_?.not(Some(Seq.empty[BigDecimal])).query.get ==> List(a, b, c)

      One.n.a1.bigDecimal_?.not(Option.empty[BigDecimal]).query.get ==> List(a, b, c)
      One.n.a1.bigDecimal_?.not(Option.empty[Seq[BigDecimal]]).query.get ==> List(a, b, c)


      One.n.a1.bigDecimal_?.<(Some(bigDecimal2)).query.get ==> List(a)
      One.n.a1.bigDecimal_?.>(Some(bigDecimal2)).query.get ==> List(c)
      One.n.a1.bigDecimal_?.<=(Some(bigDecimal2)).query.get ==> List(a, b)
      One.n.a1.bigDecimal_?.>=(Some(bigDecimal2)).query.get ==> List(b, c)

      // None can't be compared
      One.n.a1.bigDecimal_?.<(None).query.get ==> List()
      One.n.a1.bigDecimal_?.<=(None).query.get ==> List()
      One.n.a1.bigDecimal_?.>(None).query.get ==> List()
      One.n.a1.bigDecimal_?.>=(None).query.get ==> List()
    }
  }
}
