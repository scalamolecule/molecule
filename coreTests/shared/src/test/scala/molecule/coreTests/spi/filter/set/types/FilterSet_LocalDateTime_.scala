// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.time.LocalDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_LocalDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(localDateTime1, localDateTime2))
        val b = (2, Set(localDateTime2, localDateTime3, localDateTime4))
        for {
          _ <- Ns.i.localDateTimeSet.insert(List(a, b)).transact

          _ <- Ns.i.a1.localDateTimeSet.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(localDateTime1, localDateTime2))
        val b = (2, Set(localDateTime2, localDateTime3, localDateTime4))
        for {
          _ <- Ns.i.localDateTimeSet.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.localDateTimeSet(Set(localDateTime1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet(Set(localDateTime1, localDateTime2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.localDateTimeSet(Set(localDateTime1, localDateTime2, localDateTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localDateTimeSet(Seq(Set(localDateTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet(Seq(Set(localDateTime1, localDateTime2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSet(Seq(Set(localDateTime1, localDateTime2, localDateTime3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.localDateTimeSet(Set(localDateTime1), Set(localDateTime2, localDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet(Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSet(Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3, localDateTime4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateTimeSet(Seq(Set(localDateTime1), Set(localDateTime2, localDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet(Seq(Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSet(Seq(Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3, localDateTime4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.localDateTimeSet(Set(localDateTime1, localDateTime2), Set.empty[LocalDateTime]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSet(Set.empty[LocalDateTime], Set(localDateTime1, localDateTime2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSet(Set.empty[LocalDateTime], Set.empty[LocalDateTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet(Set.empty[LocalDateTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet(Seq.empty[Set[LocalDateTime]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet(Seq(Set.empty[LocalDateTime])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.localDateTimeSet().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(localDateTime1, localDateTime2))
        val b = (2, Set(localDateTime2, localDateTime3, localDateTime4))
        for {
          _ <- Ns.i.localDateTimeSet.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.localDateTimeSet.not(Set(localDateTime1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet.not(Set(localDateTime1, localDateTime2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.localDateTimeSet.not(Set(localDateTime1, localDateTime2, localDateTime3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateTimeSet.not(Seq(Set(localDateTime1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet.not(Seq(Set(localDateTime1, localDateTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeSet.not(Seq(Set(localDateTime1, localDateTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeSet.not(Seq(Set(localDateTime1, localDateTime2, localDateTime3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.localDateTimeSet.not(Set(localDateTime1), Set(localDateTime2, localDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet.not(Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeSet.not(Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3, localDateTime4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localDateTimeSet.not(Seq(Set(localDateTime1), Set(localDateTime2, localDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet.not(Seq(Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeSet.not(Seq(Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3, localDateTime4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.localDateTimeSet.not(Seq(Set(localDateTime1, localDateTime2), Set.empty[LocalDateTime])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeSet.not(Set.empty[LocalDateTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet.not(Seq.empty[Set[LocalDateTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(localDateTime1, localDateTime2))
        val b = (2, Set(localDateTime2, localDateTime3, localDateTime4))
        for {
          _ <- Ns.i.localDateTimeSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.localDateTimeSet.has(localDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet.has(localDateTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSet.has(localDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet.has(localDateTime3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localDateTimeSet.has(Seq(localDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet.has(Seq(localDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSet.has(Seq(localDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet.has(Seq(localDateTime3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localDateTimeSet.has(localDateTime1, localDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet.has(localDateTime1, localDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet.has(localDateTime2, localDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet.has(localDateTime1, localDateTime2, localDateTime3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateTimeSet.has(Seq(localDateTime1, localDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet.has(Seq(localDateTime1, localDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet.has(Seq(localDateTime2, localDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet.has(Seq(localDateTime1, localDateTime2, localDateTime3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.localDateTimeSet.has(Seq.empty[LocalDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(localDateTime1, localDateTime2))
        val b = (2, Set(localDateTime2, localDateTime3, localDateTime4))
        for {
          _ <- Ns.i.localDateTimeSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.localDateTimeSet.hasNo(localDateTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet.hasNo(localDateTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeSet.hasNo(localDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet.hasNo(localDateTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSet.hasNo(localDateTime4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSet.hasNo(localDateTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateTimeSet.hasNo(Seq(localDateTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet.hasNo(Seq(localDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeSet.hasNo(Seq(localDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet.hasNo(Seq(localDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSet.hasNo(Seq(localDateTime4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSet.hasNo(Seq(localDateTime5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.localDateTimeSet.hasNo(localDateTime1, localDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet.hasNo(localDateTime1, localDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet.hasNo(localDateTime1, localDateTime4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet.hasNo(localDateTime1, localDateTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localDateTimeSet.hasNo(Seq(localDateTime1, localDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet.hasNo(Seq(localDateTime1, localDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet.hasNo(Seq(localDateTime1, localDateTime4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet.hasNo(Seq(localDateTime1, localDateTime5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.localDateTimeSet.hasNo(Seq.empty[LocalDateTime]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localDateTimeSet.insert(List(
            (1, Set(localDateTime1, localDateTime2)),
            (2, Set(localDateTime2, localDateTime3, localDateTime4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // localDateTimeSet not asserted for i = 0
          _ <- Ns.i.a1.localDateTimeSet_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.localDateTimeSet_?.insert(List(
            (0, None),
            (1, Some(Set(localDateTime1, localDateTime2))),
            (2, Some(Set(localDateTime2, localDateTime3, localDateTime4))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.localDateTimeSet_().query.get.map(_ ==> List(0))

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.localDateTimeSet_(Set(localDateTime1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_(Set(localDateTime1, localDateTime2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.localDateTimeSet_(Set(localDateTime1, localDateTime2, localDateTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localDateTimeSet_(Seq(Set(localDateTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_(Seq(Set(localDateTime1, localDateTime2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeSet_(Seq(Set(localDateTime1, localDateTime2, localDateTime3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.localDateTimeSet_(Set(localDateTime1), Set(localDateTime2, localDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_(Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeSet_(Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3, localDateTime4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localDateTimeSet_(Seq(Set(localDateTime1), Set(localDateTime2, localDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_(Seq(Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeSet_(Seq(Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3, localDateTime4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.localDateTimeSet_(Set(localDateTime1, localDateTime2), Set.empty[LocalDateTime]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeSet_(Set.empty[LocalDateTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_(Seq.empty[Set[LocalDateTime]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_(Seq(Set.empty[LocalDateTime])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localDateTimeSet.insert(List(
            (1, Set(localDateTime1, localDateTime2)),
            (2, Set(localDateTime2, localDateTime3, localDateTime4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.localDateTimeSet_.not(Set(localDateTime1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSet_.not(Set(localDateTime1, localDateTime2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.localDateTimeSet_.not(Set(localDateTime1, localDateTime2, localDateTime3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localDateTimeSet_.not(Seq(Set(localDateTime1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSet_.not(Seq(Set(localDateTime1, localDateTime2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeSet_.not(Seq(Set(localDateTime1, localDateTime2, localDateTime3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.localDateTimeSet_.not(Set(localDateTime1), Set(localDateTime2, localDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSet_.not(Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeSet_.not(Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3, localDateTime4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localDateTimeSet_.not(Seq(Set(localDateTime1), Set(localDateTime2, localDateTime3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSet_.not(Seq(Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeSet_.not(Seq(Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3, localDateTime4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.localDateTimeSet_.not(Seq(Set(localDateTime1, localDateTime2), Set.empty[LocalDateTime])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeSet_.not(Set.empty[LocalDateTime]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSet_.not(Seq.empty[Set[LocalDateTime]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localDateTimeSet.insert(List(
            (1, Set(localDateTime1, localDateTime2)),
            (2, Set(localDateTime2, localDateTime3, localDateTime4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.localDateTimeSet_.has(localDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_.has(localDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeSet_.has(localDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSet_.has(localDateTime3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localDateTimeSet_.has(Seq(localDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_.has(Seq(localDateTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeSet_.has(Seq(localDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSet_.has(Seq(localDateTime3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localDateTimeSet_.has(localDateTime1, localDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSet_.has(localDateTime1, localDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSet_.has(localDateTime2, localDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSet_.has(localDateTime1, localDateTime2, localDateTime3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localDateTimeSet_.has(Seq(localDateTime1, localDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSet_.has(Seq(localDateTime1, localDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSet_.has(Seq(localDateTime2, localDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSet_.has(Seq(localDateTime1, localDateTime2, localDateTime3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.localDateTimeSet_.has(Seq.empty[LocalDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localDateTimeSet.insert(List(
            (1, Set(localDateTime1, localDateTime2)),
            (2, Set(localDateTime2, localDateTime3, localDateTime4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(localDateTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(localDateTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(localDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(localDateTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(localDateTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(localDateTime5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(Seq(localDateTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(Seq(localDateTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(Seq(localDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(Seq(localDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(Seq(localDateTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(Seq(localDateTime5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(localDateTime1, localDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(localDateTime1, localDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(localDateTime1, localDateTime4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(localDateTime1, localDateTime5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(Seq(localDateTime1, localDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(Seq(localDateTime1, localDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(Seq(localDateTime1, localDateTime4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(Seq(localDateTime1, localDateTime5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.localDateTimeSet_.hasNo(Seq.empty[LocalDateTime]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(localDateTime1, localDateTime2)))
        val b = (2, Some(Set(localDateTime2, localDateTime3, localDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localDateTimeSet_?.insert(a, b, c).transact

          _ <- Ns.i.a1.localDateTimeSet_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no localDateTime value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.localDateTimeSet_?.query.get.map(_ ==> List(
            (2, Some(Set(localDateTime2, localDateTime3, localDateTime4))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(localDateTime1, localDateTime2)))
        val b = (2, Some(Set(localDateTime2, localDateTime3, localDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localDateTimeSet_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.localDateTimeSet_?(Some(Set(localDateTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_?(Some(Set(localDateTime1, localDateTime2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.localDateTimeSet_?(Some(Set(localDateTime1, localDateTime2, localDateTime3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.localDateTimeSet_?(Some(Seq(Set(localDateTime1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_?(Some(Seq(Set(localDateTime1, localDateTime2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSet_?(Some(Seq(Set(localDateTime1, localDateTime2, localDateTime3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.localDateTimeSet_?(Some(Seq(Set(localDateTime1), Set(localDateTime2, localDateTime3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_?(Some(Seq(Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSet_?(Some(Seq(Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3, localDateTime4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.localDateTimeSet_?(Some(Set.empty[LocalDateTime])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_?(Some(Seq.empty[Set[LocalDateTime]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_?(Some(Seq(Set.empty[LocalDateTime]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.localDateTimeSet_?(Option.empty[Set[LocalDateTime]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.localDateTimeSet_?(Option.empty[Seq[Set[LocalDateTime]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(localDateTime1, localDateTime2)))
        val b = (2, Some(Set(localDateTime2, localDateTime3, localDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localDateTimeSet_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.localDateTimeSet_?.not(Some(Set(localDateTime1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet_?.not(Some(Set(localDateTime1, localDateTime2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.localDateTimeSet_?.not(Some(Set(localDateTime1, localDateTime2, localDateTime3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateTimeSet_?.not(Some(Seq(Set(localDateTime1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet_?.not(Some(Seq(Set(localDateTime1, localDateTime2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeSet_?.not(Some(Seq(Set(localDateTime1, localDateTime2, localDateTime3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.localDateTimeSet_?.not(Some(Seq(Set(localDateTime1), Set(localDateTime2, localDateTime3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet_?.not(Some(Seq(Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeSet_?.not(Some(Seq(Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3, localDateTime4)))).query.get.map(_ ==> List())

          // Empty Sets are ignored
          _ <- Ns.i.a1.localDateTimeSet_?.not(Some(Seq(Set(localDateTime1, localDateTime2), Set.empty[LocalDateTime]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeSet_?.not(Some(Set.empty[LocalDateTime])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet_?.not(Some(Seq.empty[Set[LocalDateTime]])).query.get.map(_ ==> List(a, b))

          // Negation of None matches all asserted
          _ <- Ns.i.a1.localDateTimeSet_?.not(Option.empty[Set[LocalDateTime]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet_?.not(Option.empty[Seq[Set[LocalDateTime]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(localDateTime1, localDateTime2)))
        val b = (2, Some(Set(localDateTime2, localDateTime3, localDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localDateTimeSet_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.localDateTimeSet_?.has(Some(localDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_?.has(Some(localDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSet_?.has(Some(localDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet_?.has(Some(localDateTime3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localDateTimeSet_?.has(Some(Seq(localDateTime0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_?.has(Some(Seq(localDateTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSet_?.has(Some(Seq(localDateTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet_?.has(Some(Seq(localDateTime3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localDateTimeSet_?.has(Some(Seq(localDateTime1, localDateTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet_?.has(Some(Seq(localDateTime1, localDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet_?.has(Some(Seq(localDateTime2, localDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet_?.has(Some(Seq(localDateTime1, localDateTime2, localDateTime3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.localDateTimeSet_?.has(Some(Seq.empty[LocalDateTime])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.localDateTimeSet_?.has(Option.empty[LocalDateTime]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.localDateTimeSet_?.has(Option.empty[Seq[LocalDateTime]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(localDateTime1, localDateTime2)))
        val b = (2, Some(Set(localDateTime2, localDateTime3, localDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localDateTimeSet_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.localDateTimeSet_?.hasNo(Some(localDateTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet_?.hasNo(Some(localDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeSet_?.hasNo(Some(localDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_?.hasNo(Some(localDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSet_?.hasNo(Some(localDateTime4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSet_?.hasNo(Some(localDateTime5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateTimeSet_?.hasNo(Some(Seq(localDateTime0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet_?.hasNo(Some(Seq(localDateTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeSet_?.hasNo(Some(Seq(localDateTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_?.hasNo(Some(Seq(localDateTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSet_?.hasNo(Some(Seq(localDateTime4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSet_?.hasNo(Some(Seq(localDateTime5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.localDateTimeSet_?.hasNo(Some(Seq(localDateTime1, localDateTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_?.hasNo(Some(Seq(localDateTime1, localDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_?.hasNo(Some(Seq(localDateTime1, localDateTime4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSet_?.hasNo(Some(Seq(localDateTime1, localDateTime5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.localDateTimeSet_?.hasNo(Some(Seq.empty[LocalDateTime])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.localDateTimeSet_?.hasNo(Option.empty[LocalDateTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSet_?.hasNo(Option.empty[Seq[LocalDateTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}