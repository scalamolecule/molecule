// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.time.Duration
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_Duration_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(duration1, duration2))
        val b = (2, Set(duration2, duration3, duration4))
        for {
          _ <- Ns.i.durationSet.insert(List(a, b)).transact

          _ <- Ns.i.a1.durationSet.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(duration1, duration2))
        val b = (2, Set(duration2, duration3, duration4))
        for {
          _ <- Ns.i.durationSet.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.durationSet(Set(duration1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet(Set(duration1, duration2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.durationSet(Set(duration1, duration2, duration3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.durationSet(Seq(Set(duration1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet(Seq(Set(duration1, duration2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet(Seq(Set(duration1, duration2, duration3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.durationSet(Set(duration1), Set(duration2, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet(Set(duration1, duration2), Set(duration2, duration3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet(Set(duration1, duration2), Set(duration2, duration3, duration4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durationSet(Seq(Set(duration1), Set(duration2, duration3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet(Seq(Set(duration1, duration2), Set(duration2, duration3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet(Seq(Set(duration1, duration2), Set(duration2, duration3, duration4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.durationSet(Set(duration1, duration2), Set.empty[Duration]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet(Set.empty[Duration], Set(duration1, duration2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet(Set.empty[Duration], Set.empty[Duration]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet(Set.empty[Duration]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet(Seq.empty[Set[Duration]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet(Seq(Set.empty[Duration])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.durationSet().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(duration1, duration2))
        val b = (2, Set(duration2, duration3, duration4))
        for {
          _ <- Ns.i.durationSet.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.durationSet.not(Set(duration1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.not(Set(duration1, duration2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.durationSet.not(Set(duration1, duration2, duration3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durationSet.not(Seq(Set(duration1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.not(Seq(Set(duration1, duration2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSet.not(Seq(Set(duration1, duration2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSet.not(Seq(Set(duration1, duration2, duration3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.durationSet.not(Set(duration1), Set(duration2, duration3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.not(Set(duration1, duration2), Set(duration2, duration3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSet.not(Set(duration1, duration2), Set(duration2, duration3, duration4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.durationSet.not(Seq(Set(duration1), Set(duration2, duration3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.not(Seq(Set(duration1, duration2), Set(duration2, duration3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSet.not(Seq(Set(duration1, duration2), Set(duration2, duration3, duration4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.durationSet.not(Seq(Set(duration1, duration2), Set.empty[Duration])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSet.not(Set.empty[Duration]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.not(Seq.empty[Set[Duration]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(duration1, duration2))
        val b = (2, Set(duration2, duration3, duration4))
        for {
          _ <- Ns.i.durationSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.durationSet.has(duration0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet.has(duration1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet.has(duration2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.has(duration3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.durationSet.has(Seq(duration0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet.has(Seq(duration1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet.has(Seq(duration2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.has(Seq(duration3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.durationSet.has(duration1, duration2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.has(duration1, duration3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.has(duration2, duration3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.has(duration1, duration2, duration3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durationSet.has(Seq(duration1, duration2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.has(Seq(duration1, duration3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.has(Seq(duration2, duration3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.has(Seq(duration1, duration2, duration3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.durationSet.has(Seq.empty[Duration]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(duration1, duration2))
        val b = (2, Set(duration2, duration3, duration4))
        for {
          _ <- Ns.i.durationSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.durationSet.hasNo(duration0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.hasNo(duration1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSet.hasNo(duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet.hasNo(duration3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet.hasNo(duration4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet.hasNo(duration5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durationSet.hasNo(Seq(duration0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet.hasNo(Seq(duration1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSet.hasNo(Seq(duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet.hasNo(Seq(duration3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet.hasNo(Seq(duration4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet.hasNo(Seq(duration5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.durationSet.hasNo(duration1, duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet.hasNo(duration1, duration3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet.hasNo(duration1, duration4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet.hasNo(duration1, duration5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.durationSet.hasNo(Seq(duration1, duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet.hasNo(Seq(duration1, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet.hasNo(Seq(duration1, duration4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet.hasNo(Seq(duration1, duration5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.durationSet.hasNo(Seq.empty[Duration]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.durationSet.insert(List(
            (1, Set(duration1, duration2)),
            (2, Set(duration2, duration3, duration4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // durationSet not asserted for i = 0
          _ <- Ns.i.a1.durationSet_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.durationSet_?.insert(List(
            (0, None),
            (1, Some(Set(duration1, duration2))),
            (2, Some(Set(duration2, duration3, duration4))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.durationSet_().query.get.map(_ ==> List(0))

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.durationSet_(Set(duration1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_(Set(duration1, duration2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.durationSet_(Set(duration1, duration2, duration3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.durationSet_(Seq(Set(duration1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_(Seq(Set(duration1, duration2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSet_(Seq(Set(duration1, duration2, duration3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.durationSet_(Set(duration1), Set(duration2, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_(Set(duration1, duration2), Set(duration2, duration3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSet_(Set(duration1, duration2), Set(duration2, duration3, duration4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.durationSet_(Seq(Set(duration1), Set(duration2, duration3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_(Seq(Set(duration1, duration2), Set(duration2, duration3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSet_(Seq(Set(duration1, duration2), Set(duration2, duration3, duration4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.durationSet_(Set(duration1, duration2), Set.empty[Duration]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSet_(Set.empty[Duration]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_(Seq.empty[Set[Duration]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_(Seq(Set.empty[Duration])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.durationSet.insert(List(
            (1, Set(duration1, duration2)),
            (2, Set(duration2, duration3, duration4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.durationSet_.not(Set(duration1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.not(Set(duration1, duration2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.durationSet_.not(Set(duration1, duration2, duration3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.durationSet_.not(Seq(Set(duration1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.not(Seq(Set(duration1, duration2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationSet_.not(Seq(Set(duration1, duration2, duration3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.durationSet_.not(Set(duration1), Set(duration2, duration3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.not(Set(duration1, duration2), Set(duration2, duration3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationSet_.not(Set(duration1, duration2), Set(duration2, duration3, duration4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.durationSet_.not(Seq(Set(duration1), Set(duration2, duration3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.not(Seq(Set(duration1, duration2), Set(duration2, duration3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationSet_.not(Seq(Set(duration1, duration2), Set(duration2, duration3, duration4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.durationSet_.not(Seq(Set(duration1, duration2), Set.empty[Duration])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationSet_.not(Set.empty[Duration]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.not(Seq.empty[Set[Duration]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.durationSet.insert(List(
            (1, Set(duration1, duration2)),
            (2, Set(duration2, duration3, duration4))
          )).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.durationSet_.has(duration0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_.has(duration1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSet_.has(duration2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.has(duration3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.durationSet_.has(Seq(duration0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_.has(Seq(duration1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSet_.has(Seq(duration2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.has(Seq(duration3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.durationSet_.has(duration1, duration2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.has(duration1, duration3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.has(duration2, duration3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.has(duration1, duration2, duration3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.durationSet_.has(Seq(duration1, duration2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.has(Seq(duration1, duration3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.has(Seq(duration2, duration3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.has(Seq(duration1, duration2, duration3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.durationSet_.has(Seq.empty[Duration]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.durationSet.insert(List(
            (1, Set(duration1, duration2)),
            (2, Set(duration2, duration3, duration4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.durationSet_.hasNo(duration0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.hasNo(duration1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationSet_.hasNo(duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_.hasNo(duration3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSet_.hasNo(duration4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSet_.hasNo(duration5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.durationSet_.hasNo(Seq(duration0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durationSet_.hasNo(Seq(duration1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durationSet_.hasNo(Seq(duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_.hasNo(Seq(duration3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSet_.hasNo(Seq(duration4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durationSet_.hasNo(Seq(duration5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.durationSet_.hasNo(duration1, duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_.hasNo(duration1, duration3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_.hasNo(duration1, duration4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_.hasNo(duration1, duration5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.durationSet_.hasNo(Seq(duration1, duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_.hasNo(Seq(duration1, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_.hasNo(Seq(duration1, duration4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_.hasNo(Seq(duration1, duration5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.durationSet_.hasNo(Seq.empty[Duration]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(duration1, duration2)))
        val b = (2, Some(Set(duration2, duration3, duration4)))
        val c = (3, None)
        for {
          _ <- Ns.i.durationSet_?.insert(a, b, c).transact

          _ <- Ns.i.a1.durationSet_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no duration value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.durationSet_?.query.get.map(_ ==> List(
            (2, Some(Set(duration2, duration3, duration4))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(duration1, duration2)))
        val b = (2, Some(Set(duration2, duration3, duration4)))
        val c = (3, None)
        for {
          _ <- Ns.i.durationSet_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.durationSet_?(Some(Set(duration1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_?(Some(Set(duration1, duration2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.durationSet_?(Some(Set(duration1, duration2, duration3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.durationSet_?(Some(Seq(Set(duration1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_?(Some(Seq(Set(duration1, duration2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet_?(Some(Seq(Set(duration1, duration2, duration3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.durationSet_?(Some(Seq(Set(duration1), Set(duration2, duration3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_?.apply(Some(Seq(Set(duration1, duration2), Set(duration2, duration3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet_?(Some(Seq(Set(duration1, duration2), Set(duration2, duration3, duration4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.durationSet_?(Some(Set.empty[Duration])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_?(Some(Seq.empty[Set[Duration]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_?(Some(Seq(Set.empty[Duration]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.durationSet_?(Option.empty[Set[Duration]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.durationSet_?(Option.empty[Seq[Set[Duration]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(duration1, duration2)))
        val b = (2, Some(Set(duration2, duration3, duration4)))
        val c = (3, None)
        for {
          _ <- Ns.i.durationSet_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.durationSet_?.not(Some(Set(duration1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet_?.not(Some(Set(duration1, duration2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.durationSet_?.not(Some(Set(duration1, duration2, duration3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durationSet_?.not(Some(Seq(Set(duration1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet_?.not(Some(Seq(Set(duration1, duration2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSet_?.not(Some(Seq(Set(duration1, duration2, duration3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.durationSet_?.not(Some(Seq(Set(duration1), Set(duration2, duration3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet_?.not(Some(Seq(Set(duration1, duration2), Set(duration2, duration3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSet_?.not(Some(Seq(Set(duration1, duration2), Set(duration2, duration3, duration4)))).query.get.map(_ ==> List())

          // Empty Sets are ignored
          _ <- Ns.i.a1.durationSet_?.not(Some(Seq(Set(duration1, duration2), Set.empty[Duration]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSet_?.not(Some(Set.empty[Duration])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet_?.not(Some(Seq.empty[Set[Duration]])).query.get.map(_ ==> List(a, b))

          // Negation of None matches all asserted
          _ <- Ns.i.a1.durationSet_?.not(Option.empty[Set[Duration]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet_?.not(Option.empty[Seq[Set[Duration]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(duration1, duration2)))
        val b = (2, Some(Set(duration2, duration3, duration4)))
        val c = (3, None)
        for {
          _ <- Ns.i.durationSet_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.durationSet_?.has(Some(duration0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_?.has(Some(duration1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet_?.has(Some(duration2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet_?.has(Some(duration3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.durationSet_?.has(Some(Seq(duration0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_?.has(Some(Seq(duration1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet_?.has(Some(Seq(duration2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet_?.has(Some(Seq(duration3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.durationSet_?.has(Some(Seq(duration1, duration2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet_?.has(Some(Seq(duration1, duration3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet_?.has(Some(Seq(duration2, duration3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet_?.has(Some(Seq(duration1, duration2, duration3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.durationSet_?.has(Some(Seq.empty[Duration])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.durationSet_?.has(Option.empty[Duration]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.durationSet_?.has(Option.empty[Seq[Duration]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(duration1, duration2)))
        val b = (2, Some(Set(duration2, duration3, duration4)))
        val c = (3, None)
        for {
          _ <- Ns.i.durationSet_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.durationSet_?.hasNo(Some(duration0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet_?.hasNo(Some(duration1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSet_?.hasNo(Some(duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_?.hasNo(Some(duration3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet_?.hasNo(Some(duration4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet_?.hasNo(Some(duration5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durationSet_?.hasNo(Some(Seq(duration0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet_?.hasNo(Some(Seq(duration1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durationSet_?.hasNo(Some(Seq(duration2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_?.hasNo(Some(Seq(duration3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet_?.hasNo(Some(Seq(duration4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durationSet_?.hasNo(Some(Seq(duration5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.durationSet_?.hasNo(Some(Seq(duration1, duration2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_?.hasNo(Some(Seq(duration1, duration3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_?.hasNo(Some(Seq(duration1, duration4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durationSet_?.hasNo(Some(Seq(duration1, duration5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.durationSet_?.hasNo(Some(Seq.empty[Duration])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.durationSet_?.hasNo(Option.empty[Duration]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durationSet_?.hasNo(Option.empty[Seq[Duration]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}