// GENERATED CODE ********************************
package molecule.db.datomic.test.expr

import molecule.boilerplate.api.Keywords._
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Expr_Long_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - cardOne { implicit conn =>
      val a = (1, long1)
      val b = (2, long2)
      val c = (3, long3)
      One.n.long.insert(List(a, b, c)).transact

      // Apply args to find matching values
      One.n.a1.long(long0).query.get ==> List()
      One.n.a1.long(long1).query.get ==> List(a)
      One.n.a1.long(long1, long2).query.get ==> List(a, b)
      One.n.a1.long(long1, long0).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.long(Seq(long0)).query.get ==> List()
      One.n.a1.long(Seq(long1)).query.get ==> List(a)
      One.n.a1.long(Seq(long1, long2)).query.get ==> List(a, b)
      One.n.a1.long(Seq(long1, long0)).query.get ==> List(a)
      One.n.a1.long(Seq.empty[Long]).query.get ==> List()


      One.n.a1.long.not(long0).query.get ==> List(a, b, c)
      One.n.a1.long.not(long1).query.get ==> List(b, c)
      One.n.a1.long.not(long2).query.get ==> List(a, c)
      One.n.a1.long.not(long3).query.get ==> List(a, b)
      One.n.a1.long.not(long0, long1).query.get ==> List(b, c)
      One.n.a1.long.not(long1, long2).query.get ==> List(c)
      One.n.a1.long.not(long2, long3).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.long.not(Seq(long0)).query.get ==> List(a, b, c)
      One.n.a1.long.not(Seq(long1)).query.get ==> List(b, c)
      One.n.a1.long.not(Seq(long2)).query.get ==> List(a, c)
      One.n.a1.long.not(Seq(long3)).query.get ==> List(a, b)
      One.n.a1.long.not(Seq(long0, long1)).query.get ==> List(b, c)
      One.n.a1.long.not(Seq(long1, long2)).query.get ==> List(c)
      One.n.a1.long.not(Seq(long2, long3)).query.get ==> List(a)
      One.n.a1.long.not(Seq.empty[Long]).query.get ==> List(a, b, c)


      One.n.a1.long.<(long2).query.get ==> List(a)
      One.n.a1.long.>(long2).query.get ==> List(c)
      One.n.a1.long.<=(long2).query.get ==> List(a, b)
      One.n.a1.long.>=(long2).query.get ==> List(b, c)
    }


    "Tacit" - cardOne { implicit conn =>
      One.n.long_?.insert(List(
        (1, Some(long1)),
        (2, Some(long2)),
        (3, Some(long3)),
        (4, None)
      )).transact

      val a = 1
      val b = 2
      val c = 3
      val d = 4

      // No value asserted
      One.n.a1.long_.apply().query.get ==> List(d)

      One.n.a1.long_(long0).query.get ==> List()
      One.n.a1.long_.apply(long1).query.get ==> List(a)
      One.n.a1.long_(long1, long2).query.get ==> List(a, b)
      One.n.a1.long_(long1, long0).query.get ==> List(a)
      // Same as
      One.n.a1.long_(Seq(long0)).query.get ==> List()
      One.n.a1.long_(Seq(long1)).query.get ==> List(a)
      One.n.a1.long_(Seq(long1, long2)).query.get ==> List(a, b)
      One.n.a1.long_(Seq(long1, long0)).query.get ==> List(a)
      One.n.a1.long_(Seq.empty[Long]).query.get ==> List()


      One.n.a1.long_.not(long0).query.get ==> List(a, b, c)
      One.n.a1.long_.not(long1).query.get ==> List(b, c)
      One.n.a1.long_.not(long2).query.get ==> List(a, c)
      One.n.a1.long_.not(long3).query.get ==> List(a, b)
      One.n.a1.long_.not(long0, long1).query.get ==> List(b, c)
      One.n.a1.long_.not(long1, long2).query.get ==> List(c)
      One.n.a1.long_.not(long2, long3).query.get ==> List(a)
      // Same as
      One.n.a1.long_.not(Seq(long0)).query.get ==> List(a, b, c)
      One.n.a1.long_.not(Seq(long1)).query.get ==> List(b, c)
      One.n.a1.long_.not(Seq(long2)).query.get ==> List(a, c)
      One.n.a1.long_.not(Seq(long3)).query.get ==> List(a, b)
      One.n.a1.long_.not(Seq(long0, long1)).query.get ==> List(b, c)
      One.n.a1.long_.not(Seq(long1, long2)).query.get ==> List(c)
      One.n.a1.long_.not(Seq(long2, long3)).query.get ==> List(a)
      One.n.a1.long_.not(Seq.empty[Long]).query.get ==> List(a, b, c)


      One.n.a1.long_.<(long2).query.get ==> List(a)
      One.n.a1.long_.>(long2).query.get ==> List(c)
      One.n.a1.long_.<=(long2).query.get ==> List(a, b)
      One.n.a1.long_.>=(long2).query.get ==> List(b, c)
    }


    "Optional" - cardOne { implicit conn =>
      val a = (1, Some(long1))
      val b = (2, Some(long2))
      val c = (3, Some(long3))
      val x = (4, Option.empty[Long])

      One.n.long_?.insert(List(a, b, c, x)).transact

      One.n.a1.long_?(Some(long0)).query.get ==> List()
      One.n.a1.long_?.apply(Some(long1)).query.get ==> List(a)

      One.n.a1.long_?(Some(Seq(long0))).query.get ==> List()
      One.n.a1.long_?(Some(Seq(long1))).query.get ==> List(a)
      One.n.a1.long_?(Some(Seq(long1, long2))).query.get ==> List(a, b)
      One.n.a1.long_?(Some(Seq(long1, long0))).query.get ==> List(a)
      One.n.a1.long_?(Some(Seq.empty[Long])).query.get ==> List()

      One.n.a1.long_?(Option.empty[Long]).query.get ==> List(x)
      One.n.a1.long_?(Option.empty[Seq[Long]]).query.get ==> List(x)


      // For non-String cardinality-one attributes, `not` and `!=` have same semantics
      // "Not this value"
      One.n.a1.long_?.not(Some(long0)).query.get ==> List(a, b, c)
      One.n.a1.long_?.not(Some(long1)).query.get ==> List(b, c)
      One.n.a1.long_?.not(Some(long2)).query.get ==> List(a, c)
      One.n.a1.long_?.not(Some(long3)).query.get ==> List(a, b)
      // Same as
      One.n.a1.long_?.not(Some(Seq(long0))).query.get ==> List(a, b, c)
      One.n.a1.long_?.not(Some(Seq(long1))).query.get ==> List(b, c)
      One.n.a1.long_?.not(Some(Seq(long2))).query.get ==> List(a, c)
      One.n.a1.long_?.not(Some(Seq(long3))).query.get ==> List(a, b)

      // "Not this OR that value
      One.n.a1.long_?.not(Some(Seq(long0, long1))).query.get ==> List(b, c)
      One.n.a1.long_?.not(Some(Seq(long1, long2))).query.get ==> List(c)
      One.n.a1.long_?.not(Some(Seq(long2, long3))).query.get ==> List(a)
      One.n.a1.long_?.not(Some(Seq.empty[Long])).query.get ==> List(a, b, c)

      One.n.a1.long_?.not(Option.empty[Long]).query.get ==> List(a, b, c)
      One.n.a1.long_?.not(Option.empty[Seq[Long]]).query.get ==> List(a, b, c)


      One.n.a1.long_?.<(Some(long2)).query.get ==> List(a)
      One.n.a1.long_?.>(Some(long2)).query.get ==> List(c)
      One.n.a1.long_?.<=(Some(long2)).query.get ==> List(a, b)
      One.n.a1.long_?.>=(Some(long2)).query.get ==> List(b, c)

      // None can't be compared
      One.n.a1.long_?.<(None).query.get ==> List()
      One.n.a1.long_?.<=(None).query.get ==> List()
      One.n.a1.long_?.>(None).query.get ==> List()
      One.n.a1.long_?.>=(None).query.get ==> List()
    }
  }
}
