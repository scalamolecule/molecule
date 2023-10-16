// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set

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
          _ <- Ns.i.durations.insert(List(a, b)).transact

          _ <- Ns.i.a1.durations.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(duration1, duration2))
        val b = (2, Set(duration2, duration3, duration4))
        for {
          _ <- Ns.i.durations.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.durations(Set(duration1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations(Set(duration1, duration2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.durations(Set(duration2, duration1)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.durations(Set(duration1, duration2, duration3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.durations(Seq(Set(duration1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations(Seq(Set(duration1, duration2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations(Seq(Set(duration2, duration1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations(Seq(Set(duration1, duration2, duration3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.durations(Set(duration1), Set(duration2, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations(Set(duration1, duration2), Set(duration2, duration3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations(Set(duration2, duration1), Set(duration4, duration3, duration2)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durations(Seq(Set(duration1), Set(duration2, duration3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations(Seq(Set(duration1, duration2), Set(duration2, duration3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations(Seq(Set(duration2, duration1), Set(duration4, duration3, duration2))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.durations(Set(duration1, duration2), Set.empty[Duration]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations(Set.empty[Duration], Set(duration2, duration1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations(Set.empty[Duration], Set.empty[Duration]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations(Set.empty[Duration]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations(Seq.empty[Set[Duration]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations(Seq(Set.empty[Duration])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(duration1, duration2))
        val b = (2, Set(duration2, duration3, duration4))
        for {
          _ <- Ns.i.durations.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.durations.not(Set(duration1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.not(Set(duration1, duration2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.durations.not(Set(duration2, duration1)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.durations.not(Set(duration1, duration2, duration3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durations.not(Seq(Set(duration1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.not(Seq(Set(duration1, duration2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations.not(Seq(Set(duration2, duration1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations.not(Seq(Set(duration1, duration2, duration3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.durations.not(Set(duration1), Set(duration2, duration3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.not(Set(duration1, duration2), Set(duration2, duration3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations.not(Set(duration2, duration1), Set(duration4, duration3, duration2)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.durations.not(Seq(Set(duration1), Set(duration2, duration3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.not(Seq(Set(duration1, duration2), Set(duration2, duration3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations.not(Seq(Set(duration2, duration1), Set(duration4, duration3, duration2))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.durations.not(Seq(Set(duration1, duration2), Set.empty[Duration])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations.not(Set.empty[Duration]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.not(Seq.empty[Set[Duration]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(duration1, duration2))
        val b = (2, Set(duration2, duration3, duration4))
        for {
          _ <- Ns.i.durations.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.durations.has(duration0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations.has(duration1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations.has(duration2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.has(duration3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.durations.has(Seq(duration0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations.has(Seq(duration1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations.has(Seq(duration2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.has(Seq(duration3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.durations.has(duration1, duration2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.has(duration1, duration3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.has(duration2, duration3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.has(duration1, duration2, duration3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durations.has(Seq(duration1, duration2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.has(Seq(duration1, duration3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.has(Seq(duration2, duration3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.has(Seq(duration1, duration2, duration3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.durations.has(Set(duration1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations.has(Set(duration1, duration2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations.has(Set(duration1, duration2, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations.has(Set(duration2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.has(Set(duration2, duration3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations.has(Set(duration2, duration3, duration4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.durations.has(Seq(Set(duration1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations.has(Seq(Set(duration1, duration2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations.has(Seq(Set(duration1, duration2, duration3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations.has(Seq(Set(duration2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.has(Seq(Set(duration2, duration3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations.has(Seq(Set(duration2, duration3, duration4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.durations.has(Set(duration1, duration2), Set(duration0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations.has(Set(duration1, duration2), Set(duration0, duration3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations.has(Set(duration1, duration2), Set(duration2, duration3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.has(Set(duration1, duration2), Set(duration2, duration3, duration4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durations.has(Seq(Set(duration1, duration2), Set(duration0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations.has(Seq(Set(duration1, duration2), Set(duration0, duration3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations.has(Seq(Set(duration1, duration2), Set(duration2, duration3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.has(Seq(Set(duration1, duration2), Set(duration2, duration3, duration4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.durations.has(Set(duration1, duration2), Set.empty[Duration]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations.has(Seq.empty[Duration]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations.has(Set.empty[Duration]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations.has(Seq.empty[Set[Duration]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(duration1, duration2))
        val b = (2, Set(duration2, duration3, duration4))
        for {
          _ <- Ns.i.durations.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.durations.hasNo(duration0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.hasNo(duration1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations.hasNo(duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations.hasNo(duration3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations.hasNo(duration4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations.hasNo(duration5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durations.hasNo(Seq(duration0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.hasNo(Seq(duration1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations.hasNo(Seq(duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations.hasNo(Seq(duration3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations.hasNo(Seq(duration4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations.hasNo(Seq(duration5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.durations.hasNo(duration1, duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations.hasNo(duration1, duration3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations.hasNo(duration1, duration4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations.hasNo(duration1, duration5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.durations.hasNo(Seq(duration1, duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations.hasNo(Seq(duration1, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations.hasNo(Seq(duration1, duration4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations.hasNo(Seq(duration1, duration5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.durations.hasNo(Set(duration1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations.hasNo(Set(duration1, duration2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations.hasNo(Set(duration1, duration2, duration3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.hasNo(Set(duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations.hasNo(Set(duration2, duration3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations.hasNo(Set(duration2, duration3, duration4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.durations.hasNo(Seq(Set(duration1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations.hasNo(Seq(Set(duration1, duration2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations.hasNo(Seq(Set(duration1, duration2, duration3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.hasNo(Seq(Set(duration2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations.hasNo(Seq(Set(duration2, duration3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations.hasNo(Seq(Set(duration2, duration3, duration4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.durations.hasNo(Set(duration1, duration2), Set(duration0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations.hasNo(Set(duration1, duration2), Set(duration0, duration3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations.hasNo(Set(duration1, duration2), Set(duration2, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations.hasNo(Set(duration1, duration2), Set(duration2, duration3, duration4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.durations.hasNo(Seq(Set(duration1, duration2), Set(duration0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations.hasNo(Seq(Set(duration1, duration2), Set(duration0, duration3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations.hasNo(Seq(Set(duration1, duration2), Set(duration2, duration3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations.hasNo(Seq(Set(duration1, duration2), Set(duration2, duration3, duration4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.durations.hasNo(Set(duration1, duration2), Set.empty[Duration]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations.hasNo(Seq.empty[Duration]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.hasNo(Set.empty[Duration]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.hasNo(Seq.empty[Set[Duration]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations.hasNo(Seq(Set.empty[Duration])).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.durations.insert(List(
            (1, Set(duration1, duration2)),
            (2, Set(duration2, duration3, duration4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))
          _ <- Ns.i.a1.durations_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.durations_?.insert(List(
            (0, None),
            (1, Some(Set(duration1, duration2))),
            (2, Some(Set(duration2, duration3, duration4))),
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.durations_(Set(duration1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_(Set(duration1, duration2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.durations_(Set(duration2, duration1)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.durations_(Set(duration1, duration2, duration3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.durations_(Seq(Set(duration1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_(Seq(Set(duration1, duration2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_(Seq(Set(duration2, duration1))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_(Seq(Set(duration1, duration2, duration3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.durations_(Set(duration1), Set(duration2, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_(Set(duration1, duration2), Set(duration2, duration3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_(Set(duration2, duration1), Set(duration4, duration3, duration2)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.durations_(Seq(Set(duration1), Set(duration2, duration3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_(Seq(Set(duration1, duration2), Set(duration2, duration3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_(Seq(Set(duration2, duration1), Set(duration4, duration3, duration2))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.durations_(Set(duration1, duration2), Set.empty[Duration]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_(Set.empty[Duration]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_(Seq.empty[Set[Duration]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_(Seq(Set.empty[Duration])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.durations.insert(List(
            (1, Set(duration1, duration2)),
            (2, Set(duration2, duration3, duration4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.durations_.not(Set(duration1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.not(Set(duration1, duration2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.durations_.not(Set(duration2, duration1)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.durations_.not(Set(duration1, duration2, duration3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.durations_.not(Seq(Set(duration1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.not(Seq(Set(duration1, duration2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durations_.not(Seq(Set(duration2, duration1))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durations_.not(Seq(Set(duration1, duration2, duration3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.durations_.not(Set(duration1), Set(duration2, duration3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.not(Set(duration1, duration2), Set(duration2, duration3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durations_.not(Set(duration2, duration1), Set(duration4, duration3, duration2)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.durations_.not(Seq(Set(duration1), Set(duration2, duration3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.not(Seq(Set(duration1, duration2), Set(duration2, duration3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durations_.not(Seq(Set(duration2, duration1), Set(duration4, duration3, duration2))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.durations_.not(Seq(Set(duration1, duration2), Set.empty[Duration])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durations_.not(Set.empty[Duration]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.not(Seq.empty[Set[Duration]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.durations.insert(List(
            (1, Set(duration1, duration2)),
            (2, Set(duration2, duration3, duration4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.durations_.has(duration0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_.has(duration1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_.has(duration2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.has(duration3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.durations_.has(Seq(duration0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_.has(Seq(duration1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_.has(Seq(duration2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.has(Seq(duration3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.durations_.has(duration1, duration2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.has(duration1, duration3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.has(duration2, duration3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.has(duration1, duration2, duration3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.durations_.has(Seq(duration1, duration2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.has(Seq(duration1, duration3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.has(Seq(duration2, duration3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.has(Seq(duration1, duration2, duration3)).query.get.map(_ ==> List(1, 2))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.durations_.has(Set(duration1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_.has(Set(duration1, duration2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_.has(Set(duration1, duration2, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_.has(Set(duration2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.has(Set(duration2, duration3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durations_.has(Set(duration2, duration3, duration4)).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.durations_.has(Seq(Set(duration1))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_.has(Seq(Set(duration1, duration2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_.has(Seq(Set(duration1, duration2, duration3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_.has(Seq(Set(duration2))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.has(Seq(Set(duration2, duration3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durations_.has(Seq(Set(duration2, duration3, duration4))).query.get.map(_ ==> List(2))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.durations_.has(Set(duration1, duration2), Set(duration0)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_.has(Set(duration1, duration2), Set(duration0, duration3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_.has(Set(duration1, duration2), Set(duration2, duration3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.has(Set(duration1, duration2), Set(duration2, duration3, duration4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.durations_.has(Seq(Set(duration1, duration2), Set(duration0))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_.has(Seq(Set(duration1, duration2), Set(duration0, duration3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_.has(Seq(Set(duration1, duration2), Set(duration2, duration3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.has(Seq(Set(duration1, duration2), Set(duration2, duration3, duration4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.durations_.has(Set(duration1, duration2), Set.empty[Duration]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_.has(Seq.empty[Duration]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_.has(Set.empty[Duration]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_.has(Seq.empty[Set[Duration]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.durations.insert(List(
            (1, Set(duration1, duration2)),
            (2, Set(duration2, duration3, duration4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.durations_.hasNo(duration0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.hasNo(duration1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durations_.hasNo(duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_.hasNo(duration3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_.hasNo(duration4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_.hasNo(duration5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.durations_.hasNo(Seq(duration0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.hasNo(Seq(duration1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durations_.hasNo(Seq(duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_.hasNo(Seq(duration3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_.hasNo(Seq(duration4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_.hasNo(Seq(duration5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.durations_.hasNo(duration1, duration2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_.hasNo(duration1, duration3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_.hasNo(duration1, duration4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_.hasNo(duration1, duration5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.durations_.hasNo(Seq(duration1, duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_.hasNo(Seq(duration1, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_.hasNo(Seq(duration1, duration4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_.hasNo(Seq(duration1, duration5)).query.get.map(_ ==> List(2))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.durations_.hasNo(Set(duration1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durations_.hasNo(Set(duration1, duration2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durations_.hasNo(Set(duration1, duration2, duration3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.hasNo(Set(duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_.hasNo(Set(duration2, duration3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_.hasNo(Set(duration2, duration3, duration4)).query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.durations_.hasNo(Seq(Set(duration1))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durations_.hasNo(Seq(Set(duration1, duration2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durations_.hasNo(Seq(Set(duration1, duration2, duration3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.hasNo(Seq(Set(duration2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_.hasNo(Seq(Set(duration2, duration3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.durations_.hasNo(Seq(Set(duration2, duration3, duration4))).query.get.map(_ ==> List(1))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.durations_.hasNo(Set(duration1, duration2), Set(duration0)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durations_.hasNo(Set(duration1, duration2), Set(duration0, duration3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durations_.hasNo(Set(duration1, duration2), Set(duration2, duration3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_.hasNo(Set(duration1, duration2), Set(duration2, duration3, duration4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.durations_.hasNo(Seq(Set(duration1, duration2), Set(duration0))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durations_.hasNo(Seq(Set(duration1, duration2), Set(duration0, duration3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durations_.hasNo(Seq(Set(duration1, duration2), Set(duration2, duration3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_.hasNo(Seq(Set(duration1, duration2), Set(duration2, duration3, duration4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.durations_.hasNo(Set(duration1, duration2), Set.empty[Duration]).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.durations_.hasNo(Seq.empty[Duration]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.hasNo(Set.empty[Duration]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.hasNo(Seq.empty[Set[Duration]]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.durations_.hasNo(Seq(Set.empty[Duration])).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(duration1, duration2)))
        val b = (2, Some(Set(duration2, duration3, duration4)))
        val c = (3, None)
        for {
          _ <- Ns.i.durations_?.insert(a, b, c).transact

          _ <- Ns.i.a1.durations_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no duration value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.durations_?.query.get.map(_ ==> List(
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
          _ <- Ns.i.durations_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.durations_?(Some(Set(duration1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_?(Some(Set(duration1, duration2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.durations_?(Some(Set(duration2, duration1))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.durations_?(Some(Set(duration1, duration2, duration3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.durations_?(Some(Seq(Set(duration1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_?(Some(Seq(Set(duration1, duration2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations_?(Some(Seq(Set(duration2, duration1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations_?(Some(Seq(Set(duration1, duration2, duration3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.durations_?(Some(Seq(Set(duration1), Set(duration2, duration3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_?(Some(Seq(Set(duration1, duration2), Set(duration2, duration3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations_?(Some(Seq(Set(duration2, duration1), Set(duration4, duration3, duration2)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.durations_?(Some(Set.empty[Duration])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_?(Some(Seq.empty[Set[Duration]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_?(Some(Seq(Set.empty[Duration]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.durations_?(Option.empty[Set[Duration]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.durations_?(Option.empty[Seq[Set[Duration]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(duration1, duration2)))
        val b = (2, Some(Set(duration2, duration3, duration4)))
        val c = (3, None)
        for {
          _ <- Ns.i.durations_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.durations_?.not(Some(Set(duration1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.not(Some(Set(duration1, duration2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.durations_?.not(Some(Set(duration2, duration1))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.durations_?.not(Some(Set(duration1, duration2, duration3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durations_?.not(Some(Seq(Set(duration1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.not(Some(Seq(Set(duration1, duration2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations_?.not(Some(Seq(Set(duration2, duration1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations_?.not(Some(Seq(Set(duration1, duration2, duration3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.durations_?.not(Some(Seq(Set(duration1), Set(duration2, duration3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.not(Some(Seq(Set(duration1, duration2), Set(duration2, duration3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations_?.not(Some(Seq(Set(duration2, duration1), Set(duration4, duration3, duration2)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.durations_?.not(Some(Seq(Set(duration1, duration2), Set.empty[Duration]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations_?.not(Some(Set.empty[Duration])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.not(Some(Seq.empty[Set[Duration]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.durations_?(Option.empty[Set[Duration]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.durations_?(Option.empty[Seq[Set[Duration]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(duration1, duration2)))
        val b = (2, Some(Set(duration2, duration3, duration4)))
        val c = (3, None)
        for {
          _ <- Ns.i.durations_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.durations_?.has(Some(duration0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_?.has(Some(duration1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations_?.has(Some(duration2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.has(Some(duration3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.durations_?.has(Some(Seq(duration0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_?.has(Some(Seq(duration1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations_?.has(Some(Seq(duration2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.has(Some(Seq(duration3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.durations_?.has(Some(Seq(duration1, duration2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.has(Some(Seq(duration1, duration3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.has(Some(Seq(duration2, duration3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.has(Some(Seq(duration1, duration2, duration3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.durations_?.has(Some(Set(duration1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations_?.has(Some(Set(duration1, duration2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations_?.has(Some(Set(duration1, duration2, duration3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_?.has(Some(Set(duration2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.has(Some(Set(duration2, duration3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations_?.has(Some(Set(duration2, duration3, duration4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.durations_?.has(Some(Seq(Set(duration1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations_?.has(Some(Seq(Set(duration1, duration2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations_?.has(Some(Seq(Set(duration1, duration2, duration3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_?.has(Some(Seq(Set(duration2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.has(Some(Seq(Set(duration2, duration3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations_?.has(Some(Seq(Set(duration2, duration3, duration4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.durations_?.has(Some(Seq(Set(duration1, duration2), Set(duration0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations_?.has(Some(Seq(Set(duration1, duration2), Set(duration0, duration3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations_?.has(Some(Seq(Set(duration1, duration2), Set(duration2, duration3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.has(Some(Seq(Set(duration1, duration2), Set(duration2, duration3, duration4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.durations_?.has(Some(Seq.empty[Duration])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_?.has(Some(Set.empty[Duration])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_?.has(Some(Seq.empty[Set[Duration]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.durations_?.has(Option.empty[Duration]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.durations_?.has(Option.empty[Seq[Duration]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.durations_?.has(Option.empty[Set[Duration]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.durations_?.has(Option.empty[Seq[Set[Duration]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(duration1, duration2)))
        val b = (2, Some(Set(duration2, duration3, duration4)))
        val c = (3, None)
        for {
          _ <- Ns.i.durations_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.durations_?.hasNo(Some(duration0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.hasNo(Some(duration1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations_?.hasNo(Some(duration2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_?.hasNo(Some(duration3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations_?.hasNo(Some(duration4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations_?.hasNo(Some(duration5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(duration0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(duration1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(duration2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(duration3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(duration4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(duration5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(duration1, duration2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(duration1, duration3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(duration1, duration4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(duration1, duration5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.durations_?.hasNo(Some(Set(duration1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations_?.hasNo(Some(Set(duration1, duration2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations_?.hasNo(Some(Set(duration1, duration2, duration3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.hasNo(Some(Set(duration2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_?.hasNo(Some(Set(duration2, duration3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations_?.hasNo(Some(Set(duration2, duration3, duration4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(Set(duration1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(Set(duration1, duration2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(Set(duration1, duration2, duration3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(Set(duration2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(Set(duration2, duration3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(Set(duration2, duration3, duration4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(Set(duration1, duration2), Set(duration0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(Set(duration1, duration2), Set(duration0, duration3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(Set(duration1, duration2), Set(duration2, duration3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(Set(duration1, duration2), Set(duration2, duration3, duration4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq.empty[Duration])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.hasNo(Some(Set.empty[Duration])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq.empty[Set[Duration]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.hasNo(Some(Seq(Set.empty[Duration]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.durations_?.hasNo(Option.empty[Duration]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.hasNo(Option.empty[Seq[Duration]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.hasNo(Option.empty[Set[Duration]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.durations_?.hasNo(Option.empty[Seq[Set[Duration]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}