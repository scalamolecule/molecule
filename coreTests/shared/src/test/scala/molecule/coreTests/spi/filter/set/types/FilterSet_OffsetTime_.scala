// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

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
          _ <- Ns.i.offsetTimeSet.insert(List(a, b)).transact

          _ <- Ns.i.a1.offsetTimeSet.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(offsetTime1, offsetTime2))
        val b = (2, Set(offsetTime2, offsetTime3, offsetTime4))
        for {
          _ <- Ns.i.offsetTimeSet.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.offsetTimeSet(Set(offsetTime1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet(Set(offsetTime1, offsetTime2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.offsetTimeSet(Set(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetTimeSet(Seq(Set(offsetTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet(Seq(Set(offsetTime1, offsetTime2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSet(Seq(Set(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimeSet(Set(offsetTime1), Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSet(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3, offsetTime4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSet(Seq(Set(offsetTime1), Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSet(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3, offsetTime4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetTimeSet(Set(offsetTime1, offsetTime2), Set.empty[OffsetTime]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSet(Set.empty[OffsetTime], Set(offsetTime1, offsetTime2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSet(Set.empty[OffsetTime], Set.empty[OffsetTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet(Set.empty[OffsetTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet(Seq.empty[Set[OffsetTime]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet(Seq(Set.empty[OffsetTime])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.offsetTimeSet().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(offsetTime1, offsetTime2))
        val b = (2, Set(offsetTime2, offsetTime3, offsetTime4))
        for {
          _ <- Ns.i.offsetTimeSet.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimeSet.not(Set(offsetTime1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet.not(Set(offsetTime1, offsetTime2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.offsetTimeSet.not(Set(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSet.not(Seq(Set(offsetTime1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet.not(Seq(Set(offsetTime1, offsetTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSet.not(Seq(Set(offsetTime1, offsetTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSet.not(Seq(Set(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimeSet.not(Set(offsetTime1), Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet.not(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSet.not(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3, offsetTime4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetTimeSet.not(Seq(Set(offsetTime1), Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet.not(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSet.not(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3, offsetTime4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.offsetTimeSet.not(Seq(Set(offsetTime1, offsetTime2), Set.empty[OffsetTime])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSet.not(Set.empty[OffsetTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet.not(Seq.empty[Set[OffsetTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(offsetTime1, offsetTime2))
        val b = (2, Set(offsetTime2, offsetTime3, offsetTime4))
        for {
          _ <- Ns.i.offsetTimeSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.offsetTimeSet.has(offsetTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet.has(offsetTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSet.has(offsetTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet.has(offsetTime3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSet.has(Seq(offsetTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet.has(Seq(offsetTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSet.has(Seq(offsetTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet.has(Seq(offsetTime3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetTimeSet.has(offsetTime1, offsetTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet.has(offsetTime1, offsetTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet.has(offsetTime2, offsetTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet.has(offsetTime1, offsetTime2, offsetTime3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSet.has(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet.has(Seq(offsetTime1, offsetTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet.has(Seq(offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet.has(Seq(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetTimeSet.has(Seq.empty[OffsetTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(offsetTime1, offsetTime2))
        val b = (2, Set(offsetTime2, offsetTime3, offsetTime4))
        for {
          _ <- Ns.i.offsetTimeSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.offsetTimeSet.hasNo(offsetTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet.hasNo(offsetTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSet.hasNo(offsetTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet.hasNo(offsetTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSet.hasNo(offsetTime4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSet.hasNo(offsetTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSet.hasNo(Seq(offsetTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet.hasNo(Seq(offsetTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSet.hasNo(Seq(offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet.hasNo(Seq(offsetTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSet.hasNo(Seq(offsetTime4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSet.hasNo(Seq(offsetTime5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.offsetTimeSet.hasNo(offsetTime1, offsetTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet.hasNo(offsetTime1, offsetTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet.hasNo(offsetTime1, offsetTime4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet.hasNo(offsetTime1, offsetTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSet.hasNo(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet.hasNo(Seq(offsetTime1, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet.hasNo(Seq(offsetTime1, offsetTime4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet.hasNo(Seq(offsetTime1, offsetTime5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.offsetTimeSet.hasNo(Seq.empty[OffsetTime]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetTimeSet.insert(List(
            (1, Set(offsetTime1, offsetTime2)),
            (2, Set(offsetTime2, offsetTime3, offsetTime4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // offsetTimeSet not asserted for i = 0
          _ <- Ns.i.a1.offsetTimeSet_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.offsetTimeSet_?.insert(List(
            (0, None),
            (1, Some(Set(offsetTime1, offsetTime2))),
            (2, Some(Set(offsetTime2, offsetTime3, offsetTime4))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.offsetTimeSet_().query.get.map(_ ==> List(0))

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.offsetTimeSet_(Set(offsetTime1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_(Set(offsetTime1, offsetTime2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.offsetTimeSet_(Set(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetTimeSet_(Seq(Set(offsetTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_(Seq(Set(offsetTime1, offsetTime2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSet_(Seq(Set(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimeSet_(Set(offsetTime1), Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSet_(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3, offsetTime4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetTimeSet_(Seq(Set(offsetTime1), Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSet_(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3, offsetTime4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetTimeSet_(Set(offsetTime1, offsetTime2), Set.empty[OffsetTime]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSet_(Set.empty[OffsetTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_(Seq.empty[Set[OffsetTime]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_(Seq(Set.empty[OffsetTime])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetTimeSet.insert(List(
            (1, Set(offsetTime1, offsetTime2)),
            (2, Set(offsetTime2, offsetTime3, offsetTime4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimeSet_.not(Set(offsetTime1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSet_.not(Set(offsetTime1, offsetTime2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.offsetTimeSet_.not(Set(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetTimeSet_.not(Seq(Set(offsetTime1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSet_.not(Seq(Set(offsetTime1, offsetTime2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeSet_.not(Seq(Set(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimeSet_.not(Set(offsetTime1), Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSet_.not(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeSet_.not(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3, offsetTime4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetTimeSet_.not(Seq(Set(offsetTime1), Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSet_.not(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeSet_.not(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3, offsetTime4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.offsetTimeSet_.not(Seq(Set(offsetTime1, offsetTime2), Set.empty[OffsetTime])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeSet_.not(Set.empty[OffsetTime]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSet_.not(Seq.empty[Set[OffsetTime]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetTimeSet.insert(List(
            (1, Set(offsetTime1, offsetTime2)),
            (2, Set(offsetTime2, offsetTime3, offsetTime4))
          )).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.offsetTimeSet_.has(offsetTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_.has(offsetTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSet_.has(offsetTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSet_.has(offsetTime3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetTimeSet_.has(Seq(offsetTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_.has(Seq(offsetTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSet_.has(Seq(offsetTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSet_.has(Seq(offsetTime3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetTimeSet_.has(offsetTime1, offsetTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSet_.has(offsetTime1, offsetTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSet_.has(offsetTime2, offsetTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSet_.has(offsetTime1, offsetTime2, offsetTime3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetTimeSet_.has(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSet_.has(Seq(offsetTime1, offsetTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSet_.has(Seq(offsetTime2, offsetTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSet_.has(Seq(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetTimeSet_.has(Seq.empty[OffsetTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetTimeSet.insert(List(
            (1, Set(offsetTime1, offsetTime2)),
            (2, Set(offsetTime2, offsetTime3, offsetTime4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(offsetTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(offsetTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(offsetTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(offsetTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(offsetTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(offsetTime5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(Seq(offsetTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(Seq(offsetTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(Seq(offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(Seq(offsetTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(Seq(offsetTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(Seq(offsetTime5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(offsetTime1, offsetTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(offsetTime1, offsetTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(offsetTime1, offsetTime4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(offsetTime1, offsetTime5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(Seq(offsetTime1, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(Seq(offsetTime1, offsetTime4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(Seq(offsetTime1, offsetTime5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.offsetTimeSet_.hasNo(Seq.empty[OffsetTime]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(offsetTime1, offsetTime2)))
        val b = (2, Some(Set(offsetTime2, offsetTime3, offsetTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetTimeSet_?.insert(a, b, c).transact

          _ <- Ns.i.a1.offsetTimeSet_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no offsetTime value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.offsetTimeSet_?.query.get.map(_ ==> List(
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
          _ <- Ns.i.offsetTimeSet_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.offsetTimeSet_?(Some(Set(offsetTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_?(Some(Set(offsetTime1, offsetTime2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.offsetTimeSet_?(Some(Set(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetTimeSet_?(Some(Seq(Set(offsetTime1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_?(Some(Seq(Set(offsetTime1, offsetTime2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSet_?(Some(Seq(Set(offsetTime1, offsetTime2, offsetTime3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimeSet_?(Some(Seq(Set(offsetTime1), Set(offsetTime2, offsetTime3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_?.apply(Some(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSet_?(Some(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3, offsetTime4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetTimeSet_?(Some(Set.empty[OffsetTime])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_?(Some(Seq.empty[Set[OffsetTime]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_?(Some(Seq(Set.empty[OffsetTime]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.offsetTimeSet_?(Option.empty[Set[OffsetTime]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.offsetTimeSet_?(Option.empty[Seq[Set[OffsetTime]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(offsetTime1, offsetTime2)))
        val b = (2, Some(Set(offsetTime2, offsetTime3, offsetTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetTimeSet_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimeSet_?.not(Some(Set(offsetTime1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet_?.not(Some(Set(offsetTime1, offsetTime2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.offsetTimeSet_?.not(Some(Set(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSet_?.not(Some(Seq(Set(offsetTime1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet_?.not(Some(Seq(Set(offsetTime1, offsetTime2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSet_?.not(Some(Seq(Set(offsetTime1, offsetTime2, offsetTime3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.offsetTimeSet_?.not(Some(Seq(Set(offsetTime1), Set(offsetTime2, offsetTime3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet_?.not(Some(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSet_?.not(Some(Seq(Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3, offsetTime4)))).query.get.map(_ ==> List())

          // Empty Sets are ignored
          _ <- Ns.i.a1.offsetTimeSet_?.not(Some(Seq(Set(offsetTime1, offsetTime2), Set.empty[OffsetTime]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSet_?.not(Some(Set.empty[OffsetTime])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet_?.not(Some(Seq.empty[Set[OffsetTime]])).query.get.map(_ ==> List(a, b))

          // Negation of None matches all asserted
          _ <- Ns.i.a1.offsetTimeSet_?.not(Option.empty[Set[OffsetTime]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet_?.not(Option.empty[Seq[Set[OffsetTime]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(offsetTime1, offsetTime2)))
        val b = (2, Some(Set(offsetTime2, offsetTime3, offsetTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetTimeSet_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.offsetTimeSet_?.has(Some(offsetTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_?.has(Some(offsetTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSet_?.has(Some(offsetTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet_?.has(Some(offsetTime3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSet_?.has(Some(Seq(offsetTime0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_?.has(Some(Seq(offsetTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSet_?.has(Some(Seq(offsetTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet_?.has(Some(Seq(offsetTime3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetTimeSet_?.has(Some(Seq(offsetTime1, offsetTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet_?.has(Some(Seq(offsetTime1, offsetTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet_?.has(Some(Seq(offsetTime2, offsetTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet_?.has(Some(Seq(offsetTime1, offsetTime2, offsetTime3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetTimeSet_?.has(Some(Seq.empty[OffsetTime])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.offsetTimeSet_?.has(Option.empty[OffsetTime]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.offsetTimeSet_?.has(Option.empty[Seq[OffsetTime]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(offsetTime1, offsetTime2)))
        val b = (2, Some(Set(offsetTime2, offsetTime3, offsetTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetTimeSet_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.offsetTimeSet_?.hasNo(Some(offsetTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet_?.hasNo(Some(offsetTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSet_?.hasNo(Some(offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_?.hasNo(Some(offsetTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSet_?.hasNo(Some(offsetTime4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSet_?.hasNo(Some(offsetTime5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSet_?.hasNo(Some(Seq(offsetTime0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet_?.hasNo(Some(Seq(offsetTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSet_?.hasNo(Some(Seq(offsetTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_?.hasNo(Some(Seq(offsetTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSet_?.hasNo(Some(Seq(offsetTime4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSet_?.hasNo(Some(Seq(offsetTime5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.offsetTimeSet_?.hasNo(Some(Seq(offsetTime1, offsetTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_?.hasNo(Some(Seq(offsetTime1, offsetTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_?.hasNo(Some(Seq(offsetTime1, offsetTime4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSet_?.hasNo(Some(Seq(offsetTime1, offsetTime5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.offsetTimeSet_?.hasNo(Some(Seq.empty[OffsetTime])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.offsetTimeSet_?.hasNo(Option.empty[OffsetTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSet_?.hasNo(Option.empty[Seq[OffsetTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}