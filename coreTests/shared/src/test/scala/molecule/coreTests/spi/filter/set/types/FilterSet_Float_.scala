// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_Float_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, Set(float1, float2))
        val b = (2, Set(float2, float3, float4))
        for {
          _ <- Ns.i.floatSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.floatSet.has(float0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet.has(float1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSet.has(float2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet.has(float3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.floatSet.has(Seq(float0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet.has(Seq(float1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSet.has(Seq(float2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet.has(Seq(float3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.floatSet.has(float1, float2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet.has(float1, float3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet.has(float2, float3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet.has(float1, float2, float3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floatSet.has(Seq(float1, float2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet.has(Seq(float1, float3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet.has(Seq(float2, float3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet.has(Seq(float1, float2, float3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.floatSet.has(Seq.empty[Float]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(float1, float2))
        val b = (2, Set(float2, float3, float4))
        for {
          _ <- Ns.i.floatSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.floatSet.hasNo(float0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet.hasNo(float1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSet.hasNo(float2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet.hasNo(float3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSet.hasNo(float4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSet.hasNo(float5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floatSet.hasNo(Seq(float0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet.hasNo(Seq(float1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSet.hasNo(Seq(float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet.hasNo(Seq(float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSet.hasNo(Seq(float4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSet.hasNo(Seq(float5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.floatSet.hasNo(float1, float2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet.hasNo(float1, float3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet.hasNo(float1, float4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet.hasNo(float1, float5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.floatSet.hasNo(Seq(float1, float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet.hasNo(Seq(float1, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet.hasNo(Seq(float1, float4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet.hasNo(Seq(float1, float5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.floatSet.hasNo(Seq.empty[Float]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.floatSet.insert(List(
            (1, Set(float1, float2)),
            (2, Set(float2, float3, float4))
          )).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.floatSet_.has(float0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_.has(float1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSet_.has(float2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSet_.has(float3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.floatSet_.has(Seq(float0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_.has(Seq(float1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSet_.has(Seq(float2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSet_.has(Seq(float3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.floatSet_.has(float1, float2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSet_.has(float1, float3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSet_.has(float2, float3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSet_.has(float1, float2, float3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.floatSet_.has(Seq(float1, float2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSet_.has(Seq(float1, float3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSet_.has(Seq(float2, float3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSet_.has(Seq(float1, float2, float3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.floatSet_.has(Seq.empty[Float]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.floatSet.insert(List(
            (1, Set(float1, float2)),
            (2, Set(float2, float3, float4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.floatSet_.hasNo(float0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSet_.hasNo(float1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatSet_.hasNo(float2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_.hasNo(float3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSet_.hasNo(float4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSet_.hasNo(float5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.floatSet_.hasNo(Seq(float0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSet_.hasNo(Seq(float1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatSet_.hasNo(Seq(float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_.hasNo(Seq(float3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSet_.hasNo(Seq(float4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSet_.hasNo(Seq(float5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.floatSet_.hasNo(float1, float2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_.hasNo(float1, float3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_.hasNo(float1, float4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_.hasNo(float1, float5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.floatSet_.hasNo(Seq(float1, float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_.hasNo(Seq(float1, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_.hasNo(Seq(float1, float4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_.hasNo(Seq(float1, float5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.floatSet_.hasNo(Seq.empty[Float]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "has" - types { implicit conn =>
        val a = (1, Some(Set(float1, float2)))
        val b = (2, Some(Set(float2, float3, float4)))
        val c = (3, None)
        for {
          _ <- Ns.i.floatSet_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.floatSet_?.has(Some(float0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_?.has(Some(float1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSet_?.has(Some(float2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet_?.has(Some(float3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.floatSet_?.has(Some(Seq(float0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_?.has(Some(Seq(float1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSet_?.has(Some(Seq(float2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet_?.has(Some(Seq(float3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.floatSet_?.has(Some(Seq(float1, float2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet_?.has(Some(Seq(float1, float3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet_?.has(Some(Seq(float2, float3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet_?.has(Some(Seq(float1, float2, float3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.floatSet_?.has(Some(Seq.empty[Float])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.floatSet_?.has(Option.empty[Float]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.floatSet_?.has(Option.empty[Seq[Float]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(float1, float2)))
        val b = (2, Some(Set(float2, float3, float4)))
        val c = (3, None)
        for {
          _ <- Ns.i.floatSet_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.floatSet_?.hasNo(Some(float0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet_?.hasNo(Some(float1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSet_?.hasNo(Some(float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_?.hasNo(Some(float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSet_?.hasNo(Some(float4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSet_?.hasNo(Some(float5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floatSet_?.hasNo(Some(Seq(float0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet_?.hasNo(Some(Seq(float1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSet_?.hasNo(Some(Seq(float2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_?.hasNo(Some(Seq(float3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSet_?.hasNo(Some(Seq(float4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSet_?.hasNo(Some(Seq(float5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.floatSet_?.hasNo(Some(Seq(float1, float2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_?.hasNo(Some(Seq(float1, float3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_?.hasNo(Some(Seq(float1, float4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_?.hasNo(Some(Seq(float1, float5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.floatSet_?.hasNo(Some(Seq.empty[Float])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.floatSet_?.hasNo(Option.empty[Float]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet_?.hasNo(Option.empty[Seq[Float]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}