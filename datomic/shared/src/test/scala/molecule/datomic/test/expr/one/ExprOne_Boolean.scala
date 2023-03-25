package molecule.datomic.test.expr.one

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._

object ExprOne_Boolean extends DatomicTestSuite {


  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, true)
      val b = (2, false)
      for {
        _ <- Ns.i.boolean.insert(List(a, b)).transact

        // Find all attribute values
        _ <- Ns.i.a1.boolean.query.get.map(_ ==> List(a, b))

        // Find value(s) matching
        _ <- Ns.i.a1.boolean(true).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.boolean(false).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.boolean(Seq(true)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.boolean(Seq(false)).query.get.map(_ ==> List(b))
        // OR semantics for multiple args (for Boolean meaning all)
        _ <- Ns.i.a1.boolean(true, false).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.boolean(Seq(true, false)).query.get.map(_ ==> List(a, b))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.boolean(Seq.empty[Boolean]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.boolean.not(true).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.boolean.not(false).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.boolean.not(Seq(true)).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.boolean.not(Seq(false)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args (for Boolean meaning none)
        _ <- Ns.i.a1.boolean.not(true, false).query.get.map(_ ==> List())
        _ <- Ns.i.a1.boolean.not(Seq(true, false)).query.get.map(_ ==> List())
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.boolean.not(Seq.empty[Boolean]).query.get.map(_ ==> List(a, b))

        // Find values in range
        _ <- Ns.i.a1.boolean.<(true).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.boolean.>(true).query.get.map(_ ==> List())
        _ <- Ns.i.a1.boolean.<=(true).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.boolean.>=(true).query.get.map(_ ==> List(a))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, x) = (1, 2, 3)
      for {
        _ <- Ns.i.boolean_?.insert(List(
          (1, Some(true)),
          (2, Some(false)),
          (3, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.boolean_.query.get.map(_ ==> List(a, b))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.boolean_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.boolean_(true).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.boolean_(false).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.boolean_(Seq(true)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.boolean_(Seq(false)).query.get.map(_ ==> List(b))
        // OR semantics for multiple args (for Boolean meaning all)
        _ <- Ns.i.a1.boolean_(true, false).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.boolean_(Seq(true, false)).query.get.map(_ ==> List(a, b))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.boolean_(Seq.empty[Boolean]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.boolean_.not(true).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.boolean_.not(false).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.boolean_.not(Seq(true)).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.boolean_.not(Seq(false)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args (for Boolean meaning none)
        _ <- Ns.i.a1.boolean_.not(true, false).query.get.map(_ ==> List())
        _ <- Ns.i.a1.boolean_.not(Seq(true, false)).query.get.map(_ ==> List())
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.boolean_.not(Seq.empty[Boolean]).query.get.map(_ ==> List(a, b))

        // Match value ranges without returning them
        _ <- Ns.i.a1.boolean_.<(true).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.boolean_.>(true).query.get.map(_ ==> List())
        _ <- Ns.i.a1.boolean_.<=(true).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.boolean_.>=(true).query.get.map(_ ==> List(a))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(true))
      val b = (2, Some(false))
      val x = (4, Option.empty[Boolean])
      for {
        _ <- Ns.i.boolean_?.insert(List(a, b, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.boolean_?.query.get.map(_ ==> List(a, b, x))

        // Find optional values matching
        _ <- Ns.i.a1.boolean_?(Some(true)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.boolean_?(Some(false)).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.boolean_?(Some(Seq(true))).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.boolean_?(Some(Seq(false))).query.get.map(_ ==> List(b))
        // OR semantics for Ses of multiple args
        _ <- Ns.i.a1.boolean_?(Some(Seq(true, false))).query.get.map(_ ==> List(a, b))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.boolean_?(Some(Seq.empty[Boolean])).query.get.map(_ ==> List())
        // None matches non-asserted/null values
        _ <- Ns.i.a1.boolean_?(Option.empty[Boolean]).query.get.map(_ ==> List(x))
        _ <- Ns.i.a1.boolean_?(Option.empty[Seq[Boolean]]).query.get.map(_ ==> List(x))


        // Find optional values not matching
        _ <- Ns.i.a1.boolean_?.not(Some(true)).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.boolean_?.not(Some(false)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.boolean_?.not(Some(Seq(true))).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.boolean_?.not(Some(Seq(false))).query.get.map(_ ==> List(a))
        // OR semantics for multiple args (for Boolean meaning all)
        _ <- Ns.i.a1.boolean_?.not(Some(Seq(true, false))).query.get.map(_ ==> List())
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.boolean_?.not(Some(Seq.empty[Boolean])).query.get.map(_ ==> List(a, b))
        // None matches all asserted values (non-null)
        _ <- Ns.i.a1.boolean_?.not(Option.empty[Boolean]).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.boolean_?.not(Option.empty[Seq[Boolean]]).query.get.map(_ ==> List(a, b))

        // Find optional values in range
        _ <- Ns.i.a1.boolean_?.<(Some(true)).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.boolean_?.<=(Some(true)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.boolean_?.>(Some(true)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.boolean_?.>=(Some(true)).query.get.map(_ ==> List(a))
        // None can't be compared and returns empty result
        _ <- Ns.i.a1.boolean_?.<(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.boolean_?.>(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.boolean_?.<=(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.boolean_?.>=(None).query.get.map(_ ==> List())
      } yield ()
    }
  }
}