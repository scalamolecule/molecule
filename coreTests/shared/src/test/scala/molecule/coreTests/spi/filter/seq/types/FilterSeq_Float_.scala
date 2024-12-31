// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterSeq_Float_(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  val a = (1, List(float1, float2))
  val b = (2, List(float2, float3, float3))

  import api._
  import suite._


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.floatSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.floatSeq.has(float0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSeq.has(float1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatSeq.has(float2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatSeq.has(float3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.floatSeq.has(List(float0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSeq.has(List(float1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatSeq.has(List(float2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatSeq.has(List(float3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.floatSeq.has(float0, float1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatSeq.has(float1, float2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatSeq.has(float1, float3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatSeq.has(float2, float3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatSeq.has(float1, float2, float3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.floatSeq.has(List(float0, float1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatSeq.has(List(float1, float2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatSeq.has(List(float1, float3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatSeq.has(List(float2, float3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatSeq.has(List(float1, float2, float3)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.floatSeq.has(List.empty[Float]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.floatSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.floatSeq.hasNo(float0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatSeq.hasNo(float1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.floatSeq.hasNo(float2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSeq.hasNo(float3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatSeq.hasNo(float3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatSeq.hasNo(float5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.floatSeq.hasNo(List(float0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.floatSeq.hasNo(List(float1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.floatSeq.hasNo(List(float2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSeq.hasNo(List(float3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatSeq.hasNo(List(float3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.floatSeq.hasNo(List(float5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.floatSeq.hasNo(float1, float2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSeq.hasNo(float1, float3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSeq.hasNo(float1, float3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSeq.hasNo(float1, float5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.floatSeq.hasNo(List(float1, float2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSeq.hasNo(List(float1, float3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSeq.hasNo(List(float1, float3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSeq.hasNo(List(float1, float5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.floatSeq.hasNo(List.empty[Float]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.floatSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.floatSeq_.has(float0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSeq_.has(float1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatSeq_.has(float2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatSeq_.has(float3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.floatSeq_.has(List(float0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSeq_.has(List(float1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatSeq_.has(List(float2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatSeq_.has(List(float3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.floatSeq_.has(float0, float1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatSeq_.has(float1, float2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatSeq_.has(float1, float3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatSeq_.has(float2, float3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatSeq_.has(float3, float4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.floatSeq_.has(List(float0, float1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatSeq_.has(List(float1, float2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatSeq_.has(List(float1, float3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatSeq_.has(List(float2, float3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatSeq_.has(List(float3, float4)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.floatSeq_.has(List.empty[Float]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.floatSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.floatSeq_.hasNo(float0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatSeq_.hasNo(float1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.floatSeq_.hasNo(float2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSeq_.hasNo(float3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatSeq_.hasNo(float3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatSeq_.hasNo(float5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.floatSeq_.hasNo(List(float0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.floatSeq_.hasNo(List(float1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.floatSeq_.hasNo(List(float2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSeq_.hasNo(List(float3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatSeq_.hasNo(List(float3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.floatSeq_.hasNo(List(float5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.floatSeq_.hasNo(float1, float2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSeq_.hasNo(float1, float3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSeq_.hasNo(float1, float3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSeq_.hasNo(float1, float5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.floatSeq_.hasNo(List(float1, float2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSeq_.hasNo(List(float1, float3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSeq_.hasNo(List(float1, float3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.floatSeq_.hasNo(List(float1, float5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.floatSeq_.hasNo(List.empty[Float]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}