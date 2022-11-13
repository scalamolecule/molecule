package molecule.db.datomic.test.exprOne

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object ExprOne_Boolean extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, true)
      val b = (2, false)
      Ns.n.boolean.insert(List(a, b)).transact

      // Find all attribute values
      Ns.n.a1.boolean.query.get ==> List(a, b)

      // Find value(s) matching
      Ns.n.a1.boolean(true).query.get ==> List(a)
      Ns.n.a1.boolean(false).query.get ==> List(b)
      Ns.n.a1.boolean(Seq(true)).query.get ==> List(a)
      Ns.n.a1.boolean(Seq(false)).query.get ==> List(b)
      // OR semantics for multiple args (for Boolean meaning all)
      Ns.n.a1.boolean(true, false).query.get ==> List(a, b)
      Ns.n.a1.boolean(Seq(true, false)).query.get ==> List(a, b)
      // Empty Seq of args matches no values
      Ns.n.a1.boolean(Seq.empty[Boolean]).query.get ==> List()

      // Find values not matching
      Ns.n.a1.boolean.not(true).query.get ==> List(b)
      Ns.n.a1.boolean.not(false).query.get ==> List(a)
      Ns.n.a1.boolean.not(Seq(true)).query.get ==> List(b)
      Ns.n.a1.boolean.not(Seq(false)).query.get ==> List(a)
      // OR semantics for multiple args (for Boolean meaning none)
      Ns.n.a1.boolean.not(true, false).query.get ==> List()
      Ns.n.a1.boolean.not(Seq(true, false)).query.get ==> List()
      // Empty Seq of negation args matches all values
      Ns.n.a1.boolean.not(Seq.empty[Boolean]).query.get ==> List(a, b)

      // Find values in range
      Ns.n.a1.boolean.<(true).query.get ==> List(b)
      Ns.n.a1.boolean.>(true).query.get ==> List()
      Ns.n.a1.boolean.<=(true).query.get ==> List(a, b)
      Ns.n.a1.boolean.>=(true).query.get ==> List(a)
    }


    "Tacit" - types { implicit conn =>
      val (a, b, x) = (1, 2, 3)
      Ns.n.boolean_?.insert(List(
        (1, Some(true)),
        (2, Some(false)),
        (3, None)
      )).transact

      // Match asserted attribute without returning its value
      Ns.n.a1.boolean_.query.get ==> List(a, b)

      // Match non-asserted attribute (null)
      Ns.n.a1.boolean_().query.get ==> List(x)

      // Match attribute values without returning them
      Ns.n.a1.boolean_(true).query.get ==> List(a)
      Ns.n.a1.boolean_(false).query.get ==> List(b)
      Ns.n.a1.boolean_(Seq(true)).query.get ==> List(a)
      Ns.n.a1.boolean_(Seq(false)).query.get ==> List(b)
      // OR semantics for multiple args (for Boolean meaning all)
      Ns.n.a1.boolean_(true, false).query.get ==> List(a, b)
      Ns.n.a1.boolean_(Seq(true, false)).query.get ==> List(a, b)
      // Empty Seq of args matches no values
      Ns.n.a1.boolean_(Seq.empty[Boolean]).query.get ==> List()

      // Match non-matching values without returning them
      Ns.n.a1.boolean_.not(true).query.get ==> List(b)
      Ns.n.a1.boolean_.not(false).query.get ==> List(a)
      Ns.n.a1.boolean_.not(Seq(true)).query.get ==> List(b)
      Ns.n.a1.boolean_.not(Seq(false)).query.get ==> List(a)
      // OR semantics for multiple args (for Boolean meaning none)
      Ns.n.a1.boolean_.not(true, false).query.get ==> List()
      Ns.n.a1.boolean_.not(Seq(true, false)).query.get ==> List()
      // Empty Seq of negation args matches all asserted values (non-null)
      Ns.n.a1.boolean_.not(Seq.empty[Boolean]).query.get ==> List(a, b)

      // Match value ranges without returning them
      Ns.n.a1.boolean_.<(true).query.get ==> List(b)
      Ns.n.a1.boolean_.>(true).query.get ==> List()
      Ns.n.a1.boolean_.<=(true).query.get ==> List(a, b)
      Ns.n.a1.boolean_.>=(true).query.get ==> List(a)
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(true))
      val b = (2, Some(false))
      val x = (4, Option.empty[Boolean])
      Ns.n.boolean_?.insert(List(a, b, x)).transact

      // Find all optional attribute values
      Ns.n.a1.boolean_?.query.get ==> List(a, b, x)

      // Find optional values matching
      Ns.n.a1.boolean_?(Some(true)).query.get ==> List(a)
      Ns.n.a1.boolean_?(Some(false)).query.get ==> List(b)
      Ns.n.a1.boolean_?(Some(Seq(true))).query.get ==> List(a)
      Ns.n.a1.boolean_?(Some(Seq(false))).query.get ==> List(b)
      // OR semantics for Ses of multiple args
      Ns.n.a1.boolean_?(Some(Seq(true, false))).query.get ==> List(a, b)
      // Empty Seq of args matches no values
      Ns.n.a1.boolean_?(Some(Seq.empty[Boolean])).query.get ==> List()
      // None matches non-asserted/null values
      Ns.n.a1.boolean_?(Option.empty[Boolean]).query.get ==> List(x)
      Ns.n.a1.boolean_?(Option.empty[Seq[Boolean]]).query.get ==> List(x)


      // Find optional values not matching
      Ns.n.a1.boolean_?.not(Some(true)).query.get ==> List(b)
      Ns.n.a1.boolean_?.not(Some(false)).query.get ==> List(a)
      Ns.n.a1.boolean_?.not(Some(Seq(true))).query.get ==> List(b)
      Ns.n.a1.boolean_?.not(Some(Seq(false))).query.get ==> List(a)
      // OR semantics for multiple args (for Boolean meaning all)
      Ns.n.a1.boolean_?.not(Some(Seq(true, false))).query.get ==> List()
      // Empty Seq of negation args matches all asserted values (non-null)
      Ns.n.a1.boolean_?.not(Some(Seq.empty[Boolean])).query.get ==> List(a, b)
      // None matches all asserted values (non-null)
      Ns.n.a1.boolean_?.not(Option.empty[Boolean]).query.get ==> List(a, b)
      Ns.n.a1.boolean_?.not(Option.empty[Seq[Boolean]]).query.get ==> List(a, b)

      // Find optional values in range
      Ns.n.a1.boolean_?.<(Some(true)).query.get ==> List(b)
      Ns.n.a1.boolean_?.<=(Some(true)).query.get ==> List(a, b)
      Ns.n.a1.boolean_?.>(Some(true)).query.get ==> List()
      Ns.n.a1.boolean_?.>=(Some(true)).query.get ==> List(a)
      // None can't be compared and returns empty result
      Ns.n.a1.boolean_?.<(None).query.get ==> List()
      Ns.n.a1.boolean_?.>(None).query.get ==> List()
      Ns.n.a1.boolean_?.<=(None).query.get ==> List()
      Ns.n.a1.boolean_?.>=(None).query.get ==> List()
    }
  }
}