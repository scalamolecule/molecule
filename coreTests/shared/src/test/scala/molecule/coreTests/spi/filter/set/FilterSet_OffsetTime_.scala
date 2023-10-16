// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set

import java.time.OffsetTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_OffsetTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(offsetTime1, offsetTime2))
        val b = (2, Set(offsetTime2, offsetTime3, offsetTime4))
        for {
          _ <- Ns.i.offsetTimes.insert(List(a, b)).transact

          _ <- Ns.i.a1.offsetTimes.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(offsetTime1, offsetTime2))
        val b = (2, Set(offsetTime2, offsetTime3, offsetTime4))
        for {
          _ <- Ns.i.offsetTimes.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.offsetTimes(Set(offsetTime1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes(Set(offsetTime1, offsetTime2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.offsetTimes(Set(offsetTime2, offsetTime1)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.offsetTimes(Set(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetTimes(Seq(Set(offsetTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes(Seq(Set(offsetTime1, offsetTime2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes(Seq(Set(offsetTime2, offsetTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes(Seq(Set(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimes(Set(offsetTime1), Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes(Set(offsetTime2, offsetTime1), Set(offsetTime4, offsetTime3, offsetTime2)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimes(Seq(Set(offsetTime1), Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes(Seq(Set(offsetTime2, offsetTime1), Set(offsetTime4, offsetTime3, offsetTime2))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetTimes(Set(offsetTime1, offsetTime2), Set.empty[OffsetTime]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes(Set.empty[OffsetTime], Set(offsetTime2, offsetTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes(Set.empty[OffsetTime], Set.empty[OffsetTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes(Set.empty[OffsetTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes(Seq.empty[Set[OffsetTime]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes(Seq(Set.empty[OffsetTime])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(offsetTime1, offsetTime2))
        val b = (2, Set(offsetTime2, offsetTime3, offsetTime4))
        for {
          _ <- Ns.i.offsetTimes.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimes.not(Set(offsetTime1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.not(Set(offsetTime1, offsetTime2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.offsetTimes.not(Set(offsetTime2, offsetTime1)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.offsetTimes.not(Set(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimes.not(Seq(Set(offsetTime1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.not(Seq(Set(offsetTime1, offsetTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes.not(Seq(Set(offsetTime2, offsetTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes.not(Seq(Set(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimes.not(Set(offsetTime1), Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.not(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes.not(Set(offsetTime2, offsetTime1), Set(offsetTime4, offsetTime3, offsetTime2)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetTimes.not(Seq(Set(offsetTime1), Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.not(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes.not(Seq(Set(offsetTime2, offsetTime1), Set(offsetTime4, offsetTime3, offsetTime2))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.offsetTimes.not(Seq(Set(offsetTime1, offsetTime2), Set.empty[OffsetTime])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes.not(Set.empty[OffsetTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.not(Seq.empty[Set[OffsetTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(offsetTime1, offsetTime2))
        val b = (2, Set(offsetTime2, offsetTime3, offsetTime4))
        for {
          _ <- Ns.i.offsetTimes.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.offsetTimes.has(offsetTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes.has(offsetTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes.has(offsetTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.has(offsetTime3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetTimes.has(Seq(offsetTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes.has(Seq(offsetTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes.has(Seq(offsetTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.has(Seq(offsetTime3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetTimes.has(offsetTime1, offsetTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.has(offsetTime1, offsetTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.has(offsetTime2, offsetTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.has(offsetTime1, offsetTime2, offsetTime3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimes.has(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.has(Seq(offsetTime1, offsetTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.has(Seq(offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.has(Seq(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.offsetTimes.has(Set(offsetTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes.has(Set(offsetTime1, offsetTime2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes.has(Set(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes.has(Set(offsetTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.has(Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes.has(Set(offsetTime2, offsetTime3, offsetTime4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetTimes.has(Seq(Set(offsetTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes.has(Seq(Set(offsetTime1, offsetTime2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes.has(Seq(Set(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes.has(Seq(Set(offsetTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.has(Seq(Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes.has(Seq(Set(offsetTime2, offsetTime3, offsetTime4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.offsetTimes.has(Set(offsetTime1, offsetTime2), Set(offsetTime0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes.has(Set(offsetTime1, offsetTime2), Set(offsetTime0, offsetTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes.has(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.has(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3, offsetTime4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimes.has(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes.has(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime0, offsetTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes.has(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.has(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3, offsetTime4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetTimes.has(Set(offsetTime1, offsetTime2), Set.empty[OffsetTime]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes.has(Seq.empty[OffsetTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes.has(Set.empty[OffsetTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes.has(Seq.empty[Set[OffsetTime]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(offsetTime1, offsetTime2))
        val b = (2, Set(offsetTime2, offsetTime3, offsetTime4))
        for {
          _ <- Ns.i.offsetTimes.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.offsetTimes.hasNo(offsetTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.hasNo(offsetTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes.hasNo(offsetTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes.hasNo(offsetTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes.hasNo(offsetTime4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes.hasNo(offsetTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(offsetTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(offsetTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(offsetTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(offsetTime4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(offsetTime5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.offsetTimes.hasNo(offsetTime1, offsetTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes.hasNo(offsetTime1, offsetTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes.hasNo(offsetTime1, offsetTime4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes.hasNo(offsetTime1, offsetTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(offsetTime1, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(offsetTime1, offsetTime4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(offsetTime1, offsetTime5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.offsetTimes.hasNo(Set(offsetTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes.hasNo(Set(offsetTime1, offsetTime2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes.hasNo(Set(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.hasNo(Set(offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes.hasNo(Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes.hasNo(Set(offsetTime2, offsetTime3, offsetTime4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(Set(offsetTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(Set(offsetTime1, offsetTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(Set(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(Set(offsetTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(Set(offsetTime2, offsetTime3, offsetTime4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.offsetTimes.hasNo(Set(offsetTime1, offsetTime2), Set(offsetTime0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes.hasNo(Set(offsetTime1, offsetTime2), Set(offsetTime0, offsetTime3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes.hasNo(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes.hasNo(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3, offsetTime4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime0, offsetTime3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3, offsetTime4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.offsetTimes.hasNo(Set(offsetTime1, offsetTime2), Set.empty[OffsetTime]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq.empty[OffsetTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.hasNo(Set.empty[OffsetTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq.empty[Set[OffsetTime]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes.hasNo(Seq(Set.empty[OffsetTime])).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetTimes.insert(List(
            (1, Set(offsetTime1, offsetTime2)),
            (2, Set(offsetTime2, offsetTime3, offsetTime4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))
          _ <- Ns.i.a1.offsetTimes_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.offsetTimes_?.insert(List(
            (0, None),
            (1, Some(Set(offsetTime1, offsetTime2))),
            (2, Some(Set(offsetTime2, offsetTime3, offsetTime4))),
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.offsetTimes_(Set(offsetTime1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_(Set(offsetTime1, offsetTime2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.offsetTimes_(Set(offsetTime2, offsetTime1)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.offsetTimes_(Set(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetTimes_(Seq(Set(offsetTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_(Seq(Set(offsetTime1, offsetTime2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_(Seq(Set(offsetTime2, offsetTime1))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_(Seq(Set(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimes_(Set(offsetTime1), Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_(Set(offsetTime2, offsetTime1), Set(offsetTime4, offsetTime3, offsetTime2)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetTimes_(Seq(Set(offsetTime1), Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_(Seq(Set(offsetTime2, offsetTime1), Set(offsetTime4, offsetTime3, offsetTime2))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetTimes_(Set(offsetTime1, offsetTime2), Set.empty[OffsetTime]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_(Set.empty[OffsetTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_(Seq.empty[Set[OffsetTime]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_(Seq(Set.empty[OffsetTime])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetTimes.insert(List(
            (1, Set(offsetTime1, offsetTime2)),
            (2, Set(offsetTime2, offsetTime3, offsetTime4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimes_.not(Set(offsetTime1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.not(Set(offsetTime1, offsetTime2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.offsetTimes_.not(Set(offsetTime2, offsetTime1)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.offsetTimes_.not(Set(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetTimes_.not(Seq(Set(offsetTime1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.not(Seq(Set(offsetTime1, offsetTime2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimes_.not(Seq(Set(offsetTime2, offsetTime1))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimes_.not(Seq(Set(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimes_.not(Set(offsetTime1), Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.not(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimes_.not(Set(offsetTime2, offsetTime1), Set(offsetTime4, offsetTime3, offsetTime2)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetTimes_.not(Seq(Set(offsetTime1), Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.not(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimes_.not(Seq(Set(offsetTime2, offsetTime1), Set(offsetTime4, offsetTime3, offsetTime2))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.offsetTimes_.not(Seq(Set(offsetTime1, offsetTime2), Set.empty[OffsetTime])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimes_.not(Set.empty[OffsetTime]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.not(Seq.empty[Set[OffsetTime]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetTimes.insert(List(
            (1, Set(offsetTime1, offsetTime2)),
            (2, Set(offsetTime2, offsetTime3, offsetTime4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.offsetTimes_.has(offsetTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_.has(offsetTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_.has(offsetTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.has(offsetTime3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetTimes_.has(Seq(offsetTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_.has(Seq(offsetTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_.has(Seq(offsetTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.has(Seq(offsetTime3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetTimes_.has(offsetTime1, offsetTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.has(offsetTime1, offsetTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.has(offsetTime2, offsetTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.has(offsetTime1, offsetTime2, offsetTime3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetTimes_.has(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.has(Seq(offsetTime1, offsetTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.has(Seq(offsetTime2, offsetTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.has(Seq(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List(1, 2))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.offsetTimes_.has(Set(offsetTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_.has(Set(offsetTime1, offsetTime2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_.has(Set(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_.has(Set(offsetTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.has(Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimes_.has(Set(offsetTime2, offsetTime3, offsetTime4)).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetTimes_.has(Seq(Set(offsetTime1))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_.has(Seq(Set(offsetTime1, offsetTime2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_.has(Seq(Set(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_.has(Seq(Set(offsetTime2))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.has(Seq(Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimes_.has(Seq(Set(offsetTime2, offsetTime3, offsetTime4))).query.get.map(_ ==> List(2))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.offsetTimes_.has(Set(offsetTime1, offsetTime2), Set(offsetTime0)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_.has(Set(offsetTime1, offsetTime2), Set(offsetTime0, offsetTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_.has(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.has(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3, offsetTime4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetTimes_.has(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime0))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_.has(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime0, offsetTime3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_.has(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.has(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3, offsetTime4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetTimes_.has(Set(offsetTime1, offsetTime2), Set.empty[OffsetTime]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_.has(Seq.empty[OffsetTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_.has(Set.empty[OffsetTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_.has(Seq.empty[Set[OffsetTime]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetTimes.insert(List(
            (1, Set(offsetTime1, offsetTime2)),
            (2, Set(offsetTime2, offsetTime3, offsetTime4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.offsetTimes_.hasNo(offsetTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.hasNo(offsetTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimes_.hasNo(offsetTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_.hasNo(offsetTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_.hasNo(offsetTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_.hasNo(offsetTime5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(offsetTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(offsetTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(offsetTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(offsetTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(offsetTime5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.offsetTimes_.hasNo(offsetTime1, offsetTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_.hasNo(offsetTime1, offsetTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_.hasNo(offsetTime1, offsetTime4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_.hasNo(offsetTime1, offsetTime5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(offsetTime1, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(offsetTime1, offsetTime4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(offsetTime1, offsetTime5)).query.get.map(_ ==> List(2))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.offsetTimes_.hasNo(Set(offsetTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimes_.hasNo(Set(offsetTime1, offsetTime2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimes_.hasNo(Set(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.hasNo(Set(offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_.hasNo(Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_.hasNo(Set(offsetTime2, offsetTime3, offsetTime4)).query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(Set(offsetTime1))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(Set(offsetTime1, offsetTime2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(Set(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(Set(offsetTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(Set(offsetTime2, offsetTime3, offsetTime4))).query.get.map(_ ==> List(1))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.offsetTimes_.hasNo(Set(offsetTime1, offsetTime2), Set(offsetTime0)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimes_.hasNo(Set(offsetTime1, offsetTime2), Set(offsetTime0, offsetTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimes_.hasNo(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_.hasNo(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3, offsetTime4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime0))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime0, offsetTime3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3, offsetTime4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.offsetTimes_.hasNo(Set(offsetTime1, offsetTime2), Set.empty[OffsetTime]).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq.empty[OffsetTime]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.hasNo(Set.empty[OffsetTime]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq.empty[Set[OffsetTime]]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimes_.hasNo(Seq(Set.empty[OffsetTime])).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(offsetTime1, offsetTime2)))
        val b = (2, Some(Set(offsetTime2, offsetTime3, offsetTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetTimes_?.insert(a, b, c).transact

          _ <- Ns.i.a1.offsetTimes_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no offsetTime value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.offsetTimes_?.query.get.map(_ ==> List(
            (2, Some(Set(offsetTime2, offsetTime3, offsetTime4))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(offsetTime1, offsetTime2)))
        val b = (2, Some(Set(offsetTime2, offsetTime3, offsetTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetTimes_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.offsetTimes_?(Some(Set(offsetTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_?(Some(Set(offsetTime1, offsetTime2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.offsetTimes_?(Some(Set(offsetTime2, offsetTime1))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.offsetTimes_?(Some(Set(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetTimes_?(Some(Seq(Set(offsetTime1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_?(Some(Seq(Set(offsetTime1, offsetTime2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes_?(Some(Seq(Set(offsetTime2, offsetTime1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes_?(Some(Seq(Set(offsetTime1, offsetTime2, offsetTime3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimes_?(Some(Seq(Set(offsetTime1), Set(offsetTime2, offsetTime3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_?(Some(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes_?(Some(Seq(Set(offsetTime2, offsetTime1), Set(offsetTime4, offsetTime3, offsetTime2)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetTimes_?(Some(Set.empty[OffsetTime])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_?(Some(Seq.empty[Set[OffsetTime]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_?(Some(Seq(Set.empty[OffsetTime]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.offsetTimes_?(Option.empty[Set[OffsetTime]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.offsetTimes_?(Option.empty[Seq[Set[OffsetTime]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(offsetTime1, offsetTime2)))
        val b = (2, Some(Set(offsetTime2, offsetTime3, offsetTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetTimes_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimes_?.not(Some(Set(offsetTime1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.not(Some(Set(offsetTime1, offsetTime2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.offsetTimes_?.not(Some(Set(offsetTime2, offsetTime1))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.offsetTimes_?.not(Some(Set(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimes_?.not(Some(Seq(Set(offsetTime1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.not(Some(Seq(Set(offsetTime1, offsetTime2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes_?.not(Some(Seq(Set(offsetTime2, offsetTime1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes_?.not(Some(Seq(Set(offsetTime1, offsetTime2, offsetTime3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimes_?.not(Some(Seq(Set(offsetTime1), Set(offsetTime2, offsetTime3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.not(Some(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes_?.not(Some(Seq(Set(offsetTime2, offsetTime1), Set(offsetTime4, offsetTime3, offsetTime2)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.offsetTimes_?.not(Some(Seq(Set(offsetTime1, offsetTime2), Set.empty[OffsetTime]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes_?.not(Some(Set.empty[OffsetTime])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.not(Some(Seq.empty[Set[OffsetTime]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.offsetTimes_?(Option.empty[Set[OffsetTime]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.offsetTimes_?(Option.empty[Seq[Set[OffsetTime]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(offsetTime1, offsetTime2)))
        val b = (2, Some(Set(offsetTime2, offsetTime3, offsetTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetTimes_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.offsetTimes_?.has(Some(offsetTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_?.has(Some(offsetTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes_?.has(Some(offsetTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.has(Some(offsetTime3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Seq(offsetTime0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Seq(offsetTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Seq(offsetTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Seq(offsetTime3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Seq(offsetTime1, offsetTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Seq(offsetTime1, offsetTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Seq(offsetTime2, offsetTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Seq(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Set(offsetTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Set(offsetTime1, offsetTime2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Set(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Set(offsetTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Set(offsetTime2, offsetTime3, offsetTime4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Seq(Set(offsetTime1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Seq(Set(offsetTime1, offsetTime2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Seq(Set(offsetTime1, offsetTime2, offsetTime3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Seq(Set(offsetTime2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Seq(Set(offsetTime2, offsetTime3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Seq(Set(offsetTime2, offsetTime3, offsetTime4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime0, offsetTime3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3, offsetTime4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Seq.empty[OffsetTime])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Set.empty[OffsetTime])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_?.has(Some(Seq.empty[Set[OffsetTime]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.offsetTimes_?.has(Option.empty[OffsetTime]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.offsetTimes_?.has(Option.empty[Seq[OffsetTime]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.offsetTimes_?.has(Option.empty[Set[OffsetTime]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.offsetTimes_?.has(Option.empty[Seq[Set[OffsetTime]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(offsetTime1, offsetTime2)))
        val b = (2, Some(Set(offsetTime2, offsetTime3, offsetTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetTimes_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(offsetTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(offsetTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(offsetTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(offsetTime4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(offsetTime5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(offsetTime0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(offsetTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(offsetTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(offsetTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(offsetTime4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(offsetTime5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(offsetTime1, offsetTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(offsetTime1, offsetTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(offsetTime1, offsetTime4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(offsetTime1, offsetTime5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Set(offsetTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Set(offsetTime1, offsetTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Set(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Set(offsetTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Set(offsetTime2, offsetTime3, offsetTime4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(Set(offsetTime1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(Set(offsetTime1, offsetTime2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(Set(offsetTime1, offsetTime2, offsetTime3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(Set(offsetTime2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(Set(offsetTime2, offsetTime3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(Set(offsetTime2, offsetTime3, offsetTime4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime0, offsetTime3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3, offsetTime4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq.empty[OffsetTime])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Set.empty[OffsetTime])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq.empty[Set[OffsetTime]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Some(Seq(Set.empty[OffsetTime]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Option.empty[OffsetTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Option.empty[Seq[OffsetTime]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Option.empty[Set[OffsetTime]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimes_?.hasNo(Option.empty[Seq[Set[OffsetTime]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}