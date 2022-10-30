// GENERATED CODE ********************************
package molecule.db.datomic.test.expr

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Expr_String_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - cardOne { implicit conn =>
      val a = (1, string1)
      val b = (2, string2)
      val c = (3, string3)
      One.n.string.insert(List(a, b, c)).transact

      // Apply args to find matching values
      One.n.a1.string(string0).query.get ==> List()
      One.n.a1.string(string1).query.get ==> List(a)
      One.n.a1.string(string1, string2).query.get ==> List(a, b)
      One.n.a1.string(string1, string0).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.string(Seq(string0)).query.get ==> List()
      One.n.a1.string(Seq(string1)).query.get ==> List(a)
      One.n.a1.string(Seq(string1, string2)).query.get ==> List(a, b)
      One.n.a1.string(Seq(string1, string0)).query.get ==> List(a)
      One.n.a1.string(Seq.empty[String]).query.get ==> List()


      One.n.a1.string.not(string0).query.get ==> List(a, b, c)
      One.n.a1.string.not(string1).query.get ==> List(b, c)
      One.n.a1.string.not(string2).query.get ==> List(a, c)
      One.n.a1.string.not(string3).query.get ==> List(a, b)
      One.n.a1.string.not(string0, string1).query.get ==> List(b, c)
      One.n.a1.string.not(string1, string2).query.get ==> List(c)
      One.n.a1.string.not(string2, string3).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.string.not(Seq(string0)).query.get ==> List(a, b, c)
      One.n.a1.string.not(Seq(string1)).query.get ==> List(b, c)
      One.n.a1.string.not(Seq(string2)).query.get ==> List(a, c)
      One.n.a1.string.not(Seq(string3)).query.get ==> List(a, b)
      One.n.a1.string.not(Seq(string0, string1)).query.get ==> List(b, c)
      One.n.a1.string.not(Seq(string1, string2)).query.get ==> List(c)
      One.n.a1.string.not(Seq(string2, string3)).query.get ==> List(a)
      One.n.a1.string.not(Seq.empty[String]).query.get ==> List(a, b, c)


      One.n.a1.string.<(string2).query.get ==> List(a)
      One.n.a1.string.>(string2).query.get ==> List(c)
      One.n.a1.string.<=(string2).query.get ==> List(a, b)
      One.n.a1.string.>=(string2).query.get ==> List(b, c)
    }


    "Tacit" - cardOne { implicit conn =>
      One.n.string_?.insert(List(
        (1, Some(string1)),
        (2, Some(string2)),
        (3, Some(string3)),
        (4, None)
      )).transact

      val a = 1
      val b = 2
      val c = 3
      val d = 4

      // No value asserted
      One.n.a1.string_.apply().query.get ==> List(d)

      One.n.a1.string_(string0).query.get ==> List()
      One.n.a1.string_.apply(string1).query.get ==> List(a)
      One.n.a1.string_(string1, string2).query.get ==> List(a, b)
      One.n.a1.string_(string1, string0).query.get ==> List(a)
      // Same as
      One.n.a1.string_(Seq(string0)).query.get ==> List()
      One.n.a1.string_(Seq(string1)).query.get ==> List(a)
      One.n.a1.string_(Seq(string1, string2)).query.get ==> List(a, b)
      One.n.a1.string_(Seq(string1, string0)).query.get ==> List(a)
      One.n.a1.string_(Seq.empty[String]).query.get ==> List()


      One.n.a1.string_.not(string0).query.get ==> List(a, b, c)
      One.n.a1.string_.not(string1).query.get ==> List(b, c)
      One.n.a1.string_.not(string2).query.get ==> List(a, c)
      One.n.a1.string_.not(string3).query.get ==> List(a, b)
      One.n.a1.string_.not(string0, string1).query.get ==> List(b, c)
      One.n.a1.string_.not(string1, string2).query.get ==> List(c)
      One.n.a1.string_.not(string2, string3).query.get ==> List(a)
      // Same as
      One.n.a1.string_.not(Seq(string0)).query.get ==> List(a, b, c)
      One.n.a1.string_.not(Seq(string1)).query.get ==> List(b, c)
      One.n.a1.string_.not(Seq(string2)).query.get ==> List(a, c)
      One.n.a1.string_.not(Seq(string3)).query.get ==> List(a, b)
      One.n.a1.string_.not(Seq(string0, string1)).query.get ==> List(b, c)
      One.n.a1.string_.not(Seq(string1, string2)).query.get ==> List(c)
      One.n.a1.string_.not(Seq(string2, string3)).query.get ==> List(a)
      One.n.a1.string_.not(Seq.empty[String]).query.get ==> List(a, b, c)


      One.n.a1.string_.<(string2).query.get ==> List(a)
      One.n.a1.string_.>(string2).query.get ==> List(c)
      One.n.a1.string_.<=(string2).query.get ==> List(a, b)
      One.n.a1.string_.>=(string2).query.get ==> List(b, c)
    }


    "Optional" - cardOne { implicit conn =>
      val a = (1, Some(string1))
      val b = (2, Some(string2))
      val c = (3, Some(string3))
      val x = (4, Option.empty[String])

      One.n.string_?.insert(List(a, b, c, x)).transact

      One.n.a1.string_?(Some(string0)).query.get ==> List()
      One.n.a1.string_?.apply(Some(string1)).query.get ==> List(a)

      One.n.a1.string_?(Some(Seq(string0))).query.get ==> List()
      One.n.a1.string_?(Some(Seq(string1))).query.get ==> List(a)
      One.n.a1.string_?(Some(Seq(string1, string2))).query.get ==> List(a, b)
      One.n.a1.string_?(Some(Seq(string1, string0))).query.get ==> List(a)
      One.n.a1.string_?(Some(Seq.empty[String])).query.get ==> List()

      One.n.a1.string_?(Option.empty[String]).query.get ==> List(x)
      One.n.a1.string_?(Option.empty[Seq[String]]).query.get ==> List(x)


      // For non-String cardinality-one attributes, `not` and `!=` have same semantics
      // "Not this value"
      One.n.a1.string_?.not(Some(string0)).query.get ==> List(a, b, c)
      One.n.a1.string_?.not(Some(string1)).query.get ==> List(b, c)
      One.n.a1.string_?.not(Some(string2)).query.get ==> List(a, c)
      One.n.a1.string_?.not(Some(string3)).query.get ==> List(a, b)
      // Same as
      One.n.a1.string_?.not(Some(Seq(string0))).query.get ==> List(a, b, c)
      One.n.a1.string_?.not(Some(Seq(string1))).query.get ==> List(b, c)
      One.n.a1.string_?.not(Some(Seq(string2))).query.get ==> List(a, c)
      One.n.a1.string_?.not(Some(Seq(string3))).query.get ==> List(a, b)

      // "Not this OR that value
      One.n.a1.string_?.not(Some(Seq(string0, string1))).query.get ==> List(b, c)
      One.n.a1.string_?.not(Some(Seq(string1, string2))).query.get ==> List(c)
      One.n.a1.string_?.not(Some(Seq(string2, string3))).query.get ==> List(a)
      One.n.a1.string_?.not(Some(Seq.empty[String])).query.get ==> List(a, b, c)

      One.n.a1.string_?.not(Option.empty[String]).query.get ==> List(a, b, c)
      One.n.a1.string_?.not(Option.empty[Seq[String]]).query.get ==> List(a, b, c)


      One.n.a1.string_?.<(Some(string2)).query.get ==> List(a)
      One.n.a1.string_?.>(Some(string2)).query.get ==> List(c)
      One.n.a1.string_?.<=(Some(string2)).query.get ==> List(a, b)
      One.n.a1.string_?.>=(Some(string2)).query.get ==> List(b, c)

      // None can't be compared
      One.n.a1.string_?.<(None).query.get ==> List()
      One.n.a1.string_?.<=(None).query.get ==> List()
      One.n.a1.string_?.>(None).query.get ==> List()
      One.n.a1.string_?.>=(None).query.get ==> List()
    }
  }
}
