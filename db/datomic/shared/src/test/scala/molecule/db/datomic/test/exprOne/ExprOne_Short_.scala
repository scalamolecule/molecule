// GENERATED CODE ********************************
package molecule.db.datomic.test.exprOne

import molecule.coreTests.dataModels.core.types.dsl.TypesOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object ExprOne_Short_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - typesOne { implicit conn =>
      val a = (1, short1)
      val b = (2, short2)
      val c = (3, short3)
      NsOne.n.short.insert(List(a, b, c)).transact

      // Find all attribute values
      NsOne.n.a1.short.query.get ==> List(a, b, c)

      // Find value(s) matching
      NsOne.n.a1.short(short0).query.get ==> List()
      NsOne.n.a1.short(short1).query.get ==> List(a)
      NsOne.n.a1.short(Seq(short0)).query.get ==> List()
      NsOne.n.a1.short(Seq(short1)).query.get ==> List(a)
      // OR semantics for multiple args
      NsOne.n.a1.short(short1, short2).query.get ==> List(a, b)
      NsOne.n.a1.short(short1, short0).query.get ==> List(a)
      NsOne.n.a1.short(Seq(short1, short2)).query.get ==> List(a, b)
      NsOne.n.a1.short(Seq(short1, short0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      NsOne.n.a1.short(Seq.empty[Short]).query.get ==> List()

      // Find values not matching
      NsOne.n.a1.short.not(short0).query.get ==> List(a, b, c)
      NsOne.n.a1.short.not(short1).query.get ==> List(b, c)
      NsOne.n.a1.short.not(short2).query.get ==> List(a, c)
      NsOne.n.a1.short.not(short3).query.get ==> List(a, b)
      NsOne.n.a1.short.not(Seq(short0)).query.get ==> List(a, b, c)
      NsOne.n.a1.short.not(Seq(short1)).query.get ==> List(b, c)
      NsOne.n.a1.short.not(Seq(short2)).query.get ==> List(a, c)
      NsOne.n.a1.short.not(Seq(short3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      NsOne.n.a1.short.not(short0, short1).query.get ==> List(b, c)
      NsOne.n.a1.short.not(short1, short2).query.get ==> List(c)
      NsOne.n.a1.short.not(short2, short3).query.get ==> List(a)
      NsOne.n.a1.short.not(Seq(short0, short1)).query.get ==> List(b, c)
      NsOne.n.a1.short.not(Seq(short1, short2)).query.get ==> List(c)
      NsOne.n.a1.short.not(Seq(short2, short3)).query.get ==> List(a)
      // Empty Seq of negation args matches all values
      NsOne.n.a1.short.not(Seq.empty[Short]).query.get ==> List(a, b, c)

      // Find values in range
      NsOne.n.a1.short.<(short2).query.get ==> List(a)
      NsOne.n.a1.short.>(short2).query.get ==> List(c)
      NsOne.n.a1.short.<=(short2).query.get ==> List(a, b)
      NsOne.n.a1.short.>=(short2).query.get ==> List(b, c)
    }


    "Tacit" - typesOne { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      NsOne.n.short_?.insert(List(
        (a, Some(short1)),
        (b, Some(short2)),
        (c, Some(short3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      NsOne.n.a1.short_.query.get ==> List(a, b, c)

      // Match non-asserted attribute (null)
      NsOne.n.a1.short_().query.get ==> List(x)

      // Match attribute values without returning them
      NsOne.n.a1.short_(short0).query.get ==> List()
      NsOne.n.a1.short_(short1).query.get ==> List(a)
      NsOne.n.a1.short_(Seq(short0)).query.get ==> List()
      NsOne.n.a1.short_(Seq(short1)).query.get ==> List(a)
      // OR semantics for multiple args
      NsOne.n.a1.short_(short1, short2).query.get ==> List(a, b)
      NsOne.n.a1.short_(short1, short0).query.get ==> List(a)
      NsOne.n.a1.short_(Seq(short1, short2)).query.get ==> List(a, b)
      NsOne.n.a1.short_(Seq(short1, short0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      NsOne.n.a1.short_(Seq.empty[Short]).query.get ==> List()

      // Match non-matching values without returning them
      NsOne.n.a1.short_.not(short0).query.get ==> List(a, b, c)
      NsOne.n.a1.short_.not(short1).query.get ==> List(b, c)
      NsOne.n.a1.short_.not(short2).query.get ==> List(a, c)
      NsOne.n.a1.short_.not(short3).query.get ==> List(a, b)
      NsOne.n.a1.short_.not(Seq(short0)).query.get ==> List(a, b, c)
      NsOne.n.a1.short_.not(Seq(short1)).query.get ==> List(b, c)
      NsOne.n.a1.short_.not(Seq(short2)).query.get ==> List(a, c)
      NsOne.n.a1.short_.not(Seq(short3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      NsOne.n.a1.short_.not(short0, short1).query.get ==> List(b, c)
      NsOne.n.a1.short_.not(short1, short2).query.get ==> List(c)
      NsOne.n.a1.short_.not(short2, short3).query.get ==> List(a)
      NsOne.n.a1.short_.not(Seq(short0, short1)).query.get ==> List(b, c)
      NsOne.n.a1.short_.not(Seq(short1, short2)).query.get ==> List(c)
      NsOne.n.a1.short_.not(Seq(short2, short3)).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      NsOne.n.a1.short_.not(Seq.empty[Short]).query.get ==> List(a, b, c)

      // Match value ranges without returning them
      NsOne.n.a1.short_.<(short2).query.get ==> List(a)
      NsOne.n.a1.short_.>(short2).query.get ==> List(c)
      NsOne.n.a1.short_.<=(short2).query.get ==> List(a, b)
      NsOne.n.a1.short_.>=(short2).query.get ==> List(b, c)
    }


    "Optional" - typesOne { implicit conn =>
      val a = (1, Some(short1))
      val b = (2, Some(short2))
      val c = (3, Some(short3))
      val x = (4, Option.empty[Short])
      NsOne.n.short_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      NsOne.n.a1.short_?.query.get ==> List(a, b, c, x)

      // Find optional values matching
      NsOne.n.a1.short_?(Some(short0)).query.get ==> List()
      NsOne.n.a1.short_?(Some(short1)).query.get ==> List(a)
      NsOne.n.a1.short_?(Some(Seq(short0))).query.get ==> List()
      NsOne.n.a1.short_?(Some(Seq(short1))).query.get ==> List(a)
      // OR semantics for Ses of multiple args
      NsOne.n.a1.short_?(Some(Seq(short1, short2))).query.get ==> List(a, b)
      NsOne.n.a1.short_?(Some(Seq(short1, short0))).query.get ==> List(a)
      // Empty Seq of args matches no values
      NsOne.n.a1.short_?(Some(Seq.empty[Short])).query.get ==> List()
      // None matches non-asserted/null values
      NsOne.n.a1.short_?(Option.empty[Short]).query.get ==> List(x)
      NsOne.n.a1.short_?(Option.empty[Seq[Short]]).query.get ==> List(x)

      // Find optional values not matching
      NsOne.n.a1.short_?.not(Some(short0)).query.get ==> List(a, b, c)
      NsOne.n.a1.short_?.not(Some(short1)).query.get ==> List(b, c)
      NsOne.n.a1.short_?.not(Some(short2)).query.get ==> List(a, c)
      NsOne.n.a1.short_?.not(Some(short3)).query.get ==> List(a, b)
      NsOne.n.a1.short_?.not(Some(Seq(short0))).query.get ==> List(a, b, c)
      NsOne.n.a1.short_?.not(Some(Seq(short1))).query.get ==> List(b, c)
      NsOne.n.a1.short_?.not(Some(Seq(short2))).query.get ==> List(a, c)
      NsOne.n.a1.short_?.not(Some(Seq(short3))).query.get ==> List(a, b)
      // OR semantics for multiple negation args
      NsOne.n.a1.short_?.not(Some(Seq(short0, short1))).query.get ==> List(b, c)
      NsOne.n.a1.short_?.not(Some(Seq(short1, short2))).query.get ==> List(c)
      NsOne.n.a1.short_?.not(Some(Seq(short2, short3))).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      NsOne.n.a1.short_?.not(Some(Seq.empty[Short])).query.get ==> List(a, b, c)
      // None matches all asserted values (non-null)
      NsOne.n.a1.short_?.not(Option.empty[Short]).query.get ==> List(a, b, c)
      NsOne.n.a1.short_?.not(Option.empty[Seq[Short]]).query.get ==> List(a, b, c)

      // Find optional values in range
      NsOne.n.a1.short_?.<(Some(short2)).query.get ==> List(a)
      NsOne.n.a1.short_?.>(Some(short2)).query.get ==> List(c)
      NsOne.n.a1.short_?.<=(Some(short2)).query.get ==> List(a, b)
      NsOne.n.a1.short_?.>=(Some(short2)).query.get ==> List(b, c)
      // None can't be compared and returns empty result
      NsOne.n.a1.short_?.<(None).query.get ==> List()
      NsOne.n.a1.short_?.<=(None).query.get ==> List()
      NsOne.n.a1.short_?.>(None).query.get ==> List()
      NsOne.n.a1.short_?.>=(None).query.get ==> List()
    }
  }
}
