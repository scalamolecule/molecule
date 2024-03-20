// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_Double_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        for {
          _ <- Ns.i.doubleSet.insert(List(a, b)).transact

          _ <- Ns.i.a1.doubleSet.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        for {
          _ <- Ns.i.doubleSet.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.doubleSet(Set(double1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet(Set(double1, double2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.doubleSet(Set(double1, double2, double3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubleSet(Seq(Set(double1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet(Seq(Set(double1, double2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSet(Seq(Set(double1, double2, double3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.doubleSet(Set(double1), Set(double2, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet(Set(double1, double2), Set(double2, double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSet(Set(double1, double2), Set(double2, double3, double4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubleSet(Seq(Set(double1), Set(double2, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet(Seq(Set(double1, double2), Set(double2, double3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSet(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.doubleSet(Set(double1, double2), Set.empty[Double]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSet(Set.empty[Double], Set(double1, double2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSet(Set.empty[Double], Set.empty[Double]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet(Set.empty[Double]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet(Seq.empty[Set[Double]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet(Seq(Set.empty[Double])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.doubleSet().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        for {
          _ <- Ns.i.doubleSet.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.doubleSet.not(Set(double1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet.not(Set(double1, double2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.doubleSet.not(Set(double1, double2, double3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubleSet.not(Seq(Set(double1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet.not(Seq(Set(double1, double2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleSet.not(Seq(Set(double1, double2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleSet.not(Seq(Set(double1, double2, double3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.doubleSet.not(Set(double1), Set(double2, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet.not(Set(double1, double2), Set(double2, double3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleSet.not(Set(double1, double2), Set(double2, double3, double4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubleSet.not(Seq(Set(double1), Set(double2, double3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet.not(Seq(Set(double1, double2), Set(double2, double3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleSet.not(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.doubleSet.not(Seq(Set(double1, double2), Set.empty[Double])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleSet.not(Set.empty[Double]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet.not(Seq.empty[Set[Double]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        for {
          _ <- Ns.i.doubleSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.doubleSet.has(double0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet.has(double1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSet.has(double2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet.has(double3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubleSet.has(Seq(double0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet.has(Seq(double1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSet.has(Seq(double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet.has(Seq(double3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.doubleSet.has(double1, double2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet.has(double1, double3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet.has(double2, double3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet.has(double1, double2, double3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubleSet.has(Seq(double1, double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet.has(Seq(double1, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet.has(Seq(double2, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet.has(Seq(double1, double2, double3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.doubleSet.has(Seq.empty[Double]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        for {
          _ <- Ns.i.doubleSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.doubleSet.hasNo(double0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet.hasNo(double1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleSet.hasNo(double2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet.hasNo(double3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSet.hasNo(double4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSet.hasNo(double5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubleSet.hasNo(Seq(double0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet.hasNo(Seq(double1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleSet.hasNo(Seq(double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet.hasNo(Seq(double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSet.hasNo(Seq(double4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSet.hasNo(Seq(double5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.doubleSet.hasNo(double1, double2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet.hasNo(double1, double3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet.hasNo(double1, double4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet.hasNo(double1, double5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubleSet.hasNo(Seq(double1, double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet.hasNo(Seq(double1, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet.hasNo(Seq(double1, double4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet.hasNo(Seq(double1, double5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.doubleSet.hasNo(Seq.empty[Double]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.doubleSet.insert(List(
            (1, Set(double1, double2)),
            (2, Set(double2, double3, double4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // doubleSet not asserted for i = 0
          _ <- Ns.i.a1.doubleSet_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.doubleSet_?.insert(List(
            (0, None),
            (1, Some(Set(double1, double2))),
            (2, Some(Set(double2, double3, double4))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.doubleSet_().query.get.map(_ ==> List(0))

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.doubleSet_(Set(double1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_(Set(double1, double2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.doubleSet_(Set(double1, double2, double3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubleSet_(Seq(Set(double1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_(Seq(Set(double1, double2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleSet_(Seq(Set(double1, double2, double3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.doubleSet_(Set(double1), Set(double2, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_(Set(double1, double2), Set(double2, double3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleSet_(Set(double1, double2), Set(double2, double3, double4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.doubleSet_(Seq(Set(double1), Set(double2, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_(Seq(Set(double1, double2), Set(double2, double3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleSet_(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.doubleSet_(Set(double1, double2), Set.empty[Double]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleSet_(Set.empty[Double]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_(Seq.empty[Set[Double]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_(Seq(Set.empty[Double])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.doubleSet.insert(List(
            (1, Set(double1, double2)),
            (2, Set(double2, double3, double4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.doubleSet_.not(Set(double1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSet_.not(Set(double1, double2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.doubleSet_.not(Set(double1, double2, double3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.doubleSet_.not(Seq(Set(double1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSet_.not(Seq(Set(double1, double2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleSet_.not(Seq(Set(double1, double2, double3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.doubleSet_.not(Set(double1), Set(double2, double3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSet_.not(Set(double1, double2), Set(double2, double3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleSet_.not(Set(double1, double2), Set(double2, double3, double4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubleSet_.not(Seq(Set(double1), Set(double2, double3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSet_.not(Seq(Set(double1, double2), Set(double2, double3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleSet_.not(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.doubleSet_.not(Seq(Set(double1, double2), Set.empty[Double])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleSet_.not(Set.empty[Double]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSet_.not(Seq.empty[Set[Double]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.doubleSet.insert(List(
            (1, Set(double1, double2)),
            (2, Set(double2, double3, double4))
          )).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.doubleSet_.has(double0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_.has(double1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleSet_.has(double2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSet_.has(double3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.doubleSet_.has(Seq(double0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_.has(Seq(double1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleSet_.has(Seq(double2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSet_.has(Seq(double3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.doubleSet_.has(double1, double2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSet_.has(double1, double3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSet_.has(double2, double3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSet_.has(double1, double2, double3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.doubleSet_.has(Seq(double1, double2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSet_.has(Seq(double1, double3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSet_.has(Seq(double2, double3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSet_.has(Seq(double1, double2, double3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.doubleSet_.has(Seq.empty[Double]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.doubleSet.insert(List(
            (1, Set(double1, double2)),
            (2, Set(double2, double3, double4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.doubleSet_.hasNo(double0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSet_.hasNo(double1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleSet_.hasNo(double2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_.hasNo(double3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleSet_.hasNo(double4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleSet_.hasNo(double5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.doubleSet_.hasNo(Seq(double0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSet_.hasNo(Seq(double1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleSet_.hasNo(Seq(double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_.hasNo(Seq(double3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleSet_.hasNo(Seq(double4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleSet_.hasNo(Seq(double5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.doubleSet_.hasNo(double1, double2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_.hasNo(double1, double3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_.hasNo(double1, double4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_.hasNo(double1, double5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.doubleSet_.hasNo(Seq(double1, double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_.hasNo(Seq(double1, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_.hasNo(Seq(double1, double4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_.hasNo(Seq(double1, double5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.doubleSet_.hasNo(Seq.empty[Double]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        for {
          _ <- Ns.i.doubleSet_?.insert(a, b, c).transact

          _ <- Ns.i.a1.doubleSet_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no double value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.doubleSet_?.query.get.map(_ ==> List(
            (2, Some(Set(double2, double3, double4))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        for {
          _ <- Ns.i.doubleSet_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.doubleSet_?(Some(Set(double1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_?(Some(Set(double1, double2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.doubleSet_?(Some(Set(double1, double2, double3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubleSet_?(Some(Seq(Set(double1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_?(Some(Seq(Set(double1, double2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSet_?(Some(Seq(Set(double1, double2, double3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.doubleSet_?(Some(Seq(Set(double1), Set(double2, double3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_?.apply(Some(Seq(Set(double1, double2), Set(double2, double3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSet_?(Some(Seq(Set(double1, double2), Set(double2, double3, double4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.doubleSet_?(Some(Set.empty[Double])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_?(Some(Seq.empty[Set[Double]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_?(Some(Seq(Set.empty[Double]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.doubleSet_?(Option.empty[Set[Double]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.doubleSet_?(Option.empty[Seq[Set[Double]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        for {
          _ <- Ns.i.doubleSet_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.doubleSet_?.not(Some(Set(double1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet_?.not(Some(Set(double1, double2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.doubleSet_?.not(Some(Set(double1, double2, double3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubleSet_?.not(Some(Seq(Set(double1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet_?.not(Some(Seq(Set(double1, double2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleSet_?.not(Some(Seq(Set(double1, double2, double3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.doubleSet_?.not(Some(Seq(Set(double1), Set(double2, double3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet_?.not(Some(Seq(Set(double1, double2), Set(double2, double3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleSet_?.not(Some(Seq(Set(double1, double2), Set(double2, double3, double4)))).query.get.map(_ ==> List())

          // Empty Sets are ignored
          _ <- Ns.i.a1.doubleSet_?.not(Some(Seq(Set(double1, double2), Set.empty[Double]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleSet_?.not(Some(Set.empty[Double])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet_?.not(Some(Seq.empty[Set[Double]])).query.get.map(_ ==> List(a, b))

          // Negation of None matches all asserted
          _ <- Ns.i.a1.doubleSet_?.not(Option.empty[Set[Double]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet_?.not(Option.empty[Seq[Set[Double]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        for {
          _ <- Ns.i.doubleSet_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.doubleSet_?.has(Some(double0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_?.has(Some(double1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSet_?.has(Some(double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet_?.has(Some(double3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubleSet_?.has(Some(Seq(double0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_?.has(Some(Seq(double1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSet_?.has(Some(Seq(double2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet_?.has(Some(Seq(double3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.doubleSet_?.has(Some(Seq(double1, double2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet_?.has(Some(Seq(double1, double3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet_?.has(Some(Seq(double2, double3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet_?.has(Some(Seq(double1, double2, double3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.doubleSet_?.has(Some(Seq.empty[Double])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.doubleSet_?.has(Option.empty[Double]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.doubleSet_?.has(Option.empty[Seq[Double]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        for {
          _ <- Ns.i.doubleSet_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.doubleSet_?.hasNo(Some(double0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet_?.hasNo(Some(double1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleSet_?.hasNo(Some(double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_?.hasNo(Some(double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSet_?.hasNo(Some(double4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSet_?.hasNo(Some(double5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubleSet_?.hasNo(Some(Seq(double0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet_?.hasNo(Some(Seq(double1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleSet_?.hasNo(Some(Seq(double2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_?.hasNo(Some(Seq(double3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSet_?.hasNo(Some(Seq(double4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSet_?.hasNo(Some(Seq(double5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.doubleSet_?.hasNo(Some(Seq(double1, double2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_?.hasNo(Some(Seq(double1, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_?.hasNo(Some(Seq(double1, double4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSet_?.hasNo(Some(Seq(double1, double5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.doubleSet_?.hasNo(Some(Seq.empty[Double])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.doubleSet_?.hasNo(Option.empty[Double]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSet_?.hasNo(Option.empty[Seq[Double]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}