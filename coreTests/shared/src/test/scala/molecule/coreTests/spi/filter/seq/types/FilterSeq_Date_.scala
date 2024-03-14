// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.util.Date
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_Date_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, List(date1, date2))
        val b = (2, List(date2, date3, date3))
        for {
          _ <- Ns.i.dateSeq.insert(List(a, b)).transact

          _ <- Ns.i.a1.dateSeq.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, List(date1, date2))
        val b = (2, List(date2, date3, date3))
        for {
          _ <- Ns.i.dateSeq.insert(List(a, b)).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.dateSeq(List(date1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq(List(date1, date2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.dateSeq(List(date1, date2, date3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dateSeq(List(List(date1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq(List(List(date1, date2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSeq(List(List(date1, date2, date3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.dateSeq(List(date1), List(date2, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq(List(date1, date2), List(date2, date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSeq(List(date1, date2), List(date2, date3, date3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dateSeq(List(List(date1), List(date2, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq(List(List(date1, date2), List(date2, date3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSeq(List(List(date1, date2), List(date2, date3, date3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.dateSeq(List(date1, date2), List.empty[Date]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSeq(List.empty[Date], List(date1, date2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSeq(List.empty[Date], List.empty[Date]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq(List.empty[Date]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq(List.empty[List[Date]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq(List(List.empty[Date])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, List(date1, date2))
        val b = (2, List(date2, date3, date3))
        for {
          _ <- Ns.i.dateSeq.insert(List(a, b)).transact

          // Exact Seq non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.dateSeq.not(List(date1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq.not(List(date1, date2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.dateSeq.not(List(date1, date2, date3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dateSeq.not(List(List(date1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq.not(List(List(date1, date2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSeq.not(List(List(date1, date2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSeq.not(List(List(date1, date2, date3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.dateSeq.not(List(date1), List(date2, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq.not(List(date1, date2), List(date2, date3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSeq.not(List(date1, date2), List(date2, date3, date3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dateSeq.not(List(List(date1), List(date2, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq.not(List(List(date1, date2), List(date2, date3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSeq.not(List(List(date1, date2), List(date2, date3, date3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.dateSeq.not(List(List(date1, date2), List.empty[Date])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSeq.not(List.empty[Date]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq.not(List.empty[List[Date]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, List(date1, date2))
        val b = (2, List(date2, date3, date3))
        for {
          _ <- Ns.i.dateSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.dateSeq.has(date0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq.has(date1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSeq.has(date2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq.has(date3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dateSeq.has(List(date0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq.has(List(date1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSeq.has(List(date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq.has(List(date3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.dateSeq.has(date1, date2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq.has(date1, date3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq.has(date2, date3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq.has(date1, date2, date3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dateSeq.has(List(date1, date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq.has(List(date1, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq.has(List(date2, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq.has(List(date1, date2, date3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.dateSeq.has(List.empty[Date]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(date1, date2))
        val b = (2, List(date2, date3, date3))
        for {
          _ <- Ns.i.dateSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.dateSeq.hasNo(date0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq.hasNo(date1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSeq.hasNo(date2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq.hasNo(date3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSeq.hasNo(date3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSeq.hasNo(date5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dateSeq.hasNo(List(date0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq.hasNo(List(date1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSeq.hasNo(List(date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq.hasNo(List(date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSeq.hasNo(List(date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSeq.hasNo(List(date5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.dateSeq.hasNo(date1, date2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq.hasNo(date1, date3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq.hasNo(date1, date3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq.hasNo(date1, date5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dateSeq.hasNo(List(date1, date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq.hasNo(List(date1, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq.hasNo(List(date1, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq.hasNo(List(date1, date5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.dateSeq.hasNo(List.empty[Date]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.dateSeq.insert(List(
            (1, List(date1, date2)),
            (2, List(date2, date3, date3))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // dateSeq not asserted for i = 0
          _ <- Ns.i.a1.dateSeq_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.dateSeq_?.insert(List(
            (0, None),
            (1, Some(List(date1, date2))),
            (2, Some(List(date2, date3, date3))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.dateSeq_().query.get.map(_ ==> List(0))

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.dateSeq_(List(date1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_(List(date1, date2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.dateSeq_(List(date1, date2, date3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dateSeq_(List(List(date1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_(List(List(date1, date2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSeq_(List(List(date1, date2, date3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.dateSeq_(List(date1), List(date2, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_(List(date1, date2), List(date2, date3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSeq_(List(date1, date2), List(date2, date3, date3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.dateSeq_(List(List(date1), List(date2, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_(List(List(date1, date2), List(date2, date3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSeq_(List(List(date1, date2), List(date2, date3, date3))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.dateSeq_(List(date1, date2), List.empty[Date]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSeq_(List.empty[Date]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_(List.empty[List[Date]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_(List(List.empty[Date])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.dateSeq.insert(List(
            (1, List(date1, date2)),
            (2, List(date2, date3, date3))
          )).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.dateSeq_.not(List(date1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSeq_.not(List(date1, date2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.dateSeq_.not(List(date1, date2, date3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.dateSeq_.not(List(List(date1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSeq_.not(List(List(date1, date2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateSeq_.not(List(List(date1, date2, date3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.dateSeq_.not(List(date1), List(date2, date3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSeq_.not(List(date1, date2), List(date2, date3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateSeq_.not(List(date1, date2), List(date2, date3, date3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dateSeq_.not(List(List(date1), List(date2, date3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSeq_.not(List(List(date1, date2), List(date2, date3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateSeq_.not(List(List(date1, date2), List(date2, date3, date3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.dateSeq_.not(List(List(date1, date2), List.empty[Date])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateSeq_.not(List.empty[Date]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSeq_.not(List.empty[List[Date]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.dateSeq.insert(List(
            (1, List(date1, date2)),
            (2, List(date2, date3, date3))
          )).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.dateSeq_.has(date0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_.has(date1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSeq_.has(date2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSeq_.has(date3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.dateSeq_.has(List(date0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_.has(List(date1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSeq_.has(List(date2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSeq_.has(List(date3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.dateSeq_.has(date1, date2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSeq_.has(date1, date3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSeq_.has(date2, date3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSeq_.has(date1, date2, date3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.dateSeq_.has(List(date1, date2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSeq_.has(List(date1, date3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSeq_.has(List(date2, date3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSeq_.has(List(date1, date2, date3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.dateSeq_.has(List.empty[Date]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.dateSeq.insert(List(
            (1, List(date1, date2)),
            (2, List(date2, date3, date3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.dateSeq_.hasNo(date0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSeq_.hasNo(date1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateSeq_.hasNo(date2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_.hasNo(date3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSeq_.hasNo(date3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSeq_.hasNo(date5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.dateSeq_.hasNo(List(date0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSeq_.hasNo(List(date1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateSeq_.hasNo(List(date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_.hasNo(List(date3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSeq_.hasNo(List(date3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSeq_.hasNo(List(date5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.dateSeq_.hasNo(date1, date2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_.hasNo(date1, date3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_.hasNo(date1, date3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_.hasNo(date1, date5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.dateSeq_.hasNo(List(date1, date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_.hasNo(List(date1, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_.hasNo(List(date1, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_.hasNo(List(date1, date5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.dateSeq_.hasNo(List.empty[Date]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(List(date1, date2)))
        val b = (2, Some(List(date2, date3, date3)))
        val c = (3, None)
        for {
          _ <- Ns.i.dateSeq_?.insert(a, b, c).transact

          _ <- Ns.i.a1.dateSeq_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no date value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.dateSeq_?.query.get.map(_ ==> List(
            (2, Some(List(date2, date3, date3))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(List(date1, date2)))
        val b = (2, Some(List(date2, date3, date3)))
        val c = (3, None)
        for {
          _ <- Ns.i.dateSeq_?.insert(a, b, c).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.dateSeq_?(Some(List(date1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_?(Some(List(date1, date2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.dateSeq_?(Some(List(date1, date2, date3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dateSeq_?(Some(List(List(date1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_?(Some(List(List(date1, date2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSeq_?(Some(List(List(date1, date2, date3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.dateSeq_?(Some(List(List(date1), List(date2, date3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_?(Some(List(List(date1, date2), List(date2, date3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSeq_?(Some(List(List(date1, date2), List(date2, date3, date3)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.dateSeq_?(Some(List.empty[Date])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_?(Some(List.empty[List[Date]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_?(Some(List(List.empty[Date]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.dateSeq_?(Option.empty[List[Date]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.dateSeq_?(Option.empty[List[List[Date]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(List(date1, date2)))
        val b = (2, Some(List(date2, date3, date3)))
        val c = (3, None)
        for {
          _ <- Ns.i.dateSeq_?.insert(a, b, c).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.dateSeq_?.not(Some(List(date1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq_?.not(Some(List(date1, date2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.dateSeq_?.not(Some(List(date1, date2, date3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dateSeq_?.not(Some(List(List(date1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq_?.not(Some(List(List(date1, date2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSeq_?.not(Some(List(List(date1, date2, date3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.dateSeq_?.not(Some(List(List(date1), List(date2, date3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq_?.not(Some(List(List(date1, date2), List(date2, date3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSeq_?.not(Some(List(List(date1, date2), List(date2, date3, date3)))).query.get.map(_ ==> List())

          // Empty Seqs are ignored
          _ <- Ns.i.a1.dateSeq_?.not(Some(List(List(date1, date2), List.empty[Date]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSeq_?.not(Some(List.empty[Date])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq_?.not(Some(List.empty[List[Date]])).query.get.map(_ ==> List(a, b))


          // Negation of None matches all asserted
          _ <- Ns.i.a1.dateSeq_?.not(Option.empty[List[Date]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq_?.not(Option.empty[List[List[Date]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(List(date1, date2)))
        val b = (2, Some(List(date2, date3, date3)))
        val c = (3, None)
        for {
          _ <- Ns.i.dateSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.dateSeq_?.has(Some(date0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_?.has(Some(date1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSeq_?.has(Some(date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq_?.has(Some(date3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dateSeq_?.has(Some(List(date0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_?.has(Some(List(date1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSeq_?.has(Some(List(date2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq_?.has(Some(List(date3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.dateSeq_?.has(Some(List(date1, date2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq_?.has(Some(List(date1, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq_?.has(Some(List(date2, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq_?.has(Some(List(date1, date2, date3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.dateSeq_?.has(Some(List.empty[Date])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.dateSeq_?.has(Option.empty[Date]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.dateSeq_?.has(Option.empty[List[Date]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(date1, date2)))
        val b = (2, Some(List(date2, date3, date3)))
        val c = (3, None)
        for {
          _ <- Ns.i.dateSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.dateSeq_?.hasNo(Some(date0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq_?.hasNo(Some(date1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSeq_?.hasNo(Some(date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_?.hasNo(Some(date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSeq_?.hasNo(Some(date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSeq_?.hasNo(Some(date5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dateSeq_?.hasNo(Some(List(date0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq_?.hasNo(Some(List(date1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSeq_?.hasNo(Some(List(date2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_?.hasNo(Some(List(date3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSeq_?.hasNo(Some(List(date3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSeq_?.hasNo(Some(List(date5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.dateSeq_?.hasNo(Some(List(date1, date2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_?.hasNo(Some(List(date1, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_?.hasNo(Some(List(date1, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSeq_?.hasNo(Some(List(date1, date5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.dateSeq_?.hasNo(Some(List.empty[Date])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.dateSeq_?.hasNo(Option.empty[Date]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSeq_?.hasNo(Option.empty[List[Date]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}