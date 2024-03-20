// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_Short_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, List(short1, short2))
        val b = (2, List(short2, short3, short3))
        for {
          _ <- Ns.i.shortSeq.insert(List(a, b)).transact

          _ <- Ns.i.a1.shortSeq.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, List(short1, short2))
        val b = (2, List(short2, short3, short3))
        for {
          _ <- Ns.i.shortSeq.insert(List(a, b)).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.shortSeq(List(short1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq(List(short1, short2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.shortSeq(List(short1, short2, short3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shortSeq(List(List(short1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq(List(List(short1, short2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq(List(List(short1, short2, short3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.shortSeq(List(short1), List(short2, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq(List(short1, short2), List(short2, short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq(List(short1, short2), List(short2, short3, short3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shortSeq(List(List(short1), List(short2, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq(List(List(short1, short2), List(short2, short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq(List(List(short1, short2), List(short2, short3, short3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.shortSeq(List(short1, short2), List.empty[Short]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq(List.empty[Short], List(short1, short2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq(List.empty[Short], List.empty[Short]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq(List.empty[Short]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq(List.empty[List[Short]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq(List(List.empty[Short])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.shortSeq().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, List(short1, short2))
        val b = (2, List(short2, short3, short3))
        for {
          _ <- Ns.i.shortSeq.insert(List(a, b)).transact

          // Exact Seq non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.shortSeq.not(List(short1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.not(List(short1, short2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.shortSeq.not(List(short1, short2, short3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shortSeq.not(List(List(short1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.not(List(List(short1, short2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortSeq.not(List(List(short1, short2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortSeq.not(List(List(short1, short2, short3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.shortSeq.not(List(short1), List(short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.not(List(short1, short2), List(short2, short3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortSeq.not(List(short1, short2), List(short2, short3, short3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shortSeq.not(List(List(short1), List(short2, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.not(List(List(short1, short2), List(short2, short3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortSeq.not(List(List(short1, short2), List(short2, short3, short3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.shortSeq.not(List(List(short1, short2), List.empty[Short])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortSeq.not(List.empty[Short]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.not(List.empty[List[Short]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, List(short1, short2))
        val b = (2, List(short2, short3, short3))
        for {
          _ <- Ns.i.shortSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.shortSeq.has(short0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq.has(short1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq.has(short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(short3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shortSeq.has(List(short0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq.has(List(short1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq.has(List(short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(List(short3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.shortSeq.has(short1, short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(short1, short3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(short2, short3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(short1, short2, short3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shortSeq.has(List(short1, short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(List(short1, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(List(short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(List(short1, short2, short3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.shortSeq.has(List.empty[Short]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(short1, short2))
        val b = (2, List(short2, short3, short3))
        for {
          _ <- Ns.i.shortSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.shortSeq.hasNo(short0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.hasNo(short1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortSeq.hasNo(short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq.hasNo(short3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq.hasNo(short3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq.hasNo(short5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shortSeq.hasNo(List(short0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.hasNo(List(short1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortSeq.hasNo(List(short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq.hasNo(List(short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq.hasNo(List(short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq.hasNo(List(short5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.shortSeq.hasNo(short1, short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq.hasNo(short1, short3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq.hasNo(short1, short3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq.hasNo(short1, short5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shortSeq.hasNo(List(short1, short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq.hasNo(List(short1, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq.hasNo(List(short1, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq.hasNo(List(short1, short5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.shortSeq.hasNo(List.empty[Short]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.shortSeq.insert(List(
            (1, List(short1, short2)),
            (2, List(short2, short3, short3))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // shortSeq not asserted for i = 0
          _ <- Ns.i.a1.shortSeq_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.shortSeq_?.insert(List(
            (0, None),
            (1, Some(List(short1, short2))),
            (2, Some(List(short2, short3, short3))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.shortSeq_().query.get.map(_ ==> List(0))

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.shortSeq_(List(short1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_(List(short1, short2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.shortSeq_(List(short1, short2, short3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shortSeq_(List(List(short1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_(List(List(short1, short2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSeq_(List(List(short1, short2, short3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.shortSeq_(List(short1), List(short2, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_(List(short1, short2), List(short2, short3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSeq_(List(short1, short2), List(short2, short3, short3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.shortSeq_(List(List(short1), List(short2, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_(List(List(short1, short2), List(short2, short3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSeq_(List(List(short1, short2), List(short2, short3, short3))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.shortSeq_(List(short1, short2), List.empty[Short]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSeq_(List.empty[Short]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_(List.empty[List[Short]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_(List(List.empty[Short])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.shortSeq.insert(List(
            (1, List(short1, short2)),
            (2, List(short2, short3, short3))
          )).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.shortSeq_.not(List(short1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.not(List(short1, short2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.shortSeq_.not(List(short1, short2, short3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.shortSeq_.not(List(List(short1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.not(List(List(short1, short2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortSeq_.not(List(List(short1, short2, short3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.shortSeq_.not(List(short1), List(short2, short3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.not(List(short1, short2), List(short2, short3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortSeq_.not(List(short1, short2), List(short2, short3, short3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shortSeq_.not(List(List(short1), List(short2, short3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.not(List(List(short1, short2), List(short2, short3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortSeq_.not(List(List(short1, short2), List(short2, short3, short3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.shortSeq_.not(List(List(short1, short2), List.empty[Short])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortSeq_.not(List.empty[Short]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.not(List.empty[List[Short]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.shortSeq.insert(List(
            (1, List(short1, short2)),
            (2, List(short2, short3, short3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.shortSeq_.has(short0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_.has(short1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSeq_.has(short2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(short3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.shortSeq_.has(List(short0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_.has(List(short1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSeq_.has(List(short2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(List(short3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.shortSeq_.has(short1, short2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(short1, short3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(short2, short3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(short1, short2, short3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.shortSeq_.has(List(short1, short2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(List(short1, short3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(List(short2, short3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(List(short1, short2, short3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.shortSeq_.has(List.empty[Short]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.shortSeq.insert(List(
            (1, List(short1, short2)),
            (2, List(short2, short3, short3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.shortSeq_.hasNo(short0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.hasNo(short1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortSeq_.hasNo(short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_.hasNo(short3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSeq_.hasNo(short3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSeq_.hasNo(short5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.shortSeq_.hasNo(List(short0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.hasNo(List(short1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortSeq_.hasNo(List(short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_.hasNo(List(short3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSeq_.hasNo(List(short3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSeq_.hasNo(List(short5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.shortSeq_.hasNo(short1, short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_.hasNo(short1, short3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_.hasNo(short1, short3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_.hasNo(short1, short5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.shortSeq_.hasNo(List(short1, short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_.hasNo(List(short1, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_.hasNo(List(short1, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_.hasNo(List(short1, short5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.shortSeq_.hasNo(List.empty[Short]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(List(short1, short2)))
        val b = (2, Some(List(short2, short3, short3)))
        val c = (3, None)
        for {
          _ <- Ns.i.shortSeq_?.insert(a, b, c).transact

          _ <- Ns.i.a1.shortSeq_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no short value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.shortSeq_?.query.get.map(_ ==> List(
            (2, Some(List(short2, short3, short3))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(List(short1, short2)))
        val b = (2, Some(List(short2, short3, short3)))
        val c = (3, None)
        for {
          _ <- Ns.i.shortSeq_?.insert(a, b, c).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.shortSeq_?(Some(List(short1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_?(Some(List(short1, short2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.shortSeq_?(Some(List(short1, short2, short3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shortSeq_?(Some(List(List(short1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_?(Some(List(List(short1, short2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq_?(Some(List(List(short1, short2, short3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.shortSeq_?(Some(List(List(short1), List(short2, short3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_?(Some(List(List(short1, short2), List(short2, short3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq_?(Some(List(List(short1, short2), List(short2, short3, short3)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.shortSeq_?(Some(List.empty[Short])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_?(Some(List.empty[List[Short]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_?(Some(List(List.empty[Short]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.shortSeq_?(Option.empty[List[Short]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.shortSeq_?(Option.empty[List[List[Short]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(List(short1, short2)))
        val b = (2, Some(List(short2, short3, short3)))
        val c = (3, None)
        for {
          _ <- Ns.i.shortSeq_?.insert(a, b, c).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.shortSeq_?.not(Some(List(short1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.not(Some(List(short1, short2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.shortSeq_?.not(Some(List(short1, short2, short3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shortSeq_?.not(Some(List(List(short1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.not(Some(List(List(short1, short2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortSeq_?.not(Some(List(List(short1, short2, short3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.shortSeq_?.not(Some(List(List(short1), List(short2, short3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.not(Some(List(List(short1, short2), List(short2, short3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortSeq_?.not(Some(List(List(short1, short2), List(short2, short3, short3)))).query.get.map(_ ==> List())

          // Empty Seqs are ignored
          _ <- Ns.i.a1.shortSeq_?.not(Some(List(List(short1, short2), List.empty[Short]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortSeq_?.not(Some(List.empty[Short])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.not(Some(List.empty[List[Short]])).query.get.map(_ ==> List(a, b))


          // Negation of None matches all asserted
          _ <- Ns.i.a1.shortSeq_?.not(Option.empty[List[Short]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.not(Option.empty[List[List[Short]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(List(short1, short2)))
        val b = (2, Some(List(short2, short3, short3)))
        val c = (3, None)
        for {
          _ <- Ns.i.shortSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.shortSeq_?.has(Some(short0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_?.has(Some(short1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq_?.has(Some(short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.has(Some(short3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shortSeq_?.has(Some(List(short0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_?.has(Some(List(short1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq_?.has(Some(List(short2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.has(Some(List(short3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.shortSeq_?.has(Some(List(short1, short2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.has(Some(List(short1, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.has(Some(List(short2, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.has(Some(List(short1, short2, short3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.shortSeq_?.has(Some(List.empty[Short])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.shortSeq_?.has(Option.empty[Short]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.shortSeq_?.has(Option.empty[List[Short]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(short1, short2)))
        val b = (2, Some(List(short2, short3, short3)))
        val c = (3, None)
        for {
          _ <- Ns.i.shortSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(short0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(short1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(short5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List(short0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List(short1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List(short2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List(short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List(short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List(short5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List(short1, short2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List(short1, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List(short1, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List(short1, short5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List.empty[Short])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.shortSeq_?.hasNo(Option.empty[Short]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.hasNo(Option.empty[List[Short]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}