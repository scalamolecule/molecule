// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_BigDecimal_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, List(bigDecimal1, bigDecimal2))
        val b = (2, List(bigDecimal2, bigDecimal3, bigDecimal3))
        for {
          _ <- Ns.i.bigDecimalSeq.insert(List(a, b)).transact

          _ <- Ns.i.a1.bigDecimalSeq.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, List(bigDecimal1, bigDecimal2))
        val b = (2, List(bigDecimal2, bigDecimal3, bigDecimal3))
        for {
          _ <- Ns.i.bigDecimalSeq.insert(List(a, b)).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.bigDecimalSeq(List(bigDecimal1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.bigDecimalSeq(List(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq(List(List(bigDecimal1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq(List(List(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq(List(List(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimalSeq(List(bigDecimal1), List(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq(List(bigDecimal1, bigDecimal2), List(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq(List(bigDecimal1, bigDecimal2), List(bigDecimal2, bigDecimal3, bigDecimal3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq(List(List(bigDecimal1), List(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq(List(List(bigDecimal1, bigDecimal2), List(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq(List(List(bigDecimal1, bigDecimal2), List(bigDecimal2, bigDecimal3, bigDecimal3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.bigDecimalSeq(List(bigDecimal1, bigDecimal2), List.empty[BigDecimal]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq(List.empty[BigDecimal], List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq(List.empty[BigDecimal], List.empty[BigDecimal]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq(List.empty[BigDecimal]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq(List.empty[List[BigDecimal]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq(List(List.empty[BigDecimal])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.bigDecimalSeq().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, List(bigDecimal1, bigDecimal2))
        val b = (2, List(bigDecimal2, bigDecimal3, bigDecimal3))
        for {
          _ <- Ns.i.bigDecimalSeq.insert(List(a, b)).transact

          // Exact Seq non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimalSeq.not(List(bigDecimal1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.not(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.bigDecimalSeq.not(List(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq.not(List(List(bigDecimal1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.not(List(List(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimalSeq.not(List(List(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimalSeq.not(List(List(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimalSeq.not(List(bigDecimal1), List(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.not(List(bigDecimal1, bigDecimal2), List(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimalSeq.not(List(bigDecimal1, bigDecimal2), List(bigDecimal2, bigDecimal3, bigDecimal3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq.not(List(List(bigDecimal1), List(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.not(List(List(bigDecimal1, bigDecimal2), List(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimalSeq.not(List(List(bigDecimal1, bigDecimal2), List(bigDecimal2, bigDecimal3, bigDecimal3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.bigDecimalSeq.not(List(List(bigDecimal1, bigDecimal2), List.empty[BigDecimal])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimalSeq.not(List.empty[BigDecimal]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.not(List.empty[List[BigDecimal]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, List(bigDecimal1, bigDecimal2))
        val b = (2, List(bigDecimal2, bigDecimal3, bigDecimal3))
        for {
          _ <- Ns.i.bigDecimalSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.bigDecimalSeq.has(bigDecimal0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq.has(bigDecimal1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq.has(bigDecimal2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.has(bigDecimal3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq.has(List(bigDecimal0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq.has(List(bigDecimal1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq.has(List(bigDecimal2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.has(List(bigDecimal3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigDecimalSeq.has(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.has(bigDecimal1, bigDecimal3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.has(bigDecimal2, bigDecimal3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.has(bigDecimal1, bigDecimal2, bigDecimal3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq.has(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.has(List(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.has(List(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.has(List(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.bigDecimalSeq.has(List.empty[BigDecimal]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(bigDecimal1, bigDecimal2))
        val b = (2, List(bigDecimal2, bigDecimal3, bigDecimal3))
        for {
          _ <- Ns.i.bigDecimalSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(bigDecimal0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(bigDecimal1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(bigDecimal2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(bigDecimal3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(bigDecimal3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(bigDecimal5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List(bigDecimal0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List(bigDecimal1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List(bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List(bigDecimal3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List(bigDecimal3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List(bigDecimal5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(bigDecimal1, bigDecimal2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(bigDecimal1, bigDecimal3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(bigDecimal1, bigDecimal3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(bigDecimal1, bigDecimal5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List(bigDecimal1, bigDecimal5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List.empty[BigDecimal]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigDecimalSeq.insert(List(
            (1, List(bigDecimal1, bigDecimal2)),
            (2, List(bigDecimal2, bigDecimal3, bigDecimal3))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // bigDecimalSeq not asserted for i = 0
          _ <- Ns.i.a1.bigDecimalSeq_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.bigDecimalSeq_?.insert(List(
            (0, None),
            (1, Some(List(bigDecimal1, bigDecimal2))),
            (2, Some(List(bigDecimal2, bigDecimal3, bigDecimal3))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.bigDecimalSeq_().query.get.map(_ ==> List(0))

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.bigDecimalSeq_(List(bigDecimal1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.bigDecimalSeq_(List(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq_(List(List(bigDecimal1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_(List(List(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSeq_(List(List(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimalSeq_(List(bigDecimal1), List(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_(List(bigDecimal1, bigDecimal2), List(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSeq_(List(bigDecimal1, bigDecimal2), List(bigDecimal2, bigDecimal3, bigDecimal3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq_(List(List(bigDecimal1), List(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_(List(List(bigDecimal1, bigDecimal2), List(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSeq_(List(List(bigDecimal1, bigDecimal2), List(bigDecimal2, bigDecimal3, bigDecimal3))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.bigDecimalSeq_(List(bigDecimal1, bigDecimal2), List.empty[BigDecimal]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSeq_(List.empty[BigDecimal]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_(List.empty[List[BigDecimal]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_(List(List.empty[BigDecimal])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigDecimalSeq.insert(List(
            (1, List(bigDecimal1, bigDecimal2)),
            (2, List(bigDecimal2, bigDecimal3, bigDecimal3))
          )).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimalSeq_.not(List(bigDecimal1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.not(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.bigDecimalSeq_.not(List(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq_.not(List(List(bigDecimal1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.not(List(List(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalSeq_.not(List(List(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimalSeq_.not(List(bigDecimal1), List(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.not(List(bigDecimal1, bigDecimal2), List(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalSeq_.not(List(bigDecimal1, bigDecimal2), List(bigDecimal2, bigDecimal3, bigDecimal3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq_.not(List(List(bigDecimal1), List(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.not(List(List(bigDecimal1, bigDecimal2), List(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalSeq_.not(List(List(bigDecimal1, bigDecimal2), List(bigDecimal2, bigDecimal3, bigDecimal3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.bigDecimalSeq_.not(List(List(bigDecimal1, bigDecimal2), List.empty[BigDecimal])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalSeq_.not(List.empty[BigDecimal]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.not(List.empty[List[BigDecimal]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigDecimalSeq.insert(List(
            (1, List(bigDecimal1, bigDecimal2)),
            (2, List(bigDecimal2, bigDecimal3, bigDecimal3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.bigDecimalSeq_.has(bigDecimal0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_.has(bigDecimal1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSeq_.has(bigDecimal2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.has(bigDecimal3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq_.has(List(bigDecimal0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_.has(List(bigDecimal1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSeq_.has(List(bigDecimal2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.has(List(bigDecimal3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigDecimalSeq_.has(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.has(bigDecimal1, bigDecimal3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.has(bigDecimal2, bigDecimal3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.has(bigDecimal1, bigDecimal2, bigDecimal3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq_.has(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.has(List(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.has(List(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.has(List(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.bigDecimalSeq_.has(List.empty[BigDecimal]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigDecimalSeq.insert(List(
            (1, List(bigDecimal1, bigDecimal2)),
            (2, List(bigDecimal2, bigDecimal3, bigDecimal3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(bigDecimal0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(bigDecimal1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(bigDecimal2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(bigDecimal3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(bigDecimal3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(bigDecimal5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(bigDecimal1, bigDecimal2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(bigDecimal1, bigDecimal3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(bigDecimal1, bigDecimal3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(bigDecimal1, bigDecimal5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal1, bigDecimal5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List.empty[BigDecimal]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(List(bigDecimal1, bigDecimal2)))
        val b = (2, Some(List(bigDecimal2, bigDecimal3, bigDecimal3)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigDecimalSeq_?.insert(a, b, c).transact

          _ <- Ns.i.a1.bigDecimalSeq_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no bigDecimal value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.bigDecimalSeq_?.query.get.map(_ ==> List(
            (2, Some(List(bigDecimal2, bigDecimal3, bigDecimal3))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(List(bigDecimal1, bigDecimal2)))
        val b = (2, Some(List(bigDecimal2, bigDecimal3, bigDecimal3)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigDecimalSeq_?.insert(a, b, c).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.bigDecimalSeq_?(Some(List(bigDecimal1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_?(Some(List(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.bigDecimalSeq_?(Some(List(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq_?(Some(List(List(bigDecimal1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_?(Some(List(List(bigDecimal1, bigDecimal2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq_?(Some(List(List(bigDecimal1, bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimalSeq_?(Some(List(List(bigDecimal1), List(bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_?(Some(List(List(bigDecimal1, bigDecimal2), List(bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq_?(Some(List(List(bigDecimal1, bigDecimal2), List(bigDecimal2, bigDecimal3, bigDecimal3)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.bigDecimalSeq_?(Some(List.empty[BigDecimal])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_?(Some(List.empty[List[BigDecimal]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_?(Some(List(List.empty[BigDecimal]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.bigDecimalSeq_?(Option.empty[List[BigDecimal]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigDecimalSeq_?(Option.empty[List[List[BigDecimal]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(List(bigDecimal1, bigDecimal2)))
        val b = (2, Some(List(bigDecimal2, bigDecimal3, bigDecimal3)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigDecimalSeq_?.insert(a, b, c).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimalSeq_?.not(Some(List(bigDecimal1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq_?.not(Some(List(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.bigDecimalSeq_?.not(Some(List(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq_?.not(Some(List(List(bigDecimal1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq_?.not(Some(List(List(bigDecimal1, bigDecimal2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimalSeq_?.not(Some(List(List(bigDecimal1, bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimalSeq_?.not(Some(List(List(bigDecimal1), List(bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq_?.not(Some(List(List(bigDecimal1, bigDecimal2), List(bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimalSeq_?.not(Some(List(List(bigDecimal1, bigDecimal2), List(bigDecimal2, bigDecimal3, bigDecimal3)))).query.get.map(_ ==> List())

          // Empty Seqs are ignored
          _ <- Ns.i.a1.bigDecimalSeq_?.not(Some(List(List(bigDecimal1, bigDecimal2), List.empty[BigDecimal]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimalSeq_?.not(Some(List.empty[BigDecimal])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq_?.not(Some(List.empty[List[BigDecimal]])).query.get.map(_ ==> List(a, b))


          // Negation of None matches all asserted
          _ <- Ns.i.a1.bigDecimalSeq_?.not(Option.empty[List[BigDecimal]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq_?.not(Option.empty[List[List[BigDecimal]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(List(bigDecimal1, bigDecimal2)))
        val b = (2, Some(List(bigDecimal2, bigDecimal3, bigDecimal3)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigDecimalSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.bigDecimalSeq_?.has(Some(bigDecimal0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_?.has(Some(bigDecimal1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq_?.has(Some(bigDecimal2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq_?.has(Some(bigDecimal3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq_?.has(Some(List(bigDecimal0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_?.has(Some(List(bigDecimal1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq_?.has(Some(List(bigDecimal2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq_?.has(Some(List(bigDecimal3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigDecimalSeq_?.has(Some(List(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq_?.has(Some(List(bigDecimal1, bigDecimal3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq_?.has(Some(List(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq_?.has(Some(List(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.bigDecimalSeq_?.has(Some(List.empty[BigDecimal])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.bigDecimalSeq_?.has(Option.empty[BigDecimal]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigDecimalSeq_?.has(Option.empty[List[BigDecimal]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(bigDecimal1, bigDecimal2)))
        val b = (2, Some(List(bigDecimal2, bigDecimal3, bigDecimal3)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigDecimalSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.bigDecimalSeq_?.hasNo(Some(bigDecimal0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq_?.hasNo(Some(bigDecimal1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimalSeq_?.hasNo(Some(bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_?.hasNo(Some(bigDecimal3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq_?.hasNo(Some(bigDecimal3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq_?.hasNo(Some(bigDecimal5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq_?.hasNo(Some(List(bigDecimal0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq_?.hasNo(Some(List(bigDecimal1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimalSeq_?.hasNo(Some(List(bigDecimal2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_?.hasNo(Some(List(bigDecimal3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq_?.hasNo(Some(List(bigDecimal3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq_?.hasNo(Some(List(bigDecimal5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.bigDecimalSeq_?.hasNo(Some(List(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_?.hasNo(Some(List(bigDecimal1, bigDecimal3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_?.hasNo(Some(List(bigDecimal1, bigDecimal3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_?.hasNo(Some(List(bigDecimal1, bigDecimal5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.bigDecimalSeq_?.hasNo(Some(List.empty[BigDecimal])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.bigDecimalSeq_?.hasNo(Option.empty[BigDecimal]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq_?.hasNo(Option.empty[List[BigDecimal]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}