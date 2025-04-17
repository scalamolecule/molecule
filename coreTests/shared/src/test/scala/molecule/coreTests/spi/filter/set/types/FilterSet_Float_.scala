// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterSet_Float_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Set(float1, float2))
  val b = (2, Set(float2, float3, float4))

  import api._
  import suite._


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.floatSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.floatSet.has(float0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSet.has(float1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatSet.has(float2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatSet.has(float3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.floatSet.has(Seq(float0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSet.has(Seq(float1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatSet.has(Seq(float2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatSet.has(Seq(float3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.floatSet.has(float1, float2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatSet.has(float1, float3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatSet.has(float2, float3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatSet.has(float1, float2, float3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.floatSet.has(Seq(float1, float2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatSet.has(Seq(float1, float3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatSet.has(Seq(float2, float3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatSet.has(Seq(float1, float2, float3)).query.get.map(_ ==> List(a, b))


      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.floatSet.has(Seq.empty[Float]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.floatSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.floatSet.hasNo(float0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatSet.hasNo(float1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.floatSet.hasNo(float2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSet.hasNo(float3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatSet.hasNo(float4).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatSet.hasNo(float5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.floatSet.hasNo(Seq(float0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatSet.hasNo(Seq(float1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.floatSet.hasNo(Seq(float2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSet.hasNo(Seq(float3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatSet.hasNo(Seq(float4)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatSet.hasNo(Seq(float5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.floatSet.hasNo(float1, float2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSet.hasNo(float1, float3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSet.hasNo(float1, float4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSet.hasNo(float1, float5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.floatSet.hasNo(Seq(float1, float2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSet.hasNo(Seq(float1, float3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSet.hasNo(Seq(float1, float4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSet.hasNo(Seq(float1, float5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.floatSet.hasNo(Seq.empty[Float]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.floatSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.floatSet_.has(float0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSet_.has(float1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatSet_.has(float2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatSet_.has(float3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.floatSet_.has(Seq(float0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSet_.has(Seq(float1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatSet_.has(Seq(float2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatSet_.has(Seq(float3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.floatSet_.has(float0, float1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatSet_.has(float1, float2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatSet_.has(float1, float3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatSet_.has(float2, float3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatSet_.has(float3, float4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.floatSet_.has(Seq(float0, float1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatSet_.has(Seq(float1, float2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatSet_.has(Seq(float1, float3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatSet_.has(Seq(float2, float3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatSet_.has(Seq(float3, float4)).query.get.map(_ ==> List(2))

      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.floatSet_.has(Seq.empty[Float]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.floatSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.floatSet_.hasNo(float0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatSet_.hasNo(float1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.floatSet_.hasNo(float2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSet_.hasNo(float3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatSet_.hasNo(float4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatSet_.hasNo(float5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.floatSet_.hasNo(Seq(float0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatSet_.hasNo(Seq(float1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.floatSet_.hasNo(Seq(float2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSet_.hasNo(Seq(float3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatSet_.hasNo(Seq(float4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatSet_.hasNo(Seq(float5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.floatSet_.hasNo(float1, float2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSet_.hasNo(float1, float3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSet_.hasNo(float1, float4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSet_.hasNo(float1, float5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.floatSet_.hasNo(Seq(float1, float2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSet_.hasNo(Seq(float1, float3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSet_.hasNo(Seq(float1, float4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSet_.hasNo(Seq(float1, float5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.floatSet_.hasNo(Seq.empty[Float]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Set attributes
}