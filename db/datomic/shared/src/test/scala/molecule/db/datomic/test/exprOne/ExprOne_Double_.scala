// GENERATED CODE ********************************
package molecule.db.datomic.test.exprOne

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object ExprOne_Double_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - cardOne { implicit conn =>
      val a = (1, double1)
      val b = (2, double2)
      val c = (3, double3)
      NsOne.n.double.insert(List(a, b, c)).transact

      // Find all attribute values
      NsOne.n.a1.double.query.get ==> List(a, b, c)

      // Find value(s) matching
      NsOne.n.a1.double(double0).query.get ==> List()
      NsOne.n.a1.double(double1).query.get ==> List(a)
      NsOne.n.a1.double(Seq(double0)).query.get ==> List()
      NsOne.n.a1.double(Seq(double1)).query.get ==> List(a)
      // OR semantics for multiple args
      NsOne.n.a1.double(double1, double2).query.get ==> List(a, b)
      NsOne.n.a1.double(double1, double0).query.get ==> List(a)
      NsOne.n.a1.double(Seq(double1, double2)).query.get ==> List(a, b)
      NsOne.n.a1.double(Seq(double1, double0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      NsOne.n.a1.double(Seq.empty[Double]).query.get ==> List()

      // Find values not matching
      NsOne.n.a1.double.not(double0).query.get ==> List(a, b, c)
      NsOne.n.a1.double.not(double1).query.get ==> List(b, c)
      NsOne.n.a1.double.not(double2).query.get ==> List(a, c)
      NsOne.n.a1.double.not(double3).query.get ==> List(a, b)
      NsOne.n.a1.double.not(Seq(double0)).query.get ==> List(a, b, c)
      NsOne.n.a1.double.not(Seq(double1)).query.get ==> List(b, c)
      NsOne.n.a1.double.not(Seq(double2)).query.get ==> List(a, c)
      NsOne.n.a1.double.not(Seq(double3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      NsOne.n.a1.double.not(double0, double1).query.get ==> List(b, c)
      NsOne.n.a1.double.not(double1, double2).query.get ==> List(c)
      NsOne.n.a1.double.not(double2, double3).query.get ==> List(a)
      NsOne.n.a1.double.not(Seq(double0, double1)).query.get ==> List(b, c)
      NsOne.n.a1.double.not(Seq(double1, double2)).query.get ==> List(c)
      NsOne.n.a1.double.not(Seq(double2, double3)).query.get ==> List(a)
      // Empty Seq of negation args matches all values
      NsOne.n.a1.double.not(Seq.empty[Double]).query.get ==> List(a, b, c)

      // Find values in range
      NsOne.n.a1.double.<(double2).query.get ==> List(a)
      NsOne.n.a1.double.>(double2).query.get ==> List(c)
      NsOne.n.a1.double.<=(double2).query.get ==> List(a, b)
      NsOne.n.a1.double.>=(double2).query.get ==> List(b, c)
    }


    "Tacit" - cardOne { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      NsOne.n.double_?.insert(List(
        (a, Some(double1)),
        (b, Some(double2)),
        (c, Some(double3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      NsOne.n.a1.double_.query.get ==> List(a, b, c)

      // Match non-asserted attribute (null)
      NsOne.n.a1.double_().query.get ==> List(x)

      // Match attribute values without returning them
      NsOne.n.a1.double_(double0).query.get ==> List()
      NsOne.n.a1.double_(double1).query.get ==> List(a)
      NsOne.n.a1.double_(Seq(double0)).query.get ==> List()
      NsOne.n.a1.double_(Seq(double1)).query.get ==> List(a)
      // OR semantics for multiple args
      NsOne.n.a1.double_(double1, double2).query.get ==> List(a, b)
      NsOne.n.a1.double_(double1, double0).query.get ==> List(a)
      NsOne.n.a1.double_(Seq(double1, double2)).query.get ==> List(a, b)
      NsOne.n.a1.double_(Seq(double1, double0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      NsOne.n.a1.double_(Seq.empty[Double]).query.get ==> List()

      // Match non-matching values without returning them
      NsOne.n.a1.double_.not(double0).query.get ==> List(a, b, c)
      NsOne.n.a1.double_.not(double1).query.get ==> List(b, c)
      NsOne.n.a1.double_.not(double2).query.get ==> List(a, c)
      NsOne.n.a1.double_.not(double3).query.get ==> List(a, b)
      NsOne.n.a1.double_.not(Seq(double0)).query.get ==> List(a, b, c)
      NsOne.n.a1.double_.not(Seq(double1)).query.get ==> List(b, c)
      NsOne.n.a1.double_.not(Seq(double2)).query.get ==> List(a, c)
      NsOne.n.a1.double_.not(Seq(double3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      NsOne.n.a1.double_.not(double0, double1).query.get ==> List(b, c)
      NsOne.n.a1.double_.not(double1, double2).query.get ==> List(c)
      NsOne.n.a1.double_.not(double2, double3).query.get ==> List(a)
      NsOne.n.a1.double_.not(Seq(double0, double1)).query.get ==> List(b, c)
      NsOne.n.a1.double_.not(Seq(double1, double2)).query.get ==> List(c)
      NsOne.n.a1.double_.not(Seq(double2, double3)).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      NsOne.n.a1.double_.not(Seq.empty[Double]).query.get ==> List(a, b, c)

      // Match value ranges without returning them
      NsOne.n.a1.double_.<(double2).query.get ==> List(a)
      NsOne.n.a1.double_.>(double2).query.get ==> List(c)
      NsOne.n.a1.double_.<=(double2).query.get ==> List(a, b)
      NsOne.n.a1.double_.>=(double2).query.get ==> List(b, c)
    }


    "Optional" - cardOne { implicit conn =>
      val a = (1, Some(double1))
      val b = (2, Some(double2))
      val c = (3, Some(double3))
      val x = (4, Option.empty[Double])
      NsOne.n.double_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      NsOne.n.a1.double_?.query.get ==> List(a, b, c, x)

      // Find optional values matching
      NsOne.n.a1.double_?(Some(double0)).query.get ==> List()
      NsOne.n.a1.double_?(Some(double1)).query.get ==> List(a)
      NsOne.n.a1.double_?(Some(Seq(double0))).query.get ==> List()
      NsOne.n.a1.double_?(Some(Seq(double1))).query.get ==> List(a)
      // OR semantics for Ses of multiple args
      NsOne.n.a1.double_?(Some(Seq(double1, double2))).query.get ==> List(a, b)
      NsOne.n.a1.double_?(Some(Seq(double1, double0))).query.get ==> List(a)
      // Empty Seq of args matches no values
      NsOne.n.a1.double_?(Some(Seq.empty[Double])).query.get ==> List()
      // None matches non-asserted/null values
      NsOne.n.a1.double_?(Option.empty[Double]).query.get ==> List(x)
      NsOne.n.a1.double_?(Option.empty[Seq[Double]]).query.get ==> List(x)

      // Find optional values not matching
      NsOne.n.a1.double_?.not(Some(double0)).query.get ==> List(a, b, c)
      NsOne.n.a1.double_?.not(Some(double1)).query.get ==> List(b, c)
      NsOne.n.a1.double_?.not(Some(double2)).query.get ==> List(a, c)
      NsOne.n.a1.double_?.not(Some(double3)).query.get ==> List(a, b)
      NsOne.n.a1.double_?.not(Some(Seq(double0))).query.get ==> List(a, b, c)
      NsOne.n.a1.double_?.not(Some(Seq(double1))).query.get ==> List(b, c)
      NsOne.n.a1.double_?.not(Some(Seq(double2))).query.get ==> List(a, c)
      NsOne.n.a1.double_?.not(Some(Seq(double3))).query.get ==> List(a, b)
      // OR semantics for multiple negation args
      NsOne.n.a1.double_?.not(Some(Seq(double0, double1))).query.get ==> List(b, c)
      NsOne.n.a1.double_?.not(Some(Seq(double1, double2))).query.get ==> List(c)
      NsOne.n.a1.double_?.not(Some(Seq(double2, double3))).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      NsOne.n.a1.double_?.not(Some(Seq.empty[Double])).query.get ==> List(a, b, c)
      // None matches all asserted values (non-null)
      NsOne.n.a1.double_?.not(Option.empty[Double]).query.get ==> List(a, b, c)
      NsOne.n.a1.double_?.not(Option.empty[Seq[Double]]).query.get ==> List(a, b, c)

      // Find optional values in range
      NsOne.n.a1.double_?.<(Some(double2)).query.get ==> List(a)
      NsOne.n.a1.double_?.>(Some(double2)).query.get ==> List(c)
      NsOne.n.a1.double_?.<=(Some(double2)).query.get ==> List(a, b)
      NsOne.n.a1.double_?.>=(Some(double2)).query.get ==> List(b, c)
      // None can't be compared and returns empty result
      NsOne.n.a1.double_?.<(None).query.get ==> List()
      NsOne.n.a1.double_?.<=(None).query.get ==> List()
      NsOne.n.a1.double_?.>(None).query.get ==> List()
      NsOne.n.a1.double_?.>=(None).query.get ==> List()
    }
  }
}
