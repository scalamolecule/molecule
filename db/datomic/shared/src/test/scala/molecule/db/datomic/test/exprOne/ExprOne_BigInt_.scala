// GENERATED CODE ********************************
package molecule.db.datomic.test.exprOne

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object ExprOne_BigInt_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - cardOne { implicit conn =>
      val a = (1, bigInt1)
      val b = (2, bigInt2)
      val c = (3, bigInt3)
      One.n.bigInt.insert(List(a, b, c)).transact

      // Find all attribute values
      One.n.a1.bigInt.query.get ==> List(a, b, c)

      // Find value(s) matching
      One.n.a1.bigInt(bigInt0).query.get ==> List()
      One.n.a1.bigInt(bigInt1).query.get ==> List(a)
      One.n.a1.bigInt(Seq(bigInt0)).query.get ==> List()
      One.n.a1.bigInt(Seq(bigInt1)).query.get ==> List(a)
      // OR semantics for multiple args
      One.n.a1.bigInt(bigInt1, bigInt2).query.get ==> List(a, b)
      One.n.a1.bigInt(bigInt1, bigInt0).query.get ==> List(a)
      One.n.a1.bigInt(Seq(bigInt1, bigInt2)).query.get ==> List(a, b)
      One.n.a1.bigInt(Seq(bigInt1, bigInt0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      One.n.a1.bigInt(Seq.empty[BigInt]).query.get ==> List()

      // Find values not matching
      One.n.a1.bigInt.not(bigInt0).query.get ==> List(a, b, c)
      One.n.a1.bigInt.not(bigInt1).query.get ==> List(b, c)
      One.n.a1.bigInt.not(bigInt2).query.get ==> List(a, c)
      One.n.a1.bigInt.not(bigInt3).query.get ==> List(a, b)
      One.n.a1.bigInt.not(Seq(bigInt0)).query.get ==> List(a, b, c)
      One.n.a1.bigInt.not(Seq(bigInt1)).query.get ==> List(b, c)
      One.n.a1.bigInt.not(Seq(bigInt2)).query.get ==> List(a, c)
      One.n.a1.bigInt.not(Seq(bigInt3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      One.n.a1.bigInt.not(bigInt0, bigInt1).query.get ==> List(b, c)
      One.n.a1.bigInt.not(bigInt1, bigInt2).query.get ==> List(c)
      One.n.a1.bigInt.not(bigInt2, bigInt3).query.get ==> List(a)
      One.n.a1.bigInt.not(Seq(bigInt0, bigInt1)).query.get ==> List(b, c)
      One.n.a1.bigInt.not(Seq(bigInt1, bigInt2)).query.get ==> List(c)
      One.n.a1.bigInt.not(Seq(bigInt2, bigInt3)).query.get ==> List(a)
      // Empty Seq of negation args matches all values
      One.n.a1.bigInt.not(Seq.empty[BigInt]).query.get ==> List(a, b, c)

      // Find values in range
      One.n.a1.bigInt.<(bigInt2).query.get ==> List(a)
      One.n.a1.bigInt.>(bigInt2).query.get ==> List(c)
      One.n.a1.bigInt.<=(bigInt2).query.get ==> List(a, b)
      One.n.a1.bigInt.>=(bigInt2).query.get ==> List(b, c)
    }


    "Tacit" - cardOne { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      One.n.bigInt_?.insert(List(
        (a, Some(bigInt1)),
        (b, Some(bigInt2)),
        (c, Some(bigInt3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      One.n.a1.bigInt_.query.get ==> List(a, b, c)

      // Match non-asserted attribute (null)
      One.n.a1.bigInt_().query.get ==> List(x)

      // Match attribute values without returning them
      One.n.a1.bigInt_(bigInt0).query.get ==> List()
      One.n.a1.bigInt_(bigInt1).query.get ==> List(a)
      One.n.a1.bigInt_(Seq(bigInt0)).query.get ==> List()
      One.n.a1.bigInt_(Seq(bigInt1)).query.get ==> List(a)
      // OR semantics for multiple args
      One.n.a1.bigInt_(bigInt1, bigInt2).query.get ==> List(a, b)
      One.n.a1.bigInt_(bigInt1, bigInt0).query.get ==> List(a)
      One.n.a1.bigInt_(Seq(bigInt1, bigInt2)).query.get ==> List(a, b)
      One.n.a1.bigInt_(Seq(bigInt1, bigInt0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      One.n.a1.bigInt_(Seq.empty[BigInt]).query.get ==> List()

      // Match non-matching values without returning them
      One.n.a1.bigInt_.not(bigInt0).query.get ==> List(a, b, c)
      One.n.a1.bigInt_.not(bigInt1).query.get ==> List(b, c)
      One.n.a1.bigInt_.not(bigInt2).query.get ==> List(a, c)
      One.n.a1.bigInt_.not(bigInt3).query.get ==> List(a, b)
      One.n.a1.bigInt_.not(Seq(bigInt0)).query.get ==> List(a, b, c)
      One.n.a1.bigInt_.not(Seq(bigInt1)).query.get ==> List(b, c)
      One.n.a1.bigInt_.not(Seq(bigInt2)).query.get ==> List(a, c)
      One.n.a1.bigInt_.not(Seq(bigInt3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      One.n.a1.bigInt_.not(bigInt0, bigInt1).query.get ==> List(b, c)
      One.n.a1.bigInt_.not(bigInt1, bigInt2).query.get ==> List(c)
      One.n.a1.bigInt_.not(bigInt2, bigInt3).query.get ==> List(a)
      One.n.a1.bigInt_.not(Seq(bigInt0, bigInt1)).query.get ==> List(b, c)
      One.n.a1.bigInt_.not(Seq(bigInt1, bigInt2)).query.get ==> List(c)
      One.n.a1.bigInt_.not(Seq(bigInt2, bigInt3)).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      One.n.a1.bigInt_.not(Seq.empty[BigInt]).query.get ==> List(a, b, c)

      // Match value ranges without returning them
      One.n.a1.bigInt_.<(bigInt2).query.get ==> List(a)
      One.n.a1.bigInt_.>(bigInt2).query.get ==> List(c)
      One.n.a1.bigInt_.<=(bigInt2).query.get ==> List(a, b)
      One.n.a1.bigInt_.>=(bigInt2).query.get ==> List(b, c)
    }


    "Optional" - cardOne { implicit conn =>
      val a = (1, Some(bigInt1))
      val b = (2, Some(bigInt2))
      val c = (3, Some(bigInt3))
      val x = (4, Option.empty[BigInt])
      One.n.bigInt_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      One.n.a1.bigInt_?.query.get ==> List(a, b, c, x)

      // Find optional values matching
      One.n.a1.bigInt_?(Some(bigInt0)).query.get ==> List()
      One.n.a1.bigInt_?(Some(bigInt1)).query.get ==> List(a)
      One.n.a1.bigInt_?(Some(Seq(bigInt0))).query.get ==> List()
      One.n.a1.bigInt_?(Some(Seq(bigInt1))).query.get ==> List(a)
      // OR semantics for Ses of multiple args
      One.n.a1.bigInt_?(Some(Seq(bigInt1, bigInt2))).query.get ==> List(a, b)
      One.n.a1.bigInt_?(Some(Seq(bigInt1, bigInt0))).query.get ==> List(a)
      // Empty Seq of args matches no values
      One.n.a1.bigInt_?(Some(Seq.empty[BigInt])).query.get ==> List()
      // None matches non-asserted/null values
      One.n.a1.bigInt_?(Option.empty[BigInt]).query.get ==> List(x)
      One.n.a1.bigInt_?(Option.empty[Seq[BigInt]]).query.get ==> List(x)

      // Find optional values not matching
      One.n.a1.bigInt_?.not(Some(bigInt0)).query.get ==> List(a, b, c)
      One.n.a1.bigInt_?.not(Some(bigInt1)).query.get ==> List(b, c)
      One.n.a1.bigInt_?.not(Some(bigInt2)).query.get ==> List(a, c)
      One.n.a1.bigInt_?.not(Some(bigInt3)).query.get ==> List(a, b)
      One.n.a1.bigInt_?.not(Some(Seq(bigInt0))).query.get ==> List(a, b, c)
      One.n.a1.bigInt_?.not(Some(Seq(bigInt1))).query.get ==> List(b, c)
      One.n.a1.bigInt_?.not(Some(Seq(bigInt2))).query.get ==> List(a, c)
      One.n.a1.bigInt_?.not(Some(Seq(bigInt3))).query.get ==> List(a, b)
      // OR semantics for multiple negation args
      One.n.a1.bigInt_?.not(Some(Seq(bigInt0, bigInt1))).query.get ==> List(b, c)
      One.n.a1.bigInt_?.not(Some(Seq(bigInt1, bigInt2))).query.get ==> List(c)
      One.n.a1.bigInt_?.not(Some(Seq(bigInt2, bigInt3))).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      One.n.a1.bigInt_?.not(Some(Seq.empty[BigInt])).query.get ==> List(a, b, c)
      // None matches all asserted values (non-null)
      One.n.a1.bigInt_?.not(Option.empty[BigInt]).query.get ==> List(a, b, c)
      One.n.a1.bigInt_?.not(Option.empty[Seq[BigInt]]).query.get ==> List(a, b, c)

      // Find optional values in range
      One.n.a1.bigInt_?.<(Some(bigInt2)).query.get ==> List(a)
      One.n.a1.bigInt_?.>(Some(bigInt2)).query.get ==> List(c)
      One.n.a1.bigInt_?.<=(Some(bigInt2)).query.get ==> List(a, b)
      One.n.a1.bigInt_?.>=(Some(bigInt2)).query.get ==> List(b, c)
      // None can't be compared and returns empty result
      One.n.a1.bigInt_?.<(None).query.get ==> List()
      One.n.a1.bigInt_?.<=(None).query.get ==> List()
      One.n.a1.bigInt_?.>(None).query.get ==> List()
      One.n.a1.bigInt_?.>=(None).query.get ==> List()
    }
  }
}