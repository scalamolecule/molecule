package molecule.db.datomic.test.expr

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object ExprOne_Int extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - cardOne { implicit conn =>
      val a = (1, int1)
      val b = (2, int2)
      val c = (3, int3)
      One.n.int.insert(List(a, b, c)).transact

      // Apply args to find matching values
      One.n.a1.int(int0).query.get ==> List()
      One.n.a1.int(int1).query.get ==> List(a)
      One.n.a1.int(int1, int2).query.get ==> List(a, b)
      One.n.a1.int(int1, int0).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.int(Seq(int0)).query.get ==> List()
      One.n.a1.int(Seq(int1)).query.get ==> List(a)
      One.n.a1.int(Seq(int1, int2)).query.get ==> List(a, b)
      One.n.a1.int(Seq(int1, int0)).query.get ==> List(a)
      One.n.a1.int(Seq.empty[Int]).query.get ==> List()


      One.n.a1.int.not(int0).query.get ==> List(a, b, c)
      One.n.a1.int.not(int1).query.get ==> List(b, c)
      One.n.a1.int.not(int2).query.get ==> List(a, c)
      One.n.a1.int.not(int3).query.get ==> List(a, b)
      One.n.a1.int.not(int0, int1).query.get ==> List(b, c)
      One.n.a1.int.not(int1, int2).query.get ==> List(c)
      One.n.a1.int.not(int2, int3).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.int.not(Seq(int0)).query.get ==> List(a, b, c)
      One.n.a1.int.not(Seq(int1)).query.get ==> List(b, c)
      One.n.a1.int.not(Seq(int2)).query.get ==> List(a, c)
      One.n.a1.int.not(Seq(int3)).query.get ==> List(a, b)
      One.n.a1.int.not(Seq(int0, int1)).query.get ==> List(b, c)
      One.n.a1.int.not(Seq(int1, int2)).query.get ==> List(c)
      One.n.a1.int.not(Seq(int2, int3)).query.get ==> List(a)
      One.n.a1.int.not(Seq.empty[Int]).query.get ==> List(a, b, c)


      One.n.a1.int.<(int2).query.get ==> List(a)
      One.n.a1.int.>(int2).query.get ==> List(c)
      One.n.a1.int.<=(int2).query.get ==> List(a, b)
      One.n.a1.int.>=(int2).query.get ==> List(b, c)
    }


    "Tacit" - cardOne { implicit conn =>
      One.n.int_?.insert(List(
        (1, Some(int1)),
        (2, Some(int2)),
        (3, Some(int3)),
        (4, None)
      )).transact

      val a = 1
      val b = 2
      val c = 3
      val d = 4

      // No value asserted
      One.n.a1.int_.apply().query.get ==> List(d)

      One.n.a1.int_(int0).query.get ==> List()
      One.n.a1.int_.apply(int1).query.get ==> List(a)
      One.n.a1.int_(int1, int2).query.get ==> List(a, b)
      One.n.a1.int_(int1, int0).query.get ==> List(a)
      // Same as
      One.n.a1.int_(Seq(int0)).query.get ==> List()
      One.n.a1.int_(Seq(int1)).query.get ==> List(a)
      One.n.a1.int_(Seq(int1, int2)).query.get ==> List(a, b)
      One.n.a1.int_(Seq(int1, int0)).query.get ==> List(a)
      One.n.a1.int_(Seq.empty[Int]).query.get ==> List()


      One.n.a1.int_.not(int0).query.get ==> List(a, b, c)
      One.n.a1.int_.not(int1).query.get ==> List(b, c)
      One.n.a1.int_.not(int2).query.get ==> List(a, c)
      One.n.a1.int_.not(int3).query.get ==> List(a, b)
      One.n.a1.int_.not(int0, int1).query.get ==> List(b, c)
      One.n.a1.int_.not(int1, int2).query.get ==> List(c)
      One.n.a1.int_.not(int2, int3).query.get ==> List(a)
      // Same as
      One.n.a1.int_.not(Seq(int0)).query.get ==> List(a, b, c)
      One.n.a1.int_.not(Seq(int1)).query.get ==> List(b, c)
      One.n.a1.int_.not(Seq(int2)).query.get ==> List(a, c)
      One.n.a1.int_.not(Seq(int3)).query.get ==> List(a, b)
      One.n.a1.int_.not(Seq(int0, int1)).query.get ==> List(b, c)
      One.n.a1.int_.not(Seq(int1, int2)).query.get ==> List(c)
      One.n.a1.int_.not(Seq(int2, int3)).query.get ==> List(a)
      One.n.a1.int_.not(Seq.empty[Int]).query.get ==> List(a, b, c)


      One.n.a1.int_.<(int2).query.get ==> List(a)
      One.n.a1.int_.>(int2).query.get ==> List(c)
      One.n.a1.int_.<=(int2).query.get ==> List(a, b)
      One.n.a1.int_.>=(int2).query.get ==> List(b, c)
    }


    "Optional" - cardOne { implicit conn =>
      val a = (1, Some(int1))
      val b = (2, Some(int2))
      val c = (3, Some(int3))
      val x = (4, Option.empty[Int])

      One.n.int_?.insert(List(a, b, c, x)).transact

      One.n.a1.int_?(Some(int0)).query.get ==> List()
      One.n.a1.int_?.apply(Some(int1)).query.get ==> List(a)

      One.n.a1.int_?(Some(Seq(int0))).query.get ==> List()
      One.n.a1.int_?(Some(Seq(int1))).query.get ==> List(a)
      One.n.a1.int_?(Some(Seq(int1, int2))).query.get ==> List(a, b)
      One.n.a1.int_?(Some(Seq(int1, int0))).query.get ==> List(a)
      One.n.a1.int_?(Some(Seq.empty[Int])).query.get ==> List()

      One.n.a1.int_?(Option.empty[Int]).query.get ==> List(x)
      One.n.a1.int_?(Option.empty[Seq[Int]]).query.get ==> List(x)


      // For non-String cardinality-one attributes, `not` and `!=` have same semantics
      // "Not this value"
      One.n.a1.int_?.not(Some(int0)).query.get ==> List(a, b, c)
      One.n.a1.int_?.not(Some(int1)).query.get ==> List(b, c)
      One.n.a1.int_?.not(Some(int2)).query.get ==> List(a, c)
      One.n.a1.int_?.not(Some(int3)).query.get ==> List(a, b)
      // Same as
      One.n.a1.int_?.not(Some(Seq(int0))).query.get ==> List(a, b, c)
      One.n.a1.int_?.not(Some(Seq(int1))).query.get ==> List(b, c)
      One.n.a1.int_?.not(Some(Seq(int2))).query.get ==> List(a, c)
      One.n.a1.int_?.not(Some(Seq(int3))).query.get ==> List(a, b)

      // "Not this OR that value
      One.n.a1.int_?.not(Some(Seq(int0, int1))).query.get ==> List(b, c)
      One.n.a1.int_?.not(Some(Seq(int1, int2))).query.get ==> List(c)
      One.n.a1.int_?.not(Some(Seq(int2, int3))).query.get ==> List(a)
      One.n.a1.int_?.not(Some(Seq.empty[Int])).query.get ==> List(a, b, c)

      One.n.a1.int_?.not(Option.empty[Int]).query.get ==> List(a, b, c)
      One.n.a1.int_?.not(Option.empty[Seq[Int]]).query.get ==> List(a, b, c)


      One.n.a1.int_?.<(Some(int2)).query.get ==> List(a)
      One.n.a1.int_?.>(Some(int2)).query.get ==> List(c)
      One.n.a1.int_?.<=(Some(int2)).query.get ==> List(a, b)
      One.n.a1.int_?.>=(Some(int2)).query.get ==> List(b, c)

      // None can't be compared
      One.n.a1.int_?.<(None).query.get ==> List()
      One.n.a1.int_?.<=(None).query.get ==> List()
      One.n.a1.int_?.>(None).query.get ==> List()
      One.n.a1.int_?.>=(None).query.get ==> List()
    }
  }
}
