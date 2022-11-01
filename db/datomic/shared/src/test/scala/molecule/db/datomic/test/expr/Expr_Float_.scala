// GENERATED CODE ********************************
package molecule.db.datomic.test.expr

import molecule.boilerplate.api.Keywords._
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Expr_Float_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - cardOne { implicit conn =>
      val a = (1, float1)
      val b = (2, float2)
      val c = (3, float3)
      One.n.float.insert(List(a, b, c)).transact

      // Apply args to find matching values
      One.n.a1.float(float0).query.get ==> List()
      One.n.a1.float(float1).query.get ==> List(a)
      One.n.a1.float(float1, float2).query.get ==> List(a, b)
      One.n.a1.float(float1, float0).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.float(Seq(float0)).query.get ==> List()
      One.n.a1.float(Seq(float1)).query.get ==> List(a)
      One.n.a1.float(Seq(float1, float2)).query.get ==> List(a, b)
      One.n.a1.float(Seq(float1, float0)).query.get ==> List(a)
      One.n.a1.float(Seq.empty[Float]).query.get ==> List()


      One.n.a1.float.not(float0).query.get ==> List(a, b, c)
      One.n.a1.float.not(float1).query.get ==> List(b, c)
      One.n.a1.float.not(float2).query.get ==> List(a, c)
      One.n.a1.float.not(float3).query.get ==> List(a, b)
      One.n.a1.float.not(float0, float1).query.get ==> List(b, c)
      One.n.a1.float.not(float1, float2).query.get ==> List(c)
      One.n.a1.float.not(float2, float3).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.float.not(Seq(float0)).query.get ==> List(a, b, c)
      One.n.a1.float.not(Seq(float1)).query.get ==> List(b, c)
      One.n.a1.float.not(Seq(float2)).query.get ==> List(a, c)
      One.n.a1.float.not(Seq(float3)).query.get ==> List(a, b)
      One.n.a1.float.not(Seq(float0, float1)).query.get ==> List(b, c)
      One.n.a1.float.not(Seq(float1, float2)).query.get ==> List(c)
      One.n.a1.float.not(Seq(float2, float3)).query.get ==> List(a)
      One.n.a1.float.not(Seq.empty[Float]).query.get ==> List(a, b, c)


      One.n.a1.float.<(float2).query.get ==> List(a)
      One.n.a1.float.>(float2).query.get ==> List(c)
      One.n.a1.float.<=(float2).query.get ==> List(a, b)
      One.n.a1.float.>=(float2).query.get ==> List(b, c)
    }


    "Tacit" - cardOne { implicit conn =>
      One.n.float_?.insert(List(
        (1, Some(float1)),
        (2, Some(float2)),
        (3, Some(float3)),
        (4, None)
      )).transact

      val a = 1
      val b = 2
      val c = 3
      val d = 4

      // No value asserted
      One.n.a1.float_.apply().query.get ==> List(d)

      One.n.a1.float_(float0).query.get ==> List()
      One.n.a1.float_.apply(float1).query.get ==> List(a)
      One.n.a1.float_(float1, float2).query.get ==> List(a, b)
      One.n.a1.float_(float1, float0).query.get ==> List(a)
      // Same as
      One.n.a1.float_(Seq(float0)).query.get ==> List()
      One.n.a1.float_(Seq(float1)).query.get ==> List(a)
      One.n.a1.float_(Seq(float1, float2)).query.get ==> List(a, b)
      One.n.a1.float_(Seq(float1, float0)).query.get ==> List(a)
      One.n.a1.float_(Seq.empty[Float]).query.get ==> List()


      One.n.a1.float_.not(float0).query.get ==> List(a, b, c)
      One.n.a1.float_.not(float1).query.get ==> List(b, c)
      One.n.a1.float_.not(float2).query.get ==> List(a, c)
      One.n.a1.float_.not(float3).query.get ==> List(a, b)
      One.n.a1.float_.not(float0, float1).query.get ==> List(b, c)
      One.n.a1.float_.not(float1, float2).query.get ==> List(c)
      One.n.a1.float_.not(float2, float3).query.get ==> List(a)
      // Same as
      One.n.a1.float_.not(Seq(float0)).query.get ==> List(a, b, c)
      One.n.a1.float_.not(Seq(float1)).query.get ==> List(b, c)
      One.n.a1.float_.not(Seq(float2)).query.get ==> List(a, c)
      One.n.a1.float_.not(Seq(float3)).query.get ==> List(a, b)
      One.n.a1.float_.not(Seq(float0, float1)).query.get ==> List(b, c)
      One.n.a1.float_.not(Seq(float1, float2)).query.get ==> List(c)
      One.n.a1.float_.not(Seq(float2, float3)).query.get ==> List(a)
      One.n.a1.float_.not(Seq.empty[Float]).query.get ==> List(a, b, c)


      One.n.a1.float_.<(float2).query.get ==> List(a)
      One.n.a1.float_.>(float2).query.get ==> List(c)
      One.n.a1.float_.<=(float2).query.get ==> List(a, b)
      One.n.a1.float_.>=(float2).query.get ==> List(b, c)
    }


    "Optional" - cardOne { implicit conn =>
      val a = (1, Some(float1))
      val b = (2, Some(float2))
      val c = (3, Some(float3))
      val x = (4, Option.empty[Float])

      One.n.float_?.insert(List(a, b, c, x)).transact

      One.n.a1.float_?(Some(float0)).query.get ==> List()
      One.n.a1.float_?.apply(Some(float1)).query.get ==> List(a)

      One.n.a1.float_?(Some(Seq(float0))).query.get ==> List()
      One.n.a1.float_?(Some(Seq(float1))).query.get ==> List(a)
      One.n.a1.float_?(Some(Seq(float1, float2))).query.get ==> List(a, b)
      One.n.a1.float_?(Some(Seq(float1, float0))).query.get ==> List(a)
      One.n.a1.float_?(Some(Seq.empty[Float])).query.get ==> List()

      One.n.a1.float_?(Option.empty[Float]).query.get ==> List(x)
      One.n.a1.float_?(Option.empty[Seq[Float]]).query.get ==> List(x)


      // For non-String cardinality-one attributes, `not` and `!=` have same semantics
      // "Not this value"
      One.n.a1.float_?.not(Some(float0)).query.get ==> List(a, b, c)
      One.n.a1.float_?.not(Some(float1)).query.get ==> List(b, c)
      One.n.a1.float_?.not(Some(float2)).query.get ==> List(a, c)
      One.n.a1.float_?.not(Some(float3)).query.get ==> List(a, b)
      // Same as
      One.n.a1.float_?.not(Some(Seq(float0))).query.get ==> List(a, b, c)
      One.n.a1.float_?.not(Some(Seq(float1))).query.get ==> List(b, c)
      One.n.a1.float_?.not(Some(Seq(float2))).query.get ==> List(a, c)
      One.n.a1.float_?.not(Some(Seq(float3))).query.get ==> List(a, b)

      // "Not this OR that value
      One.n.a1.float_?.not(Some(Seq(float0, float1))).query.get ==> List(b, c)
      One.n.a1.float_?.not(Some(Seq(float1, float2))).query.get ==> List(c)
      One.n.a1.float_?.not(Some(Seq(float2, float3))).query.get ==> List(a)
      One.n.a1.float_?.not(Some(Seq.empty[Float])).query.get ==> List(a, b, c)

      One.n.a1.float_?.not(Option.empty[Float]).query.get ==> List(a, b, c)
      One.n.a1.float_?.not(Option.empty[Seq[Float]]).query.get ==> List(a, b, c)


      One.n.a1.float_?.<(Some(float2)).query.get ==> List(a)
      One.n.a1.float_?.>(Some(float2)).query.get ==> List(c)
      One.n.a1.float_?.<=(Some(float2)).query.get ==> List(a, b)
      One.n.a1.float_?.>=(Some(float2)).query.get ==> List(b, c)

      // None can't be compared
      One.n.a1.float_?.<(None).query.get ==> List()
      One.n.a1.float_?.<=(None).query.get ==> List()
      One.n.a1.float_?.>(None).query.get ==> List()
      One.n.a1.float_?.>=(None).query.get ==> List()
    }
  }
}
