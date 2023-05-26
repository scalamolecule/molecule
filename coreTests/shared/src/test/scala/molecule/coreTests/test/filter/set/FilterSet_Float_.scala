// GENERATED CODE ********************************
package molecule.coreTests.test.filter.set

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_Float_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(float1, float2))
        val b = (2, Set(float2, float3, float4))
        for {
          _ <- Ns.i.floats.insert(List(a, b)).transact

          _ <- Ns.i.a1.floats.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(float1, float2))
        val b = (2, Set(float2, float3, float4))
        for {
          _ <- Ns.i.floats.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.floats(Set(float1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats(Set(float1, float2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.floats(Set(float1, float2, float3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.floats(Seq(Set(float1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats(Seq(Set(float1, float2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats(Seq(Set(float1, float2, float3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.floats(Set(float1), Set(float2, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats(Set(float1, float2), Set(float2, float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats(Set(float1, float2), Set(float2, float3, float4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floats(Seq(Set(float1), Set(float2, float3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats(Seq(Set(float1, float2), Set(float2, float3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats(Seq(Set(float1, float2), Set(float2, float3, float4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.floats(Set(float1, float2), Set.empty[Float]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats(Set.empty[Float], Set(float1, float2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats(Set.empty[Float]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats(Seq.empty[Set[Float]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats(Seq(Set.empty[Float])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(float1, float2))
        val b = (2, Set(float2, float3, float4))
        for {
          _ <- Ns.i.floats.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.floats.not(Set(float1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.not(Set(float1, float2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.floats.not(Set(float1, float2, float3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floats.not(Seq(Set(float1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.not(Seq(Set(float1, float2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats.not(Seq(Set(float1, float2, float3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.floats.not(Set(float1), Set(float2, float3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.not(Set(float1, float2), Set(float2, float3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats.not(Set(float1, float2), Set(float2, float3, float4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.floats.not(Seq(Set(float1), Set(float2, float3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.not(Seq(Set(float1, float2), Set(float2, float3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats.not(Seq(Set(float1, float2), Set(float2, float3, float4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.floats.not(Seq(Set(float1, float2), Set.empty[Float])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats.not(Set.empty[Float]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.not(Seq.empty[Set[Float]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(float1, float2))
        val b = (2, Set(float2, float3, float4))
        for {
          _ <- Ns.i.floats.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.floats.has(float0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.has(float1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats.has(float2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.has(float3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.floats.has(Seq(float0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.has(Seq(float1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats.has(Seq(float2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.has(Seq(float3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.floats.has(float1, float2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.has(float1, float3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.has(float2, float3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.has(float1, float2, float3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floats.has(Seq(float1, float2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.has(Seq(float1, float3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.has(Seq(float2, float3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.has(Seq(float1, float2, float3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.floats.has(Set(float1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats.has(Set(float1, float2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats.has(Set(float1, float2, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.has(Set(float2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.has(Set(float2, float3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats.has(Set(float2, float3, float4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.floats.has(Seq(Set(float1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats.has(Seq(Set(float1, float2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats.has(Seq(Set(float1, float2, float3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.has(Seq(Set(float2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.has(Seq(Set(float2, float3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats.has(Seq(Set(float2, float3, float4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.floats.has(Set(float1, float2), Set(float0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats.has(Set(float1, float2), Set(float0, float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats.has(Set(float1, float2), Set(float2, float3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.has(Set(float1, float2), Set(float2, float3, float4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floats.has(Seq(Set(float1, float2), Set(float0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats.has(Seq(Set(float1, float2), Set(float0, float3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats.has(Seq(Set(float1, float2), Set(float2, float3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.has(Seq(Set(float1, float2), Set(float2, float3, float4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.floats.has(Set(float1, float2), Set.empty[Float]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats.has(Seq.empty[Float]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.has(Set.empty[Float]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.has(Seq.empty[Set[Float]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(float1, float2))
        val b = (2, Set(float2, float3, float4))
        for {
          _ <- Ns.i.floats.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.floats.hasNo(float0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.hasNo(float1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats.hasNo(float2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.hasNo(float3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats.hasNo(float4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats.hasNo(float5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floats.hasNo(Seq(float0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.hasNo(Seq(float1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats.hasNo(Seq(float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.hasNo(Seq(float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats.hasNo(Seq(float4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats.hasNo(Seq(float5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.floats.hasNo(float1, float2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.hasNo(float1, float3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.hasNo(float1, float4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.hasNo(float1, float5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.floats.hasNo(Seq(float1, float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.hasNo(Seq(float1, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.hasNo(Seq(float1, float4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.hasNo(Seq(float1, float5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.floats.hasNo(Set(float1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats.hasNo(Set(float1, float2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats.hasNo(Set(float1, float2, float3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.hasNo(Set(float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.hasNo(Set(float2, float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats.hasNo(Set(float2, float3, float4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.floats.hasNo(Seq(Set(float1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats.hasNo(Seq(Set(float1, float2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats.hasNo(Seq(Set(float1, float2, float3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.hasNo(Seq(Set(float2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.hasNo(Seq(Set(float2, float3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats.hasNo(Seq(Set(float2, float3, float4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.floats.hasNo(Set(float1, float2), Set(float0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats.hasNo(Set(float1, float2), Set(float0, float3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats.hasNo(Set(float1, float2), Set(float2, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.hasNo(Set(float1, float2), Set(float2, float3, float4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.floats.hasNo(Seq(Set(float1, float2), Set(float0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats.hasNo(Seq(Set(float1, float2), Set(float0, float3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats.hasNo(Seq(Set(float1, float2), Set(float2, float3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.hasNo(Seq(Set(float1, float2), Set(float2, float3, float4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.floats.hasNo(Set(float1, float2), Set.empty[Float]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats.hasNo(Seq.empty[Float]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.hasNo(Set.empty[Float]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.hasNo(Seq.empty[Set[Float]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.hasNo(Seq(Set.empty[Float])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(float1, float2))
        val b = (2, Set(float2, float3, float4))
        for {
          _ <- Ns.i.floats.insert(List(a, b)).transact

          // <
          _ <- Ns.i.a1.floats.hasLt(float0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.hasLt(float1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.hasLt(float2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats.hasLt(float3).query.get.map(_ ==> List(a, b))

          // <=
          _ <- Ns.i.a1.floats.hasLe(float0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats.hasLe(float1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats.hasLe(float2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.hasLe(float3).query.get.map(_ ==> List(a, b))

          // >
          _ <- Ns.i.a1.floats.hasGt(float0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.hasGt(float1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.hasGt(float2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats.hasGt(float3).query.get.map(_ ==> List(b))

          // >=
          _ <- Ns.i.a1.floats.hasGe(float0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.hasGe(float1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.hasGe(float2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats.hasGe(float3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.floats.insert(List(
            (a, Set(float1, float2)),
            (b, Set(float2, float3, float4))
          )).transact

          _ <- Ns.i.a1.floats_.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.floats.insert(List(
            (a, Set(float1, float2)),
            (b, Set(float2, float3, float4))
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.floats_(Set(float1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_(Set(float1, float2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.floats_(Set(float1, float2, float3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.floats_(Seq(Set(float1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_(Seq(Set(float1, float2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_(Seq(Set(float1, float2, float3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.floats_(Set(float1), Set(float2, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_(Set(float1, float2), Set(float2, float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_(Set(float1, float2), Set(float2, float3, float4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floats_(Seq(Set(float1), Set(float2, float3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_(Seq(Set(float1, float2), Set(float2, float3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_(Seq(Set(float1, float2), Set(float2, float3, float4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.floats_(Set(float1, float2), Set.empty[Float]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_(Set.empty[Float]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_(Seq.empty[Set[Float]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_(Seq(Set.empty[Float])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.floats.insert(List(
            (a, Set(float1, float2)),
            (b, Set(float2, float3, float4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.floats_.not(Set(float1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.not(Set(float1, float2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.floats_.not(Set(float1, float2, float3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floats_.not(Seq(Set(float1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.not(Seq(Set(float1, float2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_.not(Seq(Set(float1, float2, float3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.floats_.not(Set(float1), Set(float2, float3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.not(Set(float1, float2), Set(float2, float3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_.not(Set(float1, float2), Set(float2, float3, float4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.floats_.not(Seq(Set(float1), Set(float2, float3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.not(Seq(Set(float1, float2), Set(float2, float3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_.not(Seq(Set(float1, float2), Set(float2, float3, float4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.floats_.not(Seq(Set(float1, float2), Set.empty[Float])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_.not(Set.empty[Float]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.not(Seq.empty[Set[Float]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.floats.insert(List(
            (a, Set(float1, float2)),
            (b, Set(float2, float3, float4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.floats_.has(float0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.has(float1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_.has(float2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.has(float3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.floats_.has(Seq(float0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.has(Seq(float1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_.has(Seq(float2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.has(Seq(float3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.floats_.has(float1, float2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.has(float1, float3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.has(float2, float3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.has(float1, float2, float3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floats_.has(Seq(float1, float2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.has(Seq(float1, float3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.has(Seq(float2, float3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.has(Seq(float1, float2, float3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.floats_.has(Set(float1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_.has(Set(float1, float2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_.has(Set(float1, float2, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.has(Set(float2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.has(Set(float2, float3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_.has(Set(float2, float3, float4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.floats_.has(Seq(Set(float1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_.has(Seq(Set(float1, float2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_.has(Seq(Set(float1, float2, float3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.has(Seq(Set(float2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.has(Seq(Set(float2, float3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_.has(Seq(Set(float2, float3, float4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.floats_.has(Set(float1, float2), Set(float0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_.has(Set(float1, float2), Set(float0, float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_.has(Set(float1, float2), Set(float2, float3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.has(Set(float1, float2), Set(float2, float3, float4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floats_.has(Seq(Set(float1, float2), Set(float0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_.has(Seq(Set(float1, float2), Set(float0, float3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_.has(Seq(Set(float1, float2), Set(float2, float3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.has(Seq(Set(float1, float2), Set(float2, float3, float4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.floats_.has(Set(float1, float2), Set.empty[Float]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_.has(Seq.empty[Float]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.has(Set.empty[Float]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.has(Seq.empty[Set[Float]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.floats.insert(List(
            (a, Set(float1, float2)),
            (b, Set(float2, float3, float4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.floats_.hasNo(float0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.hasNo(float1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_.hasNo(float2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.hasNo(float3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_.hasNo(float4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_.hasNo(float5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floats_.hasNo(Seq(float0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.hasNo(Seq(float1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_.hasNo(Seq(float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.hasNo(Seq(float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_.hasNo(Seq(float4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_.hasNo(Seq(float5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.floats_.hasNo(float1, float2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.hasNo(float1, float3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.hasNo(float1, float4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.hasNo(float1, float5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.floats_.hasNo(Seq(float1, float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.hasNo(Seq(float1, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.hasNo(Seq(float1, float4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.hasNo(Seq(float1, float5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.floats_.hasNo(Set(float1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_.hasNo(Set(float1, float2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_.hasNo(Set(float1, float2, float3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.hasNo(Set(float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.hasNo(Set(float2, float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_.hasNo(Set(float2, float3, float4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.floats_.hasNo(Seq(Set(float1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_.hasNo(Seq(Set(float1, float2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_.hasNo(Seq(Set(float1, float2, float3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.hasNo(Seq(Set(float2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.hasNo(Seq(Set(float2, float3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_.hasNo(Seq(Set(float2, float3, float4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.floats_.hasNo(Set(float1, float2), Set(float0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_.hasNo(Set(float1, float2), Set(float0, float3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_.hasNo(Set(float1, float2), Set(float2, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.hasNo(Set(float1, float2), Set(float2, float3, float4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.floats_.hasNo(Seq(Set(float1, float2), Set(float0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_.hasNo(Seq(Set(float1, float2), Set(float0, float3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_.hasNo(Seq(Set(float1, float2), Set(float2, float3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.hasNo(Seq(Set(float1, float2), Set(float2, float3, float4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.floats_.hasNo(Set(float1, float2), Set.empty[Float]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_.hasNo(Seq.empty[Float]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.hasNo(Set.empty[Float]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.hasNo(Seq.empty[Set[Float]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.hasNo(Seq(Set.empty[Float])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.floats.insert(List(
            (a, Set(float1, float2)),
            (b, Set(float2, float3, float4))
          )).transact

          // <
          _ <- Ns.i.a1.floats_.hasLt(float0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.hasLt(float1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.hasLt(float2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_.hasLt(float3).query.get.map(_ ==> List(a, b))

          // <=
          _ <- Ns.i.a1.floats_.hasLe(float0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_.hasLe(float1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_.hasLe(float2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.hasLe(float3).query.get.map(_ ==> List(a, b))

          // >
          _ <- Ns.i.a1.floats_.hasGt(float0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.hasGt(float1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.hasGt(float2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_.hasGt(float3).query.get.map(_ ==> List(b))

          // >=
          _ <- Ns.i.a1.floats_.hasGe(float0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.hasGe(float1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.hasGe(float2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_.hasGe(float3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(float1, float2)))
        val b = (2, Some(Set(float2, float3, float4)))
        val c = (3, None)
        for {
          _ <- Ns.i.floats_?.insert(a, b, c).transact

          _ <- Ns.i.a1.floats_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no float value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.floats_?.query.get.map(_ ==> List(
            (2, Some(Set(float2, float3, float4))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(float1, float2)))
        val b = (2, Some(Set(float2, float3, float4)))
        val c = (3, None)
        for {
          _ <- Ns.i.floats_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.floats_?(Some(Set(float1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?(Some(Set(float1, float2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.floats_?(Some(Set(float1, float2, float3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.floats_?(Some(Seq(Set(float1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?(Some(Seq(Set(float1, float2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_?(Some(Seq(Set(float1, float2, float3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.floats_?(Some(Seq(Set(float1), Set(float2, float3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?(Some(Seq(Set(float1, float2), Set(float2, float3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_?(Some(Seq(Set(float1, float2), Set(float2, float3, float4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.floats_?(Some(Set.empty[Float])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?(Some(Seq.empty[Set[Float]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?(Some(Seq(Set.empty[Float]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.floats_?(Option.empty[Set[Float]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.floats_?(Option.empty[Seq[Set[Float]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(float1, float2)))
        val b = (2, Some(Set(float2, float3, float4)))
        val c = (3, None)
        for {
          _ <- Ns.i.floats_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.floats_?.not(Some(Set(float1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.not(Some(Set(float1, float2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.floats_?.not(Some(Set(float1, float2, float3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floats_?.not(Some(Seq(Set(float1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.not(Some(Seq(Set(float1, float2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_?.not(Some(Seq(Set(float1, float2, float3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.floats_?.not(Some(Seq(Set(float1), Set(float2, float3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.not(Some(Seq(Set(float1, float2), Set(float2, float3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_?.not(Some(Seq(Set(float1, float2), Set(float2, float3, float4)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.floats_?.not(Some(Seq(Set(float1, float2), Set.empty[Float]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_?.not(Some(Set.empty[Float])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.not(Some(Seq.empty[Set[Float]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.floats_?(Option.empty[Set[Float]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.floats_?(Option.empty[Seq[Set[Float]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(float1, float2)))
        val b = (2, Some(Set(float2, float3, float4)))
        val c = (3, None)
        for {
          _ <- Ns.i.floats_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.floats_?.has(Some(float0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?.has(Some(float1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_?.has(Some(float2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.has(Some(float3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.floats_?.has(Some(Seq(float0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?.has(Some(Seq(float1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_?.has(Some(Seq(float2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.has(Some(Seq(float3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.floats_?.has(Some(Seq(float1, float2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.has(Some(Seq(float1, float3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.has(Some(Seq(float2, float3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.has(Some(Seq(float1, float2, float3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.floats_?.has(Some(Set(float1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_?.has(Some(Set(float1, float2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_?.has(Some(Set(float1, float2, float3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?.has(Some(Set(float2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.has(Some(Set(float2, float3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_?.has(Some(Set(float2, float3, float4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.floats_?.has(Some(Seq(Set(float1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_?.has(Some(Seq(Set(float1, float2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_?.has(Some(Seq(Set(float1, float2, float3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?.has(Some(Seq(Set(float2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.has(Some(Seq(Set(float2, float3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_?.has(Some(Seq(Set(float2, float3, float4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.floats_?.has(Some(Seq(Set(float1, float2), Set(float0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_?.has(Some(Seq(Set(float1, float2), Set(float0, float3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_?.has(Some(Seq(Set(float1, float2), Set(float2, float3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.has(Some(Seq(Set(float1, float2), Set(float2, float3, float4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.floats_?.has(Some(Seq.empty[Float])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?.has(Some(Set.empty[Float])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?.has(Some(Seq.empty[Set[Float]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.floats_?.has(Option.empty[Float]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.floats_?.has(Option.empty[Seq[Float]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.floats_?.has(Option.empty[Set[Float]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.floats_?.has(Option.empty[Seq[Set[Float]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(float1, float2)))
        val b = (2, Some(Set(float2, float3, float4)))
        val c = (3, None)
        for {
          _ <- Ns.i.floats_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.floats_?.hasNo(Some(float0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.hasNo(Some(float1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_?.hasNo(Some(float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?.hasNo(Some(float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_?.hasNo(Some(float4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_?.hasNo(Some(float5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(float0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(float1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(float2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(float3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(float4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(float5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(float1, float2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(float1, float3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(float1, float4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(float1, float5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.floats_?.hasNo(Some(Set(float1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_?.hasNo(Some(Set(float1, float2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_?.hasNo(Some(Set(float1, float2, float3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.hasNo(Some(Set(float2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?.hasNo(Some(Set(float2, float3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_?.hasNo(Some(Set(float2, float3, float4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(Set(float1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(Set(float1, float2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(Set(float1, float2, float3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(Set(float2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(Set(float2, float3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(Set(float2, float3, float4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(Set(float1, float2), Set(float0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(Set(float1, float2), Set(float0, float3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(Set(float1, float2), Set(float2, float3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(Set(float1, float2), Set(float2, float3, float4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq.empty[Float])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.hasNo(Some(Set.empty[Float])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq.empty[Set[Float]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.hasNo(Some(Seq(Set.empty[Float]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.floats_?.hasNo(Option.empty[Float]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.hasNo(Option.empty[Seq[Float]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.hasNo(Option.empty[Set[Float]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.hasNo(Option.empty[Seq[Set[Float]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(float1, float2)))
        val b = (2, Some(Set(float2, float3, float4)))
        val c = (3, None)
        for {
          _ <- Ns.i.floats_?.insert(a, b, c).transact

          // <
          _ <- Ns.i.a1.floats_?.hasLt(Some(float0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?.hasLt(Some(float1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?.hasLt(Some(float2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_?.hasLt(Some(float3)).query.get.map(_ ==> List(a, b))

          // <=
          _ <- Ns.i.a1.floats_?.hasLe(Some(float0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floats_?.hasLe(Some(float1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floats_?.hasLe(Some(float2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.hasLe(Some(float3)).query.get.map(_ ==> List(a, b))

          // >
          _ <- Ns.i.a1.floats_?.hasGt(Some(float0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.hasGt(Some(float1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.hasGt(Some(float2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floats_?.hasGt(Some(float3)).query.get.map(_ ==> List(b))

          // >=
          _ <- Ns.i.a1.floats_?.hasGe(Some(float0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.hasGe(Some(float1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.hasGe(Some(float2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.hasGe(Some(float3)).query.get.map(_ ==> List(b))


          // None comparison matches any asserted values
          _ <- Ns.i.a1.floats_?.hasLt(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.hasGt(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.hasLe(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floats_?.hasGe(None).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}