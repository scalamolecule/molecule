// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.time.OffsetDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_OffsetDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(offsetDateTime1, offsetDateTime2))
        val b = (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4))
        for {
          _ <- Ns.i.offsetDateTimeSet.insert(List(a, b)).transact

          _ <- Ns.i.a1.offsetDateTimeSet.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(offsetDateTime1, offsetDateTime2))
        val b = (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4))
        for {
          _ <- Ns.i.offsetDateTimeSet.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.offsetDateTimeSet(Set(offsetDateTime1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet(Set(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.offsetDateTimeSet(Set(offsetDateTime1, offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet(Seq(Set(offsetDateTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet(Seq(Set(offsetDateTime1, offsetDateTime2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet(Seq(Set(offsetDateTime1, offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.offsetDateTimeSet(Set(offsetDateTime1), Set(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet(Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet(Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet(Seq(Set(offsetDateTime1), Set(offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet(Seq(Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet(Seq(Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3, offsetDateTime4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetDateTimeSet(Set(offsetDateTime1, offsetDateTime2), Set.empty[OffsetDateTime]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet(Set.empty[OffsetDateTime], Set(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet(Set.empty[OffsetDateTime], Set.empty[OffsetDateTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet(Set.empty[OffsetDateTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet(Seq.empty[Set[OffsetDateTime]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet(Seq(Set.empty[OffsetDateTime])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.offsetDateTimeSet().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(offsetDateTime1, offsetDateTime2))
        val b = (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4))
        for {
          _ <- Ns.i.offsetDateTimeSet.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.offsetDateTimeSet.not(Set(offsetDateTime1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.not(Set(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.offsetDateTimeSet.not(Set(offsetDateTime1, offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet.not(Seq(Set(offsetDateTime1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.not(Seq(Set(offsetDateTime1, offsetDateTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSet.not(Seq(Set(offsetDateTime1, offsetDateTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSet.not(Seq(Set(offsetDateTime1, offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.offsetDateTimeSet.not(Set(offsetDateTime1), Set(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.not(Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSet.not(Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet.not(Seq(Set(offsetDateTime1), Set(offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.not(Seq(Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSet.not(Seq(Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3, offsetDateTime4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.offsetDateTimeSet.not(Seq(Set(offsetDateTime1, offsetDateTime2), Set.empty[OffsetDateTime])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSet.not(Set.empty[OffsetDateTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.not(Seq.empty[Set[OffsetDateTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(offsetDateTime1, offsetDateTime2))
        val b = (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4))
        for {
          _ <- Ns.i.offsetDateTimeSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.offsetDateTimeSet.has(offsetDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet.has(offsetDateTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet.has(offsetDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.has(offsetDateTime3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet.has(Seq(offsetDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet.has(Seq(offsetDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet.has(Seq(offsetDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.has(Seq(offsetDateTime3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetDateTimeSet.has(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.has(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.has(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.has(offsetDateTime1, offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet.has(Seq(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.has(Seq(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.has(Seq(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.has(Seq(offsetDateTime1, offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetDateTimeSet.has(Seq.empty[OffsetDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(offsetDateTime1, offsetDateTime2))
        val b = (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4))
        for {
          _ <- Ns.i.offsetDateTimeSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(offsetDateTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(offsetDateTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(offsetDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(offsetDateTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(offsetDateTime4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(offsetDateTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq(offsetDateTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq(offsetDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq(offsetDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq(offsetDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq(offsetDateTime4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq(offsetDateTime5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(offsetDateTime1, offsetDateTime4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(offsetDateTime1, offsetDateTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq(offsetDateTime1, offsetDateTime4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq(offsetDateTime1, offsetDateTime5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.offsetDateTimeSet.hasNo(Seq.empty[OffsetDateTime]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetDateTimeSet.insert(List(
            (1, Set(offsetDateTime1, offsetDateTime2)),
            (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // offsetDateTimeSet not asserted for i = 0
          _ <- Ns.i.a1.offsetDateTimeSet_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.offsetDateTimeSet_?.insert(List(
            (0, None),
            (1, Some(Set(offsetDateTime1, offsetDateTime2))),
            (2, Some(Set(offsetDateTime2, offsetDateTime3, offsetDateTime4))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.offsetDateTimeSet_().query.get.map(_ ==> List(0))

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.offsetDateTimeSet_(Set(offsetDateTime1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_(Set(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.offsetDateTimeSet_(Set(offsetDateTime1, offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet_(Seq(Set(offsetDateTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_(Seq(Set(offsetDateTime1, offsetDateTime2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSet_(Seq(Set(offsetDateTime1, offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.offsetDateTimeSet_(Set(offsetDateTime1), Set(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_(Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSet_(Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet_(Seq(Set(offsetDateTime1), Set(offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_(Seq(Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSet_(Seq(Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3, offsetDateTime4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetDateTimeSet_(Set(offsetDateTime1, offsetDateTime2), Set.empty[OffsetDateTime]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSet_(Set.empty[OffsetDateTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_(Seq.empty[Set[OffsetDateTime]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_(Seq(Set.empty[OffsetDateTime])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetDateTimeSet.insert(List(
            (1, Set(offsetDateTime1, offsetDateTime2)),
            (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.offsetDateTimeSet_.not(Set(offsetDateTime1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.not(Set(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.offsetDateTimeSet_.not(Set(offsetDateTime1, offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet_.not(Seq(Set(offsetDateTime1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.not(Seq(Set(offsetDateTime1, offsetDateTime2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeSet_.not(Seq(Set(offsetDateTime1, offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.offsetDateTimeSet_.not(Set(offsetDateTime1), Set(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.not(Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeSet_.not(Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet_.not(Seq(Set(offsetDateTime1), Set(offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.not(Seq(Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeSet_.not(Seq(Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3, offsetDateTime4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.offsetDateTimeSet_.not(Seq(Set(offsetDateTime1, offsetDateTime2), Set.empty[OffsetDateTime])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeSet_.not(Set.empty[OffsetDateTime]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.not(Seq.empty[Set[OffsetDateTime]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetDateTimeSet.insert(List(
            (1, Set(offsetDateTime1, offsetDateTime2)),
            (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet_.has(Seq(offsetDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_.has(Seq(offsetDateTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(Seq(offsetDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(Seq(offsetDateTime3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(offsetDateTime1, offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet_.has(Seq(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(Seq(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(Seq(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.has(Seq(offsetDateTime1, offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetDateTimeSet_.has(Seq.empty[OffsetDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetDateTimeSet.insert(List(
            (1, Set(offsetDateTime1, offsetDateTime2)),
            (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(offsetDateTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(offsetDateTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(offsetDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(offsetDateTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(offsetDateTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(offsetDateTime5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq(offsetDateTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq(offsetDateTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq(offsetDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq(offsetDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq(offsetDateTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq(offsetDateTime5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(offsetDateTime1, offsetDateTime4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(offsetDateTime1, offsetDateTime5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq(offsetDateTime1, offsetDateTime4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq(offsetDateTime1, offsetDateTime5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.offsetDateTimeSet_.hasNo(Seq.empty[OffsetDateTime]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(offsetDateTime1, offsetDateTime2)))
        val b = (2, Some(Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetDateTimeSet_?.insert(a, b, c).transact

          _ <- Ns.i.a1.offsetDateTimeSet_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no offsetDateTime value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.offsetDateTimeSet_?.query.get.map(_ ==> List(
            (2, Some(Set(offsetDateTime2, offsetDateTime3, offsetDateTime4))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(offsetDateTime1, offsetDateTime2)))
        val b = (2, Some(Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetDateTimeSet_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.offsetDateTimeSet_?(Some(Set(offsetDateTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_?(Some(Set(offsetDateTime1, offsetDateTime2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.offsetDateTimeSet_?(Some(Set(offsetDateTime1, offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet_?(Some(Seq(Set(offsetDateTime1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_?(Some(Seq(Set(offsetDateTime1, offsetDateTime2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet_?(Some(Seq(Set(offsetDateTime1, offsetDateTime2, offsetDateTime3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.offsetDateTimeSet_?(Some(Seq(Set(offsetDateTime1), Set(offsetDateTime2, offsetDateTime3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_?(Some(Seq(Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet_?(Some(Seq(Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetDateTimeSet_?(Some(Set.empty[OffsetDateTime])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_?(Some(Seq.empty[Set[OffsetDateTime]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_?(Some(Seq(Set.empty[OffsetDateTime]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.offsetDateTimeSet_?(Option.empty[Set[OffsetDateTime]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.offsetDateTimeSet_?(Option.empty[Seq[Set[OffsetDateTime]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(offsetDateTime1, offsetDateTime2)))
        val b = (2, Some(Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetDateTimeSet_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.offsetDateTimeSet_?.not(Some(Set(offsetDateTime1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.not(Some(Set(offsetDateTime1, offsetDateTime2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.offsetDateTimeSet_?.not(Some(Set(offsetDateTime1, offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet_?.not(Some(Seq(Set(offsetDateTime1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.not(Some(Seq(Set(offsetDateTime1, offsetDateTime2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.not(Some(Seq(Set(offsetDateTime1, offsetDateTime2, offsetDateTime3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.offsetDateTimeSet_?.not(Some(Seq(Set(offsetDateTime1), Set(offsetDateTime2, offsetDateTime3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.not(Some(Seq(Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.not(Some(Seq(Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)))).query.get.map(_ ==> List())

          // Empty Sets are ignored
          _ <- Ns.i.a1.offsetDateTimeSet_?.not(Some(Seq(Set(offsetDateTime1, offsetDateTime2), Set.empty[OffsetDateTime]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.not(Some(Set.empty[OffsetDateTime])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.not(Some(Seq.empty[Set[OffsetDateTime]])).query.get.map(_ ==> List(a, b))

          // Negation of None matches all asserted
          _ <- Ns.i.a1.offsetDateTimeSet_?.not(Option.empty[Set[OffsetDateTime]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.not(Option.empty[Seq[Set[OffsetDateTime]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(offsetDateTime1, offsetDateTime2)))
        val b = (2, Some(Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetDateTimeSet_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(offsetDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(offsetDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(offsetDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(offsetDateTime3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(Seq(offsetDateTime0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(Seq(offsetDateTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(Seq(offsetDateTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(Seq(offsetDateTime3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(Seq(offsetDateTime1, offsetDateTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(Seq(offsetDateTime1, offsetDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(Seq(offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(Seq(offsetDateTime1, offsetDateTime2, offsetDateTime3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Some(Seq.empty[OffsetDateTime])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Option.empty[OffsetDateTime]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.offsetDateTimeSet_?.has(Option.empty[Seq[OffsetDateTime]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(offsetDateTime1, offsetDateTime2)))
        val b = (2, Some(Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.offsetDateTimeSet_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(offsetDateTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(offsetDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(offsetDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(offsetDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(offsetDateTime4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(offsetDateTime5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq(offsetDateTime0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq(offsetDateTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq(offsetDateTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq(offsetDateTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq(offsetDateTime4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq(offsetDateTime5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq(offsetDateTime1, offsetDateTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq(offsetDateTime1, offsetDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq(offsetDateTime1, offsetDateTime4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq(offsetDateTime1, offsetDateTime5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Some(Seq.empty[OffsetDateTime])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Option.empty[OffsetDateTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSet_?.hasNo(Option.empty[Seq[OffsetDateTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}