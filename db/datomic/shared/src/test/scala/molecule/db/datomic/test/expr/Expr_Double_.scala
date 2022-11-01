// GENERATED CODE ********************************
package molecule.db.datomic.test.expr

import molecule.boilerplate.api.Keywords._
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Expr_Double_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - cardOne { implicit conn =>
      val a = (1, double1)
      val b = (2, double2)
      val c = (3, double3)
      One.n.double.insert(List(a, b, c)).transact

      // Apply args to find matching values
      One.n.a1.double(double0).query.get ==> List()
      One.n.a1.double(double1).query.get ==> List(a)
      One.n.a1.double(double1, double2).query.get ==> List(a, b)
      One.n.a1.double(double1, double0).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.double(Seq(double0)).query.get ==> List()
      One.n.a1.double(Seq(double1)).query.get ==> List(a)
      One.n.a1.double(Seq(double1, double2)).query.get ==> List(a, b)
      One.n.a1.double(Seq(double1, double0)).query.get ==> List(a)
      One.n.a1.double(Seq.empty[Double]).query.get ==> List()


      One.n.a1.double.not(double0).query.get ==> List(a, b, c)
      One.n.a1.double.not(double1).query.get ==> List(b, c)
      One.n.a1.double.not(double2).query.get ==> List(a, c)
      One.n.a1.double.not(double3).query.get ==> List(a, b)
      One.n.a1.double.not(double0, double1).query.get ==> List(b, c)
      One.n.a1.double.not(double1, double2).query.get ==> List(c)
      One.n.a1.double.not(double2, double3).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.double.not(Seq(double0)).query.get ==> List(a, b, c)
      One.n.a1.double.not(Seq(double1)).query.get ==> List(b, c)
      One.n.a1.double.not(Seq(double2)).query.get ==> List(a, c)
      One.n.a1.double.not(Seq(double3)).query.get ==> List(a, b)
      One.n.a1.double.not(Seq(double0, double1)).query.get ==> List(b, c)
      One.n.a1.double.not(Seq(double1, double2)).query.get ==> List(c)
      One.n.a1.double.not(Seq(double2, double3)).query.get ==> List(a)
      One.n.a1.double.not(Seq.empty[Double]).query.get ==> List(a, b, c)


      One.n.a1.double.<(double2).query.get ==> List(a)
      One.n.a1.double.>(double2).query.get ==> List(c)
      One.n.a1.double.<=(double2).query.get ==> List(a, b)
      One.n.a1.double.>=(double2).query.get ==> List(b, c)
    }


    "Tacit" - cardOne { implicit conn =>
      One.n.double_?.insert(List(
        (1, Some(double1)),
        (2, Some(double2)),
        (3, Some(double3)),
        (4, None)
      )).transact

      val a = 1
      val b = 2
      val c = 3
      val d = 4

      // No value asserted
      One.n.a1.double_.apply().query.get ==> List(d)

      One.n.a1.double_(double0).query.get ==> List()
      One.n.a1.double_.apply(double1).query.get ==> List(a)
      One.n.a1.double_(double1, double2).query.get ==> List(a, b)
      One.n.a1.double_(double1, double0).query.get ==> List(a)
      // Same as
      One.n.a1.double_(Seq(double0)).query.get ==> List()
      One.n.a1.double_(Seq(double1)).query.get ==> List(a)
      One.n.a1.double_(Seq(double1, double2)).query.get ==> List(a, b)
      One.n.a1.double_(Seq(double1, double0)).query.get ==> List(a)
      One.n.a1.double_(Seq.empty[Double]).query.get ==> List()


      One.n.a1.double_.not(double0).query.get ==> List(a, b, c)
      One.n.a1.double_.not(double1).query.get ==> List(b, c)
      One.n.a1.double_.not(double2).query.get ==> List(a, c)
      One.n.a1.double_.not(double3).query.get ==> List(a, b)
      One.n.a1.double_.not(double0, double1).query.get ==> List(b, c)
      One.n.a1.double_.not(double1, double2).query.get ==> List(c)
      One.n.a1.double_.not(double2, double3).query.get ==> List(a)
      // Same as
      One.n.a1.double_.not(Seq(double0)).query.get ==> List(a, b, c)
      One.n.a1.double_.not(Seq(double1)).query.get ==> List(b, c)
      One.n.a1.double_.not(Seq(double2)).query.get ==> List(a, c)
      One.n.a1.double_.not(Seq(double3)).query.get ==> List(a, b)
      One.n.a1.double_.not(Seq(double0, double1)).query.get ==> List(b, c)
      One.n.a1.double_.not(Seq(double1, double2)).query.get ==> List(c)
      One.n.a1.double_.not(Seq(double2, double3)).query.get ==> List(a)
      One.n.a1.double_.not(Seq.empty[Double]).query.get ==> List(a, b, c)


      One.n.a1.double_.<(double2).query.get ==> List(a)
      One.n.a1.double_.>(double2).query.get ==> List(c)
      One.n.a1.double_.<=(double2).query.get ==> List(a, b)
      One.n.a1.double_.>=(double2).query.get ==> List(b, c)
    }


    "Optional" - cardOne { implicit conn =>
      val a = (1, Some(double1))
      val b = (2, Some(double2))
      val c = (3, Some(double3))
      val x = (4, Option.empty[Double])

      One.n.double_?.insert(List(a, b, c, x)).transact

      One.n.a1.double_?(Some(double0)).query.get ==> List()
      One.n.a1.double_?.apply(Some(double1)).query.get ==> List(a)

      One.n.a1.double_?(Some(Seq(double0))).query.get ==> List()
      One.n.a1.double_?(Some(Seq(double1))).query.get ==> List(a)
      One.n.a1.double_?(Some(Seq(double1, double2))).query.get ==> List(a, b)
      One.n.a1.double_?(Some(Seq(double1, double0))).query.get ==> List(a)
      One.n.a1.double_?(Some(Seq.empty[Double])).query.get ==> List()

      One.n.a1.double_?(Option.empty[Double]).query.get ==> List(x)
      One.n.a1.double_?(Option.empty[Seq[Double]]).query.get ==> List(x)


      // For non-String cardinality-one attributes, `not` and `!=` have same semantics
      // "Not this value"
      One.n.a1.double_?.not(Some(double0)).query.get ==> List(a, b, c)
      One.n.a1.double_?.not(Some(double1)).query.get ==> List(b, c)
      One.n.a1.double_?.not(Some(double2)).query.get ==> List(a, c)
      One.n.a1.double_?.not(Some(double3)).query.get ==> List(a, b)
      // Same as
      One.n.a1.double_?.not(Some(Seq(double0))).query.get ==> List(a, b, c)
      One.n.a1.double_?.not(Some(Seq(double1))).query.get ==> List(b, c)
      One.n.a1.double_?.not(Some(Seq(double2))).query.get ==> List(a, c)
      One.n.a1.double_?.not(Some(Seq(double3))).query.get ==> List(a, b)

      // "Not this OR that value
      One.n.a1.double_?.not(Some(Seq(double0, double1))).query.get ==> List(b, c)
      One.n.a1.double_?.not(Some(Seq(double1, double2))).query.get ==> List(c)
      One.n.a1.double_?.not(Some(Seq(double2, double3))).query.get ==> List(a)
      One.n.a1.double_?.not(Some(Seq.empty[Double])).query.get ==> List(a, b, c)

      One.n.a1.double_?.not(Option.empty[Double]).query.get ==> List(a, b, c)
      One.n.a1.double_?.not(Option.empty[Seq[Double]]).query.get ==> List(a, b, c)


      One.n.a1.double_?.<(Some(double2)).query.get ==> List(a)
      One.n.a1.double_?.>(Some(double2)).query.get ==> List(c)
      One.n.a1.double_?.<=(Some(double2)).query.get ==> List(a, b)
      One.n.a1.double_?.>=(Some(double2)).query.get ==> List(b, c)

      // None can't be compared
      One.n.a1.double_?.<(None).query.get ==> List()
      One.n.a1.double_?.<=(None).query.get ==> List()
      One.n.a1.double_?.>(None).query.get ==> List()
      One.n.a1.double_?.>=(None).query.get ==> List()
    }
  }
}
