// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.util.UUID
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_UUID_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        for {
          _ <- Ns.i.uuidSet.insert(List(a, b)).transact

          _ <- Ns.i.a1.uuidSet.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        for {
          _ <- Ns.i.uuidSet.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uuidSet(Set(uuid1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet(Set(uuid1, uuid2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.uuidSet(Set(uuid1, uuid2, uuid3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuidSet(Seq(Set(uuid1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet(Seq(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uuidSet(Set(uuid1), Set(uuid2, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuidSet(Seq(Set(uuid1), Set(uuid2, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuidSet(Set(uuid1, uuid2), Set.empty[UUID]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet(Set.empty[UUID], Set(uuid1, uuid2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet(Set.empty[UUID], Set.empty[UUID]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet(Set.empty[UUID]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet(Seq.empty[Set[UUID]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet(Seq(Set.empty[UUID])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.uuidSet().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        for {
          _ <- Ns.i.uuidSet.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uuidSet.not(Set(uuid1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.not(Set(uuid1, uuid2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.uuidSet.not(Set(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuidSet.not(Seq(Set(uuid1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.not(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSet.not(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSet.not(Seq(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.uuidSet.not(Set(uuid1), Set(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.not(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSet.not(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuidSet.not(Seq(Set(uuid1), Set(uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.not(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSet.not(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.uuidSet.not(Seq(Set(uuid1, uuid2), Set.empty[UUID])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSet.not(Set.empty[UUID]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.not(Seq.empty[Set[UUID]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        for {
          _ <- Ns.i.uuidSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.uuidSet.has(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet.has(uuid1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet.has(uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.has(uuid3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuidSet.has(Seq(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet.has(Seq(uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet.has(Seq(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.has(Seq(uuid3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uuidSet.has(uuid1, uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.has(uuid1, uuid3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.has(uuid2, uuid3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.has(uuid1, uuid2, uuid3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuidSet.has(Seq(uuid1, uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.has(Seq(uuid1, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.has(Seq(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.has(Seq(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuidSet.has(Seq.empty[UUID]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        for {
          _ <- Ns.i.uuidSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.uuidSet.hasNo(uuid0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.hasNo(uuid1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSet.hasNo(uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet.hasNo(uuid3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet.hasNo(uuid4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet.hasNo(uuid5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuidSet.hasNo(Seq(uuid0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.hasNo(Seq(uuid1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSet.hasNo(Seq(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet.hasNo(Seq(uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet.hasNo(Seq(uuid4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet.hasNo(Seq(uuid5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.uuidSet.hasNo(uuid1, uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet.hasNo(uuid1, uuid4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet.hasNo(uuid1, uuid5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuidSet.hasNo(Seq(uuid1, uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet.hasNo(Seq(uuid1, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet.hasNo(Seq(uuid1, uuid4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet.hasNo(Seq(uuid1, uuid5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.uuidSet.hasNo(Seq.empty[UUID]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uuidSet.insert(List(
            (1, Set(uuid1, uuid2)),
            (2, Set(uuid2, uuid3, uuid4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // uuidSet not asserted for i = 0
          _ <- Ns.i.a1.uuidSet_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.uuidSet_?.insert(List(
            (0, None),
            (1, Some(Set(uuid1, uuid2))),
            (2, Some(Set(uuid2, uuid3, uuid4))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.uuidSet_().query.get.map(_ ==> List(0))

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uuidSet_(Set(uuid1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_(Set(uuid1, uuid2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.uuidSet_(Set(uuid1, uuid2, uuid3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuidSet_(Seq(Set(uuid1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSet_(Seq(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uuidSet_(Set(uuid1), Set(uuid2, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSet_(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uuidSet_(Seq(Set(uuid1), Set(uuid2, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSet_(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuidSet_(Set(uuid1, uuid2), Set.empty[UUID]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSet_(Set.empty[UUID]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_(Seq.empty[Set[UUID]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_(Seq(Set.empty[UUID])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uuidSet.insert(List(
            (1, Set(uuid1, uuid2)),
            (2, Set(uuid2, uuid3, uuid4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uuidSet_.not(Set(uuid1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.not(Set(uuid1, uuid2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.uuidSet_.not(Set(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uuidSet_.not(Seq(Set(uuid1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.not(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidSet_.not(Seq(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.uuidSet_.not(Set(uuid1), Set(uuid2, uuid3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.not(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidSet_.not(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuidSet_.not(Seq(Set(uuid1), Set(uuid2, uuid3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.not(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidSet_.not(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.uuidSet_.not(Seq(Set(uuid1, uuid2), Set.empty[UUID])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidSet_.not(Set.empty[UUID]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.not(Seq.empty[Set[UUID]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uuidSet.insert(List(
            (1, Set(uuid1, uuid2)),
            (2, Set(uuid2, uuid3, uuid4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.uuidSet_.has(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_.has(uuid1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSet_.has(uuid2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.has(uuid3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.uuidSet_.has(Seq(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_.has(Seq(uuid1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSet_.has(Seq(uuid2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.has(Seq(uuid3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uuidSet_.has(uuid1, uuid2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.has(uuid1, uuid3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.has(uuid2, uuid3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.has(uuid1, uuid2, uuid3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uuidSet_.has(Seq(uuid1, uuid2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.has(Seq(uuid1, uuid3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.has(Seq(uuid2, uuid3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.has(Seq(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuidSet_.has(Seq.empty[UUID]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uuidSet.insert(List(
            (1, Set(uuid1, uuid2)),
            (2, Set(uuid2, uuid3, uuid4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.uuidSet_.hasNo(uuid0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.hasNo(uuid1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidSet_.hasNo(uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_.hasNo(uuid3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSet_.hasNo(uuid4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSet_.hasNo(uuid5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq(uuid0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq(uuid1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq(uuid3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq(uuid4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq(uuid5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.uuidSet_.hasNo(uuid1, uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_.hasNo(uuid1, uuid4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_.hasNo(uuid1, uuid5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq(uuid1, uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq(uuid1, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq(uuid1, uuid4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq(uuid1, uuid5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq.empty[UUID]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuidSet_?.insert(a, b, c).transact

          _ <- Ns.i.a1.uuidSet_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no uuid value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.uuidSet_?.query.get.map(_ ==> List(
            (2, Some(Set(uuid2, uuid3, uuid4))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuidSet_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uuidSet_?(Some(Set(uuid1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_?(Some(Set(uuid1, uuid2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.uuidSet_?(Some(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuidSet_?(Some(Seq(Set(uuid1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_?(Some(Seq(Set(uuid1, uuid2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet_?(Some(Seq(Set(uuid1, uuid2, uuid3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uuidSet_?(Some(Seq(Set(uuid1), Set(uuid2, uuid3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_?(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet_?(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuidSet_?(Some(Set.empty[UUID])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_?(Some(Seq.empty[Set[UUID]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_?(Some(Seq(Set.empty[UUID]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.uuidSet_?(Option.empty[Set[UUID]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uuidSet_?(Option.empty[Seq[Set[UUID]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuidSet_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uuidSet_?.not(Some(Set(uuid1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.not(Some(Set(uuid1, uuid2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.uuidSet_?.not(Some(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuidSet_?.not(Some(Seq(Set(uuid1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.not(Some(Seq(Set(uuid1, uuid2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSet_?.not(Some(Seq(Set(uuid1, uuid2, uuid3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.uuidSet_?.not(Some(Seq(Set(uuid1), Set(uuid2, uuid3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.not(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSet_?.not(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)))).query.get.map(_ ==> List())

          // Empty Sets are ignored
          _ <- Ns.i.a1.uuidSet_?.not(Some(Seq(Set(uuid1, uuid2), Set.empty[UUID]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSet_?.not(Some(Set.empty[UUID])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.not(Some(Seq.empty[Set[UUID]])).query.get.map(_ ==> List(a, b))

          // Negation of None matches all asserted
          _ <- Ns.i.a1.uuidSet_?.not(Option.empty[Set[UUID]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.not(Option.empty[Seq[Set[UUID]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuidSet_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.uuidSet_?.has(Some(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_?.has(Some(uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet_?.has(Some(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.has(Some(uuid3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuidSet_?.has(Some(Seq(uuid0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_?.has(Some(Seq(uuid1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet_?.has(Some(Seq(uuid2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.has(Some(Seq(uuid3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uuidSet_?.has(Some(Seq(uuid1, uuid2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.has(Some(Seq(uuid1, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.has(Some(Seq(uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.has(Some(Seq(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuidSet_?.has(Some(Seq.empty[UUID])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.uuidSet_?.has(Option.empty[UUID]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uuidSet_?.has(Option.empty[Seq[UUID]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuidSet_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(uuid0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(uuid1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(uuid4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(uuid5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq(uuid0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq(uuid1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq(uuid2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq(uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq(uuid4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq(uuid5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq(uuid1, uuid2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq(uuid1, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq(uuid1, uuid4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq(uuid1, uuid5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq.empty[UUID])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.uuidSet_?.hasNo(Option.empty[UUID]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.hasNo(Option.empty[Seq[UUID]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}