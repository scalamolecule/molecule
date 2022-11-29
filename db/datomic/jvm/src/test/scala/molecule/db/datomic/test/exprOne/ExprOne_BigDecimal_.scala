// GENERATED CODE ********************************
package molecule.db.datomic.test.exprOne

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object ExprOne_BigDecimal_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, bigDecimal1)
      val b = (2, bigDecimal2)
      val c = (3, bigDecimal3)
      Ns.i.bigDecimal.insert(List(a, b, c)).transact

      // Find all attribute values
      Ns.i.a1.bigDecimal.query.get ==> List(a, b, c)

      // Find value(s) matching
      Ns.i.a1.bigDecimal(bigDecimal0).query.get ==> List()
      Ns.i.a1.bigDecimal(bigDecimal1).query.get ==> List(a)
      Ns.i.a1.bigDecimal(Seq(bigDecimal0)).query.get ==> List()
      Ns.i.a1.bigDecimal(Seq(bigDecimal1)).query.get ==> List(a)
      // OR semantics for multiple args
      Ns.i.a1.bigDecimal(bigDecimal1, bigDecimal2).query.get ==> List(a, b)
      Ns.i.a1.bigDecimal(bigDecimal1, bigDecimal0).query.get ==> List(a)
      Ns.i.a1.bigDecimal(Seq(bigDecimal1, bigDecimal2)).query.get ==> List(a, b)
      Ns.i.a1.bigDecimal(Seq(bigDecimal1, bigDecimal0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      Ns.i.a1.bigDecimal(Seq.empty[BigDecimal]).query.get ==> List()

      // Find values not matching
      Ns.i.a1.bigDecimal.not(bigDecimal0).query.get ==> List(a, b, c)
      Ns.i.a1.bigDecimal.not(bigDecimal1).query.get ==> List(b, c)
      Ns.i.a1.bigDecimal.not(bigDecimal2).query.get ==> List(a, c)
      Ns.i.a1.bigDecimal.not(bigDecimal3).query.get ==> List(a, b)
      Ns.i.a1.bigDecimal.not(Seq(bigDecimal0)).query.get ==> List(a, b, c)
      Ns.i.a1.bigDecimal.not(Seq(bigDecimal1)).query.get ==> List(b, c)
      Ns.i.a1.bigDecimal.not(Seq(bigDecimal2)).query.get ==> List(a, c)
      Ns.i.a1.bigDecimal.not(Seq(bigDecimal3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      Ns.i.a1.bigDecimal.not(bigDecimal0, bigDecimal1).query.get ==> List(b, c)
      Ns.i.a1.bigDecimal.not(bigDecimal1, bigDecimal2).query.get ==> List(c)
      Ns.i.a1.bigDecimal.not(bigDecimal2, bigDecimal3).query.get ==> List(a)
      Ns.i.a1.bigDecimal.not(Seq(bigDecimal0, bigDecimal1)).query.get ==> List(b, c)
      Ns.i.a1.bigDecimal.not(Seq(bigDecimal1, bigDecimal2)).query.get ==> List(c)
      Ns.i.a1.bigDecimal.not(Seq(bigDecimal2, bigDecimal3)).query.get ==> List(a)
      // Empty Seq of negation args matches all values
      Ns.i.a1.bigDecimal.not(Seq.empty[BigDecimal]).query.get ==> List(a, b, c)

      // Find values in range
      Ns.i.a1.bigDecimal.<(bigDecimal2).query.get ==> List(a)
      Ns.i.a1.bigDecimal.>(bigDecimal2).query.get ==> List(c)
      Ns.i.a1.bigDecimal.<=(bigDecimal2).query.get ==> List(a, b)
      Ns.i.a1.bigDecimal.>=(bigDecimal2).query.get ==> List(b, c)
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      Ns.i.bigDecimal_?.insert(List(
        (a, Some(bigDecimal1)),
        (b, Some(bigDecimal2)),
        (c, Some(bigDecimal3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      Ns.i.a1.bigDecimal_.query.get ==> List(a, b, c)

      // Match non-asserted attribute (null)
      Ns.i.a1.bigDecimal_().query.get ==> List(x)

      // Match attribute values without returning them
      Ns.i.a1.bigDecimal_(bigDecimal0).query.get ==> List()
      Ns.i.a1.bigDecimal_(bigDecimal1).query.get ==> List(a)
      Ns.i.a1.bigDecimal_(Seq(bigDecimal0)).query.get ==> List()
      Ns.i.a1.bigDecimal_(Seq(bigDecimal1)).query.get ==> List(a)
      // OR semantics for multiple args
      Ns.i.a1.bigDecimal_(bigDecimal1, bigDecimal2).query.get ==> List(a, b)
      Ns.i.a1.bigDecimal_(bigDecimal1, bigDecimal0).query.get ==> List(a)
      Ns.i.a1.bigDecimal_(Seq(bigDecimal1, bigDecimal2)).query.get ==> List(a, b)
      Ns.i.a1.bigDecimal_(Seq(bigDecimal1, bigDecimal0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      Ns.i.a1.bigDecimal_(Seq.empty[BigDecimal]).query.get ==> List()

      // Match non-matching values without returning them
      Ns.i.a1.bigDecimal_.not(bigDecimal0).query.get ==> List(a, b, c)
      Ns.i.a1.bigDecimal_.not(bigDecimal1).query.get ==> List(b, c)
      Ns.i.a1.bigDecimal_.not(bigDecimal2).query.get ==> List(a, c)
      Ns.i.a1.bigDecimal_.not(bigDecimal3).query.get ==> List(a, b)
      Ns.i.a1.bigDecimal_.not(Seq(bigDecimal0)).query.get ==> List(a, b, c)
      Ns.i.a1.bigDecimal_.not(Seq(bigDecimal1)).query.get ==> List(b, c)
      Ns.i.a1.bigDecimal_.not(Seq(bigDecimal2)).query.get ==> List(a, c)
      Ns.i.a1.bigDecimal_.not(Seq(bigDecimal3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      Ns.i.a1.bigDecimal_.not(bigDecimal0, bigDecimal1).query.get ==> List(b, c)
      Ns.i.a1.bigDecimal_.not(bigDecimal1, bigDecimal2).query.get ==> List(c)
      Ns.i.a1.bigDecimal_.not(bigDecimal2, bigDecimal3).query.get ==> List(a)
      Ns.i.a1.bigDecimal_.not(Seq(bigDecimal0, bigDecimal1)).query.get ==> List(b, c)
      Ns.i.a1.bigDecimal_.not(Seq(bigDecimal1, bigDecimal2)).query.get ==> List(c)
      Ns.i.a1.bigDecimal_.not(Seq(bigDecimal2, bigDecimal3)).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      Ns.i.a1.bigDecimal_.not(Seq.empty[BigDecimal]).query.get ==> List(a, b, c)

      // Match value ranges without returning them
      Ns.i.a1.bigDecimal_.<(bigDecimal2).query.get ==> List(a)
      Ns.i.a1.bigDecimal_.>(bigDecimal2).query.get ==> List(c)
      Ns.i.a1.bigDecimal_.<=(bigDecimal2).query.get ==> List(a, b)
      Ns.i.a1.bigDecimal_.>=(bigDecimal2).query.get ==> List(b, c)
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(bigDecimal1))
      val b = (2, Some(bigDecimal2))
      val c = (3, Some(bigDecimal3))
      val x = (4, Option.empty[BigDecimal])
      Ns.i.bigDecimal_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      Ns.i.a1.bigDecimal_?.query.get ==> List(a, b, c, x)

      // Find optional values matching
      Ns.i.a1.bigDecimal_?(Some(bigDecimal0)).query.get ==> List()
      Ns.i.a1.bigDecimal_?(Some(bigDecimal1)).query.get ==> List(a)
      Ns.i.a1.bigDecimal_?(Some(Seq(bigDecimal0))).query.get ==> List()
      Ns.i.a1.bigDecimal_?(Some(Seq(bigDecimal1))).query.get ==> List(a)
      // OR semantics for Ses of multiple args
      Ns.i.a1.bigDecimal_?(Some(Seq(bigDecimal1, bigDecimal2))).query.get ==> List(a, b)
      Ns.i.a1.bigDecimal_?(Some(Seq(bigDecimal1, bigDecimal0))).query.get ==> List(a)
      // Empty Seq of args matches no values
      Ns.i.a1.bigDecimal_?(Some(Seq.empty[BigDecimal])).query.get ==> List()
      // None matches non-asserted/null values
      Ns.i.a1.bigDecimal_?(Option.empty[BigDecimal]).query.get ==> List(x)
      Ns.i.a1.bigDecimal_?(Option.empty[Seq[BigDecimal]]).query.get ==> List(x)

      // Find optional values not matching
      Ns.i.a1.bigDecimal_?.not(Some(bigDecimal0)).query.get ==> List(a, b, c)
      Ns.i.a1.bigDecimal_?.not(Some(bigDecimal1)).query.get ==> List(b, c)
      Ns.i.a1.bigDecimal_?.not(Some(bigDecimal2)).query.get ==> List(a, c)
      Ns.i.a1.bigDecimal_?.not(Some(bigDecimal3)).query.get ==> List(a, b)
      Ns.i.a1.bigDecimal_?.not(Some(Seq(bigDecimal0))).query.get ==> List(a, b, c)
      Ns.i.a1.bigDecimal_?.not(Some(Seq(bigDecimal1))).query.get ==> List(b, c)
      Ns.i.a1.bigDecimal_?.not(Some(Seq(bigDecimal2))).query.get ==> List(a, c)
      Ns.i.a1.bigDecimal_?.not(Some(Seq(bigDecimal3))).query.get ==> List(a, b)
      // OR semantics for multiple negation args
      Ns.i.a1.bigDecimal_?.not(Some(Seq(bigDecimal0, bigDecimal1))).query.get ==> List(b, c)
      Ns.i.a1.bigDecimal_?.not(Some(Seq(bigDecimal1, bigDecimal2))).query.get ==> List(c)
      Ns.i.a1.bigDecimal_?.not(Some(Seq(bigDecimal2, bigDecimal3))).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      Ns.i.a1.bigDecimal_?.not(Some(Seq.empty[BigDecimal])).query.get ==> List(a, b, c)
      // None matches all asserted values (non-null)
      Ns.i.a1.bigDecimal_?.not(Option.empty[BigDecimal]).query.get ==> List(a, b, c)
      Ns.i.a1.bigDecimal_?.not(Option.empty[Seq[BigDecimal]]).query.get ==> List(a, b, c)

      // Find optional values in range
      Ns.i.a1.bigDecimal_?.<(Some(bigDecimal2)).query.get ==> List(a)
      Ns.i.a1.bigDecimal_?.>(Some(bigDecimal2)).query.get ==> List(c)
      Ns.i.a1.bigDecimal_?.<=(Some(bigDecimal2)).query.get ==> List(a, b)
      Ns.i.a1.bigDecimal_?.>=(Some(bigDecimal2)).query.get ==> List(b, c)
      // None can't be compared and returns empty result
      Ns.i.a1.bigDecimal_?.<(None).query.get ==> List()
      Ns.i.a1.bigDecimal_?.<=(None).query.get ==> List()
      Ns.i.a1.bigDecimal_?.>(None).query.get ==> List()
      Ns.i.a1.bigDecimal_?.>=(None).query.get ==> List()
    }
  }
}
