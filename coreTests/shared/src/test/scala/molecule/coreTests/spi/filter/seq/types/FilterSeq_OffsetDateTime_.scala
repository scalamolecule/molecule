// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.time.OffsetDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_OffsetDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, List(offsetDateTime1, offsetDateTime2))
        val b = (2, List(offsetDateTime2, offsetDateTime3, offsetDateTime3))
        for {
          _ <- Ns.i.offsetDateTimeSeq.insert(List(a, b)).transact

          _ <- Ns.i.a1.offsetDateTimeSeq.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, List(offsetDateTime1, offsetDateTime2))
        val b = (2, List(offsetDateTime2, offsetDateTime3, offsetDateTime3))
        for {
          _ <- Ns.i.offsetDateTimeSeq.insert(List(a, b)).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.offsetDateTimeSeq(List(offsetDateTime1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.offsetDateTimeSeq(List(offsetDateTime1, offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq(List(List(offsetDateTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq(List(List(offsetDateTime1, offsetDateTime2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq(List(List(offsetDateTime1, offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.offsetDateTimeSeq(List(offsetDateTime1), List(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq(List(offsetDateTime1, offsetDateTime2), List(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq(List(offsetDateTime1, offsetDateTime2), List(offsetDateTime2, offsetDateTime3, offsetDateTime3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq(List(List(offsetDateTime1), List(offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq(List(List(offsetDateTime1, offsetDateTime2), List(offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq(List(List(offsetDateTime1, offsetDateTime2), List(offsetDateTime2, offsetDateTime3, offsetDateTime3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.offsetDateTimeSeq(List(offsetDateTime1, offsetDateTime2), List.empty[OffsetDateTime]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq(List.empty[OffsetDateTime], List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq(List.empty[OffsetDateTime], List.empty[OffsetDateTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq(List.empty[OffsetDateTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq(List.empty[List[OffsetDateTime]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq(List(List.empty[OffsetDateTime])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.offsetDateTimeSeq().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, List(offsetDateTime1, offsetDateTime2))
        val b = (2, List(offsetDateTime2, offsetDateTime3, offsetDateTime3))
        for {
          _ <- Ns.i.offsetDateTimeSeq.insert(List(a, b)).transact

          // Exact Seq non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.offsetDateTimeSeq.not(List(offsetDateTime1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.not(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.offsetDateTimeSeq.not(List(offsetDateTime1, offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq.not(List(List(offsetDateTime1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.not(List(List(offsetDateTime1, offsetDateTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSeq.not(List(List(offsetDateTime1, offsetDateTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSeq.not(List(List(offsetDateTime1, offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.offsetDateTimeSeq.not(List(offsetDateTime1), List(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.not(List(offsetDateTime1, offsetDateTime2), List(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSeq.not(List(offsetDateTime1, offsetDateTime2), List(offsetDateTime2, offsetDateTime3, offsetDateTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq.not(List(List(offsetDateTime1), List(offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.not(List(List(offsetDateTime1, offsetDateTime2), List(offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSeq.not(List(List(offsetDateTime1, offsetDateTime2), List(offsetDateTime2, offsetDateTime3, offsetDateTime3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.offsetDateTimeSeq.not(List(List(offsetDateTime1, offsetDateTime2), List.empty[OffsetDateTime])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSeq.not(List.empty[OffsetDateTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.not(List.empty[List[OffsetDateTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, List(offsetDateTime1, offsetDateTime2))
        val b = (2, List(offsetDateTime2, offsetDateTime3, offsetDateTime3))
        for {
          _ <- Ns.i.offsetDateTimeSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.offsetDateTimeSeq.has(offsetDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq.has(offsetDateTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq.has(offsetDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.has(offsetDateTime3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq.has(List(offsetDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq.has(List(offsetDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq.has(List(offsetDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.has(List(offsetDateTime3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetDateTimeSeq.has(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.has(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.has(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.has(offsetDateTime1, offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq.has(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.has(List(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.has(List(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.has(List(offsetDateTime1, offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.offsetDateTimeSeq.has(List.empty[OffsetDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(offsetDateTime1, offsetDateTime2))
        val b = (2, List(offsetDateTime2, offsetDateTime3, offsetDateTime3))
        for {
          _ <- Ns.i.offsetDateTimeSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime1, offsetDateTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime1, offsetDateTime5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List.empty[OffsetDateTime]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetDateTimeSeq.insert(List(
            (1, List(offsetDateTime1, offsetDateTime2)),
            (2, List(offsetDateTime2, offsetDateTime3, offsetDateTime3))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // offsetDateTimeSeq not asserted for i = 0
          _ <- Ns.i.a1.offsetDateTimeSeq_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.offsetDateTimeSeq_?.insert(List(
            (0, None),
            (1, Some(List(offsetDateTime1, offsetDateTime2))),
            (2, Some(List(offsetDateTime2, offsetDateTime3, offsetDateTime3))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.offsetDateTimeSeq_().query.get.map(_ ==> List(0))

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.offsetDateTimeSeq_(List(offsetDateTime1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.offsetDateTimeSeq_(List(offsetDateTime1, offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq_(List(List(offsetDateTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_(List(List(offsetDateTime1, offsetDateTime2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSeq_(List(List(offsetDateTime1, offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.offsetDateTimeSeq_(List(offsetDateTime1), List(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_(List(offsetDateTime1, offsetDateTime2), List(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSeq_(List(offsetDateTime1, offsetDateTime2), List(offsetDateTime2, offsetDateTime3, offsetDateTime3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq_(List(List(offsetDateTime1), List(offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_(List(List(offsetDateTime1, offsetDateTime2), List(offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSeq_(List(List(offsetDateTime1, offsetDateTime2), List(offsetDateTime2, offsetDateTime3, offsetDateTime3))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.offsetDateTimeSeq_(List(offsetDateTime1, offsetDateTime2), List.empty[OffsetDateTime]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSeq_(List.empty[OffsetDateTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_(List.empty[List[OffsetDateTime]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_(List(List.empty[OffsetDateTime])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetDateTimeSeq.insert(List(
            (1, List(offsetDateTime1, offsetDateTime2)),
            (2, List(offsetDateTime2, offsetDateTime3, offsetDateTime3))
          )).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.offsetDateTimeSeq_.not(List(offsetDateTime1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.not(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.offsetDateTimeSeq_.not(List(offsetDateTime1, offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq_.not(List(List(offsetDateTime1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.not(List(List(offsetDateTime1, offsetDateTime2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.not(List(List(offsetDateTime1, offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.offsetDateTimeSeq_.not(List(offsetDateTime1), List(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.not(List(offsetDateTime1, offsetDateTime2), List(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.not(List(offsetDateTime1, offsetDateTime2), List(offsetDateTime2, offsetDateTime3, offsetDateTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq_.not(List(List(offsetDateTime1), List(offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.not(List(List(offsetDateTime1, offsetDateTime2), List(offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.not(List(List(offsetDateTime1, offsetDateTime2), List(offsetDateTime2, offsetDateTime3, offsetDateTime3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.offsetDateTimeSeq_.not(List(List(offsetDateTime1, offsetDateTime2), List.empty[OffsetDateTime])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.not(List.empty[OffsetDateTime]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.not(List.empty[List[OffsetDateTime]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetDateTimeSeq.insert(List(
            (1, List(offsetDateTime1, offsetDateTime2)),
            (2, List(offsetDateTime2, offsetDateTime3, offsetDateTime3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(offsetDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(offsetDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(offsetDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(offsetDateTime3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(offsetDateTime1, offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime1, offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(List.empty[OffsetDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetDateTimeSeq.insert(List(
            (1, List(offsetDateTime1, offsetDateTime2)),
            (2, List(offsetDateTime2, offsetDateTime3, offsetDateTime3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime1, offsetDateTime5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime1, offsetDateTime5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List.empty[OffsetDateTime]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(List(offsetDateTime1, offsetDateTime2)))
        val b = (2, Some(List(offsetDateTime2, offsetDateTime3, offsetDateTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetDateTimeSeq_?.insert(a, b, c).transact

          _ <- Ns.i.a1.offsetDateTimeSeq_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no offsetDateTime value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.offsetDateTimeSeq_?.query.get.map(_ ==> List(
            (2, Some(List(offsetDateTime2, offsetDateTime3, offsetDateTime3))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(List(offsetDateTime1, offsetDateTime2)))
        val b = (2, Some(List(offsetDateTime2, offsetDateTime3, offsetDateTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetDateTimeSeq_?.insert(a, b, c).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.offsetDateTimeSeq_?(Some(List(offsetDateTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_?(Some(List(offsetDateTime1, offsetDateTime2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.offsetDateTimeSeq_?(Some(List(offsetDateTime1, offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq_?(Some(List(List(offsetDateTime1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_?(Some(List(List(offsetDateTime1, offsetDateTime2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq_?(Some(List(List(offsetDateTime1, offsetDateTime2, offsetDateTime3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.offsetDateTimeSeq_?(Some(List(List(offsetDateTime1), List(offsetDateTime2, offsetDateTime3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_?(Some(List(List(offsetDateTime1, offsetDateTime2), List(offsetDateTime2, offsetDateTime3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq_?(Some(List(List(offsetDateTime1, offsetDateTime2), List(offsetDateTime2, offsetDateTime3, offsetDateTime3)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.offsetDateTimeSeq_?(Some(List.empty[OffsetDateTime])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_?(Some(List.empty[List[OffsetDateTime]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_?(Some(List(List.empty[OffsetDateTime]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.offsetDateTimeSeq_?(Option.empty[List[OffsetDateTime]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.offsetDateTimeSeq_?(Option.empty[List[List[OffsetDateTime]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(List(offsetDateTime1, offsetDateTime2)))
        val b = (2, Some(List(offsetDateTime2, offsetDateTime3, offsetDateTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetDateTimeSeq_?.insert(a, b, c).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.offsetDateTimeSeq_?.not(Some(List(offsetDateTime1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.not(Some(List(offsetDateTime1, offsetDateTime2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.offsetDateTimeSeq_?.not(Some(List(offsetDateTime1, offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq_?.not(Some(List(List(offsetDateTime1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.not(Some(List(List(offsetDateTime1, offsetDateTime2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.not(Some(List(List(offsetDateTime1, offsetDateTime2, offsetDateTime3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.offsetDateTimeSeq_?.not(Some(List(List(offsetDateTime1), List(offsetDateTime2, offsetDateTime3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.not(Some(List(List(offsetDateTime1, offsetDateTime2), List(offsetDateTime2, offsetDateTime3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.not(Some(List(List(offsetDateTime1, offsetDateTime2), List(offsetDateTime2, offsetDateTime3, offsetDateTime3)))).query.get.map(_ ==> List())

          // Empty Seqs are ignored
          _ <- Ns.i.a1.offsetDateTimeSeq_?.not(Some(List(List(offsetDateTime1, offsetDateTime2), List.empty[OffsetDateTime]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.not(Some(List.empty[OffsetDateTime])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.not(Some(List.empty[List[OffsetDateTime]])).query.get.map(_ ==> List(a, b))


          // Negation of None matches all asserted
          _ <- Ns.i.a1.offsetDateTimeSeq_?.not(Option.empty[List[OffsetDateTime]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.not(Option.empty[List[List[OffsetDateTime]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(List(offsetDateTime1, offsetDateTime2)))
        val b = (2, Some(List(offsetDateTime2, offsetDateTime3, offsetDateTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetDateTimeSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.offsetDateTimeSeq_?.has(Some(offsetDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_?.has(Some(offsetDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.has(Some(offsetDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.has(Some(offsetDateTime3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq_?.has(Some(List(offsetDateTime0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_?.has(Some(List(offsetDateTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.has(Some(List(offsetDateTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.has(Some(List(offsetDateTime3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetDateTimeSeq_?.has(Some(List(offsetDateTime1, offsetDateTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.has(Some(List(offsetDateTime1, offsetDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.has(Some(List(offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.has(Some(List(offsetDateTime1, offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.offsetDateTimeSeq_?.has(Some(List.empty[OffsetDateTime])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.offsetDateTimeSeq_?.has(Option.empty[OffsetDateTime]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.has(Option.empty[List[OffsetDateTime]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(offsetDateTime1, offsetDateTime2)))
        val b = (2, Some(List(offsetDateTime2, offsetDateTime3, offsetDateTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetDateTimeSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.offsetDateTimeSeq_?.hasNo(Some(offsetDateTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.hasNo(Some(offsetDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.hasNo(Some(offsetDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_?.hasNo(Some(offsetDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.hasNo(Some(offsetDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.hasNo(Some(offsetDateTime5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq_?.hasNo(Some(List(offsetDateTime0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.hasNo(Some(List(offsetDateTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.hasNo(Some(List(offsetDateTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_?.hasNo(Some(List(offsetDateTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.hasNo(Some(List(offsetDateTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.hasNo(Some(List(offsetDateTime5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.offsetDateTimeSeq_?.hasNo(Some(List(offsetDateTime1, offsetDateTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_?.hasNo(Some(List(offsetDateTime1, offsetDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_?.hasNo(Some(List(offsetDateTime1, offsetDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_?.hasNo(Some(List(offsetDateTime1, offsetDateTime5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.offsetDateTimeSeq_?.hasNo(Some(List.empty[OffsetDateTime])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.offsetDateTimeSeq_?.hasNo(Option.empty[OffsetDateTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq_?.hasNo(Option.empty[List[OffsetDateTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}