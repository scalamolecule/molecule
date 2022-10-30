// GENERATED CODE ********************************
package molecule.db.datomic.test.expr

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Expr_Char_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - cardOne { implicit conn =>
      val a = (1, char1)
      val b = (2, char2)
      val c = (3, char3)
      One.n.char.insert(List(a, b, c)).transact

      // Apply args to find matching values
      One.n.a1.char(char0).query.get ==> List()
      One.n.a1.char(char1).query.get ==> List(a)
      One.n.a1.char(char1, char2).query.get ==> List(a, b)
      One.n.a1.char(char1, char0).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.char(Seq(char0)).query.get ==> List()
      One.n.a1.char(Seq(char1)).query.get ==> List(a)
      One.n.a1.char(Seq(char1, char2)).query.get ==> List(a, b)
      One.n.a1.char(Seq(char1, char0)).query.get ==> List(a)
      One.n.a1.char(Seq.empty[Char]).query.get ==> List()


      One.n.a1.char.not(char0).query.get ==> List(a, b, c)
      One.n.a1.char.not(char1).query.get ==> List(b, c)
      One.n.a1.char.not(char2).query.get ==> List(a, c)
      One.n.a1.char.not(char3).query.get ==> List(a, b)
      One.n.a1.char.not(char0, char1).query.get ==> List(b, c)
      One.n.a1.char.not(char1, char2).query.get ==> List(c)
      One.n.a1.char.not(char2, char3).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.char.not(Seq(char0)).query.get ==> List(a, b, c)
      One.n.a1.char.not(Seq(char1)).query.get ==> List(b, c)
      One.n.a1.char.not(Seq(char2)).query.get ==> List(a, c)
      One.n.a1.char.not(Seq(char3)).query.get ==> List(a, b)
      One.n.a1.char.not(Seq(char0, char1)).query.get ==> List(b, c)
      One.n.a1.char.not(Seq(char1, char2)).query.get ==> List(c)
      One.n.a1.char.not(Seq(char2, char3)).query.get ==> List(a)
      One.n.a1.char.not(Seq.empty[Char]).query.get ==> List(a, b, c)


      One.n.a1.char.<(char2).query.get ==> List(a)
      One.n.a1.char.>(char2).query.get ==> List(c)
      One.n.a1.char.<=(char2).query.get ==> List(a, b)
      One.n.a1.char.>=(char2).query.get ==> List(b, c)
    }


    "Tacit" - cardOne { implicit conn =>
      One.n.char_?.insert(List(
        (1, Some(char1)),
        (2, Some(char2)),
        (3, Some(char3)),
        (4, None)
      )).transact

      val a = 1
      val b = 2
      val c = 3
      val d = 4

      // No value asserted
      One.n.a1.char_.apply().query.get ==> List(d)

      One.n.a1.char_(char0).query.get ==> List()
      One.n.a1.char_.apply(char1).query.get ==> List(a)
      One.n.a1.char_(char1, char2).query.get ==> List(a, b)
      One.n.a1.char_(char1, char0).query.get ==> List(a)
      // Same as
      One.n.a1.char_(Seq(char0)).query.get ==> List()
      One.n.a1.char_(Seq(char1)).query.get ==> List(a)
      One.n.a1.char_(Seq(char1, char2)).query.get ==> List(a, b)
      One.n.a1.char_(Seq(char1, char0)).query.get ==> List(a)
      One.n.a1.char_(Seq.empty[Char]).query.get ==> List()


      One.n.a1.char_.not(char0).query.get ==> List(a, b, c)
      One.n.a1.char_.not(char1).query.get ==> List(b, c)
      One.n.a1.char_.not(char2).query.get ==> List(a, c)
      One.n.a1.char_.not(char3).query.get ==> List(a, b)
      One.n.a1.char_.not(char0, char1).query.get ==> List(b, c)
      One.n.a1.char_.not(char1, char2).query.get ==> List(c)
      One.n.a1.char_.not(char2, char3).query.get ==> List(a)
      // Same as
      One.n.a1.char_.not(Seq(char0)).query.get ==> List(a, b, c)
      One.n.a1.char_.not(Seq(char1)).query.get ==> List(b, c)
      One.n.a1.char_.not(Seq(char2)).query.get ==> List(a, c)
      One.n.a1.char_.not(Seq(char3)).query.get ==> List(a, b)
      One.n.a1.char_.not(Seq(char0, char1)).query.get ==> List(b, c)
      One.n.a1.char_.not(Seq(char1, char2)).query.get ==> List(c)
      One.n.a1.char_.not(Seq(char2, char3)).query.get ==> List(a)
      One.n.a1.char_.not(Seq.empty[Char]).query.get ==> List(a, b, c)


      One.n.a1.char_.<(char2).query.get ==> List(a)
      One.n.a1.char_.>(char2).query.get ==> List(c)
      One.n.a1.char_.<=(char2).query.get ==> List(a, b)
      One.n.a1.char_.>=(char2).query.get ==> List(b, c)
    }


    "Optional" - cardOne { implicit conn =>
      val a = (1, Some(char1))
      val b = (2, Some(char2))
      val c = (3, Some(char3))
      val x = (4, Option.empty[Char])

      One.n.char_?.insert(List(a, b, c, x)).transact

      One.n.a1.char_?(Some(char0)).query.get ==> List()
      One.n.a1.char_?.apply(Some(char1)).query.get ==> List(a)

      One.n.a1.char_?(Some(Seq(char0))).query.get ==> List()
      One.n.a1.char_?(Some(Seq(char1))).query.get ==> List(a)
      One.n.a1.char_?(Some(Seq(char1, char2))).query.get ==> List(a, b)
      One.n.a1.char_?(Some(Seq(char1, char0))).query.get ==> List(a)
      One.n.a1.char_?(Some(Seq.empty[Char])).query.get ==> List()

      One.n.a1.char_?(Option.empty[Char]).query.get ==> List(x)
      One.n.a1.char_?(Option.empty[Seq[Char]]).query.get ==> List(x)


      // For non-String cardinality-one attributes, `not` and `!=` have same semantics
      // "Not this value"
      One.n.a1.char_?.not(Some(char0)).query.get ==> List(a, b, c)
      One.n.a1.char_?.not(Some(char1)).query.get ==> List(b, c)
      One.n.a1.char_?.not(Some(char2)).query.get ==> List(a, c)
      One.n.a1.char_?.not(Some(char3)).query.get ==> List(a, b)
      // Same as
      One.n.a1.char_?.not(Some(Seq(char0))).query.get ==> List(a, b, c)
      One.n.a1.char_?.not(Some(Seq(char1))).query.get ==> List(b, c)
      One.n.a1.char_?.not(Some(Seq(char2))).query.get ==> List(a, c)
      One.n.a1.char_?.not(Some(Seq(char3))).query.get ==> List(a, b)

      // "Not this OR that value
      One.n.a1.char_?.not(Some(Seq(char0, char1))).query.get ==> List(b, c)
      One.n.a1.char_?.not(Some(Seq(char1, char2))).query.get ==> List(c)
      One.n.a1.char_?.not(Some(Seq(char2, char3))).query.get ==> List(a)
      One.n.a1.char_?.not(Some(Seq.empty[Char])).query.get ==> List(a, b, c)

      One.n.a1.char_?.not(Option.empty[Char]).query.get ==> List(a, b, c)
      One.n.a1.char_?.not(Option.empty[Seq[Char]]).query.get ==> List(a, b, c)


      One.n.a1.char_?.<(Some(char2)).query.get ==> List(a)
      One.n.a1.char_?.>(Some(char2)).query.get ==> List(c)
      One.n.a1.char_?.<=(Some(char2)).query.get ==> List(a, b)
      One.n.a1.char_?.>=(Some(char2)).query.get ==> List(b, c)

      // None can't be compared
      One.n.a1.char_?.<(None).query.get ==> List()
      One.n.a1.char_?.<=(None).query.get ==> List()
      One.n.a1.char_?.>(None).query.get ==> List()
      One.n.a1.char_?.>=(None).query.get ==> List()
    }
  }
}
