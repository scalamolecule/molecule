// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.net.URI
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_URI_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, List(uri1, uri2))
        val b = (2, List(uri2, uri3, uri3))
        for {
          _ <- Ns.i.uriSeq.insert(List(a, b)).transact

          _ <- Ns.i.a1.uriSeq.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, List(uri1, uri2))
        val b = (2, List(uri2, uri3, uri3))
        for {
          _ <- Ns.i.uriSeq.insert(List(a, b)).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uriSeq(List(uri1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq(List(uri1, uri2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.uriSeq(List(uri1, uri2, uri3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uriSeq(List(List(uri1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq(List(List(uri1, uri2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq(List(List(uri1, uri2, uri3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uriSeq(List(uri1), List(uri2, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq(List(uri1, uri2), List(uri2, uri3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq(List(uri1, uri2), List(uri2, uri3, uri3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uriSeq(List(List(uri1), List(uri2, uri3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq(List(List(uri1, uri2), List(uri2, uri3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq(List(List(uri1, uri2), List(uri2, uri3, uri3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.uriSeq(List(uri1, uri2), List.empty[URI]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq(List.empty[URI], List(uri1, uri2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq(List.empty[URI], List.empty[URI]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq(List.empty[URI]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq(List.empty[List[URI]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq(List(List.empty[URI])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.uriSeq().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, List(uri1, uri2))
        val b = (2, List(uri2, uri3, uri3))
        for {
          _ <- Ns.i.uriSeq.insert(List(a, b)).transact

          // Exact Seq non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uriSeq.not(List(uri1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.not(List(uri1, uri2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.uriSeq.not(List(uri1, uri2, uri3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uriSeq.not(List(List(uri1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.not(List(List(uri1, uri2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uriSeq.not(List(List(uri1, uri2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uriSeq.not(List(List(uri1, uri2, uri3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.uriSeq.not(List(uri1), List(uri2, uri3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.not(List(uri1, uri2), List(uri2, uri3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uriSeq.not(List(uri1, uri2), List(uri2, uri3, uri3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uriSeq.not(List(List(uri1), List(uri2, uri3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.not(List(List(uri1, uri2), List(uri2, uri3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uriSeq.not(List(List(uri1, uri2), List(uri2, uri3, uri3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.uriSeq.not(List(List(uri1, uri2), List.empty[URI])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uriSeq.not(List.empty[URI]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.not(List.empty[List[URI]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, List(uri1, uri2))
        val b = (2, List(uri2, uri3, uri3))
        for {
          _ <- Ns.i.uriSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.uriSeq.has(uri0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq.has(uri1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq.has(uri2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.has(uri3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uriSeq.has(List(uri0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq.has(List(uri1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq.has(List(uri2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.has(List(uri3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uriSeq.has(uri1, uri2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.has(uri1, uri3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.has(uri2, uri3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.has(uri1, uri2, uri3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uriSeq.has(List(uri1, uri2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.has(List(uri1, uri3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.has(List(uri2, uri3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.has(List(uri1, uri2, uri3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.uriSeq.has(List.empty[URI]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(uri1, uri2))
        val b = (2, List(uri2, uri3, uri3))
        for {
          _ <- Ns.i.uriSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.uriSeq.hasNo(uri0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.hasNo(uri1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uriSeq.hasNo(uri2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq.hasNo(uri3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq.hasNo(uri3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq.hasNo(uri5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uriSeq.hasNo(List(uri0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.hasNo(List(uri1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uriSeq.hasNo(List(uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq.hasNo(List(uri3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq.hasNo(List(uri3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq.hasNo(List(uri5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.uriSeq.hasNo(uri1, uri2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq.hasNo(uri1, uri3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq.hasNo(uri1, uri3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq.hasNo(uri1, uri5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uriSeq.hasNo(List(uri1, uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq.hasNo(List(uri1, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq.hasNo(List(uri1, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq.hasNo(List(uri1, uri5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.uriSeq.hasNo(List.empty[URI]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uriSeq.insert(List(
            (1, List(uri1, uri2)),
            (2, List(uri2, uri3, uri3))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // uriSeq not asserted for i = 0
          _ <- Ns.i.a1.uriSeq_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.uriSeq_?.insert(List(
            (0, None),
            (1, Some(List(uri1, uri2))),
            (2, Some(List(uri2, uri3, uri3))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.uriSeq_().query.get.map(_ ==> List(0))

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uriSeq_(List(uri1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_(List(uri1, uri2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.uriSeq_(List(uri1, uri2, uri3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uriSeq_(List(List(uri1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_(List(List(uri1, uri2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriSeq_(List(List(uri1, uri2, uri3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uriSeq_(List(uri1), List(uri2, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_(List(uri1, uri2), List(uri2, uri3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriSeq_(List(uri1, uri2), List(uri2, uri3, uri3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uriSeq_(List(List(uri1), List(uri2, uri3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_(List(List(uri1, uri2), List(uri2, uri3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriSeq_(List(List(uri1, uri2), List(uri2, uri3, uri3))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.uriSeq_(List(uri1, uri2), List.empty[URI]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriSeq_(List.empty[URI]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_(List.empty[List[URI]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_(List(List.empty[URI])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uriSeq.insert(List(
            (1, List(uri1, uri2)),
            (2, List(uri2, uri3, uri3))
          )).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uriSeq_.not(List(uri1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.not(List(uri1, uri2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.uriSeq_.not(List(uri1, uri2, uri3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uriSeq_.not(List(List(uri1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.not(List(List(uri1, uri2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriSeq_.not(List(List(uri1, uri2, uri3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.uriSeq_.not(List(uri1), List(uri2, uri3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.not(List(uri1, uri2), List(uri2, uri3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriSeq_.not(List(uri1, uri2), List(uri2, uri3, uri3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uriSeq_.not(List(List(uri1), List(uri2, uri3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.not(List(List(uri1, uri2), List(uri2, uri3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriSeq_.not(List(List(uri1, uri2), List(uri2, uri3, uri3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.uriSeq_.not(List(List(uri1, uri2), List.empty[URI])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriSeq_.not(List.empty[URI]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.not(List.empty[List[URI]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uriSeq.insert(List(
            (1, List(uri1, uri2)),
            (2, List(uri2, uri3, uri3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.uriSeq_.has(uri0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_.has(uri1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriSeq_.has(uri2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.has(uri3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.uriSeq_.has(List(uri0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_.has(List(uri1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriSeq_.has(List(uri2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.has(List(uri3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uriSeq_.has(uri1, uri2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.has(uri1, uri3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.has(uri2, uri3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.has(uri1, uri2, uri3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uriSeq_.has(List(uri1, uri2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.has(List(uri1, uri3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.has(List(uri2, uri3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.has(List(uri1, uri2, uri3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.uriSeq_.has(List.empty[URI]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uriSeq.insert(List(
            (1, List(uri1, uri2)),
            (2, List(uri2, uri3, uri3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.uriSeq_.hasNo(uri0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.hasNo(uri1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriSeq_.hasNo(uri2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_.hasNo(uri3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriSeq_.hasNo(uri3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriSeq_.hasNo(uri5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uriSeq_.hasNo(List(uri0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.hasNo(List(uri1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriSeq_.hasNo(List(uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_.hasNo(List(uri3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriSeq_.hasNo(List(uri3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriSeq_.hasNo(List(uri5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.uriSeq_.hasNo(uri1, uri2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_.hasNo(uri1, uri3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_.hasNo(uri1, uri3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_.hasNo(uri1, uri5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.uriSeq_.hasNo(List(uri1, uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_.hasNo(List(uri1, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_.hasNo(List(uri1, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_.hasNo(List(uri1, uri5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.uriSeq_.hasNo(List.empty[URI]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(List(uri1, uri2)))
        val b = (2, Some(List(uri2, uri3, uri3)))
        val c = (3, None)
        for {
          _ <- Ns.i.uriSeq_?.insert(a, b, c).transact

          _ <- Ns.i.a1.uriSeq_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no uri value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.uriSeq_?.query.get.map(_ ==> List(
            (2, Some(List(uri2, uri3, uri3))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(List(uri1, uri2)))
        val b = (2, Some(List(uri2, uri3, uri3)))
        val c = (3, None)
        for {
          _ <- Ns.i.uriSeq_?.insert(a, b, c).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uriSeq_?(Some(List(uri1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_?(Some(List(uri1, uri2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.uriSeq_?(Some(List(uri1, uri2, uri3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uriSeq_?(Some(List(List(uri1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_?(Some(List(List(uri1, uri2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq_?(Some(List(List(uri1, uri2, uri3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uriSeq_?(Some(List(List(uri1), List(uri2, uri3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_?(Some(List(List(uri1, uri2), List(uri2, uri3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq_?(Some(List(List(uri1, uri2), List(uri2, uri3, uri3)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.uriSeq_?(Some(List.empty[URI])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_?(Some(List.empty[List[URI]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_?(Some(List(List.empty[URI]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.uriSeq_?(Option.empty[List[URI]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uriSeq_?(Option.empty[List[List[URI]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(List(uri1, uri2)))
        val b = (2, Some(List(uri2, uri3, uri3)))
        val c = (3, None)
        for {
          _ <- Ns.i.uriSeq_?.insert(a, b, c).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uriSeq_?.not(Some(List(uri1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq_?.not(Some(List(uri1, uri2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.uriSeq_?.not(Some(List(uri1, uri2, uri3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uriSeq_?.not(Some(List(List(uri1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq_?.not(Some(List(List(uri1, uri2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uriSeq_?.not(Some(List(List(uri1, uri2, uri3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.uriSeq_?.not(Some(List(List(uri1), List(uri2, uri3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq_?.not(Some(List(List(uri1, uri2), List(uri2, uri3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uriSeq_?.not(Some(List(List(uri1, uri2), List(uri2, uri3, uri3)))).query.get.map(_ ==> List())

          // Empty Seqs are ignored
          _ <- Ns.i.a1.uriSeq_?.not(Some(List(List(uri1, uri2), List.empty[URI]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uriSeq_?.not(Some(List.empty[URI])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq_?.not(Some(List.empty[List[URI]])).query.get.map(_ ==> List(a, b))


          // Negation of None matches all asserted
          _ <- Ns.i.a1.uriSeq_?.not(Option.empty[List[URI]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq_?.not(Option.empty[List[List[URI]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(List(uri1, uri2)))
        val b = (2, Some(List(uri2, uri3, uri3)))
        val c = (3, None)
        for {
          _ <- Ns.i.uriSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.uriSeq_?.has(Some(uri0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_?.has(Some(uri1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq_?.has(Some(uri2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq_?.has(Some(uri3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uriSeq_?.has(Some(List(uri0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_?.has(Some(List(uri1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq_?.has(Some(List(uri2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq_?.has(Some(List(uri3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uriSeq_?.has(Some(List(uri1, uri2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq_?.has(Some(List(uri1, uri3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq_?.has(Some(List(uri2, uri3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq_?.has(Some(List(uri1, uri2, uri3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.uriSeq_?.has(Some(List.empty[URI])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.uriSeq_?.has(Option.empty[URI]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uriSeq_?.has(Option.empty[List[URI]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(uri1, uri2)))
        val b = (2, Some(List(uri2, uri3, uri3)))
        val c = (3, None)
        for {
          _ <- Ns.i.uriSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.uriSeq_?.hasNo(Some(uri0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq_?.hasNo(Some(uri1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uriSeq_?.hasNo(Some(uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_?.hasNo(Some(uri3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq_?.hasNo(Some(uri3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq_?.hasNo(Some(uri5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uriSeq_?.hasNo(Some(List(uri0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq_?.hasNo(Some(List(uri1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uriSeq_?.hasNo(Some(List(uri2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_?.hasNo(Some(List(uri3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq_?.hasNo(Some(List(uri3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq_?.hasNo(Some(List(uri5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.uriSeq_?.hasNo(Some(List(uri1, uri2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_?.hasNo(Some(List(uri1, uri3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_?.hasNo(Some(List(uri1, uri3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_?.hasNo(Some(List(uri1, uri5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.uriSeq_?.hasNo(Some(List.empty[URI])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.uriSeq_?.hasNo(Option.empty[URI]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq_?.hasNo(Option.empty[List[URI]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}