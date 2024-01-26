// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.time.LocalTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_LocalTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(localTime1, localTime2))
        val b = (2, Set(localTime2, localTime3, localTime4))
        for {
          _ <- Ns.i.localTimes.insert(List(a, b)).transact

          _ <- Ns.i.a1.localTimes.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(localTime1, localTime2))
        val b = (2, Set(localTime2, localTime3, localTime4))
        for {
          _ <- Ns.i.localTimes.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.localTimes(Set(localTime1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes(Set(localTime1, localTime2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.localTimes(Set(localTime2, localTime1)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.localTimes(Set(localTime1, localTime2, localTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localTimes(Seq(Set(localTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes(Seq(Set(localTime1, localTime2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes(Seq(Set(localTime2, localTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes(Seq(Set(localTime1, localTime2, localTime3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.localTimes(Set(localTime1), Set(localTime2, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes(Set(localTime1, localTime2), Set(localTime2, localTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes(Set(localTime2, localTime1), Set(localTime4, localTime3, localTime2)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localTimes(Seq(Set(localTime1), Set(localTime2, localTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes(Seq(Set(localTime1, localTime2), Set(localTime2, localTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes(Seq(Set(localTime2, localTime1), Set(localTime4, localTime3, localTime2))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.localTimes(Set(localTime1, localTime2), Set.empty[LocalTime]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes(Set.empty[LocalTime], Set(localTime2, localTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes(Set.empty[LocalTime], Set.empty[LocalTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes(Set.empty[LocalTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes(Seq.empty[Set[LocalTime]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes(Seq(Set.empty[LocalTime])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(localTime1, localTime2))
        val b = (2, Set(localTime2, localTime3, localTime4))
        for {
          _ <- Ns.i.localTimes.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.localTimes.not(Set(localTime1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.not(Set(localTime1, localTime2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.localTimes.not(Set(localTime2, localTime1)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.localTimes.not(Set(localTime1, localTime2, localTime3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localTimes.not(Seq(Set(localTime1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.not(Seq(Set(localTime1, localTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes.not(Seq(Set(localTime2, localTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes.not(Seq(Set(localTime1, localTime2, localTime3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.localTimes.not(Set(localTime1), Set(localTime2, localTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.not(Set(localTime1, localTime2), Set(localTime2, localTime3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes.not(Set(localTime2, localTime1), Set(localTime4, localTime3, localTime2)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localTimes.not(Seq(Set(localTime1), Set(localTime2, localTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.not(Seq(Set(localTime1, localTime2), Set(localTime2, localTime3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes.not(Seq(Set(localTime2, localTime1), Set(localTime4, localTime3, localTime2))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.localTimes.not(Seq(Set(localTime1, localTime2), Set.empty[LocalTime])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes.not(Set.empty[LocalTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.not(Seq.empty[Set[LocalTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(localTime1, localTime2))
        val b = (2, Set(localTime2, localTime3, localTime4))
        for {
          _ <- Ns.i.localTimes.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.localTimes.has(localTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes.has(localTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes.has(localTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.has(localTime3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localTimes.has(Seq(localTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes.has(Seq(localTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes.has(Seq(localTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.has(Seq(localTime3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localTimes.has(localTime1, localTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.has(localTime1, localTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.has(localTime2, localTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.has(localTime1, localTime2, localTime3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localTimes.has(Seq(localTime1, localTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.has(Seq(localTime1, localTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.has(Seq(localTime2, localTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.has(Seq(localTime1, localTime2, localTime3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.localTimes.has(Set(localTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes.has(Set(localTime1, localTime2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes.has(Set(localTime1, localTime2, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes.has(Set(localTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.has(Set(localTime2, localTime3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes.has(Set(localTime2, localTime3, localTime4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localTimes.has(Seq(Set(localTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes.has(Seq(Set(localTime1, localTime2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes.has(Seq(Set(localTime1, localTime2, localTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes.has(Seq(Set(localTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.has(Seq(Set(localTime2, localTime3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes.has(Seq(Set(localTime2, localTime3, localTime4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.localTimes.has(Set(localTime1, localTime2), Set(localTime0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes.has(Set(localTime1, localTime2), Set(localTime0, localTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes.has(Set(localTime1, localTime2), Set(localTime2, localTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.has(Set(localTime1, localTime2), Set(localTime2, localTime3, localTime4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localTimes.has(Seq(Set(localTime1, localTime2), Set(localTime0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes.has(Seq(Set(localTime1, localTime2), Set(localTime0, localTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes.has(Seq(Set(localTime1, localTime2), Set(localTime2, localTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.has(Seq(Set(localTime1, localTime2), Set(localTime2, localTime3, localTime4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.localTimes.has(Set(localTime1, localTime2), Set.empty[LocalTime]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes.has(Seq.empty[LocalTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes.has(Set.empty[LocalTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes.has(Seq.empty[Set[LocalTime]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(localTime1, localTime2))
        val b = (2, Set(localTime2, localTime3, localTime4))
        for {
          _ <- Ns.i.localTimes.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.localTimes.hasNo(localTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.hasNo(localTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes.hasNo(localTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes.hasNo(localTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes.hasNo(localTime4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes.hasNo(localTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localTimes.hasNo(Seq(localTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.hasNo(Seq(localTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes.hasNo(Seq(localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes.hasNo(Seq(localTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes.hasNo(Seq(localTime4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes.hasNo(Seq(localTime5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.localTimes.hasNo(localTime1, localTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes.hasNo(localTime1, localTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes.hasNo(localTime1, localTime4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes.hasNo(localTime1, localTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localTimes.hasNo(Seq(localTime1, localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes.hasNo(Seq(localTime1, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes.hasNo(Seq(localTime1, localTime4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes.hasNo(Seq(localTime1, localTime5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.localTimes.hasNo(Set(localTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes.hasNo(Set(localTime1, localTime2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes.hasNo(Set(localTime1, localTime2, localTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.hasNo(Set(localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes.hasNo(Set(localTime2, localTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes.hasNo(Set(localTime2, localTime3, localTime4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.localTimes.hasNo(Seq(Set(localTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes.hasNo(Seq(Set(localTime1, localTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes.hasNo(Seq(Set(localTime1, localTime2, localTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.hasNo(Seq(Set(localTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes.hasNo(Seq(Set(localTime2, localTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes.hasNo(Seq(Set(localTime2, localTime3, localTime4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.localTimes.hasNo(Set(localTime1, localTime2), Set(localTime0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes.hasNo(Set(localTime1, localTime2), Set(localTime0, localTime3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes.hasNo(Set(localTime1, localTime2), Set(localTime2, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes.hasNo(Set(localTime1, localTime2), Set(localTime2, localTime3, localTime4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localTimes.hasNo(Seq(Set(localTime1, localTime2), Set(localTime0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes.hasNo(Seq(Set(localTime1, localTime2), Set(localTime0, localTime3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes.hasNo(Seq(Set(localTime1, localTime2), Set(localTime2, localTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes.hasNo(Seq(Set(localTime1, localTime2), Set(localTime2, localTime3, localTime4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.localTimes.hasNo(Set(localTime1, localTime2), Set.empty[LocalTime]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes.hasNo(Seq.empty[LocalTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.hasNo(Set.empty[LocalTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.hasNo(Seq.empty[Set[LocalTime]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes.hasNo(Seq(Set.empty[LocalTime])).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localTimes.insert(List(
            (1, Set(localTime1, localTime2)),
            (2, Set(localTime2, localTime3, localTime4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))
          _ <- Ns.i.a1.localTimes_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.localTimes_?.insert(List(
            (0, None),
            (1, Some(Set(localTime1, localTime2))),
            (2, Some(Set(localTime2, localTime3, localTime4))),
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.localTimes_(Set(localTime1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_(Set(localTime1, localTime2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.localTimes_(Set(localTime2, localTime1)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.localTimes_(Set(localTime1, localTime2, localTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localTimes_(Seq(Set(localTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_(Seq(Set(localTime1, localTime2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_(Seq(Set(localTime2, localTime1))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_(Seq(Set(localTime1, localTime2, localTime3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.localTimes_(Set(localTime1), Set(localTime2, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_(Set(localTime1, localTime2), Set(localTime2, localTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_(Set(localTime2, localTime1), Set(localTime4, localTime3, localTime2)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localTimes_(Seq(Set(localTime1), Set(localTime2, localTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_(Seq(Set(localTime1, localTime2), Set(localTime2, localTime3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_(Seq(Set(localTime2, localTime1), Set(localTime4, localTime3, localTime2))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.localTimes_(Set(localTime1, localTime2), Set.empty[LocalTime]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_(Set.empty[LocalTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_(Seq.empty[Set[LocalTime]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_(Seq(Set.empty[LocalTime])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localTimes.insert(List(
            (1, Set(localTime1, localTime2)),
            (2, Set(localTime2, localTime3, localTime4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.localTimes_.not(Set(localTime1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.not(Set(localTime1, localTime2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.localTimes_.not(Set(localTime2, localTime1)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.localTimes_.not(Set(localTime1, localTime2, localTime3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localTimes_.not(Seq(Set(localTime1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.not(Seq(Set(localTime1, localTime2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimes_.not(Seq(Set(localTime2, localTime1))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimes_.not(Seq(Set(localTime1, localTime2, localTime3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.localTimes_.not(Set(localTime1), Set(localTime2, localTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.not(Set(localTime1, localTime2), Set(localTime2, localTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimes_.not(Set(localTime2, localTime1), Set(localTime4, localTime3, localTime2)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localTimes_.not(Seq(Set(localTime1), Set(localTime2, localTime3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.not(Seq(Set(localTime1, localTime2), Set(localTime2, localTime3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimes_.not(Seq(Set(localTime2, localTime1), Set(localTime4, localTime3, localTime2))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.localTimes_.not(Seq(Set(localTime1, localTime2), Set.empty[LocalTime])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimes_.not(Set.empty[LocalTime]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.not(Seq.empty[Set[LocalTime]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localTimes.insert(List(
            (1, Set(localTime1, localTime2)),
            (2, Set(localTime2, localTime3, localTime4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.localTimes_.has(localTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_.has(localTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_.has(localTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.has(localTime3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localTimes_.has(Seq(localTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_.has(Seq(localTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_.has(Seq(localTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.has(Seq(localTime3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localTimes_.has(localTime1, localTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.has(localTime1, localTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.has(localTime2, localTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.has(localTime1, localTime2, localTime3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localTimes_.has(Seq(localTime1, localTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.has(Seq(localTime1, localTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.has(Seq(localTime2, localTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.has(Seq(localTime1, localTime2, localTime3)).query.get.map(_ ==> List(1, 2))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.localTimes_.has(Set(localTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_.has(Set(localTime1, localTime2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_.has(Set(localTime1, localTime2, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_.has(Set(localTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.has(Set(localTime2, localTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimes_.has(Set(localTime2, localTime3, localTime4)).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localTimes_.has(Seq(Set(localTime1))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_.has(Seq(Set(localTime1, localTime2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_.has(Seq(Set(localTime1, localTime2, localTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_.has(Seq(Set(localTime2))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.has(Seq(Set(localTime2, localTime3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimes_.has(Seq(Set(localTime2, localTime3, localTime4))).query.get.map(_ ==> List(2))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.localTimes_.has(Set(localTime1, localTime2), Set(localTime0)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_.has(Set(localTime1, localTime2), Set(localTime0, localTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_.has(Set(localTime1, localTime2), Set(localTime2, localTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.has(Set(localTime1, localTime2), Set(localTime2, localTime3, localTime4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localTimes_.has(Seq(Set(localTime1, localTime2), Set(localTime0))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_.has(Seq(Set(localTime1, localTime2), Set(localTime0, localTime3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_.has(Seq(Set(localTime1, localTime2), Set(localTime2, localTime3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.has(Seq(Set(localTime1, localTime2), Set(localTime2, localTime3, localTime4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.localTimes_.has(Set(localTime1, localTime2), Set.empty[LocalTime]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_.has(Seq.empty[LocalTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_.has(Set.empty[LocalTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_.has(Seq.empty[Set[LocalTime]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localTimes.insert(List(
            (1, Set(localTime1, localTime2)),
            (2, Set(localTime2, localTime3, localTime4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.localTimes_.hasNo(localTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.hasNo(localTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimes_.hasNo(localTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_.hasNo(localTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_.hasNo(localTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_.hasNo(localTime5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(localTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(localTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(localTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(localTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(localTime5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.localTimes_.hasNo(localTime1, localTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_.hasNo(localTime1, localTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_.hasNo(localTime1, localTime4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_.hasNo(localTime1, localTime5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(localTime1, localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(localTime1, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(localTime1, localTime4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(localTime1, localTime5)).query.get.map(_ ==> List(2))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.localTimes_.hasNo(Set(localTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimes_.hasNo(Set(localTime1, localTime2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimes_.hasNo(Set(localTime1, localTime2, localTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.hasNo(Set(localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_.hasNo(Set(localTime2, localTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_.hasNo(Set(localTime2, localTime3, localTime4)).query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(Set(localTime1))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(Set(localTime1, localTime2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(Set(localTime1, localTime2, localTime3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(Set(localTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(Set(localTime2, localTime3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(Set(localTime2, localTime3, localTime4))).query.get.map(_ ==> List(1))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.localTimes_.hasNo(Set(localTime1, localTime2), Set(localTime0)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimes_.hasNo(Set(localTime1, localTime2), Set(localTime0, localTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimes_.hasNo(Set(localTime1, localTime2), Set(localTime2, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_.hasNo(Set(localTime1, localTime2), Set(localTime2, localTime3, localTime4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(Set(localTime1, localTime2), Set(localTime0))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(Set(localTime1, localTime2), Set(localTime0, localTime3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(Set(localTime1, localTime2), Set(localTime2, localTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(Set(localTime1, localTime2), Set(localTime2, localTime3, localTime4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.localTimes_.hasNo(Set(localTime1, localTime2), Set.empty[LocalTime]).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimes_.hasNo(Seq.empty[LocalTime]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.hasNo(Set.empty[LocalTime]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.hasNo(Seq.empty[Set[LocalTime]]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimes_.hasNo(Seq(Set.empty[LocalTime])).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(localTime1, localTime2)))
        val b = (2, Some(Set(localTime2, localTime3, localTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localTimes_?.insert(a, b, c).transact

          _ <- Ns.i.a1.localTimes_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no localTime value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.localTimes_?.query.get.map(_ ==> List(
            (2, Some(Set(localTime2, localTime3, localTime4))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(localTime1, localTime2)))
        val b = (2, Some(Set(localTime2, localTime3, localTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localTimes_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.localTimes_?(Some(Set(localTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_?(Some(Set(localTime1, localTime2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.localTimes_?(Some(Set(localTime2, localTime1))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.localTimes_?(Some(Set(localTime1, localTime2, localTime3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localTimes_?(Some(Seq(Set(localTime1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_?(Some(Seq(Set(localTime1, localTime2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes_?(Some(Seq(Set(localTime2, localTime1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes_?(Some(Seq(Set(localTime1, localTime2, localTime3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.localTimes_?(Some(Seq(Set(localTime1), Set(localTime2, localTime3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_?(Some(Seq(Set(localTime1, localTime2), Set(localTime2, localTime3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes_?(Some(Seq(Set(localTime2, localTime1), Set(localTime4, localTime3, localTime2)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.localTimes_?(Some(Set.empty[LocalTime])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_?(Some(Seq.empty[Set[LocalTime]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_?(Some(Seq(Set.empty[LocalTime]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.localTimes_?(Option.empty[Set[LocalTime]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.localTimes_?(Option.empty[Seq[Set[LocalTime]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(localTime1, localTime2)))
        val b = (2, Some(Set(localTime2, localTime3, localTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localTimes_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.localTimes_?.not(Some(Set(localTime1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.not(Some(Set(localTime1, localTime2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.localTimes_?.not(Some(Set(localTime2, localTime1))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.localTimes_?.not(Some(Set(localTime1, localTime2, localTime3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localTimes_?.not(Some(Seq(Set(localTime1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.not(Some(Seq(Set(localTime1, localTime2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes_?.not(Some(Seq(Set(localTime2, localTime1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes_?.not(Some(Seq(Set(localTime1, localTime2, localTime3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.localTimes_?.not(Some(Seq(Set(localTime1), Set(localTime2, localTime3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.not(Some(Seq(Set(localTime1, localTime2), Set(localTime2, localTime3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes_?.not(Some(Seq(Set(localTime2, localTime1), Set(localTime4, localTime3, localTime2)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.localTimes_?.not(Some(Seq(Set(localTime1, localTime2), Set.empty[LocalTime]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes_?.not(Some(Set.empty[LocalTime])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.not(Some(Seq.empty[Set[LocalTime]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.localTimes_?(Option.empty[Set[LocalTime]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.localTimes_?(Option.empty[Seq[Set[LocalTime]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(localTime1, localTime2)))
        val b = (2, Some(Set(localTime2, localTime3, localTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localTimes_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.localTimes_?.has(Some(localTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_?.has(Some(localTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes_?.has(Some(localTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.has(Some(localTime3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localTimes_?.has(Some(Seq(localTime0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_?.has(Some(Seq(localTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes_?.has(Some(Seq(localTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.has(Some(Seq(localTime3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localTimes_?.has(Some(Seq(localTime1, localTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.has(Some(Seq(localTime1, localTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.has(Some(Seq(localTime2, localTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.has(Some(Seq(localTime1, localTime2, localTime3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.localTimes_?.has(Some(Set(localTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes_?.has(Some(Set(localTime1, localTime2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes_?.has(Some(Set(localTime1, localTime2, localTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_?.has(Some(Set(localTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.has(Some(Set(localTime2, localTime3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes_?.has(Some(Set(localTime2, localTime3, localTime4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localTimes_?.has(Some(Seq(Set(localTime1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes_?.has(Some(Seq(Set(localTime1, localTime2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes_?.has(Some(Seq(Set(localTime1, localTime2, localTime3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_?.has(Some(Seq(Set(localTime2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.has(Some(Seq(Set(localTime2, localTime3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes_?.has(Some(Seq(Set(localTime2, localTime3, localTime4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.localTimes_?.has(Some(Seq(Set(localTime1, localTime2), Set(localTime0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes_?.has(Some(Seq(Set(localTime1, localTime2), Set(localTime0, localTime3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes_?.has(Some(Seq(Set(localTime1, localTime2), Set(localTime2, localTime3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.has(Some(Seq(Set(localTime1, localTime2), Set(localTime2, localTime3, localTime4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.localTimes_?.has(Some(Seq.empty[LocalTime])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_?.has(Some(Set.empty[LocalTime])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_?.has(Some(Seq.empty[Set[LocalTime]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.localTimes_?.has(Option.empty[LocalTime]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.localTimes_?.has(Option.empty[Seq[LocalTime]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.localTimes_?.has(Option.empty[Set[LocalTime]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.localTimes_?.has(Option.empty[Seq[Set[LocalTime]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(localTime1, localTime2)))
        val b = (2, Some(Set(localTime2, localTime3, localTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localTimes_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(localTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(localTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(localTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(localTime4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(localTime5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(localTime0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(localTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(localTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(localTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(localTime4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(localTime5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(localTime1, localTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(localTime1, localTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(localTime1, localTime4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(localTime1, localTime5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Set(localTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Set(localTime1, localTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Set(localTime1, localTime2, localTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Set(localTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Set(localTime2, localTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Set(localTime2, localTime3, localTime4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(Set(localTime1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(Set(localTime1, localTime2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(Set(localTime1, localTime2, localTime3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(Set(localTime2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(Set(localTime2, localTime3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(Set(localTime2, localTime3, localTime4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(Set(localTime1, localTime2), Set(localTime0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(Set(localTime1, localTime2), Set(localTime0, localTime3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(Set(localTime1, localTime2), Set(localTime2, localTime3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(Set(localTime1, localTime2), Set(localTime2, localTime3, localTime4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq.empty[LocalTime])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Set.empty[LocalTime])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq.empty[Set[LocalTime]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.hasNo(Some(Seq(Set.empty[LocalTime]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.localTimes_?.hasNo(Option.empty[LocalTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.hasNo(Option.empty[Seq[LocalTime]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.hasNo(Option.empty[Set[LocalTime]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimes_?.hasNo(Option.empty[Seq[Set[LocalTime]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}