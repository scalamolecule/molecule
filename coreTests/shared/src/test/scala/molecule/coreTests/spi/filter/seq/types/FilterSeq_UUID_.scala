// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.util.UUID
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_UUID_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, List(uuid1, uuid2))
        val b = (2, List(uuid2, uuid3, uuid3))
        for {
          _ <- Ns.i.uuidSeq.insert(List(a, b)).transact

          _ <- Ns.i.a1.uuidSeq.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, List(uuid1, uuid2))
        val b = (2, List(uuid2, uuid3, uuid3))
        for {
          _ <- Ns.i.uuidSeq.insert(List(a, b)).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uuidSeq(List(uuid1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq(List(uuid1, uuid2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.uuidSeq(List(uuid1, uuid2, uuid3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuidSeq(List(List(uuid1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq(List(List(uuid1, uuid2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq(List(List(uuid1, uuid2, uuid3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uuidSeq(List(uuid1), List(uuid2, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq(List(uuid1, uuid2), List(uuid2, uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq(List(uuid1, uuid2), List(uuid2, uuid3, uuid3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuidSeq(List(List(uuid1), List(uuid2, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq(List(List(uuid1, uuid2), List(uuid2, uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq(List(List(uuid1, uuid2), List(uuid2, uuid3, uuid3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.uuidSeq(List(uuid1, uuid2), List.empty[UUID]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq(List.empty[UUID], List(uuid1, uuid2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq(List.empty[UUID], List.empty[UUID]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq(List.empty[UUID]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq(List.empty[List[UUID]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq(List(List.empty[UUID])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.uuidSeq().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, List(uuid1, uuid2))
        val b = (2, List(uuid2, uuid3, uuid3))
        for {
          _ <- Ns.i.uuidSeq.insert(List(a, b)).transact

          // Exact Seq non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uuidSeq.not(List(uuid1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.not(List(uuid1, uuid2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.uuidSeq.not(List(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuidSeq.not(List(List(uuid1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.not(List(List(uuid1, uuid2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSeq.not(List(List(uuid1, uuid2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSeq.not(List(List(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.uuidSeq.not(List(uuid1), List(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.not(List(uuid1, uuid2), List(uuid2, uuid3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSeq.not(List(uuid1, uuid2), List(uuid2, uuid3, uuid3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuidSeq.not(List(List(uuid1), List(uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.not(List(List(uuid1, uuid2), List(uuid2, uuid3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSeq.not(List(List(uuid1, uuid2), List(uuid2, uuid3, uuid3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.uuidSeq.not(List(List(uuid1, uuid2), List.empty[UUID])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSeq.not(List.empty[UUID]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.not(List.empty[List[UUID]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, List(uuid1, uuid2))
        val b = (2, List(uuid2, uuid3, uuid3))
        for {
          _ <- Ns.i.uuidSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.uuidSeq.has(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq.has(uuid1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq.has(uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.has(uuid3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuidSeq.has(List(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq.has(List(uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq.has(List(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.has(List(uuid3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uuidSeq.has(uuid1, uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.has(uuid1, uuid3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.has(uuid2, uuid3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.has(uuid1, uuid2, uuid3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuidSeq.has(List(uuid1, uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.has(List(uuid1, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.has(List(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.has(List(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.uuidSeq.has(List.empty[UUID]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(uuid1, uuid2))
        val b = (2, List(uuid2, uuid3, uuid3))
        for {
          _ <- Ns.i.uuidSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.uuidSeq.hasNo(uuid0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.hasNo(uuid1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSeq.hasNo(uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq.hasNo(uuid3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq.hasNo(uuid3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq.hasNo(uuid5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuidSeq.hasNo(List(uuid0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.hasNo(List(uuid1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSeq.hasNo(List(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq.hasNo(List(uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq.hasNo(List(uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq.hasNo(List(uuid5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.uuidSeq.hasNo(uuid1, uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq.hasNo(uuid1, uuid5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuidSeq.hasNo(List(uuid1, uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq.hasNo(List(uuid1, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq.hasNo(List(uuid1, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq.hasNo(List(uuid1, uuid5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.uuidSeq.hasNo(List.empty[UUID]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uuidSeq.insert(List(
            (1, List(uuid1, uuid2)),
            (2, List(uuid2, uuid3, uuid3))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // uuidSeq not asserted for i = 0
          _ <- Ns.i.a1.uuidSeq_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.uuidSeq_?.insert(List(
            (0, None),
            (1, Some(List(uuid1, uuid2))),
            (2, Some(List(uuid2, uuid3, uuid3))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.uuidSeq_().query.get.map(_ ==> List(0))

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uuidSeq_(List(uuid1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_(List(uuid1, uuid2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.uuidSeq_(List(uuid1, uuid2, uuid3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuidSeq_(List(List(uuid1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_(List(List(uuid1, uuid2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSeq_(List(List(uuid1, uuid2, uuid3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uuidSeq_(List(uuid1), List(uuid2, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_(List(uuid1, uuid2), List(uuid2, uuid3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSeq_(List(uuid1, uuid2), List(uuid2, uuid3, uuid3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uuidSeq_(List(List(uuid1), List(uuid2, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_(List(List(uuid1, uuid2), List(uuid2, uuid3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSeq_(List(List(uuid1, uuid2), List(uuid2, uuid3, uuid3))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.uuidSeq_(List(uuid1, uuid2), List.empty[UUID]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSeq_(List.empty[UUID]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_(List.empty[List[UUID]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_(List(List.empty[UUID])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uuidSeq.insert(List(
            (1, List(uuid1, uuid2)),
            (2, List(uuid2, uuid3, uuid3))
          )).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uuidSeq_.not(List(uuid1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.not(List(uuid1, uuid2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.uuidSeq_.not(List(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uuidSeq_.not(List(List(uuid1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.not(List(List(uuid1, uuid2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidSeq_.not(List(List(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.uuidSeq_.not(List(uuid1), List(uuid2, uuid3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.not(List(uuid1, uuid2), List(uuid2, uuid3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidSeq_.not(List(uuid1, uuid2), List(uuid2, uuid3, uuid3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuidSeq_.not(List(List(uuid1), List(uuid2, uuid3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.not(List(List(uuid1, uuid2), List(uuid2, uuid3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidSeq_.not(List(List(uuid1, uuid2), List(uuid2, uuid3, uuid3))).query.get.map(_ ==> List())


          // Empty Seq/Seqs
          _ <- Ns.i.a1.uuidSeq_.not(List(List(uuid1, uuid2), List.empty[UUID])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidSeq_.not(List.empty[UUID]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.not(List.empty[List[UUID]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uuidSeq.insert(List(
            (1, List(uuid1, uuid2)),
            (2, List(uuid2, uuid3, uuid3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.uuidSeq_.has(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_.has(uuid1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSeq_.has(uuid2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.has(uuid3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.uuidSeq_.has(List(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_.has(List(uuid1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSeq_.has(List(uuid2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.has(List(uuid3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uuidSeq_.has(uuid1, uuid2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.has(uuid1, uuid3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.has(uuid2, uuid3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.has(uuid1, uuid2, uuid3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uuidSeq_.has(List(uuid1, uuid2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.has(List(uuid1, uuid3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.has(List(uuid2, uuid3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.has(List(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.uuidSeq_.has(List.empty[UUID]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uuidSeq.insert(List(
            (1, List(uuid1, uuid2)),
            (2, List(uuid2, uuid3, uuid3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.uuidSeq_.hasNo(uuid0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.hasNo(uuid1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidSeq_.hasNo(uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_.hasNo(uuid3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSeq_.hasNo(uuid3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSeq_.hasNo(uuid5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uuidSeq_.hasNo(List(uuid0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.hasNo(List(uuid1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidSeq_.hasNo(List(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_.hasNo(List(uuid3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSeq_.hasNo(List(uuid3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSeq_.hasNo(List(uuid5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.uuidSeq_.hasNo(uuid1, uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_.hasNo(uuid1, uuid5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.uuidSeq_.hasNo(List(uuid1, uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_.hasNo(List(uuid1, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_.hasNo(List(uuid1, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_.hasNo(List(uuid1, uuid5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.uuidSeq_.hasNo(List.empty[UUID]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(List(uuid1, uuid2)))
        val b = (2, Some(List(uuid2, uuid3, uuid3)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuidSeq_?.insert(a, b, c).transact

          _ <- Ns.i.a1.uuidSeq_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no uuid value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.uuidSeq_?.query.get.map(_ ==> List(
            (2, Some(List(uuid2, uuid3, uuid3))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(List(uuid1, uuid2)))
        val b = (2, Some(List(uuid2, uuid3, uuid3)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuidSeq_?.insert(a, b, c).transact

          // Exact Seq matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uuidSeq_?(Some(List(uuid1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_?(Some(List(uuid1, uuid2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.uuidSeq_?(Some(List(uuid1, uuid2, uuid3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuidSeq_?(Some(List(List(uuid1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_?(Some(List(List(uuid1, uuid2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq_?(Some(List(List(uuid1, uuid2, uuid3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Seqs

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uuidSeq_?(Some(List(List(uuid1), List(uuid2, uuid3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_?(Some(List(List(uuid1, uuid2), List(uuid2, uuid3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq_?(Some(List(List(uuid1, uuid2), List(uuid2, uuid3, uuid3)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.uuidSeq_?(Some(List.empty[UUID])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_?(Some(List.empty[List[UUID]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_?(Some(List(List.empty[UUID]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.uuidSeq_?(Option.empty[List[UUID]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uuidSeq_?(Option.empty[List[List[UUID]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(List(uuid1, uuid2)))
        val b = (2, Some(List(uuid2, uuid3, uuid3)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuidSeq_?.insert(a, b, c).transact

          // Non-exact Seq matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uuidSeq_?.not(Some(List(uuid1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq_?.not(Some(List(uuid1, uuid2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.uuidSeq_?.not(Some(List(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuidSeq_?.not(Some(List(List(uuid1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq_?.not(Some(List(List(uuid1, uuid2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSeq_?.not(Some(List(List(uuid1, uuid2, uuid3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Seqs

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.uuidSeq_?.not(Some(List(List(uuid1), List(uuid2, uuid3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq_?.not(Some(List(List(uuid1, uuid2), List(uuid2, uuid3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSeq_?.not(Some(List(List(uuid1, uuid2), List(uuid2, uuid3, uuid3)))).query.get.map(_ ==> List())

          // Empty Seqs are ignored
          _ <- Ns.i.a1.uuidSeq_?.not(Some(List(List(uuid1, uuid2), List.empty[UUID]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSeq_?.not(Some(List.empty[UUID])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq_?.not(Some(List.empty[List[UUID]])).query.get.map(_ ==> List(a, b))


          // Negation of None matches all asserted
          _ <- Ns.i.a1.uuidSeq_?.not(Option.empty[List[UUID]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq_?.not(Option.empty[List[List[UUID]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(List(uuid1, uuid2)))
        val b = (2, Some(List(uuid2, uuid3, uuid3)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuidSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.uuidSeq_?.has(Some(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_?.has(Some(uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq_?.has(Some(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq_?.has(Some(uuid3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuidSeq_?.has(Some(List(uuid0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_?.has(Some(List(uuid1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq_?.has(Some(List(uuid2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq_?.has(Some(List(uuid3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uuidSeq_?.has(Some(List(uuid1, uuid2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq_?.has(Some(List(uuid1, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq_?.has(Some(List(uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq_?.has(Some(List(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.uuidSeq_?.has(Some(List.empty[UUID])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.uuidSeq_?.has(Option.empty[UUID]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uuidSeq_?.has(Option.empty[List[UUID]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(uuid1, uuid2)))
        val b = (2, Some(List(uuid2, uuid3, uuid3)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuidSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.uuidSeq_?.hasNo(Some(uuid0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq_?.hasNo(Some(uuid1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSeq_?.hasNo(Some(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_?.hasNo(Some(uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq_?.hasNo(Some(uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq_?.hasNo(Some(uuid5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuidSeq_?.hasNo(Some(List(uuid0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq_?.hasNo(Some(List(uuid1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSeq_?.hasNo(Some(List(uuid2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_?.hasNo(Some(List(uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq_?.hasNo(Some(List(uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq_?.hasNo(Some(List(uuid5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.uuidSeq_?.hasNo(Some(List(uuid1, uuid2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_?.hasNo(Some(List(uuid1, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_?.hasNo(Some(List(uuid1, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_?.hasNo(Some(List(uuid1, uuid5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.uuidSeq_?.hasNo(Some(List.empty[UUID])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.uuidSeq_?.hasNo(Option.empty[UUID]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq_?.hasNo(Option.empty[List[UUID]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}