// GENERATED CODE ********************************
package molecule.coreTests.compliance.filter.set

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
          _ <- Ns.i.doubles.insert(List(a, b)).transact

          _ <- Ns.i.a1.doubles.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        for {
          _ <- Ns.i.doubles.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.doubles(Set(double1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles(Set(double1, double2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.doubles(Set(double2, double1)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.doubles(Set(double1, double2, double3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubles(Seq(Set(double1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles(Seq(Set(double1, double2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles(Seq(Set(double2, double1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles(Seq(Set(double1, double2, double3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.doubles(Set(double1), Set(double2, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles(Set(double1, double2), Set(double2, double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles(Set(double2, double1), Set(double4, double3, double2)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubles(Seq(Set(double1), Set(double2, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles(Seq(Set(double1, double2), Set(double2, double3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles(Seq(Set(double2, double1), Set(double4, double3, double2))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.doubles(Set(double1, double2), Set.empty[Double]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles(Set.empty[Double], Set(double2, double1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles(Set.empty[Double], Set.empty[Double]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles(Set.empty[Double]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles(Seq.empty[Set[Double]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles(Seq(Set.empty[Double])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        for {
          _ <- Ns.i.doubles.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.doubles.not(Set(double1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.not(Set(double1, double2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.doubles.not(Set(double2, double1)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.doubles.not(Set(double1, double2, double3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubles.not(Seq(Set(double1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.not(Seq(Set(double1, double2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.not(Seq(Set(double2, double1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.not(Seq(Set(double1, double2, double3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.doubles.not(Set(double1), Set(double2, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.not(Set(double1, double2), Set(double2, double3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.not(Set(double2, double1), Set(double4, double3, double2)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubles.not(Seq(Set(double1), Set(double2, double3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.not(Seq(Set(double1, double2), Set(double2, double3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.not(Seq(Set(double2, double1), Set(double4, double3, double2))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.doubles.not(Seq(Set(double1, double2), Set.empty[Double])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.not(Set.empty[Double]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.not(Seq.empty[Set[Double]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        for {
          _ <- Ns.i.doubles.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.doubles.has(double0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.has(double1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.has(double2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.has(double3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubles.has(Seq(double0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.has(Seq(double1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.has(Seq(double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.has(Seq(double3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.doubles.has(double1, double2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.has(double1, double3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.has(double2, double3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.has(double1, double2, double3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubles.has(Seq(double1, double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.has(Seq(double1, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.has(Seq(double2, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.has(Seq(double1, double2, double3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.doubles.has(Set(double1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.has(Set(double1, double2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.has(Set(double1, double2, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.has(Set(double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.has(Set(double2, double3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.has(Set(double2, double3, double4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubles.has(Seq(Set(double1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.has(Seq(Set(double1, double2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.has(Seq(Set(double1, double2, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.has(Seq(Set(double2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.has(Seq(Set(double2, double3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.has(Seq(Set(double2, double3, double4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.doubles.has(Set(double1, double2), Set(double0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.has(Set(double1, double2), Set(double0, double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.has(Set(double1, double2), Set(double2, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.has(Set(double1, double2), Set(double2, double3, double4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubles.has(Seq(Set(double1, double2), Set(double0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.has(Seq(Set(double1, double2), Set(double0, double3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.has(Seq(Set(double1, double2), Set(double2, double3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.has(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.doubles.has(Set(double1, double2), Set.empty[Double]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.has(Seq.empty[Double]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.has(Set.empty[Double]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.has(Seq.empty[Set[Double]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        for {
          _ <- Ns.i.doubles.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.doubles.hasNo(double0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.hasNo(double1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.hasNo(double2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.hasNo(double3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.hasNo(double4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.hasNo(double5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubles.hasNo(Seq(double0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.hasNo(Seq(double1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.hasNo(Seq(double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.hasNo(Seq(double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.hasNo(Seq(double4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.hasNo(Seq(double5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.doubles.hasNo(double1, double2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.hasNo(double1, double3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.hasNo(double1, double4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.hasNo(double1, double5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubles.hasNo(Seq(double1, double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.hasNo(Seq(double1, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.hasNo(Seq(double1, double4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.hasNo(Seq(double1, double5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.doubles.hasNo(Set(double1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.hasNo(Set(double1, double2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.hasNo(Set(double1, double2, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.hasNo(Set(double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.hasNo(Set(double2, double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.hasNo(Set(double2, double3, double4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.doubles.hasNo(Seq(Set(double1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.hasNo(Seq(Set(double1, double2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.hasNo(Seq(Set(double1, double2, double3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.hasNo(Seq(Set(double2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.hasNo(Seq(Set(double2, double3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.hasNo(Seq(Set(double2, double3, double4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.doubles.hasNo(Set(double1, double2), Set(double0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.hasNo(Set(double1, double2), Set(double0, double3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.hasNo(Set(double1, double2), Set(double2, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.hasNo(Set(double1, double2), Set(double2, double3, double4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubles.hasNo(Seq(Set(double1, double2), Set(double0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.hasNo(Seq(Set(double1, double2), Set(double0, double3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.hasNo(Seq(Set(double1, double2), Set(double2, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.hasNo(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.doubles.hasNo(Set(double1, double2), Set.empty[Double]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.hasNo(Seq.empty[Double]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.hasNo(Set.empty[Double]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.hasNo(Seq.empty[Set[Double]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.hasNo(Seq(Set.empty[Double])).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.doubles.insert(List(
            (1, Set(double1, double2)),
            (2, Set(double2, double3, double4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))
          _ <- Ns.i.a1.doubles_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.doubles_?.insert(List(
            (0, None),
            (1, Some(Set(double1, double2))),
            (2, Some(Set(double2, double3, double4))),
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.doubles_(Set(double1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_(Set(double1, double2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.doubles_(Set(double2, double1)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.doubles_(Set(double1, double2, double3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubles_(Seq(Set(double1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_(Seq(Set(double1, double2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_(Seq(Set(double2, double1))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_(Seq(Set(double1, double2, double3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.doubles_(Set(double1), Set(double2, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_(Set(double1, double2), Set(double2, double3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_(Set(double2, double1), Set(double4, double3, double2)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.doubles_(Seq(Set(double1), Set(double2, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_(Seq(Set(double1, double2), Set(double2, double3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_(Seq(Set(double2, double1), Set(double4, double3, double2))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.doubles_(Set(double1, double2), Set.empty[Double]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_(Set.empty[Double]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_(Seq.empty[Set[Double]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_(Seq(Set.empty[Double])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.doubles.insert(List(
            (1, Set(double1, double2)),
            (2, Set(double2, double3, double4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.doubles_.not(Set(double1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.not(Set(double1, double2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.doubles_.not(Set(double2, double1)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.doubles_.not(Set(double1, double2, double3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.doubles_.not(Seq(Set(double1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.not(Seq(Set(double1, double2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubles_.not(Seq(Set(double2, double1))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubles_.not(Seq(Set(double1, double2, double3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.doubles_.not(Set(double1), Set(double2, double3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.not(Set(double1, double2), Set(double2, double3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubles_.not(Set(double2, double1), Set(double4, double3, double2)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubles_.not(Seq(Set(double1), Set(double2, double3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.not(Seq(Set(double1, double2), Set(double2, double3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubles_.not(Seq(Set(double2, double1), Set(double4, double3, double2))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.doubles_.not(Seq(Set(double1, double2), Set.empty[Double])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubles_.not(Set.empty[Double]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.not(Seq.empty[Set[Double]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.doubles.insert(List(
            (1, Set(double1, double2)),
            (2, Set(double2, double3, double4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.doubles_.has(double0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.has(double1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_.has(double2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.has(double3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.doubles_.has(Seq(double0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.has(Seq(double1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_.has(Seq(double2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.has(Seq(double3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.doubles_.has(double1, double2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.has(double1, double3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.has(double2, double3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.has(double1, double2, double3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.doubles_.has(Seq(double1, double2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.has(Seq(double1, double3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.has(Seq(double2, double3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.has(Seq(double1, double2, double3)).query.get.map(_ ==> List(1, 2))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.doubles_.has(Set(double1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_.has(Set(double1, double2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_.has(Set(double1, double2, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.has(Set(double2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.has(Set(double2, double3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubles_.has(Set(double2, double3, double4)).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.doubles_.has(Seq(Set(double1))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_.has(Seq(Set(double1, double2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_.has(Seq(Set(double1, double2, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.has(Seq(Set(double2))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.has(Seq(Set(double2, double3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubles_.has(Seq(Set(double2, double3, double4))).query.get.map(_ ==> List(2))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.doubles_.has(Set(double1, double2), Set(double0)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_.has(Set(double1, double2), Set(double0, double3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_.has(Set(double1, double2), Set(double2, double3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.has(Set(double1, double2), Set(double2, double3, double4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.doubles_.has(Seq(Set(double1, double2), Set(double0))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_.has(Seq(Set(double1, double2), Set(double0, double3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_.has(Seq(Set(double1, double2), Set(double2, double3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.has(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.doubles_.has(Set(double1, double2), Set.empty[Double]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_.has(Seq.empty[Double]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.has(Set.empty[Double]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.has(Seq.empty[Set[Double]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.doubles.insert(List(
            (1, Set(double1, double2)),
            (2, Set(double2, double3, double4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.doubles_.hasNo(double0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.hasNo(double1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubles_.hasNo(double2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.hasNo(double3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_.hasNo(double4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_.hasNo(double5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.doubles_.hasNo(Seq(double0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.hasNo(Seq(double1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubles_.hasNo(Seq(double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.hasNo(Seq(double3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_.hasNo(Seq(double4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_.hasNo(Seq(double5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.doubles_.hasNo(double1, double2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.hasNo(double1, double3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.hasNo(double1, double4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.hasNo(double1, double5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.doubles_.hasNo(Seq(double1, double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.hasNo(Seq(double1, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.hasNo(Seq(double1, double4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.hasNo(Seq(double1, double5)).query.get.map(_ ==> List(2))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.doubles_.hasNo(Set(double1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubles_.hasNo(Set(double1, double2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubles_.hasNo(Set(double1, double2, double3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.hasNo(Set(double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.hasNo(Set(double2, double3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_.hasNo(Set(double2, double3, double4)).query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.doubles_.hasNo(Seq(Set(double1))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubles_.hasNo(Seq(Set(double1, double2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubles_.hasNo(Seq(Set(double1, double2, double3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.hasNo(Seq(Set(double2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.hasNo(Seq(Set(double2, double3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubles_.hasNo(Seq(Set(double2, double3, double4))).query.get.map(_ ==> List(1))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.doubles_.hasNo(Set(double1, double2), Set(double0)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubles_.hasNo(Set(double1, double2), Set(double0, double3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubles_.hasNo(Set(double1, double2), Set(double2, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.hasNo(Set(double1, double2), Set(double2, double3, double4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubles_.hasNo(Seq(Set(double1, double2), Set(double0))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubles_.hasNo(Seq(Set(double1, double2), Set(double0, double3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubles_.hasNo(Seq(Set(double1, double2), Set(double2, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.hasNo(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.doubles_.hasNo(Set(double1, double2), Set.empty[Double]).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubles_.hasNo(Seq.empty[Double]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.hasNo(Set.empty[Double]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.hasNo(Seq.empty[Set[Double]]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubles_.hasNo(Seq(Set.empty[Double])).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        for {
          _ <- Ns.i.doubles_?.insert(a, b, c).transact

          _ <- Ns.i.a1.doubles_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no double value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.doubles_?.query.get.map(_ ==> List(
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
          _ <- Ns.i.doubles_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.doubles_?(Some(Set(double1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?(Some(Set(double1, double2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.doubles_?(Some(Set(double2, double1))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.doubles_?(Some(Set(double1, double2, double3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubles_?(Some(Seq(Set(double1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?(Some(Seq(Set(double1, double2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?(Some(Seq(Set(double2, double1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?(Some(Seq(Set(double1, double2, double3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.doubles_?(Some(Seq(Set(double1), Set(double2, double3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?(Some(Seq(Set(double1, double2), Set(double2, double3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?(Some(Seq(Set(double2, double1), Set(double4, double3, double2)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.doubles_?(Some(Set.empty[Double])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?(Some(Seq.empty[Set[Double]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?(Some(Seq(Set.empty[Double]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.doubles_?(Option.empty[Set[Double]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.doubles_?(Option.empty[Seq[Set[Double]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        for {
          _ <- Ns.i.doubles_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.doubles_?.not(Some(Set(double1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.not(Some(Set(double1, double2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.doubles_?.not(Some(Set(double2, double1))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.doubles_?.not(Some(Set(double1, double2, double3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(Set(double1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(Set(double1, double2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(Set(double2, double1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(Set(double1, double2, double3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(Set(double1), Set(double2, double3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(Set(double1, double2), Set(double2, double3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(Set(double2, double1), Set(double4, double3, double2)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(Set(double1, double2), Set.empty[Double]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.not(Some(Set.empty[Double])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.not(Some(Seq.empty[Set[Double]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.doubles_?(Option.empty[Set[Double]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.doubles_?(Option.empty[Seq[Set[Double]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        for {
          _ <- Ns.i.doubles_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.doubles_?.has(Some(double0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.has(Some(double1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.has(Some(double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.has(Some(double3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubles_?.has(Some(Seq(double0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.has(Some(Seq(double1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.has(Some(Seq(double2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.has(Some(Seq(double3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.doubles_?.has(Some(Seq(double1, double2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.has(Some(Seq(double1, double3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.has(Some(Seq(double2, double3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.has(Some(Seq(double1, double2, double3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.doubles_?.has(Some(Set(double1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.has(Some(Set(double1, double2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.has(Some(Set(double1, double2, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.has(Some(Set(double2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.has(Some(Set(double2, double3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.has(Some(Set(double2, double3, double4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubles_?.has(Some(Seq(Set(double1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.has(Some(Seq(Set(double1, double2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.has(Some(Seq(Set(double1, double2, double3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.has(Some(Seq(Set(double2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.has(Some(Seq(Set(double2, double3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.has(Some(Seq(Set(double2, double3, double4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.doubles_?.has(Some(Seq(Set(double1, double2), Set(double0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.has(Some(Seq(Set(double1, double2), Set(double0, double3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.has(Some(Seq(Set(double1, double2), Set(double2, double3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.has(Some(Seq(Set(double1, double2), Set(double2, double3, double4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.doubles_?.has(Some(Seq.empty[Double])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.has(Some(Set.empty[Double])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.has(Some(Seq.empty[Set[Double]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.doubles_?.has(Option.empty[Double]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.doubles_?.has(Option.empty[Seq[Double]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.doubles_?.has(Option.empty[Set[Double]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.doubles_?.has(Option.empty[Seq[Set[Double]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        for {
          _ <- Ns.i.doubles_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.doubles_?.hasNo(Some(double0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(double1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.hasNo(Some(double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(double4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(double5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(double0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(double1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(double2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(double3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(double4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(double5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(double1, double2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(double1, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(double1, double4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(double1, double5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Set(double1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Set(double1, double2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Set(double1, double2, double3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Set(double2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Set(double2, double3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Set(double2, double3, double4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(Set(double1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(Set(double1, double2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(Set(double1, double2, double3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(Set(double2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(Set(double2, double3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(Set(double2, double3, double4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(Set(double1, double2), Set(double0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(Set(double1, double2), Set(double0, double3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(Set(double1, double2), Set(double2, double3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(Set(double1, double2), Set(double2, double3, double4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq.empty[Double])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Set.empty[Double])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq.empty[Set[Double]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.hasNo(Some(Seq(Set.empty[Double]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.doubles_?.hasNo(Option.empty[Double]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.hasNo(Option.empty[Seq[Double]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.hasNo(Option.empty[Set[Double]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.hasNo(Option.empty[Seq[Set[Double]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}