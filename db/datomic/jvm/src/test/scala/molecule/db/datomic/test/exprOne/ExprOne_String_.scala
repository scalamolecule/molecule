// GENERATED CODE ********************************
package molecule.db.datomic.test.exprOne

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object ExprOne_String_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, string1)
      val b = (2, string2)
      val c = (3, string3)
      Ns.i.string.insert(List(a, b, c)).transact

      // Find all attribute values
      Ns.i.a1.string.query.get ==> List(a, b, c)

      // Find value(s) matching
      Ns.i.a1.string(string0).query.get ==> List()
      Ns.i.a1.string(string1).query.get ==> List(a)
      Ns.i.a1.string(Seq(string0)).query.get ==> List()
      Ns.i.a1.string(Seq(string1)).query.get ==> List(a)
      // OR semantics for multiple args
      Ns.i.a1.string(string1, string2).query.get ==> List(a, b)
      Ns.i.a1.string(string1, string0).query.get ==> List(a)
      Ns.i.a1.string(Seq(string1, string2)).query.get ==> List(a, b)
      Ns.i.a1.string(Seq(string1, string0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      Ns.i.a1.string(Seq.empty[String]).query.get ==> List()

      // Find values not matching
      Ns.i.a1.string.not(string0).query.get ==> List(a, b, c)
      Ns.i.a1.string.not(string1).query.get ==> List(b, c)
      Ns.i.a1.string.not(string2).query.get ==> List(a, c)
      Ns.i.a1.string.not(string3).query.get ==> List(a, b)
      Ns.i.a1.string.not(Seq(string0)).query.get ==> List(a, b, c)
      Ns.i.a1.string.not(Seq(string1)).query.get ==> List(b, c)
      Ns.i.a1.string.not(Seq(string2)).query.get ==> List(a, c)
      Ns.i.a1.string.not(Seq(string3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      Ns.i.a1.string.not(string0, string1).query.get ==> List(b, c)
      Ns.i.a1.string.not(string1, string2).query.get ==> List(c)
      Ns.i.a1.string.not(string2, string3).query.get ==> List(a)
      Ns.i.a1.string.not(Seq(string0, string1)).query.get ==> List(b, c)
      Ns.i.a1.string.not(Seq(string1, string2)).query.get ==> List(c)
      Ns.i.a1.string.not(Seq(string2, string3)).query.get ==> List(a)
      // Empty Seq of negation args matches all values
      Ns.i.a1.string.not(Seq.empty[String]).query.get ==> List(a, b, c)

      // Find values in range
      Ns.i.a1.string.<(string2).query.get ==> List(a)
      Ns.i.a1.string.>(string2).query.get ==> List(c)
      Ns.i.a1.string.<=(string2).query.get ==> List(a, b)
      Ns.i.a1.string.>=(string2).query.get ==> List(b, c)
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      Ns.i.string_?.insert(List(
        (a, Some(string1)),
        (b, Some(string2)),
        (c, Some(string3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      Ns.i.a1.string_.query.get ==> List(a, b, c)

      // Match non-asserted attribute (null)
      Ns.i.a1.string_().query.get ==> List(x)

      // Match attribute values without returning them
      Ns.i.a1.string_(string0).query.get ==> List()
      Ns.i.a1.string_(string1).query.get ==> List(a)
      Ns.i.a1.string_(Seq(string0)).query.get ==> List()
      Ns.i.a1.string_(Seq(string1)).query.get ==> List(a)
      // OR semantics for multiple args
      Ns.i.a1.string_(string1, string2).query.get ==> List(a, b)
      Ns.i.a1.string_(string1, string0).query.get ==> List(a)
      Ns.i.a1.string_(Seq(string1, string2)).query.get ==> List(a, b)
      Ns.i.a1.string_(Seq(string1, string0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      Ns.i.a1.string_(Seq.empty[String]).query.get ==> List()

      // Match non-matching values without returning them
      Ns.i.a1.string_.not(string0).query.get ==> List(a, b, c)
      Ns.i.a1.string_.not(string1).query.get ==> List(b, c)
      Ns.i.a1.string_.not(string2).query.get ==> List(a, c)
      Ns.i.a1.string_.not(string3).query.get ==> List(a, b)
      Ns.i.a1.string_.not(Seq(string0)).query.get ==> List(a, b, c)
      Ns.i.a1.string_.not(Seq(string1)).query.get ==> List(b, c)
      Ns.i.a1.string_.not(Seq(string2)).query.get ==> List(a, c)
      Ns.i.a1.string_.not(Seq(string3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      Ns.i.a1.string_.not(string0, string1).query.get ==> List(b, c)
      Ns.i.a1.string_.not(string1, string2).query.get ==> List(c)
      Ns.i.a1.string_.not(string2, string3).query.get ==> List(a)
      Ns.i.a1.string_.not(Seq(string0, string1)).query.get ==> List(b, c)
      Ns.i.a1.string_.not(Seq(string1, string2)).query.get ==> List(c)
      Ns.i.a1.string_.not(Seq(string2, string3)).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      Ns.i.a1.string_.not(Seq.empty[String]).query.get ==> List(a, b, c)

      // Match value ranges without returning them
      Ns.i.a1.string_.<(string2).query.get ==> List(a)
      Ns.i.a1.string_.>(string2).query.get ==> List(c)
      Ns.i.a1.string_.<=(string2).query.get ==> List(a, b)
      Ns.i.a1.string_.>=(string2).query.get ==> List(b, c)
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(string1))
      val b = (2, Some(string2))
      val c = (3, Some(string3))
      val x = (4, Option.empty[String])
      Ns.i.string_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      Ns.i.a1.string_?.query.get ==> List(a, b, c, x)

      // Find optional values matching
      Ns.i.a1.string_?(Some(string0)).query.get ==> List()
      Ns.i.a1.string_?(Some(string1)).query.get ==> List(a)
      Ns.i.a1.string_?(Some(Seq(string0))).query.get ==> List()
      Ns.i.a1.string_?(Some(Seq(string1))).query.get ==> List(a)
      // OR semantics for Ses of multiple args
      Ns.i.a1.string_?(Some(Seq(string1, string2))).query.get ==> List(a, b)
      Ns.i.a1.string_?(Some(Seq(string1, string0))).query.get ==> List(a)
      // Empty Seq of args matches no values
      Ns.i.a1.string_?(Some(Seq.empty[String])).query.get ==> List()
      // None matches non-asserted/null values
      Ns.i.a1.string_?(Option.empty[String]).query.get ==> List(x)
      Ns.i.a1.string_?(Option.empty[Seq[String]]).query.get ==> List(x)

      // Find optional values not matching
      Ns.i.a1.string_?.not(Some(string0)).query.get ==> List(a, b, c)
      Ns.i.a1.string_?.not(Some(string1)).query.get ==> List(b, c)
      Ns.i.a1.string_?.not(Some(string2)).query.get ==> List(a, c)
      Ns.i.a1.string_?.not(Some(string3)).query.get ==> List(a, b)
      Ns.i.a1.string_?.not(Some(Seq(string0))).query.get ==> List(a, b, c)
      Ns.i.a1.string_?.not(Some(Seq(string1))).query.get ==> List(b, c)
      Ns.i.a1.string_?.not(Some(Seq(string2))).query.get ==> List(a, c)
      Ns.i.a1.string_?.not(Some(Seq(string3))).query.get ==> List(a, b)
      // OR semantics for multiple negation args
      Ns.i.a1.string_?.not(Some(Seq(string0, string1))).query.get ==> List(b, c)
      Ns.i.a1.string_?.not(Some(Seq(string1, string2))).query.get ==> List(c)
      Ns.i.a1.string_?.not(Some(Seq(string2, string3))).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      Ns.i.a1.string_?.not(Some(Seq.empty[String])).query.get ==> List(a, b, c)
      // None matches all asserted values (non-null)
      Ns.i.a1.string_?.not(Option.empty[String]).query.get ==> List(a, b, c)
      Ns.i.a1.string_?.not(Option.empty[Seq[String]]).query.get ==> List(a, b, c)

      // Find optional values in range
      Ns.i.a1.string_?.<(Some(string2)).query.get ==> List(a)
      Ns.i.a1.string_?.>(Some(string2)).query.get ==> List(c)
      Ns.i.a1.string_?.<=(Some(string2)).query.get ==> List(a, b)
      Ns.i.a1.string_?.>=(Some(string2)).query.get ==> List(b, c)
      // None can't be compared and returns empty result
      Ns.i.a1.string_?.<(None).query.get ==> List()
      Ns.i.a1.string_?.<=(None).query.get ==> List()
      Ns.i.a1.string_?.>(None).query.get ==> List()
      Ns.i.a1.string_?.>=(None).query.get ==> List()
    }
  }
}
