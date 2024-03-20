// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.time.OffsetTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_OffsetTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, List(offsetTime1, offsetTime2))
        val b = (2, List(offsetTime2, offsetTime3, offsetTime3))
        for {
          _ <- Ns.i.offsetTimeSeq.insert(List(a, b)).transact

          _ <- Ns.i.a1.offsetTimeSeq.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, List(offsetTime1, offsetTime2))
        val b = (2, List(offsetTime2, offsetTime3, offsetTime3))
        for {
          _ <- Ns.i.offsetTimeSeq.insert(List(a, b)).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.offsetTimeSeq(List(offsetTime1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.offsetTimeSeq(List(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq(List(List(offsetTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq(List(List(offsetTime1, offsetTime2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq(List(List(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimeSeq(List(offsetTime1), List(offsetTime2, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq(List(offsetTime1, offsetTime2), List(offsetTime2, offsetTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq(List(offsetTime1, offsetTime2), List(offsetTime2, offsetTime3, offsetTime3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq(List(List(offsetTime1), List(offsetTime2, offsetTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq(List(List(offsetTime1, offsetTime2), List(offsetTime2, offsetTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq(List(List(offsetTime1, offsetTime2), List(offsetTime2, offsetTime3, offsetTime3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.offsetTimeSeq(List(offsetTime1, offsetTime2), List.empty[OffsetTime]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq(List.empty[OffsetTime], List(offsetTime1, offsetTime2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq(List.empty[OffsetTime], List.empty[OffsetTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq(List.empty[OffsetTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq(List.empty[List[OffsetTime]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq(List(List.empty[OffsetTime])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.offsetTimeSeq().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, List(offsetTime1, offsetTime2))
        val b = (2, List(offsetTime2, offsetTime3, offsetTime3))
        for {
          _ <- Ns.i.offsetTimeSeq.insert(List(a, b)).transact

          // Exact Seq non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimeSeq.not(List(offsetTime1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.not(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.offsetTimeSeq.not(List(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq.not(List(List(offsetTime1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.not(List(List(offsetTime1, offsetTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSeq.not(List(List(offsetTime1, offsetTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSeq.not(List(List(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimeSeq.not(List(offsetTime1), List(offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.not(List(offsetTime1, offsetTime2), List(offsetTime2, offsetTime3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSeq.not(List(offsetTime1, offsetTime2), List(offsetTime2, offsetTime3, offsetTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq.not(List(List(offsetTime1), List(offsetTime2, offsetTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.not(List(List(offsetTime1, offsetTime2), List(offsetTime2, offsetTime3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSeq.not(List(List(offsetTime1, offsetTime2), List(offsetTime2, offsetTime3, offsetTime3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.offsetTimeSeq.not(List(List(offsetTime1, offsetTime2), List.empty[OffsetTime])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSeq.not(List.empty[OffsetTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.not(List.empty[List[OffsetTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, List(offsetTime1, offsetTime2))
        val b = (2, List(offsetTime2, offsetTime3, offsetTime3))
        for {
          _ <- Ns.i.offsetTimeSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.offsetTimeSeq.has(offsetTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq.has(offsetTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq.has(offsetTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.has(offsetTime3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq.has(List(offsetTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq.has(List(offsetTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq.has(List(offsetTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.has(List(offsetTime3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetTimeSeq.has(offsetTime1, offsetTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.has(offsetTime1, offsetTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.has(offsetTime2, offsetTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.has(offsetTime1, offsetTime2, offsetTime3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq.has(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.has(List(offsetTime1, offsetTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.has(List(offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.has(List(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.offsetTimeSeq.has(List.empty[OffsetTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(offsetTime1, offsetTime2))
        val b = (2, List(offsetTime2, offsetTime3, offsetTime3))
        for {
          _ <- Ns.i.offsetTimeSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(offsetTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(offsetTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(offsetTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(offsetTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(offsetTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(offsetTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List(offsetTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List(offsetTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List(offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List(offsetTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List(offsetTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List(offsetTime5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(offsetTime1, offsetTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(offsetTime1, offsetTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(offsetTime1, offsetTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(offsetTime1, offsetTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List(offsetTime1, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List(offsetTime1, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List(offsetTime1, offsetTime5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List.empty[OffsetTime]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetTimeSeq.insert(List(
            (1, List(offsetTime1, offsetTime2)),
            (2, List(offsetTime2, offsetTime3, offsetTime3))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // offsetTimeSeq not asserted for i = 0
          _ <- Ns.i.a1.offsetTimeSeq_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.offsetTimeSeq_?.insert(List(
            (0, None),
            (1, Some(List(offsetTime1, offsetTime2))),
            (2, Some(List(offsetTime2, offsetTime3, offsetTime3))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.offsetTimeSeq_().query.get.map(_ ==> List(0))

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.offsetTimeSeq_(List(offsetTime1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.offsetTimeSeq_(List(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq_(List(List(offsetTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_(List(List(offsetTime1, offsetTime2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSeq_(List(List(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimeSeq_(List(offsetTime1), List(offsetTime2, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_(List(offsetTime1, offsetTime2), List(offsetTime2, offsetTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSeq_(List(offsetTime1, offsetTime2), List(offsetTime2, offsetTime3, offsetTime3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq_(List(List(offsetTime1), List(offsetTime2, offsetTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_(List(List(offsetTime1, offsetTime2), List(offsetTime2, offsetTime3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSeq_(List(List(offsetTime1, offsetTime2), List(offsetTime2, offsetTime3, offsetTime3))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.offsetTimeSeq_(List(offsetTime1, offsetTime2), List.empty[OffsetTime]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSeq_(List.empty[OffsetTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_(List.empty[List[OffsetTime]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_(List(List.empty[OffsetTime])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetTimeSeq.insert(List(
            (1, List(offsetTime1, offsetTime2)),
            (2, List(offsetTime2, offsetTime3, offsetTime3))
          )).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimeSeq_.not(List(offsetTime1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.not(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.offsetTimeSeq_.not(List(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq_.not(List(List(offsetTime1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.not(List(List(offsetTime1, offsetTime2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeSeq_.not(List(List(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimeSeq_.not(List(offsetTime1), List(offsetTime2, offsetTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.not(List(offsetTime1, offsetTime2), List(offsetTime2, offsetTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeSeq_.not(List(offsetTime1, offsetTime2), List(offsetTime2, offsetTime3, offsetTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq_.not(List(List(offsetTime1), List(offsetTime2, offsetTime3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.not(List(List(offsetTime1, offsetTime2), List(offsetTime2, offsetTime3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeSeq_.not(List(List(offsetTime1, offsetTime2), List(offsetTime2, offsetTime3, offsetTime3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.offsetTimeSeq_.not(List(List(offsetTime1, offsetTime2), List.empty[OffsetTime])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeSeq_.not(List.empty[OffsetTime]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.not(List.empty[List[OffsetTime]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetTimeSeq.insert(List(
            (1, List(offsetTime1, offsetTime2)),
            (2, List(offsetTime2, offsetTime3, offsetTime3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.offsetTimeSeq_.has(offsetTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_.has(offsetTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSeq_.has(offsetTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.has(offsetTime3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq_.has(List(offsetTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_.has(List(offsetTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSeq_.has(List(offsetTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.has(List(offsetTime3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetTimeSeq_.has(offsetTime1, offsetTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.has(offsetTime1, offsetTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.has(offsetTime2, offsetTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.has(offsetTime1, offsetTime2, offsetTime3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq_.has(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.has(List(offsetTime1, offsetTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.has(List(offsetTime2, offsetTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.has(List(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.offsetTimeSeq_.has(List.empty[OffsetTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetTimeSeq.insert(List(
            (1, List(offsetTime1, offsetTime2)),
            (2, List(offsetTime2, offsetTime3, offsetTime3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(offsetTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(offsetTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(offsetTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(offsetTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(offsetTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(offsetTime5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List(offsetTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List(offsetTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List(offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List(offsetTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List(offsetTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List(offsetTime5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(offsetTime1, offsetTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(offsetTime1, offsetTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(offsetTime1, offsetTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(offsetTime1, offsetTime5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List(offsetTime1, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List(offsetTime1, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List(offsetTime1, offsetTime5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List.empty[OffsetTime]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(List(offsetTime1, offsetTime2)))
        val b = (2, Some(List(offsetTime2, offsetTime3, offsetTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetTimeSeq_?.insert(a, b, c).transact

          _ <- Ns.i.a1.offsetTimeSeq_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no offsetTime value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.offsetTimeSeq_?.query.get.map(_ ==> List(
            (2, Some(List(offsetTime2, offsetTime3, offsetTime3))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(List(offsetTime1, offsetTime2)))
        val b = (2, Some(List(offsetTime2, offsetTime3, offsetTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetTimeSeq_?.insert(a, b, c).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.offsetTimeSeq_?(Some(List(offsetTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_?(Some(List(offsetTime1, offsetTime2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.offsetTimeSeq_?(Some(List(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq_?(Some(List(List(offsetTime1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_?(Some(List(List(offsetTime1, offsetTime2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq_?(Some(List(List(offsetTime1, offsetTime2, offsetTime3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimeSeq_?(Some(List(List(offsetTime1), List(offsetTime2, offsetTime3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_?(Some(List(List(offsetTime1, offsetTime2), List(offsetTime2, offsetTime3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq_?(Some(List(List(offsetTime1, offsetTime2), List(offsetTime2, offsetTime3, offsetTime3)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.offsetTimeSeq_?(Some(List.empty[OffsetTime])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_?(Some(List.empty[List[OffsetTime]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_?(Some(List(List.empty[OffsetTime]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.offsetTimeSeq_?(Option.empty[List[OffsetTime]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.offsetTimeSeq_?(Option.empty[List[List[OffsetTime]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(List(offsetTime1, offsetTime2)))
        val b = (2, Some(List(offsetTime2, offsetTime3, offsetTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetTimeSeq_?.insert(a, b, c).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimeSeq_?.not(Some(List(offsetTime1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq_?.not(Some(List(offsetTime1, offsetTime2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.offsetTimeSeq_?.not(Some(List(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq_?.not(Some(List(List(offsetTime1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq_?.not(Some(List(List(offsetTime1, offsetTime2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSeq_?.not(Some(List(List(offsetTime1, offsetTime2, offsetTime3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimeSeq_?.not(Some(List(List(offsetTime1), List(offsetTime2, offsetTime3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq_?.not(Some(List(List(offsetTime1, offsetTime2), List(offsetTime2, offsetTime3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSeq_?.not(Some(List(List(offsetTime1, offsetTime2), List(offsetTime2, offsetTime3, offsetTime3)))).query.get.map(_ ==> List())

          // Empty Seqs are ignored
          _ <- Ns.i.a1.offsetTimeSeq_?.not(Some(List(List(offsetTime1, offsetTime2), List.empty[OffsetTime]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSeq_?.not(Some(List.empty[OffsetTime])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq_?.not(Some(List.empty[List[OffsetTime]])).query.get.map(_ ==> List(a, b))


          // Negation of None matches all asserted
          _ <- Ns.i.a1.offsetTimeSeq_?.not(Option.empty[List[OffsetTime]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq_?.not(Option.empty[List[List[OffsetTime]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(List(offsetTime1, offsetTime2)))
        val b = (2, Some(List(offsetTime2, offsetTime3, offsetTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetTimeSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.offsetTimeSeq_?.has(Some(offsetTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_?.has(Some(offsetTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq_?.has(Some(offsetTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq_?.has(Some(offsetTime3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq_?.has(Some(List(offsetTime0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_?.has(Some(List(offsetTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq_?.has(Some(List(offsetTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq_?.has(Some(List(offsetTime3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetTimeSeq_?.has(Some(List(offsetTime1, offsetTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq_?.has(Some(List(offsetTime1, offsetTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq_?.has(Some(List(offsetTime2, offsetTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq_?.has(Some(List(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.offsetTimeSeq_?.has(Some(List.empty[OffsetTime])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.offsetTimeSeq_?.has(Option.empty[OffsetTime]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.offsetTimeSeq_?.has(Option.empty[List[OffsetTime]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(offsetTime1, offsetTime2)))
        val b = (2, Some(List(offsetTime2, offsetTime3, offsetTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetTimeSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.offsetTimeSeq_?.hasNo(Some(offsetTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq_?.hasNo(Some(offsetTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSeq_?.hasNo(Some(offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_?.hasNo(Some(offsetTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq_?.hasNo(Some(offsetTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq_?.hasNo(Some(offsetTime5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq_?.hasNo(Some(List(offsetTime0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq_?.hasNo(Some(List(offsetTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSeq_?.hasNo(Some(List(offsetTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_?.hasNo(Some(List(offsetTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq_?.hasNo(Some(List(offsetTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq_?.hasNo(Some(List(offsetTime5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.offsetTimeSeq_?.hasNo(Some(List(offsetTime1, offsetTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_?.hasNo(Some(List(offsetTime1, offsetTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_?.hasNo(Some(List(offsetTime1, offsetTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_?.hasNo(Some(List(offsetTime1, offsetTime5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.offsetTimeSeq_?.hasNo(Some(List.empty[OffsetTime])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.offsetTimeSeq_?.hasNo(Option.empty[OffsetTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq_?.hasNo(Option.empty[List[OffsetTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}