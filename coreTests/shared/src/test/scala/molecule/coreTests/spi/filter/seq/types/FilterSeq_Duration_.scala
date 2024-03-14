// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.time.Duration
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_Duration_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, List(duration1, duration2))
        val b = (2, List(duration2, duration3, duration3))
        for {
          _ <- Ns.i.durationSeq.insert(List(a, b)).transact

          _ <- Ns.i.a1.durationSeq.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, List(duration1, duration2))
        val b = (2, List(duration2, duration3, duration3))
        for {
          _ <- Ns.i.durationSeq.insert(List(a, b)).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.durationSeq(List(duration1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq(List(duration1, duration2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.durationSeq(List(duration1, duration2, duration3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.durationSeq(List(List(duration1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq(List(List(duration1, duration2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq(List(List(duration1, duration2, duration3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.durationSeq(List(duration1), List(duration2, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq(List(duration1, duration2), List(duration2, duration3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq(List(duration1, duration2), List(duration2, duration3, duration3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durationSeq(List(List(duration1), List(duration2, duration3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq(List(List(duration1, duration2), List(duration2, duration3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq(List(List(duration1, duration2), List(duration2, duration3, duration3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.durationSeq(List(duration1, duration2), List.empty[Duration]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq(List.empty[Duration], List(duration1, duration2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq(List.empty[Duration], List.empty[Duration]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq(List.empty[Duration]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq(List.empty[List[Duration]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq(List(List.empty[Duration])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, List(duration1, duration2))
        val b = (2, List(duration2, duration3, duration3))
        for {
          _ <- Ns.i.durationSeq.insert(List(a, b)).transact

          // Exact Seq non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.durationSeq.not(List(duration1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.not(List(duration1, duration2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.durationSeq.not(List(duration1, duration2, duration3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durationSeq.not(List(List(duration1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.not(List(List(duration1, duration2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSeq.not(List(List(duration1, duration2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSeq.not(List(List(duration1, duration2, duration3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.durationSeq.not(List(duration1), List(duration2, duration3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.not(List(duration1, duration2), List(duration2, duration3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSeq.not(List(duration1, duration2), List(duration2, duration3, duration3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.durationSeq.not(List(List(duration1), List(duration2, duration3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.not(List(List(duration1, duration2), List(duration2, duration3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSeq.not(List(List(duration1, duration2), List(duration2, duration3, duration3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.durationSeq.not(List(List(duration1, duration2), List.empty[Duration])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSeq.not(List.empty[Duration]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.not(List.empty[List[Duration]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, List(duration1, duration2))
        val b = (2, List(duration2, duration3, duration3))
        for {
          _ <- Ns.i.durationSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.durationSeq.has(duration0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq.has(duration1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq.has(duration2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.has(duration3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.durationSeq.has(List(duration0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq.has(List(duration1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq.has(List(duration2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.has(List(duration3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.durationSeq.has(duration1, duration2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.has(duration1, duration3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.has(duration2, duration3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.has(duration1, duration2, duration3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durationSeq.has(List(duration1, duration2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.has(List(duration1, duration3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.has(List(duration2, duration3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.has(List(duration1, duration2, duration3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.durationSeq.has(List.empty[Duration]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(duration1, duration2))
        val b = (2, List(duration2, duration3, duration3))
        for {
          _ <- Ns.i.durationSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.durationSeq.hasNo(duration0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.hasNo(duration1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSeq.hasNo(duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq.hasNo(duration3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq.hasNo(duration3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq.hasNo(duration5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durationSeq.hasNo(List(duration0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq.hasNo(List(duration1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSeq.hasNo(List(duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq.hasNo(List(duration3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq.hasNo(List(duration3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq.hasNo(List(duration5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.durationSeq.hasNo(duration1, duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq.hasNo(duration1, duration3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq.hasNo(duration1, duration3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq.hasNo(duration1, duration5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.durationSeq.hasNo(List(duration1, duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq.hasNo(List(duration1, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq.hasNo(List(duration1, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq.hasNo(List(duration1, duration5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.durationSeq.hasNo(List.empty[Duration]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.durationSeq.insert(List(
            (1, List(duration1, duration2)),
            (2, List(duration2, duration3, duration3))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // durationSeq not asserted for i = 0
          _ <- Ns.i.a1.durationSeq_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.durationSeq_?.insert(List(
            (0, None),
            (1, Some(List(duration1, duration2))),
            (2, Some(List(duration2, duration3, duration3))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.durationSeq_().query.get.map(_ ==> List(0))

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.durationSeq_(List(duration1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_(List(duration1, duration2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.durationSeq_(List(duration1, duration2, duration3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.durationSeq_(List(List(duration1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_(List(List(duration1, duration2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSeq_(List(List(duration1, duration2, duration3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.durationSeq_(List(duration1), List(duration2, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_(List(duration1, duration2), List(duration2, duration3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSeq_(List(duration1, duration2), List(duration2, duration3, duration3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.durationSeq_(List(List(duration1), List(duration2, duration3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_(List(List(duration1, duration2), List(duration2, duration3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSeq_(List(List(duration1, duration2), List(duration2, duration3, duration3))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.durationSeq_(List(duration1, duration2), List.empty[Duration]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSeq_(List.empty[Duration]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_(List.empty[List[Duration]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_(List(List.empty[Duration])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.durationSeq.insert(List(
            (1, List(duration1, duration2)),
            (2, List(duration2, duration3, duration3))
          )).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.durationSeq_.not(List(duration1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.not(List(duration1, duration2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.durationSeq_.not(List(duration1, duration2, duration3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.durationSeq_.not(List(List(duration1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.not(List(List(duration1, duration2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationSeq_.not(List(List(duration1, duration2, duration3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.durationSeq_.not(List(duration1), List(duration2, duration3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.not(List(duration1, duration2), List(duration2, duration3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationSeq_.not(List(duration1, duration2), List(duration2, duration3, duration3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.durationSeq_.not(List(List(duration1), List(duration2, duration3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.not(List(List(duration1, duration2), List(duration2, duration3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationSeq_.not(List(List(duration1, duration2), List(duration2, duration3, duration3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.durationSeq_.not(List(List(duration1, duration2), List.empty[Duration])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationSeq_.not(List.empty[Duration]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.not(List.empty[List[Duration]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.durationSeq.insert(List(
            (1, List(duration1, duration2)),
            (2, List(duration2, duration3, duration3))
          )).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.durationSeq_.has(duration0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_.has(duration1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSeq_.has(duration2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.has(duration3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.durationSeq_.has(List(duration0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_.has(List(duration1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSeq_.has(List(duration2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.has(List(duration3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.durationSeq_.has(duration1, duration2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.has(duration1, duration3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.has(duration2, duration3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.has(duration1, duration2, duration3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.durationSeq_.has(List(duration1, duration2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.has(List(duration1, duration3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.has(List(duration2, duration3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.has(List(duration1, duration2, duration3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.durationSeq_.has(List.empty[Duration]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.durationSeq.insert(List(
            (1, List(duration1, duration2)),
            (2, List(duration2, duration3, duration3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.durationSeq_.hasNo(duration0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.hasNo(duration1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationSeq_.hasNo(duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_.hasNo(duration3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSeq_.hasNo(duration3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSeq_.hasNo(duration5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.durationSeq_.hasNo(List(duration0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSeq_.hasNo(List(duration1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationSeq_.hasNo(List(duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_.hasNo(List(duration3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSeq_.hasNo(List(duration3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSeq_.hasNo(List(duration5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.durationSeq_.hasNo(duration1, duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_.hasNo(duration1, duration3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_.hasNo(duration1, duration3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_.hasNo(duration1, duration5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.durationSeq_.hasNo(List(duration1, duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_.hasNo(List(duration1, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_.hasNo(List(duration1, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_.hasNo(List(duration1, duration5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.durationSeq_.hasNo(List.empty[Duration]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(List(duration1, duration2)))
        val b = (2, Some(List(duration2, duration3, duration3)))
        val c = (3, None)
        for {
          _ <- Ns.i.durationSeq_?.insert(a, b, c).transact

          _ <- Ns.i.a1.durationSeq_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no duration value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.durationSeq_?.query.get.map(_ ==> List(
            (2, Some(List(duration2, duration3, duration3))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(List(duration1, duration2)))
        val b = (2, Some(List(duration2, duration3, duration3)))
        val c = (3, None)
        for {
          _ <- Ns.i.durationSeq_?.insert(a, b, c).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.durationSeq_?(Some(List(duration1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_?(Some(List(duration1, duration2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.durationSeq_?(Some(List(duration1, duration2, duration3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.durationSeq_?(Some(List(List(duration1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_?(Some(List(List(duration1, duration2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq_?(Some(List(List(duration1, duration2, duration3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.durationSeq_?(Some(List(List(duration1), List(duration2, duration3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_?(Some(List(List(duration1, duration2), List(duration2, duration3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq_?(Some(List(List(duration1, duration2), List(duration2, duration3, duration3)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.durationSeq_?(Some(List.empty[Duration])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_?(Some(List.empty[List[Duration]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_?(Some(List(List.empty[Duration]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.durationSeq_?(Option.empty[List[Duration]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.durationSeq_?(Option.empty[List[List[Duration]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(List(duration1, duration2)))
        val b = (2, Some(List(duration2, duration3, duration3)))
        val c = (3, None)
        for {
          _ <- Ns.i.durationSeq_?.insert(a, b, c).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.durationSeq_?.not(Some(List(duration1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq_?.not(Some(List(duration1, duration2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.durationSeq_?.not(Some(List(duration1, duration2, duration3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durationSeq_?.not(Some(List(List(duration1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq_?.not(Some(List(List(duration1, duration2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSeq_?.not(Some(List(List(duration1, duration2, duration3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.durationSeq_?.not(Some(List(List(duration1), List(duration2, duration3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq_?.not(Some(List(List(duration1, duration2), List(duration2, duration3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSeq_?.not(Some(List(List(duration1, duration2), List(duration2, duration3, duration3)))).query.get.map(_ ==> List())

          // Empty Seqs are ignored
          _ <- Ns.i.a1.durationSeq_?.not(Some(List(List(duration1, duration2), List.empty[Duration]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSeq_?.not(Some(List.empty[Duration])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq_?.not(Some(List.empty[List[Duration]])).query.get.map(_ ==> List(a, b))


          // Negation of None matches all asserted
          _ <- Ns.i.a1.durationSeq_?.not(Option.empty[List[Duration]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq_?.not(Option.empty[List[List[Duration]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(List(duration1, duration2)))
        val b = (2, Some(List(duration2, duration3, duration3)))
        val c = (3, None)
        for {
          _ <- Ns.i.durationSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.durationSeq_?.has(Some(duration0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_?.has(Some(duration1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq_?.has(Some(duration2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq_?.has(Some(duration3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.durationSeq_?.has(Some(List(duration0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_?.has(Some(List(duration1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq_?.has(Some(List(duration2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq_?.has(Some(List(duration3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.durationSeq_?.has(Some(List(duration1, duration2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq_?.has(Some(List(duration1, duration3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq_?.has(Some(List(duration2, duration3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq_?.has(Some(List(duration1, duration2, duration3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.durationSeq_?.has(Some(List.empty[Duration])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.durationSeq_?.has(Option.empty[Duration]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.durationSeq_?.has(Option.empty[List[Duration]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(duration1, duration2)))
        val b = (2, Some(List(duration2, duration3, duration3)))
        val c = (3, None)
        for {
          _ <- Ns.i.durationSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.durationSeq_?.hasNo(Some(duration0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq_?.hasNo(Some(duration1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSeq_?.hasNo(Some(duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_?.hasNo(Some(duration3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq_?.hasNo(Some(duration3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq_?.hasNo(Some(duration5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durationSeq_?.hasNo(Some(List(duration0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq_?.hasNo(Some(List(duration1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSeq_?.hasNo(Some(List(duration2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_?.hasNo(Some(List(duration3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq_?.hasNo(Some(List(duration3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSeq_?.hasNo(Some(List(duration5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.durationSeq_?.hasNo(Some(List(duration1, duration2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_?.hasNo(Some(List(duration1, duration3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_?.hasNo(Some(List(duration1, duration3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSeq_?.hasNo(Some(List(duration1, duration5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.durationSeq_?.hasNo(Some(List.empty[Duration])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.durationSeq_?.hasNo(Option.empty[Duration]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSeq_?.hasNo(Option.empty[List[Duration]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}