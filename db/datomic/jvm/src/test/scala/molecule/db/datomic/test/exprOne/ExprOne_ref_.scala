// GENERATED CODE ********************************
package molecule.db.datomic.test.exprOne

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object ExprOne_ref_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, ref1)
      val b = (2, ref2)
      val c = (3, ref3)
      Ns.i.ref.insert(List(a, b, c)).transact

      // Find all attribute values
      Ns.i.a1.ref.query.get ==> List(a, b, c)

      // Find value(s) matching
      Ns.i.a1.ref(ref0).query.get ==> List()
      Ns.i.a1.ref(ref1).query.get ==> List(a)
      Ns.i.a1.ref(Seq(ref0)).query.get ==> List()
      Ns.i.a1.ref(Seq(ref1)).query.get ==> List(a)
      // OR semantics for multiple args
      Ns.i.a1.ref(ref1, ref2).query.get ==> List(a, b)
      Ns.i.a1.ref(ref1, ref0).query.get ==> List(a)
      Ns.i.a1.ref(Seq(ref1, ref2)).query.get ==> List(a, b)
      Ns.i.a1.ref(Seq(ref1, ref0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      Ns.i.a1.ref(Seq.empty[Long]).query.get ==> List()

      // Find values not matching
      Ns.i.a1.ref.not(ref0).query.get ==> List(a, b, c)
      Ns.i.a1.ref.not(ref1).query.get ==> List(b, c)
      Ns.i.a1.ref.not(ref2).query.get ==> List(a, c)
      Ns.i.a1.ref.not(ref3).query.get ==> List(a, b)
      Ns.i.a1.ref.not(Seq(ref0)).query.get ==> List(a, b, c)
      Ns.i.a1.ref.not(Seq(ref1)).query.get ==> List(b, c)
      Ns.i.a1.ref.not(Seq(ref2)).query.get ==> List(a, c)
      Ns.i.a1.ref.not(Seq(ref3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      Ns.i.a1.ref.not(ref0, ref1).query.get ==> List(b, c)
      Ns.i.a1.ref.not(ref1, ref2).query.get ==> List(c)
      Ns.i.a1.ref.not(ref2, ref3).query.get ==> List(a)
      Ns.i.a1.ref.not(Seq(ref0, ref1)).query.get ==> List(b, c)
      Ns.i.a1.ref.not(Seq(ref1, ref2)).query.get ==> List(c)
      Ns.i.a1.ref.not(Seq(ref2, ref3)).query.get ==> List(a)
      // Empty Seq of negation args matches all values
      Ns.i.a1.ref.not(Seq.empty[Long]).query.get ==> List(a, b, c)

      // Find values in range
      Ns.i.a1.ref.<(ref2).query.get ==> List(a)
      Ns.i.a1.ref.>(ref2).query.get ==> List(c)
      Ns.i.a1.ref.<=(ref2).query.get ==> List(a, b)
      Ns.i.a1.ref.>=(ref2).query.get ==> List(b, c)
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      Ns.i.ref_?.insert(List(
        (a, Some(ref1)),
        (b, Some(ref2)),
        (c, Some(ref3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      Ns.i.a1.ref_.query.get ==> List(a, b, c)

      // Match non-asserted attribute (null)
      Ns.i.a1.ref_().query.get ==> List(x)

      // Match attribute values without returning them
      Ns.i.a1.ref_(ref0).query.get ==> List()
      Ns.i.a1.ref_(ref1).query.get ==> List(a)
      Ns.i.a1.ref_(Seq(ref0)).query.get ==> List()
      Ns.i.a1.ref_(Seq(ref1)).query.get ==> List(a)
      // OR semantics for multiple args
      Ns.i.a1.ref_(ref1, ref2).query.get ==> List(a, b)
      Ns.i.a1.ref_(ref1, ref0).query.get ==> List(a)
      Ns.i.a1.ref_(Seq(ref1, ref2)).query.get ==> List(a, b)
      Ns.i.a1.ref_(Seq(ref1, ref0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      Ns.i.a1.ref_(Seq.empty[Long]).query.get ==> List()

      // Match non-matching values without returning them
      Ns.i.a1.ref_.not(ref0).query.get ==> List(a, b, c)
      Ns.i.a1.ref_.not(ref1).query.get ==> List(b, c)
      Ns.i.a1.ref_.not(ref2).query.get ==> List(a, c)
      Ns.i.a1.ref_.not(ref3).query.get ==> List(a, b)
      Ns.i.a1.ref_.not(Seq(ref0)).query.get ==> List(a, b, c)
      Ns.i.a1.ref_.not(Seq(ref1)).query.get ==> List(b, c)
      Ns.i.a1.ref_.not(Seq(ref2)).query.get ==> List(a, c)
      Ns.i.a1.ref_.not(Seq(ref3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      Ns.i.a1.ref_.not(ref0, ref1).query.get ==> List(b, c)
      Ns.i.a1.ref_.not(ref1, ref2).query.get ==> List(c)
      Ns.i.a1.ref_.not(ref2, ref3).query.get ==> List(a)
      Ns.i.a1.ref_.not(Seq(ref0, ref1)).query.get ==> List(b, c)
      Ns.i.a1.ref_.not(Seq(ref1, ref2)).query.get ==> List(c)
      Ns.i.a1.ref_.not(Seq(ref2, ref3)).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      Ns.i.a1.ref_.not(Seq.empty[Long]).query.get ==> List(a, b, c)

      // Match value ranges without returning them
      Ns.i.a1.ref_.<(ref2).query.get ==> List(a)
      Ns.i.a1.ref_.>(ref2).query.get ==> List(c)
      Ns.i.a1.ref_.<=(ref2).query.get ==> List(a, b)
      Ns.i.a1.ref_.>=(ref2).query.get ==> List(b, c)
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(ref1))
      val b = (2, Some(ref2))
      val c = (3, Some(ref3))
      val x = (4, Option.empty[Long])
      Ns.i.ref_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      Ns.i.a1.ref_?.query.get ==> List(a, b, c, x)

      // Find optional values matching
      Ns.i.a1.ref_?(Some(ref0)).query.get ==> List()
      Ns.i.a1.ref_?(Some(ref1)).query.get ==> List(a)
      Ns.i.a1.ref_?(Some(Seq(ref0))).query.get ==> List()
      Ns.i.a1.ref_?(Some(Seq(ref1))).query.get ==> List(a)
      // OR semantics for Ses of multiple args
      Ns.i.a1.ref_?(Some(Seq(ref1, ref2))).query.get ==> List(a, b)
      Ns.i.a1.ref_?(Some(Seq(ref1, ref0))).query.get ==> List(a)
      // Empty Seq of args matches no values
      Ns.i.a1.ref_?(Some(Seq.empty[Long])).query.get ==> List()
      // None matches non-asserted/null values
      Ns.i.a1.ref_?(Option.empty[Long]).query.get ==> List(x)
      Ns.i.a1.ref_?(Option.empty[Seq[Long]]).query.get ==> List(x)

      // Find optional values not matching
      Ns.i.a1.ref_?.not(Some(ref0)).query.get ==> List(a, b, c)
      Ns.i.a1.ref_?.not(Some(ref1)).query.get ==> List(b, c)
      Ns.i.a1.ref_?.not(Some(ref2)).query.get ==> List(a, c)
      Ns.i.a1.ref_?.not(Some(ref3)).query.get ==> List(a, b)
      Ns.i.a1.ref_?.not(Some(Seq(ref0))).query.get ==> List(a, b, c)
      Ns.i.a1.ref_?.not(Some(Seq(ref1))).query.get ==> List(b, c)
      Ns.i.a1.ref_?.not(Some(Seq(ref2))).query.get ==> List(a, c)
      Ns.i.a1.ref_?.not(Some(Seq(ref3))).query.get ==> List(a, b)
      // OR semantics for multiple negation args
      Ns.i.a1.ref_?.not(Some(Seq(ref0, ref1))).query.get ==> List(b, c)
      Ns.i.a1.ref_?.not(Some(Seq(ref1, ref2))).query.get ==> List(c)
      Ns.i.a1.ref_?.not(Some(Seq(ref2, ref3))).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      Ns.i.a1.ref_?.not(Some(Seq.empty[Long])).query.get ==> List(a, b, c)
      // None matches all asserted values (non-null)
      Ns.i.a1.ref_?.not(Option.empty[Long]).query.get ==> List(a, b, c)
      Ns.i.a1.ref_?.not(Option.empty[Seq[Long]]).query.get ==> List(a, b, c)

      // Find optional values in range
      Ns.i.a1.ref_?.<(Some(ref2)).query.get ==> List(a)
      Ns.i.a1.ref_?.>(Some(ref2)).query.get ==> List(c)
      Ns.i.a1.ref_?.<=(Some(ref2)).query.get ==> List(a, b)
      Ns.i.a1.ref_?.>=(Some(ref2)).query.get ==> List(b, c)
      // None can't be compared and returns empty result
      Ns.i.a1.ref_?.<(None).query.get ==> List()
      Ns.i.a1.ref_?.<=(None).query.get ==> List()
      Ns.i.a1.ref_?.>(None).query.get ==> List()
      Ns.i.a1.ref_?.>=(None).query.get ==> List()
    }
  }
}