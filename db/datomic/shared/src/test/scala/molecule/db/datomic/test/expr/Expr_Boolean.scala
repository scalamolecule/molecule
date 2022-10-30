package molecule.db.datomic.test.expr

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Expr_Boolean extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - cardOne { implicit conn =>
      val a = (1, true)
      val b = (2, false)

      One.n.boolean.insert.apply(List(a, b)).transact

      One.n.a1.boolean.apply(true).query.get ==> List(a)
      One.n.a1.boolean.apply(false).query.get ==> List(b)
      One.n.a1.boolean.apply(true, false).query.get ==> List(a, b)
      // Same as
      One.n.a1.boolean.apply(Seq(true)).query.get ==> List(a)
      One.n.a1.boolean.apply(Seq(false)).query.get ==> List(b)
      One.n.a1.boolean.apply(Seq(true, false)).query.get ==> List(a, b)
      One.n.a1.boolean.apply(Seq.empty[Boolean]).query.get ==> List()


      One.n.a1.boolean.not(true).query.get ==> List(b)
      One.n.a1.boolean.not(false).query.get ==> List(a)
      One.n.a1.boolean.not(true, false).query.get ==> List()
      // Same as
      One.n.a1.boolean.not(Seq(true)).query.get ==> List(b)
      One.n.a1.boolean.not(Seq(false)).query.get ==> List(a)
      One.n.a1.boolean.not(Seq(true, false)).query.get ==> List()

      One.n.a1.boolean.<(true).query.get ==> List(b)
      One.n.a1.boolean.>(true).query.get ==> List()
      One.n.a1.boolean.<=(true).query.get ==> List(a, b)
      One.n.a1.boolean.>=(true).query.get ==> List(a)
    }


    "Tacit" - cardOne { implicit conn =>
      One.n.boolean_?.insert(List(
        (1, Some(true)),
        (2, Some(false)),
        (3, None)
      )).transact

      val a = 1
      val b = 2
      val d = 3

      // No value asserted
      One.n.a1.boolean_.apply().query.get ==> List(d)

      One.n.a1.boolean_.apply(true).query.get ==> List(a)
      One.n.a1.boolean_.apply(false).query.get ==> List(b)
      One.n.a1.boolean_.apply(true, false).query.get ==> List(a, b)
      // Same as
      One.n.a1.boolean_.apply(Seq(true)).query.get ==> List(a)
      One.n.a1.boolean_.apply(Seq(false)).query.get ==> List(b)
      One.n.a1.boolean_.apply(Seq(true, false)).query.get ==> List(a, b)
      One.n.a1.boolean_.apply(Seq.empty[Boolean]).query.get ==> List()


      One.n.a1.boolean_.not(true).query.get ==> List(b)
      One.n.a1.boolean_.not(false).query.get ==> List(a)
      One.n.a1.boolean_.not(true, false).query.get ==> List()
      // Same as
      One.n.a1.boolean_.not(Seq(true)).query.get ==> List(b)
      One.n.a1.boolean_.not(Seq(false)).query.get ==> List(a)
      One.n.a1.boolean_.not(Seq(true, false)).query.get ==> List()


      One.n.a1.boolean_.<(true).query.get ==> List(b)
      One.n.a1.boolean_.>(true).query.get ==> List()
      One.n.a1.boolean_.<=(true).query.get ==> List(a, b)
      One.n.a1.boolean_.>=(true).query.get ==> List(a)
    }


    "Optional" - cardOne { implicit conn =>
      val a = (1, Some(true))
      val b = (2, Some(false))
      val x = (4, Option.empty[Boolean])

      One.n.boolean_?.insert(List(a, b, x)).transact

      One.n.a1.boolean_?.apply(Some(true)).query.get ==> List(a)
      One.n.a1.boolean_?.apply(Some(false)).query.get ==> List(b)

      One.n.a1.boolean_?.apply(Some(Seq(true))).query.get ==> List(a)
      One.n.a1.boolean_?.apply(Some(Seq(false))).query.get ==> List(b)
      One.n.a1.boolean_?.apply(Some(Seq(true, false))).query.get ==> List(a, b)
      One.n.a1.boolean_?.apply(Some(Seq.empty[Boolean])).query.get ==> List()

      One.n.a1.boolean_?.apply(Option.empty[Boolean]).query.get ==> List(x)
      One.n.a1.boolean_?.apply(Option.empty[Seq[Boolean]]).query.get ==> List(x)


      // For non-String cardinality-one attributes, `not` and `!=` have same semantics
      // "Not this value"
      One.n.a1.boolean_?.not(Some(true)).query.get ==> List(b)
      One.n.a1.boolean_?.not(Some(false)).query.get ==> List(a)
      // Same as
      One.n.a1.boolean_?.not(Some(Seq(true))).query.get ==> List(b)
      One.n.a1.boolean_?.not(Some(Seq(false))).query.get ==> List(a)

      // "Not this OR that value
      One.n.a1.boolean_?.not(Some(Seq(true, false))).query.get ==> List()

      One.n.a1.boolean_?.not(Option.empty[Boolean]).query.get ==> List(a, b)
      One.n.a1.boolean_?.not(Option.empty[Seq[Boolean]]).query.get ==> List(a, b)


      One.n.a1.boolean_?.<(Some(true)).query.get ==> List(b)
      One.n.a1.boolean_?.<=(Some(true)).query.get ==> List(a, b)
      One.n.a1.boolean_?.>(Some(true)).query.get ==> List()
      One.n.a1.boolean_?.>=(Some(true)).query.get ==> List(a)

      // None can't be compared
      One.n.a1.boolean_?.<(None).query.get ==> List()
      One.n.a1.boolean_?.>(None).query.get ==> List()
      One.n.a1.boolean_?.<=(None).query.get ==> List()
      One.n.a1.boolean_?.>=(None).query.get ==> List()
    }
  }
}