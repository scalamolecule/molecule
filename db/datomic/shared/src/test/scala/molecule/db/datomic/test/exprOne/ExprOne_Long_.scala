// GENERATED CODE ********************************
package molecule.db.datomic.test.exprOne

import molecule.coreTests.dataModels.core.types.dsl.TypesOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object ExprOne_Long_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - typesOne { implicit conn =>
      val a = (1, long1)
      val b = (2, long2)
      val c = (3, long3)
      NsOne.n.long.insert(List(a, b, c)).transact

      // Find all attribute values
      NsOne.n.a1.long.query.get ==> List(a, b, c)

      // Find value(s) matching
      NsOne.n.a1.long(long0).query.get ==> List()
      NsOne.n.a1.long(long1).query.get ==> List(a)
      NsOne.n.a1.long(Seq(long0)).query.get ==> List()
      NsOne.n.a1.long(Seq(long1)).query.get ==> List(a)
      // OR semantics for multiple args
      NsOne.n.a1.long(long1, long2).query.get ==> List(a, b)
      NsOne.n.a1.long(long1, long0).query.get ==> List(a)
      NsOne.n.a1.long(Seq(long1, long2)).query.get ==> List(a, b)
      NsOne.n.a1.long(Seq(long1, long0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      NsOne.n.a1.long(Seq.empty[Long]).query.get ==> List()

      // Find values not matching
      NsOne.n.a1.long.not(long0).query.get ==> List(a, b, c)
      NsOne.n.a1.long.not(long1).query.get ==> List(b, c)
      NsOne.n.a1.long.not(long2).query.get ==> List(a, c)
      NsOne.n.a1.long.not(long3).query.get ==> List(a, b)
      NsOne.n.a1.long.not(Seq(long0)).query.get ==> List(a, b, c)
      NsOne.n.a1.long.not(Seq(long1)).query.get ==> List(b, c)
      NsOne.n.a1.long.not(Seq(long2)).query.get ==> List(a, c)
      NsOne.n.a1.long.not(Seq(long3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      NsOne.n.a1.long.not(long0, long1).query.get ==> List(b, c)
      NsOne.n.a1.long.not(long1, long2).query.get ==> List(c)
      NsOne.n.a1.long.not(long2, long3).query.get ==> List(a)
      NsOne.n.a1.long.not(Seq(long0, long1)).query.get ==> List(b, c)
      NsOne.n.a1.long.not(Seq(long1, long2)).query.get ==> List(c)
      NsOne.n.a1.long.not(Seq(long2, long3)).query.get ==> List(a)
      // Empty Seq of negation args matches all values
      NsOne.n.a1.long.not(Seq.empty[Long]).query.get ==> List(a, b, c)

      // Find values in range
      NsOne.n.a1.long.<(long2).query.get ==> List(a)
      NsOne.n.a1.long.>(long2).query.get ==> List(c)
      NsOne.n.a1.long.<=(long2).query.get ==> List(a, b)
      NsOne.n.a1.long.>=(long2).query.get ==> List(b, c)
    }


    "Tacit" - typesOne { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      NsOne.n.long_?.insert(List(
        (a, Some(long1)),
        (b, Some(long2)),
        (c, Some(long3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      NsOne.n.a1.long_.query.get ==> List(a, b, c)

      // Match non-asserted attribute (null)
      NsOne.n.a1.long_().query.get ==> List(x)

      // Match attribute values without returning them
      NsOne.n.a1.long_(long0).query.get ==> List()
      NsOne.n.a1.long_(long1).query.get ==> List(a)
      NsOne.n.a1.long_(Seq(long0)).query.get ==> List()
      NsOne.n.a1.long_(Seq(long1)).query.get ==> List(a)
      // OR semantics for multiple args
      NsOne.n.a1.long_(long1, long2).query.get ==> List(a, b)
      NsOne.n.a1.long_(long1, long0).query.get ==> List(a)
      NsOne.n.a1.long_(Seq(long1, long2)).query.get ==> List(a, b)
      NsOne.n.a1.long_(Seq(long1, long0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      NsOne.n.a1.long_(Seq.empty[Long]).query.get ==> List()

      // Match non-matching values without returning them
      NsOne.n.a1.long_.not(long0).query.get ==> List(a, b, c)
      NsOne.n.a1.long_.not(long1).query.get ==> List(b, c)
      NsOne.n.a1.long_.not(long2).query.get ==> List(a, c)
      NsOne.n.a1.long_.not(long3).query.get ==> List(a, b)
      NsOne.n.a1.long_.not(Seq(long0)).query.get ==> List(a, b, c)
      NsOne.n.a1.long_.not(Seq(long1)).query.get ==> List(b, c)
      NsOne.n.a1.long_.not(Seq(long2)).query.get ==> List(a, c)
      NsOne.n.a1.long_.not(Seq(long3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      NsOne.n.a1.long_.not(long0, long1).query.get ==> List(b, c)
      NsOne.n.a1.long_.not(long1, long2).query.get ==> List(c)
      NsOne.n.a1.long_.not(long2, long3).query.get ==> List(a)
      NsOne.n.a1.long_.not(Seq(long0, long1)).query.get ==> List(b, c)
      NsOne.n.a1.long_.not(Seq(long1, long2)).query.get ==> List(c)
      NsOne.n.a1.long_.not(Seq(long2, long3)).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      NsOne.n.a1.long_.not(Seq.empty[Long]).query.get ==> List(a, b, c)

      // Match value ranges without returning them
      NsOne.n.a1.long_.<(long2).query.get ==> List(a)
      NsOne.n.a1.long_.>(long2).query.get ==> List(c)
      NsOne.n.a1.long_.<=(long2).query.get ==> List(a, b)
      NsOne.n.a1.long_.>=(long2).query.get ==> List(b, c)
    }


    "Optional" - typesOne { implicit conn =>
      val a = (1, Some(long1))
      val b = (2, Some(long2))
      val c = (3, Some(long3))
      val x = (4, Option.empty[Long])
      NsOne.n.long_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      NsOne.n.a1.long_?.query.get ==> List(a, b, c, x)

      // Find optional values matching
      NsOne.n.a1.long_?(Some(long0)).query.get ==> List()
      NsOne.n.a1.long_?(Some(long1)).query.get ==> List(a)
      NsOne.n.a1.long_?(Some(Seq(long0))).query.get ==> List()
      NsOne.n.a1.long_?(Some(Seq(long1))).query.get ==> List(a)
      // OR semantics for Ses of multiple args
      NsOne.n.a1.long_?(Some(Seq(long1, long2))).query.get ==> List(a, b)
      NsOne.n.a1.long_?(Some(Seq(long1, long0))).query.get ==> List(a)
      // Empty Seq of args matches no values
      NsOne.n.a1.long_?(Some(Seq.empty[Long])).query.get ==> List()
      // None matches non-asserted/null values
      NsOne.n.a1.long_?(Option.empty[Long]).query.get ==> List(x)
      NsOne.n.a1.long_?(Option.empty[Seq[Long]]).query.get ==> List(x)

      // Find optional values not matching
      NsOne.n.a1.long_?.not(Some(long0)).query.get ==> List(a, b, c)
      NsOne.n.a1.long_?.not(Some(long1)).query.get ==> List(b, c)
      NsOne.n.a1.long_?.not(Some(long2)).query.get ==> List(a, c)
      NsOne.n.a1.long_?.not(Some(long3)).query.get ==> List(a, b)
      NsOne.n.a1.long_?.not(Some(Seq(long0))).query.get ==> List(a, b, c)
      NsOne.n.a1.long_?.not(Some(Seq(long1))).query.get ==> List(b, c)
      NsOne.n.a1.long_?.not(Some(Seq(long2))).query.get ==> List(a, c)
      NsOne.n.a1.long_?.not(Some(Seq(long3))).query.get ==> List(a, b)
      // OR semantics for multiple negation args
      NsOne.n.a1.long_?.not(Some(Seq(long0, long1))).query.get ==> List(b, c)
      NsOne.n.a1.long_?.not(Some(Seq(long1, long2))).query.get ==> List(c)
      NsOne.n.a1.long_?.not(Some(Seq(long2, long3))).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      NsOne.n.a1.long_?.not(Some(Seq.empty[Long])).query.get ==> List(a, b, c)
      // None matches all asserted values (non-null)
      NsOne.n.a1.long_?.not(Option.empty[Long]).query.get ==> List(a, b, c)
      NsOne.n.a1.long_?.not(Option.empty[Seq[Long]]).query.get ==> List(a, b, c)

      // Find optional values in range
      NsOne.n.a1.long_?.<(Some(long2)).query.get ==> List(a)
      NsOne.n.a1.long_?.>(Some(long2)).query.get ==> List(c)
      NsOne.n.a1.long_?.<=(Some(long2)).query.get ==> List(a, b)
      NsOne.n.a1.long_?.>=(Some(long2)).query.get ==> List(b, c)
      // None can't be compared and returns empty result
      NsOne.n.a1.long_?.<(None).query.get ==> List()
      NsOne.n.a1.long_?.<=(None).query.get ==> List()
      NsOne.n.a1.long_?.>(None).query.get ==> List()
      NsOne.n.a1.long_?.>=(None).query.get ==> List()
    }
  }
}
