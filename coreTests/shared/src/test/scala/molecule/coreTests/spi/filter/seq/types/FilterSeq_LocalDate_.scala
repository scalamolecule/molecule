// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.time.LocalDate
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_LocalDate_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, List(localDate1, localDate2))
        val b = (2, List(localDate2, localDate3, localDate3))
        for {
          _ <- Ns.i.localDateSeq.insert(List(a, b)).transact

          _ <- Ns.i.a1.localDateSeq.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, List(localDate1, localDate2))
        val b = (2, List(localDate2, localDate3, localDate3))
        for {
          _ <- Ns.i.localDateSeq.insert(List(a, b)).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.localDateSeq(List(localDate1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq(List(localDate1, localDate2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.localDateSeq(List(localDate1, localDate2, localDate3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localDateSeq(List(List(localDate1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq(List(List(localDate1, localDate2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq(List(List(localDate1, localDate2, localDate3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.localDateSeq(List(localDate1), List(localDate2, localDate3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq(List(localDate1, localDate2), List(localDate2, localDate3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq(List(localDate1, localDate2), List(localDate2, localDate3, localDate3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateSeq(List(List(localDate1), List(localDate2, localDate3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq(List(List(localDate1, localDate2), List(localDate2, localDate3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq(List(List(localDate1, localDate2), List(localDate2, localDate3, localDate3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.localDateSeq(List(localDate1, localDate2), List.empty[LocalDate]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq(List.empty[LocalDate], List(localDate1, localDate2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq(List.empty[LocalDate], List.empty[LocalDate]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq(List.empty[LocalDate]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq(List.empty[List[LocalDate]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq(List(List.empty[LocalDate])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.localDateSeq().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, List(localDate1, localDate2))
        val b = (2, List(localDate2, localDate3, localDate3))
        for {
          _ <- Ns.i.localDateSeq.insert(List(a, b)).transact

          // Exact Seq non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.localDateSeq.not(List(localDate1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.not(List(localDate1, localDate2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.localDateSeq.not(List(localDate1, localDate2, localDate3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateSeq.not(List(List(localDate1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.not(List(List(localDate1, localDate2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateSeq.not(List(List(localDate1, localDate2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateSeq.not(List(List(localDate1, localDate2, localDate3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.localDateSeq.not(List(localDate1), List(localDate2, localDate3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.not(List(localDate1, localDate2), List(localDate2, localDate3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateSeq.not(List(localDate1, localDate2), List(localDate2, localDate3, localDate3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localDateSeq.not(List(List(localDate1), List(localDate2, localDate3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.not(List(List(localDate1, localDate2), List(localDate2, localDate3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateSeq.not(List(List(localDate1, localDate2), List(localDate2, localDate3, localDate3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.localDateSeq.not(List(List(localDate1, localDate2), List.empty[LocalDate])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateSeq.not(List.empty[LocalDate]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.not(List.empty[List[LocalDate]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, List(localDate1, localDate2))
        val b = (2, List(localDate2, localDate3, localDate3))
        for {
          _ <- Ns.i.localDateSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.localDateSeq.has(localDate0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq.has(localDate1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq.has(localDate2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.has(localDate3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localDateSeq.has(List(localDate0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq.has(List(localDate1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq.has(List(localDate2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.has(List(localDate3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localDateSeq.has(localDate1, localDate2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.has(localDate1, localDate3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.has(localDate2, localDate3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.has(localDate1, localDate2, localDate3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateSeq.has(List(localDate1, localDate2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.has(List(localDate1, localDate3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.has(List(localDate2, localDate3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.has(List(localDate1, localDate2, localDate3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.localDateSeq.has(List.empty[LocalDate]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(localDate1, localDate2))
        val b = (2, List(localDate2, localDate3, localDate3))
        for {
          _ <- Ns.i.localDateSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.localDateSeq.hasNo(localDate0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.hasNo(localDate1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateSeq.hasNo(localDate2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq.hasNo(localDate3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq.hasNo(localDate3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq.hasNo(localDate5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateSeq.hasNo(List(localDate0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.hasNo(List(localDate1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateSeq.hasNo(List(localDate2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq.hasNo(List(localDate3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq.hasNo(List(localDate3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq.hasNo(List(localDate5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.localDateSeq.hasNo(localDate1, localDate2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq.hasNo(localDate1, localDate3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq.hasNo(localDate1, localDate3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq.hasNo(localDate1, localDate5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localDateSeq.hasNo(List(localDate1, localDate2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq.hasNo(List(localDate1, localDate3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq.hasNo(List(localDate1, localDate3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq.hasNo(List(localDate1, localDate5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.localDateSeq.hasNo(List.empty[LocalDate]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localDateSeq.insert(List(
            (1, List(localDate1, localDate2)),
            (2, List(localDate2, localDate3, localDate3))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // localDateSeq not asserted for i = 0
          _ <- Ns.i.a1.localDateSeq_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.localDateSeq_?.insert(List(
            (0, None),
            (1, Some(List(localDate1, localDate2))),
            (2, Some(List(localDate2, localDate3, localDate3))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.localDateSeq_().query.get.map(_ ==> List(0))

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.localDateSeq_(List(localDate1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_(List(localDate1, localDate2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.localDateSeq_(List(localDate1, localDate2, localDate3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localDateSeq_(List(List(localDate1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_(List(List(localDate1, localDate2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSeq_(List(List(localDate1, localDate2, localDate3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.localDateSeq_(List(localDate1), List(localDate2, localDate3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_(List(localDate1, localDate2), List(localDate2, localDate3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSeq_(List(localDate1, localDate2), List(localDate2, localDate3, localDate3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localDateSeq_(List(List(localDate1), List(localDate2, localDate3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_(List(List(localDate1, localDate2), List(localDate2, localDate3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSeq_(List(List(localDate1, localDate2), List(localDate2, localDate3, localDate3))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.localDateSeq_(List(localDate1, localDate2), List.empty[LocalDate]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSeq_(List.empty[LocalDate]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_(List.empty[List[LocalDate]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_(List(List.empty[LocalDate])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localDateSeq.insert(List(
            (1, List(localDate1, localDate2)),
            (2, List(localDate2, localDate3, localDate3))
          )).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.localDateSeq_.not(List(localDate1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.not(List(localDate1, localDate2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.localDateSeq_.not(List(localDate1, localDate2, localDate3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localDateSeq_.not(List(List(localDate1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.not(List(List(localDate1, localDate2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateSeq_.not(List(List(localDate1, localDate2, localDate3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.localDateSeq_.not(List(localDate1), List(localDate2, localDate3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.not(List(localDate1, localDate2), List(localDate2, localDate3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateSeq_.not(List(localDate1, localDate2), List(localDate2, localDate3, localDate3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localDateSeq_.not(List(List(localDate1), List(localDate2, localDate3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.not(List(List(localDate1, localDate2), List(localDate2, localDate3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateSeq_.not(List(List(localDate1, localDate2), List(localDate2, localDate3, localDate3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.localDateSeq_.not(List(List(localDate1, localDate2), List.empty[LocalDate])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateSeq_.not(List.empty[LocalDate]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.not(List.empty[List[LocalDate]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localDateSeq.insert(List(
            (1, List(localDate1, localDate2)),
            (2, List(localDate2, localDate3, localDate3))
          )).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.localDateSeq_.has(localDate0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_.has(localDate1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSeq_.has(localDate2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.has(localDate3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localDateSeq_.has(List(localDate0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_.has(List(localDate1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSeq_.has(List(localDate2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.has(List(localDate3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localDateSeq_.has(localDate1, localDate2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.has(localDate1, localDate3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.has(localDate2, localDate3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.has(localDate1, localDate2, localDate3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localDateSeq_.has(List(localDate1, localDate2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.has(List(localDate1, localDate3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.has(List(localDate2, localDate3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.has(List(localDate1, localDate2, localDate3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.localDateSeq_.has(List.empty[LocalDate]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localDateSeq.insert(List(
            (1, List(localDate1, localDate2)),
            (2, List(localDate2, localDate3, localDate3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.localDateSeq_.hasNo(localDate0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.hasNo(localDate1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateSeq_.hasNo(localDate2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_.hasNo(localDate3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSeq_.hasNo(localDate3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSeq_.hasNo(localDate5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localDateSeq_.hasNo(List(localDate0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.hasNo(List(localDate1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateSeq_.hasNo(List(localDate2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_.hasNo(List(localDate3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSeq_.hasNo(List(localDate3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSeq_.hasNo(List(localDate5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.localDateSeq_.hasNo(localDate1, localDate2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_.hasNo(localDate1, localDate3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_.hasNo(localDate1, localDate3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_.hasNo(localDate1, localDate5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localDateSeq_.hasNo(List(localDate1, localDate2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_.hasNo(List(localDate1, localDate3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_.hasNo(List(localDate1, localDate3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_.hasNo(List(localDate1, localDate5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.localDateSeq_.hasNo(List.empty[LocalDate]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(List(localDate1, localDate2)))
        val b = (2, Some(List(localDate2, localDate3, localDate3)))
        val c = (3, None)
        for {
          _ <- Ns.i.localDateSeq_?.insert(a, b, c).transact

          _ <- Ns.i.a1.localDateSeq_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no localDate value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.localDateSeq_?.query.get.map(_ ==> List(
            (2, Some(List(localDate2, localDate3, localDate3))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(List(localDate1, localDate2)))
        val b = (2, Some(List(localDate2, localDate3, localDate3)))
        val c = (3, None)
        for {
          _ <- Ns.i.localDateSeq_?.insert(a, b, c).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.localDateSeq_?(Some(List(localDate1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_?(Some(List(localDate1, localDate2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.localDateSeq_?(Some(List(localDate1, localDate2, localDate3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localDateSeq_?(Some(List(List(localDate1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_?(Some(List(List(localDate1, localDate2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq_?(Some(List(List(localDate1, localDate2, localDate3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.localDateSeq_?(Some(List(List(localDate1), List(localDate2, localDate3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_?(Some(List(List(localDate1, localDate2), List(localDate2, localDate3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq_?(Some(List(List(localDate1, localDate2), List(localDate2, localDate3, localDate3)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.localDateSeq_?(Some(List.empty[LocalDate])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_?(Some(List.empty[List[LocalDate]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_?(Some(List(List.empty[LocalDate]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.localDateSeq_?(Option.empty[List[LocalDate]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.localDateSeq_?(Option.empty[List[List[LocalDate]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(List(localDate1, localDate2)))
        val b = (2, Some(List(localDate2, localDate3, localDate3)))
        val c = (3, None)
        for {
          _ <- Ns.i.localDateSeq_?.insert(a, b, c).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.localDateSeq_?.not(Some(List(localDate1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq_?.not(Some(List(localDate1, localDate2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.localDateSeq_?.not(Some(List(localDate1, localDate2, localDate3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateSeq_?.not(Some(List(List(localDate1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq_?.not(Some(List(List(localDate1, localDate2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateSeq_?.not(Some(List(List(localDate1, localDate2, localDate3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.localDateSeq_?.not(Some(List(List(localDate1), List(localDate2, localDate3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq_?.not(Some(List(List(localDate1, localDate2), List(localDate2, localDate3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateSeq_?.not(Some(List(List(localDate1, localDate2), List(localDate2, localDate3, localDate3)))).query.get.map(_ ==> List())

          // Empty Seqs are ignored
          _ <- Ns.i.a1.localDateSeq_?.not(Some(List(List(localDate1, localDate2), List.empty[LocalDate]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateSeq_?.not(Some(List.empty[LocalDate])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq_?.not(Some(List.empty[List[LocalDate]])).query.get.map(_ ==> List(a, b))


          // Negation of None matches all asserted
          _ <- Ns.i.a1.localDateSeq_?.not(Option.empty[List[LocalDate]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq_?.not(Option.empty[List[List[LocalDate]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(List(localDate1, localDate2)))
        val b = (2, Some(List(localDate2, localDate3, localDate3)))
        val c = (3, None)
        for {
          _ <- Ns.i.localDateSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.localDateSeq_?.has(Some(localDate0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_?.has(Some(localDate1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq_?.has(Some(localDate2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq_?.has(Some(localDate3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localDateSeq_?.has(Some(List(localDate0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_?.has(Some(List(localDate1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq_?.has(Some(List(localDate2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq_?.has(Some(List(localDate3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localDateSeq_?.has(Some(List(localDate1, localDate2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq_?.has(Some(List(localDate1, localDate3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq_?.has(Some(List(localDate2, localDate3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq_?.has(Some(List(localDate1, localDate2, localDate3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.localDateSeq_?.has(Some(List.empty[LocalDate])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.localDateSeq_?.has(Option.empty[LocalDate]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.localDateSeq_?.has(Option.empty[List[LocalDate]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(localDate1, localDate2)))
        val b = (2, Some(List(localDate2, localDate3, localDate3)))
        val c = (3, None)
        for {
          _ <- Ns.i.localDateSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.localDateSeq_?.hasNo(Some(localDate0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq_?.hasNo(Some(localDate1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateSeq_?.hasNo(Some(localDate2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_?.hasNo(Some(localDate3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq_?.hasNo(Some(localDate3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq_?.hasNo(Some(localDate5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateSeq_?.hasNo(Some(List(localDate0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq_?.hasNo(Some(List(localDate1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateSeq_?.hasNo(Some(List(localDate2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_?.hasNo(Some(List(localDate3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq_?.hasNo(Some(List(localDate3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq_?.hasNo(Some(List(localDate5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.localDateSeq_?.hasNo(Some(List(localDate1, localDate2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_?.hasNo(Some(List(localDate1, localDate3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_?.hasNo(Some(List(localDate1, localDate3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_?.hasNo(Some(List(localDate1, localDate5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.localDateSeq_?.hasNo(Some(List.empty[LocalDate])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.localDateSeq_?.hasNo(Option.empty[LocalDate]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq_?.hasNo(Option.empty[List[LocalDate]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}