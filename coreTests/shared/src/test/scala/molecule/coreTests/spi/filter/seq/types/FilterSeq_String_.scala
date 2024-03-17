// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_String_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, List(string1, string2))
        val b = (2, List(string2, string3, string3))
        for {
          _ <- Ns.i.stringSeq.insert(List(a, b)).transact

          _ <- Ns.i.a1.stringSeq.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, List(string1, string2))
        val b = (2, List(string2, string3, string3))
        for {
          _ <- Ns.i.stringSeq.insert(List(a, b)).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.stringSeq(List(string1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq(List(string1, string2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.stringSeq(List(string1, string2, string3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.stringSeq(List(List(string1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq(List(List(string1, string2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq(List(List(string1, string2, string3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.stringSeq(List(string1), List(string2, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq(List(string1, string2), List(string2, string3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq(List(string1, string2), List(string2, string3, string3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.stringSeq(List(List(string1), List(string2, string3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq(List(List(string1, string2), List(string2, string3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq(List(List(string1, string2), List(string2, string3, string3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.stringSeq(List(string1, string2), List.empty[String]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq(List.empty[String], List(string1, string2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq(List.empty[String], List.empty[String]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq(List.empty[String]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq(List.empty[List[String]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq(List(List.empty[String])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.stringSeq().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, List(string1, string2))
        val b = (2, List(string2, string3, string3))
        for {
          _ <- Ns.i.stringSeq.insert(List(a, b)).transact

          // Exact Seq non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.stringSeq.not(List(string1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.not(List(string1, string2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.stringSeq.not(List(string1, string2, string3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.stringSeq.not(List(List(string1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.not(List(List(string1, string2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.stringSeq.not(List(List(string1, string2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.stringSeq.not(List(List(string1, string2, string3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.stringSeq.not(List(string1), List(string2, string3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.not(List(string1, string2), List(string2, string3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.stringSeq.not(List(string1, string2), List(string2, string3, string3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.stringSeq.not(List(List(string1), List(string2, string3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.not(List(List(string1, string2), List(string2, string3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.stringSeq.not(List(List(string1, string2), List(string2, string3, string3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.stringSeq.not(List(List(string1, string2), List.empty[String])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.stringSeq.not(List.empty[String]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.not(List.empty[List[String]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, List(string1, string2))
        val b = (2, List(string2, string3, string3))
        for {
          _ <- Ns.i.stringSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.stringSeq.has(string0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq.has(string1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq.has(string2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.has(string3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.stringSeq.has(List(string0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq.has(List(string1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq.has(List(string2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.has(List(string3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.stringSeq.has(string1, string2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.has(string1, string3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.has(string2, string3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.has(string1, string2, string3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.stringSeq.has(List(string1, string2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.has(List(string1, string3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.has(List(string2, string3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.has(List(string1, string2, string3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.stringSeq.has(List.empty[String]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(string1, string2))
        val b = (2, List(string2, string3, string3))
        for {
          _ <- Ns.i.stringSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.stringSeq.hasNo(string0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.hasNo(string1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.stringSeq.hasNo(string2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq.hasNo(string3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq.hasNo(string3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq.hasNo(string5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.stringSeq.hasNo(List(string0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.hasNo(List(string1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.stringSeq.hasNo(List(string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq.hasNo(List(string3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq.hasNo(List(string3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq.hasNo(List(string5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.stringSeq.hasNo(string1, string2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq.hasNo(string1, string3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq.hasNo(string1, string3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq.hasNo(string1, string5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.stringSeq.hasNo(List(string1, string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq.hasNo(List(string1, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq.hasNo(List(string1, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq.hasNo(List(string1, string5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.stringSeq.hasNo(List.empty[String]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.stringSeq.insert(List(
            (1, List(string1, string2)),
            (2, List(string2, string3, string3))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // stringSeq not asserted for i = 0
          _ <- Ns.i.a1.stringSeq_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.stringSeq_?.insert(List(
            (0, None),
            (1, Some(List(string1, string2))),
            (2, Some(List(string2, string3, string3))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.stringSeq_().query.get.map(_ ==> List(0))

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.stringSeq_(List(string1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_(List(string1, string2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.stringSeq_(List(string1, string2, string3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.stringSeq_(List(List(string1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_(List(List(string1, string2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSeq_(List(List(string1, string2, string3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.stringSeq_(List(string1), List(string2, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_(List(string1, string2), List(string2, string3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSeq_(List(string1, string2), List(string2, string3, string3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.stringSeq_(List(List(string1), List(string2, string3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_(List(List(string1, string2), List(string2, string3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSeq_(List(List(string1, string2), List(string2, string3, string3))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.stringSeq_(List(string1, string2), List.empty[String]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSeq_(List.empty[String]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_(List.empty[List[String]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_(List(List.empty[String])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.stringSeq.insert(List(
            (1, List(string1, string2)),
            (2, List(string2, string3, string3))
          )).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.stringSeq_.not(List(string1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.not(List(string1, string2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.stringSeq_.not(List(string1, string2, string3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.stringSeq_.not(List(List(string1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.not(List(List(string1, string2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringSeq_.not(List(List(string1, string2, string3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.stringSeq_.not(List(string1), List(string2, string3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.not(List(string1, string2), List(string2, string3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringSeq_.not(List(string1, string2), List(string2, string3, string3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.stringSeq_.not(List(List(string1), List(string2, string3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.not(List(List(string1, string2), List(string2, string3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringSeq_.not(List(List(string1, string2), List(string2, string3, string3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.stringSeq_.not(List(List(string1, string2), List.empty[String])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringSeq_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.not(List.empty[List[String]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.stringSeq.insert(List(
            (1, List(string1, string2)),
            (2, List(string2, string3, string3))
          )).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.stringSeq_.has(string0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_.has(string1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSeq_.has(string2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.has(string3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.stringSeq_.has(List(string0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_.has(List(string1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSeq_.has(List(string2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.has(List(string3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.stringSeq_.has(string1, string2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.has(string1, string3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.has(string2, string3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.has(string1, string2, string3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.stringSeq_.has(List(string1, string2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.has(List(string1, string3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.has(List(string2, string3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.has(List(string1, string2, string3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.stringSeq_.has(List.empty[String]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.stringSeq.insert(List(
            (1, List(string1, string2)),
            (2, List(string2, string3, string3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.stringSeq_.hasNo(string0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.hasNo(string1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringSeq_.hasNo(string2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_.hasNo(string3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSeq_.hasNo(string3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSeq_.hasNo(string5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.stringSeq_.hasNo(List(string0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.hasNo(List(string1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringSeq_.hasNo(List(string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_.hasNo(List(string3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSeq_.hasNo(List(string3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSeq_.hasNo(List(string5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.stringSeq_.hasNo(string1, string2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_.hasNo(string1, string3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_.hasNo(string1, string3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_.hasNo(string1, string5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.stringSeq_.hasNo(List(string1, string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_.hasNo(List(string1, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_.hasNo(List(string1, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_.hasNo(List(string1, string5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.stringSeq_.hasNo(List.empty[String]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(List(string1, string2)))
        val b = (2, Some(List(string2, string3, string3)))
        val c = (3, None)
        for {
          _ <- Ns.i.stringSeq_?.insert(a, b, c).transact

          _ <- Ns.i.a1.stringSeq_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no string value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.stringSeq_?.query.get.map(_ ==> List(
            (2, Some(List(string2, string3, string3))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(List(string1, string2)))
        val b = (2, Some(List(string2, string3, string3)))
        val c = (3, None)
        for {
          _ <- Ns.i.stringSeq_?.insert(a, b, c).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.stringSeq_?(Some(List(string1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_?(Some(List(string1, string2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.stringSeq_?(Some(List(string1, string2, string3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.stringSeq_?(Some(List(List(string1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_?(Some(List(List(string1, string2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq_?(Some(List(List(string1, string2, string3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.stringSeq_?(Some(List(List(string1), List(string2, string3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_?(Some(List(List(string1, string2), List(string2, string3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq_?(Some(List(List(string1, string2), List(string2, string3, string3)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.stringSeq_?(Some(List.empty[String])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_?(Some(List.empty[List[String]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_?(Some(List(List.empty[String]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.stringSeq_?(Option.empty[List[String]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.stringSeq_?(Option.empty[List[List[String]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(List(string1, string2)))
        val b = (2, Some(List(string2, string3, string3)))
        val c = (3, None)
        for {
          _ <- Ns.i.stringSeq_?.insert(a, b, c).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.stringSeq_?.not(Some(List(string1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq_?.not(Some(List(string1, string2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.stringSeq_?.not(Some(List(string1, string2, string3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.stringSeq_?.not(Some(List(List(string1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq_?.not(Some(List(List(string1, string2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.stringSeq_?.not(Some(List(List(string1, string2, string3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.stringSeq_?.not(Some(List(List(string1), List(string2, string3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq_?.not(Some(List(List(string1, string2), List(string2, string3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.stringSeq_?.not(Some(List(List(string1, string2), List(string2, string3, string3)))).query.get.map(_ ==> List())

          // Empty Seqs are ignored
          _ <- Ns.i.a1.stringSeq_?.not(Some(List(List(string1, string2), List.empty[String]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.stringSeq_?.not(Some(List.empty[String])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq_?.not(Some(List.empty[List[String]])).query.get.map(_ ==> List(a, b))


          // Negation of None matches all asserted
          _ <- Ns.i.a1.stringSeq_?.not(Option.empty[List[String]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq_?.not(Option.empty[List[List[String]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(List(string1, string2)))
        val b = (2, Some(List(string2, string3, string3)))
        val c = (3, None)
        for {
          _ <- Ns.i.stringSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.stringSeq_?.has(Some(string0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_?.has(Some(string1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq_?.has(Some(string2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq_?.has(Some(string3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.stringSeq_?.has(Some(List(string0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_?.has(Some(List(string1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq_?.has(Some(List(string2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq_?.has(Some(List(string3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.stringSeq_?.has(Some(List(string1, string2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq_?.has(Some(List(string1, string3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq_?.has(Some(List(string2, string3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq_?.has(Some(List(string1, string2, string3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.stringSeq_?.has(Some(List.empty[String])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.stringSeq_?.has(Option.empty[String]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.stringSeq_?.has(Option.empty[List[String]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(string1, string2)))
        val b = (2, Some(List(string2, string3, string3)))
        val c = (3, None)
        for {
          _ <- Ns.i.stringSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.stringSeq_?.hasNo(Some(string0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq_?.hasNo(Some(string1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.stringSeq_?.hasNo(Some(string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_?.hasNo(Some(string3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq_?.hasNo(Some(string3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq_?.hasNo(Some(string5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.stringSeq_?.hasNo(Some(List(string0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq_?.hasNo(Some(List(string1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.stringSeq_?.hasNo(Some(List(string2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_?.hasNo(Some(List(string3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq_?.hasNo(Some(List(string3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq_?.hasNo(Some(List(string5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.stringSeq_?.hasNo(Some(List(string1, string2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_?.hasNo(Some(List(string1, string3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_?.hasNo(Some(List(string1, string3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_?.hasNo(Some(List(string1, string5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.stringSeq_?.hasNo(Some(List.empty[String])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.stringSeq_?.hasNo(Option.empty[String]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq_?.hasNo(Option.empty[List[String]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}