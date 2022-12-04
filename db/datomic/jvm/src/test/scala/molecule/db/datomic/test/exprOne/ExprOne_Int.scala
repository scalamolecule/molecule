package molecule.db.datomic.test.exprOne

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object ExprOne_Int extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, int1)
      val b = (2, int2)
      val c = (3, int3)
      Ns.i.int.insert(List(a, b, c)).transact

      // Find all attribute values
      Ns.i.a1.int.query.get ==> List(a, b, c)

      // Find value(s) matching
      Ns.i.a1.int(int0).query.get ==> List()
      Ns.i.a1.int(int1).query.get ==> List(a)
      Ns.i.a1.int(Seq(int0)).query.get ==> List()
      Ns.i.a1.int(Seq(int1)).query.get ==> List(a)
      // OR semantics for multiple args
      Ns.i.a1.int(int1, int2).query.get ==> List(a, b)
      Ns.i.a1.int(int1, int0).query.get ==> List(a)
      Ns.i.a1.int(Seq(int1, int2)).query.get ==> List(a, b)
      Ns.i.a1.int(Seq(int1, int0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      Ns.i.a1.int(Seq.empty[Int]).query.get ==> List()

      // Find values not matching
      Ns.i.a1.int.not(int0).query.get ==> List(a, b, c)
      Ns.i.a1.int.not(int1).query.get ==> List(b, c)
      Ns.i.a1.int.not(int2).query.get ==> List(a, c)
      Ns.i.a1.int.not(int3).query.get ==> List(a, b)
      Ns.i.a1.int.not(Seq(int0)).query.get ==> List(a, b, c)
      Ns.i.a1.int.not(Seq(int1)).query.get ==> List(b, c)
      Ns.i.a1.int.not(Seq(int2)).query.get ==> List(a, c)
      Ns.i.a1.int.not(Seq(int3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      Ns.i.a1.int.not(int0, int1).query.get ==> List(b, c)
      Ns.i.a1.int.not(int1, int2).query.get ==> List(c)
      Ns.i.a1.int.not(int2, int3).query.get ==> List(a)
      Ns.i.a1.int.not(Seq(int0, int1)).query.get ==> List(b, c)
      Ns.i.a1.int.not(Seq(int1, int2)).query.get ==> List(c)
      Ns.i.a1.int.not(Seq(int2, int3)).query.get ==> List(a)
      // Empty Seq of negation args matches all values
      Ns.i.a1.int.not(Seq.empty[Int]).query.get ==> List(a, b, c)

      // Find values in range
      Ns.i.a1.int.<(int2).query.get ==> List(a)
      Ns.i.a1.int.>(int2).query.get ==> List(c)
      Ns.i.a1.int.<=(int2).query.get ==> List(a, b)
      Ns.i.a1.int.>=(int2).query.get ==> List(b, c)
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      Ns.i.int_?.insert(List(
        (a, Some(int1)),
        (b, Some(int2)),
        (c, Some(int3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      Ns.i.a1.int_.query.get ==> List(a, b, c)

      // Match non-asserted attribute (null)
      Ns.i.a1.int_().query.get ==> List(x)

      // Match attribute values without returning them
      Ns.i.a1.int_(int0).query.get ==> List()
      Ns.i.a1.int_(int1).query.get ==> List(a)
      Ns.i.a1.int_(Seq(int0)).query.get ==> List()
      Ns.i.a1.int_(Seq(int1)).query.get ==> List(a)
      // OR semantics for multiple args
      Ns.i.a1.int_(int1, int2).query.get ==> List(a, b)
      Ns.i.a1.int_(int1, int0).query.get ==> List(a)
      Ns.i.a1.int_(Seq(int1, int2)).query.get ==> List(a, b)
      Ns.i.a1.int_(Seq(int1, int0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      Ns.i.a1.int_(Seq.empty[Int]).query.get ==> List()

      // Match non-matching values without returning them
      Ns.i.a1.int_.not(int0).query.get ==> List(a, b, c)
      Ns.i.a1.int_.not(int1).query.get ==> List(b, c)
      Ns.i.a1.int_.not(int2).query.get ==> List(a, c)
      Ns.i.a1.int_.not(int3).query.get ==> List(a, b)
      Ns.i.a1.int_.not(Seq(int0)).query.get ==> List(a, b, c)
      Ns.i.a1.int_.not(Seq(int1)).query.get ==> List(b, c)
      Ns.i.a1.int_.not(Seq(int2)).query.get ==> List(a, c)
      Ns.i.a1.int_.not(Seq(int3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      Ns.i.a1.int_.not(int0, int1).query.get ==> List(b, c)
      Ns.i.a1.int_.not(int1, int2).query.get ==> List(c)
      Ns.i.a1.int_.not(int2, int3).query.get ==> List(a)
      Ns.i.a1.int_.not(Seq(int0, int1)).query.get ==> List(b, c)
      Ns.i.a1.int_.not(Seq(int1, int2)).query.get ==> List(c)
      Ns.i.a1.int_.not(Seq(int2, int3)).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      Ns.i.a1.int_.not(Seq.empty[Int]).query.get ==> List(a, b, c)

      // Match value ranges without returning them
      Ns.i.a1.int_.<(int2).query.get ==> List(a)
      Ns.i.a1.int_.>(int2).query.get ==> List(c)
      Ns.i.a1.int_.<=(int2).query.get ==> List(a, b)
      Ns.i.a1.int_.>=(int2).query.get ==> List(b, c)
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(int1))
      val b = (2, Some(int2))
      val c = (3, Some(int3))
      val x = (4, Option.empty[Int])
      Ns.i.int_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      Ns.i.a1.int_?.query.get ==> List(a, b, c, x)

      // Find optional values matching
      Ns.i.a1.int_?(Some(int0)).query.get ==> List()
      Ns.i.a1.int_?(Some(int1)).query.get ==> List(a)
      Ns.i.a1.int_?(Some(Seq(int0))).query.get ==> List()
      Ns.i.a1.int_?(Some(Seq(int1))).query.get ==> List(a)
      // OR semantics for Ses of multiple args
      Ns.i.a1.int_?(Some(Seq(int1, int2))).query.get ==> List(a, b)
      Ns.i.a1.int_?(Some(Seq(int1, int0))).query.get ==> List(a)
      // Empty Seq of args matches no values
      Ns.i.a1.int_?(Some(Seq.empty[Int])).query.get ==> List()
      // None matches non-asserted/null values
      Ns.i.a1.int_?(Option.empty[Int]).query.get ==> List(x)
      Ns.i.a1.int_?(Option.empty[Seq[Int]]).query.get ==> List(x)

      // Find optional values not matching
      Ns.i.a1.int_?.not(Some(int0)).query.get ==> List(a, b, c)
      Ns.i.a1.int_?.not(Some(int1)).query.get ==> List(b, c)
      Ns.i.a1.int_?.not(Some(int2)).query.get ==> List(a, c)
      Ns.i.a1.int_?.not(Some(int3)).query.get ==> List(a, b)
      Ns.i.a1.int_?.not(Some(Seq(int0))).query.get ==> List(a, b, c)
      Ns.i.a1.int_?.not(Some(Seq(int1))).query.get ==> List(b, c)
      Ns.i.a1.int_?.not(Some(Seq(int2))).query.get ==> List(a, c)
      Ns.i.a1.int_?.not(Some(Seq(int3))).query.get ==> List(a, b)
      // OR semantics for multiple negation args
      Ns.i.a1.int_?.not(Some(Seq(int0, int1))).query.get ==> List(b, c)
      Ns.i.a1.int_?.not(Some(Seq(int1, int2))).query.get ==> List(c)
      Ns.i.a1.int_?.not(Some(Seq(int2, int3))).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      Ns.i.a1.int_?.not(Some(Seq.empty[Int])).query.get ==> List(a, b, c)
      // None matches all asserted values (non-null)
      Ns.i.a1.int_?.not(Option.empty[Int]).query.get ==> List(a, b, c)
      Ns.i.a1.int_?.not(Option.empty[Seq[Int]]).query.get ==> List(a, b, c)

      // Find optional values in range
      Ns.i.a1.int_?.<(Some(int2)).query.get ==> List(a)
      Ns.i.a1.int_?.>(Some(int2)).query.get ==> List(c)
      Ns.i.a1.int_?.<=(Some(int2)).query.get ==> List(a, b)
      Ns.i.a1.int_?.>=(Some(int2)).query.get ==> List(b, c)
      // None can't be compared and returns empty result
      Ns.i.a1.int_?.<(None).query.get ==> List()
      Ns.i.a1.int_?.<=(None).query.get ==> List()
      Ns.i.a1.int_?.>(None).query.get ==> List()
      Ns.i.a1.int_?.>=(None).query.get ==> List()
    }
  }
}
