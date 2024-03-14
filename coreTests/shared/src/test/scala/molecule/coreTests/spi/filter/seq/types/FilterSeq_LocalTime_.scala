// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.time.LocalTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_LocalTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, List(localTime1, localTime2))
        val b = (2, List(localTime2, localTime3, localTime3))
        for {
          _ <- Ns.i.localTimeSeq.insert(List(a, b)).transact

          _ <- Ns.i.a1.localTimeSeq.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, List(localTime1, localTime2))
        val b = (2, List(localTime2, localTime3, localTime3))
        for {
          _ <- Ns.i.localTimeSeq.insert(List(a, b)).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.localTimeSeq(List(localTime1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq(List(localTime1, localTime2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.localTimeSeq(List(localTime1, localTime2, localTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localTimeSeq(List(List(localTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq(List(List(localTime1, localTime2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq(List(List(localTime1, localTime2, localTime3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.localTimeSeq(List(localTime1), List(localTime2, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq(List(localTime1, localTime2), List(localTime2, localTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq(List(localTime1, localTime2), List(localTime2, localTime3, localTime3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localTimeSeq(List(List(localTime1), List(localTime2, localTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq(List(List(localTime1, localTime2), List(localTime2, localTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq(List(List(localTime1, localTime2), List(localTime2, localTime3, localTime3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.localTimeSeq(List(localTime1, localTime2), List.empty[LocalTime]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq(List.empty[LocalTime], List(localTime1, localTime2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq(List.empty[LocalTime], List.empty[LocalTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq(List.empty[LocalTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq(List.empty[List[LocalTime]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq(List(List.empty[LocalTime])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, List(localTime1, localTime2))
        val b = (2, List(localTime2, localTime3, localTime3))
        for {
          _ <- Ns.i.localTimeSeq.insert(List(a, b)).transact

          // Exact Seq non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.localTimeSeq.not(List(localTime1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.not(List(localTime1, localTime2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.localTimeSeq.not(List(localTime1, localTime2, localTime3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localTimeSeq.not(List(List(localTime1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.not(List(List(localTime1, localTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeSeq.not(List(List(localTime1, localTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeSeq.not(List(List(localTime1, localTime2, localTime3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.localTimeSeq.not(List(localTime1), List(localTime2, localTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.not(List(localTime1, localTime2), List(localTime2, localTime3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeSeq.not(List(localTime1, localTime2), List(localTime2, localTime3, localTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localTimeSeq.not(List(List(localTime1), List(localTime2, localTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.not(List(List(localTime1, localTime2), List(localTime2, localTime3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeSeq.not(List(List(localTime1, localTime2), List(localTime2, localTime3, localTime3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.localTimeSeq.not(List(List(localTime1, localTime2), List.empty[LocalTime])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeSeq.not(List.empty[LocalTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.not(List.empty[List[LocalTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, List(localTime1, localTime2))
        val b = (2, List(localTime2, localTime3, localTime3))
        for {
          _ <- Ns.i.localTimeSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.localTimeSeq.has(localTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq.has(localTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq.has(localTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.has(localTime3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localTimeSeq.has(List(localTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq.has(List(localTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq.has(List(localTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.has(List(localTime3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localTimeSeq.has(localTime1, localTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.has(localTime1, localTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.has(localTime2, localTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.has(localTime1, localTime2, localTime3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localTimeSeq.has(List(localTime1, localTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.has(List(localTime1, localTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.has(List(localTime2, localTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.has(List(localTime1, localTime2, localTime3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.localTimeSeq.has(List.empty[LocalTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(localTime1, localTime2))
        val b = (2, List(localTime2, localTime3, localTime3))
        for {
          _ <- Ns.i.localTimeSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.localTimeSeq.hasNo(localTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.hasNo(localTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeSeq.hasNo(localTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq.hasNo(localTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq.hasNo(localTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq.hasNo(localTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localTimeSeq.hasNo(List(localTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.hasNo(List(localTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeSeq.hasNo(List(localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq.hasNo(List(localTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq.hasNo(List(localTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq.hasNo(List(localTime5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.localTimeSeq.hasNo(localTime1, localTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq.hasNo(localTime1, localTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq.hasNo(localTime1, localTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq.hasNo(localTime1, localTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localTimeSeq.hasNo(List(localTime1, localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq.hasNo(List(localTime1, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq.hasNo(List(localTime1, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq.hasNo(List(localTime1, localTime5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.localTimeSeq.hasNo(List.empty[LocalTime]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localTimeSeq.insert(List(
            (1, List(localTime1, localTime2)),
            (2, List(localTime2, localTime3, localTime3))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // localTimeSeq not asserted for i = 0
          _ <- Ns.i.a1.localTimeSeq_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.localTimeSeq_?.insert(List(
            (0, None),
            (1, Some(List(localTime1, localTime2))),
            (2, Some(List(localTime2, localTime3, localTime3))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.localTimeSeq_().query.get.map(_ ==> List(0))

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.localTimeSeq_(List(localTime1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_(List(localTime1, localTime2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.localTimeSeq_(List(localTime1, localTime2, localTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localTimeSeq_(List(List(localTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_(List(List(localTime1, localTime2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSeq_(List(List(localTime1, localTime2, localTime3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.localTimeSeq_(List(localTime1), List(localTime2, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_(List(localTime1, localTime2), List(localTime2, localTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSeq_(List(localTime1, localTime2), List(localTime2, localTime3, localTime3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localTimeSeq_(List(List(localTime1), List(localTime2, localTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_(List(List(localTime1, localTime2), List(localTime2, localTime3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSeq_(List(List(localTime1, localTime2), List(localTime2, localTime3, localTime3))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.localTimeSeq_(List(localTime1, localTime2), List.empty[LocalTime]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSeq_(List.empty[LocalTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_(List.empty[List[LocalTime]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_(List(List.empty[LocalTime])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localTimeSeq.insert(List(
            (1, List(localTime1, localTime2)),
            (2, List(localTime2, localTime3, localTime3))
          )).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.localTimeSeq_.not(List(localTime1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.not(List(localTime1, localTime2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.localTimeSeq_.not(List(localTime1, localTime2, localTime3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localTimeSeq_.not(List(List(localTime1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.not(List(List(localTime1, localTime2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeSeq_.not(List(List(localTime1, localTime2, localTime3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.localTimeSeq_.not(List(localTime1), List(localTime2, localTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.not(List(localTime1, localTime2), List(localTime2, localTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeSeq_.not(List(localTime1, localTime2), List(localTime2, localTime3, localTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localTimeSeq_.not(List(List(localTime1), List(localTime2, localTime3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.not(List(List(localTime1, localTime2), List(localTime2, localTime3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeSeq_.not(List(List(localTime1, localTime2), List(localTime2, localTime3, localTime3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.localTimeSeq_.not(List(List(localTime1, localTime2), List.empty[LocalTime])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeSeq_.not(List.empty[LocalTime]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.not(List.empty[List[LocalTime]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localTimeSeq.insert(List(
            (1, List(localTime1, localTime2)),
            (2, List(localTime2, localTime3, localTime3))
          )).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.localTimeSeq_.has(localTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_.has(localTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSeq_.has(localTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.has(localTime3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localTimeSeq_.has(List(localTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_.has(List(localTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSeq_.has(List(localTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.has(List(localTime3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localTimeSeq_.has(localTime1, localTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.has(localTime1, localTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.has(localTime2, localTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.has(localTime1, localTime2, localTime3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localTimeSeq_.has(List(localTime1, localTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.has(List(localTime1, localTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.has(List(localTime2, localTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.has(List(localTime1, localTime2, localTime3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.localTimeSeq_.has(List.empty[LocalTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localTimeSeq.insert(List(
            (1, List(localTime1, localTime2)),
            (2, List(localTime2, localTime3, localTime3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.localTimeSeq_.hasNo(localTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.hasNo(localTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeSeq_.hasNo(localTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_.hasNo(localTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSeq_.hasNo(localTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSeq_.hasNo(localTime5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List(localTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List(localTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List(localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List(localTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List(localTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List(localTime5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.localTimeSeq_.hasNo(localTime1, localTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_.hasNo(localTime1, localTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_.hasNo(localTime1, localTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_.hasNo(localTime1, localTime5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List(localTime1, localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List(localTime1, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List(localTime1, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List(localTime1, localTime5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List.empty[LocalTime]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(List(localTime1, localTime2)))
        val b = (2, Some(List(localTime2, localTime3, localTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.localTimeSeq_?.insert(a, b, c).transact

          _ <- Ns.i.a1.localTimeSeq_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no localTime value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.localTimeSeq_?.query.get.map(_ ==> List(
            (2, Some(List(localTime2, localTime3, localTime3))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(List(localTime1, localTime2)))
        val b = (2, Some(List(localTime2, localTime3, localTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.localTimeSeq_?.insert(a, b, c).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.localTimeSeq_?(Some(List(localTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_?(Some(List(localTime1, localTime2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.localTimeSeq_?(Some(List(localTime1, localTime2, localTime3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localTimeSeq_?(Some(List(List(localTime1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_?(Some(List(List(localTime1, localTime2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq_?(Some(List(List(localTime1, localTime2, localTime3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.localTimeSeq_?(Some(List(List(localTime1), List(localTime2, localTime3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_?(Some(List(List(localTime1, localTime2), List(localTime2, localTime3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq_?(Some(List(List(localTime1, localTime2), List(localTime2, localTime3, localTime3)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.localTimeSeq_?(Some(List.empty[LocalTime])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_?(Some(List.empty[List[LocalTime]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_?(Some(List(List.empty[LocalTime]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.localTimeSeq_?(Option.empty[List[LocalTime]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.localTimeSeq_?(Option.empty[List[List[LocalTime]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(List(localTime1, localTime2)))
        val b = (2, Some(List(localTime2, localTime3, localTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.localTimeSeq_?.insert(a, b, c).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.localTimeSeq_?.not(Some(List(localTime1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq_?.not(Some(List(localTime1, localTime2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.localTimeSeq_?.not(Some(List(localTime1, localTime2, localTime3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localTimeSeq_?.not(Some(List(List(localTime1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq_?.not(Some(List(List(localTime1, localTime2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeSeq_?.not(Some(List(List(localTime1, localTime2, localTime3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.localTimeSeq_?.not(Some(List(List(localTime1), List(localTime2, localTime3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq_?.not(Some(List(List(localTime1, localTime2), List(localTime2, localTime3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeSeq_?.not(Some(List(List(localTime1, localTime2), List(localTime2, localTime3, localTime3)))).query.get.map(_ ==> List())

          // Empty Seqs are ignored
          _ <- Ns.i.a1.localTimeSeq_?.not(Some(List(List(localTime1, localTime2), List.empty[LocalTime]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeSeq_?.not(Some(List.empty[LocalTime])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq_?.not(Some(List.empty[List[LocalTime]])).query.get.map(_ ==> List(a, b))


          // Negation of None matches all asserted
          _ <- Ns.i.a1.localTimeSeq_?.not(Option.empty[List[LocalTime]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq_?.not(Option.empty[List[List[LocalTime]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(List(localTime1, localTime2)))
        val b = (2, Some(List(localTime2, localTime3, localTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.localTimeSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.localTimeSeq_?.has(Some(localTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_?.has(Some(localTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq_?.has(Some(localTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq_?.has(Some(localTime3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localTimeSeq_?.has(Some(List(localTime0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_?.has(Some(List(localTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq_?.has(Some(List(localTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq_?.has(Some(List(localTime3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localTimeSeq_?.has(Some(List(localTime1, localTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq_?.has(Some(List(localTime1, localTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq_?.has(Some(List(localTime2, localTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq_?.has(Some(List(localTime1, localTime2, localTime3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.localTimeSeq_?.has(Some(List.empty[LocalTime])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.localTimeSeq_?.has(Option.empty[LocalTime]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.localTimeSeq_?.has(Option.empty[List[LocalTime]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(localTime1, localTime2)))
        val b = (2, Some(List(localTime2, localTime3, localTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.localTimeSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.localTimeSeq_?.hasNo(Some(localTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq_?.hasNo(Some(localTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeSeq_?.hasNo(Some(localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_?.hasNo(Some(localTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq_?.hasNo(Some(localTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq_?.hasNo(Some(localTime5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localTimeSeq_?.hasNo(Some(List(localTime0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq_?.hasNo(Some(List(localTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeSeq_?.hasNo(Some(List(localTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_?.hasNo(Some(List(localTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq_?.hasNo(Some(List(localTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq_?.hasNo(Some(List(localTime5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.localTimeSeq_?.hasNo(Some(List(localTime1, localTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_?.hasNo(Some(List(localTime1, localTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_?.hasNo(Some(List(localTime1, localTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_?.hasNo(Some(List(localTime1, localTime5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.localTimeSeq_?.hasNo(Some(List.empty[LocalTime])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.localTimeSeq_?.hasNo(Option.empty[LocalTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq_?.hasNo(Option.empty[List[LocalTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}