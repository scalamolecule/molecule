package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_Int extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, List(int1, int2))
        val b = (2, List(int2, int3, int3))
        for {
          _ <- Ns.i.intSeq.insert(List(a, b)).transact

          _ <- Ns.i.a1.intSeq.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, List(int1, int2))
        val b = (2, List(int2, int3, int3))
        for {
          _ <- Ns.i.intSeq.insert(List(a, b)).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.intSeq(List(int1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq(List(int1, int2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.intSeq(List(int1, int2, int3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.intSeq(List(List(int1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq(List(List(int1, int2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq(List(List(int1, int2, int3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.intSeq(List(int1), List(int2, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq(List(int1, int2), List(int2, int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq(List(int1, int2), List(int2, int3, int3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.intSeq(List(List(int1), List(int2, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq(List(List(int1, int2), List(int2, int3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq(List(List(int1, int2), List(int2, int3, int3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.intSeq(List(int1, int2), List.empty[Int]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq(List.empty[Int], List(int1, int2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq(List.empty[Int], List.empty[Int]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq(List.empty[Int]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq(List.empty[List[Int]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq(List(List.empty[Int])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.intSeq().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, List(int1, int2))
        val b = (2, List(int2, int3, int3))
        for {
          _ <- Ns.i.intSeq.insert(List(a, b)).transact

          // Exact Seq non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.intSeq.not(List(int1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.not(List(int1, int2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.intSeq.not(List(int1, int2, int3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.intSeq.not(List(List(int1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.not(List(List(int1, int2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.intSeq.not(List(List(int1, int2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.intSeq.not(List(List(int1, int2, int3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.intSeq.not(List(int1), List(int2, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.not(List(int1, int2), List(int2, int3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.intSeq.not(List(int1, int2), List(int2, int3, int3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.intSeq.not(List(List(int1), List(int2, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.not(List(List(int1, int2), List(int2, int3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.intSeq.not(List(List(int1, int2), List(int2, int3, int3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.intSeq.not(List(List(int1, int2), List.empty[Int])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.intSeq.not(List.empty[Int]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.not(List.empty[List[Int]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, List(int1, int2))
        val b = (2, List(int2, int3, int3))
        for {
          _ <- Ns.i.intSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.intSeq.has(int0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq.has(int1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq.has(int2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.has(int3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.intSeq.has(List(int0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq.has(List(int1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq.has(List(int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.has(List(int3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.intSeq.has(int1, int2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.has(int1, int3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.has(int2, int3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.has(int1, int2, int3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.intSeq.has(List(int1, int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.has(List(int1, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.has(List(int2, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.has(List(int1, int2, int3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.intSeq.has(List.empty[Int]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(int1, int2))
        val b = (2, List(int2, int3, int3))
        for {
          _ <- Ns.i.intSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.intSeq.hasNo(int0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.hasNo(int1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.intSeq.hasNo(int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq.hasNo(int3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq.hasNo(int3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq.hasNo(int5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.intSeq.hasNo(List(int0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.hasNo(List(int1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.intSeq.hasNo(List(int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq.hasNo(List(int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq.hasNo(List(int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq.hasNo(List(int5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.intSeq.hasNo(int1, int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq.hasNo(int1, int3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq.hasNo(int1, int3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq.hasNo(int1, int5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.intSeq.hasNo(List(int1, int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq.hasNo(List(int1, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq.hasNo(List(int1, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq.hasNo(List(int1, int5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.intSeq.hasNo(List.empty[Int]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.intSeq.insert(List(
            (1, List(int1, int2)),
            (2, List(int2, int3, int3))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // intSeq not asserted for i = 0
          _ <- Ns.i.a1.intSeq_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.intSeq_?.insert(List(
            (0, None),
            (1, Some(List(int1, int2))),
            (2, Some(List(int2, int3, int3))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.intSeq_().query.get.map(_ ==> List(0))

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.intSeq_(List(int1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_(List(int1, int2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.intSeq_(List(int1, int2, int3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.intSeq_(List(List(int1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_(List(List(int1, int2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSeq_(List(List(int1, int2, int3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.intSeq_(List(int1), List(int2, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_(List(int1, int2), List(int2, int3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSeq_(List(int1, int2), List(int2, int3, int3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.intSeq_(List(List(int1), List(int2, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_(List(List(int1, int2), List(int2, int3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSeq_(List(List(int1, int2), List(int2, int3, int3))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.intSeq_(List(int1, int2), List.empty[Int]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSeq_(List.empty[Int]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_(List.empty[List[Int]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_(List(List.empty[Int])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.intSeq.insert(List(
            (1, List(int1, int2)),
            (2, List(int2, int3, int3))
          )).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.intSeq_.not(List(int1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.not(List(int1, int2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.intSeq_.not(List(int1, int2, int3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.intSeq_.not(List(List(int1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.not(List(List(int1, int2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intSeq_.not(List(List(int1, int2, int3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.intSeq_.not(List(int1), List(int2, int3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.not(List(int1, int2), List(int2, int3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intSeq_.not(List(int1, int2), List(int2, int3, int3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.intSeq_.not(List(List(int1), List(int2, int3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.not(List(List(int1, int2), List(int2, int3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intSeq_.not(List(List(int1, int2), List(int2, int3, int3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.intSeq_.not(List(List(int1, int2), List.empty[Int])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intSeq_.not(List.empty[Int]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.not(List.empty[List[Int]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.intSeq.insert(List(
            (1, List(int1, int2)),
            (2, List(int2, int3, int3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.intSeq_.has(int0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_.has(int1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSeq_.has(int2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.has(int3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.intSeq_.has(List(int0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_.has(List(int1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSeq_.has(List(int2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.has(List(int3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.intSeq_.has(int1, int2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.has(int1, int3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.has(int2, int3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.has(int1, int2, int3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.intSeq_.has(List(int1, int2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.has(List(int1, int3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.has(List(int2, int3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.has(List(int1, int2, int3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.intSeq_.has(List.empty[Int]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.intSeq.insert(List(
            (1, List(int1, int2)),
            (2, List(int2, int3, int3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.intSeq_.hasNo(int0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.hasNo(int1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intSeq_.hasNo(int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_.hasNo(int3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSeq_.hasNo(int3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSeq_.hasNo(int5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.intSeq_.hasNo(List(int0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.hasNo(List(int1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intSeq_.hasNo(List(int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_.hasNo(List(int3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSeq_.hasNo(List(int3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSeq_.hasNo(List(int5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.intSeq_.hasNo(int1, int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_.hasNo(int1, int3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_.hasNo(int1, int3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_.hasNo(int1, int5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.intSeq_.hasNo(List(int1, int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_.hasNo(List(int1, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_.hasNo(List(int1, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_.hasNo(List(int1, int5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.intSeq_.hasNo(List.empty[Int]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(List(int1, int2)))
        val b = (2, Some(List(int2, int3, int3)))
        val c = (3, None)
        for {
          _ <- Ns.i.intSeq_?.insert(a, b, c).transact

          _ <- Ns.i.a1.intSeq_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no int value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.intSeq_?.query.get.map(_ ==> List(
            (2, Some(List(int2, int3, int3))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(List(int1, int2)))
        val b = (2, Some(List(int2, int3, int3)))
        val c = (3, None)
        for {
          _ <- Ns.i.intSeq_?.insert(a, b, c).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.intSeq_?(Some(List(int1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_?(Some(List(int1, int2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.intSeq_?(Some(List(int1, int2, int3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.intSeq_?(Some(List(List(int1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_?(Some(List(List(int1, int2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq_?(Some(List(List(int1, int2, int3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.intSeq_?(Some(List(List(int1), List(int2, int3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_?(Some(List(List(int1, int2), List(int2, int3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq_?(Some(List(List(int1, int2), List(int2, int3, int3)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.intSeq_?(Some(List.empty[Int])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_?(Some(List.empty[List[Int]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_?(Some(List(List.empty[Int]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.intSeq_?(Option.empty[List[Int]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.intSeq_?(Option.empty[List[List[Int]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(List(int1, int2)))
        val b = (2, Some(List(int2, int3, int3)))
        val c = (3, None)
        for {
          _ <- Ns.i.intSeq_?.insert(a, b, c).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.intSeq_?.not(Some(List(int1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.not(Some(List(int1, int2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.intSeq_?.not(Some(List(int1, int2, int3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.intSeq_?.not(Some(List(List(int1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.not(Some(List(List(int1, int2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.intSeq_?.not(Some(List(List(int1, int2, int3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.intSeq_?.not(Some(List(List(int1), List(int2, int3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.not(Some(List(List(int1, int2), List(int2, int3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.intSeq_?.not(Some(List(List(int1, int2), List(int2, int3, int3)))).query.get.map(_ ==> List())

          // Empty Seqs are ignored
          _ <- Ns.i.a1.intSeq_?.not(Some(List(List(int1, int2), List.empty[Int]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.intSeq_?.not(Some(List.empty[Int])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.not(Some(List.empty[List[Int]])).query.get.map(_ ==> List(a, b))


          // Negation of None matches all asserted
          _ <- Ns.i.a1.intSeq_?.not(Option.empty[List[Int]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.not(Option.empty[List[List[Int]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(List(int1, int2)))
        val b = (2, Some(List(int2, int3, int3)))
        val c = (3, None)
        for {
          _ <- Ns.i.intSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.intSeq_?.has(Some(int0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_?.has(Some(int1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq_?.has(Some(int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.has(Some(int3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.intSeq_?.has(Some(List(int0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_?.has(Some(List(int1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq_?.has(Some(List(int2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.has(Some(List(int3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.intSeq_?.has(Some(List(int1, int2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.has(Some(List(int1, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.has(Some(List(int2, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.has(Some(List(int1, int2, int3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.intSeq_?.has(Some(List.empty[Int])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.intSeq_?.has(Option.empty[Int]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.intSeq_?.has(Option.empty[List[Int]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(int1, int2)))
        val b = (2, Some(List(int2, int3, int3)))
        val c = (3, None)
        for {
          _ <- Ns.i.intSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(int0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(int1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(int5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List(int0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List(int1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List(int2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List(int3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List(int3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List(int5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List(int1, int2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List(int1, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List(int1, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List(int1, int5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List.empty[Int])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.intSeq_?.hasNo(Option.empty[Int]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.hasNo(Option.empty[List[Int]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}