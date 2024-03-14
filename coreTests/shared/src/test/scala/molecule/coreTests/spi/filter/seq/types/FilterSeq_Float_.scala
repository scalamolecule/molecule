// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_Float_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, List(float1, float2))
        val b = (2, List(float2, float3, float3))
        for {
          _ <- Ns.i.floatSeq.insert(List(a, b)).transact

          _ <- Ns.i.a1.floatSeq.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, List(float1, float2))
        val b = (2, List(float2, float3, float3))
        for {
          _ <- Ns.i.floatSeq.insert(List(a, b)).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.floatSeq(List(float1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq(List(float1, float2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.floatSeq(List(float1, float2, float3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.floatSeq(List(List(float1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq(List(List(float1, float2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq(List(List(float1, float2, float3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.floatSeq(List(float1), List(float2, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq(List(float1, float2), List(float2, float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq(List(float1, float2), List(float2, float3, float3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floatSeq(List(List(float1), List(float2, float3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq(List(List(float1, float2), List(float2, float3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq(List(List(float1, float2), List(float2, float3, float3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.floatSeq(List(float1, float2), List.empty[Float]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq(List.empty[Float], List(float1, float2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq(List.empty[Float], List.empty[Float]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq(List.empty[Float]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq(List.empty[List[Float]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq(List(List.empty[Float])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, List(float1, float2))
        val b = (2, List(float2, float3, float3))
        for {
          _ <- Ns.i.floatSeq.insert(List(a, b)).transact

          // Exact Seq non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.floatSeq.not(List(float1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.not(List(float1, float2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.floatSeq.not(List(float1, float2, float3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floatSeq.not(List(List(float1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.not(List(List(float1, float2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSeq.not(List(List(float1, float2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSeq.not(List(List(float1, float2, float3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.floatSeq.not(List(float1), List(float2, float3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.not(List(float1, float2), List(float2, float3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSeq.not(List(float1, float2), List(float2, float3, float3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.floatSeq.not(List(List(float1), List(float2, float3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.not(List(List(float1, float2), List(float2, float3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSeq.not(List(List(float1, float2), List(float2, float3, float3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.floatSeq.not(List(List(float1, float2), List.empty[Float])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSeq.not(List.empty[Float]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.not(List.empty[List[Float]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, List(float1, float2))
        val b = (2, List(float2, float3, float3))
        for {
          _ <- Ns.i.floatSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.floatSeq.has(float0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq.has(float1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq.has(float2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.has(float3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.floatSeq.has(List(float0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq.has(List(float1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq.has(List(float2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.has(List(float3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.floatSeq.has(float1, float2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.has(float1, float3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.has(float2, float3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.has(float1, float2, float3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floatSeq.has(List(float1, float2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.has(List(float1, float3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.has(List(float2, float3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.has(List(float1, float2, float3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.floatSeq.has(List.empty[Float]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(float1, float2))
        val b = (2, List(float2, float3, float3))
        for {
          _ <- Ns.i.floatSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.floatSeq.hasNo(float0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.hasNo(float1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSeq.hasNo(float2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq.hasNo(float3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq.hasNo(float3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq.hasNo(float5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floatSeq.hasNo(List(float0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.hasNo(List(float1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSeq.hasNo(List(float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq.hasNo(List(float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq.hasNo(List(float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq.hasNo(List(float5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.floatSeq.hasNo(float1, float2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq.hasNo(float1, float3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq.hasNo(float1, float3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq.hasNo(float1, float5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.floatSeq.hasNo(List(float1, float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq.hasNo(List(float1, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq.hasNo(List(float1, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq.hasNo(List(float1, float5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.floatSeq.hasNo(List.empty[Float]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.floatSeq.insert(List(
            (1, List(float1, float2)),
            (2, List(float2, float3, float3))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // floatSeq not asserted for i = 0
          _ <- Ns.i.a1.floatSeq_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.floatSeq_?.insert(List(
            (0, None),
            (1, Some(List(float1, float2))),
            (2, Some(List(float2, float3, float3))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.floatSeq_().query.get.map(_ ==> List(0))

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.floatSeq_(List(float1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_(List(float1, float2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.floatSeq_(List(float1, float2, float3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.floatSeq_(List(List(float1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_(List(List(float1, float2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSeq_(List(List(float1, float2, float3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.floatSeq_(List(float1), List(float2, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_(List(float1, float2), List(float2, float3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSeq_(List(float1, float2), List(float2, float3, float3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.floatSeq_(List(List(float1), List(float2, float3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_(List(List(float1, float2), List(float2, float3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSeq_(List(List(float1, float2), List(float2, float3, float3))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.floatSeq_(List(float1, float2), List.empty[Float]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSeq_(List.empty[Float]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_(List.empty[List[Float]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_(List(List.empty[Float])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.floatSeq.insert(List(
            (1, List(float1, float2)),
            (2, List(float2, float3, float3))
          )).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.floatSeq_.not(List(float1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.not(List(float1, float2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.floatSeq_.not(List(float1, float2, float3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.floatSeq_.not(List(List(float1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.not(List(List(float1, float2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatSeq_.not(List(List(float1, float2, float3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.floatSeq_.not(List(float1), List(float2, float3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.not(List(float1, float2), List(float2, float3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatSeq_.not(List(float1, float2), List(float2, float3, float3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.floatSeq_.not(List(List(float1), List(float2, float3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.not(List(List(float1, float2), List(float2, float3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatSeq_.not(List(List(float1, float2), List(float2, float3, float3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.floatSeq_.not(List(List(float1, float2), List.empty[Float])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatSeq_.not(List.empty[Float]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.not(List.empty[List[Float]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.floatSeq.insert(List(
            (1, List(float1, float2)),
            (2, List(float2, float3, float3))
          )).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.floatSeq_.has(float0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_.has(float1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSeq_.has(float2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.has(float3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.floatSeq_.has(List(float0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_.has(List(float1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSeq_.has(List(float2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.has(List(float3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.floatSeq_.has(float1, float2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.has(float1, float3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.has(float2, float3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.has(float1, float2, float3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.floatSeq_.has(List(float1, float2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.has(List(float1, float3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.has(List(float2, float3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.has(List(float1, float2, float3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.floatSeq_.has(List.empty[Float]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.floatSeq.insert(List(
            (1, List(float1, float2)),
            (2, List(float2, float3, float3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.floatSeq_.hasNo(float0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.hasNo(float1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatSeq_.hasNo(float2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_.hasNo(float3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSeq_.hasNo(float3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSeq_.hasNo(float5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.floatSeq_.hasNo(List(float0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.hasNo(List(float1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatSeq_.hasNo(List(float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_.hasNo(List(float3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSeq_.hasNo(List(float3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSeq_.hasNo(List(float5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.floatSeq_.hasNo(float1, float2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_.hasNo(float1, float3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_.hasNo(float1, float3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_.hasNo(float1, float5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.floatSeq_.hasNo(List(float1, float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_.hasNo(List(float1, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_.hasNo(List(float1, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_.hasNo(List(float1, float5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.floatSeq_.hasNo(List.empty[Float]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(List(float1, float2)))
        val b = (2, Some(List(float2, float3, float3)))
        val c = (3, None)
        for {
          _ <- Ns.i.floatSeq_?.insert(a, b, c).transact

          _ <- Ns.i.a1.floatSeq_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no float value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.floatSeq_?.query.get.map(_ ==> List(
            (2, Some(List(float2, float3, float3))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(List(float1, float2)))
        val b = (2, Some(List(float2, float3, float3)))
        val c = (3, None)
        for {
          _ <- Ns.i.floatSeq_?.insert(a, b, c).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.floatSeq_?(Some(List(float1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_?(Some(List(float1, float2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.floatSeq_?(Some(List(float1, float2, float3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.floatSeq_?(Some(List(List(float1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_?(Some(List(List(float1, float2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq_?(Some(List(List(float1, float2, float3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.floatSeq_?(Some(List(List(float1), List(float2, float3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_?(Some(List(List(float1, float2), List(float2, float3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq_?(Some(List(List(float1, float2), List(float2, float3, float3)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.floatSeq_?(Some(List.empty[Float])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_?(Some(List.empty[List[Float]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_?(Some(List(List.empty[Float]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.floatSeq_?(Option.empty[List[Float]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.floatSeq_?(Option.empty[List[List[Float]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(List(float1, float2)))
        val b = (2, Some(List(float2, float3, float3)))
        val c = (3, None)
        for {
          _ <- Ns.i.floatSeq_?.insert(a, b, c).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.floatSeq_?.not(Some(List(float1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.not(Some(List(float1, float2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.floatSeq_?.not(Some(List(float1, float2, float3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floatSeq_?.not(Some(List(List(float1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.not(Some(List(List(float1, float2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSeq_?.not(Some(List(List(float1, float2, float3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.floatSeq_?.not(Some(List(List(float1), List(float2, float3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.not(Some(List(List(float1, float2), List(float2, float3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSeq_?.not(Some(List(List(float1, float2), List(float2, float3, float3)))).query.get.map(_ ==> List())

          // Empty Seqs are ignored
          _ <- Ns.i.a1.floatSeq_?.not(Some(List(List(float1, float2), List.empty[Float]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSeq_?.not(Some(List.empty[Float])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.not(Some(List.empty[List[Float]])).query.get.map(_ ==> List(a, b))


          // Negation of None matches all asserted
          _ <- Ns.i.a1.floatSeq_?.not(Option.empty[List[Float]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.not(Option.empty[List[List[Float]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(List(float1, float2)))
        val b = (2, Some(List(float2, float3, float3)))
        val c = (3, None)
        for {
          _ <- Ns.i.floatSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.floatSeq_?.has(Some(float0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_?.has(Some(float1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq_?.has(Some(float2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.has(Some(float3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.floatSeq_?.has(Some(List(float0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_?.has(Some(List(float1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq_?.has(Some(List(float2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.has(Some(List(float3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.floatSeq_?.has(Some(List(float1, float2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.has(Some(List(float1, float3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.has(Some(List(float2, float3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.has(Some(List(float1, float2, float3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.floatSeq_?.has(Some(List.empty[Float])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.floatSeq_?.has(Option.empty[Float]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.floatSeq_?.has(Option.empty[List[Float]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(float1, float2)))
        val b = (2, Some(List(float2, float3, float3)))
        val c = (3, None)
        for {
          _ <- Ns.i.floatSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(float0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(float1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(float5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List(float0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List(float1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List(float2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List(float3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List(float3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List(float5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List(float1, float2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List(float1, float3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List(float1, float3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List(float1, float5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List.empty[Float])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.floatSeq_?.hasNo(Option.empty[Float]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.hasNo(Option.empty[List[Float]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}