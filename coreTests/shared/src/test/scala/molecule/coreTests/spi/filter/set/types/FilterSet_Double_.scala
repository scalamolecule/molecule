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