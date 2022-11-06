// GENERATED CODE ********************************
package molecule.db.datomic.test.exprOne

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object ExprOne_Char_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - cardOne { implicit conn =>
      val a = (1, char1)
      val b = (2, char2)
      val c = (3, char3)
      NsOne.n.char.insert(List(a, b, c)).transact

      // Find all attribute values
      NsOne.n.a1.char.query.get ==> List(a, b, c)

      // Find value(s) matching
      NsOne.n.a1.char(char0).query.get ==> List()
      NsOne.n.a1.char(char1).query.get ==> List(a)
      NsOne.n.a1.char(Seq(char0)).query.get ==> List()
      NsOne.n.a1.char(Seq(char1)).query.get ==> List(a)
      // OR semantics for multiple args
      NsOne.n.a1.char(char1, char2).query.get ==> List(a, b)
      NsOne.n.a1.char(char1, char0).query.get ==> List(a)
      NsOne.n.a1.char(Seq(char1, char2)).query.get ==> List(a, b)
      NsOne.n.a1.char(Seq(char1, char0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      NsOne.n.a1.char(Seq.empty[Char]).query.get ==> List()

      // Find values not matching
      NsOne.n.a1.char.not(char0).query.get ==> List(a, b, c)
      NsOne.n.a1.char.not(char1).query.get ==> List(b, c)
      NsOne.n.a1.char.not(char2).query.get ==> List(a, c)
      NsOne.n.a1.char.not(char3).query.get ==> List(a, b)
      NsOne.n.a1.char.not(Seq(char0)).query.get ==> List(a, b, c)
      NsOne.n.a1.char.not(Seq(char1)).query.get ==> List(b, c)
      NsOne.n.a1.char.not(Seq(char2)).query.get ==> List(a, c)
      NsOne.n.a1.char.not(Seq(char3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      NsOne.n.a1.char.not(char0, char1).query.get ==> List(b, c)
      NsOne.n.a1.char.not(char1, char2).query.get ==> List(c)
      NsOne.n.a1.char.not(char2, char3).query.get ==> List(a)
      NsOne.n.a1.char.not(Seq(char0, char1)).query.get ==> List(b, c)
      NsOne.n.a1.char.not(Seq(char1, char2)).query.get ==> List(c)
      NsOne.n.a1.char.not(Seq(char2, char3)).query.get ==> List(a)
      // Empty Seq of negation args matches all values
      NsOne.n.a1.char.not(Seq.empty[Char]).query.get ==> List(a, b, c)

      // Find values in range
      NsOne.n.a1.char.<(char2).query.get ==> List(a)
      NsOne.n.a1.char.>(char2).query.get ==> List(c)
      NsOne.n.a1.char.<=(char2).query.get ==> List(a, b)
      NsOne.n.a1.char.>=(char2).query.get ==> List(b, c)
    }


    "Tacit" - cardOne { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      NsOne.n.char_?.insert(List(
        (a, Some(char1)),
        (b, Some(char2)),
        (c, Some(char3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      NsOne.n.a1.char_.query.get ==> List(a, b, c)

      // Match non-asserted attribute (null)
      NsOne.n.a1.char_().query.get ==> List(x)

      // Match attribute values without returning them
      NsOne.n.a1.char_(char0).query.get ==> List()
      NsOne.n.a1.char_(char1).query.get ==> List(a)
      NsOne.n.a1.char_(Seq(char0)).query.get ==> List()
      NsOne.n.a1.char_(Seq(char1)).query.get ==> List(a)
      // OR semantics for multiple args
      NsOne.n.a1.char_(char1, char2).query.get ==> List(a, b)
      NsOne.n.a1.char_(char1, char0).query.get ==> List(a)
      NsOne.n.a1.char_(Seq(char1, char2)).query.get ==> List(a, b)
      NsOne.n.a1.char_(Seq(char1, char0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      NsOne.n.a1.char_(Seq.empty[Char]).query.get ==> List()

      // Match non-matching values without returning them
      NsOne.n.a1.char_.not(char0).query.get ==> List(a, b, c)
      NsOne.n.a1.char_.not(char1).query.get ==> List(b, c)
      NsOne.n.a1.char_.not(char2).query.get ==> List(a, c)
      NsOne.n.a1.char_.not(char3).query.get ==> List(a, b)
      NsOne.n.a1.char_.not(Seq(char0)).query.get ==> List(a, b, c)
      NsOne.n.a1.char_.not(Seq(char1)).query.get ==> List(b, c)
      NsOne.n.a1.char_.not(Seq(char2)).query.get ==> List(a, c)
      NsOne.n.a1.char_.not(Seq(char3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      NsOne.n.a1.char_.not(char0, char1).query.get ==> List(b, c)
      NsOne.n.a1.char_.not(char1, char2).query.get ==> List(c)
      NsOne.n.a1.char_.not(char2, char3).query.get ==> List(a)
      NsOne.n.a1.char_.not(Seq(char0, char1)).query.get ==> List(b, c)
      NsOne.n.a1.char_.not(Seq(char1, char2)).query.get ==> List(c)
      NsOne.n.a1.char_.not(Seq(char2, char3)).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      NsOne.n.a1.char_.not(Seq.empty[Char]).query.get ==> List(a, b, c)

      // Match value ranges without returning them
      NsOne.n.a1.char_.<(char2).query.get ==> List(a)
      NsOne.n.a1.char_.>(char2).query.get ==> List(c)
      NsOne.n.a1.char_.<=(char2).query.get ==> List(a, b)
      NsOne.n.a1.char_.>=(char2).query.get ==> List(b, c)
    }


    "Optional" - cardOne { implicit conn =>
      val a = (1, Some(char1))
      val b = (2, Some(char2))
      val c = (3, Some(char3))
      val x = (4, Option.empty[Char])
      NsOne.n.char_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      NsOne.n.a1.char_?.query.get ==> List(a, b, c, x)

      // Find optional values matching
      NsOne.n.a1.char_?(Some(char0)).query.get ==> List()
      NsOne.n.a1.char_?(Some(char1)).query.get ==> List(a)
      NsOne.n.a1.char_?(Some(Seq(char0))).query.get ==> List()
      NsOne.n.a1.char_?(Some(Seq(char1))).query.get ==> List(a)
      // OR semantics for Ses of multiple args
      NsOne.n.a1.char_?(Some(Seq(char1, char2))).query.get ==> List(a, b)
      NsOne.n.a1.char_?(Some(Seq(char1, char0))).query.get ==> List(a)
      // Empty Seq of args matches no values
      NsOne.n.a1.char_?(Some(Seq.empty[Char])).query.get ==> List()
      // None matches non-asserted/null values
      NsOne.n.a1.char_?(Option.empty[Char]).query.get ==> List(x)
      NsOne.n.a1.char_?(Option.empty[Seq[Char]]).query.get ==> List(x)

      // Find optional values not matching
      NsOne.n.a1.char_?.not(Some(char0)).query.get ==> List(a, b, c)
      NsOne.n.a1.char_?.not(Some(char1)).query.get ==> List(b, c)
      NsOne.n.a1.char_?.not(Some(char2)).query.get ==> List(a, c)
      NsOne.n.a1.char_?.not(Some(char3)).query.get ==> List(a, b)
      NsOne.n.a1.char_?.not(Some(Seq(char0))).query.get ==> List(a, b, c)
      NsOne.n.a1.char_?.not(Some(Seq(char1))).query.get ==> List(b, c)
      NsOne.n.a1.char_?.not(Some(Seq(char2))).query.get ==> List(a, c)
      NsOne.n.a1.char_?.not(Some(Seq(char3))).query.get ==> List(a, b)
      // OR semantics for multiple negation args
      NsOne.n.a1.char_?.not(Some(Seq(char0, char1))).query.get ==> List(b, c)
      NsOne.n.a1.char_?.not(Some(Seq(char1, char2))).query.get ==> List(c)
      NsOne.n.a1.char_?.not(Some(Seq(char2, char3))).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      NsOne.n.a1.char_?.not(Some(Seq.empty[Char])).query.get ==> List(a, b, c)
      // None matches all asserted values (non-null)
      NsOne.n.a1.char_?.not(Option.empty[Char]).query.get ==> List(a, b, c)
      NsOne.n.a1.char_?.not(Option.empty[Seq[Char]]).query.get ==> List(a, b, c)

      // Find optional values in range
      NsOne.n.a1.char_?.<(Some(char2)).query.get ==> List(a)
      NsOne.n.a1.char_?.>(Some(char2)).query.get ==> List(c)
      NsOne.n.a1.char_?.<=(Some(char2)).query.get ==> List(a, b)
      NsOne.n.a1.char_?.>=(Some(char2)).query.get ==> List(b, c)
      // None can't be compared and returns empty result
      NsOne.n.a1.char_?.<(None).query.get ==> List()
      NsOne.n.a1.char_?.<=(None).query.get ==> List()
      NsOne.n.a1.char_?.>(None).query.get ==> List()
      NsOne.n.a1.char_?.>=(None).query.get ==> List()
    }
  }
}
