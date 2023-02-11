// GENERATED CODE ********************************
package molecule.db.datomic.test.expr.one

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic.async._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object ExprOne_BigInt_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, bigInt1)
      val b = (2, bigInt2)
      val c = (3, bigInt3)
      for {
        _ <- Ns.i.bigInt.insert(List(a, b, c)).transact

        // Find all attribute values
        _ <- Ns.i.a1.bigInt.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.bigInt(bigInt0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigInt(bigInt1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigInt(Seq(bigInt0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigInt(Seq(bigInt1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.bigInt(bigInt1, bigInt2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInt(bigInt1, bigInt0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigInt(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInt(Seq(bigInt1, bigInt0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.bigInt(Seq.empty[BigInt]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.bigInt.not(bigInt0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.bigInt.not(bigInt1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigInt.not(bigInt2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.bigInt.not(bigInt3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInt.not(Seq(bigInt0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.bigInt.not(Seq(bigInt1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigInt.not(Seq(bigInt2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.bigInt.not(Seq(bigInt3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.bigInt.not(bigInt0, bigInt1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigInt.not(bigInt1, bigInt2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.bigInt.not(bigInt2, bigInt3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigInt.not(Seq(bigInt0, bigInt1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigInt.not(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.bigInt.not(Seq(bigInt2, bigInt3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.bigInt.not(Seq.empty[BigInt]).query.get.map(_ ==> List(a, b, c))

        // Find values in range
        _ <- Ns.i.a1.bigInt.<(bigInt2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigInt.>(bigInt2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.bigInt.<=(bigInt2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInt.>=(bigInt2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      for {
        _ <- Ns.i.bigInt_?.insert(List(
          (a, Some(bigInt1)),
          (b, Some(bigInt2)),
          (c, Some(bigInt3)),
          (x, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.bigInt_.query.get.map(_ ==> List(a, b, c))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.bigInt_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.bigInt_(bigInt0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigInt_(bigInt1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigInt_(Seq(bigInt0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigInt_(Seq(bigInt1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.bigInt_(bigInt1, bigInt2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInt_(bigInt1, bigInt0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigInt_(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInt_(Seq(bigInt1, bigInt0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.bigInt_(Seq.empty[BigInt]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.bigInt_.not(bigInt0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.bigInt_.not(bigInt1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigInt_.not(bigInt2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.bigInt_.not(bigInt3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInt_.not(Seq(bigInt0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.bigInt_.not(Seq(bigInt1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigInt_.not(Seq(bigInt2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.bigInt_.not(Seq(bigInt3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.bigInt_.not(bigInt0, bigInt1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigInt_.not(bigInt1, bigInt2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.bigInt_.not(bigInt2, bigInt3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigInt_.not(Seq(bigInt0, bigInt1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigInt_.not(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.bigInt_.not(Seq(bigInt2, bigInt3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.bigInt_.not(Seq.empty[BigInt]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.bigInt_.<(bigInt2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigInt_.>(bigInt2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.bigInt_.<=(bigInt2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInt_.>=(bigInt2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(bigInt1))
      val b = (2, Some(bigInt2))
      val c = (3, Some(bigInt3))
      val x = (4, Option.empty[BigInt])
      for {
        _ <- Ns.i.bigInt_?.insert(List(a, b, c, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.bigInt_?.query.get.map(_ ==> List(a, b, c, x))

        // Find optional values matching
        _ <- Ns.i.a1.bigInt_?(Some(bigInt0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigInt_?(Some(bigInt1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigInt_?(Some(Seq(bigInt0))).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigInt_?(Some(Seq(bigInt1))).query.get.map(_ ==> List(a))
        // OR semantics for Ses of multiple args
        _ <- Ns.i.a1.bigInt_?(Some(Seq(bigInt1, bigInt2))).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInt_?(Some(Seq(bigInt1, bigInt0))).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.bigInt_?(Some(Seq.empty[BigInt])).query.get.map(_ ==> List())
        // None matches non-asserted/null values
        _ <- Ns.i.a1.bigInt_?(Option.empty[BigInt]).query.get.map(_ ==> List(x))
        _ <- Ns.i.a1.bigInt_?(Option.empty[Seq[BigInt]]).query.get.map(_ ==> List(x))

        // Find optional values not matching
        _ <- Ns.i.a1.bigInt_?.not(Some(bigInt0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.bigInt_?.not(Some(bigInt1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigInt_?.not(Some(bigInt2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.bigInt_?.not(Some(bigInt3)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInt_?.not(Some(Seq(bigInt0))).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.bigInt_?.not(Some(Seq(bigInt1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigInt_?.not(Some(Seq(bigInt2))).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.bigInt_?.not(Some(Seq(bigInt3))).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple negation args
        _ <- Ns.i.a1.bigInt_?.not(Some(Seq(bigInt0, bigInt1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigInt_?.not(Some(Seq(bigInt1, bigInt2))).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.bigInt_?.not(Some(Seq(bigInt2, bigInt3))).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.bigInt_?.not(Some(Seq.empty[BigInt])).query.get.map(_ ==> List(a, b, c))
        // Negating None matches all asserted values (non-null)
        _ <- Ns.i.a1.bigInt_?.not(Option.empty[BigInt]).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.bigInt_?.not(Option.empty[Seq[BigInt]]).query.get.map(_ ==> List(a, b, c))

        // Find optional values in range
        _ <- Ns.i.a1.bigInt_?.<(Some(bigInt2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigInt_?.>(Some(bigInt2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.bigInt_?.<=(Some(bigInt2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInt_?.>=(Some(bigInt2)).query.get.map(_ ==> List(b, c))
        // None can't be compared and returns empty result
        _ <- Ns.i.a1.bigInt_?.<(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigInt_?.<=(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigInt_?.>(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigInt_?.>=(None).query.get.map(_ ==> List())

        // Distinct result even with redundant None's (two i = 4 with no bigInt value)
        _ <- Ns.i.insert(4).transact
        _ <- Ns.i.>=(3).a1.bigInt_?.query.get.map(_ ==> List(
          (3, Some(bigInt3)),
          (4, None),
        ))
      } yield ()
    }
  }
}
