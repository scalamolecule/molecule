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

      "attr" - types { implicit conn =>
        val a = (1, Set(float1, float2))
        val b = (2, Set(float2, float3, float4))
        for {
          _ <- Ns.i.floatSet.insert(List(a, b)).transact

          _ <- Ns.i.a1.floatSet.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(float1, float2))
        val b = (2, Set(float2, float3, float4))
        for {
          _ <- Ns.i.floatSet.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.floatSet(Set(float1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet(Set(float1, float2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.floatSet(Set(float1, float2, float3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.floatSet(Seq(Set(float1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet(Seq(Set(float1, float2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSet(Seq(Set(float1, float2, float3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.floatSet(Set(float1), Set(float2, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet(Set(float1, float2), Set(float2, float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSet(Set(float1, float2), Set(float2, float3, float4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floatSet(Seq(Set(float1), Set(float2, float3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet(Seq(Set(float1, float2), Set(float2, float3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSet(Seq(Set(float1, float2), Set(float2, float3, float4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.floatSet(Set(float1, float2), Set.empty[Float]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSet(Set.empty[Float], Set(float1, float2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSet(Set.empty[Float], Set.empty[Float]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet(Set.empty[Float]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet(Seq.empty[Set[Float]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet(Seq(Set.empty[Float])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.floatSet().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(float1, float2))
        val b = (2, Set(float2, float3, float4))
        for {
          _ <- Ns.i.floatSet.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.floatSet.not(Set(float1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet.not(Set(float1, float2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.floatSet.not(Set(float1, float2, float3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floatSet.not(Seq(Set(float1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet.not(Seq(Set(float1, float2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSet.not(Seq(Set(float1, float2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSet.not(Seq(Set(float1, float2, float3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.floatSet.not(Set(float1), Set(float2, float3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet.not(Set(float1, float2), Set(float2, float3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSet.not(Set(float1, float2), Set(float2, float3, float4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.floatSet.not(Seq(Set(float1), Set(float2, float3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet.not(Seq(Set(float1, float2), Set(float2, float3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSet.not(Seq(Set(float1, float2), Set(float2, float3, float4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.floatSet.not(Seq(Set(float1, float2), Set.empty[Float])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSet.not(Set.empty[Float]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet.not(Seq.empty[Set[Float]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(float1, float2))
        val b = (2, Set(float2, float3, float4))
        for {
          _ <- Ns.i.floatSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
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

          // "Doesn't have this value"
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

          // "Not (has this OR that)"
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

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.floatSet.insert(List(
            (1, Set(float1, float2)),
            (2, Set(float2, float3, float4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // floatSet not asserted for i = 0
          _ <- Ns.i.a1.floatSet_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.floatSet_?.insert(List(
            (0, None),
            (1, Some(Set(float1, float2))),
            (2, Some(Set(float2, float3, float4))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.floatSet_().query.get.map(_ ==> List(0))

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.floatSet_(Set(float1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_(Set(float1, float2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.floatSet_(Set(float1, float2, float3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.floatSet_(Seq(Set(float1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_(Seq(Set(float1, float2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSet_(Seq(Set(float1, float2, float3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.floatSet_(Set(float1), Set(float2, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_(Set(float1, float2), Set(float2, float3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSet_(Set(float1, float2), Set(float2, float3, float4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.floatSet_(Seq(Set(float1), Set(float2, float3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_(Seq(Set(float1, float2), Set(float2, float3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSet_(Seq(Set(float1, float2), Set(float2, float3, float4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.floatSet_(Set(float1, float2), Set.empty[Float]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSet_(Set.empty[Float]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_(Seq.empty[Set[Float]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_(Seq(Set.empty[Float])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.floatSet.insert(List(
            (1, Set(float1, float2)),
            (2, Set(float2, float3, float4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.floatSet_.not(Set(float1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSet_.not(Set(float1, float2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.floatSet_.not(Set(float1, float2, float3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.floatSet_.not(Seq(Set(float1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSet_.not(Seq(Set(float1, float2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatSet_.not(Seq(Set(float1, float2, float3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.floatSet_.not(Set(float1), Set(float2, float3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSet_.not(Set(float1, float2), Set(float2, float3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatSet_.not(Set(float1, float2), Set(float2, float3, float4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.floatSet_.not(Seq(Set(float1), Set(float2, float3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSet_.not(Seq(Set(float1, float2), Set(float2, float3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatSet_.not(Seq(Set(float1, float2), Set(float2, float3, float4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.floatSet_.not(Seq(Set(float1, float2), Set.empty[Float])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatSet_.not(Set.empty[Float]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSet_.not(Seq.empty[Set[Float]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.floatSet.insert(List(
            (1, Set(float1, float2)),
            (2, Set(float2, float3, float4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
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

          // "Doesn't have this value"
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

          // "Not (has this OR that)"
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

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(float1, float2)))
        val b = (2, Some(Set(float2, float3, float4)))
        val c = (3, None)
        for {
          _ <- Ns.i.floatSet_?.insert(a, b, c).transact

          _ <- Ns.i.a1.floatSet_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no float value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.floatSet_?.query.get.map(_ ==> List(
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
          _ <- Ns.i.floatSet_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.floatSet_?(Some(Set(float1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_?(Some(Set(float1, float2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.floatSet_?(Some(Set(float1, float2, float3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.floatSet_?(Some(Seq(Set(float1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_?(Some(Seq(Set(float1, float2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSet_?(Some(Seq(Set(float1, float2, float3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.floatSet_?(Some(Seq(Set(float1), Set(float2, float3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_?(Some(Seq(Set(float1, float2), Set(float2, float3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSet_?(Some(Seq(Set(float1, float2), Set(float2, float3, float4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.floatSet_?(Some(Set.empty[Float])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_?(Some(Seq.empty[Set[Float]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSet_?(Some(Seq(Set.empty[Float]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.floatSet_?(Option.empty[Set[Float]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.floatSet_?(Option.empty[Seq[Set[Float]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(float1, float2)))
        val b = (2, Some(Set(float2, float3, float4)))
        val c = (3, None)
        for {
          _ <- Ns.i.floatSet_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.floatSet_?.not(Some(Set(float1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet_?.not(Some(Set(float1, float2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.floatSet_?.not(Some(Set(float1, float2, float3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floatSet_?.not(Some(Seq(Set(float1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet_?.not(Some(Seq(Set(float1, float2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSet_?.not(Some(Seq(Set(float1, float2, float3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.floatSet_?.not(Some(Seq(Set(float1), Set(float2, float3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet_?.not(Some(Seq(Set(float1, float2), Set(float2, float3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSet_?.not(Some(Seq(Set(float1, float2), Set(float2, float3, float4)))).query.get.map(_ ==> List())

          // Empty Sets are ignored
          _ <- Ns.i.a1.floatSet_?.not(Some(Seq(Set(float1, float2), Set.empty[Float]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSet_?.not(Some(Set.empty[Float])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet_?.not(Some(Seq.empty[Set[Float]])).query.get.map(_ ==> List(a, b))

          // Negation of None matches all asserted
          _ <- Ns.i.a1.floatSet_?.not(Option.empty[Set[Float]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSet_?.not(Option.empty[Seq[Set[Float]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(float1, float2)))
        val b = (2, Some(Set(float2, float3, float4)))
        val c = (3, None)
        for {
          _ <- Ns.i.floatSet_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
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

          // "Doesn't have this value"
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

          // "Not (has this OR that)"
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