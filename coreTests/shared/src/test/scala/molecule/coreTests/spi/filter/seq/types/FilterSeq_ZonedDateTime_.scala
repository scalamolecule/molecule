// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.time.ZonedDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_ZonedDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, List(zonedDateTime1, zonedDateTime2))
        val b = (2, List(zonedDateTime2, zonedDateTime3, zonedDateTime3))
        for {
          _ <- Ns.i.zonedDateTimeSeq.insert(List(a, b)).transact

          _ <- Ns.i.a1.zonedDateTimeSeq.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, List(zonedDateTime1, zonedDateTime2))
        val b = (2, List(zonedDateTime2, zonedDateTime3, zonedDateTime3))
        for {
          _ <- Ns.i.zonedDateTimeSeq.insert(List(a, b)).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.zonedDateTimeSeq(List(zonedDateTime1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.zonedDateTimeSeq(List(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq(List(List(zonedDateTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq(List(List(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq(List(List(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.zonedDateTimeSeq(List(zonedDateTime1), List(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq(List(zonedDateTime1, zonedDateTime2), List(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq(List(zonedDateTime1, zonedDateTime2), List(zonedDateTime2, zonedDateTime3, zonedDateTime3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq(List(List(zonedDateTime1), List(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq(List(List(zonedDateTime1, zonedDateTime2), List(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq(List(List(zonedDateTime1, zonedDateTime2), List(zonedDateTime2, zonedDateTime3, zonedDateTime3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.zonedDateTimeSeq(List(zonedDateTime1, zonedDateTime2), List.empty[ZonedDateTime]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq(List.empty[ZonedDateTime], List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq(List.empty[ZonedDateTime], List.empty[ZonedDateTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq(List.empty[ZonedDateTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq(List.empty[List[ZonedDateTime]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq(List(List.empty[ZonedDateTime])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.zonedDateTimeSeq().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, List(zonedDateTime1, zonedDateTime2))
        val b = (2, List(zonedDateTime2, zonedDateTime3, zonedDateTime3))
        for {
          _ <- Ns.i.zonedDateTimeSeq.insert(List(a, b)).transact

          // Exact Seq non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.zonedDateTimeSeq.not(List(zonedDateTime1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.not(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.zonedDateTimeSeq.not(List(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq.not(List(List(zonedDateTime1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.not(List(List(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeSeq.not(List(List(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeSeq.not(List(List(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.zonedDateTimeSeq.not(List(zonedDateTime1), List(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.not(List(zonedDateTime1, zonedDateTime2), List(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeSeq.not(List(zonedDateTime1, zonedDateTime2), List(zonedDateTime2, zonedDateTime3, zonedDateTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq.not(List(List(zonedDateTime1), List(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.not(List(List(zonedDateTime1, zonedDateTime2), List(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeSeq.not(List(List(zonedDateTime1, zonedDateTime2), List(zonedDateTime2, zonedDateTime3, zonedDateTime3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.zonedDateTimeSeq.not(List(List(zonedDateTime1, zonedDateTime2), List.empty[ZonedDateTime])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeSeq.not(List.empty[ZonedDateTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.not(List.empty[List[ZonedDateTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, List(zonedDateTime1, zonedDateTime2))
        val b = (2, List(zonedDateTime2, zonedDateTime3, zonedDateTime3))
        for {
          _ <- Ns.i.zonedDateTimeSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.zonedDateTimeSeq.has(zonedDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq.has(zonedDateTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq.has(zonedDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.has(zonedDateTime3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq.has(List(zonedDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq.has(List(zonedDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq.has(List(zonedDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.has(List(zonedDateTime3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.zonedDateTimeSeq.has(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.has(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.has(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.has(zonedDateTime1, zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq.has(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.has(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.has(List(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.has(List(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.zonedDateTimeSeq.has(List.empty[ZonedDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(zonedDateTime1, zonedDateTime2))
        val b = (2, List(zonedDateTime2, zonedDateTime3, zonedDateTime3))
        for {
          _ <- Ns.i.zonedDateTimeSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime1, zonedDateTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime1, zonedDateTime5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List.empty[ZonedDateTime]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.zonedDateTimeSeq.insert(List(
            (1, List(zonedDateTime1, zonedDateTime2)),
            (2, List(zonedDateTime2, zonedDateTime3, zonedDateTime3))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // zonedDateTimeSeq not asserted for i = 0
          _ <- Ns.i.a1.zonedDateTimeSeq_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.zonedDateTimeSeq_?.insert(List(
            (0, None),
            (1, Some(List(zonedDateTime1, zonedDateTime2))),
            (2, Some(List(zonedDateTime2, zonedDateTime3, zonedDateTime3))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.zonedDateTimeSeq_().query.get.map(_ ==> List(0))

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.zonedDateTimeSeq_(List(zonedDateTime1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.zonedDateTimeSeq_(List(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq_(List(List(zonedDateTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_(List(List(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSeq_(List(List(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.zonedDateTimeSeq_(List(zonedDateTime1), List(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_(List(zonedDateTime1, zonedDateTime2), List(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSeq_(List(zonedDateTime1, zonedDateTime2), List(zonedDateTime2, zonedDateTime3, zonedDateTime3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq_(List(List(zonedDateTime1), List(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_(List(List(zonedDateTime1, zonedDateTime2), List(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSeq_(List(List(zonedDateTime1, zonedDateTime2), List(zonedDateTime2, zonedDateTime3, zonedDateTime3))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.zonedDateTimeSeq_(List(zonedDateTime1, zonedDateTime2), List.empty[ZonedDateTime]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSeq_(List.empty[ZonedDateTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_(List.empty[List[ZonedDateTime]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_(List(List.empty[ZonedDateTime])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.zonedDateTimeSeq.insert(List(
            (1, List(zonedDateTime1, zonedDateTime2)),
            (2, List(zonedDateTime2, zonedDateTime3, zonedDateTime3))
          )).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.zonedDateTimeSeq_.not(List(zonedDateTime1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.not(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.zonedDateTimeSeq_.not(List(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq_.not(List(List(zonedDateTime1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.not(List(List(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.not(List(List(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.zonedDateTimeSeq_.not(List(zonedDateTime1), List(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.not(List(zonedDateTime1, zonedDateTime2), List(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.not(List(zonedDateTime1, zonedDateTime2), List(zonedDateTime2, zonedDateTime3, zonedDateTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq_.not(List(List(zonedDateTime1), List(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.not(List(List(zonedDateTime1, zonedDateTime2), List(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.not(List(List(zonedDateTime1, zonedDateTime2), List(zonedDateTime2, zonedDateTime3, zonedDateTime3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.zonedDateTimeSeq_.not(List(List(zonedDateTime1, zonedDateTime2), List.empty[ZonedDateTime])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.not(List.empty[ZonedDateTime]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.not(List.empty[List[ZonedDateTime]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.zonedDateTimeSeq.insert(List(
            (1, List(zonedDateTime1, zonedDateTime2)),
            (2, List(zonedDateTime2, zonedDateTime3, zonedDateTime3))
          )).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(zonedDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(zonedDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(zonedDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(zonedDateTime3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(zonedDateTime1, zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(List.empty[ZonedDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.zonedDateTimeSeq.insert(List(
            (1, List(zonedDateTime1, zonedDateTime2)),
            (2, List(zonedDateTime2, zonedDateTime3, zonedDateTime3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime1, zonedDateTime5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime1, zonedDateTime5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List.empty[ZonedDateTime]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(List(zonedDateTime1, zonedDateTime2)))
        val b = (2, Some(List(zonedDateTime2, zonedDateTime3, zonedDateTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.zonedDateTimeSeq_?.insert(a, b, c).transact

          _ <- Ns.i.a1.zonedDateTimeSeq_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no zonedDateTime value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.zonedDateTimeSeq_?.query.get.map(_ ==> List(
            (2, Some(List(zonedDateTime2, zonedDateTime3, zonedDateTime3))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(List(zonedDateTime1, zonedDateTime2)))
        val b = (2, Some(List(zonedDateTime2, zonedDateTime3, zonedDateTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.zonedDateTimeSeq_?.insert(a, b, c).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.zonedDateTimeSeq_?(Some(List(zonedDateTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_?(Some(List(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.zonedDateTimeSeq_?(Some(List(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq_?(Some(List(List(zonedDateTime1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_?(Some(List(List(zonedDateTime1, zonedDateTime2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq_?(Some(List(List(zonedDateTime1, zonedDateTime2, zonedDateTime3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.zonedDateTimeSeq_?(Some(List(List(zonedDateTime1), List(zonedDateTime2, zonedDateTime3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_?(Some(List(List(zonedDateTime1, zonedDateTime2), List(zonedDateTime2, zonedDateTime3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq_?(Some(List(List(zonedDateTime1, zonedDateTime2), List(zonedDateTime2, zonedDateTime3, zonedDateTime3)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.zonedDateTimeSeq_?(Some(List.empty[ZonedDateTime])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_?(Some(List.empty[List[ZonedDateTime]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_?(Some(List(List.empty[ZonedDateTime]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.zonedDateTimeSeq_?(Option.empty[List[ZonedDateTime]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.zonedDateTimeSeq_?(Option.empty[List[List[ZonedDateTime]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(List(zonedDateTime1, zonedDateTime2)))
        val b = (2, Some(List(zonedDateTime2, zonedDateTime3, zonedDateTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.zonedDateTimeSeq_?.insert(a, b, c).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.zonedDateTimeSeq_?.not(Some(List(zonedDateTime1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.not(Some(List(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.zonedDateTimeSeq_?.not(Some(List(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq_?.not(Some(List(List(zonedDateTime1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.not(Some(List(List(zonedDateTime1, zonedDateTime2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.not(Some(List(List(zonedDateTime1, zonedDateTime2, zonedDateTime3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.zonedDateTimeSeq_?.not(Some(List(List(zonedDateTime1), List(zonedDateTime2, zonedDateTime3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.not(Some(List(List(zonedDateTime1, zonedDateTime2), List(zonedDateTime2, zonedDateTime3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.not(Some(List(List(zonedDateTime1, zonedDateTime2), List(zonedDateTime2, zonedDateTime3, zonedDateTime3)))).query.get.map(_ ==> List())

          // Empty Seqs are ignored
          _ <- Ns.i.a1.zonedDateTimeSeq_?.not(Some(List(List(zonedDateTime1, zonedDateTime2), List.empty[ZonedDateTime]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.not(Some(List.empty[ZonedDateTime])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.not(Some(List.empty[List[ZonedDateTime]])).query.get.map(_ ==> List(a, b))


          // Negation of None matches all asserted
          _ <- Ns.i.a1.zonedDateTimeSeq_?.not(Option.empty[List[ZonedDateTime]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.not(Option.empty[List[List[ZonedDateTime]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(List(zonedDateTime1, zonedDateTime2)))
        val b = (2, Some(List(zonedDateTime2, zonedDateTime3, zonedDateTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.zonedDateTimeSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(zonedDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(zonedDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(zonedDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(zonedDateTime3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(List(zonedDateTime0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(List(zonedDateTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(List(zonedDateTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(List(zonedDateTime3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(List(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(List(zonedDateTime1, zonedDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(List(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(List(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(List.empty[ZonedDateTime])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Option.empty[ZonedDateTime]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Option.empty[List[ZonedDateTime]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(zonedDateTime1, zonedDateTime2)))
        val b = (2, Some(List(zonedDateTime2, zonedDateTime3, zonedDateTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.zonedDateTimeSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(zonedDateTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(zonedDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(zonedDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(zonedDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(zonedDateTime5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List(zonedDateTime0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List(zonedDateTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List(zonedDateTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List(zonedDateTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List(zonedDateTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List(zonedDateTime5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List(zonedDateTime1, zonedDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List(zonedDateTime1, zonedDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List(zonedDateTime1, zonedDateTime5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List.empty[ZonedDateTime])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Option.empty[ZonedDateTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Option.empty[List[ZonedDateTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}