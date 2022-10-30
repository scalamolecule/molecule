// GENERATED CODE ********************************
package molecule.db.datomic.test.expr

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

      // Apply args to find matching values
      One.n.a1.bigInt(bigInt0).query.get ==> List()
      One.n.a1.bigInt(bigInt1).query.get ==> List(a)
      One.n.a1.bigInt(bigInt1, bigInt2).query.get ==> List(a, b)
      One.n.a1.bigInt(bigInt1, bigInt0).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.bigInt(Seq(bigInt0)).query.get ==> List()
      One.n.a1.bigInt(Seq(bigInt1)).query.get ==> List(a)
      One.n.a1.bigInt(Seq(bigInt1, bigInt2)).query.get ==> List(a, b)
      One.n.a1.bigInt(Seq(bigInt1, bigInt0)).query.get ==> List(a)
      One.n.a1.bigInt(Seq.empty[BigInt]).query.get ==> List()


      One.n.a1.bigInt.not(bigInt0).query.get ==> List(a, b, c)
      One.n.a1.bigInt.not(bigInt1).query.get ==> List(b, c)
      One.n.a1.bigInt.not(bigInt2).query.get ==> List(a, c)
      One.n.a1.bigInt.not(bigInt3).query.get ==> List(a, b)
      One.n.a1.bigInt.not(bigInt0, bigInt1).query.get ==> List(b, c)
      One.n.a1.bigInt.not(bigInt1, bigInt2).query.get ==> List(c)
      One.n.a1.bigInt.not(bigInt2, bigInt3).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.bigInt.not(Seq(bigInt0)).query.get ==> List(a, b, c)
      One.n.a1.bigInt.not(Seq(bigInt1)).query.get ==> List(b, c)
      One.n.a1.bigInt.not(Seq(bigInt2)).query.get ==> List(a, c)
      One.n.a1.bigInt.not(Seq(bigInt3)).query.get ==> List(a, b)
      One.n.a1.bigInt.not(Seq(bigInt0, bigInt1)).query.get ==> List(b, c)
      One.n.a1.bigInt.not(Seq(bigInt1, bigInt2)).query.get ==> List(c)
      One.n.a1.bigInt.not(Seq(bigInt2, bigInt3)).query.get ==> List(a)
      One.n.a1.bigInt.not(Seq.empty[BigInt]).query.get ==> List(a, b, c)


      One.n.a1.bigInt.<(bigInt2).query.get ==> List(a)
      One.n.a1.bigInt.>(bigInt2).query.get ==> List(c)
      One.n.a1.bigInt.<=(bigInt2).query.get ==> List(a, b)
      One.n.a1.bigInt.>=(bigInt2).query.get ==> List(b, c)
    }


    "Tacit" - cardOne { implicit conn =>
      One.n.bigInt_?.insert(List(
        (1, Some(bigInt1)),
        (2, Some(bigInt2)),
        (3, Some(bigInt3)),
        (4, None)
      )).transact

      val a = 1
      val b = 2
      val c = 3
      val d = 4

      // No value asserted
      One.n.a1.bigInt_.apply().query.get ==> List(d)

      One.n.a1.bigInt_(bigInt0).query.get ==> List()
      One.n.a1.bigInt_.apply(bigInt1).query.get ==> List(a)
      One.n.a1.bigInt_(bigInt1, bigInt2).query.get ==> List(a, b)
      One.n.a1.bigInt_(bigInt1, bigInt0).query.get ==> List(a)
      // Same as
      One.n.a1.bigInt_(Seq(bigInt0)).query.get ==> List()
      One.n.a1.bigInt_(Seq(bigInt1)).query.get ==> List(a)
      One.n.a1.bigInt_(Seq(bigInt1, bigInt2)).query.get ==> List(a, b)
      One.n.a1.bigInt_(Seq(bigInt1, bigInt0)).query.get ==> List(a)
      One.n.a1.bigInt_(Seq.empty[BigInt]).query.get ==> List()


      One.n.a1.bigInt_.not(bigInt0).query.get ==> List(a, b, c)
      One.n.a1.bigInt_.not(bigInt1).query.get ==> List(b, c)
      One.n.a1.bigInt_.not(bigInt2).query.get ==> List(a, c)
      One.n.a1.bigInt_.not(bigInt3).query.get ==> List(a, b)
      One.n.a1.bigInt_.not(bigInt0, bigInt1).query.get ==> List(b, c)
      One.n.a1.bigInt_.not(bigInt1, bigInt2).query.get ==> List(c)
      One.n.a1.bigInt_.not(bigInt2, bigInt3).query.get ==> List(a)
      // Same as
      One.n.a1.bigInt_.not(Seq(bigInt0)).query.get ==> List(a, b, c)
      One.n.a1.bigInt_.not(Seq(bigInt1)).query.get ==> List(b, c)
      One.n.a1.bigInt_.not(Seq(bigInt2)).query.get ==> List(a, c)
      One.n.a1.bigInt_.not(Seq(bigInt3)).query.get ==> List(a, b)
      One.n.a1.bigInt_.not(Seq(bigInt0, bigInt1)).query.get ==> List(b, c)
      One.n.a1.bigInt_.not(Seq(bigInt1, bigInt2)).query.get ==> List(c)
      One.n.a1.bigInt_.not(Seq(bigInt2, bigInt3)).query.get ==> List(a)
      One.n.a1.bigInt_.not(Seq.empty[BigInt]).query.get ==> List(a, b, c)


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

      One.n.a1.bigInt_?(Some(bigInt0)).query.get ==> List()
      One.n.a1.bigInt_?.apply(Some(bigInt1)).query.get ==> List(a)

      One.n.a1.bigInt_?(Some(Seq(bigInt0))).query.get ==> List()
      One.n.a1.bigInt_?(Some(Seq(bigInt1))).query.get ==> List(a)
      One.n.a1.bigInt_?(Some(Seq(bigInt1, bigInt2))).query.get ==> List(a, b)
      One.n.a1.bigInt_?(Some(Seq(bigInt1, bigInt0))).query.get ==> List(a)
      One.n.a1.bigInt_?(Some(Seq.empty[BigInt])).query.get ==> List()

      One.n.a1.bigInt_?(Option.empty[BigInt]).query.get ==> List(x)
      One.n.a1.bigInt_?(Option.empty[Seq[BigInt]]).query.get ==> List(x)


      // For non-String cardinality-one attributes, `not` and `!=` have same semantics
      // "Not this value"
      One.n.a1.bigInt_?.not(Some(bigInt0)).query.get ==> List(a, b, c)
      One.n.a1.bigInt_?.not(Some(bigInt1)).query.get ==> List(b, c)
      One.n.a1.bigInt_?.not(Some(bigInt2)).query.get ==> List(a, c)
      One.n.a1.bigInt_?.not(Some(bigInt3)).query.get ==> List(a, b)
      // Same as
      One.n.a1.bigInt_?.not(Some(Seq(bigInt0))).query.get ==> List(a, b, c)
      One.n.a1.bigInt_?.not(Some(Seq(bigInt1))).query.get ==> List(b, c)
      One.n.a1.bigInt_?.not(Some(Seq(bigInt2))).query.get ==> List(a, c)
      One.n.a1.bigInt_?.not(Some(Seq(bigInt3))).query.get ==> List(a, b)

      // "Not this OR that value
      One.n.a1.bigInt_?.not(Some(Seq(bigInt0, bigInt1))).query.get ==> List(b, c)
      One.n.a1.bigInt_?.not(Some(Seq(bigInt1, bigInt2))).query.get ==> List(c)
      One.n.a1.bigInt_?.not(Some(Seq(bigInt2, bigInt3))).query.get ==> List(a)
      One.n.a1.bigInt_?.not(Some(Seq.empty[BigInt])).query.get ==> List(a, b, c)

      One.n.a1.bigInt_?.not(Option.empty[BigInt]).query.get ==> List(a, b, c)
      One.n.a1.bigInt_?.not(Option.empty[Seq[BigInt]]).query.get ==> List(a, b, c)


      One.n.a1.bigInt_?.<(Some(bigInt2)).query.get ==> List(a)
      One.n.a1.bigInt_?.>(Some(bigInt2)).query.get ==> List(c)
      One.n.a1.bigInt_?.<=(Some(bigInt2)).query.get ==> List(a, b)
      One.n.a1.bigInt_?.>=(Some(bigInt2)).query.get ==> List(b, c)

      // None can't be compared
      One.n.a1.bigInt_?.<(None).query.get ==> List()
      One.n.a1.bigInt_?.<=(None).query.get ==> List()
      One.n.a1.bigInt_?.>(None).query.get ==> List()
      One.n.a1.bigInt_?.>=(None).query.get ==> List()
    }
  }
}
