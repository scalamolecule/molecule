// GENERATED CODE ********************************
package molecule.datomic.test.filter.one

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._

object FilterOne_Float_ extends DatomicTestSuite {


  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, float1)
      val b = (2, float2)
      val c = (3, float3)
      for {
        _ <- Ns.i.float.insert(List(a, b, c)).transact

        // Find all attribute values
        _ <- Ns.i.a1.float.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.float(float0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.float(float1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.float(Seq(float0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.float(Seq(float1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.float(float1, float2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.float(float1, float0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.float(Seq(float1, float2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.float(Seq(float1, float0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.float(Seq.empty[Float]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.float.not(float0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.float.not(float1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.float.not(float2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.float.not(float3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.float.not(Seq(float0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.float.not(Seq(float1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.float.not(Seq(float2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.float.not(Seq(float3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.float.not(float0, float1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.float.not(float1, float2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.float.not(float2, float3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.float.not(Seq(float0, float1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.float.not(Seq(float1, float2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.float.not(Seq(float2, float3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.float.not(Seq.empty[Float]).query.get.map(_ ==> List(a, b, c))

        // Find values in range
        _ <- Ns.i.a1.float.<(float2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.float.>(float2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.float.<=(float2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.float.>=(float2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      for {
        _ <- Ns.i.float_?.insert(List(
          (a, Some(float1)),
          (b, Some(float2)),
          (c, Some(float3)),
          (x, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.float_.query.get.map(_ ==> List(a, b, c))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.float_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.float_(float0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.float_(float1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.float_(Seq(float0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.float_(Seq(float1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.float_(float1, float2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.float_(float1, float0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.float_(Seq(float1, float2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.float_(Seq(float1, float0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.float_(Seq.empty[Float]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.float_.not(float0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.float_.not(float1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.float_.not(float2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.float_.not(float3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.float_.not(Seq(float0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.float_.not(Seq(float1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.float_.not(Seq(float2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.float_.not(Seq(float3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.float_.not(float0, float1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.float_.not(float1, float2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.float_.not(float2, float3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.float_.not(Seq(float0, float1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.float_.not(Seq(float1, float2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.float_.not(Seq(float2, float3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.float_.not(Seq.empty[Float]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.float_.<(float2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.float_.>(float2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.float_.<=(float2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.float_.>=(float2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(float1))
      val b = (2, Some(float2))
      val c = (3, Some(float3))
      val x = (4, Option.empty[Float])
      for {
        _ <- Ns.i.float_?.insert(List(a, b, c, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.float_?.query.get.map(_ ==> List(a, b, c, x))

        // Find optional values matching
        _ <- Ns.i.a1.float_?(Some(float0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.float_?(Some(float1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.float_?(Some(Seq(float0))).query.get.map(_ ==> List())
        _ <- Ns.i.a1.float_?(Some(Seq(float1))).query.get.map(_ ==> List(a))
        // OR semantics for Ses of multiple args
        _ <- Ns.i.a1.float_?(Some(Seq(float1, float2))).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.float_?(Some(Seq(float1, float0))).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.float_?(Some(Seq.empty[Float])).query.get.map(_ ==> List())
        // None matches non-asserted/null values
        _ <- Ns.i.a1.float_?(Option.empty[Float]).query.get.map(_ ==> List(x))
        _ <- Ns.i.a1.float_?(Option.empty[Seq[Float]]).query.get.map(_ ==> List(x))

        // Find optional values not matching
        _ <- Ns.i.a1.float_?.not(Some(float0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.float_?.not(Some(float1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.float_?.not(Some(float2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.float_?.not(Some(float3)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.float_?.not(Some(Seq(float0))).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.float_?.not(Some(Seq(float1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.float_?.not(Some(Seq(float2))).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.float_?.not(Some(Seq(float3))).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple negation args
        _ <- Ns.i.a1.float_?.not(Some(Seq(float0, float1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.float_?.not(Some(Seq(float1, float2))).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.float_?.not(Some(Seq(float2, float3))).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.float_?.not(Some(Seq.empty[Float])).query.get.map(_ ==> List(a, b, c))
        // Negating None matches all asserted values (non-null)
        _ <- Ns.i.a1.float_?.not(Option.empty[Float]).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.float_?.not(Option.empty[Seq[Float]]).query.get.map(_ ==> List(a, b, c))

        // Find optional values in range
        _ <- Ns.i.a1.float_?.<(Some(float2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.float_?.>(Some(float2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.float_?.<=(Some(float2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.float_?.>=(Some(float2)).query.get.map(_ ==> List(b, c))
        // None can't be compared and returns empty result
        _ <- Ns.i.a1.float_?.<(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.float_?.<=(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.float_?.>(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.float_?.>=(None).query.get.map(_ ==> List())

        // Distinct result even with redundant None's (two i = 4 with no float value)
        _ <- Ns.i.insert(4).transact
        _ <- Ns.i.>=(3).a1.float_?.query.get.map(_ ==> List(
          (3, Some(float3)),
          (4, None),
        ))
      } yield ()
    }
  }
}
