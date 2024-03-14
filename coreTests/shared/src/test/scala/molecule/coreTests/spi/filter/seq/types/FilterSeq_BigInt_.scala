// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_BigInt_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, List(bigInt1, bigInt2))
        val b = (2, List(bigInt2, bigInt3, bigInt3))
        for {
          _ <- Ns.i.bigIntSeq.insert(List(a, b)).transact

          _ <- Ns.i.a1.bigIntSeq.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, List(bigInt1, bigInt2))
        val b = (2, List(bigInt2, bigInt3, bigInt3))
        for {
          _ <- Ns.i.bigIntSeq.insert(List(a, b)).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.bigIntSeq(List(bigInt1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq(List(bigInt1, bigInt2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.bigIntSeq(List(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigIntSeq(List(List(bigInt1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq(List(List(bigInt1, bigInt2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq(List(List(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigIntSeq(List(bigInt1), List(bigInt2, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq(List(bigInt1, bigInt2), List(bigInt2, bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq(List(bigInt1, bigInt2), List(bigInt2, bigInt3, bigInt3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigIntSeq(List(List(bigInt1), List(bigInt2, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq(List(List(bigInt1, bigInt2), List(bigInt2, bigInt3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq(List(List(bigInt1, bigInt2), List(bigInt2, bigInt3, bigInt3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.bigIntSeq(List(bigInt1, bigInt2), List.empty[BigInt]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq(List.empty[BigInt], List(bigInt1, bigInt2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq(List.empty[BigInt], List.empty[BigInt]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq(List.empty[BigInt]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq(List.empty[List[BigInt]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq(List(List.empty[BigInt])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, List(bigInt1, bigInt2))
        val b = (2, List(bigInt2, bigInt3, bigInt3))
        for {
          _ <- Ns.i.bigIntSeq.insert(List(a, b)).transact

          // Exact Seq non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.bigIntSeq.not(List(bigInt1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.not(List(bigInt1, bigInt2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.bigIntSeq.not(List(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigIntSeq.not(List(List(bigInt1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.not(List(List(bigInt1, bigInt2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntSeq.not(List(List(bigInt1, bigInt2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntSeq.not(List(List(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.bigIntSeq.not(List(bigInt1), List(bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.not(List(bigInt1, bigInt2), List(bigInt2, bigInt3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntSeq.not(List(bigInt1, bigInt2), List(bigInt2, bigInt3, bigInt3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigIntSeq.not(List(List(bigInt1), List(bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.not(List(List(bigInt1, bigInt2), List(bigInt2, bigInt3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntSeq.not(List(List(bigInt1, bigInt2), List(bigInt2, bigInt3, bigInt3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.bigIntSeq.not(List(List(bigInt1, bigInt2), List.empty[BigInt])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntSeq.not(List.empty[BigInt]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.not(List.empty[List[BigInt]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, List(bigInt1, bigInt2))
        val b = (2, List(bigInt2, bigInt3, bigInt3))
        for {
          _ <- Ns.i.bigIntSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.bigIntSeq.has(bigInt0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq.has(bigInt1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq.has(bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.has(bigInt3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigIntSeq.has(List(bigInt0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq.has(List(bigInt1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq.has(List(bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.has(List(bigInt3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigIntSeq.has(bigInt1, bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.has(bigInt1, bigInt3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.has(bigInt2, bigInt3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.has(bigInt1, bigInt2, bigInt3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigIntSeq.has(List(bigInt1, bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.has(List(bigInt1, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.has(List(bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.has(List(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.bigIntSeq.has(List.empty[BigInt]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(bigInt1, bigInt2))
        val b = (2, List(bigInt2, bigInt3, bigInt3))
        for {
          _ <- Ns.i.bigIntSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.bigIntSeq.hasNo(bigInt0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.hasNo(bigInt1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntSeq.hasNo(bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq.hasNo(bigInt3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq.hasNo(bigInt3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq.hasNo(bigInt5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigIntSeq.hasNo(List(bigInt0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.hasNo(List(bigInt1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntSeq.hasNo(List(bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq.hasNo(List(bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq.hasNo(List(bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq.hasNo(List(bigInt5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.bigIntSeq.hasNo(bigInt1, bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq.hasNo(bigInt1, bigInt5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigIntSeq.hasNo(List(bigInt1, bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq.hasNo(List(bigInt1, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq.hasNo(List(bigInt1, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq.hasNo(List(bigInt1, bigInt5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.bigIntSeq.hasNo(List.empty[BigInt]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigIntSeq.insert(List(
            (1, List(bigInt1, bigInt2)),
            (2, List(bigInt2, bigInt3, bigInt3))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // bigIntSeq not asserted for i = 0
          _ <- Ns.i.a1.bigIntSeq_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.bigIntSeq_?.insert(List(
            (0, None),
            (1, Some(List(bigInt1, bigInt2))),
            (2, Some(List(bigInt2, bigInt3, bigInt3))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.bigIntSeq_().query.get.map(_ ==> List(0))

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.bigIntSeq_(List(bigInt1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_(List(bigInt1, bigInt2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.bigIntSeq_(List(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigIntSeq_(List(List(bigInt1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_(List(List(bigInt1, bigInt2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntSeq_(List(List(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigIntSeq_(List(bigInt1), List(bigInt2, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_(List(bigInt1, bigInt2), List(bigInt2, bigInt3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntSeq_(List(bigInt1, bigInt2), List(bigInt2, bigInt3, bigInt3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigIntSeq_(List(List(bigInt1), List(bigInt2, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_(List(List(bigInt1, bigInt2), List(bigInt2, bigInt3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntSeq_(List(List(bigInt1, bigInt2), List(bigInt2, bigInt3, bigInt3))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.bigIntSeq_(List(bigInt1, bigInt2), List.empty[BigInt]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntSeq_(List.empty[BigInt]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_(List.empty[List[BigInt]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_(List(List.empty[BigInt])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigIntSeq.insert(List(
            (1, List(bigInt1, bigInt2)),
            (2, List(bigInt2, bigInt3, bigInt3))
          )).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.bigIntSeq_.not(List(bigInt1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.not(List(bigInt1, bigInt2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.bigIntSeq_.not(List(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigIntSeq_.not(List(List(bigInt1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.not(List(List(bigInt1, bigInt2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntSeq_.not(List(List(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.bigIntSeq_.not(List(bigInt1), List(bigInt2, bigInt3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.not(List(bigInt1, bigInt2), List(bigInt2, bigInt3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntSeq_.not(List(bigInt1, bigInt2), List(bigInt2, bigInt3, bigInt3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigIntSeq_.not(List(List(bigInt1), List(bigInt2, bigInt3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.not(List(List(bigInt1, bigInt2), List(bigInt2, bigInt3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntSeq_.not(List(List(bigInt1, bigInt2), List(bigInt2, bigInt3, bigInt3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.bigIntSeq_.not(List(List(bigInt1, bigInt2), List.empty[BigInt])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntSeq_.not(List.empty[BigInt]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.not(List.empty[List[BigInt]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigIntSeq.insert(List(
            (1, List(bigInt1, bigInt2)),
            (2, List(bigInt2, bigInt3, bigInt3))
          )).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.bigIntSeq_.has(bigInt0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_.has(bigInt1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntSeq_.has(bigInt2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.has(bigInt3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.bigIntSeq_.has(List(bigInt0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_.has(List(bigInt1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntSeq_.has(List(bigInt2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.has(List(bigInt3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigIntSeq_.has(bigInt1, bigInt2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.has(bigInt1, bigInt3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.has(bigInt2, bigInt3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.has(bigInt1, bigInt2, bigInt3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigIntSeq_.has(List(bigInt1, bigInt2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.has(List(bigInt1, bigInt3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.has(List(bigInt2, bigInt3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.has(List(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.bigIntSeq_.has(List.empty[BigInt]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigIntSeq.insert(List(
            (1, List(bigInt1, bigInt2)),
            (2, List(bigInt2, bigInt3, bigInt3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.bigIntSeq_.hasNo(bigInt0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.hasNo(bigInt1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntSeq_.hasNo(bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_.hasNo(bigInt3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntSeq_.hasNo(bigInt3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntSeq_.hasNo(bigInt5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List(bigInt0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List(bigInt1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List(bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List(bigInt3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List(bigInt3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List(bigInt5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.bigIntSeq_.hasNo(bigInt1, bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_.hasNo(bigInt1, bigInt5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List(bigInt1, bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List(bigInt1, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List(bigInt1, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List(bigInt1, bigInt5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List.empty[BigInt]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(List(bigInt1, bigInt2)))
        val b = (2, Some(List(bigInt2, bigInt3, bigInt3)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigIntSeq_?.insert(a, b, c).transact

          _ <- Ns.i.a1.bigIntSeq_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no bigInt value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.bigIntSeq_?.query.get.map(_ ==> List(
            (2, Some(List(bigInt2, bigInt3, bigInt3))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(List(bigInt1, bigInt2)))
        val b = (2, Some(List(bigInt2, bigInt3, bigInt3)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigIntSeq_?.insert(a, b, c).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.bigIntSeq_?(Some(List(bigInt1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_?(Some(List(bigInt1, bigInt2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.bigIntSeq_?(Some(List(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigIntSeq_?(Some(List(List(bigInt1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_?(Some(List(List(bigInt1, bigInt2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq_?(Some(List(List(bigInt1, bigInt2, bigInt3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigIntSeq_?(Some(List(List(bigInt1), List(bigInt2, bigInt3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_?(Some(List(List(bigInt1, bigInt2), List(bigInt2, bigInt3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq_?(Some(List(List(bigInt1, bigInt2), List(bigInt2, bigInt3, bigInt3)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.bigIntSeq_?(Some(List.empty[BigInt])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_?(Some(List.empty[List[BigInt]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_?(Some(List(List.empty[BigInt]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.bigIntSeq_?(Option.empty[List[BigInt]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigIntSeq_?(Option.empty[List[List[BigInt]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(List(bigInt1, bigInt2)))
        val b = (2, Some(List(bigInt2, bigInt3, bigInt3)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigIntSeq_?.insert(a, b, c).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.bigIntSeq_?.not(Some(List(bigInt1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq_?.not(Some(List(bigInt1, bigInt2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.bigIntSeq_?.not(Some(List(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigIntSeq_?.not(Some(List(List(bigInt1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq_?.not(Some(List(List(bigInt1, bigInt2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntSeq_?.not(Some(List(List(bigInt1, bigInt2, bigInt3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.bigIntSeq_?.not(Some(List(List(bigInt1), List(bigInt2, bigInt3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq_?.not(Some(List(List(bigInt1, bigInt2), List(bigInt2, bigInt3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntSeq_?.not(Some(List(List(bigInt1, bigInt2), List(bigInt2, bigInt3, bigInt3)))).query.get.map(_ ==> List())

          // Empty Seqs are ignored
          _ <- Ns.i.a1.bigIntSeq_?.not(Some(List(List(bigInt1, bigInt2), List.empty[BigInt]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntSeq_?.not(Some(List.empty[BigInt])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq_?.not(Some(List.empty[List[BigInt]])).query.get.map(_ ==> List(a, b))


          // Negation of None matches all asserted
          _ <- Ns.i.a1.bigIntSeq_?.not(Option.empty[List[BigInt]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq_?.not(Option.empty[List[List[BigInt]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(List(bigInt1, bigInt2)))
        val b = (2, Some(List(bigInt2, bigInt3, bigInt3)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigIntSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.bigIntSeq_?.has(Some(bigInt0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_?.has(Some(bigInt1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq_?.has(Some(bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq_?.has(Some(bigInt3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigIntSeq_?.has(Some(List(bigInt0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_?.has(Some(List(bigInt1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq_?.has(Some(List(bigInt2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq_?.has(Some(List(bigInt3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigIntSeq_?.has(Some(List(bigInt1, bigInt2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq_?.has(Some(List(bigInt1, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq_?.has(Some(List(bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq_?.has(Some(List(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.bigIntSeq_?.has(Some(List.empty[BigInt])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.bigIntSeq_?.has(Option.empty[BigInt]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigIntSeq_?.has(Option.empty[List[BigInt]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(bigInt1, bigInt2)))
        val b = (2, Some(List(bigInt2, bigInt3, bigInt3)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigIntSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.bigIntSeq_?.hasNo(Some(bigInt0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq_?.hasNo(Some(bigInt1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntSeq_?.hasNo(Some(bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_?.hasNo(Some(bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq_?.hasNo(Some(bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq_?.hasNo(Some(bigInt5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigIntSeq_?.hasNo(Some(List(bigInt0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq_?.hasNo(Some(List(bigInt1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntSeq_?.hasNo(Some(List(bigInt2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_?.hasNo(Some(List(bigInt3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq_?.hasNo(Some(List(bigInt3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq_?.hasNo(Some(List(bigInt5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.bigIntSeq_?.hasNo(Some(List(bigInt1, bigInt2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_?.hasNo(Some(List(bigInt1, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_?.hasNo(Some(List(bigInt1, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_?.hasNo(Some(List(bigInt1, bigInt5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.bigIntSeq_?.hasNo(Some(List.empty[BigInt])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.bigIntSeq_?.hasNo(Option.empty[BigInt]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq_?.hasNo(Option.empty[List[BigInt]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}