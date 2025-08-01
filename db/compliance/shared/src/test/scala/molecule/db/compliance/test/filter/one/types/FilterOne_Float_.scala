// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.one.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterOne_Float_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Mandatory" - types {
    val a = (1, float1)
    val b = (2, float2)
    val c = (3, float3)
    for {
      _ <- Entity.i.float.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.float.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.float(float0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.float(float1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.float(Seq(float0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.float(Seq(float1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.float(float1, float2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.float(float1, float0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.float(Seq(float1, float2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.float(Seq(float1, float0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.float(Seq.empty[Float]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.float.not(float0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.float.not(float1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.float.not(float2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.float.not(float3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.float.not(Seq(float0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.float.not(Seq(float1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.float.not(Seq(float2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.float.not(Seq(float3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.float.not(float0, float1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.float.not(float1, float2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.float.not(float2, float3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.float.not(Seq(float0, float1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.float.not(Seq(float1, float2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.float.not(Seq(float2, float3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.float.not(Seq.empty[Float]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.float.<(float2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.float.>(float2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.float.<=(float2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.float.>=(float2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types {
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.float_?.insert(List(
        (a, Some(float1)),
        (b, Some(float2)),
        (c, Some(float3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.float_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.float_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.float_(float0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.float_(float1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.float_(Seq(float0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.float_(Seq(float1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.float_(float1, float2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.float_(float1, float0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.float_(Seq(float1, float2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.float_(Seq(float1, float0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.float_(Seq.empty[Float]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.float_.not(float0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.float_.not(float1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.float_.not(float2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.float_.not(float3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.float_.not(Seq(float0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.float_.not(Seq(float1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.float_.not(Seq(float2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.float_.not(Seq(float3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.float_.not(float0, float1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.float_.not(float1, float2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.float_.not(float2, float3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.float_.not(Seq(float0, float1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.float_.not(Seq(float1, float2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.float_.not(Seq(float2, float3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.float_.not(Seq.empty[Float]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.float_.<(float2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.float_.>(float2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.float_.<=(float2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.float_.>=(float2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types {
    val a = (1, Some(float1))
    val b = (2, Some(float2))
    val c = (3, Some(float3))
    val x = (4, Option.empty[Float])
    for {
      _ <- Entity.i.float_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.float_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.float_?(Some(float0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.float_?(Some(float1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.float_?(Option.empty[Float]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.float_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types {
    for {
      _ <- Entity.i.float.insert(
        (1, float1),
        (2, float2),
        (3, float3),
        (4, float4),
        (5, float5),
        (6, float6),
        (7, float7),
        (8, float8),
        (9, float9),
      ).transact

      _ <- Entity.i.a1.float_.>(float2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.float_.>(float2).float_.<=(float8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.float_.>(float2).float_.<=(float8).float_.not(float4, float5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
