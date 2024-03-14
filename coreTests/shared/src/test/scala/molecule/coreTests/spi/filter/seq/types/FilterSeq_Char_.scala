// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_Char_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, List(char1, char2))
        val b = (2, List(char2, char3, char3))
        for {
          _ <- Ns.i.charSeq.insert(List(a, b)).transact

          _ <- Ns.i.a1.charSeq.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, List(char1, char2))
        val b = (2, List(char2, char3, char3))
        for {
          _ <- Ns.i.charSeq.insert(List(a, b)).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.charSeq(List(char1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq(List(char1, char2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.charSeq(List(char1, char2, char3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.charSeq(List(List(char1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq(List(List(char1, char2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq(List(List(char1, char2, char3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.charSeq(List(char1), List(char2, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq(List(char1, char2), List(char2, char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq(List(char1, char2), List(char2, char3, char3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.charSeq(List(List(char1), List(char2, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq(List(List(char1, char2), List(char2, char3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq(List(List(char1, char2), List(char2, char3, char3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.charSeq(List(char1, char2), List.empty[Char]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq(List.empty[Char], List(char1, char2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq(List.empty[Char], List.empty[Char]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq(List.empty[Char]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq(List.empty[List[Char]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq(List(List.empty[Char])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, List(char1, char2))
        val b = (2, List(char2, char3, char3))
        for {
          _ <- Ns.i.charSeq.insert(List(a, b)).transact

          // Exact Seq non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.charSeq.not(List(char1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.not(List(char1, char2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.charSeq.not(List(char1, char2, char3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.charSeq.not(List(List(char1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.not(List(List(char1, char2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSeq.not(List(List(char1, char2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSeq.not(List(List(char1, char2, char3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.charSeq.not(List(char1), List(char2, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.not(List(char1, char2), List(char2, char3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSeq.not(List(char1, char2), List(char2, char3, char3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.charSeq.not(List(List(char1), List(char2, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.not(List(List(char1, char2), List(char2, char3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSeq.not(List(List(char1, char2), List(char2, char3, char3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.charSeq.not(List(List(char1, char2), List.empty[Char])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSeq.not(List.empty[Char]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.not(List.empty[List[Char]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, List(char1, char2))
        val b = (2, List(char2, char3, char3))
        for {
          _ <- Ns.i.charSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.charSeq.has(char0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq.has(char1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq.has(char2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.has(char3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.charSeq.has(List(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq.has(List(char1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq.has(List(char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.has(List(char3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.charSeq.has(char1, char2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.has(char1, char3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.has(char2, char3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.has(char1, char2, char3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.charSeq.has(List(char1, char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.has(List(char1, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.has(List(char2, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.has(List(char1, char2, char3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.charSeq.has(List.empty[Char]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(char1, char2))
        val b = (2, List(char2, char3, char3))
        for {
          _ <- Ns.i.charSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.charSeq.hasNo(char0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.hasNo(char1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSeq.hasNo(char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq.hasNo(char3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq.hasNo(char3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq.hasNo(char5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.charSeq.hasNo(List(char0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.hasNo(List(char1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSeq.hasNo(List(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq.hasNo(List(char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq.hasNo(List(char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq.hasNo(List(char5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.charSeq.hasNo(char1, char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq.hasNo(char1, char3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq.hasNo(char1, char3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq.hasNo(char1, char5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.charSeq.hasNo(List(char1, char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq.hasNo(List(char1, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq.hasNo(List(char1, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq.hasNo(List(char1, char5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.charSeq.hasNo(List.empty[Char]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.charSeq.insert(List(
            (1, List(char1, char2)),
            (2, List(char2, char3, char3))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // charSeq not asserted for i = 0
          _ <- Ns.i.a1.charSeq_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.charSeq_?.insert(List(
            (0, None),
            (1, Some(List(char1, char2))),
            (2, Some(List(char2, char3, char3))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.charSeq_().query.get.map(_ ==> List(0))

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.charSeq_(List(char1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_(List(char1, char2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.charSeq_(List(char1, char2, char3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.charSeq_(List(List(char1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_(List(List(char1, char2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSeq_(List(List(char1, char2, char3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.charSeq_(List(char1), List(char2, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_(List(char1, char2), List(char2, char3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSeq_(List(char1, char2), List(char2, char3, char3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.charSeq_(List(List(char1), List(char2, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_(List(List(char1, char2), List(char2, char3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSeq_(List(List(char1, char2), List(char2, char3, char3))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.charSeq_(List(char1, char2), List.empty[Char]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSeq_(List.empty[Char]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_(List.empty[List[Char]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_(List(List.empty[Char])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.charSeq.insert(List(
            (1, List(char1, char2)),
            (2, List(char2, char3, char3))
          )).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.charSeq_.not(List(char1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.not(List(char1, char2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.charSeq_.not(List(char1, char2, char3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.charSeq_.not(List(List(char1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.not(List(List(char1, char2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charSeq_.not(List(List(char1, char2, char3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.charSeq_.not(List(char1), List(char2, char3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.not(List(char1, char2), List(char2, char3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charSeq_.not(List(char1, char2), List(char2, char3, char3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.charSeq_.not(List(List(char1), List(char2, char3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.not(List(List(char1, char2), List(char2, char3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charSeq_.not(List(List(char1, char2), List(char2, char3, char3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.charSeq_.not(List(List(char1, char2), List.empty[Char])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charSeq_.not(List.empty[Char]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.not(List.empty[List[Char]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.charSeq.insert(List(
            (1, List(char1, char2)),
            (2, List(char2, char3, char3))
          )).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.charSeq_.has(char0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_.has(char1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSeq_.has(char2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.has(char3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.charSeq_.has(List(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_.has(List(char1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSeq_.has(List(char2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.has(List(char3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.charSeq_.has(char1, char2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.has(char1, char3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.has(char2, char3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.has(char1, char2, char3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.charSeq_.has(List(char1, char2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.has(List(char1, char3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.has(List(char2, char3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.has(List(char1, char2, char3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.charSeq_.has(List.empty[Char]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.charSeq.insert(List(
            (1, List(char1, char2)),
            (2, List(char2, char3, char3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.charSeq_.hasNo(char0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.hasNo(char1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charSeq_.hasNo(char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_.hasNo(char3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSeq_.hasNo(char3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSeq_.hasNo(char5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.charSeq_.hasNo(List(char0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.hasNo(List(char1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charSeq_.hasNo(List(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_.hasNo(List(char3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSeq_.hasNo(List(char3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSeq_.hasNo(List(char5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.charSeq_.hasNo(char1, char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_.hasNo(char1, char3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_.hasNo(char1, char3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_.hasNo(char1, char5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.charSeq_.hasNo(List(char1, char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_.hasNo(List(char1, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_.hasNo(List(char1, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_.hasNo(List(char1, char5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.charSeq_.hasNo(List.empty[Char]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(List(char1, char2)))
        val b = (2, Some(List(char2, char3, char3)))
        val c = (3, None)
        for {
          _ <- Ns.i.charSeq_?.insert(a, b, c).transact

          _ <- Ns.i.a1.charSeq_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no char value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.charSeq_?.query.get.map(_ ==> List(
            (2, Some(List(char2, char3, char3))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(List(char1, char2)))
        val b = (2, Some(List(char2, char3, char3)))
        val c = (3, None)
        for {
          _ <- Ns.i.charSeq_?.insert(a, b, c).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.charSeq_?(Some(List(char1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_?(Some(List(char1, char2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.charSeq_?(Some(List(char1, char2, char3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.charSeq_?(Some(List(List(char1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_?(Some(List(List(char1, char2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq_?(Some(List(List(char1, char2, char3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.charSeq_?(Some(List(List(char1), List(char2, char3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_?(Some(List(List(char1, char2), List(char2, char3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq_?(Some(List(List(char1, char2), List(char2, char3, char3)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.charSeq_?(Some(List.empty[Char])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_?(Some(List.empty[List[Char]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_?(Some(List(List.empty[Char]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.charSeq_?(Option.empty[List[Char]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.charSeq_?(Option.empty[List[List[Char]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(List(char1, char2)))
        val b = (2, Some(List(char2, char3, char3)))
        val c = (3, None)
        for {
          _ <- Ns.i.charSeq_?.insert(a, b, c).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.charSeq_?.not(Some(List(char1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq_?.not(Some(List(char1, char2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.charSeq_?.not(Some(List(char1, char2, char3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.charSeq_?.not(Some(List(List(char1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq_?.not(Some(List(List(char1, char2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSeq_?.not(Some(List(List(char1, char2, char3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.charSeq_?.not(Some(List(List(char1), List(char2, char3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq_?.not(Some(List(List(char1, char2), List(char2, char3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSeq_?.not(Some(List(List(char1, char2), List(char2, char3, char3)))).query.get.map(_ ==> List())

          // Empty Seqs are ignored
          _ <- Ns.i.a1.charSeq_?.not(Some(List(List(char1, char2), List.empty[Char]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSeq_?.not(Some(List.empty[Char])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq_?.not(Some(List.empty[List[Char]])).query.get.map(_ ==> List(a, b))


          // Negation of None matches all asserted
          _ <- Ns.i.a1.charSeq_?.not(Option.empty[List[Char]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq_?.not(Option.empty[List[List[Char]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(List(char1, char2)))
        val b = (2, Some(List(char2, char3, char3)))
        val c = (3, None)
        for {
          _ <- Ns.i.charSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.charSeq_?.has(Some(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_?.has(Some(char1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq_?.has(Some(char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq_?.has(Some(char3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.charSeq_?.has(Some(List(char0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_?.has(Some(List(char1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq_?.has(Some(List(char2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq_?.has(Some(List(char3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.charSeq_?.has(Some(List(char1, char2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq_?.has(Some(List(char1, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq_?.has(Some(List(char2, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq_?.has(Some(List(char1, char2, char3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.charSeq_?.has(Some(List.empty[Char])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.charSeq_?.has(Option.empty[Char]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.charSeq_?.has(Option.empty[List[Char]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(char1, char2)))
        val b = (2, Some(List(char2, char3, char3)))
        val c = (3, None)
        for {
          _ <- Ns.i.charSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.charSeq_?.hasNo(Some(char0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq_?.hasNo(Some(char1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSeq_?.hasNo(Some(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_?.hasNo(Some(char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq_?.hasNo(Some(char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq_?.hasNo(Some(char5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.charSeq_?.hasNo(Some(List(char0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq_?.hasNo(Some(List(char1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSeq_?.hasNo(Some(List(char2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_?.hasNo(Some(List(char3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq_?.hasNo(Some(List(char3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq_?.hasNo(Some(List(char5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.charSeq_?.hasNo(Some(List(char1, char2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_?.hasNo(Some(List(char1, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_?.hasNo(Some(List(char1, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_?.hasNo(Some(List(char1, char5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.charSeq_?.hasNo(Some(List.empty[Char])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.charSeq_?.hasNo(Option.empty[Char]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq_?.hasNo(Option.empty[List[Char]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}