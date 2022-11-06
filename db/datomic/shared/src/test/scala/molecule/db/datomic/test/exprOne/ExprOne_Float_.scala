// GENERATED CODE ********************************
package molecule.db.datomic.test.exprOne

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object ExprOne_Float_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - cardOne { implicit conn =>
      val a = (1, float1)
      val b = (2, float2)
      val c = (3, float3)
      NsOne.n.float.insert(List(a, b, c)).transact

      // Find all attribute values
      NsOne.n.a1.float.query.get ==> List(a, b, c)

      // Find value(s) matching
      NsOne.n.a1.float(float0).query.get ==> List()
      NsOne.n.a1.float(float1).query.get ==> List(a)
      NsOne.n.a1.float(Seq(float0)).query.get ==> List()
      NsOne.n.a1.float(Seq(float1)).query.get ==> List(a)
      // OR semantics for multiple args
      NsOne.n.a1.float(float1, float2).query.get ==> List(a, b)
      NsOne.n.a1.float(float1, float0).query.get ==> List(a)
      NsOne.n.a1.float(Seq(float1, float2)).query.get ==> List(a, b)
      NsOne.n.a1.float(Seq(float1, float0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      NsOne.n.a1.float(Seq.empty[Float]).query.get ==> List()

      // Find values not matching
      NsOne.n.a1.float.not(float0).query.get ==> List(a, b, c)
      NsOne.n.a1.float.not(float1).query.get ==> List(b, c)
      NsOne.n.a1.float.not(float2).query.get ==> List(a, c)
      NsOne.n.a1.float.not(float3).query.get ==> List(a, b)
      NsOne.n.a1.float.not(Seq(float0)).query.get ==> List(a, b, c)
      NsOne.n.a1.float.not(Seq(float1)).query.get ==> List(b, c)
      NsOne.n.a1.float.not(Seq(float2)).query.get ==> List(a, c)
      NsOne.n.a1.float.not(Seq(float3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      NsOne.n.a1.float.not(float0, float1).query.get ==> List(b, c)
      NsOne.n.a1.float.not(float1, float2).query.get ==> List(c)
      NsOne.n.a1.float.not(float2, float3).query.get ==> List(a)
      NsOne.n.a1.float.not(Seq(float0, float1)).query.get ==> List(b, c)
      NsOne.n.a1.float.not(Seq(float1, float2)).query.get ==> List(c)
      NsOne.n.a1.float.not(Seq(float2, float3)).query.get ==> List(a)
      // Empty Seq of negation args matches all values
      NsOne.n.a1.float.not(Seq.empty[Float]).query.get ==> List(a, b, c)

      // Find values in range
      NsOne.n.a1.float.<(float2).query.get ==> List(a)
      NsOne.n.a1.float.>(float2).query.get ==> List(c)
      NsOne.n.a1.float.<=(float2).query.get ==> List(a, b)
      NsOne.n.a1.float.>=(float2).query.get ==> List(b, c)
    }


    "Tacit" - cardOne { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      NsOne.n.float_?.insert(List(
        (a, Some(float1)),
        (b, Some(float2)),
        (c, Some(float3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      NsOne.n.a1.float_.query.get ==> List(a, b, c)

      // Match non-asserted attribute (null)
      NsOne.n.a1.float_().query.get ==> List(x)

      // Match attribute values without returning them
      NsOne.n.a1.float_(float0).query.get ==> List()
      NsOne.n.a1.float_(float1).query.get ==> List(a)
      NsOne.n.a1.float_(Seq(float0)).query.get ==> List()
      NsOne.n.a1.float_(Seq(float1)).query.get ==> List(a)
      // OR semantics for multiple args
      NsOne.n.a1.float_(float1, float2).query.get ==> List(a, b)
      NsOne.n.a1.float_(float1, float0).query.get ==> List(a)
      NsOne.n.a1.float_(Seq(float1, float2)).query.get ==> List(a, b)
      NsOne.n.a1.float_(Seq(float1, float0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      NsOne.n.a1.float_(Seq.empty[Float]).query.get ==> List()

      // Match non-matching values without returning them
      NsOne.n.a1.float_.not(float0).query.get ==> List(a, b, c)
      NsOne.n.a1.float_.not(float1).query.get ==> List(b, c)
      NsOne.n.a1.float_.not(float2).query.get ==> List(a, c)
      NsOne.n.a1.float_.not(float3).query.get ==> List(a, b)
      NsOne.n.a1.float_.not(Seq(float0)).query.get ==> List(a, b, c)
      NsOne.n.a1.float_.not(Seq(float1)).query.get ==> List(b, c)
      NsOne.n.a1.float_.not(Seq(float2)).query.get ==> List(a, c)
      NsOne.n.a1.float_.not(Seq(float3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      NsOne.n.a1.float_.not(float0, float1).query.get ==> List(b, c)
      NsOne.n.a1.float_.not(float1, float2).query.get ==> List(c)
      NsOne.n.a1.float_.not(float2, float3).query.get ==> List(a)
      NsOne.n.a1.float_.not(Seq(float0, float1)).query.get ==> List(b, c)
      NsOne.n.a1.float_.not(Seq(float1, float2)).query.get ==> List(c)
      NsOne.n.a1.float_.not(Seq(float2, float3)).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      NsOne.n.a1.float_.not(Seq.empty[Float]).query.get ==> List(a, b, c)

      // Match value ranges without returning them
      NsOne.n.a1.float_.<(float2).query.get ==> List(a)
      NsOne.n.a1.float_.>(float2).query.get ==> List(c)
      NsOne.n.a1.float_.<=(float2).query.get ==> List(a, b)
      NsOne.n.a1.float_.>=(float2).query.get ==> List(b, c)
    }


    "Optional" - cardOne { implicit conn =>
      val a = (1, Some(float1))
      val b = (2, Some(float2))
      val c = (3, Some(float3))
      val x = (4, Option.empty[Float])
      NsOne.n.float_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      NsOne.n.a1.float_?.query.get ==> List(a, b, c, x)

      // Find optional values matching
      NsOne.n.a1.float_?(Some(float0)).query.get ==> List()
      NsOne.n.a1.float_?(Some(float1)).query.get ==> List(a)
      NsOne.n.a1.float_?(Some(Seq(float0))).query.get ==> List()
      NsOne.n.a1.float_?(Some(Seq(float1))).query.get ==> List(a)
      // OR semantics for Ses of multiple args
      NsOne.n.a1.float_?(Some(Seq(float1, float2))).query.get ==> List(a, b)
      NsOne.n.a1.float_?(Some(Seq(float1, float0))).query.get ==> List(a)
      // Empty Seq of args matches no values
      NsOne.n.a1.float_?(Some(Seq.empty[Float])).query.get ==> List()
      // None matches non-asserted/null values
      NsOne.n.a1.float_?(Option.empty[Float]).query.get ==> List(x)
      NsOne.n.a1.float_?(Option.empty[Seq[Float]]).query.get ==> List(x)

      // Find optional values not matching
      NsOne.n.a1.float_?.not(Some(float0)).query.get ==> List(a, b, c)
      NsOne.n.a1.float_?.not(Some(float1)).query.get ==> List(b, c)
      NsOne.n.a1.float_?.not(Some(float2)).query.get ==> List(a, c)
      NsOne.n.a1.float_?.not(Some(float3)).query.get ==> List(a, b)
      NsOne.n.a1.float_?.not(Some(Seq(float0))).query.get ==> List(a, b, c)
      NsOne.n.a1.float_?.not(Some(Seq(float1))).query.get ==> List(b, c)
      NsOne.n.a1.float_?.not(Some(Seq(float2))).query.get ==> List(a, c)
      NsOne.n.a1.float_?.not(Some(Seq(float3))).query.get ==> List(a, b)
      // OR semantics for multiple negation args
      NsOne.n.a1.float_?.not(Some(Seq(float0, float1))).query.get ==> List(b, c)
      NsOne.n.a1.float_?.not(Some(Seq(float1, float2))).query.get ==> List(c)
      NsOne.n.a1.float_?.not(Some(Seq(float2, float3))).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      NsOne.n.a1.float_?.not(Some(Seq.empty[Float])).query.get ==> List(a, b, c)
      // None matches all asserted values (non-null)
      NsOne.n.a1.float_?.not(Option.empty[Float]).query.get ==> List(a, b, c)
      NsOne.n.a1.float_?.not(Option.empty[Seq[Float]]).query.get ==> List(a, b, c)

      // Find optional values in range
      NsOne.n.a1.float_?.<(Some(float2)).query.get ==> List(a)
      NsOne.n.a1.float_?.>(Some(float2)).query.get ==> List(c)
      NsOne.n.a1.float_?.<=(Some(float2)).query.get ==> List(a, b)
      NsOne.n.a1.float_?.>=(Some(float2)).query.get ==> List(b, c)
      // None can't be compared and returns empty result
      NsOne.n.a1.float_?.<(None).query.get ==> List()
      NsOne.n.a1.float_?.<=(None).query.get ==> List()
      NsOne.n.a1.float_?.>(None).query.get ==> List()
      NsOne.n.a1.float_?.>=(None).query.get ==> List()
    }
  }
}
