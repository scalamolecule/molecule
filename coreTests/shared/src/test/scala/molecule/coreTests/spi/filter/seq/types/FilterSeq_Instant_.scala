// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.time.Instant
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_Instant_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, List(instant1, instant2))
        val b = (2, List(instant2, instant3, instant3))
        for {
          _ <- Ns.i.instantSeq.insert(List(a, b)).transact

          _ <- Ns.i.a1.instantSeq.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, List(instant1, instant2))
        val b = (2, List(instant2, instant3, instant3))
        for {
          _ <- Ns.i.instantSeq.insert(List(a, b)).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.instantSeq(List(instant1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq(List(instant1, instant2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.instantSeq(List(instant1, instant2, instant3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.instantSeq(List(List(instant1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq(List(List(instant1, instant2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSeq(List(List(instant1, instant2, instant3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.instantSeq(List(instant1), List(instant2, instant3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq(List(instant1, instant2), List(instant2, instant3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSeq(List(instant1, instant2), List(instant2, instant3, instant3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.instantSeq(List(List(instant1), List(instant2, instant3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq(List(List(instant1, instant2), List(instant2, instant3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSeq(List(List(instant1, instant2), List(instant2, instant3, instant3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.instantSeq(List(instant1, instant2), List.empty[Instant]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSeq(List.empty[Instant], List(instant1, instant2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSeq(List.empty[Instant], List.empty[Instant]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq(List.empty[Instant]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq(List.empty[List[Instant]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq(List(List.empty[Instant])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, List(instant1, instant2))
        val b = (2, List(instant2, instant3, instant3))
        for {
          _ <- Ns.i.instantSeq.insert(List(a, b)).transact

          // Exact Seq non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.instantSeq.not(List(instant1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq.not(List(instant1, instant2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.instantSeq.not(List(instant1, instant2, instant3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.instantSeq.not(List(List(instant1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq.not(List(List(instant1, instant2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instantSeq.not(List(List(instant1, instant2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instantSeq.not(List(List(instant1, instant2, instant3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.instantSeq.not(List(instant1), List(instant2, instant3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq.not(List(instant1, instant2), List(instant2, instant3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instantSeq.not(List(instant1, instant2), List(instant2, instant3, instant3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.instantSeq.not(List(List(instant1), List(instant2, instant3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq.not(List(List(instant1, instant2), List(instant2, instant3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instantSeq.not(List(List(instant1, instant2), List(instant2, instant3, instant3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.instantSeq.not(List(List(instant1, instant2), List.empty[Instant])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instantSeq.not(List.empty[Instant]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq.not(List.empty[List[Instant]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, List(instant1, instant2))
        val b = (2, List(instant2, instant3, instant3))
        for {
          _ <- Ns.i.instantSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.instantSeq.has(instant0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq.has(instant1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSeq.has(instant2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq.has(instant3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.instantSeq.has(List(instant0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq.has(List(instant1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSeq.has(List(instant2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq.has(List(instant3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.instantSeq.has(instant1, instant2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq.has(instant1, instant3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq.has(instant2, instant3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq.has(instant1, instant2, instant3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.instantSeq.has(List(instant1, instant2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq.has(List(instant1, instant3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq.has(List(instant2, instant3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq.has(List(instant1, instant2, instant3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.instantSeq.has(List.empty[Instant]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(instant1, instant2))
        val b = (2, List(instant2, instant3, instant3))
        for {
          _ <- Ns.i.instantSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.instantSeq.hasNo(instant0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq.hasNo(instant1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instantSeq.hasNo(instant2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq.hasNo(instant3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSeq.hasNo(instant3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSeq.hasNo(instant5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.instantSeq.hasNo(List(instant0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq.hasNo(List(instant1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instantSeq.hasNo(List(instant2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq.hasNo(List(instant3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSeq.hasNo(List(instant3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSeq.hasNo(List(instant5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.instantSeq.hasNo(instant1, instant2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq.hasNo(instant1, instant3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq.hasNo(instant1, instant3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq.hasNo(instant1, instant5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.instantSeq.hasNo(List(instant1, instant2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq.hasNo(List(instant1, instant3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq.hasNo(List(instant1, instant3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq.hasNo(List(instant1, instant5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.instantSeq.hasNo(List.empty[Instant]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.instantSeq.insert(List(
            (1, List(instant1, instant2)),
            (2, List(instant2, instant3, instant3))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // instantSeq not asserted for i = 0
          _ <- Ns.i.a1.instantSeq_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.instantSeq_?.insert(List(
            (0, None),
            (1, Some(List(instant1, instant2))),
            (2, Some(List(instant2, instant3, instant3))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.instantSeq_().query.get.map(_ ==> List(0))

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.instantSeq_(List(instant1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_(List(instant1, instant2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.instantSeq_(List(instant1, instant2, instant3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.instantSeq_(List(List(instant1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_(List(List(instant1, instant2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantSeq_(List(List(instant1, instant2, instant3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.instantSeq_(List(instant1), List(instant2, instant3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_(List(instant1, instant2), List(instant2, instant3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantSeq_(List(instant1, instant2), List(instant2, instant3, instant3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.instantSeq_(List(List(instant1), List(instant2, instant3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_(List(List(instant1, instant2), List(instant2, instant3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantSeq_(List(List(instant1, instant2), List(instant2, instant3, instant3))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.instantSeq_(List(instant1, instant2), List.empty[Instant]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantSeq_(List.empty[Instant]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_(List.empty[List[Instant]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_(List(List.empty[Instant])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.instantSeq.insert(List(
            (1, List(instant1, instant2)),
            (2, List(instant2, instant3, instant3))
          )).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.instantSeq_.not(List(instant1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSeq_.not(List(instant1, instant2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.instantSeq_.not(List(instant1, instant2, instant3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.instantSeq_.not(List(List(instant1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSeq_.not(List(List(instant1, instant2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantSeq_.not(List(List(instant1, instant2, instant3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.instantSeq_.not(List(instant1), List(instant2, instant3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSeq_.not(List(instant1, instant2), List(instant2, instant3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantSeq_.not(List(instant1, instant2), List(instant2, instant3, instant3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.instantSeq_.not(List(List(instant1), List(instant2, instant3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSeq_.not(List(List(instant1, instant2), List(instant2, instant3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantSeq_.not(List(List(instant1, instant2), List(instant2, instant3, instant3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.instantSeq_.not(List(List(instant1, instant2), List.empty[Instant])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantSeq_.not(List.empty[Instant]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSeq_.not(List.empty[List[Instant]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.instantSeq.insert(List(
            (1, List(instant1, instant2)),
            (2, List(instant2, instant3, instant3))
          )).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.instantSeq_.has(instant0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_.has(instant1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantSeq_.has(instant2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSeq_.has(instant3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.instantSeq_.has(List(instant0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_.has(List(instant1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantSeq_.has(List(instant2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSeq_.has(List(instant3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.instantSeq_.has(instant1, instant2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSeq_.has(instant1, instant3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSeq_.has(instant2, instant3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSeq_.has(instant1, instant2, instant3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.instantSeq_.has(List(instant1, instant2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSeq_.has(List(instant1, instant3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSeq_.has(List(instant2, instant3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSeq_.has(List(instant1, instant2, instant3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.instantSeq_.has(List.empty[Instant]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.instantSeq.insert(List(
            (1, List(instant1, instant2)),
            (2, List(instant2, instant3, instant3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.instantSeq_.hasNo(instant0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSeq_.hasNo(instant1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantSeq_.hasNo(instant2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_.hasNo(instant3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantSeq_.hasNo(instant3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantSeq_.hasNo(instant5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.instantSeq_.hasNo(List(instant0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instantSeq_.hasNo(List(instant1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instantSeq_.hasNo(List(instant2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_.hasNo(List(instant3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantSeq_.hasNo(List(instant3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instantSeq_.hasNo(List(instant5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.instantSeq_.hasNo(instant1, instant2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_.hasNo(instant1, instant3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_.hasNo(instant1, instant3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_.hasNo(instant1, instant5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.instantSeq_.hasNo(List(instant1, instant2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_.hasNo(List(instant1, instant3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_.hasNo(List(instant1, instant3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_.hasNo(List(instant1, instant5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.instantSeq_.hasNo(List.empty[Instant]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(List(instant1, instant2)))
        val b = (2, Some(List(instant2, instant3, instant3)))
        val c = (3, None)
        for {
          _ <- Ns.i.instantSeq_?.insert(a, b, c).transact

          _ <- Ns.i.a1.instantSeq_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no instant value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.instantSeq_?.query.get.map(_ ==> List(
            (2, Some(List(instant2, instant3, instant3))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(List(instant1, instant2)))
        val b = (2, Some(List(instant2, instant3, instant3)))
        val c = (3, None)
        for {
          _ <- Ns.i.instantSeq_?.insert(a, b, c).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.instantSeq_?(Some(List(instant1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_?(Some(List(instant1, instant2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.instantSeq_?(Some(List(instant1, instant2, instant3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.instantSeq_?(Some(List(List(instant1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_?(Some(List(List(instant1, instant2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSeq_?(Some(List(List(instant1, instant2, instant3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.instantSeq_?(Some(List(List(instant1), List(instant2, instant3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_?(Some(List(List(instant1, instant2), List(instant2, instant3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSeq_?(Some(List(List(instant1, instant2), List(instant2, instant3, instant3)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.instantSeq_?(Some(List.empty[Instant])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_?(Some(List.empty[List[Instant]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_?(Some(List(List.empty[Instant]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.instantSeq_?(Option.empty[List[Instant]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.instantSeq_?(Option.empty[List[List[Instant]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(List(instant1, instant2)))
        val b = (2, Some(List(instant2, instant3, instant3)))
        val c = (3, None)
        for {
          _ <- Ns.i.instantSeq_?.insert(a, b, c).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.instantSeq_?.not(Some(List(instant1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq_?.not(Some(List(instant1, instant2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.instantSeq_?.not(Some(List(instant1, instant2, instant3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.instantSeq_?.not(Some(List(List(instant1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq_?.not(Some(List(List(instant1, instant2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instantSeq_?.not(Some(List(List(instant1, instant2, instant3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.instantSeq_?.not(Some(List(List(instant1), List(instant2, instant3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq_?.not(Some(List(List(instant1, instant2), List(instant2, instant3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instantSeq_?.not(Some(List(List(instant1, instant2), List(instant2, instant3, instant3)))).query.get.map(_ ==> List())

          // Empty Seqs are ignored
          _ <- Ns.i.a1.instantSeq_?.not(Some(List(List(instant1, instant2), List.empty[Instant]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instantSeq_?.not(Some(List.empty[Instant])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq_?.not(Some(List.empty[List[Instant]])).query.get.map(_ ==> List(a, b))


          // Negation of None matches all asserted
          _ <- Ns.i.a1.instantSeq_?.not(Option.empty[List[Instant]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq_?.not(Option.empty[List[List[Instant]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(List(instant1, instant2)))
        val b = (2, Some(List(instant2, instant3, instant3)))
        val c = (3, None)
        for {
          _ <- Ns.i.instantSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.instantSeq_?.has(Some(instant0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_?.has(Some(instant1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSeq_?.has(Some(instant2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq_?.has(Some(instant3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.instantSeq_?.has(Some(List(instant0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_?.has(Some(List(instant1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSeq_?.has(Some(List(instant2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq_?.has(Some(List(instant3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.instantSeq_?.has(Some(List(instant1, instant2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq_?.has(Some(List(instant1, instant3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq_?.has(Some(List(instant2, instant3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq_?.has(Some(List(instant1, instant2, instant3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.instantSeq_?.has(Some(List.empty[Instant])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.instantSeq_?.has(Option.empty[Instant]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.instantSeq_?.has(Option.empty[List[Instant]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(instant1, instant2)))
        val b = (2, Some(List(instant2, instant3, instant3)))
        val c = (3, None)
        for {
          _ <- Ns.i.instantSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.instantSeq_?.hasNo(Some(instant0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq_?.hasNo(Some(instant1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instantSeq_?.hasNo(Some(instant2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_?.hasNo(Some(instant3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSeq_?.hasNo(Some(instant3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSeq_?.hasNo(Some(instant5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.instantSeq_?.hasNo(Some(List(instant0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq_?.hasNo(Some(List(instant1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instantSeq_?.hasNo(Some(List(instant2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_?.hasNo(Some(List(instant3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSeq_?.hasNo(Some(List(instant3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instantSeq_?.hasNo(Some(List(instant5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.instantSeq_?.hasNo(Some(List(instant1, instant2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_?.hasNo(Some(List(instant1, instant3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_?.hasNo(Some(List(instant1, instant3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instantSeq_?.hasNo(Some(List(instant1, instant5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.instantSeq_?.hasNo(Some(List.empty[Instant])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.instantSeq_?.hasNo(Option.empty[Instant]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instantSeq_?.hasNo(Option.empty[List[Instant]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}